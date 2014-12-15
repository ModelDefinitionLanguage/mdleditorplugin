/*******************************************************************************
 * Copyright (C) 2002 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.domain.hal;

/**
 * Holds constants for relations used in links
 */
public final class LinkRelations {
    /**
     * Relation for link which holds conversion's result resource
     */
    public static final String RESULT = "result";
    /**
     * Relation for link to delete a conversion
     */
    public static final String DELETE = "delete";
    /**
     * Relation for link refering to resource's 'self' location
     */
    public static final String SELF = "self";
    /**
     * Relation for link pointing to home location of the service
     */
    public static final String HOME = "home";
    /**
     * Relation for link representing submission of a conversion
     */
    public static final String SUBMIT = "submit";
    /**
     * Relation for link representing a list of conversions being processed
     */
    public static final String CONVERSIONS = "conversions";
}
