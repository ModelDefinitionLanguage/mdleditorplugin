package eu.ddmore.converter.mdl2json;

import static org.junit.Assert.*;

import org.apache.log4j.Logger

import eu.ddmore.convertertoolbox.api.response.ConversionReport
import eu.ddmore.converter.mdl2json.domain.Parameter
import eu.ddmore.converter.mdl2json.domain.Data
import eu.ddmore.mdlparse.MdlParser
import groovy.json.JsonSlurper
import org.ddmore.mdl.mdl.Mcl
import org.junit.Test;

class TestParameterToJSONConverter {
	private static Logger logger = Logger.getLogger(TestParameterToJSONConverter.class)
	
	final static String TEST_DATA_DIR = "./"
	
	private final MDLToJSONConverter converter = new MDLToJSONConverter();
		
	@Test
	public void testStructuralModel() {
		def json = getJson("simpleParameter.mdl")
			
		def parameterObject = json.tumour_size_par
			
		def structuralModel = parameterObject[Parameter.STRUCTURAL]
		
		def POP_SIZE0 = structuralModel.POP_SIZE0
		assertEquals("6.66", POP_SIZE0.value[0])
		assertEquals("1", POP_SIZE0.lo[0])
		assertEquals("10", POP_SIZE0.hi[0] )
		
		def POP_AE50 = structuralModel.POP_AE50
		assertEquals("11700.", POP_AE50.value[0])			
		assertEquals("-1", POP_AE50.lo[0])
		
	}
	
	@Test
	public void testVariabilityModel() {
		def json = getJson("simpleParameter.mdl")
			
		def parameterObject = json.tumour_size_par
			
		def variabilityModel = parameterObject[Parameter.VARIABILITY]
		
		def PPV_SIZE0 = variabilityModel.PPV_SIZE0

		def val = PPV_SIZE0["value"]
		
		assertEquals("0.317", val[0][0])
		assertEquals("VAR", PPV_SIZE0.type[0][0] )
		
	}
	
	@Test
	public void testParameterWithMatrix() {
		def struc1 = 0
		def PPV_KOUT = 1

		def json = getJson("parameterWithMatrix.mdl")
		def paramObj = json.ex_model7_prolactin_Jan2014_par
		
		def structuralModel = paramObj[Parameter.STRUCTURAL]
		
		def POP_KOUT = structuralModel.POP_KOUT
		assertEquals(".1", POP_KOUT.lo[0])
		
		def POP_AMP = structuralModel.POP_AMP
		assertEquals("-.75", POP_AMP.lo[0])			

		def variabilityBlock = paramObj[Parameter.VARIABILITY][0]
		
		assertEquals(9, variabilityBlock.size())
		
		struc1 = variabilityBlock[struc1]
		def attributes = struc1["matrix"]
		assertEquals("PPV_PRL0=.425,\n.758,PPV_KI=1.82", attributes["content"])
		
		
		PPV_KOUT = variabilityBlock[PPV_KOUT]
		attributes = PPV_KOUT["PPV_KOUT"]
		assertEquals("0.589", attributes["value"])

	}
	
	def getJson  = { String fileToConvert ->
        File srcFile = getFile(fileToConvert)

		MdlParser p = new MdlParser()
		Mcl mcl = p.parse(srcFile)

        String jsonText = converter.toJSON(mcl)
		
		logger.debug(jsonText)
		
		JsonSlurper slurper = new JsonSlurper();
		slurper.parseText(jsonText)
    }

    private File getFile(final String pathToFile) {
		String path = TEST_DATA_DIR + pathToFile
		URL url = this.getClass().getResource(path)
		new File(url.getFile())        
    }
}
