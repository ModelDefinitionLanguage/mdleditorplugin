/* File			:  NONMEMProcessingDetailedStatusMarshaller.java
 * Project		:  MIFServer
 * Created on	:  Sep 4, 2012
 */
package com.mango.mif.nonmemsgeconnector;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.google.common.collect.Maps;
import com.mango.mif.domain.nonmemsgeconnector.NONMEMProcessingDetailedStatus;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * A class responsible for marshalling and unmarshalling the NONMEMProcessingDetailedStatus objects
 */
public class NONMEMProcessingDetailedStatusMarshaller {
    protected static final Logger LOG = Logger.getLogger(NONMEMProcessingDetailedStatusMarshaller.class);
    /**
     * 
     * @author mrogalski
     * constant keys expected in the output of the command retrieving child jobs details.
     * 
     */
    enum NONMEMRunProperties {
        jobId,
        runName,
        modFileName,
        runWorkingDir,
        startTime,
        duration,
        outputFile,
        description,
        status,
        summaryMessage,
        objectiveFunctionValue,
        estimationMethod
    }
    
    private final static String NEWLINE = "\n";
    private final static String KEY_VALUE_SEP = ":";
    private final static String CSV_SEP = ";";

    private final static Pattern DETAILED_PROPERTY = Pattern.compile("([^"+KEY_VALUE_SEP+"]+)"+KEY_VALUE_SEP+"\\s*(.*)");
    /**
     * Parses a given string as a list of NONMEM processing detailed statuses
     * @param content
     * @return
     */
    public static Map<String,NONMEMProcessingDetailedStatus> unmarshallNonmemRuns(String content) {
        LOG.debug(content);
        String[] rows = StringUtils.trimToEmpty(content).split(NEWLINE);
        Map<String,NONMEMProcessingDetailedStatus> nonmemJobStatuses = Maps.newHashMap();
        for(String row : rows) {
            NONMEMProcessingDetailedStatus status = new NONMEMProcessingDetailedStatus();
            unmarshallNonmemRun(row, status);
            
            nonmemJobStatuses.put(status.getGridJobId(),status);
        }
        return nonmemJobStatuses;
    }
    
    /**
     * Populates given detailed status with properties found in the content
     * @param content
     * @param detailedStatus
     */
    public static void unmarshallNonmemRun(String content, NONMEMProcessingDetailedStatus detailedStatus) {
            Map<String,String> properties = parseMap(content);
            detailedStatus.setGridJobId(properties.get(NONMEMRunProperties.jobId.name()));
            detailedStatus.setRunName(properties.get(NONMEMRunProperties.runName.name()));
            detailedStatus.setControlFileName(properties.get(NONMEMRunProperties.modFileName.name()));
            detailedStatus.setWorkingDirectory(properties.get(NONMEMRunProperties.runWorkingDir.name()));
            detailedStatus.setStartTime(properties.get(NONMEMRunProperties.startTime.name()));
            detailedStatus.setDuration(properties.get(NONMEMRunProperties.duration.name()));
            detailedStatus.setOutputFile(properties.get(NONMEMRunProperties.outputFile.name()));
            detailedStatus.setDescription(properties.get(NONMEMRunProperties.description.name()));
            detailedStatus.setObjectiveFunctionValue(properties.get(NONMEMRunProperties.objectiveFunctionValue.name()));
            detailedStatus.setEstimationMethod(properties.get(NONMEMRunProperties.estimationMethod.name()));
            detailedStatus.setSummary(properties.get(NONMEMRunProperties.summaryMessage.name()));
            detailedStatus.setStatus(properties.get(NONMEMRunProperties.status.name()));
    }
    /**
     * Parses a map represented as csv of key:value pairs
     * @param row
     * @return
     */
    private static Map<String, String> parseMap(String row) {
        HashMap<String,String> result = Maps.newHashMap();
        Scanner scanner = new Scanner(row).useDelimiter(CSV_SEP);
        try {
            while (scanner.hasNext()) {
                String line = scanner.next();
                Matcher matcher = DETAILED_PROPERTY.matcher(line);
                if (matcher.find()) {
                    String key = matcher.group(1);
                    String value = matcher.group(2);

                    result.put(key,value);
                }
            }
        } finally {
            scanner.close();
        }
        return result;
    }
    /**
     * Parses a given string as a list of noneme processing detailed statuses
     * @param content
     * @return
     */
    public static String marshallNonmemRuns(Map<String,NONMEMProcessingDetailedStatus> nonmemJobStatuses) {
        StringBuilder builder = new StringBuilder();
        for(NONMEMProcessingDetailedStatus detailedStatus : nonmemJobStatuses.values()) {
            builder.append(NONMEMRunProperties.jobId).append(KEY_VALUE_SEP).append(detailedStatus.getGridJobId()).append(CSV_SEP)
                   .append(NONMEMRunProperties.modFileName).append(KEY_VALUE_SEP).append(detailedStatus.getControlFileName()).append(CSV_SEP)
                   .append(NONMEMRunProperties.runName).append(KEY_VALUE_SEP).append(detailedStatus.getRunName()).append(CSV_SEP)
                   .append(NONMEMRunProperties.runWorkingDir).append(KEY_VALUE_SEP).append(detailedStatus.getWorkingDirectory()).append(CSV_SEP)
                   .append(NONMEMRunProperties.startTime).append(KEY_VALUE_SEP).append(detailedStatus.getStartTime()).append(CSV_SEP)
                   .append(NONMEMRunProperties.duration).append(KEY_VALUE_SEP).append(detailedStatus.getDuration()).append(CSV_SEP)
                   .append(NONMEMRunProperties.outputFile).append(KEY_VALUE_SEP).append(detailedStatus.getOutputFile()).append(CSV_SEP)
                   .append(NONMEMRunProperties.description).append(KEY_VALUE_SEP).append(detailedStatus.getDescription()).append(CSV_SEP)
                   .append(NONMEMRunProperties.objectiveFunctionValue).append(KEY_VALUE_SEP).append(detailedStatus.getObjectiveFunctionValue()).append(CSV_SEP)
                   .append(NONMEMRunProperties.estimationMethod).append(KEY_VALUE_SEP).append(detailedStatus.getEstimationMethod()).append(CSV_SEP)
                   .append(NONMEMRunProperties.summaryMessage).append(KEY_VALUE_SEP).append(detailedStatus.getSummary()).append(CSV_SEP)
                   .append(NONMEMRunProperties.status).append(KEY_VALUE_SEP).append(detailedStatus.getStatus());
     
            builder.append(NEWLINE);
        }
        return builder.toString();
    }
}
