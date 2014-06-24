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
}
