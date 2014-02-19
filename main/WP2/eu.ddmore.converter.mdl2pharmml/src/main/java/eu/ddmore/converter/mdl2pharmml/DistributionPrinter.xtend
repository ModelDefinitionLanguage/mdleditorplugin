package eu.ddmore.converter.mdl2pharmml

import org.ddmore.mdl.mdl.RandomList
import org.ddmore.mdl.mdl.DistributionArguments 
import org.ddmore.mdl.mdl.DistributionArgument 
import org.ddmore.mdl.mdl.Primary 
import org.ddmore.mdl.mdl.FullyQualifiedSymbolName
import org.ddmore.mdl.mdl.FullyQualifiedArgumentName
import org.ddmore.mdl.mdl.Vector
import eu.ddmore.converter.mdlprinting.MdlPrinter

class DistributionPrinter extends MdlPrinter {

	val xmlns_uncert="http://www.uncertml.org/3.0"; 
	val definition_normal = "http://www.uncertml.org/distributions/normal";
	val definition_bernoulli = "http://www.uncertml.org/distributions/bernoulli";

	public def print_uncert_Distribution(RandomList randomList){
		if (randomList != null){
			if (randomList.arguments != null){
				var type = randomList.arguments.getAttribute("type");
				switch (type){
					case type.equals("Bernoulli"): return randomList.arguments.print_Bernoulli
					case type.equals("Normal"): return randomList.arguments.print_Normal
					default: ""
				}
			}
		}
	}
	
	def print_Bernoulli(DistributionArguments args){
		var probabilities = args.findAttribute("probabilities");
		'''
			<BernoulliDistribution xmlns="«xmlns_uncert»" definition="«definition_bernoulli»">
				«IF probabilities != null»
				<probabilities>
					«probabilities.valueToStr»
				</probabilities>
				«ENDIF»
			</BernoulliDistribution>
		'''
	}
	
	def print_Normal(DistributionArguments args){
		var mean = args.findAttribute("mean");
		var variance = args.findAttribute("variance");
		'''
			<NormalDistribution xmlns="«xmlns_uncert»" definition="«definition_normal»">
				«IF mean != null»
				<mean>
					«mean.valueToStr»
				</mean>
				«ENDIF»
				«IF variance != null»
				<variance>
					«variance.valueToStr»
				</variance>
				«ENDIF»
			</NormalDistribution>
		'''
	}
	
	override valueToStr(DistributionArgument arg){
		if (arg.distribution != null)
			return arg.distribution.identifier;
		if (arg.value != null)
			return arg.value.toStr;
		return "";	
	}	
	
	override toStr(Primary p){
		if (p.number != null){
			return '''<rVal>«p.number»</rVal>''';
		}
		if (p.symbol != null){
			return '''<var varId="«p.symbol.toStr»"/>'''; 
		}
		if (p.functionCall != null) {
			return "";
			//TODO: inherit DistributionPrinter from PharmML MathPrinter and call function converter
		}
		if (p.vector != null) {
			return p.vector.toStr;
		}
		if (p.attribute != null) {
			return '''<var varId="«p.attribute.toStr»"/>'''; 
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
	
	//TODO: How to print attribute in PharmML
	override toStr(FullyQualifiedArgumentName s){
		return s.parent.toStr;
	}
}