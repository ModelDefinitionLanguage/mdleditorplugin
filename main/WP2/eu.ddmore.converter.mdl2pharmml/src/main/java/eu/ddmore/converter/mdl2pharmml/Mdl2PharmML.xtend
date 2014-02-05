package eu.ddmore.converter.mdl2pharmml

import org.ddmore.mdl.mdl.AnyExpression
import org.ddmore.mdl.mdl.Argument
import org.ddmore.mdl.mdl.ConditionalExpression
import org.ddmore.mdl.mdl.Expression
import org.ddmore.mdl.mdl.FullyQualifiedSymbolName
import org.ddmore.mdl.mdl.FunctionCall
import org.ddmore.mdl.mdl.Mcl
import org.ddmore.mdl.mdl.MclObject
import org.ddmore.mdl.mdl.ModelObject
import org.ddmore.mdl.mdl.OrExpression
import org.ddmore.mdl.mdl.SymbolDeclaration
import org.ddmore.mdl.mdl.UnaryExpression
import org.ddmore.mdl.mdl.AndExpression
import org.ddmore.mdl.mdl.LogicalExpression
import org.ddmore.mdl.mdl.AdditiveExpression
import org.ddmore.mdl.mdl.MultiplicativeExpression
import org.ddmore.mdl.mdl.PowerExpression
import org.ddmore.mdl.mdl.FullyQualifiedArgumentName
import org.ddmore.mdl.mdl.Vector
import org.ddmore.mdl.mdl.List
import java.io.FileReader
import java.io.BufferedReader
import java.util.ArrayList
import java.io.FileNotFoundException
import java.io.File
import org.ddmore.mdl.mdl.SymbolModification
import eu.ddmore.converter.mdlprinting.MdlPrinter
import org.ddmore.mdl.mdl.BlockStatement
import org.ddmore.mdl.mdl.ConditionalStatement
import java.util.HashMap

class Mdl2PharmML extends MdlPrinter {
	val	xsi="http://www.w3.org/2001/XMLSchema-instance"; 
	val xsi_schemaLocation="http://www.pharmml.org/2013/03/PharmML http://www.pharmml.org/2013/03/PharmML";
	val xmlns_pharmML="http://www.pharmml.org/2013/03/PharmML";
	val xmlns_math="http://www.pharmml.org/2013/03/Maths";
	val xmlns_ct="http://www.pharmml.org/2013/03/CommonTypes";
	val xmlns_mdef="http://www.pharmml.org/2013/03/ModelDefinition";
	val xmlns_mstep="http://www.pharmml.org/2013/03/ModellingSteps";
	val xmlns_design="http://www.pharmml.org/2013/03/TrialDesign";
	val xmlns_uncert="http://www.pharmml.org/2013/03/Uncertainty";
	val xmlns_dataSet="http://www.pharmml.org/2013/08/Dataset";
	
	val TYPE_INT = "int";
	val TYPE_REAL = "real";
	val writtenVersion = "0.1";
		
	var Mcl mcl = null;
	//Print file name and analyse all MCL objects in the source file
  	def convertToPharmML(Mcl m){
  		mcl = m;  		
  		//Create a map of variables
  		m.prepareCollections;
		var idv = m.getIndependentVariable; 
		'''
		<?xml version="1.0" encoding="UTF-8"?>
		<PharmML 
			«print_PharmML_NameSpaces»
			writtenVersion="«writtenVersion»">
			<ct:Name>"«m.eResource.fileName»"</ct:Name>
			«IF idv != null»
				<IndependentVariable symbID="«idv»"/>
			«ENDIF»
			«print_mdef_ModelDefinition»
		</PharmML>
		'''
		//print_design_TrialDesign
		//print_msteps_ModellingSteps
	}
	
	//+ Print PharmML namespaces
	def print_PharmML_NameSpaces()
		'''
		xmlns:xsi="«xsi»" 
		xmlns="«xmlns_pharmML»"
		xsi:schemaLocation="«xsi_schemaLocation»"
		xmlns:math="«xmlns_math»"
		xmlns:ct="«xmlns_ct»"
		xmlns:mdef="«xmlns_mdef»"
		xmlns:mstep="«xmlns_mstep»"
		xmlns:design="«xmlns_design»"
		xmlns:uncert="«xmlns_uncert»"
		'''
	
	//////////////////////////////////////
	// I. Model Definition
	//////////////////////////////////////
	
