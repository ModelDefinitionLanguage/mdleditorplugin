/* File			:  NmMetrics.java
 * Project		:  MIFClient
 * Created on	:  28  Aug 2012
 */
package com.mango.mif.client.api.rest;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.mango.mif.domain.DetailedStatus;

/**
 * JAXB wrapper for NonMem metrics we get back from the REST Service method.
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
@XmlRootElement(name = "nmMetrics")
public class NmMetrics extends DetailedStatus{
	
	/**
	 * 
	 */
	private Map<String, Double[]> metrics = new HashMap<String, Double[]>();
	

	/**
	 * 
	 */
	private String errors;
	
	/**
	 * 
	 * @return
	 */
	public Map<String, Double[]> getMetrics() {
		return metrics;
	}

	/**
	 * 
	 * @param parameters
	 */
	public void setMetrics(Map<String, Double[]> metrics) {
		this.metrics = metrics;
	}

	/**
	 * 
	 * @return
	 */
	public String getErrors() {
		return errors;
	}

	/**
	 * 
	 * @param errors
	 */
	public void setErrors(String errors) {
		this.errors = errors;
	}
	
	
	

}
