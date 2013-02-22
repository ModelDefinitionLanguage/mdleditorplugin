/* File			:  TemplatedResourcePublisher.java
 * Project		:  MIFClient
 * Created on	:  12 Jun 2012
 */
package com.mango.mif.core.resource.shell;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.google.common.base.Preconditions;
import com.mango.mif.core.api.ResourceCopierFactory;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.Invoker;
import com.mango.mif.core.exec.InvokerResult;
import com.mango.mif.core.exec.invoker.InvokerHelper;
import com.mango.mif.core.resource.BaseResourcePublisher;
import com.mango.mif.core.resource.ResourceCopier;
import com.mango.mif.core.resource.ResourceCopierFactoryImpl;
import com.mango.mif.core.resource.ResourceCopierParameters;
import com.mango.mif.exception.MIFException;
import com.mango.mif.utils.Pair;

/**
 * A template based resource publisher which is responsible for publishing resources.
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 *
 */
public class ShellBasedResourcePublisher extends BaseResourcePublisher {

    private static Logger logger = Logger.getLogger(ShellBasedResourcePublisher.class);

    /** The invoker. */
    Invoker invoker;

    /** The resource copier. */
    ResourceCopier resourceCopier;

    /**
     * Instantiates a new shell based resource publisher.
     *
     * @param publisherParameters the shell based resource publisher parameters
     */
    public ShellBasedResourcePublisher(PublisherParameters publisherParameters) {

        Preconditions.checkNotNull(publisherParameters, "Parameters must not be null");
        Preconditions.checkNotNull(publisherParameters.getInvoker(), "Invoker must be set");
        Preconditions.checkNotNull(publisherParameters.getCommandBuilder(), "CommandBuilder must be set");
        Preconditions.checkNotNull(publisherParameters.getSharedLocationManager(), "Shared Location manager must be set");
        Preconditions.checkNotNull(publisherParameters.getRootDirectory(), "Root Directory must be set");
        File sharedLocationRoot = publisherParameters.getSharedLocationManager().getSharedLocationRoot();
        Preconditions.checkNotNull(sharedLocationRoot, "Shared location manager must have a shared location");

        this.invoker = publisherParameters.getInvoker();
        this.commandBuilder = publisherParameters.getCommandBuilder();
        this.submitHostPreamble = publisherParameters.getSubmitHostPreamble();
        this.rootDirectory = publisherParameters.getRootDirectory();
        this.sharedLocationManager = publisherParameters.getSharedLocationManager();
        this.requestAttributes = publisherParameters.getRequestAttributes();
    }

