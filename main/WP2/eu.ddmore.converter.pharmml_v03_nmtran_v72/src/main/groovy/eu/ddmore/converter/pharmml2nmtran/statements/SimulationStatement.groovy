/*******************************************************************************
 * Copyright (C) 2014 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.pharmml2nmtran.statements

import eu.ddmore.libpharmml.dom.PharmML;
import eu.ddmore.libpharmml.dom.modellingsteps.EstimationStepType
import eu.ddmore.libpharmml.dom.modellingsteps.SimulationStepType
import javax.xml.bind.JAXBElement


class SimulationStatement extends NMTranFormatter {

    def getStatement(PharmML pmlDOM) {
        def s = new StringBuilder()
        for (JAXBElement elem in pmlDOM.modellingSteps.commonModellingStep) {
            if (elem.value instanceof SimulationStepType) {
                s << "(20140331)"
            }
        }
        s
    }
}
