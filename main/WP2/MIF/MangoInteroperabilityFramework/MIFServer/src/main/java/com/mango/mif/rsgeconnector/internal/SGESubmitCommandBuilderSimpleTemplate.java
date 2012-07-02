package com.mango.mif.rsgeconnector.internal;

import com.google.common.base.Preconditions;

/**
 * SGE submit command builder that uses a simple template to execute qsub
 * @author mrogalski
 *
 */
public class SGESubmitCommandBuilderSimpleTemplate extends
		SGESubmitCommandBuilder {
    /**
     * SGE submission script template
     */
    private String submitCommandTemplate;
    /**
     * Command token
     */
    public final static String COMMAND_TOKEN = "<<COMMAND>>";
    /**
     * Command token
     */
    public final static String PROLOG_TOKEN = "<<PROLOG>>";
    
    @Override
    public String getCommand() {
    	String command = super.getCommand();
		Preconditions.checkNotNull(submitCommandTemplate, "Account command template not set.");
		
    	return submitCommandTemplate.replace(COMMAND_TOKEN,command).replace(PROLOG_TOKEN,prolog);
    }
    /**
	 * @param submitCommandTemplate the submitCommandTemplate to set
	 */
	public void setSubmitCommandTemplate(String submitCommandTemplate) {
		this.submitCommandTemplate = submitCommandTemplate;
	}
}
