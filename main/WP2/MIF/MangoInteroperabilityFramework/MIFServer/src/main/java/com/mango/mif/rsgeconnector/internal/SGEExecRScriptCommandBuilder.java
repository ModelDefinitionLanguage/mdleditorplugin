package com.mango.mif.rsgeconnector.internal;

import java.io.File;

import com.google.common.base.Preconditions;

/**
 * Builds a shell script that executes given script with R
 * @author mrogalski
 *
 */
public class SGEExecRScriptCommandBuilder {

    /**
     * SGE submission script template
     */
    private String commandTemplate;
    /**
     * Script file to be executed
     */
    private File scriptFile;
    /**
     * 
     * @return a script content to be executed
     */
    public String getCommand() {
    	Preconditions.checkNotNull(commandTemplate, "R script command template not set");
    	Preconditions.checkNotNull(scriptFile, "R script file not set");
    	return commandTemplate.replace("<<SCRIPT_FILE>>",scriptFile.getAbsolutePath());
    }
    
    public void setScriptFile(File scriptFile) {
		this.scriptFile = scriptFile;
	}
    
    public void setCommandTemplate(String commandTemplate) {
    	this.commandTemplate = commandTemplate;
    }
}
