/*******************************************************************************
 * Copyright (C) 2002 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.service;

import java.util.Collection;

import eu.ddmore.convertertoolbox.domain.ConversionCapability;

/**
 * implementing classes are responsible for collecting available Converion Capabilities
 */
public interface ConversionCapabilitiesProvider {
    
    /**
     * 
     * @return a list of capabilities representing availalbe conversions
     */
    Collection<ConversionCapability> getCapabilities();
    
}
