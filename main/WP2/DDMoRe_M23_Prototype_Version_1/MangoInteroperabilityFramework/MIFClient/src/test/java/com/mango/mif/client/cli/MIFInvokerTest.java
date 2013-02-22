/* File			:  MIFInvokerTest.java
 * Project		:  MIFClient
 * Created on	:  29 May 2012
 */
package com.mango.mif.client.cli;

import java.io.File;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 *
 */
public class MIFInvokerTest {

    /**
     * Logger
     */
    public static final Logger LOG = Logger.getLogger(MIFInvokerTest.class);
	/**
	 * Example input files directory
	 */
	private File exampleInputFilesDir;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		exampleInputFilesDir = FileUtils.toFile(MIFInvokerTest.class.getResource("example"));
		MockitoAnnotations.initMocks(this);
	}

	@Test(expected=NullPointerException.class)
	public void shouldThrowExceptionIfBrokerUrlPropertyNotSet() {
		String[] args = new String[] {exampleInputFilesDir.getAbsolutePath(),"connector-id"};
		Properties properties = new Properties();
		properties.setProperty(MIFInvoker.MIF_REST_SERVICE_URL, "empty");
		properties.setProperty(MIFInvoker.SHARED_FILES, "empty");
		properties.setProperty(MIFInvoker.MIF_CLIENT_USER_NAME, "empty");
		properties.setProperty(MIFInvoker.MIF_CLIENT_USER_PASSWORD, "empty");
		MIFInvoker.checkArguments(args,properties);
	
	}

	@Test(expected=NullPointerException.class)
	public void shouldThrowExceptionIfRESTServiceUrlPropertyNotSet() {
		String[] args = new String[] {exampleInputFilesDir.getAbsolutePath(),"connector-id"};
		Properties properties = new Properties();
		properties.setProperty(MIFInvoker.MIF_BROKER_URL, "empty");
		properties.setProperty(MIFInvoker.SHARED_FILES, "empty");
		properties.setProperty(MIFInvoker.MIF_CLIENT_USER_NAME, "empty");
		properties.setProperty(MIFInvoker.MIF_CLIENT_USER_PASSWORD, "empty");
		MIFInvoker.checkArguments(args,properties);
	
	}

	@Test(expected=NullPointerException.class)
	public void shouldThrowExceptionIfSharedLocationPropertyNotSet() {
		String[] args = new String[] {exampleInputFilesDir.getAbsolutePath(),"connector-id"};
		Properties properties = new Properties();
		properties.setProperty(MIFInvoker.MIF_BROKER_URL, "empty");
		properties.setProperty(MIFInvoker.MIF_REST_SERVICE_URL, "empty");
		properties.setProperty(MIFInvoker.MIF_CLIENT_USER_NAME, "empty");
		properties.setProperty(MIFInvoker.MIF_CLIENT_USER_PASSWORD, "empty");
		MIFInvoker.checkArguments(args,properties);
	}

	@Test(expected=NullPointerException.class)
	public void shouldThrowExceptionIfUserNamePropertyNotSet() {
		String[] args = new String[] {exampleInputFilesDir.getAbsolutePath(),"connector-id"};
		Properties properties = new Properties();
		properties.setProperty(MIFInvoker.SHARED_FILES, "empty");
		properties.setProperty(MIFInvoker.MIF_BROKER_URL, "empty");
		properties.setProperty(MIFInvoker.MIF_REST_SERVICE_URL, "empty");
		properties.setProperty(MIFInvoker.MIF_CLIENT_USER_PASSWORD, "empty");
		MIFInvoker.checkArguments(args,properties);
	}

	@Test(expected=NullPointerException.class)
	public void shouldThrowExceptionIfUserPasswordPropertyNotSet() {
		String[] args = new String[] {exampleInputFilesDir.getAbsolutePath(),"connector-id"};
		Properties properties = new Properties();
		properties.setProperty(MIFInvoker.SHARED_FILES, "empty");
		properties.setProperty(MIFInvoker.MIF_BROKER_URL, "empty");
		properties.setProperty(MIFInvoker.MIF_REST_SERVICE_URL, "empty");
		properties.setProperty(MIFInvoker.MIF_CLIENT_USER_NAME, "empty");
		MIFInvoker.checkArguments(args,properties);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowExceptionIfNotDirectoryPassedNotSet() {
		String[] args = new String[] {new File(exampleInputFilesDir,"example.R").getAbsolutePath(),"connector-id"};
		Properties properties = new Properties();
		properties.setProperty(MIFInvoker.MIF_BROKER_URL, "empty");
		properties.setProperty(MIFInvoker.MIF_REST_SERVICE_URL, "empty");
		properties.setProperty(MIFInvoker.SHARED_FILES, "empty");
		properties.setProperty(MIFInvoker.MIF_CLIENT_USER_NAME, "empty");
		properties.setProperty(MIFInvoker.MIF_CLIENT_USER_PASSWORD, "empty");
		MIFInvoker.checkArguments(args,properties);
	}

	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowExceptionIfNotEnoughArguments() {
		String[] args = new String[] {new File(exampleInputFilesDir,"example.R").getAbsolutePath()};
		Properties properties = new Properties();
		properties.setProperty(MIFInvoker.MIF_BROKER_URL, "empty");
		properties.setProperty(MIFInvoker.MIF_REST_SERVICE_URL, "empty");
		properties.setProperty(MIFInvoker.SHARED_FILES, "empty");
		properties.setProperty(MIFInvoker.MIF_CLIENT_USER_NAME, "empty");
		properties.setProperty(MIFInvoker.MIF_CLIENT_USER_PASSWORD, "empty");
		MIFInvoker.checkArguments(args,properties);
	}

	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowExceptionIfDirectoryDoesNotExist() {
		String[] args = new String[] {new File(exampleInputFilesDir,"doesNotExist").getAbsolutePath()};
		Properties properties = new Properties();
		properties.setProperty(MIFInvoker.MIF_BROKER_URL, "empty");
		properties.setProperty(MIFInvoker.MIF_REST_SERVICE_URL, "empty");
		properties.setProperty(MIFInvoker.SHARED_FILES, "empty");
		properties.setProperty(MIFInvoker.MIF_CLIENT_USER_NAME, "empty");
		properties.setProperty(MIFInvoker.MIF_CLIENT_USER_PASSWORD, "empty");
		MIFInvoker.checkArguments(args,properties);
	}

}
