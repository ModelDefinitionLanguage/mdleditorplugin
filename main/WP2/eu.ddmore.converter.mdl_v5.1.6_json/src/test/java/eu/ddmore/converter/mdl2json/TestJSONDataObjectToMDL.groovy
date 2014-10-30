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
	
	// Using slashy strings /.../ here so we don't have to escape anything other than forward slashes 
	private String sourceBlockJson =
		/ {"SOURCE":{"file":"\"warfarin_conc.csv\"","ignore":"\"#\"","inputformat":"nonmemFormat"}} /
	private String dataInputVariablesJson =
		/ {"DATA_INPUT_VARIABLES":[{"name":"ID","type":"categorical"},{"name":"TIME","type":"continuous","units":"\"h\""},{"name":"logtWT","type":"continuous"},{"name":"AMT","type":"continuous","units":"\"mg\""},{"name":"DVID","type":"categorical"},{"name":"DV","type":"continuous"},{"name":"MDV","type":"categorical"}]} /
	private String dataDerivedVariablesJson =
		/ {"DATA_DERIVED_VARIABLES":[{"logtWT":"log(WT\/70)"}]} /
	
	@Test
	public void testSource() {
		
		def json = getJson(sourceBlockJson)

		def dataObj = new Data(json)
		
		String expected = """dataobj {

    SOURCE {
        file="warfarin_conc.csv"
        ignore="#"
        inputformat=nonmemFormat
    }

}
"""
		assertEquals(expected, dataObj.toMDL())
	}
	
	@Test
	public void testDataInputVariables() {
		
		def json = getJson(dataInputVariablesJson)
		
		def dataObj = new Data(json)
		
		String expected = """dataobj {

    DATA_INPUT_VARIABLES {
        ID : {type=categorical}
        TIME : {type=continuous, units="h"}
        logtWT : {type=continuous}
        AMT : {type=continuous, units="mg"}
        DVID : {type=categorical}
        DV : {type=continuous}
        MDV : {type=categorical}
    }

}
"""
		assertEquals(expected, dataObj.toMDL())
	}
	
	@Test
	public void testDataDerivedVariables() {
		
		def json = getJson(dataDerivedVariablesJson)
		
		def dataObj = new Data(json)
		
		String expected = """dataobj {

    DATA_DERIVED_VARIABLES {
        logtWT = log(WT/70)
    }

}
"""
		assertEquals(expected, dataObj.toMDL())
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
