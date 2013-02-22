/* File			:  NewAndModifiedFilesDetector.java
 * Project		:  MIFClient
 * Created on	:  9 Jul 2012
 */
package com.mango.mif.core.resource;

import java.io.File;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public interface NewAndModifiedFilesDetector {

	public void preserveLastModifiedTimeForDir(File directory);
	
	public File[] getNewOrModifiedFilesFromDir(File directory);
}
