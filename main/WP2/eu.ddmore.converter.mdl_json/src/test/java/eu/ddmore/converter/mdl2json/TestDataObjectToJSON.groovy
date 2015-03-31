package eu.ddmore.converter.mdl2json;

import static org.junit.Assert.*

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger
import org.junit.Ignore
import org.junit.Test

import eu.ddmore.converter.mdl2json.domain.Data
import eu.ddmore.converter.mdl2json.domain.Variable

class TestDataObjectToJSON extends ConverterTestsParent  {
	private static Logger logger = Logger.getLogger(TestDataObjectToJSON.class)
		
	@Test
	public void testSourceBlock() {
		def json = getJsonFromMDLFile("Warfarin_DataObject.mdl")
			
		logger.debug(json)
		
		def dataObject = json[0].warfarin_dat // The [0] is because the JSON is enclosed within superfluous square brackets [...]
			
		def source = dataObject[Data.SOURCE]
		
		assertEquals("Checking file attribute of source block", "\"warfarin_conc.csv\"", source.file)
		assertEquals("Checking inputFormat attribute of source block", "nonmemFormat", source.inputformat)
		assertEquals("Checking ignoreChar attribute of source block", "\"#\"", source.ignore)
	}
	
	@Test
	public void testDataInputVariablesBlock_Warfarin() {
		def json = getJsonFromMDLFile("Warfarin_DataObject.mdl")
		
		logger.debug(json)
	
		def dataObject = json[0].warfarin_dat // The [0] is because the JSON is enclosed within superfluous square brackets [...]
		
		def dataInputVars = dataObject.DATA_INPUT_VARIABLES

		assertEquals("Checking the number of Data Input Variables", 9, dataInputVars.size())
		
		final var1 = dataInputVars[0]
		assertEquals("Checking Data Input Variable 1/9 - Name", "ID", var1.get(Variable.NAME_KEY))
		assertEquals("Checking Data Input Variable 1/9 - Use", "id", var1.use)
		
		final var2 = dataInputVars[1]
		assertEquals("Checking Data Input Variable 2/9 - Name", "TIME", var2.get(Variable.NAME_KEY))
		assertEquals("Checking Data Input Variable 2/9 - Use", "idv", var2.use)
		assertEquals("Checking Data Input Variable 2/9 - Units", "\"h\"", var2.units)
		
		final var3 = dataInputVars[2]
		assertEquals("Checking Data Input Variable 3/9 - Name", "WT", var3.get(Variable.NAME_KEY))
		assertEquals("Checking Data Input Variable 3/9 - Use", "covariate", var3.use)
		assertEquals("Checking Data Input Variable 3/9 - Type", "continuous", var3.type)
		
		final var4 = dataInputVars[3]
		assertEquals("Checking Data Input Variable 4/9 - Name", "AGE", var4.get(Variable.NAME_KEY))
		assertEquals("Checking Data Input Variable 4/9 - Use", "covariate", var4.use)

        final var5 = dataInputVars[4]
        assertEquals("Checking Data Input Variable 5/9 - Name", "SEX", var5.get(Variable.NAME_KEY))
        assertEquals("Checking Data Input Variable 5/9 - Use", "covariate", var5.use)
        assertEquals("Checking Data Input Variable 5/9 - Type", "categorical(male, female)", var5.type)
        assertEquals("Checking Data Input Variable 5/9 - Define", new HashMap(["male": "0", "female": "1"]), var5.define)
        
        final var6 = dataInputVars[5]
        assertEquals("Checking Data Input Variable 6/9 - Name", "AMT", var6.get(Variable.NAME_KEY))
        assertEquals("Checking Data Input Variable 6/9 - Use", "amt", var6.use)
        assertEquals("Checking Data Input Variable 6/9 - Cmpt", "D", var6.cmpt)
        		
		final var7 = dataInputVars[6]
		assertEquals("Checking Data Input Variable 7/9 - Name", "DVID", var7.get(Variable.NAME_KEY))
		assertEquals("Checking Data Input Variable 7/9 - Use", "dvid", var7.use)
		
		final var8 = dataInputVars[7]
		assertEquals("Checking Data Input Variable 8/9 - Name", "DV", var8.get(Variable.NAME_KEY))
		assertEquals("Checking Data Input Variable 8/9 - Use", "dv", var8.use)
		assertEquals("Checking Data Input Variable 8/9 - Units", "\"mg/L\"", var8.units)
        assertEquals("Checking Data Input Variable 8/9 - Prediction", "Y when DVID==1 otherwise N;", var8.prediction)

        final var9 = dataInputVars[8]
        assertEquals("Checking Data Input Variable 9/9 - Name", "MDV", var9.get(Variable.NAME_KEY))
        assertEquals("Checking Data Input Variable 9/9 - Use", "mdv", var9.use)
        
	}
    
