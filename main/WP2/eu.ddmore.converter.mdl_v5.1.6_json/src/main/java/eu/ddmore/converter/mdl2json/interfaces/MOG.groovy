package eu.ddmore.converter.mdl2json.interfaces;

import eu.ddmore.converter.mdl2json.domain.Data;
import eu.ddmore.converter.mdl2json.domain.Model;
import eu.ddmore.converter.mdl2json.domain.Parameter;
import eu.ddmore.converter.mdl2json.domain.Task;


/**
 * Interface to be implemented by the MOG object implementation
 */
public interface MOG {

	Data getData();
	Parameter getParameter();
	Model getModel();
	Task getTask();
}
