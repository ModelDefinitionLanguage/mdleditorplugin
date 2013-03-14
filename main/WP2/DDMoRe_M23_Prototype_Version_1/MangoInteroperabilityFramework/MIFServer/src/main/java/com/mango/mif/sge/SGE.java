/* File			:  SGE.java
 * Project		:  MIFServer
 * Created on	:  2 Jul 2012
 */
package com.mango.mif.sge;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 * holds common constants used in the code
 */
public class SGE {
	/**
	 * Job ID on SGE
	 */
	public static final String SGE_JOB_ID_KEY = "SGE_JOB_ID";
	/**
	 * A constant for a SGE Job directory
	 */
	public final static String GRID_JOB_DIRECTORY_KEY = "GRID_JOB_DIRECTORY";
	/**
	 * A constant of the script that is to be submitted to grid as a job
	 */
	public final static String SGE_SUBMIT_SCRIPT_KEY = "SGE_JOB_SUBMIT_SCRIPT";
	
    public static final String SGE_JOB_START_TIME_KEY = "SGE_JOB_START_TIME_KEY";
    
    public static final String SGE_JOB_END_TIME_KEY = "SGE_JOB_END_TIME_KEY";
    /**
	 * A constant for the SGE share root directory
	 */	
	public static final String GRID_SHARE_ROOT_KEY	= "GRID_SHARE_ROOT_KEY";
}
