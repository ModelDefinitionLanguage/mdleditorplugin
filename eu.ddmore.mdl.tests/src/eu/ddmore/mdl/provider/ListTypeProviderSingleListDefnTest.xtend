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
class ListTypeProviderSingleListDefnTest {
	@Inject extension MDLBuildFixture
	@Inject extension MDLLibraryBuildFixture
	@Inject extension MdlUtils
	@Inject extension MdlLibUtils

	@Inject extension ListDefinitionProvider
	
	val static String LIST1_ATT1 = "Att1"
	val static String WRONG_KEY_ATT = "wrongkeyAtt"
	val static String LIST1_ATT2 = "att1_2"
	val static String LIST1_ATT3 = "att1_3"

	var ListTypeDefinition listDefn
	var BlockStatement obsBlk
	var ListAttributeDefn expectedAttDefn
	var TypeDefinition keyType
	var TypeDefinition realType
	var ListDefInfo ldi
	var BlockDefinition blkDefn
	
	@Before
	def void setUp(){
		val libDefn = createLibraryRoot
		realType = libDefn.createTypeDefinition('Real', TypeClass.REAL)
		keyType = libDefn.createEnumTypeDefinition("keyType", TypeClass.ENUM, #["val1", "val2"])
		expectedAttDefn = createAttributeDefn(LIST1_ATT1, createTypeSpec(keyType, null))
		listDefn = libDefn.createListTypeDefinition(createTypeSpec(realType, null), null, #[
			expectedAttDefn,
			createAttributeDefn(LIST1_ATT2, createTypeSpec(realType, null)),
			createAttributeDefn(LIST1_ATT3, createTypeSpec(keyType, null))
		], #[true, true, false])
		blkDefn = libDefn.createBlockDefn("testBlk", listDefn)
		ldi = new ListDefInfo(listDefn)
		
		obsBlk = createBlock(blkDefn)
	}
	
	@After
	def void tearDown(){
		listDefn = null
		obsBlk = null
		expectedAttDefn = null
		keyType = null
		realType = null
		ldi = null
	}
	
	@Test
	def void testGetListDefinitionWithSingleList(){
		val ld = obsBlk.createListDefn("CL", #[
			createAssignPair(LIST1_ATT1, createRealLiteral(2.2))
		])
		
		val actualLstDefn = getListDefinition(ld.firstAttributeList)
		assertEquals("Expected defn", listDefn, actualLstDefn.listTypeDefinition)
	}
	
	@Test
	def void testGetListDefinitionWithSingleListWrongAtt(){
		val altLd = obsBlk.createListDefn("CL", #[
			createAssignPair(WRONG_KEY_ATT, createRealLiteral(2.2))
		])
		
		val actualLstDefn = getListDefinition(altLd.firstAttributeList)
		assertEquals("Expected defn", listDefn, actualLstDefn.listTypeDefinition)
	}
	
	@Test
	def void testGetAttributeDefinition(){
		val actualAtTDefn = ldi.getAttributeDefinition(LIST1_ATT1)
		assertEquals("expected defn", new MdlListAttributeDefn(expectedAttDefn), actualAtTDefn)
	}
	
	@Test
	def void testGetAttributeType(){
		val actualType = ldi.getAttributeType(LIST1_ATT2)
		assertEquals("expected defn", realType.typeInfo, actualType)
	}
	
	@Test
	def void testGetAttributeTypeValuePairWithSingleList(){
		val vp = createAssignPair(LIST1_ATT2, createRealLiteral(2.2)) 
		obsBlk.createListDefn("CL", #[
			createEnumPair(LIST1_ATT1, "val2"),
			vp
		])

		val actualType = vp.attributeType
		assertEquals("expected defn", realType.typeInfo, actualType)
	}
	
	@Test
	def void testGetAttributeTypeValuePairWithSingleListWrongAtt(){
		val vp = createAssignPair(WRONG_KEY_ATT, createRealLiteral(2.2)) 
		obsBlk.createListDefn("CL", #[
			createEnumPair(LIST1_ATT1, "val2"),
			vp
		])

		val actualType = vp.attributeType
		assertEquals("expected defn", TypeSystemProvider::UNDEFINED_TYPE, actualType)
	}
	
	@Test
	def void testGetUnusedMandatoryAttributesNoneMissing(){
		val ld = obsBlk.createListDefn("CL", #[
			createAssignPair(LIST1_ATT1, createIntLiteral(1)),
			createAssignPair(LIST1_ATT2, createRealLiteral(0.1))
		])
		
		val actualListDefn = ld.firstAttributeList.unusedMandatoryAttributes

		assertTrue("expected defn", actualListDefn.isEmpty)
	}
	
	@Test
	def void testGetUnusedMandatoryAttributesOneMissing(){
		val ld = obsBlk.createListDefn("CL", #[
			createAssignPair(LIST1_ATT1, createIntLiteral(1))
		])
		
		val actualListDefn = ld.firstAttributeList.unusedMandatoryAttributes

		assertEquals("expected defn", #{ LIST1_ATT2 }, actualListDefn)
	}
	
	@Test
	def void testIsKeyAttributeDefinedTrue(){
		val ld = obsBlk.createListDefn("CL", #[
			createAssignPair(LIST1_ATT1, createIntLiteral(1))
		])
		
		assertTrue("has key", blkDefn.isKeyAttributeDefined(ld.firstAttributeList))
	}

	@Test
	def void testIsKeyAttributeDefinedOtherAttTrue(){
		val ld = obsBlk.createListDefn("CL", #[
			createAssignPair(LIST1_ATT2, createIntLiteral(1))
		])
		
		assertTrue("has key", blkDefn.isKeyAttributeDefined(ld.firstAttributeList))
	}

}