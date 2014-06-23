package eu.ddmore.converter.mdl2json;

import static org.junit.Assert.*;

import org.apache.log4j.Logger

import eu.ddmore.convertertoolbox.api.response.ConversionReport
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
			
		def source = dataObject.SOURCE
		logger.debug(source)
		
		assertEquals("ex_data_prolactin.csv", source.file[0])
		assertEquals("nonmemFormat", source.inputformat[0])
		assertEquals("@", source.ignore[0])
		
		def dataInputVariables = dataObject.DATA_INPUT_VARIABLES
		
		println(dataInputVariables)
		def STU = dataInputVariables.STU
		assertEquals("continuous", STU.type[0])

		def TIME = dataInputVariables.TIME
		assertEquals("h", TIME.units[0])	
		
		def parameterObject = json.ex_model7_prolactin_Jan2014_par
		def structuralModel = parameterObject.STRUCTURAL
		
		def POP_KOUT = structuralModel.POP_KOUT
		assertEquals(".1", POP_KOUT.lo[0])
		
		def POP_AMP = structuralModel.POP_AMP
		assertEquals("-.75", POP_AMP.lo[0])
		
		
		
			
	}
	
    def getJson  = { String fileToConvert ->
        File srcFile = getFile(fileToConvert)

		MdlParser p = new MdlParser()
		Mcl mcl = p.parse(srcFile)

        String jsonText = converter.toJSON(mcl)
		
		println jsonText
		
		JsonSlurper slurper = new JsonSlurper();
		slurper.parseText(jsonText)
    }

    private File getFile(final String pathToFile) {
		String path = TEST_DATA_DIR + pathToFile
		URL url = this.getClass().getResource(path)
		new File(url.getFile())        
    }
}
