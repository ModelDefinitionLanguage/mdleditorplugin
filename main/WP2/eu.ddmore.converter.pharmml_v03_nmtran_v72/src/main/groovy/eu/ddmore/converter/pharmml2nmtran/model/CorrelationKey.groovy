/*******************************************************************************
 * Copyright (C) 2014 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.pharmml2nmtran.model

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class CorrelationKey {

    private Set<String> etas;
    
    public CorrelationKey(String eta1, String eta2) {
        etas = new HashSet<String>()
        etas.add(eta1);
        etas.add(eta2);
    }

    @Override
    public String toString() {
        return "CorrelationKey [etas=" + etas + "]";
    }
}
