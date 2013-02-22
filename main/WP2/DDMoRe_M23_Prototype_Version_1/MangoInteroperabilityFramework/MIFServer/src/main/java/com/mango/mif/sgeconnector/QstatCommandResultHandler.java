/* File			:  QacctCommandResultHandler.java
 * Project		:  MIFServer
 * Created on	:  Jul 24, 2012
 */
package com.mango.mif.sgeconnector;

import java.text.ParseException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import javax.annotation.Nullable;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.InvokerResult;
import com.mango.mif.core.exec.invoker.InvokerHelper;
import com.mango.mif.sge.QstatJobOutput;
import com.mango.mif.sge.SGE;
import com.mango.mif.sge.SGEOutput;
import com.mango.mif.utils.CommonUtils;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * A Qstat command results handler
 */
public class QstatCommandResultHandler extends SGECommandResultHandler {
    /**
     * failure event
     */
    private final String failureEvent;
    /**
     * success event
     */
    private final String successEvent;
    /**
     * A key of a property holding an error message
     */
    private Pattern compiledErrorPropertyKeyPattern;
    /**
     * Number of milliseconds to wait before re-running poll command
     */
    private Long pollingRate = 5000l;

    /**
     * Constructor
     * @param successEvent
     */
    public QstatCommandResultHandler(String successEvent, String failureEvent) {
        super(successEvent);
        this.successEvent = successEvent;
        this.failureEvent = failureEvent;
    }

    @Override
    public void handle(InvokerResult invokerResult) throws ExecutionException {
        try {
            super.handle(invokerResult);
        } catch (ExecutionException e) {
            //we just invoke the parent to check preconditions
            //the ExecutionException here is normal when a job has just finished, we don't log it as error
        }
        SGEOutput output = null;
        if (InvokerHelper.success(invokerResult)) {
            output = outputParser.parseStatusMessage(invokerResult.getOutputStream());
        } else {
            output = outputParser.parseStatusMessage(invokerResult.getErrorStream());
        }

        if (SGEOutput.JOB_PROCESSING.equals(output.get(SGEOutput.SGE_COMMAND_RESULT_KEY))) {
            String submissionTime = getSubmissionTime(output);
            job.addToDataMap(SGE.SGE_JOB_START_TIME_KEY, submissionTime);
            try {
                Thread.sleep(pollingRate);
            } catch (InterruptedException e) {
                LOG.error(e);
            } 
            
            handleQstatErrors(output, invokerResult);
            this.stateExitEvent = failureEvent;

        } else {
            this.stateExitEvent = successEvent;
        }
    }

    private String getSubmissionTime(SGEOutput output) {
        String result = "-1";
        String submisisonTime = output.get(QstatJobOutput.SUBMISSION_TIME.getToken());
        try {
            result = CommonUtils.convertToMilliseconds(submisisonTime).toString();
        } catch (ParseException e1) {
            LOG.error("could not parse SGE job start time", e1);
            job.addToDataMap(SGE.SGE_JOB_START_TIME_KEY, "-1");
        }
        return result;
    }
    
    /**
     * Handles any errors returned from qstat, like 'Eqw'
     * @throws ExecutionException 
     */
    private void handleQstatErrors(SGEOutput output, InvokerResult invokerResult) throws ExecutionException {
        Preconditions.checkNotNull(compiledErrorPropertyKeyPattern, "Qstat error message pattern not set");
        Map<String, String> result = Maps.filterEntries(output.getData(), new Predicate<Entry<String, String>>() {

            @Override
            public boolean apply(@Nullable Entry<String, String> entry) {
                return compiledErrorPropertyKeyPattern.matcher(entry.getKey()).matches();
            }
        });
        if (!result.isEmpty()) {
            String submissionTime = getSubmissionTime(output);
            job.addToDataMap(SGE.SGE_JOB_END_TIME_KEY, submissionTime);
            
            throw new ExecutionException(buildErrorMessage("Error found in qstat output.", invokerResult));
        }
    }

    public void setPollingRate(Long pollingRate) {
        this.pollingRate = pollingRate;
    }

    public void setErrorPropertyKeyPattern(String errorPropertyKeyPattern) {
        compiledErrorPropertyKeyPattern = Pattern.compile(errorPropertyKeyPattern);
    }
}
