package eu.ddmore.converter.pharmml2nmtran;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;


class MainTest {

    public static String path = "0.3/"


    @Test
    public void convertExample5() {
        PharmMLToNMTRANConverter converter = new PharmMLToNMTRANConverter();
        File src = new File(Thread.currentThread().getContextClassLoader().getResource(path +'example5.xml').getPath());
        converter.performConvert(src, src.getParentFile());
    }

    @Test
    public void convertExample3() {
        PharmMLToNMTRANConverter converter = new PharmMLToNMTRANConverter();
        File src = new File(Thread.currentThread().getContextClassLoader().getResource(path +'example3.xml').getPath());
        converter.performConvert(src, src.getParentFile());
    }
    
    @Ignore
    @Test
    public void convertExample1() {
        PharmMLToNMTRANConverter converter = new PharmMLToNMTRANConverter();
        File src = new File(Thread.currentThread().getContextClassLoader().getResource(path +'example1.xml').getPath());
        converter.performConvert(src, src.getParentFile());
    }
    
    @Ignore
    @Test
    public void convertExample2() {
        PharmMLToNMTRANConverter converter = new PharmMLToNMTRANConverter();
        File src = new File(Thread.currentThread().getContextClassLoader().getResource(path +'example2.xml').getPath());
        converter.performConvert(src, src.getParentFile());
    }
    
    @Ignore
    @Test
    public void convertExample4() {
        PharmMLToNMTRANConverter converter = new PharmMLToNMTRANConverter();
        File src = new File(Thread.currentThread().getContextClassLoader().getResource(path +'example4.xml').getPath());
        converter.performConvert(src, src.getParentFile());
    }
    
    @Test
    public void convert2008ThamJCCR() {
        PharmMLToNMTRANConverter converter = new PharmMLToNMTRANConverter();
        File src = new File(Thread.currentThread().getContextClassLoader().getResource(path +'2008ThamJCCR.xml').getPath());
        converter.performConvert(src, src.getParentFile());
    }
    
    @Ignore
    @Test
    public void convertFriberg2009Prolactinev20140331v9() {
        PharmMLToNMTRANConverter converter = new PharmMLToNMTRANConverter();
        File src = new File(Thread.currentThread().getContextClassLoader().getResource(path +'Friberg2009Prolactinev20140331v9.xml').getPath());
        converter.performConvert(src, src.getParentFile());
    }
    
    @Ignore
    @Test
    public void convertDelbene() {
        PharmMLToNMTRANConverter converter = new PharmMLToNMTRANConverter();
        File src = new File(Thread.currentThread().getContextClassLoader().getResource(path +'delbene.xml').getPath());
        converter.performConvert(src, src.getParentFile());
    }

    @Ignore
    @Test
    public void convertDeWinter_JPP_2006() {
        PharmMLToNMTRANConverter converter = new PharmMLToNMTRANConverter();
        File src = new File(Thread.currentThread().getContextClassLoader().getResource(path +'DeWinter_JPP_2006.xml').getPath());
        converter.performConvert(src, src.getParentFile());
    }

    @Ignore
    @Test
    public void convertFriberg_SchizophreniaModel__ClinPharmcolTher_2009_fixAUC_v2() {
        PharmMLToNMTRANConverter converter = new PharmMLToNMTRANConverter();
        File src = new File(Thread.currentThread().getContextClassLoader().getResource(path +'Friberg_SchizophreniaModel__ClinPharmcolTher_2009_fixAUC_v2.xml').getPath());
        converter.performConvert(src, src.getParentFile());
    }

    @Ignore
    @Test
    public void convertHamren_CPT_2008() {
        PharmMLToNMTRANConverter converter = new PharmMLToNMTRANConverter();
        File src = new File(Thread.currentThread().getContextClassLoader().getResource(path +'Hamren_CPT_2008.xml').getPath());
        converter.performConvert(src, src.getParentFile());
    }

    @Ignore
    @Test
    public void convertLledo_JPP_2013() {
        PharmMLToNMTRANConverter converter = new PharmMLToNMTRANConverter();
        File src = new File(Thread.currentThread().getContextClassLoader().getResource(path +'Lledo_JPP_2013.xml').getPath());
        converter.performConvert(src, src.getParentFile());
    }
    
    @Ignore
    @Test
    public void convertRocchetti() {
        PharmMLToNMTRANConverter converter = new PharmMLToNMTRANConverter();
        File src = new File(Thread.currentThread().getContextClassLoader().getResource(path +'rocchetti.xml').getPath());
        converter.performConvert(src, src.getParentFile());
    }

    @Ignore
    @Test
    public void convertSimeoni() {
        PharmMLToNMTRANConverter converter = new PharmMLToNMTRANConverter();
        File src = new File(Thread.currentThread().getContextClassLoader().getResource(path +'simeoni.xml').getPath());
        converter.performConvert(src, src.getParentFile());
    }
    
}