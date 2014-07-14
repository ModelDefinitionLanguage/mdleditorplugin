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
		
		assertEquals("\"ex_data_prolactin.csv\"", source.file[0])
		assertEquals("nonmemFormat", source.inputformat[0])
		assertEquals("\"@\"", source.ignore[0])
		
		def dataInputVariables = dataObject[Data.DATA_INPUT_VARIABLES]
				
		def STU = dataInputVariables.STU
		assertEquals("continuous", STU.type[0])

		def TIME = dataInputVariables.TIME
		assertEquals("\"h\"", TIME.units[0])	
		
		def paramObj = json.ex_model7_prolactin_Jan2014_par
		assertEquals(Parameter.IDENTIFIER, paramObj.identifier[0])
	}
	
	@Test
	public void testOGTTJun2014() {
		def json = getJson("run_final_OGTT_04Jun2014_OAM.mdl")
		
		def paramObj = json.run_final_OGTT_par
		assertEquals(Parameter.IDENTIFIER, paramObj.identifier[0])
			
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
