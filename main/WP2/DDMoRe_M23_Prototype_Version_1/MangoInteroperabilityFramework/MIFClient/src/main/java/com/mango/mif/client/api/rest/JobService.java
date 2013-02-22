/* File			:  JobService.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Apr 13, 2012
 */
package com.mango.mif.client.api.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.model.wadl.Description;
import org.apache.cxf.jaxrs.model.wadl.Descriptions;

import com.mango.mif.domain.DetailedStatus;
import com.mango.mif.domain.nonmemsgeconnector.NONMEMProcessingDetailedStatus;
import com.mango.mif.domain.psnsgeconnector.PsNBootstrapProcessingDetailedStatus;
import com.mango.mif.domain.psnsgeconnector.PsNSCMProcessingDetailedStatus;
import com.mango.mif.domain.psnsgeconnector.PsNVPCProcessingDetailedStatus;
import com.mango.mif.exception.MIFException;

/**
 * REST Service interface for making calls to MIFServer web service. CXF
 * configuration in Spring allows us to use this interface to create a proxy
 * bean to the RESTful webservice implementation.
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
@Path("/jobService")
public interface JobService {

    @GET
    @Path("/jobs/{jobId}")
    @Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
    @Descriptions({ @Description("Get the status for a job given the job id") })
    String getJobStatus(@PathParam("jobId") String jobId);

    @GET
    @Path("/summaryMessage/allRunning")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
    @Descriptions({ @Description("Get the summary messages for ALL running jobs") })
    JobSummaryMessages getRunningJobsSummaryMessages();

    @GET
    @Path("/jobs/{jobId}/detailedStatusNew")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
    @Descriptions({ @Description("Get the new detailed status for a job given the job id") })
    DetailedStatus getDetailedStatus(@PathParam("jobId") String jobId) throws MIFException;
    
    @GET
    @Path("/jobs/{jobId}/psnbootstrapsgeconnector/detailedStatus")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
    @Descriptions({ @Description("Get the bootstrap detailed status for a job given the job id") })
    PsNBootstrapProcessingDetailedStatus getPsNBootstrapDetailedStatus(@PathParam("jobId") String jobId);

    @GET
    @Path("/jobs/{jobId}/psnscmsgeconnector/detailedStatus")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
    @Descriptions({ @Description("Get the SCM detailed status for a job given the job id") })
    PsNSCMProcessingDetailedStatus getPsNSCMDetailedStatus(@PathParam("jobId") String jobId);

    @GET
    @Path("/jobs/{jobId}/psnvpcsgeconnector/detailedStatus")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
    @Descriptions({ @Description("Get the VPC detailed status for a job given the job id") })
    PsNVPCProcessingDetailedStatus getPsNVPCDetailedStatus(@PathParam("jobId") String jobId) throws MIFException;

    @GET
    @Path("/jobs/{jobId}/nonmemsgeconnector/detailedStatus")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
    @Descriptions({ @Description("Get the NONMEM detailed status for a job given the job id") })
    NONMEMProcessingDetailedStatus getNONMEMDetailedStatus(@PathParam("jobId") String jobId) throws MIFException;
    
    @GET
    @Path("/psnscmsgeconnector/{jobId}")
    @Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, "application/text", MediaType.APPLICATION_OCTET_STREAM, MediaType.TEXT_XML })
    @Descriptions({ @Description("Get the log file content for an SCM job given the job id") })
    byte[] getSCMLogFileContent(@PathParam("jobId") String jobId);
    
    @GET
    @Path("/psnbootstrapsgeconnector/activeJobs/{jobId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
    @Descriptions({ @Description("Get the active bootstrap children of a job given the job id") })
    BootstrapActiveChildJobs getActiveJobsOfBootstrap(@PathParam("jobId") String jobId);
    
    @GET
    @Path("/psnbootstrapsgeconnector/command/cancelChildJobs/{jobId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
    @Descriptions({ @Description("Kill a bootstrap child given the childs job id and the Sun Grid Engine job ids") })
    MIFResponse killPsNBootstrapChildSGEJobs(@PathParam("jobId") String jobId, @MatrixParam("sgeJobId") List<String> sgeJobIds);

	@GET
	@Path("/jobs/{jobId}/ld/{endPath:.*}")
	@Produces({ MediaType.APPLICATION_JSON,  MediaType.TEXT_XML })
	@Descriptions({ @Description("List a directory given a job id and the path") })
	FileList listDir(@PathParam("jobId") String jobId, @PathParam("endPath") String endPath) throws MIFException;

	@GET
	@Path("/jobs/{jobId}/rf/{endPath:.*}")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM, MediaType.TEXT_XML })
	@Descriptions({ @Description("Tail a file contents given the job id and the path to the file") })
	byte[] getTailedFileContents(@PathParam("jobId") String jobId,
			@PathParam("endPath") String endPath) throws MIFException;
		
	@GET
	@Path("/jobs/{jobId}/rcf/{endPath:.*}")
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM, MediaType.TEXT_XML })
	@Descriptions({ @Description("Retrieve a file contents given the job id and the path to the file") })
	byte[] getFullFileContents(@PathParam("jobId") String jobId, @PathParam("endPath") String endPath)
			throws MIFException;

    @GET
    @Path("/nonmemmetrics/{requestId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
    @Descriptions({ @Description("Retrieves NONMEM metrics given a request id") })
    NmMetrics getNONMEMMetrics( @PathParam("requestId") String requestId);  
    
    @GET
    @Path("/jobs/acknowledgedjobs/{dateTo}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
    @Descriptions({ @Description("Retrieves acknowledged jobs from dateTo -n days up to the dateTo") })
    String[] getAcknowledgedJobsByDate( @PathParam("dateTo") Long dateTo);  
}
