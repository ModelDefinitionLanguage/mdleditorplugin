package eu.ddmore.mdl.type

import eu.ddmore.mdl.tests.MdlInjectorProvider
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.junit.runner.RunWith
import org.junit.Test
import org.junit.Before
import static org.junit.Assert.*

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlInjectorProvider))
class CategoryTypeInfoTest {
	val static TEST_NAME = "foo"
	val static TEST_CATAGS = newHashSet("cat1", "cat2")
	val static TEST_ALT_CATAGS = newHashSet("cat11", "cat12")
	val static String EXPECTED_TYPE_NAME = "Category:" + TEST_NAME
	val static TypeInfoClass EXPECTED_TYPE_CLASS = TypeInfoClass.Category
	
	var CategoryTypeInfo testInstance
	var CategoryTypeInfo equivTestInstance
	var CategoryTypeInfo equivDiffCatsTestInstance
	var CategoryTypeInfo nonEquivTestInstance
	var CategoryListTypeInfo equivNumListInstance
	var CategoryListTypeInfo nonEquivNumListInstance
	
	@Before
	def void setUp(){
		testInstance = new CategoryTypeInfo(TEST_NAME, TEST_CATAGS)
		equivTestInstance = new CategoryTypeInfo(TEST_NAME, TEST_CATAGS)
		this.nonEquivTestInstance = new CategoryTypeInfo("bar", TEST_CATAGS)
		equivDiffCatsTestInstance = new CategoryTypeInfo(TEST_NAME, TEST_ALT_CATAGS)
		equivNumListInstance = new CategoryListTypeInfo("TestEnumList", this.testInstance)
		nonEquivNumListInstance = new CategoryListTypeInfo("AltTestEnumList", this.nonEquivTestInstance)
	}
	
	@Test
	def void testEqualsSameInstance(){
		assertEquals("same", testInstance, testInstance)
	}

	@Test
	def void testEqualsSameDiffInstance(){
		assertEquals("same", testInstance, this.equivTestInstance)
		assertNotEquals("not same", testInstance, this.nonEquivTestInstance)
		assertNotEquals("not same", testInstance, this.equivDiffCatsTestInstance)
	}
	
	@Test
	def void testNotEqualsNull(){
		assertFalse("not same as null", testInstance === null)
	}
	
	@Test
	def void testHashCodeSameInstance(){
		assertEquals("same", testInstance.hashCode, testInstance.hashCode)
	}

	@Test
	def void testHashCodeSameDiffInstance(){
		assertEquals("same", testInstance.hashCode, this.equivTestInstance.hashCode)
		assertNotEquals("not same", testInstance.hashCode, this.nonEquivTestInstance.hashCode)
		assertEquals("same", testInstance.hashCode, this.equivDiffCatsTestInstance.hashCode)
	}
	
	@Test
	def void testExpectedTypeName(){
		assertEquals(EXPECTED_TYPE_NAME, this.testInstance.typeName)
	}
	
	@Test
	def void testExpectedTypeClass(){
		assertEquals(EXPECTED_TYPE_CLASS, this.testInstance.typeClass)
	}
	
	@Test
	def void testExpectedCategories(){
		assertEquals(TEST_CATAGS, this.testInstance.categories)
	}
	@Test
	def void testIsCompatible(){
		assertTrue(this.testInstance.isCompatible(this.testInstance))
		assertTrue(this.testInstance.isCompatible(this.equivTestInstance))
	}
	
	@Test
	def void testIsCompatibleWithEnumListType(){
		assertTrue(this.testInstance.isCompatible(this.testInstance))
		assertTrue(this.testInstance.isCompatible(this.equivNumListInstance))
		assertFalse(this.testInstance.isCompatible(this.	nonEquivNumListInstance))
	}
	
	@Test
	def void testIsCompatibleWithNull(){
		assertFalse(this.testInstance.isCompatible(null))
	}
	
	@Test
	def void testIsCompatibleWithPrimitveTypes(){
		assertFalse(this.testInstance.isCompatible(TypeSystemProvider::REAL_TYPE))
		assertFalse(this.testInstance.isCompatible(TypeSystemProvider::INT_TYPE))
	}
	
	@Test
	def void testIsCompatibleWithRefType(){
		assertTrue(this.testInstance.isCompatible(this.testInstance.makeReference))
		assertTrue(this.testInstance.isCompatible(this.equivTestInstance.makeReference))
	}
	
	
}