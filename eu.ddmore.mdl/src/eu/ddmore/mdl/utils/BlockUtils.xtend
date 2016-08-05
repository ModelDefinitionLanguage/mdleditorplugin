package eu.ddmore.mdl.utils

import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.MclObject
import java.util.List
import eu.ddmore.mdl.mdl.Statement
import eu.ddmore.mdl.mdl.BlockStatementBody
import java.util.Collections

class BlockUtils {
	
	def String getIdentifier(BlockStatement it){
		blkId.name
	}
	
	def String getMdlObjType(MclObject it){
		objId?.name
	}
	
	def List<Statement> getStatements(BlockStatement it){
		val bd = body
		if(bd instanceof BlockStatementBody){
			bd.statements
		}
		else Collections::emptyList
	}
	
}