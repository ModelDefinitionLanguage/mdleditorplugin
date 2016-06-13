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
import eu.ddmore.mdllib.mdllib.SymbolDefinition
import java.util.List
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.assertEquals

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class CovariateModelWriterTest {
	@Inject extension MDLBuildFixture
	@Inject extension MdlTestHelper<Mcl>
	@Inject extension MdlLibUtils
	@Inject extension LibraryUtils
	@Inject extension CovariateModelWriter
	
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
	def void testWriteContinuousCovariate(){
		val root = createRoot
		val mObj = root.createObject("mObj", libDefns.getObjectDefinition('mdlObj'))
		val covBlk = mObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::COVARIATE_BLK_NAME))
		val covW = covBlk.createEqnDefn('W')
		
		val List<SymbolDefinition> covList = #[ covW ]
		val actual = covList.writeCovariateModel
		val expected = '''
			<CovariateModel blkId="cm">
				<Covariate symbId="W">
					<Continuous/>
				</Covariate>
			</CovariateModel>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteContinuousTransformedCovariate(){
		val root = createRoot
		val mObj = root.createObject("mObj", libDefns.getObjectDefinition('mdlObj'))
		val covBlk = mObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::COVARIATE_BLK_NAME))
		val covW = covBlk.createEqnDefn('W')
		val covLnW = covBlk.createEqnDefn('lnW', createFunctionCall(libDefns.getFunctionDefinition('ln'), covW.createSymbolRef))
		
		val List<SymbolDefinition> covList = #[ covLnW, covW ]
		val actual = covList.writeCovariateModel
		val expected = '''
			<CovariateModel blkId="cm">
				<Covariate symbId="W">
					<Continuous>
						<Transformation>
						    <TransformedCovariate symbId="lnW"/>
							<ct:Assign>
								<math:Uniop op="log">
									<ct:SymbRef blkIdRef="cm" symbIdRef="W"/>
								</math:Uniop>	
							</ct:Assign>
						</Transformation>
					</Continuous>
				</Covariate>
			</CovariateModel>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteContinuousCovariateNormal(){
		val root = createRoot
		val mObj = root.createObject("mObj", libDefns.getObjectDefinition('mdlObj'))
		val covBlk = mObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::COVARIATE_BLK_NAME))
		val covW = covBlk.createRandVar('W', createNamedFunction(libDefns.getFunctionDefinition('Normal1'), 
																	createAssignPair('mean', createRealLiteral(70.0)),
																	createAssignPair('stdev', createRealLiteral(10.0))
																)
										)
		val List<SymbolDefinition> covList = #[ covW ]
		val actual = covList.writeCovariateModel
		val expected = '''
			<CovariateModel blkId="cm">
				<Covariate symbId="W">
					<Continuous>
						<Distribution>
							<ProbOnto xmlns="http://www.pharmml.org/probonto/ProbOnto" name="Normal1">
								<Parameter name="mean">
									<ct:Assign>
										<ct:Real>70.0</ct:Real>
									</ct:Assign>
								</Parameter>
								<Parameter name="stdev">
									<ct:Assign>
										<ct:Real>10.0</ct:Real>
									</ct:Assign>
								</Parameter>
							</ProbOnto>
						</Distribution>
					</Continuous>
				</Covariate>
			</CovariateModel>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteCategoricalCovariate(){
		val root = createRoot
		val mObj = root.createObject("mObj", libDefns.getObjectDefinition('mdlObj'))
		val covBlk = mObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::COVARIATE_BLK_NAME))
		val covW = covBlk.createCategoricalDefinition('S', 'male', 'female')
		
		val List<SymbolDefinition> covList = #[ covW ]
		val actual = covList.writeCovariateModel
		val expected = '''
			<CovariateModel blkId="cm">
				<Covariate symbId="S">
					<Categorical>
						<Category catId="male"/>
						<Category catId="female"/>
					</Categorical>
				</Covariate>
			</CovariateModel>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}


	@Test
	def void testWriteCategoricalCovariateWithDistn(){
		val root = createRoot
		val mObj = root.createObject("mObj", libDefns.getObjectDefinition('mdlObj'))
		val covBlk = mObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::COVARIATE_BLK_NAME))
		val covW = covBlk.createCategoricalDefinition('S', createNamedFunction(libDefns.getFunctionDefinition('Bernoulli1'),
																					createAssignPair('probabilityOfSuccess', createRealLiteral(0.5))
																				),
														'male', 'female')
		
		val List<SymbolDefinition> covList = #[ covW ]
		val actual = covList.writeCovariateModel
		val expected = '''
			<CovariateModel blkId="cm">
				<Covariate symbId="S">
					<Categorical>
						<Category catId="male"/>
						<Category catId="female"/>
						<Distribution>
							<ProbOnto xmlns="http://www.pharmml.org/probonto/ProbOnto" name="Bernoulli1">
								<Parameter name="probabilityOfSuccess">
									<ct:Assign>
										<ct:Real>0.5</ct:Real>
									</ct:Assign>
								</Parameter>
							</ProbOnto>
						</Distribution>
					</Categorical>
				</Covariate>
			</CovariateModel>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteCovariateWithDiscreteDistn(){
		val root = createRoot
		val mObj = root.createObject("mObj", libDefns.getObjectDefinition('mdlObj'))
		val covBlk = mObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::COVARIATE_BLK_NAME))
		val covW = covBlk.createRandVar('S', createNamedFunction(libDefns.getFunctionDefinition('Poisson1'),
																					createAssignPair('rate', createRealLiteral(1.0))
																				))
		
		val List<SymbolDefinition> covList = #[ covW ]
		val actual = covList.writeCovariateModel
		val expected = '''
		<CovariateModel blkId="cm">
			<Covariate symbId="S">
				<Categorical>
					<Distribution>
						<ProbOnto xmlns="http://www.pharmml.org/probonto/ProbOnto" name="Poisson1">
							<Parameter name="rate">
								<ct:Assign>
									<ct:Real>1.0</ct:Real>
								</ct:Assign>
							</Parameter>
						</ProbOnto>
					</Distribution>
				</Categorical>
			</Covariate>
		</CovariateModel>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

}