/**
 * Copyright Mango Solutions, 2012.
 * <p>SVN Entry: $HeadURL: $
 * <p>SVN ID: $Id: $
 * <p>Last edited by : $Author$
 * @author Tony Bamford &lt;tbamford@mango-solutions.com&gt;
 * @version $Revision$ as of on $Date$
 */
package com.mango.mif.core.exec.jsch;

import org.apache.log4j.Logger;

import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.InvokerResult;
import com.mango.mif.core.exec.invoker.InvokerHelper;

/**
 * A runnable that executes a sleep command using the invoker helper and quits once processing returns from the shell
 * @author mrogalski
 *
 */
public class JschSessionPoolTester implements Runnable {
    private final static Logger logger = Logger.getLogger(JschSessionPoolTester.class);
    private int counter;
    private int time;
    private InvokerHelper invokerHelper;

    private volatile boolean done = false;
    /**
     * Constructor
     * @param invokerHelper
     * @param counter the tester's id
     * @param time the time that it should run the shell command
     */
    public JschSessionPoolTester(InvokerHelper invokerHelper, int counter, int time) {
        this.counter = counter;
        this.time = time;
        this.invokerHelper = invokerHelper;
    }

    public void run() {
        try {
            logger.debug("Hello from thread #" + counter);
            InvokerResult results = invokerHelper.run("sleep " + time + "; echo \"#" + counter + " $$ finished\"");
            if (InvokerHelper.success(results)) {
                logger.debug("Result from thread #" + counter + ": " + results.getStdout());
            } else {
                logger.debug("Thread #" + counter + " FAILED: " + InvokerHelper.errors(results));
            }
        } catch (ExecutionException ee) {
            logger.debug("Thread #" + counter + " caught exception " + ee);
        } finally {
            done = true;
        }
    }

    public boolean isDone() {
        return done;
    }
}
