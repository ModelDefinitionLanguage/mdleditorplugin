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

class TrialDesignDataObjectPrinter implements TrialDesignObjectPrinter {
	extension MdlUtils mu = new MdlUtils 
	extension PharmMLExpressionBuilder peb = new PharmMLExpressionBuilder 
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	extension MogDefinitionProvider mdp = new MogDefinitionProvider

	private var mappedColumns = new HashSet<String>
	
	val MclObject mObj
	val MclObject dObj
	val AbstractParameterWriter priorDsWriter
	
	new(MclObject mog, AbstractParameterWriter priorDsWriter){
		mObj = mog.mdlObj
		dObj = mog.dataObj
		this.priorDsWriter = priorDsWriter
	}
	
	override writeTrialDesign()'''
		<TrialDesign xmlns="«xmlns_design»">
			«priorDsWriter.writeAllDatasets»
			«IF mObj != null && dObj != null»
				«writeTargetDataSet»
			«ENDIF»
		</TrialDesign>
	'''	

	def private writeTargetDataSet() {
		var res = "";
		if (dObj != null || mObj != null) {
			val s = dObj.dataSourceStmt
				// get first statement
			if (s != null){
				if(s.firstAttributeList.getAttributeEnumValue('inputFormat') == 'nonmemFormat') {
					var content = print_ds_NONMEM_DataSet;
					res = res + writeExternalDataSet(content, "NONMEM", BLK_DS_NONMEM_DATASET);
				}
			}
		}
		return res;
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

	def print_ds_NONMEM_DataSet() {
		var res = "";
		for (column : dObj.dataColumnDefinitions) {
			val use = column.firstAttributeList.getAttributeEnumValue(ListDefinitionTable::USE_ATT);
			switch(use){
				case(ListDefinitionTable::ID_USE_VALUE),
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
						res = res + mObj.print_ds_MagicMapping(column)
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
//					Potential bug here. This is meant to ensure that no mapping
//					is generated if no variables match. @TODO: fix this properly.
//					if(mObj.findMdlSymbolDefn(column.name) != null){
						res = res + column.print_ds_AmtMapping
						// record that mapping to model found
						saveMappedColumn(column.name)
//					}
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
			}
		}
		res += print_ds_DataSet;
	}


	def CharSequence writeDoseTimeMapping(ListDefinition column){
		var idvCol = column.firstAttributeList.getAttributeExpression(ListDefinitionTable::IDV_COL_ATT)
		var amtCol = column.firstAttributeList.getAttributeExpression(ListDefinitionTable::AMT_COL_ATT)
		var mdlDtSymb = mObj.findMdlSymbolDefn(column.name)
		'''
		<!-- doseTime=«mdlDtSymb.name» -->
		<ColumnMapping>
			<ColumnRef xmlns="«xmlns_ds»" columnIdRef="«idvCol.convertToString»"/>
			<math:Piecewise>
				<math:Piece>
					«mdlDtSymb.symbolReference»
					<math:Condition>
						<math:LogicBinop op="gt">
							<ds:ColumnRef columnIdRef="«amtCol.convertToString»"/>
							<ct:Int>0</ct:Int>
						</math:LogicBinop>
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
						«ENDFOR» 
					</math:Piecewise>
				</ColumnMapping>
				'''
		  default: ''''''
		}
	}
	
	def hasCompartmentDosing(MappingExpression me){
		me.attList.exists[
			val mdlSymb = mObj.findMdlSymbolDefn(mappedSymbol.ref.name)
			mdlSymb instanceof ListDefinition && (mdlSymb as ListDefinition).isAdministrationMacro
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
		else if(!hasCompartmentDosing(define as MappingExpression)){
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

	protected def print_ds_ColumnMapping(ListDefinition column, SymbolDefinition mdlSymb, String complexMapping) '''
		<ColumnMapping>
			<ColumnRef xmlns="«xmlns_ds»" columnIdRef="«column.name»"/>
			«mdlSymb.symbolReference»
			«IF complexMapping.length > 0»
				«complexMapping»
			«ENDIF»
		</ColumnMapping>
	'''

	def print_ds_MagicMapping(MclObject mdlObj, ListDefinition column) {
		var mdlSymb = mdlObj.findMdlSymbolDefn(column.name)
		print_ds_ColumnMapping(column, mdlSymb, "").toString
	}
	
	def writeIdvMapping(MclObject mdlObj, ListDefinition column) {
		var mdlIdvSymb = mdlObj.mdlIdv
		print_ds_ColumnMapping(column, mdlIdvSymb, "").toString
	}
	
	
	def print_ds_CategoricalMagicMapping(MclObject mdlObj, ListDefinition column){
		var mdlSymb = mdlObj.findMdlSymbolDefn(column.name)
		var categoricalMapping = column.print_ds_CategoricalMapping
		print_ds_ColumnMapping(column, mdlSymb, categoricalMapping)
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
					<math:Piecewise>
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
					</math:Piecewise>
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
			MappingExpression:
				for(p : testExpr.attList){
				//@TODO fix this for new obs types 
					if(mdlObservations.exists[o|
						if(o instanceof SymbolDefinition)
							name == p.mappedSymbol?.ref?.name
						else false
					])
						return true
				}
			CatValRefMappingExpression:
				for(p : testExpr.attLists){
				//@TODO fix this for new obs types 
					if(mdlObservations.exists[o|
						if(o instanceof SymbolDefinition)
							name == p.catRef.getSymbolDefnFromCatValRef.name
						else false
					])
						return true
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
			case ListDefinitionTable::VARIABLE_USE_VALUE: "undefined"
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