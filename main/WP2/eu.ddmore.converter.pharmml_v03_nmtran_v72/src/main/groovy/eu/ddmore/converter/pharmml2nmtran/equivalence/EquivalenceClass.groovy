/*******************************************************************************
 * Copyright (C) 2014 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.pharmml2nmtran.equivalence

/**
 * This class models and equivalence class, i.e. a set of elements that are equivalent according to some equivalence relation defined
 * on their domain.
 *
 * @param <T> the type of elements comprising the Equivalence Class.
 */
class EquivalenceClass<T> {

    private Set<T> elements 
    
    public EquivalenceClass() {
        elements = new HashSet<T>()
    }
    
    def add(T element) {
        elements.add(element)
    }
 
    def merge(EquivalenceClass<T> other) {
        this.elements.addAll(other.elements)
    }
    
    def includes(T element) {
        return elements.contains(element)
    }
    
    public String toString() {
        elements.join(" ")
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((elements == null) ? 0 : elements.hashCode());
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
        EquivalenceClass other = (EquivalenceClass) obj;
        if (elements == null) {
            if (other.elements != null) {
                return false;
            }
        } else if (!elements.equals(other.elements)) {
            return false;
        }
        return true;
    }
    
}