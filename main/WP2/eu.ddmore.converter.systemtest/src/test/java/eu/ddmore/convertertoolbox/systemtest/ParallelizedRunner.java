/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.systemtest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.runners.Parameterized;
import org.junit.runners.model.RunnerScheduler;


/**
 * A JUnit Runner which performs {@link Parameterized} tests run in parallel.
 */
public class ParallelizedRunner extends Parameterized {
        private static class RunnerSchedulerWithThreadPool implements RunnerScheduler
        {
            private ExecutorService executor; 
            private long shutdownTimeout;
            
            public RunnerSchedulerWithThreadPool(int numThreads, long shutdownTimeout)
            {
                executor = Executors.newFixedThreadPool(numThreads);
                this.shutdownTimeout = shutdownTimeout;
            }
            
            @Override
            public void finished()
            {
                executor.shutdown();
                try
                {
                    executor.awaitTermination(shutdownTimeout, TimeUnit.MILLISECONDS);
                }
                catch (InterruptedException exc)
                {
                    throw new RuntimeException(exc);
                }
            }

            @Override
            public void schedule(Runnable childStatement)
            {
                executor.submit(childStatement);
            }
        }

        public ParallelizedRunner(Class<?> klass) throws Throwable
        {
            super(klass);
            int numThreads = 4;
            long shutdownTimeout = TimeUnit.MINUTES.toMillis(10);
            if(klass.isAnnotationPresent(Workers.class)) {
                Workers annotation = klass.getAnnotation(Workers.class);
                numThreads = annotation.value();
            }
            setScheduler(new RunnerSchedulerWithThreadPool(numThreads, shutdownTimeout));
        }
}
