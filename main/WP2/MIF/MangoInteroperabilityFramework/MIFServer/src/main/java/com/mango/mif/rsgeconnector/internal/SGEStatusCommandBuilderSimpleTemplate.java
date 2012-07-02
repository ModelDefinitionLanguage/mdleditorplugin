package com.mango.mif.rsgeconnector.internal;

import com.google.common.base.Preconditions;

/**
 * A SGE status command builder that uses a predefined template
 * @author mrogalski
 *
 */
public class SGEStatusCommandBuilderSimpleTemplate extends
		SGEStatusCommandBuilder {
	/**
	 * Poll command template
	 */
	private String pollCommandTemplate;
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
		Preconditions.checkNotNull(pollCommandTemplate, "Poll command template not set.");
		return pollCommandTemplate.replace(COMMAND_TOKEN, command).replace(PROLOG_TOKEN,prolog); 
	}
	/**
	 * @param pollCommandTemplate the pollCommandTemplate to set
	 */
	public void setPollCommandTemplate(String pollCommandTemplate) {
		this.pollCommandTemplate = pollCommandTemplate;
	}
}
