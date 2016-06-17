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
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.assertEquals
import org.junit.Ignore

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class PriorParameterWriterTest {
	@Inject extension MDLBuildFixture
	@Inject extension MdlTestHelper<Mcl>
	@Inject extension MdlLibUtils
	@Inject extension LibraryUtils
	
	var Library libDefns
	var PriorParameterWriter testInstance
	
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
	def void testWritePriorDistn(){
		val root = createRoot
		val priorObj = root.createObject("pObj", libDefns.getObjectDefinition('priorObj'))
		val priorBlk = priorObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::PRIOR_VAR_DEFN))
		priorBlk.createRandVar('p1',
							createNamedFunction(libDefns.getFunctionDefinition('Gamma2'),
									createAssignPair('shape', createRealLiteral(2.0)),
									createAssignPair('rate', createRealLiteral(3.0))
							)
						)
		
		val mdlObj = root.createObject("mObj", libDefns.getObjectDefinition('mdlObj'))
		val paramBlk = mdlObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_STRUCT_PARAMS))
		val parmStmt = paramBlk.createEqnDefn('p1')
		
		
		this.testInstance = new PriorParameterWriter(null, priorObj, [null])
		val actual = testInstance.writeParameter(parmStmt)
		val expected = '''
			<PopulationParameter symbId="p1">
				<ct:VariabilityReference>
					<ct:SymbRef blkIdRef="vm_mdl" symbIdRef="MDL__prior"/>
				</ct:VariabilityReference>
				<Distribution>
					<ProbOnto xmlns="http://www.pharmml.org/probonto/ProbOnto" name="Gamma2">
						<Parameter name="shape">
							<ct:Assign>
								<ct:Real>2.0</ct:Real>
							</ct:Assign>
						</Parameter>
						<Parameter name="rate">
							<ct:Assign>
								<ct:Real>3.0</ct:Real>
							</ct:Assign>
						</Parameter>
					</ProbOnto>
				</Distribution>
			</PopulationParameter>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}


	@Test
	def void testWritePriorWithMathsExpr(){
		val root = createRoot
		val priorObj = root.createObject("pObj", libDefns.getObjectDefinition('priorObj'))
		val priorBlk = priorObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::PRIOR_VAR_DEFN))
		val symbRef = priorBlk.createEqnDefn("anOther", createRealLiteral(2.0))
		priorBlk.createEqnDefn('p1', symbRef.createSymbolRef)
		
		val mdlObj = root.createObject("mObj", libDefns.getObjectDefinition('mdlObj'))
		val paramBlk = mdlObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_STRUCT_PARAMS))
		val parmStmt = paramBlk.createEqnDefn('p1')
		
		
		this.testInstance = new PriorParameterWriter(null, priorObj, [null])
		val actual = testInstance.writeParameter(parmStmt)
		val expected = '''
			<PopulationParameter symbId="p1">
				<ct:Assign>
					<ct:SymbRef blkIdRef="pm" symbIdRef="anOther"/>
				</ct:Assign>
			</PopulationParameter>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}


	@Test
	def void testWritePriorWithLiteral(){
		val root = createRoot
		val priorObj = root.createObject("pObj", libDefns.getObjectDefinition('priorObj'))
		val priorBlk = priorObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::PRIOR_VAR_DEFN))
		priorBlk.createEqnDefn('p1',createRealLiteral(2.0))
		
		val mdlObj = root.createObject("mObj", libDefns.getObjectDefinition('mdlObj'))
		val paramBlk = mdlObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_STRUCT_PARAMS))
		val parmStmt = paramBlk.createEqnDefn('p1')
		
		
		this.testInstance = new PriorParameterWriter(null, priorObj, [null])
		val actual = testInstance.writeParameter(parmStmt)
		val expected = '''
			<PopulationParameter symbId="p1">
				<ct:Assign>
					<ct:Real>2.0</ct:Real>
				</ct:Assign>
			</PopulationParameter>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}


	@Test
	def void testWriteParamNotInPriorObj(){
		val root = createRoot
		val priorObj = root.createObject("pObj", libDefns.getObjectDefinition('priorObj'))
		val priorBlk = priorObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::PRIOR_VAR_DEFN))
		priorBlk.createEqnDefn('p111',createRealLiteral(2.0))
		
		val mdlObj = root.createObject("mObj", libDefns.getObjectDefinition('mdlObj'))
		val paramBlk = mdlObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_STRUCT_PARAMS))
		val parmStmt = paramBlk.createEqnDefn('p1', createRealLiteral(22.0))
		
		
		this.testInstance = new PriorParameterWriter(null, priorObj, [null])
		val actual = testInstance.writeParameter(parmStmt)
		val expected = '''
			<PopulationParameter symbId="p1">
				<ct:Assign>
					<ct:Real>22.0</ct:Real>
				</ct:Assign>
			</PopulationParameter>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Ignore("Not sure how to encode in PharmML at the moment")
	def void testWriteParamFromData(){
		val root = createRoot
		val priorObj = root.createObject("pObj", libDefns.getObjectDefinition('priorObj'))
//		val priorSrcBlk = priorObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::PRIOR_SOURCE_BLK))
		
		
		
		val priorBlk = priorObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::PRIOR_VAR_DEFN))
		priorBlk.createEqnDefn('p111',createRealLiteral(2.0))
		
		val mdlObj = root.createObject("mObj", libDefns.getObjectDefinition('mdlObj'))
		val paramBlk = mdlObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_STRUCT_PARAMS))
		val parmStmt = paramBlk.createEqnDefn('p1', createRealLiteral(22.0))
		
		
		this.testInstance = new PriorParameterWriter(null, priorObj, [null])
		val actual = testInstance.writeParameter(parmStmt)
		val expected = '''
			<PopulationParameter symbId="p1"/>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}


	@Test
	def void testWriteVarLevels(){
		val root = createRoot
		val priorObj = root.createObject("pObj", libDefns.getObjectDefinition('priorObj'))
		
		val mdlObj = root.createObject("mObj", libDefns.getObjectDefinition('mdlObj'))
		val varLvlBlk = mdlObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::VAR_LVL_BLK_NAME))
		varLvlBlk.createListDefn("bsv", createEnumPair('type', 'parameter'), createAssignPair('level', createIntLiteral(1)))
		
		this.testInstance = new PriorParameterWriter(mdlObj, priorObj, [null])
		val actual = testInstance.writeVariabilityModel
		val expected = '''
			<VariabilityModel blkId="vm_mdl" type="parameterVariability">
				<Level referenceLevel="false" symbId="MDL__prior"/>
				<Level referenceLevel="false" symbId="bsv">
					<ParentLevel>
						<ct:SymbRef symbIdRef="MDL__prior"/>
					</ParentLevel>
				</Level>
			</VariabilityModel>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}



}