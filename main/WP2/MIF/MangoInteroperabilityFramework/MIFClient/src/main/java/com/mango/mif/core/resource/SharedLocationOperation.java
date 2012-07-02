/* File			:  SharedLocationOperation.java
 * Project		:  MIFClient
 * Created on	:  11 Jun 2012
 */
package com.mango.mif.core.resource;

import com.mango.mif.core.api.SharedLocationManager;

/**
 * 
 * A super interface of shared location operations like request files retrieval and publishing 
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
public interface SharedLocationOperation {
    
    void setSharedLocationManager(SharedLocationManager sharedLocationManager);

}
