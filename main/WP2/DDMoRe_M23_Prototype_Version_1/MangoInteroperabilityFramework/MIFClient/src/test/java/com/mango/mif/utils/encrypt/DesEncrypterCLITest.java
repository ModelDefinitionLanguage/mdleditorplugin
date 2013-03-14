/* File			:  DesEncrypterCLI.java
 * Project		:  MIFClient
 * Created on	:  Oct 10, 2012
 */
package com.mango.mif.utils.encrypt;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

import java.io.File;
import java.net.MalformedURLException;

import org.junit.Before;
import org.junit.Test;

import com.mango.mif.connector.TestsHelper;


/**
 * 
 * Test the DES Encrypter CLI
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class DesEncrypterCLITest {

    /**
     * Key file
     */
    private File keyFile;

    @Before
    public void setUp() {
        keyFile = new File(TestsHelper.createTmpDirectory(),"generatedTestKey.key");
    }
    @Test
    public void shouldCreateAFileContainingGeneratedKey() {
        DesEncrypterCLI.main(new String[] {DesEncrypterCLI.Command.generateKeyFile.name(),"shouldCreateAFileContainingGeneratedKey", keyFile.getAbsolutePath()});
        assertTrue(keyFile.exists());
    }

    @Test
    public void shouldCreateAFileContainingGeneratedKeyAndUseItForEncryptionDecryption() throws MalformedURLException {
        DesEncrypterCLI.main(new String[] {DesEncrypterCLI.Command.generateKeyFile.name(),"shouldCreateAFileContainingGeneratedKeyAndUseItForEncryptionDecryption", keyFile.getAbsolutePath()});
        DesEncrypter tmpEncrypter = new DesEncrypter(keyFile.toURI().toURL());

        String valueToBeEncrypted = "testststststststs";
        String encrypt = tmpEncrypter.encrypt(valueToBeEncrypted);
        String decryptedValue = tmpEncrypter.decrypt(encrypt);
        assertThat("Message is null", encrypt, is(not(equalTo(valueToBeEncrypted))));
        assertThat("Message is null", decryptedValue, is(equalTo(valueToBeEncrypted)));

    }
    

    @Test
    public void shouldEncryptAMessageUsingDefaultKey() {
        DesEncrypterCLI.main(new String[] {DesEncrypterCLI.Command.encryptMessage.name(),"Message encrypted using default key"});
    }

    @Test
    public void shouldEncryptAMessageUsingNewlyGenerateKey() {
        DesEncrypterCLI.main(new String[] {DesEncrypterCLI.Command.generateKeyFile.name(),"shouldEncryptAMessageUsingNewlyGenerateKey", keyFile.getAbsolutePath()});
        assertTrue(keyFile.exists());
        
        DesEncrypterCLI.main(new String[] {DesEncrypterCLI.Command.encryptMessage.name(),"My message to be encrypted", keyFile.getAbsolutePath()});
        
    }

}
