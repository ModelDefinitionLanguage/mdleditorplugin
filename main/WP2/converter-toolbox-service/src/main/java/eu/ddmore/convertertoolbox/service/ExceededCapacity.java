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
     * @param message
     * @param cause
     */
    public ExceededCapacity(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public ExceededCapacity(String message) {
        super(message);
    }
    
}
