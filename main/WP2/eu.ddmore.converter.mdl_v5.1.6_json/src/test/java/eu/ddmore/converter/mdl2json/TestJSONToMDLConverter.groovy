package eu.ddmore.converter.mdl2json;

import static org.junit.Assert.*;

import org.apache.log4j.Logger

import eu.ddmore.converter.mdl2json.domain.MCLFile
import eu.ddmore.convertertoolbox.api.response.ConversionReport
import eu.ddmore.mdlparse.MdlParser
import groovy.json.JsonSlurper
import org.ddmore.mdl.mdl.Mcl
import org.junit.Test;

class TestJSONToMDLConverter extends MDLToJSONTest {
	private static Logger logger = Logger.getLogger(TestJSONToMDLConverter.class)
	
	@Test
	public void testProlactinMay2014() {
		def json = getJsonFromMDLFile("ex_model7_prolactin_May2014_OAM.mdl")

		MCLFile mclFile = new MCLFile(json)
		
		assertNotEquals(null, mclFile.toMDL() )			
			
	}
}
