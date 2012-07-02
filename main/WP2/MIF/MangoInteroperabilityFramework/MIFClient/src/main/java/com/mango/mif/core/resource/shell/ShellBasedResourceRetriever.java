/* File			:  TemplateBasedResourceRetriever.java
 * Project		:  MIFClient
 * Created on	:  12 Jun 2012
 */
package com.mango.mif.core.resource.shell;

import java.io.File;

import com.mango.mif.core.resource.BaseResourceRetriever;


/**
 * 
 * A template based resource retriever
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 *
 */
public class ShellBasedResourceRetriever extends BaseResourceRetriever {
	/**
	 * Template of the submit.sh command
	 */
	private File template;
	/**
	 * User name
	 */
	private String	userName;
	/**
	 * User encrypted password
	 */
	private String	password;
	/* (non-Javadoc)
	 * @see com.mango.mif.core.api.ResourceRetriever#retrieve()
	 */
	@Override
	public void retrieve() {

		/*TODO
		 * See how FileUtilsResourceRetriever works
		 * 2. Populate postprocess.sh script template with parameters (TODO create abstract RetrieveFilesCommandBuilder and TemplateBaseRetrieveFilesCommandBuilder)
		 * 		a. root project directory to copy results to
		 *  	b. request output directory (sharedLocationManager.getRequestOutputDirectory(requestID)
		 *  	
		 * 3. execute the script using JSCH and user name and password
		 *  
		 */
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setTemplate(File template) {
		this.template = template;
	}
}
