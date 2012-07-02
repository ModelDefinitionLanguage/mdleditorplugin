/* File			:  SharedLocationOperation.java
 * Project		:  MIFClient
 * Created on	:  11 Jun 2012
 */
package com.mango.mif.core.resource;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
import com.mango.mif.core.api.SharedLocationManager;

/**
 * @author mrogalski
 *
 */
public class BaseSharedLocationOperation implements SharedLocationOperation {

	/**
	 * Request ID
	 */
	protected String	requestID;
	/**
	 * Shared Location Manager providing basic shared location operations
	 */
	protected SharedLocationManager	sharedLocationManager;

	/**
	 * 
	 */
	public BaseSharedLocationOperation() {
		super();
	}

	@Override
	public void setSharedLocationManager(SharedLocationManager sharedLocationManager) {
		this.sharedLocationManager = sharedLocationManager;
	}


}