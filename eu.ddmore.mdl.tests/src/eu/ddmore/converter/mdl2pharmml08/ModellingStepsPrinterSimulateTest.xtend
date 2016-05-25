package eu.ddmore.converter.mdl2pharmml08

import com.google.inject.Inject
import eu.ddmore.mdl.MdlAndLibInjectorProvider
import eu.ddmore.mdl.MdlTestHelper
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.provider.MogDefinitionProvider
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.assertEquals

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class ModellingStepsPrinterSimulateTest {
	@Inject extension MdlTestHelper<Mcl>
	@Inject extension ModellingStepsPrinter
	@Inject extension ValidationTestHelper
	@Inject extension MogDefinitionProvider
	
	@Test
	def void testSimulationStepTargetSettingsFile(){
		val mcl = '''
			mobj = mdlObj {
				IDV{ T }
			
				COVARIATES{
					logtWT
				}
			
				VARIABILITY_LEVELS{
					ID : { level=2, type is parameter }
					DV : { level=1, type is observation }
				} 
			
				STRUCTURAL_PARAMETERS { 
					POP_CL
					POP_V
					POP_KA
					POP_TLAG
					BETA_CL_WT
					BETA_V_WT
					RUV_PROP
					RUV_ADD
				} # end STRUCTURAL_PARAMETERS
				
				VARIABILITY_PARAMETERS {
					PPV_CL
					PPV_V
					PPV_KA
					PPV_TLAG
				} # end VARIABILITY_PARAMETERS 
				
				RANDOM_VARIABLE_DEFINITION(level=ID) {
					ETA_CL ~ Normal(mean = 0, sd = PPV_CL)
					ETA_V ~ Normal(mean = 0, sd = PPV_V)
					ETA_KA ~ Normal(mean = 0, sd = PPV_KA)
					ETA_TLAG ~ Normal(mean = 0, sd = PPV_TLAG)
				} # end RANDOM_VARIABLE_DEFINITION 
			
				INDIVIDUAL_VARIABLES { # This maps to the "Type 3" individual parameter definition in PharmML
				    CL : { type is linear, trans is ln, pop = POP_CL, fixEff = {coeff=BETA_CL_WT, cov=logtWT} , ranEff = ETA_CL }
				    V : { type is linear, trans is ln, pop = POP_V, fixEff = {coeff=BETA_V_WT, cov=logtWT} , ranEff = ETA_V }
				    KA : { type is linear, trans is ln, pop = POP_KA, ranEff = ETA_KA }
				    TLAG : { type is linear, trans is ln, pop = POP_TLAG, ranEff = ETA_TLAG }
				} # end INDIVIDUAL_VARIABLES
			
				MODEL_PREDICTION {
					DEQ{
						RATEIN = if(T >= TLAG) then GUT * KA  
								 else 0
						GUT : { deriv =(- RATEIN), init = 0, x0 = 0 }
						CENTRAL : { deriv =(RATEIN - CL * CENTRAL / V), init = 0, x0 = 0 } 
					}
				    CC = CENTRAL / V 
				} # end MODEL_PREDICTION
				
				RANDOM_VARIABLE_DEFINITION(level=DV){
				    EPS_Y ~ Normal(mean = 0, var = 1)
				}
			
				OBSERVATION {
				    Y : { type is combinedError1, additive = RUV_ADD, proportional = RUV_PROP, eps = EPS_Y, prediction = CC }
				} # end OBSERVATION
			}
			
			dobj = dataObj {
				DECLARED_VARIABLES{GUT::dosingTarget Y::continuousObs}
			
				DATA_INPUT_VARIABLES {
					ID : { use is id }
					TIME : { use is idv }
					WT : { use is covariate }
					AMT : { use is amt, variable = GUT }
					DVID : { use is dvid}
					DV : { use is dv, variable = Y }
					MDV : { use is mdv }
					logtWT:{ use is covariate }  
				} # end DATA_INPUT_VARIABLES
				
				SOURCE {
				    srcfile : {file = "warfarin_conc.csv", 
			            inputFormat is nonmemFormat  } 
				} # end SOURCE
			}
			
			pobj = parObj {
				# By default a parameter is to be estimated if fix is omitted
			 	STRUCTURAL {
					POP_CL : { value = 0.1, lo = 0.001 }
					POP_V : { value = 8, lo = 0.001 } 
					POP_KA : { value = 0.362, lo = 0.001 }
					POP_TLAG : { value=1, lo=0.001 }
					BETA_CL_WT : { value = 0.75, fix = true }
					BETA_V_WT : { value = 1, fix = true } 
					RUV_PROP : { value = 0.1, lo = 0 }
					RUV_ADD : { value = 0.1, lo = 0 } 
					} # end STRUCTURAL
				VARIABILITY {
					PPV_CL : { value = 0.1, type is sd }
					PPV_V : { value = 0.1, type is sd }
					PPV_KA : { value = 0.1, type is sd }
					PPV_TLAG : { value = 0.1, type is sd, fix=true }
				} # end VARIABILITY
			} # end of parameter object 

			tobj = taskObj {
				SIMULATE{ 
					set solver is stiff
					
					TARGET_SETTINGS(target = "NONMEM"){
						set set1 = "Asetting",
							set2 = 34.0,
							set3 = true
					}

					TARGET_SETTINGS(target = "PFIM", settingsFile="foo/bar/setting.txt"){
						set set11 = "Asetting"
						set graphicsSettings = "setting.txt"
					}
				}
			}
			
			mgobj = mogObj {
				OBJECTS{
					mobj : { type is mdlObj }
					pobj : { type is parObj }
					dobj : { type is dataObj }
					tobj : { type is taskObj }
				}
			}
		'''.parse
		mcl.assertNoErrors
		
		val mdlObj = mcl.firstMogObj.mdlObj
		val parObj = mcl.firstMogObj.paramObj
		val taskObj = mcl.firstMogObj.taskObj
		
		val actual = writeSimulationStep("simOid2", mdlObj, null, parObj, taskObj.blocks.head)

		val expected = '''
		<SimulationStep oid="simOid2">
			<SoftwareSettings>
				<File oid="oid_estimStep_1">
					<ds:path>foo/bar/setting.txt</ds:path>
				</File>
			</SoftwareSettings>
			<ct:VariableAssignment>
				<ct:SymbRef blkIdRef="pm" symbIdRef="POP_CL"/>
				<ct:Assign>
					<ct:Real>0.1</ct:Real>
				</ct:Assign>
			</ct:VariableAssignment>
			<ct:VariableAssignment>
				<ct:SymbRef blkIdRef="pm" symbIdRef="POP_V"/>
				<ct:Assign>
					<ct:Int>8</ct:Int>
				</ct:Assign>
			</ct:VariableAssignment>
			<ct:VariableAssignment>
				<ct:SymbRef blkIdRef="pm" symbIdRef="POP_KA"/>
				<ct:Assign>
					<ct:Real>0.362</ct:Real>
				</ct:Assign>
			</ct:VariableAssignment>
			<ct:VariableAssignment>
				<ct:SymbRef blkIdRef="pm" symbIdRef="POP_TLAG"/>
				<ct:Assign>
					<ct:Int>1</ct:Int>
				</ct:Assign>
			</ct:VariableAssignment>
			<ct:VariableAssignment>
				<ct:SymbRef blkIdRef="pm" symbIdRef="BETA_CL_WT"/>
				<ct:Assign>
					<ct:Real>0.75</ct:Real>
				</ct:Assign>
			</ct:VariableAssignment>
			<ct:VariableAssignment>
				<ct:SymbRef blkIdRef="pm" symbIdRef="BETA_V_WT"/>
				<ct:Assign>
					<ct:Int>1</ct:Int>
				</ct:Assign>
			</ct:VariableAssignment>
			<ct:VariableAssignment>
				<ct:SymbRef blkIdRef="pm" symbIdRef="RUV_PROP"/>
				<ct:Assign>
					<ct:Real>0.1</ct:Real>
				</ct:Assign>
			</ct:VariableAssignment>
			<ct:VariableAssignment>
				<ct:SymbRef blkIdRef="pm" symbIdRef="RUV_ADD"/>
				<ct:Assign>
					<ct:Real>0.1</ct:Real>
				</ct:Assign>
			</ct:VariableAssignment>
			<ct:VariableAssignment>
				<ct:SymbRef blkIdRef="pm" symbIdRef="PPV_CL"/>
				<ct:Assign>
					<ct:Real>0.1</ct:Real>
				</ct:Assign>
			</ct:VariableAssignment>
			<ct:VariableAssignment>
				<ct:SymbRef blkIdRef="pm" symbIdRef="PPV_V"/>
				<ct:Assign>
					<ct:Real>0.1</ct:Real>
				</ct:Assign>
			</ct:VariableAssignment>
			<ct:VariableAssignment>
				<ct:SymbRef blkIdRef="pm" symbIdRef="PPV_KA"/>
				<ct:Assign>
					<ct:Real>0.1</ct:Real>
				</ct:Assign>
			</ct:VariableAssignment>
			<ct:VariableAssignment>
				<ct:SymbRef blkIdRef="pm" symbIdRef="PPV_TLAG"/>
				<ct:Assign>
					<ct:Real>0.1</ct:Real>
				</ct:Assign>
			</ct:VariableAssignment>
			<Operation order="1" opType="generic">
				<Property name="solver">
					<ct:Assign>
						<ct:String>stiff</ct:String>
					</ct:Assign>
				</Property>
			</Operation>
			<Operation order="2" opType="NONMEM">
				<Property name="set1">
					<ct:Assign>
						<ct:String>Asetting</ct:String>
					</ct:Assign>
				</Property>
				<Property name="set2">
					<ct:Assign>
						<ct:Real>34.0</ct:Real>
					</ct:Assign>
				</Property>
				<Property name="set3">
					<ct:Assign>
						<ct:True/>
					</ct:Assign>
				</Property>
			</Operation>
			<Operation order="3" opType="PFIM">
				<Property name="MDL__settingsFiles">
					<ct:Assign>
						<ct:Vector>
							<ct:VectorElements>
								<ct:String>oid_estimStep_1</ct:String>
							</ct:VectorElements>
						</ct:Vector>
					</ct:Assign>
				</Property>
				<Property name="set11">
					<ct:Assign>
						<ct:String>Asetting</ct:String>
					</ct:Assign>
				</Property>
				<Property name="graphicsSettings">
					<ct:Assign>
						<ct:String>setting.txt</ct:String>
					</ct:Assign>
				</Property>
			</Operation>
		</SimulationStep>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}
	
	@Test
	def void testSimulationStepTargetSettingsOnly(){
		val mcl = '''
			mobj = mdlObj {
				IDV{ T }
			
				COVARIATES{
					logtWT
				}
			
				VARIABILITY_LEVELS{
					ID : { level=2, type is parameter }
					DV : { level=1, type is observation }
				} 
			
				STRUCTURAL_PARAMETERS { 
					POP_CL
					POP_V
					POP_KA
					POP_TLAG
					BETA_CL_WT
					BETA_V_WT
					RUV_PROP
					RUV_ADD
				} # end STRUCTURAL_PARAMETERS
				
				VARIABILITY_PARAMETERS {
					PPV_CL
					PPV_V
					PPV_KA
					PPV_TLAG
				} # end VARIABILITY_PARAMETERS 
				
				RANDOM_VARIABLE_DEFINITION(level=ID) {
					ETA_CL ~ Normal(mean = 0, sd = PPV_CL)
					ETA_V ~ Normal(mean = 0, sd = PPV_V)
					ETA_KA ~ Normal(mean = 0, sd = PPV_KA)
					ETA_TLAG ~ Normal(mean = 0, sd = PPV_TLAG)
				} # end RANDOM_VARIABLE_DEFINITION 
			
				INDIVIDUAL_VARIABLES { # This maps to the "Type 3" individual parameter definition in PharmML
				    CL : { type is linear, trans is ln, pop = POP_CL, fixEff = {coeff=BETA_CL_WT, cov=logtWT} , ranEff = ETA_CL }
				    V : { type is linear, trans is ln, pop = POP_V, fixEff = {coeff=BETA_V_WT, cov=logtWT} , ranEff = ETA_V }
				    KA : { type is linear, trans is ln, pop = POP_KA, ranEff = ETA_KA }
				    TLAG : { type is linear, trans is ln, pop = POP_TLAG, ranEff = ETA_TLAG }
				} # end INDIVIDUAL_VARIABLES
			
				MODEL_PREDICTION {
					DEQ{
						RATEIN = if(T >= TLAG) then GUT * KA  
								 else 0
						GUT : { deriv =(- RATEIN), init = 0, x0 = 0 }
						CENTRAL : { deriv =(RATEIN - CL * CENTRAL / V), init = 0, x0 = 0 } 
					}
				    CC = CENTRAL / V 
				} # end MODEL_PREDICTION
				
				RANDOM_VARIABLE_DEFINITION(level=DV){
				    EPS_Y ~ Normal(mean = 0, var = 1)
				}
			
				OBSERVATION {
				    Y : { type is combinedError1, additive = RUV_ADD, proportional = RUV_PROP, eps = EPS_Y, prediction = CC }
				} # end OBSERVATION
			}
			
			dobj = dataObj {
				DECLARED_VARIABLES{GUT::dosingTarget Y::continuousObs}
			
				DATA_INPUT_VARIABLES {
					ID : { use is id }
					TIME : { use is idv }
					WT : { use is covariate }
					AMT : { use is amt, variable = GUT }
					DVID : { use is dvid}
					DV : { use is dv, variable = Y }
					MDV : { use is mdv }
					logtWT:{ use is covariate }  
				} # end DATA_INPUT_VARIABLES
				
				SOURCE {
				    srcfile : {file = "warfarin_conc.csv", 
			            inputFormat is nonmemFormat  } 
				} # end SOURCE
			}
			
			pobj = parObj {
				# By default a parameter is to be estimated if fix is omitted
			 	STRUCTURAL {
					POP_CL : { value = 0.1, lo = 0.001 }
					POP_V : { value = 8, lo = 0.001 } 
					POP_KA : { value = 0.362, lo = 0.001 }
					POP_TLAG : { value=1, lo=0.001 }
					BETA_CL_WT : { value = 0.75, fix = true }
					BETA_V_WT : { value = 1, fix = true } 
					RUV_PROP : { value = 0.1, lo = 0 }
					RUV_ADD : { value = 0.1, lo = 0 } 
					} # end STRUCTURAL
				VARIABILITY {
					PPV_CL : { value = 0.1, type is sd }
					PPV_V : { value = 0.1, type is sd }
					PPV_KA : { value = 0.1, type is sd }
					PPV_TLAG : { value = 0.1, type is sd, fix=true }
				} # end VARIABILITY
			} # end of parameter object 

			tobj = taskObj {
				SIMULATE{ 
					set solver is stiff
					
					TARGET_SETTINGS(target = "NONMEM"){
						set set1 = "Asetting",
							set2 = 34.0,
							set3 = true
					}

					TARGET_SETTINGS(target = "PFIM"){
						set set11 = "Asetting"
					}
				}
			}
			
			mgobj = mogObj {
				OBJECTS{
					mobj : { type is mdlObj }
					pobj : { type is parObj }
					dobj : { type is dataObj }
					tobj : { type is taskObj }
				}
			}
		'''.parse
		mcl.assertNoErrors
		
		val mdlObj = mcl.firstMogObj.mdlObj
		val parObj = mcl.firstMogObj.paramObj
		val taskObj = mcl.firstMogObj.taskObj
		
		val actual = writeSimulationStep("simOid99", mdlObj, null, parObj, taskObj.blocks.head)

		val expected = '''
		<SimulationStep oid="simOid99">
			<ct:VariableAssignment>
				<ct:SymbRef blkIdRef="pm" symbIdRef="POP_CL"/>
				<ct:Assign>
					<ct:Real>0.1</ct:Real>
				</ct:Assign>
			</ct:VariableAssignment>
			<ct:VariableAssignment>
				<ct:SymbRef blkIdRef="pm" symbIdRef="POP_V"/>
				<ct:Assign>
					<ct:Int>8</ct:Int>
				</ct:Assign>
			</ct:VariableAssignment>
			<ct:VariableAssignment>
				<ct:SymbRef blkIdRef="pm" symbIdRef="POP_KA"/>
				<ct:Assign>
					<ct:Real>0.362</ct:Real>
				</ct:Assign>
			</ct:VariableAssignment>
			<ct:VariableAssignment>
				<ct:SymbRef blkIdRef="pm" symbIdRef="POP_TLAG"/>
				<ct:Assign>
					<ct:Int>1</ct:Int>
				</ct:Assign>
			</ct:VariableAssignment>
			<ct:VariableAssignment>
				<ct:SymbRef blkIdRef="pm" symbIdRef="BETA_CL_WT"/>
				<ct:Assign>
					<ct:Real>0.75</ct:Real>
				</ct:Assign>
			</ct:VariableAssignment>
			<ct:VariableAssignment>
				<ct:SymbRef blkIdRef="pm" symbIdRef="BETA_V_WT"/>
				<ct:Assign>
					<ct:Int>1</ct:Int>
				</ct:Assign>
			</ct:VariableAssignment>
			<ct:VariableAssignment>
				<ct:SymbRef blkIdRef="pm" symbIdRef="RUV_PROP"/>
				<ct:Assign>
					<ct:Real>0.1</ct:Real>
				</ct:Assign>
			</ct:VariableAssignment>
			<ct:VariableAssignment>
				<ct:SymbRef blkIdRef="pm" symbIdRef="RUV_ADD"/>
				<ct:Assign>
					<ct:Real>0.1</ct:Real>
				</ct:Assign>
			</ct:VariableAssignment>
			<ct:VariableAssignment>
				<ct:SymbRef blkIdRef="pm" symbIdRef="PPV_CL"/>
				<ct:Assign>
					<ct:Real>0.1</ct:Real>
				</ct:Assign>
			</ct:VariableAssignment>
			<ct:VariableAssignment>
				<ct:SymbRef blkIdRef="pm" symbIdRef="PPV_V"/>
				<ct:Assign>
					<ct:Real>0.1</ct:Real>
				</ct:Assign>
			</ct:VariableAssignment>
			<ct:VariableAssignment>
				<ct:SymbRef blkIdRef="pm" symbIdRef="PPV_KA"/>
				<ct:Assign>
					<ct:Real>0.1</ct:Real>
				</ct:Assign>
			</ct:VariableAssignment>
			<ct:VariableAssignment>
				<ct:SymbRef blkIdRef="pm" symbIdRef="PPV_TLAG"/>
				<ct:Assign>
					<ct:Real>0.1</ct:Real>
				</ct:Assign>
			</ct:VariableAssignment>
			<Operation order="1" opType="generic">
				<Property name="solver">
					<ct:Assign>
						<ct:String>stiff</ct:String>
					</ct:Assign>
				</Property>
			</Operation>
			<Operation order="2" opType="NONMEM">
				<Property name="set1">
					<ct:Assign>
						<ct:String>Asetting</ct:String>
					</ct:Assign>
				</Property>
				<Property name="set2">
					<ct:Assign>
						<ct:Real>34.0</ct:Real>
					</ct:Assign>
				</Property>
				<Property name="set3">
					<ct:Assign>
						<ct:True/>
					</ct:Assign>
				</Property>
			</Operation>
			<Operation order="3" opType="PFIM">
				<Property name="set11">
					<ct:Assign>
						<ct:String>Asetting</ct:String>
					</ct:Assign>
				</Property>
			</Operation>
		</SimulationStep>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testSimulationStepNoTargetSettings(){
		val mcl = '''
			mobj = mdlObj {
				IDV{ T }
			
				COVARIATES{
					logtWT
				}
			
				VARIABILITY_LEVELS{
					ID : { level=2, type is parameter }
					DV : { level=1, type is observation }
				} 
			
				STRUCTURAL_PARAMETERS { 
					POP_CL
					POP_V
					POP_KA
					POP_TLAG
					BETA_CL_WT
					BETA_V_WT
					RUV_PROP
					RUV_ADD
				} # end STRUCTURAL_PARAMETERS
				
				VARIABILITY_PARAMETERS {
					PPV_CL
					PPV_V
					PPV_KA
					PPV_TLAG
				} # end VARIABILITY_PARAMETERS 
				
				RANDOM_VARIABLE_DEFINITION(level=ID) {
					ETA_CL ~ Normal(mean = 0, sd = PPV_CL)
					ETA_V ~ Normal(mean = 0, sd = PPV_V)
					ETA_KA ~ Normal(mean = 0, sd = PPV_KA)
					ETA_TLAG ~ Normal(mean = 0, sd = PPV_TLAG)
				} # end RANDOM_VARIABLE_DEFINITION 
			
				INDIVIDUAL_VARIABLES { # This maps to the "Type 3" individual parameter definition in PharmML
				    CL : { type is linear, trans is ln, pop = POP_CL, fixEff = {coeff=BETA_CL_WT, cov=logtWT} , ranEff = ETA_CL }
				    V : { type is linear, trans is ln, pop = POP_V, fixEff = {coeff=BETA_V_WT, cov=logtWT} , ranEff = ETA_V }
				    KA : { type is linear, trans is ln, pop = POP_KA, ranEff = ETA_KA }
				    TLAG : { type is linear, trans is ln, pop = POP_TLAG, ranEff = ETA_TLAG }
				} # end INDIVIDUAL_VARIABLES
			
				MODEL_PREDICTION {
					DEQ{
						RATEIN = if(T >= TLAG) then GUT * KA  
								 else 0
						GUT : { deriv =(- RATEIN), init = 0, x0 = 0 }
						CENTRAL : { deriv =(RATEIN - CL * CENTRAL / V), init = 0, x0 = 0 } 
					}
				    CC = CENTRAL / V 
				} # end MODEL_PREDICTION
				
				RANDOM_VARIABLE_DEFINITION(level=DV){
				    EPS_Y ~ Normal(mean = 0, var = 1)
				}
			
				OBSERVATION {
				    Y : { type is combinedError1, additive = RUV_ADD, proportional = RUV_PROP, eps = EPS_Y, prediction = CC }
				} # end OBSERVATION
			}
			
			dobj = dataObj {
				DECLARED_VARIABLES{GUT::dosingTarget Y::continuousObs}
			
				DATA_INPUT_VARIABLES {
					ID : { use is id }
					TIME : { use is idv }
					WT : { use is covariate }
					AMT : { use is amt, variable = GUT }
					DVID : { use is dvid}
					DV : { use is dv, variable = Y }
					MDV : { use is mdv }
					logtWT:{ use is covariate }  
				} # end DATA_INPUT_VARIABLES
				
				SOURCE {
				    srcfile : {file = "warfarin_conc.csv", 
			            inputFormat is nonmemFormat  } 
				} # end SOURCE
			}
			
			pobj = parObj {
				# By default a parameter is to be estimated if fix is omitted
			 	STRUCTURAL {
					POP_CL : { value = 0.1, lo = 0.001 }
					POP_V : { value = 8, lo = 0.001 } 
					POP_KA : { value = 0.362, lo = 0.001 }
					POP_TLAG : { value=1, lo=0.001 }
					BETA_CL_WT : { value = 0.75, fix = true }
					BETA_V_WT : { value = 1, fix = true } 
					RUV_PROP : { value = 0.1, lo = 0 }
					RUV_ADD : { value = 0.1, lo = 0 } 
					} # end STRUCTURAL
				VARIABILITY {
					PPV_CL : { value = 0.1, type is sd }
					PPV_V : { value = 0.1, type is sd }
					PPV_KA : { value = 0.1, type is sd }
					PPV_TLAG : { value = 0.1, type is sd, fix=true }
				} # end VARIABILITY
			} # end of parameter object 

			tobj = taskObj {
				SIMULATE{ 
					set solver is nonStiff
				}
			}
			
			mgobj = mogObj {
				OBJECTS{
					mobj : { type is mdlObj }
					pobj : { type is parObj }
					dobj : { type is dataObj }
					tobj : { type is taskObj }
				}
			}
		'''.parse
		mcl.assertNoErrors
		
		val mdlObj = mcl.firstMogObj.mdlObj
		val parObj = mcl.firstMogObj.paramObj
		val taskObj = mcl.firstMogObj.taskObj
		
		val actual = writeSimulationStep("simOid1", mdlObj, null, parObj, taskObj.blocks.head)

		val expected = '''
		<SimulationStep oid="simOid1">
			<ct:VariableAssignment>
				<ct:SymbRef blkIdRef="pm" symbIdRef="POP_CL"/>
				<ct:Assign>
					<ct:Real>0.1</ct:Real>
				</ct:Assign>
			</ct:VariableAssignment>
			<ct:VariableAssignment>
				<ct:SymbRef blkIdRef="pm" symbIdRef="POP_V"/>
				<ct:Assign>
					<ct:Int>8</ct:Int>
				</ct:Assign>
			</ct:VariableAssignment>
			<ct:VariableAssignment>
				<ct:SymbRef blkIdRef="pm" symbIdRef="POP_KA"/>
				<ct:Assign>
					<ct:Real>0.362</ct:Real>
				</ct:Assign>
			</ct:VariableAssignment>
			<ct:VariableAssignment>
				<ct:SymbRef blkIdRef="pm" symbIdRef="POP_TLAG"/>
				<ct:Assign>
					<ct:Int>1</ct:Int>
				</ct:Assign>
			</ct:VariableAssignment>
			<ct:VariableAssignment>
				<ct:SymbRef blkIdRef="pm" symbIdRef="BETA_CL_WT"/>
				<ct:Assign>
					<ct:Real>0.75</ct:Real>
				</ct:Assign>
			</ct:VariableAssignment>
			<ct:VariableAssignment>
				<ct:SymbRef blkIdRef="pm" symbIdRef="BETA_V_WT"/>
				<ct:Assign>
					<ct:Int>1</ct:Int>
				</ct:Assign>
			</ct:VariableAssignment>
			<ct:VariableAssignment>
				<ct:SymbRef blkIdRef="pm" symbIdRef="RUV_PROP"/>
				<ct:Assign>
					<ct:Real>0.1</ct:Real>
				</ct:Assign>
			</ct:VariableAssignment>
			<ct:VariableAssignment>
				<ct:SymbRef blkIdRef="pm" symbIdRef="RUV_ADD"/>
				<ct:Assign>
					<ct:Real>0.1</ct:Real>
				</ct:Assign>
			</ct:VariableAssignment>
			<ct:VariableAssignment>
				<ct:SymbRef blkIdRef="pm" symbIdRef="PPV_CL"/>
				<ct:Assign>
					<ct:Real>0.1</ct:Real>
				</ct:Assign>
			</ct:VariableAssignment>
			<ct:VariableAssignment>
				<ct:SymbRef blkIdRef="pm" symbIdRef="PPV_V"/>
				<ct:Assign>
					<ct:Real>0.1</ct:Real>
				</ct:Assign>
			</ct:VariableAssignment>
			<ct:VariableAssignment>
				<ct:SymbRef blkIdRef="pm" symbIdRef="PPV_KA"/>
				<ct:Assign>
					<ct:Real>0.1</ct:Real>
				</ct:Assign>
			</ct:VariableAssignment>
			<ct:VariableAssignment>
				<ct:SymbRef blkIdRef="pm" symbIdRef="PPV_TLAG"/>
				<ct:Assign>
					<ct:Real>0.1</ct:Real>
				</ct:Assign>
			</ct:VariableAssignment>
			<Operation order="1" opType="generic">
				<Property name="solver">
					<ct:Assign>
						<ct:String>nonStiff</ct:String>
					</ct:Assign>
				</Property>
			</Operation>
		</SimulationStep>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

}