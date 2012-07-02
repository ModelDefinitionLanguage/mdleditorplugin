/* File			:  FileUtilsOutputResourcePublisher.java
 * Project		:  MIFClient
 * Created on	:  12 Jun 2012
 */
package com.mango.mif.core.resource.fileutils;

import java.io.File;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 *
 */
public class FileUtilsOutputResourcePublisher extends FileUtilsResourcePublisher {
	/**
	 * 
	 * @param file
	 * @param requestID
	 * @return a file path of the file on the shared location
	 */
	protected File getRequestDestinationFile(File file, String requestID) {
		String path = file.getAbsolutePath().replace(rootDirectory.getAbsolutePath()+File.separator, "");
		return new File(sharedLocationManager.getRequestOutputDirectory(requestID),path);
	}
}
