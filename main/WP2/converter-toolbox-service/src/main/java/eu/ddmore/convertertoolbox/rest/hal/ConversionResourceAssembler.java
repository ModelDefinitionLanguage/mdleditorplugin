package eu.ddmore.convertertoolbox.rest.hal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import eu.ddmore.convertertoolbox.domain.Conversion;
import eu.ddmore.convertertoolbox.domain.ConversionResource;
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
    public ConversionResource toResource(Conversion entity) {
        ConversionResource resource = new ConversionResource(entity);
        resource.add(entityLinks.linkToSingleResource(entity));
        return resource;
    }

}
