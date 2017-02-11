package eu.ddmore.converter.mdl2pharmml08

import eu.ddmore.mdl.mdl.CatValRefMappingExpression
import eu.ddmore.mdl.mdl.CategoricalDefinitionExpr
import eu.ddmore.mdl.mdl.CategoryValueReference
import eu.ddmore.mdl.mdl.EnumExpression
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.MappingExpression
import eu.ddmore.mdl.mdl.MappingPair
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.provider.ListDefinitionProvider
import eu.ddmore.mdl.provider.ListDefinitionTable
import eu.ddmore.mdl.provider.MogDefinitionProvider
import eu.ddmore.mdl.utils.MdlUtils
import eu.ddmore.mdllib.mdllib.Expression
import eu.ddmore.mdllib.mdllib.SymbolDefinition
import java.util.HashSet

import static eu.ddmore.converter.mdl2pharmml08.Constants.*

import static extension eu.ddmore.mdl.utils.ExpressionConverter.convertToString
import eu.ddmore.mdl.utils.DomainObjectModelUtils
import eu.ddmore.mdl.provider.BlockDefinitionTable
import eu.ddmore.mdl.mdl.FunctionReference
import eu.ddmore.mdl.utils.ExpressionUtils
import eu.ddmore.mdl.provider.BlockArgumentDefinitionProvider
import java.nio.file.Paths
import java.nio.file.Files
import java.nio.file.LinkOption
import java.nio.file.Path

class TrialDesignDataObjectPrinter implements TrialDesignObjectPrinter {
	extension MdlUtils mu = new MdlUtils 
	extension PharmMLExpressionBuilder peb = new PharmMLExpressionBuilder 
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	extension MogDefinitionProvider mdp = new MogDefinitionProvider
	extension DomainObjectModelUtils domu = new DomainObjectModelUtils
	extension ExpressionUtils eu = new ExpressionUtils
	extension BlockArgumentDefinitionProvider badp = new BlockArgumentDefinitionProvider

	private var mappedColumns = new HashSet<String>
	
	val MclObject mObj
	val MclObject dObj
	val AbstractParameterWriter priorDsWriter
	val Path referencePath;
	
	
	new(MclObject mog, AbstractParameterWriter priorDsWriter){
		this(mog, priorDsWriter, null)
	}
	
	new(MclObject mog, AbstractParameterWriter priorDsWriter, Path referencePath){
		mObj = mog.mdlObj
		dObj = mog.dataObj
		this.priorDsWriter = priorDsWriter
		this.referencePath = referencePath
	}

	override writeTrialDesign()'''
		<TrialDesign xmlns="«xmlns_design»">
			«priorDsWriter.writeAllDatasets»
			«IF mObj != null && dObj != null»
				«writeTargetDataSet»
			«ENDIF»
		</TrialDesign>
	'''	

	def writeTargetDataSet() {
		var res = "";
		if (dObj != null || mObj != null) {
			val s = dObj.dataSourceStmt
				// get first statement
			if (s != null){
				if(s.firstAttributeList.getAttributeEnumValue('inputFormat') == 'nonmemFormat') {
					var content = print_ds_NONMEM_DataSet;
					res = res + writeExternalDataSet(content, if(modelContainsAdministrationMacros) "Monolix" else "NONMEM", BLK_DS_NONMEM_DATASET);
				}
			}
		}
		return res;
	}
	
	def modelContainsAdministrationMacros(){
		mObj.mdlCompartmentStatements.exists[stmt|
			if(stmt instanceof ListDefinition){
				stmt.isAdministrationMacro
			}
			else false
		]
	}

	def private writeExternalDataSet(String content, String toolName, String oid) '''
		<ExternalDataSet toolName="«toolName»" oid="«oid»">
			«content»
		</ExternalDataSet>
	'''

	private def saveMappedColumn(String colName){
		mappedColumns.add(colName)
	} 

	private def isColumnMapped(String colName){
		mappedColumns.contains(colName)
	}

