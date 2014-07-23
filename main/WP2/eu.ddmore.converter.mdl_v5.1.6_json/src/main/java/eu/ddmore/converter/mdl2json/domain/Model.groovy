package eu.ddmore.converter.mdl2json.domain;

import java.util.Map;

import org.apache.log4j.Logger
import org.ddmore.mdl.mdl.BlockStatement
import org.ddmore.mdl.mdl.FunctionCallStatement
import org.ddmore.mdl.mdl.GroupVariablesBlock
import org.ddmore.mdl.mdl.GroupVariablesBlockStatement
import org.ddmore.mdl.mdl.IndividualVariablesBlock
import org.ddmore.mdl.mdl.InputVariablesBlock
import org.ddmore.mdl.mdl.ModelObject
import org.ddmore.mdl.mdl.ModelObjectBlock
import org.ddmore.mdl.mdl.ModelPredictionBlock
import org.ddmore.mdl.mdl.ModelPredictionBlockStatement
import org.ddmore.mdl.mdl.ObservationBlock
import org.ddmore.mdl.mdl.OutputVariablesBlock
import org.ddmore.mdl.mdl.RandomVariableDefinitionBlock
import org.ddmore.mdl.mdl.SymbolDeclaration
import org.ddmore.mdl.mdl.SymbolName
import org.ddmore.mdl.mdl.VariabilityParametersBlock

import eu.ddmore.converter.mdl2json.utils.MDLUtils
import eu.ddmore.converter.mdlprinting.MdlPrinter

public class Model extends Expando implements MDLPrintable, MDLAsJSON {

	public static final Logger logger = Logger.getLogger(Model.class)
	private static MdlPrinter mdlPrinter = MdlPrinter.getInstance()
	
	static final String IDENTIFIER = "mdlobj"
	public static String STRUCTURAL_PARAMETERS = "STRUCTURAL_PARAMETERS"
	public static String MODEL_INPUT_VARIABLES = "MODEL_INPUT_VARIABLES"
	public static String VARIABILITY_PARAMETERS = "VARIABILITY_PARAMETERS"
	public static String GROUP_VARIABLES = "GROUP_VARIABLES"
	public static String RANDOM_VARIABLE_DEFINITION = "RANDOM_VARIABLE_DEFINITION"
	public static String INDIVIDUAL_VARIABLES = "INDIVIDUAL_VARIABLES"
	public static String MODEL_PREDICTION = "MODEL_PREDICTION"
	public static String OBSERVATION = "OBSERVATION"
	public static String MODEL_OUTPUT_VARIABLES = "MODEL_OUTPUT_VARIABLES"
	
	public Model(ModelObject modelObject) {
	
		setProperty(IDENTIFIER_PROPNAME, IDENTIFIER)
		for(ModelObjectBlock modelObjectBlock : modelObject.getBlocks()) {
			if(modelObjectBlock.getInputVariablesBlock()) {
				// Model Input Variables - note, getter is called InputVariables
				setProperty(MODEL_INPUT_VARIABLES,  makeModelInputVariables(modelObjectBlock.getInputVariablesBlock()) )
			}
			else if(modelObjectBlock.getStructuralParametersBlock()) {
				// Structural Parameters
				setProperty(STRUCTURAL_PARAMETERS, makeStructuralParameters(modelObjectBlock))
			} else if(modelObjectBlock.getVariabilityParametersBlock()) {
				// Variability Parameters Block
				setProperty(VARIABILITY_PARAMETERS, makeVariabilityParameters(modelObjectBlock.getVariabilityParametersBlock()))
			} else if(modelObjectBlock.getEstimationBlock()) {
				// Estimation block
			} else if(modelObjectBlock.getGroupVariablesBlock()) {
				// Group Variables
				setProperty(GROUP_VARIABLES, makeGroupVariables(modelObjectBlock.getGroupVariablesBlock()))
			} else if(modelObjectBlock.getRandomVariableDefinitionBlock()) {
				// Random Variable Definition
				setProperty(RANDOM_VARIABLE_DEFINITION, makeRandomBlock(modelObjectBlock.getRandomVariableDefinitionBlock()))
			} else if(modelObjectBlock.getIndividualVariablesBlock()) {
				// Individual Variables
				setProperty(INDIVIDUAL_VARIABLES, makeIndividualVariables(modelObjectBlock.getIndividualVariablesBlock()))
			} else if(modelObjectBlock.getModelPredictionBlock()) {
				// Model Predictions
				setProperty(MODEL_PREDICTION, makeModelPredictionBlock(modelObjectBlock.getModelPredictionBlock()))
			} else if(modelObjectBlock.getObservationBlock()) {
				// Observations
				setProperty(OBSERVATION, makeObservationBlock(modelObjectBlock.getObservationBlock()))
			} else if(modelObjectBlock.getOutputVariablesBlock()) {
				// Output variables
				setProperty(MODEL_OUTPUT_VARIABLES, makeModelOutputVariables(modelObjectBlock.getOutputVariablesBlock()))
			} else if(modelObjectBlock.getSimulationBlock()) {
				// Simulation block
				throw new UnsupportedOperationException("Do not support simulation block yet")
			} else if(modelObjectBlock.getTargetBlock()) {
				// Target block 
				throw new UnsupportedOperationException("Do not support target block yet")
			}
		}			
	}

