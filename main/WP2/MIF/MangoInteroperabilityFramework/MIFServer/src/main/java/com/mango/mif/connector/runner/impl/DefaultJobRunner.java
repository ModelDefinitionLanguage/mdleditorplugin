/* File			:  DefaultJobRunner.java
 * Project		:  MIFServer
 * Created on	:  May 10, 2012
 */
package com.mango.mif.connector.runner.impl;

import java.util.Map;

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
import com.mango.mif.domain.ExecutionRequest;
import com.mango.mif.domain.ExecutionResponseBuilder;
import com.mango.mif.domain.Parameter;
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
public abstract class DefaultJobRunner extends SCXMLJobRunner {
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
     * results map
     */
    private Map<String, Parameter> results;
    /**
     * encrypter
     */
    protected Encrypter encrypter;
    /**
     * Result message
     */
	private String	resultMessage;
    @Override
    public void startProcessing() {
        driver.fireEvent(JobRunnerSates.RUNNING.getTriggeringEvent());
    }
    
    /**
     * Gets the Jobrunner's working directory metadata from the Job.data Map.
     * @return String absolute path of the job runner's working directory
     */
    protected abstract String getWorkingDirectory();
    
    @Override
    public JobMessage call() {
        JobMessage jobMessage = new JobMessage();
        try {
            Preconditions.checkNotNull(job, "Job not set.");
            Preconditions.checkNotNull(workspaceManager, "Workspace Manager not set on the job runner.");
            Preconditions.checkNotNull(driver, "Driver not set.");
            Preconditions.checkNotNull(resultDestination, "Result Destination Queue not set.");
            
            startProcessing();
            // TODO currently JobRunner actively waits for state machine to reach final state
            // SCXMLDriver in complete method should check if the current status is final and notify job runner
            // to do this make the job runner an observer of the driver or use guava's EventBus
            
            while(isJobRunning()) {
                Thread.sleep(1000);
            }
            resultMessage = prepareResponse();
            
            publishResultsMessage();
        
        } catch(Exception e) {
            LOG.error("Error executing Job Runner", e);
            throw new RuntimeException("Error executing Job Runner",e);
        }
        return jobMessage;
    }
    /**
     * 
     * @return true if job is being processed and is not cancelled
     */
    private boolean isJobRunning() {
    	return !driver.isInFinalState()&&!DriverProcessingStatus.CANCELLED.equals(driver.getProcessingStatus());
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
        String status = driver.getProcessingStatus().getMessage();

        ExecutionRequest request = job.getExecutionRequest();
        ExecutionResponseBuilder builder = new ExecutionResponseBuilder();
        builder.setRequestId(request.getRequestId());
        builder.setStatus(status);
        
        if(this.getDriver().getException()!=null) {
        	builder.setResult(getErrorMessage(getDriver().getException()));
        } else {
            builder.setParameters(getResults());
        }
        String responseString = "";
        
        try {
            responseString = builder.getExecutionResponseMsg();
        } catch (JAXBException e) {
            throw new RuntimeException("Error building result message.", e);
        }
        
        LOG.debug(responseString);
        return responseString;
    }
    
    
	/**
	 * Builds an error message that will be passed to the client
	 * @return
	 */
	String getErrorMessage(Exception exception) {
    	Throwable tmp = exception;
    	StringBuilder errorMsgBuilder = new StringBuilder();
    	while(tmp!=null) {
    		errorMsgBuilder.append(tmp.getMessage());
    		errorMsgBuilder.append("\n");
    		if(tmp instanceof ExecutionException) {
    			break;
    		}
    		tmp = tmp.getCause();
    	}
    	String msg = errorMsgBuilder.toString();
    	if(tmp==null) {
    		msg = exception.getMessage();
    	}
    	if(StringUtils.isEmpty(msg)) {
    		msg = "Error message not available.";
    	}
		return msg;
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


    public void setResults(Map<String, Parameter> results) {
        this.results = results;
    }

    public Map<String, Parameter> getResults() {
        return results;
    }
    
	public void setEncrypter(Encrypter encrypter) {
		this.encrypter = encrypter;
	}
}
