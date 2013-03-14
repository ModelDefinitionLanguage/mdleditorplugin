/* File			:  BasicResourcePublisherTest.java
 * Project		:  MIFClient
 * Created on	:  11 Jun 2012
 */
package com.mango.mif.core.resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

import com.mango.mif.core.resource.BaseResourcePublisher;


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
public class BaseResourcePublisherTest {
	/**
	 * instance being tested
	 */
	ResourcePublisher resourcePublisher;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		resourcePublisher = mock(BaseResourcePublisher.class,Mockito.CALLS_REAL_METHODS);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = NullPointerException.class)
	public void shouldThrowExceptionIfRequestDirectoryIfPublishNotInvokedFirst() {
		resourcePublisher.getRequestID();
	}

}
