package eu.ddmore.converter.mdl2json.domain;

import org.ddmore.mdl.mdl.Mcl;
import org.ddmore.mdl.mdl.MclObject;
import org.eclipse.emf.common.util.URI;

import groovy.util.Expando;

public class MCLFile extends Expando {

	public MCLFile(Mcl mclFile) {
		for(MclObject mclObj : mclFile.getObjects()) {
			String name = mclObj.getObjectName().getName();
			// log println " found ${name}"
			if(mclObj.getDataObject()!=null) {
				setProperty(name, new Data(mclObj.getDataObject()));
			} else if (mclObj.getModelObject() != null) {
				setProperty(name, new Model(mclObj.getModelObject()));
			} else if (mclObj.getParameterObject() != null) {
				setProperty(name, new Parameter(mclObj.getParameterObject()));
			} else if (mclObj.getTaskObject() != null) {
				setProperty(name, new Task(mclObj.getTaskObject()));
			}
		}
	}
	
	public MCLFile(Object json) {
		// JSON is a list of Maps
		json.each {
			// "it" is a Map
			it.each { key, value ->
				// Each key/value pair is the object (e.g. ex_model7_prolactin_Jan2014_dat -> properties )
				String type = value.identifier
				switch( type ) {
					case Task.IDENTIFIER: 
						setProperty(key, new Task(value));
						break;
					case Model.IDENTIFIER: 
						setProperty(key, new Model(value));
						break;
					case Parameter.IDENTIFIER: 
						setProperty(key, new Parameter(value));
						break;
					case Data.IDENTIFIER: 
						setProperty(key, new Data(value));
						break;
				} 
			}
		}
	}
	
	public String toMDL() {
		String retVal = ""
		
		getProperties().each{ key, value ->
			if(key!=null) {
				retVal += "${key} = ${value.toMDL()}"
			} 
		}
		retVal
	}
}
