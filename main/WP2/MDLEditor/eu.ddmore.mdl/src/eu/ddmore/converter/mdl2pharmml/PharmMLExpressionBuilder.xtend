package eu.ddmore.converter.mdl2pharmml

import eu.ddmore.mdl.mdl.AdditiveExpression
import eu.ddmore.mdl.mdl.AndExpression
import eu.ddmore.mdl.mdl.BooleanLiteral
import eu.ddmore.mdl.mdl.BuiltinFunctionCall
import eu.ddmore.mdl.mdl.ConstantLiteral
import eu.ddmore.mdl.mdl.EqualityExpression
import eu.ddmore.mdl.mdl.Expression
import eu.ddmore.mdl.mdl.IfExprPart
import eu.ddmore.mdl.mdl.IntegerLiteral
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.MultiplicativeExpression
import eu.ddmore.mdl.mdl.NamedFuncArguments
import eu.ddmore.mdl.mdl.OrExpression
import eu.ddmore.mdl.mdl.ParExpression
import eu.ddmore.mdl.mdl.RealLiteral
import eu.ddmore.mdl.mdl.RelationalExpression
import eu.ddmore.mdl.mdl.StringLiteral
import eu.ddmore.mdl.mdl.SymbolDefinition
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.mdl.UnaryExpression
import eu.ddmore.mdl.mdl.UnnamedFuncArguments
import eu.ddmore.mdl.mdl.VectorElement
import eu.ddmore.mdl.mdl.VectorLiteral
import eu.ddmore.mdl.mdl.WhenExpression
import eu.ddmore.mdl.validation.BlockDefinitionProvider
import eu.ddmore.mdl.validation.ListDefinitionProvider

import static eu.ddmore.converter.mdl2pharmml.Constants.*

import eu.ddmore.mdl.mdl.EnumExpression
import eu.ddmore.mdl.mdl.PowerExpression
import eu.ddmore.mdl.utils.DomainObjectModelUtils

class PharmMLExpressionBuilder {
	
	extension ListDefinitionProvider ldp = new ListDefinitionProvider 
	extension DomainObjectModelUtils domu = new DomainObjectModelUtils
	
	static val GLOBAL_VAR = 'global'
	
