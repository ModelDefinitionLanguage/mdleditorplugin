/*******************************************************************************
 * Copyright (C) 2002 Mango Solutions Ltd - All rights reserved.
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
    
    public Conversion setId(String id) {
        this.id = id;
        return this;
    }
    
    public ConversionStatus getStatus() {
        return status;
    }
    
    public Conversion setStatus(ConversionStatus status) {
        this.status = status;
        return this;
    }
    
    public Long getOutputFileSize() {
        return outputFileSize;
    }
    
    public Conversion setOutputFileSize(Long outputFileSize) {
        this.outputFileSize = outputFileSize;
        return this;
    }
    
    public Long getSubmissionTime() {
        return submissionTime;
    }
    
    public Conversion setSubmissionTime(Long submissionTime) {
        this.submissionTime = submissionTime;
        return this;
    }
    
    public Long getCompletionTime() {
        return completionTime;
    }
    
    public Conversion setCompletionTime(Long completionTime) {
        this.completionTime = completionTime;
        return this;
    }
    
    public ConversionReport getConversionReport() {
        return conversionReport;
    }
    
    public Conversion setConversionReport(ConversionReport conversionReport) {
        this.conversionReport = conversionReport;
        return this;
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
    
    public Conversion setFrom(LanguageVersion from) {
        this.from = from;
        return this;
    }
    
    public Conversion setInputFileName(String inputFileName) {
        this.inputFileName = inputFileName;
        return this;
    }
    
    public Conversion setTo(LanguageVersion to) {
        this.to = to;
        return this;
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
