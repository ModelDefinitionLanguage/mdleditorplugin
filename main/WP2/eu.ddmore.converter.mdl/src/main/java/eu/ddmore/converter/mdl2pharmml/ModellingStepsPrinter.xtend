package eu.ddmore.converter.mdl2pharmml

import org.ddmore.mdl.mdl.SymbolDeclaration
import org.ddmore.mdl.mdl.Mcl
import org.ddmore.mdl.mdl.ModelObject
import java.util.ArrayList

class ModellingStepsPrinter extends DataSetPrinter{ 
	
	new(Mcl mcl, MathPrinter mathPrinter){
		super(mcl, mathPrinter);
	}	
	
	////////////////////////////////////////////////
	// III Modelling Steps
	////////////////////////////////////////////////
	def print_msteps_ModellingSteps()
	'''
	<ModellingSteps>
		«print_msteps_EstimationStep("estimStep1")»
		«print_msteps_SimulationStep("simulStep1")»
		«print_msteps_StepDependencies»
	</ModellingSteps>
	'''

	////////////////////////////////////////////////
	// III.a Estimation Step
	////////////////////////////////////////////////
	def print_msteps_EstimationStep(String stepId)'''
	<EstimationStep oid="«stepId»">
		«print_msteps_ObjectiveDataSet»
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
						names.add(s.identifier);
						types.add(mathPrinter.TYPE_REAL);
					} 
				}
			}
		}
		var fileName = getDataSource(mcl);
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
	
}