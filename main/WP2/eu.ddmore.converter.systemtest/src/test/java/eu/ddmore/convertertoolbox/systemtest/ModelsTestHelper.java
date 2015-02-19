package eu.ddmore.convertertoolbox.systemtest;

import java.io.File;

import org.junit.runners.Parameterized;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;

import eu.ddmore.convertertoolbox.systemtest.ModelsDiscoverer;


public class ModelsTestHelper {

    /**
     * The method that produces the parameters to be passed to each construction of the calling test class.
     * In this case, the {@link File}s that are the models for which to test the conversion.
     * <p>
     * NB: The JUnit {@link Parameterized} framework requires the parameter-providing method to
     * return an {@link Iterable} of Arrays.
     * <p>
     * @param modelsSubdirectory - subdirectory of target/WorkingDir/eu/ddmore/testdata/models from which models will be discovered
     * @param modelsFileExtension - the file extension, excluding the dot, of the model files
     * @return the models to convert, as {@link File} objects
     */
    public static Iterable<Object[]> getModelsToTest(final String modelsSubdirectory, final String modelsFileExtension) {
        return Iterables.transform(
            new ModelsDiscoverer(modelsSubdirectory, modelsFileExtension).getAllModels(),
            new Function<File, Object[]>() {
                public Object[] apply(final File input) {
                    return new Object[] { input, input.getPath().substring(ModelsDiscoverer.PATH_TO_MODELS_DIR.length()) };
                };
            }
        );
    }
    
}
