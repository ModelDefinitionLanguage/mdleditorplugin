package com.mango.mif.utils.encrypt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

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
	private static final Logger	LOG	= Logger.getLogger(DesEncrypter.class);
	/**
	 * file containing the key
	 */
	private final URL			keyFile;
	/**
	 * Loaded key
	 */
	private static SecretKey	secretKey;
	/**
	 * encryptor
	 */
	private Cipher				ecipher;
	/**
	 * decryptor
	 */
	private Cipher				dcipher;

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
	
	/**
	 * Used to generate a key file
	 * @param args
	 */
	public static void main(String[] args) {
		Preconditions.checkArgument(args.length>=2, "Not enough parameters");
		Preconditions.checkArgument(!new File(args[1]).exists(), "Specified key file already exists");
		
		String key = args[0];
		String keyFileName = args[1];
		SecretKey secretKey = generateKey(key);
		
		ObjectOutputStream oos = null;
		FileOutputStream fos = null;
		try {
			File keyFile = new File(keyFileName);
			fos = new FileOutputStream(keyFile);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(secretKey);
			System.out.println("Key saved to file " + keyFile);
		} catch (IOException e) {
			LOG.error(e);
			System.exit(-1);
		} finally {
			IOUtils.closeQuietly(fos);
			IOUtils.closeQuietly(oos);
		}
	}
	/**
	 * Generates SecretKey for the given pass phrase
	 * @param passPhrase
	 * @return
	 */
	static SecretKey generateKey(String passPhrase) {
		SecretKeyFactory keyFactory = null;
		try {
			keyFactory = SecretKeyFactory.getInstance("DES");
		} catch (NoSuchAlgorithmException e) {
			LOG.error(e);
			throw new RuntimeException("DES algorithm not availiable.",e);
		}
		
		byte[] in = null;
		try {
			in = passPhrase.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			LOG.error("Key phrase could not be converted to bytes", e);
			throw new RuntimeException("Key phrase could not be converted to bytes.",e);
		}

		SecretKey secretKey = null;
		try {
			secretKey = keyFactory.generateSecret(new DESKeySpec(in));
		} catch (Exception e) {
			LOG.error("Could not generate key from the given pass phrase",e);
			throw new RuntimeException("Could not generate key from the given pass phrase.",e);
		}
		return secretKey;
	}
}