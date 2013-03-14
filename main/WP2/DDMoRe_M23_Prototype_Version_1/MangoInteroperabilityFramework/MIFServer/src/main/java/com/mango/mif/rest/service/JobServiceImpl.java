/* File			:  JobServiceImpl.java
 * Project		:  MIFServer
 * Created on	:  10 Jul 2012
 */
package com.mango.mif.rest.service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.core.Context;
import javax.xml.ws.soap.MTOM;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.mango.mif.client.api.rest.BootstrapActiveChildJobs;
import com.mango.mif.client.api.rest.FileList;
import com.mango.mif.client.api.rest.FileSystemItem;
import com.mango.mif.client.api.rest.JobService;
import com.mango.mif.client.api.rest.JobSummaryMessages;
import com.mango.mif.client.api.rest.MIFResponse;
import com.mango.mif.client.api.rest.NmMetrics;
import com.mango.mif.client.api.rest.ResponseStatus;
import com.mango.mif.connector.domain.Job;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.InvokerResult;
import com.mango.mif.core.exec.invoker.InvokerHelper;
import com.mango.mif.domain.DetailedStatus;
import com.mango.mif.domain.ExecutionType;
import com.mango.mif.domain.JobStatus;
import com.mango.mif.domain.nonmemsgeconnector.NONMEMProcessingDetailedStatus;
import com.mango.mif.domain.psnsgeconnector.PsNBootstrapProcessingDetailedStatus;
import com.mango.mif.domain.psnsgeconnector.PsNSCMProcessingDetailedStatus;
import com.mango.mif.domain.psnsgeconnector.PsNVPCProcessingDetailedStatus;
import com.mango.mif.exception.MIFException;
import com.mango.mif.managers.JobManagementService;
import com.mango.mif.sge.SGEJobCancellationRequestHandler;

/**
 * @version $Revision: $ as of $Date: $get
 *          <p>
 *          SVN Entry : $HeadURL: $
 *          <p>
 *          SVN ID : $Id: $
 *          <p>
 *          Last edited by : $Author: $
 */
@MTOM
@Service
public class JobServiceImpl implements JobService {

    private static final String	TYPE_DIR	= "d";

	private static final String	TYPE_FILE	= "f";

	public static final String	CAT_CMD	= "cat ";

	public static final String	TAIL_CMD	= "tail -c";

	private static final String RF = "rf";

    private static final String LD = "ld";

    final static Logger LOG = Logger.getLogger(JobServiceImpl.class);

    private JobManagementService jobManagementService;
   
	private MessageContext messageContext;

    private SGEJobCancellationRequestHandler sgeJobCancellationRequestHandler;

    private final static byte[] EMPTY_FILE_CONTENT = new byte[0];

    public MessageContext getMessageContext() {
        return messageContext;
    }

    @Context
    public void setMessageContext(MessageContext messageContext) {
        this.messageContext = messageContext;
    }

    /**
     * Configurable list of patterns to blacklist files from directory listing.
     */
    // TODO: Inject
    private Collection<String> fileTypeBlackList = new ArrayList<String>();

    /**
     * Max response size foe the header.
     */
    @Value("${jobservice.controller.tailfile.maxsize}")
    private int tailFileMaxSizeInBytes;

    /**
     * The root folder on the filesystem. Empty by default
     * 
     */
    private String rootFolder = "";

    /**
     * Gets the job status.
     * 
     * @param requestId
     *            the job id
     * @return the job status
     */
    @Override
    public String getJobStatus(String requestId) {
        LOG.info("Retrieving client request status for request " + requestId);
        Job job = jobManagementService.getJob(requestId);
        if (null == job) {
            return JobStatus.NOT_AVAILABLE.name();
        } else {
            String status = job.getClientRequestStatus();
            LOG.info("Status of the Job " + requestId + " is" + status);
            return status;
        }
    }

