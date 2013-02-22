/* File			:  Job.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  13 Mar 2012
 */
package com.mango.mif.connector.domain;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.jms.IllegalStateException;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.MapKeyClass;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.common.base.Objects;
import com.mango.mif.connector.runner.impl.JobRunnerState;
import com.mango.mif.core.api.SharedLocationManager;
import com.mango.mif.core.exec.Invoker;
import com.mango.mif.domain.DetailedStatus;
import com.mango.mif.domain.ExecutionRequest;
import com.mango.mif.domain.ExecutionType;
import com.mango.mif.exception.MIFException;
import com.mango.mif.sge.SGE;
import com.mango.mif.utils.JAXBUtils;
import com.mango.mif.utils.MIFProperties;
import com.mango.mif.utils.jaxb.ExecutionJaxbUtils;

/**
 * The job that is to be run by a connector.
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 */
@Entity
@Table(name = "JOBS")
public class Job {

    private static final File NULL_FILE = new File("NULL");

    public static final Log LOG = LogFactory.getLog(Job.class);

    public static final int EXECUTION_MSG_MAX_LEN = 10000;

    @Id
    @Column(name = "JOB_ID")
    @XmlTransient
    private String jobId;

    @XmlTransient
    @Transient
    private Invoker invoker;

    @Column(name = "CONNECTOR_ID")
    private String connectorId;

    @ElementCollection(fetch = FetchType.EAGER, targetClass = String.class)
    @CollectionTable(name = "JOBS_DATA", joinColumns = @JoinColumn(name = "JOB_ID"))
    @MapKeyClass(String.class)
    @MapKeyColumn(name = "JOB_DATA_KEY")
    @Column(name = "JOB_DATA_VALUE", nullable = true)
    private Map<String, String> data = new HashMap<String, String>();

    @Lob
    @Column(name = "EXECUTION_REQUEST_MESSAGE", length = EXECUTION_MSG_MAX_LEN)
    protected String executionRequestMsg;

    @Column(name = "CLIENT_REQUEST_STATUS")
    private String clientRequestStatus;

    @Lob
    @Column(name = "DETAILED_STATUS", length = EXECUTION_MSG_MAX_LEN)
    private String detailedStatusMsg;

    @Transient
    private DetailedStatus detailedStatus;

    @Transient
    private ExecutionRequest executionRequest;

    @Transient
    private SharedLocationManager sharedLocationManager;

