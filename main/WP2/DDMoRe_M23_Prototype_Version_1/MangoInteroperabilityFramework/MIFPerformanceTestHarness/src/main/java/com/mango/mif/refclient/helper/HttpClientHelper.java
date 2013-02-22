package com.mango.mif.refclient.helper;

import java.io.IOException;
import java.net.ConnectException;
import java.util.UUID;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

import com.google.common.base.Joiner;
import com.mango.mif.domain.ExecutionRequest;
import com.mango.mif.utils.JAXBUtils;
import com.mango.mif.utils.jaxb.ExecutionJaxbUtils;

/**
 * HttpClientHelper for interacting with MIFServer REST services.
 */
public class HttpClientHelper {

    private static final Logger log = Logger.getLogger(HttpClientHelper.class);
    private String endPoint;
    private HttpClient client;

    public HttpClientHelper(String endPoint) {
        this.endPoint = endPoint;
        client = new HttpClient();
    }   
    
    public UUID prepareJob(String userName, String encryptedPassword) {
        
        String endpoint = buildEndpoint("jobsubmission", "prepare");
        PostMethod post = new PostMethod(endpoint);
        post.addRequestHeader("accept", MediaType.MEDIA_TYPE_WILDCARD);        
        post.setParameter("userName", userName);
        post.setParameter("encryptedPassword", encryptedPassword);
        
        int status = 0;
        try {
            status = client.executeMethod(post);            
            if (status == 200) {
                return UUID.fromString(post.getResponseBodyAsString());
            }
            
        } catch (ConnectException e) {
        	throw new IllegalStateException("Could not connect to server at " + endPoint);
        } catch (HttpException e) {
        	throw new IllegalStateException("Could not connect to server at " + endPoint);
        } catch (IOException e) {
        	throw new IllegalStateException("Could not connect to server at " + endPoint);
        }
        return null;
    }

    /**
     * Execute Job
     */
    public String executeJob(ExecutionRequest jobRequest) throws JAXBException {
        
        String endpoint = buildEndpoint("jobsubmission", "execute");
        PostMethod post = new PostMethod(endpoint);
        
        String xml = JAXBUtils.marshall(jobRequest, ExecutionJaxbUtils.CONTEXT_CLASSES);
        post.addRequestHeader("accept", MediaType.MEDIA_TYPE_WILDCARD);        
        post.setParameter("executionRequest", xml);
        
        log.info("Sending execution request: " + jobRequest.getRequestId() 
            + " type " + jobRequest.getType() 
            + " for " + jobRequest.getUserName());
        
        int status = 0;
        try {
            status = client.executeMethod(post);            
            if (status == 200) {
                return post.getResponseBodyAsString();
            }
            
        } catch (HttpException e) {
        	System.err.println("Could not connect to server: " + endpoint);
        	System.exit(-1);
        } catch (IOException e) {
        	System.err.println("Could not connect to server: " + endpoint);
        	System.exit(-1);
        }
        return null;
    }

    /**
     * Check status of a job.
     */
    public String checkStatus(String jobId) {
        
        String jobStatus =  null;
        String endpoint = buildEndpoint("jobService", "jobs", jobId);
        GetMethod get = new GetMethod(endpoint);
        get.addRequestHeader("accept", MediaType.MEDIA_TYPE_WILDCARD);                      
        
        try {
            client.executeMethod(get);
            jobStatus = get.getResponseBodyAsString();                       
            
        } catch (HttpException e) {
            log.error("Could not connect to server ");
            throw new IllegalStateException(e);
        } catch (IOException e) {
        	log.error("Could not connect to server ");
        	throw new IllegalStateException(e);
        } catch (Throwable t) {
        	log.error("Could not connect to server ");
        	throw new IllegalStateException(t);
        }
        return jobStatus;
    }

    private String buildEndpoint(String...parts) {
        
        StringBuilder builder = new StringBuilder();
        builder.append(endPoint);        
        builder.append(Joiner.on("/").join(parts));
        return builder.toString();
    }
}
