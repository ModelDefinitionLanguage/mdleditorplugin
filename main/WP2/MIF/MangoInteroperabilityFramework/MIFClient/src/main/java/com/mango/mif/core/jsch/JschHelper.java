/**
 * Copyright Mango Solutions, 2012.
 * <p>CVS Entry : $Header$
 * <p>Last edited by : $Author$
 * @author Tony Bamford &lt;tbamford@mango-solutions.com&gt;
 * @version $Revision$ as of on $Date$
 */
package com.mango.mif.core.jsch;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.google.common.base.Preconditions;
import com.mango.mif.exception.MIFException;


/**
 * This class allows you to setup a username, password and remote host, then run a series
 * of commands via JSCH and gather their output.
 */
public class JschHelper {

    private static Logger logger = Logger.getLogger(JschHelper.class);

    public static final int EXIT_STATUS_SUCCESS = 0;

    private JschParameterBuilder builder;

    public JschHelper(JschParameterBuilder builder) {
        this.builder = builder;
    }

    /**
     * Test the results of a command execution for success.
     * @param results The results from an executed command
     * @return true if the command was successful, false otherwise.
     */
    public static boolean success(JschResults results) {
        return results != null && results.getExitStatus() == EXIT_STATUS_SUCCESS;
    }

    /**
     * Test the resuls of a command execution for failure.
     * @param results The results from an executed command
     * @return true if the command was successful, false otherwise.
     */
    public static boolean failed(JschResults results) {
        return results == null || results.getExitStatus() != EXIT_STATUS_SUCCESS;
    }

    /**
     * Run a command line directly and return its results.
     * @return The results (exit status, standard output and standard error) all packaged up.
     * @throws MIFException If we cannot run the process for any reason.
     */
    public JschResults run(String commandLine) throws MIFException {
    	JschSession process = null;
    	JschResults results = null;
        try {
            process = builder.buildSession();
            results = process.run(commandLine); 
        } catch (MIFException mifex) {
            throw mifex;
        } catch (Exception e) {
            throw new MIFException("Caught exception executing command line " + commandLine + " " + builder.toString(), e);
        } finally {
        	if (process != null) {
				process.close();
			}
        }
        return results;
    }

    /**
     * Run a templated command and return its results.
     * @param templatedCommand The templated command.
     * @return The results (exit status, standard output and standard error) all packaged up.
     * @throws MIFException If we cannot run the process for any reason.
     */
    public JschResults run(JschTemplatedCommand templatedCommand) throws MIFException {

        return run(templatedCommand.resolveCommand());
    }

    /**
     * Run a command line and report if it fails.
     * @param commandLine The command line to run.
     * @return The results.
     * @throws MIFException If anything goes wrong.
     */
    public JschResults runAndReportFailures(String commandLine) throws MIFException {

        JschResults results = null;

        try {
            results = run(commandLine);
        } catch (MIFException mifex) {
            throw mifex;
        } catch (Exception e) {
            throw new MIFException("Caught exception while executing command " + commandLine, e);
        }

        if (failed(results)) {
            logger.error("Execution of command failed: " + commandLine);
            logger.error("Exit status of command: " + (results == null ? "not available (no results)" : results.getExitStatus()));
            logger.error("Output of command: " + (results == null ? "not available (no results)" : results.getStdout()));
            logger.error("Errors of command: " + (results == null ? "not available (no results)" : results.getStderr()));

            throw new MIFException("execution of command failed: "
                    + commandLine
                    + ": "
                    + (results == null ?
                            "no results available"
                            : "exit status "
                            + results.getExitStatus()
                            + " errors: "
                            + results.getStderr()));
        }

        return results;
    }


    /**
     * Run the specified command, via its template, to completion.
     *
     * @param templatedCommand the template to execute
     * @throws MIFException if execution fails for any reason
     */
    public JschResults runAndReportFailures(JschTemplatedCommand templatedCommand) throws MIFException {

        try {
            String commandLine = templatedCommand.resolveCommand();
            return runAndReportFailures(commandLine);
        } catch (MIFException mifex) {
            throw mifex;
        } catch (Exception e) {
            throw new MIFException("Caught exception while executing template " + templatedCommand.toString(), e);
        }
    }

