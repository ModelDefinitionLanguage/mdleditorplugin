/*******************************************************************************
 * Copyright (C) 2014-2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json.domain;

import org.ddmore.mdl.mdl.GroupVariablesBlock
import org.ddmore.mdl.mdl.GroupVariablesBlockStatement
import org.ddmore.mdl.mdl.PkMacroStatement
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
    
    static VariablesList createEmpty() {
        return new VariablesList(new ArrayList<Variable>())
    }
    
    static VariablesList buildFromListOfVariables(final List<Variable> l) {
        return new VariablesList(l)
    }

    static VariablesList buildFromSymbolDeclarations(final List<SymbolDeclaration> sds) {
        return new VariablesList(sds.collect { SymbolDeclaration sd -> new Variable(sd) })
    }

    static VariablesList buildFromSymbolNames(final List<SymbolName> sns) {
        return new VariablesList(sns.collect { SymbolName sn -> new Variable(sn) })
    }

    static VariablesList buildFromPkMacrosStatements(final List<PkMacroStatement> pkmss) {
        return new VariablesList(pkmss.collect { PkMacroStatement pkms -> Variable.buildFromPkMacroStatement(pkms) })
    }

    static VariablesList buildFromGroupVariablesBlock(GroupVariablesBlock groupVariables) {
        List groupVars = []
        groupVariables.getStatements().each { GroupVariablesBlockStatement statement ->
            if (statement.getMixtureBlock()) {
                throw new UnsupportedOperationException("Mixture sub-block of Group Variables block not currently supported")
            }
            groupVars.add(new Variable(statement.getVariable()))
        }
        return new VariablesList(groupVars)
    }

    static VariablesList buildFromJSON(final List jsonList) {
        return new VariablesList(jsonList.collect { json -> new Variable(json) })
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toMDL() {
        return toMDL(2)
    }

    public String toMDL(final int numIndents) {
        collect{ MDLPrintable elem ->
            elem.toMDL()
        }.join("\n${IDT*numIndents}")
    }

}