/**
 * Copyright Mango Solutions, 2012.
 * <p>SVN Entry: $HeadURL: $
 * <p>SVN ID: $Id: $
 * <p>Last edited by : $Author$
 * @author Tony Bamford &lt;tbamford@mango-solutions.com&gt;
 * @version $Revision$ as of on $Date$
 */
package com.mango.mif.connector.runner.impl;

import org.apache.commons.lang.StringUtils;

/**
 * An exception thrown by state handlers on a validation exception
 */
public class StateHandlerValidationException extends StateHandlerException {

    private static final long serialVersionUID = 1L;

    private String stdout;
    private String stderr;

    /**
     * @param message
     * @param cause
     */
    public StateHandlerValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public StateHandlerValidationException(String message) {
        super(message);
    }

    /**
     * @param message
     */
    public StateHandlerValidationException(String message, String stdout, String stderr) {
        super(message);
        this.stdout = stdout;
        this.stderr = stderr;
    }

    public boolean hasOutput() {
        return StringUtils.isNotBlank(stdout);
    }

    public String getStdout() {
        return stdout;
    }

    public void setStdout(String stdout) {
        this.stdout = stdout;
    }

    public boolean hasErrors() {
        return StringUtils.isNotBlank(stderr);
    }

    public String getStderr() {
        return stderr;
    }

    public void setStderr(String stderr) {
        this.stderr = stderr;
    }
}
