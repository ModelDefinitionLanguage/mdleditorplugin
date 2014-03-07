package eu.ddmore.converter.mdl2pharmml

import org.ddmore.mdl.mdl.SymbolDeclaration
import org.ddmore.mdl.mdl.RandomVariable
import org.ddmore.mdl.mdl.Mcl
import org.ddmore.mdl.mdl.MclObject
import java.util.ArrayList
import org.ddmore.mdl.mdl.BlockStatement
import org.ddmore.mdl.mdl.ConditionalStatement
import java.util.HashMap

class Mdl2PharmML{
	val	xsi="http://www.w3.org/2001/XMLSchema-instance"; 
	val xsi_schemaLocation="http://www.pharmml.org/2013/03/PharmML http://www.pharmml.org/2013/03/PharmML";
	val xmlns_pharmML="http://www.pharmml.org/2013/03/PharmML";
	val xmlns_math="http://www.pharmml.org/2013/03/Maths";
	val xmlns_ct="http://www.pharmml.org/2013/03/CommonTypes";
	val xmlns_mdef="http://www.pharmml.org/2013/03/ModelDefinition";
	val xmlns_mstep="http://www.pharmml.org/2013/03/ModellingSteps";
	val xmlns_design="http://www.pharmml.org/2013/03/TrialDesign";
	val xmlns_ds="http://www.pharmml.org/2013/08/Dataset";
	val writtenVersion = "0.1";

	var Mcl mcl = null;
	var DistributionPrinter distributionPrinter = null;
	var ReferenceResolver referenceResolver = null; 
	var MathPrinter mathPrinter = null;
	
	//Print file name and analyse all MCL objects in the source file
  	def convertToPharmML(Mcl m){
  		mcl = m;  
  		distributionPrinter = new DistributionPrinter();
  		referenceResolver = new ReferenceResolver(mcl);	
		mathPrinter = new MathPrinter(referenceResolver);
		val msPrinter = new ModellingStepsPrinter(mcl, mathPrinter);  		
		'''
		<?xml version="1.0" encoding="UTF-8"?>
		<PharmML 
			«print_PharmML_NameSpaces»
			writtenVersion="«writtenVersion»">
			<ct:Name>"«referenceResolver.fileName(m.eResource)»"</ct:Name>
			«print_mdef_IndependentVariables»
			«print_mdef_ModelDefinition»
			«msPrinter.print_msteps_ModellingSteps»
		</PharmML>
		'''
		//print_design_TrialDesign
	}
	
	//+ Print PharmML namespaces
	def print_PharmML_NameSpaces()
		'''
		xmlns:xsi="«xsi»" 
		xmlns="«xmlns_pharmML»"
		xsi:schemaLocation="«xsi_schemaLocation»"
		xmlns:math="«xmlns_math»"
		xmlns:ct="«xmlns_ct»"
		xmlns:ds="«xmlns_ds»"
		xmlns:mdef="«xmlns_mdef»"
		xmlns:mstep="«xmlns_mstep»"
		xmlns:design="«xmlns_design»"
		'''
	
	//////////////////////////////////////
	// I. Model Definition
	//////////////////////////////////////
	
	//+ convertToPharmML MCL objects
	protected def print_mdef_ModelDefinition()'''
	<ModelDefinition xmlns="«xmlns_mdef»">
		«print_mdef_VariabilityModel»
		«print_mdef_CovariateModel»
		«print_mdef_ParameterModel»
		«print_mdef_StructuralModel»
		«print_mdef_ObservationModel»
	</ModelDefinition>
	'''
	
	//////////////////////////////////////
	//IndependentVariables
	//////////////////////////////////////
	protected def print_mdef_IndependentVariables()'''
		«FOR s: referenceResolver.ind_vars»
			<IndependentVariable symbId="«s»"/>
		«ENDFOR»
	'''
	//////////////////////////////////////
	// I.a Function Definition (not used)
	//////////////////////////////////////

	/////////////////////////
	// I.b Variability Model
	/////////////////////////	
	
	protected def print_mdef_VariabilityModel(){
		var model = "";
		for (o: mcl.objects){
			if (o.modelObject != null){
				var errorVars = referenceResolver.vm_err_vars.get(o.identifier.name);
				if (errorVars != null){
					model = model + 
					'''
						<VariabilityModel blkId="vm_err.«o.identifier.name»" type = "error">
							«FOR s: errorVars»
								<Level symbId="«s»"/>
							«ENDFOR»
						</VariabilityModel>
					'''		
				}
				
				var mdlVars = referenceResolver.vm_mdl_vars.get(o.identifier.name);
				if (mdlVars != null){
					model = model + 
					'''
						<VariabilityModel blkId="vm_mdl.«o.identifier.name»" type = "model">
							«FOR s: mdlVars»
								<Level symbId="«s»"/>
							«ENDFOR»
						</VariabilityModel>
					'''		
				}
			}
		}
		return model;
	}

