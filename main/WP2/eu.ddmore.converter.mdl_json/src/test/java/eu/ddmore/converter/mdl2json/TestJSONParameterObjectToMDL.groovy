package eu.ddmore.converter.mdl2json;

import static org.junit.Assert.*

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger
import org.junit.Ignore
import org.junit.Test

import eu.ddmore.converter.mdl2json.domain.MCLFile
import eu.ddmore.converter.mdl2json.domain.Parameter
import groovy.json.JsonSlurper

class TestJSONParameterObjectToMDL extends ConverterTestsParent  {
	
	private static Logger logger = Logger.getLogger(TestJSONParameterObjectToMDL.class)
	
	// Using slashy strings /.../ here so we don't have to escape anything other than forward slashes
	private static final String structuralBlockJson =
		/ {"STRUCTURAL":[{"lo":"0.001","name":"POP_CL","value":"0.1"},{"lo":"0.001","name":"POP_V","value":"8"},{"lo":"0.001","name":"POP_KA","value":"0.362"},{"lo":"0.001","name":"POP_TLAG","value":"1"},{"fix":"true","name":"BETA_CL_WT","value":"0.75"},{"fix":"true","name":"BETA_V_WT","value":"1"}]} /
	private static final String variabilityBlockJson =
		/ {"VARIABILITY":[{"PPV_CL":{"value":"0.1","type":"SD"}},{"PPV_V":{"value":"0.1","type":"SD"}},{"PPV_KA":{"value":"0.1","type":"SD"}},{"PPV_TLAG":{"value":"0.1","type":"SD"}},{"RUV_PROP":{"value":"0.1","type":"SD"}},{"RUV_ADD":{"value":"0.1","type":"SD"}},{"CORR_PPV_CL_V":{"lo":"-1.0","value":"0.01","hi":"1.0","type":"CORR"}}]} /
	private static final String complexVariabilityBlockJson = 
		/ {"VARIABILITY":[{"matrix":{"name":"\"struc1\"","type":"VAR","content":"BSVV1=0.015,\n0.00377, BSVCL=0.0158,\n0.0156, 0.0127, BSVV2=0.0218,\n0.0273, 0.0282, 0.0411, BSVQ=0.0804"}},{"matrix":{"name":"\"struc2\"","type":"VAR","content":"BOVV11=0.0254,\n0.0152, BOVCL1=0.016,\n0.011, 0.0102, BOVV21=0.00667,\n0.0275, 0.0137, 0.0105, BOVQ1=0.0313"}},{"same":{"name":"\"struc2\"","content":"BOVCL2,\nBOVV22,\nBOVQ2"}},{"same":{"name":"\"struc2\"","content":"BOVCL3,\nBOVV23,\nBOVQ3"}},{"same":{"name":"\"struc2\"","content":"BOVCL4,\nBOVV24,\nBOVQ4"}},{"same":{"name":"\"struc2\"","content":"BOVCL5,\nBOVV25,\nBOVQ5"}},{"same":{"name":"\"struc2\"","content":"BOVCL6,\nBOVV26,\nBOVQ6"}},{"same":{"name":"\"struc2\"","content":"BOVCL7,\nBOVV27,\nBOVQ7"}},{"same":{"name":"\"struc2\"","content":"BOVCL8,\nBOVV28,\nBOVQ8"}},{"same":{"name":"\"struc2\"","content":"BOVCL9,\nBOVV29,\nBOVQ9"}},{"same":{"name":"\"struc2\"","content":"BOVCL10,\nBOVV210,\nBOVQ10"}},{"PPV_CVTTK0":{"fix":"true","value":"0","type":"VAR"}},{"PPV_CVRSYN":{"value":"0.513","type":"VAR"}},{"PPV_CVCSSOP":{"value":"1.53","type":"VAR"}},{"matrix":{"name":"\"struc12\"","type":"VAR","content":"PPV_BTVV11=0.156,\n0.0407, PPV_BTVCL1=0.0292,\n0.018, 0.0273, PPV_BTVV21=0.043,\n-0.059, 0.0203, 0.0545, PPV_BTVQ1=0.115"}},{"same":{"name":"\"struc12\"","content":"PPV_BTVV12,\nPPV_BTVCL2,\nPPV_BTVV22,\nPPV_BTVQ2"}},{"same":{"name":"\"struc12\"","content":"PPV_BTVV13,\nPPV_BTVCL3,\nPPV_BTVV23,\nPPV_BTVQ3"}},{"same":{"name":"\"struc12\"","content":"PPV_BTVV14,\nPPV_BTVCL4,\nPPV_BTVV24,\nPPV_BTVQ4"}},{"same":{"name":"\"struc12\"","content":"PPV_BTVV15,\nPPV_BTVCL5,\nPPV_BTVV25,\nPPV_BTVQ5"}},{"RUV_CVCP":{"value":"0.0118","type":"VAR"}},{"RUV_SDCP":{"value":"0.0249","type":"VAR"}},{"diag":{"name":"\"struc3\"","type":"VAR","content":"PPV_IOV_IN_PRL0_1=.0657,\nPPV_IOV_IN_PRL0_2=.0757"}}]} /

	@Test
	public void testStructural() {
		
		def json = getJson(structuralBlockJson)
		
		def paramObj = new Parameter(json)
				
		String expected = """parobj {

    STRUCTURAL {
        POP_CL : {lo=0.001, value=0.1}
        POP_V : {lo=0.001, value=8}
        POP_KA : {lo=0.001, value=0.362}
        POP_TLAG : {lo=0.001, value=1}
        BETA_CL_WT : {fix=true, value=0.75}
        BETA_V_WT : {fix=true, value=1}
    }

}
"""
		assertEquals(expected, paramObj.toMDL())
	}
	
	@Test
	public void testVariability() {
		
		def json = getJson(variabilityBlockJson)
		
		def paramObj = new Parameter(json)
				
		String expected = """parobj {

    VARIABILITY {
        PPV_CL : {type=SD, value=0.1}
        PPV_V : {type=SD, value=0.1}
        PPV_KA : {type=SD, value=0.1}
        PPV_TLAG : {type=SD, value=0.1}
        RUV_PROP : {type=SD, value=0.1}
        RUV_ADD : {type=SD, value=0.1}
        CORR_PPV_CL_V : {hi=1.0, lo=-1.0, type=CORR, value=0.01}
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
	
	
	@Test
	@Ignore
	public void testPrior() {
		fail("Not implemented yet")
	}
	
}
