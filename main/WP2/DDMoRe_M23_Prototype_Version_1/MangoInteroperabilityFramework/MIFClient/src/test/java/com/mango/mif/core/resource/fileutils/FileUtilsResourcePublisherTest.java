/* File			:  FileUtilsResourcePublisherTest.java
 * Project		:  MIFClient
 * Created on	:  11 Jun 2012
 */
package com.mango.mif.core.resource.fileutils;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.mango.mif.connector.TestsHelper;
import com.mango.mif.core.impl.DefaultWorkspaceManager;
import com.mango.mif.core.resource.UserSharedLocationManager;
import com.mango.mif.core.resource.fileutils.FileUtilsResourcePublisher;


/**
 * FileUtils based resource publisher unit test
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 *
 */
public class FileUtilsResourcePublisherTest {
	File tmpSharedLocationDirectory;
	File tmpRootLocationDirectory;
	
	UserSharedLocationManager sharedLocationManager;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		tmpSharedLocationDirectory = TestsHelper.createTmpDirectory();
		tmpRootLocationDirectory = TestsHelper.createTmpDirectory();
		sharedLocationManager = new UserSharedLocationManager();
		sharedLocationManager.setWorkspaceManager(new DefaultWorkspaceManager(tmpSharedLocationDirectory));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
    	FileUtils.deleteDirectory(tmpSharedLocationDirectory);
    	FileUtils.deleteDirectory(tmpRootLocationDirectory);
	}

	@Test(expected = NullPointerException.class)
	public void shouldThrowExceptionIfRootDirectoryIsNotSet() {
		FileUtilsResourcePublisher publisher = new FileUtilsResourcePublisher();
		publisher.setSharedLocationManager(sharedLocationManager);
		publisher.publish();
		
	}

	@Test(expected = NullPointerException.class)
	public void shouldThrowExceptionIfSharedLocationManagerIsNotSet() {
		FileUtilsResourcePublisher publisher = new FileUtilsResourcePublisher();
		publisher.setRootDirectory(tmpRootLocationDirectory);
		publisher.publish();
	}
	
	@Test
	public void shouldConvertTheExternalFilePathToSharedLocationPath() {
		FileUtilsResourcePublisher publisher = new FileUtilsResourcePublisher();
		publisher.setRootDirectory(tmpRootLocationDirectory);
		publisher.setSharedLocationManager(sharedLocationManager);
		String requestID = UUID.randomUUID().toString();
		new File(sharedLocationManager.getSharedLocationRoot(),requestID).mkdirs();
		File aFile = new File(tmpRootLocationDirectory,"a/b/c");
		File expected = new File(tmpSharedLocationDirectory,requestID+"/inputs/a/b/c");
		File result = publisher.getRequestDestinationFile(aFile, requestID);
		assertEquals(expected.getAbsolutePath(), result.getAbsolutePath());
	}

	
	@Test
	public void shouldPublishListOfInputFiles() throws IOException {
		FileUtilsResourcePublisher publisher = new FileUtilsResourcePublisher();
		publisher.setRootDirectory(tmpRootLocationDirectory);
		publisher.setSharedLocationManager(sharedLocationManager);
		
		File dirA = new File(tmpRootLocationDirectory,"A");
		dirA.mkdir();
		File dirB = new File(tmpRootLocationDirectory,"B");
		dirB.mkdir();
		File dirC = new File(tmpRootLocationDirectory,"C");
		dirC.mkdir();
		
		File aFile = new File(dirA,"a.txt");
		FileUtils.writeStringToFile(aFile, "This is test content of a.txt file.");

		File bFile = new File(dirB,"b.txt");
		FileUtils.writeStringToFile(bFile, "This is test content of b.txt file.");
		
		List<File> files = Lists.newArrayList(dirA, dirB, dirC, aFile, bFile);
		
		publisher.addFiles(files);
		
		String requestID = publisher.publish();
		
		File requestInputDirectory = sharedLocationManager.getRequestInputDirectory(requestID);
		assertTrue(new File(requestInputDirectory,dirA.getName()).exists());
		assertTrue(new File(requestInputDirectory,dirB.getName()).exists());
		assertTrue(new File(requestInputDirectory,dirC.getName()).exists());
		assertTrue(new File(requestInputDirectory,dirA.getName()+"/" + aFile.getName()).exists());
		assertTrue(new File(requestInputDirectory,dirB.getName()+"/" + bFile.getName()).exists());
	}
}
