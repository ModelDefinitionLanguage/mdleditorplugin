package eu.ddmore.converter.mdl2pharmml08

import com.google.inject.Inject
import eu.ddmore.mdl.MdlAndLibInjectorProvider
import eu.ddmore.mdl.mdl.BlockStatementBody
import eu.ddmore.mdl.provider.BlockDefinitionTable
import eu.ddmore.mdl.utils.MDLBuildFixture
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.assertEquals

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class NonListIndivParamWriterTest {
	@Inject extension FunctionIndivParamWriter
	@Inject extension MDLBuildFixture
	
	@Test
	def void testWriteIndivParamExplicit(){
		val obsBlk = createBlock(BlockDefinitionTable::MDL_INDIV_PARAMS)
		val mParamsBlk = createBlock(BlockDefinitionTable::MDL_STRUCT_PARAMS)
		val bdy = (obsBlk.body as BlockStatementBody)
		val eqnDefn = obsBlk.createEqnDefn("Z", mParamsBlk.createSymbolRef("POP"))
		bdy.statements.add(eqnDefn)
		
		val actual = writeIndividualParameter(eqnDefn)
		val expected = '''
			<IndividualParameter symbId="Z">
				<ct:Assign>
					<ct:SymbRef blkIdRef="pm" symbIdRef="POP"/>
				</ct:Assign>
			</IndividualParameter>
			'''
		assertEquals("Output as expected", expected, actual.toString)
	}

}