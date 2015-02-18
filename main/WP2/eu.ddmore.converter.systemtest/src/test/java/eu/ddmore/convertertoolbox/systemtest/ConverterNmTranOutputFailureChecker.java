/**
 * 
 */
package eu.ddmore.convertertoolbox.systemtest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * A Converter Output Failure Checker for nmTran output; it enhances the standard Converter Output Failure
 * Checker to also check that the generated nonmem file is valid by comparing it with base line output file.
 */
class ConverterNmTranOutputFailureChecker extends DefaultConverterOutputFailureChecker {

    private static final String COMMENT_SYMBOL = ";";
    private static final String BLOCK_SYMBOL = "$";
    private static final Logger LOGGER = Logger.getLogger(ConverterNmTranOutputFailureChecker.class);
    // We'll consider a conversion to have failed if the converted output file has a size that is less than this number of bytes.
    // This is derived from the following minimal skeleton NMTRAN file:
    // $PROB $INPUT $DATA
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

            File baselineNmTranFile = new File(ModelsDiscoverer.PATH_TO_MODELS_DIR+File.separator+NMTRAN_DIR+File.separator+
                FileType.NMTRAN.getVersion()+File.separator+outputParentDir.getName()+File.separator+outputFileName);

            if(baselineNmTranFile.exists()){
                Map<String, StringBuilder> outputNmTranBlocks = getFileContentWithoutComments(expectedOutputNmTranFile);
                Map<String, StringBuilder> baselineNmTranBlocks = getFileContentWithoutComments(baselineNmTranFile);

                for(String block : baselineNmTranBlocks.keySet()){
                    String actualNmTranBlock = (outputNmTranBlocks.get(block)).toString();
                    String expectedNmTranBlock = (baselineNmTranBlocks.get(block)).toString();
                    assertEquals("The output block content should match for Block :"+block, expectedNmTranBlock, actualNmTranBlock);
                }
                
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
     * 
     * @param  expectedOutputNmTranFile
     * @return StringBuilder file content 
     * @throws IOException
     */
    private Map<String, StringBuilder> getFileContentWithoutComments(File expectedOutputNmTranFile) throws IOException{
        Map<String, StringBuilder> blocks = new HashMap<String, StringBuilder>();
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(expectedOutputNmTranFile)));
            blocks = getAllNmTranBlocksFromFile(reader);
        }finally{
            reader.close();
        }
        return blocks;
    }

    /**
     * Retrieves map nmtran blocks from the file without comments in the file. 
     * 
     * @param reader
     * @return Map<String, StringBuilder> blocks with block content
     * @throws IOException
     */
    private Map<String, StringBuilder> getAllNmTranBlocksFromFile(BufferedReader reader) throws IOException {
        Map<String, StringBuilder> blocks = new HashMap<String, StringBuilder>();
        String nextLine = null;
        StringBuilder fileContent = new StringBuilder();
        String blockName = new String();
        
        while((nextLine = reader.readLine())!=null){
            if(nextLine.isEmpty() || nextLine.startsWith(COMMENT_SYMBOL)){
                continue;
            }else if(nextLine.startsWith(BLOCK_SYMBOL)) {
                if(!(blockName.isEmpty() || fileContent.toString().isEmpty())){
                    blocks.put(blockName, fileContent);
                    fileContent = new StringBuilder();
                    blockName = new String();
                }
                blockName = getBlockName(nextLine);
            }
            fileContent.append(nextLine.trim()+ System.getProperty("line.separator"));
        }
        return blocks;
    }

    /**
     * Returns block name from the line provided. It removes block symbol prefix 
     * and also removes block content added on the same line.
     * 
     * @param nextLine
     * @return symbol The block symbol
     */
    private String getBlockName(String nextLine) {
        if(!nextLine.isEmpty() && nextLine.startsWith(BLOCK_SYMBOL)){
            String symbol = StringUtils.split(nextLine)[0];
            return symbol.replaceFirst("["+BLOCK_SYMBOL+"]", "");
        }
        return new String();
    }
}
