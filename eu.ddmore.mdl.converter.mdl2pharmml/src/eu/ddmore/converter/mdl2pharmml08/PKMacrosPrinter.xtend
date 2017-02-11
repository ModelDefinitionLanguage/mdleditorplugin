package eu.ddmore.converter.mdl2pharmml08

import eu.ddmore.mdl.mdl.AnonymousListStatement
import eu.ddmore.mdl.mdl.AttributeList
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.mdl.Statement
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.provider.ListDefinitionProvider
import eu.ddmore.mdl.utils.MdlUtils
import eu.ddmore.mdllib.mdllib.Expression
import eu.ddmore.mdllib.mdllib.SymbolDefinition
import java.util.HashMap
import java.util.List
import org.eclipse.xtext.EcoreUtil2
import eu.ddmore.mdl.type.TypeSystemProvider

class PKMacrosPrinter{
//	private static val MATH_NS = "http://www.pharmml.org/pharmml/0.6/Maths"; 
	
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	extension PharmMLExpressionBuilder peb = new PharmMLExpressionBuilder
	extension MdlUtils mu = new MdlUtils
	extension TypeSystemProvider tsp = new TypeSystemProvider
	
//	private val pk_types = newHashMap(
//		'direct' -> "IV",
//		'compartment' -> "Compartment",
//		'distribution' -> "Peripheral",
////		'input' -> "Absorption",
//		'depot' -> "Absorption",
//		'effect' -> "Effect",
//		'transfer' -> "Transfer",
//		'elimination' -> "Elimination"
//	);

	private static var PKMacrosPrinter _INSTANCE = null
	private static val  COMPARTMENT_TYPE_NAME = 'List:Compartment'

	val cmpNumMap = new HashMap<String, Integer>
	var cmpNum = 0
	

	public static def INSTANCE(){
		if(_INSTANCE == null)
			_INSTANCE = new PKMacrosPrinter
			
		_INSTANCE
	}	
	
	private new (){}
	
	private val pk_attrs = newHashMap(
		'finput' -> "p",
		'tlag' -> "Tlag",
		'v' -> "V",
		'cl' -> "CL",
		'ktr' -> "Ktr",
		'mtt' -> "Mtt",
		'k'-> "k",
		'kt' -> "kt",
		'ka' -> "ka",
		'vm' -> "Vm",
		'km' -> "Km",
		'modelDur' -> 'Tk0'
	);
	

	def writeVariable(SymbolDefinition it)'''
		<ct:Variable symbId="«name»" symbolType="real"/>
	'''
		
	
	def printCompartmentDefinitions(List<Statement> statements)'''
		«FOR s : statements»
			«switch(s){
				ListDefinition case(s.firstAttributeList.getAttributeEnumValue('type') == 'compartment'):
					s.writeVariable
				ListDefinition case(s.firstAttributeList.getAttributeEnumValue('type') == 'effect'):
					s.writeVariable
				ListDefinition case(s.firstAttributeList.getAttributeEnumValue('type') == 'distribution'):
					s.writeVariable
			}»
		«ENDFOR»
	'''	
	
	def printMacros(List<Statement> statements) {
		resetCompartments
		'''
		«statements.forEach[defineCompartments]»
		<PKmacros>
			«FOR s : statements»
				«s.writeMacro»
			«ENDFOR»
		</PKmacros>
		'''
	}
	
	def resetCompartments(){
		cmpNum = 0
		cmpNumMap.clear
	}
	
	def storeCompartment(ListDefinition it){
		cmpNum += 1
		cmpNumMap.put(name, cmpNum)
	}
	
