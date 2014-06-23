package eu.ddmore.converter.mdl2json.domain;

import org.ddmore.mdl.mdl.ModelObject

public class Model extends Expando {

	public Model(ModelObject modelObject) {
	
		setProperty("identifier", modelObject.getIdentifier())
			
	}
}
