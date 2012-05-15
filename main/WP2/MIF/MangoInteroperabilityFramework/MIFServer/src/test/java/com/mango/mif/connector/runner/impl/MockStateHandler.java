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
    private int seconds = 1;
    
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
        for(int i=0;i<seconds;i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                LOG.error(e);
            }
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
     * Sets the number of seconds the mock processing should take
     * @param iterations
     */
    public void setNumberOfSeconds(int seconds) {
        this.seconds = seconds;
    }
    
    @Override
    public String complete() {
        done = true;
        return super.complete();
    }

    @Override
    public String failed() {
        throw new RuntimeException("Method not implemented!");
    }

}
