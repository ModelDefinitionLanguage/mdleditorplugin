package eu.ddmore.converter.mdl2json.domain.data;

import eu.ddmore.converter.mdl2json.domain.MDLPrintable
import groovy.util.Expando;

import java.util.ArrayList;
import java.util.List;

import org.ddmore.mdl.mdl.DataInputBlock;
import org.ddmore.mdl.mdl.SymbolDeclaration;

public class DataInputVariables extends ArrayList implements MDLPrintable {

	public DataInputVariables(DataInputBlock dataInputBlock) {
		for (SymbolDeclaration sd : dataInputBlock.getVariables()) {
			add(new Variable(sd));
		}
	}
	
	public DataInputVariables(List json) {
		json.each{ v ->
			add(new Variable(v))
		}
	}
	
	public String toMDL() {
		List parameters = []
		each{ v ->
			parameters.add("${v.name}=${v.toMDL()}")
		}

		"""
    DATA_INPUT_VARIABLES {
        ${parameters.join("\n        ")}
    }
"""
	}

}
