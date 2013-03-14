/* File			:  Invoker.java
 * Project		:  MIFServer
 * Created on	:  29 Jun 2012
 */
package com.mango.mif.core.exec;


/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */

/**
 * @author mrogalski
 * Invoker used to execute commands
 */
public interface Invoker {
    /**
     * Executes given command.
     * 
     * @param command the command, plus any arguments (may be multi-line)
     * @return the results of the command execution (stdout, stderr, exit status)
     */
    InvokerResult execute(String command) throws ExecutionException;

    /**
     * Executes given command with the standard input specified
     * 
     * @param command the command, plus any arguments (may be multi-line)
     * @param input text to shove up the command's standard input
     * @return the results of the command execution (stdout, stderr, exit status)
     */
    InvokerResult execute(String command, String input) throws ExecutionException;
}
