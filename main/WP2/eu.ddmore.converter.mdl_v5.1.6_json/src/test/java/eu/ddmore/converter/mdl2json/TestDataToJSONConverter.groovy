package eu.ddmore.converter.mdl2json;

import static org.junit.Assert.*;

import org.apache.log4j.Logger

import eu.ddmore.convertertoolbox.api.response.ConversionReport
import eu.ddmore.converter.mdl2json.domain.MCLFile
import eu.ddmore.converter.mdl2json.domain.Parameter
import eu.ddmore.converter.mdl2json.domain.Data
import eu.ddmore.converter.mdl2json.domain.data.DataInputVariables

import eu.ddmore.mdlparse.MdlParser
import groovy.json.JsonSlurper
import org.ddmore.mdl.mdl.Mcl
import org.junit.Test;

class TestDataToJSONConverter extends ConverterTestsParent  {
	private static Logger logger = Logger.getLogger(TestDataToJSONConverter.class)
		
	@Test
	public void testProlactinMay2014() {
		def json = getJsonFromMDLFile("simpleData.mdl")
			
		logger.debug(json)
		
		def dataObject = json.tumour_size_dat
			
		def source = dataObject[Data.SOURCE]
		
		assertEquals("\"tumour_exposure.csv\"", source.file[0])
		assertEquals("nonmemFormat", source.inputformat[0])
		assertEquals("\"@\"", source.ignore[0])
		
		def dataInputVariables = dataObject[Data.DATA_INPUT_VARIABLES]
						
		def ID = dataInputVariables.ID
		assertEquals("categorical", ID.type[0])

		def TIME = dataInputVariables.TIME
		assertEquals("\"h\"", TIME.units[0])	
	}
	
	@Test 
	void testTumourDataInputVariablesOrder() {
		
		File srcFile = getFile("tumourDataObject.mdl")
		
		MdlParser p = new MdlParser()
		Mcl mcl = p.parse(srcFile)
		
		MCLFile f = new MCLFile(mcl)
			
		Data dataObject = f.tumour_size_TABLES_ORG_dat	
		
		DataInputVariables divs = dataObject.DATA_INPUT_VARIABLES
		
		def vars = divs.entrySet().toArray()
		
		assertEquals("ID", vars[0].key)
		assertEquals("TIME", vars[1].key)
		assertEquals("AMT", vars[2].key)
		assertEquals("DV", vars[3].key)
	}
	
}
