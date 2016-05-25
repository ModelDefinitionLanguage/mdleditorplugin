package eu.ddmore.mdl.utils

import com.google.common.base.Preconditions
import eu.ddmore.mdl.mdl.AssignPair
import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.BlockStatementBody
import eu.ddmore.mdl.mdl.EnumPair
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.mdl.MdlFactory
import eu.ddmore.mdl.mdl.SubListExpression
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.mdl.VectorLiteral
import eu.ddmore.mdllib.mdllib.BlockDefinition
import eu.ddmore.mdllib.mdllib.Expression
import eu.ddmore.mdllib.mdllib.FunctionDefnBody
import eu.ddmore.mdllib.mdllib.MdlLibFactory
import eu.ddmore.mdllib.mdllib.ObjectDefinition
import eu.ddmore.mdllib.mdllib.SymbolDefinition
import eu.ddmore.mdllib.mdllib.TypeDefinition
import java.util.ArrayList
import java.util.List
import java.util.Map

class MDLBuildFixture {
//	val public static String REAL_TYPE_NAME = "Real"
//	val TypeDefinition realType

//	new(){
//		realType = MdlLibFactory.eINSTANCE.createTypeDefinition
//		realType.name = REAL_TYPE_NAME
//	}

//	def ObjectDefinition createObjectDefinition(String name){
//		val retVal = MdlLibFactory.eINSTANCE.createObjectDefinition
//		retVal.name = name
//		
//		retVal
//	}
	
	def Mcl createRoot(){
		MdlFactory.eINSTANCE.createMcl
	}

	def MclObject createObject(Mcl mdl, String name, ObjectDefinition oType){
		val retVal = MdlFactory.eINSTANCE.createMclObject
		retVal.name = name
		retVal.objId = oType
		mdl.objects.add(retVal)
		
		retVal
	}

//	def MclObject createObject(String name, ObjectDefinition oType){
//		val retVal = MdlFactory.eINSTANCE.createMclObject
//		retVal.name = name
//		retVal.objId = oType
//		
//		retVal
//	}

	def BlockStatement createBlock(MclObject parent, BlockDefinition blkName, ValuePair ... args){
		val blk = createBlock(blkName, args)
		parent.blocks.add(blk)
		blk
	}
	
	def BlockStatement createBlock(BlockStatement parent, BlockDefinition blkName, ValuePair ... args){
		val bd = parent.body
		if(bd instanceof BlockStatementBody){
			val blk = createBlock(blkName, args)
			bd.statements.add(blk)
			blk
		}
		else null
	}
	
	def BlockStatement createBlock(BlockDefinition blkDefn, ValuePair ... args){
		val retVal = MdlFactory.eINSTANCE.createBlockStatement
		retVal.blkId = blkDefn
		retVal.body = MdlFactory.eINSTANCE.createBlockStatementBody
		if(args.size > 0){
			retVal.blkArgs = MdlFactory.eINSTANCE.createBlockArguments
			for(a : args){
				retVal.blkArgs.args.add(a)
			}
		}
		retVal
	}
	
	def EnumPair createEnumPair(String name, String value){
		val retVal = MdlFactory.eINSTANCE.createEnumPair
		retVal.argumentName = name
		val enumExpr =  MdlFactory.eINSTANCE.createEnumExpression
		enumExpr.enumValue = value
		retVal.expression = enumExpr
		retVal
	}
	
	def AssignPair createAssignPair(String name, Expression exprVal){
		val retVal = MdlFactory.eINSTANCE.createAssignPair
		retVal.argumentName = name
		retVal.expression = exprVal
		retVal
	}
	
	def SymbolReference createSymbolRef(BlockStatement it, String name){
		createSymbolRef(createEqnDefn(name))
	}
	
	def SymbolReference createSymbolRef(SymbolDefinition sd){
		val retVal = MdlFactory.eINSTANCE.createSymbolReference
		retVal.ref = sd
		retVal
	}
	
	def SymbolReference createFunctionCall(FunctionDefnBody fSpec, Expression ... funcArgs){
		val retVal = MdlFactory.eINSTANCE.createSymbolReference
		retVal.ref = fSpec
		val unnamedArgs = MdlFactory.eINSTANCE.createUnnamedFuncArguments
		funcArgs.forEach[
			val unArg = MdlFactory.eINSTANCE.createUnnamedArgument
			unArg.argument = it
			unnamedArgs.args.add(unArg)
		]
		retVal.argList = unnamedArgs
		retVal
	}
	
	
	
	
//	def SymbolReference createNamedFunctionCall(FunctionDefnBody fSpec, ValuePair ... funcArgs){
//		val retVal = MdlFactory.eINSTANCE.createSymbolReference
//		retVal.ref = fSpec
//		val namedArgs = MdlFactory.eINSTANCE.createNamedFuncArguments
//		funcArgs.forEach[
//			namedArgs.arguments.add(it)
//		]
//		retVal.argList = namedArgs
//		retVal
//	}
	
	def EquationDefinition createEqnDefn(BlockStatement it, String name){
		val bdy = body as BlockStatementBody
		val sd = MdlFactory.eINSTANCE.createEquationDefinition
		sd.name = name
		bdy.statements.add(sd)
		sd
	}
	
