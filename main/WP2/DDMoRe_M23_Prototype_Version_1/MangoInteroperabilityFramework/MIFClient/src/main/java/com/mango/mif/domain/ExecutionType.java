/* File			:  ExecutionType.java
 * Project		:  MIFClient
 * Created on	:  Aug 3, 2012
 */
package com.mango.mif.domain;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * Holds a list of execution types
 */
public enum ExecutionType {
    /**
    * NMFE execution type.
    */
    NMFE,

    /**
    * PsN Exec .
    */
    PsN_EXEC,
    
    /**
    * PsN VPC.
    */
    PsN_VPC,
    
    /**
    * PsN SCM.
    */
    PsN_SCM,
    
    /**
    * PsN Bootstrap.
    */
    PsN_Bootstrap,
    
    /**
    * R Script.
    */
    R_Script,
    
    /**
    * Shell Command.
    */
    COMMAND_LINE,
    
    /**
    * Unknown Profile type
    */
    Unknown;

}
