/* File			:  AbstractStateHandlerTest.java
 * Project		:  MIFServer
 * Created on	:  25 Jun 2012
 */
package com.mango.mif.connector.runner.impl;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import javax.jms.IllegalStateException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mango.mif.connector.runner.api.SCXMLDriver;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 * Tests abstract state handler
 */
public class AbstractStateHandlerTest {
	/**
	 * Instance under test
	 */
	private AbstractStateHandler stateHandler;
	/**
	 * Driver
	 */
	@Mock SCXMLDriver scxmlDriver;
	/**
	 * Mock state
	 */
	private String mockState = "state";
	/**
	 * mock event
	 */
	private String mockEvent = "event";
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void shouldInvokeFailIfDoProcessingThrowsException() {
		
		stateHandler = new AbstractStateHandler(mockState, mockEvent) {
			
			@Override
			protected void doProcessing() throws StateHandlerException {
				throw new StateHandlerException("State handler exception");
			}
		};
		stateHandler.setSCXMLDriver(scxmlDriver);
		
		
		stateHandler = spy(stateHandler);
		stateHandler.call();
		
		verify(stateHandler).fail();
	}

	@Test
	public void shouldInvokeFailIfDriverCompleteMethodThrowsException() throws IllegalStateException {
		stateHandler = spy(new AbstractStateHandler(mockState, mockEvent) {

			@Override
			protected void doProcessing() throws StateHandlerException {
				//do nothing
			}
		});
		
		doThrow(new IllegalStateException("message")).when(scxmlDriver).complete(stateHandler);
		stateHandler.setSCXMLDriver(scxmlDriver);
		stateHandler.call();
		
		verify(stateHandler).fail();
	}

	@Test
	public void shouldInvokeDriverFailedMethodWhenFailedAndNotInCancelledState() {
		stateHandler = spy(new AbstractStateHandler(mockState, mockEvent) {
			
			@Override
			protected void doProcessing() throws StateHandlerException {
				//do nothing
			}
		});
		when(scxmlDriver.getProcessingStatus()).thenReturn(DriverProcessingStatus.FAILED);
		
		stateHandler.setSCXMLDriver(scxmlDriver);
		stateHandler.fail();
		
		verify(scxmlDriver).failed(stateHandler);
	}
	
	@Test
	public void shouldNotInvokeDriverFailedMethodWhenCancelledState() {
		stateHandler = spy(new AbstractStateHandler(mockState, mockEvent) {

			
			@Override
			protected void doProcessing() throws StateHandlerException {
				//do nothing
			}
		});
		when(scxmlDriver.getProcessingStatus()).thenReturn(DriverProcessingStatus.CANCELLED);
		stateHandler.setSCXMLDriver(scxmlDriver);
		stateHandler.fail();
		
		verify(scxmlDriver, times(0)).failed(stateHandler);
	}
}
