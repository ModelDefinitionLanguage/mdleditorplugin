package com.mango.mif.sge;

import java.util.Iterator;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * Represents parsed SGE output
 * 
 * @author mrogalski
 *
 */
public class SGEOutput {
    /**
     * Types of the SGE output
     * @author mrogalski
     *
     */
    public enum SGEOutputType {
        Submit,
        Status,
        Summary, Delete
    }
    /**
     * Job ID key
     */
    public static final String JOB_ID_KEY = "JOB_ID";
    /**
     * A key representing the status of the response
     */
    public static final String SGE_COMMAND_RESULT_KEY = "STATUS";
    /**
     * Job Submitted
     */
    public static final String JOB_SUBMITTED = "SUBMITTED";
    /**
     * Job was not found
     */
    public static final String JOB_NOT_FOUND = "NOT_FOUND";
    /**
     * Job is running
     */
    public static final String JOB_PROCESSING = "RUNNING";
    /**
     * Job finished
     */
    public static final String JOB_FINISHED = "FINISHED";
    /**
     * Job submission failed
     */
    public static final String JOB_SUBMISSION_FAILED = "JOB_SUBMISSION_FAILED";
    /**
     * A deleted status
     */
    public static final String JOB_DELETED = "DELETED";
    /**
     * A key that holds the original output message from SGE
     */
    public static final String OUTPUT_MESSAGE_KEY = "OUTPUT_MESSAGE";
    /**
     * Type of the output
     */
    private final SGEOutputType type;
    /**
     * Map holding parsed data from the SGE output
     */
    private Map<String,String> data = Maps.newHashMap();
    /**
     * 
     * @param type
     */
    public SGEOutput(SGEOutputType type) {
        this.type = type;
    }

    public SGEOutputType getType() {
        return type;
    }

    /**
     * 
     * {@link #java.util.Map.put(String, String)}
     * 
     * @param key
     * @param value
     * @return
     */
    public String put(String key, String value) {
        return data.put(key, value);
    }

    /**
     * 
     * {@link #java.util.Map.get(String)}
     * @param key
     * @return
     */
    public String get(Object key) {
        return data.get(key);
    }

    public Map<String, String> getData() {
        return data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Iterator<Map.Entry<String, String>> it = data.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> pairs = it.next();
            if (sb.length() > 1) {
                sb.append(" ");
            }
            sb.append(pairs.getKey());
            sb.append("=>");
            sb.append(pairs.getValue());
        }
        return sb.toString();
    }
}
