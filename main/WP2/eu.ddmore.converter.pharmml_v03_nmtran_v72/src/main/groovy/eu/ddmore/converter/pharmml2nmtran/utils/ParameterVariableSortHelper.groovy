/*******************************************************************************
 * Copyright (C) 2014 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.pharmml2nmtran.utils;

import eu.ddmore.libpharmml.dom.commontypes.CommonVariableDefinitionType;
import eu.ddmore.libpharmml.dom.commontypes.DerivativeVariableType
import eu.ddmore.libpharmml.dom.commontypes.IdValueType;
import eu.ddmore.libpharmml.dom.commontypes.IntValueType;
import eu.ddmore.libpharmml.dom.commontypes.RealValueType;
import eu.ddmore.libpharmml.dom.commontypes.Rhs;
import eu.ddmore.libpharmml.dom.commontypes.Scalar;
import eu.ddmore.libpharmml.dom.commontypes.SymbolRefType;
import eu.ddmore.libpharmml.dom.commontypes.VariableDefinitionType
import eu.ddmore.libpharmml.dom.maths.BinopType;
import eu.ddmore.libpharmml.dom.maths.ConstantType;
import eu.ddmore.libpharmml.dom.maths.Equation
import eu.ddmore.libpharmml.dom.maths.FunctionCallType;
import eu.ddmore.libpharmml.dom.maths.LogicBinOpType;
import eu.ddmore.libpharmml.dom.maths.PieceType;
import eu.ddmore.libpharmml.dom.maths.UniopType;
import eu.ddmore.libpharmml.dom.modeldefn.IndividualParameterType
import eu.ddmore.libpharmml.dom.modeldefn.ModelDefinitionType
import eu.ddmore.libpharmml.dom.modeldefn.ObservationModelType
import eu.ddmore.libpharmml.dom.modeldefn.ParameterModelType
import eu.ddmore.libpharmml.dom.modeldefn.SimpleParameterType
import eu.ddmore.libpharmml.dom.modeldefn.StructuralModelType
import eu.ddmore.libpharmml.dom.uncertml.RealArrayValueType;

import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBElement;

import org.codehaus.groovy.util.StringUtil;

public class ParameterVariableSortHelper {
	
	private Set<String> relatedVariables = new HashSet<String>()
	static final int LHS = 0
	static final int RHS = 1
	static final int ALL = -1
	static final String SKIP= ""
	String dadt = "DADT"
	Map referencesMap = [:]
	Map rearrangedMap = [:]
	Map tempSortMap = [:]

	/**
	 * Returns symbId names from Rhs of an equation.
	 * 
	 * @param assignment
	 * @return
	 */
	private Set<String> getSymbIdNames(Rhs assignment) {
		relatedVariables = new HashSet<String>()
		if (assignment.equation) {
			Equation equation = assignment.equation
			String symbIdName = getSymbIdName(equation,false)
			if(symbIdName && !symbIdName.isEmpty()){
				relatedVariables.add(symbIdName)
			}
		}
		if(assignment.symbRef){
			relatedVariables.add("INITIAL_VALUE")
			getSymbIdName(assignment.symbRef, ALL)
		}
		if(assignment.scalar){
			getSymbIdName(assignment.scalar, ALL)
		}
		relatedVariables
	}

	/**
	 * Sets symbId names associated to an equation. 
	 * We can retrieve LHS only of this equation by passing boolean flag for the same.
	 * 
	 * @param equation
	 * @param isLHSOnly
	 * @return
	 */
	def getSymbIdName(Equation equation, boolean isLHSOnly){
		
		if (equation.symbRef) {
			relatedVariables.add(equation.symbRef.symbIdRef)
			equation.symbRef.symbIdRef
		} else if (equation.binop) {
			isLHSOnly?getSymbIdName(equation.binop,LHS):getSymbIdName(equation.binop, ALL)
		} else if (equation.piecewise) {
			for(PieceType nextPiece: equation.piecewise.piece){				
				isLHSOnly?getSymbIdName(nextPiece,LHS):getSymbIdName(nextPiece, ALL)
			}
		} else if (equation.uniop) {
			isLHSOnly?getSymbIdName(equation.uniop,LHS):getSymbIdName(equation.uniop,ALL)
		} else if(equation.scalar){
			getSymbIdName(equation.scalar, ALL)
		}
	}

	/**
	 * Gets symb Id name for BinopType
	 * 
	 * @param type
	 * @param index
	 * @return
	 */
	def getSymbIdName(BinopType type, Integer index) {
		if(index == ALL){
			getSymbIdName(type.getContent()[LHS].value,index)
			getSymbIdName(type.getContent()[RHS].value,index)
		}else{
			getSymbIdName(type.getContent()[index].value,index)
		}
	}

	/**
	 * Gets symb Id names associated in a FunctionCallType
	 * 
	 * @param type
	 * @param index
	 * @return
	 */
	def getSymbIdName(FunctionCallType type, Integer index) {
		getSymbIdName(type.symbRef,index)
		if(type.functionArgument){
			type.functionArgument.each {
				if (it!=null)
					getSymbIdName(it,index)
			}
		}
	}

	/**
	 * Gets symb Id names associated in a logic binop type.
	 * 
	 * @param type
	 * @param index
	 * @return
	 */
	def getSymbIdName(LogicBinOpType type, Integer index) {
		if(index == ALL){
			getSymbIdName(type.getContent()[LHS].value,index)
			getSymbIdName(type.getContent()[RHS].value,index)
		}else{
			getSymbIdName(type.getContent()[index].value,index)
		}
	}

	/**
	 * 
	 * @param pt
	 * @param index
	 * @return
	 */
	def getSymbIdName(PieceType pt, Integer index) {
		if(pt.getCondition().getLogicBinop() !=null){
			LogicBinOpType logicBinOpType = pt.getCondition().getLogicBinop()
			if(index == ALL){
				getSymbIdName(logicBinOpType.getContent()[LHS].value,index)
				getSymbIdName(logicBinOpType.getContent()[RHS].value,index)
			}else{
				getSymbIdName(logicBinOpType.getContent()[index].value,index)
			}
		}
	}
	
	def getSymbIdName(UniopType ut, Integer index) {
		if(ut.symbRef != null){
			getSymbIdName(ut.symbRef,index)
		}else if(ut.binop){
			getSymbIdName(ut.binop,index)
		}else if(ut.uniop){
			getSymbIdName(ut.uniop,index)
		}else if(ut.scalar){
			getSymbIdName(ut.scalar, ALL)
		}
	}
	
	def getSymbIdName(SymbolRefType srt, Integer index) {
		relatedVariables.add(srt.symbIdRef)
		srt.symbIdRef
	}
	
	def getSymbIdName(IdValueType idv, Integer index) {
		SKIP
	}
	
	def getSymbIdName(ConstantType constant, Integer index) {
		SKIP
	}
	
	def getSymbIdName(RealValueType rvt, Integer index) {
		SKIP
	 }

	def getSymbIdName(IntValueType ivt, Integer index) {
		SKIP
	}

	def getSymbIdName(JAXBElement<Object> scalarNames, Integer index) {
		if(index == ALL){
			if(scalarNames){
				if(scalarNames.value){
					if(scalarNames.value instanceof RealValueType){
						relatedVariables.add("INITIAL_VALUE")
					}
				}else {
					throw new RuntimeException("Unexpected equation type: ${scalarNames}")
				}
			}
		} else{
			SKIP
		}
	}
	
	def getParameterVariableRefs(ModelDefinitionType modelDefinitionType){
		
		def getReferencesInParameter = { parameter ->
			def symbIdRefs = []

			parameter?.assign?.each {
				symbIdRefs.add(getSymbIdNames(parameter.assign))
			}
			symbIdRefs.flatten()
		}
		
		def checkForInitialConditionValues = { parameter ->
			def symbIdRefs = []
			if(parameter?.initialCondition?.initialValue?.assign){
				parameter.initialCondition?.initialValue.assign.each {
					symbIdRefs.add(getSymbIdNames(parameter.initialCondition.initialValue.assign))
				}
			}
			symbIdRefs.flatten()
		}
		
		Map references = [:]
		def dadtCount = 0
		
		def getReferenceForElements = {elem ->
			
			if(elem.value instanceof SimpleParameterType
				|| elem.value instanceof IndividualParameterType
				|| elem.value instanceof VariableDefinitionType
				) {				
				references[elem] = getReferencesInParameter(elem.value)
			}
			if(elem.value instanceof DerivativeVariableType){				
				references[elem] = checkForInitialConditionValues(elem.value)
				def refList = getReferencesInParameter(elem.value)
				if(refList){					
					dadtCount++
					references[dadt+dadtCount] = refList
				}
			}
		}
		
		for(ParameterModelType pmt in modelDefinitionType.parameterModel) {
			for ( JAXBElement elem in pmt.commonParameterElement) {
				getReferenceForElements(elem)
			}
		}
		
		for(StructuralModelType structModelType in modelDefinitionType.structuralModel) {
			for ( JAXBElement elem in structModelType.commonVariable) {
				getReferenceForElements(elem)
			}
		}

		for(ObservationModelType obsModelType in modelDefinitionType.observationModel) {
			for ( JAXBElement elem in obsModelType.commonParameterElement) {
				getReferenceForElements(elem)
			}
		}
		references
		
	}
	
	Set paramsLookingFor = []
	public Map arrangeParameterElements(referencesMap){
		this.referencesMap = referencesMap
		referencesMap.each {k,v->
			if(k instanceof JAXBElement){
				(v)?tempSortMap.put(k.value.symbId,k):rearrangedMap.put(k.value.symbId,k)
			}
			else if(isDADT(k)){
					tempSortMap.put(k,k)
				}
		}

		tempSortMap.each { k,v ->
			if(!checkIfReferenceExists(rearrangedMap.keySet(),k)){
				compareNextParams(v)
			}
		}
		rearrangedMap
	}

	private boolean isDADT(Object objToTest){
		(objToTest instanceof String)?objToTest.contains(dadt):false
	}
	def compareNextParams(Object parameter){
		if(parameter!=null){
			List nextReferenceList = referencesMap[parameter]			
			def symbId = (isDADT(parameter))?parameter:parameter.value.symbId
			paramsLookingFor.add(symbId)
			for (String reference :nextReferenceList){
				
				if(reference.equals(symbId)){
					continue
				}
				if(reference.equals("INITIAL_VALUE")){
					if(!isDADT(parameter)){
						rearrangedMap.put(symbId,parameter)
						paramsLookingFor.remove(symbId)
					}
				}else if(!checkIfReferenceExists(rearrangedMap.keySet(),reference)){
					if(paramsLookingFor.contains(reference)){
						throw new RuntimeException("Cyclic dependency encountered for : "+ reference + " to : "+ symbId)					 
					}else{
						compareNextParams(tempSortMap.get(reference))
					}
				}
			}
			if(!isDADT(parameter)){
				rearrangedMap.put(symbId,parameter)
			}
			paramsLookingFor.remove(symbId)
		}
	}
	
	private boolean checkIfReferenceExists(Set comparisonList, String nextReference){
		comparisonList.contains(nextReference)
	}
}
