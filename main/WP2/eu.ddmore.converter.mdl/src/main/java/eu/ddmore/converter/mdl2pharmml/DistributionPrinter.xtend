/* 
 * MDL converter toolbox, @DDMoRe
 * Author: Natallia Kokash, LIACS, 2014
 * 
 * A class to convert distributions from MDL to PharmML
 */
package eu.ddmore.converter.mdl2pharmml

import org.ddmore.mdl.mdl.RandomList
import org.ddmore.mdl.mdl.Primary 
import org.ddmore.mdl.mdl.FullyQualifiedSymbolName
import org.ddmore.mdl.mdl.DistributionArgument
import eu.ddmore.converter.mdlprinting.MdlPrinter

//TODO: test for all types of distributions 
//TODO: document + examples
class DistributionPrinter extends MdlPrinter{

	//TODO: external parameters? What if the location changes?
	val xmlns_uncert="http://www.uncertml.org/3.0"; 
	val definition = "http://www.uncertml.org/distributions/";
	
	val nVal = "nVal";          //natural number
	val pnVal = "pnVal";       //positive natural 
	val rVal = "rVal";         //rVal number
	val prVal = "prVal";      //positive rVal
	val pVal = "pVal";  //probability type
	
	//Recognised types of distributions and pairs (attribute, value type) to print as PharmML tags
	val distribution_attrs = newHashMap(
		'Bernoulli' -> newHashMap(
			"probability" -> pVal),          
		'Beta'  -> newHashMap(
			"alpha" -> rVal, 
			"beta" -> rVal,  
			"truncationLowerInclusiveBound" -> pVal, 
			"truncationUpperInclusiveBound" -> pVal),  
		'Binomial' -> newHashMap(
			"numberOfTrials" -> nVal, 
			"probabilityOfSuccess" -> pVal, 
			"truncationLowerInclusiveBound" -> nVal, 
			"truncationUpperInclusiveBound" -> nVal),
		'Categorical' -> newHashMap(
			"ncategories" -> nVal, 
			"probabilities" -> pVal), 
		'Cauchy' -> newHashMap(
			"location" -> rVal, 
			"scale" -> prVal, 
			"truncationLowerInclusiveBound" -> nVal, 
			"truncationUpperInclusiveBound" -> nVal),
		'ChiSquare' -> newHashMap(
			"degreesOfFreedom" -> pnVal, "dof" -> pnVal, 
			"truncationLowerInclusiveBound" -> prVal, 
			"truncationUpperInclusiveBound" -> prVal), 
		'Dirichlet' -> newHashMap(
			"concentration" -> prVal),
		'Exponential' -> newHashMap(
			"rate" -> prVal, 
			"truncationLowerInclusiveBound" -> prVal, 
			"truncationUpperInclusiveBound" -> prVal),
		'FDistribution' -> newHashMap(
			"denominator" -> nVal, 
			"numerator" -> nVal, 
			"truncationLowerInclusiveBound" -> prVal, 
			"truncationUpperInclusiveBound" -> prVal),
		'Gamma' -> newHashMap(
			"shape" -> prVal, 
			"scale" -> prVal, 
			"truncationLowerInclusiveBound" -> rVal, 
			"truncationUpperInclusiveBound" -> rVal),
		'Geometric' -> newHashMap(
			"probability" -> pVal, 
			"truncationLowerInclusiveBound" -> rVal, 
			"truncationUpperInclusiveBound" -> rVal),
		'Hypergeometric' -> newHashMap(
			"numberOfSuccesses" -> nVal, 
			"numberOfTrials" -> nVal, 
			"populationSize" -> nVal, 
			"truncationLowerInclusiveBound" -> rVal, 
			"truncationUpperInclusiveBound" -> rVal), 
		'InverseGamma' -> newHashMap(
			"shape" -> prVal, 
			"scale" -> prVal, 
			"truncationLowerInclusiveBound" -> rVal, 
			"truncationUpperInclusiveBound" -> rVal), 
		'Laplace' -> newHashMap(
			"location" -> rVal, 
			"scale" -> prVal,
			"truncationLowerInclusiveBound" -> rVal, 
			"truncationUpperInclusiveBound" -> rVal), 
		'Logistic' -> newHashMap(
			"location" -> rVal, 
			"scale" -> prVal, 
			"truncationLowerInclusiveBound" -> rVal, 
			"truncationUpperInclusiveBound" -> rVal), 
		'LogNormal' -> newHashMap(
			"logScale" -> rVal, 
			"shape" -> prVal, 
			"truncationLowerInclusiveBound" -> rVal, 
			"truncationUpperInclusiveBound" -> rVal),
		'Multinomial' -> newHashMap(
			"numberOfTrials" -> nVal, 
			"probabilities" -> pVal), 
		'MultivariateNormal' -> newHashMap(
			"meanVector" -> rVal, 
			"covarianceMatrix" -> rVal), 
		'MultivariateStudentT'	-> newHashMap(
			"meanVector" -> rVal, 
			"covarianceMatrix" -> rVal, 
			"degreesOfFreedom" -> pnVal, "dof" -> pnVal), 
		'NegativeBinomial'    	-> newHashMap(
			"numberOfFailures" -> nVal, 
			"probability" -> pVal,  
			"truncationLowerInclusiveBound" -> rVal, 
			"truncationUpperInclusiveBound" -> rVal),
		'Normal' -> newHashMap(
			"mean" -> rVal, 
			"variance" -> prVal, 
			"stddev" -> prVal, 
			"truncationLowerInclusiveBound" -> rVal, 
			"truncationUpperInclusiveBound" -> rVal),
		'NormalInverseGamma' 	-> newHashMap(
			"mean" -> rVal, 
			"varianceScaling" -> prVal, 
			"shape" -> prVal, 
			"scale" -> prVal,
			"truncationLowerInclusiveBoundN" -> nVal, 
			"truncationUpperInclusiveBoundN" -> nVal, 
			"truncationLowerInclusiveBoundIG" -> rVal, 
			"truncationUpperInclusiveBoundIG" -> rVal),
		'Pareto' -> newHashMap(
			"scale" -> prVal, 
			"shape" -> prVal, 
			"truncationLowerInclusiveBound" -> rVal, 
			"truncationUpperInclusiveBound" -> rVal),
		'Poisson' -> newHashMap(
			"rate" -> prVal, 
			"truncationLowerInclusiveBound" -> rVal, 
			"truncationUpperInclusiveBound" -> rVal),
		'StudentT' -> newHashMap(
			"location" -> rVal, 
			"scale" -> prVal, 
			"degreesOfFreedom" -> pnVal, "dof" -> pnVal, 
			"truncationLowerInclusiveBound" -> rVal, 
			"truncationUpperInclusiveBound" -> rVal),
		'Uniform' -> newHashMap(
			"minimum" -> rVal, 
			"maximum" -> rVal, 
			"numberOfClasses" -> nVal),
		'Weibull' -> newHashMap(
			"scale" -> prVal, 
			"shape" -> prVal, 
			"truncationLowerInclusiveBound" -> rVal, 
			"truncationUpperInclusiveBound" -> rVal),
		'Wishart' -> newHashMap(
			"degreesOfFreedom" -> rVal, "dof" -> rVal, 
			"scaleMatrix" -> prVal)
	)
	
