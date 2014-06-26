package eu.ddmore.converter.mdl2json;

import static org.junit.Assert.*;

import org.apache.log4j.Logger

import eu.ddmore.converter.mdl2json.domain.MCLFile
import eu.ddmore.convertertoolbox.api.response.ConversionReport
import eu.ddmore.mdlparse.MdlParser
import groovy.json.JsonSlurper
import org.ddmore.mdl.mdl.Mcl
import org.junit.Test;

class TestJSONParameterObjectToMDL {
	private static Logger logger = Logger.getLogger(TestJSONParameterObjectToMDL.class)
	
	final static String TEST_DATA_DIR = "./"
	
	private final MDLToJSONConverter converterToJson = new MDLToJSONConverter();
	private final JSONToMDLConverter converter = new JSONToMDLConverter();

	@Test
	public void testProlactinMay2014() {
		// TODO Write proper test
		
		def json = getJson("ex_model7_prolactin_May2014_OAM.mdl")

		MCLFile mclFile = new MCLFile(json)
		
		String mdl = mclFile.toMDL()			
		
		assertNotEquals(null, mdl)
	}
	
	
    def getJson  = { String fileToConvert ->
        File srcFile = getFile(fileToConvert)

		MdlParser p = new MdlParser()
		Mcl mcl = p.parse(srcFile)

        String jsonText = converterToJson.toJSON(mcl)
		
		JsonSlurper slurper = new JsonSlurper();
		slurper.parseText(jsonText)
    }

    private File getFile(final String pathToFile) {
		String path = TEST_DATA_DIR + pathToFile
		URL url = this.getClass().getResource(path)
		new File(url.getFile())        
    }
}
