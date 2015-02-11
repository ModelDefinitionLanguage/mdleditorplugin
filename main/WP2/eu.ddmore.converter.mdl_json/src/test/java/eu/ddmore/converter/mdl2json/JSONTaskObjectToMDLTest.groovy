package eu.ddmore.converter.mdl2json;

import static org.junit.Assert.*

import org.apache.log4j.Logger
import org.junit.Test

import eu.ddmore.converter.mdl2json.domain.Task

class JSONTaskObjectToMDLTest extends ConverterTestsParent  {
	
	private static Logger logger = Logger.getLogger(JSONTaskObjectToMDLTest.class)
	
	// Using slashy strings /.../ here so we don't have to escape anything other than forward slashes
	private static final String estimateBlockJson =
		/ {"ESTIMATE":"target=MLXTRAN_CODE\nversion=\"4.3.2\"\nalgo=[\"SAEM\"]"} /

	@Test
	public void testEstimate() {
		
		def json = getJson(estimateBlockJson)
		
		def taskObj = new Task(json)
				
		String expected = """taskobj {

    ESTIMATE {
        target=MLXTRAN_CODE
        version="4.3.2"
        algo=["SAEM"]
    }

}
"""
		assertEquals(expected, taskObj.toMDL())
	}
	
}
