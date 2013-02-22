/* File			:  JAXBUtils.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Mar 20, 2012
 */
package com.mango.mif.utils;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * JAXB Utils for Marshalling a request and Unmarshalling.
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class JAXBUtils {
	
	/**
	 * Unmarshall an object.
	 * Usage as below.
	 *  JAXBUtils.unmarshall(ExecutionResponse.class , jobRunnerResponse);
	 *
	 * @param <T> the generic type
	 * @param clazz the clazz
	 * @param receivedText the received text
	 * @return the t
	 * @throws JAXBException the jAXB exception
	 */
	public static  <T> T unmarshall(Class<T> clazz, String receivedText ) throws JAXBException {
		JAXBContext context = null;
		T execution = null;
		context = JAXBContext.newInstance(clazz);
		Unmarshaller um = context.createUnmarshaller();
		execution = (T)um.unmarshal(new StringReader(receivedText));
		return execution;
	}
	
	/**
	 * Marshall the request.
	 *
	 * @param <T> the generic type
	 * @param object the object
	 * @return the string
	 * @throws JAXBException the jAXB exception
	 */
	public static <T>String marshall(T object) throws JAXBException {
		StringWriter sw = new StringWriter();
		JAXBContext context = null;
		context = JAXBContext.newInstance(object.getClass());
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		
		m.marshal(object, sw);
		return sw.toString();
	}
	
	/**
	 * Unmarshall the request for a list of class params.
	 * JAXBUtils.unmarshall(executionRequest, ExecutionJaxbUtils.EXECUTION_REQUEST_CLASS_ARRAY);
	 *
	 * @param <T> the generic type
	 * @param receivedText the received text
	 * @param parameters the parameters
	 * @return the t
	 * @throws JAXBException the jAXB exception
	 */
	public static  <T> T unmarshall(String receivedText, Class<?>[] parameters) throws JAXBException {
		JAXBContext context = null;
		T execution = null;
		context = JAXBContext.newInstance(parameters);
		Unmarshaller um = context.createUnmarshaller();
		execution = (T)um.unmarshal(new StringReader(receivedText));
		return execution;
	}
	
	/**
	 * Marshalls the request.
	 *
	 * @param <T> the generic type
	 * @param object the object
	 * @param parameters the parameters
	 * @return the string
	 * @throws JAXBException the jAXB exception
	 */
	public static <T>String marshall(T object, Class<?>[] parameters) throws JAXBException {
		StringWriter sw = new StringWriter();
		JAXBContext context = null;
		context = JAXBContext.newInstance(parameters);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "");
		
		m.marshal(object, sw);
		return sw.toString();
	}


}
