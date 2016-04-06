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


}