    @Transient
    private boolean runInRequestDirectory = false;
    
    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "PASSWORD")
    private String password;

    @Version
    @Column(name = "VERSION")
    private long version;
    
    /**
     * The time in millis that this job was handled and acknowledged.
     */
    @Column(name = "TIME_ACKNOWLEDGED", nullable=true)
    private long acknowledged;

    /**
     * The absolute path of the timestamp file, created after all the files were copied from the
     * request folder to the grid folder.
     */
    @Column(name = "TIMESTAMP_FILE_PATH")
    private String timestampFilePath;


    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    @Transient
    public JobRunnerState getJobRunnerState() {
        String status = getData().get(JobRunnerState.STATUS);
        if (status == null) {
            return JobRunnerState.UNDEFINED;
        }
        return JobRunnerState.valueOf(status);
    }

    public void setJobRunnerState(JobRunnerState status) {
        addToDataMap(JobRunnerState.STATUS, status.name());
    }

    public String getConnectorId() {
        return connectorId;
    }

    public void setConnectorId(String connectorId) {
        this.connectorId = connectorId;
    }

    public String getExecutionRequestMsg() {
        return executionRequestMsg;
    }

    /**
     * Will deserialise the provided message to set the execution request property on this job
     * NOTE: If request provided submitAsUser=true it will also populate the job's user credentials
     * with the details contained within the request
     * @param executionRequestMsg
     * @throws MIFException
     */
    public void setExecutionRequestMsg(String executionRequestMsg) throws MIFException {
        this.executionRequestMsg = executionRequestMsg;
        try {
            executionRequest = JAXBUtils.unmarshall(executionRequestMsg, ExecutionJaxbUtils.CONTEXT_CLASSES);
        } catch (JAXBException e) {
            throw new MIFException("Could not unmarshall execution message.", e);
        }

        setJobId(executionRequest.getRequestId().toString());
        if (getExecutionRequest().getSubmitAsUserMode()) {
            setUserName(executionRequest.getUserName());
            setPassword(executionRequest.getUserPassword());
        }
    }

    public String getClientRequestStatus() {
        return clientRequestStatus;
    }

    public void setClientRequestStatus(String clientRequestStatus) {
        this.clientRequestStatus = clientRequestStatus;
    }

    public ExecutionRequest getExecutionRequest() {
        if (executionRequest == null) {
            try {
                executionRequest = JAXBUtils.unmarshall(executionRequestMsg, ExecutionJaxbUtils.CONTEXT_CLASSES);
            } catch (JAXBException e) {
                throw new RuntimeException("Could not unmarshal request!", e);
            }
        }
        return executionRequest;
    }

    /**
     * A user name that executes the job, 
     * If submit as user flag of the execution request is false, it returns the service account user name
     */
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public void addToDataMap(String key, String value) {
        this.data.put(key, value);
    }

    /**
     * The script file (.ctl file in case of NONMEM) that should be executed
     * the path is relative to the request root directory
     * @return the execution file
     */
    public String getExecutionFile() {
        if (hasExecutionFile()) {
            return getExecutionRequest().getExecutionFile();
        }
        LOG.warn("no execution file set");
        return "";
    }

    private boolean hasExecutionFile() {
        return getExecutionRequest() != null && !StringUtils.isEmpty(getExecutionRequest().getExecutionFile());
    }

    /**
     * @return The "basename" of the execution file, i.e. the file name stripped of all directories.
     */
    @Transient
    public String getExecutionFileName() {
        String executionFile = getExecutionFile();
        if (StringUtils.isNotEmpty(executionFile)) {
            File f = new File(executionFile);
            return f.getName();
        }
        return "";
    }

    /**
     * The directory for this Job on the Grid share.
     * Inside of which we will find a copy of the Project folder 
     * structure plus all of the files needed for execution
     * @return the grid job directory
     * @throws IllegalStateException 
     */
    @Transient
    public File getGridJobDirectory() {
        if (StringUtils.isEmpty(getGridJobDirectoryDataMapValue())) {
            LOG.warn(String.format("must not access grid job directory before it has been set: \n %s", this));
            return NULL_FILE;
        }
        LOG.debug("returning GridJobDirectory: " + getGridJobDirectoryDataMapValue());
        return new File(getGridJobDirectoryDataMapValue());
    }

    private String getGridJobDirectoryDataMapValue() {
        return getData().get(SGE.GRID_JOB_DIRECTORY_KEY);
    }

    public void setGridJobDirectory(String absPath) {
        this.addToDataMap(SGE.GRID_JOB_DIRECTORY_KEY, absPath);
    }

    /**
     * The working directory which was set when submitting the job to the Grid
     * This will be the directory containing the actual script/control file to be executed
     * It will be within the gridJobDirectory but NOT necessarily its root
     */
    @Transient
    public File getGridJobCurrentWorkingDirectory() {
        File gridJobExecutionFileHandle = getGridJobExecutionFile();
        if (!gridJobExecutionFileHandle.exists()) {
            LOG.warn("gridJobExecutionFile does not exist or is a directory. returning empty file handle");
            return NULL_FILE;
        } else if (!gridJobExecutionFileHandle.isDirectory()) {
            return gridJobExecutionFileHandle.getParentFile();
        } else {
            return gridJobExecutionFileHandle;
        }
    }

    @Transient
    public File getGridJobExecutionFile() {
        File gridJobDirectory = getGridJobDirectory();
        if (!gridJobDirectory.exists() || !hasExecutionFile()) {
            // no need to proceed - return empty file handle
            LOG.warn("returning empty gridJobExecutionFile");
            return gridJobDirectory;
        }
        File gridJobExecutionFileHandle = new File(gridJobDirectory, getExecutionFile());
        if (!gridJobExecutionFileHandle.exists() || gridJobExecutionFileHandle.isDirectory()) {
            LOG.warn("Cannot access" + gridJobExecutionFileHandle.getAbsolutePath() + ". Returning empty file handle.");
            return NULL_FILE;
        } else {
            LOG.debug("returning gridJobExecutionFile: " + gridJobExecutionFileHandle.getAbsolutePath());
            return gridJobExecutionFileHandle;
        }
    }

    @Transient
    public String getRequestUserName() {
        return getExecutionRequest().getUserName();
    }

    @Transient
    public String getSGEJobID() {
        return data.get(SGE.SGE_JOB_ID_KEY);
    }

    @Transient
    public File getSGEJobErrorStreamFile() {
        return new File(getGridJobDirectoryMifHiddenDir(), MIFProperties.GRID_STD_ERR_FILE_NAME);
    }

    @Transient
    public File getSGEJobOutputStreamFile() {
        return new File(getGridJobDirectoryMifHiddenDir(), MIFProperties.GRID_STD_OUT_FILE_NAME);
    }

    @Transient
    public File getGridJobDirectoryMifHiddenDir() {
        return new File(getGridJobDirectory(), MIFProperties.MIF_HIDDEN_DIRECTORY);
    }
    
    
    @Transient
    public File getRequestOutputDirectoryStdErrStreamFile() {
        return new File(getRequestOutputDirectoryMifHiddenDir(), MIFProperties.GRID_STD_ERR_FILE_NAME);
    }

    @Transient
    public File getRequestOutputDirectoryStdOutStreamFile() {
        return new File(getRequestOutputDirectoryMifHiddenDir(), MIFProperties.GRID_STD_OUT_FILE_NAME);
    }

    @Transient
    public File getRequestOutputDirectoryMifHiddenDir() {
        return new File(getRequestOutputDirectory(), MIFProperties.MIF_HIDDEN_DIRECTORY);
    }

    @Transient
    public String getRequestInputDirectory() {
        return sharedLocationManager.getRequestInputDirectory(jobId).getAbsolutePath();
    }

    @Transient
    public String getRequestOutputDirectory() {
        return sharedLocationManager.getRequestOutputDirectory(jobId).getAbsolutePath();
    }

    @Transient
    public String getRunName() {
        if (hasExecutionFile() && getExecutionFileName().indexOf(".") != -1) {
            return getExecutionFileName().substring(0, getExecutionFileName().lastIndexOf('.'));
        }
        return "";
    }

    @Transient
    public String getRunLstFileName() {
        if (hasExecutionFile()) {
            return getRunName() + MIFProperties.NONMEM_LST_FILE_EXT;
        }
        return "";
    }

    public void setSharedLocationManager(SharedLocationManager sharedLocationManager) {
        this.sharedLocationManager = sharedLocationManager;
    }

    @Transient
    public SharedLocationManager getSharedLocationManager() {
        return sharedLocationManager;
    }

    public String getTimestampFilePath() {
        return timestampFilePath;
    }

    public void setTimestampFilePath(String timestampFilePath) {
        this.timestampFilePath = timestampFilePath;
    }

    public void setExecutionRequest(ExecutionRequest executionRequest) {
        this.executionRequest = executionRequest;
    }

    public void setInvoker(Invoker invoker) {
        this.invoker = invoker;
    }

    @Transient
    public Invoker getInvoker() {
        return invoker;
    }

    public void setDetailedStatusMsg(String detailedStatusMsg) {
        this.detailedStatusMsg = detailedStatusMsg;
        this.detailedStatus = null;
    }

    public String getDetailedStatusMsg() {
        return detailedStatusMsg;
    }

    public DetailedStatus getDetailedStatus() throws MIFException {
        if (detailedStatus == null) {
            if (hasDetailedStatusMsg()) {
                try {
                    this.detailedStatus = JAXBUtils.unmarshall(detailedStatusMsg,
                            ExecutionJaxbUtils.CONTEXT_CLASSES);
                } catch (JAXBException e) {
                    throw new MIFException("Could not unmarshall detailed status.", e);
                }
            }
        }
        return detailedStatus;
    }

    private boolean hasDetailedStatusMsg() {
        return !StringUtils.isEmpty(detailedStatusMsg);
    }

    @Transient
    public File getPsNSCMLogFile() {
        String scmLogFileName = MIFProperties.getInstance().getProperty("psnscmsgeconnector.scmLogFileName");
        return new File(getPsNOutputDir(), scmLogFileName);
    }

    @Transient
    public File getPsNOutputDir() {
        String executionParameters = getExecutionRequest().getExecutionParameters();
        if (executionParameters == null) {
            executionParameters = "";
        }
        Pattern dirPattern = Pattern.compile(MIFProperties.getInstance().getProperty("psnsgeconnector.directoryOptionPattern"));
        Matcher m = dirPattern.matcher(executionParameters);
        String psnOutputDirName = null;
        if (m.find()) {
            int groupNo = m.groupCount() + 1;
            while (psnOutputDirName == null && groupNo > 0) {
                groupNo--;
                psnOutputDirName = m.group(groupNo);
            }
            if (psnOutputDirName == null) {
                psnOutputDirName = "";

            }
        }
        if (psnOutputDirName == null) {
            return getGridJobCurrentWorkingDirectory();
        } else {
            return new File(getGridJobCurrentWorkingDirectory(), psnOutputDirName);
        }
    }

    public String getSGEStartTime() {
        String startTime = data.get(SGE.SGE_JOB_START_TIME_KEY);
        if (startTime == null) {
            return "-1";
        }
        return startTime;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(jobId, executionRequestMsg, clientRequestStatus, userName);
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Job) {
            final Job other = (Job) obj;
            return Objects.equal(jobId, other.jobId)
                    && Objects.equal(executionRequestMsg, other.executionRequestMsg)
                    && Objects.equal(clientRequestStatus, other.clientRequestStatus)
                    && Objects.equal(userName, other.userName);
        } else {
            return false;
        }
    }

    @Transient
    public ExecutionType getExecutionType() {
        return ExecutionType.valueOf(getExecutionRequest().getType());
    }

    @Override
    public String toString() {
        // Check output of this now it's been refactored
        ToStringBuilder.setDefaultStyle(ToStringStyle.MULTI_LINE_STYLE);
        return ToStringBuilder.reflectionToString(this).toString();
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

	public long getAcknowledged() {
		return acknowledged;
	}

	public void setAcknowledged(long acknowledged) {
		this.acknowledged = acknowledged;
	}
    
    public void setRunInRequestDirectory(boolean runInRequestDirectory) {
        this.runInRequestDirectory = runInRequestDirectory;
    }
    
    public boolean isRunInRequestDirectory() {
        return runInRequestDirectory;
    }
}
