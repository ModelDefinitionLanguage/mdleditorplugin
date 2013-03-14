/* File			:  InvokerResult.java
 * Project		:  MIFServer
 * Created on	:  29 Jun 2012
 */
package com.mango.mif.core.exec;

import org.apache.commons.lang.StringUtils;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */

/**
 * @author mrogalski
 * 
 * A result of a shell command invocation.  This was an interface, but the implementations were identical for JSCH and for CE
 * so I dumped all the code here.
 */
public class InvokerResult {

    private final String command;
    private final String stdout;
    private final String stderr;
    private final int exitCode;

    public InvokerResult(String command, String stdout, String stderr, int exitCode) {
        this.command = command;
        this.stdout = stdout;
        this.stderr = stderr;
        this.exitCode = exitCode;
    }

    /**
     * Return the first line only of the specified string.
     * @param input The specified input
     * @return first line of the input, or the empty string if there is no input.
     */
    private String getFirstLineOnly(String input) {
        if (StringUtils.isBlank(input)) {
            return "";
        }
        int iPos = input.indexOf("\n");
        if (iPos < 0) {
            return "";
        }
        return input.substring(0, iPos);
    }

    public String getCommand() {
        return command;
    }

    public int getExitCode() {
        return exitCode;
    }

    public String getErrorStream() {
        return stderr;
    }

    public String getOutputStream() {
        return stdout;
    }

    public String getStdout() {
        return stdout;
    }

    public String getStderr() {
        return stderr;
    }

    public int getExitStatus() {
        return exitCode;
    }

    /**
     * Useful for commands where we're only expecting one line of output
     * @return the first line of the output, or null if there is no output.
     */
    public String getFirstLineOutputStream() {
        return getFirstLineOnly(stdout);
    }
}
