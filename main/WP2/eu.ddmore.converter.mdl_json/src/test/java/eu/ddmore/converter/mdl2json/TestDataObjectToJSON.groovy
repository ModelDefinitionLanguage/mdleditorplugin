package eu.ddmore.converter.mdl2json;

import static org.junit.Assert.*;

import org.apache.log4j.Logger

import eu.ddmore.convertertoolbox.api.response.ConversionReport
import eu.ddmore.converter.mdl2json.domain.DataInputVariables;
import eu.ddmore.converter.mdl2json.domain.MCLFile
import eu.ddmore.converter.mdl2json.domain.Parameter
import eu.ddmore.converter.mdl2json.domain.Data

import eu.ddmore.mdlparse.MdlParser
import groovy.json.JsonSlurper
import org.ddmore.mdl.mdl.Mcl
import org.junit.Test;
import org.junit.Ignore;

class TestDataObjectToJSON extends ConverterTestsParent  {
	private static Logger logger = Logger.getLogger(TestDataObjectToJSON.class)
		
	@Test
	public void testSourceBlock() {
		def json = getJsonFromMDLFile("warfarinODE_28Oct2014_DataObject.mdl")
			
		logger.debug(json)
		
		def dataObject = json[0].warfarin_PK_ODE_dat // The [0] is because the JSON is enclosed within superfluous square brackets [...]
			
		def source = dataObject[Data.SOURCE]
		
		assertEquals("Checking file attribute of source block", "\"warfarin_conc.csv\"", source.file)
		assertEquals("Checking inputFormat attribute of source block", "nonmemFormat", source.inputformat)
		assertEquals("Checking ignoreChar attribute of source block", "\"#\"", source.ignore)
	}
	
	@Test
	public void testDataInputVariablesBlock() {
		def json = getJsonFromMDLFile("warfarinODE_28Oct2014_DataObject.mdl")
		
		logger.debug(json)
	
		def dataObject = json[0].warfarin_PK_ODE_dat // The [0] is because the JSON is enclosed within superfluous square brackets [...]
		
		def dataInputVars = dataObject.DATA_INPUT_VARIABLES

		assertEquals("Checking the number of Data Input Variables", 8, dataInputVars.size())
		
		final var1 = dataInputVars[0]
		assertEquals("Checking Data Input Variable 1/8 - Name", "ID", var1.name)
		assertEquals("Checking Data Input Variable 1/8 - Type", "categorical", var1.type)
		assertNull("Checking Data Input Variable 1/8 - Units", var1.units)
		
		final var2 = dataInputVars[1]
		assertEquals("Checking Data Input Variable 2/8 - Name", "TIME", var2.name)
		assertEquals("Checking Data Input Variable 2/8 - Type", "continuous", var2.type)
		assertEquals("Checking Data Input Variable 2/8 - Units", "\"h\"", var2.units)
		
		final var3 = dataInputVars[2]
		assertEquals("Checking Data Input Variable 3/8 - Name", "WT", var3.name)
		assertEquals("Checking Data Input Variable 3/8 - Type", "continuous", var3.type)
		assertNull("Checking Data Input Variable 3/8 - Units", var3.units)
		
		final var4 = dataInputVars[3]
		assertEquals("Checking Data Input Variable 4/8 - Name", "AMT", var4.name)
		assertEquals("Checking Data Input Variable 4/8 - Type", "continuous", var4.type)
		assertEquals("Checking Data Input Variable 4/8 - Units", "\"mg\"", var4.units)
		
		final var5 = dataInputVars[4]
		assertEquals("Checking Data Input Variable 5/8 - Name", "DVID", var5.name)
		assertEquals("Checking Data Input Variable 5/8 - Type", "categorical", var5.type)
		assertNull("Checking Data Input Variable 5/8 - Units", var5.units)
		
		final var6 = dataInputVars[5]
		assertEquals("Checking Data Input Variable 6/8 - Name", "DV", var6.name)
		assertEquals("Checking Data Input Variable 6/8 - Type", "continuous", var6.type)
		assertNull("Checking Data Input Variable 6/8 - Units", var6.units)
		
		final var7 = dataInputVars[6]
		assertEquals("Checking Data Input Variable 7/8 - Name", "MDV", var7.name)
		assertEquals("Checking Data Input Variable 7/8 - Type", "categorical", var7.type)
		assertNull("Checking Data Input Variable 7/8 - Units", var7.units)
		
		final var8 = dataInputVars[7]
		assertEquals("Checking Data Input Variable 8/8 - Name", "logtWT", var8.name)
		assertEquals("Checking Data Input Variable 8/8 - Type", "continuous", var8.type)
		assertNull("Checking Data Input Variable 8/8 - Units", var8.units)
	}
	
	@Test
	@Ignore
	public void testDataDerivedVariablesBlock() {
		def json = getJsonFromMDLFile("warfarinODE_28Oct2014_DataObject.mdl")
		
		logger.debug(json)
	
		def dataObject = json[0].warfarin_PK_ODE_dat // The [0] is because the JSON is enclosed within superfluous square brackets [...]
		
		def dataDerivedVars = dataObject.DATA_DERIVED_VARIABLES

		assertEquals("Checking the number of Data Derived Variables block statements", 1, dataDerivedVars.size())
		
		final derivVar1 = dataDerivedVars[0]
		// There is only one entry in the Map
		final k = derivVar1.keySet().toArray()[0]
		final v = derivVar1.values()[0]
		
		assertEquals("Checking Data Input Variable 1/6 - Symbol Name", "logtWT", k)
		assertEquals("Checking Data Input Variable 1/6 - RHS", "log(WT/70)", v)
	}
	
}
