/*******************************************************************************
 * Copyright (C) 2014-5 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json

import static org.junit.Assert.*

import org.apache.log4j.Logger
import org.junit.Test
import org.junit.Ignore
import java.util.Map

import eu.ddmore.converter.mdl2json.domain.Variable

class ModelObjectToJSONTest extends ConverterTestsParent {
    private static Logger logger = Logger.getLogger(ModelObjectToJSONTest.class)

    @Test
    public void testIndependentVariablesBlock() {
        def json = getJsonFromMDLFile("UseCase3_ModelObject.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]

        def modelObject = json.Hansson2013_mdl

        def independentVars = modelObject.IDV

        logger.debug(independentVars)

        assertEquals("Checking number of covariates", 1, independentVars.size())
        
        assertEquals("Checking covariate 1/1", [(Variable.NAME_KEY):'T'], independentVars[0])
    }

    @Test
    public void testCovariatesBlock_ContainingVariableBeingExpression() {
        def json = getJsonFromMDLFile("UseCase2_ModelObject.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]

        def modelObject = json.warfarin_PK_ANALYTIC_mdl

        def covariates = modelObject.COVARIATES

        logger.debug(covariates)

        assertEquals("Checking number of covariates", 2, covariates.size())
        
        assertEquals("Checking covariate 1/2", [(Variable.NAME_KEY):'WT'], covariates[0])
        assertEquals("Checking covariate 2/2", [(Variable.NAME_KEY):'logtWT', (Variable.EXPRESSION_KEY):'log(WT/70)'], covariates[1])
    }

    /**
     * Testing variables like:
     * SEX : {type=categorical(female, male, MISSING)}
     */
    @Test
    public void testCovariatesBlock_ContainingVariableBeingSetOfAttributes() {
        def json = getJsonFromMDLFile("UseCase5_ModelObject.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]

        def modelObject = json.warfarin_PK_SEXAGE_mdl

        def covariates = modelObject.COVARIATES

        logger.debug(covariates)

        assertEquals("Checking number of covariates", 6, covariates.size())
        
        assertEquals("Checking covariate 1/6", [(Variable.NAME_KEY):'WT'], covariates[0])
        assertEquals("Checking covariate 2/6", [(Variable.NAME_KEY):'AGE'], covariates[1])
        assertEquals("Checking covariate 3/6", [(Variable.NAME_KEY):'SEX', 'type':'categorical(female, male, MISSING)'], covariates[2])
        assertEquals("Checking covariate 4/6", [(Variable.NAME_KEY):'logtWT', (Variable.EXPRESSION_KEY):'ln(WT/70)'], covariates[3])
        assertEquals("Checking covariate 5/6", [(Variable.NAME_KEY):'tAGE', (Variable.EXPRESSION_KEY):'AGE-40'], covariates[4])
        assertEquals("Checking covariate 6/6", [(Variable.NAME_KEY):'tSEX', (Variable.EXPRESSION_KEY):'1 when (SEX==female) otherwise 0'], covariates[5])
    }

    @Test
    public void testVariabilityLevelsBlock() {
        def json = getJsonFromMDLFile("UseCase1_ModelObject.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]

        def modelObject = json.warfarin_PK_ODE_mdl

        def variabilityLevels = modelObject.VARIABILITY_LEVELS

        logger.debug(variabilityLevels)

        assertEquals("Checking number of variability levels", 2, variabilityLevels.size())
        
        assertEquals("Checking variability level 1/2", [(Variable.NAME_KEY):'ID', 'level':'2', 'type':'parameter'], variabilityLevels[0])
        assertEquals("Checking variability level 2/2", [(Variable.NAME_KEY):'DV', 'level':'1', 'type':'observation'], variabilityLevels[1])
    }

    @Test
    public void testStructuralParametersBlock_BeingListOfVariableNames() {
        def json = getJsonFromMDLFile("UseCase1_ModelObject.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]

        def modelObject = json.warfarin_PK_ODE_mdl

        def structuralParameters = modelObject.STRUCTURAL_PARAMETERS

        logger.debug(structuralParameters)

        assertEquals("Checking number of structural parameters", 8, structuralParameters.size())
        
        assertEquals("Checking structural parameter 1/8", [(Variable.NAME_KEY):'POP_CL'], structuralParameters[0])
        assertEquals("Checking structural parameter 2/8", [(Variable.NAME_KEY):'POP_V'], structuralParameters[1])
        assertEquals("Checking structural parameter 3/8", [(Variable.NAME_KEY):'POP_KA'], structuralParameters[2])
        assertEquals("Checking structural parameter 4/8", [(Variable.NAME_KEY):'POP_TLAG'], structuralParameters[3])
        assertEquals("Checking structural parameter 5/8", [(Variable.NAME_KEY):'BETA_CL_WT'], structuralParameters[4])
        assertEquals("Checking structural parameter 6/8", [(Variable.NAME_KEY):'BETA_V_WT'], structuralParameters[5])
        assertEquals("Checking structural parameter 7/8", [(Variable.NAME_KEY):'RUV_PROP'], structuralParameters[6])
        assertEquals("Checking structural parameter 8/8", [(Variable.NAME_KEY):'RUV_ADD'], structuralParameters[7])
    }
    
    /**
     * Testing variables like:
     * POP_CL : {units="L/h/kg*70"}
     */
    @Test
    public void testStructuralParametersBlock_ContainingVariablesBeingSetsOfAttributes() {
        def json = getJsonFromMDLFile("UseCase10_ModelObject.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]
        
        def modelObject = json.warfarin_PK_2Compartments_mdl
        
        def structuralParameters = modelObject.STRUCTURAL_PARAMETERS
        
        logger.debug(structuralParameters)

        assertEquals("Checking number of structural parameters", 10, structuralParameters.size())
        
        assertEquals("Checking structural parameter 1/10", [(Variable.NAME_KEY):'POP_CL', 'units':'\"L/h/kg*70\"'], structuralParameters[0])
        assertEquals("Checking structural parameter 2/10", [(Variable.NAME_KEY):'POP_VC', 'units':'\"L/kg*70\"'], structuralParameters[1])
        assertEquals("Checking structural parameter 9/10", [(Variable.NAME_KEY):'RUV_PROP'], structuralParameters[8])
    }

    @Test
    public void testVariabilityParametersBlock_BeingListOfVariableNames() {
        def json = getJsonFromMDLFile("UseCase1_ModelObject.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]

        def modelObject = json.warfarin_PK_ODE_mdl

        def variabilityParameters = modelObject.VARIABILITY_PARAMETERS

        logger.debug(variabilityParameters)

        assertEquals("Checking number of variability parameters", 4, variabilityParameters.size())
        
        assertEquals("Checking variability parameter 1/4", [(Variable.NAME_KEY):'PPV_CL'], variabilityParameters[0])
        assertEquals("Checking variability parameter 2/4", [(Variable.NAME_KEY):'PPV_V'], variabilityParameters[1])
        assertEquals("Checking variability parameter 3/4", [(Variable.NAME_KEY):'PPV_KA'], variabilityParameters[2])
        assertEquals("Checking variability parameter 4/4", [(Variable.NAME_KEY):'PPV_TLAG'], variabilityParameters[3])
    }

    /**
     * Testing attributes like:
     * params=[ETA_CL, ETA_V]
     */
    @Test
    public void testVariabilityParametersBlock_ContainingVariableHavingAttributeBeingListOfVariableNames() {
        def json = getJsonFromMDLFile("UseCase20_ModelObject.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]

        def modelObject = json.warfarin_PK_SIM_mdl

        def variabilityParameters = modelObject.VARIABILITY_PARAMETERS

        logger.debug(variabilityParameters)

        assertEquals("Checking number of variability parameters", 7, variabilityParameters.size())
        
        assertEquals("Checking variability parameter 1/7", [(Variable.NAME_KEY):'PPV_CL'], variabilityParameters[0])
        assertEquals("Checking variability parameter 2/7", [(Variable.NAME_KEY):'PPV_V'], variabilityParameters[1])
        assertEquals("Checking variability parameter 3/7", [(Variable.NAME_KEY):'PPV_KA'], variabilityParameters[2])
        assertEquals("Checking variability parameter 4/7", [(Variable.NAME_KEY):'PPV_TLAG'], variabilityParameters[3])
        assertEquals("Checking variability parameter 5/7", [(Variable.NAME_KEY):'RUV_PROP'], variabilityParameters[4])
        assertEquals("Checking variability parameter 6/7", [(Variable.NAME_KEY):'RUV_ADD'], variabilityParameters[5])
        assertEquals("Checking variability parameter 7/7", [(Variable.NAME_KEY):'OMEGA', 'params':'[ETA_CL, ETA_V]', 'type':'CORR'], variabilityParameters[6])
    }

    @Test
    public void testRandomVariableDefinitionBlocks_1() {
        def json = getJsonFromMDLFile("UseCase1_ModelObject.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]
        
        def modelObject = json.warfarin_PK_ODE_mdl
    
        def randomVariableDefinitions = modelObject.RANDOM_VARIABLE_DEFINITION
        
        logger.debug(randomVariableDefinitions)
        
        assertEquals("Checking number of random variable definitions", 5, randomVariableDefinitions.size())
        
        // level=ID
        assertEquals("Checking name of distribution-variable 1/5", 'ETA_CL', randomVariableDefinitions[0][(Variable.NAME_KEY)])
        assertEquals("Checking level of distribution-variable 1/5 (an attribute of the block itself in the original MDL)", 'ID', randomVariableDefinitions[0]['level'])
        assertEquals("Checking distribution-variable 1/5 parameters", ['mean':'0', 'sd':'PPV_CL'], randomVariableDefinitions[0][Variable.RANDOMVAR_ATTRS_KEY])
        assertEquals("Checking distribution-variable 1/5 type", 'Normal', randomVariableDefinitions[0][Variable.RANDOMVAR_DISTRIBUTION_KEY])
        assertEquals("Checking name of distribution-variable 2/5", 'ETA_V', randomVariableDefinitions[1][(Variable.NAME_KEY)])
        assertEquals("Checking level of distribution-variable 2/5 (an attribute of the block itself in the original MDL)", 'ID', randomVariableDefinitions[1]['level'])
        assertEquals("Checking distribution-variable 2/5 parameters ", ['mean':'0', 'sd':'PPV_V'], randomVariableDefinitions[1][Variable.RANDOMVAR_ATTRS_KEY])
        assertEquals("Checking distribution-variable 2/5 type", 'Normal', randomVariableDefinitions[1][Variable.RANDOMVAR_DISTRIBUTION_KEY])
        assertEquals("Checking name of distribution-variable 3/5", 'ETA_KA', randomVariableDefinitions[2][(Variable.NAME_KEY)])
        assertEquals("Checking level of distribution-variable 3/5 (an attribute of the block itself in the original MDL)", 'ID', randomVariableDefinitions[2]['level'])
        assertEquals("Checking distribution-variable 3/5 parameters", ['mean':'0', 'sd':'PPV_KA'], randomVariableDefinitions[2][Variable.RANDOMVAR_ATTRS_KEY])
        assertEquals("Checking distribution-variable 3/5 type", 'Normal', randomVariableDefinitions[2][Variable.RANDOMVAR_DISTRIBUTION_KEY])
        assertEquals("Checking name of distribution-variable 4/5", 'ETA_TLAG', randomVariableDefinitions[3][(Variable.NAME_KEY)])
        assertEquals("Checking level of distribution-variable 4/5 (an attribute of the block itself in the original MDL)", 'ID', randomVariableDefinitions[3]['level'])
        assertEquals("Checking distribution-variable 4/5 parameters", ['mean':'0', 'sd':'PPV_TLAG'], randomVariableDefinitions[3][Variable.RANDOMVAR_ATTRS_KEY])
        assertEquals("Checking distribution-variable 4/5 type", 'Normal', randomVariableDefinitions[3][Variable.RANDOMVAR_DISTRIBUTION_KEY])
        
        // level=DV
        assertEquals("Checking name of distribution-variable 5/5", 'EPS_Y', randomVariableDefinitions[4][(Variable.NAME_KEY)])
        assertEquals("Checking level of distribution-variable 5/5 (an attribute of the block itself in the original MDL)", 'DV', randomVariableDefinitions[4]['level'])
        assertEquals("Checking distribution-variable 5/5 parameters", ['mean':'0', 'var':'1'], randomVariableDefinitions[4][Variable.RANDOMVAR_ATTRS_KEY])
        assertEquals("Checking distribution-variable 5/5 type", 'Normal', randomVariableDefinitions[4][Variable.RANDOMVAR_DISTRIBUTION_KEY])
        
    }
    
    @Test
    public void testRandomVariableDefinitionBlocks_2() {
        def json = getJsonFromMDLFile("UseCase8_ModelObject.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]
        
        def modelObject = json.warfarin_PK_BOV_mdl
    
        def randomVariableDefinitions = modelObject.RANDOM_VARIABLE_DEFINITION
        
        logger.debug(randomVariableDefinitions)
        
        assertEquals("Checking number of random variable definitions", 9, randomVariableDefinitions.size())
        
        // level=ID
        assertEquals("Checking name of distribution-variable 1/9", 'eta_BSV_CL', randomVariableDefinitions[0][(Variable.NAME_KEY)])
        assertEquals("Checking level of distribution-variable 1/9 (an attribute of the block itself in the original MDL)", 'ID', randomVariableDefinitions[0]['level'])
        assertEquals("Checking distribution-variable 1/9 parameters", ['mean':'0', 'var':'BSV_CL'], randomVariableDefinitions[0][Variable.RANDOMVAR_ATTRS_KEY])
        assertEquals("Checking distribution-variable 1/9 type", 'Normal', randomVariableDefinitions[0][Variable.RANDOMVAR_DISTRIBUTION_KEY])
        assertEquals("Checking name of distribution-variable 2/9", 'eta_BSV_V', randomVariableDefinitions[1][(Variable.NAME_KEY)])
        assertEquals("Checking level of distribution-variable 2/9 (an attribute of the block itself in the original MDL)", 'ID', randomVariableDefinitions[1]['level'])
        assertEquals("Checking distribution-variable 2/9 parameters", ['mean':'0', 'var':'BSV_V'], randomVariableDefinitions[1][Variable.RANDOMVAR_ATTRS_KEY])
        assertEquals("Checking distribution-variable 2/9 type", 'Normal', randomVariableDefinitions[1][Variable.RANDOMVAR_DISTRIBUTION_KEY])
        
        // level=OCC
        assertEquals("Checking name of distribution-variable 5/9", 'eta_BOV_CL', randomVariableDefinitions[4][(Variable.NAME_KEY)])
        assertEquals("Checking level of distribution-variable 5/9 (an attribute of the block itself in the original MDL)", 'OCC', randomVariableDefinitions[4]['level'])
        assertEquals("Checking distribution-variable 5/9 parameters", ['mean':'0', 'var':'BOV_CL'], randomVariableDefinitions[4][Variable.RANDOMVAR_ATTRS_KEY])
        assertEquals("Checking distribution-variable 5/9 type", 'Normal', randomVariableDefinitions[4][Variable.RANDOMVAR_DISTRIBUTION_KEY])
        assertEquals("Checking name of distribution-variable 6/9", 'eta_BOV_V', randomVariableDefinitions[5][(Variable.NAME_KEY)])
        assertEquals("Checking level of distribution-variable 6/9 (an attribute of the block itself in the original MDL)", 'OCC', randomVariableDefinitions[5]['level'])
        assertEquals("Checking distribution-variable 6/9 parameters", ['mean':'0', 'var':'BOV_V'], randomVariableDefinitions[5][Variable.RANDOMVAR_ATTRS_KEY])
        assertEquals("Checking distribution-variable 6/9 type", 'Normal', randomVariableDefinitions[5][Variable.RANDOMVAR_DISTRIBUTION_KEY])
        
        // level=DV
        assertEquals("Checking name of distribution-variable 9/9", 'EPS_Y', randomVariableDefinitions[8][(Variable.NAME_KEY)])
        assertEquals("Checking level of distribution-variable 9/9 (an attribute of the block itself in the original MDL)", 'DV', randomVariableDefinitions[8]['level'])
        assertEquals("Checking distribution-variable 9/9 parameters", ['mean':'0', 'var':'1'], randomVariableDefinitions[8][Variable.RANDOMVAR_ATTRS_KEY])
        assertEquals("Checking distribution-variable 9/9 type", 'Normal', randomVariableDefinitions[8][Variable.RANDOMVAR_DISTRIBUTION_KEY])
        
    }

    /**
     * Testing attributes like:
     * fixEff={coeff=BETA_CL_WT, cov=logtWT}
     */
    @Test
    public void testIndividualVariablesBlock_ContainingVariableHavingAttributeBeingSetOfNameValuePairs() {
        def json = getJsonFromMDLFile("UseCase1_ModelObject.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]

        def modelObject = json.warfarin_PK_ODE_mdl

        def individualVars = modelObject.INDIVIDUAL_VARIABLES

        logger.debug(individualVars)

        assertEquals("Checking number of Individual Variables", 4, individualVars.size())
        
        assertEquals("Checking Individual Variable 1/4",
            [ (Variable.NAME_KEY):'CL', 'type':'linear', 'trans':'log', 'pop':'POP_CL', 'fixEff':'{coeff=BETA_CL_WT, cov=logtWT}', 'ranEff':'ETA_CL' ],
            individualVars[0])
        assertEquals("Checking Individual Variable 2/4",
            [ (Variable.NAME_KEY):'V', 'type':'linear', 'trans':'log', 'pop':'POP_V', 'fixEff':'{coeff=BETA_V_WT, cov=logtWT}', 'ranEff':'ETA_V' ],
            individualVars[1])
        assertEquals("Checking Individual Variable 3/4",
            [ (Variable.NAME_KEY):'KA', 'type':'linear', 'trans':'log', 'pop':'POP_KA', 'ranEff':'ETA_KA' ],
            individualVars[2])
        assertEquals("Checking Individual Variable 4/4",
            [ (Variable.NAME_KEY):'TLAG', 'type':'linear', 'trans':'log', 'pop':'POP_TLAG', 'ranEff':'ETA_TLAG' ],
            individualVars[3])   
    }

    /**
     * Testing variables like:
     * TVSLP = POP_TVSLP/1000
     * and variables like:
     * BM0 : {pop=POP_BM0, ranEff=eta_BM0, trans=log, type=linear}
     */
    @Test
    public void testIndividualVariablesBlock_MixtureOfVariablesBeingExpressionsAndVariablesBeingSetsOfAttributes() {
        def json = getJsonFromMDLFile("UseCase3_ModelObject.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]

        def modelObject = json.Hansson2013_mdl

        def individualVars = modelObject.INDIVIDUAL_VARIABLES

        logger.debug(individualVars)

        assertEquals("Checking number of Individual Variables", 28, individualVars.size())
        
        assertEquals("Checking Individual Variable 1/28",
            [ (Variable.NAME_KEY):'BM0', 'type':'linear', 'trans':'log', 'pop':'POP_BM0', 'ranEff':'eta_BM0' ],
            individualVars[0])
        assertEquals("Checking Individual Variable 2/28",
            [ (Variable.NAME_KEY):'BM02', 'type':'linear', 'trans':'log', 'pop':'POP_BM02', 'ranEff':'eta_BM02' ],
            individualVars[1])
        assertEquals("Checking Individual Variable 3/28",
            [ (Variable.NAME_KEY):'BM03', 'type':'linear', 'trans':'log', 'pop':'POP_BM03', 'ranEff':'eta_BM03' ],
            individualVars[2])
        assertEquals("Checking Individual Variable 4/28",
            [ (Variable.NAME_KEY):'BM0S', 'type':'linear', 'trans':'log', 'pop':'POP_BM0S', 'ranEff':'eta_BM0S' ],
            individualVars[3])
        assertEquals("Checking Individual Variable 5/28", [ (Variable.NAME_KEY):'IMAX1', (Variable.EXPRESSION_KEY):'POP_IMAX' ], individualVars[4])
        assertEquals("Checking Individual Variable 6/28", [ (Variable.NAME_KEY):'IMAX2', (Variable.EXPRESSION_KEY):'POP_IMAX' ], individualVars[5])
        assertEquals("Checking Individual Variable 7/28", [ (Variable.NAME_KEY):'IMAX3', (Variable.EXPRESSION_KEY):'POP_IMAX' ], individualVars[6])
        assertEquals("Checking Individual Variable 8/28", [ (Variable.NAME_KEY):'IMAXS', (Variable.EXPRESSION_KEY):'POP_IMAX' ], individualVars[7])
        assertEquals("Checking Individual Variable 13/28", [ (Variable.NAME_KEY):'HILL', (Variable.EXPRESSION_KEY):'POP_HILL' ], individualVars[12])
        assertEquals("Checking Individual Variable 14/28", [ (Variable.NAME_KEY):'HILL2', (Variable.EXPRESSION_KEY):'POP_HILL2' ], individualVars[13])
        assertEquals("Checking Individual Variable 19/28", [ (Variable.NAME_KEY):'TVSLP', (Variable.EXPRESSION_KEY):'POP_TVSLP/1000' ], individualVars[18])
        assertEquals("Checking Individual Variable 20/28", [ (Variable.NAME_KEY):'TVSLP', (Variable.EXPRESSION_KEY):'POP_TVSLP/1000' ], individualVars[18])
        assertEquals("Checking Individual Variable 23/28", [ (Variable.NAME_KEY):'KOUT', (Variable.EXPRESSION_KEY):'1/MRT1' ], individualVars[22])
        assertEquals("Checking Individual Variable 24/28", [ (Variable.NAME_KEY):'KOUT2', (Variable.EXPRESSION_KEY):'1/MRT2' ], individualVars[23])
        assertEquals("Checking Individual Variable 25/28", [ (Variable.NAME_KEY):'KOUT3', (Variable.EXPRESSION_KEY):'1/MRT3' ], individualVars[24])
        assertEquals("Checking Individual Variable 26/28", [ (Variable.NAME_KEY):'KOUTS', (Variable.EXPRESSION_KEY):'1/MRTS' ], individualVars[25])
        assertEquals("Checking Individual Variable 27/28", [ (Variable.NAME_KEY):'KIN2', (Variable.EXPRESSION_KEY):'BM02*KOUT2' ], individualVars[26])
        assertEquals("Checking Individual Variable 28/28", [ (Variable.NAME_KEY):'KIN3', (Variable.EXPRESSION_KEY):'BM03*KOUT3' ], individualVars[27])
    }

    /**
     * Testing attributes like:
     * ranEff=[eta_BSV_CL, eta_BOV_CL]
     */
    @Test
    public void testIndividualVariablesBlock_ContainingVariableHavingAttributeBeingListOfVariableNames() {
        def json = getJsonFromMDLFile("UseCase8_ModelObject.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]

        def modelObject = json.warfarin_PK_BOV_mdl

        def individualVars = modelObject.INDIVIDUAL_VARIABLES

        logger.debug(individualVars)

        assertEquals("Checking number of Individual Variables", 4, individualVars.size())
        
        assertEquals("Checking Individual Variable 1/4",
            [ (Variable.NAME_KEY):'CL', 'type':'linear', 'trans':'log', 'pop':'POP_CL', 'fixEff':'{coeff=BETA_CL_WT, cov=logtWT}', 'ranEff':'[eta_BSV_CL, eta_BOV_CL]' ],
            individualVars[0])
        assertEquals("Checking Individual Variable 2/4",
            [ (Variable.NAME_KEY):'V', 'type':'linear', 'trans':'log', 'pop':'POP_V', 'fixEff':'{coeff=BETA_V_WT, cov=logtWT}', 'ranEff':'[eta_BSV_V, eta_BOV_V]' ],
            individualVars[1])
        assertEquals("Checking Individual Variable 3/4",
            [ (Variable.NAME_KEY):'KA', 'type':'linear', 'trans':'log', 'pop':'POP_KA', 'ranEff':'[eta_BSV_KA, eta_BOV_KA]' ],
            individualVars[2])
        assertEquals("Checking Individual Variable 4/4",
            [ (Variable.NAME_KEY):'TLAG', 'type':'linear', 'trans':'log', 'pop':'POP_TLAG', 'ranEff':'[eta_BSV_TLAG, eta_BOV_TLAG]' ],
            individualVars[3])
    }

    /**
     * Testing attributes like:
     * fixEff=[{coeff=BETA_CL_WT, cov=logtWT}, ...]
     */
    @Test
    public void testIndividualVariablesBlock_ContainingVariableHavingAttributeBeingListOfSetsOfNameValuePairs() {
        def json = getJsonFromMDLFile("UseCase5_ModelObject.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]

        def modelObject = json.warfarin_PK_SEXAGE_mdl

        def individualVars = modelObject.INDIVIDUAL_VARIABLES

        logger.debug(individualVars)

        assertEquals("Checking number of Individual Variables", 4, individualVars.size())
        
        assertEquals("Checking Individual Variable 1/4",
            [ (Variable.NAME_KEY):'CL', 'type':'linear', 'trans':'log', 'pop':'POP_CL', 'fixEff':'[{coeff=BETA_CL_WT, cov=logtWT}, {coeff=POP_FCL_FEM, cov=tSEX}, {coeff=BETA_CL_AGE, cov=tAGE}]', 'ranEff':'ETA_CL'],
            individualVars[0])
    }

    /**
     * This test specifically picked up a bug in XtextWrapper re a multi-part expression without brackets and having a mixture of operators.
     */
    @Test
    public void testIndividualVariablesBlockContainingMultipartExprWithoutBracketsAndHavingMixtureOfOperators() {
        def json = getJsonFromMDLFile("Rocchetti2013_ModelObject_IndividualVariables.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]

        def modelObject = json.rocchetti2013_mdl

        def individualVars = modelObject.INDIVIDUAL_VARIABLES

        logger.debug(individualVars)

        assertEquals("Checking number of Individual Variables", 10, individualVars.size())
        
        assertEquals("Checking Individual Variable 1/10",
            [ (Variable.EXPRESSION_KEY):'24*ln(2)/24', (Variable.NAME_KEY):'KA_A' ],
            individualVars[0])
        assertEquals("Checking Individual Variable 2/10",
            [ (Variable.EXPRESSION_KEY):'ln(2)/6.05', (Variable.NAME_KEY):'KE_A' ],
            individualVars[1])
        assertEquals("Checking Individual Variable 3/10",
            [ (Variable.EXPRESSION_KEY):'1/0.119', (Variable.NAME_KEY):'FV1_A' ],
            individualVars[2])
        assertEquals("Checking Individual Variable 4/10",
            [ (Variable.EXPRESSION_KEY):'18.8', (Variable.NAME_KEY):'KA_B' ],
            individualVars[3])
    }

    /**
     * Testing attributes like:
     * error=combinedError2log(additive=0, proportional=POP_RES_VEGF_PROP, f=VEGF)
     */
    @Test
    public void testObservationBlock_ContainingVariableHavingErrorFunctionAttribute() {
        def json = getJsonFromMDLFile("UseCase3_ModelObject.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]

        def modelObject = json.Hansson2013_mdl

        logger.debug(modelObject)

        def observation = modelObject.OBSERVATION

        assertEquals("Checking number of observations", 4, observation.size())
        
        assertEquals("Checking observation 1/4",
            [ (Variable.NAME_KEY):'LNVEGF_obs', 'type':'continuous', 'error':'combinedError2log(additive=0, proportional=POP_RES_VEGF_PROP, f=VEGF)', 'prediction':'VEGF', 'eps':'eps_RES_W' ],
            observation[0])
        assertEquals("Checking observation 2/4",
            [ (Variable.NAME_KEY):'LNsVEGFR2_obs', 'type':'continuous', 'error':'combinedError2log(additive=POP_RES_sVEGFR2_ADD, proportional=POP_RES_sVEGFR2_PROP, f=sVEGFR2)', 'prediction':'sVEGFR2', 'eps':'eps_RES_W' ],
            observation[1])
        assertEquals("Checking observation 3/4",
            [ (Variable.NAME_KEY):'LNsVEGFR3_obs', 'type':'continuous', 'error':'combinedError2log(additive=0, proportional=POP_RES_sVEGFR3_PROP, f=sVEGFR3)', 'prediction':'sVEGFR3', 'eps':'eps_RES_W' ],
            observation[2])
        assertEquals("Checking observation 4/4",
            [ (Variable.NAME_KEY):'LNsKIT_obs', 'type':'continuous', 'error':'combinedError2log(additive=0, proportional=POP_RES_sKIT_PROP, f=sKIT)', 'prediction':'sKIT', 'eps':'eps_RES_W' ],
            observation[3])
    }

    @Test
    public void testObservationBlock_ContainingVariableBeingExpression() {
        def json = getJsonFromMDLFile("UseCase19_ModelObject.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]

        def modelObject = json.warfarin_PK_BOV_mdl

        logger.debug(modelObject)

        def observation = modelObject.OBSERVATION

        assertEquals("Checking number of observations", 1, observation.size())
        
        assertEquals("Checking observation 1/1",
            [ (Variable.NAME_KEY):'Y', (Variable.EXPRESSION_KEY):'CC*(1+eps_RUV_PROP)+eps_RUV_ADD' ],
            observation[0])
    }
    
    @Test
    public void testModelPredictionBlock_ContainingVariablesBeingExpressions() {
        def json = getJsonFromMDLFile("UseCase2_ModelObject.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]

        def modelObject = json.warfarin_PK_ANALYTIC_mdl

        logger.debug(modelObject)

        def modPred = modelObject.MODEL_PREDICTION

        assertEquals("Checking number of Model Prediction items", 4, modPred.size())
        
        assertEquals("Checking Model Prediction item 1/4", [(Variable.NAME_KEY):'D'], modPred[0])
        assertEquals("Checking Model Prediction item 2/4", [(Variable.NAME_KEY):'DT'], modPred[1])
        assertEquals("Checking Model Prediction item 3/4", [(Variable.NAME_KEY):'k', (Variable.EXPRESSION_KEY):'CL/V'], modPred[2])
        assertEquals("Checking Model Prediction item 4/4", [(Variable.NAME_KEY):'CC', (Variable.EXPRESSION_KEY):'0 when T-DT<TLAG otherwise (D/V)*(KA/(KA-k)*(exp(-k*(T-DT-TLAG)-exp(-KA*(T-DT-TLAG)))))'], modPred[3])
    }

    @Test
    public void testModelPredictionBlockWithDeqSubBlock() {
        def json = getJsonFromMDLFile("UseCase3_ModelObject.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]

        def modelObject = json.Hansson2013_mdl

        logger.debug(modelObject)

        def modPred = modelObject.MODEL_PREDICTION

        assertEquals("Checking number of Model Prediction items", 7, modPred.size())
        
        assertEquals("Checking Model Prediction item 1/6", [(Variable.NAME_KEY):'AUC', (Variable.EXPRESSION_KEY):'DOSE/CL'], modPred[0])
        assertEquals("Checking Model Prediction item 2/6", [(Variable.NAME_KEY):'DP1', (Variable.EXPRESSION_KEY):'BM0*(1+DPSLP*T)'], modPred[1])
        assertEquals("Checking Model Prediction item 3/6", [(Variable.NAME_KEY):'DPS', (Variable.EXPRESSION_KEY):'BM0S*(1+DPSLPS*T)'], modPred[2])
        assertEquals("Checking Model Prediction item 4/6", [(Variable.NAME_KEY):'KIN', (Variable.EXPRESSION_KEY):'DP1*KOUT'], modPred[3])
        assertEquals("Checking Model Prediction item 5/6", [(Variable.NAME_KEY):'KINS', (Variable.EXPRESSION_KEY):'DPS*KOUTS'], modPred[4])
        final Map.Entry deqSubblockMapEntry = modPred[5].entrySet().toArray()[0]
        assertEquals("Checking that Model Prediction item 6/6 is a DEQ sub-block", '.DEQ', deqSubblockMapEntry.getKey())
        final deqSubbblockItems = deqSubblockMapEntry.getValue()
        assertEquals("Checking number of Model Prediction DEQ sub-block items", 8, deqSubbblockItems.size())
        assertEquals("Checking Model Prediction DEQ sub-block item 1/8",
            [(Variable.NAME_KEY):'EFF', (Variable.EXPRESSION_KEY):'IMAX1*AUC^HILL/(IC50^HILL+AUC^HILL)'], deqSubbblockItems[0])
        assertEquals("Checking Model Prediction DEQ sub-block item 2/8",
            [(Variable.NAME_KEY):'EFF2', (Variable.EXPRESSION_KEY):'IMAX2*AUC^HILL2/(IC502^HILL2+AUC^HILL2)'], deqSubbblockItems[1])
        assertEquals("Checking Model Prediction DEQ sub-block item 3/8",
            [(Variable.NAME_KEY):'EFF3', (Variable.EXPRESSION_KEY):'IMAX3*AUC/(IC503+AUC)'], deqSubbblockItems[2])
        assertEquals("Checking Model Prediction DEQ sub-block item 4/8",
            [(Variable.NAME_KEY):'EFFS', (Variable.EXPRESSION_KEY):'IMAXS*AUC/(IC50S+AUC)'], deqSubbblockItems[3])
        assertEquals("Checking Model Prediction DEQ sub-block item 5/8",
            [(Variable.NAME_KEY):'VEGF', 'deriv':'KIN-KOUT*(1-EFF)*VEGF', 'init':'BM0'], deqSubbblockItems[4])
        assertEquals("Checking Model Prediction DEQ sub-block item 6/8",
            [(Variable.NAME_KEY):'sVEGFR2', 'deriv':'KIN2*(1-EFF2)-KOUT2*sVEGFR2', 'init':'BM02', 'wrt':'T'], deqSubbblockItems[5])
        assertEquals("Checking Model Prediction DEQ sub-block item 7/8",
            [(Variable.NAME_KEY):'sVEGFR3', 'deriv':'KIN3*(1-EFF3)-KOUT3*sVEGFR3', 'init':'BM03', 'wrt':'T'], deqSubbblockItems[6])
        assertEquals("Checking Model Prediction DEQ sub-block item 8/8",
            [(Variable.NAME_KEY):'sKIT', 'deriv':'KINS*(1-EFFS)-KOUTS*sKIT', 'init':'BM0S', 'wrt':'T'], deqSubbblockItems[7])
    }

    @Test
    public void testModelPredictionBlockWithCompartmentSubBlock() {
        def json = getJsonFromMDLFile("UseCase10_ModelObject.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]
        
        def modelObject = json.warfarin_PK_2Compartments_mdl
        
        logger.debug(modelObject)
        
        def modPred = modelObject.MODEL_PREDICTION
        
        assertEquals("Checking number of Model Prediction items", 3, modPred.size())
        
        final Map.Entry compartmentSubblockMapEntry = modPred[0].entrySet().toArray()[0]
        assertEquals("Checking that Model Prediction item 1/3 is a COMPARTMENT sub-block", '.COMPARTMENT', compartmentSubblockMapEntry.getKey())
        final compartmentSubbblockItems = compartmentSubblockMapEntry.getValue()
        assertEquals("Checking number of Model Prediction COMPARTMENT sub-block items", 4, compartmentSubbblockItems.size())
        assertEquals("Checking Model Prediction COMPARTMENT sub-block item 1/4",
            [(Variable.NAME_KEY):'INPUT_KA', 'type':'depot', 'modelCmt':'1', 'to':'CENTRAL', 'ka':'KA', 'tlag':'ALAG1'], compartmentSubbblockItems[0])
        assertEquals("Checking Model Prediction COMPARTMENT sub-block item 2/4",
            [(Variable.NAME_KEY):'CENTRAL', 'type':'compartment', 'modelCmt':'2'], compartmentSubbblockItems[1])
        assertEquals("Checking Model Prediction COMPARTMENT sub-block item 3/4",
            ['type':'elimination', 'modelCmt':'2', 'from':'CENTRAL', 'v':'V2', 'cl':'CL'], compartmentSubbblockItems[2])
        assertEquals("Checking Model Prediction COMPARTMENT sub-block item 4/4",
            [(Variable.NAME_KEY):'PERIPHERAL', 'type':'distribution', 'modelCmt':'3', 'from':'CENTRAL', 'kin':'Q/V2', 'kout':'Q/V3'], compartmentSubbblockItems[3])
        assertEquals("Checking Model Prediction item 2/3", [(Variable.NAME_KEY):'F', (Variable.EXPRESSION_KEY):'CENTRAL/S2'], modPred[1])
        assertEquals("Checking Model Prediction item 3/3", [(Variable.NAME_KEY):'CC', (Variable.EXPRESSION_KEY):'F'], modPred[2])
    }
    
    @Test
    public void testModelPredictionBlockWithPkMacroSubBlock() {
        def json = getJsonFromMDLFile("UseCase22_ModelObject.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]
        
        def modelObject = json.warfarin_PK_BOV_mdl
        
        logger.debug(modelObject)
        
        def modPred = modelObject.MODEL_PREDICTION
        
        assertEquals("Checking number of Model Prediction items", 2, modPred.size())
        
        final Map.Entry pkmacroSubblockMapEntry = modPred[0].entrySet().toArray()[0]
        assertEquals("Checking that Model Prediction item 1/2 is a PKMACRO sub-block", '.PKMACRO', pkmacroSubblockMapEntry.getKey())
        final pkmacroSubblockItems = pkmacroSubblockMapEntry.getValue()
        assertEquals("Checking number of Model Prediction PKMACRO sub-block items", 4, pkmacroSubblockItems.size())
        assertEquals("Checking Model Prediction PKMACRO sub-block item 1/4",
            [(Variable.NAME_KEY):'DEP'], pkmacroSubblockItems[0])
        assertEquals("Checking Model Prediction PKMACRO sub-block item 2/4",
            ['macro':'oral', 'to':'Ac', 'ka':'KA', 'tlag':'ALAG1'], pkmacroSubblockItems[1])
        assertEquals("Checking Model Prediction PKMACRO sub-block item 3/4",
            [(Variable.NAME_KEY):'Ac', 'macro':'compartment', 'volume':'V'], pkmacroSubblockItems[2])
        assertEquals("Checking Model Prediction PKMACRO sub-block item 4/4",
            ['macro':'elimination', 'from':'Ac', 'cl':'CL'], pkmacroSubblockItems[3])
    }
    
    @Test
    public void testGroupVariablesBlock_ContainingVariablesBeingExpressions() {
        def json = getJsonFromMDLFile("UseCase19_ModelObject.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]

        def modelObject = json.warfarin_PK_BOV_mdl

        logger.debug(modelObject)

        def groupVars = modelObject.GROUP_VARIABLES

        assertEquals("Checking number of Group Variables", 4, groupVars.size())
        
        assertEquals("Checking Group Variable 1/4", [(Variable.NAME_KEY):'GRPCL', (Variable.EXPRESSION_KEY):'POP_CL*(WT/70)^0.75'], groupVars[0])
        assertEquals("Checking Group Variable 2/4", [(Variable.NAME_KEY):'GRPV', (Variable.EXPRESSION_KEY):'POP_V*WT/70'], groupVars[1])
        assertEquals("Checking Group Variable 3/4", [(Variable.NAME_KEY):'GRPKA', (Variable.EXPRESSION_KEY):'POP_KA'], groupVars[2])
        assertEquals("Checking Group Variable 4/4", [(Variable.NAME_KEY):'GRPLG', (Variable.EXPRESSION_KEY):'POP_TLAG'], groupVars[3])
    }
    
    @Test
    public void testModelOutputVariablesBlock() {
        def json = getJsonFromMDLFile("UseCase10_ModelObject.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]
        
        def modelObject = json.warfarin_PK_2Compartments_mdl
        
        logger.debug(modelObject)
        
        def modelOutputVars = modelObject.MODEL_OUTPUT_VARIABLES
        
        assertEquals("Checking number of Model Output Variables", 13, modelOutputVars.size())
        
        assertEquals("Checking Model Output Variable 1/13", [(Variable.NAME_KEY):'ID'], modelOutputVars[0])
        assertEquals("Checking Model Output Variable 2/13", [(Variable.NAME_KEY):'TIME'], modelOutputVars[1])
        assertEquals("Checking Model Output Variable 3/13", [(Variable.NAME_KEY):'WT'], modelOutputVars[2])
    }
    
    @Test
    public void testVariableHavingDistributionAsAttribute() {
        def json = getJsonFromMDLFile("UseCase11_ModelObject.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]
        
        def modelObject = json.Poisson_DIST_mdl
        
        logger.debug(modelObject)
        
        def observation = modelObject.OBSERVATION
        
        assertEquals("Checking the distribution attribute", "~Poisson(lambda=LAMBDA)", observation[0].distn)
        
    }

}
