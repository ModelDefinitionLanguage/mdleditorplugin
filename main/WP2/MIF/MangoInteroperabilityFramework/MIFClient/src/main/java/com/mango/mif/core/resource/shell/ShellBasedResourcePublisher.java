/* File			:  TemplatedResourcePublisher.java
 * Project		:  MIFClient
 * Created on	:  12 Jun 2012
 */
package com.mango.mif.core.resource.shell;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.google.common.base.Preconditions;
import com.google.common.io.Files;
import com.mango.mif.core.jsch.JschHelper;
import com.mango.mif.core.jsch.JschParameterBuilder;
import com.mango.mif.core.jsch.JschResults;
import com.mango.mif.core.resource.BaseResourcePublisher;
import com.mango.mif.exception.MIFException;
import com.mango.mif.utils.Pair;
import com.mango.mif.utils.encrypt.DesEncrypter;
import com.mango.mif.utils.encrypt.Encrypter;
import com.mango.mif.utils.encrypt.EncrypterFactory;


/**
 * A template based resource publisher
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

    private static String CREATOR_SCRIPT_BASENAME = "create.sh";

    private static Logger logger = Logger.getLogger(ShellBasedResourcePublisher.class);

    /**
     * Template of the submit.sh command
     */
    private File template;
    /**
     * User name
     */
    private String	userName;
    /**
     * User encrypted password
     */
    private String	password;
    /**
     * Clear text password, only for testing
     */
    private String clearTextPassword;
    /**
     * The port for JSCH to use
     */
    private int port = 22;


    /**
     * 1. retrieve request id from the sharedLocationManager;
     * 2. Populate submit.sh script template with parameters using a command builder (TODO create abstract PublishFilesCommandBuilder and TemplateBasePublishFilesCommandBuilder)
     *      a. root directory to copy empty folder structure from
     *      b. request input directory (sharedLocationManager.getRequestInputDirectory(requestID)
     * 3. execute the script using JSCH and user name and password
     */
    @Override
    public String publish() throws MIFException {
        Preconditions.checkNotNull(rootDirectory,"Root directory can't be null");
        Preconditions.checkNotNull(sharedLocationManager,"Shared location manager can't be null");
        Preconditions.checkNotNull(sharedLocationManager.getSharedLocationRoot(), "Shared location manager must have a shared location");
        List<String> directoriesToCreate = new ArrayList<String>();

        directoriesToCreate.add(sharedLocationManager.getSharedLocationRoot().getAbsolutePath());

        Pair<String, String> directoriesToCopy = Pair.empty();
        directoriesToCopy.setFirst(rootDirectory.getAbsolutePath());
        String requestID = generateRequestDirectoryPaths(directoriesToCopy, directoriesToCreate);

        List<Pair<String, String>> filesToCopy = new ArrayList<Pair<String, String>>();
        for(File file : files) {
            File destFile = getRequestDestinationFile(file, requestID);
            Pair<String, String> fromTo = Pair.of(file.getAbsolutePath(), destFile.getAbsolutePath());
            filesToCopy.add(fromTo);
        }

        // We get pairs of files in "rootedFiles".  For example:
        //   1.   /usr/share/tomcat6/navigator/resources/resource1.x
        //   2.   /usr/share/tomcat6/navigator
        // This means we want to "subtract" the path in 2 from the path in 1 and copy
        //        /usr/share/tomcat6/navigator/resources/resource1.x
        //        /shared/location/root/resources/resource1.x
        //
        List<Pair<String, String>> specialFilesToCopy = new ArrayList<Pair<String, String>>();
        for(Pair<File, File> pair : rootedFiles) {
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
        this.requestID = requestID;

        runScriptAsUser(directoriesToCopy, directoriesToCreate, filesToCopy, specialFilesToCopy);

        return requestID;
    }

    /**
     * Generate and invoke a script which will create all the directories as the user and copy all the files in one go.
     * This probably isn't the best way to do this.
     * TODO rewrite using a command template
     * 
     * @param directoriesToCopy A pair of directory paths, one of which must be copied to the other
     * @param directoriesToCreate The list of full paths of directories to create.
     * @param filesToCopy Pairs of file paths to copy [to] and [from]
     * @param specialFilesToCopy Pairs of file paths to copy [to] and [from] which need special care
     */
    private void runScriptAsUser(Pair<String, String> directoriesToCopy, List<String> directoriesToCreate, List<Pair<String, String>> filesToCopy, List<Pair<String, String>> specialFilesToCopy) throws MIFException {
        File tempDirectory = Files.createTempDir();
        try {
            File tempScript = new File(tempDirectory, CREATOR_SCRIPT_BASENAME);

            try {
                FileUtils.copyURLToFile(this.getClass().getResource(CREATOR_SCRIPT_BASENAME), tempScript);
            } catch (IOException ioe) {
                MIFException mifex = new MIFException("Failed to copy the creator script " + CREATOR_SCRIPT_BASENAME + " to the temporary directory " + tempScript.getAbsolutePath(), ioe);
                logger.error(mifex);
                throw mifex;
            }

            StringBuilder sb = new StringBuilder();
            sb.append("/bin/bash ");
            sb.append(tempScript.getAbsolutePath());

            // First handle the directory creation.  The directories must exist before we
            // can copy
            for(String directoryPath : directoriesToCreate) {
                sb.append(" -d \"");
                sb.append(directoryPath);
                sb.append("\"");
            }

            // Now handle the copying of the empty directory structure
            sb.append(" -r \"");
            sb.append(directoriesToCopy.getFirst());
            sb.append("\" \"");
            sb.append(directoriesToCopy.getSecond());
            sb.append("\"");

            // Handle the copying of specially nominated files between the directories.
            for(Pair<String, String> pair : filesToCopy) {
                sb.append(" -c \"");
                sb.append(pair.getFirst());
                sb.append("\" \"");
                sb.append(pair.getSecond());
                sb.append("\"");
            }

            // Now handle the copying of external files between the directories.
            for(Pair<String, String> pair : specialFilesToCopy) {
                sb.append(" -C \"");
                sb.append(pair.getFirst());
                sb.append("\" \"");
                sb.append(pair.getSecond());
                sb.append("\"");
            }

            logger.debug("About to execute command: " + sb.toString());

            String useablePassword = clearTextPassword;
            if (StringUtils.isBlank(useablePassword)) {
                Encrypter desEncrypter = EncrypterFactory.getEncrypter();
                useablePassword = desEncrypter.decrypt(password);
            }

            JschParameterBuilder builder = new JschParameterBuilder(userName, useablePassword).port(port);
            JschHelper helper = builder.buildHelper();

            JschResults results = null;
            try {
                results = helper.runAndReportFailures(sb.toString());

                logger.debug("Stdout: " + results.getStdout());
                logger.debug("Stderr: " + results.getStderr());
                logger.debug("Exit: " + results.getExitStatus());
            } catch (Exception e) {
                logger.error("Caught exception " + e.getClass().getName() + ": " + e.getMessage());
                logger.error("Command: " + sb.toString());
                logger.error("Stdout: " + results == null ? "null" : results.getStdout());
                logger.error("Stderr: " + results == null ? "null" : results.getStderr());
                logger.error("Exit: " + results == null ? "null" : results.getExitStatus());
                throw new MIFException(e);
            }
        } finally {
            try {
                FileUtils.deleteDirectory(tempDirectory);
            } catch (IOException ioe) {
                logger.info("Caught exception deleting temporary directory "
                        + tempDirectory
                        + " You will have to delete it manually",
                        ioe);
            }
        }
    }

    private File getRequestDestinationFile(File file, String requestID) {
        String path = file.getAbsolutePath().replace(rootDirectory.getAbsolutePath() + File.separator, "");

        // The resulting file needs to go into under the "input" directory, hence the call to getRequestInputDirectory.
        // Its ok, this is probably obvious to you, but its taken me about an hour to figure out that this is actually
        // correct.
        //

        return new File(sharedLocationManager.getRequestInputDirectory(requestID), path);
    }
    private File getRequestDestinationForSpecialFile(String file, String requestID) {
        return new File(sharedLocationManager.getRequestInputDirectory(requestID), file);
    }

    /**
     * Figure a truly unique directory based in a UUID.  Then figure the directories we must
     * create under that directory.  Shove these into the parameter so they can be created later.
     * 
     * @param directoriesToCreate The directories to create later.
     * @return The UUID of the request directory.
     */
    private String generateRequestDirectoryPaths(Pair<String, String> directoriesToCopy, List<String> directoriesToCreate) {
        UUID uuid;
        File dir;

        do {
            uuid = UUID.randomUUID();
            dir = new File(sharedLocationManager.getSharedLocationRoot(),uuid.toString());
        } while (dir.exists());

        directoriesToCreate.add(dir.getAbsolutePath());

        File inputDir = sharedLocationManager.getRequestInputDirectory(dir.getName());
        directoriesToCopy.setSecond(inputDir.getAbsolutePath());

        File outputDir = sharedLocationManager.getRequestOutputDirectory(dir.getName());
        directoriesToCreate.add(inputDir.getAbsolutePath());
        directoriesToCreate.add(outputDir.getAbsolutePath());
        return uuid.toString();
    }

    @Override
	public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
	public void setPassword(String password) {
        this.password = password;
    }

    public void setTemplate(File template) {
        this.template = template;
    }

    // For testing only
    void setClearTextPassword(String clearTextPassword) {
        this.clearTextPassword = clearTextPassword;
    }

    @Override
	public void setPort(int port) {
        this.port = port;
    }
}
