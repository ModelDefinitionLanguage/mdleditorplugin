package eu.ddmore.converter.mdl2pharmml08

import eu.ddmore.mdl.mdl.AnonymousListStatement
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.provider.BlockArgumentDefinitionProvider
import eu.ddmore.mdl.provider.BlockDefinitionTable
import eu.ddmore.mdl.provider.ListDefinitionProvider
import eu.ddmore.mdl.utils.DomainObjectModelUtils
import eu.ddmore.mdl.utils.ExpressionUtils
import eu.ddmore.mdl.utils.MdlUtils
import eu.ddmore.mdllib.mdllib.Expression
import eu.ddmore.mdllib.mdllib.SymbolDefinition
import java.util.Comparator
import java.util.HashMap
import java.util.Map
import java.util.TreeMap

import static eu.ddmore.converter.mdl2pharmml08.Constants.*
import eu.ddmore.mdl.mdl.RandomVariableDefinition

abstract class AbstractParameterWriter {
	extension MdlUtils mu = new MdlUtils
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	extension ExpressionUtils eu = new ExpressionUtils
	extension PharmMLExpressionBuilder peb = new PharmMLExpressionBuilder 
	extension DomainObjectModelUtils domu = new DomainObjectModelUtils
	extension BlockArgumentDefinitionProvider badp = new BlockArgumentDefinitionProvider 
	extension DistributionPrinter dp = new DistributionPrinter 
	
	
	//A helper class for sorting maps (according to their integer values) 
	static class ValueComparator implements Comparator<String> {
	    var Map<String, Integer> base;
	    new(Map<String, Integer> base) {
	        this.base = base;
	    }
	    override compare(String a, String b) {
	        if (base.get(a) >= base.get(b)) {
	            return -1;
	        } else {
	            return 1;
	        } 
	    }
	}
	
	
	var MclObject mObj
	
	new(MclObject mdlObj){
		mObj = mdlObj
	}

	abstract def String writeSimpleParameter(SymbolDefinition stmt)
	
	abstract def String writeParameter(SymbolDefinition stmt)

	def writeVariabilityModel(){
		val vm_err_vars = new HashMap<String, Integer>
		val vm_mdl_vars = new HashMap<String, Integer>
		for(stmt : mObj.mdlVariabilityLevels){
			switch(stmt){
				ListDefinition:{
					if(stmt.firstAttributeList.getAttributeEnumValue('type') == 'parameter'){
						vm_mdl_vars.put(stmt.name, stmt.firstAttributeList.getAttributeExpression('level').integerValue)
					}
					else{
						vm_err_vars.put(stmt.name, stmt.firstAttributeList.getAttributeExpression('level').integerValue)
					}
				}
			}	
		}
		val topLevel = topLevelInsertion
		if(topLevel != null){
			val lvlNum = vm_mdl_vars.values.max
			vm_mdl_vars.put(topLevel, lvlNum+1)
		}
		var model = "";
		if (vm_err_vars.size() > 0){
			model = model + vm_err_vars.writeVariabilityModel("vm_err", VAR_TYPE_ERROR);
		}		
		if (vm_mdl_vars.size() > 0){
			model = model + vm_mdl_vars.writeVariabilityModel("vm_mdl", VAR_TYPE_PARAMETER);
		}
		return model;
	}
	
	abstract def String getTopLevelInsertion()
	
	def private writeVariabilityModel(Map<String, Integer> vars, String blkId, String varType){
		var model = "";
		if (vars.size() > 0){
			var bvc =  new ValueComparator(vars);
			var sorted_map = new TreeMap<String, Integer>(bvc);
			sorted_map.putAll(vars);
			var prev = "";
			var levels = "";
			for (s: sorted_map.entrySet){
				levels = levels +	'''
					«IF prev.length > 0»
						<Level referenceLevel="false" symbId="«s.key»">
							<ParentLevel>
								<ct:SymbRef symbIdRef="«prev»"/>
							</ParentLevel>
						</Level>
					«ELSE»
						<Level referenceLevel="false" symbId="«s.key»"/>
					«ENDIF»
				'''
				prev = s.key
			}			
			model = '''
				<VariabilityModel blkId="«blkId»" type="«varType»">
					«levels»
				</VariabilityModel>
			'''		
		}		
		return model;
	}

	def MclObject getMdlObj(){
		this.mObj
	}
	

	abstract def String writeParameterModel()

//	/////////////////////////////
//	// I.d_1 CorrelationModel
//	/////////////////////////////
	def print_mdef_CollerationModel(MclObject mObj){
		var model = "";
		for (b: mObj.getBlocksByName(BlockDefinitionTable::MDL_RND_VARS)){
			val level = b.blkArgs.getArgumentExpression('level').symbolRef
			for(s : b.statementsFromBlock){
				if(s instanceof AnonymousListStatement){
					if(s.list.hasAttribute('type')){
						val corrType = s.list.getAttributeEnumValue('type')
						val rv1 = s.list.getAttributeExpression('rv1').symbolRef
						val rv2 = s.list.getAttributeExpression('rv2').symbolRef
						val value = s.list.getAttributeExpression('value')
						model += print_mdef_Correlation(corrType, level, rv1, rv2, value)
					}
				}
			}
		}
		model
	}
	
	def getLevel(MclObject mObj, SymbolDefinition randomVar){
		val randVar = mObj.findMdlSymbolDefn(randomVar.name)
		randVar.getRandomVarLevel
	}
	
	def print_mdef_Correlation(String type, SymbolReference level, SymbolReference rv1, SymbolReference rv2, Expression value)'''
		<Correlation>
			«IF level != null»
				<ct:VariabilityReference>
					«level.symbolReference»
				</ct:VariabilityReference>
			«ENDIF»
			<Pairwise>
				<RandomVariable1>
					«rv1.localSymbolReference»
				</RandomVariable1>
				<RandomVariable2>
					«rv2.localSymbolReference»
				</RandomVariable2>
				«IF type == 'correlation'»
					<CorrelationCoefficient>
						«value.expressionAsAssignment»
					</CorrelationCoefficient>
				«ELSEIF type == 'covariance'»
					<Covariance>
						«value.expressionAsAssignment»
					</Covariance>
				«ELSE»
					<Error!/>
				«ENDIF»
			</Pairwise>
		</Correlation>
		'''


	def writeRandomVariable(RandomVariableDefinition stmt, SymbolReference level)'''
		<RandomVariable symbId="«stmt.name»">
			<ct:VariabilityReference>
				«level.pharmMLExpr»
			</ct:VariabilityReference>
			«writeDistribution(stmt.distn)»
		</RandomVariable>
	'''
		
}

