package eu.ddmore.converter.mdl2json.domain;

import org.apache.log4j.Logger
import org.ddmore.mdl.mdl.Argument
import org.ddmore.mdl.mdl.ModelObject
import org.ddmore.mdl.mdl.ModelObjectBlock
import org.eclipse.emf.common.util.EList

import eu.ddmore.converter.mdl2json.interfaces.MDLAsJSON
import eu.ddmore.converter.mdl2json.interfaces.MDLPrintable
import eu.ddmore.converter.mdl2json.interfaces.TopLevelBlock
import eu.ddmore.converter.mdl2json.utils.XtextWrapper
import eu.ddmore.converter.mdlprinting.MdlPrinter

public class Model extends Expando implements MDLPrintable, MDLAsJSON, TopLevelBlock {

	public static final Logger logger = Logger.getLogger(Model.class)
	
	private static MdlPrinter mdlPrinter = MdlPrinter.getInstance()
	
	static final String IDENTIFIER = "mdlobj"
	
    static String COVARIATES = "COVARIATES"
    static String VARIABILITY_LEVELS = "VARIABILITY_LEVELS"
	static String STRUCTURAL_PARAMETERS = "STRUCTURAL_PARAMETERS"
	static String VARIABILITY_PARAMETERS = "VARIABILITY_PARAMETERS"
	static String RANDOM_VARIABLE_DEFINITION = "RANDOM_VARIABLE_DEFINITION"
	static String INDIVIDUAL_VARIABLES = "INDIVIDUAL_VARIABLES"
	static String MODEL_PREDICTION = "MODEL_PREDICTION"
	static String OBSERVATION = "OBSERVATION"
	static String GROUP_VARIABLES = "GROUP_VARIABLES"
	
	public Model(ModelObject modelObject) {
	
		setProperty(IDENTIFIER_PROPNAME, IDENTIFIER)
		
		for (ModelObjectBlock block : modelObject.getBlocks()) {

            if (block.getCovariateBlock()) {
                setProperty(COVARIATES, VariablesList.buildFromSymbolDeclarations(block.getCovariateBlock().getVariables()))
            }
            if (block.getVariabilityBlock()) {
                setProperty(VARIABILITY_LEVELS, VariablesList.buildFromSymbolDeclarations(block.getVariabilityBlock().getVariables()))
            }
			if (block.getStructuralParametersBlock()) {
				setProperty(STRUCTURAL_PARAMETERS, VariablesList.buildFromSymbolDeclarations(block.getStructuralParametersBlock().getParameters()))
			}
			if (block.getVariabilityParametersBlock()) {
				setProperty(VARIABILITY_PARAMETERS, VariablesList.buildFromSymbolDeclarations(block.getVariabilityParametersBlock().getParameters()))
			}
			if (block.getRandomVariableDefinitionBlock()) {
                final EList<Argument> subBlockArgs = block.getRandomVariableDefinitionBlock().getArguments().getNamedArguments().getArguments()
                if (subBlockArgs.size() > 1) {
                    throw new UnsupportedOperationException("Multiple parameterisation of the RANDOM_VARIABLE_DEFINITION sub-block name is not supported")
                }
                final String subBlockName = RANDOM_VARIABLE_DEFINITION \
                    + "(" + subBlockArgs.get(0).getArgumentName().getName() + "=" + XtextWrapper.unwrap(subBlockArgs.get(0).getExpression()) + ")"
				setProperty(subBlockName, VariablesList.buildFromSymbolDeclarations(block.getRandomVariableDefinitionBlock().getVariables()))
			}
			if (block.getIndividualVariablesBlock()) {
				setProperty(INDIVIDUAL_VARIABLES, VariablesList.buildFromSymbolDeclarations(block.getIndividualVariablesBlock().getVariables()))
			}
			if (block.getModelPredictionBlock()) {
				setProperty(MODEL_PREDICTION, new ModelPredictionList(block.getModelPredictionBlock()))
			}
			if (block.getObservationBlock()) {
				setProperty(OBSERVATION, VariablesList.buildFromSymbolDeclarations(block.getObservationBlock().getVariables()))
			}
			if (block.getGroupVariablesBlock()) {
				setProperty(GROUP_VARIABLES, VariablesList.buildFromGroupVariablesBlock(block.getGroupVariablesBlock()))
			}
            if (block.getEstimationBlock()) {
                throw new UnsupportedOperationException("Estimation block within Model Object not supported")
            }
            if (block.getIndependentVariableBlock()) {
                throw new UnsupportedOperationException("Independent Variable block within Model Object not supported")
            }
            if (block.getOutputVariablesBlock()) {
                throw new UnsupportedOperationException("Output Variables block within Model Object not supported")
            }
            if (block.getSimulationBlock()) {
                throw new UnsupportedOperationException("Simulation block within Model Object not supported")
            }
            if (block.getTargetBlock()) {
                throw new UnsupportedOperationException("Target Code block within Model Object not supported")
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
        
        if (json[COVARIATES]) {
            setProperty(COVARIATES, VariablesList.buildFromJSON(json[COVARIATES]))
        }
        if (json[VARIABILITY_LEVELS]) {
            setProperty(VARIABILITY_LEVELS, VariablesList.buildFromJSON(json[VARIABILITY_LEVELS]))
        }
		if (json[STRUCTURAL_PARAMETERS]) {
			setProperty(STRUCTURAL_PARAMETERS, VariablesList.buildFromJSON(json[STRUCTURAL_PARAMETERS]))
		}
		if (json[VARIABILITY_PARAMETERS]) {
			setProperty(VARIABILITY_PARAMETERS, VariablesList.buildFromJSON(json[VARIABILITY_PARAMETERS]))
		}
		if (json[INDIVIDUAL_VARIABLES]) {
			setProperty(INDIVIDUAL_VARIABLES, VariablesList.buildFromJSON(json[INDIVIDUAL_VARIABLES]))
		}
		if (json[OBSERVATION]) {
			setProperty(OBSERVATION, VariablesList.buildFromJSON(json[OBSERVATION]))
		}
		if (json[MODEL_PREDICTION]) {
			setProperty(MODEL_PREDICTION, new ModelPredictionList(json[MODEL_PREDICTION]))
		}
		if (json[GROUP_VARIABLES]) {
            setProperty(GROUP_VARIABLES, VariablesList.buildFromJSON(json[GROUP_VARIABLES]))
		}
        // RANDOM_VARIABLE_DEFINITION block has to be treated specially as it appears multiple times
        // with (level=ID), (level=DV) etc. appended to the block name to distinguish between them
        json.entrySet().takeWhile{ mapEntry -> mapEntry.getKey().startsWith(RANDOM_VARIABLE_DEFINITION) }.each { mapEntry ->
            setProperty(mapEntry.getKey(), VariablesList.buildFromJSON(mapEntry.getValue()))
        }
		
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
	
	@Override
	public int getPrintedOrder() {
		return 3;
	}
	
}
