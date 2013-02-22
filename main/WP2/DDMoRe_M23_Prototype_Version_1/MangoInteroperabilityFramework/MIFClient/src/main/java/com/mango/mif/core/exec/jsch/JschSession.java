/**
 * Copyright Mango Solutions, 2012.
 * <p>CVS Entry : $Header$
 * <p>Last edited by : $Author$
 * @author Tony Bamford &lt;tbamford@mango-solutions.com&gt;
 * @version $Revision$ as of on $Date$
 */
package com.mango.mif.core.exec.jsch;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.vngx.jsch.ChannelExec;
import org.vngx.jsch.JSch;
import org.vngx.jsch.Session;
import org.vngx.jsch.config.SessionConfig;
import org.vngx.jsch.exception.JSchException;

import com.google.common.base.Preconditions;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.InvokerResult;

/**
 * A variation on JschForegroundProcess which breaks apart the session and the channel in the
 * hope of improving performance.
 */
public class JschSession {

    private static final int K = 1024;
    private static final int BUF_SIZE = 4 * K;
    //private static final long SECOND = 1000L;
    //private static final long WORKING_SLEEP_INTERVAL = SECOND;
    private static final long SLEEP_INTERVAL = 200L;

    private final UUID sessionId = UUID.randomUUID();

    private static final Logger logger = Logger.getLogger(JschSession.class);

    private JschParameters parameters;

    private boolean inUse;

    private Session session;

    private String lastCommand;

    /**
     * Can only create using a JschSessionCache.
     * @param parameters the invoker parameters
     * @throws ExecutionException probably because the user doesn't exist on this machine, or the password is incorrect,
     * or the protocol we need isn't one we use, or...
     */
    JschSession(JschParameters parameters) throws ExecutionException {

        Preconditions.checkNotNull(parameters, "The parameters must have been set by this point");
        Preconditions.checkNotNull(parameters.getUserName(), "The user name must have been set by this point");
        Preconditions.checkNotNull(parameters.getClearTextPassword(), "The password must have been set by this point");

        this.parameters = parameters;

        JSch jsch = JSch.getInstance();
        try {
            JSch.setLogger(new JschLogger());
            session = jsch.createSession(parameters.getUserName(), parameters.getHost(), parameters.getPort(), getSessionConfig(parameters));
            session.setUserInfo(getUserInfo(parameters));
            session.connect(parameters.getClearTextPassword().getBytes());

        } catch (Exception e) {
            String message = "Caught exception creating JSCH session, "
                    + parameters.toString();
            logger.error(message, e);
            throw new ExecutionException(message, e);
        }
    }

    private JschUserInfo getUserInfo(JschParameters parameters) {
        return new JschUserInfo(parameters.getClearTextPassword());
    }

    private SessionConfig getSessionConfig(JschParameters parameters) {
        Map<String, String> properties = new HashMap<String, String>();
        properties.put(SessionConfig.STRICT_HOST_KEY_CHECKING, "no");
        properties.put(SessionConfig.PREFFERED_AUTHS, parameters.getProtocols());
        return new SessionConfig(properties);
    }

    /**
     * Run the specified command, using the previously created session.
     * 
     * @param commandLine the specified command
     * @return the packaged results of the command - stdout, stderr and exit status
     * @throws ExecutionException on IO Exception or JSCH problems.
     */
    public InvokerResult run(String commandLine) throws ExecutionException {
        return run(commandLine, null);
    }

    public JschParameters getParameters() {
        return this.parameters;
    }

    /**
     * Run the specified command, with the specified input, using the previously created session.
     * 
     * @param commandLine the specified command
     * @param input text to be shoved up the poor command's stdin
     * @return the packaged results of the command - stdout, stderr and exit status
     * @throws ExecutionException on IO Exception or JSCH problems.
     */
    public InvokerResult run(String commandLine, String input) throws ExecutionException {

        if (session == null || !session.isConnected()) {
            throw new IllegalStateException("Session is null or uninitialised. Have you called close() previously?");
        }

        JschInvokerResult results = null;
        InputStream outStream = null;
        InputStream errStream = null;
        InputStream inStream = null;
        ChannelExec channel = null;
        try {
            inUse = true;

            channel = (ChannelExec) session.openChannel("exec");

            channel.setCommand(commandLine);

            // the output is retrieved by calling "getInputStream"?  Go figure.
            outStream = channel.getInputStream();

            // at least the error stream is retrieved with a sensibly named call
            errStream = channel.getErrStream();

            // Ah... setInputStream sets the command's standard input, as opposed to
            // getInputStream which gets its standard output...
            // So... if we have input, pass it to the command
            //
            if (StringUtils.isNotBlank(input)) {
                inStream = new ByteArrayInputStream(input.getBytes());
                channel.setInputStream(inStream);
            }
            channel.connect();

            StringBuilder sbOut = new StringBuilder();
            StringBuilder sbErr = new StringBuilder();

            byte[] tmp = new byte[BUF_SIZE];
            logger.trace("Reading output streams");
            while (true) {
                logger.trace("Reading... available bytes [outStream =" + outStream.available() + ", errStream" + errStream.available() + "]. Is channel closed: " + channel.isClosed());
                while (outStream.available() > 0) {
                    int i = outStream.read(tmp, 0, tmp.length);
                    if (i < 0) {
                        break;
                    }
                    sbOut.append(new String(tmp, 0, i, parameters.getCharSet()));
                }
                while (errStream.available() > 0) {
                    int i = errStream.read(tmp, 0, tmp.length);
                    if (i < 0) {
                        break;
                    }
                    sbErr.append(new String(tmp, 0, i, parameters.getCharSet()));
                }
                if (channel.isClosed()) {
                    break;
                }
                try {
                    Thread.sleep(SLEEP_INTERVAL);
                } catch (InterruptedException ignored) {
                }
            }
            logger.trace("Done with reading output streams");

            results = new JschInvokerResult(commandLine, sbOut.toString(), sbErr.toString(), channel.getExitStatus());

        } catch (JSchException jse) {
            throw new ExecutionException("Caught JschException while executing " + commandLine, jse);
        } catch (IOException e) {
            throw new ExecutionException("Caught IOException while gathering output " + commandLine, e);
        } finally {
            inUse = false;
            IOUtils.closeQuietly(inStream);
            IOUtils.closeQuietly(outStream);
            IOUtils.closeQuietly(errStream);
            channel.disconnect();
        }
        return results;
    }

    /**
     * Close a session.  This is used by the JschCacheRemovalListener which calls this function before a session is removed
     * from the cache.
     */
    public void close() {
        if (session != null) {
            session.disconnect();
            session = null;
        }
    }

    public boolean isConnected() {
        if (session != null) {
            return session.isConnected();
        }
        return false;
    }

    public boolean isInUse() {
        return inUse;
    }

    public String getLastCommand() {
        return lastCommand;
    }

    public void setLastCommand(String lastCommand) {
        this.lastCommand = lastCommand;
    }

    /**
     * We return a string representation of this session as a unique number so we can keep track of this sucker.
     * Fortunately our Object's default hashcode is smart enough to return a unique number for each session
     * (supposedly based upon its address).
     */
    @Override
    public String toString() {
        return "JschSession [id=" + sessionId + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((sessionId == null) ? 0 : sessionId.hashCode());
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
        JschSession other = (JschSession) obj;
        if (sessionId == null) {
            if (other.sessionId != null)
                return false;
        } else if (!sessionId.equals(other.sessionId))
            return false;
        return true;
    }
}