    /**
     * 1. retrieve request id from the sharedLocationManager;
     * 2. Populate the template with parameters using a command builder (TODO create abstract PublishFilesCommandBuilder and TemplateBasePublishFilesCommandBuilder)
     *      a. root directory to copy empty folder structure from
     *      b. request input directory (sharedLocationManager.getRequestInputDirectory(requestID)
     * 3. execute the script using JSCH and user name and password
     */
    @Override
    public String publish() throws MIFException {
        List<String> directoriesToCreate = new ArrayList<String>();
        UUID uuid = null;
        File requestDir = null;
        try {
            do {
                uuid = UUID.randomUUID();
                requestDir = getRequestDirectory(uuid);
            } while (requestDir.exists());

            directoriesToCreate.add(sharedLocationManager.getSharedLocationRoot().getAbsolutePath());
            directoriesToCreate.add(requestDir.getAbsolutePath());

            File inputDir = getRequestInputDirectory(requestDir);
            File outputDir = getRequestOutputDirectory(requestDir);

            directoriesToCreate.add(inputDir.getAbsolutePath());
            directoriesToCreate.add(outputDir.getAbsolutePath());

            Pair<String, String> directoriesStructureToCopy = Pair.empty();
            directoriesStructureToCopy.setFirst(rootDirectory.getAbsolutePath());
            directoriesStructureToCopy.setSecond(inputDir.getAbsolutePath());

            // We get pairs of files in "rootedFiles".  For example:
            //   1.   /usr/share/tomcat6/navigator/resources/resource1.x
            //   2.   /usr/share/tomcat6/navigator
            // This means we want to "subtract" the path in 2 from the path in 1 and copy
            //        /usr/share/tomcat6/navigator/resources/resource1.x
            //        /shared/location/root/resources/resource1.x
            //
            List<Pair<String, String>> filesNotInSourceDirectoryToCopy = prepareSpecialFilesToCopy(uuid.toString());

            runScript(uuid, directoriesStructureToCopy, directoriesToCreate, filesNotInSourceDirectoryToCopy, submitHostPreamble);

            if (areThereAnyFilesToPublish()) {
                this.resourceCopier = createResourceCopier(rootDirectory, inputDir);
                this.resourceCopier.setJobId(uuid.toString());
                getResourceCopier().copy(this.filesToPublish);
            }

        } catch (ExecutionException exp) {
            throw new MIFException("Unexpected error during the publishing", exp);
        } catch (MIFException exp) {
            throw exp;
        } catch (Exception exp) {
            throw new MIFException("Unexpected error during the publishing", exp);
        }
        this.requestID = (uuid != null) ? uuid.toString() : null;
        return requestID;
    }

    /**
     * Creates the resource copier.
     *
     * @param sourceDir the source dir
     * @param destDir the dest dir
     * @return the resource copier
     */
    ResourceCopier createResourceCopier(File sourceDir, File destDir) {
        ResourceCopierParameters resourceCopierParameters = buildResourceCopierparameters(sourceDir, destDir);
        ResourceCopierFactory resourceCopierFactory = new ResourceCopierFactoryImpl();
        return resourceCopierFactory.create(resourceCopierParameters);
    }

    /**
     * Builds the ResourceCopier parameters.
     *
     * @param sourceDir the source dir
     * @param destDir the dest dir
     * @return the resource copier parameters
     */
    private ResourceCopierParameters buildResourceCopierparameters(
            File sourceDir, File destDir) {
        ResourceCopierParameters resourceCopierParameters = new ResourceCopierParameters();
        resourceCopierParameters.setCommandBuilder(getCommandBuilder());
        resourceCopierParameters.setDestDirectory(destDir);
        resourceCopierParameters.setSourceDirectory(sourceDir);
        resourceCopierParameters.setInvoker(getInvoker());
        return resourceCopierParameters;
    }

    /**
     * Are there any files to publish.
     *
     * @return true, if successful
     */
    private boolean areThereAnyFilesToPublish() {
        return (this.filesToPublish != null) && (!this.filesToPublish.isEmpty());
    }

    /**
     * Gets the request directory descriptor.
     *
     * @param uuid the uuid
     * @return the request directory descriptor
     */
    private File getRequestDirectory(UUID uuid) {
        return new File(sharedLocationManager.getSharedLocationRoot(), uuid.toString());
    }

    /**
     * Prepare special files to copy.
     *
     * @param requestID the request id
     * @return the list
     */
    private List<Pair<String, String>> prepareSpecialFilesToCopy(
            String requestID) {
        List<Pair<String, String>> specialFilesToCopy = new ArrayList<Pair<String, String>>();
        for (Pair<File, File> pair : rootedFiles) {
            File file = pair.getFirst();
            File root = pair.getSecond();

            String fullPath = file.getAbsolutePath();
            String rootPath = root.getAbsolutePath();

            if (fullPath.indexOf(rootPath) != 0) {
                logger.warn("Publisher warning: file "
                        + fullPath
                        + " does not contain specified root "
                        + rootPath
                        + " SO THIS FILE WILL NOT BE COPIED.");
            } else {
                String relativePath = fullPath.substring(rootPath.length());
                if (relativePath.startsWith("/")) {
                    relativePath = relativePath.substring(1);
                }
                File destFile = getRequestDestinationForSpecialFile(relativePath, requestID);
                Pair<String, String> fromTo = Pair.of(file.getAbsolutePath(), destFile.getAbsolutePath());
                specialFilesToCopy.add(fromTo);
            }
        }
        return specialFilesToCopy;
    }

