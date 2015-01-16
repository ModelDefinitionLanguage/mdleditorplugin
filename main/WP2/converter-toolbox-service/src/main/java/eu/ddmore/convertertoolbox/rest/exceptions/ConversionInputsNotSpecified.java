/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.rest.exceptions;


import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * Exception representing an error when the conversion input archive was not uploaded.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Conversion input archive missing.")
public class ConversionInputsNotSpecified extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of the exception with the given message
     * @param message Error message
     */
    public ConversionInputsNotSpecified(String message) {
        super(message);
    }

    /**
     * Creates a new instance of the exception with the given message and root exception
     * @param message Error message
     * @param cause Root exception
     */
    public ConversionInputsNotSpecified(String message, Throwable cause) {
        super(message, cause);
    }

}
