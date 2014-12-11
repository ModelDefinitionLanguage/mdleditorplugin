package eu.ddmore.convertertoolbox.domain;

import java.io.File;

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
    private transient File inputArchive;
    private transient File outputArchive;
    private transient File workingDirectory;
    
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
        this.outputFileSize = other.outputFileSize;
        this.submissionTime = other.submissionTime;
        this.completionTime = other.completionTime;
        this.inputArchive = other.inputArchive;
        this.outputArchive = other.outputArchive;
        this.workingDirectory = other.workingDirectory;
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

    public void setWorkingDirectory(File workingDirectory) {
        this.workingDirectory = workingDirectory;
    }
    
    public File getWorkingDirectory() {
        return workingDirectory;
    }

    @Override
    public String toString() {
        return String
                .format(
                    "Conversion [id=%s, status=%s, from=%s, to=%s, inputFileName=%s, outputFileSize=%s, submissionTime=%s, completionTime=%s, conversionReport=%s, inputArchive=%s, outputArchive=%s, workingDirectory=%s]",
                    id, status, from, to, inputFileName, outputFileSize, submissionTime, completionTime, conversionReport, inputArchive,
                    outputArchive, workingDirectory);
    }
    
}
