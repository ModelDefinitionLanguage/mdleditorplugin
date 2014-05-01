/*******************************************************************************
 * Copyright (C) 2014 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.pharmml2nmtran.model



public class Omega extends Parameter {
    private int index

    public Omega(String representation, int index, boolean isDefinedInParameterModel) {
        super(representation, isDefinedInParameterModel)
        this.index = index
    }
    
    public String toIndexString() {
        return String.format("OMEGA(%d)", index);
    }

}
