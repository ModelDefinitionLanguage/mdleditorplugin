/**
 * Copyright Mango Solutions, 2012.
 * <p>SVN Entry: $HeadURL: $
 * <p>SVN ID: $Id: $
 * <p>Last edited by : $Author$
 * @author Tony Bamford &lt;tbamford@mango-solutions.com&gt;
 * @version $Revision$ as of on $Date$
 */
package com.mango.mif.core.resource.jsch;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Preconditions;
import com.mango.mif.connector.TestsHelper;
import com.mango.mif.core.jsch.JschHelper;
import com.mango.mif.core.jsch.JschParameterBuilder;
import com.mango.mif.exception.MIFException;
import com.mango.mif.utils.TestProperties;
import com.mango.mif.utils.encrypt.EncrypterFactory;
/**
 * Tests JSCH helper 
 * @author mrogalski
 *
 */
public class JschHelperTest {
	/**
	 * Logger
	 */
	private final static Logger LOG = Logger.getLogger(JschHelperTest.class);
	/**
	 * user name that will be used
	 */
    private String userName;
    /**
     * clear user password
     */
    private String userClearPassword;
    /**
     * instance
     */
    private JschHelper jschHelper;
    /**
     * SSH port
     */
	private int	port;

    @Before
    public void setup() {
    	userName = System.getProperty(TestProperties.MIF_ALTER_USER_NAME);
    	String encryptedPassword = System.getProperty(TestProperties.MIF_ALTER_USER_PASSWORD);
    	String portProp = System.getProperty(TestProperties.MIF_JSCH_PORT);
		Preconditions.checkNotNull(userName, TestProperties.MIF_ALTER_USER_NAME + " property is not set.");
		Preconditions.checkNotNull(encryptedPassword, TestProperties.MIF_ALTER_USER_PASSWORD + " property is not set.");
		Preconditions.checkNotNull(port, TestProperties.MIF_JSCH_PORT + " property is not set.");
		userClearPassword = EncrypterFactory.getEncrypter().decrypt(encryptedPassword);
		port = Integer.parseInt(portProp);
		
        JschParameterBuilder builder = new JschParameterBuilder(userName, userClearPassword).port(port);
        jschHelper = builder.buildHelper();
        
    }

    @Test
    public void shouldConfirmEtcDirectoryExistsOnLinux() throws MIFException {
            assertTrue(jschHelper.directoryExists("/etc"));
    }

    @Test
    public void shouldConfirmPasswdFileExistsOnLinux() throws MIFException {
            assertTrue(jschHelper.fileExists("/etc/passwd"));
    }

    @Test
    public void shouldExpandEtcOnLinux() throws MIFException {
            String[] filePaths = jschHelper.getFilesInDirectory("/etc");
            boolean foundPasswd = false;
            boolean foundShadow = false;
            for(String filePath : filePaths) {
                if (filePath.equals("/etc/passwd")) {
                    foundPasswd = true;
                }
                if (filePath.equals("/etc/shadow")) {
                    foundShadow = true;
                }
            }
            assertTrue(foundPasswd);
            assertTrue(foundShadow);
    }

    @Test
    public void shouldGetPasswdConentsOnLinux() throws MIFException {
            String contents = jschHelper.getFileContents("/etc/passwd");
            assertTrue(contents != null);
            assertTrue(contents.length() > 0);
    }

    @Test
    public void shouldCopyFileAcrossOnLinux() throws Exception {

        UUID uuid = UUID.randomUUID();
        File base = TestsHelper.tmpDirectoryPath(uuid.toString());

        String tmpSourceDirectoryPath = base.getAbsoluteFile() + "-SRC";
        File tmpSourceDirectory = new File(tmpSourceDirectoryPath);
        tmpSourceDirectory.mkdirs();

        String tmpTargetDirectoryPath = base.getAbsoluteFile() + "-DEST";
        if (!jschHelper.mkdir(tmpTargetDirectoryPath, 0777)) {
            throw new Exception("Unable to create directory " + tmpTargetDirectoryPath + " as user");
        }
        File tmpTargetDirectory = new File(tmpTargetDirectoryPath);

        LOG.info("SOURCE: " + tmpSourceDirectory.getAbsolutePath());
        LOG.info("DEST:   " + tmpTargetDirectory.getAbsolutePath());

        try {
            File sourceDirA = new File(tmpSourceDirectory, "A");
            File targetDirA = new File(tmpTargetDirectory, "A");
            sourceDirA.mkdir();

            File sourceDirB = new File(tmpSourceDirectory, "B");
            File targetDirB = new File(tmpTargetDirectory, "B");
            sourceDirB.mkdir();

            File sourceDirC = new File(tmpSourceDirectory, "C");
            File targetDirC = new File(tmpTargetDirectory, "C");
            sourceDirC.mkdir();

            File aSourceFile1 = new File(sourceDirA, "a1.txt");
            File aTargetFile1 = new File(targetDirA, "a1.txt");
            String aFile1Content = "This is the content of A/a1.txt.";
            FileUtils.writeStringToFile(aSourceFile1, aFile1Content);

            File aSourceFile2 = new File(sourceDirA, "a2.txt");
            File aTargetFile2 = new File(targetDirA, "a2.txt");
            String aFile2Content = "This is the content of A/a2.txt.";
            FileUtils.writeStringToFile(aSourceFile2, aFile2Content);

            File bSourceFile = new File(sourceDirB,"b.txt");
            String bFileContent = "This is the content of B/b.txt file.";
            FileUtils.writeStringToFile(bSourceFile, bFileContent);

            String[] relativePaths = new String[] { "A/a1.txt", "B/b.txt" };

            jschHelper.copySpecificFiles(tmpSourceDirectory.getAbsolutePath(), relativePaths, tmpTargetDirectory.getAbsolutePath());

            assertTrue(targetDirA.isDirectory() && targetDirA.exists());
            assertTrue(targetDirB.isDirectory() && targetDirB.exists());
            assertTrue(!targetDirC.exists());

            assertTrue(aTargetFile1.isFile() && aTargetFile1.exists());
            assertTrue(!aTargetFile2.exists());

        } finally {
            try {
                FileUtils.deleteDirectory(tmpSourceDirectory);
            } catch (Exception ignored) {
            }
            try {
                FileUtils.deleteDirectory(tmpTargetDirectory);
            } catch (Exception ignored) {
            }
        }
    }

    @Test
    public void shouldCreateAFileWithContentsOnLinux() throws MIFException {

        UUID uuid = UUID.randomUUID();
        File tempDirectory = TestsHelper.tmpDirectoryPath(uuid.toString());
        try {
            if (!jschHelper.mkdir(tempDirectory.getAbsolutePath(), 0777)) {
                throw new RuntimeException("Unable to create directory " + tempDirectory.getAbsolutePath() + " as user");
            }
            File tempFile = new File(tempDirectory, "A.txt");
            String contents = "Hello world!";
            jschHelper.createFileFromContents(contents, tempFile.getAbsolutePath(), 0600);

            assert(jschHelper.fileExists(tempFile.getAbsolutePath()));

            String actualContents = jschHelper.getFileContents(tempFile.getAbsolutePath());

            assert(actualContents.indexOf(contents) == 0);

        } finally {
            try {
                jschHelper.rmdir(tempDirectory.getAbsolutePath());
            } catch (Exception ignored) {
            }
        }
    }
}
