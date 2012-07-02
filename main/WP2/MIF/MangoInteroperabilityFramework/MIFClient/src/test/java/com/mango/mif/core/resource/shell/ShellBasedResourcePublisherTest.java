/**
 * Copyright Mango Solutions, 2012.
 * <p>SVN Entry: $HeadURL: $
 * <p>SVN ID: $Id: $
 * <p>Last edited by : $Author$
 * @author Tony Bamford &lt;tbamford@mango-solutions.com&gt;
 * @version $Revision$ as of on $Date$
 */
package com.mango.mif.core.resource.shell;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.mango.mif.connector.TestsHelper;
import com.mango.mif.core.impl.DefaultWorkspaceManager;
import com.mango.mif.core.resource.ResourcePublisher;
import com.mango.mif.core.resource.UserSharedLocationManager;
import com.mango.mif.exception.MIFException;
import com.mango.mif.utils.TestProperties;


public class ShellBasedResourcePublisherTest {
	/**
	 * Log
	 */
	private final static Logger LOG = Logger.getLogger(ShellBasedResourcePublisherTest.class);
    File tmpSharedDirectory;
    File tmpRootDirectory;
    UserSharedLocationManager sharedLocationManager;
    /**
     * SSH port
     */
	private int	port;
	/**
	 * user name that will be used
	 */
    private String userName;
    /**
     * encrypted user password
     */
	private String	encryptedPassword;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        UUID uuid = UUID.randomUUID();
        String base = uuid.toString();
        tmpSharedDirectory = TestsHelper.tmpDirectoryPath(base + "-DEST");
        tmpRootDirectory = TestsHelper.createTmpDirectory(base + "-SRC");
        sharedLocationManager = new UserSharedLocationManager();
        sharedLocationManager.setWorkspaceManager(new DefaultWorkspaceManager(tmpSharedDirectory, false));
        

    	userName = System.getProperty(TestProperties.MIF_ALTER_USER_NAME);
    	encryptedPassword = System.getProperty(TestProperties.MIF_ALTER_USER_PASSWORD);
    	String portProp = System.getProperty(TestProperties.MIF_JSCH_PORT);
		Preconditions.checkNotNull(userName, TestProperties.MIF_ALTER_USER_NAME + " property is not set.");
		Preconditions.checkNotNull(encryptedPassword, TestProperties.MIF_ALTER_USER_PASSWORD + " property is not set.");
		Preconditions.checkNotNull(port, TestProperties.MIF_JSCH_PORT + " property is not set.");
		port = Integer.parseInt(portProp);
    }

    /**
     * Try to clean up the temporary directories.  Since they were created by another user
     * it is doubtful whether we will be able to do this - hence the ignored exceptions
     */
    @After
    public void tearDown() {
        try {
            FileUtils.deleteDirectory(tmpSharedDirectory);
        } catch (Exception ignored) {
        }
        try {
            FileUtils.deleteDirectory(tmpRootDirectory);
        } catch (Exception ignored) {
        }
    }

    @Test
    public void shouldCopyInputFilesAsUser() throws IOException, MIFException {
        ResourcePublisher publisher = new ShellBasedResourcePublisher();
        publisher.setRootDirectory(tmpRootDirectory);
        publisher.setSharedLocationManager(sharedLocationManager);
        publisher.setUserName(userName);
        //publisher.setPort(28);
        publisher.setPassword(encryptedPassword);

        File dirA = new File(tmpRootDirectory,"A");
        dirA.mkdir();
        File dirB = new File(tmpRootDirectory,"B");
        dirB.mkdir();
        File dirC = new File(tmpRootDirectory,"C");
        dirC.mkdir();

        File aFile = new File(dirA,"a.txt");
        FileUtils.writeStringToFile(aFile, "This is test content of a.txt file.");

        File bFile = new File(dirB,"b.txt");
        FileUtils.writeStringToFile(bFile, "This is test content of b.txt file.");

        List<File> files = Lists.newArrayList(aFile, bFile);

        publisher.addFiles(files);

        File requestInputDirectory = null;
        String requestID = null;
        try {
            requestID = publisher.publish();
            requestInputDirectory = sharedLocationManager.getRequestInputDirectory(requestID);
        } catch (Exception e) {
            LOG.error(e);
        }

        assertTrue(requestInputDirectory != null);
        assertTrue(new File(requestInputDirectory,dirA.getName()).exists());
        assertTrue(new File(requestInputDirectory,dirB.getName()).exists());
        assertTrue(new File(requestInputDirectory,dirC.getName()).exists());
        assertTrue(new File(requestInputDirectory,dirA.getName()+"/" + aFile.getName()).exists());
        assertTrue(new File(requestInputDirectory,dirB.getName()+"/" + bFile.getName()).exists());
        assertTrue(requestID != null);
    }
}
