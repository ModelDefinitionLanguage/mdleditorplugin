package eu.ddmore.mdl.utils

import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.MdlFactory
import eu.ddmore.mdllib.mdllib.MdlLibFactory
import eu.ddmore.mdl.mdl.EnumPair
import eu.ddmore.mdl.mdl.AssignPair
import eu.ddmore.mdllib.mdllib.Expression
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.mdl.BlockStatementBody
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdllib.mdllib.SymbolDefinition
import eu.ddmore.mdl.mdl.VectorLiteral
import java.util.List
import eu.ddmore.mdl.mdl.SubListExpression
import java.util.Map

class MDLBuildFixture {

	def BlockStatement createBlock(String blkName){
		val retVal = MdlFactory.eINSTANCE.createBlockStatement
		val blkDefn = MdlLibFactory.eINSTANCE.createBlockDefinition
		blkDefn.name = blkName
		retVal.blkId = blkDefn
		retVal.body = MdlFactory.eINSTANCE.createBlockStatementBody
		retVal
	}
	
	def EnumPair createEnumPair(String name, String value){
		val retVal = MdlFactory.eINSTANCE.createEnumPair
		retVal.argumentName = name
		val enumExpr =  MdlFactory.eINSTANCE.createEnumExpression
		enumExpr.enumValue = value
		retVal.expression = enumExpr
		retVal
	}
	
	def AssignPair createAssignPair(String name, Expression exprVal){
		val retVal = MdlFactory.eINSTANCE.createAssignPair
		retVal.argumentName = name
		retVal.expression = exprVal
		retVal
	}
	
	def SymbolReference createSymbolRef(BlockStatement it, String name){
		createSymbolRef(createEqnDefn(name))
	}
	
	def SymbolReference createSymbolRef(BlockStatement it, SymbolDefinition sd){
		val retVal = MdlFactory.eINSTANCE.createSymbolReference
		retVal.ref = sd
		retVal
	}
	
	def EquationDefinition createEqnDefn(BlockStatement it, String name){
		val bdy = body as BlockStatementBody
		val sd = MdlFactory.eINSTANCE.createEquationDefinition
		sd.name = name
		bdy.statements.add(sd)
		sd
	}

	def EquationDefinition createEqnDefn(BlockStatement it, String name, Expression expr){
		val bdy = body as BlockStatementBody
		val sd = MdlFactory.eINSTANCE.createEquationDefinition
		sd.name = name
		sd.expression = expr
		bdy.statements.add(sd)
		sd
	}

	def VectorLiteral createVectorLiteral(List<Expression> elements){
		val retVal = MdlFactory.eINSTANCE.createVectorLiteral
		for(el : elements){
			val vEl = MdlFactory.eINSTANCE.createVectorElement
			vEl.element = el
			retVal.expressions.add(vEl)
		}
		retVal
	}

	def SubListExpression createSublist(Map<String, Expression> content){
		val retVal = MdlFactory.eINSTANCE.createSubListExpression
		for(key : content.keySet){
			val att = MdlFactory.eINSTANCE.createValuePair
			att.argumentName = key
			att.expression = content.get(key)
			retVal.attributes.add(att)
		}
		retVal
	}

}