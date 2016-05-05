package eu.ddmore.mdl.provider

import eu.ddmore.mdl.MdlInjectorProvider
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.runner.RunWith
import org.junit.Test
import org.junit.Before
import static org.junit.Assert.*
import eu.ddmore.mdl.utils.MDLBuildFixture
import com.google.inject.Inject
import eu.ddmore.mdllib.mdllib.Library
import eu.ddmore.mdl.MdlTestHelper
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.utils.MdlLibUtils
import eu.ddmore.mdl.utils.LibraryUtils
import eu.ddmore.mdl.mdl.MdlFactory
import eu.ddmore.mdl.mdl.BlockStatementBody
import eu.ddmore.mdl.utils.MDLLibraryBuildFixture
import eu.ddmore.mdllib.mdllib.TypeClass
import eu.ddmore.mdl.utils.MdlUtils

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlInjectorProvider))
class ListTypeProviderTest {
	@Inject extension MDLBuildFixture
	@Inject extension MDLLibraryBuildFixture
	@Inject extension MdlTestHelper<Mcl>
	@Inject extension MdlLibUtils
	@Inject extension MdlUtils
	@Inject extension LibraryUtils

	var ListDefinitionProvider testInstance	
	
//	var Library libDefns
	
	@Before
	def void setUp(){
		testInstance = new ListDefinitionProvider
//		val dummyMdl = '''
//			foo = mdlObj {
//				
//			}
//		'''.parse
//		
//		libDefns = dummyMdl.objects.head.libraryForObject
	}
	
	@Test
	def void testGetListDefinitionWithSingleList(){
		val libDefn = createLibraryRoot
		val realType = libDefn.createTypeDefinition('Real', TypeClass.REAL)
		val listDefn = libDefn.createListTypeDefinition(realType, null, #[
			createAttributeDefn("att1", createTypeSpec(realType, null))
		])
		val blkDefn = libDefn.createBlockDefn("testBlk", listDefn)
		
		val obsBlk = createBlock(blkDefn)
		val ld = obsBlk.createListDefn("CL", #[
			createAssignPair('att1', createRealLiteral(2.2))
		])
		
		val actualLstDefn = testInstance.getListDefinition(ld.firstAttributeList)
		assertEquals("Expected defn", listDefn, actualLstDefn.listTypeDefinition)
	}
	
	@Test
	def void testGetListDefinitionWithSingleListWrongAtt(){
		val libDefn = createLibraryRoot
		val realType = libDefn.createTypeDefinition('Real', TypeClass.REAL)
		val listDefn = libDefn.createListTypeDefinition(realType, null, #[
			createAttributeDefn("att1", createTypeSpec(realType, null))
		])
		val blkDefn = libDefn.createBlockDefn("testBlk", listDefn)
		
		val obsBlk = createBlock(blkDefn)
		val ld = obsBlk.createListDefn("CL", #[
			createAssignPair('att99', createRealLiteral(2.2))
		])
		
		val actualLstDefn = testInstance.getListDefinition(ld.firstAttributeList)
		assertEquals("Expected defn", listDefn, actualLstDefn.listTypeDefinition)
	}
	
	@Test
	def void testGetListDefinitionWithSingleKeyValues(){
		val libDefn = createLibraryRoot
		val realType = libDefn.createTypeDefinition('Real', TypeClass.REAL)
		val keyType = libDefn.createEnumTypeDefinition("keyType", TypeClass.ENUM, #["val1", "val2"])
		val listDefn = libDefn.createListTypeDefinition(realType, null, #[
			createAttributeDefn("keyAtt", createTypeSpec(keyType, null)),
			createAttributeDefn("att1", createTypeSpec(realType, null))
		])
		val blkDefn = libDefn.createBlockDefnWithKeyValues("testBlk", "keyAtt", #{ 'val1' -> listDefn })
		
		val obsBlk = createBlock(blkDefn)
		val ld = obsBlk.createListDefn("CL", #[
			createEnumPair('keyAtt', "val1"),
			createAssignPair('att1', createRealLiteral(2.2))
		])
		
		val actualLstDefn = testInstance.getListDefinition(ld.firstAttributeList)
		assertEquals("Expected defn", listDefn, actualLstDefn.listTypeDefinition)
	}
	
	@Test
	def void testGetListDefinitionWithSingleKeyValWrongValue(){
		val libDefn = createLibraryRoot
		val realType = libDefn.createTypeDefinition('Real', TypeClass.REAL)
		val keyType = libDefn.createEnumTypeDefinition("keyType", TypeClass.ENUM, #["val1", "val2"])
		val listDefn = libDefn.createListTypeDefinition(realType, null, #[
			createAttributeDefn("keyAtt", createTypeSpec(keyType, null)),
			createAttributeDefn("att1", createTypeSpec(realType, null))
		])
		val blkDefn = libDefn.createBlockDefnWithKeyValues("testBlk", "keyAtt", #{ 'val1' -> listDefn })
		
		val obsBlk = createBlock(blkDefn)
		val ld = obsBlk.createListDefn("CL", #[
			createEnumPair('keyAtt', "val2"),
			createAssignPair('att1', createRealLiteral(2.2))
		])
		
		val actualLstDefn = testInstance.getListDefinition(ld.firstAttributeList)
		assertNull("Expected defn", actualLstDefn)
	}
	
	@Test
	def void testGetListDefinitionWithMultiKeyValues(){
		val libDefn = createLibraryRoot
		val realType = libDefn.createTypeDefinition('Real', TypeClass.REAL)
		val keyType = libDefn.createEnumTypeDefinition("keyType", TypeClass.ENUM, #["val1", "val2"])
		val listDefn = libDefn.createListTypeDefinition(realType, null, #[
			createAttributeDefn("keyAtt", createTypeSpec(keyType, null)),
			createAttributeDefn("att1", createTypeSpec(realType, null))
		])
		val listDefn2 = libDefn.createListTypeDefinition(realType, null, #[
			createAttributeDefn("keyAtt", createTypeSpec(keyType, null)),
			createAttributeDefn("att2", createTypeSpec(realType, null))
		])
		val blkDefn = libDefn.createBlockDefnWithKeyValues("testBlk", "keyAtt", #{ 'val1' -> listDefn, 'val2' -> listDefn2 })
		
		val obsBlk = createBlock(blkDefn)
		val ld = obsBlk.createListDefn("CL", #[
			createEnumPair('keyAtt', "val2"),
			createAssignPair('att1', createRealLiteral(2.2))
		])
		
		val actualLstDefn = testInstance.getListDefinition(ld.firstAttributeList)
		assertEquals("Expected defn", listDefn2, actualLstDefn.listTypeDefinition)
	}
	
	
}