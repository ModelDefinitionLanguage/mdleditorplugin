/* File			:  DesEncrypterCommand.java
 * Project		:  MIFClient
 * Created on	:  Oct 9, 2012
 */
package com.mango.mif.utils.encrypt;

import java.io.OutputStream;

/**
 * A  DesEncrypter command line command
 * 
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public interface DesEncrypterCommand {
    /**
     * executes command
     * @param args
     */
    void execute(String[] args);
    /**
     * Prints usage info
     * @param outputStream 
     */
    void printUsageInfo(OutputStream outputStream);
}
