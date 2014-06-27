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

class TestDataToJSONConverter {
	private static Logger logger = Logger.getLogger(TestDataToJSONConverter.class)
	
	final static String TEST_DATA_DIR = "./"
	
	private final MDLToJSONConverter converter = new MDLToJSONConverter();
		
	@Test
	public void testProlactinMay2014() {
		def json = getJson("simpleData.mdl")
			
		logger.debug(json)
		
		def dataObject = json.tumour_size_dat
			
		def source = dataObject[Data.SOURCE]
		
		assertEquals("tumour_exposure.csv", source.file[0])
		assertEquals("nonmemFormat", source.inputformat[0])
		assertEquals("@", source.ignore[0])
		
		def dataInputVariables = dataObject[Data.DATA_INPUT_VARIABLES]
						
		def ID = dataInputVariables.ID
		assertEquals("categorical", ID.type[0])

		def TIME = dataInputVariables.TIME
		assertEquals("h", TIME.units[0])	
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
