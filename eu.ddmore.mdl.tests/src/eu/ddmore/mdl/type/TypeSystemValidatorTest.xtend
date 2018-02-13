package eu.ddmore.mdl.type

import com.google.inject.Inject
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.provider.BlockDefinitionTable
import eu.ddmore.mdl.tests.MdlAndLibInjectorProvider
import eu.ddmore.mdl.tests.MdlTestHelper
import eu.ddmore.mdl.utils.LibraryUtils
import eu.ddmore.mdl.utils.MDLBuildFixture
import eu.ddmore.mdl.utils.MdlLibUtils
import eu.ddmore.mdl.utils.MdlUtils
import eu.ddmore.mdl.validation.TypeSystemValidator
import eu.ddmore.mdllib.mdllib.Library
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.assertTrue
import static org.junit.Assert.fail

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class TypeSystemValidatorTest {
	@Inject extension MDLBuildFixture
	@Inject extension MdlTestHelper<Mcl>
	@Inject extension MdlLibUtils
	@Inject extension MdlUtils
	@Inject extension LibraryUtils
	@Inject extension TypeSystemValidator
	
	var Library libDefns
	var boolean errorFlag
	
	@Before
	def void setUp(){
				val dummyMdl = '''
			foo = mdlObj {
				
			}
		'''.parse
		
		libDefns = dummyMdl.objects.head.libraryForObject
		errorFlag = false	
	}

	@After
	def void tearDown(){
		errorFlag = false
	}

	@Test
	def void testCheckAttributeTypingWithVectorOfRVs(){
		val mdl = createRoot
		val obj = mdl.createObject("mObj", libDefns.getObjectDefinition("mdlObj"))
		val rvBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_RND_VARS))
		val rvVar = rvBlk.createRandVar("aRv", createNamedFunction(libDefns.getFunctionDefinition('Normal1')))
		
		val indvParBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_INDIV_PARAMS))
		val testVp = createAssignPair('ranEff', createVectorLiteral(rvVar.createSymbolRef))
		val listDef = indvParBlk.createListDefn("ld", createEnumPair('type', 'general'), testVp)
		
		checkAttributeTyping(listDef.firstAttributeList, testVp, [a, b| fail("Incompatible types") ], [fail("Unexpected error call.")])
	}
	
	@Test
	def void testCheckAttributeTypingWithVectorOfNonRVs(){
		val mdl = createRoot
		val obj = mdl.createObject("mObj", libDefns.getObjectDefinition("mdlObj"))
		val paramBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_STRUCT_PARAMS))
		val pVar = paramBlk.createEqnDefn("aRv", createRealLiteral(1.4))
		
		val indvParBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_INDIV_PARAMS))
		val testVp = createAssignPair('ranEff', createVectorLiteral(pVar.createSymbolRef))
		val listDef = indvParBlk.createListDefn("ld", createEnumPair('type', 'general'), testVp)
		
		checkAttributeTyping(listDef.firstAttributeList, testVp, [a, b| errorFlag=true ], [fail("Unexpected error call.")])
		assertTrue("Type error expected", errorFlag)
	}
	
}