    /**
     * Run the specified command template to completion.  If the exit status is non zero, we don't complain.
     * If the command fails to execute we complain.
     *
     * @param command the template to execute
     * @return The results
     */
    public JschResults runIgnoringExitStatus(JschTemplatedCommand command) throws MIFException {

        try {
            String commandLine = command.resolveCommand();
            return runIgnoringExitStatus(commandLine);
        } catch (MIFException mifex) {
            throw mifex;
        } catch (Exception e) {
            throw new MIFException("Caught process exception while executing command " + command.toString(), e);
        }
    }

    /**
     * Run the specified command line to completion.  If the exit status is non zero, we don't complain.
     * If the command fails to execute we complain.
     *
     * @param commandLine the command line to execute
     * @return The results
     */
    public JschResults runIgnoringExitStatus(String commandLine) throws MIFException {

        JschResults results = null;

        try {
            results = run(commandLine);
        } catch (MIFException mifex) {
            throw mifex;
        } catch (Exception e) {
            throw new MIFException("Caught process exception while executing command " + commandLine.toString(), e);
        }

        if (results == null) {
            logger.error("Execution of command failed (with no results): " + commandLine.toString());
        }
        return results;
    }

    /**
     * Run a command line to completion and return a boolean indicating whether it succeeded (standard
     * Unix approach of exit status zero means success, anything else means failure).  If it really,
     * really fails, throw an exception.
     * 
     * @return true if the command exits with status 0, false otherwise
     * @param command the command line to execute
     * @throws JschProcessException if the command fails to execute
     */
    public boolean runAndTestResult(String commandLine) throws MIFException {

        JschResults results = null;

        try {
            results = run(commandLine);
        } catch (MIFException mifex) {
            throw mifex;
        } catch (Exception e) {
            throw new MIFException("Caught exception while resolving command " + commandLine, e);
        }

        if (results == null) {
            logger.error("Execution of command failed (with no results): " + commandLine);

            throw new MIFException("execution of command failed (no results available): "
                    + commandLine);
        }

        return success(results);
    }

    /**
     * Run a command template to completion and return a boolean indicating whether it succeeded (standard
     * Unix approach of exit status zero means success, anything else means failure).  If it really,
     * really fails, throw an exception.
     * 
     * @return true if the command exits with status 0, false otherwise
     * @param command the command template to execute
     * @throws JschProcessException if the command fails to execute
     */
    public boolean runAndTestResult(JschTemplatedCommand command) throws MIFException {

        try {
            String commandLine = command.resolveCommand();
            return runAndTestResult(commandLine);
        } catch (MIFException mifex) {
            throw mifex;
        } catch (Exception e) {
            throw new MIFException("Caught exception while resolving command " + command.toString(), e);
        }
    }

    /**
     * Get the contents of a file as the user.
     * @param fullPath The full path of the file.
     * @return The entire contents of the file as a string.  Caveat programmer, if its a large file, you may run out of memory.
     * @throws MIFException if the command cannot be executed for some reason
     */
    public String getFileContents(String fullPath) throws MIFException {
        JschResults results = run("/bin/cat \"" + fullPath + "\"");

        if (failed(results)) {
            return null;
        }
        return results.getStdout();
    }

    /**
     * Get the contents of a file as the user.
     * @param file The file whose contents you want.
     * @return The entire contents of the file as a string.  Caveat programmer, if its a large file, you may run out of memory.
     * @throws MIFException if the command cannot be executed for some reason
     */
    public String getFileContents(File file) throws MIFException {
        return getFileContents(file.getAbsolutePath());
    }

    /**
     * Copy a file as the user.
     * @param from What to copy
     * @param to Where to copy it to
     * @return true if the copy succeeded, false otherwise
     * @throws MIFException if the command cannot be executed.
     */
    public boolean copyFile(File from, File to) throws MIFException {
        return copyFile(from.getAbsolutePath(), to.getAbsolutePath());
    }

