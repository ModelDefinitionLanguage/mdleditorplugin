/* File			:  JschSessionFactoryImpl.java
 * Project		:  MIFClient
 * Created on	:  19 Jun 2012
 */
package com.mango.mif.core.jsch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.mango.mif.exception.MIFException;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class JschSessionFactoryImpl {

	public static final String BEAN_ID = "jschSessionFactoryImpl";
	
	private static final Log logger = LogFactory.getLog(JschSessionFactoryImpl.class);
	
	private String host = "localhost";
	private int port = 22;
	
	public JschSession create(String userName, String clearTextPassword) throws Exception {
		return getSession(userName, clearTextPassword, host, port); 
	}
	
	public JschSession create(String userName, String clearTextPassword, Integer port) throws Exception {
		return getSession(userName, clearTextPassword, host, port);
	}
	
	public JschSession create(String userName, String clearTextPassword, String host) throws Exception {
		return getSession(userName, clearTextPassword, host, port);
	}
	
	public JschSession create(String userName, String clearTextPassword, String host, Integer port) throws Exception {
		return getSession(userName, clearTextPassword, host, port);
	}
	
	private JschSession getSession(String userName, String clearTextPassword, String host, Integer port) throws Exception {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("Creating jsch session for user % to host %s on port %s", userName, host, port));
			}
			return new JschParameterBuilder(userName, clearTextPassword).host(host).port(port).buildSession();
		} catch (MIFException e) {
			throw new Exception(String.format("Failed to create jsch session for user % to host %s on port %s", userName, host, port), e);
		}
	}

	public void setPort(int port) {
		this.port = port;
	}

}
