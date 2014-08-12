package eu.ddmore.converter.mdl2json;

import static org.junit.Assert.*

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger
import org.junit.Test

import eu.ddmore.converter.mdl2json.domain.MCLFile
import eu.ddmore.converter.mdl2json.domain.Parameter
import groovy.json.JsonSlurper

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
		
		def simpleStructuralModel="""{\"STRUCTURAL\": [
			{\"name\":\"POP_KOUT\", \"value\":\"0.664\",\"lo\":\".1\",\"hi\":\"10\"},
			{\"name\":\"POP_PRL0_IN_MALE_HV\",\"value\":\"7.67\",\"lo\":\"1\",\"hi\":\"15\"},
			{\"name\":\"POP_PRL0_IN_MALE_PAT\",\"value\":\"16.1\",\"lo\":\"1\",\"hi\":\"30\"}
		]}"""
		
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
	public void testVariabilityModel() {
		final URL url = this.getClass().getResource("/eu/ddmore/converter/mdl2json/parameterVariabilityModel.json")
		
		def json = FileUtils.readFileToString(new File(url.getFile()))
		Parameter param = new Parameter(new JsonSlurper().parseText(json))
		
		logger.debug(param.toMDL())
		
		String expected = """parobj {
    VARIABILITY{
        matrix(name="struc1", type=VAR) {
            PPV_PRL0=.425,
            .758,PPV_KI=1.82
        }
        PPV_KOUT=list(value=0.589,type=VAR)
        PPV_AMP1=list(value=.0558,type=VAR)
        PPV_PHS2=list(value=1.80,type=VAR)
        diag(name="struc2", type=VAR) {
            PPV_IOV_IN_PRL0_1=.0657
        }
        same(name="struc2") {
            PPV_IOV_IN_PRL0_2
        }
        same(name="struc2") {
            PPV_IOV_IN_PRL0_3
        }
        same(name="struc2") {
            PPV_IOV_IN_PRL0_4
        }
        RUV_EPS1=list(fix=true,value=1,type=VAR)
    }
}
"""
		assertEquals(expected, param.toMDL())
	}
	
}
