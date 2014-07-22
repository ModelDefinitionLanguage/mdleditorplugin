package eu.ddmore.converter.mdl2json;

import static org.junit.Assert.*;

import org.apache.log4j.Logger

import eu.ddmore.converter.mdl2json.domain.Data
import eu.ddmore.converter.mdl2json.domain.MCLFile
import eu.ddmore.convertertoolbox.api.response.ConversionReport
import eu.ddmore.mdlparse.MdlParser
import groovy.json.JsonSlurper
import org.ddmore.mdl.mdl.Mcl
import org.junit.Test;

class TestJSONDataObjectToMDL extends ConverterTestsParent {
	private static Logger logger = Logger.getLogger(TestJSONDataObjectToMDL.class)
	
	@Test
	public void testSource() {
		def json = getJson(jsonSource)

		Data data = new Data(json)
		String expected = """dataobj {
    
    
    SOURCE{
        myData=list(
            file="ex_data_prolactin.csv",
            ignore="@",
            inputformat=nonmemFormat
        )
    }

}
"""	
		assertEquals(expected, data.toMDL())			
	}
	
	@Test
	public void testDataInputVariables() {
		def json = getJson(jsonInputVars)
		
		Data data = new Data(json)
		String expected = """dataobj {
    
    DATA_INPUT_VARIABLES {
        II=list(type=continuous,units="h")
        CMT=list(type=categorical)
    }

    
}
"""
	assertEquals(expected, data.toMDL())
		//println data.toMDL()		
	}
	
	private String jsonSource = '{"SOURCE":{"file":"\\"ex_data_prolactin.csv\\"","symbolName":"myData","identifier":"SOURCE","ignore":"\\"@\\"","inputformat":"nonmemFormat"}}}'
	private String jsonInputVars = '{"DATA_INPUT_VARIABLES":{"II":{"name":"II","type":"continuous","units":"\\"h\\""},"CMT":{"name":"CMT","type":"categorical"}}}'
}
