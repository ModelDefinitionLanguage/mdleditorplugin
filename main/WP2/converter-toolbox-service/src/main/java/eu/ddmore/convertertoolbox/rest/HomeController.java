package eu.ddmore.convertertoolbox.rest;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import eu.ddmore.convertertoolbox.api.conversion.ConverterManager;
import eu.ddmore.convertertoolbox.domain.ConversionCapability;
import eu.ddmore.convertertoolbox.domain.LanguageVersion;
import eu.ddmore.convertertoolbox.domain.LanguageVersion;
import eu.ddmore.convertertoolbox.domain.ServiceDescriptor;

@RestController
@RequestMapping("/")
public class HomeController {

    private final ConverterManager converterManager;
    
    @Value("${info.app.name}")
    private String name;

    @Value("${info.app.version}")
    private String version;
    
    
    @Autowired(required=true)
    public HomeController(ConverterManager converterManager) {
        this.converterManager = converterManager;
    }
    
    @RequestMapping(method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody ServiceDescriptor index() {
        ServiceDescriptor serviceDescriptor = new ServiceDescriptor(name, version, getCapabilities());
        
        return serviceDescriptor;
    }

    private Collection<ConversionCapability> getCapabilities() {
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
