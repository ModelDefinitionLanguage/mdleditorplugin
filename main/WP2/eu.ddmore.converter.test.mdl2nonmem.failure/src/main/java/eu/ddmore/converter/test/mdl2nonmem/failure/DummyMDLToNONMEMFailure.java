/*******************************************************************************
 * Copyright (C) 2013 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.test.mdl2nonmem.failure;

import java.io.File;
import java.io.IOException;

import eu.ddmore.convertertoolbox.api.domain.LanguageVersion;
import eu.ddmore.convertertoolbox.api.domain.Version;
import eu.ddmore.convertertoolbox.api.response.ConversionDetail;
import eu.ddmore.convertertoolbox.api.response.ConversionReport;
import eu.ddmore.convertertoolbox.api.response.ConversionDetail.Severity;
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
public class DummyMDLToNONMEMFailure implements ConverterProvider {

    private LanguageVersion source;
    private LanguageVersion target;
    private Version converterVersion;

    public DummyMDLToNONMEMFailure() {
        Version sourceVersion = new VersionImpl(5, 0, 8, "qualm");
        source = new LanguageVersionImpl("MDL", sourceVersion);

        Version targetVersion = new VersionImpl(7, 2, 0, "qualn");
        target = new LanguageVersionImpl("NMTRAN", targetVersion);

        converterVersion = new VersionImpl(1, 0, 1);
    }

    @Override
    public ConversionReport performConvert(File src, File outputDirectory) throws IOException {
        if (src.getName().equals("exception")) {
            throw new IOException("DummyMDLToNMTRANFailure");
        }

        ConversionReport report = new ConversionReportImpl();
        report.setReturnCode(ConversionCode.FAILURE);
        report.addDetail(createConversionDetail(Severity.ERROR));
        if (src.getName().contains("w")) {
            report.addDetail(createConversionDetail(Severity.WARNING));
        }
        if (src.getName().contains("i")) {
            report.addDetail(createConversionDetail(Severity.INFO));
        }
        if (src.getName().contains("d")) {
            report.addDetail(createConversionDetail(Severity.DEBUG));
        }
        return report;
    }

    private ConversionDetail createConversionDetail(Severity severity) {
        ConversionDetail conversionDetail = new ConversionDetailImpl();
        conversionDetail.setSeverity(severity);
        if (severity.equals(Severity.ERROR)) {
            conversionDetail.addInfo("error1", "error1");
            conversionDetail.addInfo("error2", "error2");
            conversionDetail.setMessage("ERROR message");
        } else if (severity.equals(Severity.WARNING)) {
            conversionDetail.addInfo("warning1", "warning1");
            conversionDetail.addInfo("warning2", "warning2");
            conversionDetail.setMessage("WARNING message");
        } else if (severity.equals(Severity.INFO)) {
            conversionDetail.addInfo("info1", "info1");
            conversionDetail.addInfo("info2", "info2");
            conversionDetail.setMessage("INFO message");
        } else if (severity.equals(Severity.DEBUG)) {
            conversionDetail.addInfo("debug1", "debug1");
            conversionDetail.addInfo("debug2", "debug2");
            conversionDetail.setMessage("DEBUG message");
        }
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
        return String.format("DummyMDLToNONMEMFailureConverter [source=%s, target=%s, converterVersion=%s]", source, target, converterVersion);
    }
}
