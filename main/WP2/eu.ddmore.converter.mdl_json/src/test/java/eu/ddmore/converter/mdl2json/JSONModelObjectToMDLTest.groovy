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
    private final static String independentVariablesBlockJson =
        / {"IDV":[{".name":"T"}]} /
    private final static String covariatesBlockJson_WarfarinAnalyticSolution =
        / {"COVARIATES":[{".name":"WT"},{".expr":"log(WT\/70)",".name":"logtWT"}]} /
    private final static String covariatesBlockJson_WarfarinPkSexage =
        / {"COVARIATES":[{".name":"WT"},{".name":"AGE"},{".expr":"AGE-40",".name":"tAGE"},{".expr":"log(WT\/70)",".name":"logtWT"},{".name":"SEX","type":"categorical(female, male, MISSING)"}]} /
    private final static String variabilityLevelsBlockJson =
        / {"VARIABILITY_LEVELS":[{"level":"2",".name":"ID","type":"parameter"},{"level":"1",".name":"DV","type":"observation"}]} /
    private final static String structuralParametersBlockJson =
        / {"STRUCTURAL_PARAMETERS":[{".name":"POP_CL"},{".name":"POP_V"},{".name":"POP_KA"},{".name":"POP_TLAG"},{".name":"BETA_CL_WT"},{".name":"BETA_V_WT"},{".name":"RUV_PROP"},{".name":"RUV_ADD"}]} /
    private final static String variabilityParametersBlockJson_Warfarin =
        / {"VARIABILITY_PARAMETERS":[{".name":"PPV_CL"},{".name":"PPV_V"},{".name":"PPV_KA"},{".name":"PPV_TLAG"}]} /
    private final static String variabilityParametersBlockJson_WarfarinPkSim =
        / {"VARIABILITY_PARAMETERS":[{".name":"PPV_CL"},{".name":"PPV_V"},{".name":"PPV_KA"},{".name":"PPV_TLAG"},{".name":"RUV_PROP"},{".name":"RUV_ADD"},{"params":"[ETA_CL, ETA_V]",".name":"OMEGA","type":"CORR"}]} /
    private final static String randomVarDefinitionBlocksJson_Warfarin =
        / {"RANDOM_VARIABLE_DEFINITION":[{".random_var_distribution":"Normal",".random_var_attrs":{"mean":"0","sd":"PPV_CL"},"level":"ID",".name":"ETA_CL"},{".random_var_distribution":"Normal",".random_var_attrs":{"mean":"0","sd":"PPV_V"},"level":"ID",".name":"ETA_V"},{".random_var_distribution":"Normal",".random_var_attrs":{"mean":"0","sd":"PPV_KA"},"level":"ID",".name":"ETA_KA"},{".random_var_distribution":"Normal",".random_var_attrs":{"mean":"0","sd":"PPV_TLAG"},"level":"ID",".name":"ETA_TLAG"},{".random_var_distribution":"Normal",".random_var_attrs":{"mean":"0","var":"1"},"level":"DV",".name":"EPS_Y"}]} /
    private final static String randomVarDefinitionBlocksJson_WarfarinPkBov =
        / {"RANDOM_VARIABLE_DEFINITION":[{".random_var_distribution":"Normal",".random_var_attrs":{"mean":"0","var":"BSV_CL"},"level":"ID",".name":"eta_BSV_CL"},{".random_var_distribution":"Normal",".random_var_attrs":{"mean":"0","var":"BSV_V"},"level":"ID",".name":"eta_BSV_V"},{".random_var_distribution":"Normal",".random_var_attrs":{"mean":"0","var":"BSV_KA"},"level":"ID",".name":"eta_BSV_KA"},{".random_var_distribution":"Normal",".random_var_attrs":{"mean":"0","var":"BSV_TLAG"},"level":"ID",".name":"eta_BSV_TLAG"},{".random_var_distribution":"Normal",".random_var_attrs":{"mean":"0","var":"BOV_CL"},"level":"OCC",".name":"eta_BOV_CL"},{".random_var_distribution":"Normal",".random_var_attrs":{"mean":"0","var":"BOV_V"},"level":"OCC",".name":"eta_BOV_V"},{".random_var_distribution":"Normal",".random_var_attrs":{"mean":"0","var":"BOV_KA"},"level":"OCC",".name":"eta_BOV_KA"},{".random_var_distribution":"Normal",".random_var_attrs":{"mean":"0","var":"BOV_TLAG"},"level":"OCC",".name":"eta_BOV_TLAG"},{".random_var_distribution":"Normal",".random_var_attrs":{"mean":"0","var":"1"},"level":"DV",".name":"EPS_Y"}]} /
    private final static String individualVarsBlockJson_Warfarin =
        / {"INDIVIDUAL_VARIABLES":[{"fixEff":"{coeff=BETA_CL_WT, cov=logtWT}","trans":"log","ranEff":"ETA_CL","pop":"POP_CL",".name":"CL","type":"linear"},{"fixEff":"{coeff=BETA_V_WT, cov=logtWT}","trans":"log","ranEff":"ETA_V","pop":"POP_V",".name":"V","type":"linear"},{"trans":"log","ranEff":"ETA_KA","pop":"POP_KA",".name":"KA","type":"linear"},{"trans":"log","ranEff":"ETA_TLAG","pop":"POP_TLAG",".name":"TLAG","type":"linear"}]} /
    private final static String individualVarsBlockJson_Hansson =
        / {"INDIVIDUAL_VARIABLES":[{"trans":"log","ranEff":"eta_BM0","pop":"POP_BM0",".name":"BM0","type":"linear"},{"trans":"log","ranEff":"eta_BM02","pop":"POP_BM02",".name":"BM02","type":"linear"},{"trans":"log","ranEff":"eta_BM03","pop":"POP_BM03",".name":"BM03","type":"linear"},{"trans":"log","ranEff":"eta_BM0S","pop":"POP_BM0S",".name":"BM0S","type":"linear"},{".expr":"POP_IMAX",".name":"IMAX1"},{".expr":"POP_IMAX",".name":"IMAX2"},{".expr":"POP_IMAX",".name":"IMAX3"},{".expr":"POP_IMAX",".name":"IMAXS"},{"trans":"log","ranEff":"eta_IC50","pop":"POP_IC50",".name":"IC50","type":"linear"},{"trans":"log","ranEff":"eta_IC502","pop":"POP_IC50",".name":"IC502","type":"linear"},{"trans":"log","ranEff":"eta_IC503","pop":"POP_IC50",".name":"IC503","type":"linear"},{"trans":"log","ranEff":"eta_IC50S","pop":"POP_IC50",".name":"IC50S","type":"linear"},{".expr":"POP_HILL",".name":"HILL"},{".expr":"POP_HILL2",".name":"HILL2"},{"trans":"log","ranEff":"eta_MRT_VEGFs","pop":"POP_MRT",".name":"MRT1","type":"linear"},{"trans":"log","ranEff":"eta_MRT_VEGFs","pop":"POP_MRT2",".name":"MRT2","type":"linear"},{"trans":"log","ranEff":"eta_MRT_VEGFs","pop":"POP_MRT3",".name":"MRT3","type":"linear"},{"trans":"log","ranEff":"eta_MRT_sKIT","pop":"POP_MRTS",".name":"MRTS","type":"linear"},{".expr":"POP_TVSLP\/1000",".name":"TVSLP"},{"trans":"log","ranEff":"eta_TVSLP","pop":"TVSLP",".name":"DPSLP","type":"linear"},{".expr":"POP_TVSLP\/1000",".name":"TVSLPS"},{"trans":"log","ranEff":"eta_TVSLPS","pop":"TVSLPS",".name":"DPSLPS","type":"linear"},{".expr":"1\/MRT1",".name":"KOUT"},{".expr":"1\/MRT2",".name":"KOUT2"},{".expr":"1\/MRT3",".name":"KOUT3"},{".expr":"1\/MRTS",".name":"KOUTS"},{".expr":"BM02*KOUT2",".name":"KIN2"},{".expr":"BM03*KOUT3",".name":"KIN3"}]} /
    private final static String individualVarsBlockJson_WarfarinPkBov =
        / {"INDIVIDUAL_VARIABLES":[{"fixEff":"{coeff=BETA_CL_WT, cov=logtWT}","trans":"log","ranEff":"[eta_BSV_CL, eta_BOV_CL]","pop":"POP_CL",".name":"CL","type":"linear"},{"fixEff":"{coeff=BETA_V_WT, cov=logtWT}","trans":"log","ranEff":"[eta_BSV_V, eta_BOV_V]","pop":"POP_V",".name":"V","type":"linear"},{"trans":"log","ranEff":"[eta_BSV_KA, eta_BOV_KA]","pop":"POP_KA",".name":"KA","type":"linear"},{"trans":"log","ranEff":"[eta_BSV_TLAG, eta_BOV_TLAG]","pop":"POP_TLAG",".name":"TLAG","type":"linear"}]} /
    private final static String individualVarsBlockJson_CategoricalDIST =
        / {"INDIVIDUAL_VARIABLES":[{".expr":"B0+EDRUG+eta_PPV_EVENT",".name":"A0"},{".expr":"B1+EDRUG+eta_PPV_EVENT",".name":"A1"},{".expr":"B2+EDRUG+eta_PPV_EVENT",".name":"A2"}]} /
    private final static String individualVarsBlockJson_WarfarinPkSexage =
        / {"INDIVIDUAL_VARIABLES":[{"fixEff":"[{coeff=BETA_CL_WT, cov=logtWT}, {coeff=POP_FCL_FEM, cov=FCLSEX}, {coeff=BETA_CL_AGE, cov=tAGE}]","trans":"log","ranEff":"ETA_CL","pop":"POP_CL",".name":"CL","type":"linear"},{"fixEff":"{coeff=BETA_V_WT, cov=logtWT}","trans":"log","ranEff":"ETA_V","pop":"POP_V",".name":"V","type":"linear"},{"trans":"log","ranEff":"ETA_KA","pop":"POP_KA",".name":"KA","type":"linear"},{"trans":"log","ranEff":"ETA_TLAG","pop":"POP_TLAG",".name":"TLAG","type":"linear"}]} /
    private final static String observationBlockJson_Hansson =
        / {"OBSERVATION":[{"eps":"eps_RES_W","trans":"log","error":"additiveError(additive=POP_RES_VEGF_ADD)","prediction":"VEGF",".name":"VEGF_obs","type":"continuous"},{"eps":"eps_RES_W","trans":"log","error":"combinedError2(additive=POP_RES_sVEGFR2_ADD, proportional=POP_RES_sVEGFR2_PROP, f=sVEGFR2)","prediction":"sVEGFR2",".name":"sVEGFR2_obs","type":"continuous"},{"eps":"eps_RES_W","trans":"log","error":"additiveError(additive=POP_RES_sVEGFR3_ADD)","prediction":"sVEGFR3",".name":"sVEGFR3_obs","type":"continuous"},{"eps":"eps_RES_W","trans":"log","error":"additiveError(additive=POP_RES_sKIT_ADD)","prediction":"sKIT",".name":"sKIT_obs","type":"continuous"}]} /
    private final static String observationBlockJson_WarfarinPkBovOAM =
        / {"OBSERVATION":[{".expr":"CC*(1+eps_RUV_PROP)+eps_RUV_ADD",".name":"Y"}]} /
    private final static String observationBlockJson_CategoricalDIST =
        / {"OBSERVATION":[{"categories":"[0, 1, 2, 3]","probabilities":"[Prob0, Prob1, Prob2, Prob3]",".name":"Y","type":"categorical"}]} /
    private final static String modelPredictionBlockJson_WarfarinAnalyticSolution =
        / {"MODEL_PREDICTION":[{".name":"D"},{".name":"DT"},{".expr":"CL\/V",".name":"k"},{".expr":"0 when T-DT<TLAG otherwise (D\/V)*(KA\/(KA-k)*(exp(-k*(T-DT-TLAG)-exp(-KA*(T-DT-TLAG)))))",".name":"CC"}]} /
    private final static String modelPredictionBlockJson_Hansson =
        / {"MODEL_PREDICTION":[{".expr":"DOSE\/CL",".name":"AUC"},{".expr":"BM0*(1+DPSLP*T)",".name":"DP1"},{".expr":"BM0S*(1+DPSLPS*T)",".name":"DPS"},{".expr":"DP1*KOUT",".name":"KIN"},{".expr":"DPS*KOUTS",".name":"KINS"},{".DEQ":[{".expr":"IMAX1*AUC^HILL\/(IC50^HILL+AUC^HILL)",".name":"EFF"},{".expr":"IMAX2*AUC^HILL2\/(IC502^HILL2+AUC^HILL2)",".name":"EFF2"},{".expr":"IMAX3*AUC\/(IC503+AUC)",".name":"EFF3"},{".expr":"IMAXS*AUC\/(IC50S+AUC)",".name":"EFFS"},{"wrt":"T","deriv":"KIN-KOUT*(1-EFF)*VEGF","init":"BM0",".name":"VEGF"},{"wrt":"T","deriv":"KIN2*(1-EFF2)-KOUT2*sVEGFR2","init":"BM02",".name":"sVEGFR2"},{"wrt":"T","deriv":"KIN3*(1-EFF3)-KOUT3*sVEGFR3","init":"BM03",".name":"sVEGFR3"},{"wrt":"T","deriv":"KINS*(1-EFFS)-KOUTS*sKIT","init":"BM0S",".name":"sKIT"}]}]} /
    private final static String modelPredictionBlockPkMacroJson =
        / {"MODEL_PREDICTION":[{".PKMACRO":[{"to":"CENTRAL","macro":"iv",".name":"DEP1"},{"macro":"compartment","volume":"V",".name":"CENTRAL"},{"macro":"elimination","cl":"CL","from":"CENTRAL"},{"to":"LATENT","macro":"oral","p":"F1","ka":"KA",".name":"GUT"},{"macro":"compartment","volume":"1",".name":"LATENT"},{"to":"CENTRAL","macro":"transfer","kt":"KT","from":"LATENT"},{"macro":"elimination","from":"LATENT","k":"K1"}]},{".expr":"V",".name":"SCALE"},{".expr":"CENTRAL\/V",".name":"CC"}]} /
    private final static String groupVariablesBlockJson_WarfarinPkBovOAM =
        / {"GROUP_VARIABLES":[{".expr":"POP_CL*(WT\/70)^0.75",".name":"GRPCL"},{".expr":"POP_V*WT\/70",".name":"GRPV"},{".expr":"POP_KA",".name":"GRPKA"},{".expr":"POP_TLAG",".name":"GRPLG"}]} /
    private final static String groupVariablesBlockJson_WarfarinPkSexage =
        / {"GROUP_VARIABLES":[{".expr":"1 when SEX==female otherwise 0",".name":"FCLSEX"}]} /
    private final static String modelOutputVariablesBlockJson =
        / {"MODEL_OUTPUT_VARIABLES":[{".name":"ID"},{".name":"TIME"},{".name":"WT"},{".name":"LOGTWT"},{".name":"CL"},{".name":"VC"},{".name":"Q"},{".name":"VP"},{".name":"KA"},{".name":"TLAG"},{".name":"DVID"},{".name":"MDV"},{".name":"Y"}]} /

    @Test
    public void testIndependentVariablesBlock() {

        def json = getJson(independentVariablesBlockJson)

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
    public void testCovariatesBlock_WarfarinAnalyticSolution() {

        def json = getJson(covariatesBlockJson_WarfarinAnalyticSolution)

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

    @Test
    public void testCovariatesBlock_WarfarinPkSexage() {

        def json = getJson(covariatesBlockJson_WarfarinPkSexage)

        def modelObj = new Model(json)

        String expected = """mdlobj {

    COVARIATES {
        WT
        AGE
        tAGE = AGE-40
        logtWT = log(WT/70)
        SEX : {type=categorical(female, male, MISSING)}
    }

}
"""
        assertEquals(expected, modelObj.toMDL())
    }

    @Test
    public void testVariabilityLevelsBlock() {

        def json = getJson(variabilityLevelsBlockJson)

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
    public void testStructuralParametersBlock() {

        def json = getJson(structuralParametersBlockJson)

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

    @Test
    public void testVariabilityParametersBlock_Warfarin() {
        def json = getJson(variabilityParametersBlockJson_Warfarin)

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

    @Test
    public void testVariabilityParametersBlock_WarfarinPkSim() {
        def json = getJson(variabilityParametersBlockJson_WarfarinPkSim)

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
    public void testRandomVariableDefinitionBlocks_Warfarin() {

        def json = getJson(randomVarDefinitionBlocksJson_Warfarin)

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
    public void testRandomVariableDefinitionBlocks_WarfarinPkBov() {

        def json = getJson(randomVarDefinitionBlocksJson_WarfarinPkBov)

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

    @Test
    public void testIndividualVariablesBlock_Warfarin() {
        def json = getJson(individualVarsBlockJson_Warfarin)

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

    @Test
    public void testIndividualVariablesBlock_Hansson() {
        def json = getJson(individualVarsBlockJson_Hansson)

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

    @Test
    public void testIndividualVariablesBlock_WarfarinPkBov() {
        def json = getJson(individualVarsBlockJson_WarfarinPkBov)

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

    @Test
    public void testIndividualVariablesBlock_CategoricalDIST() {
        def json = getJson(individualVarsBlockJson_CategoricalDIST)

        def modelObj = new Model(json)

        String expected = """mdlobj {

    INDIVIDUAL_VARIABLES {
        A0 = B0+EDRUG+eta_PPV_EVENT
        A1 = B1+EDRUG+eta_PPV_EVENT
        A2 = B2+EDRUG+eta_PPV_EVENT
    }

}
"""
        assertEquals(expected, modelObj.toMDL())
    }

    @Test
    public void testIndividualVariablesBlock_WarfarinPkSexage() {
        def json = getJson(individualVarsBlockJson_WarfarinPkSexage)

        def modelObj = new Model(json)

        String expected = """mdlobj {

    INDIVIDUAL_VARIABLES {
        CL : {fixEff=[{coeff=BETA_CL_WT, cov=logtWT}, {coeff=POP_FCL_FEM, cov=FCLSEX}, {coeff=BETA_CL_AGE, cov=tAGE}], pop=POP_CL, ranEff=ETA_CL, trans=log, type=linear}
        V : {fixEff={coeff=BETA_V_WT, cov=logtWT}, pop=POP_V, ranEff=ETA_V, trans=log, type=linear}
        KA : {pop=POP_KA, ranEff=ETA_KA, trans=log, type=linear}
        TLAG : {pop=POP_TLAG, ranEff=ETA_TLAG, trans=log, type=linear}
    }

}
"""
        assertEquals(expected, modelObj.toMDL())
    }

    @Test
    public void testObservationBlock_Hansson() {
        def json = getJson(observationBlockJson_Hansson)

        def modelObj = new Model(json)

        String expected = """mdlobj {

    OBSERVATION {
        VEGF_obs : {eps=eps_RES_W, error=additiveError(additive=POP_RES_VEGF_ADD), prediction=VEGF, trans=log, type=continuous}
        sVEGFR2_obs : {eps=eps_RES_W, error=combinedError2(additive=POP_RES_sVEGFR2_ADD, proportional=POP_RES_sVEGFR2_PROP, f=sVEGFR2), prediction=sVEGFR2, trans=log, type=continuous}
        sVEGFR3_obs : {eps=eps_RES_W, error=additiveError(additive=POP_RES_sVEGFR3_ADD), prediction=sVEGFR3, trans=log, type=continuous}
        sKIT_obs : {eps=eps_RES_W, error=additiveError(additive=POP_RES_sKIT_ADD), prediction=sKIT, trans=log, type=continuous}
    }

}
"""
        assertEquals(expected, modelObj.toMDL())
    }

    @Test
    public void testObservationBlock_WarfarinPkBovOAM() {
        def json = getJson(observationBlockJson_WarfarinPkBovOAM)

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
    public void testObservationBlock_CategoricalDIST() {
        def json = getJson(observationBlockJson_CategoricalDIST)

        def modelObj = new Model(json)

        String expected = """mdlobj {

    OBSERVATION {
        Y : {categories=[0, 1, 2, 3], probabilities=[Prob0, Prob1, Prob2, Prob3], type=categorical}
    }

}
"""
        assertEquals(expected, modelObj.toMDL())
    }

    @Test
    public void testModelPredictionBlock_WarfarinAnalyticSolution() {
        def json = getJson(modelPredictionBlockJson_WarfarinAnalyticSolution)

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
    public void testModelPredictionBlock_Hansson() {
        def json = getJson(modelPredictionBlockJson_Hansson)

        def modelObj = new Model(json)

        String expected = """mdlobj {

    MODEL_PREDICTION {
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
            VEGF : {deriv=KIN-KOUT*(1-EFF)*VEGF, init=BM0, wrt=T}
            sVEGFR2 : {deriv=KIN2*(1-EFF2)-KOUT2*sVEGFR2, init=BM02, wrt=T}
            sVEGFR3 : {deriv=KIN3*(1-EFF3)-KOUT3*sVEGFR3, init=BM03, wrt=T}
            sKIT : {deriv=KINS*(1-EFFS)-KOUTS*sKIT, init=BM0S, wrt=T}
        }
    }

}
"""
        assertEquals(expected, modelObj.toMDL())
    }

    @Test
    public void testModelPredictionBlockPkMacro() {
        def json = getJson(modelPredictionBlockPkMacroJson)

        def modelObj = new Model(json)

        String expected = """mdlobj {

    MODEL_PREDICTION {
        PKMACRO {
            DEP1 : {macro=iv, to=CENTRAL}
            CENTRAL : {macro=compartment, volume=V}
            {cl=CL, from=CENTRAL, macro=elimination}
            GUT : {ka=KA, macro=oral, p=F1, to=LATENT}
            LATENT : {macro=compartment, volume=1}
            {from=LATENT, kt=KT, macro=transfer, to=CENTRAL}
            {from=LATENT, k=K1, macro=elimination}
        }
        SCALE = V
        CC = CENTRAL/V
    }

}
"""
        assertEquals(expected, modelObj.toMDL())
    }

    @Test
    public void testGroupVariablesBlock_WarfarinPkBovOAM() {
        def json = getJson(groupVariablesBlockJson_WarfarinPkBovOAM)

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
    public void testGroupVariablesBlock_WarfarinPkSexage() {
        def json = getJson(groupVariablesBlockJson_WarfarinPkSexage)

        def modelObj = new Model(json)

        String expected = """mdlobj {

    GROUP_VARIABLES {
        FCLSEX = 1 when SEX==female otherwise 0
    }

}
"""
        assertEquals(expected, modelObj.toMDL())
    }
    
    @Test
    public void testModelOutputVariablesBlock_UseCase10() {
        def json = getJson(modelOutputVariablesBlockJson)
        
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

}
