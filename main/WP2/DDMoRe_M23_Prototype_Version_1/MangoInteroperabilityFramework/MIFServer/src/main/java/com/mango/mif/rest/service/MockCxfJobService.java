/* File			:  MockMyJobServiceController.java
 * Project		:  MIFServer
 * Created on	:  10 Jul 2012
 */
package com.mango.mif.rest.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.mango.mif.client.api.rest.BootstrapActiveChildJobs;
import com.mango.mif.client.api.rest.FileList;
import com.mango.mif.client.api.rest.FileSystemItem;
import com.mango.mif.client.api.rest.JobService;
import com.mango.mif.client.api.rest.JobSummaryMessages;
import com.mango.mif.client.api.rest.MIFResponse;
import com.mango.mif.client.api.rest.NmMetrics;
import com.mango.mif.domain.DetailedStatus;
import com.mango.mif.domain.nonmemsgeconnector.NONMEMProcessingDetailedStatus;
import com.mango.mif.domain.psnsgeconnector.PsNBootstrapProcessingDetailedStatus;
import com.mango.mif.domain.psnsgeconnector.PsNSCMProcessingDetailedStatus;
import com.mango.mif.domain.psnsgeconnector.PsNVPCProcessingDetailedStatus;
import com.mango.mif.exception.MIFException;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * Mock job service
 */
public class MockCxfJobService implements JobService{
		
		@Override
		public String[] getAcknowledgedJobsByDate(Long dateTo) {
			// TODO Auto-generated method stub
			return null;
		}


		/* (non-Javadoc)
		 * @see com.mango.mif.client.api.rest.JobService#getJobStatus(java.util.UUID)
		 */
		@Override
		public String getJobStatus(String jobId) {
			//TODO:  Implement
			return null;
		}

		
		@Override
		public FileList listDir(String jobId,
				String endPath) throws MIFException {

			FileList fileList = new FileList();			
			fileList.setFileSystemItems(new ArrayList<FileSystemItem>());
			return fileList;
			
		}

		/* (non-Javadoc)
		 * @see com.mango.mif.rest.controller.CxfJobService#getFileContents(java.lang.String, java.lang.String)
		*/ 
		@Override
		public byte[] getTailedFileContents(String jobId, String endPath) throws MIFException {

			return new byte[10];
			
		}

        @Override
        public JobSummaryMessages getRunningJobsSummaryMessages() {
            JobSummaryMessages jobSummaryMessages = new JobSummaryMessages();
            
            jobSummaryMessages.setSummaryMessages(new HashMap<String,String>());
            
            return jobSummaryMessages;
        }



        @Override
        public BootstrapActiveChildJobs getActiveJobsOfBootstrap(
        String jobId) {
            return null;
        }



		@Override
		public NmMetrics getNONMEMMetrics(String requestId) {
			// TODO Auto-generated method stub
			return null;
		}

        public MIFResponse killPsNBootstrapChildSGEJobs(
        String jobId, 
        List<String> sgeJobIds) {
            MIFResponse response = new MIFResponse();
            StringBuilder builder = new StringBuilder();
            for(String segment : sgeJobIds) {
                builder.append(segment);
                builder.append(",");
            }
            response.setErrorMessage(builder.toString());
            return response;
        }


		@Override
		public byte[] getFullFileContents(String jobId, String endPath)
				throws MIFException {
			// TODO Auto-generated method stub
			return null;
		}


        @Override
        public byte[] getSCMLogFileContent(
        String jobId) {
            return null;
        }

        @Override
        public DetailedStatus getDetailedStatus(
        String jobId) throws MIFException {
            return null;
        }


        @Override
        public PsNBootstrapProcessingDetailedStatus getPsNBootstrapDetailedStatus(
        String jobId) {
            return null;
        }


        @Override
        public PsNSCMProcessingDetailedStatus getPsNSCMDetailedStatus(
        String jobId) {
            return null;
        }


        @Override
        public PsNVPCProcessingDetailedStatus getPsNVPCDetailedStatus(
        String jobId) throws MIFException {
            return null;
        }


        @Override
        public NONMEMProcessingDetailedStatus getNONMEMDetailedStatus(
        String jobId) throws MIFException {
            return null;
        }



}
