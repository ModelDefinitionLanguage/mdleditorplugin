/* 
 * MDL converter toolbox, @DDMoRe
 * Author: Natallia Kokash, LIACS, 2014
 * 
 * Multiple values are not a problem, we print vector
 * */
package eu.ddmore.converter.mdl2pharmml

import org.ddmore.mdl.mdl.RandomList
import org.ddmore.mdl.mdl.Primary 
import org.ddmore.mdl.mdl.FullyQualifiedSymbolName
import org.ddmore.mdl.mdl.Vector
import org.ddmore.mdl.mdl.DistributionArgument
import eu.ddmore.converter.mdlprinting.MdlPrinter

class DistributionPrinter extends MdlPrinter{

	//TODO: all but mixtute_model, add mixed model to the grammar, e.g.,	
	//X = ~(type=mixedModel, ~(type=Normal...), ~(type=Gaussian...)...)
	
	//TODO: test for all types of distributions 
	//TOSO: document + examples
	//TODO: rewrite to work with omitted attribute names
	
	static val distribution_attrs = newHashMap(
		'Bernoulli' 			-> newHashSet("probabilities"),          
		'Beta'  				-> newHashSet("alpha", "beta"),              
		'Binomial'  			-> newHashSet("numberOfTrials", "probabilityOfSuccess"),
		'Categorical'  			-> newHashSet(),
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
	
	val matrix_attrs = newHashSet("covarianceMatrix", "scaleMatrix");
	
	val xmlns_uncert="http://www.uncertml.org/3.0"; 
	val definition = "http://www.uncertml.org/distributions/";

	public def print_uncert_Distribution(RandomList randomList){
		if (randomList != null){
			if (randomList.arguments != null){
				var type = randomList.arguments.getAttribute("type");
				if (type.length > 0){
					if (type.equals("FDistribution")) type = "F";	
					val recognizedArgs = distribution_attrs.get(type);
					switch(type){
						case type.equals("MixtureModel"): return print_MixtureModel(randomList)
						default:
							if (recognizedArgs != null){
								return 
								'''
								<«type»Distribution xmlns="«xmlns_uncert»" definition="«type.getURLExtension»">
									«FOR arg: randomList.arguments.arguments»
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
									«ENDFOR»	
								</«type»Distribution>
								'''
							}
						}
				}
			}
		}
	}
	
	protected def defineDimension(RandomList randomList, DistributionArgument matrixAttr){
		var dimension = randomList.arguments.getAttribute("dimension");
		if (dimension.length >= 0) {
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
	
	def getURLExtension(String type){
		try{
			return definition + type.replaceAll("(.)([A-Z])", "$1-$2").toLowerCase();
		} 
		catch(Exception e){//just in case regEx fails...
			return definition + type.toLowerCase();
		}
	}
	
	//TODO
	def print_MixtureModel(RandomList randomList)
	'''
		<MixtureModelDistribution xmlns="«xmlns_uncert»" definition="«definition»mixture-model">
			
		</MixtureModelDistribution>
	'''
	
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
	
	override toStr(Vector v) { 
		var res  = v.identifier + '(';
		var iterator = v.values.iterator();
		if (iterator.hasNext) {
			res = res + iterator.next.toStr;
		}
		while (iterator.hasNext){
			res  = res + ' ';
			res = res + iterator.next.toStr;
		}
		return res + ')';
	}
	
	//ignore object name or put it to blkRefId?
	override toStr(FullyQualifiedSymbolName s){
		return s.identifier;
	}
}