package eu.ddmore.converter.mdl2nonmem;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import eu.ddmore.convertertoolbox.api.domain.LanguageVersion;
import eu.ddmore.convertertoolbox.api.domain.Version;
import eu.ddmore.convertertoolbox.api.response.ConversionReport;
import eu.ddmore.convertertoolbox.api.response.ConversionReport.ConversionCode;
import eu.ddmore.convertertoolbox.domain.LanguageVersionImpl;
import eu.ddmore.convertertoolbox.domain.VersionImpl;

public class MDLToNonmemConverterTest {

    private MDLToNonmemConverter converter;

    @Before
    public void initialize() {
        converter = new MDLToNonmemConverter();
    }

    @Test
    public void shouldSucceedToTransformMDLToNONMEM() throws IOException {
        File src = new File(Thread.currentThread().getContextClassLoader().getResource("files/warfarin_PK_PRED.mdl").getPath());
        File outputDirectory = src.getParentFile();
        assertEquals(converter.performConvert(src, outputDirectory).getReturnCode(), ConversionCode.SUCCESS);
    }

    @Test
    public void shouldSucceedToTransformMultipleMDLToNONMEM() throws IOException {
        File pkpred = new File(Thread.currentThread().getContextClassLoader().getResource("files/warfarin_PK_PRED.mdl").getPath());
        File pkbov = new File(Thread.currentThread().getContextClassLoader().getResource("files/warfarin_PK_BOV.mdl").getPath());
        File outputDirectory = pkpred.getParentFile();
        File[] src = new File[] { pkpred, pkbov };
        ConversionReport[] reports = converter.performConvert(src, outputDirectory);
        assertEquals(reports[0].getReturnCode(), ConversionCode.SUCCESS);
        assertEquals(reports[1].getReturnCode(), ConversionCode.SUCCESS);
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
        target.setLanguage("NMTRAN");
        Version targetVersion = new VersionImpl(7, 2, 0, null);
        target.setVersion(targetVersion);
        assertEquals(converter.getTarget(), target);
    }
}
