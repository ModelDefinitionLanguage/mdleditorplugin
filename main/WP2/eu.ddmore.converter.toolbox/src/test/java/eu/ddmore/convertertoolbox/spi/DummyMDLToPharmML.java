/*******************************************************************************
 * Copyright (C) 2013 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.spi;

import java.io.File;

import eu.ddmore.convertertoolbox.api.domain.LanguageVersion;
import eu.ddmore.convertertoolbox.api.domain.Version;
import eu.ddmore.convertertoolbox.api.response.ConversionReport;
import eu.ddmore.convertertoolbox.api.response.ConversionReport.ConversionCode;
import eu.ddmore.convertertoolbox.api.spi.ConverterProvider;
import eu.ddmore.convertertoolbox.domain.ConversionReportImpl;
import eu.ddmore.convertertoolbox.domain.LanguageVersionImpl;
import eu.ddmore.convertertoolbox.domain.VersionImpl;

/**
 * Interface which Converter providers should implement to enable them to be
 * called by clients of the Converter Toolbox.
 */
public class DummyMDLToPharmML implements ConverterProvider {

    private LanguageVersion source;
    private LanguageVersion target;
    private Version converterVersion;

    public DummyMDLToPharmML() {
        Version sourceVersion = new VersionImpl(5, 0, 8);
        source = new LanguageVersionImpl("MDL", sourceVersion);

        Version targetVersion = new VersionImpl(0, 2, 1);
        target = new LanguageVersionImpl("PharmML", targetVersion);

        converterVersion = new VersionImpl(1, 0, 2);
   }

    @Override
    public ConversionReport performConvert(File src, File outputDirectory) {
        ConversionReport report = new ConversionReportImpl();
        report.setReturnCode(ConversionCode.SUCCESS);
        return report;
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

    @Override
    public String toString() {
        return String.format("DummyMDLToPharmMLConverter [source=%s, target=%s, converterVersion=%s]", source, target, converterVersion);
    }
}
