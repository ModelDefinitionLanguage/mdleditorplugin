/* File			:  TestsHelper.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  23 Mar 2012
 */
package com.mango.mif.connector;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;
import java.util.Map.Entry;

import javax.xml.bind.JAXBException;


import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.google.common.base.Preconditions;
import com.google.common.io.Files;
import com.mango.mif.connector.dispatcher.domain.Job;
import com.mango.mif.connector.dispatcher.domain.MIFException;
import com.mango.mif.core.api.MIFResource;
import com.mango.mif.core.api.ResourceComponent;
import com.mango.mif.core.api.ResourceComponentException;
import com.mango.mif.domain.ExecutionRequest;
import com.mango.mif.domain.ExecutionRequestBuilder;
import com.mango.mif.domain.ExecutionResponse;
import com.mango.mif.domain.Parameter;
import com.mango.mif.utils.JAXBUtils;
import com.mango.mif.utils.jaxb.ExecutionJaxbUtils;

/**
 * 
 * Methods used in tests
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class TestsHelper {
    /**
     * Logger
     */
    public final static Logger LOG = Logger.getLogger(TestsHelper.class);
    /**
     * R exec windows 
     */
    private static final String WIN_R_EXEC = "Rscript.exe";
    /**
     * R exec linux
     */
    private static final String LINUX_R_EXEC = "Rscript";
    
    public static final String MINIMUM_EXECUTION_REQUEST_MSG = "";
    /**
     * 
     * @return test script to invoke depending on operating system
     */
    public static File getRExecutable() {
        Preconditions.checkNotNull(System.getProperty("rHome"), "R_HOME property not set");
        File binDir = new File(System.getProperty("rHome"));
        Preconditions.checkArgument(binDir.exists(), "'bin' directory in R_HOME does not exist, please ensure that you are running correct version of R 2.13.1 or above.");
        String os = System.getProperty("os.name");
        LOG.info("System OS is: " + os);
        if(os.contains("Win")) {
            return new File(new File(binDir,"bin"),WIN_R_EXEC);
        } 
        return new File(binDir,LINUX_R_EXEC);
    }
    /**
     * Creates a Job
     * @param jobId
     * @param clientRequestMsg
     * @return
     */
    public static Job createJob(String jobId, UUID requestId) {
        Job job = new Job();
        job.setJobId(jobId);
        try {
            job.setExecutionRequestMsg(new ExecutionRequestBuilder().setRequestId(requestId).setCommandName("test-command").setConnectorID("connector-id").getExecutionRequestMsg());
        } catch (JAXBException e) {
            throw new RuntimeException("Error creating test job object.",e);
        } catch (MIFException e) {
            throw new RuntimeException("Error creating test job object.",e);
        }
        job.setClientRequestStatus("Running");
        return job;
    }

    /**
     * Creates working directory
     * @return
     */
    public static File createTmpDirectory() {
        File dir = Files.createTempDir();
        dir.deleteOnExit();
        return dir;
    }
    
    /**
     * 
     * @return
     */
    public static File createTmpDirectory(String name) {
        Preconditions.checkNotNull(name);
        String tmpDirName = System.getProperty("java.io.tmpdir");
        Preconditions.checkNotNull(tmpDirName);
        File tmpDir = new File(tmpDirName);
        File result = new File(tmpDir,name);
        LOG.info("Created temporary directory:" + result);
        result.mkdirs();
        result.deleteOnExit();
        return result;
    }
    
    /**
     * 
     * @return a root directory of the directory containing R test data sets
     */
    public static File getRTestDataSetsDir() {
        URL testData = TestsHelper.class.getResource("/testData/R");
        File testDataSetsDir = FileUtils.toFile(testData);
        return testDataSetsDir;
    }

    /**
     * 
     * @return a root directory of the directory containing R test data sets
     */
    public static File[] getRTestDataSetsDirectories() {
        URL testData = TestsHelper.class.getResource("/testData/R");
        File testDataSetsDir = FileUtils.toFile(testData);
        return testDataSetsDir.listFiles(new FilenameFilter() {
            
            @Override
            public boolean accept(File dir, String name) {
                return name.startsWith("set");
            }
        });
    }
    
    /**
     * Copies results to a output directory
     * 
     * @throws ResourceComponentException
     * @throws IOException 
     * @throws JAXBException 
     */
    public static void dumpExecutionFiles(File outputDirectory, ResourceComponent resourceComponent, ExecutionRequest executionRequest, ExecutionResponse executionResponse) throws ResourceComponentException, IOException, JAXBException {
            File requestResultsDir = new File(outputDirectory,executionResponse.getRequestId().toString());
            FileUtils.writeStringToFile(new File(requestResultsDir, "executionResponse.xml"), JAXBUtils.marshall(executionResponse, ExecutionJaxbUtils.EXECUTION_RESPONSE_CLASS_ARRAY), "UTF-8");
            FileUtils.writeStringToFile(new File(requestResultsDir, "executionRequest.xml"),  JAXBUtils.marshall(executionRequest, ExecutionJaxbUtils.EXECUTION_REQUEST_CLASS_ARRAY), "UTF-8");
            requestResultsDir.mkdir();
            for(Entry<String,Parameter> parEntry : executionResponse.getParameters().entrySet()) {
                if(parEntry.getValue().getResourceUuid()!=null) {
                    MIFResource resource = resourceComponent.getResource(parEntry.getValue().getResourceUuid());
                    FileUtils.copyURLToFile(resource.getContent(), new File(requestResultsDir,parEntry.getValue().getValue()));
                }
            }
    }
}
