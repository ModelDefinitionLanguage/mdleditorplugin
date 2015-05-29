/*******************************************************************************
 * Copyright (C) 2014-2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json.domain

import java.util.Map

import com.google.common.base.Preconditions

import org.ddmore.mdl.mdl.PkMacroStatement;
import org.ddmore.mdl.mdl.SymbolDeclaration;
import org.ddmore.mdl.mdl.SymbolName
import org.eclipse.emf.common.util.EList

import eu.ddmore.converter.mdl2json.interfaces.MDLPrintable
import eu.ddmore.converter.mdl2json.domain.VariablesList
import eu.ddmore.converter.mdl2json.utils.XtextWrapper


public class ModelPredictionItem extends Variable implements MDLPrintable {
    
    /**
     * This character is used as a prefix to distinguish sub-blocks from 'normal' variables;
     * the text following this prefix is the name of the sub-block.
     */
    private static final String SUBBLOCK_PREFIX = "."
    static final List<String> SUBBLOCK_NAMES = ["DEQ", "COMPARTMENT"] // Used to be PKMACRO too; reinstate this if "PKMACRO" is re-introduced into the MDL syntax
    
    private String identifier
        
    /**
     * Private constructor to force instantiation via the factory methods
     * (at least it would if Groovy respected visibility keywords...).
     * @param sd - {@link org.ddmore.mdl.mdl.SymbolDeclaration}
     */
    private ModelPredictionItem(final SymbolDeclaration sd) {
        super(sd)
    }
    
    /**
     * Private constructor to force instantiation via the factory methods
     * (at least it would if Groovy respected visibility keywords...).
     * @param identifier - string identifier
     * @param variablesList - {@link VariablesList}
     */
    private ModelPredictionItem(final String identifier, final VariablesList variablesList) {
        super([:])
        Preconditions.checkArgument(identifier.startsWith(SUBBLOCK_PREFIX), "Instantiating a ModelPredictionItem other than via a SymbolDeclaration, requires that the identifier start with a dot to avoid clashes with variable names")
        setProperty(identifier, variablesList)
    }
    
    static ModelPredictionItem buildFromSymbolDeclaration(final SymbolDeclaration sd) {
        new ModelPredictionItem(sd)
    }
    
    static ModelPredictionItem buildFromSymbolDeclarations(final String identifier, final EList<SymbolDeclaration> symbolDeclarations) {
        new ModelPredictionItem(SUBBLOCK_PREFIX + identifier, VariablesList.buildFromSymbolDeclarations(symbolDeclarations))
    }
    
    static ModelPredictionItem buildFromPkMacroStatements(final String identifier, final EList<PkMacroStatement> pkMacroStatements) {
        new ModelPredictionItem(SUBBLOCK_PREFIX + identifier, VariablesList.buildFromPkMacrosStatements(pkMacroStatements))
    }
    
    public ModelPredictionItem(final Map json) {
        super(json)
    }

    /**
     * {@inheritDoc}
     */
    public String toMDL() {
        for (final String subBlockName : SUBBLOCK_NAMES) {
            if (getProperty(SUBBLOCK_PREFIX + subBlockName)) {
                final VariablesList varsList = VariablesList.buildFromJSON(getProperty(SUBBLOCK_PREFIX + subBlockName))
                return "${subBlockName} {\n${IDT*3}" + varsList.toMDL(3) + "\n${IDT*2}}"
            }
        }
        // If reached here then this ModelPredictionItem is not representing a sub-block
        return super.toMDL()
    }
	
}
