package eu.ddmore.converter.mdl2json.domain;

import eu.ddmore.converter.mdl2json.interfaces.MDLPrintable;
import eu.ddmore.converter.mdl2json.utils.XtextWrapper;
import groovy.util.Expando;

import java.util.ArrayList;
import java.util.List;

import org.ddmore.mdl.mdl.Argument
import org.ddmore.mdl.mdl.DataInputBlock;
import org.ddmore.mdl.mdl.SymbolDeclaration;
import org.ddmore.mdl.mdl.impl.ListImpl

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
