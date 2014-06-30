package eu.ddmore.converter.mdl2json

import eu.ddmore.mdlparse.MdlParser
import groovy.json.JsonSlurper
import java.io.File;

import org.apache.log4j.Logger;
import org.ddmore.mdl.mdl.Mcl

class MDLToJSONTest {
	final static String TEST_DATA_DIR = "./"
	
	private static Logger logger = Logger.getLogger(MDLToJSONTest.class)
	private final MDLToJSONConverter converter = new MDLToJSONConverter();

	
 	public Object getJson(String fileToConvert) {
         File srcFile = getFile(fileToConvert)
 
 		MdlParser p = new MdlParser()
 		Mcl mcl = p.parse(srcFile)
 
         String jsonText = converter.toJSON(mcl)
 		
 		logger.debug(jsonText)
 		
 		JsonSlurper slurper = new JsonSlurper();
 		slurper.parseText(jsonText)
     }

	public File getFile(final String pathToFile) {
		String path = TEST_DATA_DIR + pathToFile
		URL url = this.getClass().getResource(path)
		new File(url.getFile())        
	}

}