    @Override
    public FileList listDir(String jobId, String endPath) throws MIFException {

        final String NULL_VALUE = "null";

        FileList itemList = new FileList();
        // Get working directory from the job!
        Job job = jobManagementService.getJob(jobId);
        if (null == job) {
            throw new MIFException("Job does not exist: " + jobId);
        }

        String workingDir = job.getGridJobDirectory().getAbsolutePath();
        InvokerHelper invokerHelper = new InvokerHelper(job.getInvoker());
        try {
            if (null == workingDir || workingDir.trim().isEmpty()
                    || !invokerHelper.directoryExists(workingDir) || NULL_VALUE.equals(workingDir)) {
                return itemList;
            }
        } catch (ExecutionException e1) {
            throw new MIFException("Unable to check working directory exists using Invoker: " + e1.getMessage());
        }

        LOG.debug("listDir() - workingDir from job = " + "*" + workingDir + "*");

        // Get all the directories at this level
        List<String> directories = getFilesystemItems(jobId, workingDir, endPath, LD);
        for (String dir : directories) {

            String folderUri = getMessageContext().getUriInfo().getRequestUri().toString();
            try {
                folderUri += URLEncoder.encode(dir, "UTF-8");
            } catch (UnsupportedEncodingException uee) {
                throw new MIFException("Could not encode URL", uee);
            }
            FileSystemItem item = new FileSystemItem();
            item.setName(dir.trim());
            try {
                item.setUri(new URI(folderUri));
            } catch (URISyntaxException e) {
                throw new MIFException(e);
            }

            item.setEndPath(getMessageContext().getUriInfo().getPathParameters().get("endPath").get(0) + "/" + dir.trim());
            item.setType(FileSystemItem.FOLDER_ITEM);
            itemList.getFileSystemItems().add(item);
        }

        // Get all the files at this level
        List<String> filenames = getFilesystemItems(jobId, workingDir, endPath, RF);
        for (String file : filenames) {
            String fileUri = getMessageContext().getUriInfo().getRequestUri().toString().replaceAll("/ld/", "/rcf/");

            try {
                fileUri += URLEncoder.encode(file, "UTF-8");
            } catch (UnsupportedEncodingException uee) {
                throw new MIFException("Could not encode URL", uee);
            }
            FileSystemItem item = new FileSystemItem();
            item.setName(file.trim());
            try {
                item.setUri(new URI(fileUri));
            } catch (URISyntaxException e) {
                throw new MIFException(e);
            }

            String pathToSet = ("/" + workingDir + "/" + getMessageContext().getUriInfo().getPathParameters().get("endPath").get(0) + "/" + file.trim());
            item.setEndPath(pathToSet);

            LOG.debug("listDir() - endPath set on the filesystemitem = " + "*" + pathToSet + "*");

            item.setType(FileSystemItem.FILE_ITEM);
            itemList.getFileSystemItems().add(item);
        }
        return itemList;
    }

    /**
     * Propagates OS commands using the Invoker in order to identify files in a
     * single folder.
     * 
     * @param jobId
     *            the id of the Job associated with the files.
     * @param workingDir
     *            the working directory location where the files reside.
     * @param endPath
     *            something we can add on to the end of the path if needed
     * @param filesystemItemType
     *            "rf" = read files, "ld" = list directories
     * @return List the files found that are not blacklisted.
     * @throws MIFException
     *             if something goes wrong when issuing the Invoker OS commands.
     */
    private List<String> getFilesystemItems(String jobId, String workingDir,
            String endPath, String filesystemItemType) throws MIFException {

        Job job = jobManagementService.getJob(jobId);
        try {
            InvokerHelper invokerHelper = new InvokerHelper(job.getInvoker());
            if (filesystemItemType.equals(RF)) {
                InvokerResult invokerResult = getListing(TYPE_FILE, workingDir, endPath, invokerHelper);
                List<String> files = getValidFiles(resultLinesAsList(invokerResult));
                LOG.info("Returning links to files for job " + jobId);
                return files;
            } else if (filesystemItemType.equals(LD)) {
                List<String> dirs = resultLinesAsList(getListing(TYPE_DIR, workingDir, endPath, invokerHelper));
                LOG.info("Returning links to directories for job " + jobId);
                return dirs;
            } else {
                return new ArrayList<String>();
            }
        } catch (ExecutionException e) {
            LOG.error("Caught " + e.getClass().getName() + ": " + e.getMessage());
            throw new MIFException(e);
        }
    }

	private InvokerResult getListing(String typeFlag, String workingDir, String endPath, InvokerHelper invokerHelper) throws ExecutionException {
		return invokerHelper.runAndReportFailures(buildRetrieveCommand(
		        workingDir, endPath, typeFlag));
	}

