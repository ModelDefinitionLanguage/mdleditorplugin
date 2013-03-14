/**
 * Copyright Mango Solutions, 2012.
 * <p>CVS Entry : $Header$
 * <p>Last edited by : $Author$
 * @author Tony Bamford &lt;tbamford@mango-solutions.com&gt;
 * @version $Revision$ as of on $Date$
 */
package com.mango.mif.core.exec.invoker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.google.common.base.Preconditions;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.Invoker;
import com.mango.mif.core.exec.InvokerResult;
import com.mango.mif.exception.MIFException;

/**
 * Run commands using an invoker.
 */
public class InvokerHelper {

    private static Logger logger = Logger.getLogger(InvokerHelper.class);

    public static final int EXIT_STATUS_SUCCESS = 0;

    private Invoker invoker;

    public InvokerHelper(Invoker invoker) {
        this.invoker = invoker;
    }

    /**
     * Test the results of a command execution for success.
     * @param results The results from an executed command
     * @return true if the command was successful, false otherwise.
     */
    public static boolean success(InvokerResult results) {
        return results != null && results.getExitCode() == EXIT_STATUS_SUCCESS;
    }

    /**
     * Test the resuls of a command execution for failure.
     * @param results The results from an executed command
     * @return true if the command was successful, false otherwise.
     */
    public static boolean failed(InvokerResult results) {
        return results == null || results.getExitCode() != EXIT_STATUS_SUCCESS;
    }

    /**
     * Extract the exit status from a run's results, accounting for nulls.
     * @param results The results from an executed command
     * @return The errors if there are any, some default text if there were none.
     */
    public static String exitStatus(InvokerResult results) {
        return results == null ? "No exit status available due to failed execution" : ("" + results.getExitCode());
    }

    /**
     * Extract the output from a run's results, accounting for nulls.
     * @param results The results from an executed command
     * @return The errors if there are any, some default text if there were none.
     */
    public static String output(InvokerResult results) {
        return results == null ? "No output available due to failed execution" : results.getStdout();
    }

    /**
     * Extract the errors from a run's results, accounting for nulls.
     * @param results The results from an executed command
     * @return The errors if there are any, some default text if there were none.
     */
    public static String errors(InvokerResult results) {
        return results == null ? "No errors available due to failed execution" : results.getStderr();
    }

    /**
     * Run a command line directly and return its results.
     * @return The results (exit status, standard output and standard error) all packaged up.
     * @throws ExecutionException If we cannot run the process for any reason.
     */
    public InvokerResult run(String commandLine) throws ExecutionException {
        return runWithInput(commandLine, null);
    }

    /**
     * Run a command line directly and return its results, shoving the contents of "input" up the
     * poor command's standard input.
     * @param commandLine the command line to execute
     * @param input text to shove up the command's standard input, or null if there is none
     * @return The results (exit status, standard output and standard error) all packaged up.
     * @throws ExecutionException If we cannot run the process for any reason.
     */
    public InvokerResult runWithInput(String commandLine, String input) throws ExecutionException {

        InvokerResult results = null;
        try {
            results = invoker.execute(commandLine, input);
        } catch (ExecutionException ee) {
            throw ee;
        } catch (Exception e) {
            throw new ExecutionException("Caught "
                    + e.getClass().getName()
                    + ", message "
                    + e.getMessage()
                    + " executing: "
                    + commandLine
                    + " invoker type "
                    + invoker.getClass().getCanonicalName(),
                    e);
        }
        return results;
    }

    /**
     * Run a command line and report if it fails.
     * @param commandLine The command line to run.
     * @return The results.
     * @throws MIFException If anything goes wrong.
     */
    public InvokerResult runAndReportFailures(String commandLine) throws ExecutionException {
        return runAndReportFailures(commandLine, null);
    }

