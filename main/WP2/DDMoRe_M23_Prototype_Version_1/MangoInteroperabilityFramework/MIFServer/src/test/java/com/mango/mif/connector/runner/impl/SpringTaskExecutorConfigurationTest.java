/* File			:  SpringTaskExecutorConfigurationTest.java
 * Project		:  MIFServer
 * Created on	:  Oct 26, 2012
 */
package com.mango.mif.connector.runner.impl;

import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Lists;


/**
 * 
 * Tests TaskExecutor Spring configuration
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SpringTaskExecutorConfigurationTest {
    private final static Logger logger = Logger.getLogger(SpringTaskExecutorConfigurationTest.class);
    /**
     * the task executor being tested
     */
    @Autowired
    @Qualifier("newTaskExecutor")
    private ConcurrentTaskExecutor taskExecutor;
    /**
     * A task being used to block a thread in task executor
     * @author mrogalski
     *
     */
    private class BlockingTask implements Callable<String> {
        private String taskId;
        private boolean running = false;
        
        public BlockingTask(String taskId) {
            this.taskId = taskId;
        }
        
        @Override
        public String call() throws Exception {
            running = true;
            while(true) {
                logger.info("Task ID " + taskId + " running in thread " + Thread.currentThread().getName());
                Thread.sleep(TimeUnit.MILLISECONDS.convert(1, TimeUnit.SECONDS));
            }
        }
        
        public boolean isRunning() {
            return running;
        }
    }
    @DirtiesContext
    @Test
    public void shouldResultIn_3_running_jobs() throws InterruptedException {
        List<BlockingTask> tasks = Lists.newArrayList();
        for(int i=0;i<5;i++) {
            BlockingTask task = new BlockingTask("task "+ i);
            tasks.add(task);
            taskExecutor.submit(task);
        }
        assertEquals(3, ((ThreadPoolExecutor)taskExecutor.getConcurrentExecutor()).getPoolSize());
    }

    @DirtiesContext
    @Test
    public void shouldResultIn_5_running_jobs_and_25_on_the_queue() {
        List<BlockingTask> tasks = Lists.newArrayList();
        for(int i=0;i<30;i++) {
            BlockingTask task = new BlockingTask("task "+ i);
            tasks.add(task);
            taskExecutor.submit(task);
        }

        assertEquals(5, ((ThreadPoolExecutor)taskExecutor.getConcurrentExecutor()).getPoolSize());
        assertEquals(25, ((ThreadPoolExecutor)taskExecutor.getConcurrentExecutor()).getQueue().size());
    }

    @DirtiesContext
    @Test(expected=RejectedExecutionException.class)
    public void shouldThrowAnException() {
        List<BlockingTask> tasks = Lists.newArrayList();
        for(int i=0;i<35;i++) {
            BlockingTask task = new BlockingTask("task "+ i);
            tasks.add(task);
            taskExecutor.submit(task);
        }
        
        for(BlockingTask task : tasks) {
            assertTrue(task.isRunning());
        }
        assertEquals(5, ((ThreadPoolExecutor)taskExecutor.getConcurrentExecutor()).getPoolSize());
        assertEquals(25, ((ThreadPoolExecutor)taskExecutor.getConcurrentExecutor()).getQueue().size());
    }
}
