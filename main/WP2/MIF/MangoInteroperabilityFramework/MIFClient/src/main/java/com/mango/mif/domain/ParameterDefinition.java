/* File			:  ParameterDefinition.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  30 Mar 2012
 */
package com.mango.mif.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.base.Preconditions;

/**
 * Command Parameter definition, defines parameter name, type and direction (input/output)
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
@XmlRootElement
public class ParameterDefinition {
    /**
     * Parameter type
     */
	@XmlElement(name="paramType")
    private final ParameterType type;
    /**
     * Parameter name
     */
	 @XmlElement(name="paramName")
    private final String name;
    /**
     * Direction of the parameter (IN/OUT)
     */
    @XmlElement(name="paramDirection")
    private final ParameterDirection direction;
    
    
    ParameterDefinition(){
    	 this.type = null;
         this.name = null;
         this.direction = null;
    };
    /**
     * Constructor
     * @param type
     * @param name
     */
    public ParameterDefinition(ParameterType type, ParameterDirection direction, String name) {
        super();
        Preconditions.checkNotNull(type);
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(direction);
        this.type = type;
        this.name = name;
        this.direction = direction;
    }
    
    public ParameterType getType() {
        return type;
    }
    
    public String getName() {
        return name;
    }
    
    public ParameterDirection getDirection() {
        return direction;
    }
}
