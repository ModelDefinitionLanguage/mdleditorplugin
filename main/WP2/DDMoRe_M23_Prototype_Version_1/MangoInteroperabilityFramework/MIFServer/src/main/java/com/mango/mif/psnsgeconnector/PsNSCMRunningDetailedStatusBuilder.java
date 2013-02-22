/* File			:  PsNSCMRunningDetailedStatusBuilder.java
 * Project		:  MIFServer
 * Created on	:  Aug 13, 2012
 */
package com.mango.mif.psnsgeconnector;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.InvokerResult;
import com.mango.mif.core.exec.invoker.InvokerHelper;
import com.mango.mif.domain.psnsgeconnector.PsNSCMProcessingDetailedStatus;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * The class is responsible for generating summary messages during PsN SCM execution
 * 
 */
public class PsNSCMRunningDetailedStatusBuilder extends DetailedStatusBuilderWithCommandBuilder {

    private static final Logger LOG = Logger.getLogger(PsNSCMRunningDetailedStatusBuilder.class);

    @Override
    public PsNSCMProcessingDetailedStatus getDetailedStatus() throws ExecutionException {
        PsNSCMProcessingDetailedStatus detailedStatus = new PsNSCMProcessingDetailedStatus();
        InvokerHelper helper = new InvokerHelper(job.getInvoker());
        String command = summaryCommandBuilder.getCommand();
        InvokerResult invokerResult = helper.run(summaryCommandBuilder.getCommand());

        if (invokerResult.getExitStatus() != 0) {
            throw new ExecutionException("Could not retrieve SCM summary. \n Error Stream:\n"
                    + invokerResult.getErrorStream()
                    + "\n Output Stream:\n"
                    + invokerResult.getOutputStream());
        } else {
            String summary = StringUtils.chomp(invokerResult.getOutputStream());
            detailedStatus.setSummary(summary);
        }
        detailedStatus.setRequestId(job.getJobId());
        detailedStatus.setUserName(job.getRequestUserName());
        long startTime = Long.parseLong(job.getSGEStartTime());
        long duration = new Date().getTime();
        if (startTime < 0) {
            duration = 0;
        } else {
            duration -= startTime;
        }

        detailedStatus.setDuration(Long.toString(duration));
        return detailedStatus;
    }
}
