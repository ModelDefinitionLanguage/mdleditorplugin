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

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class SimpleParameterWriterTest {
	@Inject extension SimpleParameterWriter
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
	def void testWriteParameterNoRhs(){
		val obsBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_INDIV_PARAMS))
		val ld = obsBlk.createEqnDefn('tst')
		
		val actual = writeParameter(ld)
		val expected = '''
			<Parameter symbId="tst">
			</Parameter>
			'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteParameterWithRhs(){
		val obsBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_INDIV_PARAMS))
		val ld = obsBlk.createEqnDefn('tst', createRealLiteral(244.5))
		
		val actual = writeParameter(ld)
		val expected = '''
			<Parameter symbId="tst">
				<ct:Assign>
					<ct:Real>244.5</ct:Real>
				</ct:Assign>
			</Parameter>
			'''
		assertEquals("Output as expected", expected, actual.toString)
	}


}