	def private writeIdVarLevel(MclObject it, ListDefinition dataVar){
		var retVal = ''''''
		val varBlks = getBlocksByName(BlockDefinitionTable::VAR_LVL_BLK_NAME)
		if(!varBlks.isEmpty){
			val refId  = varBlks.head.blkArgs.getArgumentExpression("reference").symbolRef
			if(refId != null){
				retVal += print_ds_ColumnMapping(dataVar, refId.ref, '')
				saveMappedColumn(dataVar.name)
			}
			else{
				// not refernce so rely on magix mapping
				if(mObj.mdlVariabilityLevels.exists[name == dataVar.name]){
					// only generate mapping if equivalent variable exists in model
					retVal += mObj.print_ds_MagicMapping(dataVar)
					// record that mapping to model found
					saveMappedColumn(dataVar.name)
				}
			}
		}
		retVal
	}

	def print_ds_NONMEM_DataSet() {
		var res = "";
		for (column : dObj.dataColumnDefinitions) {
			val use = column.firstAttributeList.getAttributeEnumValue(ListDefinitionTable::USE_ATT);
			switch(use){
				case(ListDefinitionTable::ID_USE_VALUE):{
					res += mObj.writeIdVarLevel(column)
//					if(mObj.mdlVariabilityLevels.exists[name == column.name]){
//						// only generate mapping if equivalent variable exists in model
//						res += mObj.print_ds_MagicMapping(column)
//						// record that mapping to model found
//						saveMappedColumn(column.name)
//					}
				}
				case(ListDefinitionTable::VARLVL_USE_VALUE):{
					if(mObj.mdlVariabilityLevels.exists[name == column.name]){
						// only generate mapping if equivalent variable exists in model
						res += mObj.print_ds_MagicMapping(column)
						// record that mapping to model found
						saveMappedColumn(column.name)
					}
				}
				case(ListDefinitionTable::IDV_USE_VALUE):{
					if(mObj.mdlIdv != null){
						res += mObj.writeIdvMapping(column)
						// record that mapping to model found
						saveMappedColumn(column.name)
					}
				}
				case(ListDefinitionTable::COV_USE_VALUE):{
					if(isCovariateUsedInModel(column, mObj)){
						var String transformId = null
						if(column.firstAttributeList.hasAttribute('interp')){
							transformId = "MDL__" + column.name
							res = res + writeInterpolationTransform(column, transformId)
						}
						res = res + mObj.print_ds_MagicMapping(column, transformId)
						// record that mapping to model found
						saveMappedColumn(column.name)
					}
				}
				case(ListDefinitionTable::CATCOV_USE_VALUE):{
					if(isCovariateUsedInModel(column, mObj)){
						res = res + mObj.print_ds_CategoricalMagicMapping(column)
						// record that mapping to model found
						saveMappedColumn(column.name)
					}
				}
				case(ListDefinitionTable::VARIABLE_USE_VALUE):{
					if(isVariableUsedInModel(column, mObj)){
						res = res + mObj.print_ds_MagicMapping(column)
						// record that mapping to model found
						saveMappedColumn(column.name)
					}
				}
				case(ListDefinitionTable::AMT_USE_VALUE):{
					res = res + column.print_ds_AmtMapping
					// record that mapping to model found
					saveMappedColumn(column.name)
				}
				case(ListDefinitionTable::OBS_USE_VALUE):{
					res = res + column.print_ds_DvMapping
				}
			}
		}
		for (column : dObj.dataDerivedColumnDefinitions) {
			val use = column.firstAttributeList.getAttributeEnumValue(ListDefinitionTable::USE_ATT);
			switch(use){
				case(ListDefinitionTable::DOSE_TIME_USE_VALUE):{
					res += column.writeDoseTimeMapping
				}
				case(ListDefinitionTable::COV_USE_VALUE):{
					res += mObj.writeDerivedMapping(column)
				}
				case(ListDefinitionTable::VARIABLE_USE_VALUE):{
					res += mObj.writeDerivedMapping(column)
				}
				case(ListDefinitionTable::DOSE_INTERVAL_USE_VALUE):{
					res += '<Error!/>'
				}
				case(ListDefinitionTable::CATCOV_USE_VALUE):{
					var mdlSymb = mObj.findMdlSymbolDefn(column.name)
					var divCol = column.firstAttributeList.getAttributeExpression('column').symbolRef?.ref
					if(divCol instanceof ListDefinition){
						res += print_ds_CategoricalMagicMapping(mdlSymb, divCol, column)
					}
				}
			}
		}
		res += print_ds_DataSet;
	}
	
