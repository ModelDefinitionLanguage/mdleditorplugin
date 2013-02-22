package com.mango.mif.core.exec.jsch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.base.Preconditions;
import com.mango.mif.connector.TestsHelper;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.invoker.InvokerFactory;
import com.mango.mif.utils.TestProperties;

/**
 * Jsch session pool test
 * @author mrogalski
 *
 */
public class JschSessionTest {

    /**
     * Logger
     */
    @SuppressWarnings("unused")
	private final static Logger LOG = Logger.getLogger(JschSessionTest.class);

    private int port;

    private InvokerFactory invokerFactory;

    private String encryptedPassword;

    private String userName;

    @BeforeClass
    public static void loadProperties() throws IOException {
        TestsHelper.initProperties(JschSessionTest.class, "/tests.properties");
    }
    
    
    @Before
    public void setup() throws ExecutionException {
        userName = System.getProperty(TestProperties.MIF_ALTER_USER_NAME);
        encryptedPassword = System.getProperty(TestProperties.MIF_ALTER_USER_PASSWORD);
        String portProp = System.getProperty(TestProperties.MIF_JSCH_PORT);
        String host = System.getProperty(TestProperties.MIF_JSCH_HOST);
        Preconditions.checkNotNull(userName, TestProperties.MIF_ALTER_USER_NAME + " property is not set.");
        Preconditions.checkNotNull(encryptedPassword, TestProperties.MIF_ALTER_USER_PASSWORD + " property is not set.");
        Preconditions.checkNotNull(portProp, TestProperties.MIF_JSCH_PORT + " property is not set.");
        if (host == null) {
            host = "localhost";
        }
        port = Integer.parseInt(portProp);
        
        invokerFactory = new InvokerFactory().port(port).host(host);
    }

    @Test
    public void shouldReturnAJschSessionIfOneAlreadyExistsForAGivenUser() throws ExecutionException {
        JschParameters parameters1 = invokerFactory.buildJschParameters(userName, encryptedPassword);
        invokerFactory.setProtocols("abc");
        JschParameters parameters2 = invokerFactory.buildJschParameters(userName, encryptedPassword);
        
        // same user,host, port then equal parameters
        assertEquals(parameters1, parameters2);
        
        JschSession jschSession1 = new JschSession(parameters1);
        JschSession jschSession2 = new JschSession(parameters1);
        // Same parameters but different sessions instances
        assertTrue(!jschSession1.equals(jschSession2));
    }
}
