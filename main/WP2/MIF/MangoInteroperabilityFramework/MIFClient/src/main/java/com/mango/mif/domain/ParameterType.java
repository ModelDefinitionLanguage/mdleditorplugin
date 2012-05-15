/* File			:  ParameterType.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  2 Apr 2012
 */
package com.mango.mif.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * Task request parameter type
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
@XmlRootElement
public enum ParameterType {
    /**
     * File (i.e. script, dataset)
     */
    File,
    /**
     * Scalar (i.e. string, number etc.)
     */
    Scalar
}
