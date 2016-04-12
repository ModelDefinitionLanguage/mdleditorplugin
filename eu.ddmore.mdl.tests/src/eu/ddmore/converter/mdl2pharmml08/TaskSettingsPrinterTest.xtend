package eu.ddmore.converter.mdl2pharmml08

import com.google.inject.Inject
import eu.ddmore.mdl.MdlAndLibInjectorProvider
import eu.ddmore.mdl.provider.BlockDefinitionTable
import eu.ddmore.mdl.utils.MDLBuildFixture
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.assertEquals

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class TaskSettingsPrinterTest {
	@Inject extension TaskSettingsPrinter
	@Inject extension MDLBuildFixture
	
	@Test
	def void testWriteEstGenericSettingsOnly(){
		val estBlk = createBlock(BlockDefinitionTable::ESTIMATE_BLK)
		val propStatement = estBlk.createPropertyStatement(
						#[createAssignPair('algo', createStringLiteral("foce"))]
						)
		
		val actual = writeSettings(#[propStatement])
		val expected = '''
			<Operation order="1" opType="generic">
				<Property name="algo">
					<ct:Assign>
						<ct:String>foce</ct:String>
					</ct:Assign>
				</Property>
			</Operation>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}


}