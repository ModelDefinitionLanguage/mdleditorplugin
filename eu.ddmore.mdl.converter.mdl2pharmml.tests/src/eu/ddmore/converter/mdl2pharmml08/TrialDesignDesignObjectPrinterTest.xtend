package eu.ddmore.converter.mdl2pharmml08

import com.google.inject.Inject
import eu.ddmore.mdl.MdlAndLibInjectorProvider
import eu.ddmore.mdl.MdlTestHelper
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.provider.BlockDefinitionTable
import eu.ddmore.mdl.utils.LibraryUtils
import eu.ddmore.mdl.utils.MDLBuildFixture
import eu.ddmore.mdl.utils.MdlLibUtils
import eu.ddmore.mdllib.mdllib.Library
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.assertEquals
import eu.ddmore.mdl.mdl.MclObject
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
	
	def private createMogDefn(Mcl it, MclObject desObj){
		val mogObj = createObject("foo", libDefns.getObjectDefinition("mogObj"))
		val objBlk = mogObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MOG_OBJ_NAME))
		objBlk.createListDefn(desObj.name, createEnumPair('type', 'designObj'))
		return mogObj
	}
	
	def private createMogDefn(Mcl it, MclObject desObj, MclObject mdlObj){
		val mogObj = createObject("foo", libDefns.getObjectDefinition("mogObj"))
		val objBlk = mogObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MOG_OBJ_NAME))
		objBlk.createListDefn(desObj.name, createEnumPair('type', 'designObj'))
		objBlk.createListDefn(mdlObj.name, createEnumPair('type', 'mdlObj'))
		return mogObj
	}

	@Test
	def void testWriteDesignParameters(){
		val mdl = createRoot
		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val mog = mdl.createMogDefn(obj)
		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_DESIGN_PARAMS))
		desBlk.createEqnDefn("param1", createRealLiteral(22.0))
		desBlk.createEqnDefn("param2", createStringLiteral("A Val"))
		
		val tdow = new TrialDesignDesignObjectPrinter(mog, new StandardParameterWriter(null))
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
									createAssignPair(TrialDesignDesignObjectPrinter::AMT_ATT_NAME, createVectorLiteral(declDoseVar.createSymbolRef)),
									createAssignPair(TrialDesignDesignObjectPrinter::INPUT_ATT_NAME, doseTargetVar.createSymbolRef),
									createAssignPair(TrialDesignDesignObjectPrinter::DOSE_TIME_ATT_NAME, createVectorLiteral(createRealLiteral(0.0)))
							)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl.createMogDefn(obj, mdlObj)
, new StandardParameterWriter(null))
		val actual = tdow.writeBolusDosing(adminList)
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
	def void testWriteAdministrationBolusMultiDoseAmts(){
		val mdl = createRoot
		val mdlObj = mdl.createObject("m", libDefns.getObjectDefinition("mdlObj"))
		val mdlPredBlk = mdlObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_PRED_BLK_NAME))
		mdlPredBlk.createEqnDefn("Gut")
		
		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_INTERVENTION_BLK))
//		val desParamsBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_DESIGN_PARAMS))
		val declVarBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DECLARED_VARS_BLK))
		val doseTargetVar = declVarBlk.createEqnDefn("Gut") 
		val adminList = desBlk.createListDefn("admin1", createEnumPair(TrialDesignDesignObjectPrinter::INTVN_TYPE_ATT_NAME, TrialDesignDesignObjectPrinter::INTVN_TYPE_BOLUS_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::AMT_ATT_NAME, createVectorLiteral(
										createRealLiteral(10.0), createRealLiteral(20.0), createRealLiteral(30.0), createRealLiteral(40.0)
									)),
									createAssignPair(TrialDesignDesignObjectPrinter::INPUT_ATT_NAME, doseTargetVar.createSymbolRef),
									createAssignPair(TrialDesignDesignObjectPrinter::DOSE_TIME_ATT_NAME, createVectorLiteral(createRealLiteral(0.0)))
							)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl.createMogDefn(obj, mdlObj)
, new StandardParameterWriter(null))
		val actual = tdow.writeBolusDosing(adminList)
		val expected = '''
			<Administration oid="admin1">
				<Bolus>
					<DoseAmount>
						<TargetMapping blkIdRef="sm">
							<ds:Map modelSymbol="Gut"/>
						</TargetMapping>
						<ct:Assign>
							<ct:Vector>
								<ct:VectorElements>
									<ct:Real>10.0</ct:Real>
									<ct:Real>20.0</ct:Real>
									<ct:Real>30.0</ct:Real>
									<ct:Real>40.0</ct:Real>
								</ct:VectorElements>
							</ct:Vector>
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
	def void testWriteAdministrationBolusMultiDoseAmtsWithScale(){
		val mdl = createRoot
		val mdlObj = mdl.createObject("m", libDefns.getObjectDefinition("mdlObj"))
		val mdlPredBlk = mdlObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_PRED_BLK_NAME))
		mdlPredBlk.createEqnDefn("Gut")
		
		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_INTERVENTION_BLK))
//		val desParamsBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_DESIGN_PARAMS))
		val declVarBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DECLARED_VARS_BLK))
		val doseTargetVar = declVarBlk.createEqnDefn("Gut") 
		val adminList = desBlk.createListDefn("admin1", createEnumPair(TrialDesignDesignObjectPrinter::INTVN_TYPE_ATT_NAME, TrialDesignDesignObjectPrinter::INTVN_TYPE_BOLUS_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::AMT_ATT_NAME, createVectorLiteral(
										createRealLiteral(10.0), createRealLiteral(20.0), createRealLiteral(30.0), createRealLiteral(40.0)
									)),
									createAssignPair(TrialDesignDesignObjectPrinter::SCALE_ATT_NAME, createRealLiteral(10.0)),
									createAssignPair(TrialDesignDesignObjectPrinter::INPUT_ATT_NAME, doseTargetVar.createSymbolRef),
									createAssignPair(TrialDesignDesignObjectPrinter::DOSE_TIME_ATT_NAME, createVectorLiteral(createRealLiteral(0.0)))
							)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl.createMogDefn(obj, mdlObj)
, new StandardParameterWriter(null))
		val actual = tdow.writeBolusDosing(adminList)
		val expected = '''
			<Administration oid="admin1">
				<Bolus>
					<DoseAmount>
						<TargetMapping blkIdRef="sm">
							<ds:Map modelSymbol="Gut"/>
						</TargetMapping>
						<ct:Assign>
							<ct:Vector>
								<ct:VectorElements>
									<ct:Assign>
										<math:Binop op="times">
											<ct:Real>10.0</ct:Real>
											<ct:Real>10.0</ct:Real>
										</math:Binop>
									</ct:Assign>
									<ct:Assign>
										<math:Binop op="times">
											<ct:Real>20.0</ct:Real>
											<ct:Real>10.0</ct:Real>
										</math:Binop>
									</ct:Assign>
									<ct:Assign>
										<math:Binop op="times">
											<ct:Real>30.0</ct:Real>
											<ct:Real>10.0</ct:Real>
										</math:Binop>
									</ct:Assign>
									<ct:Assign>
										<math:Binop op="times">
											<ct:Real>40.0</ct:Real>
											<ct:Real>10.0</ct:Real>
										</math:Binop>
									</ct:Assign>
								</ct:VectorElements>
							</ct:Vector>
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
	def void testWriteAdministrationBolusSimpleWithDoseTimeVar(){
		val mdl = createRoot
		val mdlObj = mdl.createObject("m", libDefns.getObjectDefinition("mdlObj"))
		val mdlPredBlk = mdlObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_PRED_BLK_NAME))
		mdlPredBlk.createEqnDefn("Gut")
		mdlPredBlk.createEqnDefn("DT")
		
		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_INTERVENTION_BLK))
		val desParamsBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_DESIGN_PARAMS))
		val declVarBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DECLARED_VARS_BLK))
		val declDoseVar = desParamsBlk.createEqnDefn("Admin1Dose") 
		val doseTargetVar = declVarBlk.createEqnDefn("Gut") 
		val declDTVar = declVarBlk.createEqnDefn("DT") 
		val adminList = desBlk.createListDefn("admin1", createEnumPair(TrialDesignDesignObjectPrinter::INTVN_TYPE_ATT_NAME, TrialDesignDesignObjectPrinter::INTVN_TYPE_BOLUS_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::AMT_ATT_NAME, createVectorLiteral(declDoseVar.createSymbolRef)),
									createAssignPair(TrialDesignDesignObjectPrinter::INPUT_ATT_NAME, doseTargetVar.createSymbolRef),
									createAssignPair(TrialDesignDesignObjectPrinter::DOSE_TIME_ATT_NAME, createVectorLiteral(createRealLiteral(0.0))),
									createAssignPair(TrialDesignDesignObjectPrinter::DOSE_TIME_VAR_ATT_NAME, declDTVar.createSymbolRef)
							)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl.createMogDefn(obj, mdlObj)