    /**
     * Gets the request output directory.
     *
     * @param requestDir the request dir
     * @return the request output directory
     */
    private File getRequestOutputDirectory(File requestDir) {
        return sharedLocationManager.getRequestOutputDirectory(requestDir.getName());
    }

    /**
     * Gets the request input directory.
     *
     * @param requestDir the request dir
     * @return the request input directory
     */
    private File getRequestInputDirectory(File requestDir) {
        return sharedLocationManager.getRequestInputDirectory(requestDir.getName());
    }

    /**
     * Invoke the template which will create all the directories as the user and copy all the files in one go.
     *
     * @param uuid - effectively the job id of the job that doesn't exist at this point.
     * @param directoriesToCopy A pair of directory paths, one of which must be copied to the other
     * @param directoriesToCreate The list of full paths of directories to create.
     * @param filesToCopy Pairs of file paths to copy [to] and [from] which need special care
     * @param submitHostPreamble The script preamble to be placed at the top of the script
     */
    private void runScript(UUID uuid,
            Pair<String, String> directoriesToCopy,
            List<String> directoriesToCreate,
            List<Pair<String, String>> filesToCopy,
            String submitHostPreamble) throws MIFException {

        Preconditions.checkNotNull(commandBuilder, "The command builder cannot be null");

        String entireCommand;
        try {
            commandBuilder.populateCommandBuilderContext(requestAttributes);
            commandBuilder.setSubmitHostPreamble(submitHostPreamble);
            commandBuilder.setVariable("navigatorJobId", uuid.toString());
            commandBuilder.setVariable("directoriesToCreate", directoriesToCreate);
            commandBuilder.setVariable("directoriesToCopy", directoriesToCopy);
            commandBuilder.setVariable("filesToCopy", filesToCopy);

            entireCommand = commandBuilder.getCommand();
        } catch (ExecutionException ee) {
            throw new MIFException(ee);
        }
        
        InvokerHelper helper = new InvokerHelper(getInvoker());
        InvokerResult results = null;
        
        String requestId = (uuid !=null ) ? uuid.toString() : null;
        
        try {
        	logExecutionMetrics(requestId, "started");
        	
            results = helper.runAndReportFailures(entireCommand);

            logger.debug("Executed command: " + entireCommand);
            logger.debug("Stdout: " + results.getStdout());
            logger.debug("Stderr: " + results.getStderr());
            logger.debug("Exit: " + results.getExitStatus());
        } catch (Exception e) {
            throw new MIFException(e);
        } finally {
        	logExecutionMetrics(requestId, "ended");
        }
    }
    
    /**
     * Logs the execution metrics for the publishing process
     */
    private void logExecutionMetrics(String requestId, String status) {
    	logger.info(String.format("*** Execution Metrics : {Resource.Publishing} Publishing of resources for request id [%s] has %s", 
	        		requestId, status));
    }

    private File getRequestDestinationForSpecialFile(String file, String requestID) {
        return new File(sharedLocationManager.getRequestInputDirectory(requestID), file);
    }

    public Invoker getInvoker() {
        return invoker;
    }

    public void setInvoker(Invoker invoker) {
        this.invoker = invoker;
    }

    @Override
    public void setSubmitHostPreamble(String submitHostPreamble) {
        this.submitHostPreamble = submitHostPreamble;
    }

    public ResourceCopier getResourceCopier() {
        return resourceCopier;
    }

    @Override
    public void setResourceCopier(ResourceCopier resourceCopier) {
        this.resourceCopier = resourceCopier;
    }

}