	static val blockPharmMLModelMapping = #{
		BlockDefinitionProvider::MDL_PRED_BLK_NAME -> 'sm',
		BlockDefinitionProvider::MDL_DEQ_BLK -> 'sm',
		BlockDefinitionProvider::MDL_CMT_BLK -> 'sm',
		BlockDefinitionProvider::MDL_INDIV_PARAMS -> 'pm',
		BlockDefinitionProvider::MDL_VAR_PARAMS -> 'pm',
		BlockDefinitionProvider::MDL_STRUCT_PARAMS -> 'pm',
		BlockDefinitionProvider::MDL_RND_VARS -> 'pm',
		BlockDefinitionProvider::MDL_GRP_PARAMS -> 'pm',
		BlockDefinitionProvider::COVARIATE_BLK_NAME -> 'cm',
		BlockDefinitionProvider::IDV_BLK_NAME -> GLOBAL_VAR,
		BlockDefinitionProvider::PARAM_STRUCT_BLK -> 'pm',
		BlockDefinitionProvider::PARAM_VARIABILITY_BLK -> 'pm',
		BlockDefinitionProvider::OBS_BLK_NAME-> 'om'
	}
	
	
	def getSymbolReference(SymbolDefinition it){
		val blkId = blockId
		'''
			<ct:SymbRef «IF blkId != GLOBAL_VAR»blkIdRef="«blkId ?: 'ERROR!'»"«ENDIF» symbIdRef="«name»"/>
		'''
	}
	
	def getBlockId(SymbolDefinition it){
		val blk = owningBlock
		val blkName = blk.identifier
		val blkId = blockPharmMLModelMapping.get(blkName)
		switch(blkName){
			case BlockDefinitionProvider::VAR_LVL_BLK_NAME:
				if((it as ListDefinition).list.getAttributeEnumValue('type') == 'parameter') 'vm_mdl'
				else 'vm_err'
			case BlockDefinitionProvider::OBS_BLK_NAME:{
				// number obs based on order in block.
				var cntr = 1
				for(obsStmt : blk.statements){
					switch(obsStmt){
						SymbolDefinition:
							if(obsStmt.name == name) return blkId + cntr
					}
					cntr += 1
				}
			}
			default: blkId
		}
//		if(blkId == null && blkName == BlockDefinitionProvider::VAR_LVL_BLK_NAME){
//			// handle variability model
//			if((it as ListDefinition).list.getAttributeEnumValue('type') == 'parameter') 'vm_mdl'
//			else 'vm_err'
//		}
//		else blkId
	}
	
	def getSymbolReference(SymbolReference it){
		ref.getSymbolReference
	}
	
	def getLocalSymbolReference(SymbolReference it){
		ref.getLocalSymbolReference
	}

	def getLocalSymbolReference(SymbolDefinition it)'''
		<ct:SymbRef symbIdRef="«name»"/>
	'''
	
	def getExpressionAsEquation(Expression it)'''
		<Equation xmlns="«xmlns_math»">
			«pharmMLExpr»
		</Equation>
	'''
	
	def getExpressionAsAssignment(Expression it)'''
		<ct:Assign>
			«expressionAsEquation»
		</ct:Assign>
	'''
	
    def dispatch CharSequence getPharmMLExpr(Expression expr){
    	switch(expr){
    		OrExpression:
    			getOrExpression(expr)
    		AndExpression:
    			getAndExpression(expr)
    		EqualityExpression:
    			getEqualityExpression(expr)
    		RelationalExpression:{
    			getRelationalExpression(expr)
    		}
    		AdditiveExpression:{
    			getAdditiveExpression(expr)
    		}
    		MultiplicativeExpression:{
    			getMultiplicativeExpression(expr)
    		}
    		PowerExpression:{
    			getPowerExpression(expr)
    		}
    		UnaryExpression:{
    			getUnaryExpression(expr)
    		}
    		ParExpression:{
    			getParExpression(expr)
    		}
    		WhenExpression:{
    			expr.getWhenExpression
    		}
    		VectorLiteral:{
    			getVectorLiteralExpression(expr)
    		}
    		VectorElement:{
    			expr.element.pharmMLExpr
    		}
    		BooleanLiteral:{
    			getBooleanLiteral(expr)
    		}
    		RealLiteral:{
    			getRealLiteral(expr)
    		}
    		ConstantLiteral:{
    			getConstantLiteral(expr)
    		}
    		IntegerLiteral:
    			getIntegerLiteral(expr)
    		StringLiteral:
    			getStringLiteral(expr)
    		SymbolReference:{
    			getSymbolReference(expr)
    		}
    		EnumExpression:{
    			getEnumExpression(expr)
    		}	
    	}
    }
	
	def getPharmMLBinop(String operator){
		switch(operator){
			case '+': 'plus'
			case '-': 'minus'
			case '*': 'times'
			case '/': 'divide'
			case '^': 'power'
			case '||': 'or'
			case '&&': 'and'
			case '<': 'lt'
			case '<=': 'leq'
			case '>': 'gt'
			case '>=': 'geq'
			case '==': 'eq'
			case '!=': 'neq'
			case '%': 'rem'
			default: operator
		}
	}
	
	def getPharmMLUniop(String operator){
		switch(operator){
			case '+': 'plus'
			case '-': 'minus'
			case '!': 'not'
			default: operator
		}
	}
	
	def isLogicOp(String operator){
		switch(operator){
			case '||',
			case '&&',
			case '<',
			case '<=',
			case '>',
			case '>=',
			case '==',
			case '!=',
			case '!': true
			default: false
		}
	}
	
	def getPharmMlFunction(String fName){
		switch(fName){
			case 'ln' : 'log'
			case 'invLogit': 'logistic'
			case 'lnFactorial': 'factln'
			default: fName
		}
	}
	
	def getVectorLiteralExpression(VectorLiteral it)'''
		<ct:Vector>
			<ct:VectorElements>
				«FOR e : expressions»
					«e.pharmMLExpr»
				«ENDFOR»
			</ct:VectorElements>
		</ct:Vector>
	'''
	
	def getParExpression(ParExpression it)'''
		«expr.pharmMLExpr»
	'''
	
	def getUnaryExpression(UnaryExpression it)'''
		«IF feature.isLogicOp»
			<math:LogicUniop op="«feature.pharmMLUniop»">
		«ELSE»
			<math:Uniop op="«feature.pharmMLUniop»">
		«ENDIF»
			«operand.pharmMLExpr»
		«IF feature.isLogicOp»
			</math:LogicUniop>
		«ELSE»
			</math:Uniop>
		«ENDIF»
	'''
	
	def getMultiplicativeExpression(MultiplicativeExpression it){
		getBinaryOperator(feature, leftOperand, rightOperand)
	}
	
	def getPowerExpression(PowerExpression it){
		getBinaryOperator(feature, leftOperand, rightOperand)
	}
	
	def getAdditiveExpression(AdditiveExpression it){
		getBinaryOperator(feature, leftOperand, rightOperand)
	}
	
	def getRelationalExpression(RelationalExpression it){
		getBinaryOperator(feature, leftOperand, rightOperand)
	}
	
	def getEqualityExpression(EqualityExpression it){
		getBinaryOperator(feature, leftOperand, rightOperand)
	}
	
	def getAndExpression(AndExpression it){
		getBinaryOperator(feature, leftOperand, rightOperand)
	}
	
	def getOrExpression(OrExpression it){
		getBinaryOperator(feature, leftOperand, rightOperand)
	}
		
	def getBinaryOperator(String feature, Expression leftOperand, Expression rightOperand)'''
		«IF feature.isLogicOp»
			<math:LogicBinop op="«feature.pharmMLBinop»">
		«ELSE»
			<math:Binop op="«feature.pharmMLBinop»">
		«ENDIF»
			«leftOperand.pharmMLExpr»
			«rightOperand.pharmMLExpr»
		«IF feature.isLogicOp»
			</math:LogicBinop>
		«ELSE»
			</math:Binop>
		«ENDIF»
	'''
	
	def getWhenExpression(WhenExpression it)'''
		<math:Piecewise>
			«FOR w : when»
				«w.whenClause»
			«ENDFOR»
			«IF other != null»
				<math:Piece>
					«other.other.pharmMLExpr»
					<math:Condition>
						<math:Otherwise/>
					</math:Condition>
				</math:Piece>
			«ENDIF»
		</math:Piecewise>
	'''
	
	def getWhenClause(IfExprPart it)'''
		<math:Piece>
			«value.pharmMLExpr»
			<math:Condition>
				«cond.pharmMLExpr»
			</math:Condition>
		</math:Piece>
	'''
	
	def getStringLiteral(StringLiteral it) '''
		<ct:String>«value»</ct:String>
	'''
	
	def getEnumExpression(EnumExpression it)'''
		<ct:String>«enumValue»</ct:String>
	'''
	
	def getIntegerLiteral(IntegerLiteral it) '''
		<ct:Int>«value»</ct:Int>
	'''
	
	def getConstantLiteral(ConstantLiteral it) {
		val constType = switch(value){
			case("inf"): "infinity"
			case("exponentiale"): "exponentiale"
			case("pi") : "pi"
			default:
				"error:NotDefined"	
		}
		'''
		<math:Binop op="times">
			<ct:Real>1.0</ct:Real>
			<math:Constant op="«constType»"/>
		</math:Binop>
		'''
	}
	
	def getRealLiteral(RealLiteral it)'''
		<ct:Real>«value»</ct:Real>
	'''
	
	def getBooleanLiteral(BooleanLiteral it)'''
		«IF isTrue» <ct:True/>
		«ELSE» <ct:False/>
		«ENDIF»
	'''
    
    def dispatch CharSequence getPharmMLExpr(BuiltinFunctionCall it){
    	var retVal = ''''''
    	val a = argList
    	switch(a){
    		NamedFuncArguments:
    			retVal += '''
						<math:FunctionCall>
							«func.localSymbolReference»
							«a.namedArguments»
						</math:FunctionCall>
    			''' 
    			
    		UnnamedFuncArguments:{
    			val opType = if(a.args.size > 1) "Binop" else "Uniop"
    			retVal += '''
    					<math:«opType» op="«func.pharmMlFunction»">
    						«a.unnamedArguments»
    					</math:«opType»>	
    					'''
    		}
    	}
    	retVal
    }

	
	def getNamedArguments(NamedFuncArguments it)'''
		«FOR arg : arguments»
			<math:FunctionArgument symbId="«arg.argumentName»">
				«arg.expression.pharmMLExpr»
			</math:FunctionArgument>
		«ENDFOR»
	'''
	
	def getUnnamedArguments(UnnamedFuncArguments it)'''
		«FOR arg : args»
			«arg.argument.pharmMLExpr»
		«ENDFOR»
	'''
	
	def getLocalSymbolReference(String v) '''
		<ct:SymbRef symbId="«v»"/>
	'''
}
