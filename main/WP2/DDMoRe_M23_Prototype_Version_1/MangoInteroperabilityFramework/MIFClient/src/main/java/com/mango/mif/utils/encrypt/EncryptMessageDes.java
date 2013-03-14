/* File			:  EncryptMessageDes.java
 * Project		:  MIFClient
 * Created on	:  Oct 9, 2012
 */
package com.mango.mif.utils.encrypt;

import java.io.File;
import java.io.OutputStream;

import org.apache.log4j.Logger;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.spi.StringOptionHandler;

import com.google.common.base.Preconditions;
import com.mango.mif.client.api.CommonProperties;

/**
 * 
 * Command encrypting a given message using DES encryption
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class EncryptMessageDes implements DesEncrypterCommand {

    private static final Logger logger = Logger.getLogger(EncryptMessageDes.class);
    
    private CmdLineParser parser = null;
    
    @Argument(index=0, required=true, metaVar="MESSAGE", handler=StringOptionHandler.class, usage = "Message to be encrypted")
    private String message;
    
    @Argument(index=1, metaVar="KEY_FILE", handler=StringOptionHandler.class, usage = "Key file name")
    private String keyFile;

    public EncryptMessageDes() {
        parser = new CmdLineParser(this);
    }
    
    @Override
    public void printUsageInfo(OutputStream outputStream) {
        parser.printUsage(outputStream);
    }
    
    @Override
    public void execute(String[] args) {
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            throw new RuntimeException(e.getMessage());
        }
        if(keyFile==null) {
            logger.warn("File containing DES key not found - using default key file.");
        } else {
            Preconditions.checkArgument(new File(keyFile).exists(), "Specified key file does not exist");
            System.setProperty(CommonProperties.ENCRYPTION_KEY_FILE, keyFile);
        }
        Encrypter desEncrypter = EncrypterFactory.getEncrypter();
        System.out.println("Encrypted message:");
        System.out.println(desEncrypter.encrypt(message));
        
    }

}