    @Test
    public void testDataInputVariablesBlock_Hansson() {
        def json = getJsonFromMDLFile("Hansson_DataObject.mdl")
        
        logger.debug(json)
    
        def dataObject = json[0].Hansson2013_data // The [0] is because the JSON is enclosed within superfluous square brackets [...]
        
        def dataInputVars = dataObject.DATA_INPUT_VARIABLES

        assertEquals("Checking the number of Data Input Variables", 11, dataInputVars.size())
        
        final var2 = dataInputVars[1]
        assertEquals("Checking Data Input Variable 2/9 - Name", "CYCL", var2.get(Variable.NAME_KEY))
        assertEquals("Checking Data Input Variable 2/9 - Type", "categorical", var2.type)
        
        final var4 = dataInputVars[4]
        assertEquals("Checking Data Input Variable 5/11 - Name", "DV", var4.get(Variable.NAME_KEY))
        assertEquals("Checking Data Input Variable 5/11 - Use", "dv", var4.use)
        assertEquals("Checking Data Input Variable 5/11 - Prediction", "VEGF_obs when FLAG==5, VEGFR2_obs when FLAG==6, VEGFR3_obs when FLAG==7, SKIT_obs when FLAG==8;", var4.prediction)
        
    }
    
    @Test
    public void testDeclaredVariablesBlock_OnSameLine() {
        def json = getJsonFromMDLFile("Warfarin_DataObject.mdl")
            
        logger.debug(json)
        
        def dataObject = json[0].warfarin_dat // The [0] is because the JSON is enclosed within superfluous square brackets [...]
            
        def declaredVars = dataObject[Data.DECLARED_VARIABLES]
        
        assertEquals("Checking the number of Declared Variables", 2, declaredVars.size())
        
        assertEquals("Checking Declared Variable 1/2", "Y", declaredVars[0].get(Variable.NAME_KEY))
        assertEquals("Checking Declared Variable 2/2", "D", declaredVars[1].get(Variable.NAME_KEY))
        
    }
    
    @Test
    public void testDeclaredVariablesBlock_OnSeparateLines() {
        def json = getJsonFromMDLFile("Hansson_DataObject.mdl")
            
        logger.debug(json)
        
        def dataObject = json[0].Hansson2013_data // The [0] is because the JSON is enclosed within superfluous square brackets [...]
            
        def declaredVars = dataObject[Data.DECLARED_VARIABLES]
        
        assertEquals("Checking the number of Declared Variables", 4, declaredVars.size())
        
        assertEquals("Checking Declared Variable 1/4", "VEGF_obs", declaredVars[0].get(Variable.NAME_KEY))
        assertEquals("Checking Declared Variable 2/4", "VEGFR2_obs", declaredVars[1].get(Variable.NAME_KEY))
        assertEquals("Checking Declared Variable 3/4", "VEGFR3_obs", declaredVars[2].get(Variable.NAME_KEY))
        assertEquals("Checking Declared Variable 4/4", "SKIT_obs", declaredVars[3].get(Variable.NAME_KEY))
        
    }
    
}
