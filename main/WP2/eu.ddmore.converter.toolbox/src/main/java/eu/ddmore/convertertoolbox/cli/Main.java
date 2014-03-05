/*******************************************************************************
 * Copyright (C) 2013 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.cli;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.common.base.Preconditions;

import eu.ddmore.convertertoolbox.api.conversion.Converter;
import eu.ddmore.convertertoolbox.api.conversion.ConverterManager;
import eu.ddmore.convertertoolbox.api.domain.LanguageVersion;
import eu.ddmore.convertertoolbox.api.domain.Version;
import eu.ddmore.convertertoolbox.api.exception.ConverterNotFoundException;
import eu.ddmore.convertertoolbox.api.response.ConversionReport;
import eu.ddmore.convertertoolbox.conversion.ConverterManagerImpl;
import eu.ddmore.convertertoolbox.domain.LanguageVersionImpl;
import eu.ddmore.convertertoolbox.domain.VersionImpl;

/**
 * Command line interface to the converter toolbox, aims to provide the same (where possible) functionality to the programmatic API 
 */
public final class Main {

    private ConverterManager converterManager;
    private static final int MAX_VERSION_NUMBERS = 3;
    private static final Logger LOGGER = Logger.getLogger(Main.class);

    private ConversionReport convert(File src, String srcLanguage, String srcVersion, String targetLanguage, String targetVersion,
            File outputDirectory) throws ConverterNotFoundException, IOException {
        LanguageVersion source = getLanguageVersion(srcLanguage, srcVersion);
        LanguageVersion target = getLanguageVersion(targetLanguage, targetVersion);

        Converter converter = converterManager.getConverter(source, target);
        return converter.convert(src, outputDirectory);
    }

    private ConversionReport[] convert(File[] src, String srcLanguage, String srcVersion, String targetLanguage, String targetVersion,
            File outputDirectory) throws ConverterNotFoundException, IOException {
        LanguageVersion source = getLanguageVersion(srcLanguage, srcVersion);
        LanguageVersion target = getLanguageVersion(targetLanguage, targetVersion);

        Converter converter = converterManager.getConverter(source, target);
        ConversionReport[] reports = new ConversionReport[src.length];
        int i = 0;
        for (File inputFile : src) {
            reports[i++] = converter.convert(inputFile, outputDirectory);
        }
        return reports;
    }

    LanguageVersion getLanguageVersion(String language, String version) {
        int hyphenFirstIndex = version.indexOf('-');
        String qualifier = null;
        String versionNumbers = null;
        if (hyphenFirstIndex != -1) {
            versionNumbers = version.substring(0, hyphenFirstIndex);
            qualifier = version.substring(hyphenFirstIndex + 1);
        }
        Version sourceVersion = createVersion(qualifier, versionNumbers);
        return new LanguageVersionImpl(language, sourceVersion);
    }

    private Version createVersion(String qualifier, String versionNumbers) {
        String versionNumbersAsArray[] = versionNumbers.split("\\.");

        Preconditions
                .checkArgument(versionNumbersAsArray.length > 0,
                    "The language version should contain at least one number, e.g. '1' is interpreted into '1.0.0', '2.5' is interpreted into '2.5.0'.");
        Preconditions
                .checkArgument(versionNumbersAsArray.length <= MAX_VERSION_NUMBERS,
                    "The language version should contain at most three numbers according to the 'Major.Minor.Patch' naming convention, e.g. '2.5.1'.");

        int major = Integer.parseInt(versionNumbersAsArray[0]);
        int minor = 0;
        int patch = 0;
        if (versionNumbersAsArray.length > MAX_VERSION_NUMBERS - 2) {
            minor = Integer.parseInt(versionNumbersAsArray[1]);
        }
        if (versionNumbersAsArray.length > MAX_VERSION_NUMBERS - 1) {
            patch = Integer.parseInt(versionNumbersAsArray[2]);
        }
        return new VersionImpl(major, minor, patch, qualifier);
    }

    /**
     * 
     * @param args an array of 6 strings.   0:input file/folder, 1:output folder, 
     *                                      2:source language name, 3:source language version-qualifier, 
     *                                      4:target language name, 5:target language version-qualifier.
     *                                      e.g. 'myMDLFile.mdl C:/output/ MDL 5.0.8 NMTRAN 7.2.0'
     * @return array of Conversion reports that 
     * @throws ConverterNotFoundException
     * @throws IOException
     */
    public ConversionReport[] runFromCommandLine(String... args) throws ConverterNotFoundException, IOException {
        Preconditions
                .checkArgument(
                    args.length == 6,
                    "Illegal arguments. Run again by giving the arguments in the following format: 'sourcePath outputPath sourceLanguage sourceVersion targetLanguage targetVersion', e.g. 'myMDLFile.mdl C:/output/ MDL 5.0.8 NMTRAN 7.2.0'");
        converterManager = new ConverterManagerImpl();
        converterManager.discoverConverters();
        File src = new File(args[0]);
        File outputDirectory = new File(args[1]);
        if (src.isDirectory()) {
            File[] filesForConversion = getFilesFromDirectory(src);
            return convert(filesForConversion, args[2], args[3], args[4], args[5], outputDirectory);
        } else {
            return new ConversionReport[] { convert(src, args[2], args[3], args[4], args[5], outputDirectory) };
        }
    }

    private File[] getFilesFromDirectory(File src) {
        List<File> filesForConversion = new ArrayList<File>();
        for (File f : src.listFiles()) {
            if (!f.isDirectory()) {
                filesForConversion.add(f);
            }
        }
        return filesForConversion.toArray(new File[filesForConversion.size()]);
    }

    public static void main(String... args) throws ConverterNotFoundException, IOException {
        
        
        
        ConversionReport[] reports = new Main().runFromCommandLine(args);
        for (ConversionReport report : reports) {
            if (report.getReturnCode().equals(ConversionReport.ConversionCode.SUCCESS)) {
                LOGGER.info(report);
                System.out.println(report);
            } else {
                LOGGER.error(report);
                System.err.println(report);
                System.exit(1);
            }
        }
    }
}