	def createCategoricalDefinition(BlockStatement it, String varName, SymbolReference distn, String ... categories){
		val bdy = body as BlockStatementBody
		val retVal = MdlFactory.eINSTANCE.createEnumerationDefinition
		retVal.name = varName
		retVal.catDefn = MdlFactory.eINSTANCE.createCategoricalDefinitionExpr
		categories.forEach[c|
			val cd = MdlFactory.eINSTANCE.createCategoryValueDefinition
			cd.name = c
			retVal.catDefn.categories.add(cd)
		]
		retVal.distn = distn
		bdy.statements.add(retVal)
		retVal
	}

	
	
	def EquationDefinition createEqnDefn(BlockStatement it, String name, Expression expr){
		val bdy = body as BlockStatementBody
		val sd = MdlFactory.eINSTANCE.createEquationDefinition
		sd.name = name
		sd.expression = expr
		bdy.statements.add(sd)
		sd
	}

	def VectorLiteral createVectorLiteral(Expression ... elements){
		val retVal = MdlFactory.eINSTANCE.createVectorLiteral
		for(el : elements){
			val vEl = MdlFactory.eINSTANCE.createVectorElement
			vEl.element = el
			retVal.expressions.add(vEl)
		}
		retVal
	}

	def createExpressionsFromList(List<? extends Object> elements){
		val retVal = new ArrayList<Expression>
		for(el : elements){
			switch(el){
				String:
					retVal.add(createStringLiteral(el))
				Integer:
					retVal.add(createIntLiteral(el))
				Double:
					retVal.add(createRealLiteral(el))
				Float:
					retVal.add(createRealLiteral(el))
				Boolean:
					retVal.add(createBooleanLiteral(el))
				default:
					Preconditions.checkArgument(!(el instanceof List<?>))
			}
		}
		retVal
	}


	def SubListExpression createSublist(Map<String, Expression> content){
		val retVal = MdlFactory.eINSTANCE.createSubListExpression
		for(key : content.keySet){
			val att = MdlFactory.eINSTANCE.createValuePair
			att.argumentName = key
			att.expression = content.get(key)
			retVal.attributes.add(att)
		}
		retVal
	}
	
	
	def createAnonList(BlockStatement it, ValuePair ... vps){
		val bdy = body as BlockStatementBody
		val retVal = MdlFactory.eINSTANCE.createAnonymousListStatement
		retVal.list = createAttributeList(vps)
		bdy.statements.add(retVal)
		retVal
	}

	def createAttributeList(ValuePair ... vps){
		val retVal = MdlFactory.eINSTANCE.createAttributeList
		retVal.attributes.addAll(new ArrayList(vps))
		return retVal
	}

	def createListDefn(BlockStatement it, String listName, ValuePair ... vps){
		val bdy = body as BlockStatementBody
		val retVal = MdlFactory.eINSTANCE.createListDefinition
		retVal.name = listName
		retVal.list = createAttributeList(vps)
		bdy.statements.add(retVal)
		retVal
	}

	def createPropertyStatement(BlockStatement it, ValuePair ... vps){
		val bdy = body as BlockStatementBody
		val retVal = MdlFactory.eINSTANCE.createPropertyStatement
		bdy.statements.add(retVal)
		retVal.properties.addAll(new ArrayList(vps))
		retVal
	}


	def createRandVar(BlockStatement it, String varName, SymbolReference distn){
		val bdy = body as BlockStatementBody
		val retVal = MdlFactory.eINSTANCE.createRandomVariableDefinition
		retVal.name = varName
		retVal.distn = distn
		bdy.statements.add(retVal)
		retVal
	}

	def createNamedFunction(FunctionDefnBody funcDefn, ValuePair ... argVals){
//		val funcDefn = MdlLibFactory.eINSTANCE.createFunctionDefnBody
//		funcDefn.name = varName
//		funcDefn.funcSpec = MdlLibFactory.eINSTANCE.createFunctionSpec
//		val argDefn = MdlLibFactory.eINSTANCE.createNamedFuncArgs
//		funcDefn.funcSpec.argument = argDefn
//		for(vp : argVals){
//			val arg = MdlLibFactory.eINSTANCE.createFuncArgumentDefinition
//			arg.name = vp.argumentName
//			arg.typeSpec = createSimpleTypeSpec(realType)
//			argDefn.arguments.add(arg)
//		}
		val retVal = MdlFactory.eINSTANCE.createSymbolReference
		retVal.ref = funcDefn
		val namedArgs = MdlFactory.eINSTANCE.createNamedFuncArguments
		retVal.argList = namedArgs
		for(vp : argVals){
			namedArgs.arguments.add(vp)
		}
		retVal
	}
	
	def createSimpleTypeSpec(TypeDefinition td){
		val retVal = MdlLibFactory.eINSTANCE.createTypeSpec
		retVal.typeName = td
		retVal
	}
	
	def createRealLiteral(double realVal){
		val retVal = MdlFactory.eINSTANCE.createRealLiteral
		retVal.value = realVal
		retVal
	}

	def createStringLiteral(String strVal){
		val retVal = MdlFactory.eINSTANCE.createStringLiteral
		retVal.value = strVal
		retVal
	}
	
	def createIntLiteral(int intVal){
		val retVal = MdlFactory.eINSTANCE.createIntegerLiteral
		retVal.value = intVal
		retVal
	}
	
	def createBooleanLiteral(boolean isTrueFlag){
		val retVal = MdlFactory.eINSTANCE.createBooleanLiteral
		retVal.isTrue = isTrueFlag
		retVal
	}
	
}