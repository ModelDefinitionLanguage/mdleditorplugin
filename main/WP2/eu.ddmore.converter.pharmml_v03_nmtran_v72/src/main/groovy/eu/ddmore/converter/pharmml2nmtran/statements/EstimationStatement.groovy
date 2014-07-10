/*******************************************************************************
 * Copyright (C) 2014 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.pharmml2nmtran.statements

import eu.ddmore.libpharmml.dom.PharmML;
import eu.ddmore.libpharmml.dom.modellingsteps.EstimationStepType
import javax.xml.bind.JAXBElement


class EstimationStatement extends NMTranFormatter {
	
	def estFIMFound = false;
    def getStatement(PharmML pmlDOM) {
        def sb = new StringBuilder()        
        for (JAXBElement elem in pmlDOM.modellingSteps.commonModellingStep) {
            if (elem.value instanceof EstimationStepType) {
                EstimationStepType estStep = (EstimationStepType) elem.value
                estStep.operation.each {
                    switch ( it.opType.value ) {
                        case "estPop":
                            sb << computeMethod(estStep.operation[0].algorithm)
                            break
                            
                        case "estFIM":
                            estFIMFound = true
                            break
                            
                        case "estIndiv":
                            break

                        default:
                            throw new RuntimeException("Only 'estPop', 'estFIM', or 'estIndiv' are allowed here.")
                            break
                    }
                }
            }
        }
        sb
    }

    def computeMethod(algorithm) {
        def sb = new StringBuilder()
        sb << "METHOD="
        if (algorithm) {
            if (algorithm.definition.equals("FOCEI")) {
                sb << "COND INTER MAXEVALS=9999 PRINT=10 NOABORT"
            }else if (algorithm.definition.equals("SAEM")) {
                sb << "SAEM INTER NBURN=2000 NITER=1000 ISAMPLE=2 IACCEPT=0.4 PRINT=10 CTYPE=3"
            } else {
                sb << algorithm.definition
            }
        } else {
            sb << "COND"
        }
        sb
    }
}
