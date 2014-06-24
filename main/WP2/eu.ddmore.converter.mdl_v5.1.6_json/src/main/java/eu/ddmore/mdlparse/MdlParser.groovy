package eu.ddmore.mdlparse

import java.io.File;

import org.ddmore.mdl.MdlStandaloneSetup;
import org.ddmore.mdl.mdl.Mcl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.inject.Injector;

class MdlParser {

	static Injector injector;
	static XtextResourceSet resourceSet;
	
	static {
        injector = new MdlStandaloneSetup().createInjectorAndDoEMFRegistration();
        resourceSet = injector.getInstance(XtextResourceSet.class);
        resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);
	}

	public Mcl parse(String model) {

        println("Model parameter: " + model);
        println("Resource: " + Thread.currentThread().getContextClassLoader().getResource(model));
        println("Path: " + Thread.currentThread().getContextClassLoader().getResource(model).getPath());
        File src = new File(Thread.currentThread().getContextClassLoader().getResource(model).getPath());
        
        return parse(src);
	}
	
	public Mcl parse(File mdlFile) {
        println("Path: " + mdlFile.getPath());
        println("Thread: " + Thread.currentThread());
        println("Classloader: " + Thread.currentThread().getContextClassLoader());

        Resource resource = resourceSet.getResource(URI.createURI("file:///" + mdlFile.getAbsolutePath()), true);
		return (Mcl) resource.getContents().get(0);
	}

}