	/////////////////////////
	// I.c Covariate Model
	/////////////////////////
	
	//INDIVIDUAL_VARIABLES, use=covariate -> CovariateModel (transformation with reference)
	//GROUP_VARIABLES -> ParameterModel, SimpleParameter + expression (see I.d)
	protected def print_mdef_CovariateModel(){
		var model = "";
		for (o: mcl.objects){
			if (o.modelObject != null){
				var covariateVars = referenceResolver.cm_vars.get(o.identifier.name);
				if (covariateVars != null){
					model = model +
					'''
					<CovariateModel blkId="cm.«o.identifier.name»">
						«FOR s: covariateVars»
							«s.print_mdef_CovariateModel»
						«ENDFOR»
					</CovariateModel>
					'''
				}
			}
		}
		return model;
	}
	

	//Keep maps of known PharmML variables per block to use it in blkRefId	
	protected def print_mdef_CovariateModel(String symbId)'''
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
	protected def print_mdef_ParameterModel(){		
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
								st.statement.print_BlockStatement("SimpleParameter", false);
							}							
						}
					}	
					//Model object, RANDOM_VARIABLES_DEFINITION
					if (b.randomVariableDefinitionBlock != null){
						for (s: b.randomVariableDefinitionBlock.variables){
							if (referenceResolver.isIndependentVariable(s.identifier))
								statements = statements + s.print_mdef_RandomVariable;
						} 
			  		}
			  		//Model object, INDIVIDUAL_VARIABLES
					if (b.individualVariablesBlock != null){
						for (s: b.individualVariablesBlock.statements){
							statements = statements + s.print_BlockStatement("IndividualParameter", false);
						} 
			  		}
			  	}
			}
	  		if (statements.length > 0){
		  		model = model + 
				'''
					<ParameterModel blkId="pm.«o.identifier.name»">
						«statements»
					</ParameterModel>
				''';
			}
  		}
  		return model;
	}
	
	protected def print_ConditionalStatement(ConditionalStatement s, String tag){
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
						model = model + o.key.print_Pieces(tag, pieces, true);
				}
			}	
		}
		return model;
	}	
	
	
	protected def prepareConditionalSymbols(ConditionalStatement s, Piece parent, HashMap<String, ArrayList<Piece>> symbols){
	 	if (s.ifStatement != null){
			val mainExpr = mathPrinter.print_Math_LogicOr(s.expression, 0).toString;
			s.ifStatement.addConditionalSymbol(mainExpr, parent, symbols);
		}
		if (s.elseStatement != null){
			val dualExpr = mathPrinter.print_DualExpression(s.expression).toString;
			s.elseStatement.addConditionalSymbol(dualExpr, parent, symbols);
		}		
		if (s.ifBlock != null){
			val mainExpr = mathPrinter.print_Math_LogicOr(s.expression, 0).toString;
			for (b:s.ifBlock.statements)
				b.addConditionalSymbol(mainExpr, parent, symbols);
		}
		if (s.elseBlock != null){
			val dualExpr = mathPrinter.print_DualExpression(s.expression).toString;
			for (b:s.elseBlock.statements)
				b.addConditionalSymbol(dualExpr, parent, symbols);
		}
	}	
	 
	protected def void addConditionalSymbol(BlockStatement s, String condition, Piece parent, HashMap<String, ArrayList<Piece>> symbols){
		if (s.symbol != null){
			if (s.symbol.expression != null){
				if (s.symbol.expression.expression != null){
					var pieces = symbols.get(s.symbol.identifier); 
					if (pieces == null) pieces = new ArrayList<Piece>();
					var Piece piece = new Piece(parent, mathPrinter.print_Math_Expr(s.symbol.expression.expression).toString, condition);
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
	
	def print_Pieces(String symbol, String tag, ArrayList<Piece> pieces, boolean printType)'''
	<«tag» symbId="«symbol»"«IF printType» symbolType="«mathPrinter.TYPE_REAL»"«ENDIF»>
		«mathPrinter.print_Pieces(pieces)»
	</«tag»>
	'''
	
	//Define order in which symbols will eb translated to PharmML	
	def void defineOrderOfConditionalSymbols(ConditionalStatement s, HashMap<String, Integer> symbolOrders, Integer base){
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
			var i = 0;
			for (b:s.elseBlock.statements){
				b.addOrderOfConditionalSymbol(symbolOrders, base, i);
				i = i + 1;
			}
		}
	}	
	
	def void addOrderOfConditionalSymbol(BlockStatement s, HashMap<String, Integer> symbolOrders, Integer base, Integer order){
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
	
	def print_mdef_RandomVariable(RandomVariable s)'''
		<RandomVariable symbId="«s.identifier»">
			«s.print_VariabilityReference»
			«distributionPrinter.print_uncert_Distribution(s.randomList)»
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
				for (b: o.modelObject.blocks){
					if (b.modelPredictionBlock != null){
						for (st: b.modelPredictionBlock.statements){
							if (st.statement != null) {
								variables = variables + '''«st.statement.print_BlockStatement("ct:Variable", true)»''';
							} else 
								if (st.odeBlock != null){
									for (s: st.odeBlock.statements){
										variables = variables + '''«s.print_BlockStatement("ct:Variable", true)»''';	
									}
								}
						}
					}
				}
				model = model + 
				'''
					«IF (variables.length > 0)»
						<StructuralModel blkId="sm.«o.identifier.name»">
							«IF (variables.length > 0)»«variables»«ENDIF»
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
							statements = statements + '''«st.print_mdef_ObservationModel»''';
						}
					}
				}
				model = model +
				'''
					«IF (statements.length > 0)»
						<ObservationModel blkId="om.«o.identifier.name»">
							«statements»
						</ObservationModel>
					«ENDIF»
				'''				
			}
		}
		return model;
	}
	
	//Print splitting random variables and simple parameters, needed for ObservationModel 
	def print_mdef_ObservationModel(BlockStatement st)'''
		«IF st.symbol != null»
			«st.symbol.print_mdef_ObservationModel»
		«ENDIF»
		«IF st.statement != null»
		«ENDIF»
	'''
	//TODO print conditionally defined observation models «st.statement.print_ConditionalStatement(tag)»
		
	//Print observation model declaration
	def print_mdef_ObservationModel(SymbolDeclaration s)'''
		«IF s.expression != null»
			«IF s.expression.expression != null»
				«var expr = s.expression.expression»
				«var classifiedVars = referenceResolver.getReferences(expr)»
				«IF classifiedVars.size > 0»
					«FOR ss: classifiedVars.entrySet.filter[x | x.value.equals("random")]»
						«val ref = ss.key.defineDistribution»
						«IF ref != null»
							«ref.print_mdef_RandomVariable»
						«ENDIF»
					«ENDFOR»
				«ENDIF»
			«ENDIF»
		«ENDIF»
		<General symbId="«s.identifier»">
			«IF s.expression.expression != null»
				«mathPrinter.print_Assign(s.expression.expression)»
			«ENDIF»
		</General>
	'''	
	
	//
	def print_VariabilityReference(RandomVariable s)'''
		«val level = referenceResolver.getAttribute(s.randomList.arguments, "level")»
		«IF level.length > 0»
			<ct:VariabilityReference>
				<ct:SymbRef symbIdRef="«level»"/>
			</ct:VariabilityReference>
		«ENDIF»
	'''
	
	/////////////////////
	// I.g Error Model
	/////////////////////
	//For named arguments - reorder and match declaration!	
	/*def print_mdef_ErrorModel(Expression expr)'''
    <ErrorModel>
    	«IF expr != null»
    		«mathPrinter.print_Assign(expr)»
    	«ENDIF»
    </ErrorModel>
	'''*/
		
	//+			
	/*def print_InitialCondition(SymbolDeclaration s)'''
		«IF s.expression != null»
			«IF s.expression.odeList != null»
				«var init = referenceResolver.getAttributeExpression(s.expression.odeList.arguments, "init")»
				«IF init != null»
					«IF init.expression != null»
						<InitialCondition symbID="«s.identifier»">
							«mathPrinter.print_Math_Expr(init.expression)»
						</InitialCondition>
					«ENDIF»	
				«ENDIF»
			«ENDIF»		
		«ENDIF»
	'''*/
	
	//+ returns distribution for the first declaration with a given variance
	def defineDistribution(String ref){
		//find paramName in RANDOM_VARIABLES_DEFINITION
		for (MclObject o: mcl.objects){
			if (o.modelObject != null){
				for (b: o.modelObject.blocks){
					if(b.randomVariableDefinitionBlock != null){
						for (s: b.randomVariableDefinitionBlock.variables){
							if (s.identifier.equalsIgnoreCase(ref))
								return s;
						}
					}
				}
			}
		}
		return null;
	}
	
	def print_BlockStatement(BlockStatement st, String tag, Boolean printType)'''
		«IF st.symbol != null»
			<«tag» symbId="«st.symbol.identifier»"«IF printType» symbolType="«mathPrinter.TYPE_REAL»"«ENDIF»>
				«IF st.symbol.expression != null»
					«IF st.symbol.expression.expression != null»
						«mathPrinter.print_Assign(st.symbol.expression.expression)»
					«ENDIF»
				«ENDIF»
			</«tag»>
		«ENDIF»
		«IF st.statement != null»
			«st.statement.print_ConditionalStatement(tag)»
		«ENDIF»
	'''
	
	/////////////////////////////
	// I.i CorrelationModel
	/////////////////////////////
	def print_mdef_CollerationModel(){
		var model = "";
		for (o: mcl.objects){
			if (o.modelObject != null){
				var statements = "";
				model = model +
				'''
					«IF (statements.length > 0)»
						<Correlation blkId="c.«o.identifier.name»">
							«statements»
						</Correlation>
					«ENDIF»
				'''				
			}
		}
		return model;
	}	
}