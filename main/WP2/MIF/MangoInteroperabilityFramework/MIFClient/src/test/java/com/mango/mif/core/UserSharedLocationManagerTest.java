/* File			:  UserSharedLocationManagerTest.java
 * Project		:  MIFClient
 * Created on	:  11 Jun 2012
 */
package com.mango.mif.core;

import java.io.File;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mango.mif.connector.TestsHelper;
import com.mango.mif.core.impl.DefaultWorkspaceManager;
import com.mango.mif.core.resource.UserSharedLocationManager;


/**
 * 
 * Unit test of User Resource Component
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 *
 */
public class UserSharedLocationManagerTest {
	/**
	 * resource component instance being tested
	 */
	UserSharedLocationManager userSharedLocationManager;
    /**
     * Request UUID
     */
    private String requestUUID;
    /**
     * tmp directory holding resource files
     */
    private File tmpResourceDirectory;
    @Before
    public void setUp() throws Exception {
    	userSharedLocationManager = new UserSharedLocationManager();
    	tmpResourceDirectory = TestsHelper.createTmpDirectory();
    	userSharedLocationManager.setWorkspaceManager(new DefaultWorkspaceManager(tmpResourceDirectory));
        requestUUID = UUID.randomUUID().toString();
        new File(tmpResourceDirectory,requestUUID).mkdirs();
    }
    @After
    public void tearDown() throws Exception {
    	FileUtils.deleteDirectory(tmpResourceDirectory);
    }
    
	@Test(expected = NullPointerException.class)
	public void shouldThrowExceptionWhenInputDirectoryRequestedAndWorkspaceManagerIsNotSet() {
		userSharedLocationManager.setWorkspaceManager(null);
		userSharedLocationManager.getRequestInputDirectory(requestUUID);
	}

	@Test(expected = NullPointerException.class)
	public void shouldThrowExceptionWhenOutputDirectoryRequestedAndWorkspaceManagerIsNotSet() {
		userSharedLocationManager.setWorkspaceManager(null);
		userSharedLocationManager.getRequestOutputDirectory(requestUUID);
	}

}
