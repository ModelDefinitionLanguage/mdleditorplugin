package eu.ddmore.convertertoolbox.cli;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Sets;

import eu.ddmore.convertertoolbox.api.domain.LanguageVersion;
import eu.ddmore.convertertoolbox.api.exception.ConverterNotFoundException;

public class MainTest {

    private final static String TEST_DATA_DIR = "/test-models/MDL/Product4/";

    private final static String TEST_FILE_1 = "UseCase1.mdl";
    private final static String TEST_FILE_2 = "UseCase2.mdl";

    private final static String WORKING_DIR = "target/MainTest_Working_Dir/";

    @Before
    public void setUp() throws IOException {
        FileUtils.copyInputStreamToFile(
            MainTest.class.getResourceAsStream(TEST_DATA_DIR + TEST_FILE_1),
            new File(WORKING_DIR, TEST_FILE_1));
        FileUtils.copyInputStreamToFile(
            MainTest.class.getResourceAsStream(TEST_DATA_DIR + TEST_FILE_2),
            new File(WORKING_DIR, TEST_FILE_2));
    }

    @Test
    public void shouldCreateCorrectLanguageVersion() {
        String language = "MDL";
        String version = "5.0.8-qualm";
        LanguageVersion source = new Main().getLanguageVersion(language, version);
        assertEquals(language, source.getLanguage());
        assertEquals(5, source.getVersion().getMajor());
        assertEquals(0, source.getVersion().getMinor());
        assertEquals(8, source.getVersion().getPatch());
        assertEquals("qualm", source.getVersion().getQualifier());
    }

    @Test
    public void shouldCreateCorrectLanguageVersionMissingPatch() {
        String language = "MDL";
        String version = "5.0-qualm";
        LanguageVersion source = new Main().getLanguageVersion(language, version);
        assertEquals(language, source.getLanguage());
        assertEquals(5, source.getVersion().getMajor());
        assertEquals(0, source.getVersion().getMinor());
        assertEquals("qualm", source.getVersion().getQualifier());
    }

    @Test
    public void shouldCreateCorrectLanguageVersionDoubleHyphen() {
        String language = "MDL";
        String version = "5.0.8-some-qualm";
        LanguageVersion source = new Main().getLanguageVersion(language, version);
        assertEquals(language, source.getLanguage());
        assertEquals(5, source.getVersion().getMajor());
        assertEquals(0, source.getVersion().getMinor());
        assertEquals("some-qualm", source.getVersion().getQualifier());
    }

    @Test
    public void shouldFindFilesInDirectory() {

        Main cli = new Main();

        File[] found = cli.getFilesFromDirectory(new File(WORKING_DIR));

        File[] expected = new File[] { new File(WORKING_DIR, TEST_FILE_1), new File(WORKING_DIR, TEST_FILE_2) };

        assertEquals(Sets.newHashSet(expected), Sets.newHashSet(found));
    }

    @Test
    public void shouldParseArguments() throws ConverterNotFoundException, IOException {
        String[] args = new String[] { "-in", WORKING_DIR, "-out", "files", "-sn", "MDL", "-sv", "5.0.8-qualm", "-tn", "NMTRAN", "-tv",
                "7.2.0-qualn" };
        Main cli = new Main();
        cli.parseArguments(args);
        assertEquals(WORKING_DIR, cli.src);
        assertEquals("files", cli.output);
        assertEquals("MDL", cli.sourceLanguageName);
        assertEquals("5.0.8-qualm", cli.sourceLanguageVersion);
        assertEquals("NMTRAN", cli.targetLanguageName);
        assertEquals("7.2.0-qualn", cli.targetLanguageVersion);
    }

    @Test(expected = ConverterNotFoundException.class)
    public void shouldNotFindValidConverter() throws ConverterNotFoundException, IOException {
        String[] args = new String[] { "-in", "files", "-out", "files", "-sn", "MDL", "-sv", "5.0.8-qualm", "-tn", "NMTRAN", "-tv",
                "7.2.0-qualn" };
        Main cli = new Main();
        cli.parseArguments(args);
        cli.runFromCommandLine();
    }

}
