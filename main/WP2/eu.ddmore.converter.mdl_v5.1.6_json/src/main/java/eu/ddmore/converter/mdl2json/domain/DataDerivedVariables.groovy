package eu.ddmore.converter.mdl2json.domain;

import org.ddmore.mdl.mdl.DataDerivedBlock
import org.ddmore.mdl.mdl.SymbolDeclaration

import eu.ddmore.converter.mdl2json.interfaces.MDLPrintable

public class DataDerivedVariables extends ArrayList implements MDLPrintable {

	public DataDerivedVariables(final DataDerivedBlock dataDerivedBlock) {
		for (final SymbolDeclaration sd : dataDerivedBlock.getVariables()) {
			add(new DerivedVariable(sd))
		}
	}
	
	public DataDerivedVariables(final List json) {
		json.each{ v ->
			add(new DerivedVariable(v))
		}
	}
	
	public String toMDL() {
		collect { DerivedVariable v -> "${v.toMDL()}" }.join("\n        ")
	}

}
