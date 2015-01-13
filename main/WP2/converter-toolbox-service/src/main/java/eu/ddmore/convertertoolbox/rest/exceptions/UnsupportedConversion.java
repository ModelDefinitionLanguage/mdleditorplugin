/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.rest.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;


/**
 * Exception representing an error when client requested unsupported conversion.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Unsupported conversion")
public class UnsupportedConversion extends Exception {

	private static final long serialVersionUID = 1L;
    /**
     * Creates a new instance of the exception with the given message and root exception
     * @param message
     * @param cause
     */
    public UnsupportedConversion(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a new instance of the exception with the given message
     * @param message
     */
    public UnsupportedConversion(String message) {
        super(message);
    }
    
}
