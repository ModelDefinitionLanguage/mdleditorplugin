/* File			:  Parameter.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  30 Mar 2012
 */
package com.mango.mif.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.base.Preconditions;

/**
 * 
 * Represents an input parameter
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Parameter {
    /**
     * name of the parameter, as expected by command definition 
     */
	@XmlElement
    private final String name;
    /**
     * Value, in case of a scalar value, actual value, in case of a file its name
     */
	@XmlElement 
    private final String value;
    /**
     * null in case of a scalar value uuid if a file 
     */
	@XmlElement
    private final String resourceUuid;
    /**
     * Parameter type
     */
    @XmlElement(name="paramType")
    private final ParameterType type;
    
    /** 
     * Used By JAXB purpose.
     */
    private Parameter(){
    	this.name = null;
        this.value = null;
        this.resourceUuid = null;
        this.type = null;
    };
    /**
     * Constructor
     * @param name
     * @param value
     */
    public Parameter(String name, String value, ParameterType type) {
        super();
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(value);
        this.name = name;
        this.value = value;
        this.resourceUuid = null;
        this.type = type;
    }
    /**
     * Constructor
     * @param name
     * @param value
     * @param resourceUuid
     */
    public Parameter(String name, String value, String resourceUuid, ParameterType type) {
        super();
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(value);
        Preconditions.checkNotNull(resourceUuid);
        Preconditions.checkNotNull(type);
        this.name = name;
        this.value = value;
        this.resourceUuid = resourceUuid;
        this.type = type;
    }
    
    public String getName() {
        return name;
    }
    
    public String getValue() {
        return value;
    }
    
    public String getResourceUuid() {
        return resourceUuid;
    }
    
    public ParameterType getType() {
        return type;
    }
}
