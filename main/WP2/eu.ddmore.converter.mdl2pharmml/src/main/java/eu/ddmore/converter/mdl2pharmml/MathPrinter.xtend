package eu.ddmore.converter.mdl2pharmml

import org.ddmore.mdl.mdl.ConditionalExpression
import org.ddmore.mdl.mdl.Expression
import org.ddmore.mdl.mdl.OrExpression
import org.ddmore.mdl.mdl.UnaryExpression
import org.ddmore.mdl.mdl.AndExpression
import org.ddmore.mdl.mdl.LogicalExpression
import org.ddmore.mdl.mdl.AdditiveExpression
import org.ddmore.mdl.mdl.MultiplicativeExpression
import org.ddmore.mdl.mdl.PowerExpression
import org.ddmore.mdl.mdl.FullyQualifiedSymbolName
import org.ddmore.mdl.mdl.Vector
import org.ddmore.mdl.mdl.List
import org.ddmore.mdl.mdl.AnyExpression
import org.ddmore.mdl.mdl.Argument
import org.ddmore.mdl.mdl.FunctionCall
import java.util.ArrayList
import eu.ddmore.converter.mdlprinting.MdlPrinter
import org.ddmore.mdl.mdl.FullyQualifiedArgumentName

class MathPrinter extends MdlPrinter{

 	extension ReferenceResolver resolver=null
  
  	new(ReferenceResolver resolver) {
    	this.resolver = resolver
 	}
  
	val xmlns_math="http://www.pharmml.org/2013/03/Maths";
	val xmlns_ct="http://www.pharmml.org/2013/03/CommonTypes";
		
	val TYPE_REAL = "real";
	
	//List of mathematical functions (MDL = PharmML) converted to PharmML operator
	var standardFunctions = newHashSet("abs", "exp", "factorial", "factl", "gammaln", "ln", "log", 
		"logistic", "logit", "normcdf", "probit", "sqrt", "sin", "cos", "tan", "sec", "csc", "cot", 
		"sinh", "cosh", "tanh", "sech", "csch", "coth", "arcsin", "arccos", "arctan", "arcsec", "arccsc", 
		"arccot", "arcsinh", "arccosh", "arctanh", "arcsech", "arccsch", "arccoth", 
		"floor", "ceiling", "logx", "root", "min", "max");
	
	//List of functions that are represented by special PharmML tags
	//MDL != PharmML, e.g., "errorexit" is validated in MDL but converts to a usual function call  
	var specialFunctions = newHashSet("seq", "update", "runif", "PHI");
	
		//Generate function definition from a math expression like a + b*f
	def print_mdef_FunctionDefinition(Expression expr) { 
		var arguments = newHashSet;
		var iterator = expr.eAllContents();
	    while (iterator.hasNext()){
	    	var obj = iterator.next();
	    	if (obj instanceof FullyQualifiedSymbolName){
	    		var ref = obj as FullyQualifiedSymbolName;
	    		arguments.add(ref.toStr);
	    	}
	    }
		'''
		<FunctionDefinition xmlns:ct="«xmlns_ct»"
			symbId="combinedErrorModel" symbolType="«TYPE_REAL»">
			«FOR arg: arguments»
				<FunctionArgument symbId="«arg»" symbolType="«TYPE_REAL»"/>
			«ENDFOR»
			<Definition>
				«expr.print_Math_Equation»
			</Definition>
		</FunctionDefinition>
		'''
	}

	
	//+
	def CharSequence print_Math_Expr(AnyExpression e)'''
		«IF e.expression != null»
			«e.expression.print_Math_Expr»
		«ENDIF»
		«IF e.list != null»
			«e.print_list»
		«ENDIF»	
		«IF e.odeList != null»
			«e.print_odeList»
		«ENDIF»	
	'''
	
	//+
	def print_odeList(AnyExpression e) '''
		«var deriv = e.odeList.arguments.getAttributeExpression("deriv")»
		«IF deriv != null»«deriv.print_Math_Expr»«ENDIF»
	'''
	
