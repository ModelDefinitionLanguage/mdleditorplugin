/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.domain;

import org.springframework.hateoas.Identifiable;
import org.springframework.hateoas.core.Relation;


/**
 * Represents a conversion
 */
@Relation()
public class Conversion implements Identifiable<String> {
    private String id;
    private ConversionStatus status = ConversionStatus.New;
    private LanguageVersion from;
    private LanguageVersion to;
    private String inputFileName;
    private Long outputFileSize;
    private Long submissionTime;
    private Long completionTime;
    private ConversionReport conversionReport;
    
    /**
     * Empty constructor
     */
    public Conversion() {
        super();
    }

    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public ConversionStatus getStatus() {
        return status;
    }
    
    public void setStatus(ConversionStatus status) {
        this.status = status;
    }
    
    public Long getOutputFileSize() {
        return outputFileSize;
    }
    
    public void setOutputFileSize(Long outputFileSize) {
        this.outputFileSize = outputFileSize;
    }
    
    public Long getSubmissionTime() {
        return submissionTime;
    }
    
    public void setSubmissionTime(Long submissionTime) {
        this.submissionTime = submissionTime;
    }
    
    public Long getCompletionTime() {
        return completionTime;
    }
    
    public void setCompletionTime(Long completionTime) {
        this.completionTime = completionTime;
    }
    
    public ConversionReport getConversionReport() {
        return conversionReport;
    }
    
    public void setConversionReport(ConversionReport conversionReport) {
        this.conversionReport = conversionReport;
    }
    
    public LanguageVersion getFrom() {
        return from;
    }
    
    public LanguageVersion getTo() {
        return to;
    }
    
    public String getInputFileName() {
        return inputFileName;
    }
    
    public void setFrom(LanguageVersion from) {
        this.from = from;
    }
    
    public void setInputFileName(String inputFileName) {
        this.inputFileName = inputFileName;
    }
    
    public void setTo(LanguageVersion to) {
        this.to = to;
    }


    @Override
    public String toString() {
        return String
                .format(
                    "Conversion [id=%s, status=%s, from=%s, to=%s, inputFileName=%s, outputFileSize=%s, submissionTime=%s, completionTime=%s, conversionReport=%s]",
                    id, status, from, to, inputFileName, outputFileSize, submissionTime, completionTime, conversionReport);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Conversion other = (Conversion) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
