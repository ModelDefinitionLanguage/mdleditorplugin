/*******************************************************************************
 * Copyright (C) 2013 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.test.mdl2nonmem.success;

import java.io.File;

import eu.ddmore.convertertoolbox.api.domain.LanguageVersion;
import eu.ddmore.convertertoolbox.api.domain.Version;
import eu.ddmore.convertertoolbox.api.response.ConversionDetail;
import eu.ddmore.convertertoolbox.api.response.ConversionDetail.Severity;
import eu.ddmore.convertertoolbox.api.response.ConversionReport;
import eu.ddmore.convertertoolbox.api.response.ConversionReport.ConversionCode;
import eu.ddmore.convertertoolbox.api.spi.ConverterProvider;
import eu.ddmore.convertertoolbox.domain.LanguageVersionImpl;
import eu.ddmore.convertertoolbox.domain.VersionImpl;
import eu.ddmore.convertertoolbox.response.ConversionDetailImpl;
import eu.ddmore.convertertoolbox.response.ConversionReportImpl;

/**
 * Interface which Converter providers should implement to enable them to be
 * called by clients of the Converter Toolbox.
 */
public class DummyMDLToNONMEMSuccess implements ConverterProvider {

    private LanguageVersion source;
    private LanguageVersion target;
    private Version converterVersion;

    public DummyMDLToNONMEMSuccess() {
        source = new LanguageVersionImpl();
        source.setLanguage("MDL");
        Version sourceVersion = new VersionImpl(5, 0, 8, "qualm");
        source.setVersion(sourceVersion);

        target = new LanguageVersionImpl();
        target.setLanguage("NMTRAN");
        Version targetVersion = new VersionImpl(7, 2, 0, "qualn");
        target.setVersion(targetVersion);

        converterVersion = new VersionImpl(1, 0, 2, null);
    }

    @Override
    public ConversionReport performConvert(File src, File outputDirectory) {
        ConversionReport report = new ConversionReportImpl();
        report.setReturnCode(ConversionCode.SUCCESS);
        ConversionDetail conversionDetail = createConversionDetail();
        report.addDetail(conversionDetail);
        return report;
    }

    private ConversionDetail createConversionDetail() {
        ConversionDetail conversionDetail = new ConversionDetailImpl();
        conversionDetail.addInfo("INFO", "What a nice conversion!");
        conversionDetail.setMessage("Some message");
        conversionDetail.setSeverity(Severity.ALL);
        return conversionDetail;
    }

    @Override
    public ConversionReport[] performConvert(File[] src, File outputDirectory) {
        ConversionReport report = new ConversionReportImpl();
        report.setReturnCode(ConversionCode.SUCCESS);
        ConversionDetail conversionDetail = createConversionDetail();
        report.addDetail(conversionDetail);
        return new ConversionReport[] { report, report };
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
