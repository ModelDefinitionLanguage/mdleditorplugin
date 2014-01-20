/*******************************************************************************
 * Copyright (C) 2013 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.cli;

import java.io.File;
import java.io.IOException;

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
public class Main {

    private static ConverterManager converterManager;

    public static ConversionReport convert(File src, String srcLanguage, String srcVersion, String targetLanguage, String targetVersion,
            File outputDirectory) throws ConverterNotFoundException, IOException {
        LanguageVersion source = getLanguageVersion(srcLanguage, srcVersion);
        LanguageVersion target = getLanguageVersion(targetLanguage, targetVersion);

        Converter converter = converterManager.getConverter(source, target);
        return converter.convert(src, outputDirectory);
    }

    public static ConversionReport[] convert(File[] src, String srcLanguage, String srcVersion, String targetLanguage, String targetVersion,
            File outputDirectory) throws ConverterNotFoundException, IOException {
        LanguageVersion source = getLanguageVersion(srcLanguage, srcVersion);
        LanguageVersion target = getLanguageVersion(targetLanguage, targetVersion);

        Converter converter = converterManager.getConverter(source, target);
        return converter.convert(src, outputDirectory);
    }

    private static LanguageVersion getLanguageVersion(String language, String version) {
        Version sourceVersion = new VersionImpl();

        LanguageVersion langVer = new LanguageVersionImpl();
        langVer.setLanguage(language);
        String versionAsArray[] = version.split("-");
        if (versionAsArray.length > 1) {
            sourceVersion.setQualifier(versionAsArray[1]);
        }
        String versionNumbers = versionAsArray[0];
        String versionNumbersAsArray[] = versionNumbers.split("\\.");
        if (versionNumbersAsArray.length == 0) {
            throw new IllegalArgumentException(
                    "The language version should contain at least one number, e.g. '1' is interpreted into '1.0.0', '2.5' is interpreted into '2.5.0'.");
        } else if (versionNumbersAsArray.length > 3) {
            throw new IllegalArgumentException(
                    "The language version should contain at most thre numbers according to the 'Major.Minor.Patch' naming convention.");
        }
        sourceVersion.setMajor(Integer.parseInt(versionNumbersAsArray[0]));
        if (versionNumbersAsArray.length > 1) {
            sourceVersion.setMinor(Integer.parseInt(versionNumbersAsArray[1]));
        }
        if (versionNumbersAsArray.length > 2) {
            sourceVersion.setPatch(Integer.parseInt(versionNumbersAsArray[2]));
        }
        langVer.setVersion(sourceVersion);
        return langVer;
    }

    public static void main(String... args) throws ConverterNotFoundException, IOException {
        if (args.length != 6) {
            System.out.println("Illegal arguments. Run again by giving the arguments in the following format: 'sourcePath outputPath sourceLanguage sourceVersion targetLanguage targetVersion', e.g. 'myMDLFile.mdl C:/output/ MDL 5.0.8 NONMEM 7.2.0'");
        }
        converterManager = new ConverterManagerImpl();
        File src = new File(args[0]);
        File outputDirectory = new File(args[1]);
        if (src.isDirectory()) {
            convert(src.listFiles(), args[2], args[3], args[4], args[5], outputDirectory);
        } else {
            convert(src, args[2], args[3], args[4], args[5], outputDirectory);
        }
    }
}
