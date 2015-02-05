/**
 * 
 */
package eu.ddmore.convertertoolbox.systemtest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * A Converter Output Failure Checker for JSON output; it enhances the standard Converter Output Failure
 * Checker to also check that the generated JSON is valid i.e. can be parsed successfully.
 */
class ConverterJsonOutputFailureChecker extends DefaultConverterOutputFailureChecker {

    private static final Logger LOGGER = Logger.getLogger(ConverterJsonOutputFailureChecker.class);

    // We'll consider a conversion to have failed if the converted output file has a size that is less than this number of bytes.
    // This is derived from the following minimal skeleton JSON representation:
    // [{"d":{"identifier":"dataobj"},"p":{"identifier":"parobj"},"m":{"identifier":"mdlobj"},"t":{"identifier":"taskobj"}}]
    private final static int JSON_FILE_SIZE_THRESHOLD = 117;

    ConverterJsonOutputFailureChecker() {
        super(JSON_FILE_SIZE_THRESHOLD);
    }

    @Override
    public void check(File expectedOutputJsonFile, File stdoutFile, File stderrFile) {
        super.check(expectedOutputJsonFile, stdoutFile, stderrFile);
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final JsonNode jsonAsTree = mapper.readTree(expectedOutputJsonFile);
            assertNotNull("Checking that the JSON from file " + expectedOutputJsonFile
                + " was valid JSON i.e. was able to be parsed successfully", jsonAsTree);
            LOGGER.debug(jsonAsTree);
        } catch (Exception e) {
            LOGGER.error(
                "Exception \"" + e.getMessage() + "\" thrown parsing JSON from file " + expectedOutputJsonFile, e);
            fail("Error parsing JSON from file " + expectedOutputJsonFile);
        }
    }

}
