package eu.ddmore.convertertoolbox.systemtest;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;


/**
 * Class to heuristically and generically check the output from a converter execution,
 * i.e. the generated model file, the standard output file and the standard error file,
 * to attempt to determine if the conversion process failed.
 * <p>
 * At the very least, this checks that,
 * <ol>
 * <li>the expected generated model file exists
 * <li>the expected generated model file is larger than a specified threshhold size (number of bytes)
 * <li>the standard error output is empty
 * </ol>
 * <p>
 * <b>Note that even if this checking determines that the process didn't fail,
 * it doesn't mean that the conversion was actually successful, since garbage
 * could have been written out to the generated model file with no errors
 * produced, and this wouldn't know any different!</b>
 * <p>
 * Subclasses could extend this class to provide additional checks.
 */
public class DefaultConverterOutputFailureChecker implements ConverterOutputFailureChecker {
    private final static Logger LOGGER = Logger.getLogger(DefaultConverterOutputFailureChecker.class);

    // We'll consider a conversion to have failed if the converted output file has a size that is less than this number of bytes
    private final int outputFileSizeThreshhold;
    
    /**
     * Constructor providing suitable parameters to check against.
     * <p>
     * @param outputFileSizeThreshhold - We'll consider a conversion to have failed if the converted 
     *                                   output file has a size that is less than this number of bytes
     */
    DefaultConverterOutputFailureChecker(final int outputFileSizeThreshhold) {
        this.outputFileSizeThreshhold = outputFileSizeThreshhold;
    }
    
    /**
     * {@inheritDoc}
     */
    public void check(final File expectedOutputFile, final File stdoutFile, final File stderrFile) {
        if (FileUtils.sizeOf(stderrFile) > 0) {
            // Errors were produced onto the standard error stream
            try {
                fail("Standard error output was produced during the conversion process:\n--- BEGIN STDERR DUMP ---\n" + FileUtils.readFileToString(stderrFile) + "\n--- END STDERR DUMP ---");
            } catch (IOException e) {
                fail("IOException thrown while trying to read stderr file: " + e);
            }
        }
        if (! expectedOutputFile.exists()) {
            fail("Model file wasn't created: " + expectedOutputFile);
        }
        if (FileUtils.sizeOf(expectedOutputFile) < this.outputFileSizeThreshhold) {
            fail("Expected created model file too small to be valid: " + expectedOutputFile);
        }
        LOGGER.info("Conversion process successfully created model file: " + expectedOutputFile);
    }
    
}
