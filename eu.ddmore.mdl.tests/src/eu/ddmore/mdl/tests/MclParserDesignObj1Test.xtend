package eu.ddmore.mdl.tests

import com.google.inject.Inject
import eu.ddmore.mdl.mdl.Mcl
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class MclParserDesignObj1Test {
	@Inject extension MdlTestHelper<Mcl>
	@Inject extension ValidationTestHelper
	
	val static CODE_SNIPPET = '''
warfarin_design = designObj {
	DECLARED_VARIABLES{
		GUT::dosingTarget; Y::observation
	}
	
	# trial design
	# arm 1 is placebo
	# arm 2 is 2.5 mg dosing time given, then interdose (II) period, the number of doses (addl)
	# arm 3 is 5 mg
	# arm 4 is 10 mg

	# wgt covariate is simulating

	DESIGN_PARAMETERS{
		# again there is some repetition here so allowing a variable defn here would be useful
		doseStart = 0
		doseEnd = 24
		doseGap = 2
		baseAmt = 2.5
«««		WT_MEAN = 85.5
«««		WT_VAR = 19
		epochStart = 0
		epochEnd = 24
«««		varlevel = bsv_lvl
	}

«««	COVARIATES{
«««	    WT ~ Normal(mean=WT_MEAN, var=WT_VAR)
«««	}


	INTERVENTION{

		# could use an adm type instead so do
		dreg1 : { type is bolus, input=GUT, doseTime=seq(doseStart, doseEnd, doseGap), amount=baseAmt*0}
		dreg2 : { type is bolus, input=GUT, doseTime=seq(doseStart, doseEnd, doseGap), amount=baseAmt*0}
		dreg3 : { type is bolus, input=GUT, doseTime=seq(doseStart, doseEnd, doseGap), amount=baseAmt*0}
		dreg4 : { type is bolus, input=GUT, doseTime=seq(doseStart, doseEnd, doseGap), amount=baseAmt*0}
	}

	STUDY_DESIGN{
		arm1 : {
		     	armSize = 10,
		     	interventionSequence = {
		       		admin = dreg1,
		       		start = [epochStart, epochEnd]
		      	},
		      	samplingSequence = {
		      		sample = samp,
		      		start = 0
		      	}
		}
		arm2 : {
		     armSize = 20,
		     interventionSequence = {
		     		admin = dreg2,
		       		start = [epochStart, epochEnd]
		     },
	      	samplingSequence = {
	      		sample = samp,
	      		start = 0
	      	}
		}
		arm3 : {
		     armSize = 20,
		     interventionSequence = {
			       admin = dreg3,
			       start = [epochStart, epochEnd]
			 },
	      	samplingSequence = {
	      		sample = samp,
	      		start = 0
	      	}
		}
		arm4 : {
		     armSize = 20,
		     interventionSequence = {
			       admin = dreg4,
			       start = [epochStart, epochEnd]
			 },
	      	samplingSequence = {
	      		sample = samp,
	      		start = 0
	      	}
		}
	}
	
	SAMPLING{
		samp : { type is simple, sampleTime = [ 0, 20, 40, 60 ], outcome = Y } 
	}
}
		'''
	
	@Test
	def void testParsing(){
		CODE_SNIPPET.parse.assertNoErrors
		
	}
	
	
}
