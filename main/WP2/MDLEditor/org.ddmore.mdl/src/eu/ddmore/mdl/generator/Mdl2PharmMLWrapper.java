package eu.ddmore.mdl.generator;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
//import org.apache.log4j.Logger;
import org.ddmore.mdl.MdlStandaloneSetup;
import org.ddmore.mdl.mdl.MOGObject;
import org.ddmore.mdl.mdl.Mcl;
import org.ddmore.mdl.validation.Utils;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.inject.Injector;

import eu.ddmore.converter.mdlprinting.MdlPrinter;

public class Mdl2PharmMLWrapper extends MdlPrinter implements IGenerator {
   //private static final Logger LOGGER = Logger.getLogger(Mdl2PharmMLWrapper.class);

   public void doGenerate(final Resource resource, final IFileSystemAccess fsa) {
        String relativeResourcePath = resource.getURI().toPlatformString(true);
        String[] uriParts = relativeResourcePath.split("/");
        String projectName = uriParts[1];
        String projectPath = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName).getLocation().toString();

        String sourcePath = relativeResourcePath.replace('/' + projectName, projectPath);
        File source = new File(sourcePath);
        File targetDir = new File(projectPath + Preferences.SRC_GEN_PREFIX);
        
        performConvert(source, targetDir);

        /*
        //TODO: How do we pass that info to the user?
        try {
            Converter converter = converterManager.getConverter(mdl, target);
            converter.convert(source, targetDir);
        } catch (ConverterNotFoundException e) {
            LOGGER.error(e.getMessage());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        */ 
    }
    
   public void performConvert(File src, File outputDirectory) {
       Injector injector = new MdlStandaloneSetup().createInjectorAndDoEMFRegistration();
       XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
       resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);

       Resource resource = resourceSet.getResource(URI.createURI("file:///" + src.getAbsolutePath()), true);
       Mcl mcl = (Mcl) resource.getContents().get(0);

       //TODO: we do not need this check when the converter is called from Eclipse UI
       //if (Utils.getMOGs(mcl).size() == 0)
       //	LOGGER.warn("PharmML generation error: no MOG found!");
       
       eu.ddmore.converter.mdl2pharmml.Mdl2PharmML xtendConverter = new eu.ddmore.converter.mdl2pharmml.Mdl2PharmML();
       List<MOGObject> mogs = Utils.getMOGs(mcl); 
	   String baseName = src.getName().replace(".mdl", ".xml");
       for (int i = 0; i < mogs.size(); i++){
    	   MOGObject mog = mogs.get(i);
    	   String fileName = baseName;
    	   if (i > 0) fileName = fileName + Utils.getObjectName(mog).getName(); 
           CharSequence converted = xtendConverter.convertToPharmML(mog, fileName);
           
           printOutputFile(fileName, outputDirectory, converted.toString());
           File targetDirectory = new File(outputDirectory.getPath() + "/target");
           CharSequence externalCode = xtendConverter.extractTargetCode(mog);
           if (externalCode.length() > 0)
        	   printOutputFile(fileName, targetDirectory, externalCode.toString());
       }
   }
   
   public void printOutputFile(String outputFileName, File outputDirectory, String text) {
       File outputFile = new File(outputDirectory.getAbsolutePath() +'/'+ outputFileName);
       try {
           FileUtils.writeStringToFile(outputFile, text);
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
   }

}