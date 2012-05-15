/* File			:  DefaultResourceComponentTest.java
 * Project		:  MangoInteroperabilityFramework-trunk
 * Created on	:  4 Apr 2012
 */
package com.mango.mif.core;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mango.mif.connector.TestsHelper;
import com.mango.mif.core.api.MIFResource;
import com.mango.mif.core.api.ResourceComponentException;
import com.mango.mif.core.impl.DefaultResourceComponent;
import com.mango.mif.core.impl.DefaultWorkspaceManager;


/**
 * 
 * Resource Component Unit Test
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */

public class DefaultResourceComponentTest {
    /**
     * Tested Resource component instance 
     */
    private DefaultResourceComponent defaultResourceComponent;
    /**
     * UUID that does not have associated resource with it
     */
    private String notRegisteredResourceUUID;
    /**
     * UUID that does have associated resource with it
     */
    private String registeredResourceUUID;
    /**
     * Request UUID
     */
    private String requestUUID;
    /**
     * Test resource that is uplodaded to resource component
     */
    private static String TEST_RESOURCE = "test.R";
    
    @Before
    public void setUp() throws Exception {
        defaultResourceComponent = new DefaultResourceComponent();
        defaultResourceComponent.setWorkspaceManager(new DefaultWorkspaceManager(TestsHelper.createTmpDirectory()));
        notRegisteredResourceUUID = UUID.randomUUID().toString();
        requestUUID = defaultResourceComponent.createRequestDirectory();
        registeredResourceUUID = defaultResourceComponent.addRequestResource(this.getClass().getResource(TEST_RESOURCE), requestUUID, TEST_RESOURCE).getUuid();
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Requesting a resource with null UUID is not allowed 
     * @throws ResourceComponentException 
     */
    @Test(expected=java.lang.NullPointerException.class)
    public void testGetResourceStringPreconditionCheck() throws ResourceComponentException {
        defaultResourceComponent.getResource(null);
    }

    /**
     * Requesting a not registered UUID should return null 
     * @throws ResourceComponentException 
     */
    @Test
    public void testGetResourceNotRegisteredResourceRequested() throws ResourceComponentException {
        assertTrue(defaultResourceComponent.getResource(notRegisteredResourceUUID)==null);
    }

    /**
     * Requesting a registered UUID should return a resource 
     * @throws ResourceComponentException 
     */
    @Test
    public void testGetResource() throws ResourceComponentException {
        assertNotNull(defaultResourceComponent.getResource(registeredResourceUUID));
    }

    /**
     * Added resource should be registered with the UUID that should resolve to that resource
     * @throws IOException 
     */
    @Test
    public void testAddRequestResource() throws ResourceComponentException, IOException {
        String requestUUID = defaultResourceComponent.createRequestDirectory();
        MIFResource mifResource = defaultResourceComponent.addRequestResource(this.getClass().getResource(TEST_RESOURCE),requestUUID, TEST_RESOURCE);
        assertNotNull(mifResource);
        assertNotNull(defaultResourceComponent.getResource(mifResource.getUuid()));
        InputStream iStream  = null;
        
        try {
            iStream = mifResource.getContent().openStream();
        } finally {
            if(iStream!=null) iStream.close();
        }
    }
    /**
     * Added resource should be registered with the UUID that should resolve to that resource
     * @throws IOException 
     */
    @Test
    public void testAddResultResource() throws ResourceComponentException, IOException {
        String requestUUID = defaultResourceComponent.createRequestDirectory();
        MIFResource mifResource = defaultResourceComponent.addResponseResource(this.getClass().getResource(TEST_RESOURCE),requestUUID, TEST_RESOURCE);
        assertNotNull(mifResource);
        assertNotNull(defaultResourceComponent.getResource(mifResource.getUuid()));
        InputStream iStream  = null;
        
        try {
            iStream = mifResource.getContent().openStream();
        } finally {
            if(iStream!=null) iStream.close();
        }
    }

    /**
     * Not configured resource component should not be called
     */
    @Test(expected=java.lang.NullPointerException.class)
    public void testSetWorkspaceManagerNotSet() throws ResourceComponentException {
        defaultResourceComponent.setWorkspaceManager(null);
        defaultResourceComponent.addRequestResource(this.getClass().getResource(TEST_RESOURCE), UUID.randomUUID().toString(), TEST_RESOURCE);
    }

    /**
     * Should return workspace manager that has been set 
     */
    @Test
    public void testGetWorkspaceManager() {
        assertNotNull(defaultResourceComponent.getWorkspaceManager());
    }

    @Test(expected=RuntimeException.class)
    public void testRemoveResource() throws ResourceComponentException {
        String resID = defaultResourceComponent.addRequestResource(this.getClass().getResource(TEST_RESOURCE), UUID.randomUUID().toString(), TEST_RESOURCE).getUuid();
        MIFResource mifResource = defaultResourceComponent.removeResource(resID);
        assertNotNull(mifResource);
        assertTrue(!mifResource.exists());
        mifResource.getContent();
    }
    
    
    /**
     * @throws ResourceComponentException 
     * 
     */
    @Test
    public void testPublishFolderStructure() throws ResourceComponentException {
        String requestUUID = defaultResourceComponent.createRequestDirectory();
        String fileName =  "sub-dir/" + TEST_RESOURCE;
        String resID = defaultResourceComponent.addRequestResource(this.getClass().getResource(TEST_RESOURCE), requestUUID,fileName).getUuid();
        assertEquals(resID,requestUUID+"/request/" +fileName);
        assertTrue(defaultResourceComponent.getWorkspaceManager().resolveFile(new File(new File(requestUUID,"request"),fileName)).exists());
    }
}
