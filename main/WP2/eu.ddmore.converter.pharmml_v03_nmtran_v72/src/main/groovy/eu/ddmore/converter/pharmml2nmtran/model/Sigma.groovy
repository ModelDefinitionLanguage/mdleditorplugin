/*******************************************************************************
 * Copyright (C) 2014 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.pharmml2nmtran.model



public class Sigma extends Parameter {
    
    private static final CONSTANT_SIGMA = "_Constant_Sigma_"
    private int index

    public Sigma(String representation, int index, boolean isDefinedInParameterModel) {
        super(representation, isDefinedInParameterModel)
        this.index = index
    }
    
    public Sigma(boolean isDefinedInParameterModel, int index) {
        super("${CONSTANT_SIGMA}${index}", isDefinedInParameterModel)
        this.representation = representation
        this.isDefinedInParameterModel = isDefinedInParameterModel
        this.index = index
    }

    public String toIndexString() {
        return String.format("SIGMA(%d)", index);
    }

}
