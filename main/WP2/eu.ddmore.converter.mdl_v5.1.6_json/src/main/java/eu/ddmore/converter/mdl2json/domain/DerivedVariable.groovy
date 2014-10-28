package eu.ddmore.converter.mdl2json.domain

import org.ddmore.mdl.mdl.SymbolDeclaration

import eu.ddmore.converter.mdl2json.interfaces.MDLPrintable
import eu.ddmore.converter.mdl2json.utils.XtextWrapper

class DerivedVariable extends Expando implements MDLPrintable {

	public DerivedVariable(final SymbolDeclaration sd) {
		final String symbolName = sd.getSymbolName().getName()
		final String rhs = XtextWrapper.unwrap(sd.getExpression())
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
