package eu.ddmore.mdl.utils

import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.provider.ListDefinitionProvider
import eu.ddmore.mdllib.mdllib.SymbolDefinition
import java.util.ArrayList
import java.util.List

class MdlPredictionHelper {
	val static DERIV_LIST_TYPE = "DerivList"
	
	
	extension MdlUtils mu = new MdlUtils
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	
	def getVariables(BlockStatement it){
		val List<SymbolDefinition> retVal = new ArrayList<SymbolDefinition>()
		nonBlockStatements.forEach[nbs|
			if(nbs instanceof SymbolDefinition)
				retVal.add(nbs)
		]
		
		retVal
	}
	
	
	def getDerivativeVariables(BlockStatement it){
		val List<ListDefinition> retVal = new ArrayList<ListDefinition>()
		nonBlockStatements.forEach[nbs|
			if(nbs instanceof ListDefinition){
				if(nbs.firstAttributeList?.listDefinition?.listTypeDefinition.name == DERIV_LIST_TYPE){
					retVal.add(nbs)
				}
			}
		]

		retVal
	}
	
}