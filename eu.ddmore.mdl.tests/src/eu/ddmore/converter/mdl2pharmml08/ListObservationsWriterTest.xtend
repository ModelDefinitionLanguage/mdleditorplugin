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

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class ListObservationsWriterTest {
	@Inject extension ListObservationsWriter
	@Inject extension MDLBuildFixture
	
	@Test
	def void testWriteCombinedError2NoTrans(){
		val obsBlk = createBlock(BlockDefinitionTable::OBS_BLK_NAME)
		val mPredBlk = createBlock(BlockDefinitionTable::MDL_PRED_BLK_NAME)
		val mParamsBlk = createBlock(BlockDefinitionTable::MDL_STRUCT_PARAMS)
		val rvBlk = createBlock(BlockDefinitionTable::MDL_RND_VARS)
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
		val obsBlk = createBlock(BlockDefinitionTable::OBS_BLK_NAME)
		val mPredBlk = createBlock(BlockDefinitionTable::MDL_PRED_BLK_NAME)
		val mParamsBlk = createBlock(BlockDefinitionTable::MDL_STRUCT_PARAMS)
		val rvBlk = createBlock(BlockDefinitionTable::MDL_RND_VARS)
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
		val obsBlk = createBlock(BlockDefinitionTable::OBS_BLK_NAME)
		val mPredBlk = createBlock(BlockDefinitionTable::MDL_PRED_BLK_NAME)
		val mParamsBlk = createBlock(BlockDefinitionTable::MDL_STRUCT_PARAMS)
		val rvBlk = createBlock(BlockDefinitionTable::MDL_RND_VARS)
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
		val obsBlk = createBlock(BlockDefinitionTable::OBS_BLK_NAME)
		val mParamsBlk = createBlock(BlockDefinitionTable::MDL_STRUCT_PARAMS)
		val varLvlBlock = createBlock(BlockDefinitionTable::VAR_LVL_BLK_NAME)
		val lvlListDefn = varLvlBlock.createListDefn("DV", #[
											createEnumPair("type", "observation"),
											createAssignPair("level", createIntLiteral(1))
										])
		val rvBlk = createBlock(BlockDefinitionTable::MDL_RND_VARS, #[
																	createAssignPair("level", createSymbolRef(lvlListDefn))
																	])
		val randVar = rvBlk.createRandVar("Y", createNamedFunction("Normal", #[
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
						<UncertML>
							<NormalDistribution xmlns="http://www.uncertml.org/3.0" definition="http://www.uncertml.org/distributions/normal">
								<mean>
									<rVal>0.0</rVal>
								</mean>
								<stddev>
									<var varId="pm.B"/>
								</stddev>
							</NormalDistribution>
						</UncertML>
					</Distribution>
				</General>
			</ContinuousData>
		</ObservationModel>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteCountObs(){
		val obsBlk = createBlock(BlockDefinitionTable::OBS_BLK_NAME)
		val smBlk = createBlock(BlockDefinitionTable::MDL_PRED_BLK_NAME)
		val varLvlBlock = createBlock(BlockDefinitionTable::VAR_LVL_BLK_NAME)
		val lvlListDefn = varLvlBlock.createListDefn("DV", #[
											createEnumPair("type", "observation"),
											createAssignPair("level", createIntLiteral(1))
										])
		val rvBlk = createBlock(BlockDefinitionTable::MDL_RND_VARS, #[
																	createAssignPair("level", createSymbolRef(lvlListDefn))
																	])
		val lambda = smBlk.createEqnDefn("LAMBDA", createRealLiteral(22.2))
		val randVar = rvBlk.createRandVar("Y", createNamedFunction("Poisson", #[
																createAssignPair("lambda", createSymbolRef(lambda))
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
								<UncertML>
									<PoissonDistribution xmlns="http://www.uncertml.org/3.0" definition="http://www.uncertml.org/distributions/poisson">
										<rate>
											<var varId="sm.LAMBDA"/>
										</rate>
									</PoissonDistribution>
								</UncertML>
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
		val obsBlk = createBlock(BlockDefinitionTable::OBS_BLK_NAME)
		val smBlk = createBlock(BlockDefinitionTable::MDL_PRED_BLK_NAME)
		val varLvlBlock = createBlock(BlockDefinitionTable::VAR_LVL_BLK_NAME)
		val lvlListDefn = varLvlBlock.createListDefn("DV", #[
											createEnumPair("type", "observation"),
											createAssignPair("level", createIntLiteral(1))
										])
		val rvBlk = createBlock(BlockDefinitionTable::MDL_RND_VARS, #[
																	createAssignPair("level", createSymbolRef(lvlListDefn))
																	])
		val lambda = smBlk.createEqnDefn("P1", createRealLiteral(22.2))
		val randVar = rvBlk.createCategoricalDefinition("Y", #["a", "b"], createNamedFunction("Bernoulli", #[
																createAssignPair("probability", createSymbolRef(lambda))
															])) 
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
		val obsBlk = createBlock(BlockDefinitionTable::OBS_BLK_NAME)
		val smBlk = createBlock(BlockDefinitionTable::MDL_PRED_BLK_NAME)
		val varLvlBlock = createBlock(BlockDefinitionTable::VAR_LVL_BLK_NAME)
		val lvlListDefn = varLvlBlock.createListDefn("DV", #[
											createEnumPair("type", "observation"),
											createAssignPair("level", createIntLiteral(1))
										])
		val rvBlk = createBlock(BlockDefinitionTable::MDL_RND_VARS, #[
																	createAssignPair("level", createSymbolRef(lvlListDefn))
																	])
		val p1 = smBlk.createEqnDefn("P1", createRealLiteral(22.2))
		val randVar = rvBlk.createCategoricalDefinition("Y", #["a", "b"], createNamedFunction("Binomial", #[
																createAssignPair("probabilityOfSuccess", createSymbolRef(p1)),
																createAssignPair("numberOfTrials", createIntLiteral(1))
															])) 
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
							<UncertML>
								<BinomialDistribution xmlns="http://www.uncertml.org/3.0" definition="http://www.uncertml.org/3.0">
									<numberOfTrials>
										<nVal>1</nVal>
									</numberOfTrials>
									<probabilityOfSuccess>
										<var varId="sm.P1"/>
									</probabilityOfSuccess>
								</BinomialDistribution>
							</UncertML>
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
		val obsBlk = createBlock(BlockDefinitionTable::OBS_BLK_NAME)
		val smBlk = createBlock(BlockDefinitionTable::MDL_PRED_BLK_NAME)
		val varLvlBlock = createBlock(BlockDefinitionTable::VAR_LVL_BLK_NAME)
		val lvlListDefn = varLvlBlock.createListDefn("DV", #[
											createEnumPair("type", "observation"),
											createAssignPair("level", createIntLiteral(1))
										])
		val rvBlk = createBlock(BlockDefinitionTable::MDL_RND_VARS, #[
																	createAssignPair("level", createSymbolRef(lvlListDefn))
																	])
		val p1 = smBlk.createEqnDefn("P1", createRealLiteral(22.2))
		val p2 = smBlk.createEqnDefn("P2", createRealLiteral(23.2))
		val p3 = smBlk.createEqnDefn("P3", createRealLiteral(24.2))
		val p4 = smBlk.createEqnDefn("P4", createRealLiteral(25.2))
		val randVar = rvBlk.createCategoricalDefinition("Y", #["a", "b", "c", "d"], createNamedFunction("CategoricalNonordered1", #[
																createAssignPair("categoryProb", createVectorLiteral(#[
																	createSymbolRef(p1),
																	createSymbolRef(p2),
																	createSymbolRef(p3),
																	createSymbolRef(p4)
																	]))
															]))
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