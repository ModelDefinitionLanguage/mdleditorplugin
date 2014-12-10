package eu.ddmore.convertertoolbox.service;

import java.util.Collection;

import com.google.common.base.Optional;

import eu.ddmore.convertertoolbox.domain.Conversion;
import eu.ddmore.convertertoolbox.domain.ConversionStatus;

/**
 * implementations are responsible for holding a registry of Conversions
 */
public interface ConversionRepository {
    
    /**
     * 
     * @param id conversion id
     * @return conversion with given id or unset optional
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
     * @return number of uncompleted conversions
     */
    int countUncompletedConversions();

    /**
     * Removes conversion
     * @param conversion
     */
    void delete(Conversion conversion);
}