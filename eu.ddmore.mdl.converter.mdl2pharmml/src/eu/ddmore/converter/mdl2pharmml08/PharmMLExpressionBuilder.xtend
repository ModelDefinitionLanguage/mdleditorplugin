package eu.ddmore.converter.mdl2pharmml08

import eu.ddmore.mdl.mdl.AdditiveExpression
import eu.ddmore.mdl.mdl.AndExpression
import eu.ddmore.mdl.mdl.BooleanLiteral
import eu.ddmore.mdl.mdl.CategoryValueReference
import eu.ddmore.mdl.mdl.ConstantLiteral
import eu.ddmore.mdl.mdl.EnumExpression
import eu.ddmore.mdl.mdl.EqualityExpression
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.IfExprPart
import eu.ddmore.mdl.mdl.IfExpression
import eu.ddmore.mdl.mdl.IntegerLiteral
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.MatrixElement
import eu.ddmore.mdl.mdl.MatrixLiteral
import eu.ddmore.mdl.mdl.MatrixRow
import eu.ddmore.mdl.mdl.MultiplicativeExpression
import eu.ddmore.mdl.mdl.NamedFuncArguments
import eu.ddmore.mdl.mdl.OrExpression
import eu.ddmore.mdl.mdl.PWClause
import eu.ddmore.mdl.mdl.ParExpression
import eu.ddmore.mdl.mdl.PiecewiseExpression
import eu.ddmore.mdl.mdl.PowerExpression
import eu.ddmore.mdl.mdl.RealLiteral
import eu.ddmore.mdl.mdl.RelationalExpression
import eu.ddmore.mdl.mdl.StringLiteral
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.mdl.UnaryExpression
import eu.ddmore.mdl.mdl.UnnamedArgument
import eu.ddmore.mdl.mdl.UnnamedFuncArguments
import eu.ddmore.mdl.mdl.VectorElement
import eu.ddmore.mdl.mdl.VectorLiteral
import eu.ddmore.mdl.provider.BlockDefinitionTable
import eu.ddmore.mdl.provider.BuiltinFunctionProvider
import eu.ddmore.mdl.provider.ListDefinitionProvider
import eu.ddmore.mdl.type.TypeSystemProvider
import eu.ddmore.mdl.utils.BlockUtils
import eu.ddmore.mdl.utils.DomainObjectModelUtils
import eu.ddmore.mdl.utils.ExpressionUtils
import eu.ddmore.mdl.utils.MdlUtils
import eu.ddmore.mdllib.mdllib.Expression
import eu.ddmore.mdllib.mdllib.SymbolDefinition
import java.util.Collections
import java.util.Deque
import java.util.LinkedList
import java.util.List
import java.util.ArrayList
import eu.ddmore.mdl.utils.ConstantEvaluation

class PharmMLExpressionBuilder {
	
	extension ListDefinitionProvider ldp = new ListDefinitionProvider 
	extension DomainObjectModelUtils domu = new DomainObjectModelUtils
	extension BuiltinFunctionProvider bfp = new BuiltinFunctionProvider
	extension MdlUtils mu = new MdlUtils
	extension BlockUtils bu = new BlockUtils
	extension TypeSystemProvider tsp = new TypeSystemProvider
	extension ExpressionUtils eu = new ExpressionUtils
	extension ConstantEvaluation ce = new ConstantEvaluation 
	
	static val GLOBAL_VAR = 'global'
	
