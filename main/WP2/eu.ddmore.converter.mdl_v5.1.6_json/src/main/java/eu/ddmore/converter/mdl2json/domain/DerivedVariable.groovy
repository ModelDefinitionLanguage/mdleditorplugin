package eu.ddmore.converter.mdl2json.domain

import java.util.Map;

import org.ddmore.mdl.mdl.BlockStatement
import org.ddmore.mdl.mdl.SymbolDeclaration;

import eu.ddmore.converter.mdl2json.interfaces.MDLPrintable;
import eu.ddmore.converter.mdl2json.utils.XtextWrapper;

class DerivedVariable extends Expando implements MDLPrintable {

	public DerivedVariable(final BlockStatement bs) {
		final String symbolName = bs.getSymbol().getSymbolName().getName()
		final String rhs = XtextWrapper.unwrap(bs.getSymbol().getExpression())
		setProperty(symbolName, rhs)
	}
	
	public DerivedVariable(final Map json) {
		getProperties().putAll(json)
	}
	
	public String toMDL() {
		// There is only actually one key-value pair in the Map
		return getProperties().collect { k, v ->
			"${k} = ${v}"
		}.join("\n")
	}
	
}
