package eu.ddmore.converter.mdl2json.domain.data;

import eu.ddmore.converter.mdl2json.domain.MDLPrintable
import groovy.util.Expando;

import java.util.ArrayList;
import java.util.List;

import org.ddmore.mdl.mdl.DataInputBlock;
import org.ddmore.mdl.mdl.SymbolDeclaration;

//import org.ddmore.mdl.mdl.HeaderBlock;
//import org.ddmore.mdl.mdl.SymbolModification;

public class DataInputVariables extends LinkedHashMap implements MDLPrintable {

	public DataInputVariables(DataInputBlock dataInputBlock) {

		makeVariables(dataInputBlock);
	}
	
	public DataInputVariables(Map json) {
		json.each{ k, v ->
			put(k, new Variable(v))
		}
	}
	
	private void makeVariables(DataInputBlock dataInputBlock) {
		for( SymbolDeclaration sd : dataInputBlock.getVariables() ) {
			Variable v = new Variable(sd);
			put(v.getName(), v);
		}	
	}

	public String toMDL() {
		List parameters = []
		entrySet().each{ entry ->
			parameters.add("${entry.key}=${entry.value.toMDL()}")
		}

		"""
    DATA_INPUT_VARIABLES {
        ${parameters.join("\n        ")}
    }
"""
	}

}
