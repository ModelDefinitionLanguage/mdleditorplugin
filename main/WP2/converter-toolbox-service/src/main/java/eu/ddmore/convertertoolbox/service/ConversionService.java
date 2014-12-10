package eu.ddmore.convertertoolbox.service;

import java.util.Collection;

import com.google.common.base.Optional;

import eu.ddmore.convertertoolbox.domain.Conversion;


/**
 * Implementations are responsible for executing conversions.
 * 
 * The service ensures that there are no race conditions when preparing and scheduling conversions
 */
public interface ConversionService {
    /**
     * schedules a conversion
     * @param conversion that should be performed
     * @return a conversion that has been scheduled extended with execution metadata
     * @throws IllegalArgumentException if conversion does not exist
     */
    Conversion schedule(Conversion conversion);

    /**
     * schedules a conversion
     * @param conversion that should be performed
     * @return a conversion that has been scheduled extended with execution metadata
     * @throws ExceededCapacity if the service reached its capacity
     * @throws IllegalArgumentException if conversion is invalid in any sense 
     */
    Conversion add(Conversion conversion) throws ExceededCapacity;
    
    /**
     * 
     * @return true if the conversion service reached its capacity and will not accept any more conversions
     */
    boolean isFull();
    
    /**
     * Removes conversion and all its resources
     * @param conversion conversion to be deleted
     * @return deleted conversion
     */
    void delete(Conversion conversion);
    
    /**
     * 
     * @return a collection of all conversions handled by this service
     */
    Collection<Conversion> getConversions();
    /**
     * 
     * @return get completed conversions
     */
    Collection<Conversion> getCompletedConversions();
    
    /**
     * 
     * @param conversion id
     * @return conversion for given id
     * @throws IllegalArgumentException if conversion 
     */
    Optional<Conversion> getConversionForId(String id);
}
