/* File			:  SGEOutputParser.java
 * Project		:  MIFServer
 * Created on	:  May 10, 2012
 */
package com.mango.mif.sge;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Preconditions;
import com.mango.mif.core.exec.ExecutionException;

/**
 * 
 * Class responsible for parsing output messages from the SGE commands
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class SGEOutputParser {

    private SGEOutputSearcher searcher;

    /**
     * Validates the output
     * @param message
     * @throws ExecutionException 
     */
    private void captureErrorMessage(String message) throws ExecutionException {
    	if(searcher.matchInvalidUsage(message)) throw new ExecutionException("Invalid command usage: " + message);
    	if(searcher.matchCommandNotFound(message)) throw new ExecutionException("Error: " + message);
    }
    /**
     * Parses submit message
     * @param message
     * @return
     * @throws ExecutionException 
     */
    public SGEOutput parseSubmitMessage(String message) throws ExecutionException {
        Preconditions.checkNotNull(message);
        captureErrorMessage(message);
        SGEOutput output = new SGEOutput(SGEOutput.SGEOutputType.Submit);
        String jobID = captureGridJobID(message);
        output.put(SGEOutput.OUTPUT_MESSAGE_KEY,message);
        output.put(SGEOutput.JOB_ID_KEY, jobID);
        if(StringUtils.isEmpty(jobID)) {
            output.put(SGEOutput.SGE_COMMAND_RESULT_KEY, SGEOutput.JOB_SUBMISSION_FAILED);
        } else {
            output.put(SGEOutput.SGE_COMMAND_RESULT_KEY, SGEOutput.JOB_SUBMITTED);
        }
        return output;
    }

    /**
     * Parses status message
     * @param message
     * @return
     * @throws ExecutionException 
     */
    public SGEOutput parseStatusMessage(String message) throws ExecutionException {
        Preconditions.checkNotNull(message);
        captureErrorMessage(message);
        SGEOutput output = new SGEOutput(SGEOutput.SGEOutputType.Status);
        String tmp = message.trim();
        output.put(SGEOutput.OUTPUT_MESSAGE_KEY, message);

        if (searcher.matchStatusJobNotFound(tmp)) {
            output.put(SGEOutput.SGE_COMMAND_RESULT_KEY, SGEOutput.JOB_NOT_FOUND);
            return output;
        }
        output.put(SGEOutput.SGE_COMMAND_RESULT_KEY, SGEOutput.JOB_PROCESSING);

        searcher.matchJobStatusOutput(tmp, output);
        return output;
    }

    /**
     * Parses grid accounting program message (i.e qacct)
     * @param message stuff from qacct command
     * @return SGEOuput object appropriate to input
     * @throws ExecutionException 
     */
    public SGEOutput parseAccountingMessage(String message) throws ExecutionException {
        Preconditions.checkNotNull(message);
        captureErrorMessage(message);
        String tmp = message.trim();
        SGEOutput output = new SGEOutput(SGEOutput.SGEOutputType.Summary);
        output.put(SGEOutput.OUTPUT_MESSAGE_KEY,message);

        if (searcher.matchAccountingJobNotFound(message)) {
            output.put(SGEOutput.SGE_COMMAND_RESULT_KEY, SGEOutput.JOB_NOT_FOUND);
            return output;
        }

        output.put(SGEOutput.SGE_COMMAND_RESULT_KEY, SGEOutput.JOB_FINISHED);
        searcher.matchJobAccountingOutput(tmp, output);
        return output;
    }

    /**
     * Parses the given string and returns job id
     * @param output
     * @return
     */
    private String captureGridJobID(String text) {
        String tmp = text.trim();

        String id = searcher.matchJobId(tmp);
        if (StringUtils.isBlank(id)) {
            return "";
        }
        return id;
    }

    /**
     * Always returns successful deletion output
     * @param message
     * @return
     * @throws ExecutionException 
     */
    public SGEOutput parseDeleteMessage(String message) throws ExecutionException {
        Preconditions.checkNotNull(message);
        captureErrorMessage(message);
        SGEOutput output = new SGEOutput(SGEOutput.SGEOutputType.Delete);
        output.put(SGEOutput.OUTPUT_MESSAGE_KEY,message);
        String tmp = message.trim();

        if (searcher.matchDeleteJobNotFound(tmp)) {
            output.put(SGEOutput.SGE_COMMAND_RESULT_KEY, SGEOutput.JOB_NOT_FOUND);
            return output;
        }

        output.put(SGEOutput.SGE_COMMAND_RESULT_KEY, SGEOutput.JOB_DELETED);
        String[] matches = searcher.matchJobDeletedOutput(tmp);
        if (matches != null && matches.length >= 3) {
            output.put(matches[1], matches[2]);
        }
        return output;
    }

    public SGEOutputSearcher getSearcher() {
        return searcher;
    }

    public void setSearcher(SGEOutputSearcher configRegexp) {
        this.searcher = configRegexp;
    }
    
}
