package eu.ddmore.converter.mdl2json.domain;

import org.ddmore.mdl.mdl.SymbolDeclaration
import org.ddmore.mdl.mdl.SymbolName

import eu.ddmore.converter.mdl2json.interfaces.MDLPrintable


/**
 * Class encapsulating a list of {@link Variable}s.
 * Used to aid conversion to MDL.
 */
public class VariablesList extends ArrayList<Variable> implements MDLPrintable {

    /**
     * Private constructor to force instantiation via the factory methods
     * (at least it would if Groovy respected visibility keywords...).
     * @param l - list of Variable objects
     */
    private VariablesList(final List<Variable> l) {
        super(l)
    }

    static VariablesList buildFromSymbolDeclarations(final List<SymbolDeclaration> sds) {
        return new VariablesList(sds.collect { SymbolDeclaration sd -> new Variable(sd) });
    }

    static VariablesList buildFromSymbolNames(final List<SymbolName> sns) {
        return new VariablesList(sns.collect { SymbolName sn -> new Variable(sn) });
    }

    static VariablesList buildFromJSON(final List jsonList) {
        return new VariablesList(jsonList.collect { json ->
            new Variable(json)
        })
    }

    @Override
    public String toMDL() {
        collect{ MDLPrintable elem ->
            elem.toMDL()
        }.join("\n${IDT*2}")
    }
}