	//+
	def print_Categorical(List categories)'''
		<Categorical>
		«FOR c: categories.arguments.arguments»
			<Category>
				«c.identifier»
			</Category>
		«ENDFOR»
		</Categorical>
	'''
		
	//+ Translate depending on list attributes
	def	print_list(AnyExpression e){
		if (e.list != null){
			val args  = e.list.arguments;
			val type = args.getAttribute("type");
			val define =  args.getAttributeExpression("define");
			if (type.equals("categorical") && (define.list != null)){
				define.list.print_Categorical;
			}
		}		
	} 

	//+ Convert math functions to PharmML 
	def print_Math_FunctionCall(FunctionCall call){
		if (specialFunctions.contains(call.identifier.identifier)){
			 if (call.identifier.identifier.equals("seq")){
			 	val params = call.arguments.arguments;
			 	//TODO: process named parameters: (start, stepSize, end) or (start, stepSize, repetition)
			 	if (params.size == 3)
			 		return print_ct_Sequence(
			 			params.get(0).expression.print_Math_Expr.toString, 
			 			params.get(1).expression.print_Math_Expr.toString, 
			 			params.get(2).expression.print_Math_Expr.toString
			 		);
			 }
		} else {
			if (standardFunctions.contains(call.identifier.identifier)){
				//Convert standard mathematical functions to a PharmML operator with the same name;		
				return call.print_Math_FunctionCall_Standard;	
			} else {
				return call.print_Math_FunctionCall_UserDefined;
			}
		}
	}

	//Functions from the standardFunctions list are PharmML operators
	def print_Math_FunctionCall_Standard(FunctionCall call)
	'''
		«IF call.arguments.arguments.size == 1»
			<Uniop op="«call.identifier.identifier»">
				«call.arguments.arguments.get(0).expression.print_Math_Expr»
			</Uniop>
		«ELSE»
			«IF call.arguments.arguments.size == 2»
				<Binop op="«call.identifier.identifier»">
					«call.arguments.arguments.get(0).expression.print_Math_Expr»
					«call.arguments.arguments.get(1).expression.print_Math_Expr»
				</Binop>
			«ENDIF»
		«ENDIF»
	'''
	
	//+ Convert user defined math functions to PharmML 
	def print_Math_FunctionCall_UserDefined(FunctionCall call)
	'''
		<math:FunctionCall>
			«call.identifier.print_ct_SymbolRef»
			«FOR arg: call.arguments.arguments»
				«arg.print_Math_FunctionArgument»
			«ENDFOR»
		</math:FunctionCall>
	'''
	
	//+
	def print_Math_FunctionArgument(Argument arg)'''
	<FunctionArgument«IF arg.identifier != null» symbId="«arg.identifier»"«ENDIF»>
		«arg.expression.print_Math_Expr»
	</FunctionArgument>
	'''	
	
		def print_Assign(Expression expr)'''
		<ct:Assign>
			«expr.print_Math_Equation»
		</ct:Assign>
	'''
	
	//+
	def print_Math_Equation(Expression expr)'''
		<Equation xmlns="«xmlns_math»">
			«expr.print_Math_Expr»
		</Equation>
	'''	
	
	//+
	def CharSequence print_Math_Expr(Expression expr)'''
		«expr.conditionalExpression.print_Math_LogicOp» 
	'''
	
	//+
	def print_Math_LogicOp(ConditionalExpression expr)'''
		«IF expr.expression1 != null»
			<Piecewise>
				«expr.expression1.print_Math_LogicOpPiece(expr.expression.print_Math_LogicOr(0).toString)»
				«expr.expression2.print_Math_LogicOpPiece(expr.expression.print_DualExpression.toString)»
			</Piecewise>
		«ELSE»
			«expr.expression.print_Math_LogicOr(0)»
		«ENDIF»
	'''		
	
	//+
	def print_Math_LogicOpPiece(Expression expr, String condition)'''
		<Piece>
			«expr.print_Math_Expr»
			«IF condition != null»
				<Condition>
					«condition»
				</Condition>
			«ENDIF»
		</Piece>
	'''
	
