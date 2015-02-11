package eu.ddmore.converter.mdl2json;

import static org.junit.Assert.*

import org.apache.log4j.Logger
import org.junit.Ignore
import org.junit.Test

import eu.ddmore.converter.mdl2json.domain.Data
import eu.ddmore.converter.mdl2json.domain.Variable

class DataObjectToJSONTest extends ConverterTestsParent  {
	private static Logger logger = Logger.getLogger(DataObjectToJSONTest.class)
		
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
		assertEquals("Checking Data Input Variable 1/8 - Name", "ID", var1.get(Variable.NAME_KEY))
		assertEquals("Checking Data Input Variable 1/8 - Type", "categorical", var1.type)
		assertNull("Checking Data Input Variable 1/8 - Units", var1.units)
		
		final var2 = dataInputVars[1]
		assertEquals("Checking Data Input Variable 2/8 - Name", "TIME", var2.get(Variable.NAME_KEY))
		assertEquals("Checking Data Input Variable 2/8 - Type", "continuous", var2.type)
		assertEquals("Checking Data Input Variable 2/8 - Units", "\"h\"", var2.units)
		
		final var3 = dataInputVars[2]
		assertEquals("Checking Data Input Variable 3/8 - Name", "WT", var3.get(Variable.NAME_KEY))
		assertEquals("Checking Data Input Variable 3/8 - Type", "continuous", var3.type)
		assertNull("Checking Data Input Variable 3/8 - Units", var3.units)
		
		final var4 = dataInputVars[3]
		assertEquals("Checking Data Input Variable 4/8 - Name", "AMT", var4.get(Variable.NAME_KEY))
		assertEquals("Checking Data Input Variable 4/8 - Type", "continuous", var4.type)
		assertEquals("Checking Data Input Variable 4/8 - Units", "\"mg\"", var4.units)
		
		final var5 = dataInputVars[4]
		assertEquals("Checking Data Input Variable 5/8 - Name", "DVID", var5.get(Variable.NAME_KEY))
		assertEquals("Checking Data Input Variable 5/8 - Type", "categorical", var5.type)
		assertNull("Checking Data Input Variable 5/8 - Units", var5.units)
		
		final var6 = dataInputVars[5]
		assertEquals("Checking Data Input Variable 6/8 - Name", "DV", var6.get(Variable.NAME_KEY))
		assertEquals("Checking Data Input Variable 6/8 - Type", "continuous", var6.type)
		assertNull("Checking Data Input Variable 6/8 - Units", var6.units)
		
		final var7 = dataInputVars[6]
		assertEquals("Checking Data Input Variable 7/8 - Name", "MDV", var7.get(Variable.NAME_KEY))
		assertEquals("Checking Data Input Variable 7/8 - Type", "categorical", var7.type)
		assertNull("Checking Data Input Variable 7/8 - Units", var7.units)
		
		final var8 = dataInputVars[7]
		assertEquals("Checking Data Input Variable 8/8 - Name", "logtWT", var8.get(Variable.NAME_KEY))
		assertEquals("Checking Data Input Variable 8/8 - Type", "continuous", var8.type)
		assertNull("Checking Data Input Variable 8/8 - Units", var8.units)
	}
    
}
