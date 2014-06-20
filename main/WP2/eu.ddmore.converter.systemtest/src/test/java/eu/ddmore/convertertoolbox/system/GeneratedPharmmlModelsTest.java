package eu.ddmore.convertertoolbox.system;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;


public class GeneratedPharmmlModelsTest {
    
    private final static Logger LOGGER = Logger.getLogger(GeneratedPharmmlModelsTest.class);
    
    private final static String ORIGINAL_MDL_MODELS_SUBDIRECTORY = "mdl";
    private final static String GENERATED_PHARMML_MODELS_SUBDIRECTORY = "pharmml-generated-from-MDL";
    private final static String PHARMML_MODELS_FILE_EXTENSION = "xml";
    private final static String OUTPUT_FILE_EXTENSION = ".ctl";
    
    // We'll consider a conversion to have failed if the converted output file has a size that is less than this number of bytes
    private final static int NMTRAN_FILE_SIZE_THRESHOLD = 50; // TODO: Proper threshhold
    
    /**
     * Delete the pharmml-generated-from-MDL directory before the tests are run.
     * @throws IOException - if unable to delete the directory
     */
    @Before
    public void setUp() throws IOException {
        FileUtils.deleteDirectory(new File(ModelsDiscoverer.PATH_TO_MODELS_DIR + GENERATED_PHARMML_MODELS_SUBDIRECTORY));
    }
    
    @Test
    public void runTests() throws IOException {
        
        copyGeneratedPharmmlFiles();
        
        final Collection<File> allModels = new ModelsDiscoverer(GENERATED_PHARMML_MODELS_SUBDIRECTORY, PHARMML_MODELS_FILE_EXTENSION).getAllModels();
        for (final File model : allModels) {
            new ConverterRunner(model, OUTPUT_FILE_EXTENSION, "PharmML", "0.3.0", "NMTRAN", "7.2", NMTRAN_FILE_SIZE_THRESHOLD).run();
        }
    }
    
    /**
     * <b>Precondition:</b> The MDL Models conversion tests have already been run.
     * <p>
     * The PharmML models generated from the MDL Models conversion are written to "mdl/[modelname]/output" subdirectories.
     * Find all the actual PharmML files (i.e. with .xml extension) within these directories (there may be data files etc.
     * in there too), and copy each of their containing folders (i.e. including the associated data files), to a suitably
     * named directory (i.e. taken from the name of the model in the path to the PharmML file) under a
     * "pharmml-generated-from-MDL" directory.
     * <p>
     * This is in preparation for the {@link ModelsDiscoverer} to be run over these PharmML files in the
     * "pharmml-generated-from-MDL" directory.
     */
    private void copyGeneratedPharmmlFiles() {
        
        Iterable<File> generatedPharmmlModelFiles = Iterables.filter(
            new ModelsDiscoverer(ORIGINAL_MDL_MODELS_SUBDIRECTORY, PHARMML_MODELS_FILE_EXTENSION).getAllModels(),
            new Predicate<File>() {
                /**
                 * Since .xml is a generic file extension, need to filter out any XML files that are not within a "mdl/[modelname]/output" subdirectory.
                 */
                public boolean apply(File input) {
                    return input.getParentFile().getName().equals(ConverterRunner.OUTPUT_SUBDIRECTORY);
                };
            }
        );
        
        for (final Iterator<File> it = generatedPharmmlModelFiles.iterator(); it.hasNext(); ) {
            final File srcDir = it.next().getParentFile();
            
            final File destDir = new File(srcDir.getParentFile().getAbsolutePath().replace(
                new File(ModelsDiscoverer.PATH_TO_MODELS_DIR + ORIGINAL_MDL_MODELS_SUBDIRECTORY).getAbsolutePath(),
                new File(ModelsDiscoverer.PATH_TO_MODELS_DIR + GENERATED_PHARMML_MODELS_SUBDIRECTORY).getPath()
            ));
            
            LOGGER.info("Copying from " + srcDir + " to " + destDir);
            try {
                FileUtils.copyDirectory(srcDir, destDir);
            } catch (final IOException ioe) {
                fail("Unable to copy generated PharmML models from " + srcDir + " to " + destDir + " - Cause: " + ioe);
            }
        }
        
    }
    
}
