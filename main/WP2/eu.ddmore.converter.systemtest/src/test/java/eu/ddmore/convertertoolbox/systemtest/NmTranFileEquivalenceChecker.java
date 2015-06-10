/**
 * 
 */
package eu.ddmore.convertertoolbox.systemtest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * A Converter Output Failure Checker for nmTran output; it enhances the standard Converter Output Failure
 * Checker to also check that the generated nonmem file is valid by comparing it with base line output file.
 */
class NmTranFileEquivalenceChecker extends DefaultConverterOutputFailureChecker {

    private static final String COMMENT_SYMBOL = ";";
    private static final String BLOCK_SYMBOL = "$";
    private static final Logger LOGGER = Logger.getLogger(NmTranFileEquivalenceChecker.class);
    // We'll consider a conversion to have failed if the converted output file has a size that is less than this number of bytes.
    // This is derived from the following minimal skeleton NMTRAN file:
    // $PROB $INPUT $DATA
    private final static int NMTRAN_FILE_SIZE_THRESHOLD = 30;
    private final String NMTRAN_DIR = "NM-TRAN";

    NmTranFileEquivalenceChecker() {
        super(NMTRAN_FILE_SIZE_THRESHOLD);
    }

    @Override
    public void check(File generatedOutput, File stdout, File stderr) {
        super.check(generatedOutput, stdout, stderr);
        try {
            String outputFileName = generatedOutput.getName();
            File outputParentDir = generatedOutput.getParentFile().getParentFile();
            File baselineNmTranFile = Paths.get(ModelsDiscoverer.PATH_TO_MODELS_DIR,NMTRAN_DIR,
                FileType.NMTRAN.getVersion(),outputParentDir.getName(),outputFileName).toFile();
//            File baselineNmTranFile = new File(ModelsDiscoverer.PATH_TO_MODELS_DIR+File.separator+NMTRAN_DIR+File.separator+
//                FileType.NMTRAN.getVersion()+File.separator+outputParentDir.getName()+File.separator+outputFileName);

            if(baselineNmTranFile.exists()){
                Map<String, String> outputNmTranBlocks = getNmtranBlocks(generatedOutput);
                Map<String, String> baselineNmTranBlocks = getNmtranBlocks(baselineNmTranFile);

                for(String block : baselineNmTranBlocks.keySet()){
                    String actualNmTranBlock = outputNmTranBlocks.get(block);
                    String expectedNmTranBlock = baselineNmTranBlocks.get(block);
                    outputNmTranBlocks.remove(block);
                    assertEquals("The output block content should match for Block :"+block, expectedNmTranBlock, actualNmTranBlock);
                }
                assertTrue("There should not be any redundant nmtran blocks",outputNmTranBlocks.isEmpty());

            }else{
                fail("Base line nmTran file doesn't exist " + generatedOutput);   
            }

        } catch (Exception e) {
            LOGGER.error("Exception thrown while parsing NmTran from file : " + generatedOutput);
            LOGGER.error("Exception details : ", e);
            fail("Error parsing NmTran from file " + generatedOutput);
        }
    }

    /**
     * Returns file content without comments added at start which are file specific to each output file.
     * 
     * @param  expectedOutputNmTranFile
     * @return StringBuilder file content 
     * @throws IOException
     */
    private Map<String, String> getNmtranBlocks(File expectedOutputNmTranFile) throws IOException{

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(expectedOutputNmTranFile)));){
            return getNmTranBlocks(reader);
        }
    }

    /**
     * Retrieves map nmtran blocks from the file without comments in the file. 
     * 
     * @param reader
     * @return Map<String, StringBuilder> blocks with block content
     * @throws IOException
     */
    private Map<String, String> getNmTranBlocks(BufferedReader reader) throws IOException {
        Map<String, String> blocks = new HashMap<String, String>();
        String nextLine = null;
        StringBuilder fileContent = new StringBuilder();
        String blockName = new String();

        while((nextLine = reader.readLine())!=null){
            if(nextLine.isEmpty() || nextLine.startsWith(COMMENT_SYMBOL)){
                continue;
            }else if(nextLine.startsWith(BLOCK_SYMBOL)) {
                if(!(blockName.isEmpty() || fileContent.toString().isEmpty())){
                    blocks.put(blockName, fileContent.toString());
                }
                fileContent = new StringBuilder();
                blockName = getBlockName(nextLine);
            }
            fileContent.append(nextLine.trim()+ System.getProperty("line.separator"));
        }
        if(!(blockName.isEmpty() || fileContent.toString().isEmpty())){
            blocks.put(blockName, fileContent.toString());
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
