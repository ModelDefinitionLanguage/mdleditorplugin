/*******************************************************************************
 * Copyright (C) 2014 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.pharmml2nmtran.statements

import java.util.Map;

import eu.ddmore.converter.pharmml2nmtran.equivalence.EquivalenceClass
import eu.ddmore.converter.pharmml2nmtran.equivalence.EquivalenceClassesComputer
import eu.ddmore.converter.pharmml2nmtran.model.CorrelationKey
import eu.ddmore.converter.pharmml2nmtran.utils.Parameters;
import eu.ddmore.converter.pharmml2nmtran.utils.ConversionContext;
import eu.ddmore.libpharmml.dom.PharmML;
import eu.ddmore.libpharmml.dom.commontypes.VariableAssignmentType
import eu.ddmore.libpharmml.dom.modeldefn.CorrelationType
import eu.ddmore.libpharmml.dom.modeldefn.SimpleParameterType
import eu.ddmore.libpharmml.dom.modellingsteps.EstimationStepType
import eu.ddmore.libpharmml.dom.modellingsteps.ParameterEstimateType
import eu.ddmore.libpharmml.dom.modellingsteps.SimulationStepType
import javax.xml.bind.JAXBElement


class OmegasStatement extends NMTranFormatter {
    private Parameters parameters
    private PharmML pmlDOM
    private ConversionContext conversionContext
	def omegasFoundInCorrelations = []
    
    def getStatement() {
        def sb = new StringBuilder();
        
        for (JAXBElement elem in pmlDOM.modellingSteps.commonModellingStep) {
            if (elem.value instanceof EstimationStepType) {
                EstimationStepType estStep = (EstimationStepType) elem.value
                estStep.parametersToEstimate?.parameterEstimation.each {
                    sb << considerOmega(it, omegasFoundInCorrelations)
                }
            }
            if (elem.value instanceof SimulationStepType) {
                SimulationStepType simStep = (SimulationStepType) elem.value
                simStep.variableAssignment.each {
                    sb << considerOmega(it, omegasFoundInCorrelations)
                }
            }
        }
        sb << considerOmegasInCovariates()
        sb << considerFixedOmegasInParameterModel()
        sb
    }

	def getCorrelatedEquivalenceClass() {
		def sb = new StringBuilder()
		EquivalenceClassesComputer eqClassesComputer = getCorrelations()
		for (EquivalenceClass eqClass : eqClassesComputer.equivalenceClasses) {
			eqClass.elements.each {
				omegasFoundInCorrelations.add(parameters.etaToOmega[it])
			}
			sb<< convert(eqClass)
		}
		sb
	}
    
    def considerOmegasInCovariates() {
        def sb = new StringBuilder()
        pmlDOM.modelDefinition.covariateModel.each {
            it.simpleParameter.each {
                if (parameters.isOmega(it.symbId)) {
                    sb << "${it.assign.scalar.value.value} FIX ; ${it.symbId.toUpperCase()}\n"
                }
            }
        }
        sb
    }

    private StringBuilder considerFixedOmegasInParameterModel() {
        def sb = new StringBuilder()
        pmlDOM.modelDefinition.parameterModel.each { paramModel ->
            paramModel.commonParameterElement.each { commonParamElem ->
                if (commonParamElem.value instanceof SimpleParameterType) {
                    String name = commonParamElem.value.symbId
                    if (parameters.isOmega(name) && commonParamElem.value.assign) {
                        sb << "${commonParamElem.value.assign.scalar.value.value} FIX ; ${name.toUpperCase()}\n"
                        conversionContext.omegasInPrintOrder.add(name)
                    }
                }
            }
        }
        sb
    }
    
    private StringBuilder considerOmega(type, omegasFoundInCorrelations) {
        def sb = new StringBuilder()
        String name = type.symbRef.symbIdRef        
        if (parameters.isOmega(name)) {
            if (!omegasFoundInCorrelations.contains(name)) {
                sb << conversionContext.convert(type)
                conversionContext.omegasInPrintOrder.add(name)
            }
        }
        return sb
    }

    private EquivalenceClassesComputer getCorrelations() {
        Set<String> elements = new HashSet<String>();
        def equalsMapping = [:]
        for (CorrelationType correlation : parameters.correlations.values()) {
            elements.add(correlation.pairwise.randomVariable1.symbRef.symbIdRef)
            elements.add(correlation.pairwise.randomVariable2.symbRef.symbIdRef)
            equalsMapping[correlation.pairwise.randomVariable1.symbRef.symbIdRef] = correlation.pairwise.randomVariable2.symbRef.symbIdRef
        }
        EquivalenceClassesComputer eqClassesComputer = new EquivalenceClassesComputer(elements, equalsMapping)
        eqClassesComputer.populate()
        eqClassesComputer
    }
    
    public StringBuilder convert(EquivalenceClass eqClass) {
        int dimension = eqClass.elements.size()
        def sb = new StringBuilder();
        sb << "\$OMEGA BLOCK(${dimension})\n"
        String[] elements = eqClass.elements.toArray()

        for (int i=0; i<dimension; i++) {
            String eta1 = elements[i]
            conversionContext.omegasInPrintOrder.add(parameters.etaToOmega[eta1])
            for (int j=0; j<=i; j++) {
                if (i == j) {
                    sb << findInitialEstimateOf(parameters.etaToOmega[eta1])
                } else {
                    String eta2 = elements[j]
                    CorrelationKey key = new CorrelationKey(eta1, eta2)
                    String correlationName = parameters.correlationkeyToName[key]
                    sb << findInitialEstimateOf(correlationName)
                    sb << " "
                }
            }
            sb << "; ${parameters.etaToOmega[eta1]}\n"
        }
        sb
    }

    def findInitialEstimateOf(String idref) {
        def sb = new StringBuilder()
        for (JAXBElement elem in pmlDOM.modellingSteps.commonModellingStep) {
            if (elem.value instanceof EstimationStepType) {
                EstimationStepType estStep = (EstimationStepType) elem.value
                ParameterEstimateType parameterEstimateType = estStep.parametersToEstimate.parameterEstimation.find {
                    it.symbRef.symbIdRef.equals(idref)
                }
                //if no correlation is defined in any ParameterEstimation element
                if (parameterEstimateType) {
                    sb << conversionContext.convert(parameterEstimateType, false)
                }
            }
            if (elem.value instanceof SimulationStepType) {
                SimulationStepType simStep = (SimulationStepType) elem.value
                VariableAssignmentType type = simStep.variableAssignment.find {
                    it.symbRef.symbIdRef == idref
                }
                sb << conversionContext.convert(type, false)
            }
        }
        sb
    }

}
