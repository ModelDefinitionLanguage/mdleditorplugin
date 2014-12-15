/*******************************************************************************
 * Copyright (C) 2002 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.rest.hal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import eu.ddmore.convertertoolbox.domain.Conversion;
import eu.ddmore.convertertoolbox.domain.ConversionStatus;
import eu.ddmore.convertertoolbox.domain.hal.ConversionResource;
import eu.ddmore.convertertoolbox.domain.hal.LinkRelations;
import eu.ddmore.convertertoolbox.rest.ConversionController;

/**
 * Component responsible for facilitating wrapping of the { @link Conversion } into { @link ConversionResource }
 */
@Component
public class ConversionResourceAssembler extends ResourceAssemblerSupport<Conversion, ConversionResource> {
    @Autowired
    private EntityLinks entityLinks;
    
    public ConversionResourceAssembler() {
        super(ConversionController.class, ConversionResource.class);
    }

    @Override
    public ConversionResource toResource(Conversion conversion) {
        ConversionResource resource = new ConversionResource(conversion);
        resource.add(entityLinks.linkToSingleResource(conversion));
        if(ConversionStatus.Completed.equals(conversion.getStatus())) {
            addLinksForCompletedConversion(resource);
        }
        return resource;
    }

    private void addLinksForCompletedConversion(ConversionResource conversionResource) {
        if(conversionResource.getContent().getOutputArchive()!=null) {
            if(conversionResource.getContent().getOutputArchive().exists()) {
                conversionResource.add(linkTo(methodOn(ConversionController.class).getOutputs(conversionResource.getContent().getId())).withRel(LinkRelations.RESULT));
            }
        }
        conversionResource.add(linkTo(methodOn(ConversionController.class).delete(conversionResource.getContent().getId())).withRel(LinkRelations.DELETE));
    }
    
    public void setEntityLinks(EntityLinks entityLinks) {
        this.entityLinks = entityLinks;
    }
}
