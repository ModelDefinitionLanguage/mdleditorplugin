package com.mango.mif.utils.encrypt;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.google.common.base.Preconditions;

/**
 * Simple DES encryption and decryption
 */
public class DesEncrypter implements Encrypter {
    /**
     * Logger
     */
    private static final Logger LOG = Logger.getLogger(DesEncrypter.class);
    /**
     * file containing the key
     */
    private final URL keyFile;
    /**
     * Loaded key
     */
    private static SecretKey    secretKey;
    /**
     * encryptor
     */
    private Cipher ecipher;
    /**
     * decryptor
     */
    private Cipher dcipher;

    /**
     * Constructor
     * @param keyFile a file containing the key
     */
    public DesEncrypter(URL keyFile) {
        Preconditions.checkNotNull(keyFile);
        this.keyFile = keyFile;
        try {
            secretKey = getKeyFromFile();
            ecipher = Cipher.getInstance("DES");
            dcipher = Cipher.getInstance("DES");
            ecipher.init(Cipher.ENCRYPT_MODE, secretKey);
            dcipher.init(Cipher.DECRYPT_MODE, secretKey);
        } catch (Exception e) {
            LOG.error("Cannot initialize DesEncrypter", e);
            throw new RuntimeException("Cannot initialize DesEncrypter", e);
        }
    }
    /**
     * Loads the key from the specified location
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private SecretKey getKeyFromFile() throws IOException, ClassNotFoundException {
        SecretKey key = null;
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(keyFile.openStream());
            key = (SecretKey) ois.readObject();
        } finally {
            IOUtils.closeQuietly(ois);
        }
        return key;
    }

    @Override
    public synchronized String encrypt(String message) {
        Preconditions.checkNotNull(message, "Can't encrypt null message");
        try {
            byte[] utf8 = message.getBytes("UTF8");
            byte[] enc = ecipher.doFinal(utf8);
            return Base64.encodeBase64URLSafeString(enc);
        } catch (Exception e) {
            LOG.error(e);
            throw new RuntimeException("Could not encrypt the message", e);
        }
    }

    @Override
    public synchronized String decrypt(String cryptogram) {
        Preconditions.checkNotNull(cryptogram, "Can't decrypt null message");
        try {
            byte[] dec = Base64.decodeBase64(cryptogram);
            byte[] utf8 = dcipher.doFinal(dec);
            return new String(utf8, "UTF8");
        } catch (Exception e) {
            LOG.error(e);
            throw new RuntimeException("Could not decrypt the message", e);
        }
    }

    
}