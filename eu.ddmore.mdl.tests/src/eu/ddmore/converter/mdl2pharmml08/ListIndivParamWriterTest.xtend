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
import org.junit.Before
import eu.ddmore.mdl.utils.LibraryUtils
import eu.ddmore.mdl.utils.MdlLibUtils
import eu.ddmore.mdllib.mdllib.Library
import org.junit.After
import eu.ddmore.mdl.MdlTestHelper
import eu.ddmore.mdl.mdl.Mcl

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class ListIndivParamWriterTest {
	@Inject extension ListIndivParamWriter
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
	def void testWriteIndivParamLinearNoTrans(){
		val obsBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_INDIV_PARAMS))
		val covBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::COVARIATE_BLK_NAME))
		val mParamsBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_STRUCT_PARAMS))
		val rvBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_RND_VARS))
		val ld = MdlFactory.eINSTANCE.createListDefinition
		val bdy = (obsBlk.body as BlockStatementBody)
		bdy.statements.add(ld)
		val attList = MdlFactory.eINSTANCE.createAttributeList
		ld.setName("CL")
		ld.list = attList
		attList.attributes.add(createEnumPair('type', 'linear'))
		attList.attributes.add(createAssignPair('pop', mParamsBlk.createSymbolRef('A')))
		attList.attributes.add(createAssignPair('fixEff', createVectorLiteral(#[createSublist(#{ "cov"->covBlk.createSymbolRef("W"), 
																							"coeff"->mParamsBlk.createSymbolRef("BETA_W") })])))
		attList.attributes.add(createAssignPair('ranEff', createVectorLiteral(#[rvBlk.createSymbolRef('ETA')])))
		
		val actual = writeIndividualParameter(ld)
		val expected = '''
			<IndividualParameter symbId="CL">
				<StructuredModel>
					<LinearCovariate>
						<PopulationValue>
							<ct:Assign>
								<ct:SymbRef blkIdRef="pm" symbIdRef="A"/>
							</ct:Assign>
						</PopulationValue>
						<Covariate>
							<ct:SymbRef blkIdRef="cm" symbIdRef="W"/>
							<FixedEffect>
								<ct:SymbRef blkIdRef="pm" symbIdRef="BETA_W"/>
							</FixedEffect>
						</Covariate>
					</LinearCovariate>
					<RandomEffects>
						<ct:SymbRef blkIdRef="pm" symbIdRef="ETA"/>
					</RandomEffects>
				</StructuredModel>
			</IndividualParameter>
			'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteIndivParamLinearLogitTrans(){
		val obsBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_INDIV_PARAMS))
		val covBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::COVARIATE_BLK_NAME))
		val mParamsBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_STRUCT_PARAMS))
		val rvBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_RND_VARS))
		val ld = MdlFactory.eINSTANCE.createListDefinition
		val bdy = (obsBlk.body as BlockStatementBody)
		bdy.statements.add(ld)
		val attList = MdlFactory.eINSTANCE.createAttributeList
		ld.setName("CL")
		ld.list = attList
		attList.attributes.add(createEnumPair('type', 'linear'))
		attList.attributes.add(createEnumPair('trans', 'logit'))
		attList.attributes.add(createAssignPair('pop', mParamsBlk.createSymbolRef('A')))
		attList.attributes.add(createAssignPair('fixEff', createVectorLiteral(#[createSublist(#{ "cov"->covBlk.createSymbolRef("W"), 
																							"coeff"->mParamsBlk.createSymbolRef("BETA_W") })])))
		attList.attributes.add(createAssignPair('ranEff', createVectorLiteral(#[rvBlk.createSymbolRef('ETA')])))
		
		val actual = writeIndividualParameter(ld)
		val expected = '''
			<IndividualParameter symbId="CL">
				<StructuredModel>
					<Transformation type="logit" />
					<LinearCovariate>
						<PopulationValue>
							<ct:Assign>
								<ct:SymbRef blkIdRef="pm" symbIdRef="A"/>
							</ct:Assign>
						</PopulationValue>
						<Covariate>
							<ct:SymbRef blkIdRef="cm" symbIdRef="W"/>
							<FixedEffect>
								<ct:SymbRef blkIdRef="pm" symbIdRef="BETA_W"/>
							</FixedEffect>
						</Covariate>
					</LinearCovariate>
					<RandomEffects>
						<ct:SymbRef blkIdRef="pm" symbIdRef="ETA"/>
					</RandomEffects>
				</StructuredModel>
			</IndividualParameter>
			'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteIndivParamGeneralNoTrans(){
		val obsBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_INDIV_PARAMS))
		val mParamsBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_STRUCT_PARAMS))
		val rvBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_RND_VARS))
		val ld = MdlFactory.eINSTANCE.createListDefinition
		val bdy = (obsBlk.body as BlockStatementBody)
		bdy.statements.add(ld)
		val attList = MdlFactory.eINSTANCE.createAttributeList
		ld.setName("CL")
		ld.list = attList
		attList.attributes.add(createEnumPair('type', 'general'))
		attList.attributes.add(createAssignPair('grp', mParamsBlk.createSymbolRef('A')))
		attList.attributes.add(createAssignPair('ranEff', createVectorLiteral(#[rvBlk.createSymbolRef('ETA')])))
		
		val actual = writeIndividualParameter(ld)
		val expected = '''
			<IndividualParameter symbId="CL">
				<StructuredModel>
					<GeneralCovariate>
						<ct:Assign>
							<ct:SymbRef blkIdRef="pm" symbIdRef="A"/>
						</ct:Assign>
					</GeneralCovariate>
					<RandomEffects>
						<ct:SymbRef blkIdRef="pm" symbIdRef="ETA"/>
					</RandomEffects>
				</StructuredModel>
			</IndividualParameter>
			'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteIndivParamGeneralLogitTrans(){
		val obsBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_INDIV_PARAMS))
		val mParamsBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_STRUCT_PARAMS))
		val rvBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_RND_VARS))
		val ld = MdlFactory.eINSTANCE.createListDefinition
		val bdy = (obsBlk.body as BlockStatementBody)
		bdy.statements.add(ld)
		val attList = MdlFactory.eINSTANCE.createAttributeList
		ld.setName("CL")
		ld.list = attList
		attList.attributes.add(createEnumPair('type', 'general'))
		attList.attributes.add(createEnumPair('trans', 'logit'))
		attList.attributes.add(createAssignPair('grp', mParamsBlk.createSymbolRef('A')))
		attList.attributes.add(createAssignPair('ranEff', createVectorLiteral(#[rvBlk.createSymbolRef('ETA')])))
		
		val actual = writeIndividualParameter(ld)
		val expected = '''
			<IndividualParameter symbId="CL">
				<StructuredModel>
					<Transformation type="logit"/>
					<GeneralCovariate>
						<ct:Assign>
							<ct:SymbRef blkIdRef="pm" symbIdRef="A"/>
						</ct:Assign>
					</GeneralCovariate>
					<RandomEffects>
						<ct:SymbRef blkIdRef="pm" symbIdRef="ETA"/>
					</RandomEffects>
				</StructuredModel>
			</IndividualParameter>
			'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteIndivUserDefined(){
		val obsBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_INDIV_PARAMS))
		val ld = obsBlk.createListDefn('CL', createEnumPair('type', 'userDefined'),
											createAssignPair('value', createRealLiteral(22.0))
		)	
		
		val actual = writeIndividualParameter(ld)
		val expected = '''
			<IndividualParameter symbId="CL">
				<ct:Assign>
					<ct:Real>22.0</ct:Real>
				</ct:Assign>
			</IndividualParameter>
			'''
		assertEquals("Output as expected", expected, actual.toString)
	}


}