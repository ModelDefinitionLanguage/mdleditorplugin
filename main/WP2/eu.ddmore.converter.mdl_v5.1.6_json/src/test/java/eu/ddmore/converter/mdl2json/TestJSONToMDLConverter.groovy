package eu.ddmore.converter.mdl2json;

import static org.junit.Assert.*;

import org.apache.log4j.Logger

import eu.ddmore.converter.mdl2json.domain.MCLFile
import eu.ddmore.convertertoolbox.api.response.ConversionReport
import eu.ddmore.mdlparse.MdlParser
import groovy.json.JsonSlurper
import org.ddmore.mdl.mdl.Mcl
import org.junit.Test;
import org.junit.Ignore;

class TestJSONToMDLConverter extends ConverterTestsParent {
	private static Logger logger = Logger.getLogger(TestJSONToMDLConverter.class)
	
	@Test
	public void testProlactinMay2014() {
        
        def File mdlFile = getFileFromModelsProject("FribergCPT2009/ex_model7_prolactin_01July2014_OAM.mdl")
        def json = getJsonFromMDLFile(mdlFile)

		MCLFile mclFile = new MCLFile(json)
		
		assertNotNull(mclFile.toMDL())

	}

	/**
	 * The ex_model7_prolactin_25June2014_OAM.mdl MDL file was run through the MDL->JSON->R->JSON->MDL pipeline.
	 * The JSON on the way out was dumped out and saved as the "prolactinOutputMog.json" file.
	 * This test converts this JSON into MDL and compares certain blocks of this against the corresponding blocks
	 * taken from the original Prolactin MDL.
	 * If any of the parts of the aforementioned conversion pipeline are modified then the updated JSON should be
	 * re-captured and re-saved.
	 * Ultimately this would be an automated integration test... DDMORE-845.
	 */
    @Test
	@Ignore("Pending re-generation of MOG from R")
	public void testRGeneratedMOG() {
		
		File jsonFile = getFile("prolactinOutputMog.json")
		
		String content = jsonFile.getText()
		
		def json = getJson(content)

		MCLFile mclFile = new MCLFile(json)
		
		String mdl = mclFile.toMDL()
		
		assertNotNull(mdl)
	
		logger.debug(mdl)
		
		def dataInputVars = TestJSONModelToMDL.extractSpecificBlock(mdl, "DATA_INPUT_VARIABLES")
			
		assertEquals("Checking content of DATA_INPUT_VARIABLES block",
"""    DATA_INPUT_VARIABLES {
        STU=list(type=continuous)
        ID=list(type=categorical)
        TRT=list(type=continuous)
        NPER=list(type=continuous)
        TIME=list(type=continuous,units="h")
        CMT=list(type=categorical)
        AMT=list(type=continuous,units="mg")
        RATE=list(type=continuous)
        EVID=list(type=categorical)
        SEX=list(type=categorical)
        PRL=list(type=continuous)
        DV=list(type=continuous)
        CLR=list(type=continuous)
        VC=list(type=continuous)
        VP=list(type=continuous)
        QB=list(type=continuous)
        KS=list(type=continuous)
        FR=list(type=continuous)
        DUR=list(type=continuous)
        ALAG=list(type=continuous)
        PAT=list(type=continuous)
        SS=list(type=categorical)
        II=list(type=continuous,units="h")
    }
""", dataInputVars)
		
		// TODO: Ideally we'd have similar tests for all the blocks
		
	}
	
}
