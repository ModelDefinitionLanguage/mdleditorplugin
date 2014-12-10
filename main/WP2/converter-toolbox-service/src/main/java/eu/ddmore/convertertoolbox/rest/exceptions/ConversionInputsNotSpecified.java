package eu.ddmore.convertertoolbox.rest.exceptions;


import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;


@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Conversion input file missing.")
public class ConversionInputsNotSpecified extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * @param message
     */
    public ConversionInputsNotSpecified(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    public ConversionInputsNotSpecified(String message, Throwable cause) {
        super(message, cause);
    }

}
