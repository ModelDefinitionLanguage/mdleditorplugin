package eu.ddmore.convertertoolbox.rest;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import eu.ddmore.convertertoolbox.api.conversion.ConverterManager;
import eu.ddmore.convertertoolbox.api.domain.LanguageVersion;

@RestController
@RequestMapping("/")
public class HomeController {

    private final ConverterManager converterManager;
    
    @Autowired(required=true)
    public HomeController(ConverterManager converterManager) {
        this.converterManager = converterManager;
    }
    
    @RequestMapping(method=RequestMethod.GET, produces={MediaType.TEXT_HTML_VALUE})
    public @ResponseBody String index() {
        StringBuilder resultBuilder = new StringBuilder();
        for(Map.Entry<LanguageVersion, Collection<LanguageVersion>> en : converterManager.getCapabilities().entrySet()) {
            resultBuilder.append(en.getKey().toString()).append(en.getValue());
        }
        return resultBuilder.toString();
    }
    
}
