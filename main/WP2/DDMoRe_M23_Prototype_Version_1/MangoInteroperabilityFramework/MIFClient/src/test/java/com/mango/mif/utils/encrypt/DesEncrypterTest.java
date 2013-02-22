package com.mango.mif.utils.encrypt;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.io.File;

import javax.crypto.SecretKey;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
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
	 * Logger
	 */
	private final static Logger LOG = Logger.getLogger(DesEncrypterTest.class);
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
        encrypter = new DesEncrypter(DesEncrypterTest.class.getResource("defaultDesKey.key"));
        keyFile = new File(TestsHelper.createTmpDirectory(),"generatedTestKey.key");
    }

    @After
    public void tearDown() {
        FileUtils.deleteQuietly(keyFile);
    }

    @Test
    public void shouldEncryptAndDecryptAString() {
        String valueToBeEncrypted = "tstsststss";
        String encrypt = encrypter.encrypt(valueToBeEncrypted);
        String decryptedValue = encrypter.decrypt(encrypt);
        LOG.debug("encrypt: " + encrypt);
        LOG.debug("decrypt: " + decryptedValue);
        assertThat("Message is null", encrypt, is(not(equalTo(valueToBeEncrypted))));
        assertThat("Message is null", decryptedValue, is(equalTo(valueToBeEncrypted)));
    }
    
    @Test
    public void shouldDecryptAPreviouslyEncryptedString() {
    	String expected = "tstsststss";
        String encrypt = encrypter.encrypt(expected);
        String decryptedValue = encrypter.decrypt(encrypt);
        LOG.debug("decrypt: " + decryptedValue);
        assertThat("Encrypted message different from expected", decryptedValue, is(equalTo(expected)));
    }
    
    @Test
    public void shouldGenerateAKeyForGivenPassPhrase() {
        SecretKey key = GenerateDesKeyFile.generateKey("testPhrase");

        assertNotNull(key);
    }

}
