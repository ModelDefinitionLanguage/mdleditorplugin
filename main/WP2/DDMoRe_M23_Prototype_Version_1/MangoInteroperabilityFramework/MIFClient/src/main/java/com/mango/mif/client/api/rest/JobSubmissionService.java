package com.mango.mif.client.api.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.model.wadl.Description;
import org.apache.cxf.jaxrs.model.wadl.Descriptions;

import com.mango.mif.exception.MIFException;

/**
 * JobSubmissionService provides a RESTful mechanism for submitting a job for execution.
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 */
@Path("/jobsubmission")
public interface JobSubmissionService {

    @GET        
    @Produces({ MediaType.TEXT_XML})
    @Descriptions({ @Description("Provide an index for help documentation on XML schema for job submission") })
    String indextext();
    
    @GET        
    @Produces({ MediaType.TEXT_HTML})
    @Descriptions({ @Description("Provide an index for help documentation on XML schema for job submission") })
    String indexweb();

    @POST
    @Path("/prepare")
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
    @Produces({ MediaType.APPLICATION_XML, MediaType.TEXT_XML })
    @Descriptions({ @Description("Prepare an execution request for a job") })
    String prepare(@FormParam("userName") String userName, @FormParam("encryptedPassword") String encryptedPassword) throws MIFException;
    
    
    @POST
    @Path("/execute")
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
    @Produces({ MediaType.APPLICATION_XML, MediaType.TEXT_XML })
    @Descriptions({ @Description("Submit an execution request for a job") })
    String execute(@FormParam("executionRequest") String executionRequest) throws MIFException;
}
