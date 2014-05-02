/*******************************************************************************
 * Copyright (C) 2014 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.pharmml2nmtran.model


abstract class Parameter {
    protected String representation
    protected boolean isDefinedInParameterModel

    /**
     * 
     * @param representation the name of the parameter in PharmML input. In NMTRAN it is not used, we only print that in comments.
     * @param isDefinedInParameterModel true if the parameter is defined inside the parameter model, false otherwise. 
     */
    public Parameter(String representation, boolean isDefinedInParameterModel) {
        this.representation = representation
        this.isDefinedInParameterModel = isDefinedInParameterModel
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((representation == null) ? 0 : representation.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Parameter other = (Parameter) obj;
        if (representation == null) {
            if (other.representation != null) {
                return false;
            }
        } else if (!representation.equals(other.representation)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("[%s, %s]", representation, isDefinedInParameterModel);
    }
        
}
