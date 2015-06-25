/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.service.impl.conversion;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;
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
    
    @Value("${cts.conversion.phex.resultFileNameExclusionPattern:.*\\.csv}")
    private String resultFileNameExclusionPattern;
    
    @Override
    public void execute(ConversionContext conversionContext) {
        // TODO remove this debug line
        LOG.info("Using resultFileNameExclusionPattern=" + resultFileNameExclusionPattern);
    
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

    private void prepareOutputArchive(Conversion conversion, File outputArchiveFile, File outputDir) {
        LOG.debug(String.format("Conversion [%s]: archiving %s to %s.",conversion.getId(),outputDir.getAbsolutePath(),outputArchiveFile.getAbsolutePath()));
        
        Archive inputArchive = archiveFactory.createArchive(conversion.getInputArchive());
        Archive outputArchive = archiveFactory.createArchive(outputArchiveFile);
        try {
            inputArchive.open();
            outputArchive.open();
            
            LOG.info("Adding directory contents into output archive: " + outputDir + " at " + FilenameUtils.getPathNoEndSeparator(conversion.getInputFileName()) + " into " + outputArchiveFile.getAbsolutePath());
            archiveFactory.createArchiveHelper(outputArchive).addDirectoryContents(outputDir, FilenameUtils.getPathNoEndSeparator(conversion.getInputFileName()));
            
            final Entry mainEntryForOutputArchive = attemptToDetermineMainOutputFromConversion(inputArchive.getEntries(), outputArchive.getEntries());
            LOG.info("Setting main entry for output archive to " + mainEntryForOutputArchive.getFilePath());
            outputArchive.setMainEntries(Arrays.asList(mainEntryForOutputArchive));
            
        } catch (ArchiveException e) {
            throw new RuntimeException("Could not add files to output archive", e);
        } finally {
            inputArchive.close();
            outputArchive.close();
        }
    }
    
    /**
     * Attempt to determine the main output from the conversion, by determining which of the output Archive's
     * Entries were not present in the input Archive. Ignore any files matching the {@link #resultFileNameExclusionPattern}.
     * <p>
     * @param inputEntries - {@link Collection} of {@link Entry}s that the Archive that is the input to the Conversion, contains
     * @param outputEntries - {@link Collection} of {@link Entry}s that the Archive that is the output from the Conversion, contains
     * @return {@link Entry}, which should be unique; if not then an arbitrary one is selected
     */
    private Entry attemptToDetermineMainOutputFromConversion(final Collection<Entry> inputEntries, final Collection<Entry> outputEntries) {
        final Map<String, Entry> inputEntriesKeyedByFilePath = new HashMap<String, Entry>();
        final Map<String, Entry> outputEntriesKeyedByFilePath = new HashMap<String, Entry>();
        for (final Entry e : inputEntries) {
            inputEntriesKeyedByFilePath.put(e.getFilePath(), e);
        }
        for (final Entry e : outputEntries) {
            if (!e.getFileName().matches(this.resultFileNameExclusionPattern)) {
                outputEntriesKeyedByFilePath.put(e.getFilePath(), e);
            }
        }
        final SetView<String> filePathsOnlyInOutputEntries = Sets.difference(outputEntriesKeyedByFilePath.keySet(), inputEntriesKeyedByFilePath.keySet());
        LOG.debug("Paths to files in output Archive that were not present in input Archive: " + filePathsOnlyInOutputEntries);
        Preconditions.checkState(!filePathsOnlyInOutputEntries.isEmpty(), "No output files were determined in the result of the conversion");
        if (filePathsOnlyInOutputEntries.size() > 1) {
            LOG.warn("More than one output file was generated by the conversion - it is arbitrary which one will be selected as the Main Entry for the resulting Archive");
        }
        // Arbitrarily pick one of the Entries to choose as the Main Entry - but there should only be one usually
        return outputEntriesKeyedByFilePath.get(filePathsOnlyInOutputEntries.iterator().next());
    }
   
    @VisibleForTesting
    void setResultFileNameExclusionPattern(String resultFileNameExclusionPattern) {
        this.resultFileNameExclusionPattern = resultFileNameExclusionPattern;
    }
}
