package com.mango.mif.core.resource.shell;

import java.util.ArrayList;
import java.util.List;

import com.mango.mif.core.api.SharedLocationManager;
import com.mango.mif.core.exec.CommandBuilder;

/**
 * The Class ResourceRetrieverSetter.
 * Responsible for setting arguments to set up a resource retriever. 
 */
public class ResourceRetrieverSetter {
	/**
	 * Request ID
	 */
	protected String	requestID;
	/**
	 * Shared Location Manager providing basic shared location operations
	 */
	protected SharedLocationManager	sharedLocationManager;
	
	/** The builder. */
	CommandBuilder builder;
	
	/**
	 * User name
	 */
	private String	userName;
	/**
	 * User encrypted password
	 */
	private String	password;
	
	/** The files list which are to be ignored during copy. */
	private List<String> ignoreFilesList = new ArrayList<String>();
	
	/**
     * The port for JSCH to use
     */
    private int port = 22;
	
    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getIgnoreFilesList() {
		return ignoreFilesList;
	}

	public void setIgnoreFilesList(List<String> ignoreFilesList) {
		this.ignoreFilesList = ignoreFilesList;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getRequestID() {
		return requestID;
	}

	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}

	public SharedLocationManager getSharedLocationManager() {
		return sharedLocationManager;
	}

	public void setSharedLocationManager(SharedLocationManager sharedLocationManager) {
		this.sharedLocationManager = sharedLocationManager;
	}

	public CommandBuilder getBuilder() {
		return builder;
	}

	public void setBuilder(CommandBuilder builder) {
		this.builder = builder;
	}


}
