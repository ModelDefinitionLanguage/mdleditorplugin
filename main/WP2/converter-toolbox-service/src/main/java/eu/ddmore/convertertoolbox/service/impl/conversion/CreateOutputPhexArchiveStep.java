/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.service.impl.conversion;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.io.Files;

import eu.ddmore.archive.Archive;
import eu.ddmore.archive.ArchiveFactory;
import eu.ddmore.archive.Entry;
import eu.ddmore.archive.exception.ArchiveException;
import eu.ddmore.convertertoolbox.domain.internal.Conversion;
import eu.ddmore.convertertoolbox.service.impl.ConversionResourcesConvention;

/**
 * Prepares output PHEX archive
 */
@Order(200)
@Component
public class CreateOutputPhexArchiveStep implements ConversionStep {
    private static final Logger LOG = Logger.getLogger(CreateOutputPhexArchiveStep.class);
    
    @Autowired(required=true)
    private ArchiveFactory archiveFactory;
    
    @Value("${:.*%s.*(?<!csv)$}")
    private String resultFileNamePattern;
    
    @Override
    public void execute(ConversionContext conversionContext) {
        Preconditions.checkNotNull(conversionContext, "Conversion context was null");
        Preconditions.checkNotNull(conversionContext.getConversion(), "Conversion in Conversion Context was null");
        Conversion conversion = conversionContext.getConversion();
        File outputArchive = new File(conversion.getWorkingDirectory(),ConversionResourcesConvention.OUTPUTS_ARCHIVE_NAME);
        File inputArchive = new File(conversion.getWorkingDirectory(),ConversionResourcesConvention.INPUTS_ARCHIVE_NAME);
        File outputDir = new File(conversion.getWorkingDirectory(),ConversionResourcesConvention.OUTPUTS_DIRECTORY_NAME);
        try {
            // the output archive is based on input archive
            Files.copy(inputArchive, outputArchive);
        } catch (IOException e) {
            throw new RuntimeException("Could not copy input archive", e);
        }
        prepareOutputArchive(conversion, outputArchive, outputDir);
        
        conversion.setOutputArchive(outputArchive);
        conversion.setOutputFileSize(outputArchive.length());
    }

    private void prepareOutputArchive(Conversion conversion, File outputArchive, File outputDir) {
        LOG.debug(String.format("Conversion [%s]: archiving %s to %s.",conversion.getId(),outputDir.getAbsolutePath(),outputArchive.getAbsolutePath()));
        Archive archive = archiveFactory.createArchive(outputArchive);
        try {
            archive.open();
            LOG.info("Adding directory contents into output archive: " + outputDir + " at " + FilenameUtils.getPathNoEndSeparator(conversion.getInputFileName()));
            archiveFactory.createArchiveHelper(archive).addDirectoryContents(outputDir, FilenameUtils.getPathNoEndSeparator(conversion.getInputFileName()));
            Collection<Entry> matches = Collections2.filter(archive.getEntries(),new ResultEntryPredicate(conversion,resultFileNamePattern));
            Preconditions.checkState(matches.size()>0, String.format("There were no result file matches for input file %s", conversion.getInputFileName()));
            if(matches.size()>1) {
                LOG.warn(String.format("There were more than one main entry result candidate in the conversion result."));
            }
            archive.setMainEntries(matches);
        } catch (ArchiveException e) {
            throw new RuntimeException("Could not add files to output directory", e);
        } finally {
            archive.close();
        }
    }
    
    /**
    * Identifies if an Entry represents conversion result.
    * Currently it assumes (as FIS at the time of this writing) that the conversion output file is the input file with different extension.
    */
   private final class ResultEntryPredicate implements Predicate<Entry> {
       private final Pattern resultFilePathPattern;
       private final String inputFileName;
       private ResultEntryPredicate(Conversion conversion, String pattern) {
           resultFilePathPattern = Pattern.compile(
               String.format(pattern, Pattern.quote(FilenameUtils.removeExtension(conversion.getInputFileName())))
           );
           inputFileName = FilenameUtils.getName(conversion.getInputFileName());
       }

       public boolean apply(Entry entry) {
           // NB: Given a conversion.getInputFileName() of e.g. "inputFile.mdl" (i.e. no path prefix), if the archive happens to contain
           // multiple inputFile.xml (i.e. converted) files in subdirectories, these will all be matched. This is probably benign, and
           // in any case would be addressed by future work whereby the converters themselves will specify which files in the archive
           // are the result of the conversion.
           LOG.debug(String.format("Considering main entry candidate %s - matches %s", entry.getFilePath(), resultFilePathPattern.pattern()));
           return resultFilePathPattern.matcher(entry.getFilePath()).matches() && !entry.getFileName().equals(inputFileName);
       }
   }
   
   
    @VisibleForTesting
    void setResultFileNamePattern(String resultFileNamePattern) {
        this.resultFileNamePattern = resultFileNamePattern;
    }
}
