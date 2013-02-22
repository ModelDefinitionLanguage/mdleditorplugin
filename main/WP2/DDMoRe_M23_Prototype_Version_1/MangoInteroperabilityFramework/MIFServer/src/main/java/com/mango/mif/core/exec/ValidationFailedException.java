/**
 * Copyright Mango Solutions, 2012.
 * <p>SVN Entry: $HeadURL: $
 * <p>SVN ID: $Id: $
 * <p>Last edited by : $Author$
 * @author Tony Bamford &lt;tbamford@mango-solutions.com&gt;
 * @version $Revision$ as of on $Date$
 */
package com.mango.mif.core.exec;

/**
 * Indicate that output validation has failed, as opposed to any fault of MIF.
 */
public class ValidationFailedException extends ExecutionException {

    private String stdout;

    private String stderr;

    private static final long serialVersionUID = -1749099800046837589L;

    public ValidationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationFailedException(String message) {
        super(message);
    }

    public ValidationFailedException(String message, String output, String error) {
        super(message);
        this.stdout = output;
        this.stderr = error;
    }

    public String getStdout() {
        return stdout;
    }

    public void setStdout(String stdout) {
        this.stdout = stdout;
    }

    public String getStderr() {
        return stderr;
    }

    public void setStderr(String stderr) {
        this.stderr = stderr;
    }
}
