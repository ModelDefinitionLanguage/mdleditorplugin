package eu.ddmore.converter.mdl2json.domain;

import org.ddmore.mdl.mdl.SymbolDeclaration

import eu.ddmore.converter.mdl2json.interfaces.MDLPrintable


/**
 * Class encapsulating a list of RandomVariables.
 * Used tp aid conversion to MDL.
 */
public class RandomVariablesList extends ArrayList<RandomVariable> implements MDLPrintable {
	
	/**
	 * Private constructor to force instantiation via the factory methods
	 * (at least it would if Groovy respected visibility keywords...).
	 * @param l - list of RandomVariable objects
	 */
	private RandomVariablesList(final List<RandomVariable> l) {
		super(l)
	}
	
	static RandomVariablesList buildFromSymbolDeclarations(final List<SymbolDeclaration> sds) {
		return new RandomVariablesList(sds.collect { SymbolDeclaration sd -> new RandomVariable(sd) });
	}
	
	static RandomVariablesList buildFromJSON(final List jsonList) {
		return new RandomVariablesList(jsonList.collect { json ->
			new RandomVariable(json)
		})
	}
	
	@Override
    public String toMDL() {
	    collect{ MDLPrintable elem ->
			elem.toMDL()
		}.join("\n${IDT*2}")
    }

}