	static val blockPharmMLModelMapping = #{
		BlockDefinitionTable::MDL_PRED_BLK_NAME -> 'sm',
		BlockDefinitionTable::MDL_DEQ_BLK -> 'sm',
		BlockDefinitionTable::MDL_CMT_BLK -> 'sm',
		BlockDefinitionTable::MDL_INDIV_PARAMS -> 'pm',
		BlockDefinitionTable::MDL_VAR_PARAMS -> 'pm',
		BlockDefinitionTable::MDL_STRUCT_PARAMS -> 'pm',
		BlockDefinitionTable::MDL_RND_VARS -> 'pm',
		BlockDefinitionTable::MDL_GRP_PARAMS -> 'pm',
		BlockDefinitionTable::PRIOR_VAR_DEFN -> 'pm',
		BlockDefinitionTable::PRIOR_PARAMS -> 'pm',
		BlockDefinitionTable::COVARIATE_BLK_NAME -> 'cm',
		BlockDefinitionTable::IDV_BLK_NAME -> GLOBAL_VAR,
		BlockDefinitionTable::PARAM_STRUCT_BLK -> 'pm',
		BlockDefinitionTable::PARAM_VARIABILITY_BLK -> 'pm',
		BlockDefinitionTable::OBS_BLK_NAME-> 'om',
		BlockDefinitionTable::DES_DESIGN_PARAMS-> GLOBAL_VAR
	}
	
	
	def getSymbolReference(SymbolDefinition it){
		val blkId = blockId
		'''
			<ct:SymbRef «IF blkId != GLOBAL_VAR»blkIdRef="«blkId ?: 'ERROR!'»" «ENDIF»symbIdRef="«name»"/>
		'''
	}
	
	def getUncertMLSymbolReference(SymbolDefinition it){
		val blkId = blockId
		'''
			<var varId="«IF blkId != GLOBAL_VAR»«blkId ?: 'ERROR!'».«ENDIF»«name»"/>
		'''
	}
	
	def getBlockId(SymbolDefinition it){
		val blk = owningBlock
		val blkName = blk.identifier
		val blkId = blockPharmMLModelMapping.get(blkName)
		switch(blkName){
			case BlockDefinitionTable::VAR_LVL_BLK_NAME:
				if((it as ListDefinition).firstAttributeList.getAttributeEnumValue('type') == 'parameter') 'vm_mdl'
				else 'vm_err'
			case BlockDefinitionTable::OBS_BLK_NAME:{
				// number obs based on order in block.
				var cntr = 1
				for(obsStmt : blk.statements){
					switch(obsStmt){
						ListDefinition:
							if(obsStmt.name == name) return blkId + cntr
					}
					if(!(obsStmt instanceof EquationDefinition)) cntr++
				}
				GLOBAL_VAR
			}
			default: blkId
		}
	}
	
	def CharSequence getSymbolReference(SymbolReference it){
		if(isFunction)
			functionCall
		else if(indexExpr != null){
			// referencing of a
			getIndexedSymbolReference 
		}
		else ref.getSymbolReference
	}
	
	def private CharSequence getIndexedSymbolReference(SymbolReference it){
		val refType = ref.typeFor
		if(refType.underlyingType.isVector){
			if(indexExpr.rowIdx.begin == null && indexExpr.rowIdx.begin == null)
				// if [:] form of indexing then this is a copy so don't bother with vector selection				
				symbolReference
			else
				// Assumes with [x:]. [:y] or [x:y] indexing 
				getVectorSelectionForSymbolReference
		}
		else if(refType.underlyingType.isMatrix){
			if(indexExpr.rowIdx.begin == null && indexExpr.rowIdx.begin == null)
				// if [:,:] form of indexing then this is a copy so don't bother with vector selection				
				symbolReference
			else
				// Assumes with [x:]. [:y] or [x:y] indexing 
				getMatrixSelectionForSymbolReference
		}
	}
	
	def convertToPharmML(int intVal)'''
		<ct:Int>1</ct:Int>
	'''
		
	
	def private getMatrixSelectionForSymbolReference(SymbolReference it)'''
		<ct:MatrixSelector>
			«ref.symbolReference»
			«IF indexExpr.rowIdx.end == null && indexExpr.colIdx.end == null»
«««				No range so do simple Cell referencing
				<ct:Cell>
					<ct:RowIndex>
						«indexExpr.rowIdx.begin.expressionAsAssignment»
					</ct:RowIndex>
					<ct:ColumnIndex>
						«indexExpr.colIdx.begin.expressionAsAssignment»
					</ct:ColumnIndex>
				</ct:Cell>
			«ELSE»
				<ct:Block>
«««					If start is null then indexing is at first  
					<ct:BlockStartRow>
						«indexExpr.rowIdx.begin?.expressionAsAssignment ?: 1.convertToPharmML»
					</ct:BlockStartRow>
					<ct:BlockStartColumn>
						«indexExpr.colIdx.begin?.expressionAsAssignment ?: 1.convertToPharmML»
					</ct:BlockStartColumn>				
					«IF indexExpr.rowIdx.end == null»
«««						Not sure how to handle this 
						<!Error not supported in PharmML/>
					«ELSE»
						<ct:RowsNumber>
							<math:Binop op="plus">
								<math:Binop op="minus">
									«indexExpr.rowIdx.end.pharmMLExpr»
									«indexExpr.rowIdx.begin.pharmMLExpr»
								</math:Binop>
								<ct:Int>1</ct:Int>
							</math:Binop>
						</ct:RowsNumber>
					«ENDIF»
					«IF indexExpr.colIdx.end == null»
«««						Not sure how to handle this 
						<!Error not supported in PharmML/>
					«ELSE»
						<ct:ColumnsNumber>
							<math:Binop op="plus">
								<math:Binop op="minus">
									«indexExpr.colIdx.end.pharmMLExpr»
									«indexExpr.colIdx.begin.pharmMLExpr»
								</math:Binop>
								<ct:Int>1</ct:Int>
							</math:Binop>
						</ct:ColumnsNumber>
					«ENDIF»
				</ct:Block>
			«ENDIF»
		</ct:MatrixSelector>
	'''
	
	def private getVectorSelectionForSymbolReference(SymbolReference it)'''
		<ct:VectorSelector>
			«ref.symbolReference»
			«IF indexExpr.rowIdx.end == null»
«««				No range so do simple Cell referencing
				<ct:Cell>
					«indexExpr.rowIdx.begin.expressionAsAssignment»
				</ct:Cell>
			«ELSE»
«««				If start is null then indexing is at first  
				<ct:StartIndex>
					«indexExpr.rowIdx.begin?.expressionAsAssignment ?: 1.convertToPharmML»
				</ct:StartIndex>
				«IF indexExpr.rowIdx.end == null»
«««					Not sure how to handle this 
					<!Error not supported in PharmML/>
				«ELSE»
					<ct:SegmentLength>
						<math:Binop op="plus">
							<math:Binop op="minus">
								«indexExpr.rowIdx.end.pharmMLExpr»
								«indexExpr.rowIdx.begin.pharmMLExpr»
							</math:Binop>
							<ct:Int>1</ct:Int>
						</math:Binop>
					</ct:SegmentLength>
				«ENDIF»
			«ENDIF»
		</ct:VectorSelector>
	'''
	
	def getCategoryValueReference(CategoryValueReference it)'''
		<ct:CatRef catIdRef="«ref.name»"/>
	'''
	
	
	def getLocalSymbolReference(SymbolReference it){
		ref.getLocalSymbolReference
	}

	def getLocalSymbolReference(SymbolDefinition it)'''
		<ct:SymbRef symbIdRef="«name»"/>
	'''
	
	def getExpressionAsEquation(Expression it)'''
		<ct:Assign>
			«pharmMLExpr»
		</ct:Assign>
	'''
	
	def getExpressionAsAssignment(Expression it)'''
		<ct:Assign>
			«pharmMLExpr»
		</ct:Assign>
	'''
	
	def getExpressionAsRange(Expression expr){
		val rangeVect = if(expr instanceof VectorLiteral) expr.vector else Collections::emptyList
		'''
			<ct:Assign>
				<ct:Interval>
					«IF expr instanceof VectorLiteral»
						<ct:LeftEndpoint>
							<ct:Assign>
								«if(0 < rangeVect.size) expr.vector.get(0).pharmMLExpr else '<Error!/>'»
							</ct:Assign>
						</ct:LeftEndpoint>
						<ct:RightEndpoint>
							<ct:Assign>
								«if(1 < rangeVect.size) expr.vector.get(1).pharmMLExpr else '<Error!/>'»
							</ct:Assign>
						</ct:RightEndpoint>
					«ELSE»
						<Error!/>
					«ENDIF»
				</ct:Interval>
			</ct:Assign>
		'''
	}
	
	
    def CharSequence getPharmMLExpr(Expression expr){
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
    		IfExpression:{
    			expr.getIfExpression
    		}
    		PiecewiseExpression:{
    			expr.getPiecewiseExpression
    		}
    		VectorLiteral:{
    			getVectorLiteralExpression(expr)
    		}
    		VectorElement:{
    			expr.element.pharmMLExpr
    		}
    		MatrixLiteral:{
    			getMatrixLiteralExpression(expr)
    		}
    		MatrixElement:{
    			expr.cell.pharmMLExpr
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
    		CategoryValueReference:{
    			getCategoryValueReference(expr)
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
			case '%',
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
					«IF e.symbolRef == null»
						«IF e.evaluateMathsExpression == null»
							«IF e.evaluateLogicalExpression == null»
								«IF e.evaluateStringExpression == null»
									«e.expressionAsAssignment»
								«ELSE»
									«e.pharmMLExpr»
								«ENDIF»
							«ELSE»
								«e.pharmMLExpr»
							«ENDIF»
						«ELSE»
							«e.pharmMLExpr»
						«ENDIF»
					«ELSEIF e.symbolRef.isFunction»
						«e.expressionAsAssignment»
					«ELSE»
						«e.pharmMLExpr»
					«ENDIF»
				«ENDFOR»
			</ct:VectorElements>
		</ct:Vector>
	'''
	
	def getMatrixLiteralExpression(MatrixLiteral it)'''
		<ct:Matrix matrixType="Any">
			«FOR r : rows»
				«IF r instanceof MatrixRow»
					<ct:MatrixRow>
						«FOR e : r.cells»
							«e.expressionAsAssignment»
						«ENDFOR»
					</ct:MatrixRow>
				«ENDIF»
			«ENDFOR»
		</ct:Matrix>
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
	
	def getPiecewiseExpression(PiecewiseExpression it)'''
		<math:Piecewise>
			«FOR w : when»
				«w.whenClause»
			«ENDFOR»
			«IF otherwise != null»
				<math:Piece>
					«otherwise.pharmMLExpr»
					<math:Condition>
						<math:Otherwise/>
					</math:Condition>
				</math:Piece>
			«ENDIF»
		</math:Piecewise>
	'''
	
	def getWhenClause(PWClause it)'''
		<math:Piece>
			«value.pharmMLExpr»
			<math:Condition>
				«cond.pharmMLExpr»
			</math:Condition>
		</math:Piece>
	'''
	
	def getIfExpression(IfExpression it)'''
		<math:Piecewise>
			«FOR w : ifelseClause»
				«w.ifElseClause»
			«ENDFOR»
			«IF elseClause != null»
				<math:Piece>
					«elseClause.value.pharmMLExpr»
					<math:Condition>
						<math:Otherwise/>
					</math:Condition>
				</math:Piece>
			«ENDIF»
		</math:Piecewise>
	'''
	
	def getIfElseClause(IfExprPart it)'''
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
	
	def getCommonConstantLiteral(ConstantLiteral it) {
		val constType = switch(value){
//			case("inf"): "infinity"
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
	
	private def getConstantLiteral(ConstantLiteral it){
		switch(value){
			case("inf"): "<ct:plusInf/>"
			default:
					commonConstantLiteral
		}
	}
	
	def getRealLiteral(RealLiteral it)'''
		<ct:Real>«value»</ct:Real>
	'''
	
	def getBooleanLiteral(BooleanLiteral it)'''
		«IF isTrue»
			<ct:True/>
		«ELSE»
			<ct:False/>
		«ENDIF»
	'''
    
    private def writeMatrixUniOp(String opName, UnnamedFuncArguments args)'''
		<math:MatrixUniop op="«opName»">
			«args.unnamedArguments»
		</math:MatrixUniop>
    '''
    
    private def CharSequence getFunctionCall(SymbolReference it){
    	val a = argList
    	switch(a){
    		NamedFuncArguments:
    			switch(func){
    				case 'matrix':
    					writeMatrixConverter
					default:    				
	    			'''
					<math:FunctionCall>
						«func.localSymbolReference»
						«a.namedArguments»
					</math:FunctionCall>
	    			''' 
 				}   			
    		UnnamedFuncArguments:{
    			switch(func){
    				case 'dseq':
    					writeSequence(a.args)
    				case 'seq':
    					writeSequence(a.args)
    				case 'inverse',
    				case 'det',
    				case 'transpose':
    					writeMatrixUniOp(func, a)
    				case 'triangle':
    					writeTriangularMatrix
    				default:{
		    			val opType = if(a.args.size > 1) "Binop" else "Uniop"
		    			'''
    					<math:«opType» op="«func.pharmMlFunction»">
    						«a.unnamedArguments»
    					</math:«opType»>	
    					'''
   					}
    			}
    		}
    	}
    }
    
    
    def private writeTriangularMatrix(SymbolReference it){
    	val a = argList
    	if(a instanceof UnnamedFuncArguments){
    		val Deque<Expression> mat = new LinkedList(a.args.get(0).argument.vector)
    		val p = a.args.get(1).argument.integerValue
    		val withDiag = a.args.get(2).argument.booleanValue
    		'''
				<ct:Matrix matrixType="LowerTriangular">
					«FOR c : 1 .. p »
						<ct:MatrixRow>
							«FOR i : 1 .. c»
								«IF i == c && !withDiag»
									<ct:Real>0</ct:Real>
								«ELSE»
									«mat.pop.pharmMLExpr»
								«ENDIF»
							«ENDFOR»
						</ct:MatrixRow>
					«ENDFOR»
				</ct:Matrix>
    		'''
    	}
    	else ''''''
    }

	def private writeFullMatrix(List<List<Expression>> matVals){
		'''
		<ct:Matrix matrixType="Any">
			«FOR r : matVals»
				<ct:MatrixRow>
					«FOR c : r»
						«c.pharmMLExpr»
					«ENDFOR»
				</ct:MatrixRow>
			«ENDFOR»
		</ct:Matrix>
		'''
	}

    def private writeMatrixConverter(SymbolReference it){
    	val a = argList
    	if(a instanceof NamedFuncArguments){
    		val vectorArg = a.getArgumentExpression('vector').vector
    		val ncol = a.getArgumentExpression('ncol').integerValue
    		val isByRow = a.getArgumentExpression('byRow').booleanValue
    		val int numRows = vectorArg.length/ncol
    		val List<List<Expression>> matVals = new ArrayList<List<Expression>>
    		for(var r = 0; r < numRows; r++){
    			val row = new ArrayList<Expression>(ncol)
    			matVals.add(row)
    			for(var c = 0; c < if(isByRow) ncol else numRows; c++){
    				row.add(null)
    			}
    		}
    		var i = 0
    		for(var r = 0; r < if(isByRow) numRows else ncol; r++){
    			for(var c = 0; c < if(isByRow) ncol else numRows; c++){
    				if(isByRow){
    					matVals.get(r).set(c, vectorArg.get(i++))
    				}
    				else{
    					matVals.get(c).set(r, vectorArg.get(i++))
    				}
    			}
    		}
    		writeFullMatrix(matVals)
    	}
    	else ''''''
    }

	def private writeSequence(List<UnnamedArgument> args)'''
«««    seq functions tage args(from, to, interval)
		<ct:Sequence>
			<ct:Begin>
				«args.get(0).argument.pharmMLExpr»
			</ct:Begin>
			<ct:StepSize>
				«args.get(2).argument.pharmMLExpr»
			</ct:StepSize>
			<ct:End>
				«args.get(1).argument.pharmMLExpr»
			</ct:End>
		</ct:Sequence>
	'''
	
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
