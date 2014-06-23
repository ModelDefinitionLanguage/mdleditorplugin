package eu.ddmore.converter.mdl2json.domain;

import org.ddmore.mdl.mdl.ParameterObject


public class Parameter extends Expando {

	public Parameter(ParameterObject paramObject) {
		this.setProperty("identifier", paramObject.getIdentifier())
	}
}
