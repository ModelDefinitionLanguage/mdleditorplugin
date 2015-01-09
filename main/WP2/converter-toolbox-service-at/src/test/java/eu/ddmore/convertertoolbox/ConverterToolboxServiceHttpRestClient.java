/*******************************************************************************
 * Copyright (C) 2002 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.RelProvider;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.core.AnnotationRelProvider;
import org.springframework.hateoas.core.DefaultRelProvider;
import org.springframework.hateoas.core.DelegatingRelProvider;
import org.springframework.hateoas.hal.CurieProvider;
import org.springframework.hateoas.hal.DefaultCurieProvider;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.plugin.core.OrderAwarePluginRegistry;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;

import eu.ddmore.convertertoolbox.domain.Conversion;
import eu.ddmore.convertertoolbox.domain.hal.ConversionResource;
import eu.ddmore.convertertoolbox.domain.hal.ServiceDescriptorResource;

/**
 * HTTP REST client for interacting with Converter Toolbox Service REST services.
 */
public class ConverterToolboxServiceHttpRestClient {
    private static final Logger log = Logger.getLogger(ConverterToolboxServiceHttpRestClient.class);
    private String indexEndpoint;
    private CloseableHttpClient client;
    private ObjectMapper objectMapper;
    private boolean initiated = false;
    
    /**
     * Creates a new instance with given Converter Toolbox Service index endpoint
     * @param indexEndpoint
     */
    public ConverterToolboxServiceHttpRestClient(String indexEndpoint) {
        Preconditions.checkNotNull(indexEndpoint, "Converter Toolbox Service locataion can't be null");
        this.indexEndpoint = indexEndpoint;
         client = HttpClients.createDefault();
    }
    
    /**
     * 
     * @return conversion service descriptor retrieved from the remote servie
     */
    public ServiceDescriptorResource getServiceDescriptor() {
        Preconditions.checkState(initiated, "The client was not initiated. Call init() first");
        return getResource(indexEndpoint, ServiceDescriptorResource.class);
    }
    
    /**
     * Retrieves conversion from the given location
     * @param href - conversion resource location
     * @return conversion resource
     */
    public ConversionResource getConversionResource(String href) {
        Preconditions.checkState(initiated, "The client was not initiated. Call init() first");
        Preconditions.checkNotNull(href, "Resource location can't be null");
        return getResource(href, ConversionResource.class);
    }
    
    /**
     * Retrieves a remote resource and parses it as a given class.
     * @param href - location of the resource 
     * @param resourceType - a class specifying type of the resource
     * @return retrieved resource
     */
    private <T extends ResourceSupport> T getResource(String href, Class<T> resourceType) {
        HttpGet httpget = new HttpGet(href);
        httpget.addHeader("accept", MediaTypes.HAL_JSON.toString());
        
        log.info(String.format("Requesting Service Descriptor from: %s",indexEndpoint));
        String responseBody = null;
        try {
            responseBody = client.execute(httpget, getResponseHandler());
        } catch (ResourceNotFound e) {
            e.setUrl(href);
            throw e;
        } catch (Exception ex) {
            throw new RuntimeException(String.format("Could not retrieve resource %s", ex.getMessage()), ex);
        }
        log.debug(String.format("Received %s",responseBody));
        
        try {
            return objectMapper.readValue(responseBody, resourceType);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Could not parse json %s",responseBody), e);
        }
    }
    


