/*******************************************************************************
 * Copyright (C) 2014 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.pharmml2nmtran.model;

import java.util.Comparator;

import eu.ddmore.pharmacometrics.model.trialdesign.Subject;


public class SubjectComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        Subject s1 = o1
        Subject s2 = o2
        return s1.id - s2.id;
    }

}
