package eu.ddmore.converter.treerewrite

import eu.ddmore.mdl.mdl.MdlPackage
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.mdl.util.MdlSwitch
import eu.ddmore.mdl.type.TypeSystemProvider
import eu.ddmore.mdl.type.VectorTypeInfo

class VectorAttributeRewrite extends MdlSwitch<Boolean> {
	
	extension TypeSystemProvider tsp = new TypeSystemProvider
//	extension BuiltinFunctionProvider bfp = new BuiltinFunctionProvider
//	extension ListDefinitionProvider ldp = new ListDefinitionProvider
//	extension SublistDefinitionProvider sdp = new SublistDefinitionProvider
//	extension PropertyDefinitionProvider pdp = new PropertyDefinitionProvider
	
	// this assumes that the AST is valid 
	override caseValuePair(ValuePair it){
		val argType = typeFor
		if(argType instanceof VectorTypeInfo){
			if(!(expression.typeFor.underlyingType instanceof VectorTypeInfo)){
				val fact = MdlPackage.eINSTANCE.mdlFactory
				val vect = fact.createVectorLiteral
				val el = fact.createVectorElement
				el.element = expression
				vect.expressions.add(el)
				expression = vect
				Boolean::TRUE
			}
		}
		
	}
	
	
//	private def TypeInfo getArgumentType(ValuePair it){
//		val parent = eContainer
//		switch(parent){
//			NamedFuncArguments:
//				namedArgumentType
//			AttributeList:
//				attributeType
//			SubListExpression:
//				sublistAttributeType
//			PropertyStatement:
//				typeForProperty
//			default:
//				TypeSystemProvider::UNDEFINED_TYPE
//		}
//	}
	
}