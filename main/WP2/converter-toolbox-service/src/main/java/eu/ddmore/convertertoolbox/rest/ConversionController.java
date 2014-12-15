/*******************************************************************************
 * Copyright (C) 2002 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.rest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;

import javax.validation.constraints.NotNull;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.core.io.FileSystemResource;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

import eu.ddmore.convertertoolbox.domain.Conversion;
import eu.ddmore.convertertoolbox.domain.ConversionCapability;
import eu.ddmore.convertertoolbox.domain.ConversionStatus;
import eu.ddmore.convertertoolbox.domain.LanguageVersion;
import eu.ddmore.convertertoolbox.rest.exceptions.ConversionInputsNotSpecified;
import eu.ddmore.convertertoolbox.rest.exceptions.UnsupportedConversion;
import eu.ddmore.convertertoolbox.rest.hal.ConversionResource;
import eu.ddmore.convertertoolbox.rest.hal.ConversionResourceAssembler;
import eu.ddmore.convertertoolbox.rest.hal.ConversionResources;
import eu.ddmore.convertertoolbox.rest.hal.LinkRelations;
import eu.ddmore.convertertoolbox.service.ConversionCapabilitiesProvider;
import eu.ddmore.convertertoolbox.service.ConversionService;
import eu.ddmore.convertertoolbox.service.ExceededCapacity;
import eu.ddmore.convertertoolbox.service.impl.ConversionResourcesConvention;
import eu.ddmore.convertertoolbox.service.impl.ServiceWorkingDirectory;

@RestController
@RequestMapping(value="/conversion", produces={ "application/hal+json" })
@ExposesResourceFor(Conversion.class)
@EnableHypermediaSupport(type = { HypermediaType.HAL })
public class ConversionController {
    private static final Logger LOG = Logger.getLogger(ConversionController.class);
    private final ConversionCapabilitiesProvider capabilitiesProvider;
    private final ConversionService conversionService;
    private final ConversionResourceAssembler conversionResourceAssembler;
    
    private final ServiceWorkingDirectory serviceWorkingDirectory;
    
    @Autowired(required=true)
    public ConversionController(ConversionCapabilitiesProvider capabilitiesProvider, 
            ConversionService conversionService, ConversionResourceAssembler conversionResourceAssembler,
            ServiceWorkingDirectory serviceWorkingDirectory) {
        this.capabilitiesProvider = capabilitiesProvider;
        this.conversionService = conversionService;
        this.conversionResourceAssembler = conversionResourceAssembler;
        this.serviceWorkingDirectory = serviceWorkingDirectory;
    }
    
    @RequestMapping(method=RequestMethod.GET)
    @Description("Returns a list of conversions being handled by the service")
    public @ResponseBody HttpEntity<ConversionResources> list() {
        Collection<Conversion> conversions = conversionService.getConversions();
        Collection<ConversionResource> conversionResouces = new ArrayList<ConversionResource>();
        for ( Conversion conversion : conversions ) {
            conversionResouces.add(conversionResourceAssembler.toResource(conversion));
        }
        ConversionResources conversionResources = new ConversionResources(conversionResouces);
        conversionResources.add(linkTo(HomeController.class).withRel(LinkRelations.HOME));
        conversionResources.add(linkTo(methodOn(ConversionController.class).list()).withSelfRel());
        return new ResponseEntity<ConversionResources>(conversionResources,HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.POST)
    @Description("Receives a new Conversion for processing")
    public @ResponseBody HttpEntity<ConversionResource> post(@RequestParam("conversion") @NotNull Conversion conversion, 
                                                             @RequestParam("name") @NotNull String fileName,
                                                            @RequestParam("file") @NotNull MultipartFile file) throws UnsupportedConversion, ConversionInputsNotSpecified {
        LOG.debug(conversion.toString());
        LOG.debug(fileName);
        LOG.debug(file.getSize());
        
        if(!isConversionSupported(conversion.getFrom(), conversion.getTo())) {
            throw new UnsupportedConversion(String.format("Requested conversion from %s to %s is not supported",conversion.getFrom(), conversion.getTo()));
        }
        if(file.isEmpty()) {
            throw new ConversionInputsNotSpecified(String.format("File was not uploaded"));
        }
        
        Conversion persistedConversion = null;
        try {
            persistedConversion = conversionService.add(conversion);
            Preconditions.checkNotNull(persistedConversion, "Conversion could not be accepted");
        } catch (ExceededCapacity e) {
            return new ResponseEntity<ConversionResource>(HttpStatus.TOO_MANY_REQUESTS);
        }
        persistedConversion.setWorkingDirectory(prepareConversionWorkingDirectory(persistedConversion));
        persistedConversion.setInputArchive(persistInputFile(persistedConversion, ConversionResourcesConvention.INPUTS_ARCHIVE_NAME, file));
        
        conversionService.schedule(persistedConversion);
        
        return new ResponseEntity<ConversionResource>(conversionResourceAssembler.toResource(persistedConversion),HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    @Description("Returns a Conversion identified by the given id")
    public @ResponseBody HttpEntity<ConversionResource> find(@PathVariable("id") String id) {
        Optional<Conversion> conversion = conversionService.getConversionForId(id);
        if(!conversion.isPresent()) {
            return new ResponseEntity<ConversionResource>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ConversionResource>(conversionResourceAssembler.toResource(conversion.get()),HttpStatus.OK);
    }
    
    @RequestMapping(value="/{id}/result", method=RequestMethod.GET, produces = { MediaType.APPLICATION_OCTET_STREAM_VALUE })
    @Description("Returns a Conversion result identified by the given id")
    public @ResponseBody HttpEntity<FileSystemResource> getOutputs(@PathVariable("id") String id) {
        Optional<Conversion> conversion = conversionService.getConversionForId(id);
        if(!conversion.isPresent()) {
            return new ResponseEntity<FileSystemResource>(HttpStatus.NOT_FOUND);
        }
        if(!ConversionStatus.Completed.equals(conversion.get().getStatus())) {
            return new ResponseEntity<FileSystemResource>(HttpStatus.CONFLICT);
        }
        if(conversion.get().getOutputArchive()==null) {
            return new ResponseEntity<FileSystemResource>(HttpStatus.NOT_FOUND);
        }
        if(!conversion.get().getOutputArchive().exists()) {
            return new ResponseEntity<FileSystemResource>(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<FileSystemResource>(new FileSystemResource(conversion.get().getOutputArchive()),HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    @Description("Removes conversion and all its resources")
    public @ResponseBody HttpEntity<String> delete(@PathVariable("id") String id) {
        Optional<Conversion> conversion = conversionService.getConversionForId(id);
        if(!conversion.isPresent()) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        if(!ConversionStatus.Completed.equals(conversion.get().getStatus())) {
            return new ResponseEntity<String>(HttpStatus.CONFLICT);
        }
        
        conversionService.delete(conversion.get());
        
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }
    private File prepareConversionWorkingDirectory(Conversion conversion) {
        File workingDir = serviceWorkingDirectory.newDirectory(conversion.getId());
        if(!workingDir.exists()) {
            throw new IllegalStateException(String.format("Could not create working directory for request %s in %s",conversion.getId(),workingDir) );
        }
        return workingDir;
    }
    private File persistInputFile(Conversion conversion, String fileName, MultipartFile file) {
       File outputFile = new File(conversion.getWorkingDirectory(), fileName);
       try {
            byte[] bytes = file.getBytes();
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(outputFile));
            stream.write(bytes);
            stream.close();
            return outputFile;
        } catch (Exception e) {
            throw new RuntimeException(String.format("Could not upload input file for request %s to %s",conversion.getId(),conversion.getWorkingDirectory()),e);
        }
    }

    private boolean isConversionSupported(final LanguageVersion from, final LanguageVersion to) {
        Collection<ConversionCapability> matchingCapabilities =  Collections2.filter(this.capabilitiesProvider.getCapabilities(), new Predicate<ConversionCapability>() {
            public boolean apply(ConversionCapability candidate) {
                if(candidate.getSource().equals(from)) {
                    if(candidate.getTarget().contains(to)) {
                        return true;
                    }
                }
                return false;
            }
        });
        
        if(matchingCapabilities.size()==1) {
            return true;
        }
        if(matchingCapabilities.size()>1) {
            throw new IllegalStateException(String.format("Too many converters matching requested conversion from %s to %s", from, to));
        }
        return false;
    }
}
