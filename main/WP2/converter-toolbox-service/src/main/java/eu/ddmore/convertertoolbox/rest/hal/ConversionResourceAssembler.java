/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.rest.hal;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import eu.ddmore.convertertoolbox.domain.ConversionStatus;
import eu.ddmore.convertertoolbox.domain.hal.ConversionResource;
import eu.ddmore.convertertoolbox.domain.hal.LinkRelation;
import eu.ddmore.convertertoolbox.domain.internal.ObjectMapper;
import eu.ddmore.convertertoolbox.rest.ConversionController;

/**
 * Component responsible for facilitating wrapping of the { @link Conversion } into { @link ConversionResource }
 * 
 * This is in fact a helper class that Controllers should use to create HAL Representations of Resources.
 * 
 * Refer to Spring HATEOAS for details.
 */
@Component
public class ConversionResourceAssembler extends ResourceAssemblerSupport<eu.ddmore.convertertoolbox.domain.internal.Conversion, ConversionResource> {
    @Autowired
    private EntityLinks entityLinks;
    
    public ConversionResourceAssembler() {
        super(ConversionController.class, ConversionResource.class);
    }

    @Override
    public ConversionResource toResource(eu.ddmore.convertertoolbox.domain.internal.Conversion conversion) {
        ConversionResource resource = new ConversionResource(ObjectMapper.map(conversion));
        resource.add(entityLinks.linkToSingleResource(conversion));
        if(ConversionStatus.Completed.equals(conversion.getStatus())) {
            addLinksForCompletedConversion(conversion, resource);
        }
        return resource;
    }

    private void addLinksForCompletedConversion(eu.ddmore.convertertoolbox.domain.internal.Conversion conversion, ConversionResource conversionResource) {
        if(conversion.getOutputArchive()!=null) {
            if(conversion.getOutputArchive().exists()) {
                conversionResource.add(linkTo(methodOn(ConversionController.class).getOutputs(conversionResource.getContent().getId())).withRel(LinkRelation.RESULT.getRelation()));
            }
        }
        conversionResource.add(linkTo(methodOn(ConversionController.class).delete(conversionResource.getContent().getId())).withRel(LinkRelation.DELETE.getRelation()));
    }
    
    public void setEntityLinks(EntityLinks entityLinks) {
        this.entityLinks = entityLinks;
    }
}
