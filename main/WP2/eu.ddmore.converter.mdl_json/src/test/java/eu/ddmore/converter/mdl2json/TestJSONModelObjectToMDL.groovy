package eu.ddmore.converter.mdl2json

import static org.junit.Assert.*

import org.apache.log4j.Logger
import org.junit.Test

import eu.ddmore.converter.mdl2json.domain.Model


class TestJSONModelObjectToMDL extends ConverterTestsParent {
	private static final Logger logger = Logger.getLogger(TestJSONModelObjectToMDL.class)
	
	// Using slashy strings /.../ here so we don't have to escape anything other than forward slashes
    private final static String independentVariablesBlockJson =
        / {"IDV":[{".name":"T"}]} /
    private final static String covariatesBlockJson =
        / {"COVARIATES":[{".name":"WT"},{".expr":"log(WT\/70)",".name":"logtWT"}]} /
    private final static String variabilityLevelsBlockJson =
        / {"VARIABILITY_LEVELS":[{"level":"2",".name":"ID","type":"model"},{"level":"1",".name":"DV","type":"observation"}]} /
	private final static String structuralParametersBlockJson =
        / {"STRUCTURAL_PARAMETERS":[{".name":"POP_CL"},{".name":"POP_V"},{".name":"POP_KA"},{".name":"POP_TLAG"},{".name":"BETA_CL_WT"},{".name":"BETA_V_WT"}]} /
	private final static String variabilityParametersBlockJson_Warfarin =
		/ {"VARIABILITY_PARAMETERS":[{".name":"PPV_CL"},{".name":"PPV_V"},{".name":"PPV_KA"},{".name":"PPV_TLAG"},{".name":"RUV_PROP"},{".name":"RUV_ADD"}]} /
    private final static String variabilityParametersBlockJson_WarfarinPkBov =
        / {"VARIABILITY_PARAMETERS":[{".name":"BSV_CL"},{".name":"BSV_V"},{".name":"BOV_CL"},{".name":"BOV_V"},{".name":"BOV_KA"},{".name":"BOV_TLAG"},{".name":"BSV_KA"},{".name":"BSV_TLAG"},{".name":"RUV_PROP"},{".name":"RUV_ADD"},{"params":"[eta_BOV_CL, eta_BOV_V]",".name":"BOV_COV_CL_V","type":"COV"},{"params":"[eta_BOV_KA, eta_BOV_TLAG]",".name":"BOV_COV_KA_TLAG","type":"COV"}]} /
    private final static String randomVarDefinitionBlockDVJson =
        / {"RANDOM_VARIABLE_DEFINITION(level=DV)":[{".random_var_distribution":"Normal",".random_var_attrs":{"mean":"0","var":"1"},".name":"EPS_Y"}]} /
    private final static String randomVarDefinitionBlockIDJson =
        / {"RANDOM_VARIABLE_DEFINITION(level=ID)":[{".random_var_distribution":"Normal",".random_var_attrs":{"mean":"0","sd":"PPV_CL"},".name":"ETA_CL"},{".random_var_distribution":"Normal",".random_var_attrs":{"mean":"0","sd":"PPV_V"},".name":"ETA_V"},{".random_var_distribution":"Normal",".random_var_attrs":{"mean":"0","sd":"PPV_KA"},".name":"ETA_KA"},{".random_var_distribution":"Normal",".random_var_attrs":{"mean":"0","sd":"PPV_TLAG"},".name":"ETA_TLAG"},{"rv1":"ETA_CL","rv2":"ETA_V",".name":"CORR_PPV_CL_V","type":"CORR"}]} /
    private final static String randomVarDefinitionBlockJson_WarfarinPkBov_ID =
        / {"RANDOM_VARIABLE_DEFINITION(level=ID)":[{".random_var_distribution":"Normal",".random_var_attrs":{"mean":"0","var":"BSV_CL"},".name":"eta_BSV_CL"},{".random_var_distribution":"Normal",".random_var_attrs":{"mean":"0","var":"BSV_V"},".name":"eta_BSV_V"},{".random_var_distribution":"Normal",".random_var_attrs":{"mean":"0","var":"BSV_KA"},".name":"eta_BSV_KA"},{".random_var_distribution":"Normal",".random_var_attrs":{"mean":"0","var":"BSV_TLAG"},".name":"eta_BSV_TLAG"}]} /
    private final static String randomVarDefinitionBlockJson_WarfarinPkBov_OCC =
        / {"RANDOM_VARIABLE_DEFINITION(level=OCC)":[{".random_var_distribution":"Normal",".random_var_attrs":{"mean":"0","var":"BOV_CL"},".name":"eta_BOV_CL"},{".random_var_distribution":"Normal",".random_var_attrs":{"mean":"0","var":"BOV_V"},".name":"eta_BOV_V"},{".random_var_distribution":"Normal",".random_var_attrs":{"mean":"0","var":"BOV_KA"},".name":"eta_BOV_KA"},{".random_var_distribution":"Normal",".random_var_attrs":{"mean":"0","var":"BOV_TLAG"},".name":"eta_BOV_TLAG"}]} /
    private final static String randomVarDefinitionBlockJson_WarfarinPkBov_DV =
        / {"RANDOM_VARIABLE_DEFINITION(level=DV)":[{".random_var_distribution":"Normal",".random_var_attrs":{"mean":"0","var":"RUV_PROP"},".name":"eps_RUV_PROP"},{".random_var_distribution":"Normal",".random_var_attrs":{"mean":"0","var":"RUV_ADD"},".name":"eps_RUV_ADD"}]} /
    private final static String individualVarsBlockJson_Warfarin =
        / {"INDIVIDUAL_VARIABLES":[{"fixEff":"[BETA_CL_WT]","trans":"log","cov":"[logtWT]","ranEff":"ETA_CL","pop":"POP_CL",".name":"CL","type":"linear"},{"fixEff":"[BETA_V_WT]","trans":"log","cov":"[logtWT]","ranEff":"ETA_V","pop":"POP_V",".name":"V","type":"linear"},{"trans":"log","ranEff":"ETA_KA","pop":"POP_KA",".name":"KA","type":"linear"},{"trans":"log","ranEff":"ETA_TLAG","pop":"POP_TLAG",".name":"TLAG","type":"linear"}]} /
    private final static String individualVarsBlockJson_Hansson =
        / {"INDIVIDUAL_VARIABLES":[{"trans":"log","ranEff":"eta_BM0","pop":"POP_BM0",".name":"BM0","type":"linear"},{"trans":"log","ranEff":"eta_BM02","pop":"POP_BM02",".name":"BM02","type":"linear"},{"trans":"log","ranEff":"eta_BM03","pop":"POP_BM03",".name":"BM03","type":"linear"},{"trans":"log","ranEff":"eta_BM0S","pop":"POP_BM0S",".name":"BM0S","type":"linear"},{".expr":"POP_IMAX",".name":"IMAX1"},{".expr":"POP_IMAX",".name":"IMAX2"},{".expr":"POP_IMAX",".name":"IMAX3"},{".expr":"POP_IMAX",".name":"IMAXS"},{"trans":"log","ranEff":"eta_IC50","pop":"POP_IC50",".name":"IC50","type":"linear"},{"trans":"log","ranEff":"eta_IC502","pop":"POP_IC50",".name":"IC502","type":"linear"},{"trans":"log","ranEff":"eta_IC503","pop":"POP_IC50",".name":"IC503","type":"linear"},{"trans":"log","ranEff":"eta_IC50S","pop":"POP_IC50",".name":"IC50S","type":"linear"},{".expr":"POP_HILL",".name":"HILL"},{".expr":"POP_HILL2",".name":"HILL2"},{"trans":"log","ranEff":"eta_MRT_VEGFs","pop":"POP_MRT",".name":"MRT1","type":"linear"},{"trans":"log","ranEff":"eta_MRT_VEGFs","pop":"POP_MRT2",".name":"MRT2","type":"linear"},{"trans":"log","ranEff":"eta_MRT_VEGFs","pop":"POP_MRT3",".name":"MRT3","type":"linear"},{"trans":"log","ranEff":"eta_MRT_sKIT","pop":"POP_MRTS",".name":"MRTS","type":"linear"},{".expr":"POP_TVSLP\/1000",".name":"TVSLP"},{"trans":"log","ranEff":"eta_TVSLP","pop":"TVSLP",".name":"DPSLP","type":"linear"},{".expr":"POP_TVSLP\/1000",".name":"TVSLPS"},{"trans":"log","ranEff":"eta_TVSLPS","pop":"TVSLPS",".name":"DPSLPS","type":"linear"},{".expr":"1\/MRT1",".name":"KOUT"},{".expr":"1\/MRT2",".name":"KOUT2"},{".expr":"1\/MRT3",".name":"KOUT3"},{".expr":"1\/MRTS",".name":"KOUTS"},{".expr":"BM02*KOUT2",".name":"KIN2"},{".expr":"BM03*KOUT3",".name":"KIN3"}]} /
    private final static String individualVarsBlockJson_WarfarinPkBov =
        / {"INDIVIDUAL_VARIABLES":[{".expr":"GRPCL*exp(eta_BSV_CL+eta_BOV_CL)",".name":"CL"},{".expr":"GRPV*exp(eta_BSV_V+eta_BOV_V)",".name":"V"},{".expr":"GRPKA*exp(eta_BSV_KA+eta_BOV_KA)",".name":"KA"},{".expr":"GRPLG*exp(eta_BSV_TLAG+eta_BOV_TLAG)",".name":"ALAG1"}]} /
    private final static String observationBlockJson_Hansson =
		/ {"OBSERVATION":[{"eps":"eps_RES_W","trans":"log","error":"additiveError(additive=POP_RES_VEGF_ADD)","prediction":"VEGF",".name":"VEGF_obs","type":"continuous"},{"eps":"eps_RES_W","trans":"log","error":"combinedError2(additive=POP_RES_sVEGFR2_ADD, proportional=POP_RES_sVEGFR2_PROP, f=sVEGFR2)","prediction":"sVEGFR2",".name":"sVEGFR2_obs","type":"continuous"},{"eps":"eps_RES_W","trans":"log","error":"additiveError(additive=POP_RES_sVEGFR3_ADD)","prediction":"sVEGFR3",".name":"sVEGFR3_obs","type":"continuous"},{"eps":"eps_RES_W","trans":"log","error":"additiveError(additive=POP_RES_sKIT_ADD)","prediction":"sKIT",".name":"sKIT_obs","type":"continuous"}]} /
    private final static String observationBlockJson_WarfarinPkBov =
        / {"OBSERVATION":[{".expr":"CONC*(1+eps_RUV_PROP)+eps_RUV_ADD",".name":"Y"}]} /
    private final static String modelPredictionBlockJson_WarfarinAnalyticSolution =
        / {"MODEL_PREDICTION":[{".name":"D"},{".name":"TD"},{".expr":"CL\/V",".name":"k"},{".expr":"0 when T-TD<TLAG otherwise (D\/V)*(KA\/(KA-k)*(exp(-k*(T-TD-TLAG)-exp(-KA*(T-TD-TLAG)))))",".name":"CC"}]} /
    private final static String modelPredictionBlockJson_Hansson =
        / {"MODEL_PREDICTION":[{".expr":"DOSE\/CL",".name":"AUC"},{".expr":"BM0*(1+DPSLP*T)",".name":"DP1"},{".expr":"BM0S*(1+DPSLPS*T)",".name":"DPS"},{".expr":"DP1*KOUT",".name":"KIN"},{".expr":"DPS*KOUTS",".name":"KINS"},{".DEQ":[{".expr":"IMAX1*AUC^HILL\/(IC50^HILL+AUC^HILL)",".name":"EFF"},{".expr":"IMAX2*AUC^HILL2\/(IC502^HILL2+AUC^HILL2)",".name":"EFF2"},{".expr":"IMAX3*AUC\/(IC503+AUC)",".name":"EFF3"},{".expr":"IMAXS*AUC\/(IC50S+AUC)",".name":"EFFS"},{"wrt":"T","deriv":"KIN-KOUT*(1-EFF)*VEGF","init":"BM0",".name":"VEGF"},{"wrt":"T","deriv":"KIN2*(1-EFF2)-KOUT2*sVEGFR2","init":"BM02",".name":"sVEGFR2"},{"wrt":"T","deriv":"KIN3*(1-EFF3)-KOUT3*sVEGFR3","init":"BM03",".name":"sVEGFR3"},{"wrt":"T","deriv":"KINS*(1-EFFS)-KOUTS*sKIT","init":"BM0S",".name":"sKIT"}]}]} /
    private final static String modelPredictionBlockJson_WarfarinPkBov =
        / {"MODEL_PREDICTION":[{".PKMACRO":[{".name":"DEP"},{"to":"Ac","macro":"oral","tlag":"ALAG1","ka":"KA"},{"macro":"compartment","volume":"V",".name":"Ac"},{"macro":"elimination","cl":"CL","from":"Ac"}]},{".expr":"Ac\/V",".name":"CONC"}]} /
    private final static String groupVariablesBlockJson =
        / {"GROUP_VARIABLES":[{".expr":"POP_CL*(WT\/70)^0.75",".name":"GRPCL"},{".expr":"POP_V*WT\/70",".name":"GRPV"},{".expr":"POP_KA",".name":"GRPKA"},{".expr":"POP_TLAG",".name":"GRPLG"}]} /

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
    public void testCovariatesBlock() {

        def json = getJson(covariatesBlockJson)

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
    public void testVariabilityLevelsBlock() {

        def json = getJson(variabilityLevelsBlockJson)

        def modelObj = new Model(json)
        
        String expected = """mdlobj {

    VARIABILITY_LEVELS {
        ID : {level=2, type=model}
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
        RUV_PROP
        RUV_ADD
    }

}
"""
		assertEquals(expected, modelObj.toMDL())
	}
    
    @Test
    public void testVariabilityParametersBlock_WarfarinPkBov() {
        def json = getJson(variabilityParametersBlockJson_WarfarinPkBov)

        def modelObj = new Model(json)
        
        String expected = """mdlobj {

    VARIABILITY_PARAMETERS {
        BSV_CL
        BSV_V
        BOV_CL
        BOV_V
        BOV_KA
        BOV_TLAG
        BSV_KA
        BSV_TLAG
        RUV_PROP
        RUV_ADD
        BOV_COV_CL_V : {params=[eta_BOV_CL, eta_BOV_V], type=COV}
        BOV_COV_KA_TLAG : {params=[eta_BOV_KA, eta_BOV_TLAG], type=COV}
    }

}
"""
        assertEquals(expected, modelObj.toMDL())
    }
	
    @Test
    public void testRandomVariableDefinitionDVBlock() {

        def json = getJson(randomVarDefinitionBlockDVJson)

        def modelObj = new Model(json)
        
        String expected = """mdlobj {

    RANDOM_VARIABLE_DEFINITION(level=DV) {
        EPS_Y ~ Normal(mean=0, var=1)
    }

}
"""
        assertEquals(expected, modelObj.toMDL())
    }
    
    @Test
    public void testRandomVariableDefinitionIDBlock() {

        def json = getJson(randomVarDefinitionBlockIDJson)

        def modelObj = new Model(json)
        
        String expected = """mdlobj {

    RANDOM_VARIABLE_DEFINITION(level=ID) {
        ETA_CL ~ Normal(mean=0, sd=PPV_CL)
        ETA_V ~ Normal(mean=0, sd=PPV_V)
        ETA_KA ~ Normal(mean=0, sd=PPV_KA)
        ETA_TLAG ~ Normal(mean=0, sd=PPV_TLAG)
        CORR_PPV_CL_V : {rv1=ETA_CL, rv2=ETA_V, type=CORR}
    }

}
"""
        assertEquals(expected, modelObj.toMDL())
    }
    
    @Test
    public void testRandomVariableDefinitionBlocks_WarfarinPkBov() {

        def json_ID = getJson(randomVarDefinitionBlockJson_WarfarinPkBov_ID)

        def modelObj_ID = new Model(json_ID)
        
        String expected_ID = """mdlobj {

    RANDOM_VARIABLE_DEFINITION(level=ID) {
        eta_BSV_CL ~ Normal(mean=0, var=BSV_CL)
        eta_BSV_V ~ Normal(mean=0, var=BSV_V)
        eta_BSV_KA ~ Normal(mean=0, var=BSV_KA)
        eta_BSV_TLAG ~ Normal(mean=0, var=BSV_TLAG)
    }

}
"""
        assertEquals(expected_ID, modelObj_ID.toMDL())
        
        def json_OCC = getJson(randomVarDefinitionBlockJson_WarfarinPkBov_OCC)
        
        def modelObj_OCC = new Model(json_OCC)
        
        String expected_OCC = """mdlobj {

    RANDOM_VARIABLE_DEFINITION(level=OCC) {
        eta_BOV_CL ~ Normal(mean=0, var=BOV_CL)
        eta_BOV_V ~ Normal(mean=0, var=BOV_V)
        eta_BOV_KA ~ Normal(mean=0, var=BOV_KA)
        eta_BOV_TLAG ~ Normal(mean=0, var=BOV_TLAG)
    }

}
"""
        assertEquals(expected_OCC, modelObj_OCC.toMDL())
        
        def json_DV = getJson(randomVarDefinitionBlockJson_WarfarinPkBov_DV)
        
        def modelObj_DV = new Model(json_DV)
        
        String expected_DV = """mdlobj {

    RANDOM_VARIABLE_DEFINITION(level=DV) {
        eps_RUV_PROP ~ Normal(mean=0, var=RUV_PROP)
        eps_RUV_ADD ~ Normal(mean=0, var=RUV_ADD)
    }

}
"""
        assertEquals(expected_DV, modelObj_DV.toMDL())
        
    }
    
    @Test
    public void testIndividualVariablesBlock_Warfarin() {
        def json = getJson(individualVarsBlockJson_Warfarin)

        def modelObj = new Model(json)
        
        String expected = """mdlobj {

    INDIVIDUAL_VARIABLES {
        CL : {cov=[logtWT], fixEff=[BETA_CL_WT], pop=POP_CL, ranEff=ETA_CL, trans=log, type=linear}
        V : {cov=[logtWT], fixEff=[BETA_V_WT], pop=POP_V, ranEff=ETA_V, trans=log, type=linear}
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
        CL = GRPCL*exp(eta_BSV_CL+eta_BOV_CL)
        V = GRPV*exp(eta_BSV_V+eta_BOV_V)
        KA = GRPKA*exp(eta_BSV_KA+eta_BOV_KA)
        ALAG1 = GRPLG*exp(eta_BSV_TLAG+eta_BOV_TLAG)
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
    public void testObservationBlock_WarfarinPkBov() {
        def json = getJson(observationBlockJson_WarfarinPkBov)

        def modelObj = new Model(json)
        
        String expected = """mdlobj {

    OBSERVATION {
        Y = CONC*(1+eps_RUV_PROP)+eps_RUV_ADD
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
        TD
        k = CL/V
        CC = 0 when T-TD<TLAG otherwise (D/V)*(KA/(KA-k)*(exp(-k*(T-TD-TLAG)-exp(-KA*(T-TD-TLAG)))))
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
    public void testModelPredictionBlock_WarfarinPkBov() {
        def json = getJson(modelPredictionBlockJson_WarfarinPkBov)

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
	public void testGroupVariablesBlock() {
        def json = getJson(groupVariablesBlockJson)
        
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
        // Note: the replace makes the line endings consistent so text compare passes
        assertEquals(expected, modelObj.toMDL().replace("\r\n", "\n"))
	}
	
}