	//+ convertToPharmML MCL objects
	def print_mdef_ModelDefinition()'''
	<ModelDefinition xmlns="«xmlns_mdef»">
		«print_mdef_VariabilityModel»
		«print_mdef_CovariateModel»
		«print_mdef_ParameterModel»
		«print_mdef_StructuralModel»
		«print_mdef_ObservationModel»
	</ModelDefinition>
	'''
	//////////////////////////////////////
	// I.a Function Definition (not used)
	//////////////////////////////////////

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
			symbId="combinedErrorModel" symbolType="real">
			«FOR arg: arguments»
				<FunctionArgument symbId="«arg»" symbolType="«TYPE_REAL»"/>
			«ENDFOR»
			<Definition>
				«expr.print_Math_Equation»
			</Definition>
		</FunctionDefinition>
		'''
	}

	/////////////////////////
	// I.b Variability Model
	/////////////////////////	
	
	def print_mdef_VariabilityModel(){
		var model = '''''';
		for (e: level_vars.entrySet){
			if (e.value.equals("1"))
				model = model + '''«print_mdef_VariabilityModel("obsErr", "error", e.key as String)»''';
			if (e.value.equals("2"))	
				model = model + '''«print_mdef_VariabilityModel("model", "model", e.key as String)»''';
		}
		'''«model»''';
	}

	def print_mdef_VariabilityModel(String blkId, String type, String symbId)	
	'''	
	<VariabilityModel blkId="«blkId»" type="«type»">
		<Level symbId="«symbId»"/>
	</VariabilityModel>
	'''	
	
	/////////////////////////
	// I.c Covariate Model
	/////////////////////////
	
	//INDIVIDUAL_VARIABLES, use=covariate -> CovariateModel (transformation with reference)
	//GROUP_VARIABLES -> ParameterModel, SimpleParameter + expression (see I.d)
	def print_mdef_CovariateModel(){
		var model = "";
		for (o: mcl.objects){
			if (o.modelObject != null){
				var modelBlock = "";
				for (b: o.modelObject.blocks){
					if (b.inputVariablesBlock != null){
						for (s: b.inputVariablesBlock.variables){
							if (s.expression != null){
								if (s.expression.list != null){
									var use = s.expression.list.arguments.getAttribute("use");
									if (use.equalsIgnoreCase("covariate")) {
										modelBlock = modelBlock + s.identifier.print_mdef_CovariateModel;
									}
								}
							}
														
						}
					}					
				}
				model = model + 
				'''
				«IF (modelBlock.length > 0)»
					<CovariateModel blkId="«o.identifier.name»">
						«modelBlock»
					</CovariateModel>
				«ENDIF»
				'''				
			}
		}
		return model;
	}
		
	def print_mdef_CovariateModel(String symbId)'''
		<Covariate symbId="«symbId»">
			<Continuous>
				<Transformation>
					<math:Equation>
						<ct:SymbRef symbIdRef="«symbId»"/>
					</math:Equation>
				</Transformation>
			</Continuous>	
		</Covariate>
	'''	

			
	/////////////////////////////
	// I.d Parameter Model
	////////////////////////////	
		
	//Parameter object, STRUCTURAL + VARIABILITY -> ParameterModel, SimpleAttribute  
	//RANDOM_VARIABBLES_DEFINITION -> ParameterModel, RandomVariable
	def print_mdef_ParameterModel(){		
		var model = "";
		for (o: mcl.objects){
			var statements = "";
			if (o.parameterObject != null){
				for (b: o.parameterObject.blocks){
					//Parameter object, STRUCTURAL
					if (b.structuralBlock != null){
						for (id: b.structuralBlock.parameters) 
							statements = statements + '''<SimpleParameter symbId = "«id.identifier»"/>'''
			  		}
			  		//ParameterObject, VARIABILITY
			  		if (b.variabilityBlock != null){
						for (st: b.variabilityBlock.statements){
							if (st.parameter != null)
								statements = statements + '''<SimpleParameter symbId = "«st.parameter.identifier»"/>'''
						} 
			  		}
			  	}
			}
			if (o.modelObject != null){
				for (b: o.modelObject.blocks){
					//Model object, GROUP_VARIABLES (covariate parameters)
					if (b.groupVariablesBlock != null){
						for (st: b.groupVariablesBlock.statements){
							if (st.statement != null){
								st.statement.print_BlockStatement("SimpleParameter");
							}							
						}
					}	
					//Model object, RANDOM_VARIABLES_DEFINITION
					if (b.randomVariableDefinitionBlock != null){
						for (s: b.randomVariableDefinitionBlock.variables){
							if (eps_vars.get(s.identifier) != null)
								statements = statements + s.print_mdef_RandomVariable;
						} 
			  		}
			  		//Model object, INDIVIDUAL_VARIABLES
					if (b.individualVariablesBlock != null){
						for (s: b.individualVariablesBlock.statements){
							statements = statements + s.print_BlockStatement("IndividualParameter");
						} 
			  		}
			  	}
			}
	  		if (statements.length > 0){
		  		model = model + 
				'''
					<ParameterModel blkId="«o.identifier.name»">
						«statements»
					</ParameterModel>
				''';
			}
  		}
  		return model;
	}
	
	def print_ConditionalStatement(ConditionalStatement s, String tag){
		var symbols = new HashMap<String, ArrayList<Piece>>();
		var symbolOrders = new HashMap<String, Integer>();
		var Piece parent = null;
		s.prepareConditionalSymbols(parent, symbols);
		s.defineOrderOfConditionalSymbols(symbolOrders, 0);
		var max  = 0;
		for (o: symbolOrders.entrySet){
			if (max < o.value) max = o.value;
		}
		var model = "";
		for (i: 0..max){
			for (o: symbolOrders.entrySet){
				if (i == o.value) {//print a symbol declaration with this number
					val ArrayList<Piece> pieces = symbols.get(o.key);
					if (pieces != null)
						model = model + o.key.print_Pieces(tag, pieces);
				}
			}	
		}
		return model;
	}	
	
	def print_Pieces(String symbol, String tag, ArrayList<Piece> pieces)'''
	<«tag» symbId="«symbol»">
		<Assign>
			<Equation>
				<Piecewise>
					«var parts = pieces.assembleConditions»
					«FOR part:parts»
						«print_Math_LogicOpPiece(part.expression, part.condition)»
					«ENDFOR»
				</Piecewise>
			</Equation>
		</Assign>
	</«tag»>	
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
	
