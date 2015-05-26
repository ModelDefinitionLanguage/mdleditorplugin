/*******************************************************************************
 * Copyright (C) 2014-5 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json

import static org.junit.Assert.*

import org.apache.log4j.Logger
import org.junit.Test

import eu.ddmore.converter.mdl2json.domain.Model


class JSONModelObjectToMDLTest extends ConverterTestsParent {
    private static final Logger logger = Logger.getLogger(JSONModelObjectToMDLTest.class)

    // Using slashy strings /.../ here so we don't have to escape anything other than forward slashes
    private final static String independentVariablesBlockJson_UseCase16 =
        / {"IDV":[{".name":"T"}]} /
    private final static String covariatesBlockJson_UseCase2 =
        / {"COVARIATES":[{".name":"WT"},{".expr":"log(WT\/70)",".name":"logtWT"}]} /
    private final static String covariatesBlockJson_UseCase5 =
        / {"COVARIATES":[{".name":"WT"},{".name":"AGE"},{".name":"SEX","type":"categorical(female, male, MISSING)"},{".expr":"ln(WT\/70)",".name":"logtWT"},{".expr":"AGE-40",".name":"tAGE"},{".expr":"1 when (SEX==female) otherwise 0",".name":"tSEX"}]} /
    private final static String variabilityLevelsBlockJson_UseCase1 =
        / {"VARIABILITY_LEVELS":[{"level":"2",".name":"ID","type":"parameter"},{"level":"1",".name":"DV","type":"observation"}]} /
    private final static String structuralParametersBlockJson_UseCase1 =
        / {"STRUCTURAL_PARAMETERS":[{".name":"POP_CL"},{".name":"POP_V"},{".name":"POP_KA"},{".name":"POP_TLAG"},{".name":"BETA_CL_WT"},{".name":"BETA_V_WT"},{".name":"RUV_PROP"},{".name":"RUV_ADD"}]} /
    private final static String structuralParametersBlockJson_UseCase10 =
        / {"STRUCTURAL_PARAMETERS":[{".name":"POP_CL","units":"\"L\/h\/kg*70\""},{".name":"POP_VC","units":"\"L\/kg*70\""},{".name":"POP_Q","units":"\"L\/h\/kg*70\""},{".name":"POP_VP","units":"\"L\/kg*70\""},{".name":"POP_KA","units":"\"1\/h\""},{".name":"POP_TLAG","units":"\"h\""},{".name":"POP_BETA_CL_WT"},{".name":"POP_BETA_V_WT"},{".name":"RUV_PROP"},{".name":"RUV_ADD","units":"\"mg\/L\""}]} /
    private final static String variabilityParametersBlockJson_UseCase1 =
        / {"VARIABILITY_PARAMETERS":[{".name":"PPV_CL"},{".name":"PPV_V"},{".name":"PPV_KA"},{".name":"PPV_TLAG"}]} /
    private final static String variabilityParametersBlockJson_UseCase20 =
        / {"VARIABILITY_PARAMETERS":[{".name":"PPV_CL"},{".name":"PPV_V"},{".name":"PPV_KA"},{".name":"PPV_TLAG"},{".name":"RUV_PROP"},{".name":"RUV_ADD"},{"params":"[ETA_CL, ETA_V]",".name":"OMEGA","type":"CORR"}]} /
    private final static String randomVarDefinitionBlocksJson_UseCase1 =
        / {"RANDOM_VARIABLE_DEFINITION":[{".random_var_distribution":"Normal",".random_var_attrs":{"mean":"0","sd":"PPV_CL"},"level":"ID",".name":"ETA_CL"},{".random_var_distribution":"Normal",".random_var_attrs":{"mean":"0","sd":"PPV_V"},"level":"ID",".name":"ETA_V"},{".random_var_distribution":"Normal",".random_var_attrs":{"mean":"0","sd":"PPV_KA"},"level":"ID",".name":"ETA_KA"},{".random_var_distribution":"Normal",".random_var_attrs":{"mean":"0","sd":"PPV_TLAG"},"level":"ID",".name":"ETA_TLAG"},{".random_var_distribution":"Normal",".random_var_attrs":{"mean":"0","var":"1"},"level":"DV",".name":"EPS_Y"}]} /
    private final static String randomVarDefinitionBlocksJson_UseCase8 =
        / {"RANDOM_VARIABLE_DEFINITION":[{".random_var_distribution":"Normal",".random_var_attrs":{"mean":"0","var":"BSV_CL"},"level":"ID",".name":"eta_BSV_CL"},{".random_var_distribution":"Normal",".random_var_attrs":{"mean":"0","var":"BSV_V"},"level":"ID",".name":"eta_BSV_V"},{".random_var_distribution":"Normal",".random_var_attrs":{"mean":"0","var":"BSV_KA"},"level":"ID",".name":"eta_BSV_KA"},{".random_var_distribution":"Normal",".random_var_attrs":{"mean":"0","var":"BSV_TLAG"},"level":"ID",".name":"eta_BSV_TLAG"},{".random_var_distribution":"Normal",".random_var_attrs":{"mean":"0","var":"BOV_CL"},"level":"OCC",".name":"eta_BOV_CL"},{".random_var_distribution":"Normal",".random_var_attrs":{"mean":"0","var":"BOV_V"},"level":"OCC",".name":"eta_BOV_V"},{".random_var_distribution":"Normal",".random_var_attrs":{"mean":"0","var":"BOV_KA"},"level":"OCC",".name":"eta_BOV_KA"},{".random_var_distribution":"Normal",".random_var_attrs":{"mean":"0","var":"BOV_TLAG"},"level":"OCC",".name":"eta_BOV_TLAG"},{".random_var_distribution":"Normal",".random_var_attrs":{"mean":"0","var":"1"},"level":"DV",".name":"EPS_Y"}]} /
    private final static String individualVarsBlockJson_UseCase1 =
        / {"INDIVIDUAL_VARIABLES":[{"fixEff":"{coeff=BETA_CL_WT, cov=logtWT}","trans":"log","ranEff":"ETA_CL","pop":"POP_CL",".name":"CL","type":"linear"},{"fixEff":"{coeff=BETA_V_WT, cov=logtWT}","trans":"log","ranEff":"ETA_V","pop":"POP_V",".name":"V","type":"linear"},{"trans":"log","ranEff":"ETA_KA","pop":"POP_KA",".name":"KA","type":"linear"},{"trans":"log","ranEff":"ETA_TLAG","pop":"POP_TLAG",".name":"TLAG","type":"linear"}]} /
    private final static String individualVarsBlockJson_UseCase16 =
        / {"INDIVIDUAL_VARIABLES":[{"trans":"log","ranEff":"eta_BM0","pop":"POP_BM0",".name":"BM0","type":"linear"},{"trans":"log","ranEff":"eta_BM02","pop":"POP_BM02",".name":"BM02","type":"linear"},{"trans":"log","ranEff":"eta_BM03","pop":"POP_BM03",".name":"BM03","type":"linear"},{"trans":"log","ranEff":"eta_BM0S","pop":"POP_BM0S",".name":"BM0S","type":"linear"},{".expr":"POP_IMAX",".name":"IMAX1"},{".expr":"POP_IMAX",".name":"IMAX2"},{".expr":"POP_IMAX",".name":"IMAX3"},{".expr":"POP_IMAX",".name":"IMAXS"},{"trans":"log","ranEff":"eta_IC50","pop":"POP_IC50",".name":"IC50","type":"linear"},{"trans":"log","ranEff":"eta_IC502","pop":"POP_IC50",".name":"IC502","type":"linear"},{"trans":"log","ranEff":"eta_IC503","pop":"POP_IC50",".name":"IC503","type":"linear"},{"trans":"log","ranEff":"eta_IC50S","pop":"POP_IC50",".name":"IC50S","type":"linear"},{".expr":"POP_HILL",".name":"HILL"},{".expr":"POP_HILL2",".name":"HILL2"},{"trans":"log","ranEff":"eta_MRT_VEGFs","pop":"POP_MRT",".name":"MRT1","type":"linear"},{"trans":"log","ranEff":"eta_MRT_VEGFs","pop":"POP_MRT2",".name":"MRT2","type":"linear"},{"trans":"log","ranEff":"eta_MRT_VEGFs","pop":"POP_MRT3",".name":"MRT3","type":"linear"},{"trans":"log","ranEff":"eta_MRT_sKIT","pop":"POP_MRTS",".name":"MRTS","type":"linear"},{".expr":"POP_TVSLP\/1000",".name":"TVSLP"},{"trans":"log","ranEff":"eta_TVSLP","pop":"TVSLP",".name":"DPSLP","type":"linear"},{".expr":"POP_TVSLP\/1000",".name":"TVSLPS"},{"trans":"log","ranEff":"eta_TVSLPS","pop":"TVSLPS",".name":"DPSLPS","type":"linear"},{".expr":"1\/MRT1",".name":"KOUT"},{".expr":"1\/MRT2",".name":"KOUT2"},{".expr":"1\/MRT3",".name":"KOUT3"},{".expr":"1\/MRTS",".name":"KOUTS"},{".expr":"BM02*KOUT2",".name":"KIN2"},{".expr":"BM03*KOUT3",".name":"KIN3"}]} /
    private final static String individualVarsBlockJson_UseCase8 =
        / {"INDIVIDUAL_VARIABLES":[{"fixEff":"{coeff=BETA_CL_WT, cov=logtWT}","trans":"log","ranEff":"[eta_BSV_CL, eta_BOV_CL]","pop":"POP_CL",".name":"CL","type":"linear"},{"fixEff":"{coeff=BETA_V_WT, cov=logtWT}","trans":"log","ranEff":"[eta_BSV_V, eta_BOV_V]","pop":"POP_V",".name":"V","type":"linear"},{"trans":"log","ranEff":"[eta_BSV_KA, eta_BOV_KA]","pop":"POP_KA",".name":"KA","type":"linear"},{"trans":"log","ranEff":"[eta_BSV_TLAG, eta_BOV_TLAG]","pop":"POP_TLAG",".name":"TLAG","type":"linear"}]} /
    private final static String individualVarsBlockJson_UseCase5 =
        / {"INDIVIDUAL_VARIABLES":[{"fixEff":"[{coeff=BETA_CL_WT, cov=logtWT}, {coeff=POP_FCL_FEM, cov=tSEX}, {coeff=BETA_CL_AGE, cov=tAGE}]","trans":"log","ranEff":"ETA_CL","pop":"POP_CL",".name":"CL","type":"linear"},{"fixEff":"{coeff=BETA_V_WT, cov=logtWT}","trans":"log","ranEff":"ETA_V","pop":"POP_V",".name":"V","type":"linear"},{"trans":"log","ranEff":"ETA_KA","pop":"POP_KA",".name":"KA","type":"linear"},{"trans":"log","ranEff":"ETA_TLAG","pop":"POP_TLAG",".name":"TLAG","type":"linear"}]} /
    private final static String observationBlockJson_UseCase16 =
        / {"OBSERVATION":[{"eps":"eps_RES_W","error":"combinedError2log(additive=0, proportional=POP_RES_VEGF_PROP, f=VEGF)","prediction":"VEGF",".name":"LNVEGF_obs","type":"continuous"},{"eps":"eps_RES_W","error":"combinedError2log(additive=POP_RES_sVEGFR2_ADD, proportional=POP_RES_sVEGFR2_PROP, f=sVEGFR2)","prediction":"sVEGFR2",".name":"LNsVEGFR2_obs","type":"continuous"},{"eps":"eps_RES_W","error":"combinedError2log(additive=0, proportional=POP_RES_sVEGFR3_PROP, f=sVEGFR3)","prediction":"sVEGFR3",".name":"LNsVEGFR3_obs","type":"continuous"},{"eps":"eps_RES_W","error":"combinedError2log(additive=0, proportional=POP_RES_sKIT_PROP, f=sKIT)","prediction":"sKIT",".name":"LNsKIT_obs","type":"continuous"}]} /
    private final static String observationBlockJson_UseCase19 =
        / {"OBSERVATION":[{".expr":"CC*(1+eps_RUV_PROP)+eps_RUV_ADD",".name":"Y"}]} /
    private final static String modelPredictionBlockJson_UseCase2 =
        / {"MODEL_PREDICTION":[{".name":"D"},{".name":"DT"},{".expr":"CL\/V",".name":"k"},{".expr":"0 when T-DT<TLAG otherwise (D\/V)*(KA\/(KA-k)*(exp(-k*(T-DT-TLAG)-exp(-KA*(T-DT-TLAG)))))",".name":"CC"}]} /
    private final static String modelPredictionBlockJson_UseCase16 =
        / {"MODEL_PREDICTION":[{".name":"DOSE"},{".expr":"DOSE\/CL",".name":"AUC"},{".expr":"BM0*(1+DPSLP*T)",".name":"DP1"},{".expr":"BM0S*(1+DPSLPS*T)",".name":"DPS"},{".expr":"DP1*KOUT",".name":"KIN"},{".expr":"DPS*KOUTS",".name":"KINS"},{".DEQ":[{".expr":"IMAX1*AUC^HILL\/(IC50^HILL+AUC^HILL)",".name":"EFF"},{".expr":"IMAX2*AUC^HILL2\/(IC502^HILL2+AUC^HILL2)",".name":"EFF2"},{".expr":"IMAX3*AUC\/(IC503+AUC)",".name":"EFF3"},{".expr":"IMAXS*AUC\/(IC50S+AUC)",".name":"EFFS"},{"deriv":"KIN-KOUT*(1-EFF)*VEGF","init":"BM0",".name":"VEGF"},{"wrt":"T","deriv":"KIN2*(1-EFF2)-KOUT2*sVEGFR2","init":"BM02",".name":"sVEGFR2"},{"wrt":"T","deriv":"KIN3*(1-EFF3)-KOUT3*sVEGFR3","init":"BM03",".name":"sVEGFR3"},{"wrt":"T","deriv":"KINS*(1-EFFS)-KOUTS*sKIT","init":"BM0S",".name":"sKIT"}]},{".expr":"ln(VEGF)",".name":"LNVEGF"}]} /
    private final static String modelPredictionBlockCompartmentJson_UseCase10 =
        / {"MODEL_PREDICTION":[{".COMPARTMENT":[{"to":"CENTRAL","modelCmt":"1","tlag":"ALAG1","ka":"KA",".name":"INPUT_KA","type":"depot"},{"modelCmt":"2",".name":"CENTRAL","type":"compartment"},{"v":"V2","modelCmt":"2","cl":"CL","from":"CENTRAL","type":"elimination"},{"modelCmt":"3","kout":"Q\/V3","kin":"Q\/V2","from":"CENTRAL",".name":"PERIPHERAL","type":"distribution"}]},{".expr":"CENTRAL\/S2",".name":"F"},{".expr":"F",".name":"CC"}]} /
    private final static String modelPredictionBlockPkMacroJson_UseCase22 =
        / {"MODEL_PREDICTION":[{".PKMACRO":[{".name":"DEP"},{"to":"Ac","macro":"oral","tlag":"ALAG1","ka":"KA"},{"macro":"compartment","volume":"V",".name":"Ac"},{"macro":"elimination","cl":"CL","from":"Ac"}]},{".expr":"Ac\/V",".name":"CONC"}]} /
    private final static String groupVariablesBlockJson_UseCase19 =
        / {"GROUP_VARIABLES":[{".expr":"POP_CL*(WT\/70)^0.75",".name":"GRPCL"},{".expr":"POP_V*WT\/70",".name":"GRPV"},{".expr":"POP_KA",".name":"GRPKA"},{".expr":"POP_TLAG",".name":"GRPLG"}]} /
    private final static String modelOutputVariablesBlockJson_UseCase10 =
        / {"MODEL_OUTPUT_VARIABLES":[{".name":"ID"},{".name":"TIME"},{".name":"WT"},{".name":"LOGTWT"},{".name":"CL"},{".name":"VC"},{".name":"Q"},{".name":"VP"},{".name":"KA"},{".name":"TLAG"},{".name":"DVID"},{".name":"MDV"},{".name":"Y"}]} /
    private final static String observationBlockJson_UseCase11 =
        / {"OBSERVATION":[{"link":"log",".name":"Y","type":"count","distn":"~Poisson(lambda=LAMBDA)"}]} /
    private final static String Friberg_2009_Schizophrenia_Asenapine_PANSS_HM_20150520_Prod4__Json =
        / {"MODEL_PREDICTION":[{".expr":"PMAX*(1-exp(-(TIME\/TP)^POW))",".name":"PMOD"},{".expr":"1 when TIME>42 otherwise TIME\/42",".name":"FT"},{".expr":"EMAX*AUC\/(AUC50+AUC)*FT",".name":"EFF"},{".expr":"EFF when (TIME>0 && AUC>0) otherwise 0",".name":"EMOD"},{".expr":"PAN0*(1-PMOD)*(1-EMOD)",".name":"PANSS_total"}]} /
    
    @Test
    public void testIndependentVariablesBlock() {

        def json = getJson(independentVariablesBlockJson_UseCase16)

        def modelObj = new Model(json)

        String expected = """mdlobj {

    IDV {
        T
    }

}
"""
        assertEquals(expected, modelObj.toMDL())
    }

    @Test
    public void testCovariatesBlock_ContainingVariableBeingExpression() {

        def json = getJson(covariatesBlockJson_UseCase2)

        def modelObj = new Model(json)

        String expected = """mdlobj {

    COVARIATES {
        WT
        logtWT = log(WT/70)
    }

}
"""
        assertEquals(expected, modelObj.toMDL())
    }

    /**
     * Testing variables like:
     * SEX : {type=categorical(female, male, MISSING)}
     */
    @Test
    public void testCovariatesBlock_ContainingVariableBeingSetOfAttributes() {

        def json = getJson(covariatesBlockJson_UseCase5)

        def modelObj = new Model(json)

        String expected = """mdlobj {

    COVARIATES {
        WT
        AGE
        SEX : {type=categorical(female, male, MISSING)}
        logtWT = ln(WT/70)
        tAGE = AGE-40
        tSEX = 1 when (SEX==female) otherwise 0
    }

}
"""
        assertEquals(expected, modelObj.toMDL())
    }

    @Test
    public void testVariabilityLevelsBlock() {

        def json = getJson(variabilityLevelsBlockJson_UseCase1)

        def modelObj = new Model(json)

        String expected = """mdlobj {

    VARIABILITY_LEVELS {
        ID : {level=2, type=parameter}
        DV : {level=1, type=observation}
    }

}
"""
        assertEquals(expected, modelObj.toMDL())
    }

    @Test
    public void testStructuralParametersBlock_BeingListOfVariableNames() {

        def json = getJson(structuralParametersBlockJson_UseCase1)

        def modelObj = new Model(json)

        String expected = """mdlobj {

    STRUCTURAL_PARAMETERS {
        POP_CL
        POP_V
        POP_KA
        POP_TLAG
        BETA_CL_WT
        BETA_V_WT
        RUV_PROP
        RUV_ADD
    }

}
"""
        assertEquals(expected, modelObj.toMDL())
    }
    
    /**
     * Testing variables like:
     * POP_CL : {units="L/h/kg*70"}
     */
    @Test
    public void testStructuralParametersBlock_ContainingVariablesBeingSetsOfAttributes() {

        def json = getJson(structuralParametersBlockJson_UseCase10)

        def modelObj = new Model(json)
        
        String expected = """mdlobj {

    STRUCTURAL_PARAMETERS {
        POP_CL : {units="L/h/kg*70"}
        POP_VC : {units="L/kg*70"}
        POP_Q : {units="L/h/kg*70"}
        POP_VP : {units="L/kg*70"}
        POP_KA : {units="1/h"}
        POP_TLAG : {units="h"}
        POP_BETA_CL_WT
        POP_BETA_V_WT
        RUV_PROP
        RUV_ADD : {units="mg/L"}
    }

}
"""
        assertEquals(expected, modelObj.toMDL())
    }

    @Test
    public void testVariabilityParametersBlock_BeingListOfVariableNames() {
        def json = getJson(variabilityParametersBlockJson_UseCase1)

        def modelObj = new Model(json)

        String expected = """mdlobj {

    VARIABILITY_PARAMETERS {
        PPV_CL
        PPV_V
        PPV_KA
        PPV_TLAG
    }

}
"""
        assertEquals(expected, modelObj.toMDL())
    }

    /**
     * Testing attributes like:
     * params=[ETA_CL, ETA_V]
     */
    @Test
    public void testVariabilityParametersBlock_ContainingVariableHavingAttributeBeingListOfVariableNames() {
        def json = getJson(variabilityParametersBlockJson_UseCase20)

        def modelObj = new Model(json)

        String expected = """mdlobj {

    VARIABILITY_PARAMETERS {
        PPV_CL
        PPV_V
        PPV_KA
        PPV_TLAG
        RUV_PROP
        RUV_ADD
        OMEGA : {params=[ETA_CL, ETA_V], type=CORR}
    }

}
"""
        assertEquals(expected, modelObj.toMDL())
    }

    @Test
    public void testRandomVariableDefinitionBlocks_1() {

        def json = getJson(randomVarDefinitionBlocksJson_UseCase1)

        def modelObj = new Model(json)
        
        String expected = """mdlobj {

    RANDOM_VARIABLE_DEFINITION(level=ID) {
        ETA_CL ~ Normal(mean=0, sd=PPV_CL)
        ETA_V ~ Normal(mean=0, sd=PPV_V)
        ETA_KA ~ Normal(mean=0, sd=PPV_KA)
        ETA_TLAG ~ Normal(mean=0, sd=PPV_TLAG)
    }

    RANDOM_VARIABLE_DEFINITION(level=DV) {
        EPS_Y ~ Normal(mean=0, var=1)
    }

}
"""
        assertEquals(expected, modelObj.toMDL())
    }
    
    @Test
    public void testRandomVariableDefinitionBlocks_2() {

        def json = getJson(randomVarDefinitionBlocksJson_UseCase8)

        def modelObj = new Model(json)
        
        String expected = """mdlobj {

    RANDOM_VARIABLE_DEFINITION(level=ID) {
        eta_BSV_CL ~ Normal(mean=0, var=BSV_CL)
        eta_BSV_V ~ Normal(mean=0, var=BSV_V)
        eta_BSV_KA ~ Normal(mean=0, var=BSV_KA)
        eta_BSV_TLAG ~ Normal(mean=0, var=BSV_TLAG)
    }

    RANDOM_VARIABLE_DEFINITION(level=OCC) {
        eta_BOV_CL ~ Normal(mean=0, var=BOV_CL)
        eta_BOV_V ~ Normal(mean=0, var=BOV_V)
        eta_BOV_KA ~ Normal(mean=0, var=BOV_KA)
        eta_BOV_TLAG ~ Normal(mean=0, var=BOV_TLAG)
    }

    RANDOM_VARIABLE_DEFINITION(level=DV) {
        EPS_Y ~ Normal(mean=0, var=1)
    }

}
"""
        assertEquals(expected, modelObj.toMDL())
    }

    /**
     * Testing attributes like:
     * fixEff={coeff=BETA_CL_WT, cov=logtWT}
     */
    @Test
    public void testIndividualVariablesBlock_ContainingVariableHavingAttributeBeingSetOfNameValuePairs() {
        def json = getJson(individualVarsBlockJson_UseCase1)

        def modelObj = new Model(json)

        String expected = """mdlobj {

    INDIVIDUAL_VARIABLES {
        CL : {fixEff={coeff=BETA_CL_WT, cov=logtWT}, pop=POP_CL, ranEff=ETA_CL, trans=log, type=linear}
        V : {fixEff={coeff=BETA_V_WT, cov=logtWT}, pop=POP_V, ranEff=ETA_V, trans=log, type=linear}
        KA : {pop=POP_KA, ranEff=ETA_KA, trans=log, type=linear}
        TLAG : {pop=POP_TLAG, ranEff=ETA_TLAG, trans=log, type=linear}
    }

}
"""
        assertEquals(expected, modelObj.toMDL())
    }

    /**
     * Testing variables like:
     * TVSLP = POP_TVSLP/1000
     * and variables like:
     * BM0 : {pop=POP_BM0, ranEff=eta_BM0, trans=log, type=linear}
     */
    @Test
    public void testIndividualVariablesBlock_MixtureOfVariablesBeingExpressionsAndVariablesBeingSetsOfAttributes() {
        def json = getJson(individualVarsBlockJson_UseCase16)

        def modelObj = new Model(json)

        String expected = """mdlobj {

    INDIVIDUAL_VARIABLES {
        BM0 : {pop=POP_BM0, ranEff=eta_BM0, trans=log, type=linear}
        BM02 : {pop=POP_BM02, ranEff=eta_BM02, trans=log, type=linear}
        BM03 : {pop=POP_BM03, ranEff=eta_BM03, trans=log, type=linear}
        BM0S : {pop=POP_BM0S, ranEff=eta_BM0S, trans=log, type=linear}
        IMAX1 = POP_IMAX
        IMAX2 = POP_IMAX
        IMAX3 = POP_IMAX
        IMAXS = POP_IMAX
        IC50 : {pop=POP_IC50, ranEff=eta_IC50, trans=log, type=linear}
        IC502 : {pop=POP_IC50, ranEff=eta_IC502, trans=log, type=linear}
        IC503 : {pop=POP_IC50, ranEff=eta_IC503, trans=log, type=linear}
        IC50S : {pop=POP_IC50, ranEff=eta_IC50S, trans=log, type=linear}
        HILL = POP_HILL
        HILL2 = POP_HILL2
        MRT1 : {pop=POP_MRT, ranEff=eta_MRT_VEGFs, trans=log, type=linear}
        MRT2 : {pop=POP_MRT2, ranEff=eta_MRT_VEGFs, trans=log, type=linear}
        MRT3 : {pop=POP_MRT3, ranEff=eta_MRT_VEGFs, trans=log, type=linear}
        MRTS : {pop=POP_MRTS, ranEff=eta_MRT_sKIT, trans=log, type=linear}
        TVSLP = POP_TVSLP/1000
        DPSLP : {pop=TVSLP, ranEff=eta_TVSLP, trans=log, type=linear}
        TVSLPS = POP_TVSLP/1000
        DPSLPS : {pop=TVSLPS, ranEff=eta_TVSLPS, trans=log, type=linear}
        KOUT = 1/MRT1
        KOUT2 = 1/MRT2
        KOUT3 = 1/MRT3
        KOUTS = 1/MRTS
        KIN2 = BM02*KOUT2
        KIN3 = BM03*KOUT3
    }

}
"""
        assertEquals(expected, modelObj.toMDL())
    }

    /**
     * Testing attributes like:
     * ranEff=[eta_BSV_CL, eta_BOV_CL]
     */
    @Test
    public void testIndividualVariablesBlock_ContainingVariableHavingAttributeBeingListOfVariableNames() {
        def json = getJson(individualVarsBlockJson_UseCase8)

        def modelObj = new Model(json)

        String expected = """mdlobj {

    INDIVIDUAL_VARIABLES {
        CL : {fixEff={coeff=BETA_CL_WT, cov=logtWT}, pop=POP_CL, ranEff=[eta_BSV_CL, eta_BOV_CL], trans=log, type=linear}
        V : {fixEff={coeff=BETA_V_WT, cov=logtWT}, pop=POP_V, ranEff=[eta_BSV_V, eta_BOV_V], trans=log, type=linear}
        KA : {pop=POP_KA, ranEff=[eta_BSV_KA, eta_BOV_KA], trans=log, type=linear}
        TLAG : {pop=POP_TLAG, ranEff=[eta_BSV_TLAG, eta_BOV_TLAG], trans=log, type=linear}
    }

}
"""
        assertEquals(expected, modelObj.toMDL())
    }

    /**
     * Testing attributes like:
     * fixEff=[{coeff=BETA_CL_WT, cov=logtWT}, ...]
     */
    @Test
    public void testIndividualVariablesBlock_ContainingVariableHavingAttributeBeingListOfSetsOfNameValuePairs() {
        def json = getJson(individualVarsBlockJson_UseCase5)

        def modelObj = new Model(json)

        String expected = """mdlobj {

    INDIVIDUAL_VARIABLES {
        CL : {fixEff=[{coeff=BETA_CL_WT, cov=logtWT}, {coeff=POP_FCL_FEM, cov=tSEX}, {coeff=BETA_CL_AGE, cov=tAGE}], pop=POP_CL, ranEff=ETA_CL, trans=log, type=linear}
        V : {fixEff={coeff=BETA_V_WT, cov=logtWT}, pop=POP_V, ranEff=ETA_V, trans=log, type=linear}
        KA : {pop=POP_KA, ranEff=ETA_KA, trans=log, type=linear}
        TLAG : {pop=POP_TLAG, ranEff=ETA_TLAG, trans=log, type=linear}
    }

}
"""
        assertEquals(expected, modelObj.toMDL())
    }

    /**
     * Testing attributes like:
     * error=combinedError2log(additive=0, proportional=POP_RES_VEGF_PROP, f=VEGF)
     */
    @Test
    public void testObservationBlock_ContainingVariableHavingErrorFunctionAttribute() {
        def json = getJson(observationBlockJson_UseCase16)

        def modelObj = new Model(json)

        String expected = """mdlobj {

    OBSERVATION {
        LNVEGF_obs : {eps=eps_RES_W, error=combinedError2log(additive=0, proportional=POP_RES_VEGF_PROP, f=VEGF), prediction=VEGF, type=continuous}
        LNsVEGFR2_obs : {eps=eps_RES_W, error=combinedError2log(additive=POP_RES_sVEGFR2_ADD, proportional=POP_RES_sVEGFR2_PROP, f=sVEGFR2), prediction=sVEGFR2, type=continuous}
        LNsVEGFR3_obs : {eps=eps_RES_W, error=combinedError2log(additive=0, proportional=POP_RES_sVEGFR3_PROP, f=sVEGFR3), prediction=sVEGFR3, type=continuous}
        LNsKIT_obs : {eps=eps_RES_W, error=combinedError2log(additive=0, proportional=POP_RES_sKIT_PROP, f=sKIT), prediction=sKIT, type=continuous}
    }

}
"""
        assertEquals(expected, modelObj.toMDL())
    }

    @Test
    public void testObservationBlock_ContainingVariableBeingExpression() {
        def json = getJson(observationBlockJson_UseCase19)

        def modelObj = new Model(json)

        String expected = """mdlobj {

    OBSERVATION {
        Y = CC*(1+eps_RUV_PROP)+eps_RUV_ADD
    }

}
"""
        assertEquals(expected, modelObj.toMDL())
    }

    @Test
    public void testModelPredictionBlock_ContainingVariablesBeingExpressions() {
        def json = getJson(modelPredictionBlockJson_UseCase2)

        def modelObj = new Model(json)

        String expected = """mdlobj {

    MODEL_PREDICTION {
        D
        DT
        k = CL/V
        CC = 0 when T-DT<TLAG otherwise (D/V)*(KA/(KA-k)*(exp(-k*(T-DT-TLAG)-exp(-KA*(T-DT-TLAG)))))
    }

}
"""
        assertEquals(expected, modelObj.toMDL())
    }

    @Test
    public void testModelPredictionBlockWithDeqSubBlock() {
        def json = getJson(modelPredictionBlockJson_UseCase16)

        def modelObj = new Model(json)

        String expected = """mdlobj {

    MODEL_PREDICTION {
        DOSE
        AUC = DOSE/CL
        DP1 = BM0*(1+DPSLP*T)
        DPS = BM0S*(1+DPSLPS*T)
        KIN = DP1*KOUT
        KINS = DPS*KOUTS
        DEQ {
            EFF = IMAX1*AUC^HILL/(IC50^HILL+AUC^HILL)
            EFF2 = IMAX2*AUC^HILL2/(IC502^HILL2+AUC^HILL2)
            EFF3 = IMAX3*AUC/(IC503+AUC)
            EFFS = IMAXS*AUC/(IC50S+AUC)
            VEGF : {deriv=KIN-KOUT*(1-EFF)*VEGF, init=BM0}
            sVEGFR2 : {deriv=KIN2*(1-EFF2)-KOUT2*sVEGFR2, init=BM02, wrt=T}
            sVEGFR3 : {deriv=KIN3*(1-EFF3)-KOUT3*sVEGFR3, init=BM03, wrt=T}
            sKIT : {deriv=KINS*(1-EFFS)-KOUTS*sKIT, init=BM0S, wrt=T}
        }
        LNVEGF = ln(VEGF)
    }

}
"""
        assertEquals(expected, modelObj.toMDL())
    }

    @Test
    public void testModelPredictionBlockWithCompartmentSubBlock() {
        def json = getJson(modelPredictionBlockCompartmentJson_UseCase10)
        
        def modelObj = new Model(json)
                
        String expected = """mdlobj {

    MODEL_PREDICTION {
        COMPARTMENT {
            INPUT_KA : {ka=KA, modelCmt=1, tlag=ALAG1, to=CENTRAL, type=depot}
            CENTRAL : {modelCmt=2, type=compartment}
            {cl=CL, from=CENTRAL, modelCmt=2, type=elimination, v=V2}
            PERIPHERAL : {from=CENTRAL, kin=Q/V2, kout=Q/V3, modelCmt=3, type=distribution}
        }
        F = CENTRAL/S2
        CC = F
    }

}
"""
        assertEquals(expected, modelObj.toMDL())
    }
    
    @Test
    public void testModelPredictionBlockWithPkMacroSubBlock() {
        def json = getJson(modelPredictionBlockPkMacroJson_UseCase22)

        def modelObj = new Model(json)
        
        String expected = """mdlobj {

    MODEL_PREDICTION {
        PKMACRO {
            DEP
            {ka=KA, macro=oral, tlag=ALAG1, to=Ac}
            Ac : {macro=compartment, volume=V}
            {cl=CL, from=Ac, macro=elimination}
        }
        CONC = Ac/V
    }

}
"""
        assertEquals(expected, modelObj.toMDL())
    }

    @Test
    public void testGroupVariablesBlock_ContainingVariablesBeingExpressions() {
        def json = getJson(groupVariablesBlockJson_UseCase19)

        def modelObj = new Model(json)

        String expected = """mdlobj {

    GROUP_VARIABLES {
        GRPCL = POP_CL*(WT/70)^0.75
        GRPV = POP_V*WT/70
        GRPKA = POP_KA
        GRPLG = POP_TLAG
    }

}
"""
        assertEquals(expected, modelObj.toMDL())
    }
    
    @Test
    public void testModelOutputVariablesBlock() {
        def json = getJson(modelOutputVariablesBlockJson_UseCase10)
        
        def modelObj = new Model(json)
        
        String expected = """mdlobj {

    MODEL_OUTPUT_VARIABLES {
        ID
        TIME
        WT
        LOGTWT
        CL
        VC
        Q
        VP
        KA
        TLAG
        DVID
        MDV
        Y
    }

}
"""
        assertEquals(expected, modelObj.toMDL())
    }
    
    @Test
    public void testVariableHavingDistributionAsAttribute() {
        def json = getJson(observationBlockJson_UseCase11)
        
        def modelObj = new Model(json)
        
        String expected = """mdlobj {

    OBSERVATION {
        Y : {distn=~Poisson(lambda=LAMBDA), link=log, type=count}
    }

}
"""
        assertEquals(expected, modelObj.toMDL())        
    }

    /**
     * This test was added to expose the bug reported by DDMORE-1250 re AndExpressions not being handled.
     */
    @Test
    public void testVariableBeingExpressionContainingAnAndExpression() {
        def json = getJson(Friberg_2009_Schizophrenia_Asenapine_PANSS_HM_20150520_Prod4__Json)
        
        def modelObj = new Model(json)
        
        String expected = """mdlobj {

    MODEL_PREDICTION {
        PMOD = PMAX*(1-exp(-(TIME/TP)^POW))
        FT = 1 when TIME>42 otherwise TIME/42
        EFF = EMAX*AUC/(AUC50+AUC)*FT
        EMOD = EFF when (TIME>0 && AUC>0) otherwise 0
        PANSS_total = PAN0*(1-PMOD)*(1-EMOD)
    }

}
"""
        assertEquals(expected, modelObj.toMDL())
    }

}
