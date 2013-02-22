/* File			:  NONMEMMetricsCollector.java
 * Project		:  MIFServer
 * Created on	:  2 Oct 2012
 */
package com.mango.mif.rest.service;

import static org.apache.commons.lang.StringUtils.isBlank;

import java.io.File;
import java.io.StringReader;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.jms.IllegalStateException;

import org.apache.log4j.Logger;

import com.mango.mif.client.api.rest.NmMetrics;
import com.mango.mif.connector.domain.Job;
import com.mango.mif.core.exec.invoker.InvokerHelper;
import com.mango.mif.exception.MIFException;
import com.mango.pharma.nonmem.ControlFile;
import com.mango.pharma.nonmem.ExtFile;
import com.mango.pharma.nonmem.NonmemMetricsCollector;
import com.mango.pharma.nonmem.OutputFile;

/**
 * Allow for collection of NONMEM metrics for a specific Job using the JOB's invoker
 * Will populate a NMMetric instance received as a function argument with data of a run read from the runs related files
 * for plotting in the Detailed Monitor
 * 
 * Using the NonmemMetricsCollector class in mango pharma the nonmem metrics data is populated in the NmMetric object
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class NONMEMMetricsCollector {
	
	private static final Logger logger = Logger.getLogger(NONMEMMetricsCollector.class);
	
	/** This is the output file we are looking for during the run */
	private static final String OUTPUT_FILE = "OUTPUT";
	/** This is the output file pattern we are looking for during the run if we are unable to find OUTPUT*/
	private static final String LST_FILE = "*.lst";
	/** This is the ext file pattern we are looking for during the run */
	private static final String EXT_FILE = "*.ext";

	/**
	 * Uses the NonmemMetricsCollector class to populate the plot metrics
	 * in the NmMetrics object
	 * 
	 * @param job the job of interest
	 * @param nmMetrics the value object to populate
	 */
	public void collectNONMEMMetrics(Job job, NmMetrics nmMetrics) {	
		if (job.getGridJobCurrentWorkingDirectory().exists()) {
			InvokerHelper invokerHelper = new InvokerHelper(job.getInvoker());
			try {
				String controlFilePath = getControlFilePath(job);		
				ControlFile controlFile = getControlFileFromPath(controlFilePath, invokerHelper);
				logger.debug(String.format("Control file [%s] constructed", controlFile.getFilename()));
				OutputFile outputFile = getLatestOutputFile(controlFilePath, invokerHelper);
				logger.debug(String.format("Output file [%s] constructed", outputFile.getFilename()));
				ExtFile extFile = getLatestExtFile(controlFilePath, invokerHelper);
				logger.debug(String.format("Ext file [%s] constructed", extFile.getExtFilename()));
				nmMetrics.setMetrics(getPlotMetrics(controlFile, outputFile, extFile));
			} catch (Exception ex) {
				logger.error(String.format("Couldn't get the metrics for job [%s]",job.getJobId()), ex);
				nmMetrics.setErrors(String.format("Couldn't get the metrics for job [%s]",job.getJobId()));
			}
		} else {
			final String errorMessage = "Could not find working directory " + job.getGridJobCurrentWorkingDirectory().getAbsolutePath();
			logger.error(errorMessage);
			nmMetrics.setErrors(errorMessage);
		}
	}
	
	/**
	 * Returns the file path of the control file
	 */
	private String getControlFilePath(Job job) {
		return job.getGridJobExecutionFile().getAbsolutePath();
	}
	
	/**
	 * Gets the control file that's being executed using the control file path
	 */
	private ControlFile getControlFileFromPath(String controlFilePath, InvokerHelper invokerHelper) throws Exception {
		String controlFileContents = getFileContents(controlFilePath, invokerHelper);
		return new ControlFile(new StringReader(controlFileContents), controlFilePath);
	}
	
	/**
	 * Gets the latest output file that's been modified/created by Nonmem using the invoker helper
	 * It tries to find the latest OUTPUT file and if not found, it tries to find the latest
	 * lst file
	 */
	private OutputFile getLatestOutputFile(String controlFilePath, InvokerHelper invokerHelper) throws Exception {
		String directoryPath = new File(controlFilePath).getParent();
		String outputFilePath = null;
		try {
			outputFilePath = getPathOfLastModifiedFile(directoryPath, OUTPUT_FILE, invokerHelper);
		}catch (Exception ex) {
			outputFilePath = getPathOfLastModifiedFile(directoryPath, LST_FILE, invokerHelper);
		}
		String outputFileContents = getFileContents(outputFilePath, invokerHelper);
		return new OutputFile(new StringReader(outputFileContents), outputFilePath);
	}
	
	/**
	 * Gets the latest ext file that's been modified/created by Nonmem using the invoker helper
	 */
	private ExtFile getLatestExtFile(String controlFilePath, InvokerHelper invokerHelper) throws Exception {
		String directoryPath = new File(controlFilePath).getParent();
		String extFilePath = getPathOfLastModifiedFile(directoryPath, EXT_FILE, invokerHelper);
		String extFileContents = getFileContents(extFilePath, invokerHelper);
		return new ExtFile(new StringReader(extFileContents), extFilePath);
	}
	
	/**
	 * Gets the nonmem metrics that could be used to generate plots
	 */
	private Map<String, Double[]> getPlotMetrics(ControlFile controlFile, OutputFile outputFile, ExtFile extFile) {
		Map<String, Double[]> metrics = new LinkedHashMap<String, Double[]>();
		try {
			NonmemMetricsCollector collector = new NonmemMetricsCollector(controlFile, outputFile, extFile);
			metrics.putAll(collector.getPlotMetrics());
		} catch(Throwable t) {
			logger.error("Couldn't retrieve metrics from NonmemMetricsCollector for model "+controlFile.getFilename(), t);
		}
		logger.debug(String.format("The metrics collected for the model [%s] has %d headers -> [%s]", 
				controlFile.getFilename(), metrics.size(), metrics.keySet().toString()));
		return metrics;
	}
	
	/**
	 * Returns the file path of the last modified file with the name filename under the directory directoryPath using invoker helper
	 */
	private String getPathOfLastModifiedFile(String directoryPath, String filename, InvokerHelper invokerHelper) throws Exception {
		String filePath = invokerHelper.getPathOfLastModifiedFile(directoryPath, filename);
		if(isBlank(filePath)) {
			throw new IllegalStateException(String.format(
					"Couldn't find the required file [%s] under [%s] for building metrics", filename, directoryPath));
		}
		return filePath;
	}

	/**
	 * Gets the file contents of the file corresponding to the given file path using a invoker helper
	 */
	private String getFileContents(String filePath, InvokerHelper invokerHelper) throws Exception {
		String content = null;
		try {
			logger.debug("Getting file contents of "+filePath);
			content = invokerHelper.getFileContents(filePath);
		} catch (Exception e) {
			throw new MIFException("Problem retrieving file " + filePath + " using Invoker", e);
		}
		if(isBlank(content)) {
			throw new IllegalStateException(String.format(
					"Couldn't find any contents in the required file [%s] for building metrics", filePath));
		}
		return content;
	}
}
