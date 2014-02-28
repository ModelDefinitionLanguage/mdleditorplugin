package eu.ddmore.converter.mdl2pharmml;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import eu.ddmore.convertertoolbox.api.domain.LanguageVersion;
import eu.ddmore.convertertoolbox.api.domain.Version;
import eu.ddmore.convertertoolbox.api.response.ConversionReport;
import eu.ddmore.convertertoolbox.api.response.ConversionReport.ConversionCode;
import eu.ddmore.convertertoolbox.domain.LanguageVersionImpl;
import eu.ddmore.convertertoolbox.domain.VersionImpl;

public class MDLToPharmMLConverterTest {

    private MDLToPharmMLConverter converter;

    @Before
    public void initialize() {
        converter = new MDLToPharmMLConverter();
    }

    @Test
    public void shouldSucceedToTransformMDLToPharmML() throws IOException {
        File src = new File(Thread.currentThread().getContextClassLoader().getResource("files/warfarin_PK_PRED.mdl").getPath());
        File outputDirectory = src.getParentFile();
        assertEquals(converter.performConvert(src, outputDirectory).getReturnCode(), ConversionCode.SUCCESS);
    }

    @Test
    public void shouldSucceedToTransformMultipleMDLToPharmML() throws IOException {
        File folder = new File(Thread.currentThread().getContextClassLoader().getResource("files/").getPath());
        File[] listOfFiles = folder.listFiles(new FilenameFilter() {

            public boolean accept(File dir, String filename) {
                return filename.endsWith(".mdl");
            }
        });
        ConversionReport[] reports = converter.performConvert(listOfFiles, folder);
        for (int i = 0; i < reports.length; i++) {
            assertEquals(reports[0].getReturnCode(), ConversionCode.SUCCESS);
        }
    }

    @Test
    public void shouldFindCorrectConverterVersion() {
        Version converterVersion = new VersionImpl(1, 0, 2, null);
        assertEquals(converter.getConverterVersion(), converterVersion);
    }

    @Test
    public void shouldFindCorrectSourceVersion() {
        LanguageVersion source = new LanguageVersionImpl();
        source.setLanguage("MDL");
        Version sourceVersion = new VersionImpl(5, 0, 8, null);
        source.setVersion(sourceVersion);
        assertEquals(converter.getSource(), source);
    }

    @Test
    public void shouldFindCorrectTargetVersion() {
        LanguageVersion target = new LanguageVersionImpl();
        target.setLanguage("PharmML");
        Version targetVersion = new VersionImpl(0, 2, 1, null);
        target.setVersion(targetVersion);
        assertEquals(converter.getTarget(), target);
    }

}
