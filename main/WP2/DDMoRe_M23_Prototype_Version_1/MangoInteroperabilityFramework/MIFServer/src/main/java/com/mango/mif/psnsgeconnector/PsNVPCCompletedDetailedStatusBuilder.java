/* File			:  PsNVPCCompletedDetailedStatusBuilder.java
 * Project		:  MIFServer
 * Created on	:  Aug 13, 2012
 */
package com.mango.mif.psnsgeconnector;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.InvokerResult;
import com.mango.mif.core.exec.invoker.InvokerHelper;
import com.mango.mif.domain.psnsgeconnector.PsNVPCProcessingDetailedStatus;


/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * Generates a summary message for PsN VPC execution that is in completed state
 * 
 */
public class PsNVPCCompletedDetailedStatusBuilder extends 
DetailedStatusBuilderWithCommandBuilder  {

    private static final Logger LOG = Logger.getLogger(PsNVPCCompletedDetailedStatusBuilder.class);

    @Override
    public PsNVPCProcessingDetailedStatus getDetailedStatus() throws ExecutionException {
        PsNVPCProcessingDetailedStatus detailedStatus = new PsNVPCProcessingDetailedStatus();
        InvokerHelper helper = new InvokerHelper(job.getInvoker());
        String command = summaryCommandBuilder.getCommand();
        InvokerResult invokerResult = helper.run(summaryCommandBuilder.getCommand());        
        
        if (invokerResult.getExitStatus()!=0) {
            throw new ExecutionException("Could not retrieve VPC summary. \n Error Stream:\n" 
                    + invokerResult.getErrorStream() 
                    + "\n Output Stream:\n" 
                    + invokerResult.getOutputStream());
        } else {
            String summary = StringUtils.chomp(invokerResult.getOutputStream());            
            detailedStatus.setSummary(summary);            
        }  
        detailedStatus.setRequestId(job.getJobId());
        detailedStatus.setUserName(job.getRequestUserName());
        return detailedStatus;
    }
}
