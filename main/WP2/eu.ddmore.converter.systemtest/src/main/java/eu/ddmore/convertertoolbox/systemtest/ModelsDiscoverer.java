package eu.ddmore.convertertoolbox.systemtest;

import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;


public class ModelsDiscoverer {
    private final static Logger LOGGER = Logger.getLogger(ModelsDiscoverer.class);
    
    final static String PATH_TO_MODELS_DIR = "target/WorkingDir/eu/ddmore/testdata/models/";
    
    private final String modelsSubdirectory;
    private final String modelFileExtension;
    
    ModelsDiscoverer(final String modelsSubdirectory, final String modelFileExtension) {
        this.modelsSubdirectory = modelsSubdirectory;
        this.modelFileExtension = modelFileExtension;
    }
    
    Collection<File> getAllModels() {
        LOGGER.info("Discovering models with file extension ." + modelFileExtension + " within subdirectory \"" + this.modelsSubdirectory + "\" ...");
        final File rootDirectory = new File(PATH_TO_MODELS_DIR + this.modelsSubdirectory);
        final Collection<File> allModelFiles = FileUtils.listFiles(rootDirectory, new String[] {this.modelFileExtension}, true);
        for (final File modelFile : allModelFiles) {
            LOGGER.info("Found model file: " + modelFile);
        }
        return allModelFiles;
    }

}
