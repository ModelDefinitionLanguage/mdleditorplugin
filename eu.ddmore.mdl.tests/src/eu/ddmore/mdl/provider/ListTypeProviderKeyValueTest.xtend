package eu.ddmore.mdl.provider

import com.google.inject.Inject
import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.tests.MdlInjectorProvider
import eu.ddmore.mdl.type.TypeSystemProvider
import eu.ddmore.mdl.utils.MDLBuildFixture
import eu.ddmore.mdl.utils.MDLLibraryBuildFixture
import eu.ddmore.mdl.utils.MdlLibUtils
import eu.ddmore.mdl.utils.MdlUtils
import eu.ddmore.mdllib.mdllib.BlockDefinition
import eu.ddmore.mdllib.mdllib.Library
import eu.ddmore.mdllib.mdllib.ListAttributeDefn
import eu.ddmore.mdllib.mdllib.ListTypeDefinition
import eu.ddmore.mdllib.mdllib.TypeClass
import eu.ddmore.mdllib.mdllib.TypeDefinition
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.*

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlInjectorProvider))
class ListTypeProviderKeyValueTest {
	@Inject extension MDLBuildFixture
	@Inject extension MDLLibraryBuildFixture
	@Inject extension MdlUtils
	@Inject extension MdlLibUtils

	@Inject extension ListDefinitionProvider
	
	val static String KEY_ATT = "keyAtt"
	val static String KEY_VAL1 = "keyVal1"
	val static String KEY_VAL2 = "keyVal2"
	val static String NON_KEY_VAL = "noVal"
	val static String LIST1_ATT2 = "att1_2"
	val static String LIST2_ATT2 = "att2_2"
	
	var Library libDefn
	var TypeDefinition realType
	var TypeDefinition keyType
	
	var ListAttributeDefn keyAttDefn
	var ListAttributeDefn list1Att2Defn 
	var ListAttributeDefn list2KeyAttDefn 
	var ListAttributeDefn list2Att2Defn 

	var ListTypeDefinition listDefn1
	var ListTypeDefinition listDefn2

	var BlockDefinition blkDefn
	var BlockStatement obsBlk
		
	@Before
	def void setUp(){
		libDefn = createLibraryRoot
		realType = libDefn.createTypeDefinition('Real', TypeClass.REAL)
		keyType = libDefn.createEnumTypeDefinition("keyType", TypeClass.ENUM, #[KEY_VAL1, KEY_VAL2])
		keyAttDefn = createAttributeDefn(KEY_ATT, createTypeSpec(keyType, null))
		list1Att2Defn = createAttributeDefn(LIST1_ATT2, createTypeSpec(realType, null))
		
		listDefn1 = libDefn.createListTypeDefinition(createTypeSpec(realType, null), null, #[ keyAttDefn, list1Att2Defn ])

		list2KeyAttDefn = createAttributeDefn(KEY_ATT, createTypeSpec(keyType, null))
		list2Att2Defn = createAttributeDefn(LIST2_ATT2, createTypeSpec(realType, null)) 
		listDefn2 = libDefn.createListTypeDefinition(createTypeSpec(realType, null), null, #[ list2KeyAttDefn, list2Att2Defn ], #[true, false])
		blkDefn = libDefn.createBlockDefnWithKeyValues("testBlk", KEY_ATT, #{ KEY_VAL1->listDefn1, KEY_VAL2->listDefn2 })
		
		obsBlk = createBlock(blkDefn)
	}
	
	@After
	def void tearDown(){
//		testInstance = null
	}
	
	@Test
	def void testGetListDefinitionWithDifferentKeysList(){
		val ld1 = obsBlk.createListDefn("CL1", #[
			createEnumPair(KEY_ATT, KEY_VAL1),
			createAssignPair(LIST1_ATT2, createRealLiteral(2.2))
		])
		val ld2 = obsBlk.createListDefn("CL2", #[
			createEnumPair(KEY_ATT, KEY_VAL2),
			createAssignPair(LIST2_ATT2, createRealLiteral(2.2))
		])
		
		val actualLstDefn1 = getListDefinition(ld1.firstAttributeList)
		assertEquals("Expected defn", listDefn1, actualLstDefn1.listTypeDefinition)
		val actualLstDefn2 = getListDefinition(ld2.firstAttributeList)
		assertEquals("Expected defn", listDefn2, actualLstDefn2.listTypeDefinition)
	}
	
	
	@Test
	def void testGetListDefinitionWithSingleKeyValWrongValue(){
		val ld = obsBlk.createListDefn("CL", #[
			createEnumPair(KEY_ATT, NON_KEY_VAL),
			createAssignPair(LIST1_ATT2, createRealLiteral(2.2))
		])
		
		val actualLstDefn = getListDefinition(ld.firstAttributeList)
		assertNull("Expected defn", actualLstDefn)
	}
	
	@Test
	def void testGetAttributeDefinitionKey(){
		val ldi = new ListDefInfo(listDefn1)
		val actualAtTDefn = ldi.getAttributeDefinition(KEY_ATT)
		assertEquals("expected defn", new MdlListAttributeDefn(keyAttDefn), actualAtTDefn)
	}
	
	@Test
	def void testGetAttributeDefinitionNonKeyAtt(){
		val ldi = new ListDefInfo(listDefn1)
		val actualAtTDefn = ldi.getAttributeDefinition(LIST1_ATT2)
		assertEquals("expected defn", new MdlListAttributeDefn(list1Att2Defn), actualAtTDefn)
	}
	
	@Test
	def void testGetAttributeDefinitionMissingAtt(){
		val ldi = new ListDefInfo(listDefn1)
		val actualAtTDefn = ldi.getAttributeDefinition(LIST2_ATT2)
		assertNull("expected defn", actualAtTDefn)
	}
	
	@Test
	def void testGetAttributeType(){
		val ldi = new ListDefInfo(listDefn1)
		val actualType = ldi.getAttributeType(KEY_ATT)
		assertEquals("expected defn", keyType.typeInfo, actualType)
	}
	
	@Test
	def void testGetAttributeTypeNonKeyAtt(){
		val ldi = new ListDefInfo(listDefn1)
		val actualType = ldi.getAttributeType(LIST1_ATT2)
		assertEquals("expected defn", realType.typeInfo, actualType)
	}
	
	@Test
	def void testGetAttributeTypeMissingAtt(){
		val ldi = new ListDefInfo(listDefn1)
		val actualType = ldi.getAttributeType(LIST2_ATT2)
		assertEquals("expected defn", TypeSystemProvider::UNDEFINED_TYPE, actualType)
	}
	
	@Test
	def void testGetAttributeTypeValuePair(){
		val vp = createAssignPair(LIST1_ATT2, createRealLiteral(2.2)) 
		obsBlk.createListDefn("CL", #[
			createEnumPair(KEY_ATT, KEY_VAL1),
			vp
		])
		
		val actualType = vp.attributeType

		assertEquals("expected defn", realType.typeInfo, actualType)
	}
	
