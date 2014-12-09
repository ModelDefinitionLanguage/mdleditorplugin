package eu.ddmore.convertertoolbox.service.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

import org.springframework.stereotype.Service;

import eu.ddmore.convertertoolbox.api.conversion.ConverterManager;
import eu.ddmore.convertertoolbox.domain.ConversionCapability;
import eu.ddmore.convertertoolbox.domain.LanguageVersion;
import eu.ddmore.convertertoolbox.service.ConversionCapabilitiesProvider;


/**
 * Default implementation of the { @link ConversionCapabilitiesProvider } which queries {@link ConverterManager} for 
 * capabilities and converts the returned Map to Domain representation
 */
@Service
public class ConversionCapabilitiesProviderImpl implements ConversionCapabilitiesProvider {

    private final ConverterManager converterManager;
    
    public ConversionCapabilitiesProviderImpl(ConverterManager converterManager) {
        this.converterManager = converterManager;
    }
    
    @Override
    public Collection<ConversionCapability> getCapabilities() {
        Map<eu.ddmore.convertertoolbox.api.domain.LanguageVersion, Collection<eu.ddmore.convertertoolbox.api.domain.LanguageVersion>> capabilities = converterManager.getCapabilities();
        Collection<ConversionCapability> result = new HashSet<ConversionCapability>();
        
        for(Map.Entry<eu.ddmore.convertertoolbox.api.domain.LanguageVersion, Collection<eu.ddmore.convertertoolbox.api.domain.LanguageVersion>> en : capabilities.entrySet()) {
            Collection<LanguageVersion> tos = new HashSet<LanguageVersion>();
            
            for(eu.ddmore.convertertoolbox.api.domain.LanguageVersion language : en.getValue()) {
                tos.add(LanguageVersion.fromOldAPI(language));
            }
            
            result.add(new ConversionCapability(LanguageVersion.fromOldAPI(en.getKey()), tos));
        }
        return result;
    }
}
