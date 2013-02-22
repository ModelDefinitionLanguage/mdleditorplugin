/**
 * Copyright Mango Solutions, 2012.
 * <p>CVS Entry : $Header$
 * <p>Last edited by : $Author$
 * @version $Revision$ as of on $Date$
 */
package com.mango.mif.core.exec.invoker;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.Invoker;
import com.mango.mif.core.exec.ce.CeInvoker;
import com.mango.mif.core.exec.jsch.JschInvoker;
import com.mango.mif.core.exec.jsch.JschParameters;
import com.mango.mif.utils.encrypt.Encrypter;
import com.mango.mif.utils.encrypt.EncrypterFactory;

/**
 * Make Invokers!
 */
public class InvokerFactory {

    private static final int SECONDS = 1000;
    static final int DEFAULT_TIMEOUT = 10 * SECONDS;
    static final String DEFAULT_HOST = "localhost";
    static final int DEFAULT_PORT = 22;
    static final String DEFAULT_PROTOCOLS = "gssapi-with-mic,publickey,keyboard-interactive,password";

    // These members are compulsory for RAU, but not otherwise
    //
    private String userName;
    private String clearTextPassword;

    // These are always optional because all are defaulted
    //
    private String host = DEFAULT_HOST;
    private String charSet = Charsets.UTF_8.name();
    private int port = DEFAULT_PORT;
    private int timeoutInMilliseconds = DEFAULT_TIMEOUT;
    private String protocols = DEFAULT_PROTOCOLS;
	private final Encrypter	desEncrypter	= EncrypterFactory.getEncrypter();

    //=========================================================================

    public InvokerFactory() {
    }

    //=========================================================================

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public InvokerFactory userName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getClearTextPassword() {
        return clearTextPassword;
    }

    public InvokerFactory encryptedPassword(String encryptedPassword) {
        this.setEncryptedPassword(encryptedPassword);
        return this;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.clearTextPassword = deCrypt(encryptedPassword);
    }
    
    private synchronized String deCrypt(String encrypted) {
    	return desEncrypter.decrypt(encrypted);
    }

    public void setClearTextPassword(String clearTextPassword) {
        this.clearTextPassword = clearTextPassword;
    }

    public InvokerFactory clearTextPassword(String clearTextPassword) {
        this.setClearTextPassword(clearTextPassword);
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

    public InvokerFactory host(String host) {
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

    public InvokerFactory charSet(String charSet) {
        this.charSet = charSet;
        return this;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public InvokerFactory port(int port) {
        this.port = port;
        return this;
    }

    public int getTimeoutInMilliseconds() {
        return timeoutInMilliseconds;
    }

    public void setTimeoutInMilliseconds(int timeoutInMilliseconds) {
        this.timeoutInMilliseconds = timeoutInMilliseconds;
    }

    public InvokerFactory timeoutInMilliseconds(int timeoutInMilliseconds) {
        this.timeoutInMilliseconds = timeoutInMilliseconds;
        return this;
    }

    public String getProtocols() {
        return protocols;
    }

    public void setProtocols(String protocols) {
        this.protocols = protocols;
    }

    public InvokerFactory protocols(String protocols) {
        this.protocols = protocols;
        return this;
    }

    //=========================================================================
    //=========================================================================

    @Override
    public String toString() {
        return "[username: " + userName
                + ", host " + host
                + ", port " + port + "]";
    }

    //=========================================================================
    //=========================================================================

    /**
     * Creates a jsch invoker using all the values that have been set plus provided credentials.
     * @return the jsch invoker
     * @throws ExecutionException if we screw up
     */
    public synchronized Invoker createRunAsUserInvoker(String userName, String encryptedPassword) throws ExecutionException {
        Preconditions.checkArgument(StringUtils.isNotBlank(userName), "User name cannot be blank ");
        Preconditions.checkArgument(StringUtils.isNotBlank(encryptedPassword), "Password cannot be blank");

        JschInvoker result = new JschInvoker(buildJschParameters(userName,encryptedPassword));
        return result;
    }

    /**
     * Builds the jsch parameters object
     * @param userName
     * @param encryptedPassword
     * @return
     */
    public JschParameters buildJschParameters(String userName, String encryptedPassword) {
        return new JschParameters(userName, deCrypt(encryptedPassword)).port(getPort())
                .charSet(getCharSet())
                .host(getHost())
                .timeoutInMilliseconds(getTimeoutInMilliseconds())
                .protocols(getProtocols());
    }
    /**
     * Creates the opposite of a JSCH invoker, which uses commons exec to execute commands as whoever we are
     * running as.  No idea what to call this, since we may not actually be running as navplus.
     *
     * @return the commons exec invoker
     * @throws ExecutionException if we screw up
     */
    public Invoker createRunAsNavplusInvoker() throws ExecutionException {
        CeInvoker result = new CeInvoker();
        return result;
    }
}
