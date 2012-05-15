/* File			:  DefaultResourceComponentTest.java
 * Project		:  MangoInteroperabilityFramework-trunk
 * Created on	:  4 Apr 2012
 */
package com.mango.mif.core;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mango.mif.core.api.ResourceComponentException;
import com.mango.mif.core.impl.DefaultResourceComponent;


/**
 * 
 * Resource Component Spring configuration test
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SpringConfiguredDefaultResourceComponentTest {
    /**
     * Tested Resource component instance 
     */
    @Autowired
    private DefaultResourceComponent defaultResourceComponent;

    /**
     * @throws ResourceComponentException 
     */
    @Test
    public void testConfiguration()  {
        assertNotNull(defaultResourceComponent);
        assertNotNull(defaultResourceComponent.getWorkspaceManager());
    }

}
