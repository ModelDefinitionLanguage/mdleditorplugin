/*******************************************************************************
 * Copyright (C) 2014-5 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json

import org.apache.log4j.Logger

import eu.ddmore.converter.mdl2json.domain.MCLFile
import eu.ddmore.convertertoolbox.api.domain.LanguageVersion
import eu.ddmore.convertertoolbox.api.domain.Version
import eu.ddmore.convertertoolbox.api.response.ConversionReport
import eu.ddmore.convertertoolbox.api.response.ConversionReport.ConversionCode
import eu.ddmore.convertertoolbox.api.spi.ConverterProvider
import eu.ddmore.convertertoolbox.domain.ConversionReportImpl
import eu.ddmore.convertertoolbox.domain.LanguageVersionImpl
import eu.ddmore.convertertoolbox.domain.VersionImpl
import groovy.json.JsonSlurper


/**
 * This is a {@link ConverterProvider} implementation from JSON to MDL, specified versions thereof.
 */
public class JSONToMDLConverter implements ConverterProvider {

    private static Logger logger = Logger.getLogger(this.getClass());

    private static final String MDL_FILE_EXTENSION = ".mdl"
    private static final String JSON_FILE_EXTENSION = ".json"

    private final LanguageVersion source = new LanguageVersionImpl("JSON", new VersionImpl(6, 0, 8))
    private final LanguageVersion target = new LanguageVersionImpl("MDL", new VersionImpl(6, 0, 8))
    private final Version converterVersion = new VersionImpl(1, 0, 5);

    private String mdl

    /**
     * Converter Toolbox required entry point.
     */
    public ConversionReport performConvert(File src, File outputDirectory) throws IOException {
        String outputFileName = computeOutputFileName(src.getName())

        JsonSlurper jsonSlurper = new JsonSlurper();
        MCLFile mclFile = new MCLFile(jsonSlurper.parseText(src.getText()))

        ConversionReport report = new ConversionReportImpl();

        mdl = mclFile.toMDL();

        if(mdl) {
            def outputFile = new File(outputDirectory.getAbsolutePath() + File.separator + outputFileName);

            outputFile.write(mdl)
            report.setReturnCode(ConversionCode.SUCCESS);
        } else {
            def errorMsg = "Could not parse " + src.getPath()
            logger.error(errorMsg)
            System.err.println(errorMsg)
            report.setReturnCode(ConversionCode.FAILURE)
        }

        return report
    }

    private String computeOutputFileName(String name) {
        int dotIndex = name.lastIndexOf(JSON_FILE_EXTENSION)

        if (dotIndex == -1) {
            return name + MDL_FILE_EXTENSION
        } else {
            return name.substring(0, dotIndex) + MDL_FILE_EXTENSION
        }
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
        return String.format("JSON to MDL Converter [source=%s, target=%s, converterVersion=%s]", source, target, converterVersion)
    }
}
