package eu.ddmore.convertertoolbox.system;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.junit.Test;


public class PharmmlModelsTest {
    
    private final static Logger LOGGER = Logger.getLogger(PharmmlModelsTest.class);
    
    private final static String MODELS_SUBDIRECTORY = "pharmml";
    private final static String MODELS_FILE_EXTENSION = "xml";
    private final static String OUTPUT_FILE_EXTENSION = ".ctl";
    
    // We'll consider a conversion to have failed if the converted output file has a size that is less than this number of bytes
    private final static int NMTRAN_FILE_SIZE_THRESHOLD = 50; // TODO: Proper threshhold
    
    @Test
    public void runTests() throws IOException {
        final Collection<File> allModels = new ModelsDiscoverer(MODELS_SUBDIRECTORY, MODELS_FILE_EXTENSION).getAllModels();
        for (final File model : allModels) {
            new ConverterRunner(model, OUTPUT_FILE_EXTENSION, "PharmML", "0.3.0", "NMTRAN", "7.2", NMTRAN_FILE_SIZE_THRESHOLD).run();
        }
    }
    
}
