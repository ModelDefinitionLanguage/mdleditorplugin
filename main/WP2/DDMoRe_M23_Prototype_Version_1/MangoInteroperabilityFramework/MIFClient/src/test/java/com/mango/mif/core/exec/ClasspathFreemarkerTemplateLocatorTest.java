/* File			:  ClasspathFreemarkeTemplateLocator.java
 * Project		:  MIFServer
 * Created on	:  02 Jul 2012
 */
package com.mango.mif.core.exec;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mango.mif.core.exec.template.ClasspathFreemarkerTemplateLocator;


/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * Tests classpath based Freemarker template locator
 * @author mrogalski
 *
 */
public class ClasspathFreemarkerTemplateLocatorTest {
    /**
     * tested instance
     */
    ClasspathFreemarkerTemplateLocator instance;
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        instance = new ClasspathFreemarkerTemplateLocator();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionIfTheTemplateNameIsNull() throws Exception {
        instance.locate(null);
    }

    @Test(expected = ExecutionException.class)
    public void shouldThrowExceptionIfTemplateIsNotFound() throws Exception {
        instance.locate("/com/mango/mif/core/exec/DOES-NOT-EXIST.FTL");
    }

    @Test
    public void shouldReturnTemplateFileIfItExists() throws Exception {
        assertTrue(new File(instance.locate("/com/mango/mif/core/exec/TEST.FTL")).exists());
    }
    
    @Test
    public void shouldReturnTemplateFileIfItExistsAndTemplatesDirectoryIsSet() throws Exception {
    	instance.setCommandTemplateDirectory("/com/mango/mif/core/exec/");
        assertTrue(new File(instance.locate("TEST.FTL")).exists());
    }
}
