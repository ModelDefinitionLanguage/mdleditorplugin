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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.mango.mif.exception.MIFException;


/**
 * This class will allow you to run a SINGLE command as the given user with the given password on the
 * given machine (default "localhost").  There is a choice of how to capture its output.  You can
 * immediately grab (in which case there may well be nothing because the command hasn't gotten around
 * to generating any output yet) or you can capture all the output within a specified time interval.
 * 
 * When the command terminates and you try to read from it, you get an exception.  It doesn't have to
 * be that way, you could check the status first.
 */
public class JschBackgroundProcess {

    private static final Logger logger = LoggerFactory.getLogger(JschBackgroundProcess.class);

    // non optional
    private final String commandLine;
    private final String userName;
    private final String password;

    // optional (defaulted)
    private final String host;
    private final String charSet;
    private int port;
    private Status status;

    private Session session;
    private Channel channel;

    public enum Status {
        RUNNING,
        FINISHED,
        ERROR
    }

    public JschBackgroundProcess(String commandLine, JschParameterBuilder builder) throws MIFException {
        this.commandLine = commandLine;
        this.userName = builder.userName;
        this.password = builder.password;
        this.host = builder.host;
        this.port = builder.port;
        this.charSet = builder.charSet;

        JSch jsch = new JSch();
        try {
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");

            jsch = new JSch();
            session = jsch.getSession(userName, host, port);
            session.setPassword(password);
            session.setConfig(config);
            session.connect();
            channel = session.openChannel("exec");

            ((ChannelExec) channel).setCommand(commandLine);
            channel.setInputStream(null);

            // In a process I clearly don't understand, if you do the following, the command's standard error will
            // appear on your own error stream, especially if you don't try to read it.  My guess is when a certain
            // number of errors are produced, some buffer limit is hit and the buffers are flushed... all over you.
            //
            // ((ChannelExec) channel).setErrStream(System.err);

            channel.connect();

            status = Status.RUNNING;

        } catch (Exception e) {
            String message = "JschUserProcess CTOR failed, " + report();
            logger.error(message, e);
            throw new MIFException(message, e);
        }
    }


    /**
     * If you call this version (as opposed to the "waitFor" version of this function), you might
     * get back an empty string because you've called it too quickly and the command hasn't produced
     * any output yet.  That doesn't mean it won't.  Caveat programmer.
     *
     * @return the latest available data from stdout
     * @throws JschProcessTerminatedException if asking for output from a terminated command
     * @throws JschProcessIOException if an IO error occurs
     */
    public String getLatestStdout() throws MIFException {
        try {
            // The output is retrieved by calling "getInputStream"?  Go figure.
            return getLatestFrom(channel.getInputStream(), "stdout", 0);
        }  catch (IOException e) {
            status = Status.ERROR;
            throw new MIFException(report() + " gave IO error while reading stdout: " + e.getMessage(), e);
        }
    }

    /**
     * If you call this version (as opposed to the "waitFor" version of this function), you might
     * get back an empty string because you've called it too quickly and the command hasn't produced
     * any output yet.  That doesn't mean it won't.  Caveat programmer.
     *
     * @return the latest available data from stderr
     * @throws JschProcessTerminatedException if asking for output from a terminated command
     * @throws JschProcessIOException if an IO error occurs
     */
    public String getLatestStderr() throws MIFException {
        try {
            // at least the error stream is retrieved with a sensibly named call
            // although we do need this silly cast to ChannelExec
            return getLatestFrom(((ChannelExec) channel).getErrStream(), "stderr", 0);
        }  catch (IOException e) {
            status = Status.ERROR;
            throw new MIFException(report() + " gave IO error while reading stdout: " + e.getMessage(), e);
        }
    }

    /**
     * Wait around for timeoutMilliseconds for some output from the process.  Return whatever we get
     * during that time.
     *
     * @param timeoutMilliseconds the maximum time to wait in milliseconds
     * @return the latest available data from stdout as a string
     * @throws JschProcessTerminatedException if asking for output from a terminated command
     * @throws JschProcessIOException if an IO error occurs
     */
    public String getWaitForLatestStdout(int timeoutMilliseconds) throws MIFException {
        try {
            // The output is retrieved by calling "getInputStream"?  Go figure.
            return getLatestFrom(channel.getInputStream(), "stdout", timeoutMilliseconds);
        }  catch (IOException e) {
            status = Status.ERROR;
            throw new MIFException(report() + " gave IO error while reading stdout: " + e.getMessage(), e);
        }
    }

    /**
     * @param timeoutMilliseconds the maximum time to wait in milliseconds
     * @return the latest available data from stderr
     * @throws JschProcessTerminatedException if asking for output from a terminated command
     * @throws JschProcessIOException if an IO error occurs
     */
    public String getWaitForLatestStderr(int timeoutMilliseconds) throws MIFException {
        try {
            // at least the error stream is retrieved with a sensibly named call
            // although we do need this silly cast to ChannelExec
            return getLatestFrom(((ChannelExec) channel).getErrStream(), "stderr", timeoutMilliseconds);
        }  catch (IOException e) {
            status = Status.ERROR;
            throw new MIFException(report() + " gave IO error while reading stdout: " + e.getMessage(), e);
        }
    }


    /**
     * Gather data from the specified stream.
     * 
     * @param stream The specified stream.
     * @param which String used for error reporting.
     * @param timeoutMilliseconds wait for a maximum of this many milliseconds
     * @return Data gathered from the specified stream in the specified time interval, both
     * as output and punctuated with buffer end markers for debugging
     *
     * @throws JschProcessTerminatedException if the process is already terminated
     * @throws JschProcessIOException if there is an I/O exception during processing
     */
    private String getLatestFrom(InputStream stream, String which, int timeoutMilliseconds) throws MIFException {

        if (!channel.isConnected()) {
            status = Status.FINISHED;
            throw new MIFException("Cannot read "
                    + which
                    + " of "
                    + report()
                    + " because the process has already terminated");
        }

        StringBuilder sb = new StringBuilder();
        try {
            long then = System.currentTimeMillis() + timeoutMilliseconds;

            do {
                if (!channel.isEOF()) {
                    byte[] tmp = new byte[1024];
                    while (stream.available() > 0) {
                        int i = stream.read(tmp, 0, tmp.length);
                        if (i < 0) {
                            break;
                        }
                        sb.append(new String(tmp, 0, i, charSet));
                    }
                }

                // Don't want to go around multiple times if we want immediate
                // feedback and we are so fast we execute in the same millisecond.
                if (timeoutMilliseconds == 0) {
                    break;
                }
            } while (System.currentTimeMillis() < then);

            return sb.toString();
        } catch (IOException e) {
            throw new MIFException(report() + " gave IO error while reading " + which + ": " + e.getMessage(), e);
        }
    }

    /**
     * @return as much useful information as we can about this process. Useful for exception messages.
     */
    private String report() {
        return "command \""
                + commandLine
                + "\" run as user "
                + userName
                + " on machine "
                + host;
    }

    /**
     * @return the exit status of the command.
     * @throws JschProcessRunningException if the process is still running and thus does not have an exit status yet
     */
    public int getExitStatus() throws MIFException {

        if (channel.isConnected()) {
            throw new MIFException(report() + " has no exit status because it is still running");
        }
        status = Status.FINISHED;
        return channel.getExitStatus();
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

    public Status getStatus() {
        // return the status, but check we have it right first.
        //
        if (status != Status.ERROR && !channel.isConnected()) {
            status = Status.FINISHED;
        }
        return status;
    }
}
