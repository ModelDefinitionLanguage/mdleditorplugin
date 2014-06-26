package eu.ddmore.converter.mdl2json.domain;

import org.ddmore.mdl.mdl.TaskObject


public class Task extends Expando{
	
	static final String IDENTIFIER = "taskobj"
	
	public Task(TaskObject taskObj) {
		setProperty("identifier", taskObj.getIdentifier())	
	}
	
	public Task(Object json) {
		setProperty("identifier", "taskobj")
	}
	
	public String toMDL() {
		return """ ${IDENTIFIER} {
			}
		
		"""
	}

}