	/**
	 * Constructor from a JSON object
	 * 
	 * @param json The json object to construct the Model with
	 */
	public Model(Object json) {
		getProperties().putAll(json)
	}
	
	private Map makeModelInputVariables(InputVariablesBlock inputVariables) {
		MDLUtils.makeSymbolMap(inputVariables.getVariables())
	}

	private List makeModelOutputVariables(OutputVariablesBlock outputVariables) {
		outputVariables.getVariables().collect { SymbolName sn ->
			sn.getName()
		}
	}

	private makeStructuralParameters(ModelObjectBlock modelObjectBlock) {
		List symbolDeclarations = modelObjectBlock.getStructuralParametersBlock().getParameters()
		makeSymbols(symbolDeclarations)
	}

	private makeVariabilityParameters(VariabilityParametersBlock variabilityParameters) {
		List symbolDeclarations = variabilityParameters.getParameters()
		makeSymbols(symbolDeclarations)
	}

	private makeGroupVariables(GroupVariablesBlock groupVariables) {
		List statements = []
		groupVariables.getStatements().each { GroupVariablesBlockStatement statement ->
            def mixtureBlock = statement.getMixtureBlock()
            def stmt = statement.getStatement()
			if (mixtureBlock) {
				mixtureBlock.getStatements().each { statements.add(mdlPrinter.print(it)) }
			} else if (stmt) {
				statements.add(mdlPrinter.print(stmt))
			}
		}
		statements.join()
	}
	
	private makeIndividualVariables(IndividualVariablesBlock indVariables) {
		List statements = []
		indVariables.getStatements().each { BlockStatement statement ->
			statements.add(mdlPrinter.print(statement))
		}
		statements.join("${IDT*2}")
	}

    private makeModelPredictionBlock(ModelPredictionBlock mpb) {
        LinkedHashMap modPredBlock = [:]
        List odeStatements = []
        List libraryStatements = []
		List statements = []
        
		mpb.getStatements().each { ModelPredictionBlockStatement statement ->
            
			if (statement.getStatement() != null) {
                statements.add("${IDT*2}" + mdlPrinter.print(statement.getStatement()))
			}
			else if (statement.getOdeBlock() != null) {
				statement.getOdeBlock().getStatements().each {
                    odeStatements.add("${IDT*3}" + mdlPrinter.print(it))
                }
			} else if (statement.getLibraryBlock() != null) {
				statement.getLibraryBlock().getStatements().each { FunctionCallStatement fcs ->
					libraryStatements.add("${IDT*3}${fcs.getSymbolName().getName()}=${mdlPrinter.print(fcs.getExpression())}\n")
				}
			}
		}
        
        if (!odeStatements.isEmpty()) {
            modPredBlock.put("ODE", odeStatements.join())
        }

		if (!libraryStatements.isEmpty()) {
		    modPredBlock.put("LIBRARY", libraryStatements.join())
		}
        
		modPredBlock.put("content", statements.join())
        
        modPredBlock
	}
	
