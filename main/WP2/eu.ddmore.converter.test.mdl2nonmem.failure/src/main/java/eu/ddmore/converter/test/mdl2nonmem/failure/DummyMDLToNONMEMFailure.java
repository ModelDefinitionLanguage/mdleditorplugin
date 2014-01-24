/*******************************************************************************
 * Copyright (C) 2013 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.test.mdl2nonmem.failure;

import java.io.File;
import java.io.IOException;

import eu.ddmore.convertertoolbox.api.domain.LanguageVersion;
import eu.ddmore.convertertoolbox.api.domain.Version;
import eu.ddmore.convertertoolbox.api.response.ConversionReport;
import eu.ddmore.convertertoolbox.api.spi.ConverterProvider;
import eu.ddmore.convertertoolbox.domain.LanguageVersionImpl;
import eu.ddmore.convertertoolbox.domain.VersionImpl;

/**
 * Interface which Converter providers should implement to enable them to be
 * called by clients of the Converter Toolbox.
 */
public class DummyMDLToNONMEMFailure implements ConverterProvider {

    private LanguageVersion source;
    private LanguageVersion target;
    private Version converterVersion;
    
    public DummyMDLToNONMEMFailure() {
        source = new LanguageVersionImpl();
        source.setLanguage("MDL");
        Version sourceVersion = new VersionImpl();
        sourceVersion.setMajor(5);
        sourceVersion.setMinor(0);
        sourceVersion.setPatch(8);
        source.setVersion(sourceVersion);
        
        target = new LanguageVersionImpl();
        target.setLanguage("NONMEM");
        Version targetVersion = new VersionImpl();
        targetVersion.setMajor(7);
        targetVersion.setMinor(2);
        target.setVersion(targetVersion);
        
        converterVersion = new VersionImpl();
        converterVersion.setMajor(1);
        converterVersion.setMinor(0);
        converterVersion.setPatch(1);
    }
    
    @Override
    public ConversionReport performConvert(File src, File outputDirectory) throws IOException {
        throw new IOException("DummyMDLToNMTRANFailure");
    }

    @Override
    public ConversionReport[] performConvert(File[] src, File outputDirectory) throws IOException {
        throw new IOException("DummyMDLToNMTRANFailure");
    }

    @Override
    public LanguageVersion getSource() {
        return source;
    }

    @Override
    public LanguageVersion getTarget() {
        return target;
    }

    @Override
    public Version getConverterVersion() {
        return converterVersion;
    }

    public void setSource(LanguageVersion source) {
        this.source = source;
    }

    public void setTarget(LanguageVersion target) {
        this.target = target;
    }

    public void setConverterVersion(Version converterVersion) {
        this.converterVersion = converterVersion;
    }

}
