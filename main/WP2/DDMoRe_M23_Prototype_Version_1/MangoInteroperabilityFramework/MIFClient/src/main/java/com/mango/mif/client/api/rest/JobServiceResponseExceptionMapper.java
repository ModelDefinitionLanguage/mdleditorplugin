/* File			:  JobServiceResponseExceptionMapper.java
 * Project		:  MIFServer
 * Created on	:  10 Jul 2012
 */

package com.mango.mif.client.api.rest;

import java.util.Iterator;

import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.ResponseExceptionMapper;

/**
 * @version $Revision: $ as of $Date: $
 *          <p>
 *          SVN Entry : $HeadURL: $
 *          <p>
 *          SVN ID : $Id: $
 *          <p>
 *          Last edited by : $Author: $
 */
public class JobServiceResponseExceptionMapper implements
		ResponseExceptionMapper<JobServiceException> {

	@Override
	public JobServiceException fromResponse(Response response) {
		StringBuilder msg = new StringBuilder();
		if (response != null) {
			if (response.getMetadata() != null
					&& response.getMetadata().get("ETag") != null) {
				Iterator<Object> tags = response.getMetadata().get("ETag")
						.iterator();
				while (tags.hasNext()) {
					String tag = tags.next().toString();
					msg.append(tag);
					msg.append(String.format(" %n"));
				}
			} else {
				msg.append("Technical Failure: ");
				msg.append(response.getStatus());
			}
		}
		return new JobServiceException(msg.toString());
	}

}
