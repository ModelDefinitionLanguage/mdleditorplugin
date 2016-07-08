package eu.ddmore.converter.mdl2pharmml08

import eu.ddmore.mdl.mdl.AnonymousListStatement
import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.EquationTypeDefinition
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.mdl.RandomVariableDefinition
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.mdl.util.MdlSwitch
import eu.ddmore.mdl.provider.BlockArgumentDefinitionProvider
import eu.ddmore.mdl.provider.BlockDefinitionTable
import eu.ddmore.mdl.provider.ListDefinitionProvider
import eu.ddmore.mdl.utils.DomainObjectModelUtils
import eu.ddmore.mdl.utils.ExpressionUtils
import eu.ddmore.mdl.utils.MdlUtils
import eu.ddmore.mdllib.mdllib.Expression
import eu.ddmore.mdllib.mdllib.SymbolDefinition
import java.util.Collections
import java.util.Comparator
import java.util.HashMap
import java.util.Map
import java.util.TreeMap
import org.eclipse.xtext.EcoreUtil2

import static eu.ddmore.converter.mdl2pharmml08.Constants.*

abstract class AbstractParameterWriter {
	extension MdlUtils mu = new MdlUtils
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	extension ExpressionUtils eu = new ExpressionUtils
	extension PharmMLExpressionBuilder peb = new PharmMLExpressionBuilder 
	extension DomainObjectModelUtils domu = new DomainObjectModelUtils
	extension BlockArgumentDefinitionProvider badp = new BlockArgumentDefinitionProvider 
	extension DistributionPrinter dp = new DistributionPrinter 
	extension ListIndivParamWriter lip = new ListIndivParamWriter
	extension FunctionIndivParamWriter fip = new FunctionIndivParamWriter
	
	
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
//	val () => SymbolDefinition findMatchingIdLevelInBlockLambda
	
	new(MclObject mdlObj){ //}, () => SymbolDefinition findMatchingIdLevelInBlockLambda){
		mObj = mdlObj
//		this.findMatchingIdLevelInBlockLambda = findMatchingIdLevelInBlockLambda
	}

	abstract def String writeSimpleParameter(SymbolDefinition stmt)
	
	abstract def String writeParameter(SymbolDefinition stmt)
	
	abstract def String writeAllDatasets()

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
			val lvlNum = if(vm_mdl_vars.isEmpty) if(vm_err_vars.isEmpty) 1 else vm_err_vars.values.max else vm_mdl_vars.values.max
			vm_mdl_vars.put(topLevel, lvlNum+1)
		}
		var model = "";
		if (vm_err_vars.size() > 0){
			model = model + mObj.writeVariabilityModel(vm_err_vars, "vm_err", VAR_TYPE_ERROR);
		}		
		if (vm_mdl_vars.size() > 0){
			model = model + mObj.writeVariabilityModel(vm_mdl_vars, "vm_mdl", VAR_TYPE_PARAMETER);
		}
		return model;
	}
	
	abstract def String getTopLevelInsertion()
	
	
	def private boolean isReferenceLevel(MclObject mdlObject, String levelName){
		val varLvlBlk = mdlObject.getBlocksByName(BlockDefinitionTable::VAR_LVL_BLK_NAME)
		if(!varLvlBlk.isEmpty){
			val refId = varLvlBlk.head.blkArgs?.getArgumentExpression('reference')?.symbolRef
			refId != null && refId.ref.name == levelName
		}
		else false
	}
	
	
	def private writeVariabilityModel(MclObject mdlObj, Map<String, Integer> vars, String blkId, String varType){
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
						<Level «IF mdlObj.isReferenceLevel(s.key)»referenceLevel="true" «ENDIF»symbId="«s.key»">
							<ParentLevel>
								<ct:SymbRef symbIdRef="«prev»"/>
							</ParentLevel>
						</Level>
					«ELSE»
						<Level «IF mdlObj.isReferenceLevel(s.key)»referenceLevel="true" «ENDIF»symbId="«s.key»"/>
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

	abstract def String writeParameters()	


	def private boolean isRvUsedDirectly(RandomVariableDefinition it){
		val visitor = new MdlSwitch<Boolean>(){
			override Boolean caseSymbolReference(SymbolReference ref){
				if(ref.ref.name == name){
					val anonList = EcoreUtil2.getContainerOfType(ref.eContainer, AnonymousListStatement)
					if(anonList != null){
						val blk = EcoreUtil2.getContainerOfType(anonList.eContainer, BlockStatement)
						val blkName = blk.blkId.name
						if(blkName == BlockDefinitionTable::MDL_POPULATION_BLK || blkName == BlockDefinitionTable::MDL_INDIV_PARAMS
							|| blkName == BlockDefinitionTable::OBS_BLK_NAME)
							Boolean.TRUE
						else
							Boolean.FALSE
					}
					else Boolean.FALSE
				}
				else Boolean.FALSE
			}
		}
		val parent = EcoreUtil2.getContainerOfType(eContainer, MclObject)
		val iter = parent?.eAllContents ?: Collections::emptyIterator
		var found = false
		while(iter.hasNext && !found){
			val node = iter.next
			if(visitor.doSwitch(node) == Boolean.TRUE)
				found = true	
		}
		found
	}
	
	def writeRandomVariables(BlockStatement b)'''
		«IF b.blkId.name == BlockDefinitionTable::MDL_RND_VARS»
			«FOR stmt: b.getNonBlockStatements»
				«switch(stmt){
					RandomVariableDefinition:{
						if(!stmt.isRvUsedDirectly)
							writeRandomVariable(stmt, b.getVarLevel)
					}
				}»
			«ENDFOR» 
		«ENDIF»
	'''

	def writeIndividualParameters(BlockStatement b)'''
		«IF b.blkId.name == BlockDefinitionTable::MDL_INDIV_PARAMS»
			«FOR stmt: b.getNonBlockStatements»
				«switch(stmt){
					EquationTypeDefinition:
						writeIndividualParameter(stmt)
					ListDefinition:
						writeIndividualParameter(stmt)
					AnonymousListStatement:
						writeIndividualParameter(stmt)
				}»
			«ENDFOR» 
		«ENDIF»
	'''

	def private writePopulationParameter(AnonymousListStatement it){
		val rvDefnRef = if(list.getAttributeEnumValue('type') == 'continuous')
			 				list.getAttributeExpression('continuous')
			 			else
			 				list.getAttributeExpression('categorical')

		if(rvDefnRef instanceof SymbolReference){
			val rvDefn = rvDefnRef.ref
			if(rvDefn instanceof RandomVariableDefinition){
				return 
					'''
					<PopulationParameter id="tst">
						«rvDefn.distn.writeDistribution»
					</PopulationParameter>
					'''
			}
		}
		''''''
	}

	def writePopulationParameters(BlockStatement b)'''
		«IF b.blkId.name == BlockDefinitionTable::MDL_POPULATION_BLK»
			«FOR stmt: b.getNonBlockStatements»
				«switch(stmt){
					AnonymousListStatement:
						writePopulationParameter(stmt)
				}»
			«ENDFOR» 
		«ENDIF»
	'''

	def writeParameterModel()'''		
		<ParameterModel blkId="pm">
			«writeParameters»
			«FOR b: mdlObj.blocks»
				«writeRandomVariables(b)»
				«writePopulationParameters(b)»
				«writeIndividualParameters(b)»
			«ENDFOR»
			«print_mdef_CollerationModel(mdlObj)»
		</ParameterModel>
  	'''

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

