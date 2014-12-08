package eu.ddmore.convertertoolbox.domain;

import java.io.File;
import java.util.Date;

import eu.ddmore.convertertoolbox.api.domain.LanguageVersion;
import eu.ddmore.convertertoolbox.api.response.ConversionReport;


/**
 * Represents a conversion
 */
public class Conversion {
    private String id;
    private ConversionStatus status = ConversionStatus.New;
    private LanguageVersion from;
    private LanguageVersion to;
    private String inputFileName;
    private String outputFileName;
    private Long outputFileSize;
    private Date submissionTime;
    private Date completionTime;
    private ConversionReport conversionReport;
    private transient File inputArchive;
    private transient File outputArchive;
    
    /**
     * Empty constructor
     */
    public Conversion() {
        super();
    }
    
    /**
     * Creates a new instance by copying state of the other conversion instance
     * @param other
     */
    public Conversion(Conversion other) {
        super();
        this.id = other.id;
        this.status = other.status;
        this.from = other.from;
        this.to = other.to;
        this.inputFileName = other.inputFileName;
        this.outputFileName = other.outputFileName;
        this.outputFileSize = other.outputFileSize;
        this.submissionTime = other.submissionTime;
        this.completionTime = other.completionTime;
        this.inputArchive = other.inputArchive;
        this.outputArchive = other.outputArchive;
        //FIXME we must ensure that this is immutable
        this.conversionReport = other.conversionReport;
        
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
    
    public String getOutputFileName() {
        return outputFileName;
    }
    
    public Conversion setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
        return this;
    }
    
    public Long getOutputFileSize() {
        return outputFileSize;
    }
    
    public Conversion setOutputFileSize(Long outputFileSize) {
        this.outputFileSize = outputFileSize;
        return this;
    }
    
    public Date getSubmissionTime() {
        return submissionTime;
    }
    
    public Conversion setSubmissionTime(Date submissionTime) {
        this.submissionTime = submissionTime;
        return this;
    }
    
    public Date getCompletionTime() {
        return completionTime;
    }
    
    public Conversion setCompletionTime(Date completionTime) {
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

    public File getOutputArchive() {
        return outputArchive;
    }

    public void setOutputArchive(File outputArchive) {
        this.outputArchive = outputArchive;
    }

    public File getInputArchive() {
        return inputArchive;
    }

    public void setInputArchive(File inputArchive) {
        this.inputArchive = inputArchive;
    }
}
