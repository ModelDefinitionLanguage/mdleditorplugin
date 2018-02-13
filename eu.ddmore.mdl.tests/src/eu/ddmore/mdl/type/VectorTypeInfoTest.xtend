package eu.ddmore.mdl.type

import eu.ddmore.mdl.tests.MdlInjectorProvider
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.junit.runner.RunWith
import org.junit.Test
import org.junit.Before
import static org.junit.Assert.*
import org.junit.After

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlInjectorProvider))
class VectorTypeInfoTest {
	val static String EXPECTED_TYPE_NAME = "vector:Real"
	val static TypeInfoClass EXPECTED_TYPE_CLASS = TypeInfoClass.Vector
	
	var VectorTypeInfo testInstance
	var VectorTypeInfo equivTestInstance
	var VectorTypeInfo diffTestInstance 
	var VectorTypeInfo diffCatTestInstance
	var ListTypeInfo listType 
	var ListTypeInfo listRealType 
	var ListTypeInfo listNoSecondaryType 
	
	
	@Before
	def void setUp(){
		testInstance = new VectorTypeInfo(TypeSystemProvider::REAL_TYPE)
		equivTestInstance = new VectorTypeInfo(TypeSystemProvider::REAL_TYPE)
		diffTestInstance = new VectorTypeInfo(TypeSystemProvider::INT_TYPE)
		diffCatTestInstance = new VectorTypeInfo(new GeneralCategoryTypeInfo)
		listType = new ListTypeInfo("Foo", new VectorTypeInfo(TypeSystemProvider::REAL_TYPE))
		listRealType = new ListTypeInfo("Foo", TypeSystemProvider::REAL_TYPE)
		listNoSecondaryType = new ListTypeInfo("Foo")
	}
	
	@After
	def void tearDown(){
		this.testInstance = null
		this.equivTestInstance = null
		this.diffTestInstance = null
		this.diffCatTestInstance = null
		this.listType = null
		this.listRealType = null
		this.listNoSecondaryType = null
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
	def void testEqualsNotSame(){
		assertNotEquals("not same", testInstance, this.diffTestInstance)
		assertNotEquals("not same", testInstance, this.diffCatTestInstance)
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
	def void testHashCodeNotSame(){
		assertNotEquals("not same", testInstance.hashCode, this.diffTestInstance.hashCode)
		assertNotEquals("not same", testInstance.hashCode, this.diffCatTestInstance.hashCode)
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
		// uses type promotion
		assertTrue(this.testInstance.isCompatible(this.diffTestInstance))
		assertFalse(this.testInstance.isCompatible(this.diffCatTestInstance))
	}
	
	@Test
	def void testIsCompatibleWithListSecondaryType(){
		assertTrue(this.testInstance.isCompatible(this.listType))
		assertFalse(this.testInstance.isCompatible(this.listRealType))
		assertFalse(this.testInstance.isCompatible(this.listNoSecondaryType))
	}
	
	@Test
	def void testListSecondaryIsCompatibleWithVectorType(){
		assertTrue(this.listType.isCompatible(this.testInstance))
		assertFalse(this.listRealType.isCompatible(this.testInstance))
		assertFalse(this.listNoSecondaryType.isCompatible(this.testInstance))
	}
	
	@Test
	def void testIsCompatibleWithNull(){
		assertFalse(this.testInstance.isCompatible(null))
	}
	
	@Test
	def void testIsCompatibleWithRefs(){
		assertTrue(this.testInstance.isCompatible(this.testInstance.makeReference))
	}
	
	@Test
	def void testRefIsCompatibleWithType(){
		assertTrue(this.testInstance.makeReference.isCompatible(this.testInstance))
	}

	@Test
	def void tesReftIsCompatibleWithRefType(){
		assertTrue(this.testInstance.makeReference.isCompatible(this.testInstance.makeReference))
	}
	
	
}