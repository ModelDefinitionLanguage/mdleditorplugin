package com.mango.mif.utils;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.RollingFileAppender;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.google.common.base.Stopwatch;
import com.mango.mif.connector.Connector;

/**
 * MIFApplicationContextListener provides a hook specifically for post Spring context startup.
 */
public class MIFApplicationContextListener implements ApplicationListener<ContextRefreshedEvent> {
    
    private static final Logger LOG = Logger.getLogger(MIFApplicationContextListener.class);
    
    @Override
    public void onApplicationEvent(ContextRefreshedEvent cre) {

        LOG.info("MIFApplicationContextListener: handling post Spring start events");
        
        displayLogFileLocation();
        
        doFailureRecovery(cre);
    }

    private void doFailureRecovery(ContextRefreshedEvent cre) {
        
        LOG.info("\n\n\n - MIF Failure Recovery -\n\n");
        
        ApplicationContext ac = cre.getApplicationContext();
        String[] names = ac.getBeanNamesForType(Connector.class);
        
        Stopwatch watch = new Stopwatch();
        watch.start();
        for (String name: names) {
            
            if (notAbstractConnector(name)) {
                Connector conn = ((Connector)ac.getBean(name));
                conn.doFailureRecovery();
            }
        }
        long duration = watch.elapsedMillis();
        LOG.debug("\n\nFailure recovery took " + duration + " ms / " + TimeUnit.MILLISECONDS.toSeconds(duration) + " secs\n\n");
        watch.stop();
    }

    /**
     * Display the log4j log file location, this must match the appender in MIF's internal log4j.properties.
     */
    private void displayLogFileLocation() {
        
        Logger rootLogger = Logger.getRootLogger();
        RollingFileAppender rfa = (RollingFileAppender) rootLogger.getAppender("file");
        if (rfa != null) {
            LOG.warn("Location of MIF log file: " + rfa.getFile());
        }
    }

    private boolean notAbstractConnector(String name) {
        return !name.equals("sgeConnector");
    }
}
