/*******************************************************************************
 * Copyright (C) 2013 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.spi;

import java.io.File;

import eu.ddmore.convertertoolbox.api.domain.LanguageVersion;
import eu.ddmore.convertertoolbox.api.domain.Version;
import eu.ddmore.convertertoolbox.api.response.ConversionDetail;
import eu.ddmore.convertertoolbox.api.response.ConversionDetail.Severity;
import eu.ddmore.convertertoolbox.api.response.ConversionReport;
import eu.ddmore.convertertoolbox.api.response.ConversionReport.ConversionCode;
import eu.ddmore.convertertoolbox.api.spi.ConverterProvider;
import eu.ddmore.convertertoolbox.domain.ConversionDetailImpl;
import eu.ddmore.convertertoolbox.domain.ConversionReportImpl;
import eu.ddmore.convertertoolbox.domain.LanguageVersionImpl;
import eu.ddmore.convertertoolbox.domain.VersionImpl;

/**
 * Interface which Converter providers should implement to enable them to be
 * called by clients of the Converter Toolbox.
 */
public class DummyMDLToNMTRAN implements ConverterProvider {

    private LanguageVersion source;
    private LanguageVersion target;
    private Version converterVersion;

    public DummyMDLToNMTRAN() {
        Version sourceVersion = new VersionImpl(5, 0, 8);
        source = new LanguageVersionImpl("MDL", sourceVersion);

        Version targetVersion = new VersionImpl(7, 2, 0);
        target = new LanguageVersionImpl("NMTRAN", targetVersion);

        converterVersion = new VersionImpl(1, 0, 2);
    }

    @Override
    public ConversionReport performConvert(File src, File outputDirectory) {
        ConversionReport report = new ConversionReportImpl();
        report.setReturnCode(ConversionCode.SUCCESS);
        ConversionDetail conversionDetail = createConversionDetail(src);
        report.addDetail(conversionDetail);
        return report;
    }

    private ConversionDetail createConversionDetail(File src) {
        ConversionDetail conversionDetail = new ConversionDetailImpl();
        conversionDetail.addInfo("INFO", "What a nice conversion!");
        conversionDetail.setMessage("Some message");
        conversionDetail.setSeverity(Severity.ALL);
        return conversionDetail;
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
        return String.format("DummyMDLToNMTRANConverter [source=%s, target=%s, converterVersion=%s]", source, target, converterVersion);
    }
}