	def prepareConditionalSymbols(ConditionalStatement s, Piece parent, HashMap<String, ArrayList<Piece>> symbols){
	 	if (s.ifStatement != null){
			val mainExpr = s.expression.print_Math_LogicOr(0).toString;
			//System::out.println("Condition: " + mainExpr);
			s.ifStatement.addConditionalSymbol(mainExpr, parent, symbols);
		}
		if (s.elseStatement != null){
			val dualExpr = s.expression.print_DualExpression.toString;
			//System::out.println("Dual condition: " + dualExpr);
			s.elseStatement.addConditionalSymbol(dualExpr, parent, symbols);
		}		
		if (s.ifBlock != null){
			val mainExpr = s.expression.print_Math_LogicOr(0).toString;
			//System::out.println("Condition: " + mainExpr);
			for (b:s.ifBlock.statements)
				b.addConditionalSymbol(mainExpr, parent, symbols);
		}
		if (s.elseBlock != null){
			val dualExpr = s.expression.print_DualExpression.toString;
			//System::out.println("Dual condition: " + dualExpr);
			for (b:s.elseBlock.statements)
				b.addConditionalSymbol(dualExpr, parent, symbols);
		}
	}	
	 
	def addConditionalSymbol(BlockStatement s, String condition, Piece parent, HashMap<String, ArrayList<Piece>> symbols){
		if (s.symbol != null){
			if (s.symbol.expression != null){
				if (s.symbol.expression.expression != null){
					var pieces = symbols.get(s.symbol.identifier); 
					if (pieces == null) pieces = new ArrayList<Piece>();
					var Piece piece = new Piece(parent, s.symbol.expression.expression.print_Math_Expr.toString, condition);
					pieces.add(piece);
					symbols.put(s.symbol.identifier, pieces);
				}
			}
		}	
		if (s.statement != null){//nested conditional statement
			var Piece newParent = new Piece(parent, null, condition);
			s.statement.prepareConditionalSymbols(newParent, symbols);
		}
	}
	
	//Define order in which symbols will eb translated to PharmML	
	def defineOrderOfConditionalSymbols(ConditionalStatement s, HashMap<String, Integer> symbolOrders, Integer base){
		if (s.ifStatement != null){
			s.ifStatement.addOrderOfConditionalSymbol(symbolOrders, base, 0);
		}
		if (s.elseStatement != null){
			s.elseStatement.addOrderOfConditionalSymbol(symbolOrders, base, 0);
		}		
		if (s.ifBlock != null){
			var i = 0;
			for (b:s.ifBlock.statements){
				b.addOrderOfConditionalSymbol(symbolOrders, base, i);
				i = i + 1;
			}
		}
		if (s.elseBlock != null){
			var i =0;
			for (b:s.elseBlock.statements){
				b.addOrderOfConditionalSymbol(symbolOrders, base, i);
				i = i + 1;
			}
		}
	}	
	
	def addOrderOfConditionalSymbol(BlockStatement s, HashMap<String, Integer> symbolOrders, Integer base, Integer order){
		if (s.symbol != null){
			var prev = symbolOrders.get(s.symbol.identifier); 
			if (prev == null) prev = 0;
			if (prev <= base + order)
				symbolOrders.put(s.symbol.identifier, base + order);
		}	
		if (s.statement != null){//nested conditional statement
			s.statement.defineOrderOfConditionalSymbols(symbolOrders, base + order);
		}
	}	
	
	def print_mdef_RandomVariable(SymbolDeclaration s)'''
		<RandomVariable symbId="«s.identifier»">
			«s.print_VariabilityReference»
			«s.print_uncert_Distribution»
		</RandomVariable>
	'''
	
		
	/////////////////////////
	// I.e Structural Model
	/////////////////////////
	
