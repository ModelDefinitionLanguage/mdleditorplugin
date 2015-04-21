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
	private String dataInputVariablesJson_Warfarin =
        / {"DATA_INPUT_VARIABLES":[{"use":"id",".name":"ID"},{"use":"idv",".name":"TIME","units":"\"h\""},{"use":"covariate",".name":"WT","type":"continuous"},{"use":"covariate",".name":"AGE"},{"define":{"male":"0","female":"1"},"use":"covariate",".name":"SEX","type":"categorical(male, female)"},{"use":"amt",".name":"AMT","cmpt":"D"},{"use":"dvid",".name":"DVID"},{"prediction":"Y when DVID==1 otherwise N;","use":"dv",".name":"DV","units":"\"mg\/L\""},{"use":"mdv",".name":"MDV"}]} /
    private String dataInputVariablesJson_Hansson =
        / {"DATA_INPUT_VARIABLES":[{"use":"id",".name":"ID"},{".name":"CYCL","type":"categorical"},{"use":"idv",".name":"TIME","units":"\"h\""},{".name":"DAYS","type":"continuous","units":"\"day\""},{"prediction":"VEGF_obs when FLAG==5, VEGFR2_obs when FLAG==6, VEGFR3_obs when FLAG==7, SKIT_obs when FLAG==8;","use":"dv",".name":"DV"},{".name":"FLAG","type":"categorical"},{".name":"DVX","type":"continuous","units":"\"mg\/L\""},{"use":"amt",".name":"DOS","units":"\"mg\""},{".name":"PLA","type":"categorical"},{"use":"covariate",".name":"CL","type":"continuous","units":"\"L\/h\""},{".name":"EVID","type":"categorical"}]} /
    private String declaredVariablesJson =
        / {"DECLARED_VARIABLES":[{".name":"VEGF_obs"},{".name":"VEGFR2_obs"},{".name":"VEGFR3_obs"},{".name":"SKIT_obs"}]} /
    private String dataDerivedVariablesJson =
        / {"DATA_DERIVED_VARIABLES":[{".expr":"TIME when AMT>0;",".name":"DT"}]} /
        
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
	public void testDataInputVariablesBlock_Warfarin() {
		
		def json = getJson(dataInputVariablesJson_Warfarin)
		
		def dataObj = new Data(json)
		
		String expected = """dataobj {

    DATA_INPUT_VARIABLES {
        ID : {use=id}
        TIME : {units="h", use=idv}
        WT : {type=continuous, use=covariate}
        AGE : {use=covariate}
        SEX : {define={female=1, male=0}, type=categorical(male, female), use=covariate}
        AMT : {cmpt=D, use=amt}
        DVID : {use=dvid}
        DV : {prediction=Y when DVID==1 otherwise N;, units="mg/L", use=dv}
        MDV : {use=mdv}
    }

}
"""
		assertEquals(expected, dataObj.toMDL())
	}
    
    @Test
    public void testDataInputVariablesBlock_Hansson() {
        
        def json = getJson(dataInputVariablesJson_Hansson)
        
        def dataObj = new Data(json)
        
        String expected = """dataobj {

    DATA_INPUT_VARIABLES {
        ID : {use=id}
        CYCL : {type=categorical}
        TIME : {units="h", use=idv}
        DAYS : {type=continuous, units="day"}
        DV : {prediction=VEGF_obs when FLAG==5, VEGFR2_obs when FLAG==6, VEGFR3_obs when FLAG==7, SKIT_obs when FLAG==8;, use=dv}
        FLAG : {type=categorical}
        DVX : {type=continuous, units="mg/L"}
        DOS : {units="mg", use=amt}
        PLA : {type=categorical}
        CL : {type=continuous, units="L/h", use=covariate}
        EVID : {type=categorical}
    }

}
"""
        assertEquals(expected, dataObj.toMDL())
    }
    
    @Test
    public void testDeclaredVariablesBlock() {
        
        def json = getJson(declaredVariablesJson)
        
        def dataObj = new Data(json)
        
        String expected = """dataobj {

    DECLARED_VARIABLES {
        VEGF_obs
        VEGFR2_obs
        VEGFR3_obs
        SKIT_obs
    }

}
"""
        assertEquals(expected, dataObj.toMDL())
    }
    
    @Test
    public void testDataDerivedVariablesBlock() {
        
        def json = getJson(dataDerivedVariablesJson)
        
        def dataObj = new Data(json)
        
        String expected = """dataobj {

    DATA_DERIVED_VARIABLES {
        DT = TIME when AMT>0;
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
