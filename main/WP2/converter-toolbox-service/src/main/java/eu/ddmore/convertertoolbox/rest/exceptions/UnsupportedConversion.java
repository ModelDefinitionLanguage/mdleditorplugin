package eu.ddmore.convertertoolbox.rest.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;


@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Unsupported conversion")
public class UnsupportedConversion extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * @param message
     * @param cause
     */
    public UnsupportedConversion(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public UnsupportedConversion(String message) {
        super(message);
    }
    
}
