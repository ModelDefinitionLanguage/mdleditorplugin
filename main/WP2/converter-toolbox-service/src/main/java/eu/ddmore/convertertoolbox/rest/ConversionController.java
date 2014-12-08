package eu.ddmore.convertertoolbox.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import eu.ddmore.convertertoolbox.api.conversion.ConverterManager;
import eu.ddmore.convertertoolbox.service.ConversionRepository;
import eu.ddmore.convertertoolbox.service.ConversionService;

@RestController
@RequestMapping("/conversion")
public class ConversionController {
    private final ConverterManager converterManager;
    private final ConversionService conversionService;
    
    @Autowired(required=true)
    public ConversionController(ConverterManager converterManager, ConversionService conversionService) {
        this.converterManager = converterManager;
        this.conversionService = conversionService;
    }
    
    
    @RequestMapping(value="list", method=RequestMethod.GET, produces={MediaType.TEXT_HTML_VALUE})
    @Description("Returns a list of conversions being handled by the service")
    public @ResponseBody String list() {
        return "Not implemented yet";
    }
    
}