	private List<String> resultLinesAsList(InvokerResult invokerResult) {
		return Arrays.asList(invokerResult.getStdout().split("\n"));
	}

    /**
     * Build a command which will retrieve either a list of files, or a list of directories,
     * depending on the args specified.
     * 
     * @param dir the directory to retrieve from
     * @param append something to arbitrarily stick on the end of the directory 
     * @param type The type of thing to retrieve, f=files d=directories
     */
    private String buildRetrieveCommand(String dir, String append, String type) {
        Preconditions.checkNotNull(append);
        //FIXME: this should be done in a freemarker template
        append = append.trim();
        if (!append.isEmpty()) {
            dir = dir + "/" + append;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("if cd \"");
        sb.append(dir);
        sb.append("\"; then find . -maxdepth 1 -type ");
        sb.append(type);
        sb.append(" | cut -c3-; else echo cd \"");
        sb.append(dir);
        sb.append("\" failed >&2; exit 99; fi");

        return sb.toString();
    }

    /**
     * Gets the contents of a file as specified by a RESTful link by invoking OS
     * commands as user. The String contents are set on the ResponseBody.
     * 
     * @param jobId
     *            the job id which is used as a directory name in the path.
     * @param endPath
     *            the rest of the path to the file to be returned.
     * @return String set on the ResponseBody
     */
    @Override
    public byte[] getTailedFileContents(String jobId, String endPath) throws MIFException {
        Job job = jobManagementService.getJob(jobId);
        if (null == job) {
            return EMPTY_FILE_CONTENT;
        }

        byte[] fileContents;
        try {
            InvokerHelper invokerHelper = new InvokerHelper(job.getInvoker());
            InvokerResult result = invokerHelper.runAndReportFailures(buildTailFileCommand(endPath));
            fileContents = result.getOutputStream().getBytes(Charsets.UTF_8.name());
        } catch (ExecutionException e) {
            throw new MIFException(
                    "Problem retrieving file: Invoker failure executing: " + buildTailFileCommand(endPath), e);
        } catch (UnsupportedEncodingException e) {
            throw new MIFException(
                    "Problem dealing with file encoding: Invoker failure executing: " + buildTailFileCommand(endPath), e);
        }
        return fileContents;
    }

    /**
     * Gets the contents of a file as specified by a RESTful link by invoking OS
     * commands as user. The String contents are set on the ResponseBody.
     * 
     * @param jobId
     *            the job id which is used as a directory name in the path.
     * @param endPath
     *            the rest of the path to the file to be returned.
     * @return String set on the ResponseBody
     */
    @Override
    public byte[] getFullFileContents(String jobId, String endPath) throws MIFException {

        Job job = jobManagementService.getJob(jobId);
        if (null == job) {
            return EMPTY_FILE_CONTENT;
        }

        byte[] fileContents;
        try {
            InvokerHelper invokerHelper = new InvokerHelper(job.getInvoker());
            InvokerResult result = invokerHelper.runAndReportFailures(buildGetFileCommand(endPath));
            fileContents = result.getOutputStream().getBytes();
        } catch (ExecutionException e) {
            throw new MIFException(
                    "Problem retrieving file:  Invoker execution failure trying to cat " + endPath, e);
        }
        return fileContents;
    }

    /**
     * Tails the specified file by a measure of configured max bytes.
     * 
     * @param jobId
     * @param workingDir
     */
    private String buildTailFileCommand(String filePath) {
        StringBuilder sb = new StringBuilder();
        sb.append(TAIL_CMD);
        sb.append(tailFileMaxSizeInBytes);
        sb.append(" \"");
        sb.append("/");
        sb.append(filePath);
        sb.append("\"; ");
        return sb.toString();
    }

    /**
     * Tails the specified file by a measure of configured max bytes.
     * 
     * @param jobId
     * @param workingDir
     */
    private String buildGetFileCommand(String filePath) {

        StringBuilder sb = new StringBuilder();
        sb.append(CAT_CMD);
        sb.append(" \"");
        sb.append("/");
        sb.append(filePath);
        sb.append("\"; ");

        return sb.toString();
    }

    /**
     * Returns all valid files based on the ignore patterns supplied, Currently
     * only checks suffixes to ignore.
     * 
     * @param dir
     * @return
     * @return
     */
    private ArrayList<String> getValidFiles(List<String> files) {
        ArrayList<String> validFiles = new ArrayList<String>();
        for (Iterator<String> i = files.iterator(); i.hasNext();) {
            String file = i.next();
            if (notBlackListed(file)) {
                validFiles.add(file);
            }
        }
        return validFiles;
    }

    /**
     * Checks if the file is not blacklisted based on the ignore patterns set
     * for suffices and prefices.
     * 
     * @param file
     * @return
     */
    private boolean notBlackListed(String file) {
        // TODO: Do we want prefix blacklist too?
        /*
         * for(String pattern : fileTypePrefixBlackList) { if
         * (file.startsWith(pattern)) { return false; } }
         */
        for (String pattern : fileTypeBlackList) {
            if (file.endsWith(pattern)) {
                return false;
            }
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * @see com.mango.mif.client.api.rest.JobService#getRunningJobsSummaryMessages()
     */
    @Override
    public JobSummaryMessages getRunningJobsSummaryMessages() {
        Stopwatch watch = new Stopwatch();
        watch.start();
        JobSummaryMessages jobSummaryMessages = new JobSummaryMessages();
        try {
            jobSummaryMessages.setSummaryMessages(jobManagementService.getSummaryMessagesForRunningJobs());
        } catch (Throwable t) {
            LOG.error("error retrieving job summary messages", t);
            jobSummaryMessages.setStatus(ResponseStatus.FAILURE);
            jobSummaryMessages.setErrorMessage(t.getMessage());
        } finally {
            watch.stop();
            long timeTaken = watch.elapsedMillis();
            LOG.debug("Took " + timeTaken + " ms to query for "
                    + getNumberOfSummaries(jobSummaryMessages) + " running jobs summary messages");
        }
        return jobSummaryMessages;
    }

    private int getNumberOfSummaries(JobSummaryMessages jobSummaryMessages) {
        return (jobSummaryMessages.getSummaryMessages() == null) ? 0 : jobSummaryMessages.getSummaryMessages().size();
    }


    private Job getJob(String jobID) throws MIFException {
        Job job = jobManagementService.getJob(jobID);
        if (job == null) {
            LOG.error("Job " + jobID + " does not exist. ");
            throw new MIFException("Job " + jobID + " does not exist. ");
        }
        return job;
    }
    
    @Override
    public DetailedStatus getDetailedStatus(String jobId) throws MIFException {
        LOG.debug("Retrieving Detailed status for request " + jobId);
        Job job = getJob(jobId);
        DetailedStatus detailedStatus = retrieveDetailedStatus(job);
        return detailedStatus;
    }

    @Override
    public PsNBootstrapProcessingDetailedStatus getPsNBootstrapDetailedStatus(String jobId) {
        
    	DetailedStatus detailedStatus = null;
    	PsNBootstrapProcessingDetailedStatus resultDetailedStatus = new PsNBootstrapProcessingDetailedStatus();
    	
    	try{
	        Job job = getJob(jobId);
	        if(job.getExecutionType() != null && !ExecutionType.PsN_Bootstrap.equals(job.getExecutionType())) {
	            final String errorMessage = "Job is not a Bootstrap, found: " + job.getExecutionType();
				LOG.error(errorMessage);
				resultDetailedStatus.setErrors(errorMessage);
				return resultDetailedStatus;
	        }
	        detailedStatus = retrieveDetailedStatus(job);   
    	} catch (Exception e) {
			final String errorMessage = e.getMessage();
			LOG.error(errorMessage);
			resultDetailedStatus.setErrors(errorMessage);
			return resultDetailedStatus;
		}
         
        if(detailedStatus instanceof PsNBootstrapProcessingDetailedStatus) {
            resultDetailedStatus = (PsNBootstrapProcessingDetailedStatus)detailedStatus;
        } else {
            resultDetailedStatus = new PsNBootstrapProcessingDetailedStatus();
            copyDetails(detailedStatus, resultDetailedStatus);
        }
        return resultDetailedStatus;
    }

    @Override
    public PsNSCMProcessingDetailedStatus getPsNSCMDetailedStatus(String jobId) {
        
		DetailedStatus detailedStatus = null;
		PsNSCMProcessingDetailedStatus resultDetailedStatus = new PsNSCMProcessingDetailedStatus();

		try {
			Job job = getJob(jobId);
			if (job.getExecutionType() != null
					&& !ExecutionType.PsN_SCM.equals(job.getExecutionType())) {
				final String errorMessage = "Job is not a SCM, found: " + job.getExecutionType();
				LOG.error(errorMessage);
				resultDetailedStatus.setErrors(errorMessage);
				return resultDetailedStatus;
			}
			detailedStatus = retrieveDetailedStatus(job);
			
		} catch (Exception e) {
			final String errorMessage = e.getMessage();
			LOG.error(errorMessage);
			resultDetailedStatus.setErrors(errorMessage);
			return resultDetailedStatus;
		}

		if (detailedStatus instanceof PsNSCMProcessingDetailedStatus) {
			resultDetailedStatus = (PsNSCMProcessingDetailedStatus) detailedStatus;
		} else {
			resultDetailedStatus = new PsNSCMProcessingDetailedStatus();
			copyDetails(detailedStatus, resultDetailedStatus);
		}

        return resultDetailedStatus;
    }

    @Override
    public PsNVPCProcessingDetailedStatus getPsNVPCDetailedStatus(String jobId) throws MIFException {
        
        Job job = getJob(jobId);
        if (job.getExecutionType() != null && !ExecutionType.PsN_VPC.equals(job.getExecutionType())) {
            throw new MIFException("Job is not a VPC, found: " + job.getExecutionType());
        }
        DetailedStatus detailedStatus = retrieveDetailedStatus(job);
        PsNVPCProcessingDetailedStatus resultDetailedStatus;
        if(detailedStatus instanceof PsNVPCProcessingDetailedStatus) {
            resultDetailedStatus = (PsNVPCProcessingDetailedStatus)detailedStatus;
        } else {
            resultDetailedStatus = new PsNVPCProcessingDetailedStatus();
            copyDetails(detailedStatus, resultDetailedStatus);
        }
        return resultDetailedStatus;
    }

    @Override
    public NONMEMProcessingDetailedStatus getNONMEMDetailedStatus(String jobId) throws MIFException {
        
        Job job = getJob(jobId);        
        if (job.getExecutionType() != null) {
            if(!(ExecutionType.PsN_EXEC.equals(job.getExecutionType())||ExecutionType.NMFE.equals(job.getExecutionType()))) {
                throw new MIFException("Job is not a NONMEM run, found: " + job.getExecutionType());
            }
        }
        
        DetailedStatus detailedStatus = retrieveDetailedStatus(job);
        NONMEMProcessingDetailedStatus resultDetailedStatus;
        if(detailedStatus instanceof NONMEMProcessingDetailedStatus) {
            resultDetailedStatus = (NONMEMProcessingDetailedStatus)detailedStatus;
        } else {
            resultDetailedStatus = new NONMEMProcessingDetailedStatus();
            copyDetails(detailedStatus, resultDetailedStatus);
        }
        return resultDetailedStatus;
    }
    
    @Override
    public BootstrapActiveChildJobs getActiveJobsOfBootstrap(String jobId) {
        
        BootstrapActiveChildJobs activeChildJobs = new BootstrapActiveChildJobs();
        Job job = jobManagementService.getJob(jobId);
        if (job == null) {
            activeChildJobs.setStatus(ResponseStatus.FAILURE);
            activeChildJobs.setErrorMessage("Job " + jobId + " does not exist.");
            return activeChildJobs;
        }

        if (isBootstrap(job)) {
            PsNBootstrapProcessingDetailedStatus detailedStatus = null;
            try {
                detailedStatus = (PsNBootstrapProcessingDetailedStatus) job.getDetailedStatus();
            } catch (MIFException e) {
                LOG.error(String.format("could not get detailed status from job instance: %s", job), e);
                activeChildJobs.setStatus(ResponseStatus.FAILURE);
                activeChildJobs.setErrorMessage("Could not retrieve job's " + jobId + " detailed status. Cause " + e.getMessage());
                return activeChildJobs;
            }
            if (detailedStatus == null) {
                activeChildJobs.setStatus(ResponseStatus.FAILURE);
                activeChildJobs.setErrorMessage("Detailed status for job " + jobId + " does not exist");
                return activeChildJobs;
            }
            activeChildJobs.setActiveChildJobs(detailedStatus.getActiveChildNONMEMProcesses());
        } else {
            activeChildJobs.setStatus(ResponseStatus.FAILURE);
            activeChildJobs.setErrorMessage("Job " + jobId + " is not of type " + ExecutionType.PsN_Bootstrap);
        }
        return activeChildJobs;
    }

    private boolean isBootstrap(Job job) {
        return ExecutionType.PsN_Bootstrap.equals(getJobExecutionType(job));
    }

    private ExecutionType getJobExecutionType(Job job) {
        return ExecutionType.valueOf(job.getExecutionRequest().getType());
    }

    @Override
    public MIFResponse killPsNBootstrapChildSGEJobs(String jobId, List<String> sgeJobIds) {
        LOG.debug("Client requested cancellation of child SGE jobs of bootstrap with ID: " + jobId + ". Child SGE job ids: " + sgeJobIds);
        MIFResponse response = new MIFResponse();
        if (jobId == null) {
            response.setStatus(ResponseStatus.FAILURE);
            response.setErrorMessage("Request ID was not set.");
            return response;
        }
        if (sgeJobIds == null) {
            response.setStatus(ResponseStatus.FAILURE);
            response.setErrorMessage("SGE job ids list was not set.");
            return response;
        }
        if (sgeJobIds.size() == 0) {
            response.setStatus(ResponseStatus.FAILURE);
            response.setErrorMessage("No ids in the list of jobs to cancel.");
            return response;
        }
        Job job = jobManagementService.getJob(jobId);
        if (job == null) {
            response.setStatus(ResponseStatus.FAILURE);
            response.setErrorMessage("Request ID " + jobId + " does not exist.");
            return response;
        }

        ExecutionType executionType = getJobExecutionType(job);
        if (!ExecutionType.PsN_Bootstrap.equals(executionType)) {
            response.setStatus(ResponseStatus.FAILURE);
            response.setErrorMessage("Job " + jobId + " is not of type " + ExecutionType.PsN_Bootstrap);
            return response;
        }

        try {
            sgeJobCancellationRequestHandler.cancelChildSGEJobs(job, sgeJobIds);
        } catch (ExecutionException e) {
            LOG.warn("Error when cancelling bootstrap child SGE jobs " + sgeJobIds, e);
            response.setErrorMessage(e.getMessage());
            response.setStatus(ResponseStatus.SUCCESS); //we always return success if cancellation of a child job fails (the qdel might be issued after the job has completed on the grid)
            return response;
        }
        return response;
    }

    @Override
    public NmMetrics getNONMEMMetrics(String requestId) {
        NmMetrics nmMetrics = new NmMetrics();
        Job job = jobManagementService.getJob(requestId);        
        if (null == job) {
            final String errorMessage = "Unable to find job details for " + requestId;
			LOG.info(errorMessage);
			nmMetrics.setErrors(errorMessage);
			return nmMetrics;
        }
        
        try {    		
    		DetailedStatus detailedStatus = retrieveDetailedStatus(job);
    		copyDetails(detailedStatus, nmMetrics);
    		
        	NONMEMMetricsCollector metricsCollector = new NONMEMMetricsCollector();
        	metricsCollector.collectNONMEMMetrics(job, nmMetrics);
        	
        } catch (Throwable t) {
			final String errorMessage = t.getMessage();
			LOG.error(errorMessage);
			nmMetrics.setErrors(errorMessage);
		}
        return nmMetrics;
    }

	public SGEJobCancellationRequestHandler getSgeJobCancellationRequestHandler() {
        return sgeJobCancellationRequestHandler;
    }

    public void setSgeJobCancellationRequestHandler(SGEJobCancellationRequestHandler sgeJobCancellationRequestHandler) {
        this.sgeJobCancellationRequestHandler = sgeJobCancellationRequestHandler;
    }
    
    public JobManagementService getJobManagementService() {
		return jobManagementService;
	}

    @Required
	public void setJobManagementService(JobManagementService jobManagementService) {
		this.jobManagementService = jobManagementService;
	}

    
    /**
     * @return the fileTypeBlackList
     */
    public Collection<String> getFileTypeBlackList() {
        return fileTypeBlackList;
    }

    /**
     * @param fileTypeBlackList the fileTypeBlackList to set
     */
    public void setFileTypeBlackList(Collection<String> fileTypeBlackList) {
        this.fileTypeBlackList = fileTypeBlackList;
    }

    public String getRootFolder() {
        return rootFolder;
    }

    public void setRootFolder(String rootFolder) {
        this.rootFolder = rootFolder;
    }

    public int getTailFileMaxSizeInBytes() {
        return tailFileMaxSizeInBytes;
    }

    public void setTailFileMaxSizeInBytes(int tailFileMaxSizeInBytes) {
        this.tailFileMaxSizeInBytes = tailFileMaxSizeInBytes;
    }

    /**
     * Retrieves file contents within a given Job context
     * @param job
     * @param file
     * @return
     * @throws MIFException
     */
    private byte[] getFileContentsForJob(Job job, File file) throws MIFException {
        byte[] fileContents = EMPTY_FILE_CONTENT;

        try {
            InvokerHelper invokerHelper = new InvokerHelper(job.getInvoker());

            InvokerResult result = invokerHelper
                    .runAndReportFailures(buildGetFileCommand(file.getPath()));

            fileContents = result.getOutputStream().getBytes();
        } catch (ExecutionException e) {
            throw new MIFException(
                    "Problem retrieving file." ,e);
        }
        return fileContents;
    }

    @Override
    public byte[] getSCMLogFileContent(String jobId) {

        LOG.debug("Retrieving SCM log file contents for request " + jobId);
        if (jobId == null) {
            return EMPTY_FILE_CONTENT;
        }
        Job job = jobManagementService.getJob(jobId);
        if (job == null) {
            return EMPTY_FILE_CONTENT;
        }

        if (!ExecutionType.PsN_SCM.name().equals(job.getExecutionRequest().getType())) {
            return EMPTY_FILE_CONTENT;
        }

        File scmLogFile = job.getPsNSCMLogFile();

        if (scmLogFile == null) {
            return EMPTY_FILE_CONTENT;
        }
        InvokerHelper invokerHelper = new InvokerHelper(job.getInvoker());

        boolean scmFileExists = false;
        try {
            scmFileExists = invokerHelper.fileExists(scmLogFile.getAbsolutePath());
        } catch (ExecutionException e1) {
            LOG.error("Exception thrown ", e1);
        }
        
        if(!scmFileExists) {
            return EMPTY_FILE_CONTENT;
        }
        
        try {
            byte[] result = getFileContentsForJob(job, scmLogFile);
            LOG.debug(new String(result,Charset.forName("UTF-8")));
            return result;
        } catch (MIFException e) {
            LOG.error("Problem when retrieving contents of scm log file ", e);
            return EMPTY_FILE_CONTENT;
        }
    }

	private void copyDetails(DetailedStatus from, DetailedStatus to) {
	    to.setRequestId(from.getRequestId());
	    to.setSummary(from.getSummary());
	}

	private DetailedStatus retrieveDetailedStatus(Job job) throws MIFException {
	    DetailedStatus detailedStatus = null;
	    try {
	        detailedStatus = job.getDetailedStatus();
	    } catch (MIFException e) {
	        LOG.error(String.format("error getting detail status for job(%s)", job.getJobId()), e);
	        throw new MIFException("Could not retrieve detailed status for the request " + job.getJobId() + ".", e);
	    }
	    if (detailedStatus == null) {
	        LOG.debug("Detailed status for job " + job.getJobId() + " was null");
	        throw new MIFException("Detailed status for jobId " + job.getJobId() + " does not exist. ");
	    }
	    return detailedStatus;
	}
	
	 /**
     * Retrieve all jobs marked as acknowledged upto the date specified but after specified 
     * date minus a configurable number of days.
     * @return String[] the job ids
     * @param dateTo long value representing the linux date upto which we look for
     * acknowledged jobs
     */
    @Override
    public String[] getAcknowledgedJobsByDate(Long dateTo){
    
    	return jobManagementService.getAcknowledgedJobsToDate(dateTo);
    }
    
}