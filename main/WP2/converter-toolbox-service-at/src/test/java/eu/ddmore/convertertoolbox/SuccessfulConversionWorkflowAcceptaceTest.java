/*******************************************************************************
 * Copyright (C) 2002 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.springframework.hateoas.Link;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

import eu.ddmore.convertertoolbox.ConverterToolboxServiceHttpRestClient.ResourceNotFound;
import eu.ddmore.convertertoolbox.api.response.ConversionReport.ConversionCode;
import eu.ddmore.convertertoolbox.domain.Conversion;
import eu.ddmore.convertertoolbox.domain.ConversionCapability;
import eu.ddmore.convertertoolbox.domain.ConversionStatus;
import eu.ddmore.convertertoolbox.domain.hal.ConversionResource;
import eu.ddmore.convertertoolbox.domain.hal.ServiceDescriptorResource;

/**
 * An Acceptance Test presenting an integration workflow performed by clients when they request conversion
 * which fails.
 * 
 * This test uses test-mdl2nonmem-success converter and its API. 
 * The converter is driven by conversion input file naming convention. If the file name contains:
 * * w - the conversion report will contain warning message
 * * i - the conversion report will contain information message
 * * d - the conversion report will contain debug message
 * 
 * Refer to the converter's documentation for more details on its API.
 */
public class SuccessfulConversionWorkflowAcceptaceTest extends ConversionToolboxServiceAcceptanceTestParent {
    private static final Logger LOG = Logger.getLogger(SuccessfulConversionWorkflowAcceptaceTest.class);
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();
    
    @Test
    public void shouldPerformRequestSuccessfulConversionWorkflow() throws IOException, InterruptedException {
        LOG.info(String.format("Retrieving Conversion Toolbox service descriptor"));
        ServiceDescriptorResource serviceDescriptorResource = restClient.getServiceDescriptor();
        
        LOG.info(String.format("URL to submit conversions is %s",serviceDescriptorResource.getLink("ddmore:submit")));
        assertNotNull("URL to where submit Conversion was not specified", serviceDescriptorResource.getLink("ddmore:submit"));
        assertNotNull("URL to service home was not specified", serviceDescriptorResource.getLink("self"));
        

        LOG.info(String.format("Selecting conversion from available capabilities"));
        Collection<ConversionCapability> conversionCapabilities = serviceDescriptorResource.getContent().getCapabilities();
        if(conversionCapabilities.isEmpty()) {
            fail("Converter Toolbox Service provided empty list of conversion capabilities");
        }
        Collection<ConversionCapability> filteredCapabilities = Collections2.filter(conversionCapabilities, new Predicate<ConversionCapability>() {
            public boolean apply(ConversionCapability capability) {
                LOG.debug(capability.getSource());
                return capability.getSource().getVersion().getQualifier()!=null && capability.getSource().getVersion().getQualifier().equals("succeeding");
            }
        });
        if(filteredCapabilities==null || filteredCapabilities.isEmpty()) {
            fail("Required capability was not found in the Converter Toolbox Service");
        } else if(filteredCapabilities.size()>1) {
            fail("There are more than one matching capability - the test is not deterministic.");
        }
        
        ConversionCapability alwaysFailingCapability = filteredCapabilities.iterator().next();
        File archive = FileUtils.toFile(FailedConversionWorkflowAcceptanceTest.class.getResource("/success-wid.zip"));
        
        Conversion conversion = new Conversion().
                setFrom(alwaysFailingCapability.getSource()).
                setTo(alwaysFailingCapability.getTarget().iterator().next()).
                setInputFileName("success-wid.txt"); // resides in success-wid.zip
        
        ConversionResource submittedConversion = restClient.submitConversion(serviceDescriptorResource.getLink("ddmore:submit").getHref(), conversion, archive);

        LOG.info(String.format("Conversion [%s] has been scheduled for processing", submittedConversion.getContent().getId()));
        for(Link link : submittedConversion.getLinks()) {
            LOG.info(String.format("Available transition: %s", link));
        }
        
        Link conversionLocation = submittedConversion.getLink("self"); 
        LOG.info(String.format("Conversion [%s] state is exposed at %s ", submittedConversion.getContent().getId(),conversionLocation.getHref()));
        
        while(ConversionStatus.Completed.compareTo(submittedConversion.getContent().getStatus())>0) {
            submittedConversion = restClient.getConversionResource(conversionLocation.getHref());
            if(submittedConversion==null) {
                fail(String.format("Service returned no conversion resource from %s", conversionLocation.getHref()));
            }
            Thread.sleep(TimeUnit.MILLISECONDS.toMillis(1000));
        }
        
        LOG.info(String.format("Conversion [%s] has been completed", submittedConversion.getContent().getId()));
        for(Link link : submittedConversion.getLinks()) {
            LOG.info(String.format("Available transition: %s", link));
        }
        Conversion content = submittedConversion.getContent();
        assertNotNull(content.getConversionReport());
        assertEquals(ConversionCode.SUCCESS,content.getConversionReport().getReturnCode());
        assertEquals(3,content.getConversionReport().getDetails().size());
        
        
        LOG.info(String.format("Retrieving resuls from Conversion [%s]", submittedConversion.getContent().getId()));
        File resultFile = new File(tempFolder.getRoot(), "output.zip");
        restClient.downloadResultFile(submittedConversion.getLink("ddmore:result").getHref(),resultFile );
        assertTrue("Archive file with conversion outputs does not exist", resultFile.exists());
        assertEquals("Downloaded and remote result files differ in size", new Long(resultFile.length()),content.getOutputFileSize());

        LOG.info(String.format("Deleting conversion [%s]", submittedConversion.getContent().getId()));
        restClient.deleteResource(submittedConversion.getLink("ddmore:delete").getHref());
        try {
            submittedConversion = restClient.getConversionResource(conversionLocation.getHref());
            fail("Did not result in expected 404 exception after deleting the Conversion resource");
        } catch(ResourceNotFound ex) {
        }
        

        LOG.info(String.format("Successful conversion workflow completed"));
    }

}
