/* File			:  NONMEMDetailedStatusBuilder.java
 * Project		:  MIFServer
 * Created on	:  Aug 16, 2012
 */
package com.mango.mif.nonmemsgeconnector;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.mango.mif.connector.runner.status.GenericJobDetailedStatusBuilderWithTemplatedSummaryMessage;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.InvokerResult;
import com.mango.mif.core.exec.invoker.InvokerHelper;
import com.mango.mif.domain.DetailedStatus;
import com.mango.mif.domain.nonmemsgeconnector.NONMEMProcessingDetailedStatus;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * We get the status of a NONMEM or PsN job.
 */
public class NONMEMDetailedStatusBuilder extends GenericJobDetailedStatusBuilderWithTemplatedSummaryMessage {

    private static final String USERNAME = "username";
    private static final String GRIDJOBID = "gridjobid";
    private static final String CONTROLFILENAME = "controlfilename";
    private static final String WORKINGDIRECTORY = "workingdirectory";
    private static final String REQUESTDIRECTORY = "requestdirectory";
    private static final String GRIDJOBDIRECTORY = "gridjobdirectory";
    private static final String PROBLEM = "problem";
    private static final String DATAFILENAME = "datafilename";
    private static final String DURATION = "duration";
    private static final String STATUS = "status";
    private static final String STARTTIME = "starttime";
    private static final String LASTUPDATED = "lastupdated";

    protected static final Logger logger = Logger.getLogger(NONMEMDetailedStatusBuilder.class);

    @Override
    public DetailedStatus getDetailedStatus() throws ExecutionException {

        Preconditions.checkNotNull(getInvoker(), "getDetailedStatus found the invoker was null");

        NONMEMProcessingDetailedStatus result = new NONMEMProcessingDetailedStatus();
        String command = summaryMessageBuilder.getCommand();

        InvokerHelper invokerHelper = new InvokerHelper(getInvoker());
        InvokerResult results = invokerHelper.runAndReportFailures(command);
        if (InvokerHelper.success(results)) {
            logger.trace("Command was successful, the output is:");
            logger.trace(results.getStdout());
            logger.trace("the errors (for debugging purposes):");
            logger.trace(results.getStderr());
            //FIXME Refactor so it uses NONMEMProcessingDetailedStatusMarshaller
            parse(results.getStdout(), result);

            // I think the summary and the status are the same thing,
            // but I am confused.
            result.setSummary(result.getStatus());
        }
        return result;
    }

    /**
     * Messy, oh so messy, parsing of script results into object.
     * 
     * @param stuff The stuff to parse.
     * @param detailedStatus The place to put the stuff.
     * 
     */
    @VisibleForTesting
    public void parse(String stuff, NONMEMProcessingDetailedStatus detailedStatus) {
        Pattern pattern = Pattern.compile("([^:]+):\\s+(.*)");
        Scanner scanner = new Scanner(stuff).useDelimiter("\n");
        try {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String key = matcher.group(1);
                    String value = matcher.group(2);

                    assign(key, value, detailedStatus);
                }
            }
        } finally {
            scanner.close();
        }
    }

    /**
     * Messy, oh so messy, parsing of value into object member.
     * 
     * @param key The key, identifying where we should put the value into the object.
     * @param value The value to put in the object.
     * @param detailedStatus The object to put the stuff into
     */
    @VisibleForTesting
    public void assign(String key, String value, NONMEMProcessingDetailedStatus detailedStatus) {

        if (StringUtils.isBlank(key)) {
            return;
        }
        key = key.toLowerCase();

        if (USERNAME.equals(key)) {
            detailedStatus.setUserName(value);
        } else if (GRIDJOBID.equals(key)) {
            detailedStatus.setGridJobId(value);
        } else if (CONTROLFILENAME.equals(key)) {
            detailedStatus.setControlFileName(value);
        } else if (WORKINGDIRECTORY.equals(key)) {
            detailedStatus.setWorkingDirectory(value);
        } else if (REQUESTDIRECTORY.equals(key)) {
            detailedStatus.setRequestDirectory(value);
        } else if (GRIDJOBDIRECTORY.equals(key)) {
            detailedStatus.setGridJobDirectory(value);
        } else if (PROBLEM.equals(key)) {
            detailedStatus.setProblem(value);
        } else if (DATAFILENAME.equals(key)) {
            detailedStatus.setDataFileName(value);
        } else if (DURATION.equals(key)) {
            detailedStatus.setDuration(value);
        } else if (STATUS.equals(key)) {
            detailedStatus.setStatus(value);
        } else if (STARTTIME.equals(key)) {
            detailedStatus.setStartTime(value);
        } else if (LASTUPDATED.equals(key)) {
            detailedStatus.setLastUpdated(value);
        } else {
            logger.error("Unable to assign key \"" + key + "\" value \"" + value + " to " + detailedStatus.getClass().getName() + " because the key is not recognised");
        }
    }
}
