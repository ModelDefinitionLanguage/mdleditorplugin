/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.service.impl.conversion;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
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
@PowerMockIgnore("org.apache.log4j.*")
public class CreateOutputPhexArchiveStepTest {

    private ConversionContext conversionContext;
    
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
    private Archive inputArchive;
    
    @Mock
    private Archive outputArchive;
    
    @Captor
    private ArgumentCaptor<Collection<Entry>> mainEntriesCaptor;

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();
    
    private File outputDirectory;
    
    private File inputArchiveFile;
    
    private File outputArchiveFile;
    
    private static final String INPUT_FILE = "model.input";
    private static final String OUTPUT_FILE = "model.output";
    private static final String DATA_FILE = "model.csv";
    private static final String EXTRA_INPUT_FILE = "model.lst";
    
    @Before
    public void setUp() throws IOException {
        conversionContext = new ConversionContext(converter, conversion, conversionRepository);
        instance.setResultFileNameExclusionPattern(".*\\.csv");
        
        final File workingDirectory = tempFolder.newFolder();
        inputArchiveFile = new File(workingDirectory,ConversionResourcesConvention.INPUTS_ARCHIVE_NAME);
        outputArchiveFile = new File(workingDirectory,ConversionResourcesConvention.OUTPUTS_ARCHIVE_NAME);
        outputDirectory = new File(workingDirectory,ConversionResourcesConvention.OUTPUTS_DIRECTORY_NAME);
        
        when(conversion.getInputArchive()).thenReturn(inputArchiveFile);
        when(archiveFactory.createArchive(eq(inputArchiveFile))).thenReturn(inputArchive);
        when(archiveFactory.createArchive(eq(outputArchiveFile))).thenReturn(outputArchive);
        when(archiveFactory.createArchiveHelper(same(outputArchive))).thenReturn(archiveHelper);
        when(inputArchive.getMainEntries()).thenReturn(Arrays.asList(mock(Entry.class)));
        when(conversion.getWorkingDirectory()).thenReturn(workingDirectory);
        
        PowerMockito.mockStatic(Files.class);
    }
    
    @Test
    public void shouldCreateOutputArchive() throws IOException, ArchiveException {
        setupMockInputArchiveEntries("");
        setupMockOutputArchiveEntries("");
        
        when(conversion.getInputFileName()).thenReturn(INPUT_FILE);
        
        // Call the method under test
        instance.execute(conversionContext);
        
        verifyMockInteractions("");
        
        assertEquals("Checking that the Archive's mainEntries consists of a single entry", 1, mainEntriesCaptor.getValue().size());
        assertEquals("Checking the fileName of the single mainEntry in the Archive - should refer to the output file",
            OUTPUT_FILE, ((Entry) mainEntriesCaptor.getValue().iterator().next()).getFileName());
        assertEquals("Checking the filePath of the single mainEntry in the Archive - should refer to the output file",
            OUTPUT_FILE, ((Entry) mainEntriesCaptor.getValue().iterator().next()).getFilePath());
    }
    
    @Test
    public void shouldCreateOutputArchiveWhereModelFileInSubdirectoryUnixFormatPath() throws IOException, ArchiveException {
        setupMockInputArchiveEntries("this/is/path/to/");
        setupMockOutputArchiveEntries("this/is/path/to/");

        when(conversion.getInputFileName()).thenReturn("this/is/path/to/" + INPUT_FILE);
        
        // Call the method under test
        instance.execute(conversionContext);
        
        verifyMockInteractions("this/is/path/to");
        
        assertEquals("Checking that the Archive's mainEntries consists of a single entry", 1, mainEntriesCaptor.getValue().size());
        assertEquals("Checking the fileName of the single mainEntry in the Archive - should refer to the output file",
            OUTPUT_FILE, ((Entry) mainEntriesCaptor.getValue().iterator().next()).getFileName());
        assertEquals("Checking the filePath of the single mainEntry in the Archive - should refer to the output file",
            "this/is/path/to/" + OUTPUT_FILE, ((Entry) mainEntriesCaptor.getValue().iterator().next()).getFilePath());
    }
    
