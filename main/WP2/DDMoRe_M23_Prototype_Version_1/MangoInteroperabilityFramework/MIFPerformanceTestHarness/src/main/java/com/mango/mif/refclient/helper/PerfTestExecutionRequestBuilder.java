package com.mango.mif.refclient.helper;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.UUID;

import org.apache.cxf.common.util.StringUtils;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.mango.mif.client.domain.ExecutionRequestAttributeName;
import com.mango.mif.domain.ExecutionRequest;
import com.mango.mif.domain.ExecutionRequestBuilder;
import com.mango.mif.domain.ExecutionType;

/**
 * ExecutionRequestBuilder currently just does PsN execute.
 */
public class PerfTestExecutionRequestBuilder {
    
    private ExecutionRequestBuilder executionRequestBuilder;
            
    public PerfTestExecutionRequestBuilder() {
        executionRequestBuilder = new ExecutionRequestBuilder();
    }
    
    public void setCommandFiles(UUID uuid, String scriptName, ExecutionType execType, String description, String commandPath) {
        executionRequestBuilder.setRequestId(uuid.toString());
        executionRequestBuilder.setName(description);
        executionRequestBuilder.setExecutionType(execType.name());
        executionRequestBuilder.setExecutionFile(scriptName);
        executionRequestBuilder.setCommand(commandPath);
        executionRequestBuilder.setSubmitAsUserMode(true);
        executionRequestBuilder.setSubmitHostPreamble("");        
        executionRequestBuilder.setGridHostPreamble("");
    }
    
    public void setParameters(String parameters) {
        executionRequestBuilder.setExecutionParameters(parameters);
    }
    
    public void addEnvironmentAttributes() {
        
        Map<String, String> envAttr = Maps.newHashMap();
        envAttr.put(ExecutionRequestAttributeName.SHELL.name(), "/bin/bash");
        envAttr.put(ExecutionRequestAttributeName.DIRIGNOREPATTERN.name(), "");
        envAttr.put(ExecutionRequestAttributeName.MANGO_DEBUG.name(), "false");
        envAttr.put(ExecutionRequestAttributeName.UMASK.name(), "007");
        envAttr.put(ExecutionRequestAttributeName.GRID_SHELL.name(), "/bin/bash");
        envAttr.put(ExecutionRequestAttributeName.FILEIGNOREPATTERN.name(), "");           
        envAttr.put("QSUB_PARAMETERS", "-V -q all.q");
        executionRequestBuilder.setRequestAttributes(envAttr);
    }

    private String getIpAddress() {
		String address;
		try {
			address = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			address = "unknown";
		}
		return address;
	}
    
	public PerfTestExecutionRequestBuilder buildJobType(ExecutionType execType, int batchNum, int jobNum, UUID uuid) {
		
		String address = getIpAddress();
        String description = "PerfTest-" + execType.name() + "-" + String.valueOf(batchNum) + "-" + String.valueOf(jobNum) + "-" + address;		        
        String controlFileName = execType.name() + "-Test.mod";
        this.addEnvironmentAttributes();
        
		switch (execType) {
		
			case PsN_EXEC: {
						
				this.setCommandFiles(uuid, controlFileName, execType, description, "execute");
				this.setParameters("-nm_version=default -directory=PsN_EXEC_run" + System.currentTimeMillis());		       
                break;
			}
			
			case NMFE: {
	    		
				this.setCommandFiles(uuid, controlFileName, execType, description, "/usr/global/NONMEM72/run/nmfe72");
                break;
	    	}
	    	
	    	case PsN_SCM: {
	    		
	    		this.setCommandFiles(uuid, controlFileName, execType, description, "scm");
				this.setParameters("-nm_version=default -directory=PsN_SCM_run" + System.currentTimeMillis());
                break;
	    	}
	    	
	    	case PsN_VPC: {
	    		
	    		this.setCommandFiles(uuid, controlFileName, execType, description, "vpc");
				this.setParameters("-nm_version=default -directory=PsN_VPC_run" + System.currentTimeMillis());
                break;
	    	}
	    	
	    	case PsN_Bootstrap: {
	    		
	    		this.setCommandFiles(uuid, controlFileName, execType, description, "bootstrap");
				this.setParameters("-nm_version=default -directory=PsN_BOOTSTRAP_run" + System.currentTimeMillis());
				break;
	    	}
			default: {
			    throw new IllegalArgumentException("Unsupported execution type " + execType);
			    
			}
		}
		return this;
	}

    public PerfTestExecutionRequestBuilder setUserName(String userName) {
        executionRequestBuilder.setUserName(userName);
        return this;
    }

    public PerfTestExecutionRequestBuilder setEncryptedUserPassword(String userPassword) {
        executionRequestBuilder.setUserPassword(userPassword);
        return this;
    }
    
    /**
     * Built execution request
     * @return
     */
    public ExecutionRequest getExecutionRequest() {
        ExecutionRequest executionRequest = executionRequestBuilder.getExecutionRequest();
        Preconditions.checkArgument(!StringUtils.isEmpty(executionRequest.getUserName()), "User name is not set on the request");
        Preconditions.checkArgument(!StringUtils.isEmpty(executionRequest.getUserPassword()), "User password is not set on the request");
        return executionRequest;
    }
}
