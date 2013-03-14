/* File			:  RScriptExec.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  29 Mar 2012
 */
package com.mango.mif.r;

import java.io.File;
import java.util.Set;

import org.apache.commons.exec.CommandLine;

import com.google.common.base.Preconditions;
import com.mango.mif.core.exec.InvokerResult;
import com.mango.mif.utils.CommandExec;
import com.mango.mif.utils.CommandExecException;

/**
 * 
 * Instances Are responsible for executing R script using R executable specified in Config object.
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class RScriptExec extends CommandExec {
    /**
     * Contains Execution environment properties
     */
    private RScriptConfig config;

    /**
     * script file that should be executed
     */
    protected File rScriptFile;

    @Override
    public synchronized void executeAsync() throws CommandExecException {
        prepareCommand();
        super.executeAsync();
    }

    @Override
    public synchronized InvokerResult runAsUser() throws CommandExecException {
        prepareCommand();
        return super.runAsUser();
    }

    /**
     * create R execution command line
     * @return
     */
    public void prepareCommand() {
        Preconditions.checkNotNull(config.getExecutable(), "R executable not set");
        Preconditions.checkNotNull(rScriptFile, "R script file not set");
        Set<String> attributes = config.getAttributes();
        CommandLine commandLine = new CommandLine(config.getExecutable());
        commandLine.addArguments(attributes.toArray(new String[attributes.size()]));
        commandLine.addArgument(rScriptFile.getName());

        setCommandLine(commandLine);
    }

    public File getRscriptFile() {
        return rScriptFile;
    }

    public void setRscriptFile(File rScriptFile) {
        this.rScriptFile = rScriptFile;
    }

    public RScriptConfig getConfig() {
        return config;
    }

    public void setConfig(RScriptConfig config) {
        this.config = config;
    }
}
