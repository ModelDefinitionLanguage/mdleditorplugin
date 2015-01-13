/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.service;

import java.util.Collection;

import com.google.common.base.Optional;

import eu.ddmore.convertertoolbox.domain.internal.Conversion;
import eu.ddmore.convertertoolbox.domain.ConversionStatus;

/**
 * implementations are responsible for holding a registry of Conversions
 */
public interface ConversionRepository {
    
    /**
     * 
     * @param id conversion id
     * @return conversion with given id or absent optional
     */
    Optional<Conversion> getConversion(String id);
    
    /**
     * 
     * @return all conversions
     */
    Collection<Conversion> getConversions();
    
    /**
     * Persists state of the conversion.
     * If conversion is new, the returned conversion should have an id set
     * @param conversion
     * @return persisted conversion
     */
    Conversion save(Conversion conversion);

    /**
     * 
     * @param status
     * @return all jobs with given conversion status
     */
    Collection<Conversion> getConversionsWithStatus(ConversionStatus status);

    /**
     * 
     * @param date
     * @return Conversions which have been completed before given date
     */
    Collection<Conversion> getConversionsCompletedEarlierThan(long date);
    
    /**
     * 
     * @return number of uncompleted conversions
     */
    int countIncompleteConversions();

    /**
     * Removes conversion
     * @param conversion
     */
    void delete(Conversion conversion);
}
