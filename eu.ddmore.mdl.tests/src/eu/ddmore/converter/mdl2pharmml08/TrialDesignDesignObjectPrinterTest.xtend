package eu.ddmore.converter.mdl2pharmml08

import com.google.inject.Inject
import eu.ddmore.mdl.MdlAndLibInjectorProvider
import eu.ddmore.mdl.provider.BlockDefinitionTable
import eu.ddmore.mdl.utils.MDLBuildFixture
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.assertEquals
import eu.ddmore.mdl.MdlTestHelper
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.utils.MdlLibUtils
import org.junit.Before
import eu.ddmore.mdllib.mdllib.Library
import eu.ddmore.mdl.utils.LibraryUtils
import org.junit.Ignore

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class TrialDesignDesignObjectPrinterTest {
	@Inject extension MDLBuildFixture
	@Inject extension MdlTestHelper<Mcl>
	@Inject extension MdlLibUtils
	@Inject extension LibraryUtils
	
	var Library libDefns
	
	@Before
	def void setUp(){
				val dummyMdl = '''
			foo = mdlObj {
				
			}
		'''.parse
		
		libDefns = dummyMdl.objects.head.libraryForObject
		
	}
	
	
	@Test
	def void testWriteDesignParameters(){
		val mdl = createRoot
		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_DESIGN_PARAMS))
		desBlk.createEqnDefn("param1", createRealLiteral(22.0))
		desBlk.createEqnDefn("param2", createStringLiteral("A Val"))
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl)
		val actual = tdow.writeDesignParameters(obj.blocks.head)
		val expected = '''
			<mdef:DesignParameter symbId="param1">
				<ct:Assign>
					<ct:Real>22.0</ct:Real>
				</ct:Assign>
			</mdef:DesignParameter>
			<mdef:DesignParameter symbId="param2">
				<ct:Assign>
					<ct:String>A Val</ct:String>
				</ct:Assign>
			</mdef:DesignParameter>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteAdministrationBolusSimple(){
		val mdl = createRoot
		val mdlObj = mdl.createObject("m", libDefns.getObjectDefinition("mdlObj"))
		val mdlPredBlk = mdlObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_PRED_BLK_NAME))
		mdlPredBlk.createEqnDefn("Gut")
		
		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_INTERVENTION_BLK))
		val desParamsBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_DESIGN_PARAMS))
		val declVarBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DECLARED_VARS_BLK))
		val declDoseVar = desParamsBlk.createEqnDefn("Admin1Dose") 
		val doseTargetVar = declVarBlk.createEqnDefn("Gut") 
		val adminList = desBlk.createListDefn("admin1", createEnumPair(TrialDesignDesignObjectPrinter::INTVN_TYPE_ATT_NAME, TrialDesignDesignObjectPrinter::INTVN_TYPE_BOLUS_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::AMT_ATT_NAME, declDoseVar.createSymbolRef),
									createAssignPair(TrialDesignDesignObjectPrinter::INPUT_ATT_NAME, doseTargetVar.createSymbolRef),
									createAssignPair(TrialDesignDesignObjectPrinter::DOSE_TIME_ATT_NAME, createVectorLiteral(createRealLiteral(0.0)))
							)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl)
		val actual = tdow.writeAdministration(adminList)
		val expected = '''
			<Administration oid="admin1">
				<Bolus>
					<DoseAmount>
						<TargetMapping blkIdRef="sm">
							<ds:Map modelSymbol="Gut"/>
						</TargetMapping>
						<ct:Assign>
							<ct:SymbRef symbIdRef="Admin1Dose"/>
						</ct:Assign>
					</DoseAmount>
					<DosingTimes>
						<ct:Assign>
							<ct:Vector>
								<ct:VectorElements>
									<ct:Real>0.0</ct:Real>
								</ct:VectorElements>
							</ct:Vector>
						</ct:Assign>
					</DosingTimes>
				</Bolus>
			</Administration>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteAdministrationBolusSteadyState(){
		val mdl = createRoot
		val mdlObj = mdl.createObject("m", libDefns.getObjectDefinition("mdlObj"))
		val mdlPredBlk = mdlObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_PRED_BLK_NAME))
		mdlPredBlk.createEqnDefn("Gut")
		
		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_INTERVENTION_BLK))
		val desParamsBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_DESIGN_PARAMS))
		val declVarBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DECLARED_VARS_BLK))
		val declDoseVar = desParamsBlk.createEqnDefn("Admin1Dose") 
		val doseTargetVar = declVarBlk.createEqnDefn("Gut") 
		val adminList = desBlk.createListDefn("admin1", createEnumPair(TrialDesignDesignObjectPrinter::INTVN_TYPE_ATT_NAME, TrialDesignDesignObjectPrinter::INTVN_TYPE_BOLUS_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::AMT_ATT_NAME, declDoseVar.createSymbolRef),
									createAssignPair(TrialDesignDesignObjectPrinter::INPUT_ATT_NAME, doseTargetVar.createSymbolRef),
									createAssignPair(TrialDesignDesignObjectPrinter::SSEND_ATT_NAME, createRealLiteral(0.0))
							)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl)
		val actual = tdow.writeAdministration(adminList)
		val expected = '''
			<Administration oid="admin1">
				<Bolus>
					<DoseAmount>
						<TargetMapping blkIdRef="sm">
							<ds:Map modelSymbol="Gut"/>
						</TargetMapping>
						<ct:Assign>
							<ct:SymbRef symbIdRef="Admin1Dose"/>
						</ct:Assign>
					</DoseAmount>
					<SteadyState>
						<EndTime>
							<ct:Assign>
								<ct:Real>0.0</ct:Real>
							</ct:Assign>
						</EndTime>
					</SteadyState>
				</Bolus>
			</Administration>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteAdministrationBolusSteadyStateInterval(){
		val mdl = createRoot
		val mdlObj = mdl.createObject("m", libDefns.getObjectDefinition("mdlObj"))
		val mdlPredBlk = mdlObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_PRED_BLK_NAME))
		mdlPredBlk.createEqnDefn("Gut")
		
		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_INTERVENTION_BLK))
		val desParamsBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_DESIGN_PARAMS))
		val declVarBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DECLARED_VARS_BLK))
		val declDoseVar = desParamsBlk.createEqnDefn("Admin1Dose") 
		val doseTargetVar = declVarBlk.createEqnDefn("Gut") 
		val adminList = desBlk.createListDefn("admin1", createEnumPair(TrialDesignDesignObjectPrinter::INTVN_TYPE_ATT_NAME, TrialDesignDesignObjectPrinter::INTVN_TYPE_BOLUS_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::AMT_ATT_NAME, declDoseVar.createSymbolRef),
									createAssignPair(TrialDesignDesignObjectPrinter::INPUT_ATT_NAME, doseTargetVar.createSymbolRef),
									createAssignPair(TrialDesignDesignObjectPrinter::SSEND_ATT_NAME, createRealLiteral(0.0)),
									createAssignPair(TrialDesignDesignObjectPrinter::SSINTERVAL_ATT_NAME, createRealLiteral(10.0))
							)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl)
		val actual = tdow.writeAdministration(adminList)
		val expected = '''
			<Administration oid="admin1">
				<Bolus>
					<DoseAmount>
						<TargetMapping blkIdRef="sm">
							<ds:Map modelSymbol="Gut"/>
						</TargetMapping>
						<ct:Assign>
							<ct:SymbRef symbIdRef="Admin1Dose"/>
						</ct:Assign>
					</DoseAmount>
					<SteadyState>
						<EndTime>
							<ct:Assign>
								<ct:Real>0.0</ct:Real>
							</ct:Assign>
						</EndTime>
						<Interval>
							<ct:Assign>
								<ct:Real>10.0</ct:Real>
							</ct:Assign>
						</Interval>
					</SteadyState>
				</Bolus>
			</Administration>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteAdministrationBolusMultipleDoses(){
		val mdl = createRoot
		val mdlObj = mdl.createObject("m", libDefns.getObjectDefinition("mdlObj"))
		val mdlPredBlk = mdlObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_PRED_BLK_NAME))
		mdlPredBlk.createEqnDefn("Gut")
		
		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_INTERVENTION_BLK))
		val desParamsBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_DESIGN_PARAMS))
		val declVarBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DECLARED_VARS_BLK))
		val declDoseVar = desParamsBlk.createEqnDefn("Admin1Dose") 
		val doseTargetVar = declVarBlk.createEqnDefn("Gut") 
		val adminList = desBlk.createListDefn("admin1", createEnumPair(TrialDesignDesignObjectPrinter::INTVN_TYPE_ATT_NAME, TrialDesignDesignObjectPrinter::INTVN_TYPE_BOLUS_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::AMT_ATT_NAME, declDoseVar.createSymbolRef),
									createAssignPair(TrialDesignDesignObjectPrinter::INPUT_ATT_NAME, doseTargetVar.createSymbolRef),
									createAssignPair(TrialDesignDesignObjectPrinter::DOSE_TIME_ATT_NAME, 
										createVectorLiteral(
											createRealLiteral(0.0), createRealLiteral(10.0), createRealLiteral(60.0), createRealLiteral(120.0)
										))
							)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl)
		val actual = tdow.writeAdministration(adminList)
		val expected = '''
			<Administration oid="admin1">
				<Bolus>
					<DoseAmount>
						<TargetMapping blkIdRef="sm">
							<ds:Map modelSymbol="Gut"/>
						</TargetMapping>
						<ct:Assign>
							<ct:SymbRef symbIdRef="Admin1Dose"/>
						</ct:Assign>
					</DoseAmount>
					<DosingTimes>
						<ct:Assign>
							<ct:Vector>
								<ct:VectorElements>
									<ct:Real>0.0</ct:Real>
									<ct:Real>10.0</ct:Real>
									<ct:Real>60.0</ct:Real>
									<ct:Real>120.0</ct:Real>
								</ct:VectorElements>
							</ct:Vector>
						</ct:Assign>
					</DosingTimes>
				</Bolus>
			</Administration>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteAdministrationBolusDoseScaled(){
		val mdl = createRoot
		val mdlObj = mdl.createObject("m", libDefns.getObjectDefinition("mdlObj"))
		val mdlPredBlk = mdlObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_PRED_BLK_NAME))
		mdlPredBlk.createEqnDefn("Gut")
		
		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_INTERVENTION_BLK))
		val desParamsBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_DESIGN_PARAMS))
		val declVarBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DECLARED_VARS_BLK))
		val desCovarBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::COVARIATE_BLK_NAME))
		val declDoseVar = desParamsBlk.createEqnDefn("Admin1Dose") 
		val doseTargetVar = declVarBlk.createEqnDefn("Gut") 
		val wtCovar = desCovarBlk.createEqnDefn("WT") 
		val adminList = desBlk.createListDefn("admin1", createEnumPair(TrialDesignDesignObjectPrinter::INTVN_TYPE_ATT_NAME, TrialDesignDesignObjectPrinter::INTVN_TYPE_BOLUS_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::AMT_ATT_NAME, declDoseVar.createSymbolRef),
									createAssignPair(TrialDesignDesignObjectPrinter::INPUT_ATT_NAME, doseTargetVar.createSymbolRef),
									createAssignPair(TrialDesignDesignObjectPrinter::SCALE_ATT_NAME, wtCovar.createSymbolRef),
									createAssignPair(TrialDesignDesignObjectPrinter::DOSE_TIME_ATT_NAME, createVectorLiteral(createRealLiteral(0.0)))
							)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl)
		val actual = tdow.writeAdministration(adminList)
		val expected = '''
			<Administration oid="admin1">
				<Bolus>
					<DoseAmount>
						<TargetMapping blkIdRef="sm">
							<ds:Map modelSymbol="Gut"/>
						</TargetMapping>
						<ct:Assign>
							<math:Binop op="times">
								<ct:SymbRef symbIdRef="Admin1Dose"/>
								<ct:SymbRef blkIdRef="cm" symbIdRef="WT"/>
							</math:Binop>
						</ct:Assign>
					</DoseAmount>
					<DosingTimes>
						<ct:Assign>
							<ct:Vector>
								<ct:VectorElements>
									<ct:Real>0.0</ct:Real>
								</ct:VectorElements>
							</ct:Vector>
						</ct:Assign>
					</DosingTimes>
				</Bolus>
			</Administration>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteAdministrationBolusCmtMacroTarget(){
		val mdl = createRoot
		val mdlObj = mdl.createObject("m", libDefns.getObjectDefinition("mdlObj"))
		val mdlPredBlk = mdlObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_PRED_BLK_NAME))
		val mdlCompBlk = mdlPredBlk.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_CMT_BLK))
		val cmtDefn = mdlCompBlk.createListDefn("Gut", createEnumPair('type', 'direct'))
		val pkm = PKMacrosPrinter::INSTANCE
		pkm.resetCompartments
		pkm.defineCompartments(cmtDefn)
		
		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_INTERVENTION_BLK))
		val desParamsBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_DESIGN_PARAMS))
		val declVarBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DECLARED_VARS_BLK))
		val declDoseVar = desParamsBlk.createEqnDefn("Admin1Dose") 
		val doseTargetVar = declVarBlk.createEqnDefn("Gut") 
		val adminList = desBlk.createListDefn("admin1", createEnumPair(TrialDesignDesignObjectPrinter::INTVN_TYPE_ATT_NAME, TrialDesignDesignObjectPrinter::INTVN_TYPE_BOLUS_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::AMT_ATT_NAME, declDoseVar.createSymbolRef),
									createAssignPair(TrialDesignDesignObjectPrinter::INPUT_ATT_NAME, doseTargetVar.createSymbolRef),
									createAssignPair(TrialDesignDesignObjectPrinter::DOSE_TIME_ATT_NAME, 
										createVectorLiteral(
											createRealLiteral(0.0), createRealLiteral(10.0), createRealLiteral(60.0), createRealLiteral(120.0)
										))
							)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl)
		val actual = tdow.writeAdministration(adminList)
		val expected = '''
			<Administration oid="admin1">
				<Bolus>
					<DoseAmount>
						<TargetMapping blkIdRef="sm">
							<ds:Map admNumber="1"/>
						</TargetMapping>
						<ct:Assign>
							<ct:SymbRef symbIdRef="Admin1Dose"/>
						</ct:Assign>
					</DoseAmount>
					<DosingTimes>
						<ct:Assign>
							<ct:Vector>
								<ct:VectorElements>
									<ct:Real>0.0</ct:Real>
									<ct:Real>10.0</ct:Real>
									<ct:Real>60.0</ct:Real>
									<ct:Real>120.0</ct:Real>
								</ct:VectorElements>
							</ct:Vector>
						</ct:Assign>
					</DosingTimes>
				</Bolus>
			</Administration>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteAdministrationInfusionSimple(){
		val mdl = createRoot
		val mdlObj = mdl.createObject("m", libDefns.getObjectDefinition("mdlObj"))
		val mdlPredBlk = mdlObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_PRED_BLK_NAME))
		mdlPredBlk.createEqnDefn("Gut")
		
		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_INTERVENTION_BLK))
		val desParamsBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_DESIGN_PARAMS))
		val declVarBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DECLARED_VARS_BLK))
		val declDoseVar = desParamsBlk.createEqnDefn("Admin1Dose") 
		val doseTargetVar = declVarBlk.createEqnDefn("Gut") 
		val adminList = desBlk.createListDefn("admin1", createEnumPair(TrialDesignDesignObjectPrinter::INTVN_TYPE_ATT_NAME, TrialDesignDesignObjectPrinter::INTVN_TYPE_INFUSION_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::AMT_ATT_NAME, declDoseVar.createSymbolRef),
									createAssignPair(TrialDesignDesignObjectPrinter::INPUT_ATT_NAME, doseTargetVar.createSymbolRef),
									createAssignPair(TrialDesignDesignObjectPrinter::DOSE_TIME_ATT_NAME, createVectorLiteral(createRealLiteral(0.0)))
							)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl)
		val actual = tdow.writeAdministration(adminList)
		val expected = '''
			<Administration oid="admin1">
				<Infusion>
					<DoseAmount>
						<TargetMapping blkIdRef="sm">
							<ds:Map modelSymbol="Gut"/>
						</TargetMapping>
						<ct:Assign>
							<ct:SymbRef symbIdRef="Admin1Dose"/>
						</ct:Assign>
					</DoseAmount>
					<DosingTimes>
						<ct:Assign>
							<ct:Vector>
								<ct:VectorElements>
									<ct:Real>0.0</ct:Real>
								</ct:VectorElements>
							</ct:Vector>
						</ct:Assign>
					</DosingTimes>
				</Infusion>
			</Administration>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteAdministrationInfusionMultipleDoses(){
		val mdl = createRoot
		val mdlObj = mdl.createObject("m", libDefns.getObjectDefinition("mdlObj"))
		val mdlPredBlk = mdlObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_PRED_BLK_NAME))
		mdlPredBlk.createEqnDefn("Gut")
		
		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_INTERVENTION_BLK))
		val desParamsBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_DESIGN_PARAMS))
		val declVarBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DECLARED_VARS_BLK))
		val declDoseVar = desParamsBlk.createEqnDefn("Admin1Dose") 
		val doseTargetVar = declVarBlk.createEqnDefn("Gut") 
		val adminList = desBlk.createListDefn("admin1", createEnumPair(TrialDesignDesignObjectPrinter::INTVN_TYPE_ATT_NAME, TrialDesignDesignObjectPrinter::INTVN_TYPE_INFUSION_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::AMT_ATT_NAME, declDoseVar.createSymbolRef),
									createAssignPair(TrialDesignDesignObjectPrinter::INPUT_ATT_NAME, doseTargetVar.createSymbolRef),
									createAssignPair(TrialDesignDesignObjectPrinter::DOSE_TIME_ATT_NAME, 
										createVectorLiteral(
											createRealLiteral(0.0), createRealLiteral(10.0), createRealLiteral(60.0), createRealLiteral(120.0)
										))
							)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl)
		val actual = tdow.writeAdministration(adminList)
		val expected = '''
			<Administration oid="admin1">
				<Infusion>
					<DoseAmount>
						<TargetMapping blkIdRef="sm">
							<ds:Map modelSymbol="Gut"/>
						</TargetMapping>
						<ct:Assign>
							<ct:SymbRef symbIdRef="Admin1Dose"/>
						</ct:Assign>
					</DoseAmount>
					<DosingTimes>
						<ct:Assign>
							<ct:Vector>
								<ct:VectorElements>
									<ct:Real>0.0</ct:Real>
									<ct:Real>10.0</ct:Real>
									<ct:Real>60.0</ct:Real>
									<ct:Real>120.0</ct:Real>
								</ct:VectorElements>
							</ct:Vector>
						</ct:Assign>
					</DosingTimes>
				</Infusion>
			</Administration>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteAdministrationInfusionDoseScaled(){
		val mdl = createRoot
		val mdlObj = mdl.createObject("m", libDefns.getObjectDefinition("mdlObj"))
		val mdlPredBlk = mdlObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_PRED_BLK_NAME))
		mdlPredBlk.createEqnDefn("Gut")
		
		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_INTERVENTION_BLK))
		val desParamsBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_DESIGN_PARAMS))
		val declVarBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DECLARED_VARS_BLK))
		val desCovarBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::COVARIATE_BLK_NAME))
		val declDoseVar = desParamsBlk.createEqnDefn("Admin1Dose") 
		val doseTargetVar = declVarBlk.createEqnDefn("Gut") 
		val wtCovar = desCovarBlk.createEqnDefn("WT") 
		val adminList = desBlk.createListDefn("admin1", createEnumPair(TrialDesignDesignObjectPrinter::INTVN_TYPE_ATT_NAME, TrialDesignDesignObjectPrinter::INTVN_TYPE_INFUSION_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::AMT_ATT_NAME, declDoseVar.createSymbolRef),
									createAssignPair(TrialDesignDesignObjectPrinter::INPUT_ATT_NAME, doseTargetVar.createSymbolRef),
									createAssignPair(TrialDesignDesignObjectPrinter::SCALE_ATT_NAME, wtCovar.createSymbolRef),
									createAssignPair(TrialDesignDesignObjectPrinter::DOSE_TIME_ATT_NAME, createVectorLiteral(createRealLiteral(0.0)))
							)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl)
		val actual = tdow.writeAdministration(adminList)
		val expected = '''
			<Administration oid="admin1">
				<Infusion>
					<DoseAmount>
						<TargetMapping blkIdRef="sm">
							<ds:Map modelSymbol="Gut"/>
						</TargetMapping>
						<ct:Assign>
							<math:Binop op="times">
								<ct:SymbRef symbIdRef="Admin1Dose"/>
								<ct:SymbRef blkIdRef="cm" symbIdRef="WT"/>
							</math:Binop>
						</ct:Assign>
					</DoseAmount>
					<DosingTimes>
						<ct:Assign>
							<ct:Vector>
								<ct:VectorElements>
									<ct:Real>0.0</ct:Real>
								</ct:VectorElements>
							</ct:Vector>
						</ct:Assign>
					</DosingTimes>
				</Infusion>
			</Administration>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteAdministrationInfusionCmtMacroTarget(){
		val mdl = createRoot
		val mdlObj = mdl.createObject("m", libDefns.getObjectDefinition("mdlObj"))
		val mdlPredBlk = mdlObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_PRED_BLK_NAME))
		val mdlCompBlk = mdlPredBlk.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_CMT_BLK))
		val cmtDefn = mdlCompBlk.createListDefn("Gut", createEnumPair('type', 'direct'))
		val pkm = PKMacrosPrinter::INSTANCE
		pkm.resetCompartments
		pkm.defineCompartments(cmtDefn)
		
		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_INTERVENTION_BLK))
		val desParamsBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_DESIGN_PARAMS))
		val declVarBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DECLARED_VARS_BLK))
		val declDoseVar = desParamsBlk.createEqnDefn("Admin1Dose") 
		val doseTargetVar = declVarBlk.createEqnDefn("Gut") 
		val adminList = desBlk.createListDefn("admin1", createEnumPair(TrialDesignDesignObjectPrinter::INTVN_TYPE_ATT_NAME, TrialDesignDesignObjectPrinter::INTVN_TYPE_INFUSION_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::AMT_ATT_NAME, declDoseVar.createSymbolRef),
									createAssignPair(TrialDesignDesignObjectPrinter::INPUT_ATT_NAME, doseTargetVar.createSymbolRef),
									createAssignPair(TrialDesignDesignObjectPrinter::DOSE_TIME_ATT_NAME, 
										createVectorLiteral(
											createRealLiteral(0.0), createRealLiteral(10.0), createRealLiteral(60.0), createRealLiteral(120.0)
										))
							)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl)
		val actual = tdow.writeAdministration(adminList)
		val expected = '''
			<Administration oid="admin1">
				<Infusion>
					<DoseAmount>
						<TargetMapping blkIdRef="sm">
							<ds:Map admNumber="1"/>
						</TargetMapping>
						<ct:Assign>
							<ct:SymbRef symbIdRef="Admin1Dose"/>
						</ct:Assign>
					</DoseAmount>
					<DosingTimes>
						<ct:Assign>
							<ct:Vector>
								<ct:VectorElements>
									<ct:Real>0.0</ct:Real>
									<ct:Real>10.0</ct:Real>
									<ct:Real>60.0</ct:Real>
									<ct:Real>120.0</ct:Real>
								</ct:VectorElements>
							</ct:Vector>
						</ct:Assign>
					</DosingTimes>
				</Infusion>
			</Administration>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteCombi(){
		val mdl = createRoot
		
		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_INTERVENTION_BLK))
		val admin1List = desBlk.createListDefn("admin1", createEnumPair(TrialDesignDesignObjectPrinter::INTVN_TYPE_ATT_NAME, TrialDesignDesignObjectPrinter::INTVN_TYPE_BOLUS_VALUE))
		val admin2List = desBlk.createListDefn("admin2", createEnumPair(TrialDesignDesignObjectPrinter::INTVN_TYPE_ATT_NAME, TrialDesignDesignObjectPrinter::INTVN_TYPE_BOLUS_VALUE))
		val combiList = desBlk.createListDefn("regimen1", createEnumPair(TrialDesignDesignObjectPrinter::INTVN_TYPE_ATT_NAME, TrialDesignDesignObjectPrinter::INTVN_TYPE_COMBI_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::COMBINATION_ATT_NAME, createVectorLiteral(createSymbolRef(admin2List), createSymbolRef(admin1List))),
									createAssignPair(TrialDesignDesignObjectPrinter::START_ATT_NAME, createRealLiteral(0)),
									createAssignPair(TrialDesignDesignObjectPrinter::END_ATT_NAME, createRealLiteral(202.1))
							)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl)
		val actual = tdow.writeInterventionCombination(combiList)
		val expected = '''
			<InterventionsCombination oid="regimen1">
				<Interventions>
					<InterventionRef oidRef="admin2"/>
					<InterventionRef oidRef="admin1"/>
					<Start>
						<ct:Assign>
							<ct:Real>0.0</ct:Real>
						</ct:Assign>
					</Start>
					<End>
						<ct:Assign>
							<ct:Real>202.1</ct:Real>
						</ct:Assign>
					</End>
				</Interventions>
			</InterventionsCombination>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Ignore("Not finished.")
	def void testWriteResetAllDefaultValue(){
		val mdl = createRoot
		
		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_INTERVENTION_BLK))
		val actionList = desBlk.createListDefn("type", createEnumPair(TrialDesignDesignObjectPrinter::INTVN_TYPE_ATT_NAME, TrialDesignDesignObjectPrinter::INTVN_TYPE_RESET_ALL_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::START_ATT_NAME, createRealLiteral(10))
		)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl)
		val actual = tdow.writeInterventionCombination(actionList)
		val expected = '''
			<InterventionsCombination oid="regimen1">
				<Interventions>
					<InterventionRef oidRef="admin2"/>
					<InterventionRef oidRef="admin1"/>
					<Start>
						<ct:Assign>
							<ct:Real>0.0</ct:Real>
						</ct:Assign>
					</Start>
					<End>
						<ct:Assign>
							<ct:Real>202.1</ct:Real>
						</ct:Assign>
					</End>
				</Interventions>
			</InterventionsCombination>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

//	@Test
//	def void testWriteStudyDesign(){
//		val mdl = createRoot
//		
//		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
//		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_STUDY_DESIGN))
//
//		val declVarBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DECLARED_VARS_BLK))
//
//		val adminList = desBlk.createListDefn("arm1", createEnumPair(TrialDesignDesignObjectPrinter::INTVN_TYPE_ATT_NAME, TrialDesignDesignObjectPrinter::INTVN_TYPE_BOLUS_VALUE),
//									createAssignPair(TrialDesignDesignObjectPrinter::DOSE_TIME_ATT_NAME, createVectorLiteral(createRealLiteral(0.0)))
//							)
//		
//		val tdow = new TrialDesignDesignObjectPrinter(mdl)
//		val actual = tdow.writeAdministration(adminList)
//		val expected = '''
//			<Administration oid="admin1">
//				<Bolus>
//					<DoseAmount>
//						<TargetMapping blkIdRef="sm">
//							<ds:Map modelSymbol="Gut"/>
//						</TargetMapping>
//						<ct:Assign>
//							<ct:SymbRef symbIdRef="Admin1Dose"/>
//						</ct:Assign>
//					</DoseAmount>
//					<DosingTimes>
//						<ct:Assign>
//							<ct:Vector>
//								<ct:VectorElements>
//									<ct:Real>0.0</ct:Real>
//								</ct:VectorElements>
//							</ct:Vector>
//						</ct:Assign>
//					</DosingTimes>
//				</Bolus>
//			</Administration>
//		'''
//		assertEquals("Output as expected", expected, actual.toString)
//	}

}