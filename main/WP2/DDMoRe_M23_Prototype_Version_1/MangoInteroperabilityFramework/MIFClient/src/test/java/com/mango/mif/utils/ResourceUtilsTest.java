/* File			:  ResourceUtilsTest.java
 * Project		:  MIFClient
 * Created on	:  11 Jun 2012
 */
package com.mango.mif.utils;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.mango.mif.connector.TestsHelper;
import com.mango.mif.core.api.ResourceUtils;


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
public class ResourceUtilsTest {
	File tmpDirectory;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		tmpDirectory = TestsHelper.createTmpDirectory();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
    	FileUtils.deleteDirectory(tmpDirectory);
	}
	
	@Test
	public void shouldReturnSortedListOfAllTheFilesInDirectory() throws IOException {

		File dirB = new File(tmpDirectory,"B");
		dirB.mkdir();
		File dirC = new File(tmpDirectory,"C");
		dirC.mkdir();
		File dirA = new File(tmpDirectory,"A");
		dirA.mkdir();
		
		File aFile = new File(dirA,"a.txt");
		FileUtils.writeStringToFile(aFile, "This is test content of a.txt file.");
		
		File a2File = new File(dirA,"a2.txt");
		FileUtils.writeStringToFile(a2File, "This is test content of a.txt file.");

		File bFile = new File(dirB,"b.txt");
		FileUtils.writeStringToFile(bFile, "This is test content of b.txt file.");
		
		
		List<File> files = ResourceUtils.listFiles(tmpDirectory, new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return true;
			}
		});
		
		assertEquals(aFile,files.get(0));
		assertEquals(a2File,files.get(1));
		assertEquals(bFile,files.get(2));
		assertEquals(dirC,files.get(3));
	}

	@Test
	public void shouldCopyDirectory() throws IOException {
		File aDir = new File(tmpDirectory,"a");
		aDir.mkdir();
		File bDir = new File(tmpDirectory,"b");
		ResourceUtils.copyFile(aDir,bDir);
		assertTrue(bDir.exists());
	}
	
	@Test
	public void shouldCopyFile() throws IOException {
		File aFile = new File(tmpDirectory,"a.txt");
		FileUtils.writeStringToFile(aFile, "This is test content of a file.");
		File result = new File(tmpDirectory, "b.txt");
		ResourceUtils.copyFile(aFile,result);
		assertTrue(result.exists());
	}
	
	@Test
	public void shouldTrimTheTrailingRootPathFromFilePath() {
		File aFile = new File(tmpDirectory,"a"+File.separator+"b"+File.separator+"c"+File.separator+"d");
		File bFile = new File(tmpDirectory,File.separator+"a"+File.separator+"b"+File.separator+"c"+File.separator+"d");
		File cFile = new File(tmpDirectory,File.separator+"a"+File.separator+"b"+File.separator+"c"+File.separator+"d"+File.separator);
		
		List<File> testFiles = Lists.newArrayList(aFile,bFile,cFile);
		
		String expected = "a"+File.separator+"b"+File.separator+"c"+File.separator+"d";
		for(File file : testFiles) {
			File path = ResourceUtils.convertToRelativePath(tmpDirectory, file);
			assertEquals(expected,path.getPath());
		}
		
	}
}
