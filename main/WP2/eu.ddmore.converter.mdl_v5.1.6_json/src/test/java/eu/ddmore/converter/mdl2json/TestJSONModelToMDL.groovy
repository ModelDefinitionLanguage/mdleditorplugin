package eu.ddmore.converter.mdl2json;

import static org.junit.Assert.*

import org.apache.log4j.Logger
import org.junit.Test

import eu.ddmore.converter.mdl2json.domain.MCLFile


class TestJSONModelToMDL extends ConverterTestsParent {
	private static final Logger logger = Logger.getLogger(TestJSONModelToMDL.class)
	
	@Test
	public void testProlactin() {
		def json = getJsonFromMDLFile("prolactinModel.mdl")
		
		MCLFile mclFile = new MCLFile(json)
		
		logger.debug(mclFile.toMDL())
				
		assertNotEquals(null, mclFile.toMDL() )
		
	}

}