	//+
	def CharSequence print_Math_LogicOr(OrExpression expr, int startIndex){
		if (expr.expression != null){
			if (startIndex < expr.expression.size - 1){
				val first = expr.expression.get(startIndex).print_Math_LogicAnd(0);
				val second = expr.print_Math_LogicOr(startIndex + 1);
				return 
				'''
				<LogicBinop op="or">
					«first»
					«second»
				</LogicBinop>
				'''
			} else {
				return '''«expr.expression.get(startIndex).print_Math_LogicAnd(0)»'''
			}
		}
		return ''''''
	}
		
	//+
	def print_Math_LogicalOp(ArrayList<String> exprs, ArrayList<String> operators, int startIndex){
		if (exprs!= null){
			if ((startIndex < exprs.size - 1) && (startIndex < operators.size)){
				val first = exprs.get(startIndex);
				val second = exprs.print_Math_LogicAnd(startIndex + 1);
				return 
				'''
				<LogicBinop op="«operators.get(startIndex)»">
					«first»
					«second»
				</LogicBinop>
				'''
			} else {
				return '''«exprs.get(startIndex)»'''
			}
		}
		return ''''''
	}

	//+
	def CharSequence print_Math_LogicAnd(AndExpression expr, int startIndex){
		if (expr.expression != null){
			if (startIndex < expr.expression.size - 1){
				val first = expr.expression.get(startIndex).print_Math_LogicOp(0);
				val second = expr.print_Math_LogicAnd(startIndex + 1);
				return 
				'''
				<LogicBinop op="and">
					«first»
					«second»
				</LogicBinop>
				'''
			} else {
				return '''«expr.expression.get(startIndex).print_Math_LogicOp(0)»'''
			}
		}
		return ''''''
	}
	
	//MDL: no XOR operator
	
	//+
	def CharSequence print_Math_LogicOp(LogicalExpression expr, int startIndex){
		if (expr.expression != null){
			if (startIndex < expr.expression.size - 1){
				val operator = expr.operator.get(startIndex).convertOperator;
				val first = expr.expression.get(startIndex).print_Math_AddOp(0);
				val second = expr.print_Math_LogicOp(startIndex + 1);
				return 
				'''
				<LogicBinop op="«operator»">
					«first»
					«second»
				</LogicBinop>
				'''
			} else {
				return '''«expr.expression.get(startIndex).print_Math_AddOp(0)»'''
			}
		}
		return ''''''
	}
	
	//+
	def CharSequence print_Math_AddOp(AdditiveExpression expr, int startIndex) { 
		if (expr.expression != null){
			if (expr.expression.size > 0){
				if (startIndex < expr.expression.size - 1){
					val operator = expr.operator.get(startIndex).convertOperator;
					val first = expr.expression.get(startIndex).print_Math_MultOp(0);
					val second = expr.print_Math_AddOp(startIndex + 1);
					return 
					'''
					<Binop op="«operator»">
						«first»
						«second»
					</Binop>
					'''
				} else {
					return '''«expr.expression.get(startIndex).print_Math_MultOp(0)»'''
				}
			}
		}
		if (expr.string != null){
			if (expr.string.size > 0){
				var res = "";
				for (s: expr.string){
					res = res + s; 
				}
				return 
				'''<ct:String>«res»</ct:String>'''
			}
		}
		return ''''''			
	}
	
	//+
	def CharSequence print_Math_MultOp(MultiplicativeExpression expr, int startIndex) { 
		if (expr.expression != null){
			if (startIndex < expr.expression.size - 1){
				val operator = expr.operator.get(startIndex).convertOperator;
				val first = expr.expression.get(startIndex).print_Math_PowerOp(0);
				val second = expr.print_Math_MultOp(startIndex + 1);
				return 
				'''
				<Binop op="«operator»">
					«first»
					«second»
				</Binop>
				'''
			} else {
				return '''«expr.expression.get(startIndex).print_Math_PowerOp(0)»'''
			}
		}
		return ''''''		
	}
	
