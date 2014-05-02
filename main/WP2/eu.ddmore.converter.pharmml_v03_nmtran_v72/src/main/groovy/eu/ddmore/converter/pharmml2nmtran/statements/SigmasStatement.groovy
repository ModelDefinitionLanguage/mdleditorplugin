/*******************************************************************************
 * Copyright (C) 2014 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.pharmml2nmtran.statements

import java.util.Map;
import javax.xml.bind.JAXBElement

import eu.ddmore.converter.pharmml2nmtran.model.Sigma
import eu.ddmore.converter.pharmml2nmtran.utils.ConversionContext;
import eu.ddmore.libpharmml.dom.PharmML;
import eu.ddmore.libpharmml.dom.modeldefn.ObservationModelType
import eu.ddmore.libpharmml.dom.modeldefn.ParameterRandomVariableType
import eu.ddmore.libpharmml.dom.modellingsteps.EstimationStepType
import eu.ddmore.libpharmml.dom.modellingsteps.SimulationStepType


class SigmasStatement extends NMTranFormatter {
    private PharmML pmlDOM
    private Map<String, String> sigmasToNMtran
    private Map<String, String> epsilonToSigma = [:]
    private ConversionContext converterUtils

    def getStatement() {
        computeSigmasToNMTRAN()
        
        def sb = new StringBuilder("\$SIGMA\n");
        boolean sigmaFound=false
        
        pmlDOM.modelDefinition.observationModel.each { type ->
            type.commonParameterElement.each {
                if (it.value instanceof ParameterRandomVariableType) {
                    boolean sigmaFoundThisTime = considerRandomVariable(it.value, sb)
                    if (sigmaFoundThisTime) {
                        sigmaFound = sigmaFoundThisTime
                    }
                }
            }
        }
        if (!sigmaFound) {
            sb << "0 FIX\n"
        }
        sb
    }

    private boolean considerRandomVariable(ParameterRandomVariableType type, StringBuilder sb) {
        boolean sigmaFound=false
        if ( representsError(type.variabilityReference.symbRef.symbIdRef) ) {
            sigmaFound=true
            String varId
            String sigmaRepresentation
            if (type.abstractContinuousUnivariateDistribution.value.stddev) {
                IdRepresentaionPair res = loadSigmaWithStddev(type.abstractContinuousUnivariateDistribution.value.stddev)
                varId = res.id
                sigmaRepresentation = res.representaion                
            } else if (type.abstractContinuousUnivariateDistribution.value.variance) {
                varId = type.abstractContinuousUnivariateDistribution.value.variance.var.varId
                sigmaRepresentation = varId
            }

            epsilonToSigma.put(type.symbId, sigmaRepresentation)

            if (isNumeric(varId)) {
                sb << endline("$varId FIX")
            } else {
                sb << fixParameters(varId)
            }
        }
        sigmaFound
    }
    
    private void computeSigmasToNMTRAN() {
        sigmasToNMtran = new HashMap<String, String>()
        for (JAXBElement elem in pmlDOM.modellingSteps.commonModellingStep) {
            collectSigmaParameters(elem.value)
        }
    }
    
    private void collectSigmaParameters(EstimationStepType estStep) {
        estStep.parametersToEstimate.parameterEstimation.each {
            considerSigma(it)
        }
    }
    
    private void collectSigmaParameters(SimulationStepType simStep) {
        simStep.variableAssignment.each {
            considerSigma(it)
        }
    }
    
    private void considerSigma(type) {
        String name = type.symbRef.symbIdRef
        if (converterUtils.parameters.isSigma(name)) {
            sigmasToNMtran.put(name, converterUtils.convert(type))
        }
    }

    private StringBuilder fixParameters(varId) {
        def sb = new StringBuilder();
        String nmtran = sigmasToNMtran.get(varId)
        if (nmtran) {
            if (nmtran.contains("FIX")) {
                nmtran = nmtran.replace("(", "").replace(")", "")
            }
            sb << nmtran
        }
        sb
    }

    private IdRepresentaionPair loadSigmaWithStddev(stddev) {
        IdRepresentaionPair res = new IdRepresentaionPair()
        
        if (stddev.var) {
            res.id = stddev.var.varId
            res.representaion = stddev.var.varId
        } else {
            res.id = stddev.prVal.value
            Sigma sigma = converterUtils.parameters.createConstantSigma()
            res.representaion = sigma.representation
        }
        res
    }
        
    private boolean representsError(String symbol) {
        symbol.equals("residual") || symbol.equals("resErr")
    }

    private boolean isNumeric(String s) {
        try {
            Double.parseDouble(s)
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    
    private class IdRepresentaionPair {
        String id
        String representaion
    }

}
