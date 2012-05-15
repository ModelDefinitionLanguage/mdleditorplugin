/* File			:  ExecutionRequestHelper.java
 * Project		:  MangoInteroperabilityFramework-trunk
 * Created on	:  18 Apr 2012
 */
package com.mango.mif.utils;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.JAXBException;

import com.mango.mif.core.api.MIFResource;
import com.mango.mif.core.api.ResourceComponent;
import com.mango.mif.core.api.ResourceComponentException;
import com.mango.mif.domain.ExecutionRequest;
import com.mango.mif.domain.ExecutionRequestBuilder;
import com.mango.mif.domain.Parameter;
import com.mango.mif.domain.ParameterDefinition;
import com.mango.mif.domain.ParameterDirection;
import com.mango.mif.domain.ParameterType;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class ExecutionRequestHelper {
    /**
     * Dataset parameter key
     */
    private final static String DATASET_KEY = "dataset";
    /**
     * R script parameter key
     */
    private final static String SCRIPT_KEY = "script";
    /**
     * result key
     */
    private final static String RESULT_KEY = "dataset-result";
    /**
     * R Command name
     */
    private final static String COMMAND_NAME = "executeRScript";
    /**
     * R connector ID
     */
    private final static String CONNECTOR_ID = "R";
    
    /**
     * Creates execution request
     * @param requestID 
     * @return
     * @throws ResourceComponentException 
     * @throws JAXBException 
     */
    public static ExecutionRequest createExecutionRequest(String requestID, ResourceComponent resourceComponent ) throws ResourceComponentException, JAXBException {
        ExecutionRequestBuilder requestBuilder = createRequestBuilder(requestID);
        requestBuilder.setCommandParameterDefinitions(createCommandParameterDefinition());
        requestBuilder.setInputParameters(createParameters(requestID,resourceComponent));
        return requestBuilder.getExecutionRequest();
    }
    /**
     * Creates execution request
     * @param requestID 
     * @return
     * @throws ResourceComponentException 
     * @throws JAXBException 
     */
    public static String createExecutionRequestMsg(String requestID, ResourceComponent resourceComponent ) throws ResourceComponentException, JAXBException {
        ExecutionRequestBuilder requestBuilder = createRequestBuilder(requestID);
        requestBuilder.setCommandParameterDefinitions(createCommandParameterDefinition());
        requestBuilder.setInputParameters(createParameters(requestID,resourceComponent));
        return requestBuilder.getExecutionRequestMsg();
    }
    
    /**
     * Creates a basic Execution request from a test data set directory specified in the parameter.
     * @param requestID
     * @param testDataDir
     * @param resourceComponent
     * @return
     * @throws ResourceComponentException
     * @throws JAXBException
     * @throws MalformedURLException
     */
    public static String createExecutionRequestMsg(String requestID, File testDataDir, ResourceComponent resourceComponent ) throws ResourceComponentException, JAXBException, MalformedURLException {
        ExecutionRequestBuilder requestBuilder = createRequestBuilder(requestID);
        requestBuilder.setCommandParameterDefinitions(createBasicCommandParameterDefinition());
        requestBuilder.setInputParameters(createParameters(requestID,testDataDir, resourceComponent));
        return requestBuilder.getExecutionRequestMsg();        
    }

    /**
     * Creates a basic Execution request from a test data set directory specified in the parameter.
     * @param requestID
     * @param testDataDir
     * @param resourceComponent
     * @return
     * @throws ResourceComponentException
     * @throws JAXBException
     * @throws MalformedURLException
     */
    public static ExecutionRequest createExecutionRequest(String requestID, File testDataDir, ResourceComponent resourceComponent ) throws ResourceComponentException, JAXBException, MalformedURLException {
        ExecutionRequestBuilder requestBuilder = createRequestBuilder(requestID);
        requestBuilder.setCommandParameterDefinitions(createBasicCommandParameterDefinition());
        requestBuilder.setInputParameters(createParameters(requestID,testDataDir, resourceComponent));
        return requestBuilder.getExecutionRequest();        
    }
    /**
     * Creates request builder
     * @param requestID
     * @param resourceComponent
     * @return
     * @throws ResourceComponentException
     */
    private static ExecutionRequestBuilder createRequestBuilder(String requestID) throws ResourceComponentException {
        ExecutionRequestBuilder requestBuilder = new ExecutionRequestBuilder();
        requestBuilder.setCommandName(COMMAND_NAME);
        requestBuilder.setRequestId(UUID.fromString(requestID));
        requestBuilder.setConnectorID(CONNECTOR_ID);
        return requestBuilder;
    }
    /**
     * Creates request input parameters
     * @param resourceComponent 
     * @return
     * @throws ResourceComponentException
     */
    private static Map<String, Parameter> createParameters(String requestID, ResourceComponent resourceComponent) throws ResourceComponentException {
        MIFResource rScript = resourceComponent.addRequestResource(ExecutionRequestHelper.class.getResource("test.R"), requestID, "test.R");
        MIFResource dataset = resourceComponent.addRequestResource(ExecutionRequestHelper.class.getResource("data.csv"), requestID, "data.csv");

        Map<String, Parameter> parameters = new HashMap<String, Parameter>();
        parameters.put(DATASET_KEY, new Parameter(DATASET_KEY, "data.csv", dataset.getUuid().toString(), ParameterType.File));
        parameters.put(SCRIPT_KEY, new Parameter(SCRIPT_KEY, "test.R", rScript.getUuid().toString(), ParameterType.File));
        return parameters;
    }

    /**
     * Creates request input parameters
     * @param resourceComponent 
     * @return
     * @throws ResourceComponentException
     * @throws MalformedURLException 
     */
    private static Map<String, Parameter> createParameters(String requestID, File testDataDir, ResourceComponent resourceComponent) throws ResourceComponentException, MalformedURLException {
        Map<String, Parameter> parameters = new HashMap<String, Parameter>();
        boolean rScript = false;
        for(File file : testDataDir.listFiles()) {
            MIFResource resource = resourceComponent.addRequestResource(file.toURI().toURL(), requestID, file.getName());
            String pName = file.getName();
            if(file.getName().toLowerCase().endsWith(".r")) {
                if(rScript) {
                    throw new IllegalArgumentException("Test data folder " +testDataDir.getAbsolutePath()+ " contains more than one R script. ");
                }
                pName = SCRIPT_KEY;
                rScript = true;
            }
            parameters.put(pName, new Parameter(pName, file.getName(), resource.getUuid().toString(), ParameterType.File));
        }
        return parameters;
    }

    /**
     * Creates a command definition that describes a task of executing a script against a dataset and returning a dataset.
     * @return
     */
    private static List<ParameterDefinition> createCommandParameterDefinition() {
        List<ParameterDefinition> parameterDefinitions = new ArrayList<ParameterDefinition>();
        parameterDefinitions.add(new ParameterDefinition(ParameterType.File, ParameterDirection.IN, DATASET_KEY));
        parameterDefinitions.add(new ParameterDefinition(ParameterType.File, ParameterDirection.IN, SCRIPT_KEY));
        parameterDefinitions.add(new ParameterDefinition(ParameterType.File, ParameterDirection.OUT, RESULT_KEY));
        return parameterDefinitions;
    }

    /**
     * Creates a command definition that describes a task of executing an R script. It specifies only input script.
     * @return
     */
    private static List<ParameterDefinition> createBasicCommandParameterDefinition() {
        List<ParameterDefinition> parameterDefinitions = new ArrayList<ParameterDefinition>();
        parameterDefinitions.add(new ParameterDefinition(ParameterType.File, ParameterDirection.IN, SCRIPT_KEY));
        return parameterDefinitions;
    }

}
