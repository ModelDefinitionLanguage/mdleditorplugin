/**
 * Copyright Mango Solutions, 2012.
 * <p>SVN Entry: $HeadURL: $
 * <p>SVN ID: $Id: $
 * <p>Last edited by : $Author$
 * @author Tony Bamford &lt;tbamford@mango-solutions.com&gt;
 * @version $Revision$ as of on $Date$
 */
package com.mango.mif.core.jsch;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


/**
 * 
 * @author mrogalski
 *
 * Logger that is passed to jcraft jsch classes to route logging requests to 
 * MIF logging library
 */
public class JschLogger implements com.jcraft.jsch.Logger {
	/**
	 * Logger
	 */
    private final static Logger LOG = Logger.getLogger(JschLogger.class);

    @Override
    public boolean isEnabled(int level) {
    	Level log4jLevel = mapToLog4j(level);
    	return log4jLevel.isGreaterOrEqual(LOG.getEffectiveLevel());
    }

    @Override
    public void log(int level, String message) {
    	LOG.log(mapToLog4j(level), message);
    }
    /**
     * maps jcraft's level number to Log4j level
     * 
     * @param level
     * @return
     */
    Level mapToLog4j(int level) {
    	switch(level) {
	    	case DEBUG:
	    		return Level.DEBUG;
	    	case INFO:
	    		return Level.INFO;
	    	case WARN:
	    		return Level.WARN;
	    	case ERROR:
	    		return Level.ERROR;
	    	case FATAL:
	    		return Level.FATAL;
    	}
    	return Level.DEBUG; 
    }
}
