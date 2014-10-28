package eu.ddmore.converter.mdl2json.domain

import org.ddmore.mdl.mdl.FunctionCallStatement
import org.ddmore.mdl.mdl.ModelPredictionBlock
import org.ddmore.mdl.mdl.ModelPredictionBlockStatement

import eu.ddmore.converter.mdl2json.interfaces.MDLAsJSON
import eu.ddmore.converter.mdl2json.interfaces.MDLPrintable
import eu.ddmore.converter.mdlprinting.MdlPrinter


/**
 * Represents MODEL_PREDICTION block within the MDL file.
 */
class ModelPrediction extends Expando implements MDLPrintable, MDLAsJSON {
	
	static final String ODE = "ODE"
	static final String LIBRARY = "LIBRARY"

	private static MdlPrinter mdlPrinter = MdlPrinter.getInstance()
	
    public ModelPrediction(ModelPredictionBlock mpb) {

        List odeStatements = []
        List libraryStatements = []
		List statements = []
        
		mpb.getStatements().each { ModelPredictionBlockStatement statement ->
            
			if (statement.getOdeBlock()) {
				statement.getOdeBlock().getVariables().each {
                    odeStatements.add("${IDT*3}" + mdlPrinter.print(it))
                }
			} else if (statement.getLibraryBlock()) {
				statement.getLibraryBlock().getStatements().each { FunctionCallStatement fcs ->
					libraryStatements.add("${IDT*3}${fcs.getSymbolName().getName()}=${mdlPrinter.print(fcs.getExpression())}")
				}
			} else if (statement.getVariable()) {
				statements.add("${IDT*2}" + mdlPrinter.print(statement.getVariable()))
			} else if (statement.getCompartmentBlock()) {
				throw new UnsupportedOperationException("Compartment sub-block within Model Prediction block not supported yet")
			} else if (statement.getDegBlock()) {
				throw new UnsupportedOperationException("Deg/Deq sub-block within Model Prediction block not supported yet")
			}
		}
        
        if (!odeStatements.isEmpty()) {
            setProperty(ODE, odeStatements.join("\n"))
        }

		if (!libraryStatements.isEmpty()) {
		    setProperty(LIBRARY, libraryStatements.join("\n"))
		}
        
		setProperty("content", statements.join("\n"))
	}
	
	public ModelPrediction(Map json) {
		getProperties().putAll(json)
	}

	@Override
    public String toMDL() {
		List subBlocksAsStr = []
		
		if (getProperties().containsKey(ODE)) {
			subBlocksAsStr.add("${ODE} {\n${getProperty('ODE')}\n${IDT*2}}")
		}
		if (getProperties().containsKey(LIBRARY)) {
			subBlocksAsStr.add("${LIBRARY} {\n${getProperty('LIBRARY')}\n${IDT*2}}")
		}
		if (getProperties().containsKey("content")) {
			subBlocksAsStr.add(getProperty("content").trim())
		}
		
		subBlocksAsStr.join("\n${IDT*2}")
    }
	
}