    /**
     * Copy a file as a user.
     * @param fullPathFrom The absolute path of the file we are copying.
     * @param fullPathTo The absolute path of the file to copy to.
     * @return true if the copy succeeded, false otherwise
     * @throws MIFExeption if the command cannot be executed.
     */
    public boolean copyFile(String fullPathFrom, String fullPathTo) throws MIFException {
        JschResults results = run("/bin/cp \"" + fullPathFrom + "\" \"" + fullPathTo + "\"");

        return success(results);
    }

    /**
     * Copy a number of files as a user. THIS WILL BE FASTER IF YOU HAVE A NUMBER OF FILES TO COPY.
     * @param files Array of file objects to copy.
     * @param targetDirectory The directory we are copying to.
     * @return true if the copy succeeded, false otherwise
     * @throws MIFExeption if the command cannot be executed.
     */
    public boolean copyFiles(File[] files, File targetDirectory) throws MIFException {
        String[] paths = new String[files.length];
        int i = 0;
        for (File file : files) {
            paths[i++] = file.getAbsolutePath();
        }
        return copyFiles(paths, targetDirectory.getAbsolutePath());
    }

    /**
     * Copy a number of files as a user. THIS WILL BE FASTER IF YOU HAVE A NUMBER OF FILES TO COPY.
     * @param paths Array of absolute paths of files to copy.
     * @param targetDirectory The directory we are copying to.
     * @return true if the copy succeeded, false otherwise
     * @throws MIFExeption if the command cannot be executed.
     */
    public boolean copyFiles(String[] paths, String targetDirectory) throws MIFException {
        StringBuilder sb = new StringBuilder();
        sb.append("/bin/cp ");
        for (String path : paths) {
            sb.append("\"");
            sb.append(path);
            sb.append("\" ");
        }
        sb.append("\"");
        sb.append(targetDirectory);
        sb.append("\"");
        JschResults results = run(sb.toString());

        return success(results);
    }

    /**
     * Return true if a file exists.  We can't simply create a File object and call the exists method because
     * possibly only the user (whose credentials are in userName and password) has access to it. Note that this
     * WILL TAKE TIME as a session has to be created.
     * @param absolutePath The absolute path of the file - caveat programmer, do NOT invoke with a relative path!
     * @return true if the file exists, false otherwise
     */
    public boolean fileExists(String absolutePath) throws MIFException {
        StringBuilder sb = new StringBuilder();
        sb.append("[[ -f \"");
        sb.append(absolutePath);
        sb.append("\" ]]");
        JschResults results = run(sb.toString());

        return success(results);
    }

    /**
     * Return true if a directory exists.  We can't simply create a File object and call the exists method because
     * possibly only the user (whose credentials are in userName and password) has access to it. Note that this
     * WILL TAKE TIME as a session has to be created.
     * @param absolutePath The absolute path of the directory - caveat programmer, do NOT invoke with a relative path!
     * @return true if the file exists, false otherwise
     */
    public boolean directoryExists(String absolutePath) throws MIFException {
        StringBuilder sb = new StringBuilder();
        sb.append("[[ -d \"");
        sb.append(absolutePath);
        sb.append("\" ]]");
        JschResults results = run(sb.toString());

        return success(results);
    }

    /**
     * Expand out the files in a directory into an array of strings (absolute paths).
     * @param absolutePath The absolute path of the directory to expand.
     * @return Array of absolute paths (strings), or the empty array if the directory does not exist, or
     * it is empty.
     * @throws MIFException if we fail to execute the command (unlikely)
     */
    public String[] getFilesInDirectory(String absolutePath) throws MIFException {
        StringBuilder sb = new StringBuilder();
        sb.append("find \"");
        sb.append(absolutePath);
        sb.append("\" -type f -maxdepth 1 -print | grep -v \"");
        sb.append(absolutePath);
        sb.append("$\"");
        JschResults results = run(sb.toString());

        // The command did not succeed.  Possibly the directory does not exist.
        if (failed(results)) {
            return new String[0];
        }
        Scanner scanner = new Scanner(results.getStdout()).useDelimiter("\n");
        List<String> result = new ArrayList<String>();
        while (scanner.hasNext()) {
            result.add(scanner.nextLine());
        }
        scanner.close();
        return (String[]) result.toArray(new String[0]);
    }

