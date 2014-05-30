package eu.ddmore.converter.pharmml2nmtran;

import static org.junit.Assert.*;

import eu.ddmore.convertertoolbox.api.response.ConversionReport

import org.junit.Ignore;
import org.junit.Test;


class MainTest {

    private final static String PATH = "0.3/"
	
	private final PharmMLToNMTRANConverter converter = new PharmMLToNMTRANConverter();


    @Test
    public void convertExample5() {
		def conversionReport = performConversion('example5.xml')
		assertNotNull("ConversionReport should not be null", conversionReport)
    }

    @Test
    public void convertExample3() {
		def conversionReport = performConversion('example3.xml')
		assertNotNull("ConversionReport should not be null", conversionReport)
    }
    
    @Ignore
    @Test
    public void convertExample1() {
		def conversionReport = performConversion('example1.xml')
		assertNotNull("ConversionReport should not be null", conversionReport)
    }
    
	@Ignore
    @Test
    public void convertExample2() {
		def conversionReport = performConversion('example2.xml')
		assertNotNull("ConversionReport should not be null", conversionReport)
    }

	@Ignore    
    @Test
    public void convertExample4() {
		def conversionReport = performConversion('example4.xml')
		assertNotNull("ConversionReport should not be null", conversionReport)
    }

    @Test
    public void convert2008ThamJCCR() {
		def conversionReport = performConversion('2008ThamJCCR.xml')
		assertNotNull("ConversionReport should not be null", conversionReport)
    }

    @Test
    public void convert2008ThamJCCR_NONMEM() {
		def conversionReport = performConversion('2008ThamJCCR_NONMEM.xml')
		assertNotNull("ConversionReport should not be null", conversionReport)
    }

	@Test
	public void convert2008ThamCCR_v03_NONMEM() {
		def conversionReport = performConversion('2008ThamCCR_v03_NONMEM.xml')
		assertNotNull("ConversionReport should not be null", conversionReport)
	}

    @Test
    public void convertFriberg2009Prolactin_v20140506v11_03() {
		def conversionReport = performConversion('Friberg2009Prolactin_v20140506v11_0.3.xml')
		assertNotNull("ConversionReport should not be null", conversionReport)
    }
    
	@Test
    public void convertFriberg2009Prolactin_v20140506v13_NONMEM() {
		def conversionReport = performConversion('Friberg2009Prolactin_v20140506v13_NONMEM.xml')
		assertNotNull("ConversionReport should not be null", conversionReport)
    }
    
    @Ignore
    @Test
    public void convertDelbene() {
		def conversionReport = performConversion('delbene.xml')
		assertNotNull("ConversionReport should not be null", conversionReport)
    }

    @Ignore
    @Test
    public void convertDeWinter_JPP_2006() {
		def conversionReport = performConversion('DeWinter_JPP_2006.xml')
		assertNotNull("ConversionReport should not be null", conversionReport)
    }

    @Ignore
    @Test
    public void convertFriberg_SchizophreniaModel__ClinPharmcolTher_2009_fixAUC_v2() {
		def conversionReport = performConversion('Friberg_SchizophreniaModel__ClinPharmcolTher_2009_fixAUC_v2.xml')
		assertNotNull("ConversionReport should not be null", conversionReport)
    }

    @Ignore
    @Test
    public void convertHamren_CPT_2008() {
		def conversionReport = performConversion('Hamren_CPT_2008.xml')
		assertNotNull("ConversionReport should not be null", conversionReport)
    }

    @Ignore
    @Test
    public void convertLledo_JPP_2013() {
		def conversionReport = performConversion('Lledo_JPP_2013.xml')
		assertNotNull("ConversionReport should not be null", conversionReport)
    }
    
    @Ignore
    @Test
    public void convertRocchetti() {
		def conversionReport = performConversion('rocchetti.xml')
		assertNotNull("ConversionReport should not be null", conversionReport)
    }

    @Ignore
    @Test
    public void convertSimeoni() {
		def conversionReport = performConversion('simeoni.xml')
		assertNotNull("ConversionReport should not be null", conversionReport)
    }

	
	private ConversionReport performConversion(String fileToConvert) {
		File src = getFile(fileToConvert)
		converter.performConvert(src, src.getParentFile())
	}

	private File getFile(String file) {
		return new File(Thread.currentThread().getContextClassLoader().getResource(PATH + file).getPath());
	}
}