	//+ STRUCTURAL_PARAMETER -> <StructuralModel>
	def print_mdef_StructuralModel(){
		var model ="";
		for (o: mcl.objects){
			if (o.modelObject != null){
				var variables = "";
				var initial = "";
				for (b: o.modelObject.blocks){
					if (b.modelPredictionBlock != null){
						for (st: b.modelPredictionBlock.statements){
							if (st.statement != null) {
								variables = variables + '''«st.statement.print_BlockStatement("Variable")»''';
							} else 
								if (st.odeBlock != null){
									for (s: st.odeBlock.statements){
										variables = variables + '''«s.print_BlockStatement("Variable")»''';	
									}
								}
						}
					}
				}
				model = model + 
				'''
					«IF (variables.length > 0)»
						<StructuralModel blkId="«o.identifier.name»">
							«IF (variables.length > 0)»«variables»«ENDIF»
							«IF (initial.length > 0)»«initial»«ENDIF»
						</StructuralModel>
					«ENDIF»
				'''
			}
		}
		return model;
	}
	
	
	/////////////////////////////
	// I.f Observation Model
	/////////////////////////////
	def print_mdef_ObservationModel(){
		var model = "";
		for (o: mcl.objects){
			if (o.modelObject != null){
				var statements = "";
				for (b: o.modelObject.blocks){
					if (b.observationBlock != null){
						for (st: b.observationBlock.statements){
							if (st.symbol != null){//!TODO: revise
								statements = statements + '''«st.symbol.print_mdef_ObservationModel»''';
							}
						}
					}
				}
				model = model +
				'''
					«IF (statements.length > 0)»
						<ObservationModel blkId="«o.identifier.name»">
							«statements»
						</ObservationModel>
					«ENDIF»
				'''				
			}
		}
		return model;
	}

	//
	def print_VariabilityReference(SymbolDeclaration s)'''
		«IF s.randomList != null»
			«val level = s.randomList.arguments.getAttribute("level")»
			«IF level.length > 0»
				<ct:VariabilityReference>
					<ct:SymbRef symbIdRef="«level»"/>
				</ct:VariabilityReference>
			«ENDIF»
		«ENDIF»
	'''

	//
	def print_mdef_ObservationModel(SymbolDeclaration s)'''
		«IF s.expression != null»
			«IF s.expression.expression != null»
				«var expr = s.expression.expression»
				«var classifiedVars = expr.classifyReferences»
				«var randomVars = classifiedVars.filter[k, v | v.equals("eps")]»
				«IF randomVars.size > 0»
					«FOR ss: randomVars.keySet»
						«val ref = (ss as String).defineDistribution»
						«ref.print_mdef_RandomVariable»	
					«ENDFOR»
				«ENDIF»
			«ENDIF»
		«ENDIF»
		<General symbId="«s.identifier»">
			«IF s.expression.expression != null»
				«s.expression.expression.print_Assign»
			«ENDIF»
		</General>
	'''
	
	/////////////////////
	// I.g Error Model
	/////////////////////
	//For named arguments - reorder and match declaration!	
	def print_mdef_ErrorModel(Expression expr)'''
    <ErrorModel>
    	«IF expr != null»
    		«expr.print_Assign»
    	«ENDIF»
    </ErrorModel>
	'''
		
	//+			
	def print_InitialCondition(SymbolDeclaration s)'''
		«IF s.expression != null»
			«IF s.expression.odeList != null»
				«var init = s.expression.odeList.arguments.getAttributeExpression("init")»
				«IF init != null»
					«IF init.expression != null»
						<InitialCondition symbID="«s.identifier»">
							«init.expression.print_Math_Expr»
						</InitialCondition>
					«ENDIF»	
				«ENDIF»
			«ENDIF»		
		«ENDIF»
	'''
	
	//+ returns distribution for the first declaration with a given variance
	def defineDistribution(String ref){
		//find paramName in RANDOM_VARIABLES_DEFINITION
		for (MclObject o: mcl.objects){
			if (o.modelObject != null){
				for (b: o.modelObject.blocks){
					if(b.randomVariableDefinitionBlock != null){
						for (SymbolDeclaration s: b.randomVariableDefinitionBlock.variables){
							if (s.identifier.equalsIgnoreCase(ref))
								return s;
						}
					}
				}
			}
		}
		return null;
	}
	
	//+ For each reference, define its purpose
	def classifyReferences(Expression expr){
		var classifiedVars = newHashMap;
		var iterator = expr.eAllContents();
	    while (iterator.hasNext()){
	    	var obj = iterator.next();
	    	if (obj instanceof FullyQualifiedSymbolName){
	    		var ref = obj as FullyQualifiedSymbolName;
	    		if (classifiedVars.get(ref.toStr) == null){
		    		if (theta_vars.get(ref.toStr) != null)
			    		classifiedVars.put(ref.toStr, "theta")
			    	else 
			    	{	
			    		if (eps_vars.get(ref.toStr) != null)
			    			classifiedVars.put(ref.toStr, "eps")
			    		else 
			    		{	
			    			if (eta_vars.get(ref.toStr) != null)
			    				classifiedVars.put(ref.toStr, "eta")
			    			else 
			    				classifiedVars.put(ref.toStr, "other")
			    		}
			    	}
	    		}
	    	}
	    }
	    return classifiedVars;
	}

	/////////////////////////////////////
	// General - print expression
	/////////////////////////////////////
	//+
	def print_Assign(Expression expr)'''
		<Assign>
			«expr.print_Math_Equation»
		</Assign>
	'''
	
