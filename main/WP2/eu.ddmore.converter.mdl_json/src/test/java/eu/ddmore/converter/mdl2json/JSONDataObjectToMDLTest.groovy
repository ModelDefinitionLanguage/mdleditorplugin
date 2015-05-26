/*******************************************************************************
 * Copyright (C) 2014-5 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
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
    private String sourceBlockJson_UseCase1 =
        / {"SOURCE":{"file":"\"warfarin_conc.csv\"","ignore":"\"#\"","inputformat":"nonmemFormat"}} /
    private String dataInputVariablesJson_UseCase19 =
        / {"DATA_INPUT_VARIABLES":[{"use":"id",".name":"ID"},{"use":"idv",".name":"TIME","units":"\"h\""},{"define":"WT","use":"covariate",".name":"WT","units":"\"kg\""},{"use":"covariate",".name":"AGE"},{"define":"[{female, 1}, {male, 0}]","use":"covariate",".name":"SEX","type":"categorical(male, female)"},{"define":"DEP","use":"amt",".name":"AMT","units":"\"mg\""},{"use":"varlevel",".name":"OCC"},{"define":"Y","use":"dv",".name":"DV"},{"use":"mdv",".name":"MDV"}]} /
    private String dataInputVariablesJson_UseCase16 =
        / {"DATA_INPUT_VARIABLES":[{"use":"id",".name":"ID"},{".name":"CYCL","type":"categorical"},{"use":"idv",".name":"TIME","units":"\"h\""},{"use":"dvid",".name":"FLAG"},{".name":"DVX","type":"continuous","units":"\"mg\/L\""},{"define":"[{pred=LNVEGF_obs, predId=5}, {pred=LNsVEGFR2_obs, predId=6}, {pred=LNsVEGFR3_obs, predId=7}, {pred=LNsKIT_obs, predId=8}]","use":"dv",".name":"DV"},{"define":"DOSE","use":"amt",".name":"DOS"},{".name":"PLA","type":"categorical"},{"use":"covariate",".name":"CL","type":"continuous","units":"\"L\/h\""},{".name":"EVID","type":"categorical"}]} /
    private String dataInputVariablesJson_UseCase8 =
        / {"DATA_INPUT_VARIABLES":[{"use":"id",".name":"ID"},{"use":"idv",".name":"TIME","units":"\"h\""},{"use":"covariate",".name":"WT","units":"\"kg\""},{"use":"covariate",".name":"AGE"},{"define":"[{female, 1}, {male, 0}, {MISSING, -99}]","use":"covariate",".name":"SEX","type":"categorical(male, female, MISSING)"},{"define":"INPUT_KA","use":"amt",".name":"AMT","units":"\"mg\""},{"use":"varlevel",".name":"OCC"},{"define":"Y","use":"dv",".name":"DV"},{"use":"mdv",".name":"MDV"}]} /
    private String declaredVariablesJson_UseCase16 =
        / {"DECLARED_VARIABLES":[{".name":"DOSE"},{".name":"LNVEGF_obs"},{".name":"LNsVEGFR2_obs"},{".name":"LNsVEGFR3_obs"},{".name":"LNsKIT_obs"}]} /
    private String dataDerivedVariablesJson_UseCase1_10 =
        / {"DATA_DERIVED_VARIABLES":[{".expr":"TIME when AMT>0;",".name":"DT"}]} /

    @Test
    public void testSource() {

        def json = getJson(sourceBlockJson_UseCase1)

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

    /**
     * Testing attributes like:
     * define=[{female, 1}, {male, 0}]
     */
    @Test
    public void testDataInputVariablesBlock_ContainingAttributeBeingListOfSetsOfValues() {
        
        def json = getJson(dataInputVariablesJson_UseCase19)
        
        def dataObj = new Data(json)
        
        String expected = """dataobj {

    DATA_INPUT_VARIABLES {
        ID : {use=id}
        TIME : {units="h", use=idv}
        WT : {define=WT, units="kg", use=covariate}
        AGE : {use=covariate}
        SEX : {define=[{female, 1}, {male, 0}], type=categorical(male, female), use=covariate}
        AMT : {define=DEP, units="mg", use=amt}
        OCC : {use=varlevel}
        DV : {define=Y, use=dv}
        MDV : {use=mdv}
    }

}
"""
        assertEquals(expected, dataObj.toMDL())
    }

    /**
     * Testing attributes like:
     * define = [{pred=LNVEGF_obs, predid=5}, ...]
     */
    @Test
    public void testDataInputVariablesBlock_ContainingAttributeBeingListOfSetsOfNameValuePairs() {

        def json = getJson(dataInputVariablesJson_UseCase16)

        def dataObj = new Data(json)

        String expected = """dataobj {

    DATA_INPUT_VARIABLES {
        ID : {use=id}
        CYCL : {type=categorical}
        TIME : {units="h", use=idv}
        FLAG : {use=dvid}
        DVX : {type=continuous, units="mg/L"}
        DV : {define=[{pred=LNVEGF_obs, predId=5}, {pred=LNsVEGFR2_obs, predId=6}, {pred=LNsVEGFR3_obs, predId=7}, {pred=LNsKIT_obs, predId=8}], use=dv}
        DOS : {define=DOSE, use=amt}
        PLA : {type=categorical}
        CL : {type=continuous, units="L/h", use=covariate}
        EVID : {type=categorical}
    }

}
"""
        assertEquals(expected, dataObj.toMDL())
    }

    /**
     * Testing attributes like:
     * type=categorical(male, female, MISSING)
     */
    @Test
    public void testDataInputVariablesBlock_ContainingAttributeBeingNamedSetOfValues() {

        def json = getJson(dataInputVariablesJson_UseCase8)

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

        def json = getJson(declaredVariablesJson_UseCase16)

        def dataObj = new Data(json)

        String expected = """dataobj {

    DECLARED_VARIABLES {
        DOSE
        LNVEGF_obs
        LNsVEGFR2_obs
        LNsVEGFR3_obs
        LNsKIT_obs
    }

}
"""
        assertEquals(expected, dataObj.toMDL())
    }

    @Test
    public void testDataDerivedVariablesBlock() {

        def json = getJson(dataDerivedVariablesJson_UseCase1_10)

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