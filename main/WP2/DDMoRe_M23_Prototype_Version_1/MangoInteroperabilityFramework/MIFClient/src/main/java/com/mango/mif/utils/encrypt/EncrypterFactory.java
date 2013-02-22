/* File			:  EncrypterFactory.java
 * Project		:  MIFClient
 * Created on	:  28 Jun 2012
 */
package com.mango.mif.utils.encrypt;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;

import com.mango.mif.client.api.CommonProperties;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 * A factory returning the encrypter instance
 */
public class EncrypterFactory {
	/**
	 * LOG
	 */
	private final static Logger LOG = Logger.getLogger(EncrypterFactory.class);
	/**
	 * shared instance
	 */
	private static Encrypter encrypter;
	/**
	 * Encrypter used 
	 * @return
	 */
	public synchronized static Encrypter getEncrypter() {
		if(encrypter==null) {
			URL keyUrl = loadKeyFromExternalLocation(System.getProperty(CommonProperties.ENCRYPTION_KEY_FILE));
			if(keyUrl==null) {
				keyUrl = EncrypterFactory.class.getResource("defaultDesKey.key");
			}
			encrypter = new DesEncrypter(keyUrl);
		}
		return encrypter;
	}
	/**
	 * 
	 * @return
	 */
	static URL loadKeyFromExternalLocation(String keyFileLocation) {
		LOG.info("Loading encryption key from:" + keyFileLocation);
		if(keyFileLocation==null) {
			LOG.warn("Encryption key file not specified, using default.");
		} else {
			File encFile = new File(keyFileLocation);
			if(encFile.exists()) {
				try {
					return encFile.toURI().toURL();
				} catch (MalformedURLException e) {
					LOG.warn("Error when loading custom key, a default will be used.", e);
				}
			} else {
				LOG.warn("Specified encryption key file " + encFile.getAbsolutePath() + " does not exist. Using default key.");
			}
		}
		return null;
	}
}