	//+
	def print_Math_Equation(Expression expr)'''
		<Equation xmlns="«xmlns_math»" writtenVersion="«writtenVersion»">
			«expr.print_Math_Expr»
		</Equation>
	'''	
	
	//+
	def print_Math_Expr(Expression expr)'''
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
	def print_Math_LogicOr(OrExpression expr, int startIndex){
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
	def print_Math_AddOp(ArrayList<String> exprs, ArrayList<String> operators, int startIndex){
		if (exprs!= null){
			if ((startIndex < exprs.size - 1) && (startIndex < operators.size)){
				val first = exprs.get(startIndex);
				val second = exprs.print_Math_LogicAnd(startIndex + 1);
				return 
				'''
				<Binop op="«operators.get(startIndex)»">
					«first»
					«second»
				</Binop>
				'''
			} else {
				return '''«exprs.get(startIndex)»'''
			}
		}
		return ''''''
	}

	//+
	def print_Math_LogicAnd(AndExpression expr, int startIndex){
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
	def print_Math_LogicOp(LogicalExpression expr, int startIndex){
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
	def print_Math_AddOp(AdditiveExpression expr, int startIndex) { 
		if (expr.expression != null){
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
		} else {
			if (expr.string != null){
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
	def print_Math_MultOp(MultiplicativeExpression expr, int startIndex) { 
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
	def print_Math_PowerOp(PowerExpression expr, int startIndex) { 
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
	def print_Math_BinOp(String operator, Expression expr1, Expression expr2)'''
		<Binop op="«operator»">
			«expr1.print_Math_Equation»
			«expr2.print_Math_Equation»
		</Binop>
	'''	
	
	//+
	def print_Math_BinOp(String operator, UnaryExpression expr1, UnaryExpression expr2)'''
		<Binop op="«operator»">
			«expr1.print_Math_UniOp»
			«expr2.print_Math_UniOp»
		</Binop>
	'''
	
	//+
	def print_Math_UniOp(UnaryExpression expr)'''
		«IF expr.operator != null»
			<Uniop op="«expr.operator»">
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
	
	//+
	def print_ct_SymbolRef(FullyQualifiedSymbolName ref)'''
		<ct:SymbRef«IF ref.object != null» blkIdRef="«ref.object»"«ENDIF» symbIdRef="«ref.identifier»"/>
	'''
	
