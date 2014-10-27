package eu.ddmore.converter.mdl2json.domain;

import org.apache.log4j.Logger
import org.ddmore.mdl.mdl.BlockStatement
import org.ddmore.mdl.mdl.EstimationBlock
import org.ddmore.mdl.mdl.GroupVariablesBlock
import org.ddmore.mdl.mdl.GroupVariablesBlockStatement
import org.ddmore.mdl.mdl.IndividualVariablesBlock
import org.ddmore.mdl.mdl.ModelObject
import org.ddmore.mdl.mdl.ModelObjectBlock
import org.ddmore.mdl.mdl.ObservationBlock
import org.ddmore.mdl.mdl.SymbolDeclaration

import eu.ddmore.converter.mdl2json.interfaces.MDLAsJSON;
import eu.ddmore.converter.mdl2json.interfaces.MDLPrintable;
import eu.ddmore.converter.mdlprinting.MdlPrinter

public class Model extends Expando implements MDLPrintable, MDLAsJSON {

	public static final Logger logger = Logger.getLogger(Model.class)
	
	private static MdlPrinter mdlPrinter = MdlPrinter.getInstance()
	
	static final String IDENTIFIER = "mdlobj"
	public static String STRUCTURAL_PARAMETERS = "STRUCTURAL_PARAMETERS"
	public static String VARIABILITY_PARAMETERS = "VARIABILITY_PARAMETERS"
	public static String INDIVIDUAL_VARIABLES = "INDIVIDUAL_VARIABLES"
	public static String RANDOM_VARIABLE_DEFINITION = "RANDOM_VARIABLE_DEFINITION"
	public static String MODEL_OUTPUT_VARIABLES = "MODEL_OUTPUT_VARIABLES"
	public static String MODEL_INPUT_VARIABLES = "MODEL_INPUT_VARIABLES"
	public static String OBSERVATION = "OBSERVATION"
	public static String MODEL_PREDICTION = "MODEL_PREDICTION"
	public static String GROUP_VARIABLES = "GROUP_VARIABLES"
	public static String ESTIMATION = "ESTIMATION"
	
	public Model(ModelObject modelObject) {
	
		setProperty(IDENTIFIER_PROPNAME, IDENTIFIER)
		
		for (ModelObjectBlock b : modelObject.getBlocks()) {
			
			if (b.getStructuralParametersBlock()) {
				setProperty(STRUCTURAL_PARAMETERS, VariablesList.buildFromSymbolDeclarations(b.getStructuralParametersBlock().getParameters()))
			}
			if (b.getVariabilityParametersBlock()) {
				setProperty(VARIABILITY_PARAMETERS, VariablesList.buildFromSymbolDeclarations(b.getVariabilityParametersBlock().getParameters()))
			}
			if (b.getIndividualVariablesBlock()) {
				setProperty(INDIVIDUAL_VARIABLES, makeIndividualVariables(b.getIndividualVariablesBlock()))
			}
			if (b.getRandomVariableDefinitionBlock()) {
				setProperty(RANDOM_VARIABLE_DEFINITION, RandomVariablesList.buildFromSymbolDeclarations(b.getRandomVariableDefinitionBlock().getVariables()))
			}
			if (b.getOutputVariablesBlock()) {
				// Note the inconsistency in that the getter is called OutputVariables not ModelOutputVariables
				setProperty(MODEL_OUTPUT_VARIABLES, VariablesList.buildFromSymbolNames(b.getOutputVariablesBlock().getVariables()))
			}
			if (b.getInputVariablesBlock()) {
				// Note the inconsistency in that the getter is called InputVariables not ModelInputVariables
				setProperty(MODEL_INPUT_VARIABLES, VariablesList.buildFromSymbolDeclarations(b.getInputVariablesBlock().getVariables()))
			}
			if (b.getObservationBlock()) {
				setProperty(OBSERVATION, makeObservation(b.getObservationBlock()))
			}
			if (b.getModelPredictionBlock()) {
				setProperty(MODEL_PREDICTION, new ModelPrediction(b.getModelPredictionBlock()))
			}
			if (b.getGroupVariablesBlock()) {
				setProperty(GROUP_VARIABLES, makeGroupVariables(b.getGroupVariablesBlock()))
			}
			if (b.getEstimationBlock()) {
				setProperty(ESTIMATION, makeEstimation(b.getEstimationBlock()))
			}
			if (b.getSimulationBlock()) {
				throw new UnsupportedOperationException("Do not support simulation block yet")
			} else if (b.getTargetBlock()) {
				throw new UnsupportedOperationException("Do not support target block yet")
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
		
	}
	
	private VariablesList makeIndividualVariables(IndividualVariablesBlock indVariables) {
		List<SymbolDeclaration> symbolList = []
		indVariables.getStatements().each { BlockStatement stmt ->
			if (stmt.getSymbol()) {
				symbolList.add(stmt.getSymbol())
			}
		}
		VariablesList.buildFromSymbolDeclarations(symbolList)
	}
	
	private RandomVariablesList makeObservation(ObservationBlock observationBlock) {
		RandomVariablesList.buildFromSymbolDeclarations(
			observationBlock.getStatements().collect { BlockStatement stmt ->
				stmt.getSymbol()
			}
		)
	}

	// TODO: This needs to be revisited
	private makeGroupVariables(GroupVariablesBlock groupVariables) {
		List statements = []
		groupVariables.getStatements().each { GroupVariablesBlockStatement statement ->
			def mixtureBlock = statement.getMixtureBlock()
			def stmt = statement.getStatement()
			if (mixtureBlock) {
				mixtureBlock.getStatements().each {
					// TODO: "it" is a BlockStatement; needs to be pretty-printed correctly
					statements.add(mdlPrinter.print(it))
				}
			} else if (stmt) {
				// TODO: "stmt" is a BlockStatement; needs to be pretty-printed correctly
				statements.add(mdlPrinter.print(stmt))
			}
		}
		statements.join()
	}
	
	// TODO: This needs to be revisited
	private makeEstimation(EstimationBlock estimationBlock) {
		StringBuffer statements = new StringBuffer()
		estimationBlock.getStatements().each { BlockStatement statement ->
			statements.append(mdlPrinter.print(statement))
		}
		statements.toString()
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
""" // TODO: Target Block
	}
	
}