	//Names of attributes that expect matrix as a value
	val matrix_attrs = newHashSet("covarianceMatrix", "scaleMatrix");	

	//Prints all distributions
	public def CharSequence print_uncert_Distribution(RandomList randomList){
		if (randomList != null){
			if (randomList.arguments != null){
				var type = randomList.arguments.getAttribute("type");
				if (type.length > 0){
					if (type.equals("FDistribution")) type = "F";	
					switch(type){
						case type.equals("MixtureModel"): print_MixtureModel(randomList)
						default: print_DistributionDefault(randomList, type)
					}
				}
			}
		}
	}
	
	//NOTE: all attributes in distributions are named
	protected def print_DistributionDefault(RandomList randomList, String type){
		val recognizedArgs = distribution_attrs.get(type);
		if (recognizedArgs == null) return "";
		'''
		<«type»Distribution xmlns="«xmlns_uncert»" definition="«type.getURLExtension»">
			«FOR arg: randomList.arguments.arguments»
				«IF arg.argumentName != null»
					«IF recognizedArgs.containsKey(arg.argumentName.identifier)»
						«val dataType = recognizedArgs.get(arg.argumentName.identifier)»
						«IF matrix_attrs.contains(arg.argumentName.identifier)»
							«var dimension = defineDimension(randomList, arg)»
							<«arg.argumentName.identifier.convertAttribute» dimension="«dimension»">
								<values>«arg.value.toPharmML(dataType)»</values>
							</«arg.argumentName.identifier.convertAttribute»>
						«ELSE»	
							<«arg.argumentName.identifier.convertAttribute»>
								«arg.value.toPharmML(dataType)»
							</«arg.argumentName.identifier.convertAttribute»>
						«ENDIF»
					«ENDIF»
				«ENDIF»
			«ENDFOR»	
		</«type»Distribution>
		'''
	}	
	
