/**
 * Copyright Mango Solutions, 2012.
 * <p>SVN Entry: $HeadURL: $
 * <p>SVN ID: $Id: $
 * <p>Last edited by : $Author$
 * @author Tony Bamford &lt;tbamford@mango-solutions.com&gt;
 * @version $Revision$ as of on $Date$
 */
package com.mango.mif.core.exec.jsch;

import com.mango.mif.utils.encrypt.Encrypter;
import com.mango.mif.utils.encrypt.EncrypterFactory;

/**
 * Collect together everything needed, and everything optional, to create a JSCH session.
 * This is here because of the way in which JSCH sessions are pooled by the JschSessionPool
 * and it allows the JschSessionPoolObjectFactory to create new sessions via the parameters
 * which contain all the information we could possibly need.
 */
public class JschParameters {

    private String userName;
    private String clearTextPassword;
    private String host;
    private String charSet;
    private int port;
    private int timeoutInMilliseconds;
    private String protocols;

    public JschParameters(String userName, String clearTextPassword) {
        this.userName = userName;
        this.clearTextPassword = clearTextPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public JschParameters userName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getClearTextPassword() {
        return clearTextPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        Encrypter desEncrypter = EncrypterFactory.getEncrypter();
        this.clearTextPassword = desEncrypter.decrypt(encryptedPassword);
    }

    public JschParameters encryptedPassword(String encryptedPassword) {
        setEncryptedPassword(encryptedPassword);
        return this;
    }

    public void setClearTextPassword(String clearTextPassword) {
        this.clearTextPassword = clearTextPassword;
    }

    public JschParameters clearTextPassword(String clearTextPassword) {
        setClearTextPassword(clearTextPassword);
        return this;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        if (host != null) {
            this.host = host;
        }
    }

    public JschParameters host(String host) {
        if (host != null) {
            this.host = host;
        }
        return this;
    }

    public String getCharSet() {
        return charSet;
    }

    public void setCharSet(String charSet) {
        this.charSet = charSet;
    }

    public JschParameters charSet(String charSet) {
        this.charSet = charSet;
        return this;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public JschParameters port(int port) {
        this.port = port;
        return this;
    }

    public int getTimeoutInMilliseconds() {
        return timeoutInMilliseconds;
    }

    public void setTimeoutInMilliseconds(int timeoutInMilliseconds) {
        this.timeoutInMilliseconds = timeoutInMilliseconds;
    }

    public JschParameters timeoutInMilliseconds(int timeoutInMilliseconds) {
        this.timeoutInMilliseconds = timeoutInMilliseconds;
        return this;
    }

    public String getProtocols() {
        return protocols;
    }

    public void setProtocols(String protocols) {
        this.protocols = protocols;
    }

    public JschParameters protocols(String protocols) {
        this.protocols = protocols;
        return this;
    }

    //=========================================================================

    @Override
    public String toString() {
        return "[" + userName
                //+ ", password: " + clearTextPassword
                + ", " + host
                + ", " + port + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((host == null) ? 0 : host.hashCode());
        result = prime * result + port;
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        JschParameters other = (JschParameters) obj;
        if (host == null) {
            if (other.host != null)
                return false;
        } else if (!host.equals(other.host))
            return false;
        if (port != other.port)
            return false;
        if (userName == null) {
            if (other.userName != null)
                return false;
        } else if (!userName.equals(other.userName))
            return false;
        return true;
    }

    //=========================================================================

    /**
     * merges the invoker parameters with the given one
     * @param invokerParameters
     */
    public JschParameters merge(JschParameters invokerParameters) {
        userName = (invokerParameters.userName != null) ? invokerParameters.userName : userName;
        clearTextPassword = (invokerParameters.clearTextPassword != null) ? invokerParameters.clearTextPassword : clearTextPassword;
        host = (invokerParameters.host != null) ? invokerParameters.host : host;
        charSet = (invokerParameters.charSet != null) ? invokerParameters.charSet : charSet;
        port = (invokerParameters.port > 0) ? invokerParameters.port : port;
        timeoutInMilliseconds = (invokerParameters.timeoutInMilliseconds > 0) ? invokerParameters.timeoutInMilliseconds : timeoutInMilliseconds;
        protocols = (invokerParameters.protocols != null) ? invokerParameters.protocols : protocols;
        return this;
    }
}
