package eu.ddmore.converter.pharmml2nmtran.statements;

import static eu.ddmore.converter.pharmml2nmtran.MainTest.TEST_DATA_DIR
import static eu.ddmore.converter.pharmml2nmtran.MainTest.V_0_3_SUBDIR
import static org.junit.Assert.*

import java.io.File;

import org.apache.commons.io.FileUtils
import org.junit.Before;
import org.junit.Test

import eu.ddmore.converter.pharmml2nmtran.utils.ConversionContext
import eu.ddmore.libpharmml.PharmMlFactory


class DataStatementTest {

	private static final String WORKING_DIR = "target/DataStatementTest_Working_Dir/"
	private String example3location = "example3/example3.xml"
	private String example3_nonmemlocation = "example3/example3_NONMEM.xml"
	private String example5location = "example5/example5.xml"
	ConversionContext conversionContext
	private DataStatement ds
	def pmlDOM

	public void setupTest(String fileLocation){
		File src = getFile(fileLocation, V_0_3_SUBDIR)
		pmlDOM = getDom(src)
		conversionContext = new ConversionContext(pmlDOM, src)
		ds = new DataStatement(pmlDOM, src.toString().replace(".xml", ""))
	}
	
	@Test
	public void shouldBeCorrectDataStatementObjectiveDataSet() {
		setupTest(example3location)
		assertEquals("Statement should be correct.", "\$DATA  example3_data.csv IGNORE=@\n", conversionContext.getDataStatement(ds))
	}
	
	@Test
	public void shouldBeCorrectDataStatementNonmemDataSet() {
		setupTest(example3_nonmemlocation)
		assertEquals("Statement should be correct.", "\$DATA  warfarin_conc_pca.csv IGNORE=@\n", conversionContext.getDataStatement(ds))
	}

	@Test
	public void shouldBeCorrectHeadersEx3() {
		setupTest(example3location)

		List<String> headers = new ArrayList<String>()
		headers.add("ID")
		headers.add("TIME")
		headers.add("DV")
		headers.add("ARM")
		headers.add("WEIGHT")
		headers.add("AMT")
		headers.add("MDV")
		headers.add("EVID")
		assertEquals(headers, ds.headers)
	}

	@Test
	public void shouldBeCorrectHeadersEx5() {
		setupTest(example5location)
		
		List<String> headers = new ArrayList<String>()
		headers.add("ID")
		headers.add("TIME")
		headers.add("DV")
		headers.add("ARM")
		headers.add("AMT")
		headers.add("MDV")
		headers.add("EVID")
		assertEquals(headers, ds.headers)
	}

	@Test
    public void testExample3() {
        setupTest(example3location)
        assertEquals("\$EST METHOD=COND INTER MAXEVALS=9999 PRINT=10 NOABORT\n\$COV\n", conversionContext.getEstimationStatement().toString())
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