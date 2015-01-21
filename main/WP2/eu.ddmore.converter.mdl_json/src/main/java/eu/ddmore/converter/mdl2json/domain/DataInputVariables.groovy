package eu.ddmore.converter.mdl2json.domain;

import org.ddmore.mdl.mdl.DataInputBlock
import org.ddmore.mdl.mdl.SymbolDeclaration

import eu.ddmore.converter.mdl2json.interfaces.MDLPrintable

public class DataInputVariables extends ArrayList implements MDLPrintable {

	public DataInputVariables(final DataInputBlock dataInputBlock) {
		for (final SymbolDeclaration sd : dataInputBlock.getVariables()) {
			add(new Variable(sd));
		}
	}
	
	public DataInputVariables(final List json) {
		json.each{ v ->
			add(new Variable(v))
		}
	}
	
	public String toMDL() {
		collect { Variable v -> "${v.toMDL()}" }.join("\n        ")
	}

}
