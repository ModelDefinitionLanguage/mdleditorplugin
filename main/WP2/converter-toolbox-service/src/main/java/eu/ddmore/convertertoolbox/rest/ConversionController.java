package eu.ddmore.convertertoolbox.rest;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Optional;

import eu.ddmore.convertertoolbox.api.conversion.ConverterManager;
import eu.ddmore.convertertoolbox.domain.Conversion;
import eu.ddmore.convertertoolbox.domain.ConversionResource;
import eu.ddmore.convertertoolbox.domain.ConversionResources;
import eu.ddmore.convertertoolbox.rest.hal.ConversionResourceAssembler;
import eu.ddmore.convertertoolbox.service.ConversionService;

@RestController
@RequestMapping(value="/conversion", produces={ "application/hal+json" })
@ExposesResourceFor(Conversion.class)
@EnableHypermediaSupport(type = { HypermediaType.HAL })
public class ConversionController {
    private final ConverterManager converterManager;
    private final ConversionService conversionService;
    private final ConversionResourceAssembler conversionResourceAssembler;
    @Autowired(required=true)
    public ConversionController(ConverterManager converterManager, ConversionService conversionService, ConversionResourceAssembler conversionResourceAssembler) {
        this.converterManager = converterManager;
        this.conversionService = conversionService;
        this.conversionResourceAssembler = conversionResourceAssembler;
    }
    
    @RequestMapping(method=RequestMethod.GET)
    @Description("Returns a list of conversions being handled by the service")
    public @ResponseBody HttpEntity<ConversionResources> list() {
        Collection<Conversion> conversions = conversionService.getConversions();
        Collection<ConversionResource> conversionResouces = new ArrayList<ConversionResource>();
        for ( Conversion conversion : conversions ) {
            conversionResouces.add(conversionResourceAssembler.toResource(conversion));
        }
        return new ResponseEntity<ConversionResources>(new ConversionResources(conversionResouces),HttpStatus.OK);
    }
    
    
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    @Description("Returns a Conversion Resource identified by the given id")
    public @ResponseBody HttpEntity<ConversionResource> findConversion(@PathVariable("id") String id) {
        Optional<Conversion> conversion = conversionService.getConversionForId(id);
        if(!conversion.isPresent()) {
            return new ResponseEntity<ConversionResource>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ConversionResource>(conversionResourceAssembler.toResource(conversion.get()),HttpStatus.OK);
    }
}
