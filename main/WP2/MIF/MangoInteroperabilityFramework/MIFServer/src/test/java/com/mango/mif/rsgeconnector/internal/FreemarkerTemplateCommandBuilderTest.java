/* File			:  FreemarkerTemplateCommandBuilderTest.java
 * Project		:  MIFServer
 * Created on	:  13 Jun 2012
 */
package com.mango.mif.rsgeconnector.internal;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URL;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.mango.mif.connector.TestsHelper;
import com.mango.mif.connector.dispatcher.domain.Job;
import com.mango.mif.exception.MIFException;
import com.mango.mif.jschconnector.internal.JSCHTemplateLocator;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;


/**
 * 
 * Tests template command builder
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 *
 */
public class FreemarkerTemplateCommandBuilderTest {
	/**
	 * Log
	 */
    final static Logger LOG = Logger.getLogger(FreemarkerTemplateCommandBuilderTest.class);

    File testCommandTemplateDirectory;
    
    JSCHTemplateLocator templateLocator;
    /**
     * request id
     */
    UUID uuid;
    @Before
    public void setUp() {
        URL url = FreemarkerTemplateCommandBuilderTest.class.getResource("TEST-COMMAND.FTL");
        File foo = FileUtils.toFile(url);
        testCommandTemplateDirectory = foo.getParentFile();
        templateLocator = new JSCHTemplateLocator();
        templateLocator.setCommandTemplateDirectory(testCommandTemplateDirectory);
        uuid = UUID.randomUUID();
    }

	@Test
	public void shouldCreateABeanWrapperOnAJob() throws MIFException, TemplateModelException {
		BeansWrapper beansWrapper = BeansWrapper.getDefaultInstance();
		beansWrapper.setExposureLevel(BeansWrapper.EXPOSE_SAFE);
		TemplateModel model = beansWrapper.wrap(createJob());
		assertNotNull(model);
	}
    
	@Test
	public void shouldGenerateNonEmptyCommandFromTemplate() throws MIFException {
		FreemarkerTemplateCommandBuilder instance = new FreemarkerTemplateCommandBuilder();
		instance.setTemplateLocator(templateLocator);
		instance.setTemplate("TEST-JOB-PROPERTY-COMMAND.FTL");
		instance.setJob(createJob());
		String result = instance.getCommand();
		LOG.info(result);
		assertNotNull(result);
	}

	@Test(expected = NullPointerException.class)
	public void shouldThrowExceptionIfJobNotSet() throws MIFException {
		FreemarkerTemplateCommandBuilder instance = new FreemarkerTemplateCommandBuilder();
		instance.setTemplateLocator(templateLocator);
		instance.setTemplate("TEST-COMMAND.FTL");
		instance.getCommand();
	}
	
	@Test(expected = NullPointerException.class)
	public void shouldThrowExceptionIfTemplateLocatorNotSet() throws MIFException {
		FreemarkerTemplateCommandBuilder instance = new FreemarkerTemplateCommandBuilder();
		instance.setTemplate("TEST-COMMAND.FTL");
		instance.getCommand();
		instance.setJob(createJob());
	}

	@Test(expected = NullPointerException.class)
	public void shouldThrowExceptionIfTemplateNotSet() throws MIFException {
		FreemarkerTemplateCommandBuilder instance = new FreemarkerTemplateCommandBuilder();
		instance.setTemplateLocator(templateLocator);
		instance.getCommand();
		instance.setJob(createJob());
	}
	
	@Test(expected = MIFException.class)
	public void shouldThrowExceptionIfTemplateFileDoesNotExist() throws MIFException  {
		FreemarkerTemplateCommandBuilder instance = new FreemarkerTemplateCommandBuilder();
		instance.setTemplate("DOES-NOT-EXIST.FTL");
		instance.setTemplateLocator(templateLocator);
		instance.setJob(createJob());
		instance.getCommand();
	}
	
	private Job createJob() {
		Job job = TestsHelper.createJob(uuid.toString(), uuid);
		job.addToDataMap("TEST-PROPERTY", "TEST-VALUE");
		return job;
	}
}