	//+
	def CharSequence print_Math_PowerOp(PowerExpression expr, int startIndex) { 
		if (expr.expression != null){
			if (startIndex < expr.expression.size - 1){
				return 
				'''
				<Binop op="«expr.operator.get(startIndex).convertOperator»">
					«expr.expression.get(startIndex).print_Math_UniOp»
					«expr.print_Math_PowerOp(startIndex + 1)»
				</Binop>
				'''
			} else {
				return '''«expr.expression.get(startIndex).print_Math_UniOp»'''
			}
		}
		return ''''''				
	}
	
	//+
	def CharSequence print_Math_UniOp(UnaryExpression expr)'''
		«IF expr.operator != null»
			<Uniop op="«expr.operator.convertOperator»">
				«expr.expression.print_Math_UniOp»
			</Uniop>
		«ELSE»
			«IF expr.parExpression != null»
				«expr.parExpression.expression.print_Math_Expr»
			«ELSE»
				«IF expr.primary != null»
					«IF expr.primary.functionCall != null»
						«expr.primary.functionCall.print_Math_FunctionCall»
					«ENDIF»
					«IF expr.primary.number != null»
						«expr.primary.number.print_ct_Value»
					«ENDIF»
					«IF expr.primary.symbol !=null»
						«expr.primary.symbol.print_ct_SymbolRef»
					«ENDIF»
					«IF expr.primary.attribute !=null»
						«expr.primary.attribute.print_ct_SymbolRef»
					«ENDIF»
					«IF expr.primary.vector != null»
						<!-- TODO: print vector -->
					«ENDIF»
				«ENDIF»	
			«ENDIF»
		«ENDIF»
	'''
	
	//+
	def print_ct_Vector(Vector vector)'''
		<ct:Vector>
			«FOR v: vector.values»
				«v.print_Math_Expr»
			«ENDFOR»
		</ct:Vector>
	'''
	
		//
	def print_ct_Sequence(String begin, String stepSize,  String end)'''
		<ct:Sequence>
			<ct:Begin>
				«begin»
			</ct:Begin>
			<ct:StepSize>
				«stepSize»
			</ct:StepSize>
			<ct:End>
				«end»
			</ct:End>
		</ct:Sequence>
	'''
	
	//+
	def print_ct_Value(String value){
		try{        			
        	if (value.indexOf(".") > -1){
        		Double::parseDouble(value);
				return '''<ct:Double>«value»</ct:Double>''';
        	} else {
	       		Integer::parseInt(value);
    	   		return '''<ct:Int>«value»</ct:Int>''';	
        	}
        }
		catch (NumberFormatException e) {
			return '''<ct:Id>«value»</ct:Id>''';
		}
	}
	
	//type: int, string, etc.
	def print_ct_Value(String value, String type)'''
		<ct:«type»>«value»</ct:«type»>
	'''
	
		//+ Negation of the expression x || y -> !x && !y 
	def print_DualExpression(OrExpression expr){
		var newAndExprs = new ArrayList<String>();
		for (andExpr: expr.expression){
			var dualLogicalExprs = new ArrayList<String>();
			for (logicalExpr: andExpr.expression){
				if (logicalExpr.expression != null){
					var newExpressions = new ArrayList<String>();
					var newOperators = new ArrayList<String>();
					for (addExpr: logicalExpr.expression){
						newExpressions.add(addExpr.print_Math_AddOp(0).toString);
					}
					if (logicalExpr.operator != null)
						for (op: logicalExpr.operator){
							newOperators.add(op.getDualOperator.convertOperator);
						}
					dualLogicalExprs.add(print_Math_LogicalOp(newExpressions, newOperators, 0).toString);	
				} else {
					if (logicalExpr.boolean != null){
						if (logicalExpr.negation == null){
							dualLogicalExprs.add( 
							'''
								<Uniop op="not">
									<«logicalExpr.boolean»/>
								</Uniop>
							''')
						} else 
						dualLogicalExprs.add( 
						'''
							<«logicalExpr.boolean»/>
						''')
					}
				}
			}
			newAndExprs.add(dualLogicalExprs.print_Math_LogicAnd(0).toString);		
		}
		return newAndExprs.print_Math_LogicOr(0);
	}
	