	def getCompartmentNum(SymbolDefinition it){
		cmpNumMap.get(name)
	}
	
	
	def defineCompartments(Statement stmt){
		switch(stmt){
			ListDefinition case(stmt.firstAttributeList.getAttributeEnumValue('type') == 'effect'),
			ListDefinition case(stmt.firstAttributeList.getAttributeEnumValue('type') == 'compartment'),
			ListDefinition case(stmt.firstAttributeList.getAttributeEnumValue('type') == 'distribution'):{
				storeCompartment(stmt)
				'''
					<Variable symbId="«stmt.name»"/>
				'''
			}
			ListDefinition case(stmt.firstAttributeList.getAttributeEnumValue('type') == 'direct'),
			ListDefinition case(stmt.firstAttributeList.getAttributeEnumValue('type') == 'depot'):{
				// defining adms
				storeCompartment(stmt)
				''''''
			}
		}
	}
	
	def writeMacro(Statement stmt){
		switch(stmt){
			ListDefinition case(stmt.firstAttributeList.getAttributeEnumValue('type') == 'effect'):
				stmt.writeEffect
			ListDefinition case(stmt.firstAttributeList.getAttributeEnumValue('type') == 'compartment'):
				stmt.writeCompartment
			ListDefinition case(stmt.firstAttributeList.getAttributeEnumValue('type') == 'distribution'):
				stmt.writePeripheral
			ListDefinition case(stmt.firstAttributeList.getAttributeEnumValue('type') == 'direct'):
				if(stmt.firstAttributeList.isTargetingNonCmt){
					stmt.writeDepotWithTarget
				}
				else{
					stmt.writeIV
				}
			ListDefinition case(stmt.firstAttributeList.getAttributeEnumValue('type') == 'depot'):
				if(stmt.firstAttributeList.isTargetingNonCmt){
					stmt.writeDepotWithTarget
				}
				else{
					stmt.writeAbsorption
				}
			AnonymousListStatement case(stmt.list.getAttributeEnumValue('type') == 'transfer'):
				stmt.writeTransfer
			AnonymousListStatement case(stmt.list.getAttributeEnumValue('type') == 'elimination'):
				stmt.writeElimination
		}
	}
	
	def private boolean isTargetingNonCmt(AttributeList it){
		val toType = getAttributeExpression('to').typeFor
		toType.underlyingType.typeName != COMPARTMENT_TYPE_NAME
	}
	
	
	def writeCompartment(ListDefinition it)'''
		<Compartment>
			<Value argument="amount"> 
				«symbolReference»
			</Value>
			«writeValue("cmt", compartmentNum)»
		</Compartment>
	'''
	
	def getMclObject(ListDefinition it){
		EcoreUtil2.getContainerOfType(it, MclObject)
	}

	def getMclObject(AttributeList it){
		EcoreUtil2.getContainerOfType(it, MclObject)
	}
	
	def writeFromValue(AttributeList it, String macroName){
		val from = getAttributeExpression('from');
		if (from != null){
			val fromCompartment = mclObject.findCompartment(from as SymbolReference);
			writeValue(macroName, fromCompartment.compartmentNum)
		}
		else''''''
	}

	def writeFromValue(AttributeList it){
		writeFromValue('cmt')
	}
	
	def writeToValue(AttributeList it){
		writeToValue('cmt')
	}

	def writeToValue(AttributeList it, String macroName){
		val to = getAttributeExpression('to')
		if (to != null){
			var toCompartmentArgs = mclObject.findCompartment(to as SymbolReference)
			if (toCompartmentArgs != null){
				writeValue(macroName, toCompartmentArgs.compartmentNum)
			}
		}
	}
	
	def writePeripheralKij(ListDefinition it){
		var kin = firstAttributeList.getAttributeExpression('kin');
		val from = firstAttributeList.getAttributeExpression('from');
		if (from != null && kin != null){
			val fromCompartmentArgs = mclObject.findCompartment(from as SymbolReference);
			val fromCompartment_cmt = fromCompartmentArgs.compartmentNum
			val attr1 = "k" + '_' + fromCompartment_cmt + '_' + compartmentNum
			kin.writeValue(attr1) 
		}
	}
	
