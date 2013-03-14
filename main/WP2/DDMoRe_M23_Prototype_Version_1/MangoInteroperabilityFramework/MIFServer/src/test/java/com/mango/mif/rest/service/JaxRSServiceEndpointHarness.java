/* File			:  JaxRSServiceEndpointHarness.java
 * Project		:  MIFServer
 * Created on	:  10 Jul 2012
 */
package com.mango.mif.rest.service;


import javax.ws.rs.Path;
import javax.ws.rs.ext.ExceptionMapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.binding.BindingFactoryManager;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSBindingFactory;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.client.Client;
import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;
import org.apache.cxf.jaxrs.client.ResponseExceptionMapper;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.apache.mina.util.AvailablePortFinder;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class JaxRSServiceEndpointHarness {

	private static Log logger = LogFactory.getLog(JaxRSServiceEndpointHarness.class);
	
    private static String ENDPOINT_HOST = "localhost";
    private static String ENDPOINT_PATH = "/";

    private final JAXRSServerFactoryBean serviceFactory = new JAXRSServerFactoryBean();
    private final JAXRSClientFactoryBean clientFactory = new JAXRSClientFactoryBean();
    private Server endpoint;
	private Client serviceProxy;

	private String port = Integer.toString(AvailablePortFinder.getNextAvailable());

    @SuppressWarnings("unchecked")
    public <T> T getProxy(Class<T> jaxRsServiceInterface, T serviceImplementation) {
        buildService(jaxRsServiceInterface, serviceImplementation);
        createService();        
        buildClient(jaxRsServiceInterface);
        return (T)createClient();
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> T getProxy(Class<T> jaxRsServiceInterface, T serviceImplementation, ExceptionMapper exceptionMapper, ResponseExceptionMapper responseExceptionMapper) {
        buildService(jaxRsServiceInterface, serviceImplementation);
        serviceFactory.setProvider(exceptionMapper);        
        createService();        
        buildClient(jaxRsServiceInterface);
        clientFactory.setProvider(responseExceptionMapper);
        return (T)createClient();
    }

	@SuppressWarnings("unchecked")
	private <T> T createClient() {
		serviceProxy = clientFactory.create();

		// add interceptors such as logging
//            Client client = ClientProxy.getClient(serviceProxy);
//            client.getInInterceptors().add(XY);
//            sf.getInInterceptors().add(new LoggingInInterceptor());
//            sf.getOutInterceptors().add(new LoggingOutInterceptor()); 

		return (T) serviceProxy;
	}

	private <T> void buildClient(Class<T> jaxRsServiceInterface) {
		clientFactory.setServiceClass(jaxRsServiceInterface);
		clientFactory.setAddress(getEndpointUrl());
	}

	private void createService() {
		BindingFactoryManager manager = serviceFactory.getBus().getExtension(BindingFactoryManager.class);
		JAXRSBindingFactory factory = new JAXRSBindingFactory();
		factory.setBus(serviceFactory.getBus());
		manager.registerBindingFactory(JAXRSBindingFactory.JAXRS_BINDING_ID, factory);            
		endpoint = serviceFactory.create();
	}

	private <T> void buildService(Class<T> jaxRsServiceInterface, T serviceImplementation) {
        if (jaxRsServiceInterface.isInterface() &&
                jaxRsServiceInterface.getAnnotation(Path.class) != null &&
                jaxRsServiceInterface.isInstance(serviceImplementation)) {
		if (logger.isInfoEnabled()) {
			logger.info("Publishing service at: " + getEndpointUrl());
		}
		
		serviceFactory.setResourceClasses(serviceImplementation.getClass());            
		serviceFactory.setResourceProvider(serviceImplementation.getClass(), new SingletonResourceProvider(serviceImplementation));
		serviceFactory.setBindingId(JAXRSBindingFactory.JAXRS_BINDING_ID);
		serviceFactory.setAddress(getEndpointUrl());
        } else {
            throw new IllegalArgumentException("You need to pass a JaxRS annotated interface and an implementation thereof");

        }
	}

    /**
     * Stop endpoint and free port
     */
    public void tearDown() {
        if (endpoint != null) {
            endpoint.stop();
            endpoint.destroy();
        }
    }

    private String getEndpointUrl() {
        return "http://" + ENDPOINT_HOST + ":" + port + ENDPOINT_PATH;
    }
}
