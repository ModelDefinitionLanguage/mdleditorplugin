/* File			:  MockStateHandler.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  14 Mar 2012
 */
package com.mango.mif.connector.runner.impl;


/**
 * 
 * Mock state handler, mocks processing for the given number of seconds and then returns.
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 */
public class MockStateHandler extends AbstractStateHandler {
    /**
     * flag indicating that a state handler finished processing
     */
    private volatile boolean done = false;
    /**
     * number of seconds that mock processing should take
     */
    private int time = 1000;
    
    /**
     * Default constructor
     * @param state
     */
    public MockStateHandler(String state, String exitEvent) {
        super(state,exitEvent);
    }
    
    @Override
    protected void doProcessing() {
        String msg = "State " + handledState + " processing";
        LOG.info(msg + " - START");
        try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			LOG.error(e);
		}
        LOG.info(msg + " - END");
    }
    /**
     * 
     * @return true if done
     */
    public boolean isDone() {
        return done;
    }
    
    /**
     * Sets the number of milliseconds the mock processing should take
     * @param time
     */
    public void setTime(int time) {
        this.time = time;
    }
    
    @Override
    public String complete() {
        done = true;
        return super.complete();
    }
    
    public void setExitEvent(String exitEvent) {
    	this.exitEvent = exitEvent;
    }

    @Override
    protected void doPreprocessing() throws StateHandlerException {
    }

    @Override
    protected void doPostProcessing() throws StateHandlerException {
    }
}
