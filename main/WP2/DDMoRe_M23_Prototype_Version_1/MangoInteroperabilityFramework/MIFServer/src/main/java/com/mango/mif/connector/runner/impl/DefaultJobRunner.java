/* File			:  DefaultJobRunner.java
 * Project		:  MIFServer
 * Created on	:  May 10, 2012
 */
package com.mango.mif.connector.runner.impl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.google.common.base.Preconditions;
import com.mango.mif.connector.runner.domain.JobMessage;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.domain.ExecutionResponseBuilder;
import com.mango.mif.domain.JobStatus;
import com.mango.mif.utils.MIFProperties;
import com.mango.mif.utils.encrypt.Encrypter;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * A default implementation of a Job Runner that uses an SCXML to drive its execution
 * 
 */
public class DefaultJobRunner extends SCXMLJobRunner {
    /**
     * Logger
     */
    private final static Logger LOG = Logger.getLogger(DefaultJobRunner.class);

    /**
     * JMS template used to publish result messages to the dispatcher
     */
    private JmsTemplate jmsTemplate;

    /**
     * Job Result queue
     */
    protected String resultDestination;
    /**
     * encrypter
     */
    protected Encrypter encrypter;
    /**
     * Result message
     */
    private String resultMessage;
    /**
     * Exception that have been thrown in call method
     */
    private Exception callMethodException;
    /**
     * Summary message
     */
    private String summaryMessage;

    @Override
    public void startProcessing() {
        driver.fireEvent(JobRunnerState.RUNNING.getTriggeringEvent());
        LOG.debug("Job Runner for request " + jobId + " has been started.");
    }

    /**
     * Requests a cancellation from the driver, if there are any exceptions thrown during cancellation, the exception is available from
     * the driver. The error message will be set then on the execution response, but the final status will be CANCELLED.
     */
    @Override
    public synchronized void cancelProcessing() {
        LOG.debug("Triggering cancellation even for job " + jobId + ".");
        driver.cancel();
    }

    public void setSummaryMessage(String summaryMessage) {
        this.summaryMessage = summaryMessage;
    }

    @Override
    public JobMessage call() {
    	JobMessage jobMessage = new JobMessage();
        try {
            main();
        } catch(Exception e) {
            LOG.error("Error executing Job Runner", e);
            callMethodException = e;
        } finally {
            resultMessage = prepareResponse();
            publishResultsMessage();
            LOG.info(String.format("*** Execution Metrics : {Processing.3} " +
            		"Publishing results for jobId [%s]", jobId));
        }
        return jobMessage;
    }

    /**
     * Encapsulates processing from call method
     * @throws InterruptedException
     */
    void main() throws InterruptedException {
        Preconditions.checkNotNull(jobId, "Job not set.");
        Preconditions.checkNotNull(driver, "Driver not set.");
        Preconditions.checkNotNull(resultDestination, "Result Destination Queue not set.");
        
        LOG.info(String.format("*** Execution Metrics : {Processing.1} " +
        		"Starting process for jobId [%s]", jobId));
        
        startProcessing();
        // TODO currently JobRunner actively waits for state machine to reach final state
        // SCXMLDriver in complete method should check if the current status is final and notify job runner
        // to do this make the job runner an observer of the driver or use guava's EventBus
        
        int secondsCount = 0;
        while(isJobRunning()) {
        	LOG.info(String.format("*** Execution Metrics : {Process.Running.%d} " +
            		"Process running for jobId [%s]", secondsCount, jobId));
            Thread.sleep(1000);
            secondsCount++;
        }
        
        LOG.info(String.format("*** Execution Metrics : {Processing.2} " +
        		"Process finished running for jobId [%s]", jobId));
    }
    
    /**
     * 
     * @return true if job is being processed and is not cancelled
     */
    private synchronized boolean isJobRunning() {
        return !driver.isInFinalState() && !DriverProcessingStatus.CANCELLED.equals(driver.getProcessingStatus());
    }