	val interpLookup = #{
		'contantInterp' -> 'constant',
		'linearInterp' -> 'linear',
		'lastValueInterp' -> 'lastValue',
		'nearestInterp' -> 'nearest',
		'cubicInterp' -> 'cubic',
		'pchipInterp' -> 'pchip',
		'splineInterp' -> 'spline'
	}
	
	def String getInterpType(Expression interpType){
		if(interpType instanceof FunctionReference){
			val lookupVal = interpType.ref.name
			interpLookup.get(lookupVal) ?: 'Error!'
		}
		else 'Error!'
	}

	def writeInterpolationTransform(ListDefinition it, String transformId){
		'''
			<ColumnTransformation transformId="«transformId»">
				<ct:Assign>
					<ct:Interpolation>
						<ct:Algorithm>«firstAttributeList.getAttributeExpression('interp').interpType»</ct:Algorithm>
					</ct:Interpolation>
				</ct:Assign>
			</ColumnTransformation>
		'''
	}
	
	def CharSequence writeDerivedMapping(MclObject mdlObj, ListDefinition column){
		val divCol = column.firstAttributeList.getAttributeExpression('column').symbolRef?.ref
		if(divCol != null){
			var mdlSymb = mdlObj.findMdlSymbolDefn(column.name)
			if(divCol instanceof ListDefinition)
				print_ds_ColumnMapping(divCol, mdlSymb, "")
			else '<Error!>'
		}
		else
			'<Error!>'
	}

	def private int getDosingIdFromDosingVar(ListDefinition amtCol, SymbolDefinition dosingVar){
		val defineAtt = amtCol.firstAttributeList.getAttributeExpression(ListDefinitionTable::DEFINE_ATT)
		if(defineAtt instanceof MappingExpression){
			val mapping = defineAtt.attList.findFirst[mp|
				mp.rightOperand.symbolRef?.ref.name == dosingVar.name
			]
			if(mapping != null)
				return mapping.leftOperand.integerValue
		}
		return -1
	}

	def CharSequence writeDoseTimeMapping(ListDefinition column){
		val idvCol = column.firstAttributeList.getAttributeExpression(ListDefinitionTable::IDV_COL_ATT).symbolRef
		val localDosingVar = column.firstAttributeList.getAttributeExpression(ListDefinitionTable::DOSE_VAR_ATT).symbolRef
		val dosingVar = mObj.findMdlSymbolDefn(localDosingVar.ref.name)
		val amtCol = dObj.getDataColumnDefn(ListDefinitionTable::AMT_USE_VALUE).head
		val cmtCols = dObj.getDataColumnDefn(ListDefinitionTable::CMT_USE_VALUE)
		var ListDefinition cmtCol = null
		if(!cmtCols.isEmpty) cmtCol = cmtCols.head
		val mdlDtSymb = mObj.findMdlSymbolDefn(column.name)
		'''
		<!-- doseTime=«mdlDtSymb.name» -->
		<ColumnMapping>
			<ColumnRef xmlns="«xmlns_ds»" columnIdRef="«idvCol.ref.name»"/>
			<math:Piecewise>
				<math:Piece>
					«mdlDtSymb.symbolReference»
					<math:Condition>
						«IF cmtCol != null»
							<math:LogicBinop op="and">
								<math:LogicBinop op="gt">
									<ds:ColumnRef columnIdRef="«amtCol.name»"/>
									<ct:Int>0</ct:Int>
								</math:LogicBinop>
								<math:LogicBinop op="eq">
									<ds:ColumnRef columnIdRef="«cmtCol.name»"/>
									<ct:Int>«getDosingIdFromDosingVar(amtCol, dosingVar)»</ct:Int>
								</math:LogicBinop>
							</math:LogicBinop>
						«ELSE»
							<math:LogicBinop op="gt">
								<ds:ColumnRef columnIdRef="«amtCol.name»"/>
								<ct:Int>0</ct:Int>
							</math:LogicBinop>
						«ENDIF»
					</math:Condition>
				</math:Piece>
			</math:Piecewise>
		</ColumnMapping>
		'''
	}

	def print_ds_AmtMapping(ListDefinition amtColumn)'''
		«amtColumn.print_ds_StandardAmtMapping»
		«amtColumn.print_ds_TargetMapping»
	'''

	def writeSingleDoseMapping(ListDefinition column, Expression dataVariable){
		var mdlSymb = mObj.findMdlSymbolDefn(dataVariable.convertToString)
		'''
		<ColumnMapping>
			<ColumnRef xmlns="«xmlns_ds»" columnIdRef="«column.name»"/>
			<math:Piecewise>
				<math:Piece>
					«mdlSymb.symbolReference»
					<math:Condition>
						<math:LogicBinop op="gt">
							<ds:ColumnRef columnIdRef="«column.name»"/>
							<ct:Int>0</ct:Int>
						</math:LogicBinop>
					</math:Condition>
				</math:Piece>
			</math:Piecewise>
		</ColumnMapping>
		'''
	}

	def writeMultiDoseMapping(ListDefinition column, Expression dataDefine){
		switch(dataDefine){
			MappingExpression:
				'''
				<ColumnMapping>
				    <ColumnRef xmlns="«xmlns_ds»" columnIdRef="«column.name»"/>
					<math:Piecewise>
						«FOR p : dataDefine.attList»
							«IF !mObj.isCompartmentInput(mObj.findMdlSymbolDefn(p.mappedSymbol.ref.name))»
								<math:Piece>
									«mObj.findMdlSymbolDefn(p.mappedSymbol.ref.name).symbolReference»
								   	<math:Condition>
								   		<math:LogicBinop op="and">
								   			<math:LogicBinop op="eq">
												<ds:ColumnRef columnIdRef="«p.srcColumn.ref.name»"/>
									   			«p.leftOperand.pharmMLExpr»
											</math:LogicBinop>
								   			<math:LogicBinop op="gt">
												<ds:ColumnRef columnIdRef="«column.name»"/>
									   			<ct:Int>0</ct:Int>
											</math:LogicBinop>
										</math:LogicBinop>
								   	</math:Condition>
								</math:Piece>
							«ENDIF»
						«ENDFOR» 
					</math:Piecewise>
				</ColumnMapping>
				'''
		  default: ''''''
		}
	}
	
	def private hasOdeDosing(MappingExpression me){
		me.attList.exists[
			val mdlSymb = mObj.findMdlSymbolDefn(mappedSymbol.ref.name)
			val owningBlkName = mdlSymb.owningBlock?.blkId.name
			owningBlkName == BlockDefinitionTable::MDL_PRED_BLK_NAME || owningBlkName == BlockDefinitionTable::MDL_DEQ_BLK 
		]
	}
	
	def hasCompartmentDosing(SymbolDefinition mappedSymbol){
		val mdlSymb = mObj.findMdlSymbolDefn(mappedSymbol.name)
		mdlSymb instanceof ListDefinition && (mdlSymb as ListDefinition).isAdministrationMacro
	}
	
	protected def print_ds_StandardAmtMapping(ListDefinition amtColumn) {
		val define = amtColumn.firstAttributeList.getAttributeExpression('define');
		if (define == null) {
			val varDefn = amtColumn.firstAttributeList.getAttributeExpression('variable');
			if(varDefn instanceof SymbolReference){
				if(!hasCompartmentDosing(varDefn.ref)){
					writeSingleDoseMapping(amtColumn, varDefn)
				}
			}
		}
		else if(hasOdeDosing((define as MappingExpression))){
			writeMultiDoseMapping(amtColumn, define)
		}
	}

	def print_ds_TargetMapping(ListDefinition amtColumn){
		val define = amtColumn.firstAttributeList.getAttributeExpression('define');
		var toolMappingDefn = '''''';
		if (define != null) {
			// There really must be define in this case.
			switch(define){
				MappingExpression:
					toolMappingDefn += '''
						«FOR me : define.attList»
							«IF mObj.isCompartmentInput(me.mappedSymbol.ref)»
								«mObj.printTargetMapping(me)»
							«ENDIF»	
						«ENDFOR»
					'''
			}
		} else {
			val varDefn = amtColumn.firstAttributeList.getAttributeExpression('variable');
			if(varDefn instanceof SymbolReference){
				toolMappingDefn += '''
					«IF mObj.isCompartmentInput(varDefn.ref)»
						«mObj.printTargetMapping(varDefn.ref)»
					«ENDIF»
				'''	
			}
		}
		'''
			«IF toolMappingDefn.length > 0»
			<ColumnMapping>
				<ds:ColumnRef columnIdRef="«amtColumn.name»"/>
				<ds:TargetMapping blkIdRef="sm">
					«toolMappingDefn»
				</ds:TargetMapping>
			</ColumnMapping>
			«ENDIF»
		'''
	}
	
	def boolean isAdministrationMacro(ListDefinition cmtDefn){
		val type = cmtDefn.firstAttributeList.getAttributeEnumValue(ListDefinitionTable::CMT_TYPE_ATT)
		return type == 'depot' || type == 'input' || type == 'direct'
	}

	def printTargetMapping(MclObject it, MappingPair expression){
		val mdlDefn = findMdlSymbolDefn(expression.mappedSymbol.ref.name)
		'''
			<ds:Map dataSymbol="«expression.leftOperand.convertToString»" admNumber="«PKMacrosPrinter::INSTANCE.getCompartmentNum(mdlDefn)»"/>
		'''
	}
	
	def printTargetMapping(MclObject it, SymbolDefinition mappedSymbol){
		val mdlDefn = findMdlSymbolDefn(mappedSymbol.name)
		'''
			<ds:Map admNumber="«PKMacrosPrinter::INSTANCE.getCompartmentNum(mdlDefn)»"/>
		'''
	}
	
	private def print_ds_CategoricalMapping(ListDefinition column) {
		var res = "";
			val define = column.firstAttributeList.getAttributeExpression(ListDefinitionTable::USE_ATT);
			// get an EnumExpression here - use this to get the categories.
			switch(define){
				EnumExpression:{
					val catDefnExpr = define.catDefn as CategoricalDefinitionExpr
					for(catVal : catDefnExpr.categories){
					res = res + '''
						<ds:Map modelSymbol="«catVal.name»" dataSymbol="«catVal.mappedTo.convertToString»"/>
						'''
					}
				}
			}
		if (res.length > 0)
			res = '''
				<ds:CategoryMapping>
					«res»
				</ds:CategoryMapping>
			'''
		return res;
	}

	protected def print_ds_ColumnMapping(ListDefinition column, SymbolDefinition mdlSymb, String complexMapping){
		print_ds_ColumnMapping(column, mdlSymb, complexMapping, null)
	}

	protected def print_ds_ColumnMapping(ListDefinition column, SymbolDefinition mdlSymb, String complexMapping, String transformId) '''
		<ColumnMapping>
			<ColumnRef xmlns="«xmlns_ds»"«IF transformId != null» transformIdRef="«transformId»"«ENDIF» columnIdRef="«column.name»"/>
			«mdlSymb.symbolReference»
			«IF complexMapping.length > 0»
				«complexMapping»
			«ENDIF»
		</ColumnMapping>
	'''

	def print_ds_MagicMapping(MclObject mdlObj, ListDefinition column) {
		print_ds_MagicMapping(mdlObj, column, null)
	}

	def print_ds_MagicMapping(MclObject mdlObj, ListDefinition column, String transformId) {
		var mdlSymb = mdlObj.findMdlSymbolDefn(column.name)
		print_ds_ColumnMapping(column, mdlSymb, "", transformId).toString
	}
	
	def writeIdvMapping(MclObject mdlObj, ListDefinition column) {
		var mdlIdvSymb = mdlObj.mdlIdv
		print_ds_ColumnMapping(column, mdlIdvSymb, "").toString
	}
	
	
	def print_ds_CategoricalMagicMapping(MclObject mdlObj, ListDefinition column){
		var mdlSymb = mdlObj.findMdlSymbolDefn(column.name)
		print_ds_CategoricalMagicMapping(mdlSymb, column, column)
//		var categoricalMapping = column.print_ds_CategoricalMapping
//		print_ds_ColumnMapping(column, mdlSymb, categoricalMapping)
	}

	def print_ds_CategoricalMagicMapping(SymbolDefinition mdlCatCov, ListDefinition dataColumn, ListDefinition catCol){
		var categoricalMapping = catCol.print_ds_CategoricalMapping
		print_ds_ColumnMapping(dataColumn, mdlCatCov, categoricalMapping)
	}

	def writeSingleObsMapping(ListDefinition column, Expression dataVariable){
		var mdlSymb = mObj.findMdlSymbolDefn(dataVariable.convertToString)
		'''
			<ColumnMapping>
			    <ColumnRef xmlns="«xmlns_ds»" columnIdRef="«column.name»"/>
				«mdlSymb.symbolReference»
			</ColumnMapping>
		'''
	}

	def isCategoricalObs(SymbolDefinition symb){
		if(symb instanceof ListDefinition){
			(symb as ListDefinition).firstAttributeList.getAttributeEnumValue(ListDefinitionTable::OBS_TYPE_ATT) == ListDefinitionTable::CATEGORICAL_OBS_VALUE
		}
		else false
	}

	def isDiscreteObs(SymbolDefinition symb){
		if(symb instanceof ListDefinition){
			(symb as ListDefinition).firstAttributeList.getAttributeEnumValue(ListDefinitionTable::OBS_TYPE_ATT) == ListDefinitionTable::DISCRETE_OBS_VALUE
		}
		else false
	}

	def writeMultipleObsMapping(ListDefinition column, Expression dataDefine){
		switch(dataDefine){
			MappingExpression:
				'''
				<MultipleDVMapping>
					<ColumnRef xmlns="«xmlns_ds»" columnIdRef="«column.name»"/>
					<Piecewise>
						«FOR p : dataDefine.attList»
							«IF p.rightOperand instanceof SymbolReference»
								«IF mObj.isDefinedInMdlObservations(p.mappedSymbol)»
									<math:Piece>
									   	«mObj.findMdlSymbolDefn(p.mappedSymbol.ref.name).symbolReference»
										<math:Condition>
											<math:LogicBinop op="eq">
											<ds:ColumnRef xmlns="«xmlns_ds»" columnIdRef="«p.srcColumn.ref.name»"/>
												«p.leftOperand.pharmMLExpr»
									   		</math:LogicBinop>
									   	</math:Condition>
									</math:Piece>
								«ENDIF»
							«ELSEIF p.rightOperand instanceof CatValRefMappingExpression»
								«IF mObj.isDefinedInMdlObservations((p.rightOperand as CatValRefMappingExpression).attLists.head.catRef)»
									<math:Piece>
										«IF mObj.findMdlSymbolDefn((p.rightOperand as CatValRefMappingExpression).attLists.head.catRef.symbolDefnFromCatValRef.name).isCategoricalObs»
											«mObj.findMdlSymbolDefn((p.rightOperand as CatValRefMappingExpression).attLists.head.catRef.symbolDefnFromCatValRef.name).symbolReference»
											«printCategoricalObsMapping(p.rightOperand)»
										«ENDIF»
										<math:Condition>
											<math:LogicBinop op="eq">
											<ds:ColumnRef xmlns="«xmlns_ds»" columnIdRef="«p.srcColumn.ref.name»"/>
												«p.leftOperand.pharmMLExpr»
									   		</math:LogicBinop>
									   	</math:Condition>
									</math:Piece>
								«ENDIF»
							«ENDIF»
						«ENDFOR» 
					</Piecewise>
				</MultipleDVMapping>
				'''
			CatValRefMappingExpression:{
				val dataCatValRef = dataDefine.attLists.head.catRef
				val mdlObsSymb =  mObj.findMdlSymbolDefn(dataCatValRef.symbolDefnFromCatValRef.name)
				'''
				«IF mObj.isDefinedInMdlObservations(dataCatValRef)»
					<ColumnMapping>
						<ColumnRef xmlns="«xmlns_ds»" columnIdRef="«column.name»"/>
							«mdlObsSymb.symbolReference»
							«IF mdlObsSymb.isCategoricalObs || mdlObsSymb.isDiscreteObs»
								«printCategoricalObsMapping(dataDefine)»
							«ENDIF»
					</ColumnMapping>
				«ENDIF»
				'''
			}
			default: ''''''
		}
	}


	def printCategoricalObsMapping(Expression expression){
		switch(expression){
			CatValRefMappingExpression:{
				return '''
				<math:CategoryMapping>
					«FOR catVal : expression.attLists»
						<ds:Map modelSymbol="«catVal.catRef.ref.name»" dataSymbol="«catVal.mappedTo.convertToString»"/>
					«ENDFOR»
				</math:CategoryMapping>
				'''
			}
		}
	}
	
	
	private def isDefinedInMdlObservations(MclObject it, Expression testExpr){
		switch(testExpr){
			SymbolReference:
				//@TODO fix this for new obs types 
				mdlObservations.exists[o|
					if(o instanceof SymbolDefinition)
						o.name == testExpr?.ref.name
					else false
				]
			CategoryValueReference:
				//@TODO fix this for new obs types 
				mdlObservations.exists[o|
					if(o instanceof SymbolDefinition)
						o.name == testExpr?.symbolDefnFromCatValRef?.name
					else false
				]
			default: false
		} 
	}
	
	private def isMultiObsMappingDefinedInMdlObs(MclObject it, Expression testExpr){
		switch(testExpr){
			MappingExpression:{
				for(p : testExpr.attList){
				//@TODO fix this for new obs types 
					if(mdlObservations.exists[o|
						if(o instanceof SymbolDefinition)
							o.name == p.mappedSymbol?.ref?.name
						else false
					])
						return true
				}
				false
			}
			CatValRefMappingExpression:{
				for(p : testExpr.attLists){
				//@TODO fix this for new obs types 
					if(mdlObservations.exists[o|
						if(o instanceof SymbolDefinition)
							o.name == p.catRef.getSymbolDefnFromCatValRef.name
						else false
					])
						return true
				}
				false
			}
			default: false
		}
	}
	
	def print_ds_DvMapping(ListDefinition dvColumn){
		var CharSequence retVal = ''''''
		val variable = dvColumn.firstAttributeList.getAttributeExpression('variable');
		if (variable != null && mObj.isDefinedInMdlObservations(variable)) {
			// Reference or mapped to data
			retVal = writeSingleObsMapping(dvColumn, variable)
			saveMappedColumn(dvColumn.name)
		}
		else { 
			val define = dvColumn.firstAttributeList.getAttributeExpression(ListDefinitionTable::DEFINE_ATT);
			if(mObj.isMultiObsMappingDefinedInMdlObs(define)){
				retVal = writeMultipleObsMapping(dvColumn, define)
				saveMappedColumn(dvColumn.name)
			}
		}
		retVal
	}

	
	def private boolean isTimeDependentCovariate(ListDefinition colCol, MclObject it){
		val mdlCov = findMdlSymbolDefn(colCol.name)
		if(mdlCov != null)
			mdlCov.isIdvDepCovariate
		else false
	}
	
	def private getIgnoreLineSymbol(){
		val s = dObj.getDataSourceStmt
		s.firstAttributeList.getAttributeExpression('ignore')?.convertToString
	}
	
	def print_ds_DataSet() {
		var res = "";
		var k = 1;
		val dosingToCompartmentMacro = dObj.dataColumnDefinitions.exists[
				firstAttributeList.getAttributeEnumValue(ListDefinitionTable::USE_ATT) == ListDefinitionTable::AMT_USE_VALUE &&
				isDosingToCompartmentMacro
		]
		for (column : dObj.dataColumnDefinitions) {
			val columnType = column.firstAttributeList.getAttributeEnumValue(ListDefinitionTable::USE_ATT);
			val columnId = column.name;
			val convertedColType = switch(columnType){
				case(ListDefinitionTable::COV_USE_VALUE),
				case(ListDefinitionTable::CATCOV_USE_VALUE):
					if(isColumnMapped(column.name)) convertEnum(columnType, dosingToCompartmentMacro, column.isTimeDependentCovariate(mObj)) else "undefined"
				default:
//					if(isColumnMapped(column.name)) convertEnum(columnType, dosingToCompartmentMacro, false) else 'undefined'
					convertEnum(columnType, dosingToCompartmentMacro, false)
			}
			val valueType = column.getValueType
			res = res +
				'''
					<Column columnId="«columnId»" columnType="«convertedColType»" valueType="«valueType»" columnNum="«k»"/>
				'''
			k = k + 1;
		}
		val ignoreLineSymb = ignoreLineSymbol
		return '''
			<DataSet xmlns="«xmlns_ds»">
				<Definition>
					«res»
					«IF ignoreLineSymb != null»
						<IgnoreLine symbol="«ignoreLineSymb»"/>
					«ENDIF»
				</Definition>
				«print_ds_ExternalFile»
			</DataSet>
		'''
	}
	
	def isCovariateUsedInModel(ListDefinition col, MclObject mdlObj){
		for(mCov : mdlObj.mdlCovariateDefns){
			switch(mCov){
				EquationDefinition case(mCov.name == col.name):
					return mCov.expression == null
				SymbolDefinition case(mCov.name == col.name):
					return true
			}
		}
		false
	}
	
	def isVariableUsedInModel(ListDefinition col, MclObject mdlObj){
		mdlObj.findMdlSymbolDefn(col.name) != null
	}
	
	
	def convertEnum(String type, boolean isDosingToCompartmentMacro, boolean isRegressor) {
		switch (type) {
			case ListDefinitionTable::AMT_USE_VALUE     : "dose"
			case ListDefinitionTable::DVID_USE_VALUE   : "dvid"
			case ListDefinitionTable::VARLVL_USE_VALUE: "occasion"
			case ListDefinitionTable::VARIABLE_USE_VALUE: "reg"
			case ListDefinitionTable::COV_USE_VALUE,
			case ListDefinitionTable::CATCOV_USE_VALUE:
				if(isRegressor) "reg" else "covariate"
			case ListDefinitionTable::CMT_USE_VALUE : if(isDosingToCompartmentMacro) 'adm' else 'cmt'
			case ListDefinitionTable::IGNORE_USE_VALUE: 'undefined'
			default: type
		}
	}
	
	def boolean isDosingToCompartmentMacro(ListDefinition amtColumn){
		val define = amtColumn.firstAttributeList.getAttributeExpression('define');
	
		val mappedSymbol = define.getMappedSymbolRef
		for(symb : mappedSymbol){
			if(mObj.isCompartmentInput(symb)) return true
		}
		false
	}
	

	def isCompartmentInput(MclObject it, SymbolDefinition symb){
		mdlCompartmentStatements.exists[s|
			switch(s){
				ListDefinition:{
					s.name == symb.name && s.isAdministrationMacro
				}
					
				default: false
			}
			
		]
	}

	protected def getValueType(ListDefinition dataColumn) {
		val useValue = dataColumn.firstAttributeList.getAttributeEnumValue(ListDefinitionTable::USE_ATT);

		switch useValue {
			case ListDefinitionTable::ID_USE_VALUE: Constants::TYPE_INT
			case ListDefinitionTable::COV_USE_VALUE: Constants::TYPE_REAL
			case ListDefinitionTable::CATCOV_USE_VALUE: Constants::TYPE_INT
			case ListDefinitionTable::DVID_USE_VALUE: Constants::TYPE_INT
			case 'mdv': Constants::TYPE_INT
			case 'cmt': Constants::TYPE_INT
			case ListDefinitionTable::VARLVL_USE_VALUE: Constants::TYPE_INT
			default: Constants::TYPE_REAL
		}
	}

	protected def print_ds_ExternalFile() {
		var res = "";
		val s = dObj.getDataSourceStmt
		var file = "";
		file = s.firstAttributeList.getAttributeExpression('file').convertToString
		var dataPath = Paths.get(file)
		if(!dataPath.isAbsolute){
			if(this.referencePath != null){
				dataPath = Paths.get(this.referencePath.toString, dataPath.toString)
				if(Files.exists(dataPath, #[ LinkOption.NOFOLLOW_LINKS ])){
					file = dataPath.toAbsolutePath().normalize().toString()
				}
			}
		}
		else{
			file = dataPath.toString
		}
		if (file.length > 0) {
			res = res + '''				
				<ExternalFile oid="«BLK_DS_IMPORT_DATA»">
					<path>«file»</path>
					<format>CSV</format>
					<delimiter>COMMA</delimiter>
				</ExternalFile>
			'''
		}
		return res;
	}

	
}