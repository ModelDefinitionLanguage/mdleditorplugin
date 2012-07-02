/* File			:  SGEAccountCommandBuilderSimpleTemplateTest.java
 * Project		:  MIFServer
 * Created on	:  1 Jun 2012
 */
package com.mango.mif.rsgeconnector.internal;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 * unit test of SGEAccountCommandBuilderSimpleTemplate
 */
public class SGEAccountCommandBuilderSimpleTemplateTest {
	/**
	 * Logger
	 */
	private final static Logger LOG = Logger.getLogger(SGEAccountCommandBuilderSimpleTemplateTest.class);
			
	@Test(expected = NullPointerException.class)
	public void shouldThrowExceptionIfTemplateIsNotSet() {
		SGEAccountCommandBuilderSimpleTemplate builder = new SGEAccountCommandBuilderSimpleTemplate();
		builder.setJobID("1234");
		builder.setAccountCommandTemplate(null);
		builder.getCommand();
	}
	
	@Test
	public void shouldBuildASGEAccountCommand() {
		SGEAccountCommandBuilderSimpleTemplate builder = new SGEAccountCommandBuilderSimpleTemplate();
		builder.setJobID("1234");
		builder.setAccountCommandTemplate("That is a simple template " + SGEAccountCommandBuilderSimpleTemplate.JOB_ID_TOKEN + " rest of the command");
		LOG.info(builder.getCommand());
		assertEquals("That is a simple template 1234 rest of the command",builder.getCommand());
	
	}
}
