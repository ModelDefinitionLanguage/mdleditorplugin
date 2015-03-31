package eu.ddmore.mdlparse

import org.apache.log4j.Logger
import org.ddmore.mdl.MdlStandaloneSetup
import org.ddmore.mdl.mdl.Mcl
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.Resource.Diagnostic
import org.eclipse.xtext.parser.ParseException
import org.eclipse.xtext.resource.XtextResource
import org.eclipse.xtext.resource.XtextResourceSet

import com.google.inject.Injector

class MdlParser {
    private final static Logger LOGGER = Logger.getLogger(MdlParser.class)

	static Injector injector;
	static XtextResourceSet resourceSet;
	
	static {
        injector = new MdlStandaloneSetup().createInjectorAndDoEMFRegistration();
        resourceSet = injector.getInstance(XtextResourceSet.class);
        resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);
	}

	public Mcl parse(String model) {
        File src = new File(Thread.currentThread().getContextClassLoader().getResource(model).getPath());
        return parse(src);
	}
	
	public Mcl parse(File mdlFile) {
        final Resource resource = resourceSet.getResource(URI.createURI("file:///" + mdlFile.getAbsolutePath()), true)
        
        EList<Diagnostic> errors = resource.getErrors()
        EList<Diagnostic> warnings = resource.getWarnings()
        if (errors) {
            LOGGER.error(errors.size() + " errors encountered in parsing MDL file " + mdlFile.getAbsolutePath())
            for (Diagnostic e : errors) {
                LOGGER.error(e)
            }
            throw new ParseException("Unable to parse MDL file " + mdlFile.getAbsolutePath() + "; " + errors.size() + " error(s) encountered; see the log output.")
        }
        if (warnings) {
            LOGGER.error(warnings.size() + " warning(s) encountered in parsing MDL file " + mdlFile.getAbsolutePath())
            for (Diagnostic w : warnings) {
                LOGGER.error(w)
            }
        }
        
		return (Mcl) resource.getContents().get(0);
	}

}
