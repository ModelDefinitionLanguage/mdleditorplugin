/* File			:  JschLoggerTest.java
 * Project		:  MIFClient
 * Created on	:  27 Jun 2012
 */
package com.mango.mif.core.jsch;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.log4j.Level;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Maps;
import com.mango.mif.core.jsch.JschLogger;


/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 * A test proving that Jsch correctly resolves jcraft's log levels to Log4j
 */
public class JschLoggerTest {
	/**
	 * Tested instance
	 */
	private JschLogger logger;
	
	@Before
	public void setUp() {
		logger = new JschLogger();
	}
	
	@Test
	public void shouldMapJcraftsLevelsToLog4jLevels() {
		HashMap<Integer,Level> testData = Maps.newHashMap();
		testData.put(JschLogger.DEBUG, Level.DEBUG);
		testData.put(JschLogger.INFO, Level.INFO);
		testData.put(JschLogger.WARN, Level.WARN);
		testData.put(JschLogger.ERROR, Level.ERROR);
		testData.put(JschLogger.FATAL, Level.FATAL);
		
		for(Entry<Integer,Level> en : testData.entrySet()) {
			assertEquals(en.getValue(),logger.mapToLog4j(en.getKey()));
		}
	}

	@Test
	public void shouldReturnCorrectInformationOnLevelEnablement() {
		assertTrue(Level.INFO.isGreaterOrEqual(Level.DEBUG));
		assertTrue(Level.WARN.isGreaterOrEqual(Level.INFO));
		assertTrue(Level.ERROR.isGreaterOrEqual(Level.WARN));
		assertTrue(Level.FATAL.isGreaterOrEqual(Level.ERROR));
	}

	@Test
	public void theInfoLevelShouldBeEnabled() {
		assertTrue(logger.isEnabled(JschLogger.WARN));
	}
}