    @Test
    public void shouldCreateOutputArchiveWhereModelFileInSubdirectoryWindowsFormatPath() throws IOException, ArchiveException {
        setupMockInputArchiveEntries("this\\is\\path\\to\\");
        setupMockOutputArchiveEntries("this\\is\\path\\to\\");

        when(conversion.getInputFileName()).thenReturn("this\\is\\path\\to\\" + INPUT_FILE);
        
        // Call the method under test
        instance.execute(conversionContext);
        
        verifyMockInteractions("this\\is\\path\\to");

        assertEquals("Checking that the Archive's mainEntries consists of a single entry", 1, mainEntriesCaptor.getValue().size());
        assertEquals("Checking the fileName of the single mainEntry in the Archive - should refer to the output file",
            OUTPUT_FILE, ((Entry) mainEntriesCaptor.getValue().iterator().next()).getFileName());
        assertEquals("Checking the filePath of the single mainEntry in the Archive - should refer to the output file",
            "this\\is\\path\\to\\" + OUTPUT_FILE, ((Entry) mainEntriesCaptor.getValue().iterator().next()).getFilePath());
    }
    
    private List<Entry> createCommonArchiveEntries(final String pathPrefix) {
        final List<Entry> entries = Lists.newArrayList();
        Entry entry = mock(Entry.class);
        when(entry.getFileName()).thenReturn(INPUT_FILE);
        when(entry.getFilePath()).thenReturn(pathPrefix + INPUT_FILE);
        entries.add(entry);
        entry = mock(Entry.class);
        when(entry.getFileName()).thenReturn(DATA_FILE);
        when(entry.getFilePath()).thenReturn(pathPrefix + DATA_FILE);
        entries.add(entry);
        entry = mock(Entry.class);
        when(entry.getFileName()).thenReturn(EXTRA_INPUT_FILE);
        when(entry.getFilePath()).thenReturn(pathPrefix + EXTRA_INPUT_FILE);
        entries.add(entry);
        entry = mock(Entry.class);
        when(entry.getFileName()).thenReturn(INPUT_FILE);
        when(entry.getFilePath()).thenReturn("/some/other/path/to/" + INPUT_FILE);
        entries.add(entry);
        return entries;
    }
    
    private void setupMockInputArchiveEntries(final String pathPrefix) {
        final List<Entry> entries = createCommonArchiveEntries(pathPrefix);
        when(inputArchive.getEntries()).thenReturn(entries);
    }
    
    private void setupMockOutputArchiveEntries(final String pathPrefix) {
        final List<Entry> entries = createCommonArchiveEntries(pathPrefix);
        Entry entry = mock(Entry.class);
        when(entry.getFileName()).thenReturn(OUTPUT_FILE);
        when(entry.getFilePath()).thenReturn(pathPrefix + OUTPUT_FILE);
        entries.add(entry);
        entry = mock(Entry.class);
        when(entry.getFileName()).thenReturn(DATA_FILE);
        when(entry.getFilePath()).thenReturn("/some/other/path/to/other-" + DATA_FILE);
        entries.add(entry);
        entry = mock(Entry.class);
        when(outputArchive.getEntries()).thenReturn(entries);
    }
    
    private void verifyMockInteractions(final String filePathUsedForAddingDirContents) throws IOException, ArchiveException {
        verify(archiveFactory).createArchive(inputArchiveFile);
        verify(archiveFactory).createArchive(outputArchiveFile);
        verify(archiveFactory).createArchiveHelper(outputArchive);
        verify(archiveHelper).addDirectoryContents(eq(outputDirectory), eq(filePathUsedForAddingDirContents));
        verify(conversion).getInputArchive();
        verify(conversion, atLeastOnce()).getInputFileName();
        verify(conversion).setOutputArchive(eq(outputArchiveFile));
        verify(conversion).setOutputFileSize(any(Long.class));
        inOrder(inputArchive, outputArchive);
        verify(inputArchive).open();
        verify(inputArchive).getEntries();
        verify(inputArchive).close();
        verify(outputArchive).open();
        verify(outputArchive).getEntries();
        verify(outputArchive).setMainEntries(mainEntriesCaptor.capture());
        verify(outputArchive).close();
        verifyNoMoreInteractions(archiveFactory);
        verifyNoMoreInteractions(archiveHelper);
        verifyNoMoreInteractions(inputArchive);
        verifyNoMoreInteractions(outputArchive);
        
        PowerMockito.verifyStatic();
        Files.copy(inputArchiveFile, outputArchiveFile);
    }
    
}
