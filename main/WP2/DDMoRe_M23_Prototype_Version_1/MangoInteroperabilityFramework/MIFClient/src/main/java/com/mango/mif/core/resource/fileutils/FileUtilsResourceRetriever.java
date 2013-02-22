/* File			:  FileUtilsResourceRetriever.java
 * Project		:  MIFClient
 * Created on	:  11 Jun 2012
 */
package com.mango.mif.core.resource.fileutils;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.common.base.Preconditions;
import com.mango.mif.core.api.ResourceUtils;
import com.mango.mif.core.resource.BaseResourceRetriever;

/**
 * 
 * Basic FileUtils resource retriever that copies files from the output directory of a request 
 * to a specified destination directory
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
public class FileUtilsResourceRetriever extends BaseResourceRetriever {
	/**
	 * Logger
	 */
	public final static Logger LOG = Logger.getLogger(FileUtilsResourceRetriever.class);
	/**
	 * Destination directory
	 */
	private File destinationDir;
	
	/* (non-Javadoc)
	 * @see com.mango.mif.core.api.ResourceRetriever#retrieve()
	 */
	@Override
	public void retrieve() {
		Preconditions.checkNotNull(destinationDir);
		Preconditions.checkNotNull(sharedLocationManager,"Shared location manager can't be null");
		Preconditions.checkNotNull(requestID);
		
		File outputDir = sharedLocationManager.getRequestOutputDirectory(requestID);
		
		List<File> files = ResourceUtils.listFiles(outputDir, new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return true;
			}
		});
		for(File file : files) {
			try {
				ResourceUtils.copyFile(file, getOutputDestinationFile(file,requestID));
			} catch (IOException e) {
				LOG.error(e);
				throw new RuntimeException(e);
			}
		}
	}
	
	/**
	 * @param destinationDir the destinationDir to set
	 */
	public void setDestinationDir(File destinationDir) {
		this.destinationDir = destinationDir;
	}
	/**
	 * 
	 * @param file
	 * @param requestID
	 * @return a file path of the file on the shared location
	 */
	File getOutputDestinationFile(File file, String requestID) {
		File outputRoot = sharedLocationManager.getRequestOutputDirectory(requestID);
		String path = file.getAbsolutePath().replace(outputRoot.getAbsolutePath()+File.separator, "");
		return new File(destinationDir,path);
	}
}
