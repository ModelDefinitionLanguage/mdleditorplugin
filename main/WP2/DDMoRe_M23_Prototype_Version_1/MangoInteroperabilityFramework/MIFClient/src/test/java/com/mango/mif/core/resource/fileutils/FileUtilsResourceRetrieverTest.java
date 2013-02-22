/* File			:  FileUtilsResourceRetrieverTest.java
 * Project		:  MIFClient
 * Created on	:  12 Jun 2012
 */
package com.mango.mif.core.resource.fileutils;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mango.mif.connector.TestsHelper;
import com.mango.mif.core.impl.DefaultWorkspaceManager;
import com.mango.mif.core.resource.UserSharedLocationManager;
import com.mango.mif.core.resource.fileutils.FileUtilsResourceRetriever;


/**
 * 
 * FileUtils based resource retriever unit test
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 *
 */
public class FileUtilsResourceRetrieverTest {
	/**
	 * Temporary shared location directory
	 */
	File tmpSharedLocationDirectory;
	/**
	 * Temporary destination directory
	 */
	File tmpDestinationLocationDirectory;
	/**
	 * Shared location manager
	 */
	UserSharedLocationManager sharedLocationManager;
	
	String requestID;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		tmpSharedLocationDirectory = TestsHelper.createTmpDirectory();
		tmpDestinationLocationDirectory = TestsHelper.createTmpDirectory();
		sharedLocationManager = new UserSharedLocationManager();
		sharedLocationManager.setWorkspaceManager(new DefaultWorkspaceManager(tmpSharedLocationDirectory));

		requestID = UUID.randomUUID().toString();
		new File(sharedLocationManager.getSharedLocationRoot(),requestID).mkdirs();

		sharedLocationManager.getRequestOutputDirectory(requestID).mkdirs();
		
	}
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
    	FileUtils.deleteDirectory(tmpSharedLocationDirectory);
    	FileUtils.deleteDirectory(tmpDestinationLocationDirectory);
	}

	@Test(expected = NullPointerException.class)
	public void shouldThrowExceptionIfDestinationDirIsNotSet() {
		FileUtilsResourceRetriever retriever = new FileUtilsResourceRetriever();
		retriever.setSharedLocationManager(sharedLocationManager);
		retriever.setRequestID(requestID);
		retriever.retrieve();
	}

	@Test(expected = NullPointerException.class)
	public void shouldThrowExceptionIfSharedLocationManagerIsNotSet() {
		FileUtilsResourceRetriever retriever = new FileUtilsResourceRetriever();
		retriever.setDestinationDir(tmpDestinationLocationDirectory);
		retriever.setRequestID(requestID);
		retriever.retrieve();
	}

	@Test(expected = NullPointerException.class)
	public void shouldThrowExceptionIfRequestIDNotSet() {
		FileUtilsResourceRetriever retriever = new FileUtilsResourceRetriever();
		retriever.setSharedLocationManager(sharedLocationManager);
		retriever.setDestinationDir(tmpDestinationLocationDirectory);
		retriever.retrieve();
	}

	@Test
	public void shouldConvertASharedLocationPathToDestinationPath() {
		FileUtilsResourceRetriever retriever = new FileUtilsResourceRetriever();
		retriever.setDestinationDir(tmpDestinationLocationDirectory);
		retriever.setSharedLocationManager(sharedLocationManager);
		
		File outputDir = sharedLocationManager.getRequestOutputDirectory(requestID);
		
		File aFile = new File(outputDir,"/a/b/c");
		File expected = new File(tmpDestinationLocationDirectory,"a/b/c");
		File result = retriever.getOutputDestinationFile(aFile, requestID);
		
		assertEquals(expected.getAbsolutePath(), result.getAbsolutePath());
	}

	@Test
	public void shouldRetrieveFilesFromSharedLocation() throws IOException {

		FileUtilsResourceRetriever retriever = new FileUtilsResourceRetriever();
		retriever.setDestinationDir(tmpDestinationLocationDirectory);
		retriever.setSharedLocationManager(sharedLocationManager);
		retriever.setRequestID(requestID);
		File outputDir = sharedLocationManager.getRequestOutputDirectory(requestID);
		

		File dirA = new File(outputDir,"A");
		dirA.mkdir();
		File dirB = new File(outputDir,"B");
		dirB.mkdir();
		File dirC = new File(outputDir,"C");
		dirC.mkdir();
		
		File aFile = new File(dirA,"a.txt");
		FileUtils.writeStringToFile(aFile, "This is test content of a.txt file.");

		File bFile = new File(dirB,"b.txt");
		FileUtils.writeStringToFile(bFile, "This is test content of b.txt file.");

		retriever.retrieve();
		
		assertTrue(new File(tmpDestinationLocationDirectory,dirA.getName()).exists());
		assertTrue(new File(tmpDestinationLocationDirectory,dirB.getName()).exists());
		assertTrue(new File(tmpDestinationLocationDirectory,dirC.getName()).exists());
		assertTrue(new File(tmpDestinationLocationDirectory,dirA.getName()+"/" + aFile.getName()).exists());
		assertTrue(new File(tmpDestinationLocationDirectory,dirB.getName()+"/" + bFile.getName()).exists());

	}
	
}
