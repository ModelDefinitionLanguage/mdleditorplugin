package eu.ddmore.converter.mdl2json.domain;

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
import eu.ddmore.converter.mdlprinting.MdlPrinter;

public class Model extends Expando {

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
	
		setProperty("identifier", IDENTIFIER)
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

	private Map makeModelInputVariables(InputVariablesBlock inputVariables) {
		MDLUtils.makeSymbolMap (inputVariables.getVariables() )
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
		StringBuffer statements = new StringBuffer()
		groupVariables.getStatements().each { GroupVariablesBlockStatement statement ->
			if(statement.getMixtureBlock()) {
				logger.debug(statement.getMixtureBlock())
				statement.getMixtureBlock().getStatements().each { statements.append(mdlPrinter.print(it)) }
			} else if (statement.getStatement()) {
				logger.debug(statement.getStatement())
				statements.append(mdlPrinter.print(statement.getStatement()))
			}
		}
		statements.toString()
	}
	
	private makeIndividualVariables(IndividualVariablesBlock indVariables) {
		StringBuffer statements = new StringBuffer()
		indVariables.getStatements().each { BlockStatement statement ->
			statements.append(mdlPrinter.print(statement))
		}
		statements.toString()
	}
	
	private makeModelPredictionBlock(ModelPredictionBlock mpb) {
		StringBuffer statements = new StringBuffer()
		mpb.getStatements()each { ModelPredictionBlockStatement statement ->
			if(statement.getStatement()!=null) {
				 mdlPrinter.print(statement.getStatement())
			}
			else if(statement.getOdeBlock()!=null) {
				statements.append("ODE{\n")
				statement.getOdeBlock().getStatements().each { statements.append(mdlPrinter.print(it)) }
				statements.append("}\n")
			} else if(statement.getLibraryBlock()!=null) {
				statements.append("LIBRARY{\n")
				statement.getLibraryBlock().getStatements().each { FunctionCallStatement fcs ->
					statements.append("${fcs.getSymbolName().getName()}=${mdlPrinter.print(fcs.getExpression())}")
				}
				statements.append("\n}\n")
			}
		}
		statements.toString()
	}
	
	private makeRandomBlock(RandomVariableDefinitionBlock randomVariables) {
		makeSymbols(randomVariables.getVariables())
	}
	
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
	
	public Model(Object json) {
		setProperty("identifier", IDENTIFIER)
	}
	
	public String toMDL() {
		return """${IDENTIFIER} {
			}
		 
		"""
	}
}
