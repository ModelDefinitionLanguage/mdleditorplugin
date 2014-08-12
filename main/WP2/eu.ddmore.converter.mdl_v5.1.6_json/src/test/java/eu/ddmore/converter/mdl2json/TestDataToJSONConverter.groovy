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
import org.junit.Ignore;

class TestDataToJSONConverter extends ConverterTestsParent  {
	private static Logger logger = Logger.getLogger(TestDataToJSONConverter.class)
		
	@Test
	public void testSourceBlock() {
		def json = getJsonFromMDLFile("simpleData.mdl")
			
		logger.debug(json)
		
		def dataObject = json[0].tumour_size_dat // The [0] is because the JSON is enclosed within superfluous square brackets [...]
			
		def source = dataObject[Data.SOURCE]
		
		assertEquals("Checking file attribute of source block", "\"tumour_exposure.csv\"", source.file)
		assertEquals("Checking inputFormat attribute of source block", "nonmemFormat", source.inputformat)
		assertEquals("Checking ignoreChar attribute of source block", "\"@\"", source.ignore)
	}
	
	@Test
	public void testDataInputVariablesBlock() {
		def json = getJsonFromMDLFile("tumourDataObject.mdl")
		
		logger.debug(json)
	
		def dataObject = json[0].tumour_size_TABLES_ORG_dat // The [0] is because the JSON is enclosed within superfluous square brackets [...]
		
		def dataInputVars = dataObject.DATA_INPUT_VARIABLES

		assertEquals("Checking the number of Data Input Variables", 4, dataInputVars.size())
		
		final var1 = dataInputVars[0]
		assertEquals("Checking Data Input Variable 1/4 - Name", "ID", var1.name)
		assertEquals("Checking Data Input Variable 1/4 - Type", "categorical", var1.type)
		assertNull("Checking Data Input Variable 1/4 - Units", var1.units)
		
		final var2 = dataInputVars[1]
		assertEquals("Checking Data Input Variable 2/4 - Name", "TIME", var2.name)
		assertEquals("Checking Data Input Variable 2/4 - Type", "continuous", var2.type)
		assertEquals("Checking Data Input Variable 2/4 - Units", "\"h\"", var2.units)
		
		final var3 = dataInputVars[2]
		assertEquals("Checking Data Input Variable 3/4 - Name", "AMT", var3.name)
		assertEquals("Checking Data Input Variable 3/4 - Type", "continuous", var3.type)
		assertEquals("Checking Data Input Variable 3/4 - Units", "\"mg\"", var3.units)
		
		final var4 = dataInputVars[3]
		assertEquals("Checking Data Input Variable 4/4 - Name", "DV", var4.name)
		assertEquals("Checking Data Input Variable 4/4 - Type", "continuous", var4.type)
		assertNull("Checking Data Input Variable 4/4 - Units", var4.units)
	}
	
}
