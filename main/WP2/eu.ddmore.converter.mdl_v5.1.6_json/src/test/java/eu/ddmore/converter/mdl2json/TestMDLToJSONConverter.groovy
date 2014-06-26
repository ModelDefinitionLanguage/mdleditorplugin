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

class TestMDLToJSONConverter {
	private static Logger logger = Logger.getLogger(TestMDLToJSONConverter.class)
	
	final static String TEST_DATA_DIR = "./"
	
	private final MDLToJSONConverter converter = new MDLToJSONConverter();

	public void testExtractObjectNames() {
		// This isn't a valid MDL file
		def json = getJson("2008ThamJCCRFromMDLrepo.mdl")
		
		assert json.tumour_size_dat.file != null
		assert json.tumour_size_par != null
		assert json.tumour_size_mdl != null
		assert json.tumour_size_task != null
		
	}
		
	@Test
	public void testProlactinMay2014() {
		def json = getJson("ex_model7_prolactin_May2014_OAM.mdl")
			
		def dataObject = json.ex_model7_prolactin_Jan2014_dat
			
		def source = dataObject[Data.SOURCE]
		logger.debug(source)
		
		assertEquals("ex_data_prolactin.csv", source.file[0])
		assertEquals("nonmemFormat", source.inputformat[0])
		assertEquals("@", source.ignore[0])
		
		def dataInputVariables = dataObject[Data.DATA_INPUT_VARIABLES]
				
		logger.debug(dataInputVariables)
		
		def STU = dataInputVariables.STU
		assertEquals("continuous", STU.type[0])

		def TIME = dataInputVariables.TIME
		assertEquals("h", TIME.units[0])	
		
		def parameterObject = json.ex_model7_prolactin_Jan2014_par
		def structuralModel = parameterObject[Parameter.STRUCTURAL]
		
		def POP_KOUT = structuralModel.POP_KOUT
		assertEquals(".1", POP_KOUT.lo[0])
		
		def POP_AMP = structuralModel.POP_AMP
		assertEquals("-.75", POP_AMP.lo[0])			
	}
	
	@Test
	public void testOGTTJun2014() {
		def struc1 = 0
		def PPV_IIV_CLG_FIX = 1
		
		def json = getJson("run_final_OGTT_04Jun2014_OAM.mdl")
		
		def paramObj = json.run_final_OGTT_par
		
		def variabilityBlock = paramObj[Parameter.VARIABILITY][0]
		
		assertEquals(19, variabilityBlock.size())
		
		struc1 = variabilityBlock[struc1]
		def attributes = struc1["matrix"]
		assertEquals("PPV_IIV_VG_FIX=.0887,\n-0.192,PPV_IIV_Q_FIX=.73,\n.0855,-0.12,PPV_IIV_VI_FIX=.165", attributes["content"])
		
		
		PPV_IIV_CLG_FIX = variabilityBlock[PPV_IIV_CLG_FIX]
		attributes = PPV_IIV_CLG_FIX["PPV_IIV_CLG_FIX"]
		assertEquals("0.352", attributes["value"])
			
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
