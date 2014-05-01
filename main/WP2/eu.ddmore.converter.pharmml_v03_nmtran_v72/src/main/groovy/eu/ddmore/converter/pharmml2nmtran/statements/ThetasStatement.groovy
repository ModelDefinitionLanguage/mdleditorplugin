/*******************************************************************************
 * Copyright (C) 2014 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.pharmml2nmtran.statements

import eu.ddmore.converter.pharmml2nmtran.model.Theta
import eu.ddmore.converter.pharmml2nmtran.utils.Parameters;
import eu.ddmore.libpharmml.dom.PharmML;
import eu.ddmore.libpharmml.dom.modellingsteps.EstimationStepType
import eu.ddmore.libpharmml.dom.modellingsteps.SimulationStepType
import eu.ddmore.converter.pharmml2nmtran.utils.ConverterUtils;

import javax.xml.bind.JAXBElement


class ThetasStatement extends NMTranFormatter {
    private Parameters parameters
    private PharmML pmlDOM
    private ConverterUtils converterUtils

    def getStatement() {
        def sb = new StringBuilder("\$THETA\n");
        def thetaToNMTRAN = [:]
        for (JAXBElement elem in pmlDOM.modellingSteps.commonModellingStep) {
            if (elem.value instanceof EstimationStepType) {
                convertEstimationStep(elem.value, thetaToNMTRAN)
            }
            if (elem.value instanceof SimulationStepType) {
                convertSimulationStep(elem.value, thetaToNMTRAN)
                
            }
        }
        thetaToNMTRAN.putAll(considerThetasInBolus())
        thetaToNMTRAN.putAll(considerThetasInCovariates())
        for (int i=1; i<=thetaToNMTRAN.size(); i++) {
            sb << thetaToNMTRAN[i]
        }
        sb
    }

    private void convertSimulationStep(EstimationStepType simStep, thetaToNMTRAN) {
        simStep.variableAssignment.each {
            String name = it.symbRef.symbIdRef
            Theta theta = parameters.isTheta(name)
            if (theta) {
                thetaToNMTRAN[theta.index] = converterUtils.convert(it)
            }
        }
    }
    
    private void convertEstimationStep(EstimationStepType estStep, thetaToNMTRAN) {
        estStep.parametersToEstimate.parameterEstimation.each {
            String name = it.symbRef.symbIdRef
            Theta theta = parameters.isTheta(name)
            if (theta) {
                thetaToNMTRAN[theta.index] = converterUtils.convert(it)
            }
        }
    }
    
    def considerThetasInBolus() {
        def thetaToNMTRAN = [:]
        pmlDOM.trialDesign.structure.activity.each {
            if (it.dosingRegimen.value.steadyState) {
                String name = it.dosingRegimen.value.steadyState.interval.symbRef.symbIdRef
                Double value = it.dosingRegimen.value.steadyState.interval.assign.scalar.value.value
                int thetaIndex = parameters.isTheta(name).index
                thetaToNMTRAN[thetaIndex] = endline("${value} FIX ; ${name.toUpperCase()}")
            }
        }
        thetaToNMTRAN
    }
    
    def considerThetasInCovariates() {
        def thetaToNMTRAN = [:]
        pmlDOM.modelDefinition.covariateModel.each {
            it.simpleParameter.each {
                Theta theta = parameters.isTheta(it.symbId)
                if (theta) {
                    thetaToNMTRAN[theta.index] = endline("${it.assign.scalar.value.value} FIX ; ${it.symbId.toUpperCase()}")                  
                }
            }
        }
        thetaToNMTRAN
    }
}
