/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.service.impl.conversion;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.google.common.collect.Lists;
import com.google.common.io.Files;

import eu.ddmore.archive.Archive;
import eu.ddmore.archive.ArchiveFactory;
import eu.ddmore.archive.ArchiveHelper;
import eu.ddmore.archive.Entry;
import eu.ddmore.archive.exception.ArchiveException;
import eu.ddmore.convertertoolbox.api.conversion.Converter;
import eu.ddmore.convertertoolbox.domain.internal.Conversion;
import eu.ddmore.convertertoolbox.service.ConversionRepository;
import eu.ddmore.convertertoolbox.service.impl.ConversionResourcesConvention;

/**
 * Tests { @link CreateOutputPhexArchiveStep }
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Files.class)
public class CreateOutputPhexArchiveStepTest {
    @InjectMocks
    private CreateOutputPhexArchiveStep instance = new CreateOutputPhexArchiveStep();
    
    @Mock
    private ConversionRepository conversionRepository;
    
    @Mock
    private Conversion conversion;
    
    @Mock
    private Converter converter;
    
    @Mock
    private ArchiveFactory archiveFactory;
    
    @Mock
    private ArchiveHelper archiveHelper;
    
    @Mock
    private Archive archive;
    
    private ConversionContext conversionContext;

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();
    
    @Before
    public void setUp() {
        conversionContext = new ConversionContext(converter, conversion, conversionRepository);
        when(archiveFactory.createArchive(any(File.class))).thenReturn(archive);
        when(archiveFactory.createArchiveHelper(same(archive))).thenReturn(archiveHelper);
        instance.setResultFileNamePattern(".*%s.*(?<!csv)$");
    }
    
    @Test
    public void shouldCreateOutputArchive() throws IOException, ArchiveException {
        setupMockArchiveEntries("");
    
        PowerMockito.mockStatic(Files.class);
        File workingDirectory = tempFolder.newFolder();
        File inputArchive = new File(workingDirectory,ConversionResourcesConvention.INPUTS_ARCHIVE_NAME);
        File outputArchive = new File(workingDirectory,ConversionResourcesConvention.OUTPUTS_ARCHIVE_NAME);
        File outputDirectory = new File(workingDirectory,ConversionResourcesConvention.OUTPUTS_DIRECTORY_NAME);
        when(conversion.getWorkingDirectory()).thenReturn(workingDirectory);
        when(conversion.getInputFileName()).thenReturn("inputFile.txt");
        instance.execute(conversionContext);
        @SuppressWarnings("rawtypes")
        ArgumentCaptor<Collection> mainEntriesCaptor = ArgumentCaptor.forClass(Collection.class);
        
        verify(archiveHelper).addDirectoryContents(eq(outputDirectory), eq(""));
        verify(conversion).setOutputArchive(eq(outputArchive));
        verify(conversion).setOutputFileSize(any(Long.class));
        verify(archive).open();
        verify(archive).close();
        verify(archive).setMainEntries(mainEntriesCaptor.capture());
        assertEquals(2, mainEntriesCaptor.getValue().size()); // Should be 1 entry but see in-code comment in ResultEntryPredicate.apply()
        PowerMockito.verifyStatic();
        Files.copy(inputArchive, outputArchive);
    }
    
    @Test
    public void shouldCreateOutputArchiveWhereModelFileInSubdirectoryUnixFormatPath() throws IOException, ArchiveException {
        setupMockArchiveEntries("this/is/path/to/");
        
        PowerMockito.mockStatic(Files.class);
        File workingDirectory = tempFolder.newFolder();
        File inputArchive = new File(workingDirectory,ConversionResourcesConvention.INPUTS_ARCHIVE_NAME);
        File outputArchive = new File(workingDirectory,ConversionResourcesConvention.OUTPUTS_ARCHIVE_NAME);
        File outputDirectory = new File(workingDirectory,ConversionResourcesConvention.OUTPUTS_DIRECTORY_NAME);
        when(conversion.getWorkingDirectory()).thenReturn(workingDirectory);
        when(conversion.getInputFileName()).thenReturn("this/is/path/to/inputFile.txt");
        instance.execute(conversionContext);
        @SuppressWarnings("rawtypes")
        ArgumentCaptor<Collection> mainEntriesCaptor = ArgumentCaptor.forClass(Collection.class);
        
        verify(archiveHelper).addDirectoryContents(eq(outputDirectory), eq("this/is/path/to"));
        verify(conversion).setOutputArchive(eq(outputArchive));
        verify(conversion).setOutputFileSize(any(Long.class));
        verify(archive).open();
        verify(archive).close();
        verify(archive).setMainEntries(mainEntriesCaptor.capture());
        assertEquals(1, mainEntriesCaptor.getValue().size());
        PowerMockito.verifyStatic();
        Files.copy(inputArchive, outputArchive);
    }
    
    @Test
    public void shouldCreateOutputArchiveWhereModelFileInSubdirectoryWindowsFormatPath() throws IOException, ArchiveException {
        setupMockArchiveEntries("this\\is\\path\\to\\");
    
        PowerMockito.mockStatic(Files.class);
        File workingDirectory = tempFolder.newFolder();
        File inputArchive = new File(workingDirectory,ConversionResourcesConvention.INPUTS_ARCHIVE_NAME);
        File outputArchive = new File(workingDirectory,ConversionResourcesConvention.OUTPUTS_ARCHIVE_NAME);
        File outputDirectory = new File(workingDirectory,ConversionResourcesConvention.OUTPUTS_DIRECTORY_NAME);
        when(conversion.getWorkingDirectory()).thenReturn(workingDirectory);
        when(conversion.getInputFileName()).thenReturn("this\\is\\path\\to\\inputFile.txt");
        instance.execute(conversionContext);
        @SuppressWarnings("rawtypes")
        ArgumentCaptor<Collection> mainEntriesCaptor = ArgumentCaptor.forClass(Collection.class);
        
        verify(archiveHelper).addDirectoryContents(eq(outputDirectory), eq("this\\is\\path\\to"));
        verify(conversion).setOutputArchive(eq(outputArchive));
        verify(conversion).setOutputFileSize(any(Long.class));
        verify(archive).open();
        verify(archive).close();
        verify(archive).setMainEntries(mainEntriesCaptor.capture());
        assertEquals(1, mainEntriesCaptor.getValue().size());
        PowerMockito.verifyStatic();
        Files.copy(inputArchive, outputArchive);
    }
    
    private void setupMockArchiveEntries(final String pathPrefix) {
        List<Entry> entries = Lists.newArrayList();
        Entry entry = mock(Entry.class);
        when(entry.getFileName()).thenReturn("inputFile.txt");
        when(entry.getFilePath()).thenReturn(pathPrefix + "inputFile.txt");
        entries.add(entry);
        entry = mock(Entry.class);
        when(entry.getFileName()).thenReturn("inputFile.other");
        when(entry.getFilePath()).thenReturn(pathPrefix + "inputFile.other");
        entries.add(entry);
        entry = mock(Entry.class);
        when(entry.getFileName()).thenReturn("inputFile.other");
        when(entry.getFilePath()).thenReturn(pathPrefix + "inputFile.csv");
        entries.add(entry);
        entry = mock(Entry.class);
        when(entry.getFileName()).thenReturn("inputFile.other");
        when(entry.getFilePath()).thenReturn("/some/other/path/to/inputFile.txt");
        entries.add(entry);
        when(archive.getEntries()).thenReturn(entries);
    }
    
}
