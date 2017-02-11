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
import eu.ddmore.mdl.utils.BlockUtils

import static eu.ddmore.converter.mdl2pharmml08.Constants.*
import eu.ddmore.mdl.MdlTestHelper
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.utils.MdlLibUtils
import eu.ddmore.mdl.utils.LibraryUtils
import eu.ddmore.mdllib.mdllib.Library
import org.junit.Before
import org.junit.After

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class TaskSettingsPrinterTest {
	@Inject extension TaskSettingsPrinter
	@Inject extension BlockUtils
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
	def void testWriteEstGenericSettingsOnly(){
		val estBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::ESTIMATE_BLK))
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

	@Test
	def void testWriteEstGenericSettingsWithMcMcOnly(){
		val estBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::ESTIMATE_BLK))
		val propStatement = estBlk.createPropertyStatement(
						#[createAssignPair('algo', createStringLiteral("mcmc"))]
						)
		
		val actual = writeSettings(#[propStatement])
		val expected = '''
			<Operation order="1" opType="generic">
				<Property name="algo">
					<ct:Assign>
						<ct:String>mcmc</ct:String>
					</ct:Assign>
				</Property>
			</Operation>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteEstGenericAndTargetSettings(){
		val estBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::ESTIMATE_BLK))
		estBlk.createPropertyStatement(
						#[createAssignPair('algo', createStringLiteral("foce"))]
						)
		val taskBlk = estBlk.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::TARGET_SETTINGS),
											createAssignPair('target', createStringLiteral("aTgt")))
		
		taskBlk.createPropertyStatement(
						#[createAssignPair('set1', createStringLiteral("val1")),
						createAssignPair('set2', createRealLiteral(2.0)),
						createAssignPair('set3', createBooleanLiteral(true))]
						)
		
		val actual = writeSettings(estBlk.statements)
		val expected = '''
			<Operation order="1" opType="generic">
				<Property name="algo">
					<ct:Assign>
						<ct:String>foce</ct:String>
					</ct:Assign>
				</Property>
			</Operation>
			<Operation order="2" opType="aTgt">
				<Property name="set1">
					<ct:Assign>
						<ct:String>val1</ct:String>
					</ct:Assign>
				</Property>
				<Property name="set2">
					<ct:Assign>
						<ct:Real>2.0</ct:Real>
					</ct:Assign>
				</Property>
				<Property name="set3">
					<ct:Assign>
						<ct:True/>
					</ct:Assign>
				</Property>
			</Operation>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteEstimateWithSettingsFile(){
		val estBlk = createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::ESTIMATE_BLK))
		estBlk.createPropertyStatement(
						#[createAssignPair('algo', createStringLiteral("foce"))]
						)
		val taskBlk = estBlk.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::TARGET_SETTINGS),
											createAssignPair('target', createStringLiteral("aTgt")),
											createAssignPair('settingsFile', createStringLiteral("a/file/path.txt"))
											)
		
		taskBlk.createPropertyStatement(
						#[createAssignPair('set1', createStringLiteral("val1")),
						createAssignPair('set2', createRealLiteral(2.0)),
						createAssignPair('set3', createBooleanLiteral(true))]
						)
		
		val actual = writeSettingsFile(estBlk.statements)
		val expected = '''
			<SoftwareSettings>
				<File oid="oid_«BLK_ESTIM_STEP»1">
					<ds:path>a/file/path.txt</ds:path>
				</File>
			</SoftwareSettings>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}


}