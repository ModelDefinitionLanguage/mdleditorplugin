package eu.ddmore.convertertoolbox.cli;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;

import eu.ddmore.convertertoolbox.api.domain.LanguageVersion;
import eu.ddmore.convertertoolbox.api.exception.ConverterNotFoundException;
import eu.ddmore.convertertoolbox.api.response.ConversionReport;
import eu.ddmore.convertertoolbox.api.response.ConversionDetail.Severity;
import eu.ddmore.convertertoolbox.api.response.ConversionReport.ConversionCode;


public class MainTest {

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
        File src = new File(Thread.currentThread().getContextClassLoader().getResource("files/").getPath());
        File[] found = cli.getFilesFromDirectory(src);
        File f1 = new File(Thread.currentThread().getContextClassLoader().getResource("files/warfarin_PK_BOV.mdl").getPath());
        File f2 = new File(Thread.currentThread().getContextClassLoader().getResource("files/warfarin_PK_PRED.mdl").getPath());
        File[] expected = new File[] {f1, f2};
        assertEquals(Arrays.asList(expected), Arrays.asList(found));
    }
    
    @Test
    public void shouldParseArguments() throws ConverterNotFoundException, IOException {
        String[] args = new String[] { "-in", "files", "-out", "files", "-sn", "MDL", "-sv", "5.0.8-qualm", "-tn", "NMTRAN", "-tv", "7.2.0-qualn" };
        Main cli = new Main();
        cli.parseArguments(args);
        assertEquals("files", cli.src);
        assertEquals("files", cli.output);
        assertEquals("MDL", cli.sourceLanguageName);
        assertEquals("5.0.8-qualm", cli.sourceLanguageVersion);
        assertEquals("NMTRAN", cli.targetLanguageName);
        assertEquals("7.2.0-qualn", cli.targetLanguageVersion);
    }

    @Test(expected = ConverterNotFoundException.class)
    public void shouldNotFindValidConverter() throws ConverterNotFoundException, IOException {
        String[] args = new String[] { "-in", "files", "-out", "files", "-sn", "MDL", "-sv", "5.0.8-qualm", "-tn", "NMTRAN", "-tv", "7.2.0-qualn" };
        Main cli = new Main();
        cli.parseArguments(args);
        cli.runFromCommandLine();
    }
    
}
