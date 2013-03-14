/* File			:  PrefixIDNamingStrategy.java
 * Project		:  MIFServer
 * Created on	:  11 Sep 2012
 */
package com.mango.mif.sge;

import java.io.File;

import com.google.common.base.Preconditions;
import com.mango.mif.connector.domain.Job;
import com.mango.mif.utils.JobFileResolver;
import com.mango.mif.utils.MIFProperties;

/**
 * Build a path to a grid job working directory
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class GridJobDirectoryResolver implements JobFileResolver {

	private String prefix = MIFProperties.MIF_JOB_DIRECTORY_PREFIX;
	
	private String sgeSharedLocation;
	
	@Override
	public String getPath(Job job) {
        Preconditions.checkNotNull(job, "Job should not be null");
        Preconditions.checkNotNull(sgeSharedLocation, "Shared Location is null");
        
        if(job.isRunInRequestDirectory()) {
            return job.getRequestInputDirectory();
        } else {
            StringBuilder dir = new StringBuilder("");
            dir.append(getSgeSharedLocation());
            String gridJobDirectoryName = getGridJobDirectoryName(job);
            if (!getSgeSharedLocation().endsWith(File.separator) && !gridJobDirectoryName.startsWith(File.separator)) {
                dir.append(File.separator);
            }
            dir.append(gridJobDirectoryName);
            return dir.toString();
        }
        
    }

	private String getGridJobDirectoryName(Job job) {
        return getPrefix() + job.getJobId();
    }

    public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getPrefix() {
		return prefix;
	}

    public String getSgeSharedLocation() {
        return sgeSharedLocation;
    }
    
    public void setSgeSharedLocation(String sgeSharedLocation) {
        this.sgeSharedLocation = sgeSharedLocation;
    }
}
