package eu.ddmore.converter.mdl2json.domain.data;

import groovy.util.Expando;

import java.util.ArrayList;
import java.util.List;

import org.ddmore.mdl.mdl.DataInputBlock;
import org.ddmore.mdl.mdl.SymbolDeclaration;

//import org.ddmore.mdl.mdl.HeaderBlock;
//import org.ddmore.mdl.mdl.SymbolModification;

public class DataInputVariables extends Expando {

	public DataInputVariables(DataInputBlock dataInputBlock) {

		makeVariables(dataInputBlock);
	}
	
	public DataInputVariables(Map json) {
		json.each{ k, v ->
			setProperty(k, new Variable(v))
		}
	}
	
	private void makeVariables(DataInputBlock dataInputBlock) {
		for( SymbolDeclaration sd : dataInputBlock.getVariables() ) {
			Variable v = new Variable(sd);
			setProperty(v.getName(), v);
		}	
	}

	public String toMDL() {
		List parameters = []
		getProperties().each{ k, v ->
			parameters.add("${k}=${v.toMDL()}")
		}

		"""
    DATA_INPUT_VARIABLES {
        ${parameters.join("\n        ")}
    }
"""
	}

}
