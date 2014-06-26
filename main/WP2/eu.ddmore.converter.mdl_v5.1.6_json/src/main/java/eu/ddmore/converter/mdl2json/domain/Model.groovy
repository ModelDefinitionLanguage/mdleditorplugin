package eu.ddmore.converter.mdl2json.domain;

import org.ddmore.mdl.mdl.ModelObject

public class Model extends Expando {

	static final String IDENTIFIER = "mdlobj"
	
	public Model(ModelObject modelObject) {
	
		setProperty("identifier", IDENTIFIER)
			
	}

	public Model(Object json) {
		setProperty("identifier", IDENTIFIER)
	}
	
	public String toMDL() {
		return """${IDENTIFIER} {
			}
		 
		"""
	}
}