    /**
     * Run a command line and report if it fails.
     * @param commandLine The command line to run.
     * @param input Shove this up the command's input when it is run.
     * @return The results.
     * @throws MIFException If anything goes wrong.
     */
    public InvokerResult runAndReportFailures(String commandLine, String input) throws ExecutionException {

        InvokerResult results = null;

        try {
            results = runWithInput(commandLine, input);
        } catch (ExecutionException ee) {
            throw ee;
        } catch (Exception e) {
            throw new ExecutionException("Caught exception while executing command " + commandLine, e);
        }

        if (failed(results)) {
            logger.error("Execution of command failed: " + commandLine);
            logger.error("Exit status of command: " + (results == null ? "not available (no results)" : results.getExitCode()));
            logger.error("Output of command: " + (results == null ? "not available (no results)" : results.getOutputStream()));
            logger.error("Errors of command: " + (results == null ? "not available (no results)" : results.getErrorStream()));

            throw new ExecutionException("execution of command failed: "
                    + commandLine
                    + ": "
                    + (results == null ?
                            "no results available"
                            : "exit status "
                                    + results.getExitCode()
                                    + " errors: "
                                    + results.getErrorStream()));
        } else {
            logger.debug("Execution of command succeeded: " + commandLine);
            logger.debug("Exit status of command: " + (results == null ? "not available (no results)" : results.getExitCode()));
            logger.debug("Output of command: " + (results == null ? "not available (no results)" : results.getOutputStream()));
            logger.debug("Errors of command: " + (results == null ? "not available (no results)" : results.getErrorStream()));
        }
        return results;
    }

