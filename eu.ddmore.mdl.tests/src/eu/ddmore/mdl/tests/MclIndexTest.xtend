package eu.ddmore.mdl.tests

import com.google.inject.Inject
import eu.ddmore.mdl.mdl.Mcl
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class MclIndexTest {
	@Inject extension LibraryTestHelper<Mcl>
	@Inject extension ValidationTestHelper
	@Inject ResourceDescriptionsProvider rdp
	
	val static CODE_SNIPPET = '''
warfarin_PK_SEXAGE_mdl = mdlObj {
	IDV{T}

	COVARIATES{
		WT
		AGE
		SEX withCategories {female, male, MISSING}
		logtWT = ln(WT/70)
		tAGE = AGE - 40
	}

	VARIABILITY_LEVELS{
	}

	STRUCTURAL_PARAMETERS {
		POP_FCL_FEM
	} # end STRUCTURAL_PARAMETERS
	
	GROUP_VARIABLES{
		FSEXCL = if(SEX == SEX.female) then POP_FCL_FEM  else 1
	}
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
} # end of model object
'''
	
	@Test
	def void testParsing(){
		CODE_SNIPPET.parse.assertNoErrors
		
	}

	@Test
	def void testExpectedEObjectDescriptions(){
		CODE_SNIPPET.parse.assertExportedEObjectDescriptions("warfarin_PK_SEXAGE_mdl, warfarin_PK_SEXAGE_mdl.T, warfarin_PK_SEXAGE_mdl.WT, warfarin_PK_SEXAGE_mdl.AGE, warfarin_PK_SEXAGE_mdl.SEX, warfarin_PK_SEXAGE_mdl.SEX.female, warfarin_PK_SEXAGE_mdl.SEX.male, warfarin_PK_SEXAGE_mdl.SEX.MISSING, warfarin_PK_SEXAGE_mdl.logtWT, warfarin_PK_SEXAGE_mdl.tAGE, warfarin_PK_SEXAGE_mdl.POP_FCL_FEM, warfarin_PK_SEXAGE_mdl.FSEXCL, warfarin_PK_SEXAGE_mdl.F, warfarin_PK_SEXAGE_mdl.Y")
	}
	
	def private assertExportedEObjectDescriptions(EObject o, CharSequence expected){
		Assert::assertEquals(expected.toString,
			o.getExportedEObjectDescriptions.map[qualifiedName].join(", ")
			);
	}
	
	def getResourceDescription(EObject o){
		val index = rdp.getResourceDescriptions(o.eResource);
		index.getResourceDescription(o.eResource.URI)
	}
	
	def getExportedEObjectDescriptions(EObject o){
		o.getResourceDescription.getExportedObjects
	}
}