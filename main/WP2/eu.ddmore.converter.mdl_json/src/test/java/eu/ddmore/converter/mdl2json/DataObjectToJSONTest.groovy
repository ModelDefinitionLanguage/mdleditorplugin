package eu.ddmore.converter.mdl2json;

import static org.junit.Assert.*

import org.apache.log4j.Logger
import org.junit.Test

import eu.ddmore.converter.mdl2json.domain.Data
import eu.ddmore.converter.mdl2json.domain.Variable

class DataObjectToJSONTest extends ConverterTestsParent  {
	private static Logger logger = Logger.getLogger(DataObjectToJSONTest.class)
		
	@Test
	public void testSourceBlock() {
		def json = getJsonFromMDLFile("Warfarin_DataObject.mdl")
			
		logger.debug(json)
		
		def dataObject = json[0].warfarin_PK_ODE_dat // The [0] is because the JSON is enclosed within superfluous square brackets [...]
			
		def source = dataObject[Data.SOURCE]
		
		assertEquals("Checking file attribute of source block", "\"warfarin_conc.csv\"", source.file)
		assertEquals("Checking inputFormat attribute of source block", "nonmemFormat", source.inputformat)
		assertEquals("Checking ignoreChar attribute of source block", "\"#\"", source.ignore)
	}
	
	@Test
	public void testDataInputVariablesBlock_WarfarinAnalyticSolution() {
		def json = getJsonFromMDLFile("WarfarinAnalyticSolution_DataObject.mdl")
		
		logger.debug(json)
	
		def dataObject = json[0].warfarin_PK_v2_dat // The [0] is because the JSON is enclosed within superfluous square brackets [...]
		
		def dataInputVars = dataObject.DATA_INPUT_VARIABLES

		assertEquals("Checking the number of Data Input Variables", 9, dataInputVars.size())
		
		final var1 = dataInputVars[0]
		assertEquals("Checking Data Input Variable 1/9 - Name", "ID", var1.get(Variable.NAME_KEY))
		assertEquals("Checking Data Input Variable 1/9 - Use", "id", var1.use)
		
		final var2 = dataInputVars[1]
		assertEquals("Checking Data Input Variable 2/9 - Name", "TIME", var2.get(Variable.NAME_KEY))
		assertEquals("Checking Data Input Variable 2/9 - Use", "idv", var2.use)
		
		final var3 = dataInputVars[2]
		assertEquals("Checking Data Input Variable 3/9 - Name", "WT", var3.get(Variable.NAME_KEY))
		assertEquals("Checking Data Input Variable 3/9 - Use", "covariate", var3.use)
        
        final var4 = dataInputVars[3]
        assertEquals("Checking Data Input Variable 4/9 - Name", "AGE", var4.get(Variable.NAME_KEY))
        assertEquals("Checking Data Input Variable 4/9 - Use", "covariate", var4.use)
		
		final var5 = dataInputVars[4]
		assertEquals("Checking Data Input Variable 5/9 - Name", "SEX", var5.get(Variable.NAME_KEY))
		assertEquals("Checking Data Input Variable 5/9 - Use", "covariate", var5.use)
        assertEquals("Checking Data Input Variable 5/9 - Type", "categorical(male, female)", var5.type)
        assertEquals("Checking Data Input Variable 5/9 - Define", "[{category=male, value=0}, {category=female, value=1}]", var5.define)

        final var6 = dataInputVars[5]
        assertEquals("Checking Data Input Variable 6/9 - Name", "AMT", var6.get(Variable.NAME_KEY))
        assertEquals("Checking Data Input Variable 6/9 - Use", "amt", var6.use)
        assertEquals("Checking Data Input Variable 6/9 - Define", "D", var6.define)
        
        final var7 = dataInputVars[6]
        assertEquals("Checking Data Input Variable 7/9 - Name", "DVID", var7.get(Variable.NAME_KEY))
        assertEquals("Checking Data Input Variable 7/9 - Use", "dvid", var7.use)
        		
		final var8 = dataInputVars[7]
		assertEquals("Checking Data Input Variable 8/9 - Name", "DV", var8.get(Variable.NAME_KEY))
		assertEquals("Checking Data Input Variable 8/9 - Use", "dv", var8.use)
        assertEquals("Checking Data Input Variable 8/9 - Define", "Y", var8.define)

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

        final var1 = dataInputVars[0]
        assertEquals("Checking Data Input Variable 1/11 - Name", "ID", var1.get(Variable.NAME_KEY))
        assertEquals("Checking Data Input Variable 1/11 - Use", "id", var1.use)
                
        final var2 = dataInputVars[1]
        assertEquals("Checking Data Input Variable 2/11 - Name", "CYCL", var2.get(Variable.NAME_KEY))
        assertEquals("Checking Data Input Variable 2/11 - Type", "categorical", var2.type)
        
        final var3 = dataInputVars[2]
        assertEquals("Checking Data Input Variable 3/11 - Name", "TIME", var3.get(Variable.NAME_KEY))
        assertEquals("Checking Data Input Variable 3/11 - Use", "idv", var3.use)
        assertEquals("Checking Data Input Variable 3/11 - Units", "\"h\"", var3.units)
        
        final var4 = dataInputVars[3]
        assertEquals("Checking Data Input Variable 4/11 - Name", "DAYS", var4.get(Variable.NAME_KEY))
        assertEquals("Checking Data Input Variable 4/11 - Type", "continuous", var4.type)
        assertEquals("Checking Data Input Variable 4/11 - Units", "\"day\"", var4.units)
        
        final var5 = dataInputVars[4]
        assertEquals("Checking Data Input Variable 5/11 - Name", "DV", var5.get(Variable.NAME_KEY))
        assertEquals("Checking Data Input Variable 5/11 - Use", "dv", var5.use)
        assertEquals("Checking Data Input Variable 5/11 - Define",
            "[{pred=VEGF_obs, predID=5}, {pred=sVEGFR2_obs, predID=6}, {pred=sVEGFR3_obs, predID=7}, {pred=sKIT_obs, predID=8}]", var5.define)
    }
    
