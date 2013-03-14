/**
 * Copyright Mango Solutions, 2012.
 * <p>SVN Entry: $HeadURL: $
 * <p>SVN ID: $Id: $
 * <p>Last edited by : $Author$
 * @author Tony Bamford &lt;tbamford@mango-solutions.com&gt;
 * @version $Revision$ as of on $Date$
 */
package com.mango.mif.core;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.vngx.jsch.Channel;
import org.vngx.jsch.ChannelExec;
import org.vngx.jsch.JSch;
import org.vngx.jsch.Session;
import org.vngx.jsch.UIKeyboardInteractive;
import org.vngx.jsch.UserInfo;
import org.vngx.jsch.config.SessionConfig;

/**
 * This code is basically taken from here:
 * 
 * https://sites.google.com/site/projectcodebank/languages/java-1/jsch-example
 * 
 * and rewritten so it actually works.  It provides a basic, stand alone sanity check for JSCH against
 * which it is possible to test various things.  It was incredibly useful in proving that JSCH really can
 * gather all the output from executing commands - every last line.
 * 
 * Note that it does have some shortcomings in that it only reads the standard output from the command,
 * the standard error is left to fend for itself.
 * 
 * As such it may be useful for the future which is why I am committing it.
 */
@Ignore
public class StandaloneJschTest {

    private static Logger logger = Logger.getLogger(StandaloneJschTest.class);

    class JschUserInfo implements UserInfo, UIKeyboardInteractive {

        private String clearTextPassword;

        public JschUserInfo(String password) {
            this.clearTextPassword = password;
        }

        @Override
        public String[] promptKeyboardInteractive(String destination,
                String name,
                String instruction,
                String[] prompt,
                boolean[] echo) {

            String[] response = new String[1];
            response[0] = this.clearTextPassword;
            return response;
        }

        public void setClearTextPassword(String password) {
            this.clearTextPassword = password;
        }

        @Override
        public String getPassword() {
            return clearTextPassword;
        }

        @Override
        public String getPassphrase() {
            return null;
        }

        @Override
        public boolean promptPassphrase(String arg0) {
            return false;
        }

        @Override
        public boolean promptPassword(String arg0) {
            return true;
        }

        @Override
        public boolean promptYesNo(String arg0) {
            return true;
        }

        @Override
        public void showMessage(String arg0) {
            logger.info(this.getClass().getName() + ".showMessage: " + arg0);
        }
    }

    private Session session = null;
    private Channel channel = null;
    private String host = "localhost";
    private String user = "[USER_NAME]";
    private String password = "[PASSWORD]";
    private JSch jsch = null;

    /**
     * https://sites.google.com/site/projectcodebank/languages/java-1/jsch-example
     * 
     * MODIFIED
     */
    public Channel initializeSession() {
        try {
            jsch = JSch.getInstance();
            session = jsch.createSession(user, host, 22, getSessionConfig());
            session.setUserInfo(new JschUserInfo(password));
            session.connect(password.getBytes());
            return session.openChannel("exec");
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private SessionConfig getSessionConfig() {
        Map<String, String> properties = new HashMap<String, String>();
        properties.put(SessionConfig.STRICT_HOST_KEY_CHECKING, "no");
        properties.put(SessionConfig.PREFFERED_AUTHS, "gssapi-with-mic,publickey,keyboard-interactive,password");
        return new SessionConfig(properties);
    }

    /**
     * https://sites.google.com/site/projectcodebank/languages/java-1/jsch-example
     * 
     * MODIFIED:
     *    returns output as a string
     *    finally block closes channel and session
     */
    public String executeCommand(String command) {
        String result = null;
        try {
            channel = initializeSession();
            if (channel != null) {
                ((ChannelExec)channel).setCommand(command);
                channel.setInputStream(null);
                ((ChannelExec)channel).setErrStream(System.err);
                InputStream in = channel.getInputStream();
                channel.connect();
                result = readServerOutput(channel, in);
                in.close();
            } else {
                System.out.println("Failed to initialise session");
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if (channel != null) {
                channel.disconnect();
            }
            channel = null;
            if (session != null) {
                session.disconnect();
            }
            session = null;
        }
        return result;
    }

    /**
     * https://sites.google.com/site/projectcodebank/languages/java-1/jsch-example
     * 
     * MODIFIED to return output as string.
     */
    public String readServerOutput(Channel channel, InputStream in) {
        StringBuilder sb = new StringBuilder();
        byte[] tmp = new byte[1024];
        try {
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) {
                        break;
                    }
                    sb.append(new String(tmp, 0, i));
                }
                if (channel.isClosed()) {
                    System.out.println("exit-status: " + channel.getExitStatus());
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch(Exception ee) {
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public int countLines(String input) {
        return StringUtils.countMatches(input, "\n");
    }

    @Test
    public void shouldRepeatedlyCountAllLinesInPasswd() {
        String result = executeCommand("cat /etc/passwd");
        int expected = countLines(result);

        for (int run = 1; run <= 500; run++) {
            result = executeCommand("cat /etc/passwd");
            int got = countLines(result);

            if (got == expected) {
                System.out.println("run #" + run + " PASSED");
            } else {
                System.out.println("run #" + run + " FAILED (got " + got + " expected " + expected + ")");
            }
        }
    }

    @Test
    public void shouldRepeatedlyCountAllLinesInCommandWithFixedOutput() {
        Random rand = new Random();

        for (int run = 1; run <= 500; run++) {
            int expected = rand.nextInt(50) + 10;
            String result = executeCommand("/home/navplus/jsch-iotest.sh -s -T " + expected);
            int got = countLines(result);

            if (got == expected) {
                System.out.println("run #" + run + " PASSED");
            } else {
                System.out.println("run #" + run + " FAILED (got " + got + " expected " + expected + ")");
            }
        }
    }
}
