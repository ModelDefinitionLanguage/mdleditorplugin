package eu.ddmore.convertertoolbox.systemtest;

import java.io.File;

import org.apache.log4j.Logger;

import eu.ddmore.converter.mdl2json.ConverterTestsParent;

/**
 * A Converter Output Failure Checker for JSON->MDL conversion; it enhances the standard Converter Output
 * Failure Checker to also check for syntactic and semantic equivalence against a baseline MDL file.
 */
class MdlFileEquivalenceChecker extends DefaultConverterOutputFailureChecker {

    private static final Logger LOGGER = Logger.getLogger(MdlFileEquivalenceChecker.class);

    // We'll consider a conversion to have failed if the converted output file has a size that is less than this number of bytes.
    // This is derived from the following minimal skeleton MDL file:
    // d=dataobj{}
    // p=parobj{}
    // m=mdlobj{}
    // t=taskobj{}
    private final static int MDL_FILE_SIZE_THRESHOLD = 45;
    
    private final File baselineMdlFile;

    /**
     * Constructor.
     * @param baselineMdlFile - the baseline MDL file against which to check the generated MDL file
     */
    MdlFileEquivalenceChecker(final File baselineMdlFile) {
        super(MDL_FILE_SIZE_THRESHOLD);
        this.baselineMdlFile = baselineMdlFile;
    }

    @Override
    public void check(File expectedOutputMdlFile, File stdoutFile, File stderrFile) {
        super.check(expectedOutputMdlFile, stdoutFile, stderrFile);
        for (final String blockName : ConverterTestsParent.allBlockNames) {
            ConverterTestsParent.extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(this.baselineMdlFile, blockName, expectedOutputMdlFile);
        }
    }

}