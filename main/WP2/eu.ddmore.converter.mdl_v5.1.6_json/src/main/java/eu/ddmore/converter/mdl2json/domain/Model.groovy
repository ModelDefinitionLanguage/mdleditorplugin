package eu.ddmore.converter.mdl2json.domain;

import org.apache.log4j.Logger
import org.ddmore.mdl.mdl.EstimationBlock
import org.ddmore.mdl.mdl.GroupVariablesBlock
import org.ddmore.mdl.mdl.ModelObject
import org.ddmore.mdl.mdl.ModelObjectBlock

import eu.ddmore.converter.mdl2json.interfaces.MDLAsJSON
import eu.ddmore.converter.mdl2json.interfaces.MDLPrintable
import eu.ddmore.converter.mdlprinting.MdlPrinter

public class Model extends Expando implements MDLPrintable, MDLAsJSON {

	public static final Logger logger = Logger.getLogger(Model.class)
	
	private static MdlPrinter mdlPrinter = MdlPrinter.getInstance()
	
	static final String IDENTIFIER = "mdlobj"
	
	static String MODEL_INPUT_VARIABLES = "MODEL_INPUT_VARIABLES"
	static String STRUCTURAL_PARAMETERS = "STRUCTURAL_PARAMETERS"
	static String VARIABILITY_PARAMETERS = "VARIABILITY_PARAMETERS"
	static String RANDOM_VARIABLE_DEFINITION = "RANDOM_VARIABLE_DEFINITION"
	static String INDIVIDUAL_VARIABLES = "INDIVIDUAL_VARIABLES"
	static String MODEL_PREDICTION = "MODEL_PREDICTION"
	static String OBSERVATION = "OBSERVATION"
	static String MODEL_OUTPUT_VARIABLES = "MODEL_OUTPUT_VARIABLES"
	static String GROUP_VARIABLES = "GROUP_VARIABLES"
	static String ESTIMATION = "ESTIMATION"
	static String SIMULATION = "SIMULATION"
	static String TARGET_CODE = "TARGET_CODE"
	
	public Model(ModelObject modelObject) {
	
		setProperty(IDENTIFIER_PROPNAME, IDENTIFIER)
		
		for (ModelObjectBlock b : modelObject.getBlocks()) {
			
			if (b.getInputVariablesBlock()) {
				// Note the inconsistency in that the getter is called InputVariables not ModelInputVariables
				setProperty(MODEL_INPUT_VARIABLES, VariablesList.buildFromSymbolDeclarations(b.getInputVariablesBlock().getVariables()))
			}
			if (b.getStructuralParametersBlock()) {
				setProperty(STRUCTURAL_PARAMETERS, VariablesList.buildFromSymbolDeclarations(b.getStructuralParametersBlock().getParameters()))
			}
			if (b.getVariabilityParametersBlock()) {
				setProperty(VARIABILITY_PARAMETERS, VariablesList.buildFromSymbolDeclarations(b.getVariabilityParametersBlock().getParameters()))
			}
			if (b.getRandomVariableDefinitionBlock()) {
				setProperty(RANDOM_VARIABLE_DEFINITION, RandomVariablesList.buildFromSymbolDeclarations(b.getRandomVariableDefinitionBlock().getVariables()))
			}
			if (b.getIndividualVariablesBlock()) {
				setProperty(INDIVIDUAL_VARIABLES, VariablesList.buildFromSymbolDeclarations(b.getIndividualVariablesBlock().getVariables()))
			}
			if (b.getModelPredictionBlock()) {
				setProperty(MODEL_PREDICTION, new ModelPrediction(b.getModelPredictionBlock()))
			}
			if (b.getObservationBlock()) {
				setProperty(OBSERVATION, RandomVariablesList.buildFromSymbolDeclarations(b.getObservationBlock().getVariables()))
			}
			if (b.getOutputVariablesBlock()) {
				// Note the inconsistency in that the getter is called OutputVariables not ModelOutputVariables
				setProperty(MODEL_OUTPUT_VARIABLES, VariablesList.buildFromSymbolNames(b.getOutputVariablesBlock().getVariables()))
			}
			if (b.getGroupVariablesBlock()) {
				setProperty(GROUP_VARIABLES, makeGroupVariables(b.getGroupVariablesBlock()))
			}
			if (b.getEstimationBlock()) {
				setProperty(ESTIMATION, makeEstimation(b.getEstimationBlock()))
			}
			if (b.getSimulationBlock()) {
				throw new UnsupportedOperationException("Simulation block not supported yet")
			} else if (b.getTargetBlock()) {
				throw new UnsupportedOperationException("Target Code block not supported yet")
			}
			
		}
	}

