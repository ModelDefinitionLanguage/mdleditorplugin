/*******************************************************************************
 * Copyright (C) 2014-5 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json;

import static org.junit.Assert.*

import org.apache.log4j.Logger
import org.junit.Ignore
import org.junit.Test

import eu.ddmore.converter.mdl2json.domain.Parameter
import eu.ddmore.converter.mdl2json.domain.Variable

class ParameterObjectToJSONTest extends ConverterTestsParent {
    private static Logger logger = Logger.getLogger(ParameterObjectToJSONTest.class)

    @Test
    public void testDeclaredVariablesBlock() {
        def json = getJsonFromMDLFile("UseCase1_ParameterObject.mdl")

        def parameterObject = json[0].warfarin_PK_ODE_par // The [0] is because the JSON is enclosed within superfluous square brackets [...]

        def declaredVars = parameterObject[Parameter.DECLARED_VARIABLES]

        assertEquals("Checking number of Declared Variables", 2, declaredVars.size())

        assertEquals("Checking Declared Variable 1/2", [(Variable.NAME_KEY):"ETA_CL"], declaredVars[0])
        assertEquals("Checking Declared Variable 2/2", [(Variable.NAME_KEY):"ETA_V"], declaredVars[1])

    }

    @Test
    public void testStructuralBlock_Simple() {
        def json = getJsonFromMDLFile("UseCase1_ParameterObject.mdl")

        def parameterObject = json[0].warfarin_PK_ODE_par // The [0] is because the JSON is enclosed within superfluous square brackets [...]

        def structuralModel = parameterObject[Parameter.STRUCTURAL]

        assertEquals("Checking number of Structural parameters", 8, structuralModel.size())

        def expected_POP_CL = [ (Variable.NAME_KEY):'POP_CL', 'value':'0.1', 'lo':'0.001' ]
        assertEquals("Checking Structural parameter 1/8", expected_POP_CL, structuralModel[0])

        def expected_POP_V = [ (Variable.NAME_KEY):'POP_V', 'value':'8', 'lo':'0.001' ]
        assertEquals("Checking Structural parameter 2/8", expected_POP_V, structuralModel[1])

        def expected_POP_KA = [ (Variable.NAME_KEY):'POP_KA', 'value':'0.362', 'lo':'0.001' ]
        assertEquals("Checking Structural parameter 3/8", expected_POP_KA, structuralModel[2])

        def expected_POP_TLAG = [ (Variable.NAME_KEY):'POP_TLAG', 'value':'1', 'lo':'0.001', 'hi':'10' ]
        assertEquals("Checking Structural parameter 4/8", expected_POP_TLAG, structuralModel[3])

        def expected_BETA_CL_WT = [ (Variable.NAME_KEY):'BETA_CL_WT', 'value':'0.75', 'fix':'true' ]
        assertEquals("Checking Structural parameter 5/8", expected_BETA_CL_WT, structuralModel[4])

        def expected_BETA_V_WT = [ (Variable.NAME_KEY):'BETA_V_WT', 'value':'1', 'fix':'true' ]
        assertEquals("Checking Structural parameter 6/8", expected_BETA_V_WT, structuralModel[5])

        def expected_RUV_PROP = [ (Variable.NAME_KEY):'RUV_PROP', 'value':'0.1', 'lo':'0' ]
        assertEquals("Checking Structural parameter 7/8", expected_RUV_PROP, structuralModel[6])

        def expected_RUV_ADD = [ (Variable.NAME_KEY):'RUV_ADD', 'value':'0.1', 'lo':'0' ]
        assertEquals("Checking Structural parameter 8/8", expected_RUV_ADD, structuralModel[7])

    }

    @Test
    public void testStructuralBlock_ContainingAttributesHavingQuotedValues() {
        def json = getJsonFromMDLFile("UseCase3_ParameterObject.mdl")

        def parameterObject = json[0].Hansson2013_par // The [0] is because the JSON is enclosed within superfluous square brackets [...]

        def structuralModel = parameterObject[Parameter.STRUCTURAL]

        assertEquals("Checking number of Structural parameters", 18, structuralModel.size())

        def expected_POP_IMAX = [ (Variable.NAME_KEY):'POP_IMAX', 'value':'1', 'fix':'true' ]
        assertEquals("Checking Structural parameter 1/18", expected_POP_IMAX, structuralModel[0])

        def expected_POP_IC50 = [ (Variable.NAME_KEY):'POP_IC50', 'value':'1', 'lo':'0', 'units':'\"mg/L*h\"' ]
        assertEquals("Checking Structural parameter 2/18", expected_POP_IC50, structuralModel[1])

        def expected_POP_BM0 = [ (Variable.NAME_KEY):'POP_BM0', 'value':'59.7', 'lo':'0', 'units':'\"pg/mL\"' ]
        assertEquals("Checking Structural parameter 3/18", expected_POP_BM0, structuralModel[2])

        def expected_POP_MRT = [ (Variable.NAME_KEY):'POP_MRT', 'value':'91', 'lo':'0', 'units':'\"h\"' ]
        assertEquals("Checking Structural parameter 4/18", expected_POP_MRT, structuralModel[3])

        def expected_POP_HILL = [ (Variable.NAME_KEY):'POP_HILL', 'value':'3.31', 'lo':'0' ]
        assertEquals("Checking Structural parameter 5/18", expected_POP_HILL, structuralModel[4])

        def expected_POP_SLP = [ (Variable.NAME_KEY):'POP_TVSLP', 'value':'0.035', 'lo':'-0.06', 'units':'\"1/h\"' ]
        assertEquals("Checking Structural parameter 6/18", expected_POP_SLP, structuralModel[5])

    }
    
    /**
     * Note the extra layer of nesting in the 'simple' parameters; this is to be consistent with
     * the matrix/diag/same parameters and make the post-processing in R slightly simpler.
     * <p>
     * TODO: This might never become relevant any more in which case we can simplify/standardise
     *       the representation of this block
     */
    @Test
    public void testVariabilityBlock_Simple() {
        def json = getJsonFromMDLFile("UseCase3_ParameterObject.mdl")

        def parameterObject = json[0].Hansson2013_par // The [0] is because the JSON is enclosed within superfluous square brackets [...]

        def variabilityModel = parameterObject[Parameter.VARIABILITY]

        assertEquals("Checking the number of variables in the Variability model", 13, variabilityModel.size())

        def expected_OMEGA_BM0 = [ 'OMEGA_BM0' : [ 'value':'0.252', 'type':'VAR' ] ]
        assertEquals("Checking Variability parameter 1/13", expected_OMEGA_BM0, variabilityModel[0])

        def expected_OMEGA_BM2 = [ 'OMEGA_BM02' : [ 'value':'0.0369', 'type':'VAR' ] ]
        assertEquals("Checking Variability parameter 2/13", expected_OMEGA_BM2, variabilityModel[1])

        def expected_OMEGA_BM3 = [ 'OMEGA_BM03' : [ 'value':'0.186', 'type':'VAR' ] ]
        assertEquals("Checking Variability parameter 3/13", expected_OMEGA_BM3, variabilityModel[2])

        def expected_OMEGA_BMS = [ 'OMEGA_BM0S' : [ 'value':'0.254', 'type':'VAR' ] ]
        assertEquals("Checking Variability parameter 4/13", expected_OMEGA_BMS, variabilityModel[3])

        def expected_SIGMA_RES_W = [ 'SIGMA_RES_W' : [ 'value':'1', 'type':'VAR', 'fix':'true' ] ]
        assertEquals("Checking Variability parameter 13/13", expected_SIGMA_RES_W, variabilityModel[12])

    }

    /**
     * Testing attributes like:
     * params=[ETA_CL, ETA_V]
     */
    @Test
    public void testVariabilityBlock_ContainingAttributeBeingListOfVariableNames_1() {
        def json = getJsonFromMDLFile("UseCase1_ParameterObject.mdl")

        def parameterObject = json[0].warfarin_PK_ODE_par // The [0] is because the JSON is enclosed within superfluous square brackets [...]

        def variabilityModel = parameterObject[Parameter.VARIABILITY]

        assertEquals("Checking the number of variables in the Variability model", 5, variabilityModel.size())

        def expected_PPV_CL = [ 'PPV_CL' : [ 'value':'0.1', 'type':'SD' ] ]
        assertEquals("Checking Variability parameter 1/5", expected_PPV_CL, variabilityModel[0])

        def expected_PPV_V = [ 'PPV_V' : [ 'value':'0.1', 'type':'SD' ] ]
        assertEquals("Checking Variability parameter 2/5", expected_PPV_V, variabilityModel[1])

        def expected_PPV_KA = [ 'PPV_KA' : [ 'value':'0.1', 'type':'SD' ] ]
        assertEquals("Checking Variability parameter 3/5", expected_PPV_KA, variabilityModel[2])

        def expected_PPV_TLAG = [ 'PPV_TLAG' : [ 'value':'0.1', 'type':'SD' ] ]
        assertEquals("Checking Variability parameter 4/5", expected_PPV_TLAG, variabilityModel[3])

        def expected_OMEGA = [ 'OMEGA' : [ 'params':'[ETA_CL, ETA_V]', 'value':'[0.01]', 'type':'CORR' ] ]
        assertEquals("Checking Variability parameter 5/5", expected_OMEGA, variabilityModel[4])

    }

    /**
     * Testing attributes like:
     * params=[ETA_CL, ETA_V]
     * <p>
     */
    @Test
    public void testVariabilityBlock_ContainingAttributeBeingListOfVariableNames_2() {
        def json = getJsonFromMDLFile("UseCase8_ParameterObject.mdl")

        def parameterObject = json[0].warfarin_PK_BOV_par // The [0] is because the JSON is enclosed within superfluous square brackets [...]

        def variabilityModel = parameterObject[Parameter.VARIABILITY]

        assertEquals("Checking the number of variables in the Variability model", 10, variabilityModel.size())

        def expected_BSV_CL = [ 'BSV_CL' : [ 'value':'0.1', 'type':'VAR' ] ]
        assertEquals("Checking Variability parameter 1/10", expected_BSV_CL, variabilityModel[0])

        def expected_BOV_CL = [ 'BOV_CL' : [ 'fix':'true', 'value':'0.1', 'type':'VAR' ] ]
        assertEquals("Checking Variability parameter 3/10", expected_BOV_CL, variabilityModel[2])

        def expected_BOV_COV_CL_V = [ 'BOV_COV_CL_V' : [ 'params':'[eta_BOV_CL, eta_BOV_V]', 'type':'COV', 'value':'0.01' ] ]
        assertEquals("Checking Variability parameter 9/10", expected_BOV_COV_CL_V, variabilityModel[8])

        def expected_BOV_COV_KA_TLAG = [ 'BOV_COV_KA_TLAG' : [ 'params':'[eta_BOV_KA, eta_BOV_TLAG]', 'type':'COV', 'value':'0.01' ] ]
        assertEquals("Checking Variability parameter 10/10", expected_BOV_COV_KA_TLAG, variabilityModel[9])

    }

    /**
     * TODO: Will the "complex variability" functionality ever be reinstated in real use cases? if not, it can be removed.
     */
    @Test
    @Ignore
    public void testComplexVariability() {

        def json = getJsonFromMDLFile("complexParameter.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]

        def paramObj = json["levodopa_variability_par"]
        assertNotNull(paramObj)

        def structuralModel = paramObj[Parameter.STRUCTURAL]
        def POP_TTK0 = structuralModel[4]
        assertEquals("2", POP_TTK0.value)
        assertEquals("true", POP_TTK0["fix"])

        def variabilityModel = paramObj[Parameter.VARIABILITY]
        assertEquals("Checking the number of variables in the Variability Model", 22, variabilityModel.size())

        def matrix1 = variabilityModel[0]["matrix"]
        assertEquals("Checking the name of the first of the Matrix variables", "\"struc1\"", matrix1["name"])
        assertEquals("Checking the type of the first of the Matrix variables", "VAR", matrix1["type"])
        String expectedMatrix1 =
"""BSVV1=0.015,
0.00377, BSVCL=0.0158,
0.0156, 0.0127, BSVV2=0.0218,
0.0273, 0.0282, 0.0411, BSVQ=0.0804"""
        assertEquals("Checking the content of the first of the Matrix variables", expectedMatrix1, matrix1["content"])

        def same1 = variabilityModel[2]["same"]
        assertEquals("Checking the name of the first of the Same variables", "\"struc2\"", same1["name"])
        String expectedSame1 = """BOVCL2,
BOVV22,
BOVQ2"""
        assertEquals("Checking the content of the first of the Same variables", expectedSame1, same1["content"])

        def PPV_CVTTK0 = variabilityModel[11]
        def expected_PPV_CVTTK0 = [ 'PPV_CVTTK0' : [ 'value':'0', 'type':'VAR', 'fix':'true' ] ]
        assertEquals("Checking the first of the 'standard' variables", expected_PPV_CVTTK0, PPV_CVTTK0)

        def RUV_SDCP = variabilityModel[20]
        def expected_RUV_SDCP = [ 'RUV_SDCP' : [ 'value':'0.0249', 'type':'VAR' ] ]
        assertEquals("Checking the last of the 'standard' variables", expected_RUV_SDCP, RUV_SDCP)

        def diag1 = variabilityModel[21]["diag"]
        assertEquals("Checking the name of the single Diag variable", "\"struc3\"", diag1["name"])
        assertEquals("Checking the type of the single Diag variable", "VAR", diag1["type"])
        String expectedDiag1 = """PPV_IOV_IN_PRL0_1=.0657,
PPV_IOV_IN_PRL0_2=.0757"""
        assertEquals("Checking the content of the single Diag variable", expectedDiag1, diag1["content"])

    }

}