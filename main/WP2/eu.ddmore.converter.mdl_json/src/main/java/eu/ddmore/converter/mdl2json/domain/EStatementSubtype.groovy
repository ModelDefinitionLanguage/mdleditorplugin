/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json.domain

import eu.ddmore.converter.mdl2json.domain.StatementFactory

/**
 * Enumerates the different subclasses of {@link eu.ddmore.mdl.mdl.Statement}.
 */
public enum EStatementSubtype {

    EquationDefinition("EquationDef", "EquationDefinition"),
    ListDefinition("ListDef", "ListDefinition"),
    RandomVarDefinition("RandVarDefn", "RandomVariableDefinition"),
    TransDefinition("TransDefn", "TransformedDefinition"),
    EnumDefinition("EnumDefn", "EnumerationDefinition"),
    AnonymousListDefinition("AnonListDefn", "AnonymousListStatement"),
    BlockStmt("BlockStmt", "BlockStatement")
    
    EStatementSubtype(final String identifierString, final String className) {
        this.identifierString = identifierString
        this.className = className
    }
    
    public String getIdentifierString() {
        return this.identifierString
    }
    
    public String getClassName() {
        return this.className
    }
    
    private String identifierString
    private String className
}