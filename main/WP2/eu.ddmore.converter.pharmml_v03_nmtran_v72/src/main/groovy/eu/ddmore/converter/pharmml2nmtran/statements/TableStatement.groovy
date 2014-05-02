/*******************************************************************************
 * Copyright (C) 2014 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.pharmml2nmtran.statements

import java.util.List;

import eu.ddmore.converter.pharmml2nmtran.model.Theta;
import eu.ddmore.converter.pharmml2nmtran.utils.Parameters;
import eu.ddmore.converter.pharmml2nmtran.utils.ConversionContext;


class TableStatement extends NMTranFormatter {
    private Parameters parameters
    private String inputHeaders
    private ConversionContext converterUtils
    
    def getStatement(String fileBase) {
        def sb = new StringBuilder();
        sb << "\$TABLE ${inputHeaders} FILE=${fileBase}_data.tab\n\$TABLE "
        if (parameters.groups) {
            for (String group : parameters.groups.keySet()) {
                sb << rename(group.toUpperCase())
                sb << " "
            }
        } else {
            for (Theta theta : parameters.getThetas().values()) {
                if (theta.isDefinedInParameterModel) {
                    sb << rename(theta.representation.toUpperCase())
                    sb << " "
                }
            }
        }

        sb << "\n"
        for (Map.Entry<String, String> e : parameters.etaToOmega.entrySet()) {
            String eta = e.key
            String omega = e.value
            int omegaIndex = converterUtils.omegasInPrintOrder.indexOf(omega)+1
            
            sb << "ETA${omegaIndex} "
        }
        sb << endline("Y FILE=${fileBase}_params.tab NOAPPEND NOPRINT ONEHEADER")
    }

}
