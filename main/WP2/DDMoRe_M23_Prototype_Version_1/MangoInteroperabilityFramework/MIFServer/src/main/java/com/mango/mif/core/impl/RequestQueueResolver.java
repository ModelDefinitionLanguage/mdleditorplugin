/* File			:  RequestQueueResolver.java
 * Project		:  MIFServer
 * Created on	:  Jul 10, 2012
 */
package com.mango.mif.core.impl;

import javax.xml.bind.JAXBException;

import com.mango.mif.connector.Connector;
import com.mango.mif.connector.ConnectorExceptions;
import com.mango.mif.domain.ExecutionRequest;
import com.mango.mif.utils.JAXBUtils;
import com.mango.mif.utils.jaxb.ExecutionJaxbUtils;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * A queue resolver that parses the message as an execution request and based in it's properties
 * resolves destination queue
 * 
 */
public class RequestQueueResolver extends BaseQueueResolver {
	
	@Override
	public String resolve(String message) {
		ExecutionRequest executionRequest = parseExecutionRequest(message);
		Connector connector = connectorsRegistry.getConnector(executionRequest.getType());
		if(connector==null) {
			return "";
		}
		return connector.getRequestQueue();
	}
	/**
	 * Parses execution request.
	 *
	 * @param receivedText the received text
	 * @return the string
	 */
	protected ExecutionRequest parseExecutionRequest(String receivedText) {
		ExecutionRequest request = null;
		try {
			request = JAXBUtils.unmarshall(receivedText, ExecutionJaxbUtils.CONTEXT_CLASSES);
		} catch (JAXBException e) {
			LOG.error("JAXB Exception for the string " , e);
			throw new RuntimeException(ConnectorExceptions.INVALID_REQUEST_MESSAGE_FORMAT.getMessage());
		}
		if(request.getRequestId() == null) {
			LOG.error("request id is not present in the request" + receivedText);
			throw new RuntimeException(ConnectorExceptions.REQUEST_ID_NOT_PRESENT.getMessage());
		}
		return request;
	}
}
