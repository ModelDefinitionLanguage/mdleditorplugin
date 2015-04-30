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

class JSONDataObjectToMDLTest extends ConverterTestsParent {
	private static Logger logger = Logger.getLogger(JSONDataObjectToMDLTest.class)
	
	// Using slashy strings /.../ here so we don't have to escape anything other than forward slashes 
	private String sourceBlockJson =
		/ {"SOURCE":{"file":"\"warfarin_conc.csv\"","ignore":"\"#\"","inputformat":"nonmemFormat"}} /
	private String dataInputVariablesJson_WarfarinAnalyticSolution =
        / {"DATA_INPUT_VARIABLES":[{"use":"id",".name":"ID"},{"use":"idv",".name":"TIME"},{"use":"covariate",".name":"WT"},{"use":"covariate",".name":"AGE"},{"define":"[{category=male, value=0}, {category=female, value=1}]","use":"covariate",".name":"SEX","type":"categorical(male, female)"},{"define":"D","use":"amt",".name":"AMT"},{"use":"dvid",".name":"DVID"},{"define":"Y","use":"dv",".name":"DV"},{"use":"mdv",".name":"MDV"}]} /
    private String dataInputVariablesJson_Hansson =
        / {"DATA_INPUT_VARIABLES":[{"use":"id",".name":"ID"},{".name":"CYCL","type":"categorical"},{"use":"idv",".name":"TIME","units":"\"h\""},{".name":"DAYS","type":"continuous","units":"\"day\""},{"define":"[{pred=VEGF_obs, predID=5}, {pred=sVEGFR2_obs, predID=6}, {pred=sVEGFR3_obs, predID=7}, {pred=sKIT_obs, predID=8}]","use":"dv",".name":"DV"},{"use":"dvid",".name":"FLAG"},{".name":"DVX","type":"continuous","units":"\"mg\/L\""},{"define":"DOSE","use":"amt",".name":"DOS"},{".name":"PLA","type":"categorical"},{"use":"covariate",".name":"CL","type":"continuous","units":"\"L\/h\""},{".name":"EVID","type":"categorical"}]} /
    private String dataInputVariablesJson_WarfarinPkBov =
        / {"DATA_INPUT_VARIABLES":[{"use":"id",".name":"ID"},{"use":"idv",".name":"TIME","units":"\"h\""},{"use":"covariate",".name":"WT","units":"\"kg\""},{"use":"covariate",".name":"AGE"},{"define":"[{female, 1}, {male, 0}, {MISSING, -99}]","use":"covariate",".name":"SEX","type":"categorical(male, female, MISSING)"},{"define":"INPUT_KA","use":"amt",".name":"AMT","units":"\"mg\""},{"use":"varlevel",".name":"OCC"},{"define":"Y","use":"dv",".name":"DV"},{"use":"mdv",".name":"MDV"}]} /
    private String declaredVariablesJson =
        / {"DECLARED_VARIABLES":[{".name":"DOSE"},{".name":"VEGF_obs"},{".name":"VEGFR2_obs"},{".name":"VEGFR3_obs"},{".name":"SKIT_obs"}]} /
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
	public void testDataInputVariablesBlock_WarfarinAnalyticSolution() {
		
		def json = getJson(dataInputVariablesJson_WarfarinAnalyticSolution)
		
		def dataObj = new Data(json)
		
		String expected = """dataobj {

    DATA_INPUT_VARIABLES {
        ID : {use=id}
        TIME : {use=idv}
        WT : {use=covariate}
        AGE : {use=covariate}
        SEX : {define=[{category=male, value=0}, {category=female, value=1}], type=categorical(male, female), use=covariate}
        AMT : {define=D, use=amt}
        DVID : {use=dvid}
        DV : {define=Y, use=dv}
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
        DV : {define=[{pred=VEGF_obs, predID=5}, {pred=sVEGFR2_obs, predID=6}, {pred=sVEGFR3_obs, predID=7}, {pred=sKIT_obs, predID=8}], use=dv}
        FLAG : {use=dvid}
        DVX : {type=continuous, units="mg/L"}
        DOS : {define=DOSE, use=amt}
        PLA : {type=categorical}
        CL : {type=continuous, units="L/h", use=covariate}
        EVID : {type=categorical}
    }

}
"""
        assertEquals(expected, dataObj.toMDL())
    }
    
    @Test
    public void testDataInputVariablesBlock_WarfarinPkBov() {
        
        def json = getJson(dataInputVariablesJson_WarfarinPkBov)
        
        def dataObj = new Data(json)
        
        String expected = """dataobj {

    DATA_INPUT_VARIABLES {
        ID : {use=id}
        TIME : {units="h", use=idv}
        WT : {units="kg", use=covariate}
        AGE : {use=covariate}
        SEX : {define=[{female, 1}, {male, 0}, {MISSING, -99}], type=categorical(male, female, MISSING), use=covariate}
        AMT : {define=INPUT_KA, units="mg", use=amt}
        OCC : {use=varlevel}
        DV : {define=Y, use=dv}
        MDV : {use=mdv}
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
        DOSE
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
