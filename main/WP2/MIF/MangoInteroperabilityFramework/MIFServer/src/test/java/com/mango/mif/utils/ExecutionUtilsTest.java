/* File			:  ExecutionUtilsTest.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Apr 5, 2012
 */
package com.mango.mif.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;

import com.mango.mif.domain.CommandDefinition;
import com.mango.mif.domain.ExecutionRequest;
import com.mango.mif.domain.Parameter;
import com.mango.mif.domain.ParameterDefinition;
import com.mango.mif.domain.ParameterDirection;
import com.mango.mif.domain.ParameterType;
import com.mango.mif.utils.jaxb.ExecutionJaxbUtils;

/**
 * HelperClass To construct the execution Request.
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class ExecutionUtilsTest {
	static final Logger LOG = Logger.getLogger(ExecutionUtilsTest.class);
	
	/** R Script Tag*/
	private static final String EXECUTE_R_SCRIPT = "executeRScript";
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
    
    /** R Software*/
	private static final String R_SOFTWARE = "R";
	
	/**
	 * Creates the Request Object. 
	 * Marshalls the request to a string.
	 * @param uuid : UUID of the new Request
	 * @return
	 */
	public static String buildMessage(UUID uuid)  {
		return buildRequestMessage(uuid, null);
	}


	/**
	 * Builds the execution Request as an Object.
	 * If the software is not passed it uses R as the default software.
	 *
	 * @param uuid the uuid
	 * @param softwareType the software type
	 * @return the execution req
	 */
	public static ExecutionRequest getExecutionReq(UUID uuid,
			String softwareType) {
		List<ParameterDefinition> parameterDefinitions = prepareParameterDefnitions();
		
		CommandDefinition commandDefinition = new CommandDefinition(EXECUTE_R_SCRIPT, parameterDefinitions);
		
		Map<String, Parameter> parameters = prepareParametersForRequest();
		
		ExecutionRequest request = prepareExecutionRequest(commandDefinition,
				parameters);
		request.setType((softwareType !=null)?softwareType: R_SOFTWARE);
		request.setRequestId(uuid);
		return request;
	}
	
	/**
	 * Builds the Execution request message.
	 *
	 * @param uuid the uuid
	 * @param softwareType the software type
	 * @return the string
	 */
	public static String buildMessage(UUID uuid, String softwareType)  {
		return buildRequestMessage(uuid, softwareType);
	}
	
	
	/**
	 * Prepare execution request.
	 *
	 * @param commandDefinition the command definition
	 * @param parameters the parameters
	 * @return the execution request
	 */
	public static ExecutionRequest prepareExecutionRequest(
			CommandDefinition commandDefinition,
			Map<String, Parameter> parameters) {
		ExecutionRequest startRequest = new ExecutionRequest();
		startRequest.setCommandDefinition(commandDefinition);
		startRequest.setParameters(parameters);
		return startRequest;
	}

	/**
	 * Prepare parameters for request.
	 *
	 * @return the map
	 */
	private static Map<String, Parameter> prepareParametersForRequest() {
		Map<String, Parameter> parameters = new HashMap<String, Parameter>();
		parameters.put("SAMPLE1", new Parameter("SAMPLE_PARAM1", "data.csv", "SAMPLE_PARAM1_VALUE", ParameterType.File));
		parameters.put("SAMPLE2", new Parameter("SAMPLE_PARAM2", "data.csv", "SAMPLE_PARAM2_VALUE", ParameterType.File));
		return parameters;
	}

	/**
	 * Prepare parameter defnitions.
	 *
	 * @return the list
	 */
	private static List<ParameterDefinition> prepareParameterDefnitions() {
		List<ParameterDefinition> parameterDefinitions = new ArrayList<ParameterDefinition>();
		ParameterDefinition e = new ParameterDefinition(ParameterType.File, ParameterDirection.IN, DATASET_KEY);
		parameterDefinitions.add(e);
		parameterDefinitions.add(new ParameterDefinition(ParameterType.File, ParameterDirection.IN, SCRIPT_KEY));
		parameterDefinitions.add(new ParameterDefinition(ParameterType.File, ParameterDirection.OUT, RESULT_KEY));
		return parameterDefinitions;
	}
	

	private static String buildRequestMessage(UUID uuid, String softwareType) {
		
		ExecutionRequest request = getExecutionReq(uuid, softwareType);

		String marshallRequest = null;
		try {
			 marshallRequest = JAXBUtils.marshall(request, ExecutionJaxbUtils.EXECUTION_REQUEST_CLASS_ARRAY);
		} catch (JAXBException e) {
			LOG.error("JAXB Exception " + e );
		}
		
		LOG.info("Sending Request  " + marshallRequest);
		return marshallRequest;
	}
}
