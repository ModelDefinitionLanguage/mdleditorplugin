/* File			:  Result.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  30 Mar 2012
 */
package com.mango.mif.rconnectorcli.internal;

import java.io.File;

/**
 * 
 * Represents a result file 
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class Result {
    /**
     * Result type
     */
    private ResultType resultType;
    /**
     * File containg result, relative location to working directory
     */
    private File resultFile;
    
    /**
     * Constructor 
     * @param resultType
     * @param resultFile
     */
    public Result(ResultType resultType, File resultFile) {
        super();
        this.resultType = resultType;
        this.resultFile = resultFile;
    }
    
    public File getResultFile() {
        return resultFile;
    }
    
    public ResultType getResultType() {
        return resultType;
    }
}
