package eu.ddmore.converter.mdl2json;

import static org.junit.Assert.*

import org.apache.log4j.Logger
import org.junit.Test

import eu.ddmore.converter.mdl2json.domain.Task

class TestJSONTaskObjectToMDL extends ConverterTestsParent  {
	
	private static Logger logger = Logger.getLogger(TestJSONTaskObjectToMDL.class)
	
	// Using slashy strings /.../ here so we don't have to escape anything other than forward slashes
    private static final String estimateBlockJson_Hansson =
        / {"ESTIMATE":"target=NMTRAN_CODE\ncov=true\nalgo=[\"FOCE\"]"} /
	private static final String estimateBlockJson_WarfarinPkBov =
		/ {"ESTIMATE":"target=NMTRAN_CODE\nversion=\"7.2\"\nalgo=[\"FOCE  INTER\"]"} /

	@Test
	public void testEstimateBlock_Hansson() {
		
		def json = getJson(estimateBlockJson_Hansson)
		
		def taskObj = new Task(json)
				
		String expected = """taskobj {

    ESTIMATE {
        target=NMTRAN_CODE
        cov=true
        algo=["FOCE"]
    }

}
"""
		assertEquals(expected, taskObj.toMDL())
	}
    
    @Test
    public void testEstimateBlock_WarfarinPkBov() {
        
        def json = getJson(estimateBlockJson_WarfarinPkBov)
        
        def taskObj = new Task(json)
                
        String expected = """taskobj {

    ESTIMATE {
        target=NMTRAN_CODE
        version="7.2"
        algo=["FOCE  INTER"]
    }

}
"""
        assertEquals(expected, taskObj.toMDL())
    }
	
}
