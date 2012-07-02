package com.mango.mif.utils.encrypt;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

import java.io.File;
import java.net.MalformedURLException;

import javax.crypto.SecretKey;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mango.mif.connector.TestsHelper;

/**
 * Tests DesEncrypter
 * @author mrogalski
 *
 */
public class DesEncrypterTest {
	/**
	 * Tested instance
	 */
	private DesEncrypter encrypter;
	/**
	 * Key file
	 */
	private File keyFile;
	
	@Before
	public void setUp() {
		encrypter = new DesEncrypter(DesEncrypterTest.class.getResource("testDesKey.key"));
		keyFile = new File(TestsHelper.createTmpDirectory(),"generatedTestKey.key");
	}
	
	@After
	public void tearDown() {
		FileUtils.deleteQuietly(keyFile);
	}
	
	@Test
	public void shouldEncryptAndDecryptAString() {
		String valueToBeEncrypted = "testststststststs";
		String encrypt = encrypter.encrypt(valueToBeEncrypted);
		String decryptedValue = encrypter.decrypt(encrypt);
		assertThat("Message is null", encrypt, is(not(equalTo(valueToBeEncrypted))));
		assertThat("Message is null", decryptedValue, is(equalTo(valueToBeEncrypted)));
	}
	
	@Test
	public void shouldGenerateAKeyForGivenPassPhrase() {
		SecretKey key = DesEncrypter.generateKey("testPhrase");
		
		assertNotNull(key);
	}

	@Test
	public void shouldCreateAFileContainingGeneratedKey() {
		DesEncrypter.main(new String[] {"shouldCreateAFileContainingGeneratedKey", keyFile.getAbsolutePath()});
		assertTrue(keyFile.exists());
	}

	@Test
	public void shouldCreateAFileContainingGeneratedKeyAndUseItForEncryptionDecryption() throws MalformedURLException {
		DesEncrypter.main(new String[] {"shouldCreateAFileContainingGeneratedKeyAndUseItForEncryptionDecryption", keyFile.getAbsolutePath()});
		DesEncrypter tmpEncrypter = new DesEncrypter(keyFile.toURI().toURL());

		String valueToBeEncrypted = "testststststststs";
		String encrypt = tmpEncrypter.encrypt(valueToBeEncrypted);
		String decryptedValue = tmpEncrypter.decrypt(encrypt);
		assertThat("Message is null", encrypt, is(not(equalTo(valueToBeEncrypted))));
		assertThat("Message is null", decryptedValue, is(equalTo(valueToBeEncrypted)));
		
	}
}
