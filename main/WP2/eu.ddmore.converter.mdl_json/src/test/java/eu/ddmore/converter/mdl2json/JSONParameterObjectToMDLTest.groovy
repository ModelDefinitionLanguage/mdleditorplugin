/*******************************************************************************
 * Copyright (C) 2014-5 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json;

import static org.junit.Assert.*

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger
import org.junit.Ignore
import org.junit.Test

import eu.ddmore.converter.mdl2json.domain.MCLFile
import eu.ddmore.converter.mdl2json.domain.Parameter
import groovy.json.JsonSlurper

class JSONParameterObjectToMDLTest extends ConverterTestsParent  {

    private static Logger logger = Logger.getLogger(JSONParameterObjectToMDLTest.class)

    // Using slashy strings /.../ here so we don't have to escape anything other than forward slashes
    private static final String declaredVariablesBlockJson =
        / {"DECLARED_VARIABLES":[{".name":"ETA_CL"},{".name":"ETA_V"}]} /
    private static final String structuralBlockJson_Warfarin =
        / {"STRUCTURAL":[{"lo":"0.001","value":"0.1",".name":"POP_CL"},{"lo":"0.001","value":"8",".name":"POP_V"},{"lo":"0.001","value":"0.362",".name":"POP_KA"},{"lo":"0.001","value":"1","hi":"10",".name":"POP_TLAG"},{"fix":"true","value":"0.75",".name":"BETA_CL_WT"},{"fix":"true","value":"1",".name":"BETA_V_WT"},{"lo":"0","value":"0.1",".name":"RUV_PROP"},{"lo":"0","value":"0.1",".name":"RUV_ADD"}]} /
    private static final String structuralBlockJson_Hansson =
        / {"STRUCTURAL":[{"fix":"true","value":"1",".name":"POP_IMAX"},{"lo":"0","value":"1",".name":"POP_IC50","units":"\"mg\/L*h\""},{"lo":"0","value":"59.7",".name":"POP_BM0","units":"\"pg\/mL\""},{"lo":"0","value":"91",".name":"POP_MRT","units":"\"h\""},{"lo":"0","value":"3.31",".name":"POP_HILL"},{"lo":"-0.06","value":"0.035",".name":"POP_SLP","units":"\"1\/h\""},{"lo":"0","value":"8670",".name":"POP_BM02","units":"\"pg\/mL\""},{"lo":"0","value":"554",".name":"POP_MRT2","units":"\"h\""},{"lo":"0","value":"1.54",".name":"POP_HILL2"},{"lo":"0","value":"63900",".name":"POP_BM03","units":"\"pg\/mL\""},{"lo":"0","value":"401",".name":"POP_MRT3","units":"\"h\""},{"lo":"0","value":"39200",".name":"POP_BM0S","units":"\"pg\/mL\""},{"lo":"0","value":"2430",".name":"POP_MRTS","units":"\"h\""},{"lo":"0","value":"0.445",".name":"POP_RES_VEGF_ADD","units":"\"pg\/mL\""},{"lo":"0","value":"0.12",".name":"POP_RES_sVEGFR2_ADD","units":"\"pg\/mL\""},{"lo":"0","value":"583",".name":"POP_RES_sVEGFR2_PROP"},{"lo":"0","value":"0.22",".name":"POP_RES_sVEGFR3_ADD","units":"\"pg\/mL\""},{"lo":"0","value":"0.224",".name":"POP_RES_sKIT_ADD","units":"\"pg\/mL\""}]} /
    private static final String variabilityBlockJson_Warfarin =
        / {"VARIABILITY":[{"PPV_CL":{"value":"0.1","type":"SD"}},{"PPV_V":{"value":"0.1","type":"SD"}},{"PPV_KA":{"value":"0.1","type":"SD"}},{"PPV_TLAG":{"value":"0.1","type":"SD"}},{"OMEGA":{"value":"[0.01]","params":"[ETA_CL, ETA_V]","type":"CORR"}}]} /
    private static final String variabilityBlockJson_WarfarinPkBov =
        / {"VARIABILITY":[{"BSV_CL":{"value":"0.1","type":"VAR"}},{"BSV_V":{"value":"0.1","type":"VAR"}},{"BOV_CL":{"fix":"true","value":"0.1","type":"VAR"}},{"BOV_V":{"fix":"true","value":"0.1","type":"VAR"}},{"BSV_KA":{"value":"0.1","type":"VAR"}},{"BSV_TLAG":{"value":"0.1","type":"VAR"}},{"BOV_KA":{"value":"0.1","type":"VAR"}},{"BOV_TLAG":{"value":"0.1","type":"VAR"}},{"BOV_COV_CL_V":{"value":"0.01","params":"[eta_BOV_CL, eta_BOV_V]","type":"COV"}},{"BOV_COV_KA_TLAG":{"value":"0.01","params":"[eta_BOV_KA, eta_BOV_TLAG]","type":"COV"}}]} /
    private static final String complexVariabilityBlockJson =
        / {"VARIABILITY":[{"matrix":{"name":"\"struc1\"","type":"VAR","content":"BSVV1=0.015,\n0.00377, BSVCL=0.0158,\n0.0156, 0.0127, BSVV2=0.0218,\n0.0273, 0.0282, 0.0411, BSVQ=0.0804"}},{"matrix":{"name":"\"struc2\"","type":"VAR","content":"BOVV11=0.0254,\n0.0152, BOVCL1=0.016,\n0.011, 0.0102, BOVV21=0.00667,\n0.0275, 0.0137, 0.0105, BOVQ1=0.0313"}},{"same":{"name":"\"struc2\"","content":"BOVCL2,\nBOVV22,\nBOVQ2"}},{"same":{"name":"\"struc2\"","content":"BOVCL3,\nBOVV23,\nBOVQ3"}},{"same":{"name":"\"struc2\"","content":"BOVCL4,\nBOVV24,\nBOVQ4"}},{"same":{"name":"\"struc2\"","content":"BOVCL5,\nBOVV25,\nBOVQ5"}},{"same":{"name":"\"struc2\"","content":"BOVCL6,\nBOVV26,\nBOVQ6"}},{"same":{"name":"\"struc2\"","content":"BOVCL7,\nBOVV27,\nBOVQ7"}},{"same":{"name":"\"struc2\"","content":"BOVCL8,\nBOVV28,\nBOVQ8"}},{"same":{"name":"\"struc2\"","content":"BOVCL9,\nBOVV29,\nBOVQ9"}},{"same":{"name":"\"struc2\"","content":"BOVCL10,\nBOVV210,\nBOVQ10"}},{"PPV_CVTTK0":{"fix":"true","value":"0","type":"VAR"}},{"PPV_CVRSYN":{"value":"0.513","type":"VAR"}},{"PPV_CVCSSOP":{"value":"1.53","type":"VAR"}},{"matrix":{"name":"\"struc12\"","type":"VAR","content":"PPV_BTVV11=0.156,\n0.0407, PPV_BTVCL1=0.0292,\n0.018, 0.0273, PPV_BTVV21=0.043,\n-0.059, 0.0203, 0.0545, PPV_BTVQ1=0.115"}},{"same":{"name":"\"struc12\"","content":"PPV_BTVV12,\nPPV_BTVCL2,\nPPV_BTVV22,\nPPV_BTVQ2"}},{"same":{"name":"\"struc12\"","content":"PPV_BTVV13,\nPPV_BTVCL3,\nPPV_BTVV23,\nPPV_BTVQ3"}},{"same":{"name":"\"struc12\"","content":"PPV_BTVV14,\nPPV_BTVCL4,\nPPV_BTVV24,\nPPV_BTVQ4"}},{"same":{"name":"\"struc12\"","content":"PPV_BTVV15,\nPPV_BTVCL5,\nPPV_BTVV25,\nPPV_BTVQ5"}},{"RUV_CVCP":{"value":"0.0118","type":"VAR"}},{"RUV_SDCP":{"value":"0.0249","type":"VAR"}},{"diag":{"name":"\"struc3\"","type":"VAR","content":"PPV_IOV_IN_PRL0_1=.0657,\nPPV_IOV_IN_PRL0_2=.0757"}}]} /

    @Test
    public void testDeclaredVariablesBlock() {

        def json = getJson(declaredVariablesBlockJson)

        def paramObj = new Parameter(json)

        String expected = """parobj {

    DECLARED_VARIABLES {
        ETA_CL
        ETA_V
    }

}
"""
        assertEquals(expected, paramObj.toMDL())
    }

    @Test
    public void testStructuralBlock_Warfarin() {

        def json = getJson(structuralBlockJson_Warfarin)

        def paramObj = new Parameter(json)

        String expected = """parobj {

    STRUCTURAL {
        POP_CL : {lo=0.001, value=0.1}
        POP_V : {lo=0.001, value=8}
        POP_KA : {lo=0.001, value=0.362}
        POP_TLAG : {hi=10, lo=0.001, value=1}
        BETA_CL_WT : {fix=true, value=0.75}
        BETA_V_WT : {fix=true, value=1}
        RUV_PROP : {lo=0, value=0.1}
        RUV_ADD : {lo=0, value=0.1}
    }

}
"""
        assertEquals(expected, paramObj.toMDL())
    }

    @Test
    public void testStructuralBlock_Hansson() {

        def json = getJson(structuralBlockJson_Hansson)

        def paramObj = new Parameter(json)

        String expected = """parobj {

    STRUCTURAL {
        POP_IMAX : {fix=true, value=1}
        POP_IC50 : {lo=0, units="mg/L*h", value=1}
        POP_BM0 : {lo=0, units="pg/mL", value=59.7}
        POP_MRT : {lo=0, units="h", value=91}
        POP_HILL : {lo=0, value=3.31}
        POP_SLP : {lo=-0.06, units="1/h", value=0.035}
        POP_BM02 : {lo=0, units="pg/mL", value=8670}
        POP_MRT2 : {lo=0, units="h", value=554}
        POP_HILL2 : {lo=0, value=1.54}
        POP_BM03 : {lo=0, units="pg/mL", value=63900}
        POP_MRT3 : {lo=0, units="h", value=401}
        POP_BM0S : {lo=0, units="pg/mL", value=39200}
        POP_MRTS : {lo=0, units="h", value=2430}
        POP_RES_VEGF_ADD : {lo=0, units="pg/mL", value=0.445}
        POP_RES_sVEGFR2_ADD : {lo=0, units="pg/mL", value=0.12}
        POP_RES_sVEGFR2_PROP : {lo=0, value=583}
        POP_RES_sVEGFR3_ADD : {lo=0, units="pg/mL", value=0.22}
        POP_RES_sKIT_ADD : {lo=0, units="pg/mL", value=0.224}
    }

}
"""
        assertEquals(expected, paramObj.toMDL())
    }

    @Test
    public void testVariabilityBlock_Warfarin() {

        def json = getJson(variabilityBlockJson_Warfarin)

        def paramObj = new Parameter(json)

        String expected = """parobj {

    VARIABILITY {
        PPV_CL : {type=SD, value=0.1}
        PPV_V : {type=SD, value=0.1}
        PPV_KA : {type=SD, value=0.1}
        PPV_TLAG : {type=SD, value=0.1}
        OMEGA : {params=[ETA_CL, ETA_V], type=CORR, value=[0.01]}
    }

}
"""
        assertEquals(expected, paramObj.toMDL())
    }

    @Test
    public void testVariabilityBlock_WarfarinPkBov() {

        def json = getJson(variabilityBlockJson_WarfarinPkBov)

        def paramObj = new Parameter(json)

        String expected = """parobj {

    VARIABILITY {
        BSV_CL : {type=VAR, value=0.1}
        BSV_V : {type=VAR, value=0.1}
        BOV_CL : {fix=true, type=VAR, value=0.1}
        BOV_V : {fix=true, type=VAR, value=0.1}
        BSV_KA : {type=VAR, value=0.1}
        BSV_TLAG : {type=VAR, value=0.1}
        BOV_KA : {type=VAR, value=0.1}
        BOV_TLAG : {type=VAR, value=0.1}
        BOV_COV_CL_V : {params=[eta_BOV_CL, eta_BOV_V], type=COV, value=0.01}
        BOV_COV_KA_TLAG : {params=[eta_BOV_KA, eta_BOV_TLAG], type=COV, value=0.01}
    }

}
"""
        assertEquals(expected, paramObj.toMDL())
    }

    /**
     * Testing against JSON converted from complexParameter.mdl
     */
    @Test
    public void testComplexVariability() {

        def json = getJson(complexVariabilityBlockJson)

        def paramObj = new Parameter(json)

        String expected = """parobj {

    VARIABILITY {
        matrix(name="struc1", type=VAR) {
            BSVV1=0.015,
            0.00377, BSVCL=0.0158,
            0.0156, 0.0127, BSVV2=0.0218,
            0.0273, 0.0282, 0.0411, BSVQ=0.0804
        }
        matrix(name="struc2", type=VAR) {
            BOVV11=0.0254,
            0.0152, BOVCL1=0.016,
            0.011, 0.0102, BOVV21=0.00667,
            0.0275, 0.0137, 0.0105, BOVQ1=0.0313
        }
        same(name="struc2") {
            BOVCL2,
            BOVV22,
            BOVQ2
        }
        same(name="struc2") {
            BOVCL3,
            BOVV23,
            BOVQ3
        }
        same(name="struc2") {
            BOVCL4,
            BOVV24,
            BOVQ4
        }
        same(name="struc2") {
            BOVCL5,
            BOVV25,
            BOVQ5
        }
        same(name="struc2") {
            BOVCL6,
            BOVV26,
            BOVQ6
        }
        same(name="struc2") {
            BOVCL7,
            BOVV27,
            BOVQ7
        }
        same(name="struc2") {
            BOVCL8,
            BOVV28,
            BOVQ8
        }
        same(name="struc2") {
            BOVCL9,
            BOVV29,
            BOVQ9
        }
        same(name="struc2") {
            BOVCL10,
            BOVV210,
            BOVQ10
        }
        PPV_CVTTK0 : {fix=true, type=VAR, value=0}
        PPV_CVRSYN : {type=VAR, value=0.513}
        PPV_CVCSSOP : {type=VAR, value=1.53}
        matrix(name="struc12", type=VAR) {
            PPV_BTVV11=0.156,
            0.0407, PPV_BTVCL1=0.0292,
            0.018, 0.0273, PPV_BTVV21=0.043,
            -0.059, 0.0203, 0.0545, PPV_BTVQ1=0.115
        }
        same(name="struc12") {
            PPV_BTVV12,
            PPV_BTVCL2,
            PPV_BTVV22,
            PPV_BTVQ2
        }
        same(name="struc12") {
            PPV_BTVV13,
            PPV_BTVCL3,
            PPV_BTVV23,
            PPV_BTVQ3
        }
        same(name="struc12") {
            PPV_BTVV14,
            PPV_BTVCL4,
            PPV_BTVV24,
            PPV_BTVQ4
        }
        same(name="struc12") {
            PPV_BTVV15,
            PPV_BTVCL5,
            PPV_BTVV25,
            PPV_BTVQ5
        }
        RUV_CVCP : {type=VAR, value=0.0118}
        RUV_SDCP : {type=VAR, value=0.0249}
        diag(name="struc3", type=VAR) {
            PPV_IOV_IN_PRL0_1=.0657,
            PPV_IOV_IN_PRL0_2=.0757
        }
    }

}
"""
        assertEquals(expected, paramObj.toMDL())
    }

}