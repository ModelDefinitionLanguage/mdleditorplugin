package eu.ddmore.mdl.utils

import java.util.List
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.mdl.VectorLiteral
import eu.ddmore.mdl.mdl.EquationTypeDefinition
import eu.ddmore.mdllib.mdllib.Expression
import eu.ddmore.mdl.mdl.EnumExpression
import eu.ddmore.mdl.mdl.StringLiteral
import eu.ddmore.mdl.mdl.VectorElement
import eu.ddmore.mdl.mdl.MatrixElement
import java.util.ArrayList

class ExpressionUtils {
	
	def List<Expression> getVector(Expression it){
		val exp = it
		if(exp instanceof SymbolReference){
			val defn = exp.ref
			if(defn instanceof EquationTypeDefinition){
				defn.expression.getVector
			}
			else null
		}
		else if(exp instanceof VectorLiteral){
			// stop off the element
			val retVal = new ArrayList<Expression>(exp.expressions.size)
			exp.expressions.forEach[el|
				if(el instanceof VectorElement) retVal.add(el.element)
			]
			retVal
		}
		else null
	}

	def String getEnumValue(Expression expr){
		if(expr instanceof SymbolReference){
			val defn = expr.ref
			if(defn instanceof EquationTypeDefinition){
				defn.expression.enumValue
			}
			else null
		}
		else if(expr instanceof EnumExpression){
			return expr.enumValue
		}
		else null
	}
	
	def String getStringValue(Expression expr){
		if(expr instanceof SymbolReference){
			val defn = expr.ref
			if(defn instanceof EquationTypeDefinition){
				defn.expression.stringValue
			}
			else null
		}
		else if(expr instanceof StringLiteral){
			return expr.value
		}
		else null
	} 
	
	def SymbolReference getSymbolRef(Expression expr){
		switch(expr){
			SymbolReference:
				expr
			VectorElement:
				expr.element.symbolRef
			MatrixElement:
				expr.cell.symbolRef
			default:
				null
		}
	}
	
	
}