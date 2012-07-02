/* File			:  GridSubmitCommandBuilderTest.java
 * Project		:  MIFServer
 * Created on	:  May 10, 2012
 */
package com.mango.mif.rsgeconnector.internal;


import static org.junit.Assert.*;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.mango.mif.rsgeconnector.internal.SGESubmitCommandBuilder;

/**
 * 
 * Tests Grid Submit Command Builder
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class SGESubmitCommandBuilderTest {
	
	private final static Logger LOG = Logger.getLogger(SGESubmitCommandBuilderTest.class);
	
	@Test (expected=NullPointerException.class)
	public void shouldThrowExceptionIfQueueIsNotSet() {
		SGESubmitCommandBuilder builder = new SGESubmitCommandBuilder();
		builder.setJobName("TestJob");
		builder.setCommandFile(new File("testScript.sh"));
		builder.setUser("TestUser");
		builder.getCommand();
	}

	@Test (expected=NullPointerException.class)
	public void shouldThrowExceptionIfJobNameIsNotSet() {
		SGESubmitCommandBuilder builder = new SGESubmitCommandBuilder();
		builder.setQueue("TestQueue");
		builder.setCommandFile(new File("testScript.sh"));
		builder.setUser("TestUser");
		builder.getCommand();
	}

	@Test (expected=NullPointerException.class)
	public void shouldThrowExceptionIfCommandFileIsNotSet() {
		SGESubmitCommandBuilder builder = new SGESubmitCommandBuilder();
		builder.setJobName("TestJob");
		builder.setQueue("TestQueue");
		builder.setUser("TestUser");
		builder.getCommand();
	}

	@Test (expected=IllegalArgumentException.class)
	public void shouldThrowExceptionIfCommandFileDoesNotExist() {
		SGESubmitCommandBuilder builder = new SGESubmitCommandBuilder();
		builder.setJobName("TestJob");
		builder.setQueue("TestQueue");
		builder.setUser("TestUser");
		builder.setCommandFile(new File("testScript.sh"));
		builder.getCommand();
	}
	
	@Test (expected=NullPointerException.class)
	public void shouldThrowExceptionIfUserIsNotSet() {
		SGESubmitCommandBuilder builder = new SGESubmitCommandBuilder();
		builder.setJobName("TestJob");
		builder.setQueue("TestQueue");
		builder.setCommandFile(new File("testScript.sh"));
		builder.getCommand();
	}

	@Test 
	public void shouldProduceNotEmptyCommandString() {
		SGESubmitCommandBuilder builder = new SGESubmitCommandBuilder();
		builder.setJobName("TestJob");
		builder.setQueue("TestQueue");
		builder.setUser("TestUser");
		builder.setCommandFile(FileUtils.toFile(this.getClass().getResource("script.sh")));
		String command = builder.getCommand();
		LOG.info(command);
		assertNotNull(command);
		assertFalse(StringUtils.isEmpty(command));
	}
}