	//+Expr1 && ... && Expr_n
	def CharSequence print_Math_LogicAnd(ArrayList<String> exprs, int startIndex){
		if (exprs!= null){
			if (startIndex < exprs.size - 1){
				val first = exprs.get(startIndex);
				val second = exprs.print_Math_LogicAnd(startIndex + 1);
				return 
				'''
				<LogicBinop op="and">
					«first»
					«second»
				</LogicBinop>
				'''
			} else {
				return '''«exprs.get(startIndex)»'''
			}
		}
		return ''''''
	}
	
	//+Expr1 || ... || Expr_n
	def CharSequence print_Math_LogicOr(ArrayList<String> exprs, int startIndex){
		if (exprs!= null){
			if (startIndex < exprs.size - 1){
				val first = exprs.get(startIndex);
				val second = exprs.print_Math_LogicOr(startIndex + 1);
				return 
				'''
				<LogicBinop op="or">
					«first»
					«second»
				</LogicBinop>
				'''
			} else {
				return '''«exprs.get(startIndex)»'''
			}
		}
		return ''''''
	}
	
	def print_Pieces(ArrayList<Piece> pieces)'''
		<ct:Assign>
			<Equation xmlns="«xmlns_math»">
				<Piecewise>
					«var parts = pieces.assembleConditions»
					«FOR part:parts»
						«print_Math_LogicOpPiece(part.expression, part.condition)»
					«ENDFOR»
				</Piecewise>
			</Equation>
		</ct:Assign>
	'''

	//+ Here expr and condition are PharmML representation of MDL expressions
	def print_Math_LogicOpPiece(String expr, String condition)'''
		<Piece>
			«expr»
			«IF condition != null»
				<Condition>
					«condition»
				</Condition>
			«ENDIF»
		</Piece>
	'''	
						
	def assembleConditions(ArrayList<Piece> pieces){
		var ArrayList<Piece> model = new ArrayList<Piece>();
		var piecesWithExpr = pieces.filter[o | o.expression != null];
		for (p: piecesWithExpr){
			var Piece current = p;
			var ArrayList<String> conditions = new ArrayList<String>();
			while (current != null){ 
				if (current.condition != null){
					conditions.add(current.condition);
				}
				current = current.parent
			}
			if (conditions.size > 0){
				var condition = conditions.print_Math_LogicAnd(0).toString;				
				var Piece assembedPiece = new Piece(null, p.expression, condition);
				model.add(assembedPiece);
			}	
		}
		return model;
	}
	
	
	//+Returns a dual operator for a given logical operator
	def getDualOperator(String operator){
		switch (operator){
			case "<": ">"
			case ">": "<"
			case "<=": ">="
			case ">=": "<="
			case "==": "!="
			case "!=": "=="
			default: operator
		}
	}
	
	//+Returns PharmML name for a given operator
	override convertOperator(String operator){
		switch (operator){
			case "<": "lt"
			case ">": "gt"
			case "<=": "leq"
			case ">=": "geq"
			case "==": "neq"
			case "!=": "eq"
			case "+": "plus"
			case "-": "minus"
			case "*": "times"
			case "/": "divide"
			case "^": "power"
			default: operator
		}
	}
	
	//+
	def print_ct_SymbolRef(FullyQualifiedSymbolName ref)'''
		«var blkId = resolver.getReferenceBlock(ref)»
		<ct:SymbRef«IF blkId.length > 0» blkIdRef="«blkId»"«ENDIF» symbIdRef="«ref.identifier»"/>
	'''
	
	//+ TODO: How to print attributes?
	def print_ct_SymbolRef(FullyQualifiedArgumentName ref)'''
		<Description>MDL reference to an attribute «ref.toStr»</Description>
		«var blkId = ""»
		«IF ref.parent != null»
			«blkId = resolver.getReferenceBlock(ref.parent)»
		«ENDIF»
		<ct:SymbRef «IF blkId.length > 0»blkIdRef="«blkId»"«ENDIF» 
			symbIdRef="«ref.parent.identifier».«ref.toStr»"/>
	'''
}