	//+ TODO: How to print attributes?
	def print_ct_SymbolRef(FullyQualifiedArgumentName ref)'''
		<Description>MDL reference to an attribute «ref.toStr»</Description>
		<ct:SymbRef «IF ref.parent != null»«IF ref.parent.object != null»blkIdRef="«ref.parent.object»"«ENDIF»«ENDIF» 
			symbIdRef="«ref.parent.identifier».«ref.toStr»"/>
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
			//return '''<ct:String>«value»</ct:String>''';
		}
	}
	
	//type: int, string, etc.
	def print_ct_Value(String value, String type)'''
		<ct:«type»>«value»</ct:«type»>
	'''
		
	//+ TODO: modify to print correctly any distribution
	def print_uncert_Distribution(SymbolDeclaration s)'''
		«IF s.randomList != null»
			«var args = s.randomList.arguments»
			«IF args != null»
				«var distrType = args.getAttribute("type")»
				«var mean = args.getAttributeExpression("mean")»
				«var variance = args.getAttributeExpression("variance")»
				«var stDev = args.getAttributeExpression("sd")»
				«IF (distrType.length > 0) && distrType.equalsIgnoreCase('Normal')»
					<NormalDistribution xmlns="«xmlns_uncert»" writtenVersion = "«writtenVersion»">
					«IF mean != null»
						<Mean>
							«mean.print_Math_Expr»
						</Mean>
					«ENDIF»
					«IF variance != null»
						<Variance>
							«variance.print_Math_Expr»
						</Variance>
					«ELSE»
						«IF stDev != null»
							<StdDev>
								«stDev.print_Math_Expr»
							</StdDev>
						«ENDIF»
					«ENDIF»	
					</NormalDistribution>
				«ENDIF»
			«ENDIF»
		«ENDIF»
	'''		
	
	/////////////////////////////////////////////////////////////////////////
	// II Trial Design
	//////////////////////////////////////////////////////////////////////////
	def print_design_TrialDesign()'''
	<TrialDesign>
		«print_design_Structure»
		«print_design_Population»
		«print_design_IndividualDosing»
	</TrialDesign>	
	'''
	
	///////////////////////////
	// II.a Structure
	///////////////////////////
	def print_design_Structure()
	'''
	<Structure>
		«print_design_Epoch()»
		«print_design_Arms»
		«print_design_Cells»
		«print_design_Segments»
		«print_design_Activities»
	</Structure>
	'''
	
	def print_design_Epoch(){
		return print_design_Epoch("", "", "", "");
	}
	
	def print_design_Epoch(String name, String start, String end, String order)
	'''
	<Epoch oid="«name»">
		«start.print_design_Start»
		«start.print_design_End»
		<Order>«order»</Order>
	</Epoch>
	'''

	def print_design_Start(String value)'''
	<Start>
		<ct:Real«value»></ct:Real>
	</Start>
	'''

	def print_design_End(String value)'''
	<End>
		<ct:Real«value»></ct:Real>
	</End>
	'''
	
	//TODO
	def print_design_Arms(){
		print_design_Arm("");
	}
		
	def print_design_Arm(String name)'''
	<Arm oid=«name»/>
	'''
	
	//TODO
	def print_design_Cells(){
		print_design_Cell("", "", "", "")
	}
	
	def print_design_Cell(String name, String epochRef, String armRef, String segmentRef)'''
	<Cell oid="«name»">
		<EpochRef oidRef="«epochRef»"/>
		<ArmRef oidRef="«armRef»"/>
		<SegmentRef oidRef="«segmentRef»"/>
	</Cell>
	'''
	
	//TODO
	def print_design_Segments(){
		print_design_Segment("", "");
	}
	
	def print_design_Segment(String name, String activityRef)'''
	<Segment oid="«name»">
		«IF !activityRef.equals("")»
			<ActivityRef oidRef="«activityRef»"/>
		«ENDIF»
	</Segment>
	'''
	
	//TODO
	def print_design_Activities(){
		print_design_Activity("");
	}
	
	def print_design_Activity(String name)'''
	<Activity oid="«name»">
		«print_design_Bolus»
	</Activity>
	'''

	//TODO - define structure
	def print_design_Bolus()'''
	<Bolus>
		«print_design_DoseAmount»
		«print_design_DosingTimes(null)»
		«print_design_SteadyState(null, null)»
	</Bolus>
	'''

	def print_design_DoseAmount()'''
	<DoseAmount inputType="target">
		<ct:SymbRef symbIdRef="" blkIdRef=""/>
	</DoseAmount>
	'''
	
	def print_design_DosingTimes(SymbolDeclaration s)'''
	<DosingTimes>
		«s.print_Assign("")»
	</DosingTimes>	
	'''
	
	def print_Assign(SymbolDeclaration s, String blkIdRef)'''
	«IF s != null»
		<ct:SymbRef symbIdRef="«s.identifier»«IF blkIdRef.length > 0» blkIdRef="«blkIdRef»"«ENDIF»"/>
		«IF s.expression.expression != null»
			«s.expression.expression.print_Assign»
		«ENDIF»
	«ENDIF»
	'''

	def print_Assign(SymbolModification s)'''
	«IF s != null»
		«s.identifier.print_ct_SymbolRef»
		«val value = s.list.arguments.getAttributeExpression("value")»
		«IF value != null»
			<ct:Assign>
				«value.print_Math_Expr»
			</ct:Assign>
		«ENDIF»
	«ENDIF»
	'''

	def print_design_SteadyState(SymbolDeclaration endTime, SymbolDeclaration interval)'''
	<SteadyState>
		«endTime.print_design_EndTime»
		«interval.print_design_Interval»
	</SteadyState>
	'''

	def print_design_EndTime(SymbolDeclaration s)'''
	<EndTime>
		«s.print_Assign("")»
	</EndTime>	
	'''

	def print_design_Interval(SymbolDeclaration s)'''
	<Interval>
		«s.print_Assign("")»
	</Interval>	
	'''

	///////////////////////////
	// II.b Population
	///////////////////////////
	def print_design_Population()
		//«print_VariabilityReference(?)»
	'''
	<Population>
		«print_design_IndividualTemplate»
		«print_design_DataSet»
	</Population>
	'''
	
	//Print mapping for the input variables with use=idv (individual)
	def print_design_IndividualTemplate(){
		var mappings = "";
		for (obj: mcl.objects){
			if (obj.modelObject != null){
				for (block: obj.modelObject.blocks){
					if (block.inputVariablesBlock != null){
						for (SymbolDeclaration s: block.inputVariablesBlock.variables){
							if (s.expression != null){
								if (s.expression.list != null){
									var use = s.expression.list.arguments.getAttribute("use");
									if (use.length > 0){
										if (use.equals("id")) 
											mappings = mappings + "IndividualMapping".print_design_Mapping(s.identifier);
										if (use.equals("amt"))	
											mappings = mappings + "ArmMapping".print_design_Mapping(s.identifier);
										//...	
                					}
								}
							}
						}
					}	
				}
			}
		}
		return
		'''
		<IndividualTemplate>
			«mappings»
		</IndividualTemplate>
		'''
	}	

	//
	def print_design_Mapping(String mappingType, String ref)'''
	<«mappingType»>
		<ColumnRef xmlns="«xmlns_dataSet»" columnIdRef="«ref»"/>
	</«mappingType»>
	'''

	//
	def print_design_DataSet(){
		var String[] names = {};
		var String[] types = {};
		var definition = print_Columns(names, types);
		var table = "";
		//table = table + print_Row(row);
		print_DataSet(definition, table);
	}
	

	///////////////////////////
	// II.c Individual Dosing
	///////////////////////////
	def print_design_IndividualDosing()
	'''
	<IndividualDosing>
	</IndividualDosing>
	'''

	
	////////////////////////////////////////////////
	// III Modelling Steps
	////////////////////////////////////////////////
	def print_msteps_ModellingSteps()
	'''
	<ModellingSteps>
		«print_msteps_EstimationStep»
		«print_msteps_SimulationStep»
		«print_msteps_StepDependencies»
	</ModellingSteps>
	'''
	
		
	////////////////////////////////////////////////
	// III.a Estimation Step
	////////////////////////////////////////////////
	def print_msteps_EstimationStep()'''
	<EstimationStep>
		<Description>MDL source?</Description>
		«print_msteps_ObjectiveDataSet»
	</EstimationStep>
	'''
	
	///////////////////////////////////////////////
	// III.b Simulation Step
	////////////////////////////////////////////////
	def print_msteps_SimulationStep()'''
	<SimulationStep>		
		«print_msteps_VariableAssignments»	
		«print_msteps_Observations»	

