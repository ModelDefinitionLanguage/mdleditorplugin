/* File			:  RScriptBuilder.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  2 Apr 2012
 */
package com.mango.mif.rconnectorcli.internal;

import java.io.File;
import java.util.Map;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.mango.mif.domain.CommandDefinition;
import com.mango.mif.domain.Parameter;
import com.mango.mif.domain.ParameterDefinition;
import com.mango.mif.domain.ParameterDirection;
import com.mango.mif.domain.ParameterType;

/**
 * 
 * The class is responsible for creating R script ready for execution. Its main responsibilities are:
 * 1. To inject input parameters
 * 2. To reference input datasets
 * 3. Append results publishing commands  
 * 
 * TODO Populate with header/footer R scripts
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class RScriptBuilder {
    /**
     * Command definition
     */
    private CommandDefinition commandDefinition;
    /**
     * Input parameters
     */
    private Map<String,Parameter> parameters;
    /**
     * Working directory
     */
    private File workingDirectory;
    /**
     * Constructor
     */
    public RScriptBuilder() {
        
    }

    public void setCommandDefinition(CommandDefinition commandDefinition) {
        this.commandDefinition = commandDefinition;
    }
    
    public void setParameters(Map<String, Parameter> parameters) {
        this.parameters = parameters;
    }
    /**
     * Builds the script that can be executed using R in working directory
     * 
     * TODO the method is not implemented yet
     * @return
     */
    public String getRScript() {
        
        
        return "NOT IMPLEMENTED YET!";
    }
    
    public void setWorkingDirectory(File workingDirectory) {
        this.workingDirectory = workingDirectory;
    }
    /**
     * 
     * @return script file specified as input parameter
     */
    public File getScriptFile() {
        Preconditions.checkNotNull(commandDefinition);
        Preconditions.checkNotNull(parameters);
        
        Iterable<ParameterDefinition> pDefinitions =  Iterables.filter(commandDefinition.getParameters(), new Predicate<ParameterDefinition>() {

            @Override
            public boolean apply(ParameterDefinition parameterDefinition) {
                return parameterDefinition.getDirection().equals(ParameterDirection.IN) && parameterDefinition.getType().equals(ParameterType.File) && parameterDefinition.getName().equals("script");
            }
            
        });
        Preconditions.checkArgument(pDefinitions.iterator().hasNext(),"Invalid command definition. Input script not defined.");
        
        ParameterDefinition definition = pDefinitions.iterator().next();
        Parameter parameter = parameters.get(definition.getName());
        File script = new File(workingDirectory,parameter.getValue());
        Preconditions.checkArgument(script.exists(),"Script file does not exist.");
        return script;
    }
}
