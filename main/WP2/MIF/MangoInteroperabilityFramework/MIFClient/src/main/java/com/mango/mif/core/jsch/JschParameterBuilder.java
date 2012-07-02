/**
 * Copyright Mango Solutions, 2012.
 * <p>SVN Entry: $HeadURL: $
 * <p>SVN ID: $Id: $
 * <p>Last edited by : $Author$
 * @author Tony Bamford &lt;tbamford@mango-solutions.com&gt;
 * @version $Revision$ as of on $Date$
 */
package com.mango.mif.core.jsch;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Charsets;
import com.mango.mif.exception.MIFException;

/**
 * Build the parameters to construct a JschSession, a JschForegroundProcess, a JschBackgroundProcess or a JschHelper.
 */
public class JschParameterBuilder {

    private static final int SECONDS = 1000;
    static final int DEFAULT_TIMEOUT = 10 * SECONDS;
    static final String DEFAULT_HOST = "localhost";
    static final int DEFAULT_PORT = 22;

    // required parameters
    final String userName;
    final String password;

    // optional parameters
    String host = DEFAULT_HOST;
    String charSet = Charsets.UTF_8.name();
    int port = DEFAULT_PORT;
    int timeoutInMilliseconds = DEFAULT_TIMEOUT;

    public JschParameterBuilder(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public JschParameterBuilder host(String value) {
        if (StringUtils.isNotBlank(value)) {
            host = value;
        }
        return this;
    }

    public JschParameterBuilder charSet(String value) {
        if (StringUtils.isNotBlank(charSet)) {
            charSet = value;
        }
        return this;
    }

    public JschParameterBuilder timeoutInMilliseconds(int value) {
        if (value >= 0) {
            timeoutInMilliseconds = value;
        }
        return this;
    }

    public JschParameterBuilder port(int value) {
        if (value >= 0){
            port = value;
        }
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getHost() {
        return host;
    }

    public String getCharSet() {
        return charSet;
    }

    public int getPort() {
        return port;
    }

    public int getTimeoutInMilliseconds() {
        return timeoutInMilliseconds;
    }

    public String toString() {
        return "[username: " + userName + ", host " + host + ", port " + port + "]";
    }

    public JschHelper buildHelper() {
        return new JschHelper(this);
    }

    public JschSession buildSession() throws MIFException {
        return new JschSession(this);
    }

    public JschForegroundProcess buildForegroundProcesss(String commandLine) throws MIFException {
        return new JschForegroundProcess(commandLine, this);
    }

    public JschBackgroundProcess buildBackgroundProcesss(String commandLine) throws MIFException {
        return new JschBackgroundProcess(commandLine, this);
    }
}
