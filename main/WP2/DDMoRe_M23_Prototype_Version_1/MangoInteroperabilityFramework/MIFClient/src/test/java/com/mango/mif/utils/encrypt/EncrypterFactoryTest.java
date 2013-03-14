/* File			:  EncrypterFactoryTest.java
 * Project		:  MIFClient
 * Created on	:  28 Jun 2012
 */
package com.mango.mif.utils.encrypt;

import static org.junit.Assert.*;

import org.apache.commons.io.FileUtils;
import org.junit.Test;


/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 * 
 * Encrypter factory test
 *
 */
public class EncrypterFactoryTest {

	@Test
	public void shouldReturnUrlHoldingASpecifiedFile() {
		assertNotNull(EncrypterFactory.loadKeyFromExternalLocation(FileUtils.toFile(EncrypterFactory.class.getResource("defaultDesKey.key")).getAbsolutePath()));
	}

	@Test
	public void shouldCreateADefaultEncryptor() {
		assertNotNull(EncrypterFactory.getEncrypter());
	}
}
