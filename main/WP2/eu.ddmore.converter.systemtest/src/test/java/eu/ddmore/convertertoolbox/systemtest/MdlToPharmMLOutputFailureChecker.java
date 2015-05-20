package eu.ddmore.convertertoolbox.systemtest;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;


/**
 * A Converter Output Failure Checker for checking the result of an MDL->PharmML conversion;
 * it enhances the standard Converter Output Failure Checker to *not* fail the conversion if
 * reported error relates to the absence of a MOG in the input MDL file (since this is still
 * valid MCL inasmuch as it can be read in to TEL).
 */
public class MdlToPharmMLOutputFailureChecker extends DefaultConverterOutputFailureChecker {

    private final static Logger LOGGER = Logger.getLogger(MdlToPharmMLOutputFailureChecker.class);

    // We'll consider a conversion to have failed if the converted output file has a size that is less than this number of bytes
    private final static int PHARMML_FILE_SIZE_THRESHOLD = 638;

    MdlToPharmMLOutputFailureChecker() {
        super(PHARMML_FILE_SIZE_THRESHOLD);
    }
    
    /**
     * {@inheritDoc}
     */
    public void check(final File expectedOutputFile, final File stdoutFile, final File stderrFile) {
        try {
            final List<String> stderrLines = FileUtils.readLines(stderrFile);
            if (stderrLines.size() > 0 && stderrLines.get(0).startsWith(
                    "Exception in thread \"main\" java.lang.IllegalStateException: Must be (at least) one MOG defined in the provided MCL file")) {
                // Skip the failure checking for this known failure
                LOGGER.warn("Skipping the checking of the MDL->PharmML conversion for file " + expectedOutputFile + ", since the parsing failed due to the absence of a MOG in the MDL file, but this is valid MCL.");
                return;
            }
        } catch (IOException ioe) {
            throw new RuntimeException("Unable to read stderr file: " + stderrFile, ioe);
        }

        super.check(expectedOutputFile, stderrFile, stderrFile);
    }

}
