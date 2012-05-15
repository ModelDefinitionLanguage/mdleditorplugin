/* File			:  ParameterDirection.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  2 Apr 2012
 */
package com.mango.mif.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * Direction of the parameter (input/output)
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
@XmlRootElement
public enum ParameterDirection {
    IN,
    OUT;
    
	ParameterDirection(){
		
	}
}