    @Test
    public void testDataInputVariablesBlock_WarfarinPkBov() {
        def json = getJsonFromMDLFile("WarfarinPkBov_DataObject.mdl")
        
        logger.debug(json)
    
        def dataObject = json[0].warfarin_PK_BOV_dat // The [0] is because the JSON is enclosed within superfluous square brackets [...]
        
        def dataInputVars = dataObject.DATA_INPUT_VARIABLES

        assertEquals("Checking the number of Data Input Variables", 9, dataInputVars.size())

        final var5 = dataInputVars[4]
        assertEquals("Checking Data Input Variable 5/9 - Name", "SEX", var5.get(Variable.NAME_KEY))
        assertEquals("Checking Data Input Variable 5/9 - Use", "covariate", var5.use)
        assertEquals("Checking Data Input Variable 5/9 - Type", "categorical(male, female, MISSING)", var5.type)
        assertEquals("Checking Data Input Variable 5/9 - Define", "[{female, 1}, {male, 0}, {MISSING, -99}]", var5.define)
        
    }
    
    @Test
    public void testDeclaredVariablesBlock_OnSameLine() {
        def json = getJsonFromMDLFile("Warfarin_DataObject.mdl")
            
        logger.debug(json)
        
        def dataObject = json[0].warfarin_PK_ODE_dat // The [0] is because the JSON is enclosed within superfluous square brackets [...]
            
        def declaredVars = dataObject[Data.DECLARED_VARIABLES]
        
        assertEquals("Checking the number of Declared Variables", 2, declaredVars.size())
        
        assertEquals("Checking Declared Variable 1/2", "GUT", declaredVars[0].get(Variable.NAME_KEY))
        assertEquals("Checking Declared Variable 2/2", "Y", declaredVars[1].get(Variable.NAME_KEY))
        
    }
    
    @Test
    public void testDeclaredVariablesBlock_OnSeparateLines() {
        def json = getJsonFromMDLFile("Hansson_DataObject.mdl")
            
        logger.debug(json)
        
        def dataObject = json[0].Hansson2013_data // The [0] is because the JSON is enclosed within superfluous square brackets [...]
            
        def declaredVars = dataObject[Data.DECLARED_VARIABLES]
        
        assertEquals("Checking the number of Declared Variables", 5, declaredVars.size())
        
        assertEquals("Checking Declared Variable 1/5", "DOSE", declaredVars[0].get(Variable.NAME_KEY))
        assertEquals("Checking Declared Variable 2/5", "VEGF_obs", declaredVars[1].get(Variable.NAME_KEY))
        assertEquals("Checking Declared Variable 3/5", "VEGFR2_obs", declaredVars[2].get(Variable.NAME_KEY))
        assertEquals("Checking Declared Variable 4/5", "VEGFR3_obs", declaredVars[3].get(Variable.NAME_KEY))
        assertEquals("Checking Declared Variable 5/5", "SKIT_obs", declaredVars[4].get(Variable.NAME_KEY))
        
    }
    
    @Test
    public void testDataDerivedVariables() {
        def json = getJsonFromMDLFile("WarfarinAnalyticSolution_DataObject.mdl")
            
        logger.debug(json)
        
        def dataObject = json[0].warfarin_PK_v2_dat // The [0] is because the JSON is enclosed within superfluous square brackets [...]
            
        def dataDerivedVars = dataObject[Data.DATA_DERIVED_VARIABLES]
        
        assertEquals("Checking the number of Data Derived Variables", 1, dataDerivedVars.size())
        
        assertEquals("Checking Data Derived Variable 1/1", [(Variable.NAME_KEY):'DT', (Variable.EXPRESSION_KEY):"TIME when AMT>0;"], dataDerivedVars[0])
        
    }
    
}
