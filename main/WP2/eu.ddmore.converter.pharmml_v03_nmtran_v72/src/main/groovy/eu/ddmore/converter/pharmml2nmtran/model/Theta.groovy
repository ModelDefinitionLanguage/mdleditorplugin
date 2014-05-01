/*******************************************************************************
 * Copyright (C) 2014 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.pharmml2nmtran.model



public class Theta extends Parameter {

    private int index
    
    public Theta(String representation, boolean isDefinedInParameterModel, int index) {
        super(representation, isDefinedInParameterModel)
        this.index = index
    }

    public String toIndexString() {
        return String.format("THETA(%d)", index);
    }

    @Override
    public String toString() {
        toIndexString()
    }
}
