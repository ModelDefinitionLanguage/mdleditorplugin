package eu.ddmore.converter.pharmml2nmtran.equivalence;

import static org.junit.Assert.*;


import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.Before;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;


class EquivalenceClassesComputerTest {

    Set<String> elements
    Map<String,String> equalsMapping
    EquivalenceClassesComputer eqClassesComputer
    
    @Before
    public void init() {
        elements = Sets.newHashSet("a", "b", "c", "d", "e", "f")
        equalsMapping = ImmutableMap.of("a", "b", "b", "c", "d", "e")
        eqClassesComputer = new EquivalenceClassesComputer(elements, equalsMapping)
    }
    
    @Test
    public void shouldCheckIfAnEqClassIsCreatedForEachElement() {
        for (String element : elements) {
            EquivalenceClass elementsClass = eqClassesComputer.findEquivalenceClassOf(element)
            assertNotNull(elementsClass)
            assertTrue(elementsClass.includes(element))
            assertEquals(elementsClass.elements.size(), 1)
        }
    }
    
    @Test
    public void testEqClasses() {
        def aClassElements = Sets.newHashSet("a", "b", "c")
        def dClassElements = Sets.newHashSet("d", "e")
        def fClassElements = Sets.newHashSet("f")
        Set<EquivalenceClass<String>> expected = Sets.newHashSet(
            new EquivalenceClass("elements":aClassElements), 
            new EquivalenceClass("elements":dClassElements), 
            new EquivalenceClass("elements":fClassElements))
        
        eqClassesComputer.populate()        
        assertEquals(expected, eqClassesComputer.equivalenceClasses)
    }

}
