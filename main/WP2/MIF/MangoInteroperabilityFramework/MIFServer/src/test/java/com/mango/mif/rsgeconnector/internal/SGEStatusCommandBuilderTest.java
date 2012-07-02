/* File			:  GridStatusCommandBuilderTest.java
 * Project		:  MIFServer
 * Created on	:  May 10, 2012
 */
package com.mango.mif.rsgeconnector.internal;

import static org.junit.Assert.*;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.mango.mif.rsgeconnector.internal.SGEStatusCommandBuilder;

/**
 * 
 * Tests Grid Status Command Builder
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class SGEStatusCommandBuilderTest {

	@Test(expected = NullPointerException.class)
	public void shouldThrowExceptionWhenJobIDisNotSet() {
		SGEStatusCommandBuilder builder = new SGEStatusCommandBuilder();
		
		builder.getCommand();
	}
	
	@Test
	public void shouldReturnNonEmptyString() {
		SGEStatusCommandBuilder builder = new SGEStatusCommandBuilder();
		builder.setJobID("1234");
		String command = builder.getCommand();
		
		assertNotNull(command);
		assertFalse(StringUtils.isEmpty(command));
	}
}
