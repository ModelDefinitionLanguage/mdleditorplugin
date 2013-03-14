/* File			:  ConfigCredentialsEncrypter.java
 * Project		:  MIFClient
 * Created on	:  17 Sep 2012
 */
package com.mango.mif.utils.encrypt;


/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class ConfigCredentialsEncrypter {

	private Encrypter encrypter = EncrypterFactory.getEncrypter();

	public String encryptString(String unencrypted) {
		return getEncrypter().encrypt(unencrypted);
	}
	
	private Encrypter getEncrypter() {
		return encrypter;
	} 
	
	public void setEncrypter(Encrypter encrypter) {
		this.encrypter = encrypter;
	}
}
