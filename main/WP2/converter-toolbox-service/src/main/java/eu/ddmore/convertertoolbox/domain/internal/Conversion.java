/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.domain.internal;

import java.io.File;

/**
 * Conversion resource
 */
public class Conversion extends eu.ddmore.convertertoolbox.domain.Conversion {
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
        this.setId(other.getId());
        this.setStatus(other.getStatus());
        this.setFrom(other.getFrom());
        this.setTo(other.getTo());
        this.setInputFileName(other.getInputFileName());
        this.setOutputFileSize(other.getOutputFileSize());
        this.setSubmissionTime(other.getSubmissionTime());
        this.setCompletionTime(other.getCompletionTime());
        this.setInputArchive(other.inputArchive);
        this.setOutputArchive(other.outputArchive);
        this.setWorkingDirectory(other.workingDirectory);
        //FIXME we must ensure that this is immutable
        this.setConversionReport(other.getConversionReport());
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
}
