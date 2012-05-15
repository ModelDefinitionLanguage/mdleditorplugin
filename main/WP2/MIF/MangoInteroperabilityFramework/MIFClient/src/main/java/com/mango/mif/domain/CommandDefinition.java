/* File			:  CommandDefinition.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  30 Mar 2012
 */
package com.mango.mif.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

/**
 * 
 * Tells connector what is requested. i.e. how inputs parameters 
 * should be used in order to 
 * construct a control stream for third party software software 
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CommandDefinition {
    /**
     * Name of the command
     */
	@XmlElement(name="commandDefinitionName")
    private String name;
    /**
     * Command parameters
     */
	@XmlElementWrapper(name = "ParameterDefinitions")
	// XmlElement sets the name of the entities
	@XmlElement(name = "ParameterDefinition")
    private List<ParameterDefinition> parameters;
    
	/**
	 * For JAXB purpose
	 */
    public CommandDefinition() {
    	this.name = null;
        this.parameters = Lists.newArrayList();
    } ;
    /**
     * 
     * @param name
     * @param parameters
     */
    public CommandDefinition(String name, List<ParameterDefinition> parameters) {
        super();
        
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(parameters);
        this.name = name;
        this.parameters = parameters;
    }
   
    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Gets the parameters.
     *
     * @return the parameters
     */
    public List<ParameterDefinition> getParameters() {
        return parameters;
    }
    
    /**
     * For JAXB purpose
     * @param params
     */
    public void setParameters(List<ParameterDefinition> parameters) {
    	this.parameters = parameters;
    }
}
