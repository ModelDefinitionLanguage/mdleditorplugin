package com.mango.mif.rest.service;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.xml.bind.JAXBException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.google.common.base.Preconditions;
import com.mango.mif.client.api.rest.JobSubmissionService;
import com.mango.mif.connector.Connector;
import com.mango.mif.core.api.SharedLocationManager;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.Invoker;
import com.mango.mif.core.exec.invoker.InvokerFactory;
import com.mango.mif.core.exec.invoker.InvokerHelper;
import com.mango.mif.core.impl.ConnectorsRegistry;
import com.mango.mif.core.impl.MessagingHelper;
import com.mango.mif.domain.ExecutionRequest;
import com.mango.mif.exception.MIFException;
import com.mango.mif.utils.MIFSchemaGen;
 
/**
 * {@inheritDoc}.
 */
public class JobSubmissionServiceImpl implements JobSubmissionService {

    private static final Logger LOG = Logger.getLogger(JobSubmissionServiceImpl.class);

    private ConnectorsRegistry connectorsRegistry;
         
    private SharedLocationManager sharedLocationManager;

    private InvokerFactory invokerFactory;
    
    private final static int REQUEST_DIR_PERMISSIONS = 0755;
    /**
     * 
     * Prepare the job plus directories for execution.
     */
    @Override
    public String prepare(String userName, String encryptedPassword) throws MIFException {
        Preconditions.checkNotNull(getSharedLocationManager());
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(encryptedPassword)) {
             throw new MIFException("User name and password can't be empty");
        }                      
        UUID uuid = UUID.randomUUID();
        LOG.info("Generated UUID: "+ uuid.toString());
        
        try {
            createRequestDirectory(uuid.toString(), invokerFactory.createRunAsUserInvoker(userName, encryptedPassword));
        } catch (ExecutionException e) {
            LOG.error(e);
            throw new MIFException("Could not create request directory in the shared location " + e.getMessage());
        }
        
        return uuid.toString();
    }
    
    public String createRequestDirectory(String uuid, Invoker invoker) throws ExecutionException {
        
        LOG.debug("Random UUID: " + uuid);
        File requestDir = new File(getSharedLocationManager().getSharedLocationRoot(), uuid);

        Preconditions.checkNotNull(requestDir);
        
        InvokerHelper invokerHelper = new InvokerHelper(invoker);
        boolean success = invokerHelper.mkdir(requestDir.getAbsolutePath(), REQUEST_DIR_PERMISSIONS);
        
        if(!success) {
            throw new ExecutionException("Creation of request directory failed. ");
        }
        
        LOG.info("Created request directory: " + requestDir);
        
        File inputDir = getSharedLocationManager().getRequestInputDirectory(requestDir.getName());
        invokerHelper.mkdir(inputDir.getAbsolutePath(), REQUEST_DIR_PERMISSIONS);
        LOG.info("Created request input directory: " + inputDir);

        File outputDir = getSharedLocationManager().getRequestOutputDirectory(requestDir.getName());
        invokerHelper.mkdir(outputDir.getAbsolutePath(), REQUEST_DIR_PERMISSIONS);
        LOG.info("Created request output directory: " + outputDir);      
        
        return uuid;
    }
    
    /**
     * Execute the job.
     */
    @Override
    public String execute(String executionRequest) throws MIFException {
        
        LOG.info("Handling REST Execution Request "+ executionRequest);
        if (StringUtils.isEmpty(executionRequest)) {
            throw new MIFException("Could not parse the 'executionRequest' parameter from the body");
        }                      
                        
        ExecutionRequest request = MessagingHelper.parseExecutionRequest(executionRequest);
        executeJobRequest(executionRequest, request);
        
        return request.getRequestId();
    }

    private void executeJobRequest(String executionRequest, ExecutionRequest request) throws MIFException {
        Connector connector = connectorsRegistry.getConnector(request.getType());
        if (connector != null) {
            connector.executeJobRequest(executionRequest, request.getRequestId());
        } else {
            throw new MIFException("A Connector for " + request.getType() + " was not found.");
        }
    }
    
    public ConnectorsRegistry getConnectorsRegistry() {
        return connectorsRegistry;
    }
    
    public void setConnectorsRegistry(ConnectorsRegistry connectorsRegistry) {
        this.connectorsRegistry = connectorsRegistry;
    }

    @Override
    public String indextext() {
        return schema();
    }  
    
    @Override
    public String indexweb() {
        
        StringBuilder builder = new StringBuilder();
        builder.append("Execution Request/Response XSD:<br><textarea rows='40' cols='100' style='border:none'>");
        builder.append(schema());
        builder.append("</textarea>");
        return builder.toString();
    }

    private String schema() {
        try {            
            return MIFSchemaGen.getInstance().generateSchema();           
        } catch (JAXBException e) {
            LOG.error("Exception thrown generating schema", e);
        } catch (IOException e) {
            LOG.error("Exception thrown ", e);
        }
        return "Could not generate MIF Schema";
    }

    public SharedLocationManager getSharedLocationManager() {
        return sharedLocationManager;
    }

    public void setSharedLocationManager(SharedLocationManager sharedLocationManager) {
        this.sharedLocationManager = sharedLocationManager;
    }
    
    public InvokerFactory getInvokerFactory() {
        return invokerFactory;
    }
    
    public void setInvokerFactory(InvokerFactory invokerFactory) {
        this.invokerFactory = invokerFactory;
    }

}