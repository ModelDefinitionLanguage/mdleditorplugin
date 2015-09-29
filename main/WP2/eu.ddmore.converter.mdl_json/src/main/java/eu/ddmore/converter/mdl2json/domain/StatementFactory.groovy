/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json.domain

import java.util.Map;

import eu.ddmore.converter.mdl2json.interfaces.MDLPrintable
import org.apache.log4j.Logger

/**
 * Creates instances of subclasses of {@link AbstractStatement}.
 */
public abstract class StatementFactory {

    private final static Logger LOG = Logger.getLogger(StatementFactory.class);
    
    private final static String MDLGRAMMAR_DOMAIN_PACKAGE_NAME = eu.ddmore.mdl.mdl.Statement.class.getPackage().getName();
    private final static String CONVERTER_DOMAIN_PACKAGE_NAME = StatementFactory.class.getPackage().getName();
    
    public static AbstractStatement fromMDL(final eu.ddmore.mdl.mdl.Statement stmt) {
        for (final EStatementSubtype e : EStatementSubtype.values()) {
            final Class<? extends eu.ddmore.mdl.mdl.Statement> mdlGrammarDomainClass = Class.forName(MDLGRAMMAR_DOMAIN_PACKAGE_NAME+"."+e.getClassName())
            final Class<? extends StatementFactory> converterDomainClass = Class.forName(CONVERTER_DOMAIN_PACKAGE_NAME+"."+e.getClassName())
            if (mdlGrammarDomainClass.isAssignableFrom(stmt.getClass())) {
                return converterDomainClass.newInstance(stmt)
            }
        }
        throw new UnsupportedOperationException("Subclass " + stmt.getClass() + " of Statement not currently supported")
    }
    
    public static AbstractStatement fromJSON(final Map json) {
        if (json) { // TODO can remove once all subtypes implemented
            final String subtype = json.get(AbstractStatement.PROPERTY_SUBTYPE)
            if (subtype == null) {
                // Default implementation (if no subtype specified) of Statement is either ListDefinition or AnonymousListStatement,
                // depending on whether the variable name, the Key of the single Entry in the Map, is specified or is empty.
                if (json.containsKey(AnonymousListStatement.UNDEF_VAR_NAME_PLACEHOLDER)) {
                    return new AnonymousListStatement(json)
                } else {
                    return new ListDefinition(json)
                }
            } else {
                for (final EStatementSubtype e : EStatementSubtype.values()) {
                    if (e.getIdentifierString().equals(subtype)) {
                        final Class clazz = Class.forName(StatementFactory.class.getPackage().getName()+"."+e.getClassName())
                        return clazz.newInstance(json)
                    }
                }
            }
        }
    }
    
}
