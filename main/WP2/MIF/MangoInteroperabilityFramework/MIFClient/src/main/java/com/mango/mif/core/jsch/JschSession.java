/**
 * Copyright Mango Solutions, 2012.
 * <p>CVS Entry : $Header$
 * <p>Last edited by : $Author$
 * @author Tony Bamford &lt;tbamford@mango-solutions.com&gt;
 * @version $Revision$ as of on $Date$
 */
package com.mango.mif.core.jsch;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;
import com.mango.mif.exception.MIFException;

/**
 * A variation on JschForegroundProcess which breaks appart the session and the channel in the
 * hope of improving performance.
 */
public class JschSession {

    private static final Logger logger = LoggerFactory.getLogger(JschSession.class);

    // non optional
    private final String userName;
    private final String password;

    // optional (defaulted)
    private final String host;
    private final String charSet;
    private final int timeoutInMilliseconds;
    private final int port;

    private Session session;

    /**
     * Can only create using a JschSessionBuilder.
     * @param builder the JschSessionBuilder
     * @throws MIFException probably because the user doesn't exist on this machine, or the password is incorrect
     */
    public JschSession(JschParameterBuilder builder) throws MIFException {
        this.userName = builder.userName;
        this.password = builder.password;
        this.host = builder.host;
        this.charSet = builder.charSet;
        this.port = builder.port;
        this.timeoutInMilliseconds = builder.timeoutInMilliseconds;

        JSch jsch = new JSch();
        try {
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");

            JSch.setLogger(new JschLogger());
            jsch = new JSch();
            session = jsch.getSession(userName, host, port);
            UserInfo ui = new JschUserInfo(password);
            session.setUserInfo(ui);
            //session.setPassword(password);
            session.setConfig(config);
            session.connect();

        } catch (Exception e) {
            String message = "Caught exception creating JSCH session, username: "
                    + userName
                    + " host: "
                    + host
                    + " port: "
                    + port;
            logger.error(message, e);
            throw new MIFException(message, e);
        }
    }

    /**
     * Run the specified command, using the previously created session.
     * 
     * @param commandLine the specified command
     * @return the packaged results of the command - stdout, stderr and exit status
     * @throws MIFException on IO Exception or JSCH problems.
     */
    public JschResults run(String commandLine) throws MIFException {
        return run(commandLine, null);
    }

    /**
     * Run the specified command, with the specified input, using the previously created session.
     * 
     * @param commandLine the specified command
     * @param input text to be shoved up the poor command's stdin
     * @return the packaged results of the command - stdout, stderr and exit status
     * @throws MIFException on IO Exception or JSCH problems.
     */
    public JschResults run(String commandLine, String input) throws MIFException {

    	if (session == null || !session.isConnected()) {
    		throw new IllegalStateException("Session is null or uninitialised. Have you called close() previously?");
    	}
    	
        JschResults results = null;
        InputStream outStream = null;
        InputStream errStream = null;
        Channel channel = null;
        try {
            channel = session.openChannel("exec");

            ((ChannelExec) channel).setCommand(commandLine);

            StringBuilder sbStdout = new StringBuilder();
            StringBuilder sbStderr = new StringBuilder();

            // the output is retrieved by calling "getInputStream"?  Go figure.
            outStream = channel.getInputStream();

            // at least the error stream is retrieved with a sensibly named call
            errStream = ((ChannelExec) channel).getErrStream();

            // Ah... setInputStream sets the command's standard input, as opposed to
            // getInputStream which gets its standard output...
            // So... if we have input, pass it to the command
            //
            if (StringUtils.isNotBlank(input)) {
                channel.setInputStream(new ByteArrayInputStream(input.getBytes()));
            }
            channel.connect();

            // Note that I made several different attempts here to detect if the incoming command (in "commandLine") is
            // invalid by checking the connectedness and closedness of the channel.  Unfortunately the channel is always
            // open and always connected whether the command is valid or not.

            while (!channel.isEOF()) {
                gather(outStream, sbStdout);
                gather(errStream, sbStderr);
            }

            // Wait for the sub process to die.  We have to do this as obviously the exit status will not be available
            // until it is dead.  We have all its output, so we're really just waiting for JSCH to catch up.
            //
            if (timeoutInMilliseconds > 0) {
                long stop = System.currentTimeMillis() + timeoutInMilliseconds;
                while (System.currentTimeMillis() < stop && channel.isConnected()) {
                    // Make our wait less busy by sleeping for a while
                    try {
                        Thread.sleep(500);
                    } catch (Exception ignored) {
                    }
                }
            }
            results = new JschResults(sbStdout.toString(), sbStderr.toString(), channel.getExitStatus());

        } catch (JSchException jse) {
            throw new MIFException("Caught JschException while executing " + commandLine, jse);
        } catch (IOException e) {
            throw new MIFException("Caught IOException while gathering output " + commandLine, e);
        } finally {
            IOUtils.closeQuietly(outStream);
            IOUtils.closeQuietly(errStream);
            channel.disconnect();
        }
        return results;
    }
    
    public void close() {
    	if (session != null) {
    		session.disconnect();
    		session = null;
    	}
    }

    /**
     * Gather input from the stream and append it to the string builder.
     * @param stream The stream to read from
     * @param sb The string builder to append to.
     * @throws IOException If anything goes wrong.
     */
    private void gather(InputStream stream, StringBuilder sb) throws IOException {
        byte[] tmp = new byte[1024];
        while (stream.available() > 0) {
            int i = stream.read(tmp, 0, tmp.length);
            if (i < 0) {
                break;
            }
            sb.append(new String(tmp, 0, i, charSet));
        }
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

    public Session getSession() {
    	return session;
    }

    public String getCharSet() {
        return charSet;
    }

    public int getTimeoutInMilliseconds() {
        return timeoutInMilliseconds;
    }

    public int getPort() {
        return port;
    }
}
