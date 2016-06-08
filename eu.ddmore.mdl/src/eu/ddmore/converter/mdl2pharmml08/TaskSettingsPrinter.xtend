package eu.ddmore.converter.mdl2pharmml08

import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.PropertyStatement
import eu.ddmore.mdl.mdl.Statement
import eu.ddmore.mdl.provider.BlockArgumentDefinitionProvider
import eu.ddmore.mdl.provider.BlockDefinitionTable
import eu.ddmore.mdl.utils.ConstantEvaluation
import eu.ddmore.mdl.utils.MDLBuildFixture
import eu.ddmore.mdl.utils.MdlUtils
import eu.ddmore.mdllib.mdllib.Expression
import java.util.ArrayList
import java.util.HashMap
import java.util.List
import java.util.Map

import static eu.ddmore.converter.mdl2pharmml08.Constants.*

class TaskSettingsPrinter {
	extension MdlUtils mu = new MdlUtils 
	extension PharmMLExpressionBuilder peb = new PharmMLExpressionBuilder 
	extension BlockArgumentDefinitionProvider badp = new BlockArgumentDefinitionProvider
	extension ConstantEvaluation ce = new ConstantEvaluation
	extension MDLBuildFixture mbf = new MDLBuildFixture
	
	static val GENERIC_OP_TYPE = "generic"
	static val TARGET_ARG_NAME = "target"
	static val SETTINGS_FILE_ARG_NAME = "settingsFile"
	static val SETTINGS_PROP_NAME = "MDL__settingsFiles"
	
	val Map<BlockStatement, List<String>> fileSettingsLookup = new HashMap<BlockStatement, List<String>>


	def private getSettingsFileArgument(BlockStatement it){
		blkArgs.getArgumentExpression(SETTINGS_FILE_ARG_NAME)?.evaluateStringExpression
	}


	def writeSettingsFile(List<Statement> stmts){
		var order = 1;
		'''
			«FOR settingsBlk : stmts»
				«IF settingsBlk instanceof BlockStatement»
					«IF settingsBlk.getSettingsFileArgument != null»
						«settingsBlk.writeSettingsFileSection(order++)»
					«ENDIF»
				«ENDIF»
			«ENDFOR»
		'''
	}
	
	def private writeSettingsFileSection(BlockStatement owningBlk, int idx){
		val fileName = owningBlk.getSettingsFileArgument
		val oid = "oid_" + BLK_ESTIM_STEP + String.valueOf(idx)
		if(!fileSettingsLookup.containsKey(owningBlk)){
			fileSettingsLookup.put(owningBlk, new ArrayList<String>)
		}
		fileSettingsLookup.get(owningBlk).add(oid)
		'''
			<SoftwareSettings>
				<File oid="«oid»">
					<ds:path>«fileName»</ds:path>
				</File>
			</SoftwareSettings>
		'''
	}

	def writeSettings(List<Statement> stmts){
		var order = 1;
		'''
			«stmts.writeGenericSettings(order++)»
			«stmts.writeTargetSettings(order++)»
		'''
	}

	def writeOptimalSettings(List<Statement> stmts){
		'''
			«FOR settingsBlk : stmts»
				«IF settingsBlk instanceof BlockStatement»
					«IF settingsBlk.blkId.name == BlockDefinitionTable::TARGET_SETTINGS»
						<Algorithm definition="«settingsBlk.getTargetArgument»">
							«IF fileSettingsLookup.containsKey(settingsBlk)»
								«writeProperty(SETTINGS_PROP_NAME,
									createVectorLiteral(createExpressionsFromList(fileSettingsLookup.get(settingsBlk))))»
							«ENDIF»
							«FOR stmt : settingsBlk.nonBlockStatements»
								«IF stmt instanceof PropertyStatement»
									«writeProperties(stmt)»
								«ENDIF»
							«ENDFOR»
						</Algorithm>
					«ENDIF»
				«ENDIF»
			«ENDFOR»
		'''
	}

	def writeTargetSettings(List<Statement> stmts, int startIdx){
		var order = startIdx
		'''
			«FOR settingsBlk : stmts»
				«IF settingsBlk instanceof BlockStatement»
					«IF settingsBlk.blkId.name == BlockDefinitionTable::TARGET_SETTINGS»
						«settingsBlk.writeToolSettings(order++)»
					«ENDIF»
				«ENDIF»
			«ENDFOR»
		'''
	}


	def private writeGenericSettings(List<Statement> stmts, int order)
		'''
		<Operation order="«order»" opType="«GENERIC_OP_TYPE»">
			«FOR stmt : stmts»
				«IF stmt instanceof PropertyStatement»
					«writeProperties(stmt)»
				«ENDIF»
			«ENDFOR»
		</Operation>
		'''
	
	
	def private getTargetArgument(BlockStatement stmt){
		stmt.blkArgs.getArgumentExpression(TARGET_ARG_NAME)?.evaluateStringExpression
	}

	def private writeToolSettings(BlockStatement settingsBlk, int order)
		'''
		<Operation order="«order»" opType="«settingsBlk.getTargetArgument»">
			«IF fileSettingsLookup.containsKey(settingsBlk)»
				«writeProperty(SETTINGS_PROP_NAME,
					createVectorLiteral(createExpressionsFromList(fileSettingsLookup.get(settingsBlk))))»
			«ENDIF»
			«FOR stmt : settingsBlk.nonBlockStatements»
				«IF stmt instanceof PropertyStatement»
					«writeProperties(stmt)»
				«ENDIF»
			«ENDFOR»
		</Operation>
		'''
	

	def private writeProperties(PropertyStatement ps)'''
		«FOR kvp: ps.properties»
			«writeProperty(kvp.argumentName, kvp.expression)»
		«ENDFOR»
	'''


	def private writeProperty(String propName, Expression value)'''
		<Property name="«propName»">
			<ct:Assign>
				«value.pharmMLExpr»
			</ct:Assign>
		</Property>
	'''
	
}