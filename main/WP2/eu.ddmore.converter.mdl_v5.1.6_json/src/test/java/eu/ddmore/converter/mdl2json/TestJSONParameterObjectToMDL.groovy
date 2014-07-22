package eu.ddmore.converter.mdl2json;

import static org.junit.Assert.*;

import org.apache.log4j.Logger

import eu.ddmore.converter.mdl2json.domain.MCLFile
import eu.ddmore.converter.mdl2json.domain.Parameter
import eu.ddmore.convertertoolbox.api.response.ConversionReport
import eu.ddmore.mdlparse.MdlParser
import groovy.json.JsonSlurper
import org.ddmore.mdl.mdl.Mcl
import org.junit.Test;

class TestJSONParameterObjectToMDL extends ConverterTestsParent  {
	private static Logger logger = Logger.getLogger(TestJSONParameterObjectToMDL.class)

	@Test
	public void testProlactinMay2014() {
		
		def json = getJsonFromMDLFile("parameterWithMatrix.mdl")

		MCLFile mclFile = new MCLFile(json)
		
		String mdl = mclFile.toMDL()			
		logger.debug(mdl)
		assertNotEquals(null, mdl)
	}
	
	@Test
	public void testStructuralModel() {
		
		def simpleStructuralModel="""{\"STRUCTURAL\": {
			\"POP_KOUT\":{\"value\":\"0.664\",\"lo\":\".1\",\"hi\":\"10\"},
			\"POP_PRL0_IN_MALE_HV\":{\"value\":\"7.67\",\"lo\":\"1\",\"hi\":\"15\"},
			\"POP_PRL0_IN_MALE_PAT\":{\"value\":\"16.1\",\"lo\":\"1\",\"hi\":\"30\"}
		}}"""
		
		def json = new JsonSlurper().parseText(simpleStructuralModel)
		
		Parameter p = new Parameter(json)
		
		String expected = """parobj {
    STRUCTURAL{
        POP_KOUT=list(lo=.1,value=0.664,hi=10)
        POP_PRL0_IN_MALE_HV=list(lo=1,value=7.67,hi=15)
        POP_PRL0_IN_MALE_PAT=list(lo=1,value=16.1,hi=30)
    }
}
"""
		assertEquals(expected, p.toMDL())
	}
	
	@Test	
	public void testSimpleVariabilityModel() {
		def simpleVariabilityModel="""{\"VARIABILITY\":[
		{\"matrix\":{\"name\":\"struc1",\"type\":\"VAR\",\"content\":\"PPV_PRL0=.425,\n.758,PPV_KI=1.82\"}},
		{\"PPV_PHS2\":{\"value\":\"1.80\",\"type\":\"VAR\"}},
		{\"diag\":{\"name\":\"struc2\",\"type\":\"VAR\",\"content\":\"PPV_IOV_IN_PRL0_1=.0657\"}},
		{\"same\":{\"name\":\"struc2\",\"content\":[\"PPV_IOV_IN_PRL0_2\"]}},
		{\"RUV_EPS1\":{\"value\":\"1\",\"type\":\"VAR\",\"fix\":\"true\"}}]
}"""

		def json = new JsonSlurper().parseText(simpleVariabilityModel)
		
		Parameter p = new Parameter(json)
		
		String expected = """parobj {
    VARIABILITY{
        matrix(name="struc1", type="VAR"){
            PPV_PRL0=.425,
            .758,PPV_KI=1.82
        }
        PPV_PHS2=list(value=1.80,type=VAR)
        diag(name="struc2", type="VAR"){
            PPV_IOV_IN_PRL0_1=.0657
        }
        same(name="struc2"){
            PPV_IOV_IN_PRL0_2
        }
        RUV_EPS1=list(fix=true,value=1,type=VAR)
    }
}
"""
		assertEquals(expected, p.toMDL())
	}
}