	/**
	 * Constructor from a JSON object
	 * 
	 * @param json The json object to construct the Model with
	 */
	public Model(Map json) {
		
		setProperty(IDENTIFIER_PROPNAME, IDENTIFIER)
		
		if (json[STRUCTURAL_PARAMETERS]) {
			setProperty(STRUCTURAL_PARAMETERS, VariablesList.buildFromJSON(json[STRUCTURAL_PARAMETERS]))
		}
		if (json[VARIABILITY_PARAMETERS]) {
			setProperty(VARIABILITY_PARAMETERS, VariablesList.buildFromJSON(json[VARIABILITY_PARAMETERS]))
		}
		if (json[INDIVIDUAL_VARIABLES]) {
			setProperty(INDIVIDUAL_VARIABLES, VariablesList.buildFromJSON(json[INDIVIDUAL_VARIABLES]))
		}
		if (json[RANDOM_VARIABLE_DEFINITION]) {
			setProperty(RANDOM_VARIABLE_DEFINITION, RandomVariablesList.buildFromJSON(json[RANDOM_VARIABLE_DEFINITION]))
		}
		if (json[MODEL_OUTPUT_VARIABLES]) {
			setProperty(MODEL_OUTPUT_VARIABLES, VariablesList.buildFromJSON(json[MODEL_OUTPUT_VARIABLES]))
		}
		if (json[MODEL_INPUT_VARIABLES]) {
			setProperty(MODEL_INPUT_VARIABLES, VariablesList.buildFromJSON(json[MODEL_INPUT_VARIABLES]))
		}
		if (json[OBSERVATION]) {
			setProperty(OBSERVATION, RandomVariablesList.buildFromJSON(json[OBSERVATION]))
		}
		if (json[MODEL_PREDICTION]) {
			setProperty(MODEL_PREDICTION, new ModelPrediction(json[MODEL_PREDICTION]))
		}
		if (json[GROUP_VARIABLES]) {
			throw new UnsupportedOperationException("Group Variables block not supported yet")
		}
		if (json[ESTIMATION]) {
			throw new UnsupportedOperationException("Estimation block not supported yet")
		}
		if (json[SIMULATION]) {
			throw new UnsupportedOperationException("Simulation block not supported yet")
		}
		if (json[TARGET_CODE]) {
			throw new UnsupportedOperationException("Target Code block not supported yet")
		}
		
	}

	// TODO: This needs to be revisited
	private makeGroupVariables(GroupVariablesBlock groupVariables) {
		throw new UnsupportedOperationException("Group Variables block not supported yet")
		
//		List statements = []
//		groupVariables.getStatements().each { GroupVariablesBlockStatement statement ->
//			def mixtureBlock = statement.getMixtureBlock()
//			def stmt = statement.getStatement()
//			if (mixtureBlock) {
//				mixtureBlock.getStatements().each {
//					// TODO: "it" is a BlockStatement; needs to be pretty-printed correctly
//					statements.add(mdlPrinter.print(it))
//				}
//			} else if (stmt) {
//				// TODO: "stmt" is a BlockStatement; needs to be pretty-printed correctly
//				statements.add(mdlPrinter.print(stmt))
//			}
//		}
//		statements.join()
	}
	
	// TODO: This needs to be revisited
	private makeEstimation(EstimationBlock estimationBlock) {
		throw new UnsupportedOperationException("Estimation block not supported yet")
		
//		StringBuffer statements = new StringBuffer()
//		estimationBlock.getStatements().each { BlockStatement statement ->
//			statements.append(mdlPrinter.print(statement))
//		}
//		statements.toString()
	}
	
	public String toMDL() {
		Properties p = getProperties()
		
		StringBuffer mdl = new StringBuffer()
		def normalProperties = getProperties().minus([(IDENTIFIER_PROPNAME):(IDENTIFIER)])
		normalProperties.each { String blockName, MDLPrintable obj ->
			mdl.append("\n${IDT}${blockName} {\n${IDT*2}${obj.toMDL()}\n${IDT}}\n")
		}
		return """${IDENTIFIER} {
${mdl.toString()}
}
"""
	}
	
}