	protected def String convertAttribute(String attrName){
		switch(attrName){
			case "dof": "degreesOfFreedom"
			default: attrName
		}
	}
	
	//For distributions that expect matrix, determine its dimensions
	//First look for an explicitly specified dimensions
	//If the attribute dimensions is not found, calculate the dimensions based on the number of nested lists (not reliable)
	//e.g., c(c(1,2,...), c(10, 20,...), c(100, 200,...)) yields 3	
	protected def defineDimension(RandomList randomList, DistributionArgument matrixAttr){
		var dimension = randomList.arguments.getAttribute("dimension");
		if (dimension.length >= 0) {//explicit dimension is given
			try{
				Integer::parseInt(dimension);
			} 
			catch (NumberFormatException e){
			}
		}
		//compute from data 
		if (matrixAttr.value != null){
			if (matrixAttr.value.vector != null){
				val matrix = matrixAttr.value.vector;
				return matrix.values.size();
			}
		}
		return 0;
	}
	
	//Given distribution type, returns an URL to the XML schema
	//For example, MultivariateStudentT corresponds to multivariate-student-t
	protected def getURLExtension(String type){
		try{
			return definition + type.replaceAll("(.)([A-Z])", "$1-$2").toLowerCase();
		} 
		catch(Exception e){//just in case regEx fails...
			return definition + type.toLowerCase();
		}
	}
	
	//TODO: add mixed model to the grammar, e.g.,	X = ~(type=mixedModel, ~(type=Normal...), ~(type=StudentT...)...)
	def print_MixtureModel(RandomList randomList)
	'''
		<MixtureModelDistribution xmlns="«xmlns_uncert»" definition="«definition»mixture-model">
			«FOR arg: randomList.arguments.arguments»
				«IF arg.component != null»
					«val weight = arg.component.arguments.getAttribute("weight")»
					«IF weight.length > 0»
						<component weight="«weight»">
							«arg.component.print_uncert_Distribution»
						</component>
					«ENDIF»
				«ENDIF»
			«ENDFOR»
		</MixtureModelDistribution>
	'''
	
	//A value assigned to a distribution attribute can be a number, reference, or vector
	def toPharmML(Primary p, String type){
		if (p.number != null){
			return '''<«type»>«p.number»</«type»>''';
		}
		if (p.symbol != null){
			return '''<var varId="«p.symbol.toStr»"/>'''; 
		}
		if (p.vector != null) {
			return p.vector.toStr;
		}
	}
	
	//For references in distributions we just print its name and 
	//do not point to the PharmML block (MDL object) it appears  
	override toStr(FullyQualifiedSymbolName s){
		return s.identifier;
	}
}