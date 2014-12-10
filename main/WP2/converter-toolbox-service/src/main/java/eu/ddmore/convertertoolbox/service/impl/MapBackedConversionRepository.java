package eu.ddmore.convertertoolbox.service.impl;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

import eu.ddmore.convertertoolbox.domain.Conversion;
import eu.ddmore.convertertoolbox.domain.ConversionStatus;
import eu.ddmore.convertertoolbox.service.ConversionRepository;

/**
 * Basic implementation of conversion repository that uses a runtime map to store conversions
 */
@Repository
public class MapBackedConversionRepository implements ConversionRepository {
    private Map<String,Conversion> conversions = new ConcurrentHashMap<String, Conversion>();
    @Override
    public Optional<Conversion> getConversion(String id) {
        Preconditions.checkArgument(StringUtils.isNotBlank(id), "Conversion id can't be blank");
        Conversion conversion = conversions.get(id);
        if(conversion==null) {
            return Optional.absent();
        } else {
            return Optional.of(conversion);
        }
    }

    @Override
    public Collection<Conversion> getConversions() {
        return conversions.values();
    }

    private Conversion copy(Conversion conversion) {
        return new Conversion(conversion);
    }
    
    @Override
    public Conversion save(Conversion conversion) {
        Conversion persisted = copy(conversion);
        if(conversion.getId()==null) {
            persisted.setId(generateId());
        }
        conversions.put(persisted.getId(),persisted);
        return (Conversion)ObjectUtils.clone(persisted);
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public Collection<Conversion> getConversionsWithStatus(final ConversionStatus status) {
        Preconditions.checkNotNull(status,"Conversion status should not be null");
        return Collections2.filter(conversions.values(), new Predicate<Conversion>() {
            public boolean apply(Conversion conversion) {
                return status.equals(conversion.getStatus());
            }
        });
    }

    @Override
    public int countUncompletedConversions() {
        return Collections2.filter(conversions.values(), new Predicate<Conversion>() {
            public boolean apply(Conversion conversion) {
                return ConversionStatus.Completed.compareTo(conversion.getStatus())>0;
            }
        }).size();
    }

    @Override
    public void delete(Conversion conversion) {
        conversions.remove(conversion.getId());
    }
    
}
