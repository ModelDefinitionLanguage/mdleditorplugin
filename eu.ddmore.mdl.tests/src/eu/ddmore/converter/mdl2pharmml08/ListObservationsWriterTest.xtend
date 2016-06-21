package eu.ddmore.converter.mdl2pharmml08

import com.google.inject.Inject
import eu.ddmore.mdl.MdlAndLibInjectorProvider
import eu.ddmore.mdl.mdl.BlockStatementBody
import eu.ddmore.mdl.mdl.MdlFactory
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
import eu.ddmore.mdl.utils.LibraryUtils
import eu.ddmore.mdllib.mdllib.Library
import org.junit.Before
import org.junit.After

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class ListObservationsWriterTest {
	@Inject extension ListObservationsWriter
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
	
	@After
	def void tearDown(){
		libDefns = null
	}

	
	@Test
	def void testWriteCombinedError2NoTrans(){
		val obsBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::OBS_BLK_NAME))
		val mPredBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_PRED_BLK_NAME))
		val mParamsBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_STRUCT_PARAMS))
		val rvBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_RND_VARS))
		val ld = MdlFactory.eINSTANCE.createListDefinition
		val bdy = (obsBlk.body as BlockStatementBody)
		bdy.statements.add(ld)
		val attList = MdlFactory.eINSTANCE.createAttributeList
		ld.setName("tst")
		ld.list = attList
		attList.attributes.add(createEnumPair('type', 'combinedError2'))
		attList.attributes.add(createAssignPair('additive', mParamsBlk.createSymbolRef('A')))
		attList.attributes.add(createAssignPair('proportional', mParamsBlk.createSymbolRef('B')))
		attList.attributes.add(createAssignPair('eps', rvBlk.createSymbolRef('E')))
		attList.attributes.add(createAssignPair('prediction', mPredBlk.createSymbolRef('C')))
		
		val actual = writeListObservations(ld, 1)
		val expected = '''
		<ObservationModel blkId="om1">
			<ContinuousData>
				<Standard symbId="tst">
					<Output>
						<ct:SymbRef blkIdRef="sm" symbIdRef="C"/>
					</Output>
					<ErrorModel>
						<ct:Assign>
							<math:FunctionCall>
								<ct:SymbRef symbIdRef="combinedError2"/>
								<math:FunctionArgument symbId="additive">
									<ct:SymbRef blkIdRef="pm" symbIdRef="A"/>
								</math:FunctionArgument>
								<math:FunctionArgument symbId="proportional">
									<ct:SymbRef blkIdRef="pm" symbIdRef="B"/>
								</math:FunctionArgument>
								<math:FunctionArgument symbId="f">
									<ct:SymbRef blkIdRef="sm" symbIdRef="C"/>
								</math:FunctionArgument>
							</math:FunctionCall>
						</ct:Assign>
					</ErrorModel>
					<ResidualError>
						<ct:SymbRef blkIdRef="pm" symbIdRef="E"/>
					</ResidualError>
				</Standard>
			</ContinuousData>
		</ObservationModel>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteCombinedError2LogRhsTrans(){
		val obsBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::OBS_BLK_NAME))
		val mPredBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_PRED_BLK_NAME))
		val mParamsBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_STRUCT_PARAMS))
		val rvBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_RND_VARS))
		val ld = MdlFactory.eINSTANCE.createListDefinition
		val bdy = (obsBlk.body as BlockStatementBody)
		bdy.statements.add(ld)
		val attList = MdlFactory.eINSTANCE.createAttributeList
		ld.setName("tst")
		ld.list = attList
		attList.attributes.add(createEnumPair('type', 'combinedError2'))
		attList.attributes.add(createEnumPair('trans', 'ln'))
		attList.attributes.add(createAssignPair('additive', mParamsBlk.createSymbolRef('A')))
		attList.attributes.add(createAssignPair('proportional', mParamsBlk.createSymbolRef('B')))
		attList.attributes.add(createAssignPair('eps', rvBlk.createSymbolRef('E')))
		attList.attributes.add(createAssignPair('prediction', mPredBlk.createSymbolRef('C')))
		
		val actual = writeListObservations(ld, 1)
		val expected = '''
		<ObservationModel blkId="om1">
			<ContinuousData>
				<ct:Variable symbolType="real" symbId="C">
					<ct:Assign>
						<math:Uniop op="log">
							<ct:SymbRef blkIdRef="sm" symbIdRef="C"/>
						</math:Uniop>
					</ct:Assign>
				</ct:Variable>
				<Standard symbId="tst">
					<Output>
						<ct:SymbRef symbIdRef="C"/>
					</Output>
					<ErrorModel>
						<ct:Assign>
							<math:FunctionCall>
								<ct:SymbRef symbIdRef="combinedError2Log"/>
								<math:FunctionArgument symbId="additive">
									<ct:SymbRef blkIdRef="pm" symbIdRef="A"/>
								</math:FunctionArgument>
								<math:FunctionArgument symbId="proportional">
									<ct:SymbRef blkIdRef="pm" symbIdRef="B"/>
								</math:FunctionArgument>
								<math:FunctionArgument symbId="f">
									<ct:SymbRef blkIdRef="sm" symbIdRef="C"/>
								</math:FunctionArgument>
							</math:FunctionCall>
						</ct:Assign>
					</ErrorModel>
					<ResidualError>
						<ct:SymbRef blkIdRef="pm" symbIdRef="E"/>
					</ResidualError>
				</Standard>
			</ContinuousData>
		</ObservationModel>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}


	@Test
	def void testWriteCombinedError2LogBothTrans(){
		val obsBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::OBS_BLK_NAME))
		val mPredBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_PRED_BLK_NAME))
		val mParamsBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_STRUCT_PARAMS))
		val rvBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_RND_VARS))
		val ld = MdlFactory.eINSTANCE.createListDefinition
		val bdy = (obsBlk.body as BlockStatementBody)
		bdy.statements.add(ld)
		val attList = MdlFactory.eINSTANCE.createAttributeList
		ld.setName("tst")
		ld.list = attList
		attList.attributes.add(createEnumPair('type', 'combinedError2'))
		attList.attributes.add(createEnumPair('lhsTrans', 'ln'))
		attList.attributes.add(createEnumPair('trans', 'ln'))
		attList.attributes.add(createAssignPair('additive', mParamsBlk.createSymbolRef('A')))
		attList.attributes.add(createAssignPair('proportional', mParamsBlk.createSymbolRef('B')))
		attList.attributes.add(createAssignPair('eps', rvBlk.createSymbolRef('E')))
		attList.attributes.add(createAssignPair('prediction', mPredBlk.createSymbolRef('C')))
		
		val actual = writeListObservations(ld, 1)
		val expected = '''
		<ObservationModel blkId="om1">
			<ContinuousData>
				<Standard symbId="tst">
					<Transformation type="log"/>
					<Output>
						<ct:SymbRef blkIdRef="sm" symbIdRef="C"/>
					</Output>
					<ErrorModel>
						<ct:Assign>
							<math:FunctionCall>
								<ct:SymbRef symbIdRef="combinedError2Log"/>
								<math:FunctionArgument symbId="additive">
									<ct:SymbRef blkIdRef="pm" symbIdRef="A"/>
								</math:FunctionArgument>
								<math:FunctionArgument symbId="proportional">
									<ct:SymbRef blkIdRef="pm" symbIdRef="B"/>
								</math:FunctionArgument>
								<math:FunctionArgument symbId="f">
									<ct:SymbRef blkIdRef="sm" symbIdRef="C"/>
								</math:FunctionArgument>
							</math:FunctionCall>
						</ct:Assign>
					</ErrorModel>
					<ResidualError>
						<ct:SymbRef blkIdRef="pm" symbIdRef="E"/>
					</ResidualError>
				</Standard>
			</ContinuousData>
		</ObservationModel>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteContinuousObs(){
		val obsBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::OBS_BLK_NAME))
		val mParamsBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_STRUCT_PARAMS))
		val varLvlBlock = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::VAR_LVL_BLK_NAME))
		val lvlListDefn = varLvlBlock.createListDefn("DV", #[
											createEnumPair("type", "observation"),
											createAssignPair("level", createIntLiteral(1))
										])
		val rvBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_RND_VARS), #[
																	createAssignPair("level", createSymbolRef(lvlListDefn))
																	])
		val randVar = rvBlk.createRandVar("Y", createNamedFunction(libDefns.getFunctionDefinition("Normal"), #[
																createAssignPair("mean", createRealLiteral(0)),
																createAssignPair("sd", mParamsBlk.createSymbolRef('B'))
															])) 
		val anonList = obsBlk.createAnonList(#[
			createEnumPair('type', 'continuous'),
			createAssignPair('variable', createSymbolRef(randVar))
		])
		
		val actual = writeListObservations(anonList, 1)
		val expected = '''
		<ObservationModel blkId="om1">
			<ContinuousData>
				<General symbId="Y">
					<ct:VariabilityReference>
						<ct:SymbRef blkIdRef="vm_err" symbIdRef="DV"/>
					</ct:VariabilityReference>
					<Distribution>
						<ProbOnto xmlns="http://www.pharmml.org/probonto/ProbOnto" name="Normal1">
							<Parameter name="mean">
								<ct:Assign>
									<ct:Real>0.0</ct:Real>
								</ct:Assign>
							</Parameter>
							<Parameter name="stdev">
								<ct:Assign>
									<ct:SymbRef blkIdRef="pm" symbIdRef="B"/>
								</ct:Assign>
							</Parameter>
						</ProbOnto>
					</Distribution>
				</General>
			</ContinuousData>
		</ObservationModel>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteCountObs(){
		val obsBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::OBS_BLK_NAME))
		val smBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_PRED_BLK_NAME))
		val varLvlBlock = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::VAR_LVL_BLK_NAME))
		val lvlListDefn = varLvlBlock.createListDefn("DV", #[
											createEnumPair("type", "observation"),
											createAssignPair("level", createIntLiteral(1))
										])
		val rvBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_RND_VARS), #[
																	createAssignPair("level", createSymbolRef(lvlListDefn))
																	])
		val lambda = smBlk.createEqnDefn("LAMBDA", createRealLiteral(22.2))
		val randVar = rvBlk.createRandVar("Y", createNamedFunction(libDefns.getFunctionDefinition("Poisson1"), #[
																createAssignPair("rate", createSymbolRef(lambda))
															])) 
		val anonList = obsBlk.createAnonList(#[
			createEnumPair('type', 'count'),
			createAssignPair('variable', createSymbolRef(randVar))
		])
		
		val actual = writeListObservations(anonList, 1)
		val expected = '''
		<ObservationModel blkId="om1">
			<Discrete>
				<CountData>
					<CountVariable symbId="Y"/>
					<PMF transform="identity">
						<Distribution>
							<ProbOnto xmlns="http://www.pharmml.org/probonto/ProbOnto" name="Poisson1">
								<Parameter name="rate">
									<ct:Assign>
										<ct:SymbRef blkIdRef="sm" symbIdRef="LAMBDA"/>
									</ct:Assign>
								</Parameter>
							</ProbOnto>
						</Distribution>
					</PMF>
				</CountData>
			</Discrete>
		</ObservationModel>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteDiscreteBernoulliObs(){
		val obsBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::OBS_BLK_NAME))
		val smBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_PRED_BLK_NAME))
		val varLvlBlock = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::VAR_LVL_BLK_NAME))
		val lvlListDefn = varLvlBlock.createListDefn("DV", #[
											createEnumPair("type", "observation"),
											createAssignPair("level", createIntLiteral(1))
										])
		val rvBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_RND_VARS), #[
																	createAssignPair("level", createSymbolRef(lvlListDefn))
																	])
		val lambda = smBlk.createEqnDefn("P1", createRealLiteral(22.2))
		val randVar = rvBlk.createCategoricalDefinition("Y", createNamedFunction(libDefns.getFunctionDefinition("Bernoulli1"), #[
																createAssignPair("probability", createSymbolRef(lambda))
															]), "a", "b") 
		val anonList = obsBlk.createAnonList(#[
			createEnumPair('type', 'discrete'),
			createAssignPair('variable', createSymbolRef(randVar))
		])
		
		val actual = writeListObservations(anonList, 1)
		val expected = '''
		<ObservationModel blkId="om1">
			<Discrete>
				<CategoricalData ordered="no">
					<ListOfCategories>
						<Category symbId="a"/>
						<Category symbId="b"/>
					</ListOfCategories>
					<CategoryVariable symbId="Y"/>
					<PMF>
						<Distribution>
							<ProbOnto xmlns="http://www.pharmml.org/probonto/ProbOnto" name="Bernoulli1">
								<Parameter name="probability">
									<ct:Assign>
										<ct:SymbRef blkIdRef="sm" symbIdRef="P1"/>
									</ct:Assign>
								</Parameter>
							</ProbOnto>
						</Distribution>
					</PMF>
				</CategoricalData>
			</Discrete>
		</ObservationModel>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteDiscreteBinomialObs(){
		val obsBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::OBS_BLK_NAME))
		val smBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_PRED_BLK_NAME))
		val varLvlBlock = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::VAR_LVL_BLK_NAME))
		val lvlListDefn = varLvlBlock.createListDefn("DV", #[
											createEnumPair("type", "observation"),
											createAssignPair("level", createIntLiteral(1))
										])
		val rvBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_RND_VARS), #[
																	createAssignPair("level", createSymbolRef(lvlListDefn))
																	])
		val p1 = smBlk.createEqnDefn("P1", createRealLiteral(22.2))
		val randVar = rvBlk.createCategoricalDefinition("Y", createNamedFunction(libDefns.getFunctionDefinition("Binomial1"), #[
																createAssignPair("probability", createSymbolRef(p1)),
																createAssignPair("numberOfTrials", createIntLiteral(1))
															]), "a", "b") 
		val anonList = obsBlk.createAnonList(#[
			createEnumPair('type', 'discrete'),
			createAssignPair('variable', createSymbolRef(randVar))
		])
		
		val actual = writeListObservations(anonList, 1)
		val expected = '''
		<ObservationModel blkId="om1">
			<Discrete>
				<CategoricalData ordered="no">
					<ListOfCategories>
						<Category symbId="a"/>
						<Category symbId="b"/>
					</ListOfCategories>
					<CategoryVariable symbId="Y"/>
					<PMF>
						<Distribution>
							<ProbOnto xmlns="http://www.pharmml.org/probonto/ProbOnto" name="Binomial1">
								<Parameter name="probability">
									<ct:Assign>
										<ct:SymbRef blkIdRef="sm" symbIdRef="P1"/>
									</ct:Assign>
								</Parameter>
								<Parameter name="numberOfTrials">
									<ct:Assign>
										<ct:Int>1</ct:Int>
									</ct:Assign>
								</Parameter>
							</ProbOnto>
						</Distribution>
					</PMF>
				</CategoricalData>
			</Discrete>
		</ObservationModel>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteCategopricalNonOrderedObs(){
		val obsBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::OBS_BLK_NAME))
		val smBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_PRED_BLK_NAME))
		val varLvlBlock = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::VAR_LVL_BLK_NAME))
		val lvlListDefn = varLvlBlock.createListDefn("DV", #[
											createEnumPair("type", "observation"),
											createAssignPair("level", createIntLiteral(1))
										])
		val rvBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_RND_VARS), #[
																	createAssignPair("level", createSymbolRef(lvlListDefn))
																	])
		val p1 = smBlk.createEqnDefn("P1", createRealLiteral(22.2))
		val p2 = smBlk.createEqnDefn("P2", createRealLiteral(23.2))
		val p3 = smBlk.createEqnDefn("P3", createRealLiteral(24.2))
		val p4 = smBlk.createEqnDefn("P4", createRealLiteral(25.2))
		val randVar = rvBlk.createCategoricalDefinition("Y", createNamedFunction(libDefns.getFunctionDefinition("CategoricalNonordered1"), #[
																createAssignPair("categoryProb", createVectorLiteral(#[
																	createSymbolRef(p1),
																	createSymbolRef(p2),
																	createSymbolRef(p3),
																	createSymbolRef(p4)
																	]))
															]), "a", "b", "c", "d")
		val anonList = obsBlk.createAnonList(#[
			createEnumPair('type', 'categorical'),
			createAssignPair('variable', createSymbolRef(randVar))
		])
		
		val actual = writeListObservations(anonList, 1)
		val expected = '''
		<ObservationModel blkId="om1">
			<Discrete>
				<CategoricalData ordered="no">
					<ListOfCategories>
						<Category symbId="a"/>
						<Category symbId="b"/>
						<Category symbId="c"/>
						<Category symbId="d"/>
					</ListOfCategories>
					<CategoryVariable symbId="Y"/>
					<PMF>
						<Distribution>
							<ProbOnto xmlns="http://www.pharmml.org/probonto/ProbOnto" name="CategoricalNonordered1">
								<Parameter name="categoryProb">
									<ct:Assign>
										<ct:Vector>
											<ct:VectorElements>
												<ct:SymbRef blkIdRef="sm" symbIdRef="P1"/>
												<ct:SymbRef blkIdRef="sm" symbIdRef="P2"/>
												<ct:SymbRef blkIdRef="sm" symbIdRef="P3"/>
												<ct:SymbRef blkIdRef="sm" symbIdRef="P4"/>
											</ct:VectorElements>
										</ct:Vector>
									</ct:Assign>
								</Parameter>
							</ProbOnto>
						</Distribution>
					</PMF>
				</CategoricalData>
			</Discrete>
		</ObservationModel>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

}