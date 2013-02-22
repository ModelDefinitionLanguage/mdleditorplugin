/* File			:  PsNSCMProcessingDetailedStatus.java
 * Project		:  MIFServer
 * Created on	:  Aug 15, 2012
 */
package com.mango.mif.domain.psnsgeconnector;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.collect.Lists;
import com.mango.mif.domain.DetailedStatus;


/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * Objects of this class hold detailed status information of PsN SCM run processing
 */
@XmlRootElement(name = "PsNSCMProcessingDetailedStatus")
public class PsNSCMProcessingDetailedStatus extends DetailedStatus {
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
     * How long NONMEM has been running
     */
    private String duration;
    
    /**
	 * Error details if any raised during call to job service.
	 */
	private String errors;
	
    /**
     * Summary message
     */
    private String parameterCovariateRelation;
    /**
     * Child processes
     */
    private List<PsNSCMModelProcessingStatus> models = Lists.newArrayList();
    
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
    
    public void setModels(List<PsNSCMModelProcessingStatus> models) {
        this.models = models;
    }
    
    public List<PsNSCMModelProcessingStatus> getModels() {
        return models;
    }
    
    public void addModel(PsNSCMModelProcessingStatus model) {
        models.add(model);
    }
    
    public void setParameterCovariateRelation(String parameterCovariateRelation) {
        this.parameterCovariateRelation = parameterCovariateRelation;
    }
    
    public String getParameterCovariateRelation() {
        return parameterCovariateRelation;
    }

	public String getErrors() {
		return errors;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}
    
    
}
