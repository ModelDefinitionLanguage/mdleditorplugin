/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.domain;

/**
 * Represents conversion execution status, the order of the elements is important and it represents valid transitions
 * of the Conversion statuses.
 */
public enum ConversionStatus {
    New,
    Scheduled,
    Running, 
    Completed;
}
