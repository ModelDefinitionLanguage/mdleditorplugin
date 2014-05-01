/*******************************************************************************
 * Copyright (C) 2014 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.pharmml2nmtran

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat

import org.apache.commons.io.FileUtils;

import eu.ddmore.converter.pharmml2nmtran.statements.DataStatement;
import eu.ddmore.converter.pharmml2nmtran.utils.ConverterUtils;
import eu.ddmore.converter.pharmml2nmtran.utils.TextFileWriter;
import eu.ddmore.convertertoolbox.api.domain.LanguageVersion;
import eu.ddmore.convertertoolbox.api.domain.Version;
import eu.ddmore.convertertoolbox.api.response.ConversionReport;
import eu.ddmore.convertertoolbox.api.response.ConversionReport.ConversionCode;
import eu.ddmore.convertertoolbox.api.spi.ConverterProvider;
import eu.ddmore.convertertoolbox.domain.LanguageVersionImpl
import eu.ddmore.convertertoolbox.domain.VersionImpl
import eu.ddmore.convertertoolbox.response.ConversionReportImpl
import eu.ddmore.libpharmml.PharmMlFactory


/**
 * This is a ConverterProvider implementation from PharmML 0.2.1 to NMTRAN 7.2 
 */
public class PharmMLToNMTRANConverter implements ConverterProvider {
    private LanguageVersion source;
    private LanguageVersion target;
    private Version converterVersion;
    private ConverterUtils converterUtils;

    public PharmMLToNMTRANConverter() {
        Version sourceVersion = new VersionImpl(0, 2, 1);
        source = new LanguageVersionImpl("PharmML", sourceVersion);

        Version targetVersion = new VersionImpl(7, 2, 0);
        target = new LanguageVersionImpl("NMTRAN", targetVersion);

        converterVersion = new VersionImpl(1, 0, 2);
    }

    @Override
    public ConversionReport performConvert(File src, File outputDirectory) throws IOException {
        String outputFileName = computeOutputFileName(src.getName())

        File outputFile = new File(outputDirectory.getAbsolutePath() +'/'+ outputFileName);

        def is = FileUtils.openInputStream(src)
        def pmlAPI = PharmMlFactory.getInstance().createLibPharmML()
        def pmlDOM = pmlAPI.createDomFromResource(is).getDom()

        StringBuilder nmtran = toNMTRAN(pmlDOM, src, outputDirectory)

        new TextFileWriter().writeToFile(outputFile, nmtran.toString())

        ConversionReport report = new ConversionReportImpl();
        //TODO: Need to handle, warnings/details
        report.setReturnCode(ConversionCode.SUCCESS);

        return report
    }

    private String computeOutputFileName(String name) {
        int dotIndex = name.indexOf('.')
        if (dotIndex == -1) {
            return name + ".ctl"
        } else {
            return name.substring(0, dotIndex) + ".ctl"
        }
    }

    private StringBuilder toNMTRAN(pmlDOM, src, outputDirectory) {
        StringBuilder nmtran = new StringBuilder();
        converterUtils = new ConverterUtils(pmlDOM, src)

        nmtran.append(converterUtils.getProblemStatement())
        nmtran.append('\n')
        if (pmlDOM.modellingSteps) {
            DataStatement dataStatement = new DataStatement(pmlDOM, outputDirectory.getAbsolutePath()+'/'+ src.getName().replace(".xml", ""))
            String dataFilePointer = dataStatement.createDataFile()
            List<String> headers = dataStatement.getHeaders()
            nmtran.append(converterUtils.getInputStatement(headers))

            nmtran.append(dataFilePointer)
            nmtran.append('\n')
            if (converterUtils.isEstimation()) {
                nmtran.append(converterUtils.getEstimationStatement())
                nmtran.append('\n')
            } else if (converterUtils.isSimulation()) {
                nmtran.append(converterUtils.getSimulationStatement())
                nmtran.append('\n')
            }
            nmtran.append(converterUtils.getThetasStatement())
            nmtran.append('\n')
            nmtran.append(converterUtils.getOmegasStatement())
            nmtran.append('\n')
            nmtran.append(converterUtils.getSigmasStatement())
            nmtran.append('\n')
        } else {
            //TODO: Conversion error should be thrown here
            throw new RuntimeException("Modelling steps missing. They are required by NMTRAN.")
        }

        nmtran.append(converterUtils.getPred())
        nmtran.append('\n')
        nmtran.append(converterUtils.getTableStatement())

        nmtran
    }

    @Override
    public ConversionReport[] performConvert(File[] src, File outputDirectory) throws IOException {
        ConversionReport[] reports = new ConversionReport[src.length];
        int i = 0;
        src.each { it ->
            reports[i++] = performConvert(it, outputDirectory);
        }
        return reports;
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

    @Override
    public String toString() {
        return String.format("PharmMLToNMTRANConverter [source=%s, target=%s, converterVersion=%s]", source, target, converterVersion) ;
    }
}