package eu.ddmore.converter.pharmml2nmtran.statements;

import static eu.ddmore.converter.pharmml2nmtran.MainTest.TEST_DATA_DIR
import static eu.ddmore.converter.pharmml2nmtran.MainTest.V_0_3_SUBDIR
import static org.junit.Assert.*

import java.io.File;

import org.apache.commons.io.FileUtils
import org.junit.Test

import eu.ddmore.converter.pharmml2nmtran.utils.ConversionContext
import eu.ddmore.libpharmml.PharmMlFactory

class TargetBlockTest {

	private static final String WORKING_DIR = "target/DataStatementTest_Working_Dir/"

	@Test
    public void testGetTargetBlocks() {
        File src = getFile('Ex_model7_prolactin_OAM/target/ex_model7_prolactin_25June2014_OAM.xml', V_0_3_SUBDIR)
		TargetBlockStatement targetblocks = new TargetBlockStatement()
		List test = targetblocks.getTargetBlocks(src)
		assertEquals("Should return correct size",2,test.size())
    }

	private File getFile(final String relativePathToFile, final String versionSubDirectory) {
		def urlToFile = this.class.getResource(TEST_DATA_DIR + versionSubDirectory + relativePathToFile)
		def destFile = new File(WORKING_DIR + versionSubDirectory + relativePathToFile)
		FileUtils.copyURLToFile(urlToFile, destFile)

		destFile
	}
		
	private getDom(file) {
		def pmlAPI = PharmMlFactory.getInstance().createLibPharmML()
		pmlAPI.createDomFromResource(FileUtils.openInputStream(file)).getDom()
	}
}