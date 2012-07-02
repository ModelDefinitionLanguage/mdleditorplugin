package com.mango.mif.rsgeconnector.internal;

import com.google.common.base.Preconditions;

/**
 * A SGE cancel command builder that uses a simple template
 * @author mrogalski
 *
 */
public class SGECancelCommandBuilderSimpleTemplate extends
		SGECancelCommandBuilder {
	/**
	 * a template
	 */
	private String cancelCommandTemplate;
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
		Preconditions.checkNotNull(cancelCommandTemplate, "Cancel command template not set.");
		return cancelCommandTemplate.replace(JOB_ID_TOKEN, jobID).replace(PROLOG_TOKEN,prolog);
	}
	/**
	 * @param sgecancelCommandTemplate the cancelCommandTemplate to set
	 */
	public void setCancelCommandTemplate(String cancelCommandTemplate) {
		this.cancelCommandTemplate = cancelCommandTemplate;
	}
}
