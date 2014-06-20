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
     * return an {@link Iterable} of Arrays, even though we only have one parameter and hence
     * would much prefer to return an {@link Iterable} of {@link File}s.
     * <p>
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
