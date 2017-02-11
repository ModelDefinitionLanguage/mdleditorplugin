package eu.ddmore.converter.mdl2pharmml08

import com.google.inject.Inject
import eu.ddmore.mdl.MdlAndLibInjectorProvider
import eu.ddmore.mdl.MdlTestHelper
import eu.ddmore.mdl.mdl.Mcl
import java.io.File
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.After
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class Mcl2PharmMLConverterTest {
	static val CONVERTED_OUTPUT_DIR="convertedFiles08/"
	static val USE_CASE_DIR="testMdlFiles/"
	
	@Inject extension MdlTestHelper<Mcl>
	@Inject extension ValidationTestHelper

	extension ConverterTestHarness cth = new ConverterTestHarness
	
	@Before
	def void setUp(){
		val convertedDir = new File(CONVERTED_OUTPUT_DIR)
		if(!convertedDir.exists) convertedDir.mkdir
	}
	
	
	@After
	def void tearDown(){
	}
	
	private def validateConversion(String useCaseName){
		val mclFile = USE_CASE_DIR + useCaseName + ".mdl"
		val mcl = readFile(mclFile).parse
		mcl.assertNoErrors
		val pharmMLFile = CONVERTED_OUTPUT_DIR + useCaseName + ".xml" 
		mcl.convertTo(pharmMLFile)
		assertIsValid(pharmMLFile)
	} 
	
	@Test
	def void testUseCase1(){
		validateConversion("UseCase1")
	}

	@Test
	def void testUseCase1_2(){
		validateConversion("UseCase1_2")
	}

	@Test
	def void testUseCase2(){
		validateConversion("UseCase2")
	}

	@Test
	def void testUseCase2_1(){
		validateConversion("UseCase2_1")
	}

	@Test
	def void testUseCase2_2(){
		validateConversion("UseCase2_2")
	}

	@Test
	def void testUseCase3(){
		validateConversion("UseCase3")
	}

	@Test
	def void testUseCase3_1(){
		validateConversion("UseCase3_1")
	}

	@Test
	def void testUseCase3_design1_eval(){
		validateConversion("UseCase3_design1_eval")
	}

	@Test
	def void testUseCase4_design1_eval(){
		validateConversion("UseCase4_design1_eval")
	}

	@Test
	def void testUseCase4(){
		validateConversion("UseCase4")
	}

	@Test
	def void testUseCase4_1(){
		validateConversion("UseCase4_1")
	}

	@Test
	def void testUseCase4_2(){
		validateConversion("UseCase4_2")
	}

	@Test
	def void testUseCase5(){
		validateConversion("UseCase5")
	}

	@Ignore("Currently invalid MDL")
	def void testUseCase5_1(){
		validateConversion("UseCase5_1")
	}

	@Test
	def void testUseCase5_2(){
		validateConversion("UseCase5_2")
	}

	@Test
	def void testUseCase5_3(){
		validateConversion("UseCase5_3")
	}

	@Test
	def void testUseCase6(){
		validateConversion("UseCase6")
	}

	@Test
	def void testUseCase6_2(){
		validateConversion("UseCase6_2")
	}

	@Test
	def void testUseCase6_3(){
		validateConversion("UseCase6_3")
	}

	@Test
	def void testUseCase7(){
		validateConversion("UseCase7")
	}

	@Test
	def void testUseCase8(){
		validateConversion("UseCase8")
	}

	@Test
	def void testUseCase8_1(){
		validateConversion("UseCase8_1")
	}

	@Ignore("Unresolved typing issue")
	def void testUseCase8_4(){
		validateConversion("UseCase8_4")
	}

	@Test
	def void testUseCase9(){
		validateConversion("UseCase9")
	}

	@Test
	def void testUseCase10(){
		validateConversion("UseCase10")
	}

	@Test
	def void testUseCase10_1(){
		validateConversion("UseCase10_1")
	}

	@Test
	def void testUseCase11(){
		validateConversion("UseCase11")
	}

	@Test
	def void testUseCase12(){
		validateConversion("UseCase12")
	}

	@Test
	def void testUseCase12_1(){
		validateConversion("UseCase12_1")
	}

	@Test
	def void testUseCase12_2(){
		validateConversion("UseCase12_2")
	}

	@Test
	def void testUseCase13(){
		validateConversion("UseCase13")
	}

	@Test
	def void testUseCaseMixedObs(){
		validateConversion("UseCaseMixedObs")
	}

	@Test
	def void testUseCase13_1(){
		validateConversion("UseCase13_1")
	}

	@Test
	def void testUseCase14(){
		validateConversion("UseCase14")
	}

//	@Ignore("Not supported at present")
	@Test
	def void testUseCase14_1(){
		validateConversion("UseCase14_1")
	}

//	@Ignore("Not supported at present")
	@Test
	def void testUseCase14_2(){
		validateConversion("UseCase14_2")
	}

	@Test
	def void testUseCase15(){
		validateConversion("UseCase15")
	}

	@Test
	def void testUseCase16(){
		validateConversion("UseCase16")
	}

	@Test
	def void testUseCase16_1(){
		validateConversion("UseCase16_1")
	}

	@Test
	def void testUseCase17(){
		validateConversion("UseCase17")
	}

	@Test
	def void testUseCase20(){
		validateConversion("UseCase20")
	}

	@Test
	def void testUseCase21(){
		validateConversion("UseCase21")
	}

	@Test
	def void testUseCase23(){
		validateConversion("UseCase23")
	}

	@Test
	def void testUseCase24(){
		validateConversion("UseCase24")
	}

	@Test
	def void testUseCase101(){
		validateConversion("UseCase101")
	}

	@Test
	def void testUseCase130(){
		validateConversion("UseCase130")
	}

	@Test
	def void testUseCase102(){
		validateConversion("UseCase102")
	}

	@Test
	def void testUseCase103(){
		validateConversion("UseCase103")
	}

	@Test
	def void testUseCase104(){
		validateConversion("UseCase104")
	}

	@Test
	def void testUseCase105(){
		validateConversion("UseCase105")
	}
	
	@Test
	def void testUseCase106(){
		validateConversion("UseCase106")
	}
	
	@Test
	def void testUseCase107(){
		validateConversion("UseCase107")
	}
	
	@Test
	def void testUseCase108(){
		validateConversion("UseCase108")
	}
	
	@Test
	def void testUseCase109(){
		validateConversion("UseCase109")
	}

	@Test
	def void testUseCase110(){
		validateConversion("UseCase110")
	}

	@Test
	def void testUseCase111(){
		validateConversion("UseCase111")
	}

	@Test
	def void testUseCaseSimeoniDesign(){
		validateConversion("simeoniDesign")
	}

	@Test
	def void testUseCase1PRIOR_Cauchy_UNIPV(){
		validateConversion("UseCase1_PRIOR_Cauchy_UNIPV")
	}

	@Test
	def void testUseCase1_PRIOR_NoParObj(){
		validateConversion("UseCase1_PRIOR_NoParObj")
	}

	@Test
	def void testUseCase1_PRIOR_UNIPV(){
		validateConversion("UseCase1_PRIOR_UNIPV")
	}

	@Test
	def void testUseCase1_PRIOR_FromData(){
		validateConversion("UseCase1_PRIOR_FromData")
	}

	@Test
	def void testUseCase1_PRIOR_FromData2(){
		validateConversion("UseCase1_PRIOR_FromData2")
	}

	@Test
	def void testExecutable_Simeoni_2004_oncology_TGI_Prior(){
		validateConversion("Executable_Simeoni_2004_oncology_TGI_Prior")
	}

	@Test
	def void testCM3(){
		validateConversion("CM3")
	}

	@Test
	def void testMODEL1_design(){
		validateConversion("MODEL1_design")
	}

	@Test
	def void testRocchetti_design(){
		validateConversion("Rocchetti_design")
	}

	@Test
	def void testExample3422_design(){
		validateConversion("example3422")
	}

	@Test
	def void testExample332_design(){
		validateConversion("example332")
	}

	@Test
	def void testComplex_PK_Model(){
		validateConversion("Complex_PK_Model")
	}
}
