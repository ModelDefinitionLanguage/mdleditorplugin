/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.service.impl;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

import eu.ddmore.convertertoolbox.domain.internal.Conversion;
import eu.ddmore.convertertoolbox.domain.ConversionStatus;
import eu.ddmore.convertertoolbox.service.ConversionRepository;

/**
 * Basic implementation of conversion repository that uses a runtime map to store conversions
 */
@Repository
public class MapBackedConversionRepository implements ConversionRepository {
    
	private final Map<String,Conversion> conversions = new ConcurrentHashMap<String, Conversion>();
    
    @Override
    public Optional<Conversion> getConversion(String id) {
        Preconditions.checkArgument(StringUtils.isNotBlank(id), "Conversion id can't be blank");
        Conversion conversion = conversions.get(id);
        if(conversion==null) {
            return Optional.absent();
        } else {
            return Optional.of(copy(conversion));
        }
    }

    @Override
    public Collection<Conversion> getConversions() {
        return copy(conversions.values());
    }
    
    @Override
    public Conversion save(Conversion conversion) {
        Preconditions.checkNotNull(conversion,"Conversion should not be null");
        Conversion persisted = copy(conversion); //don't modify input parameter
        if(conversion.getId()==null) {
            persisted.setId(generateId());
        }
        conversions.put(persisted.getId(),persisted);
        return copy(persisted); // don't return an internal entity
    }

    @Override
    public Collection<Conversion> getConversionsWithStatus(final ConversionStatus status) {
        Preconditions.checkNotNull(status,"Conversion status should not be null");
        return copy(Collections2.filter(conversions.values(), new Predicate<Conversion>() {
            public boolean apply(Conversion conversion) {
                return status.equals(conversion.getStatus());
            }
        }));
    }

    @Override
    public int countIncompleteConversions() {
        return Collections2.filter(conversions.values(), new Predicate<Conversion>() {
            public boolean apply(Conversion conversion) {
                return ConversionStatus.Completed.compareTo(conversion.getStatus())>0;
            }
        }).size();
    }

    @Override
    public void delete(Conversion conversion) {
        Preconditions.checkNotNull(conversion,"Conversion should not be null");
        conversions.remove(conversion.getId());
    }

    @Override
    public Collection<Conversion> getConversionsCompletedEarlierThan(final long date) {
        Preconditions.checkNotNull(date,"Conversion status should not be null");
        return copy(Collections2.filter(conversions.values(), new Predicate<Conversion>() {
            public boolean apply(Conversion conversion) {
                return ConversionStatus.Completed.equals(conversion.getStatus()) && date>conversion.getCompletionTime();
            }
        }));
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    private Conversion copy(Conversion conversion) {
        return new Conversion(conversion);
    }
    
    private Collection<Conversion> copy(Collection<Conversion> input) {
        return Collections2.transform(input, new Function<Conversion,Conversion>() {
            public Conversion apply(Conversion conversion) {
                return copy(conversion);
            }
        });
    }
}