	def writePeripheralKji(ListDefinition it){
		var kout = firstAttributeList.getAttributeExpression('kout');
		val from = firstAttributeList.getAttributeExpression('from');
		if (from != null && kout != null){
			val fromCompartmentArgs = mclObject.findCompartment(from as SymbolReference);
			val fromCompartment_cmt = fromCompartmentArgs.compartmentNum
			val attr1 = "k" + '_' + compartmentNum + '_' + fromCompartment_cmt
			kout.writeValue(attr1) 
		}
	}
	
	def writeAttribute(AttributeList it, String attName){
		val expr = getAttributeExpression(attName)
		if(expr != null )
			expr.writeValue(pk_attrs.get(attName))
		else
			''''''
	}
	
	def writeTargetAttribute(AttributeList it){
		val expr = getAttributeExpression('to')
		if(expr != null )
			expr.writeValue('target')
		else
			'''<Error attribute 'to'!>'''
	}
	
	def writeEffect(ListDefinition it)'''
		<Effect>
			<Value argument="concentration"> 
				«symbolReference»
			</Value>
			«writeValue("cmt", compartmentNum)»
			«firstAttributeList.writeFromValue»
			«firstAttributeList.getAttributeExpression('keq').writeValue('ke0')»
		</Effect>
	'''
	
	def writePeripheral(ListDefinition it)'''
		<Peripheral>
			<Value argument="amount"> 
				«symbolReference»
			</Value>
			«writePeripheralKij»
			«writePeripheralKji»
		</Peripheral>
	'''
	
	def writeIV(ListDefinition it)'''
		<IV>
			«writeValue("adm", compartmentNum)»
			«firstAttributeList.writeToValue»
			«firstAttributeList.writeAttribute('modelDur')»
			«firstAttributeList.writeAttribute('tlag')»
			«firstAttributeList.writeAttribute('finput')»
		</IV>
	'''
	
	def writeAbsorption(ListDefinition it)'''
		<Absorption>
			«writeValue("adm", compartmentNum)»
			«firstAttributeList.writeToValue»
			«firstAttributeList.writeAttribute('ka')»
			«firstAttributeList.writeAttribute('tlag')»
			«firstAttributeList.writeAttribute('finput')»
			«firstAttributeList.writeAttribute('modelDur')»
			«firstAttributeList.writeAttribute('ktr')»
			«firstAttributeList.writeAttribute('mtt')»
		</Absorption>
	'''
	
	def writeDepotWithTarget(ListDefinition it)'''
		<Depot>
			«writeValue("adm", compartmentNum)»
			«firstAttributeList.writeTargetAttribute»
			«firstAttributeList.writeAttribute('ka')»
			«firstAttributeList.writeAttribute('tlag')»
			«firstAttributeList.writeAttribute('finput')»
			«firstAttributeList.writeAttribute('modelDur')»
			«firstAttributeList.writeAttribute('ktr')»
			«firstAttributeList.writeAttribute('mtt')»
		</Depot>
	'''
	
	def writeTransfer(AnonymousListStatement it)'''
		<Transfer>
			«list.writeFromValue('from')»
			«list.writeToValue('to')»
			«list.writeAttribute('kt')»
		</Transfer>
	'''
	
	def writeElimination(AnonymousListStatement it)'''
		<Elimination>
			«list.writeFromValue»
			«list.writeAttribute('v')»
			«list.writeAttribute('k')»
			«list.writeAttribute('cl')»
			«list.writeAttribute('vm')»
			«list.writeAttribute('km')»
		</Elimination>
	'''
	
	def findCompartment(MclObject mObj, SymbolReference to){
		for (s: mObj.mdlCompartmentStatements){
			switch(s){
				ListDefinition:{
					if(s.name == to.ref.name && s.firstAttributeList.getAttributeEnumValue('type') == 'compartment') return s
				}
					
			}
		}
		return null;
	}
	
	def writeValue(Expression value, String arg)'''
		<Value argument="«arg»"> 
			«value.expressionAsAssignment»
		</Value>
	'''

	def writeValue( String arg, int value)'''
		<Value argument="«arg»"> 
			<ct:Int>«value»</ct:Int>
		</Value>
	'''

}
