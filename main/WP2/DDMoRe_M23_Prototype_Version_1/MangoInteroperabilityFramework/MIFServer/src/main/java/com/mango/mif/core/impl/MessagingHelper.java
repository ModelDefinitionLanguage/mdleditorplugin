/* File			:  MessagingHelper.java
 * Project		:  MIFServer
 * Created on	:  Jul 25, 2012
 */
package com.mango.mif.core.impl;

import javax.xml.bind.JAXBException;

import com.mango.mif.connector.ConnectorExceptions;
import com.mango.mif.domain.ExecutionRequest;
import com.mango.mif.domain.ExecutionResponse;
import com.mango.mif.utils.JAXBUtils;
import com.mango.mif.utils.jaxb.ExecutionJaxbUtils;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * Provides helper methods used to marshall/unmarshall messages
 */
public class MessagingHelper {
    /**
     * Parses execution request.
     *
     * @param receivedText the received text
     * @return the string
     */
    public static ExecutionRequest parseExecutionRequest(String requestMsg) {
        ExecutionRequest request = null;
        try {
            request = JAXBUtils.unmarshall(requestMsg, ExecutionJaxbUtils.CONTEXT_CLASSES);
        } catch (JAXBException e) {
            throw new RuntimeException(ConnectorExceptions.INVALID_REQUEST_MESSAGE_FORMAT.getMessage());
        }
        if(request.getRequestId() == null) {
            throw new RuntimeException(ConnectorExceptions.REQUEST_ID_NOT_PRESENT.getMessage());
        }
        return request;
    }
    
    /**
     * Parses execution response.
     *
     * @param responseMsg the response message
     * @return the string
     */
    public static ExecutionResponse parseExecutionResponse(String responseMsg) {
        ExecutionResponse response = null;
        try {
            response = JAXBUtils.unmarshall(responseMsg, ExecutionJaxbUtils.CONTEXT_CLASSES);
        } catch (JAXBException e) {
            throw new RuntimeException(ConnectorExceptions.INVALID_RESPONSE_MESSAGE_FORMAT.getMessage());
        }
        if(response.getRequestId() == null) {
            throw new RuntimeException(ConnectorExceptions.REQUEST_ID_NOT_PRESENT.getMessage());
        }
        return response;
    }
}
