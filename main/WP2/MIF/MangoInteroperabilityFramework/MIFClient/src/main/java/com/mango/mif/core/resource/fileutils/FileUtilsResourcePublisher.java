/* File			:  FileUtilsResourcePublisher.java
 * Project		:  MIFClient
 * Created on	:  11 Jun 2012
 */
package com.mango.mif.core.resource.fileutils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.google.common.base.Preconditions;
import com.mango.mif.core.api.ResourceUtils;
import com.mango.mif.core.resource.BaseResourcePublisher;

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
public class FileUtilsResourcePublisher extends BaseResourcePublisher {
	/**
	 * Logger
	 */
	public final static Logger LOG = Logger.getLogger(FileUtilsResourcePublisher.class);
	/**
	 * Number of times a resource component will re-try to generate request directory GUID 
	 */
	private final static int	TRESHOLD	= 10;
	/* (non-Javadoc)
	 * @see com.mango.mif.core.impl.BasicResourcePublisher#publish()
	 */
	@Override
	public String publish() {
		Preconditions.checkNotNull(rootDirectory,"Root directory can't be null");
		Preconditions.checkNotNull(sharedLocationManager,"Shared location manager can't be null");
		String requestID = createRequestDirectory();
		for(File file : files) {
			File destFile = getRequestDestinationFile(file,requestID);
			try {
				ResourceUtils.copyFile(file,destFile);
			} catch (IOException e) {
				LOG.error(e);
				throw new RuntimeException("Could not copy file " + file + " to " + destFile,e);
			}
		}
		this.requestID = requestID;
		return requestID;
	}

	/**
	 * 
	 * @param file
	 * @param requestID
	 * @return a file path of the file on the shared location
	 */
	protected File getRequestDestinationFile(File file, String requestID) {
		String path = file.getAbsolutePath().replace(rootDirectory.getAbsolutePath()+File.separator, "");
		return new File(sharedLocationManager.getRequestInputDirectory(requestID),path);
	}
	
	/**
	 * Generates a unique identifier and creates a directory named with that unique ID
	 * @return a request directory
	 */
	public String createRequestDirectory() {
		UUID uuid = null;
		boolean done = false;
		int i = 0;
		while (!done) {
			uuid = UUID.randomUUID();
			LOG.debug("Random UUID: " + uuid);
			File dir = new File(sharedLocationManager.getSharedLocationRoot(),uuid.toString());
			if (dir.exists()) {
				uuid = UUID.randomUUID();
				continue;
			}
			if (createRequestDirectory(dir)) {
				done = true;
				break;
			}
			if (i >= TRESHOLD) {
				// should never happen
				throw new RuntimeException("Could not generate unique ID for request directory.");
			}
			i++;
		}
		return uuid.toString();
	}

	/**
	 * Creates request directories accordingly to the convention
	 * @param dir
	 */
	boolean createRequestDirectory(File dir) {
		Preconditions.checkNotNull(dir);
				
		if (!dir.mkdir()) {
			return false;
		}
		LOG.info("Created request directory: " + dir);

		File inputDir = sharedLocationManager.getRequestInputDirectory(dir.getName());
		if (!inputDir.mkdirs()) {
			return false;
		}
		LOG.info("Created request input directory: " + inputDir);
		
		File outputDir = sharedLocationManager.getRequestOutputDirectory(dir.getName());
		if (!outputDir.mkdirs()) {
			return false;
		}
		LOG.info("Created request output directory: " + outputDir);
		
		return true;
	}

	@Override
	public void setUserName(String userName) {
	}
}
