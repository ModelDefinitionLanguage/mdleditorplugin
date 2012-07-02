/**
 * Copyright Mango Solutions, 2012.
 * <p>SVN Entry: $HeadURL: $
 * <p>SVN ID: $Id: $
 * <p>Last edited by : $Author$
 * @author Tony Bamford &lt;tbamford@mango-solutions.com&gt;
 * @version $Revision$ as of on $Date$
 */
package com.mango.mif.core.resource.jsch;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Preconditions;
import com.mango.mif.core.jsch.JschHelper;
import com.mango.mif.core.jsch.JschParameterBuilder;
import com.mango.mif.core.jsch.JschResults;
import com.mango.mif.core.jsch.JschSession;
import com.mango.mif.exception.MIFException;
import com.mango.mif.utils.TestProperties;
import com.mango.mif.utils.encrypt.EncrypterFactory;

/**
 * Tests JschSession
 *
 */
public class JschSessionTest {
	/**
	 * LOG
	 */
    private static Logger LOG = Logger.getLogger(JschSessionTest.class);

	/**
	 * user name that will be used
	 */
    private String userName;
    /**
     * clear user password
     */
    private String userClearPassword;
    /**
     * jsch port
     */
	private int	port;
	
	/*
	 * Commands used in tests
	 */
    private static final String LS_COMMAND = "/bin/ls -l";
    private static final String LS_ETC_COMMAND = "/bin/ls -l /etc";
    private static final String WHOAMI_COMMAND = "/usr/bin/whoami";
    private static final String READ_COMMAND = "read junk; echo \"<$junk>\"";
    private static final String MULTILINE_COMMAND = "/usr/bin/whoami\n /bin/ls /bin\n /bin/ls /etc\n";


    @Before
    public void setup() {

    	userName = System.getProperty(TestProperties.MIF_ALTER_USER_NAME);
    	String portProp = System.getProperty(TestProperties.MIF_JSCH_PORT);
    	String encryptedPassword = System.getProperty(TestProperties.MIF_ALTER_USER_PASSWORD);
		Preconditions.checkNotNull(userName, TestProperties.MIF_ALTER_USER_NAME + " property is not set.");
		Preconditions.checkNotNull(encryptedPassword, TestProperties.MIF_ALTER_USER_PASSWORD + " property is not set.");
		Preconditions.checkNotNull(port, TestProperties.MIF_JSCH_PORT + " property is not set.");
		userClearPassword = EncrypterFactory.getEncrypter().decrypt(encryptedPassword);
		port = Integer.parseInt(portProp);
    }

    @Test
    public void shouldRunSingleCommand() throws MIFException {

            JschParameterBuilder builder = new JschParameterBuilder(userName, userClearPassword).port(port);
            JschSession process = builder.buildSession();

            JschResults results = process.run(LS_COMMAND);
            assertTrue(results != null);
            assertTrue(JschHelper.success(results));
            assertTrue(results.getStdout().length() > 0);

            LOG.info(results.getStdout());
    }

    @Test
    public void shouldRunMultipleCommands() throws MIFException {

            JschParameterBuilder builder = new JschParameterBuilder(userName, userClearPassword).port(port);
            JschSession process = new JschSession(builder);

            JschResults results = process.run(LS_COMMAND);
            assertTrue(results != null);
            assertTrue(JschHelper.success(results));
            assertTrue(results.getStdout().length() > 0);

            results = process.run(LS_ETC_COMMAND);
            assertTrue(results != null);
            assertTrue(JschHelper.success(results));
            assertTrue(results.getStdout().length() > 0);

            results = process.run(WHOAMI_COMMAND);
            assertTrue(results != null);
            assertTrue(JschHelper.success(results));
            assertTrue(results.getStdout().length() > 0);

            results = process.run(READ_COMMAND, "hello world");
            assertTrue(results != null);
            assertTrue(JschHelper.success(results));
            assertTrue(results.getStdout().length() > 0);

    }

    @Test
    public void shouldRunMultilineCommand() throws MIFException {
            JschParameterBuilder builder = new JschParameterBuilder(userName, userClearPassword).port(port);
            JschSession process = new JschSession(builder);

            JschResults results = process.run(MULTILINE_COMMAND);
            assertTrue(results != null);
            assertTrue(JschHelper.success(results));
            assertTrue(results.getStdout().length() > 0);

            LOG.info(results.getStdout());
    }
    
}
