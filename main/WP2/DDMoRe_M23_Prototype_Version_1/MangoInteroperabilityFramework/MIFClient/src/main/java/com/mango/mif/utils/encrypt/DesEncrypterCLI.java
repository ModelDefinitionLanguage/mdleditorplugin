/* File			:  DesEncrypterCLI.java
 * Project		:  MIFClient
 * Created on	:  Oct 10, 2012
 */
package com.mango.mif.utils.encrypt;

import java.util.List;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineParser;

/**
 * 
 * DES command line encrypter
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class DesEncrypterCLI {
    /**
     * Possible commands to be executed by Des Encrypter
     * @author mrogalski
     *
     */
    static enum Command {
        encryptMessage
        {
            DesEncrypterCommand encrypterCommand = new EncryptMessageDes();
            @Override
            DesEncrypterCommand getEncrypterCommand() {
                return encrypterCommand;
            }
        },
        generateKeyFile {
            DesEncrypterCommand encrypterCommand = new GenerateDesKeyFile();
            @Override
            DesEncrypterCommand getEncrypterCommand() {
                return encrypterCommand;
            }
        };
        
        abstract DesEncrypterCommand getEncrypterCommand();
    }
    
    @Argument(index=0, required=true, metaVar="COMMAND", usage = "Command that should be invoked [\"encryptMessage\"|\"generateKeyFile\"]")
    private Command command;

    @Argument(index=1, required=true, metaVar="PARAMETERS", usage = "Command parameters")
    private List<String> commandParameters;
    /**
     * Used to generate a key file
     * @param args
     */
    public static void main(String[] args) {
        DesEncrypterCLI cli = new DesEncrypterCLI();
        CmdLineParser parser = new CmdLineParser(cli);
        
        try {
            parser.parseArgument(args);
        } catch (ArrayIndexOutOfBoundsException e) {
            //Args4j doesn't handle enums properly
            exit(parser,"Command not recognized");
        } catch (Exception e) {
            exit(parser,e.getMessage());
        }
        
        DesEncrypterCommand encrypterCommand = cli.command.getEncrypterCommand();

        try {
            encrypterCommand.execute(cli.commandParameters.toArray(new String[cli.commandParameters.size()]));
        } catch(Exception e) {
            exit(parser,e.getMessage());
        }
    }
    /**
     * Exits
     * @param parser
     * @param msg
     */
    private static void exit(CmdLineParser parser, String msg) {        
        System.err.println(msg);
        parser.printUsage(System.err);
        for(Command command : Command.values()) {
            System.err.println("COMMAND: " + command.name());
            command.getEncrypterCommand().printUsageInfo(System.err);
        }
        System.exit(0);
    }
}
