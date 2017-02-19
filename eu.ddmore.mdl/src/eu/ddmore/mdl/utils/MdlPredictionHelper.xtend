package eu.ddmore.mdl.utils

import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.provider.ListDefinitionProvider
import eu.ddmore.mdllib.mdllib.SymbolDefinition
import java.util.ArrayList
import java.util.List
import eu.ddmore.mdllib.mdllib.Expression

class MdlPredictionHelper {
	val static DERIV_LIST_TYPE = "DerivList"
	val static String DERIV_EXPR_ATT_NAME = "deriv"
	
	
	extension MdlUtils mu = new MdlUtils
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	
	def getSymbolDefs(BlockStatement it){
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
				if(isDerivativeVariable(nbs)) retVal.add(nbs)
//				if(nbs.firstAttributeList?.listDefinition?.listTypeDefinition.name == DERIV_LIST_TYPE){
//					retVal.add(nbs)
//				}
			}
		]

		retVal
	}
	
	
	def isDerivativeVariable(SymbolDefinition nbs){
		if(nbs instanceof ListDefinition){
			// @TODO: Need to handle conditional lists, which should have a lowest common denominator type
			nbs.firstAttributeList?.listDefinition?.listTypeDefinition.name == DERIV_LIST_TYPE
		}
		else false
	}
	
	// Precondition: nbs is a derivative list
	def Expression getDerivativeExpr(SymbolDefinition nbs){
		if(nbs instanceof ListDefinition){
			nbs.firstAttributeList?.getAttributeExpression(DERIV_EXPR_ATT_NAME)
		}
		else null
	}
	
}