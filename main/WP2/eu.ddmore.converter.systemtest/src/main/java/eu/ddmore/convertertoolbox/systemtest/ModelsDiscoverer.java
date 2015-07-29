package eu.ddmore.convertertoolbox.systemtest;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;


public class ModelsDiscoverer {
    private final static Logger LOG = Logger.getLogger(ModelsDiscoverer.class);
    private final File modelsRoot;
    private final String modelFileExtension;
    
    ModelsDiscoverer(final File modelsRoot, final String modelFileExtension) {
        this.modelsRoot = modelsRoot;
        this.modelFileExtension = modelFileExtension;
    }
    
    Collection<File> getAllModels() {
        LOG.info("Discovering models with file extension ." + modelFileExtension + " within subdirectory \"" + this.modelsRoot + "\" ...");
        final Collection<File> allModelFiles = FileUtils.listFiles(modelsRoot, new String[] {this.modelFileExtension}, true);
        Iterator<File> modelFilesItr = allModelFiles.iterator();
        while (modelFilesItr.hasNext()) {
        	File modelFile = modelFilesItr.next();
        	if(modelFile.getParent().endsWith("targetblock")){
        		modelFilesItr.remove();
        	}else{
        		LOG.info("Found model file: " + modelFile);
        	}
        }
        return allModelFiles;
    }

}
