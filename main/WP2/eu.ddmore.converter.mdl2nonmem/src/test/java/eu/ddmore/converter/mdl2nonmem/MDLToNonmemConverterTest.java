package eu.ddmore.converter.mdl2nonmem;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import eu.ddmore.convertertoolbox.api.response.ConversionReport;
import eu.ddmore.convertertoolbox.api.response.ConversionReport.ConversionCode;

public class MDLToNonmemConverterTest {

    @Test
    public void shouldSuccedToTransformMDLToNONMEM() {
        MDLToNonmemConverter conv = new MDLToNonmemConverter();
        File src = new File(Thread.currentThread().getContextClassLoader().getResource("files/warfarin_PK_PRED.mdl").getPath());
        File outputDirectory = src.getParentFile();
        assertEquals(conv.performConvert(src, outputDirectory).getReturnCode(), ConversionCode.SUCCESS);
    }

    @Test
    public void shouldSuccedToTransformMultipleMDLToNONMEM() {
        MDLToNonmemConverter conv = new MDLToNonmemConverter();
        File pkpred = new File(Thread.currentThread().getContextClassLoader().getResource("files/warfarin_PK_PRED.mdl").getPath());
        File pkbov = new File(Thread.currentThread().getContextClassLoader().getResource("files/warfarin_PK_BOV.mdl").getPath());
        File outputDirectory = pkpred.getParentFile();
        File[] src = new File[] { pkpred, pkbov };
        ConversionReport[] reports = conv.performConvert(src, outputDirectory);
        assertEquals(reports[0].getReturnCode(), ConversionCode.SUCCESS);
        assertEquals(reports[1].getReturnCode(), ConversionCode.SUCCESS);
    }

}
