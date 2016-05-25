package eu.ddmore.mdl.type

import com.google.inject.Inject
import eu.ddmore.mdl.MdlAndLibInjectorProvider
import eu.ddmore.mdl.utils.MDLBuildFixture
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.assertEquals

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class VectorLiteralTypeTest {
	@Inject extension MDLBuildFixture
	@Inject extension TypeSystemProvider
	
	@Test
	def void testCheckAllReal(){
		val vectLit = createVectorLiteral(createRealLiteral(2.0), createRealLiteral(3.0), createRealLiteral(4.0))
		
		val actualType = vectLit.typeFor
		val expectedType = TypeSystemProvider::REAL_VECTOR_TYPE

		assertEquals("Expected type", expectedType, actualType)
	}

	@Test
	def void testCheckAllInt(){
		val vectLit = createVectorLiteral(createIntLiteral(2), createIntLiteral(3), createIntLiteral(4))
		
		val actualType = vectLit.typeFor
		val expectedType = TypeSystemProvider::INT_VECTOR_TYPE

		assertEquals("Expected type", expectedType, actualType)
	}

	@Test
	def void testCheckMixedIntReal1(){
		val vectLit = createVectorLiteral(createIntLiteral(2), createRealLiteral(1.0), createRealLiteral(4.0))
		
		val actualType = vectLit.typeFor
		val expectedType = TypeSystemProvider::REAL_VECTOR_TYPE

		assertEquals("Expected type", expectedType, actualType)
	}

	@Test
	def void testCheckMixedIntReal2(){
		val vectLit = createVectorLiteral(createRealLiteral(2.0), createIntLiteral(1), createRealLiteral(4.0))
		
		val actualType = vectLit.typeFor
		val expectedType = TypeSystemProvider::REAL_VECTOR_TYPE

		assertEquals("Expected type", expectedType, actualType)
	}

	@Test
	def void testCheckMixedIntReal3(){
		val vectLit = createVectorLiteral(createRealLiteral(2.0), createRealLiteral(4.0), createIntLiteral(1))
		
		val actualType = vectLit.typeFor
		val expectedType = TypeSystemProvider::REAL_VECTOR_TYPE

		assertEquals("Expected type", expectedType, actualType)
	}

	@Test
	def void testCheckMixedIntVectorWithReal(){
		val vectLit = createVectorLiteral(createIntLiteral(2), createRealLiteral(4.0), createIntLiteral(1))
		
		val actualType = vectLit.typeFor
		val expectedType = TypeSystemProvider::REAL_VECTOR_TYPE

		assertEquals("Expected type", expectedType, actualType)
	}

	@Test
	def void testCheckMixedIntReal4(){
		val vectLit = createVectorLiteral(createRealLiteral(4.0), createIntLiteral(1))
		
		val actualType = vectLit.typeFor
		val expectedType = TypeSystemProvider::REAL_VECTOR_TYPE

		assertEquals("Expected type", expectedType, actualType)
	}

	@Test
	def void testCheckMixedStringReal(){
		val vectLit = createVectorLiteral(createRealLiteral(4.0), createIntLiteral(1), createStringLiteral("foo"))
		
		val actualType = vectLit.typeFor
		val expectedType = TypeSystemProvider::REAL_VECTOR_TYPE

		assertEquals("Expected type", expectedType, actualType)
	}

}