    /**
     * Submits conversion to Converter Toolbox Service
     * 
     * @param href - url where the request should be submitted
     * @param conversion - an entity holding conversion 
     * @param archive - archive containing conversion input files
     * @return created conversion resource returned by the service
     */
    public ConversionResource submitConversion(String href, Conversion conversion, File archive) {
        Preconditions.checkNotNull(href, "Submission url can't be null");
        Preconditions.checkNotNull(conversion, "Conversion can't be null");
        Preconditions.checkNotNull(archive, "Conversion inputs archive can't be null");

        String conversionString = "";
        
        try {
            conversionString = objectMapper.writeValueAsString(conversion);
        } catch (JsonProcessingException e1) {
            throw new RuntimeException(String.format("Could not produce JSON for Conversion %s", conversion));
        }
        
        HttpPost post = new HttpPost(href);
        post.addHeader("accept", MediaTypes.HAL_JSON.toString());
        FileBody archivePart = new FileBody(archive);
        StringBody conversionPart = new StringBody(conversionString, ContentType.APPLICATION_JSON);

        HttpEntity reqEntity = MultipartEntityBuilder.create()
                .addPart("file", archivePart)
                .addPart("conversion", conversionPart)
                .build();

        post.setEntity(reqEntity);
        
        log.info(String.format("Sending execution request: %s",conversionString));
        

        String responseBody = null;
        try {
           responseBody = client.execute(post, getResponseHandler());
        } catch(ResourceNotFound e) {
            e.setUrl(href);
            throw e;
        } catch (Exception ex) {
            throw new RuntimeException(String.format("Could not retrieve the service descriptor %s", ex.getMessage()),ex);
        }
        log.debug(String.format("Received %s",responseBody));
        
        try {
            return objectMapper.readValue(responseBody, ConversionResource.class);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Could not parse json %s",responseBody), e);
        }
    }
    /**
     * Deletes remote resource
     * @param href - location of the resource
     */
    public void deleteResource(String href) {
        Preconditions.checkNotNull(href, "Resource location can't be null");
        HttpDelete delete = new HttpDelete(href);
        
        String responseBody = null;
        try {
            responseBody = client.execute(delete, getResponseHandler());
        } catch(ResourceNotFound e) {
            e.setUrl(href);
            throw e;
        } catch (Exception ex) {
            throw new RuntimeException(String.format("Could not delete resource %s", ex.getMessage()),ex);
        }
        log.debug(String.format("Received %s",responseBody));
    }
    /**
     * Downloads the remote resource and persists it in the specified file.
     * @param href - location of the HTTP resource
     * @param resultFile file to which the resource should be persisted
     */
    public void downloadResultFile(String href, File resultFile) {
        Preconditions.checkNotNull(href, "Resource location can't be null");
        Preconditions.checkNotNull(resultFile, "File to download resource can't be null");
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(href);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpget);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Could not download file from %s", href), e);
        }
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();
                fos = new FileOutputStream(resultFile);
                bis = new BufferedInputStream(instream);
                bos = new BufferedOutputStream(fos);
                IOUtils.copy(bis, bos);
            }
        } catch (Exception e) {
            throw new RuntimeException(String.format("Could not download file from %s", href), e);
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                log.warn("Could not free up http response resources");
            }
            if(bos!=null) {
                IOUtils.closeQuietly(bos);
            } else {
                IOUtils.closeQuietly(fos);
            }
            if(bis!=null) {
                IOUtils.closeQuietly(bis);
            }
        }
    }
    /**
     * Initiates HTTP REST client
     */
    public void init() {
        Preconditions.checkState(!initiated, "The client already initiated.");
        RelProvider defaultRelProvider = new DefaultRelProvider();
        RelProvider annotationRelProvider = new AnnotationRelProvider();

        CurieProvider curieProvider = new DefaultCurieProvider("ddmore",
            new UriTemplate("http://ddmore.eu{#rel}"));
        OrderAwarePluginRegistry<RelProvider, Class<?>> relProviderPluginRegistry = OrderAwarePluginRegistry
                .create(Arrays.asList(defaultRelProvider, annotationRelProvider));
        DelegatingRelProvider delegatingRelProvider = new DelegatingRelProvider(relProviderPluginRegistry);
        
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new Jackson2HalModule());
        objectMapper
                .setHandlerInstantiator(new Jackson2HalModule.HalHandlerInstantiator(delegatingRelProvider, curieProvider));
        
        initiated = true;
    }
    
    /**
     * Closes the underlying HTTP session
     */
    public void close() {
        try {
            client.close();
        } catch (IOException e) {
            throw new RuntimeException("Could not close the client",e);
        }
    }

    private ResponseHandler<String> getResponseHandler() {
        return new ResponseHandler<String>() {
            public String handleResponse(
                    final HttpResponse response) throws ClientProtocolException, IOException {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else if(status==404) {
                    throw new ResourceNotFound();
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            }
        };
    }

    /**
     * exception representing 404 HTTP error
     */
    public class ResourceNotFound extends RuntimeException {
        private static final long serialVersionUID = 4441296440824952063L;
        private String url;
        /**
         * 
         * @return url of the remote resource
         */
        public String getUrl() {
            return url;
        }
        
        private void setUrl(String url) {
            this.url = url;
        }
    }

}