	private makeRandomBlock(RandomVariableDefinitionBlock randomVariables) {
		makeSymbols(randomVariables.getVariables())
	}
	
	/**
	 * MDL printing routines
	 */
	
	/**
	 * Make the observation block
	 */
	private makeObservationBlock(ObservationBlock observationBlock) {
		StringBuffer statements = new StringBuffer()
		observationBlock.getStatements().each { BlockStatement statement ->
			statements.append(mdlPrinter.print(statement))
		}
		statements.toString()
	}
		
	private List makeSymbols(symbolDeclarations) {
		List symbols = []
		for( SymbolDeclaration sd : symbolDeclarations ) {
			if(sd.getExpression()!=null) {
				symbols.add(MDLUtils.makeSymbol(sd))
			} else if(sd.getRandomList() != null ) {
				symbols.add(MDLUtils.makeSymbol(sd))
			} else {
				symbols.add(sd.getSymbolName().getName())
			}
		}
		symbols
	}
	
	/**
	 * Make model input variables block
	 */
	public String makeModelInputVariablesString(Map variables) {
		List varStrings = []
		variables.each {name, attributes ->
			// Sorry!
			// v is a map of attributes - iterate over them and turn it into the format "x = y"
			// then join them together with ","
			String vstr = "${name}=list(${attributes.collect{ key,value->"${key}=${value}"}.join(",")})"
			varStrings.add(vstr)
		}
		varStrings.join("\n${IDT*2}")
	}
	
	public String makeRandomVariableMDL(List randomVariables) {
		List varStrings = []
		randomVariables.each {variable ->
			// A variable is a map
			// Key is variable name
			// Value is the variable attributes
			// Iterate over them and turn it into the format "x ~ y"
			// Join attributes together with ","
			variable.each { variableName, attributes ->
				String vstr = "${variableName} ~ (${attributes.collect{ key,value->"${key}=${value}"}.join(",")})"
				varStrings.add(vstr)
			}
		}
		varStrings.join("\n${IDT*2}")
	}
	
	public String makeModelPredictionMDLBlock(Map content) {
		StringBuffer buff = new StringBuffer()
		
		if (content.containsKey("ODE")) {
			buff.append("ODE {\n").append(content.get("ODE")).append("${IDT*2}}\n${IDT*2}")
		}
		if (content.containsKey("LIBRARY")) {
			buff.append("LIBRARY {\n").append(content.get("LIBRARY")).append("${IDT*2}}\n${IDT*2}")
		}
		if (content.containsKey("content")) {
			buff.append("\n").append(content.get("content"));
		}
		buff.toString()
	}
	
	public String toMDL() {
		StringBuffer mdl = new StringBuffer()
		
		// Possibly may need to do something about ordering here.
		
		getProperties().minus(["identifier":"mdlobj"]).each { block, content ->
			mdl.append("\n${IDT}${block} {\n${IDT*2}")
			switch(block) {
				case "MODEL_PREDICTION":
					mdl.append(makeModelPredictionMDLBlock(content));
					break;
				case "OBSERVATION":
					if (content instanceof List) {
						mdl.append(content.join("\n${IDT*2}"))
					} else {
						mdl.append(content)
					}
					break;
				case "MODEL_INPUT_VARIABLES":
					mdl.append(makeModelInputVariablesString(content)).append("\n")
					break;
				case "MODEL_OUTPUT_VARIABLES":
				case "STRUCTURAL_PARAMETERS":
				case "VARIABILITY_PARAMETERS":
					mdl.append(content.join("\n${IDT*2}")).append("\n")
					break;
				case "GROUP_VARIABLES":
				case "INDIVIDUAL_VARIABLES":
					mdl.append(content)
					break;
				case "RANDOM_VARIABLE_DEFINITION":
					mdl.append(makeRandomVariableMDL(content)).append("\n")
					break;
				default:
					break;
			}
			mdl.append("${IDT}}")
		}

		return """${IDENTIFIER} {${mdl.toString()}
}
"""
	}
}
