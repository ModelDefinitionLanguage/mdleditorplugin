/* File			:  PsNBootstrapProcessingDetailedStatus.java
 * Project		:  MIFServer
 * Created on	:  Aug 15, 2012
 */
package com.mango.mif.domain.psnsgeconnector;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.collect.Lists;
import com.mango.mif.domain.DetailedStatus;
import com.mango.mif.domain.nonmemsgeconnector.NONMEMProcessingDetailedStatus;


/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * Objects of this class hold detailed status information of PsN Bootstrap run processing
 */
@XmlRootElement(name = "PsNBootstrapProcessingDetailedStatus")
public class PsNBootstrapProcessingDetailedStatus extends DetailedStatus {
    /**
     * username who started the job
     */
    private String userName;
    /**
     * grid job id
     */
    private String gridJobId;
    /**
     * Control file name
     */
    private String controlFileName;
    /**
     * How long NONMEM has been running (in milliseconds)
     */
    private String duration;
    
    /**
	 * Error details if any raised during call to job service.
	 */
	private String errors;
	
    /**
     * Child processes
     */
    private List<NONMEMProcessingDetailedStatus> activeChildNONMEMProcesses = Lists.newArrayList();
    
    public void setControlFileName(String controlFileName) {
        this.controlFileName = controlFileName;
    }
    
    public void setDuration(String duration) {
        this.duration = duration;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public String getControlFileName() {
        return controlFileName;
    }
    
    public String getDuration() {
        return duration;
    }
    
    public String getGridJobId() {
        return gridJobId;
    }
    
    public void setGridJobId(String gridJobId) {
        this.gridJobId = gridJobId;
    }
    
    public void setActiveChildNONMEMProcesses(List<NONMEMProcessingDetailedStatus> childProcesses) {
        this.activeChildNONMEMProcesses = childProcesses;
    }
    
    public List<NONMEMProcessingDetailedStatus> getActiveChildNONMEMProcesses() {
        return activeChildNONMEMProcesses;
    }
    
    public void addActiveChildNONMEMProcess(NONMEMProcessingDetailedStatus childProcess) {
        activeChildNONMEMProcesses.add(childProcess);
    }

	public String getErrors() {
		return errors;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}
    
    
}
