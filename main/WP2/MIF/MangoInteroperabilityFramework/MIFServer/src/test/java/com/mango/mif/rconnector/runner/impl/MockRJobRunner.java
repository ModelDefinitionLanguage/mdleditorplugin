/* File			:  MockRJobRunner.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  20 Mar 2012
 */
package com.mango.mif.rconnector.runner.impl;

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
import com.mango.mif.connector.runner.api.SCXMLDriver;
import com.mango.mif.connector.runner.domain.JobMessage;
import com.mango.mif.connector.runner.impl.SCXMLJobRunner;
import com.mango.mif.domain.ExecutionRequest;
import com.mango.mif.domain.ExecutionResponse;
import com.mango.mif.utils.JAXBUtils;

/**
 * R job Runner that mocks execution of R.
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class MockRJobRunner extends SCXMLJobRunner {
    /**
     * Logger
     */
    private static Logger LOG = Logger.getLogger(MockRJobRunner.class);
    /**
     * Mocks R execution
     */
    private MockRExec rExec;
    /**
     * Job Result queue
     */
    private String resultDestination = "R.JOB.RESULT";

    @Resource(name="jmsTemplate")
    JmsTemplate jmsTemplate;

    public MockRExec getRExec() {
        return rExec;
    }
    
    public void setRExec(MockRExec rExec) {
        this.rExec = rExec;
    }
    
    public SCXMLDriver getDriver() {
        return driver;
    }
    
    @Override
    public JobMessage call() throws Exception {
        Preconditions.checkNotNull(driver);
        Preconditions.checkNotNull(rExec);
        Preconditions.checkNotNull(job);
        Preconditions.checkNotNull(job.getExecutionRequest());
        
        JobMessage jobMessage = new JobMessage();
        
        startProcessing();
        // TODO currently JobRunner actively waits for state machine to reach final state
        // SCXMLDriver in complete method should check if the current status is final and notify job runner
        // to do this make the job runner an observer of the driver or use guava's EventBus
        
        while(!driver.isInFinalState()) {
            Thread.sleep(1000);
        }
        
        
        publishResults(prepareMockResponse());
        
        
        return jobMessage;
    }

    /**
     * Prepare the mock Response from Runner.
     * @return
     */
    protected String prepareMockResponse() {
        String status = driver.getProcessingStatus().getMessage();
        String result = rExec.getResult();
        String responseString = null;
        ExecutionRequest request = job.getExecutionRequest();
        ExecutionResponse response = new ExecutionResponse();
        response.setRequestId(request.getRequestId());
        response.setStatus(status);
        response.setResult(result);

        try {
            responseString = JAXBUtils.marshall(response);
        } catch (JAXBException e) {
            LOG.error("JAXB Exception " + e );
            response.setStatus("FAILURE");
            response.setResult(e.getMessage());
        }
        
        return responseString;
    }
    
    @Override
    public void cancelProcessing() {
        //do nothing
    }

    @Override
    public void startProcessing() {
        driver.fireEvent("runner.start");

    }
    
    /**
     * Publishes result message to the job result queue
     * 
     * @param resultMessage
     */
    public void publishResults(final String resultMessage) {
        jmsTemplate.send(resultDestination, new MessageCreator() {

            public Message createMessage(Session session) throws JMSException {
                TextMessage message = session.createTextMessage();
                message.setText(resultMessage);
                return message;
            }

        });
    }
}