	</SimulationStep>
	'''

	//TODO
	def print_msteps_VariableAssignments() { 
		//call print_msteps_VariableAssignment
	}	
	
	def print_msteps_VariableAssignment(SymbolDeclaration s, String blockId)'''
	<ct:VariableAssignment>
		«s.print_Assign("")»
	</ct:VariableAssignment>
	'''

	//TODO
	def print_msteps_Observations() { 
		//call print_msteps_Observation
	}
	
	def print_msteps_Observation(String ref, String blockID)'''
	<Observations>
		<Timepoints>
			«print_msteps_Sequence("", "", "")»
		</Timepoints>
		<Continuous>
			<ct:SymbRef symbIdRef="«ref»"«IF blockID.length > 0» blkIdRef="«blockID»"«ENDIF»/>
		</Continuous>
	</Observations>
	'''
	
	//
	def print_msteps_Sequence(String begin, String stepSize,  String end)'''
	<ct:Sequence>
		<ct:Begin>
			«begin.print_ct_Value»
		</ct:Begin>
		<ct:StepSize>
			«stepSize.print_ct_Value»
		</ct:StepSize>
		<ct:End>
			«end.print_ct_Value»
		</ct:End>
	</ct:Sequence>
	'''
	
	///////////////////////////////////////////////
	// III.c Step Dependencies
	////////////////////////////////////////////////
	def print_msteps_StepDependencies()'''
	<StepDependencies>
		<Description>MDL source?</Description>
	</StepDependencies>
	'''
	
	def print_msteps_Step(String ref)'''
	<Step>
		<ct:OidRef oidRef="«ref»"/>
	</Step>
	'''
	
	//+
	def print_msteps_ObjectiveDataSet()'''
	<ObjectiveDataSet dataSetRef="">
		«FOR o: mcl.objects»
			«IF o.modelObject != null»«o.modelObject.print_msteps_DataSet»«ENDIF»
		«ENDFOR»
	</ObjectiveDataSet>
	'''
   //+ Print data set
	def print_msteps_DataSet(ModelObject obj){
		var names = new ArrayList<String>();
		var types = new ArrayList<String>();
		for (b: obj.blocks){
			if (b.inputVariablesBlock != null){
				if (b.inputVariablesBlock.variables != null){
					for (s: b.inputVariablesBlock.variables){
						//var valueType = s.getVarType;
						names.add(s.identifier);
						//types.add(valueType);
						types.add(TYPE_INT);
					} 
				}
			}
		}
		var fileName = mcl.getDataSource;
		if (fileName.length > 0){
			var values = fileName.getDataFileContent;
			if (values == null){
				val dotIndex = fileName.indexOf('.');
				var fileExtension = "";
				if (dotIndex > 0) fileExtension = fileName.substring(dotIndex + 1);		
				return
				'''				
					<Description>Source file not found («fileName»)!</Description>
					<ExternalSource url="file=///«fileName»" format="«fileExtension»"/>	
				''';
			}
			print_DataSet(names, types, values);
		}
	}
	
	//
	def getDataFileContent(String fileName){
		var values = new ArrayList<String[]>();
		var BufferedReader fileReader = null;
		var modelPath = mcl.eResource.getURI.toPlatformString(true);
		var file = new File(modelPath);
		var dataPath = file.getParent + "\\" + fileName;		
		try{
			//First try the path as it is			
			fileReader = new BufferedReader(new FileReader(dataPath));
		}		
		catch(FileNotFoundException e){
			//If not found, try to look in the folder "data"
			dataPath = file.getParent + "\\data\\" + fileName;
			try{
				fileReader = new BufferedReader(new FileReader(dataPath));
			}
			catch(FileNotFoundException e1){
				//If file is not ready to read, print a link (old PharmML format)				
				return null;
			}
		}
		if (fileReader != null){
			if (fileReader.ready()){ 
				var line = "";
				while ((line = fileReader.readLine()) != null) {
					val atoms = line.split("\\s{1,}|,|;");
		        	values.add(atoms);
		        }
		    	fileReader.close();			
			}			
		}
		return values;
	}
	
	//+ Read data from the source file
	// May need to skip first line (repeated column names) 
	// TODO: Do we need to check actual types against types deduced from MDL???
	def print_DataSet(ArrayList<String> names, ArrayList<String> types, ArrayList<String[]> values){
		var table = "";	
		if (values != null){
			for (row: values){
				table = table + print_Row(row);
			}
		}
		var definition = print_Columns(names, types);
		print_DataSet(definition, table);
	}

	//
	def print_Columns(String[] names, String[] types){
		if (names == null) return "";
		var definition = "";
		for (i: 0..names.size-1){
			var type = "";
			if (type != null){
				if (types.size > i) type = types.get(i);
			}
			definition = definition + 
			'''
				<ds:Column columnId="«names.get(i)»
				"«IF (type.length > 0)» valueType="«type»"«ENDIF» columnNum="«i»"/>
			''';
		}
		return definition;
	}
	
	//
	def print_Row(String[] atoms){
    	var row = "";
    	var iterator = atoms.iterator;
    	for (i : 0..iterator.size - 1){
			row = row + iterator.next.print_ct_Value;
    	}
    	row = 
    	'''
    	<Row>
    		«row»
    	</Row>
        ''';
        return row;
	}
		
	//definition, table
	def print_DataSet(String definition, String table)'''
	<DataSet>
		«IF definition.length > 0»
		<ds:Definition>
			«definition»
		</ds:Definition>
		«ENDIF»
		«IF table.length > 0»
		<ds:Table>	
			«table»
		</ds:Table>
		«ENDIF»
	</DataSet>	
	'''
	
	///////////////////////////
	//Mathematical expressions
	///////////////////////////
	
	//+ PharmML.ct_Annotation
	def print_XS_Comment(String text)'''
		<!--«text»-->
	'''
	
	//+ PharmML.ct_Annotation
	def print_ct_Annotation(String text)'''
	<Description>«text»</Description>
	'''

	def print_BlockStatement(BlockStatement st, String tag)'''
		«IF st.symbol != null»
			<«tag» symbId="«st.symbol.identifier»">
				«IF st.symbol.expression != null»
					«st.symbol.expression.print_Math_Expr»
				«ENDIF»
			</«tag»>
		«ENDIF»
		«IF st.statement != null»
			«st.statement.print_ConditionalStatement(tag)»
		«ENDIF»
	'''
	
	//+
	def print_Math_Expr(AnyExpression e)'''
		«IF e.expression != null»
			«e.expression.print_Math_Equation»
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
	
