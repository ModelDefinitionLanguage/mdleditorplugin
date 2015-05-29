/*******************************************************************************
 * Copyright (C) 2014-2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json.domain

import java.util.List;

import org.ddmore.mdl.mdl.ModelPredictionBlock
import org.ddmore.mdl.mdl.ModelPredictionBlockStatement
import org.ddmore.mdl.mdl.PkMacroStatement;

import eu.ddmore.converter.mdl2json.interfaces.MDLAsJSON
import eu.ddmore.converter.mdl2json.interfaces.MDLPrintable
import eu.ddmore.converter.mdlprinting.MdlPrinter


/**
 * Represents MODEL_PREDICTION block within the MDL file.
 */
class ModelPredictionList extends ArrayList<ModelPredictionItem> implements MDLPrintable, MDLAsJSON {
	
    private static MdlPrinter mdlPrinter = MdlPrinter.getInstance()
	
    public ModelPredictionList(ModelPredictionBlock mpb) {
        
		mpb.getStatements().each { ModelPredictionBlockStatement statement ->
            
			if (statement.getOdeBlock()) {
			    final ModelPredictionItem mpi = ModelPredictionItem.buildFromSymbolDeclarations(
                    statement.getOdeBlock().getIdentifier(), statement.getOdeBlock().getVariables())
                add(mpi)
			} else if (statement.getPkMacroBlock()) {
                final ModelPredictionItem mpi = ModelPredictionItem.buildFromPkMacroStatements(
                    statement.getPkMacroBlock().getIdentifier(), statement.getPkMacroBlock().getStatements())
                add(mpi)
            } else if (statement.getVariable()) {
                final ModelPredictionItem mpi = ModelPredictionItem.buildFromSymbolDeclaration(statement.getVariable())
                add(mpi)
			}
		}
	}
    
    public ModelPredictionList(final List<Map> jsonList) {
        addAll(jsonList.collect { Map json -> new ModelPredictionItem(json) })
    }

    @Override
    public String toMDL() {
        collect{
            ModelPredictionItem mpi -> mpi.toMDL()
        }.join("\n${IDT*2}")
    }
	
}