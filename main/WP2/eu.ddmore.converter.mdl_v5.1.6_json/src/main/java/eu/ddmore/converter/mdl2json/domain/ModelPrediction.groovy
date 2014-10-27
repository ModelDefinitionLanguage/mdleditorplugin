package eu.ddmore.converter.mdl2json.domain

import java.util.Map;

import org.ddmore.mdl.mdl.FunctionCallStatement
import org.ddmore.mdl.mdl.ModelPredictionBlock;
import org.ddmore.mdl.mdl.ModelPredictionBlockStatement

import eu.ddmore.converter.mdl2json.interfaces.MDLAsJSON;
import eu.ddmore.converter.mdl2json.interfaces.MDLPrintable;
import eu.ddmore.converter.mdlprinting.MdlPrinter;
import groovy.util.Expando;


/**
 * Represents MODEL_PREDICTION block within the MDL file.
 */
class ModelPrediction extends Expando implements MDLPrintable, MDLAsJSON  {

	private static MdlPrinter mdlPrinter = MdlPrinter.getInstance()
	
    public ModelPrediction(ModelPredictionBlock mpb) {

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
            setProperty("ODE", odeStatements.join())
        }

		if (!libraryStatements.isEmpty()) {
		    setProperty("LIBRARY", libraryStatements.join())
		}
        
		setProperty("content", statements.join())
	}
	
	public ModelPrediction(Map json) {
		getProperties().putAll(json)
	}

	@Override
    public String toMDL() {
		StringBuffer buff = new StringBuffer("\n${IDT*2}")
		
		if (getProperty("ODE")) {
			buff.append("ODE {\n").append(getProperty("ODE")).append("${IDT*2}}\n${IDT*2}")
		}
		if (getProperty("LIBRARY")) {
			buff.append("LIBRARY {\n").append(getProperty("LIBRARY")).append("${IDT*2}}\n${IDT*2}")
		}
		if (getProperty("content")) {
			buff.append("\n").append(getProperty("content"));
		}
		buff.toString()
    }
	
}
