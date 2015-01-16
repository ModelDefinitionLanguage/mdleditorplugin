/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.domain.hal;

/**
 * Holds constants for relations used in links
 */
public enum LinkRelation {
    /**
     * Relation for link which holds conversion's result resource
     */
    RESULT("result"),
    /**
     * Relation for link to delete a conversion
     */
    DELETE("delete"),
    /**
     * Relation for link refering to resource's 'self' location
     */
    SELF("self"),
    /**
     * Relation for link pointing to home location of the service
     */
    HOME("home"),
    /**
     * Relation for link representing submission of a conversion
     */
    SUBMIT("submit"),
    /**
     * Relation for link to a support team's system
     */
    SUPPORT("support");
    
    private final String relation;
    private LinkRelation(String relation) {
        this.relation = relation;
    }
    
    public String getRelation() {
        return relation;
    }
}
