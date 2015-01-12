/*******************************************************************************
 * Copyright (C) 2013 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.domain.internal;

/**
 * A mapper object, converts domain objects representations from internal to external
 */
public class ObjectMapper {
    /**
     * Hidden constructor
     */
    private ObjectMapper() {
        
    }
    
    
    public static eu.ddmore.convertertoolbox.domain.Conversion map(Conversion con) {
        eu.ddmore.convertertoolbox.domain.Conversion result = new eu.ddmore.convertertoolbox.domain.Conversion();
        result.setId(con.getId());
        result.setStatus(con.getStatus());
        result.setFrom(con.getFrom());
        result.setTo(con.getTo());
        result.setInputFileName(con.getInputFileName());
        result.setOutputFileSize(con.getOutputFileSize());
        result.setSubmissionTime(con.getSubmissionTime());
        result.setCompletionTime(con.getCompletionTime());
        result.setConversionReport(con.getConversionReport());
        return result;
    }
    
    public static Conversion map(eu.ddmore.convertertoolbox.domain.Conversion con) {
        Conversion result = new Conversion();
        result.setId(con.getId());
        result.setStatus(con.getStatus());
        result.setFrom(con.getFrom());
        result.setTo(con.getTo());
        result.setInputFileName(con.getInputFileName());
        result.setOutputFileSize(con.getOutputFileSize());
        result.setSubmissionTime(con.getSubmissionTime());
        result.setCompletionTime(con.getCompletionTime());
        result.setConversionReport(con.getConversionReport());
        return result;
    }
}
