package eu.ddmore.converter.mdl2pharmml08

import com.google.inject.Inject
import eu.ddmore.mdl.MdlAndLibInjectorProvider
import eu.ddmore.mdl.MdlTestHelper
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.provider.BlockDefinitionTable
import eu.ddmore.mdl.utils.LibraryUtils
import eu.ddmore.mdl.utils.MDLBuildFixture
import eu.ddmore.mdl.utils.MdlLibUtils
import eu.ddmore.mdllib.mdllib.Library
import java.nio.file.Paths
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.TemporaryFolder
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.assertEquals

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class TrialDesignDataPrinterTest {
	@Inject extension MDLBuildFixture
	@Inject extension MdlTestHelper<Mcl>
	@Inject extension MdlLibUtils
	@Inject extension LibraryUtils

	@Rule
	public val TemporaryFolder folder = new TemporaryFolder() 
	
	var Library libDefns
	
	@Before
	def void setUp(){
				val dummyMdl = '''
			foo = mdlObj {
				
			}
		'''.parse
		
		libDefns = dummyMdl.objects.head.libraryForObject
		
	}
	
	def private createMogDefn(Mcl it, MclObject dataObj, MclObject mdlObj){
		val mogObj = createObject("fooMog", libDefns.getObjectDefinition("mogObj"))
		val objBlk = mogObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MOG_OBJ_NAME))
		objBlk.createListDefn(dataObj.name, createEnumPair('type', 'dataObj'))
		objBlk.createListDefn(mdlObj.name, createEnumPair('type', 'mdlObj'))
		return mogObj
	}
	
	@Test
	def void testWriteDesignParametersRelativeNotExist(){
		val mdl = createRoot
		val dataObj = mdl.createObject("foo", libDefns.getObjectDefinition("dataObj"))
		val mdlObj = mdl.createObject("foo2", libDefns.getObjectDefinition("mdlObj"))
		val mog = createMogDefn(mdl, dataObj, mdlObj)
		val desBlk = dataObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DATA_SRC_BLK))
		desBlk.createListDefn(dataObj.name, createAssignPair("file", createStringLiteral("foo/bar.txt")),
			createEnumPair("inputFormat", "nonmemFormat")
		)
		
		val tdow = new TrialDesignDataObjectPrinter(mog, new StandardParameterWriter(null))
		val actual = tdow.writeTargetDataSet()
		val expected = '''
			<ExternalDataSet toolName="NONMEM" oid="nm_ds">
				<DataSet xmlns="http://www.pharmml.org/pharmml/0.8/Dataset">
					<Definition>
					</Definition>
					<ExternalFile oid="id">
						<path>foo/bar.txt</path>
						<format>CSV</format>
						<delimiter>COMMA</delimiter>
					</ExternalFile>
				</DataSet>
			</ExternalDataSet>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteDesignParametersRelativeExists(){
		val mdl = createRoot
		val dataObj = mdl.createObject("foo", libDefns.getObjectDefinition("dataObj"))
		val mdlObj = mdl.createObject("foo2", libDefns.getObjectDefinition("mdlObj"))
		val mog = createMogDefn(mdl, dataObj, mdlObj)
		val desBlk = dataObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DATA_SRC_BLK))
		val tstFile = folder.newFile("bar.txt")
		val pwd = Paths.get("").toAbsolutePath()
		val relative = pwd.relativize(tstFile.toPath)
		desBlk.createListDefn(dataObj.name, createAssignPair("file", createStringLiteral(relative.toString)),
			createEnumPair("inputFormat", "nonmemFormat")
		)
		
		val tdow = new TrialDesignDataObjectPrinter(mog, new StandardParameterWriter(null))
		val actual = tdow.writeTargetDataSet()
		val expected = '''
			<ExternalDataSet toolName="NONMEM" oid="nm_ds">
				<DataSet xmlns="http://www.pharmml.org/pharmml/0.8/Dataset">
					<Definition>
					</Definition>
					<ExternalFile oid="id">
						<path>«relative.toString»</path>
						<format>CSV</format>
						<delimiter>COMMA</delimiter>
					</ExternalFile>
				</DataSet>
			</ExternalDataSet>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteDesignParametersToAbsoluteExists(){
		val mdl = createRoot
		val dataObj = mdl.createObject("foo", libDefns.getObjectDefinition("dataObj"))
		val mdlObj = mdl.createObject("foo2", libDefns.getObjectDefinition("mdlObj"))
		val mog = createMogDefn(mdl, dataObj, mdlObj)
		val desBlk = dataObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DATA_SRC_BLK))
		val tstFile = folder.newFile("bar.txt")
		val pwd = Paths.get("").toAbsolutePath()
		val relative = pwd.relativize(tstFile.toPath)
		desBlk.createListDefn(dataObj.name, createAssignPair("file", createStringLiteral(relative.toString)),
			createEnumPair("inputFormat", "nonmemFormat")
		)
		
		val tdow = new TrialDesignDataObjectPrinter(mog, new StandardParameterWriter(null))
		tdow.useAbsoluteDataPath(true)
		val actual = tdow.writeTargetDataSet()
		val expectedFName = tstFile.absolutePath
		val expected = '''
			<ExternalDataSet toolName="NONMEM" oid="nm_ds">
				<DataSet xmlns="http://www.pharmml.org/pharmml/0.8/Dataset">
					<Definition>
					</Definition>
					<ExternalFile oid="id">
						<path>«expectedFName»</path>
						<format>CSV</format>
						<delimiter>COMMA</delimiter>
					</ExternalFile>
				</DataSet>
			</ExternalDataSet>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}


}