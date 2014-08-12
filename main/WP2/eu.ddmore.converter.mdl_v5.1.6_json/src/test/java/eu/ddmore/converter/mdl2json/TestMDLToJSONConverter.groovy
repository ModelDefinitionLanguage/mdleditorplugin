package eu.ddmore.converter.mdl2json;

import static org.junit.Assert.*;

import org.apache.log4j.Logger

import eu.ddmore.convertertoolbox.api.response.ConversionReport
import eu.ddmore.converter.mdl2json.domain.MCLFile
import eu.ddmore.converter.mdl2json.domain.Parameter
import eu.ddmore.converter.mdl2json.domain.Data
import eu.ddmore.mdlparse.MdlParser
import groovy.json.JsonSlurper
import org.ddmore.mdl.mdl.Mcl
import org.junit.Test;

class TestMDLToJSONConverter extends ConverterTestsParent {
	private static Logger logger = Logger.getLogger(TestMDLToJSONConverter.class)

    @Test
	public void testExtractObjectNames() {
        def File mdlFile = getFileFromModelsProject("ThamCCR2008/tumour_size_01July2014_OAM.mdl")
        def json = getJsonFromMDLFile(mdlFile)
		
		assert json.tumour_size_dat.file != null
		assert json.tumour_size_par != null
		assert json.tumour_size_mdl != null
		assert json.tumour_size_task != null
		
	}
		
	@Test
	public void testProlactin01July2014() {
        
        def File mdlFile = getFileFromModelsProject("FribergCPT2009/ex_model7_prolactin_01July2014_OAM.mdl")
		def json = getJsonFromMDLFile(mdlFile)[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]
			
		def dataObject = json.ex_model7_prolactin_May2014_NMT_ORI_dat
			
		def source = dataObject[Data.SOURCE]
		
		assertEquals("\"ex_data_prolactin.csv\"", source.file)
		assertEquals("nonmemFormat", source.inputformat)
		assertEquals("\"@\"", source.ignore)
		
		def dataInputVariables = dataObject[Data.DATA_INPUT_VARIABLES]
				
		def STU = dataInputVariables[0] // First D.I.V.
		assertEquals("continuous", STU.type)

		def TIME = dataInputVariables[4] // Fifth D.I.V.
		assertEquals("\"h\"", TIME.units)	
		
		def paramObj = json.ex_model7_prolactin_May2014_NMT_ORI_par
		assertEquals(Parameter.IDENTIFIER, paramObj.identifier)
	}
	
	@Test
	public void testOGTTJun2014() {
		def File mdlFile = getFileFromModelsProject("JauslinCPT2007/run_final_OGTT_04Jun2014_OAM.mdl")
        def json = getJsonFromMDLFile(mdlFile)[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]
		
		def paramObj = json.run_final_OGTT_par
		assertEquals(Parameter.IDENTIFIER, paramObj.identifier)
			
	}
    
}