	@Test
	def void testGetAttributeTypeValuePairWrongKeyValue(){
		val vp = createAssignPair(LIST1_ATT2, createRealLiteral(2.2)) 
		obsBlk.createListDefn("CL", #[
			createEnumPair(KEY_ATT, NON_KEY_VAL),
			vp
		])
		
		val actualType = vp.attributeType

		assertEquals("expected defn", TypeSystemProvider::UNDEFINED_TYPE, actualType)
	}
	
	@Test
	def void testGetAttributeTypeValuePairMissingKeyAtt(){
		val vp = createAssignPair(LIST2_ATT2, createRealLiteral(2.2)) 
		obsBlk.createListDefn("CL", #[
			createAssignPair(LIST1_ATT2, createRealLiteral(2.5)),
			vp
		])

		val actualType = vp.attributeType
		assertEquals("expected defn", TypeSystemProvider::UNDEFINED_TYPE, actualType)
	}
	
	@Test
	def void testGetUnusedMandatoryAttributesNoneMissing(){
		val ld = obsBlk.createListDefn("CL", #[
			createEnumPair(KEY_ATT, KEY_VAL1),
			createAssignPair(LIST1_ATT2, createRealLiteral(0.1))
		])
		
		val actualListDefn = ld.firstAttributeList.unusedMandatoryAttributes

		assertTrue("expected defn", actualListDefn.isEmpty)
	}
	
	@Test
	def void testGetUnusedMandatoryAttributesNoneMissingWithOptional(){
		val ld = obsBlk.createListDefn("CL", #[
			createEnumPair(KEY_ATT, KEY_VAL2)
		])
		
		val actualListDefn = ld.firstAttributeList.unusedMandatoryAttributes

		assertEquals("expected defn", #{ }, actualListDefn)
	}
	
	@Test
	def void testGetUnusedMandatoryAttributesOneMissing(){
		val ld = obsBlk.createListDefn("CL", #[
			createEnumPair(KEY_ATT, KEY_VAL1)
		])
		
		val actualListDefn = ld.firstAttributeList.unusedMandatoryAttributes

		assertEquals("expected defn", #{ LIST1_ATT2 }, actualListDefn)
	}
	
	@Test
	def void testIsKeyAttributeDefinedTrue(){
		val ld = obsBlk.createListDefn("CL", #[
			createAssignPair(KEY_ATT, createIntLiteral(1))
		])
		
		assertTrue("has key", blkDefn.isKeyAttributeDefined(ld.firstAttributeList))
	}

	@Test
	def void testIsKeyAttributeDefinedFalse(){
		val ld = obsBlk.createListDefn("CL", #[
			createAssignPair(LIST1_ATT2, createIntLiteral(1))
		])
		
		assertFalse("no key", blkDefn.isKeyAttributeDefined(ld.firstAttributeList))
	}

}