/**
 * Copyright Mango Solutions, 2012.
 * <p>CVS Entry : $Header$
 * <p>Last edited by : $Author$
 * @author Tony Bamford &lt;tbamford@mango-solutions.com&gt;
 * @version $Revision$ as of on $Date$
 */
package com.mango.mif.core.jsch;

import java.util.Scanner;

/**
 * Simple class to capture the standard output, standard error and exit status of a command.
 */
public class JschResults {

    private final String stdout;
    private final String stderr;
    private final int exitStatus;

    public JschResults(String stdout, String stderr, int exitStatus) {
        this.stdout = stdout;
        this.stderr = stderr;
        this.exitStatus = exitStatus;
    }

    public String getStdout() {
        return stdout;
    }

    /**
     * Useful for commands where we're only expecting one line of output
     * @return the first line of the output, or null if there is no output.
     */
    public String getStdoutFirstLineOnly() {
        return getFirstLineOnly(stdout);
    }

    public String getStderr() {
        return stderr;
    }

    public int getExitStatus() {
        return exitStatus;
    }

    private String getFirstLineOnly(String input) {
        String line = null;
        Scanner scanner = new Scanner(input).useDelimiter("\n");
        if (scanner.hasNext()) {
            line = scanner.nextLine();
        }
        scanner.close();
        return line;
    }
}
