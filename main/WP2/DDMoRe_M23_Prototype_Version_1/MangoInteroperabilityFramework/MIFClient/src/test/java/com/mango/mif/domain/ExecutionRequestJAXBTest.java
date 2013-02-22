/* File			:  ExecutionRequestJAXBTest.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Apr 4, 2012
 */
package com.mango.mif.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.UUID;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.mango.mif.utils.JAXBUtils;
import com.mango.mif.utils.encrypt.EncrypterFactory;
import com.mango.mif.utils.jaxb.ExecutionJaxbUtils;

/**
 * Test for {@link ExecutionRequest}.
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class ExecutionRequestJAXBTest {

    private static final String SUBMIT_HOST_PREAMBLE = "# SUBMIT HOST PREAMBLE";
    private static final String GRID_HOST_PREAMBLE = "# GRID HOST PREAMBLE";
    private static final String NM_VERSION = "-nm_version";
    private static final String TEST_PASSWORD = "test_password";
    private static final String TEST_USER = "test_user";
    private static final String COMMAND = "command";
    private static final String REQUEST_ID = UUID.randomUUID().toString();
    final static Logger LOG = Logger.getLogger(ExecutionRequestJAXBTest.class);

    /**
     * Test jaxb for execution request.
     */
    @Test
    public void testJaxbForExecutionRequest() throws JAXBException {


        ExecutionRequest startRequest = prepareExecutionRequest();
        startRequest.setUserName(TEST_USER);
        startRequest.setExecutionParameters(NM_VERSION);
        startRequest.setUserPassword(EncrypterFactory.getEncrypter().encrypt(TEST_PASSWORD));

        String marshallRequest = JAXBUtils.marshall(startRequest, ExecutionJaxbUtils.CONTEXT_CLASSES);

        LOG.info("THE REQUEST IS NOW" + marshallRequest);

        ExecutionRequest reqUnmarshalled = (ExecutionRequest) JAXBUtils.unmarshall(marshallRequest, ExecutionJaxbUtils.CONTEXT_CLASSES);
        assertThat("Unmarshalled request must not be null ", reqUnmarshalled, is(notNullValue()));
        assertThat("Unmarshalled request ID must match ", reqUnmarshalled.getRequestId(), is(equalTo(REQUEST_ID)));
        assertThat("GridHost preamble must match", reqUnmarshalled.getGridHostPreamble(), is(equalTo(GRID_HOST_PREAMBLE)));
        assertThat("SubmitHost preamble must match", reqUnmarshalled.getSubmitHostPreamble(), is(equalTo(SUBMIT_HOST_PREAMBLE)));
        assertThat("Unmarshalled  request : Execution Parameters ,  must match ", reqUnmarshalled.getExecutionParameters(), is(equalTo(NM_VERSION)));
        assertThat("Unmarshalled  request :Password must  ,  must match ", EncrypterFactory.getEncrypter().decrypt(reqUnmarshalled.getUserPassword()), is(equalTo(TEST_PASSWORD)));
    }

    @Test
    public void testEmptyParameterDefinitionsAreAllowed() throws JAXBException {
        ExecutionRequest startRequest = prepareExecutionRequest();

        String marshallRequest = JAXBUtils.marshall(startRequest, ExecutionJaxbUtils.CONTEXT_CLASSES);

        LOG.info("THE REQUEST IS NOW" + marshallRequest);

        ExecutionRequest reqUnmarshalled = (ExecutionRequest) JAXBUtils.unmarshall(marshallRequest, ExecutionJaxbUtils.CONTEXT_CLASSES);
        assertThat("Unmarshalled request must not be null ", reqUnmarshalled, is(notNullValue()));
        assertThat("Unmarshalled request ID must match ", reqUnmarshalled.getRequestId(), is(equalTo(REQUEST_ID)));
    }

    @Test
    public void testEmptyParametersAreAllowed() throws JAXBException {


        ExecutionRequest startRequest = prepareExecutionRequest();

        String marshallRequest = JAXBUtils.marshall(startRequest, ExecutionJaxbUtils.CONTEXT_CLASSES);

        LOG.info("THE REQUEST IS NOW" + marshallRequest);

        ExecutionRequest reqUnmarshalled = (ExecutionRequest) JAXBUtils.unmarshall(marshallRequest, ExecutionJaxbUtils.CONTEXT_CLASSES);
        assertThat("Unmarshalled request must not be null ", reqUnmarshalled, is(notNullValue()));
        assertThat("Unmarshalled request ID must match ", reqUnmarshalled.getRequestId(), is(equalTo(REQUEST_ID)));

    }

    private ExecutionRequest prepareExecutionRequest() {
        ExecutionRequest startRequest = new ExecutionRequest();
        startRequest.setCommand(COMMAND);
        startRequest.setRequestId(REQUEST_ID);
        startRequest.setGridHostPreamble(GRID_HOST_PREAMBLE);
        startRequest.setSubmitHostPreamble(SUBMIT_HOST_PREAMBLE);
        return startRequest;
    }

}
