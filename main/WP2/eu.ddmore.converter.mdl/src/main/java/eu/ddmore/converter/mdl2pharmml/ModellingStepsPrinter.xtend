package eu.ddmore.converter.mdl2pharmml

import org.ddmore.mdl.mdl.SymbolDeclaration
import org.ddmore.mdl.mdl.Mcl
import org.ddmore.mdl.mdl.DataObject

class ModellingStepsPrinter extends DataSetPrinter{ 
	
	new(Mcl mcl, MathPrinter mathPrinter, ReferenceResolver resolver){
		super(mcl, mathPrinter, resolver);
	}	
	
	////////////////////////////////////////////////
	// III Modelling Steps
	////////////////////////////////////////////////
	def print_msteps_ModellingSteps()
	'''
	<ModellingSteps>
		«print_msteps_EstimationStep("estimStep1")»
		«print_msteps_SimulationStep("simulStep1")»
	</ModellingSteps>
	'''
	//«print_msteps_StepDependencies»

	//Choose an operation: estimate vs. simulate
	def executeOperation(){
		for (o: mcl.objects){
			if (o.taskObject != null){
				for (b: o.taskObject.blocks){
					if (b.functionDeclaration != null){
						for (bb: b.functionDeclaration.functionBody.blocks){
							if (bb.estimateBlock != null){
								//model = modelBlock, 
								//parameter = parameterBlock, 
								//data = dataBlock
							}
							if (bb.simulateBlock != null){
								
							}
						}
					}
				}
			}
		}
	}

	////////////////////////////////////////////////
	// III.a Estimation Step
	////////////////////////////////////////////////
	def print_msteps_EstimationStep(String stepId)'''
	<EstimationStep oid="«stepId»">
		«FOR o: mcl.objects»
			«IF o.dataObject != null»«o.dataObject.print_msteps_DataSet»«ENDIF»
		«ENDFOR»
	</EstimationStep>
	'''
		
	///////////////////////////////////////////////
	// III.b Simulation Step
	////////////////////////////////////////////////
	def print_msteps_SimulationStep(String stepId)'''
	<SimulationStep  oid="«stepId»">		
		«print_msteps_VariableAssignments»	
		«print_msteps_Observations»
	</SimulationStep>
	'''

	//TODO
	def print_msteps_VariableAssignments() { 
		//call print_msteps_VariableAssignment
	}	

	//TODO
	def print_msteps_Observations() { 
		//call print_msteps_Observation
	}
	
	def print_msteps_VariableAssignment(SymbolDeclaration s, String blockId)'''
	<ct:VariableAssignment>
	</ct:VariableAssignment>
	'''
	
	def print_msteps_Observation(String ref, String blockID)'''
	<Observations>
		<Timepoints>
			«print_ct_Sequence("", "", "")»
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
	</ObjectiveDataSet>
	'''
	
	//Assume that 
	// DATA_INPUT_VARIABLES contains attribute 'mapping' with the name of a corresponding variable
	// MODEL_INPUT_VARIABLES contains attribute 'use'
	def print_NONMEM_DataSet(DataObject obj){
		var res = "";
		for (b: obj.blocks){
			if (b.headerBlock != null){
				for (s: b.headerBlock.variables){
					var columnId = s.symbolName.name;
					var symbId = s.symbolName.name;
					if (s.expression != null){
						if (s.expression.list != null){
							val newName = s.expression.list.arguments.getAttribute("mapping");
							if (newName.length > 0)
								symbId = newName;
						}
					}
					val expectedVar = symbId.getModelInputVariable;
					var blkIdRef = symbId.getReferenceBlock;
					if (expectedVar != null){
						if (expectedVar.expression != null){
							if (expectedVar.expression.list != null){
								val use = expectedVar.expression.list.arguments.getAttribute("use");
								if (use.length > 0){
									if (use.equals(ENUM_USE_ID)){
										res = res + print_msteps_IndividualMapping(columnId, symbId, blkIdRef);
									} else {
										if (use.equals(ENUM_USE_COVARIATE)){
											res = res + print_msteps_CovariateMapping(columnId, symbId, blkIdRef);
										} else {
											res = res + print_msteps_VariableMapping(columnId, symbId, blkIdRef);
										}
									}									
								} else {
									res = res + print_msteps_VariableMapping(columnId);
								}
							}
						}						
					}
				}
			}
		}
		'''
		<NONMEMdataSet>
			«res»
		</NONMEMdataSet>
		'''
	}
	
	def print_msteps_IndividualMapping(String columnId, String symbId, String blkIdRef)'''
		<NMIndividualMapping>
			<ds:ColumnRef columnIdRef="«columnId»"/>
			<ds:SymbRef «IF blkIdRef.length > 0» blkIdRef="«blkIdRef»" «ENDIF»symbIdRef="«symbId»"/>
		</NMIndividualMapping>
	'''
	
	def print_msteps_CovariateMapping(String columnId, String symbId, String blkIdRef)'''
		<NMCovariateMapping>
			<ds:ColumnRef columnIdRef="«columnId»"/>
			<ds:SymbRef «IF blkIdRef.length > 0» blkIdRef="«blkIdRef»" «ENDIF»symbIdRef="«symbId»"/>
		</NMCovariateMapping>
	'''
	
	def print_msteps_VariableMapping(String columnId, String symbId, String blkIdRef)'''
		<NMVariableMapping>
			<ds:ColumnRef columnIdRef="«columnId»"/>
			<ds:SymbRef «IF blkIdRef.length > 0» blkIdRef="«blkIdRef»" «ENDIF»symbIdRef="«symbId»"/>
		</NMVariableMapping>
	'''
	
	def print_msteps_VariableMapping(String columnId)'''
		<NMVariableMapping>
			<ds:ColumnRef columnIdRef="«columnId»"/>
		</NMVariableMapping>
	'''
	
	//Return a corresponding model variable
	def getModelInputVariable(String name){
		for (o: mcl.objects){
			if (o.modelObject != null){
				for (b: o.modelObject.blocks){
					if (b.inputVariablesBlock != null){
						for (s: b.inputVariablesBlock.variables){
							if (s.symbolName.name.equals(name)){
								return s;
							}
						}
					}
				}
			}
		}
		return null;
	}
    
    //+ Print data set
	def print_msteps_DataSet(DataObject obj){
		var res = "";
		for (b: obj.blocks)	{
			if (b.fileBlock != null){
				for (s: b.fileBlock.statements){
					if (s.variable.symbolName.name.equals("data")){
						if (s.variable.expression != null){
							if (s.variable.expression.list != null){
								val source = s.variable.expression.list.arguments.getAttribute("source");
								val inputformat = s.variable.expression.list.arguments.getAttribute("inputformat");
								if (inputformat.equals(ENUM_FORMAT_NONMEM)){
									res  = res + obj.print_NONMEM_DataSet;
								}
								if (source.length > 0){
									res = res + source.print_msteps_ExternalSource;
								}
							}
						}
					}
				}
			}
		}
		return res;
	}
	
	def print_msteps_ExternalSource(String source)'''				
		«val dotIndex = source.indexOf('.')»
		<ExternalSource url="file=///«source»" 
			format="«source.substring(dotIndex + 1)»"/>	
	'''	
}