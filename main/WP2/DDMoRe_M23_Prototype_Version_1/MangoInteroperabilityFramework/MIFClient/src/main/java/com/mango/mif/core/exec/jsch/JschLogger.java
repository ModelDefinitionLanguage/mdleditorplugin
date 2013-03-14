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


/**
 * 
 * @author mrogalski
 *
 * Logger that is passed to jcraft jsch classes to route logging requests to 
 * MIF logging library
 */
public class JschLogger implements org.vngx.jsch.util.Logger {
	/**
	 * Logger
	 */
    private final static Logger LOG = Logger.getLogger(JschLogger.class);

    @Override
    public boolean isEnabled(org.vngx.jsch.util.Logger.Level level) {
    	org.apache.log4j.Level log4jLevel = mapToLog4j(level);
    	return log4jLevel.isGreaterOrEqual(LOG.getEffectiveLevel());
    }

    @Override
    public void log(org.vngx.jsch.util.Logger.Level level, String message) {
    	LOG.log(mapToLog4j(level), message);
    }
    /**
     * maps jcraft's level number to Log4j level
     * 
     * @param level
     * @return
     */
    org.apache.log4j.Level mapToLog4j(org.vngx.jsch.util.Logger.Level level) {
    	switch(level) {
	    	case DEBUG:
	    		return org.apache.log4j.Level.DEBUG;
	    	case INFO:
	    		return org.apache.log4j.Level.INFO;
	    	case WARN:
	    		return org.apache.log4j.Level.WARN;
	    	case ERROR:
	    		return org.apache.log4j.Level.ERROR;
	    	case FATAL:
	    		return org.apache.log4j.Level.FATAL;
    	}
    	return org.apache.log4j.Level.DEBUG; 
    }

	@Override
	public void log(org.vngx.jsch.util.Logger.Level level, String message, Object... args) {
		LOG.log(mapToLog4j(level), message);
	}

	@Override
	public void log(org.vngx.jsch.util.Logger.Level level, String message, Throwable exception) {
		LOG.log(mapToLog4j(level), message, exception);
	}
}
