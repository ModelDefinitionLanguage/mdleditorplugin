/* File			:  ExecutionHelperTest.java
 * Project		:  MIFServer
 * Created on	:  May 11, 2012
 */
package com.mango.mif.sge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.sge.SGEOutput;
import com.mango.mif.sge.SGEOutputParser;
import com.mango.mif.sge.SGEOutputSearcher;


/**
 * 
 * Tests SGE output parser
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class SGEOutputParserTest {
	/**
	 * LOG
	 */
    private final static Logger LOG = Logger.getLogger(SGEOutputParserTest.class);
    /**
     * output parser
     */
    SGEOutputParser outputParser;

    @Before
    public void setup() throws IOException {
    	Properties properties = new Properties();
    	properties.load(SGEOutputParserTest.class.getResourceAsStream("/com/mango/mif/sgeconnector/connector.properties"));
    	
    	
    	
        SGEOutputSearcher searcher = new SGEOutputSearcher();
        
        searcher.setJobAccountingNotFoundRegexp(properties.getProperty("sgeconnector.regexp.queue-accounting-command.job-not-found"));
        searcher.setJobAccountingRegexp(properties.getProperty("sgeconnector.regexp.queue-accounting-command.extract-info"));

        searcher.setJobStatusNotFoundRegexp(properties.getProperty("sgeconnector.regexp.queue-status-command.job-not-found"));
        searcher.setJobStatusRegexp(properties.getProperty("sgeconnector.regexp.queue-status-command.extract-info"));

        searcher.setJobDeleteNotFoundRegexp(properties.getProperty("sgeconnector.regexp.queue-delete-command.job-not-found"));
        searcher.setJobDeletionRegexp(properties.getProperty("sgeconnector.regexp.queue-delete-command.extract-info"));

        searcher.setJobSubmittedExtractJobIdRegexp(properties.getProperty("sgeconnector.regexp.queue-submit-command.extract-job-id"));
        
        searcher.setCommandNotFoundRegexp(properties.getProperty("sgeconnector.regexp.commandNotFound"));
        searcher.setInvalidUsageRegexp(properties.getProperty("sgeconnector.regexp.invalidUsage"));
        
        
        outputParser = new SGEOutputParser();
        outputParser.setSearcher(searcher);
    }

    @Test
    public void shouldMatchSuccessfulSubmission() throws ExecutionException  {

        String[] testMessages = new String[] {
                "Your job 6585 (\"test.sh\") has been submitted",
                "Your job 6585 any text",
                "Your job 6585"
        };

        for(String s : testMessages) {
            LOG.info("Matching " + s);
            SGEOutput output = outputParser.parseSubmitMessage(s);
            assertNotNull(output.get(SGEOutput.JOB_ID_KEY));
            assertEquals("6585",output.get(SGEOutput.JOB_ID_KEY));
        }
    }

    @Test
    public void shouldNotMatchUnsuccessfulSubmission() throws ExecutionException  {

        String[] testMessages = new String[] {
                "Unable to read script file because of error: error opening test1.sh: No such file or directory",
        };

        for(String s : testMessages) {
            LOG.info("Matching " + s);
            SGEOutput output = outputParser.parseSubmitMessage(s);
            printData(output.getData());
            assertTrue(StringUtils.isEmpty(output.get(SGEOutput.JOB_ID_KEY)));
            assertEquals(SGEOutput.JOB_SUBMISSION_FAILED,output.get(SGEOutput.SGE_COMMAND_RESULT_KEY));
        }
    }

    @Test
    public void shouldReturnProcessingStatusAndStatusInfo() throws IOException, ExecutionException {

        String s = FileUtils.readFileToString(FileUtils.toFile(SGEOutputParserTest.class.getResource("qstat.txt")));
        SGEOutput output = outputParser.parseStatusMessage(s);
        printData(output.getData());
        assertEquals(SGEOutput.JOB_PROCESSING,output.get(SGEOutput.SGE_COMMAND_RESULT_KEY));
        Map<String, String> map = output.getData();
        assertTrue(map.containsKey("owner"));
        assertTrue(map.get("owner").equals("mrogalski"));
        assertTrue(map.containsKey("sge_o_workdir"));
        assertTrue(map.get("sge_o_workdir").equals("/net/navtst-nonmem/usr/global/mrogalski"));
        assertTrue(map.containsKey("env_list"));
        assertTrue(map.get("env_list").equals(""));
        assertTrue(map.containsKey("submission_time"));
        assertTrue(map.get("submission_time").equals("Sat May 12 20:07:00 2012"));
    }
    
    @Test
    public void shouldReturnErrorReason() throws IOException, ExecutionException {
        String s = FileUtils.readFileToString(FileUtils.toFile(SGEOutputParserTest.class.getResource("qstat-Eqw.txt")));
        SGEOutput output = outputParser.parseStatusMessage(s);
        printData(output.getData());
        assertEquals(SGEOutput.JOB_PROCESSING,output.get(SGEOutput.SGE_COMMAND_RESULT_KEY));
        Map<String, String> map = output.getData();
        assertTrue(map.get("error reason    1").equals("07/25/2012 09:57:03 [10032:27395]: error: can't chdir to /net/navtst-nonmem/usr/global/mrogalski/sge"));
    }
    
    @Test
    public void shouldReturnJobNotFoundFromQstat() throws IOException, ExecutionException {
        String s = FileUtils.readFileToString(FileUtils.toFile(SGEOutputParserTest.class.getResource("qstat-JobNotFound.txt")));
        LOG.info("Matching " + s);
        SGEOutput output = outputParser.parseStatusMessage(s);
        printData(output.getData());
        assertEquals(SGEOutput.JOB_NOT_FOUND,output.get(SGEOutput.SGE_COMMAND_RESULT_KEY));
    }

    @Test
    public void shouldReturnJobNotFoundFromQacct() throws ExecutionException {

        String[] testMessages = new String[] {
                "error: job id 6607 not found",
                "error: job id 7779 not found\n"
        };

        for(String s : testMessages) {
            LOG.info("Matching " + s);
            SGEOutput output = outputParser.parseAccountingMessage(s);
            printData(output.getData());
            assertEquals(SGEOutput.JOB_NOT_FOUND,output.get(SGEOutput.SGE_COMMAND_RESULT_KEY));
        }
    }

    @Test
    public void shouldReturnJobNotFoundFromQdel() throws ExecutionException  {

        String[] testMessages = new String[] {
                "denied: job \"00001\" does not exist",
                "denied: job \"1234\" does not exist\n"
        };

        for(String s : testMessages) {
            LOG.info("Matching " + s);
            SGEOutput output = outputParser.parseDeleteMessage(s);
            printData(output.getData());
            assertEquals(SGEOutput.JOB_NOT_FOUND,output.get(SGEOutput.SGE_COMMAND_RESULT_KEY));
        }
    }

    @Test
    public void shouldSeeJobDeletedMessage() throws ExecutionException {

        String[] testMessages = new String[] {
                "user has deleted job 7809",
                "user has deleted job 7809\n"
        };

        for(String s : testMessages) {
            LOG.info("Matching " + s);
            SGEOutput output = outputParser.parseDeleteMessage(s);
            printData(output.getData());
            assertEquals(SGEOutput.JOB_DELETED,output.get(SGEOutput.SGE_COMMAND_RESULT_KEY));
        }
    }

    @Test
    public void shouldSeeJobFinishedFromQacct() throws IOException, ExecutionException {
        String s = FileUtils.readFileToString(FileUtils.toFile(SGEOutputParserTest.class.getResource("qacct.txt")));
        SGEOutput output = outputParser.parseAccountingMessage(s);
        printData(output.getData());
        assertEquals(SGEOutput.JOB_FINISHED,output.get(SGEOutput.SGE_COMMAND_RESULT_KEY));
        Map<String, String> map = output.getData();
        assertTrue(map.get(QacctOutputJobProperty.QUEUE_NAME.getToken()).equals("all.q"));
        assertTrue(map.get(QacctOutputJobProperty.OWNER.getToken()).equals("mrogalski"));
        assertTrue(map.get(QacctOutputJobProperty.JOB_NAME.getToken()).equals("test.sh"));
        assertTrue(map.get(QacctOutputJobProperty.JOB_NUMBER.getToken()).equals("6607"));
        assertTrue(map.get(QacctOutputJobProperty.HOST_NAME.getToken()).equals("navtst-nonmemc.mango.local"));
        assertTrue(map.get(QacctOutputJobProperty.QSUB_TIME.getToken()).equals("Sat May 12 18:55:11 2012"));
        assertTrue(map.get(QacctOutputJobProperty.START_TIME.getToken()).equals("Sat May 12 18:55:14 2012"));
        assertTrue(map.get(QacctOutputJobProperty.END_TIME.getToken()).equals("Sat May 12 18:55:15 2012"));
        assertTrue(map.get(QacctOutputJobProperty.FAILED.getToken()).equals("0"));
        assertTrue(map.get(QacctOutputJobProperty.EXIT_STATUS.getToken()).equals("1"));
    }


    @Test(expected = ExecutionException.class)
    public void shouldThrowExceptionIfCommandNotFound() throws IOException, ExecutionException {
        String s = FileUtils.readFileToString(FileUtils.toFile(SGEOutputParserTest.class.getResource("qstat-commandNotFound.txt")));
        outputParser.parseStatusMessage(s);
    }

    @Test(expected = ExecutionException.class)
    public void shouldThrowExceptionIfIncorrectUsageOfSGECommand() throws IOException, ExecutionException {
        String s = FileUtils.readFileToString(FileUtils.toFile(SGEOutputParserTest.class.getResource("qstat-InvalidUsage.txt")));
        outputParser.parseStatusMessage(s);
    }
    /**
     * prints all the entries from the map
     * @param data
     */
    private void printData(Map<String,String> data) {
        LOG.debug("================================================================================");
        LOG.debug("Entries:");
        for(Entry<String,String> en : data.entrySet()) {
            LOG.debug(en.getKey() + ":" + en.getValue());
        }
        LOG.debug("================================================================================");
    }
}
