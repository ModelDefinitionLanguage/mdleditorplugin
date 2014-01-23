/*******************************************************************************
 * Copyright (C) 2013 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.response;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import eu.ddmore.convertertoolbox.api.response.ConversionDetail;

/**
 * Bean representing details generated by a conversion activity
 */
public class ConversionDetailImpl implements ConversionDetail {

    private File file;
    private Severity severity;
    private Map<String, String> info;
    private String message;

    public ConversionDetailImpl() {
        info = new HashMap<String, String>();
    }
    
    @Override
    public File getFile() {
        return file;
    }

    @Override
    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public Severity getServerity() {
        return severity;
    }

    @Override
    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    @Override
    public Map<String, String> getInfo() {
        return info;
    }

    @Override
    public void setInfo(Map<String, String> info) {
        this.info = info;
    }

    @Override
    public void addInfo(String key, String value) {
        info.put(key, value);
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("ConversionDetailImpl [file=%s, severity=%s, info=%s, message=%s]", file, severity, info, message);
    }

}
