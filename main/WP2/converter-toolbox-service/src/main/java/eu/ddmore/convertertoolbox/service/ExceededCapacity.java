/*******************************************************************************
 * Copyright (C) 2002 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.service;

/**
 * Exception thrown when the ConversionService reached its capacity
 */
public class ExceededCapacity extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * @param error message
     * @param causing exception
     */
    public ExceededCapacity(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param error message
     */
    public ExceededCapacity(String message) {
        super(message);
    }
    
}
