/* File			:  ExecutionRequest.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Mar 19, 2012
 */
package com.mango.mif.domain;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.google.common.collect.Maps;
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

    private String requestId;

    private String userName;

    private String userPassword;

    private String executionFile;

    private String executionMode;

    private String submitHostPreamble;

    private String gridHostPreamble;
    
    private String command;

    private boolean submitAsUserMode;

    /**
     * The execution command parameters.  This is one long (concatenated) string of the -max_retries stuff the user has specified in Navigator.
     */
    private String executionParameters;

    /**
     * The request attributes, which are name/value pairs, but all strings
     */
    private Map<String, String> requestAttributes;

    /**
     * Default constructor
     */
    public ExecutionRequest() {
        requestAttributes = Maps.newHashMap();
    }

    public String getExecutionParameters() {
        return executionParameters;
    }

    public void setExecutionParameters(String executionParameters) {
        this.executionParameters = executionParameters;
    }

    @XmlJavaTypeAdapter(XmlGenericMapAdapter.class)
    public Map<String, String> getRequestAttributes() {
        return requestAttributes;
    }

    public void setRequestAttributes(Map<String, String> requestAttributes) {
        this.requestAttributes = requestAttributes;
    }

    public void addRequestAttribute(String name, String value) {
        this.requestAttributes.put(name, value);
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
    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
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

    public boolean getSubmitAsUserMode() {
        return submitAsUserMode;
    }

    public void setSubmitAsUserMode(boolean submitAsUserMode) {
        this.submitAsUserMode = submitAsUserMode;
    }

    @XmlElement
    public String getExecutionMode() {
        return executionMode;
    }

    public void setExecutionMode(String executionMode) {
        this.executionMode = executionMode;
    }

    @XmlElement
    public String getSubmitHostPreamble() {
        return submitHostPreamble;
    }

    public void setSubmitHostPreamble(String submitHostPreamble) {
        this.submitHostPreamble = submitHostPreamble;
    }

    @XmlElement
    public String getGridHostPreamble() {
        return gridHostPreamble;
    }

    public void setGridHostPreamble(String gridHostPreamble) {
        this.gridHostPreamble = gridHostPreamble;
    }
    
    
    public void setCommand(String command) {
        this.command = command;
    }

    @XmlElement
    public String getCommand() {
        return command;
    }
}
