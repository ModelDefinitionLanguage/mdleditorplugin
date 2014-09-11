package eu.ddmore.converter.mdl2json;

import static org.junit.Assert.*;

import org.apache.log4j.Logger

import eu.ddmore.converter.mdl2json.domain.Data
import eu.ddmore.converter.mdl2json.domain.MCLFile
import eu.ddmore.convertertoolbox.api.response.ConversionReport
import eu.ddmore.mdlparse.MdlParser
import groovy.json.JsonSlurper
import org.ddmore.mdl.mdl.Mcl
import org.junit.Ignore
import org.junit.Test;

class TestJSONDataObjectToMDL extends ConverterTestsParent {
	private static Logger logger = Logger.getLogger(TestJSONDataObjectToMDL.class)
	
	private static String jsonSource = '{"SOURCE":{"file":"\\"tumour_exposure.csv\\"","symbolName":"myData","identifier":"SOURCE","ignore":"\\"@\\"","inputformat":"nonmemFormat"}}'
	private static String jsonInputVars = '{"DATA_INPUT_VARIABLES":[{"name":"ID","type":"categorical"},{"name":"TIME","type":"continuous","units":"\\"h\\""},{"name":"AMT","type":"continuous","units":"\\"mg\\""},{"name":"DV","type":"continuous"}]}'
	
	@Test
	public void testSource() {
		def json = getJson(jsonSource)

		Data data = new Data(json)
		String expected = """dataobj {
    
    
    SOURCE{
        myData=list(
            file="tumour_exposure.csv",
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
        ID=list(type=categorical)
        TIME=list(type=continuous,units="h")
        AMT=list(type=continuous,units="mg")
        DV=list(type=continuous)
    }

    
}
"""
		assertEquals(expected, data.toMDL())
	}
	
	@Test
	public void testWarfarin() {
		def mdlFile = getFile("warfarin_DataObject.mdl")
		
		def json = getJsonFromMDLFile(mdlFile)
		
		MCLFile mclFile = new MCLFile(json)
		
		logger.debug(mclFile.toMDL())

		extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(mdlFile, "SOURCE", mclFile.toMDL())
		extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(mdlFile, "DATA_INPUT_VARIABLES", mclFile.toMDL())
		
	}
	
	@Test
	public void testTumour() {
		def mdlFile = getFile("tumour_DataObject.mdl")
		
		def json = getJsonFromMDLFile(mdlFile)
		
		MCLFile mclFile = new MCLFile(json)
		
		logger.debug(mclFile.toMDL())

		extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(mdlFile, "SOURCE", mclFile.toMDL())
		extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(mdlFile, "DATA_INPUT_VARIABLES", mclFile.toMDL())
		
	}
	
	@Test
	@Ignore("This model redefines variables in the DATA_INPUT_VARIABLES block, which isn't properly supported at the moment")
	public void testHamren() {
		def mdlFile = getFile("hamren_DataObject.mdl")
		
		def json = getJsonFromMDLFile(mdlFile)
		
		MCLFile mclFile = new MCLFile(json)
		
		logger.debug(mclFile.toMDL())

		extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(mdlFile, "SOURCE", mclFile.toMDL())
		extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(mdlFile, "DATA_INPUT_VARIABLES", mclFile.toMDL())
		
	}
	
}
