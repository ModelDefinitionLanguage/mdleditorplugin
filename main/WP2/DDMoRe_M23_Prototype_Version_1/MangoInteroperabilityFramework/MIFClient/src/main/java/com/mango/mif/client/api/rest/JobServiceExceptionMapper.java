/* File			:  JobServiceExceptionMapper.java
 * Project		:  MIFServer
 * Created on	:  10 Jul 2012
 */
package com.mango.mif.client.api.rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
@Provider
public class JobServiceExceptionMapper implements ExceptionMapper<JobServiceException> {

	@Override
	public Response toResponse(JobServiceException exception) {
		return Response.serverError().tag(exception.getMessage()).build();
	}

}
