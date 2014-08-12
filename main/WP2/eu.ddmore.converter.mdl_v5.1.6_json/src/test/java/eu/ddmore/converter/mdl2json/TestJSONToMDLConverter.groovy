package eu.ddmore.converter.mdl2json;

import static org.junit.Assert.*;

import org.apache.log4j.Logger

import eu.ddmore.converter.mdl2json.domain.MCLFile
import eu.ddmore.convertertoolbox.api.response.ConversionReport
import eu.ddmore.mdlparse.MdlParser
import groovy.json.JsonSlurper
import org.ddmore.mdl.mdl.Mcl
import org.junit.Test;
import org.junit.Ignore;

class TestJSONToMDLConverter extends ConverterTestsParent {
	private static Logger logger = Logger.getLogger(TestJSONToMDLConverter.class)
	
	@Test
	public void testProlactinMay2014() {
        
        def File mdlFile = getFileFromModelsProject("FribergCPT2009/ex_model7_prolactin_01July2014_OAM.mdl")
        def json = getJsonFromMDLFile(mdlFile)

		MCLFile mclFile = new MCLFile(json)
		
		assertNotNull(mclFile.toMDL() )			

	}

    @Test
	@Ignore("Pending re-generation of MOG from R")
	public void testRGeneratedMOG() {
		
		File jsonFile = getFile("myMog3.json")
		
		String content = jsonFile.getText()
		
		def json = getJson(content)

		MCLFile mclFile = new MCLFile(json)
		
		String mdl = mclFile.toMDL()
		
		assertNotEquals(null, mdl )			
		
		logger.debug(mdl)
	}
	
}
