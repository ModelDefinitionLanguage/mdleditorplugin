package eu.ddmore.converter.mdl2json.domain;

import org.ddmore.mdl.mdl.TaskObject


public class Task extends Expando{
	
	public Task(TaskObject taskObj) {
		setProperty("identifier", taskObj.getIdentifier())	
	}
}
