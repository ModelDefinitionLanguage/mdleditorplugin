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
	private String testPharmMLLocation = "Ex_model7_prolactin_OAM/ex_model7_prolactin_25June2014_OAM.xml"
	private String testTargetBlockFileLocation = "Ex_model7_prolactin_OAM/targetblock/ex_model7_prolactin_25June2014_OAM.xml"
	ConversionContext conversionContext
	def pmlDOM

	public void setupTest(String fileLocation){
		File src = getFile(fileLocation, V_0_3_SUBDIR)
		pmlDOM = getDom(src)
		conversionContext = new ConversionContext(pmlDOM, src)
	}

	@Test
    public void testGetTargetBlocks() {
        File TargetBlockFile = getFile(testTargetBlockFileLocation, V_0_3_SUBDIR)
		TargetBlockStatement targetblocks = new TargetBlockStatement()
		List test = targetblocks.getTargetBlocks(TargetBlockFile)
		assertEquals("Should return correct size",20,test.size())		
    }
	
	public void shouldReturnCorrectTargetBlock(){
		File TargetBlockFile = getFile(testTargetBlockFileLocation, V_0_3_SUBDIR)
		setupTest(testPharmMLLocation)
		
	}
	
	@Test
	public void shouldCheckIfTargetBlockIsDefined(){
		File TargetBlockFile = getFile(testTargetBlockFileLocation, V_0_3_SUBDIR)
		setupTest(testPharmMLLocation)		
		String targetBlockName = "\$EST"
		assert conversionContext.targetBlockConverter.isTargetDefined(targetBlockName):"target block is not defined"
	}
	
	@Test
	public void shouldReturnTargetBlockAtStart(){
		File TargetBlockFile = getFile(testTargetBlockFileLocation, V_0_3_SUBDIR)
		setupTest(testPharmMLLocation)
		
		StringBuilder sb = new StringBuilder(
		"\$EST PRINT=5 MAX=0 SIGDIG=3 METHOD=1\n"+
		"\nMETHOD=COND\n"+
		"\n\$COV TEST TARGET BLOCK\n\n")
		
		String codeBlock = conversionContext.getEstimationStatement()
		assertEquals("Should return Code block for the statement specified",sb.toString(),codeBlock)
	}

	@Test
	public void shouldReturnTargetBlockAtEnd(){
		File TargetBlockFile = getFile(testTargetBlockFileLocation, V_0_3_SUBDIR)
		setupTest(testPharmMLLocation)
		
		StringBuilder sb = new StringBuilder(
		"\n\$TABLE null FILE=ex_model7_prolactin_25June2014_OAM_data.tab\n"+
		"\$TABLE NM_F2 NM_POP_PRL0 NM_D2 NM_POP_PHS2 NM_PHS1 \n"+
		"ETA0 ETA0 ETA0 ETA0 ETA0 ETA0 Y FILE=ex_model7_prolactin_25June2014_OAM_params.tab NOAPPEND NOPRINT ONEHEADER\n"+
		"\n\$TABLE TEST TARGET BLOCK\n\$TABLE TARGETBLOCK\n")
		
		StringBuilder returned = conversionContext.getTableStatement()
		assertEquals("Should return Code block for the statement specified",sb.toString(),returned.toString())
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