    /**
     * Publishes result message to the job result queue
     * 
     * @param resultMessage
     */
    protected void publishResultsMessage() {
        jmsTemplate.send(resultDestination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                TextMessage message = session.createTextMessage();
                message.setText(resultMessage);
                return message;
            }
        });
    }

    /**
     * Prepare the Response from Runner.
     * @return
     */
    protected String prepareResponse() {
        JobStatus status = getJobStatus();

        ExecutionResponseBuilder builder = new ExecutionResponseBuilder();
        builder.setRequestId(jobId);
        builder.setStatus(status.name());

        populateSummaryMessage(builder);

        populateResult(builder);
        populateResultDetails(builder);

        String responseString = "";

        try {
            responseString = builder.getExecutionResponseMsg();
        } catch (JAXBException e) {
            LOG.error("Error when marshalling response message", e);
            responseString = prepareMarshallableMessage(builder);
        }

        return responseString;
    }

    /**
     * Creates a message and sets result and detailed result to marshallable values
     * @param builder
     * @return
     */
    private String prepareMarshallableMessage(ExecutionResponseBuilder builder) {
        builder.setResult("ERROR when creating response message");
        builder.setResultDetails("could not marshall response message");
        try {
            return builder.getExecutionResponseMsg();
        } catch (JAXBException e) {
            throw new RuntimeException("Could not marshall a 'safe' response message", e);
        }
    }

    private void populateSummaryMessage(ExecutionResponseBuilder builder) {
        if (summaryMessage != null) {
            builder.setSummaryMessage(summaryMessage);
        } else {
            builder.setSummaryMessage(MIFProperties.getInstance().getProperty("sgeconnector.unavailableSummaryMessage"));
        }
    }

    private void populateResultDetails(ExecutionResponseBuilder responseBuilder) {
        StringBuilder builder = new StringBuilder();
        if (this.getDriver().getException() != null) {
            builder.append(prepareDetailedResultMessage(getDriver().getException()));
        }
        if (this.callMethodException != null) {
            builder.append("Job Runner exception\n");
            builder.append(prepareDetailedResultMessage(callMethodException));
        }
        responseBuilder.setResult(builder.toString());
    }

    private void populateResult(ExecutionResponseBuilder responseBuilder) {
        StringBuilder builder = new StringBuilder();
        if (this.getDriver().getException() != null) {
            builder.append(prepareResultMessage(getDriver().getException()));
        }
        if (this.callMethodException != null) {
            builder.append("Job Runner exception\n");
            builder.append(prepareResultMessage(callMethodException));
        }
        responseBuilder.setResult(builder.toString());
    }

    /**
     * maps the driver status to JobStatus
     * @return
     */
    private JobStatus getJobStatus() {
        DriverProcessingStatus processingStatus = DriverProcessingStatus.valueOf(driver.getProcessingStatus().getMessage());
        if (callMethodException != null) {
            return JobStatus.FAILED;
        }
        switch (processingStatus) {
        case CANCELLED:
            return JobStatus.CANCELLED;
        case FAILED:
            return JobStatus.FAILED;
        case FINISHED:
            return JobStatus.COMPLETED;
        default:
            return JobStatus.RUNNING;
        }
    }

    /**
     * Builds an error message that will be passed to the client
     * @return
     */
    String prepareResultMessage(Exception exception) {
        Throwable tmp = exception;
        StringBuilder errorMsgBuilder = new StringBuilder();

        StateHandlerValidationException sve = extractStateHandlerValidationException(exception);
        if (sve != null) {
            errorMsgBuilder.append(sve.getMessage() + "\n");
            if (sve.hasOutput()) {
                errorMsgBuilder.append("OUTPUT:\n");
                errorMsgBuilder.append(sve.getStdout());
            }
            if (sve.hasErrors()) {
                errorMsgBuilder.append("ERRORS:\n");
                errorMsgBuilder.append(sve.getStderr());
            }
        } else {
            while (tmp != null) {
                errorMsgBuilder.append(tmp.getMessage());
                errorMsgBuilder.append("\n");

                if (tmp instanceof ExecutionException) {
                    break;
                }
                tmp = tmp.getCause();
            }
        }
        String msg = errorMsgBuilder.toString();
        if (tmp == null) {
            msg = exception.getMessage();
        }
        if (StringUtils.isEmpty(msg)) {
            msg = "Error message not available.";
        }
        return msg;
    }

    /**
     * Builds an error message that will be passed to the client.
     *
     * @return
     */
    String prepareDetailedResultMessage(Exception exception) {
        StringBuilder errorMsgBuilder = new StringBuilder();
        Throwable tmp = exception;

        // I want validation failed exceptions to be handled specially as they
        // indicate not a failure of MIF which we need to tell the user about,
        // but a failure of the script the user was trying to run
        //
        StateHandlerValidationException sve = extractStateHandlerValidationException(exception);
        if (sve != null) {
            errorMsgBuilder.append(sve.getMessage() + "\n");
            if (sve.hasOutput()) {
                errorMsgBuilder.append("OUTPUT:\n");
                errorMsgBuilder.append(sve.getStdout());
            }
            if (sve.hasErrors()) {
                errorMsgBuilder.append("ERRORS:\n");
                errorMsgBuilder.append(sve.getStderr());
            }
        } else {
            StackTraceElement[] stackTrace = tmp.getStackTrace();
            errorMsgBuilder.append(tmp);
            errorMsgBuilder.append("\n");
            errorMsgBuilder.append(dumpStackTrace(stackTrace));
            tmp = tmp.getCause();
            while (tmp != null) {
                stackTrace = tmp.getStackTrace();
                errorMsgBuilder.append("Caused by: ");
                errorMsgBuilder.append(tmp);
                errorMsgBuilder.append("\n");
                errorMsgBuilder.append(dumpStackTrace(stackTrace));
                tmp = tmp.getCause();
            }
        }
        String msg = errorMsgBuilder.toString();
        if (StringUtils.isEmpty(msg)) {
            msg = "Error message not available.";
        }
        return msg;
    }

    /**
     * @param exception The exception we're currently dealing with.
     * @return the exception, or the exception's cause, which is a StateHandlerValidationException, or null if there is none
     */
    private StateHandlerValidationException extractStateHandlerValidationException(Exception exception) {
        Throwable tmp = exception;
        while (tmp != null) {
            if (tmp instanceof StateHandlerValidationException) {
                return (StateHandlerValidationException) tmp;
            }
            tmp = tmp.getCause();
        }
        return null;
    }

    /**
     * Dumps statck trace
     * @param stackTrace
     * @return
     */
    private String dumpStackTrace(StackTraceElement[] stackTrace) {
        StringBuilder errorMsgBuilder = new StringBuilder();
        if (stackTrace != null) {
            for (StackTraceElement stackTraceElement : stackTrace) {
                errorMsgBuilder.append(stackTraceElement);
                errorMsgBuilder.append("\n");
            }
        }
        return errorMsgBuilder.toString();
    }

    /**
     * @return the resultMsg
     */
    public String getResultMessage() {
        return resultMessage;
    }

    @Required
    public void setResultDestination(String resultDestination) {
        this.resultDestination = resultDestination;
    }

    @Required
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void setEncrypter(Encrypter encrypter) {
        this.encrypter = encrypter;
    }
}
