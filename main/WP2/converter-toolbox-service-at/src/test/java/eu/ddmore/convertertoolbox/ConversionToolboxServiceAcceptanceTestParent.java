/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox;

import org.junit.After;
import org.junit.Before;

/**
 * Parent class for Acceptance Tests
 */
public class ConversionToolboxServiceAcceptanceTestParent {

    private static final String ENDPOINT_TEMPLATE = "http://%s:%s/";
    protected ConverterToolboxServiceHttpRestClient restClient;
    private String host = "localhost";
    private String port = "9020";

    public ConversionToolboxServiceAcceptanceTestParent() {
        super();
    }

    @Before
    public void setUp() throws Exception {
        if(System.getProperty(TestProperties.CTS_HOST)!=null) {
            host = System.getProperty(TestProperties.CTS_HOST);
        }
        
        if(System.getProperty(TestProperties.CTS_PORT)!=null) {
            port = System.getProperty(TestProperties.CTS_PORT);
        }
        
        restClient = new ConverterToolboxServiceHttpRestClient(String.format(ENDPOINT_TEMPLATE, host, port));
        restClient.init();
    }

    @After
    public void tearDown() {
        restClient.close();
    }

}