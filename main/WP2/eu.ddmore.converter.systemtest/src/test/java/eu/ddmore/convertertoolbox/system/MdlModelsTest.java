package eu.ddmore.convertertoolbox.system;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.junit.Test;


public class MdlModelsTest {
    
    private final static Logger LOGGER = Logger.getLogger(MdlModelsTest.class);
    
    private final static String MODELS_SUBDIRECTORY = "mdl";
    private final static String MODELS_FILE_EXTENSION = "mdl";
    private final static String OUTPUT_FILE_EXTENSION = ".xml";
    
    // We'll consider a conversion to have failed if the converted output file has a size that is less than this number of bytes
    private final static int PHARMML_FILE_SIZE_THRESHOLD = 638;
    
    @Test
    public void runTests() throws IOException {
        final Collection<File> allModels = new ModelsDiscoverer(MODELS_SUBDIRECTORY, MODELS_FILE_EXTENSION).getAllModels();
        for (final File model : allModels) {
            new ConverterRunner(model, OUTPUT_FILE_EXTENSION, "MDL", "5.1.6", "PharmML", "0.3.0", PHARMML_FILE_SIZE_THRESHOLD).run();
        }
    }
    
}
