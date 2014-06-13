/*******************************************************************************
 * Copyright (C) 2014 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.pharmml2nmtran.utils;

import eu.ddmore.libpharmml.dom.commontypes.DerivativeVariableType
import eu.ddmore.libpharmml.dom.commontypes.IntValueType;
import eu.ddmore.libpharmml.dom.commontypes.RealValueType;
import eu.ddmore.libpharmml.dom.commontypes.Rhs;
import eu.ddmore.libpharmml.dom.commontypes.SymbolRefType;
import eu.ddmore.libpharmml.dom.commontypes.VariableDefinitionType
import eu.ddmore.libpharmml.dom.maths.BinopType;
import eu.ddmore.libpharmml.dom.maths.Equation
import eu.ddmore.libpharmml.dom.maths.LogicBinOpType;
import eu.ddmore.libpharmml.dom.maths.PieceType;
import eu.ddmore.libpharmml.dom.maths.UniopType;
import eu.ddmore.libpharmml.dom.modeldefn.IndividualParameterType
import eu.ddmore.libpharmml.dom.modeldefn.ModelDefinitionType
import eu.ddmore.libpharmml.dom.modeldefn.ParameterModelType
import eu.ddmore.libpharmml.dom.modeldefn.SimpleParameterType
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBElement;

public class ParameterVariableSortHelper {
	
	private Set<String> relatedVariables = new HashSet<String>()
	static final int LHS = 0
	static final int RHS = 1
	Map referencesMap = [:]
	Map rearrangedMap = [:]
	Map tempSortMap = [:]

	private Set<String> getName(Rhs assignment) {
		relatedVariables = new HashSet<String>()
			   if (assignment.equation) {
					 Equation equation = assignment.equation
		
					 if (equation.symbRef) {
							relatedVariables.add(equation.symbRef.symbIdRef)
					 } else if (equation.binop) {
							getName(equation.binop)
					 } else if (equation.piecewise) {
						for(PieceType nextPiece: equation.piecewise.piece){
							getName(nextPiece)
						 }
					 } else if (equation.uniop) {
							 getName(equation.uniop)
					 } else {
							throw new RuntimeException("Unexpected equation type: ${equation}")
					 }
			   }
			   relatedVariables
	}
		
	private void getName(BinopType bt) {
		getName(bt.getContent()[LHS].value)
		getName(bt.getContent()[RHS].value)
	}
	
	private void getName(LogicBinOpType lbt) {
		getName(lbt.getContent()[LHS].value)
		getName(lbt.getContent()[RHS].value)
	 }
	
	private void getName(PieceType pt) {
		if(pt.getCondition().getLogicBinop() !=null){
			LogicBinOpType logicBinOpType = pt.getCondition().getLogicBinop()
			getName(logicBinOpType.getContent()[LHS].value)
			getName(logicBinOpType.getContent()[RHS].value)
		}
	 }
			
	private String getName(UniopType ut) {
		if(ut.symbRef != null){
			relatedVariables.add(ut.symbRef.symbIdRef)
		}
	}

	private String getName(RealValueType rvt) {
		"NO_NAME_FOR_TYPE"
	 }
	
	private String getName(SymbolRefType srt) {
		relatedVariables.add(srt.symbIdRef)
	}

	private String getName(IntValueType ivt) {
	   // TODO: is returning an empty string a good thing to do here?
	   "NO_NAME_FOR_TYPE"
	}
	
	def getParameterVariableRefs(ModelDefinitionType modelDefinitionType){
		
		def getReferencesInParameter = { parameter ->
			def symbIdRefs = []

			parameter?.assign?.each {
				symbIdRefs.add(getName(parameter.assign))
			}
			symbIdRefs.flatten()
		}
		
		Map references = [:]
		
		def getReferenceForElements = {elem ->
			
			if(elem.value instanceof SimpleParameterType
				|| elem.value instanceof IndividualParameterType
				|| elem.value instanceof VariableDefinitionType
				|| elem.value instanceof DerivativeVariableType) {
				references[elem] = getReferencesInParameter(elem.value)
			}
		}
		
		for(ParameterModelType pmt in modelDefinitionType.parameterModel) {
			for ( JAXBElement elem in pmt.commonParameterElement) {
				getReferenceForElements(elem)
			}
		}
		
//		for(StructuralModelType structModelType in pmlDOM.modelDefinition.structuralModel) {
//			for ( JAXBElement elem in structModelType.commonVariable) {
//				getReferenceForElements(elem)
//			}
//		}
//
//		for(ObservationModelType obsModelType in pmlDOM.modelDefinition.observationModel) {
//			for ( JAXBElement elem in obsModelType.commonParameterElement) {
//				getReferenceForElements(elem)
//			}
//		}
		references
		
	}
	
	public Map arrangeParameterElements(referencesMap){
		this.referencesMap = referencesMap
		referencesMap.each {k,v->
			if(k instanceof JAXBElement){
				(v)?tempSortMap.put(k.value.symbId,k):rearrangedMap.put(k.value.symbId,k)
			}
		}

		tempSortMap.each { k,v ->
			if(!checkIfReferenceExists(rearrangedMap.keySet(),k)){
				compareNextParams(v)
			}
		}
		rearrangedMap
	}
	
	def compareNextParams(JAXBElement parameter){
		List nextReferenceList = referencesMap[parameter]
		for (String reference :nextReferenceList){
			if(reference.equals(parameter.value.symbId)){
				continue
			}
			if(!checkIfReferenceExists(rearrangedMap.keySet(),reference)){
				if(tempSortMap.get(reference)!=null){
					compareNextParams(tempSortMap.get(reference))
				}
			}
		}
		rearrangedMap.put(parameter.value.symbId,parameter);
	}
	
	private boolean checkIfReferenceExists(Set comparisonList, String nextReference){
		comparisonList.contains(nextReference)
	}
}
