package eu.ddmore.converter.mdl2pharmml08

import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.PropertyStatement
import eu.ddmore.mdl.mdl.Statement
import eu.ddmore.mdl.provider.BlockArgumentDefinitionProvider
import eu.ddmore.mdl.utils.ConstantEvaluation
import eu.ddmore.mdl.utils.MdlUtils
import eu.ddmore.mdllib.mdllib.Expression
import java.util.List

import static eu.ddmore.converter.mdl2pharmml08.Constants.*

class TaskSettingsPrinter {
	extension MdlUtils mu = new MdlUtils 
	extension PharmMLExpressionBuilder peb = new PharmMLExpressionBuilder 
	extension BlockArgumentDefinitionProvider badp = new BlockArgumentDefinitionProvider
	extension ConstantEvaluation ce = new ConstantEvaluation
	
	static val GENERIC_OP_TYPE = "generic"
	static val TARGET_ARG_NAME = "target"
	static val SETTINGS_FILE_ARG_NAME = "settingsFile"


	def private getSettingsFileArgument(BlockStatement it){
		blkArgs.getArgumentExpression(SETTINGS_FILE_ARG_NAME)?.evaluateStringExpression
	}


	def writeSettingsFile(List<Statement> stmts){
		var order = 1;
		'''
			«FOR settingsBlk : stmts»
				«IF settingsBlk instanceof BlockStatement»
					«IF settingsBlk.getSettingsFileArgument != null»
						«writeSettingsFileSection(settingsBlk.getSettingsFileArgument, order++)»
					«ENDIF»
				«ENDIF»
			«ENDFOR»
		'''
	}
	
	def private writeSettingsFileSection(String fileName, int idx)
		'''
			<SoftwareSettings>
				<File oid="oid_«BLK_ESTIM_STEP»«idx»">
					<ds:path>fileName</ds:path>
				</File>
			</SoftwareSettings>
		'''


	def writeSettings(List<Statement> stmts){
		var order = 1;
		'''
			«stmts.writeGenericSettings(order++)»
			«FOR settingsBlk : stmts»
				«IF settingsBlk instanceof BlockStatement»
					«settingsBlk.writeToolSettings(order++)»
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
		<Operation order="«order» opType="«settingsBlk.getTargetArgument»"
			«FOR stmt : settingsBlk.nonBlockStatements»
				«IF stmt instanceof PropertyStatement»
					«writeProperties(stmt)»
				«ENDIF»
			«ENDFOR»
		</Operation>
		'''
	


	def writeProperties(PropertyStatement ps)'''
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