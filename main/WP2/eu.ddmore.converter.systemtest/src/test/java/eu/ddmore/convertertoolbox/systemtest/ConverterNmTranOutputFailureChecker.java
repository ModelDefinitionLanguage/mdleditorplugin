/**
 * 
 */
package eu.ddmore.convertertoolbox.systemtest;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import eu.ddmore.convertertoolbox.systemtest.FileType;

/**
 * A Converter Output Failure Checker for nmTran output; it enhances the standard Converter Output Failure
 * Checker to also check that the generated nonmem file is valid by comparing it with base line output file.
 */
class ConverterNmTranOutputFailureChecker extends DefaultConverterOutputFailureChecker {

    private static final Logger LOGGER = Logger.getLogger(ConverterNmTranOutputFailureChecker.class);
    private final static int NMTRAN_FILE_SIZE_THRESHOLD = 30;
    private final String NMTRAN_DIR = "NM-TRAN";

    ConverterNmTranOutputFailureChecker() {
        super(NMTRAN_FILE_SIZE_THRESHOLD);
    }

    @Override
    public void check(File expectedOutputNmTranFile, File stdoutFile, File stderrFile) {
        super.check(expectedOutputNmTranFile, stdoutFile, stderrFile);
        try {
            String outputFileName = expectedOutputNmTranFile.getName();
            File outputParentDir = expectedOutputNmTranFile.getParentFile().getParentFile();
            
            File nmTranFile = new File(ModelsDiscoverer.PATH_TO_MODELS_DIR+File.separator+NMTRAN_DIR+File.separator+
                FileType.NMTRAN.getVersion()+File.separator+outputParentDir.getName()+File.separator+outputFileName);

            if(nmTranFile.exists()){
                StringBuilder tempOutputFileContent = getFileContentWithoutComments(expectedOutputNmTranFile);
                StringBuilder tempNmTranFileContent = getFileContentWithoutComments(nmTranFile);
                
                assertTrue("Files should match", StringUtils.equals(tempOutputFileContent.toString(), tempNmTranFileContent.toString()));
            }else{
                fail("Base line nmTran file doesn't exist " + expectedOutputNmTranFile);   
            }
            
        } catch (Exception e) {
            LOGGER.error(
                "Exception \"" + e.getMessage() + "\" thrown parsing NmTran from file " + expectedOutputNmTranFile);
            fail("Error parsing NmTran from file " + expectedOutputNmTranFile);
        }
    }

    /**
     * Returns file content without comments added at start which are file specific to each output file.
     * @param expectedOutputNmTranFile
     * @return
     * @throws IOException
     */
    private StringBuilder getFileContentWithoutComments(File expectedOutputNmTranFile) throws IOException{
        StringBuilder fileContent = new StringBuilder();
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(expectedOutputNmTranFile)));
            String nextLine = null;
            while((nextLine = reader.readLine())!=null){
                if(!nextLine.startsWith(";")){
                    fileContent.append(nextLine + System.getProperty("line.separator"));
                }
            }
        }finally{
            reader.close();
        }
        return fileContent;
    }
}
