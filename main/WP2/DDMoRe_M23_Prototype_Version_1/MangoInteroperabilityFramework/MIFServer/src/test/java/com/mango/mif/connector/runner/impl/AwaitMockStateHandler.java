/* File			:  AwaitMockStateHandler.java
 * Project		:  MIFServer
 * Created on	:  25 Jun 2012
 */
package com.mango.mif.connector.runner.impl;

import java.util.concurrent.CountDownLatch;

/**
 * 
 * A state handler that expects to be notified by an external thread when in doProcessing method.
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 *
 */
public class AwaitMockStateHandler extends AbstractStateHandler {
    /**
     * lock
     */
    private CountDownLatch awaitSignal = new CountDownLatch(1);
    /**
     * processing done
     */
    private volatile boolean done = false;
	/**
	 * @param state
	 * @param exitEvent
	 */
	public AwaitMockStateHandler(String state, String exitEvent) {
		super(state, exitEvent);
	}

	/* (non-Javadoc)
	 * @see com.mango.mif.connector.runner.impl.AbstractStateHandler#doProcessing()
	 */
	@Override
	protected void doProcessing() throws StateHandlerException {
    	try {
			awaitSignal.await();
		} catch (InterruptedException e) {
			LOG.error(e);
			throw new StateHandlerException("Error when waiting for signal from external process.",e);
		} finally {
			done = true;
		}
	}
	
	/**
	 * @return the awaitSignal
	 */
	public CountDownLatch getAwaitSignal() {
		return awaitSignal;
	}
	
	/**
	 * @return the done
	 */
	public boolean isDone() {
		return done;
	}

    @Override
    protected void doPreprocessing() throws StateHandlerException {
    }

    @Override
    protected void doPostProcessing() throws StateHandlerException {
    }
	
}
