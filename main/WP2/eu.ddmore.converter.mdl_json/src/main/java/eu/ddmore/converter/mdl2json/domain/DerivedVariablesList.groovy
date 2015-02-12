
package eu.ddmore.converter.mdl2json.domain;

import org.ddmore.mdl.mdl.GroupVariablesBlock
import org.ddmore.mdl.mdl.GroupVariablesBlockStatement

import eu.ddmore.converter.mdl2json.interfaces.MDLPrintable


/**
 * Class encapsulating a list of {@link DerivedVariable}s.
 * Used to aid conversion to MDL.
 */
public class DerivedVariablesList extends ArrayList<DerivedVariable> implements MDLPrintable {

    /**
     * Private constructor to force instantiation via the factory methods
     * (at least it would if Groovy respected visibility keywords...).
     * @param l - list of Variable objects
     */
    private DerivedVariablesList(final List<DerivedVariable> l) {
        super(l)
    }

    static DerivedVariablesList buildFromGroupVariablesBlock(GroupVariablesBlock groupVariables) {

        List groupVars = []
        groupVariables.getStatements().each { GroupVariablesBlockStatement statement ->
            def var = statement.getVariable()
            def mixtureBlock = statement.getMixtureBlock()

            groupVars.add(new DerivedVariable(var))
        }
        return new DerivedVariablesList(groupVars)
    }

    static DerivedVariablesList buildFromJSON(final List jsonList) {
        return new DerivedVariablesList(jsonList.collect { Map json ->
            new DerivedVariable(json)
        })
    }

    @Override
    public String toMDL() {
        collect{ MDLPrintable elem ->
            elem.toMDL()
        }.join("\n${IDT*2}")
    }
}
