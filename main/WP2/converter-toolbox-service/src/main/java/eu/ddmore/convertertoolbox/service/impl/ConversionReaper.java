/*******************************************************************************
 * Copyright (C) 2002 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.service.impl;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;

import eu.ddmore.convertertoolbox.domain.Conversion;
import eu.ddmore.convertertoolbox.service.ConversionRepository;


/**
 * Component responsible for cleaning up old Conversions and data
 */
@Component
public class ConversionReaper {

    private static final Logger LOG = Logger.getLogger(ConversionReaper.class);
    
    @Autowired(required=true)
    private ConversionRepository conversionRepository;
    
    @Autowired(required=true)
    private ConversionRemover conversionRemover;
    
    @Value("${cts.conversionResultsAvailabilityTimeout}")
    private long conversionResultsAvailabilityTimeout = TimeUnit.HOURS.toMillis(2);

    @Value("${cts.workingDirectory}")
    private File workingDirectory;

    @Value("${cts.workingDirectory.identityFile:.converterToolboxService_wd}")
    private String identityFileName;
    
    @Scheduled(fixedRateString = "${cts.cleanupRate:300000}")
    public void performCleanup() {
        Collection<Conversion> forDeletion = conversionRepository.
                getConversionsCompletedEarlierThan(new Date().getTime()-conversionResultsAvailabilityTimeout);
        for(Conversion conversion : forDeletion) {
            try {
                conversionRemover.remove(conversion);
            } catch(Exception ex) {
                LOG.error("Error when trying to remove old conversion",ex);
            }
        }
    }

    @Required
    public void setConversionResultsAvailabilityTimeout(long conversionResultsAvailabilityTimeout) {
        this.conversionResultsAvailabilityTimeout = conversionResultsAvailabilityTimeout;
    }
    
    @PostConstruct
    public void prepareWorkingDirectory() {
        Preconditions.checkNotNull(workingDirectory, "Working directory can't be null");
        Preconditions.checkState(StringUtils.isNotBlank(workingDirectory.getPath()),"Working directory can't be blank");
        Preconditions.checkState(StringUtils.isNotBlank(identityFileName),"Identity file can't be blank");
        File identityFile = new File(workingDirectory,identityFileName);
        if(workingDirectory.exists()) {
            LOG.info(String.format("Cleaning up working directory %s", workingDirectory));
            cleanUpWorkingDirectory(workingDirectory, identityFile);
        } else {
            LOG.info(String.format("Working directory %s does not exist, attempting to create it.",workingDirectory));
            workingDirectory.mkdirs();
            if(!workingDirectory.exists()) {
                throw new RuntimeException(String.format("Could not create working directory %s", workingDirectory));
            }
            try {
                FileUtils.writeStringToFile(identityFile, "Converter toolbox identify file");
            } catch (IOException e) {
                throw new RuntimeException(String.format("Can't write to %s", workingDirectory));
            }
        }
    }

    private void cleanUpWorkingDirectory(File workingDirectory, File identityFile) {
        if(!workingDirectory.isDirectory()) {
            throw new IllegalStateException(String.format("The specified working directory path %s is not a directory!",workingDirectory.getAbsolutePath()));
        }
        File[] files = workingDirectory.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return !(".".equals(name)||"..".equals(name));
            }
            
        });
        if(files==null) {
            throw new IllegalStateException(String.format("Could not list files in %s", workingDirectory));
        }
        if(!identityFile.exists() && files.length>1) {
            throw new IllegalStateException("Invalid working directory structure, the %s file was not found in %s. "+
                                            "Make sure that the directory you specified is empty or doesn't exist.");
        }
        for(File f : files) {
            if(f.getName().equals(identityFileName)) {
                continue;
            }
            FileUtils.deleteQuietly(f);
        }
    }
}
