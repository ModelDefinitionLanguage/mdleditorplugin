/* File			:  SGESubmitCommandBuilderSimpleTemplateTest.java
 * Project		:  MIFServer
 * Created on	:  1 Jun 2012
 */
package com.mango.mif.rsgeconnector.internal;

import static org.junit.Assert.*;

import java.io.File;

import org.apache.commons.io.FileUtils;
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
 * unit test of the SGESubmitCommandBuilderSimpleTemplate 
 */
public class SGESubmitCommandBuilderSimpleTemplateTest {
	/**
	 * Logger
	 */
	private final static Logger LOG = Logger.getLogger(SGESubmitCommandBuilderSimpleTemplateTest.class);
	@Test(expected = NullPointerException.class)
	public void shouldThrowExceptionIfTemplateNotSet() {
		SGESubmitCommandBuilderSimpleTemplate builder = new SGESubmitCommandBuilderSimpleTemplate();
		builder.setJobName("TestJob");
		builder.setQueue("TestQueue");
		builder.setUser("TestUser");
		builder.setCommandFile(FileUtils.toFile(this.getClass().getResource("script.sh")));
		builder.setSubmitCommandTemplate(null);
		builder.getCommand();
	}

	@Test
	public void shouldBuildASGESubmitCommand() {
		SGESubmitCommandBuilderSimpleTemplate builder = new SGESubmitCommandBuilderSimpleTemplate();
		builder.setJobName("TestJob");
		builder.setQueue("TestQueue");
		builder.setUser("TestUser");
		File scriptFile = FileUtils.toFile(this.getClass().getResource("script.sh"));
		builder.setCommandFile(scriptFile);
		builder.setSubmitCommandTemplate("That is a simple template " + SGESubmitCommandBuilderSimpleTemplate.COMMAND_TOKEN + " rest of the command");
		LOG.info(builder.getCommand());
		assertEquals("That is a simple template  -u TestUser -cwd  rest of the command",builder.getCommand().replace(scriptFile.getAbsolutePath(),""));
	}

	@Test
	public void shouldBuildASGESubmitCommandWithProlog() {
		SGESubmitCommandBuilderSimpleTemplate builder = new SGESubmitCommandBuilderSimpleTemplate();
		builder.setJobName("TestJob");
		builder.setQueue("TestQueue");
		builder.setUser("TestUser");
		builder.setProlog("this is prolog");
		File scriptFile = FileUtils.toFile(this.getClass().getResource("script.sh"));
		builder.setCommandFile(scriptFile);
		builder.setSubmitCommandTemplate("That is a simple template " + SGESubmitCommandBuilderSimpleTemplate.COMMAND_TOKEN + ":" + SGESubmitCommandBuilderSimpleTemplate.PROLOG_TOKEN + " rest of the command");
		LOG.info(builder.getCommand());
		assertEquals("That is a simple template  -u TestUser -cwd :this is prolog rest of the command",builder.getCommand().replace(scriptFile.getAbsolutePath(),""));
	}
}
