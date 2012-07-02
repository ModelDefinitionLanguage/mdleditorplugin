/* File			:  SGEStatusCommandBuilderSimpleTemplateTest.java
 * Project		:  MIFServer
 * Created on	:  1 Jun 2012
 */
package com.mango.mif.rsgeconnector.internal;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 * 
 * SGEStatusCommandBuilderSimpleTemplate unit test
 */
public class SGEStatusCommandBuilderSimpleTemplateTest {

	@Test(expected = NullPointerException.class)
	public void shouldThrowExceptionIfTemplateNotSet() {
		SGEStatusCommandBuilderSimpleTemplate builder = new SGEStatusCommandBuilderSimpleTemplate();
		builder.setJobID("1234");
		builder.setPollCommandTemplate(null);
		builder.getCommand();
	}

	@Test
	public void shouldBuildASGEStatusCommand() {
		SGEStatusCommandBuilderSimpleTemplate builder = new SGEStatusCommandBuilderSimpleTemplate();
		builder.setJobID("1234");
		builder.setPollCommandTemplate("That is a simple template " + SGEStatusCommandBuilderSimpleTemplate.COMMAND_TOKEN + " rest of the command");
		assertEquals("That is a simple template  -j 1234 rest of the command",builder.getCommand());
	}

}
