/* File			:  BasicResourceRetriever.java
 * Project		:  MIFClient
 * Created on	:  11 Jun 2012
 */
package com.mango.mif.core.resource;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.google.common.base.Preconditions;
import com.mango.mif.core.exec.CommandBuilder;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.Invoker;
import com.mango.mif.core.exec.invoker.InvokerHelper;
import com.mango.mif.utils.Pair;

/**
 * Responsible for copying Resources from Source Directory to the Destination Directory.
 * This should also support list of files and the folder to be ignored during copy.
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class ResourceCopierImpl implements ResourceCopier {

    private static Logger logger = Logger.getLogger(ResourceCopierImpl.class);

    private static final String FILES_TO_COPY = "filesToCopy";
    private static final String FILES_LISTING = "filesListing";
    private static final String IGNORE_PATTERN = "ignorePattern";
    private static final String SOURCE_DIRECTORY = "sourceDirectory";
    private static final String DEST_DIRECTORY = "destinationDirectory";
    private static final String JOB_ID = "jobId";

    /**
     * Destination directory where the result files should be copied into
     */
    protected File destDirectory;

    /**
     * Source directory where the result files should be copied from
     */
    protected File sourceDirectory;

    /**
     * A file containing list of files to copy
     */
    protected File filesListing;
    
    /** We build this from the incoming file and directory ignore patterns */
    private String regexpIgnorePattern;

    /** The command builder. */
    private CommandBuilder commandBuilder;

    /** The invoker to execute the command. */
    private Invoker invoker;

    /**
     * Instantiates a new resource copier.
     *
     * @param resourceCopierParameters the resource copier object
     */
    ResourceCopierImpl(ResourceCopierParameters resourceCopierParameters) {
        Preconditions.checkNotNull(resourceCopierParameters, "Resource Copier Object must not be null");
        Preconditions.checkNotNull(resourceCopierParameters.getCommandBuilder(), "Command Builder must be set");
        Preconditions.checkNotNull(resourceCopierParameters.getSourceDirectory(), "Source Directory must be set");
        Preconditions.checkNotNull(resourceCopierParameters.getDestDirectory(), "Destination Directory must be set");
        Preconditions.checkNotNull(resourceCopierParameters.getInvoker(), "Invoker must be set");

        this.destDirectory = resourceCopierParameters.getDestDirectory();
        this.sourceDirectory = resourceCopierParameters.getSourceDirectory();
        this.invoker = resourceCopierParameters.getInvoker();
        this.commandBuilder = resourceCopierParameters.getCommandBuilder();
        this.filesListing = resourceCopierParameters.getFilesListing();

        this.regexpIgnorePattern = buildPattern(resourceCopierParameters.getFileRegexPatternIgnoreList(), resourceCopierParameters.getDirectoryIgnoreList());
    }

    /* (non-Javadoc)
     * @see com.mango.mif.core.resource.ResourceCopier#copy(java.util.List)
     */
    @Override
    public void copy(List<File> filesList) throws ExecutionException {
        List<Pair<String, String>> filesToCopy = new ArrayList<Pair<String, String>>();
        if (!CollectionUtils.isEmpty(filesList)) {
            prepareFilesToCopy(filesList, filesToCopy);
            setUpBuilderToCopySpecificFilesFromSourceDirectory(filesToCopy);
        }
        setSrcDirectoryOnBuilder();
        setDesDirOnBuilder();
        setIgnoreRegexp();
        setFilesListingOnBuilder();
        String command = buildCommand();
        try {
	        logExecutionMetrics("started");
	        InvokerHelper helper = new InvokerHelper(getInvoker());
	        helper.runAndReportFailures(command);
        } finally {
        	logExecutionMetrics("ended");
        }
    }
    
    /**
     * Logs the execution metrics for the copying process
     */
    private void logExecutionMetrics(String status) {
    	if(getCommandBuilder() != null && getCommandBuilder().getVariable(JOB_ID) != null) {
    		logger.info(String.format("*** Execution Metrics : {Resource.Copying} Copying of resources for request id [%s] has %s", 
	        		getCommandBuilder().getVariable(JOB_ID).toString(), status));
    	}
    }

    /**
     * Sets the destination directory.
     *
     * @throws ExecutionException the execution exception
     */
    private void setDesDirOnBuilder() throws ExecutionException {
        getCommandBuilder().setVariable(DEST_DIRECTORY, getDestDirectory());

    }
    
    /**
     * Sets the destination directory.
     *
     * @throws ExecutionException the execution exception
     */
    private void setFilesListingOnBuilder() throws ExecutionException {
        getCommandBuilder().setVariable(FILES_LISTING, getFilesListing());

    }

    /**
     * Sets the source directory.
     *
     * @throws ExecutionException the execution exception
     */
    private void setSrcDirectoryOnBuilder() throws ExecutionException {
        getCommandBuilder().setVariable(SOURCE_DIRECTORY, getSourceDirectory());
    }

    /**
     * Sets the directories to ignore during copy.
     *
     * @throws ExecutionException the execution exception
     */
    private void setIgnoreRegexp() throws ExecutionException {
        if (!StringUtils.isEmpty(regexpIgnorePattern)) {
            getCommandBuilder().setVariable(IGNORE_PATTERN, regexpIgnorePattern);
        }
    }

    /**
     * Builds the command.
     *
     * @return the string
     * @throws ExecutionException the execution exception
     */
    private String buildCommand() throws ExecutionException {
        return getCommandBuilder().getCommand();
    }

    /**
     * Sets the up builder to copy files.
     *
     * @param filesToCopy the files to copy
     * @throws ExecutionException the execution exception
     */
    private void setUpBuilderToCopySpecificFilesFromSourceDirectory(List<Pair<String, String>> filesToCopy)
            throws ExecutionException {
        getCommandBuilder().setVariable(FILES_TO_COPY, filesToCopy);
    }

    /**
     * Prepare files to copy.
     *
     * @param filesList the files list
     * @param filesToCopy the files to copy
     */
    private void prepareFilesToCopy(List<File> filesList,
            List<Pair<String, String>> filesToCopy) {
        for (File file : filesList) {
            File destFile = getRelativePath(file);
            Pair<String, String> fromTo = Pair.of(file.getAbsolutePath(), destFile.getAbsolutePath());
            filesToCopy.add(fromTo);
        }
    }

    /**
     * Build a single regexp from the file and directory ignore list patterns.
     * @param fileIgnoreList the list of file patterns to ignore
     * @param dirIgnoreList the list of directory patterns to ignore
     * @return the built pattern as a single string
     */
    private String buildPattern(List<String> fileIgnoreList, List<String> dirIgnoreList) {
        StringBuilder sb = new StringBuilder();

        // Because these are FILE patterns, they must occur BETWEEN a "/" character and the
        // END of the match.
        if (!CollectionUtils.isEmpty(fileIgnoreList)) {
            for (String fileRegexp : fileIgnoreList) {
                if (sb.length() > 0) {
                    sb.append("|");
                }

                if (!fileRegexp.startsWith("/")) {
                    sb.append("/");
                }
                sb.append(fileRegexp);
                sb.append("$");
            }
        }

        // Because these are DIRECTORY patterns, they must occur BETWEEN two "/" characters
        if (!CollectionUtils.isEmpty(dirIgnoreList)) {
            for (String dirRegexp : dirIgnoreList) {
                if (sb.length() > 0) {
                    sb.append("|");
                }
                if (!dirRegexp.startsWith("/")) {
                    sb.append("/");
                }
                sb.append(dirRegexp);
                sb.append("/");
            }
        }
        return sb.toString();
    }

    /**
     * Gets the relative path.
     *
     * @param file the file
     * @return the relative path
     */
    private File getRelativePath(File file) {
        String path = file.getAbsolutePath().replace(getSourceDirectory() + File.separator, "");
        return new File(getDestDirectory(), path);
    }

    public File getDestDirectory() {
        return destDirectory;
    }

    public File getSourceDirectory() {
        return sourceDirectory;
    }

    public CommandBuilder getCommandBuilder() {
        return commandBuilder;
    }

    public Invoker getInvoker() {
        return invoker;
    }

    @Override
    public void setJobId(String id) throws ExecutionException {
        getCommandBuilder().setVariable(JOB_ID, id);
    }
    
    
    public File getFilesListing() {
        return filesListing;
    }
}
