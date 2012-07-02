/* File			:  JobService.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Apr 13, 2012
 */
package com.mango.mif.client.api;

import java.util.UUID;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public interface JobService {
	
	/**
	 * Gets the job status for the provided request identifier.
	 *
	 * @param uuid the uuid
	 * @return the job status
	 */
	public String getJobStatus(UUID uuid) ;

}
