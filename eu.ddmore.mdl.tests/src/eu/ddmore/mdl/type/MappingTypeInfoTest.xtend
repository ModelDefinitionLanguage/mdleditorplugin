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
class MappingTypeInfoTest {
	val static TEST_NAME = "Atest"
	val static String EXPECTED_TYPE_NAME = "mapping:" + TEST_NAME
	val static TypeInfoClass EXPECTED_TYPE_CLASS = TypeInfoClass.Mapping
	
	var MappingTypeInfo testInstance
	var MappingTypeInfo equivTestInstance
	
	
	@Before
	def void setUp(){
		testInstance = new MappingTypeInfo(TEST_NAME, TypeSystemProvider::STRING_TYPE, TypeSystemProvider::INT_TYPE,
								 TypeSystemProvider::REAL_TYPE,	TypeSystemProvider::MAPPING_TYPE)
		equivTestInstance = new MappingTypeInfo(TEST_NAME, TypeSystemProvider::STRING_TYPE, TypeSystemProvider::INT_TYPE,
											TypeSystemProvider::REAL_TYPE,	TypeSystemProvider::MAPPING_TYPE)
	}
	
	@Test
	def void testEqualsSameInstance(){
		assertEquals("same", testInstance, testInstance)
	}

	@Test
	def void testEqualsSameDiffInstance(){
		assertEquals("same", testInstance, this.equivTestInstance)
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
	def void testIsCompatible(){
		assertTrue(this.testInstance.isCompatible(this.testInstance))
		assertTrue(this.testInstance.isCompatible(this.equivTestInstance))
	}
	
	@Test
	def void testIsCompatibleWithNull(){
		assertFalse(this.testInstance.isCompatible(null))
	}
	
	@Test
	def void testIsCompatibleWithUnderlyingTypes(){
		assertTrue(this.testInstance.isCompatible(equivTestInstance))
	}
	
	@Test
	def void testPrimitiveIsCompatibleWithList(){
		assertFalse(TypeSystemProvider::REAL_TYPE.isCompatible(this.testInstance))
		assertFalse(TypeSystemProvider::INT_TYPE.isCompatible(this.testInstance))
	}
	
	@Test
	def void testIsCompatibleWithRefType(){
		assertTrue(this.testInstance.isCompatible(this.testInstance.makeReference))
		assertTrue(this.testInstance.isCompatible(this.equivTestInstance.makeReference))
	}
	
	
}