package com.mango.mif.rsgeconnector.internal;

import com.google.common.base.Preconditions;


/**
 * Builds account command execution script using simple template
 * @author mrogalski
 *
 */
public class SGEAccountCommandBuilderSimpleTemplate extends SGEAccountCommandBuilder {
	/**
	 * Account command template
	 */
	private String accountCommandTemplate;
	/**
	 * Job ID token
	 */
	public final static String JOB_ID_TOKEN = "<<JOB_ID>>";
    /**
     * Command token
     */
    public final static String PROLOG_TOKEN = "<<PROLOG>>";
	
	@Override
	public String getCommand() {
		super.getCommand();
		Preconditions.checkNotNull(accountCommandTemplate, "Account command template not set.");
		return accountCommandTemplate.replace(JOB_ID_TOKEN, jobID).replace(PROLOG_TOKEN,prolog);
	}
	
	/**
	 * @param accountCommandTemplate the accountCommandTemplate to set
	 */
	public void setAccountCommandTemplate(String accountCommandTemplate) {
		this.accountCommandTemplate = accountCommandTemplate;
	}
}
