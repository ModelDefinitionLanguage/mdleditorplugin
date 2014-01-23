package eu.ddmore.converter.mdl2pharmml;

import static org.junit.Assert.*;

import java.io.File;

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
    public void shouldSuccedToTransformMDLToPharmML() {
        File src = new File(Thread.currentThread().getContextClassLoader().getResource("files/warfarin_PK_PRED.mdl").getPath());
        File outputDirectory = src.getParentFile();
        assertEquals(converter.performConvert(src, outputDirectory).getReturnCode(), ConversionCode.SUCCESS);
    }

    @Test
    public void shouldSuccedToTransformMultipleMDLToPharmML() {
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
        Version converterVersion = new VersionImpl();
        converterVersion.setMajor(1);
        converterVersion.setMinor(0);
        converterVersion.setPatch(2);
        assertEquals(converter.getConverterVersion(), converterVersion);
    }

    @Test
    public void shouldFindCorrectSourceVersion() {
        LanguageVersion source = new LanguageVersionImpl();
        source.setLanguage("MDL");
        Version sourceVersion = new VersionImpl();
        sourceVersion.setMajor(5);
        sourceVersion.setMinor(0);
        sourceVersion.setPatch(8);
        source.setVersion(sourceVersion);
        assertEquals(converter.getSource(), source);
    }

    @Test
    public void shouldFindCorrectTargetVersion() {
        LanguageVersion target = new LanguageVersionImpl();
        target.setLanguage("PharmML");
        Version targetVersion = new VersionImpl();
        targetVersion.setMajor(0);
        targetVersion.setMinor(2);
        targetVersion.setPatch(1);
        target.setVersion(targetVersion);
        assertEquals(converter.getTarget(), target);
    }

}
