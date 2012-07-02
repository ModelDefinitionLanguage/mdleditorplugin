/* File			:  ExecutionRequestHelper.java
 * Project		:  MangoInteroperabilityFramework-trunk
 * Created on	:  18 Apr 2012
 */
package com.mango.mif.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.JAXBException;

import org.apache.commons.io.FileUtils;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.mango.mif.core.api.ResourceUtils;
import com.mango.mif.core.api.SharedLocationManager;
import com.mango.mif.core.resource.fileutils.FileUtilsResourcePublisher;
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
public class NewExecutionRequestHelper {
    /**
     * R script parameter key
     */
    private final static String SCRIPT_KEY = "script";
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
     * @throws JAXBException 
     */
    public static ExecutionRequestBuilder createMockExecutionRequestBuilder() throws JAXBException {
        ExecutionRequestBuilder requestBuilder = createRequestBuilder();
        requestBuilder.setCommandParameterDefinitions(createBasicCommandParameterDefinition());
        String requestID = UUID.randomUUID().toString();
        requestBuilder.setRequestId(UUID.fromString(requestID));
        File scriptFile = getScriptFile();
    	Map<String, Parameter> parameters = new HashMap<String, Parameter>();
        parameters.put(SCRIPT_KEY, new Parameter(SCRIPT_KEY, scriptFile.getName(), scriptFile.getName(), ParameterType.File));
        requestBuilder.setInputParameters(parameters);
        return requestBuilder;
    }
    
    /**
     * Creates execution request
     * @param requestID 
     * @return
     * @throws JAXBException 
     */
    public static ExecutionRequestBuilder createBasicRExecutionRequestBuilder(SharedLocationManager sharedLocationManager ) throws JAXBException {
        ExecutionRequestBuilder requestBuilder = createRequestBuilder();
        requestBuilder.setCommandParameterDefinitions(createBasicCommandParameterDefinition());
        String requestID = publishFiles(sharedLocationManager);
        requestBuilder.setRequestId(UUID.fromString(requestID));
        File scriptFile = getScriptFile();
    	Map<String, Parameter> parameters = new HashMap<String, Parameter>();
        parameters.put(SCRIPT_KEY, new Parameter(SCRIPT_KEY, scriptFile.getName(), scriptFile.getName(), ParameterType.File));
        requestBuilder.setInputParameters(parameters);
        return requestBuilder;
    }
    
    /**
     * Creates execution request
     * @param requestID 
     * @return
     * @throws JAXBException 
     */
    public static ExecutionRequestBuilder createRExecutionRequestBuilderForInputDirectory(SharedLocationManager sharedLocationManager, File dataDirectory) throws JAXBException {
        ExecutionRequestBuilder requestBuilder = createRequestBuilder();
        requestBuilder.setCommandParameterDefinitions(createBasicCommandParameterDefinition());
        List<File> files = ResourceUtils.listFiles(dataDirectory, new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				return true;
			}
		});
        String requestID = publishFiles(sharedLocationManager, files, dataDirectory);
        requestBuilder.setRequestId(UUID.fromString(requestID));
        Iterable<File> scriptFiles = Iterables.filter(files, new Predicate<File> () {

			@Override
			public boolean apply(File file) {
				return file.getName().toLowerCase().endsWith(".r");
			}
        	
        });
        File scriptFile = scriptFiles.iterator().next();
    	Map<String, Parameter> parameters = new HashMap<String, Parameter>();
        parameters.put(SCRIPT_KEY, new Parameter(SCRIPT_KEY, scriptFile.getName(), scriptFile.getName(), ParameterType.File));
        requestBuilder.setInputParameters(parameters);
        return requestBuilder;
    }
    /**
     * Creates request builder
     * @return
     */
    private static ExecutionRequestBuilder createRequestBuilder() {
        ExecutionRequestBuilder requestBuilder = new ExecutionRequestBuilder();
        requestBuilder.setCommandName(COMMAND_NAME);
        requestBuilder.setConnectorID(CONNECTOR_ID);
        return requestBuilder;
    }
    
    private static File getScriptFile() {
    	return FileUtils.toFile(NewExecutionRequestHelper.class.getResource("test.R"));
    }

    private static File getDataFile() {
    	return FileUtils.toFile(NewExecutionRequestHelper.class.getResource("data.csv"));
    }
    /**
     * Creates request input parameters
     * @return
     */
    private static String publishFiles(SharedLocationManager sharedLocationManager) {
    	FileUtilsResourcePublisher publisher = new FileUtilsResourcePublisher();
    	publisher.setSharedLocationManager(sharedLocationManager);
    	File fileA = getScriptFile();
    	File fileB = getDataFile();
    	File root = fileA.getParentFile();
    	publisher.addFile(fileA);
    	publisher.addFile(fileB);
    	publisher.setRootDirectory(root);
        return publisher.publish();
    }
    /**
     * Creates request input parameters
     * @return
     */
    private static String publishFiles(SharedLocationManager sharedLocationManager, List<File> files, File rootDir) {
    	FileUtilsResourcePublisher publisher = new FileUtilsResourcePublisher();
    	publisher.setSharedLocationManager(sharedLocationManager);
    	publisher.addFiles(files);
    	publisher.setRootDirectory(rootDir);
        return publisher.publish();
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
