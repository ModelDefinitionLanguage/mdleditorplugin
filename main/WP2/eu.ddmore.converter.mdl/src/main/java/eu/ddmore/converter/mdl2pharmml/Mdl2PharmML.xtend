package eu.ddmore.converter.mdl2pharmml

import org.ddmore.mdl.mdl.SymbolDeclaration
import org.ddmore.mdl.mdl.RandomVariable
import org.ddmore.mdl.mdl.Expression
import org.ddmore.mdl.mdl.Mcl
import org.ddmore.mdl.mdl.MclObject
import org.ddmore.mdl.mdl.ModelObject
import java.io.FileReader
import java.io.BufferedReader
import java.util.ArrayList
import java.io.FileNotFoundException
import java.io.File
import org.ddmore.mdl.mdl.SymbolModification
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
		
	val TYPE_INT = "int";
	val TYPE_REAL = "real";

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
  		
		'''
		<?xml version="1.0" encoding="UTF-8"?>
		<PharmML 
			«print_PharmML_NameSpaces»
			writtenVersion="«writtenVersion»">
			<ct:Name>"«referenceResolver.fileName(m.eResource)»"</ct:Name>
			«print_mdef_IndependentVariables»
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
	<«tag» symbId="«symbol»"«IF printType» symbolType="«TYPE_REAL»"«ENDIF»>
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
							if (st.symbol != null){//!TODO: revise
								statements = statements + '''«st.symbol.print_mdef_ObservationModel»''';
							}
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

	//
	def print_VariabilityReference(RandomVariable s)'''
		«val level = referenceResolver.getAttribute(s.randomList.arguments, "level")»
		«IF level.length > 0»
			<ct:VariabilityReference>
				<ct:SymbRef symbIdRef="«level»"/>
			</ct:VariabilityReference>
		«ENDIF»
	'''

	//
	def print_mdef_ObservationModel(SymbolDeclaration s)'''
		«IF s.expression != null»
			«IF s.expression.expression != null»
				«var expr = s.expression.expression»
				«var randomVars = referenceResolver.getReferencesToRandomVars(expr)»
				«IF randomVars.size > 0»
					«FOR ss: randomVars»
						«val ref = ss.defineDistribution»
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
	
	/////////////////////
	// I.g Error Model
	/////////////////////
	//For named arguments - reorder and match declaration!	
	def print_mdef_ErrorModel(Expression expr)'''
    <ErrorModel>
    	«IF expr != null»
    		«mathPrinter.print_Assign(expr)»
    	«ENDIF»
    </ErrorModel>
	'''
		
	//+			
	def print_InitialCondition(SymbolDeclaration s)'''
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
	'''
	
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
	
	/////////////////////////////////////
	// General - print expression
	/////////////////////////////////////
	
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
			«mathPrinter.print_Assign(s.expression.expression)»
		«ENDIF»
	«ENDIF»
	'''

	def print_Assign(SymbolModification s)'''
	«IF s != null»
		«mathPrinter.print_ct_SymbolRef(s.identifier)»
		«val value = referenceResolver.getAttributeExpression(s.list.arguments, "value")»
		«IF value != null»
			«IF value.expression != null»
				«mathPrinter.print_Assign(value.expression)»
			«ENDIF»
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
									var use = referenceResolver.getAttribute(s.expression.list.arguments, "use");
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
		<ColumnRef xmlns="«xmlns_ds»" columnIdRef="«ref»"/>
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
			«mathPrinter.print_ct_Sequence("", "", "")»
		</Timepoints>
		<Continuous>
			<ct:SymbRef symbIdRef="«ref»"«IF blockID.length > 0» blkIdRef="«blockID»"«ENDIF»/>
		</Continuous>
	</Observations>
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
		var fileName = referenceResolver.getDataSource(mcl);
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
			row = row + mathPrinter.print_ct_Value(iterator.next);
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

	def print_BlockStatement(BlockStatement st, String tag, Boolean printType)'''
		«IF st.symbol != null»
			<«tag» symbId="«st.symbol.identifier»"«IF printType» symbolType="«TYPE_REAL»"«ENDIF»>
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
}