	//+
	def print_Math_FunctionCall(FunctionCall call)'''
		<math:FunctionCall>
			«call.identifier.print_ct_SymbolRef»
			«FOR arg: call.arguments.arguments»
				«arg.print_Math_FunctionArgument»
			«ENDFOR»
		</math:FunctionCall>
	'''
	
	//+
	def print_Math_FunctionArgument(Argument arg)'''
	<FunctionArgument «IF arg.identifier != null»symbId="«arg.identifier»"«ENDIF»>
		«arg.expression.print_Math_Expr»
	</FunctionArgument>
	'''
	
	
	//////////////////////////////////////////////////////////////////////
	//Helper functions
	//////////////////////////////////////////////////////////////////////
	
	//When typing is supported, rewrite this
	def getVarType(FullyQualifiedSymbolName ref){
		return TYPE_INT;
	}
	
	//When typing is supported, rewrite this
	def getVarType(SymbolDeclaration s){
		return TYPE_INT;
	}

	//+ Negation of the expression
	def print_DualExpression(OrExpression expr){
		var newAndExprs = new ArrayList<String>();
		for (andExpr: expr.expression){// x || y -> !x && !y 
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
					dualLogicalExprs.add(print_Math_AddOp(newExpressions, newOperators, 0).toString);	
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
	
	//+
	def print_Math_LogicAnd(ArrayList<String> exprs, int startIndex){
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
	
	//+
	def print_Math_LogicOr(ArrayList<String> exprs, int startIndex){
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
			case "<=": "le"
			case ">=": "ge"
			case "==": "ne"
			case "!=": "eq"
			case "+": "plus"
			case "-": "minus"
			case "*": "times"
			case "/": "divide"
			case "^": "power"
			default: operator
		}
	}
	
	//+ Return first found input variable with use=idv (individual)
	def getIndependentVariable(Mcl m){
		for (obj: m.objects){
			if (obj.modelObject != null){
				for (block: obj.modelObject.blocks){
					if (block.inputVariablesBlock != null){
						for (s: block.inputVariablesBlock.variables){
							if (s.expression != null){
								if (s.expression.list != null){
									var use = s.expression.list.arguments.getAttribute("use");
									if (use.equalsIgnoreCase("idv")) return s.identifier;
								}
							}
						}
					}	
				}
			}
		}
		return null;
	}
}