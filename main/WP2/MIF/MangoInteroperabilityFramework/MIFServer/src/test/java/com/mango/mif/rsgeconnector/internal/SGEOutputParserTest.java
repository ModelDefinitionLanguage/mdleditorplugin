/* File			:  ExecutionHelperTest.java
 * Project		:  MIFServer
 * Created on	:  May 11, 2012
 */
package com.mango.mif.rsgeconnector.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;


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
    private final static Logger LOG = Logger.getLogger(SGEOutputParserTest.class);

    SGEOutputParser outputParser;

    @Before
    public void setup() {
        SGEOutputSearcher searcher = new SGEOutputSearcher();

        searcher.setJobAccountingNotFoundRegexp("error: job id \\d+ not found");
        searcher.setJobAccountingRegexp("([^\\s]+)\\s+(.*)");

        searcher.setJobStatusNotFoundRegexp("Following jobs do not exist.*");
        searcher.setJobStatusRegexp("([^:]+):\\s*(.*)");

        searcher.setJobDeleteNotFoundRegexp("does not exist");
        searcher.setJobDeletionRegexp("registered the job \\d+ for deletion");

        searcher.setJobSubmittedExtractJobIdRegexp("Your job ([\\d]+)([^\\d].+)?");

        outputParser = new SGEOutputParser();
        outputParser.setSearcher(searcher);
    }

    @Test
    public void shouldMatchSuccessfulSubmission() {

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
    public void shouldNotMatchUnsuccessfulSubmission() {

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
    public void shouldReturnProcessingStatusAndStatusInfo() throws IOException {

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
    public void shouldReturnJobNotFoundFromQstat() throws IOException {
        String s = FileUtils.readFileToString(FileUtils.toFile(SGEOutputParserTest.class.getResource("qstat-JobNotFound.txt")));
        LOG.info("Matching " + s);
        SGEOutput output = outputParser.parseStatusMessage(s);
        printData(output.getData());
        assertEquals(SGEOutput.JOB_NOT_FOUND,output.get(SGEOutput.SGE_COMMAND_RESULT_KEY));
    }

    @Test
    public void shouldReturnJobNotFoundFromQacct() {

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
    public void shouldReturnJobNotFoundFromQdel() {

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
    public void shouldSeeJobDeletedMessage() {

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
    public void shouldSeeJobFinishedFromQacct() throws IOException {
        String s = FileUtils.readFileToString(FileUtils.toFile(SGEOutputParserTest.class.getResource("qacct.txt")));
        SGEOutput output = outputParser.parseAccountingMessage(s);
        printData(output.getData());
        assertEquals(SGEOutput.JOB_FINISHED,output.get(SGEOutput.SGE_COMMAND_RESULT_KEY));
        Map<String, String> map = output.getData();
        assertTrue(map.containsKey("owner"));
        assertTrue(map.get("owner").equals("mrogalski"));
        assertTrue(map.containsKey("jobname"));
        assertTrue(map.get("jobname").equals("test.sh"));
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
