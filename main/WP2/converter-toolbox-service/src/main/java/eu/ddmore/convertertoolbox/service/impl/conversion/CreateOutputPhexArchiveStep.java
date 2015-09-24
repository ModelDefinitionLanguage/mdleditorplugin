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
 * Prepares output PHEX archive.
 * <p>
 * The output archive is based on the input archive. Then the output files from the conversion are added in.
 * The "main entry" in the output archive is set; this will be treated as the execution file in the scenario
 * where MIF is performing an execution on the archive. Finally the {@link Conversion} entity is updated with
 * appropriate references to the output archive.
 * <p>
 * Determining the "main entry" needs some further explanation. Currently it is done by by determining which
 * of the output Archive's Entries were not present in the input Archive, with any files matching the
 * injected {@link #resultFileNameExclusionPattern} ignored. But this is not very robust; in the case of
 * Monolix for example, several output files are generated, and the logic here isn't going to know that it
 * is the .mlxtran file (in a subfolder to complicate things even more!) that is the main output file from
 * the conversion. TODO: The proper solution is for the Converters themselves to specify which is the result file
 * from the conversion, e.g. by writing this out to the Conversion Report.
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
     * Entries were not present in the input Archive. Ignore any files matching the injected
     * {@link #resultFileNameExclusionPattern}.
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
