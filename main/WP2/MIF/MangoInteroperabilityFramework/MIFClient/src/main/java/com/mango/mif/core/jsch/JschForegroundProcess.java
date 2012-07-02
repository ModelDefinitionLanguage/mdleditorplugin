/**
 * Copyright Mango Solutions, 2012.
 * <p>CVS Entry : $Header$
 * <p>Last edited by : $Author$
 * @author Tony Bamford &lt;tbamford@mango-solutions.com&gt;
 * @version $Revision$ as of on $Date$
 */
package com.mango.mif.core.jsch;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;
import com.mango.mif.exception.MIFException;

/**
 * This class will allow you to run one command, as the specified user, on the specified machine (default
 * "localhost") to completion.  Its output will be gathered (stdout and stderr) and its exit status be
 * established.  We can do all this because its a "foreground" process, i.e. one we are waiting for.
 * 
 * @deprecated Use JschSession - its twice as fast.
 */
@Deprecated
public class JschForegroundProcess {

    private static final Logger logger = LoggerFactory.getLogger(JschForegroundProcess.class);

    // non optional
    private final String commandLine;
    private final String userName;
    private final String password;

    // optional (defaulted)
    private final String host;
    private final String charSet;
    private final int timeoutInMilliseconds;
    private final int port;

    private Session session;
    private Channel channel;

    private int exitStatus;
    private String stdout;
    private String stderr;


    public JschForegroundProcess(String commandLine, JschParameterBuilder builder) throws MIFException {
        this.commandLine = commandLine;
        this.userName = builder.userName;
        this.password = builder.password;
        this.host = builder.host;
        this.charSet = builder.charSet;
        this.timeoutInMilliseconds = builder.timeoutInMilliseconds;
        this.port = builder.port;

        JSch jsch = new JSch();
        try {
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");

            jsch = new JSch();
            session = jsch.getSession(userName, host, port);
            UserInfo ui = new JschUserInfo(password);
            session.setUserInfo(ui);
            //session.setPassword(password);
            session.setConfig(config);
            session.connect();
            channel = session.openChannel("exec");

            ((ChannelExec) channel).setCommand(commandLine);
            channel.setInputStream(null);

            // Do not do this.  See comments in JschBackgroundProcess
            //
            // ((ChannelExec) channel).setErrStream(System.err);

            StringBuilder sbStdout = new StringBuilder();
            StringBuilder sbStderr = new StringBuilder();

            InputStream outStream = null;
            InputStream errStream = null;
            try {
                // the output is retrieved by calling "getInputStream"?  Go figure.
                outStream = channel.getInputStream();

                // at least the error stream is retrieved with a sensibly named call
                errStream = ((ChannelExec) channel).getErrStream();

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
                this.exitStatus = channel.getExitStatus();

            } catch (IOException e) {
                throw new MIFException("Caught exception while gathering output from command " + commandLine, e);
            } finally {
                IOUtils.closeQuietly(outStream);
                IOUtils.closeQuietly(errStream);
            }

            this.stdout = sbStdout.toString();
            this.stderr = sbStderr.toString();

        } catch (Exception e) {
            String message = "JschForegroundProcess CTOR failed, " + userName + ", " + host; // + ", password " + this.password;
            logger.error(message, e);
            throw new MIFException(message, e);
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

    /**
     * @return the exit status of the command.
     */
    public int getExitStatus() {
        return this.exitStatus;
    }

    public String getCommandLine() {
        return commandLine;
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

    public String getStdout() {
        return stdout;
    }

    public String getStderr() {
        return stderr;
    }
}
