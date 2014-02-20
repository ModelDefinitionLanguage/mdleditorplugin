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
import org.ddmore.mdl.mdl.Vector
import org.ddmore.mdl.mdl.DistributionArgument
import eu.ddmore.converter.mdlprinting.MdlPrinter

//TODO: test for all types of distributions 
//TODO: document + examples
class DistributionPrinter extends MdlPrinter{

	//TODO: external parameters? What if the location changes?
	val xmlns_uncert="http://www.uncertml.org/3.0"; 
	val definition = "http://www.uncertml.org/distributions/";
	
	//Recognised types of distributions
	val distribution_attrs = newHashMap(
		'Bernoulli' 			-> newHashSet("probabilities"),          
		'Beta'  				-> newHashSet("alpha", "beta"),              
		'Binomial'  			-> newHashSet("numberOfTrials", "probabilityOfSuccess"),
		'Categorical'  			-> newHashSet(), //yet to be defined in the spec at http://www.uncertml.org/3.0
		'Cauchy'            	-> newHashSet("location", "scale"),
		'ChiSquare'         	-> newHashSet("degreesOfFreedom"), 
		'Dirichlet' 	 		-> newHashSet("concentration"),
		'Exponential'  	    	-> newHashSet("rate"),
		'FDistribution'     	-> newHashSet("denominator, numerator"),
		'Gamma'             	-> newHashSet("shape", "scale"), 
		'Geometric'         	-> newHashSet("probability"), 
		'Hypergeometric'    	-> newHashSet("numberOfSuccesses", "numberOfTrials", "populationSize"), 
		'InverseGamma'      	-> newHashSet("shape", "scale"), 
		'Laplace'          	 	-> newHashSet("location", "scale"), 
		'Logistic'         	 	-> newHashSet("location", "scale"), 
		'LogNormal'         	-> newHashSet("logScale", "shape"), 
		'Multinomial'       	-> newHashSet("numberOfTrials", "probabilities"), 
		'MultivariateNormal'	-> newHashSet("mean", "covarianceMatrix"), //"dimension"
		'MultivariateStudentT'	-> newHashSet("mean", "covarianceMatrix"), //"dimension"
		'NegativeBinomial'    	-> newHashSet("numberOfFailures", "probability"),
		'Normal'              	-> newHashSet("mean", "variance", "standardDeviation"),
		'NormalInverseGamma' 	-> newHashSet("mean", "varianceScaling", "shape", "scale"),
		'Pareto'              	-> newHashSet("scale", "shape"),
		'Poisson'            	-> newHashSet("rate"),
		'StudentT'            	-> newHashSet("location", "scale", "degreesOfFreedom"),
		'Uniform'            	-> newHashSet("minimum", "maximum"),
		'Weibull'             	-> newHashSet("scale", "shape"),
		'Wishart'             	-> newHashSet("degreesOfFreedom", "scaleMatrix")
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
				«IF arg.identifier != null»
					«IF recognizedArgs.contains(arg.identifier)»
						«IF matrix_attrs.contains(arg.identifier)»
							«var dimension = defineDimension(randomList, arg)»
							<«arg.identifier» dimension="«dimension»">
								<values>«arg.value.toStr»</values>
							</«arg.identifier»>
						«ELSE»	
							<«arg.identifier»>
								«arg.valueToStr»
							</«arg.identifier»>
						«ENDIF»
					«ENDIF»
				«ENDIF»
			«ENDFOR»	
		</«type»Distribution>
		'''
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
	//Reference to attributes and function call are fine according to MDL grammar but not PharmML specification
	//TODO: either restrict grammar or extend PharmML
	override toStr(Primary p){
		if (p.number != null){
			return '''<rVal>«p.number»</rVal>''';
		}
		if (p.symbol != null){
			return '''<var varId="«p.symbol.toStr»"/>'''; 
		}
		if (p.vector != null) {
			return p.vector.toStr;
		}
		if (p.functionCall != null) {
			return ""; //function calls not supported in distributions
		}
		if (p.attribute != null) {
			return "";	//references to attributes not supported in distributions 
		}
	}
	
	def valueToStr(Primary p){
		if (p.number != null){
			return p.number;
		}
		if (p.symbol != null){
			return p.symbol.toStr; 
		}
		if (p.vector != null) {
			return p.vector.toStr;
		}
	}
	
	//Convert a vector c(1, 2, 3...) to a flattened list (1 2 3...)
	//Should work fine with matrices c(c(1,2,3...),... c(10, 20, 30...)) as well
	//TODO: test, possibly add validation that matrix in a form of nested lists is well-defined (i.e., N x M or N x N).
	override toStr(Vector v) { 
		var res  = "";
		var iterator = v.values.iterator();
		if (iterator.hasNext) {
			res = res + iterator.next.valueToStr;
		}
		while (iterator.hasNext){
			res  = res + ' ';
			res = res + iterator.next.valueToStr;
		}
		return res;
	}
	
	//For references in distributions we just print its name and 
	//do not point to the PharmML block (MDL object) it appears  
	override toStr(FullyQualifiedSymbolName s){
		return s.identifier;
	}
}