    /**
     * This function will copy the files specified in filesRelativePath from sourceAbsolutePath to targetAbsolutePath.
     * So, say sourceAbsolutePath is "/tmp/foo" and filesRelativePath contains "Data/1.txt", "Results/2.csv" and
     * targetAbsolutePath is "/etc/out"... this routine will copy /tmp/foo/Data/1.txt to /etc/out/Data1/1.txt and
     * /tmp/foo/Results/2.csv to /etc/out/Results/2.csv.
     * 
     * @param sourceAbsolutePath The absolute path of the "starting" folder.
     * @param filesRelativePath The paths, relative to the source directory of the files to be copied.
     * @param targetAbsolutePath The absolute path of the target folder.
     * @return
     */
    public boolean copySpecificFiles(String sourceAbsolutePath, String[] filesRelativePath, String targetAbsolutePath) throws MIFException {

        Preconditions.checkArgument(sourceAbsolutePath.startsWith("/"), "source path must be absolute");
        Preconditions.checkArgument(targetAbsolutePath.startsWith("/"), "target path must be absolute");
        if (filesRelativePath.length == 0) {
            return false;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("cd ");
        sb.append(sourceAbsolutePath);
        sb.append("; cat <<% | cpio -pdum \"");
        sb.append(targetAbsolutePath);
        sb.append("\"\n");
        for(String relativeFilePath : filesRelativePath) {
            sb.append(relativeFilePath);
            sb.append("\n");
        }
        sb.append("%");

        JschResults results = run(sb.toString());

        return success(results);
    }

    /**
     * This function creates a directory as the user, with the specified mode.
     * @param absolutePath The absolute path of the directory
     * @return true if the command executed successfully
     */
    public boolean mkdir(String absolutePath, int mode) throws MIFException {
        Preconditions.checkArgument(absolutePath.startsWith("/"), "path must be absolute");

        JschResults results = run("mkdir -p \"" + absolutePath + "\"; chmod " + Integer.toOctalString(mode) + " \"" + absolutePath + "\"");

        return success(results);
    }

    /**
     * This function removes a directory and all its contents as the user.
     * @param absolutePath The absolute path of the directory
     * @return true if the command executed successfully
     */
    public boolean rmdir(String absolutePath) throws MIFException {
        Preconditions.checkArgument(absolutePath.startsWith("/"), "path must be absolute");

        JschResults results = run("rm -rf \"" + absolutePath + "\"");

        return success(results);
    }

    /**
     * Create a file as the user from the contents specified. The only caveat is the file contents cannot contain the
     * EOF marker which is defined in the function.  The EOF marker must not appear at the start of its own line and be
     * the only thing on that line, otherwise only part of your file will be copied.  I've made the marker pretty weird,
     * but there is always a chance...
     * Oh and your file may end up with an extra newline on the end, but I assume you won't care about that.
     * 
     * @param contents The desired file contents, newlines, quotes, warts and all.
     * @param absolutePath The absolute path of the file to create.
     * @param mode The final mode of the file.
     * @return true if the file creation succeeded, false if it failed.
     */
    public boolean createFileFromContents(String contents, String absolutePath, int mode) throws MIFException {
        Preconditions.checkArgument(absolutePath.startsWith("/"), "path must be absolute");

        final String EOF = "-%%-EOF-%%-";

        StringBuilder sb = new StringBuilder();
        sb.append("cat <<");
        sb.append(EOF);
        sb.append(" > \"");
        sb.append(absolutePath);
        sb.append("\"\n");
        sb.append(contents);
        sb.append("\n");
        sb.append(EOF);
        sb.append("\n");

        JschResults results = run(sb.toString());

        return success(results);
    }
}
