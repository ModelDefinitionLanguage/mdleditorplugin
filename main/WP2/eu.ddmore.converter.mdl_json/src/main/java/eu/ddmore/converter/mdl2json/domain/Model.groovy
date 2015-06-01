/*******************************************************************************
 * Copyright (C) 2014-2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json.domain;

import org.ddmore.mdl.mdl.Argument
import org.ddmore.mdl.mdl.ModelObject
import org.ddmore.mdl.mdl.ModelObjectBlock
import org.eclipse.emf.common.util.EList

import eu.ddmore.converter.mdl2json.interfaces.MDLAsJSON
import eu.ddmore.converter.mdl2json.interfaces.MDLPrintable
import eu.ddmore.converter.mdl2json.interfaces.TopLevelBlock
import eu.ddmore.converter.mdl2json.utils.XtextWrapper

public class Model extends Expando implements MDLPrintable, MDLAsJSON, TopLevelBlock {

    public static final TopLevelBlock.Identifier IDENTIFIER = TopLevelBlock.Identifier.mdlobj

    public static final String IDV = "IDV"
    public static final String COVARIATES = "COVARIATES"
    public static final String VARIABILITY_LEVELS = "VARIABILITY_LEVELS"
    public static final String STRUCTURAL_PARAMETERS = "STRUCTURAL_PARAMETERS"
    public static final String VARIABILITY_PARAMETERS = "VARIABILITY_PARAMETERS"
    public static final String RANDOM_VARIABLE_DEFINITION = "RANDOM_VARIABLE_DEFINITION"
    public static final String INDIVIDUAL_VARIABLES = "INDIVIDUAL_VARIABLES"
    public static final String MODEL_PREDICTION = "MODEL_PREDICTION"
    public static final String OBSERVATION = "OBSERVATION"
    public static final String GROUP_VARIABLES = "GROUP_VARIABLES"
    public static final String MODEL_OUTPUT_VARIABLES = "MODEL_OUTPUT_VARIABLES"

    public Model(ModelObject modelObject) {

        setProperty(IDENTIFIER_PROPNAME, IDENTIFIER)
        
        // Each separate level=XX RANDOM_VARIABLE_DEFINITION block encountered will get its
        // Variables (with level attribute added to each) added to this initially empty VariablesList
        setProperty(RANDOM_VARIABLE_DEFINITION, VariablesList.createEmpty())

        for (ModelObjectBlock block : modelObject.getBlocks()) {

            if (block.getIndependentVariableBlock()) {
                setProperty(IDV, VariablesList.buildFromSymbolDeclarations(block.getIndependentVariableBlock().getVariables()))
            }
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
                
                final VariablesList varsList = VariablesList.buildFromSymbolDeclarations(block.getRandomVariableDefinitionBlock().getVariables())
                
                // Add the attribute on the block definition, to each of the variables in the list
                varsList.collect { Variable v ->
                    v.setProperty(subBlockArgs.get(0).getArgumentName().getName(), XtextWrapper.unwrap(subBlockArgs.get(0).getExpression()))
                }
                
                getProperty(RANDOM_VARIABLE_DEFINITION).addAll(varsList)
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
            if (block.getOutputVariablesBlock()) {
                setProperty(MODEL_OUTPUT_VARIABLES, VariablesList.buildFromSymbolNames(block.getOutputVariablesBlock().getVariables()))
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

        if (json[IDV]) {
            setProperty(IDV, VariablesList.buildFromJSON(json[IDV]))
        }
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
        if (json[MODEL_PREDICTION]) {
            setProperty(MODEL_PREDICTION, new ModelPredictionList(json[MODEL_PREDICTION]))
        }
        if (json[OBSERVATION]) {
            setProperty(OBSERVATION, VariablesList.buildFromJSON(json[OBSERVATION]))
        }
        if (json[GROUP_VARIABLES]) {
            setProperty(GROUP_VARIABLES, VariablesList.buildFromJSON(json[GROUP_VARIABLES]))
        }
        if (json[MODEL_OUTPUT_VARIABLES]) {
            setProperty(MODEL_OUTPUT_VARIABLES, VariablesList.buildFromJSON(json[MODEL_OUTPUT_VARIABLES]))
        }
        if (json[RANDOM_VARIABLE_DEFINITION]) {
            setProperty(RANDOM_VARIABLE_DEFINITION, VariablesList.buildFromJSON(json[RANDOM_VARIABLE_DEFINITION]))
        }
        
    }
    
    /**
     * {@inheritDoc}
     */
    public TopLevelBlock.Identifier getIdentifier() {
        return IDENTIFIER
    }

    /**
     * {@inheritDoc}
     */
    public String toMDL() {
        Properties p = getProperties()

        StringBuffer mdl = new StringBuffer()
        def normalProperties = getProperties().minus([(IDENTIFIER_PROPNAME):(IDENTIFIER)])
        normalProperties.each { String blockName, MDLPrintable obj ->
            if (blockName.startsWith(RANDOM_VARIABLE_DEFINITION + "(")) {
                // Obsolete since we now consolidate into a single RANDOM_VARIABLE_DEFINITION block in the JSON
            } else if (blockName == RANDOM_VARIABLE_DEFINITION) {
                // Segregate into separate RANDOM_VARIABLE_DEFINITION blocks with level=XX appended to the block names
                final VariablesList allVarsList = obj
                allVarsList.groupBy{ Variable v -> v.level}.collect { Map.Entry entry ->
                    final VariablesList varsListForLevel = VariablesList.buildFromListOfVariables(entry.getValue())
                    mdl.append("\n${IDT}${blockName}(level=${entry.getKey()}) {\n${IDT*2}${varsListForLevel.toMDL()}\n${IDT}}\n")
                }
            } else {
                mdl.append("\n${IDT}${blockName} {\n${IDT*2}${obj.toMDL()}\n${IDT}}\n")
            }
        }
        return """${IDENTIFIER} {
${mdl.toString()}
}
"""
    }

}