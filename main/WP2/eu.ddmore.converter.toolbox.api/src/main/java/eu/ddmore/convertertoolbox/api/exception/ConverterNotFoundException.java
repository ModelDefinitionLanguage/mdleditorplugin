package eu.ddmore.convertertoolbox.api.exception;

/**
 * Models the exception that shold be thrown by ConvertManager if no proper conversion can be found 
 * for the given input and output language and version. 
 * Modeled as a checked exception to warn client code about this failure possibility, 
 * so as it can manage that within its business logic.
 */
public class ConverterNotFoundException extends Exception {

	public ConverterNotFoundException(String message) {
		super(message);
	}
}
