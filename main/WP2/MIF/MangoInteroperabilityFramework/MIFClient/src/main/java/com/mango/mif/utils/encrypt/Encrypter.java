/* File			:  Encrypter.java
 * Project		:  MIFClient
 * Created on	:  28 Jun 2012
 */
package com.mango.mif.utils.encrypt;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 * interface implemented by different encrypting strategies 
 */
public interface Encrypter {

    /**
     * Encrypts string
     * @param str target string
     * @return
     */
    public String encrypt(String message);
    /**
     * Decrypts string
     * @param str target string
     * @return
     */
    public String decrypt(String message);
}