    /**
     * Run the specified command line to completion.  If the exit status is non zero, we don't complain.
     * If the command fails to execute we complain.
     *
     * @param commandLine the command line to execute
     * @return The results
     */
    public InvokerResult runIgnoringExitStatus(String commandLine) throws ExecutionException {

        InvokerResult results = null;

        try {
            results = run(commandLine);
        } catch (ExecutionException ee) {
            throw ee;
        } catch (Exception e) {
            throw new ExecutionException("Caught process exception while executing command " + commandLine.toString(), e);
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
    public boolean runAndTestResult(String commandLine) throws ExecutionException {

        InvokerResult results = null;

        try {
            results = run(commandLine);
        } catch (ExecutionException ee) {
            throw ee;
        } catch (Exception e) {
            throw new ExecutionException("Caught exception while resolving command " + commandLine, e);
        }

        if (results == null) {
            logger.error("Execution of command failed (with no results): " + commandLine);

            throw new ExecutionException("execution of command failed (no results available): " + commandLine);
        }

        return success(results);
    }

    /**
     * Get the contents of a file as the user.
     * @param fullPath The full path of the file.
     * @return The entire contents of the file as a string.  Caveat programmer, if its a large file, you may run out of memory.
     * @throws ExecutionException if the command cannot be executed for some reason
     */
    public String getFileContents(String fullPath) throws ExecutionException {
        InvokerResult results = runAndReportFailures("/bin/cat \"" + fullPath + "\"");

        if (failed(results)) {
            return null;
        }
        return results.getOutputStream();
    }

    /**
     * Get the contents of a file as the user.
     * @param file The file whose contents you want.
     * @return The entire contents of the file as a string.  Caveat programmer, if its a large file, you may run out of memory.
     * @throws ExecutionException if the command cannot be executed for some reason
     */
    public String getFileContents(File file) throws ExecutionException {
        return getFileContents(file.getAbsolutePath());
    }

    /**
     * Content of a file a array of strings
     * @return
     * @throws ExecutionException 
     */
    public String[] readLines(File file) throws ExecutionException {
        String content = getFileContents(file);
        logger.debug(content);
        if (content == null) {
            return new String[0];
        }
        return content.split("\n");
    }

    /**
     * Copy a file as the user.
     * @param from What to copy
     * @param to Where to copy it to
     * @return true if the copy succeeded, false otherwise
     * @throws MIFException if the command cannot be executed.
     */
    public boolean copyFile(File from, File to) throws ExecutionException {
        return copyFile(from.getAbsolutePath(), to.getAbsolutePath());
    }

    /**
     * Copy a file as a user.
     * @param fullPathFrom The absolute path of the file we are copying.
     * @param fullPathTo The absolute path of the file to copy to.
     * @return true if the copy succeeded, false otherwise
     * @throws ExecutionException if the command cannot be executed.
     */
    public boolean copyFile(String fullPathFrom, String fullPathTo) throws ExecutionException {
        InvokerResult results = runAndReportFailures("/bin/cp \"" + fullPathFrom + "\" \"" + fullPathTo + "\"");

        return success(results);
    }

    /**
     * Copy a number of files as a user. THIS WILL BE FASTER IF YOU HAVE A NUMBER OF FILES TO COPY.
     * @param files Array of file objects to copy.
     * @param targetDirectory The directory we are copying to.
     * @return true if the copy succeeded, false otherwise
     * @throws ExecutionException if the command cannot be executed.
     */
    public boolean copyFiles(File[] files, File targetDirectory) throws ExecutionException {
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
     * @throws ExecutionException if the command cannot be executed.
     */
    public boolean copyFiles(String[] paths, String targetDirectory) throws ExecutionException {
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
        InvokerResult results = runAndReportFailures(sb.toString());

        return success(results);
    }

    /**
     * Return true if a file exists.  We can't simply create a File object and call the exists method because
     * possibly only the user (whose credentials are in userName and password) has access to it. Note that this
     * WILL TAKE TIME as a session has to be created.
     * @param absolutePath The absolute path of the file - caveat programmer, do NOT invoke with a relative path!
     * @return true if the file exists, false otherwise
     */
    public boolean fileExists(String absolutePath) throws ExecutionException {
        StringBuilder sb = new StringBuilder();
        sb.append("[[ -f \"");
        sb.append(absolutePath);
        sb.append("\" ]]");
        InvokerResult results = run(sb.toString());

        return success(results);
    }

    /**
     * Return true if a directory exists.  We can't simply create a File object and call the exists method because
     * possibly only the user (whose credentials are in userName and password) has access to it. Note that this
     * WILL TAKE TIME as a session has to be created.
     * @param absolutePath The absolute path of the directory - caveat programmer, do NOT invoke with a relative path!
     * @return true if the file exists, false otherwise
     */
    public boolean directoryExists(String absolutePath) throws ExecutionException {
        StringBuilder sb = new StringBuilder();
        sb.append("[[ -d \"");
        sb.append(absolutePath);
        sb.append("\" ]]");
        InvokerResult results = run(sb.toString());

        return success(results);
    }

    /**
     * Expand out the files in a directory into an array of strings (absolute paths).
     * @param absolutePath The absolute path of the directory to expand.
     * @return Array of absolute paths (strings), or the empty array if the directory does not exist, or
     * it is empty.
     * @throws ExecutionException if we fail to execute the command (unlikely)
     */
    public String[] getFilesInDirectory(String absolutePath) throws ExecutionException {
        StringBuilder sb = new StringBuilder();
        sb.append("find \"");
        sb.append(absolutePath);
        sb.append("\" -type f -maxdepth 1 -noleaf -print | grep -v \"");
        sb.append(absolutePath);
        sb.append("$\"");
        InvokerResult results = run(sb.toString());

        // The command did not succeed.  Possibly the directory does not exist.
        if (failed(results)) {
            return new String[0];
        }
        Scanner scanner = new Scanner(results.getOutputStream()).useDelimiter("\n");
        List<String> result = new ArrayList<String>();
        while (scanner.hasNext()) {
            result.add(scanner.nextLine());
        }
        scanner.close();
        return (String[]) result.toArray(new String[0]);
    }
    
    /**
     * This method takes two arguments - an absolute directory path and a name pattern
     * to search/find in the directory all the files matching the name pattern and returns
     * a file object of a single file that is the latest modified among all those files
     * 
     * @param directory The file object of the directory to find the files in
     * @param filename The name pattern of the target file. Could be something like *.extension or my_file etc.
     * @return The file object of the file that was last modified or null if no such file exists
     * @throws ExecutionException if the command fails to run
     */
    public File getLastModifiedFile(File directory, String filename) throws ExecutionException {
    	return getLastModifiedFile(directory.getAbsolutePath(), filename);
    }
    
    /**
     * This method takes two arguments - an absolute directory path and a name pattern
     * to search/find in the directory all the files matching the name pattern and returns
     * a file object of a single file that is the latest modified among all those files
     * 
     * @param directoryPath The absolute path of the directory to find the files in
     * @param filename The name pattern of the target file. Could be something like *.extension or my_file etc.
     * @return The file object of the file that was last modified or null if no such file exists
     * @throws ExecutionException if the command fails to run
     */
    public File getLastModifiedFile(String directoryPath, String filename) throws ExecutionException {
    	String filePath = getPathOfLastModifiedFile(directoryPath, filename);
    	if(StringUtils.isEmpty(filePath)) {
    		return null;
    	}
    	return new File(filePath);
	}
    
    /**
     * This method takes two arguments - a directory file object and a name pattern
     * to search/find in the directory all the files matching the name pattern and returns
     * absolute path of a single file that is the latest modified among all those files
     * 
     * @param directory The file object of the directory to find the files in
     * @param filename The name pattern of the target file. Could be something like *.extension or my_file etc.
     * @return The absolute path of the file that was last modified
     * @throws ExecutionException if the command fails to run
     */
    public String getPathOfLastModifiedFile(File directory, String filename) throws ExecutionException {
    	return getPathOfLastModifiedFile(directory.getAbsolutePath(), filename);
    }
    
    /**
     * This method takes two arguments - an absolute directory path and a name pattern
     * to search/find in the directory all the files matching the name pattern and returns
     * absolute path of a single file that is the latest modified among all those files
     * 
     * @param directoryPath The absolute path of the directory to find the files in
     * @param filename The name pattern of the target file. Could be something like *.extension or my_file etc.
     * @return The absolute path of the file that was last modified
     * @throws ExecutionException if the command fails to run
     */
    public String getPathOfLastModifiedFile(String directoryPath, String filename) throws ExecutionException {
		StringBuilder sb = new StringBuilder();
		//FIXME : Mantis - 11935
		//Externalize these embedded shell calls
		sb.append("find ").append(directoryPath).append(" ");
		sb.append("\\( -name \"").append(filename).append("\" ");
		sb.append("\\) -exec stat -c \"%Y %n\" {} \\; | sort -r | head -1 | cut -d\" \" -f2");
		String commandToExecute = sb.toString();
		InvokerResult result = run(commandToExecute);
		if(failed(result)) {
			logger.debug(String.format("No path or failed in retrieving the path of the last modified file " +
					"with the name [%s] under the directory [%s]", filename, directoryPath));
			return "";
		}
		String filePath = getTrimmedFilePathFromResult(result);
		logger.debug(String.format("The path of the last modified file with the name [%s] under the directory [%s] is [%s]", 
				filename, directoryPath, filePath));
		return filePath;
	}

	/**
	 * Get the trimmed first line of the result to avoid wrong paths caused due to new lines and spaces
	 */
	private String getTrimmedFilePathFromResult(InvokerResult result) {
		String out = result.getOutputStream();
		if(StringUtils.isBlank(out)) {
			return "";
		}
		Scanner resultScanner = new Scanner(out);
		String filePath = "";
		if(resultScanner.hasNextLine()) {
			filePath = resultScanner.nextLine().trim();
		}else {
			filePath = out.trim();
		}
		return filePath;
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
     * @return true if the command succeeded
     */
    public boolean copySpecificFiles(String sourceAbsolutePath, String[] filesRelativePath, String targetAbsolutePath) throws ExecutionException {

        Preconditions.checkArgument(sourceAbsolutePath.startsWith("/"), "source path must be absolute");
        Preconditions.checkArgument(targetAbsolutePath.startsWith("/"), "target path must be absolute");
        if (filesRelativePath.length == 0) {
            return false;
        }

        StringBuilder commandLine = new StringBuilder();
        commandLine.append("cd \"");
        commandLine.append(sourceAbsolutePath);
        commandLine.append("\"; cpio -pdu --quiet \"");
        commandLine.append(targetAbsolutePath);
        commandLine.append("\"\n");

        StringBuilder input = new StringBuilder();
        for (String relativeFilePath : filesRelativePath) {
            input.append(relativeFilePath);
            input.append("\n");
        }

        InvokerResult results = runAndReportFailures(commandLine.toString(), input.toString());

        return success(results);
    }

    /**
     * This function creates a directory as the user, with the specified mode.
     * @param absolutePath The absolute path of the directory
     * @return true if the command executed successfully
     */
    public boolean mkdir(String absolutePath, int mode) throws ExecutionException {
        Preconditions.checkArgument(absolutePath.startsWith("/"), "path must be absolute");

        InvokerResult results = runAndReportFailures("mkdir -p \"" + absolutePath + "\"; chmod " + Integer.toOctalString(mode) + " \"" + absolutePath + "\"");

        return success(results);
    }

    /**
     * This function removes a directory and all its contents as the user.
     * @param absolutePath The absolute path of the directory
     * @return true if the command executed successfully
     */
    public boolean rmdir(String absolutePath) throws ExecutionException {
        Preconditions.checkArgument(absolutePath.startsWith("/"), "path must be absolute");

        InvokerResult results = runAndReportFailures("rm -rf \"" + absolutePath + "\"");

        return success(results);
    }

    /**
     * Create a file as the user from the contents specified.  This should probably be done via a mango shell utility
     * which could take the filename and the mode and create them in what appears to be a single atomic operation.
     * 
     * @param contents The desired file contents, newlines, quotes, warts and all.
     * @param absolutePath The absolute path of the file to create.
     * @param mode The final mode of the file.
     * @return true if the file creation succeeded, false if it failed.
     */
    public boolean createFileFromContents(String contents, String absolutePath, int mode) throws ExecutionException {
        Preconditions.checkArgument(absolutePath.startsWith("/"), "file path to create must be absolute");

        StringBuilder sb = new StringBuilder();
        sb.append("cat > \"");
        sb.append(absolutePath);
        sb.append("\"; chmod ");
        sb.append(Integer.toOctalString(mode));
        sb.append(" \"");
        sb.append(absolutePath);
        sb.append("\"");
        InvokerResult results = runAndReportFailures(sb.toString(), contents);

        return success(results);
    }

    /**
     * Create the specified empty file.  Useful for creating a file whose only purpose is to act as a timestamp.
     * @param file The file to create.
     * @return True if we succeeded, false otherwise
     * @throws ExecutionException If we can't execute the command, for any reason.
     */
    public boolean createEmptyFile(File file) throws ExecutionException {
        return createEmptyFile(file.getAbsolutePath());
    }

    /**
     * Create the specified empty file.  Useful for creating a file whose only purpose is to act as a timestamp.
     * @param absolutePath The absolute path of the file to create.
     * @return True if we succeeded, false otherwise
     * @throws ExecutionException If we can't execute the command, for any reason.
     */
    public boolean createEmptyFile(String absolutePath) throws ExecutionException {
        Preconditions.checkArgument(absolutePath.startsWith("/"), "The path of the file to create must be absolute");

        InvokerResult results = runAndReportFailures("echo > \"" + absolutePath + "\"");

        return success(results);
    }
}
