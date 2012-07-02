/* File			:  FreemarkerTemplateCommandBuilder.java
 * Project		:  MIFServer
 * Created on	:  13 Jun 2012
 */
package com.mango.mif.rsgeconnector.internal;


import org.apache.log4j.Logger;

import com.google.common.base.Preconditions;
import com.mango.mif.connector.dispatcher.domain.Job;
import com.mango.mif.core.jsch.JschTemplateLocator;
import com.mango.mif.core.jsch.JschTemplatedCommand;
import com.mango.mif.exception.MIFException;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * 
 * A command builder that uses Freemarker to build up commands
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 *
 */
public class FreemarkerTemplateCommandBuilder implements CommandBuilder {
	/**
	 * Log
	 */
	private final static Logger LOG = Logger.getLogger(FreemarkerTemplateCommandBuilder.class);
	/**
	 * Command template
	 */
	private String template;
	/**
	 * Command template locator
	 */
	private JschTemplateLocator templateLocator;
	/**
	 * A job
	 */
	private Job job;
	/**
	 * Job parameter key
	 */
	private String jobVariableName = "job";
	
	@Override
	public String getCommand() throws MIFException {
		Preconditions.checkNotNull(template, "Command template is not set.");
		Preconditions.checkNotNull(templateLocator, "Template locator is not set.");
		Preconditions.checkNotNull(job, "Is not set.");
		JschTemplatedCommand templatedCommand = new JschTemplatedCommand(template, templateLocator);

		BeansWrapper beansWrapper = BeansWrapper.getDefaultInstance();
		beansWrapper.setExposureLevel(BeansWrapper.EXPOSE_SAFE);
		TemplateModel model;
		try {
			model = beansWrapper.wrap(job);
		} catch (TemplateModelException e) {
			LOG.error(e);
			throw new MIFException("Could not pass job to a templating engine,", e);
		}
		templatedCommand.addParam(jobVariableName, model);
        return templatedCommand.resolveCommand();
	}
	/**
	 * @param template the template to set
	 */
	public void setTemplate(String template) {
		this.template = template;
	}
	
	/**
	 * @param templateLocator the templateLocator to set
	 */
	public void setTemplateLocator(JschTemplateLocator templateLocator) {
		this.templateLocator = templateLocator;
	}
	
	@Override
	public void setJob(Job job) {
		this.job = job;
	} 
	
	public void setTemplateVariableNameOfJob(String jobVariableName) {
		this.jobVariableName = jobVariableName;
	}
}
