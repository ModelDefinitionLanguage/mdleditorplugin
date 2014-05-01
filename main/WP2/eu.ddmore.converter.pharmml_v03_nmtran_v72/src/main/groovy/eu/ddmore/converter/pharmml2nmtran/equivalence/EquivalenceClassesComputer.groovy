/*******************************************************************************
 * Copyright (C) 2014 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.pharmml2nmtran.equivalence


/**
 * Computes the set of equivalence classes given as input a set of elements and an equivalence relation defined on their domain (here symboled by 'T').
 * Refer to http://en.wikipedia.org/wiki/Equivalence_class for more details.
 * 
 * This computation is needed to find which omegas comprise which multidimensional blocks. 
 * Every single block is an equivalence class, where the equivalence relation R is defined on the set of all Omegas, 
 * and (o1, o2) in R <=> o1 and o2 are correlated (i.e. should appear in the same multidimensional omega block).
 *
 * @param <T> the domain on which equivalence classes are computed
 */
class EquivalenceClassesComputer<T> {

    private final Set<T> elements
    private final Map<T,T> equalsMapping
    private final Set<EquivalenceClass<T>> equivalenceClasses

    //Defined to efficiently find if the equivalence class that an element belongs to.
    private Map<T,EquivalenceClass<T>> elementToEqClass

    /**
     * Creates an equivalence class per element
     */
    public EquivalenceClassesComputer(Set<T> elements, Map<T,T> equalsMapping) {
        this.elements = elements;
        this.equalsMapping = equalsMapping;
        
        equivalenceClasses = new HashSet<EquivalenceClass<T>>()
        elementToEqClass = new HashMap<T,EquivalenceClass<T>>()
        for (T element : elements) {
            EquivalenceClass eqClass = new EquivalenceClass();
            eqClass.add(element)
            equivalenceClasses.add(eqClass)
            elementToEqClass.put(element, eqClass)
        }
    }
    
    def populate() {
        equalsMapping.each {key, value -> 
            EquivalenceClass keyClass = findEquivalenceClassOf(key)
            EquivalenceClass valueClass = findEquivalenceClassOf(value)
            merge(keyClass, valueClass, value)
        }
    }

    def findEquivalenceClassOf(T element) {
        elementToEqClass.get(element)
    }
    
    def merge(EquivalenceClass keyClass, EquivalenceClass valueClass, T element) {
        keyClass.merge(valueClass)
        equivalenceClasses.remove(valueClass)
        elementToEqClass.put(element, keyClass)
    }
}
