package eu.ddmore.converter.mdl2json;

import static org.junit.Assert.*

import org.apache.log4j.Logger
import org.junit.Test
import org.junit.Ignore

import eu.ddmore.converter.mdl2json.domain.MCLFile

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
	@Ignore("Pending regeneration of JSON from R")
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

		def structuralParams = TestJSONModelToMDL.extractSpecificBlock(mdl, "STRUCTURAL")
		assertEquals("Checking content of STRUCTURAL block",
"""    STRUCTURAL {
        POP_KOUT=list(lo=.1,value=0.664,hi=10)
        POP_PRL0_IN_MALE_HV=list(lo=1,value=7.67,hi=15)
        POP_PRL0_IN_MALE_PAT=list(lo=1,value=16.1,hi=30)
        POP_PRL0_IN_FEMALES_PAT=list(lo=1,value=35.2,hi=40)
        POP_PRL0_IN_MALES_STUDY_101=list(lo=0,value=11.3)
        POP_PRL0_IN_FEMALES_STUDY_101=list(lo=0,value=23.2)
        POP_KDA=list(lo=0.001,value=.156,hi=50)
        POP_UPDA=list(lo=0.001,value=1.44,hi=10)
        POP_AMP=list(lo=-.75,value=0.532,hi=1.5)
        POP_PHS1=list(lo=12,value=20.1,hi=25)
        POP_AMP2=list(lo=-.9,value=-.314,hi=0.2)
        POP_PHS2=list(lo=-2,value=13.7,hi=15)
        POP_KI=list(lo=0,value=1.96,hi=100)
        POP_PHASE_SHIFT_IN_PATIENTS=list(lo=-5,value=-1.61)
        POP_RES_ERR_IN_MALE_HV=list(lo=0,value=.290)
        POP_RES_ERR_IN_MALE_PATIENTS=list(lo=0,value=.422)
        POP_RES_ERR_IN_FEMALE_PATIENTS=list(lo=0,value=.571)
    }
""", structuralParams)
		
		// TODO: Ideally we'd have similar tests for all the blocks
		
	}
	
}
