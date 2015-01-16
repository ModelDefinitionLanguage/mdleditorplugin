/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.rest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Preconditions;

import eu.ddmore.convertertoolbox.domain.ConversionCapability;
import eu.ddmore.convertertoolbox.domain.ServiceDescriptor;
import eu.ddmore.convertertoolbox.domain.hal.LinkRelation;
import eu.ddmore.convertertoolbox.domain.hal.ServiceDescriptorResource;
import eu.ddmore.convertertoolbox.service.ConversionCapabilitiesProvider;

@RestController
@RequestMapping(value = "/", produces={ "application/hal+json" })
@EnableHypermediaSupport(type = { HypermediaType.HAL })
public class HomeController {
    private final ConversionCapabilitiesProvider capabilitiesProvider;
    
    @Value("${info.app.name}")
    private String name;

    @Value("${info.app.version}")
    private String version;

    @Value("${cts.support.url:}")
    private String supportUrl;
    
    @Autowired(required=true)
    public HomeController(ConversionCapabilitiesProvider capabilitiesProvider) {
        this.capabilitiesProvider = capabilitiesProvider;
    }
    
    @RequestMapping( method=RequestMethod.GET)
    public @ResponseBody HttpEntity<ServiceDescriptorResource> index() {
        Collection<ConversionCapability> capabilities = capabilitiesProvider.getCapabilities();
        Preconditions.checkNotNull(capabilities,"Incorrect setup, capabilities should never be null");
        ServiceDescriptor serviceDescriptor = new ServiceDescriptor(name, version, capabilities);
        ServiceDescriptorResource resource = new ServiceDescriptorResource(serviceDescriptor);
        
        resource.add(linkTo(methodOn(HomeController.class).index()).withSelfRel());
        resource.add(linkTo(ConversionController.class).withRel(LinkRelation.SUBMIT.getRelation()));
        if(StringUtils.isNotBlank(supportUrl)) {
            resource.add(new Link(supportUrl).withRel(LinkRelation.SUPPORT.getRelation()));
        }
        return new ResponseEntity<ServiceDescriptorResource>(resource, HttpStatus.OK);
    }
}
