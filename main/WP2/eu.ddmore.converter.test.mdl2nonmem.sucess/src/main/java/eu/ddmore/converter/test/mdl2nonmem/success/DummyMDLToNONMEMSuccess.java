/*******************************************************************************
 * Copyright (C) 2013 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.test.mdl2nonmem.success;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

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
public class DummyMDLToNONMEMSuccess implements ConverterProvider {

    private LanguageVersion source;
    private LanguageVersion target;
    private Version converterVersion;

    public DummyMDLToNONMEMSuccess() {
        Version sourceVersion = new VersionImpl(5, 0, 8, "succeeding");
        source = new LanguageVersionImpl("MDL", sourceVersion);

        Version targetVersion = new VersionImpl(7, 2, 0, "succeeding");
        target = new LanguageVersionImpl("NMTRAN", targetVersion);

        converterVersion = new VersionImpl(1, 0, 2);
    }

    @Override
    public ConversionReport performConvert(File src, File outputDirectory) {
        ConversionReport report = new ConversionReportImpl();
        report.setReturnCode(ConversionCode.SUCCESS);
        if (src.getName().contains("w")) {
            report.addDetail(createConversionDetail(Severity.WARNING));
        }
        if (src.getName().contains("i")) {
            report.addDetail(createConversionDetail(Severity.INFO));
        }
        if (src.getName().contains("d")) {
            report.addDetail(createConversionDetail(Severity.DEBUG));
        }
        File resultFile = new File(outputDirectory,"output-" + src.getName());
        try {
            FileUtils.writeStringToFile(resultFile, this.toString());
        } catch (IOException e) {
            throw new RuntimeException(String.format("could not create result file %s",resultFile.getAbsolutePath()));
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
        return String.format("DummyMDLToNONMEMSuccessConverter [source=%s, target=%s, converterVersion=%s]", source, target, converterVersion);
    }
}
