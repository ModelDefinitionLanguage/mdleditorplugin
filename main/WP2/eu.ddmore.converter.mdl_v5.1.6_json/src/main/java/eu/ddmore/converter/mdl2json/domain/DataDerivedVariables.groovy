package eu.ddmore.converter.mdl2json.domain;

import groovy.util.Expando;

import java.util.ArrayList;
import java.util.List;

import org.ddmore.mdl.mdl.BlockStatement
import org.ddmore.mdl.mdl.DataDerivedBlock
import org.ddmore.mdl.mdl.DataInputBlock;
import org.ddmore.mdl.mdl.ObservationBlock;
import org.ddmore.mdl.mdl.SymbolDeclaration;

import eu.ddmore.converter.mdl2json.interfaces.MDLPrintable;
import eu.ddmore.converter.mdl2json.utils.XtextWrapper;

public class DataDerivedVariables extends ArrayList implements MDLPrintable {

	public DataDerivedVariables(final DataDerivedBlock dataDerivedBlock) {
		for (final BlockStatement bs : dataDerivedBlock.getStatements()) {
			add(new DerivedVariable(bs))
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
