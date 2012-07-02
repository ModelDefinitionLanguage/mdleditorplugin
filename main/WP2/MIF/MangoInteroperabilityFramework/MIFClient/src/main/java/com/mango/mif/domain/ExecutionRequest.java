/* File			:  ExecutionRequest.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Mar 19, 2012
 */
package com.mango.mif.domain;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.google.common.collect.Maps;
import com.mango.mif.domain.CommandDefinition;
import com.mango.mif.domain.Parameter;
import com.mango.mif.utils.jaxb.XmlGenericMapAdapter;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
@XmlRootElement(name = "executionRequest")
public class ExecutionRequest {
	
	private String name;
	
	private String type;
	
	private UUID requestId;

	private String userName;
	
	private String userPassword;
	
	private String executionFile;

	/**
     * Command definition
     */
    private CommandDefinition commandDefinition;
    /**
     * Input parameters values chosen by user 
     */
    
    private Map<String, Parameter> parameters;
    
    /**
     * Default constructor
     */
    public ExecutionRequest() {
        parameters = Maps.newHashMap();
    }
    
    @XmlElement(name="commandDefinition")   
    public CommandDefinition getCommandDefinition() {
        return commandDefinition;
    }
    
    public void setCommandDefinition(CommandDefinition commandDefinition) {
        this.commandDefinition = commandDefinition;
    }
    
    @XmlJavaTypeAdapter(XmlGenericMapAdapter.class)
	public Map<String, Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, Parameter> parameters) {
		this.parameters = parameters;
	}

	@XmlElement
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@XmlElement
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@XmlElement
	public UUID getRequestId() {
		return requestId;
	}
	public void setRequestId(UUID requestId) {
		this.requestId = requestId;
	}
	
	public String getExecutionFile() {
		return executionFile;
	}

	public void setExecutionFile(String executionFile) {
		this.executionFile = executionFile;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
}
