package eu.ddmore.converter.mdl2json.domain;


/**
 * Interface to be implemented by the MOG object implementation
 */
public interface MOG {

	Data getData();
	Parameter getParameter();
	Model getModel();
	Task getTask();
}
