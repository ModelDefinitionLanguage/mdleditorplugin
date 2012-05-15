/* File			:  RJobRunner.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Mar 13, 2012
 */
package com.mango.mif.rconnectorcli.runner.impl;

import java.util.Map;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.google.common.base.Preconditions;
import com.mango.mif.connector.runner.domain.JobMessage;
import com.mango.mif.connector.runner.impl.SCXMLJobRunner;
import com.mango.mif.domain.ExecutionRequest;
import com.mango.mif.domain.ExecutionResponseBuilder;
import com.mango.mif.domain.Parameter;
import com.mango.mif.rconnectorcli.internal.RScriptExec;

/**
 * R Job Runner which will mock the R behaviour
 * Needs to be replaced with the actual R Job Runner. 
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class RCliJobRunner extends SCXMLJobRunner {
    /**
     * Logger
     */
    private final static Logger LOG = Logger.getLogger(RCliJobRunner.class);
    /**
     * results map
     */
    private Map<String, Parameter> results;
    
    @Resource(name="rScriptExec")
    private RScriptExec exec;

    @Resource(name="rCliJmsTemplate")
    private JmsTemplate jmsTemplate;

    /**
     * Job Result queue
     */
    private String resultDestination;
    
    @Override
    public void cancelProcessing() {
    }

    @Override
    public void startProcessing() {
        driver.fireEvent("runner.start");
    }

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
            
            while(!driver.isInFinalState()) {
                Thread.sleep(1000);
            }
            
            publishResultsMessage();
        
        } catch(Exception e) {
            LOG.error("Error executing Job Runner", e);
        }
        return jobMessage;
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
            builder.setResult(this.getDriver().getException().getMessage());
        } else {
            builder.setParameters(getResults());
            builder.setResult("R_RESULT");
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

    public RScriptExec getExec() {
        return exec;
    }
    
    public void setExec(RScriptExec exec) {
        this.exec = exec;
    }

    public void setResults(Map<String, Parameter> results) {
        this.results = results;
    }

    public Map<String, Parameter> getResults() {
        return results;
    }

    /**
     * Publishes result message to the job result queue
     * 
     * @param resultMessage
     */
    public void publishResultsMessage() {
        final String msg = prepareResponse();
        jmsTemplate.send(resultDestination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                TextMessage message = session.createTextMessage();
                message.setText(msg);
                return message;
            }
        });
    }
    
    public void setResultDestination(String resultDestination) {
        this.resultDestination = resultDestination;
    }
}