, new StandardParameterWriter(null))
		val actual = tdow.writeBolusDosing(adminList)
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
						<ct:SymbRef blkIdRef="sm" symbIdRef="DT"/>
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
									createAssignPair(TrialDesignDesignObjectPrinter::AMT_ATT_NAME, createVectorLiteral(declDoseVar.createSymbolRef)),
									createAssignPair(TrialDesignDesignObjectPrinter::INPUT_ATT_NAME, doseTargetVar.createSymbolRef),
									createAssignPair(TrialDesignDesignObjectPrinter::SSEND_ATT_NAME, createRealLiteral(0.0))
							)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl.createMogDefn(obj, mdlObj), new StandardParameterWriter(null))
		val actual = tdow.writeBolusDosing(adminList)
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
									createAssignPair(TrialDesignDesignObjectPrinter::AMT_ATT_NAME, createVectorLiteral(declDoseVar.createSymbolRef)),
									createAssignPair(TrialDesignDesignObjectPrinter::INPUT_ATT_NAME, doseTargetVar.createSymbolRef),
									createAssignPair(TrialDesignDesignObjectPrinter::SSEND_ATT_NAME, createRealLiteral(0.0)),
									createAssignPair(TrialDesignDesignObjectPrinter::SSINTERVAL_ATT_NAME, createRealLiteral(10.0))
							)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl.createMogDefn(obj, mdlObj), new StandardParameterWriter(null))
		val actual = tdow.writeBolusDosing(adminList)
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
									createAssignPair(TrialDesignDesignObjectPrinter::AMT_ATT_NAME, createVectorLiteral(declDoseVar.createSymbolRef)),
									createAssignPair(TrialDesignDesignObjectPrinter::INPUT_ATT_NAME, doseTargetVar.createSymbolRef),
									createAssignPair(TrialDesignDesignObjectPrinter::DOSE_TIME_ATT_NAME, 
										createVectorLiteral(
											createRealLiteral(0.0), createRealLiteral(10.0), createRealLiteral(60.0), createRealLiteral(120.0)
										))
							)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl.createMogDefn(obj, mdlObj), new StandardParameterWriter(null))
		val actual = tdow.writeBolusDosing(adminList)
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
									createAssignPair(TrialDesignDesignObjectPrinter::AMT_ATT_NAME, createVectorLiteral(declDoseVar.createSymbolRef)),
									createAssignPair(TrialDesignDesignObjectPrinter::INPUT_ATT_NAME, doseTargetVar.createSymbolRef),
									createAssignPair(TrialDesignDesignObjectPrinter::SCALE_ATT_NAME, wtCovar.createSymbolRef),
									createAssignPair(TrialDesignDesignObjectPrinter::DOSE_TIME_ATT_NAME, createVectorLiteral(createRealLiteral(0.0)))
							)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl.createMogDefn(obj, mdlObj), new StandardParameterWriter(null))
		val actual = tdow.writeBolusDosing(adminList)
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
									createAssignPair(TrialDesignDesignObjectPrinter::AMT_ATT_NAME, createVectorLiteral(declDoseVar.createSymbolRef)),
									createAssignPair(TrialDesignDesignObjectPrinter::INPUT_ATT_NAME, doseTargetVar.createSymbolRef),
									createAssignPair(TrialDesignDesignObjectPrinter::DOSE_TIME_ATT_NAME, 
										createVectorLiteral(
											createRealLiteral(0.0), createRealLiteral(10.0), createRealLiteral(60.0), createRealLiteral(120.0)
										))
							)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl.createMogDefn(obj, mdlObj), new StandardParameterWriter(null))
		val actual = tdow.writeBolusDosing(adminList)
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
									createAssignPair(TrialDesignDesignObjectPrinter::RATE_ATT_NAME, createRealLiteral(2.0)),
									createAssignPair(TrialDesignDesignObjectPrinter::AMT_ATT_NAME, createVectorLiteral(declDoseVar.createSymbolRef)),
									createAssignPair(TrialDesignDesignObjectPrinter::INPUT_ATT_NAME, doseTargetVar.createSymbolRef),
									createAssignPair(TrialDesignDesignObjectPrinter::DOSE_TIME_ATT_NAME, createVectorLiteral(createRealLiteral(0.0)))
							)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl.createMogDefn(obj, mdlObj), new StandardParameterWriter(null))
		val actual = tdow.writeInfusionDosing(adminList)
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
					<Rate>
						<ct:Assign>
							<ct:Real>2.0</ct:Real>
						</ct:Assign>
					</Rate>
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
									createAssignPair(TrialDesignDesignObjectPrinter::AMT_ATT_NAME, createVectorLiteral(declDoseVar.createSymbolRef)),
									createAssignPair(TrialDesignDesignObjectPrinter::RATE_ATT_NAME, createRealLiteral(3.0)),
									createAssignPair(TrialDesignDesignObjectPrinter::INPUT_ATT_NAME, doseTargetVar.createSymbolRef),
									createAssignPair(TrialDesignDesignObjectPrinter::DOSE_TIME_ATT_NAME, 
										createVectorLiteral(
											createRealLiteral(0.0), createRealLiteral(10.0), createRealLiteral(60.0), createRealLiteral(120.0)
										))
							)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl.createMogDefn(obj, mdlObj), new StandardParameterWriter(null))
		val actual = tdow.writeInfusionDosing(adminList)
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
					<Rate>
						<ct:Assign>
							<ct:Real>3.0</ct:Real>
						</ct:Assign>
					</Rate>
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
									createAssignPair(TrialDesignDesignObjectPrinter::AMT_ATT_NAME, createVectorLiteral(declDoseVar.createSymbolRef)),
									createAssignPair(TrialDesignDesignObjectPrinter::INPUT_ATT_NAME, doseTargetVar.createSymbolRef),
									createAssignPair(TrialDesignDesignObjectPrinter::DURATION_ATT_NAME, createRealLiteral(3.0)),
									createAssignPair(TrialDesignDesignObjectPrinter::SCALE_ATT_NAME, wtCovar.createSymbolRef),
									createAssignPair(TrialDesignDesignObjectPrinter::DOSE_TIME_ATT_NAME, createVectorLiteral(createRealLiteral(0.0)))
							)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl.createMogDefn(obj, mdlObj), new StandardParameterWriter(null))
		val actual = tdow.writeInfusionDosing(adminList)
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
					<Duration>
						<ct:Assign>
							<ct:Real>3.0</ct:Real>
						</ct:Assign>
					</Duration>
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
									createAssignPair(TrialDesignDesignObjectPrinter::AMT_ATT_NAME, createVectorLiteral(declDoseVar.createSymbolRef)),
									createAssignPair(TrialDesignDesignObjectPrinter::DURATION_ATT_NAME, createRealLiteral(3.0)),
									createAssignPair(TrialDesignDesignObjectPrinter::INPUT_ATT_NAME, doseTargetVar.createSymbolRef),
									createAssignPair(TrialDesignDesignObjectPrinter::DOSE_TIME_ATT_NAME, 
										createVectorLiteral(
											createRealLiteral(0.0), createRealLiteral(10.0), createRealLiteral(60.0), createRealLiteral(120.0)
										))
							)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl.createMogDefn(obj, mdlObj), new StandardParameterWriter(null))
		val actual = tdow.writeInfusionDosing(adminList)
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
					<Duration>
						<ct:Assign>
							<ct:Real>3.0</ct:Real>
						</ct:Assign>
					</Duration>
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
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl.createMogDefn(obj), new StandardParameterWriter(null))
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

	@Test
	def void testWriteResetAllDefaultValue(){
		val mdl = createRoot
		
		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_INTERVENTION_BLK))
		val actionList = desBlk.createListDefn("act1", createEnumPair(TrialDesignDesignObjectPrinter::INTVN_TYPE_ATT_NAME, TrialDesignDesignObjectPrinter::INTVN_TYPE_RESET_ALL_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::START_ATT_NAME, createRealLiteral(10))
		)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl.createMogDefn(obj), new StandardParameterWriter(null))
		val actual = tdow.writeResetAll(actionList)
		val expected = '''
			<Action oid="act1">
				<Washout>
					<VariableToReset>
						<FullReset/>
					</VariableToReset>
				</Washout>
			</Action>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteResetDefaultValueAndTime(){
		val mdl = createRoot
		
		val mdlObj = mdl.createObject("m", libDefns.getObjectDefinition("mdlObj"))
		val mdlPredBlk = mdlObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_PRED_BLK_NAME))
		mdlPredBlk.createEqnDefn("Gut")

		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val declVarBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DECLARED_VARS_BLK))
		val rsVar1 = declVarBlk.createEqnDefn("Gut") 

		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_INTERVENTION_BLK))
		val actionList = desBlk.createListDefn("act1",
									createEnumPair(TrialDesignDesignObjectPrinter::INTVN_TYPE_ATT_NAME, TrialDesignDesignObjectPrinter::INTVN_TYPE_RESET_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::RESET_ATT, createVectorLiteral( 
											createSublist(#{
												TrialDesignDesignObjectPrinter::RESET_VARIABLE -> createSymbolRef(rsVar1)
											})
										)
									)
								)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl.createMogDefn(obj, mdlObj), new StandardParameterWriter(null))
		val actual = tdow.writeReset(actionList)
		val expected = '''
			<Action oid="act1">
				<Washout>
					<VariableToReset>
						<ct:SymbRef blkIdRef="sm" symbIdRef="Gut"/>
					</VariableToReset>
				</Washout>
			</Action>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteResetWithSingleValueAndTime(){
		val mdl = createRoot
		
		val mdlObj = mdl.createObject("m", libDefns.getObjectDefinition("mdlObj"))
		val mdlPredBlk = mdlObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_PRED_BLK_NAME))
		mdlPredBlk.createEqnDefn("Gut")

		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val declVarBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DECLARED_VARS_BLK))
		val rsVar1 = declVarBlk.createEqnDefn("Gut") 

		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_INTERVENTION_BLK))
		val actionList = desBlk.createListDefn("act1",
									createEnumPair(TrialDesignDesignObjectPrinter::INTVN_TYPE_ATT_NAME, TrialDesignDesignObjectPrinter::INTVN_TYPE_RESET_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::RESET_ATT, createVectorLiteral( 
											createSublist(#{
												TrialDesignDesignObjectPrinter::RESET_VARIABLE -> createSymbolRef(rsVar1),
												TrialDesignDesignObjectPrinter::RESET_TIME_ATT -> createRealLiteral(11),
												TrialDesignDesignObjectPrinter::RESET_VALUE_ATT -> createRealLiteral(100.0)
											})
										)
									)
								)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl.createMogDefn(obj, mdlObj), new StandardParameterWriter(null))
		val actual = tdow.writeReset(actionList)
		val expected = '''
			<Action oid="act1">
				<Washout>
					<VariableToReset>
						<ct:SymbRef blkIdRef="sm" symbIdRef="Gut"/>
						<ResetValue>
							<ct:Real>100.0</ct:Real>
						</ResetValue>
						<ResetTime>
							<ct:Real>11.0</ct:Real>
						</ResetTime>
					</VariableToReset>
				</Washout>
			</Action>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteResetMultiVarsWithValue(){
		val mdl = createRoot
		
		val mdlObj = mdl.createObject("m", libDefns.getObjectDefinition("mdlObj"))
		val mdlPredBlk = mdlObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_PRED_BLK_NAME))
		mdlPredBlk.createEqnDefn("Gut1")
		mdlPredBlk.createEqnDefn("Gut2")

		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val declVarBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DECLARED_VARS_BLK))
		val rsVar1 = declVarBlk.createEqnDefn("Gut1") 
		val rsVar2 = declVarBlk.createEqnDefn("Gut2") 

		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_INTERVENTION_BLK))
		val actionList = desBlk.createListDefn("act1",
									createEnumPair(TrialDesignDesignObjectPrinter::INTVN_TYPE_ATT_NAME, TrialDesignDesignObjectPrinter::INTVN_TYPE_RESET_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::RESET_ATT, createVectorLiteral( 
											createSublist(#{
												TrialDesignDesignObjectPrinter::RESET_VARIABLE -> createSymbolRef(rsVar1),
												TrialDesignDesignObjectPrinter::RESET_TIME_ATT -> createRealLiteral(11),
												TrialDesignDesignObjectPrinter::RESET_VALUE_ATT -> createRealLiteral(100.0)
											}),
											createSublist(#{
												TrialDesignDesignObjectPrinter::RESET_VARIABLE -> createSymbolRef(rsVar2),
												TrialDesignDesignObjectPrinter::RESET_TIME_ATT -> createRealLiteral(119),
												TrialDesignDesignObjectPrinter::RESET_VALUE_ATT -> createRealLiteral(110.0)
											})
										)
									)
									)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl.createMogDefn(obj, mdlObj), new StandardParameterWriter(null))
		val actual = tdow.writeReset(actionList)
		val expected = '''
			<Action oid="act1">
				<Washout>
					<VariableToReset>
						<ct:SymbRef blkIdRef="sm" symbIdRef="Gut1"/>
						<ResetValue>
							<ct:Real>100.0</ct:Real>
						</ResetValue>
						<ResetTime>
							<ct:Real>11.0</ct:Real>
						</ResetTime>
					</VariableToReset>
					<VariableToReset>
						<ct:SymbRef blkIdRef="sm" symbIdRef="Gut2"/>
						<ResetValue>
							<ct:Real>110.0</ct:Real>
						</ResetValue>
						<ResetTime>
							<ct:Real>119.0</ct:Real>
						</ResetTime>
					</VariableToReset>
				</Washout>
			</Action>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteStudyDesignInterventionsAndSamples(){
		val mdl = createRoot
		
		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_STUDY_DESIGN))

		val intBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_INTERVENTION_BLK))
		val adminList = intBlk.createListDefn("arm1");
		val sampBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_SAMPLING_BLK))
		val sampList = sampBlk.createListDefn("samp1");
		
		desBlk.createPropertyStatement(createAssignPair(TrialDesignDesignObjectPrinter::TOTAL_SIZE_PROP, createIntLiteral(33)),
										createAssignPair(TrialDesignDesignObjectPrinter::NUM_SAMPLES_PROP, createVectorLiteral(createIntLiteral(5), createIntLiteral(7))),
										createAssignPair(TrialDesignDesignObjectPrinter::NUM_ARMS_PROP, createIntLiteral(6)),
										createAssignPair(TrialDesignDesignObjectPrinter::SAME_TIMES_PROP, createBooleanLiteral(true)),
										createAssignPair(TrialDesignDesignObjectPrinter::TOTAL_COST_PROP, createRealLiteral(22.643))
									)
		
		desBlk.createListDefn("arm1",
									createAssignPair(TrialDesignDesignObjectPrinter::ARM_SIZE_ATT, createIntLiteral(1)),
									createAssignPair(TrialDesignDesignObjectPrinter::INTSEQ_ATT, createVectorLiteral(createSublist(#{
																	TrialDesignDesignObjectPrinter::INTSEQ_ADMIN_ATT -> createVectorLiteral(createSymbolRef(adminList)),
																	TrialDesignDesignObjectPrinter::START_ATT_NAME -> createVectorLiteral(createRealLiteral(0.0))
																}))),
									createAssignPair(TrialDesignDesignObjectPrinter::SAMPSEQ_ATT, createVectorLiteral(createSublist(#{
																	TrialDesignDesignObjectPrinter::SAMPSEQ_SAMP_ATT -> createVectorLiteral(createSymbolRef(sampList)),
																	TrialDesignDesignObjectPrinter::START_ATT_NAME -> createVectorLiteral(createRealLiteral(10.0))
																})))
							)
		desBlk.createListDefn("arm2",
									createAssignPair(TrialDesignDesignObjectPrinter::ARM_SIZE_ATT, createIntLiteral(1)),
									createAssignPair(TrialDesignDesignObjectPrinter::INTSEQ_ATT, createVectorLiteral(createSublist(#{
																	TrialDesignDesignObjectPrinter::INTSEQ_ADMIN_ATT -> createVectorLiteral(createSymbolRef(adminList)),
																	TrialDesignDesignObjectPrinter::START_ATT_NAME -> createVectorLiteral(createRealLiteral(0.0))
																}))),
									createAssignPair(TrialDesignDesignObjectPrinter::SAMPSEQ_ATT, createVectorLiteral(createSublist(#{
																	TrialDesignDesignObjectPrinter::SAMPSEQ_SAMP_ATT -> createVectorLiteral(createSymbolRef(sampList)),
																	TrialDesignDesignObjectPrinter::START_ATT_NAME -> createVectorLiteral(createRealLiteral(10.0))
																})))
							)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl.createMogDefn(obj), new StandardParameterWriter(null))
		val actual = tdow.writeStudyDesign(desBlk)
		val expected = '''
		<Arms>
			<TotalSize>
				<ct:Assign>
					<ct:Int>33</ct:Int>
				</ct:Assign>
			</TotalSize>
			<NumberSamples>
				<ct:Assign>
					<ct:Vector>
						<ct:VectorElements>
							<ct:Int>5</ct:Int>
							<ct:Int>7</ct:Int>
						</ct:VectorElements>
					</ct:Vector>
				</ct:Assign>
			</NumberSamples>
			<NumberArms>
				<ct:Assign>
					<ct:Int>6</ct:Int>
				</ct:Assign>
			</NumberArms>
			<SameTimes>
				<ct:Assign>
					<ct:True/>
				</ct:Assign>
			</SameTimes>
			<TotalCost>
				<ct:Assign>
					<ct:Real>22.643</ct:Real>
				</ct:Assign>
			</TotalCost>
			<Arm oid="arm1">
				<ArmSize>
					<ct:Assign>
						<ct:Int>1</ct:Int>
					</ct:Assign>
				</ArmSize>
				<InterventionSequence>
					<InterventionList>
						<InterventionRef oidRef="arm1"/>
					</InterventionList>
					<Start>
						<ct:Assign>
							<ct:Vector>
								<ct:VectorElements>
									<ct:Real>0.0</ct:Real>
								</ct:VectorElements>
							</ct:Vector>
						</ct:Assign>
					</Start>
				</InterventionSequence>
				<ObservationSequence>
					<ObservationList>
						<ObservationRef oidRef="samp1"/>
					</ObservationList>
					<Start>
						<ct:Assign>
							<ct:Vector>
								<ct:VectorElements>
									<ct:Real>10.0</ct:Real>
								</ct:VectorElements>
							</ct:Vector>
						</ct:Assign>
					</Start>
				</ObservationSequence>
			</Arm>
			<Arm oid="arm2">
				<ArmSize>
					<ct:Assign>
						<ct:Int>1</ct:Int>
					</ct:Assign>
				</ArmSize>
				<InterventionSequence>
					<InterventionList>
						<InterventionRef oidRef="arm1"/>
					</InterventionList>
					<Start>
						<ct:Assign>
							<ct:Vector>
								<ct:VectorElements>
									<ct:Real>0.0</ct:Real>
								</ct:VectorElements>
							</ct:Vector>
						</ct:Assign>
					</Start>
				</InterventionSequence>
				<ObservationSequence>
					<ObservationList>
						<ObservationRef oidRef="samp1"/>
					</ObservationList>
					<Start>
						<ct:Assign>
							<ct:Vector>
								<ct:VectorElements>
									<ct:Real>10.0</ct:Real>
								</ct:VectorElements>
							</ct:Vector>
						</ct:Assign>
					</Start>
				</ObservationSequence>
			</Arm>
		</Arms>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteArmSingleInterventionAndSample(){
		val mdl = createRoot
		
		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_STUDY_DESIGN))

		val intBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_INTERVENTION_BLK))
		val adminList = intBlk.createListDefn("arm1");
		val sampBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_SAMPLING_BLK))
		val sampList = sampBlk.createListDefn("samp1");
		
		desBlk.createPropertyStatement(createAssignPair(TrialDesignDesignObjectPrinter::TOTAL_SIZE_PROP, createIntLiteral(33)),
										createAssignPair(TrialDesignDesignObjectPrinter::NUM_SAMPLES_PROP, createVectorLiteral(createIntLiteral(5), createIntLiteral(7))),
										createAssignPair(TrialDesignDesignObjectPrinter::NUM_ARMS_PROP, createIntLiteral(6)),
										createAssignPair(TrialDesignDesignObjectPrinter::SAME_TIMES_PROP, createBooleanLiteral(true)),
										createAssignPair(TrialDesignDesignObjectPrinter::TOTAL_COST_PROP, createRealLiteral(22.643))
									)
		
		val armList = desBlk.createListDefn("arm1",
									createAssignPair(TrialDesignDesignObjectPrinter::ARM_SIZE_ATT, createIntLiteral(1)),
									createAssignPair(TrialDesignDesignObjectPrinter::INTSEQ_ATT, createVectorLiteral(createSublist(#{
																	TrialDesignDesignObjectPrinter::INTSEQ_ADMIN_ATT -> createVectorLiteral(createSymbolRef(adminList)),
																	TrialDesignDesignObjectPrinter::START_ATT_NAME -> createVectorLiteral(createRealLiteral(0.0))
																}))),
									createAssignPair(TrialDesignDesignObjectPrinter::SAMPSEQ_ATT, createVectorLiteral(createSublist(#{
																	TrialDesignDesignObjectPrinter::SAMPSEQ_SAMP_ATT -> createVectorLiteral(createSymbolRef(sampList)),
																	TrialDesignDesignObjectPrinter::START_ATT_NAME -> createVectorLiteral(createRealLiteral(10.0))
																})))
							)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl.createMogDefn(obj), new StandardParameterWriter(null))
		val actual = tdow.writeArm(armList)
		val expected = '''
			<Arm oid="arm1">
				<ArmSize>
					<ct:Assign>
						<ct:Int>1</ct:Int>
					</ct:Assign>
				</ArmSize>
				<InterventionSequence>
					<InterventionList>
						<InterventionRef oidRef="arm1"/>
					</InterventionList>
					<Start>
						<ct:Assign>
							<ct:Vector>
								<ct:VectorElements>
									<ct:Real>0.0</ct:Real>
								</ct:VectorElements>
							</ct:Vector>
						</ct:Assign>
					</Start>
				</InterventionSequence>
				<ObservationSequence>
					<ObservationList>
						<ObservationRef oidRef="samp1"/>
					</ObservationList>
					<Start>
						<ct:Assign>
							<ct:Vector>
								<ct:VectorElements>
									<ct:Real>10.0</ct:Real>
								</ct:VectorElements>
							</ct:Vector>
						</ct:Assign>
					</Start>
				</ObservationSequence>
			</Arm>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteArmMultiInterventionAndMultiSample(){
		val mdl = createRoot
		
		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_STUDY_DESIGN))

		val intBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_INTERVENTION_BLK))
		val admin1List = intBlk.createListDefn("admin1");
		val admin2List = intBlk.createListDefn("admin2");
		val sampBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_SAMPLING_BLK))
		val samp1List = sampBlk.createListDefn("samp1");
		val samp2List = sampBlk.createListDefn("samp2");
		
		desBlk.createPropertyStatement(createAssignPair(TrialDesignDesignObjectPrinter::TOTAL_SIZE_PROP, createIntLiteral(33)),
										createAssignPair(TrialDesignDesignObjectPrinter::NUM_SAMPLES_PROP, createVectorLiteral(createIntLiteral(5), createIntLiteral(7))),
										createAssignPair(TrialDesignDesignObjectPrinter::NUM_ARMS_PROP, createIntLiteral(6)),
										createAssignPair(TrialDesignDesignObjectPrinter::SAME_TIMES_PROP, createBooleanLiteral(true)),
										createAssignPair(TrialDesignDesignObjectPrinter::TOTAL_COST_PROP, createRealLiteral(22.643))
									)
		
		val armList = desBlk.createListDefn("arm1",
									createAssignPair(TrialDesignDesignObjectPrinter::ARM_SIZE_ATT, createIntLiteral(1)),
									createAssignPair(TrialDesignDesignObjectPrinter::INTSEQ_ATT, createVectorLiteral(createSublist(#{
																	TrialDesignDesignObjectPrinter::INTSEQ_ADMIN_ATT -> createVectorLiteral(createSymbolRef(admin1List),
																					createSymbolRef(admin1List)
																	),
																	TrialDesignDesignObjectPrinter::START_ATT_NAME -> createVectorLiteral(createRealLiteral(0.0), createRealLiteral(30.0))
																}),
																createSublist(#{
																	TrialDesignDesignObjectPrinter::INTSEQ_ADMIN_ATT -> createVectorLiteral(createSymbolRef(admin2List)),
																	TrialDesignDesignObjectPrinter::START_ATT_NAME -> createVectorLiteral(createRealLiteral(20.0))
																}))),
									createAssignPair(TrialDesignDesignObjectPrinter::SAMPSEQ_ATT, createVectorLiteral(createSublist(#{
																	TrialDesignDesignObjectPrinter::SAMPSEQ_SAMP_ATT -> createVectorLiteral(createSymbolRef(samp1List)),
																	TrialDesignDesignObjectPrinter::START_ATT_NAME -> createVectorLiteral(createRealLiteral(10.0))
																}),
																createSublist(#{
																	TrialDesignDesignObjectPrinter::SAMPSEQ_SAMP_ATT -> createVectorLiteral(createSymbolRef(samp1List),
																		createSymbolRef(samp2List)
																	),
																	TrialDesignDesignObjectPrinter::START_ATT_NAME -> createVectorLiteral(createRealLiteral(1.0), createRealLiteral(10.0))
																})
																
																))
							)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl.createMogDefn(obj), new StandardParameterWriter(null))
		val actual = tdow.writeArm(armList)
		val expected = '''
			<Arm oid="arm1">
				<ArmSize>
					<ct:Assign>
						<ct:Int>1</ct:Int>
					</ct:Assign>
				</ArmSize>
				<InterventionSequence>
					<InterventionList>
						<InterventionRef oidRef="admin1"/>
						<InterventionRef oidRef="admin1"/>
					</InterventionList>
					<Start>
						<ct:Assign>
							<ct:Vector>
								<ct:VectorElements>
									<ct:Real>0.0</ct:Real>
									<ct:Real>30.0</ct:Real>
								</ct:VectorElements>
							</ct:Vector>
						</ct:Assign>
					</Start>
				</InterventionSequence>
				<InterventionSequence>
					<InterventionList>
						<InterventionRef oidRef="admin2"/>
					</InterventionList>
					<Start>
						<ct:Assign>
							<ct:Vector>
								<ct:VectorElements>
									<ct:Real>20.0</ct:Real>
								</ct:VectorElements>
							</ct:Vector>
						</ct:Assign>
					</Start>
				</InterventionSequence>
				<ObservationSequence>
					<ObservationList>
						<ObservationRef oidRef="samp1"/>
					</ObservationList>
					<Start>
						<ct:Assign>
							<ct:Vector>
								<ct:VectorElements>
									<ct:Real>10.0</ct:Real>
								</ct:VectorElements>
							</ct:Vector>
						</ct:Assign>
					</Start>
				</ObservationSequence>
				<ObservationSequence>
					<ObservationList>
						<ObservationRef oidRef="samp1"/>
						<ObservationRef oidRef="samp2"/>
					</ObservationList>
					<Start>
						<ct:Assign>
							<ct:Vector>
								<ct:VectorElements>
									<ct:Real>1.0</ct:Real>
									<ct:Real>10.0</ct:Real>
								</ct:VectorElements>
							</ct:Vector>
						</ct:Assign>
					</Start>
				</ObservationSequence>
			</Arm>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}


	@Test
	def void testWriteArmSingleInterventionAndSampleWithOneLevelOccasions(){
		val mdl = createRoot

		val mdlObj = mdl.createObject("mfoo", libDefns.getObjectDefinition("mdlObj"))
		val varBlk = mdlObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::VAR_LVL_BLK_NAME))
		varBlk.createListDefn("OCC");

		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val declBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DECLARED_VARS_BLK))
		val occVarLevel = declBlk.createEqnDefn("OCC");

		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_STUDY_DESIGN))

		val intBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_INTERVENTION_BLK))
		val adminList = intBlk.createListDefn("arm1");
		val sampBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_SAMPLING_BLK))
		val sampList = sampBlk.createListDefn("samp1");
		
		desBlk.createPropertyStatement(createAssignPair(TrialDesignDesignObjectPrinter::TOTAL_SIZE_PROP, createIntLiteral(33)),
										createAssignPair(TrialDesignDesignObjectPrinter::NUM_SAMPLES_PROP, createVectorLiteral(createIntLiteral(5), createIntLiteral(7))),
										createAssignPair(TrialDesignDesignObjectPrinter::NUM_ARMS_PROP, createIntLiteral(6)),
										createAssignPair(TrialDesignDesignObjectPrinter::SAME_TIMES_PROP, createBooleanLiteral(true)),
										createAssignPair(TrialDesignDesignObjectPrinter::TOTAL_COST_PROP, createRealLiteral(22.643))
									)
		
		val armList = desBlk.createListDefn("arm1",
									createAssignPair(TrialDesignDesignObjectPrinter::ARM_SIZE_ATT, createIntLiteral(1)),
									createAssignPair(TrialDesignDesignObjectPrinter::INTSEQ_ATT, createVectorLiteral(createSublist(#{
																	TrialDesignDesignObjectPrinter::INTSEQ_ADMIN_ATT -> createVectorLiteral(createSymbolRef(adminList)),
																	TrialDesignDesignObjectPrinter::START_ATT_NAME -> createVectorLiteral(createRealLiteral(0.0))
																}))),
									createAssignPair(TrialDesignDesignObjectPrinter::SAMPSEQ_ATT, createVectorLiteral(createSublist(#{
																	TrialDesignDesignObjectPrinter::SAMPSEQ_SAMP_ATT -> createVectorLiteral(createSymbolRef(sampList)),
																	TrialDesignDesignObjectPrinter::START_ATT_NAME -> createVectorLiteral(createRealLiteral(10.0))
																}))),
									createAssignPair(TrialDesignDesignObjectPrinter::OCC_SEQ_ATT, createVectorLiteral(createSublist(#{
												TrialDesignDesignObjectPrinter::OCC_LEVEL_ATT -> createSymbolRef(occVarLevel),
												TrialDesignDesignObjectPrinter::OCC_SEQ_OCC_ATT -> createVectorLiteral(
													createIntLiteral(1), createIntLiteral(2)
												),
												TrialDesignDesignObjectPrinter::START_ATT_NAME -> createVectorLiteral(
													createRealLiteral(1.0), createRealLiteral(3.0)
												)
									})))
							)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl.createMogDefn(obj, mdlObj), new StandardParameterWriter(null))
		val actual = tdow.writeArm(armList)
		val expected = '''
			<Arm oid="arm1">
				<ArmSize>
					<ct:Assign>
						<ct:Int>1</ct:Int>
					</ct:Assign>
				</ArmSize>
				<InterventionSequence>
					<InterventionList>
						<InterventionRef oidRef="arm1"/>
					</InterventionList>
					<Start>
						<ct:Assign>
							<ct:Vector>
								<ct:VectorElements>
									<ct:Real>0.0</ct:Real>
								</ct:VectorElements>
							</ct:Vector>
						</ct:Assign>
					</Start>
				</InterventionSequence>
				<ObservationSequence>
					<ObservationList>
						<ObservationRef oidRef="samp1"/>
					</ObservationList>
					<Start>
						<ct:Assign>
							<ct:Vector>
								<ct:VectorElements>
									<ct:Real>10.0</ct:Real>
								</ct:VectorElements>
							</ct:Vector>
						</ct:Assign>
					</Start>
				</ObservationSequence>
				<OccasionSequence>
					<OccasionList oid="arm1_OCC">
						<ct:VariabilityReference>
							<ct:SymbRef blkIdRef="vm_err" symbIdRef="OCC"/>
						</ct:VariabilityReference>
						<Occasion oid="arm1_OCC_1">
							<Start>
								<ct:Assign>
									<ct:Real>1.0</ct:Real>
								</ct:Assign>
							</Start>
						</Occasion>
						<Occasion oid="arm1_OCC_2">
							<Start>
								<ct:Assign>
									<ct:Real>3.0</ct:Real>
								</ct:Assign>
							</Start>
						</Occasion>
					</OccasionList>
				</OccasionSequence>
			</Arm>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteArmSingleInterventionAndSampleWithTwoLevelOccasions(){
		val mdl = createRoot
		
		val mdlObj = mdl.createObject("mfoo", libDefns.getObjectDefinition("mdlObj"))
		val varBlk = mdlObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::VAR_LVL_BLK_NAME))
		varBlk.createListDefn("OCC");
		varBlk.createListDefn("TRIAL");

		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val declBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DECLARED_VARS_BLK))
		val occVarLevel = declBlk.createEqnDefn("OCC");
		val trialVarLevel = declBlk.createEqnDefn("TRIAL");

		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_STUDY_DESIGN))

		val intBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_INTERVENTION_BLK))
		val adminList = intBlk.createListDefn("arm1");
		val sampBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_SAMPLING_BLK))
		val sampList = sampBlk.createListDefn("samp1");
		
		desBlk.createPropertyStatement(createAssignPair(TrialDesignDesignObjectPrinter::TOTAL_SIZE_PROP, createIntLiteral(33)),
										createAssignPair(TrialDesignDesignObjectPrinter::NUM_SAMPLES_PROP, createVectorLiteral(createIntLiteral(5), createIntLiteral(7))),
										createAssignPair(TrialDesignDesignObjectPrinter::NUM_ARMS_PROP, createIntLiteral(6)),
										createAssignPair(TrialDesignDesignObjectPrinter::SAME_TIMES_PROP, createBooleanLiteral(true)),
										createAssignPair(TrialDesignDesignObjectPrinter::TOTAL_COST_PROP, createRealLiteral(22.643))
									)
		
		val armList = desBlk.createListDefn("arm1",
									createAssignPair(TrialDesignDesignObjectPrinter::ARM_SIZE_ATT, createIntLiteral(1)),
									createAssignPair(TrialDesignDesignObjectPrinter::INTSEQ_ATT, createVectorLiteral(createSublist(#{
																	TrialDesignDesignObjectPrinter::INTSEQ_ADMIN_ATT -> createVectorLiteral(createSymbolRef(adminList)),
																	TrialDesignDesignObjectPrinter::START_ATT_NAME -> createVectorLiteral(createRealLiteral(0.0))
																}))),
									createAssignPair(TrialDesignDesignObjectPrinter::SAMPSEQ_ATT, createVectorLiteral(createSublist(#{
																	TrialDesignDesignObjectPrinter::SAMPSEQ_SAMP_ATT -> createVectorLiteral(createSymbolRef(sampList)),
																	TrialDesignDesignObjectPrinter::START_ATT_NAME -> createVectorLiteral(createRealLiteral(10.0))
																}))),
									createAssignPair(TrialDesignDesignObjectPrinter::OCC_SEQ_ATT, createVectorLiteral(
										createSublist(#{
												TrialDesignDesignObjectPrinter::OCC_LEVEL_ATT -> createSymbolRef(occVarLevel),
												TrialDesignDesignObjectPrinter::OCC_SEQ_OCC_ATT -> createVectorLiteral(
													createIntLiteral(1), createIntLiteral(2)
												),
												TrialDesignDesignObjectPrinter::START_ATT_NAME -> createVectorLiteral(
													createRealLiteral(1.0), createRealLiteral(3.0)
												)
										}),
										createSublist(#{TrialDesignDesignObjectPrinter::OCC_LEVEL_ATT -> createSymbolRef(trialVarLevel),
													TrialDesignDesignObjectPrinter::OCC_SEQ_OCC_ATT -> createVectorLiteral(
														createIntLiteral(1), createIntLiteral(2)
													),
													TrialDesignDesignObjectPrinter::START_ATT_NAME -> createVectorLiteral(
														createRealLiteral(6.0), createRealLiteral(33.0)
													)
										}))
							))
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl.createMogDefn(obj, mdlObj), new StandardParameterWriter(null))
		val actual = tdow.writeArm(armList)
		val expected = '''
			<Arm oid="arm1">
				<ArmSize>
					<ct:Assign>
						<ct:Int>1</ct:Int>
					</ct:Assign>
				</ArmSize>
				<InterventionSequence>
					<InterventionList>
						<InterventionRef oidRef="arm1"/>
					</InterventionList>
					<Start>
						<ct:Assign>
							<ct:Vector>
								<ct:VectorElements>
									<ct:Real>0.0</ct:Real>
								</ct:VectorElements>
							</ct:Vector>
						</ct:Assign>
					</Start>
				</InterventionSequence>
				<ObservationSequence>
					<ObservationList>
						<ObservationRef oidRef="samp1"/>
					</ObservationList>
					<Start>
						<ct:Assign>
							<ct:Vector>
								<ct:VectorElements>
									<ct:Real>10.0</ct:Real>
								</ct:VectorElements>
							</ct:Vector>
						</ct:Assign>
					</Start>
				</ObservationSequence>
				<OccasionSequence>
					<OccasionList oid="arm1_OCC">
						<ct:VariabilityReference>
							<ct:SymbRef blkIdRef="vm_err" symbIdRef="OCC"/>
						</ct:VariabilityReference>
						<Occasion oid="arm1_OCC_1">
							<Start>
								<ct:Assign>
									<ct:Real>1.0</ct:Real>
								</ct:Assign>
							</Start>
						</Occasion>
						<Occasion oid="arm1_OCC_2">
							<Start>
								<ct:Assign>
									<ct:Real>3.0</ct:Real>
								</ct:Assign>
							</Start>
						</Occasion>
					</OccasionList>
				</OccasionSequence>
				<OccasionSequence>
					<OccasionList oid="arm1_TRIAL">
						<ct:VariabilityReference>
							<ct:SymbRef blkIdRef="vm_err" symbIdRef="TRIAL"/>
						</ct:VariabilityReference>
						<Occasion oid="arm1_TRIAL_1">
							<Start>
								<ct:Assign>
									<ct:Real>6.0</ct:Real>
								</ct:Assign>
							</Start>
						</Occasion>
						<Occasion oid="arm1_TRIAL_2">
							<Start>
								<ct:Assign>
									<ct:Real>33.0</ct:Real>
								</ct:Assign>
							</Start>
						</Occasion>
					</OccasionList>
				</OccasionSequence>
			</Arm>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}


	@Test
	def void testWriteSimpleSampling(){
		val mdl = createRoot
		
		val mdlObj = mdl.createObject("m", libDefns.getObjectDefinition("mdlObj"))
		val mdlPredBlk = mdlObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_PRED_BLK_NAME))
		mdlPredBlk.createEqnDefn("Y")

		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val declVarBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DECLARED_VARS_BLK))
		val rsVar1 = declVarBlk.createEqnDefn("Y") 

		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_SAMPLING_BLK))
		val actionList = desBlk.createListDefn("samp1",
									createEnumPair(TrialDesignDesignObjectPrinter::SAMP_TYPE_ATT_NAME, TrialDesignDesignObjectPrinter::SAMP_TYPE_SIMPLE_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::SAMP_NUM_TIMES, createIntLiteral(34)),
									createAssignPair(TrialDesignDesignObjectPrinter::SAMP_SAMP_TIMES, createVectorLiteral(
																						createRealLiteral(0.5),	
																						createRealLiteral(2),
																						createRealLiteral(6),	
																						createRealLiteral(8)		
																					)
																				),
									createAssignPair(TrialDesignDesignObjectPrinter::SAMP_OUTCOME, createSymbolRef(rsVar1))
									)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl.createMogDefn(obj, mdlObj), new StandardParameterWriter(null))
		val actual = tdow.writeSimpleSampling(actionList)
		val expected = '''
			<Observation oid="samp1">
				<NumberTimes>
					<ct:Assign>
						<ct:Int>34</ct:Int>
					</ct:Assign>
				</NumberTimes>
				<ObservationTimes>
					<ct:Assign>
						<ct:Vector>
							<ct:VectorElements>
								<ct:Real>0.5</ct:Real>
								<ct:Real>2.0</ct:Real>
								<ct:Real>6.0</ct:Real>
								<ct:Real>8.0</ct:Real>
							</ct:VectorElements>
						</ct:Vector>
					</ct:Assign>
				</ObservationTimes>
				<Continuous>
					<ct:SymbRef blkIdRef="sm" symbIdRef="Y"/>
				</Continuous>
			</Observation>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteCombiSampling(){
		val mdl = createRoot
		
		val mdlObj = mdl.createObject("m", libDefns.getObjectDefinition("mdlObj"))
		val mdlPredBlk = mdlObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_PRED_BLK_NAME))
		mdlPredBlk.createEqnDefn("Y")

		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val declVarBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DECLARED_VARS_BLK))
		val rsVar1 = declVarBlk.createEqnDefn("Y") 

		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_SAMPLING_BLK))
		val samp1 = desBlk.createListDefn("samp1",
									createEnumPair(TrialDesignDesignObjectPrinter::SAMP_TYPE_ATT_NAME, TrialDesignDesignObjectPrinter::SAMP_TYPE_SIMPLE_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::SAMP_NUM_TIMES, createIntLiteral(34)),
									createAssignPair(TrialDesignDesignObjectPrinter::SAMP_SAMP_TIMES, createVectorLiteral(
																						createRealLiteral(0.5),	
																						createRealLiteral(2),
																						createRealLiteral(6),	
																						createRealLiteral(8)		
																					)
																				),
									createAssignPair(TrialDesignDesignObjectPrinter::SAMP_OUTCOME, createSymbolRef(rsVar1))
									)

		val combiList = desBlk.createListDefn("combi1",
									createEnumPair(TrialDesignDesignObjectPrinter::SAMP_TYPE_ATT_NAME, TrialDesignDesignObjectPrinter::SAMP_TYPE_COMBI_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::SAMP_COMBI_ATT, createVectorLiteral(
																createSymbolRef(samp1),
																createSymbolRef(samp1)
															)
														),
									createAssignPair(TrialDesignDesignObjectPrinter::SAMP_START, createVectorLiteral(
																createRealLiteral(2),
																createRealLiteral(6)
															)
														),
									createAssignPair(TrialDesignDesignObjectPrinter::SAMP_RELATIVE, createBooleanLiteral(true))					
									)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl.createMogDefn(obj, mdlObj), new StandardParameterWriter(null))
		val actual = tdow.writeCombiSampling(combiList)
		val expected = '''
			<ObservationsCombination oid="combi1">
				<Observations>
					<ObservationRef oidRef="samp1"/>
					<ObservationRef oidRef="samp1"/>
					<Start>
						<ct:Assign>
							<ct:Vector>
								<ct:VectorElements>
									<ct:Real>2.0</ct:Real>
									<ct:Real>6.0</ct:Real>
								</ct:VectorElements>
							</ct:Vector>
						</ct:Assign>
					</Start>
				</Observations>
				<Relative>
					<ct:Assign>
						<ct:True/>
					</ct:Assign>
				</Relative>
			</ObservationsCombination>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteSampling(){
		val mdl = createRoot
		
		val mdlObj = mdl.createObject("m", libDefns.getObjectDefinition("mdlObj"))
		val mdlPredBlk = mdlObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_PRED_BLK_NAME))
		mdlPredBlk.createEqnDefn("Y")

		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val declVarBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DECLARED_VARS_BLK))
		val rsVar1 = declVarBlk.createEqnDefn("Y") 

		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_SAMPLING_BLK))
		val samp1 = desBlk.createListDefn("samp1",
									createEnumPair(TrialDesignDesignObjectPrinter::SAMP_TYPE_ATT_NAME, TrialDesignDesignObjectPrinter::SAMP_TYPE_SIMPLE_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::SAMP_NUM_TIMES, createIntLiteral(34)),
									createAssignPair(TrialDesignDesignObjectPrinter::SAMP_SAMP_TIMES, createVectorLiteral(
																						createRealLiteral(0.5),	
																						createRealLiteral(2),
																						createRealLiteral(6),	
																						createRealLiteral(8)		
																					)
																				),
									createAssignPair(TrialDesignDesignObjectPrinter::SAMP_OUTCOME, createSymbolRef(rsVar1))
									)

		desBlk.createListDefn("combi1",
									createEnumPair(TrialDesignDesignObjectPrinter::SAMP_TYPE_ATT_NAME, TrialDesignDesignObjectPrinter::SAMP_TYPE_COMBI_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::SAMP_COMBI_ATT, createVectorLiteral(
																createSymbolRef(samp1),
																createSymbolRef(samp1)
															)
														),
									createAssignPair(TrialDesignDesignObjectPrinter::SAMP_START, createVectorLiteral(
																createRealLiteral(2),
																createRealLiteral(6)
															)
														),
									createAssignPair(TrialDesignDesignObjectPrinter::SAMP_RELATIVE, createBooleanLiteral(true))					
									)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl.createMogDefn(obj, mdlObj), new StandardParameterWriter(null))
		val actual = tdow.writeSampling(desBlk)
		val expected = '''
		<Observations>
			<Observation oid="samp1">
				<NumberTimes>
					<ct:Assign>
						<ct:Int>34</ct:Int>
					</ct:Assign>
				</NumberTimes>
				<ObservationTimes>
					<ct:Assign>
						<ct:Vector>
							<ct:VectorElements>
								<ct:Real>0.5</ct:Real>
								<ct:Real>2.0</ct:Real>
								<ct:Real>6.0</ct:Real>
								<ct:Real>8.0</ct:Real>
							</ct:VectorElements>
						</ct:Vector>
					</ct:Assign>
				</ObservationTimes>
				<Continuous>
					<ct:SymbRef blkIdRef="sm" symbIdRef="Y"/>
				</Continuous>
			</Observation>
			<ObservationsCombination oid="combi1">
				<Observations>
					<ObservationRef oidRef="samp1"/>
					<ObservationRef oidRef="samp1"/>
					<Start>
						<ct:Assign>
							<ct:Vector>
								<ct:VectorElements>
									<ct:Real>2.0</ct:Real>
									<ct:Real>6.0</ct:Real>
								</ct:VectorElements>
							</ct:Vector>
						</ct:Assign>
					</Start>
				</Observations>
				<Relative>
					<ct:Assign>
						<ct:True/>
					</ct:Assign>
				</Relative>
			</ObservationsCombination>
		</Observations>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Ignore("Not used in design object")
	def void testWriteCovariate(){
		val mdl = createRoot
		
		val mObj = mdl.createObject("mFoo", libDefns.getObjectDefinition("mdlObj"))
		val covBlk = mObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::COVARIATE_BLK_NAME))
		covBlk.createEqnDefn("W") 

		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val desParamsBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::COVARIATE_BLK_NAME))
		val covVar1 = desParamsBlk.createEqnDefn("W", createRealLiteral(70.7)) 

		val tdow = new TrialDesignDesignObjectPrinter(mdl.createMogDefn(obj, mObj), new StandardParameterWriter(null))
		val actual = tdow.writeCovariate(covVar1)
		val expected = '''
			<Covariate symbId="W">
				<mdef:Continuous>
					<ct:Assign>
						<ct:Real>70.7</ct:Real>
					</ct:Assign>
				</mdef:Continuous>
			</Covariate>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Ignore("Not used in design object")
	def void testWriteCovariates(){
		val mdl = createRoot
		
		val mObj = mdl.createObject("mFoo", libDefns.getObjectDefinition("mdlObj"))
		val covBlk = mObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::COVARIATE_BLK_NAME))
		covBlk.createEqnDefn("W") 
		covBlk.createEqnDefn("Y") 

		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val desParamsBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::COVARIATE_BLK_NAME))
		desParamsBlk.createEqnDefn("W", createRealLiteral(70.7)) 
		desParamsBlk.createEqnDefn("Y", createRealLiteral(88.7)) 

		val tdow = new TrialDesignDesignObjectPrinter(mdl.createMogDefn(obj, mObj), new StandardParameterWriter(null))
		val actual = tdow.writeCovariates(desParamsBlk)
		val expected = '''
			<Covariates>
				<CovariateModel oid="«TrialDesignDesignObjectPrinter::COV_MOD_OID»">
					<CovariateModelRef blkIdRef="cm"/>
					<Covariate symbId="W">
						<mdef:Continuous>
							<ct:Assign>
								<ct:Real>70.7</ct:Real>
							</ct:Assign>
						</mdef:Continuous>
					</Covariate>
					<Covariate symbId="Y">
						<mdef:Continuous>
							<ct:Assign>
								<ct:Real>88.7</ct:Real>
							</ct:Assign>
						</mdef:Continuous>
					</Covariate>
				</CovariateModel>
			</Covariates>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}


}