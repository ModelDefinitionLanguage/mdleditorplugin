/*******************************************************************************
 * Copyright (C) 2014 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json

import java.io.IOException;
import java.text.SimpleDateFormat

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger
import org.ddmore.mdl.mdl.Mcl

import eu.ddmore.mdlparse.MdlParser
import eu.ddmore.converter.mdl2json.domain.MCLFile
import eu.ddmore.converter.mdl2json.domain.MCLFile
import eu.ddmore.convertertoolbox.api.domain.LanguageVersion;
import eu.ddmore.convertertoolbox.api.domain.Version;
import eu.ddmore.convertertoolbox.api.response.ConversionReport;
import eu.ddmore.convertertoolbox.api.response.ConversionReport.ConversionCode;
import eu.ddmore.convertertoolbox.api.spi.ConverterProvider;
import eu.ddmore.convertertoolbox.domain.LanguageVersionImpl
import eu.ddmore.convertertoolbox.domain.VersionImpl
import eu.ddmore.convertertoolbox.response.ConversionReportImpl
import eu.ddmore.libpharmml.PharmMlFactory
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper


/**
 * This is a ConverterProvider implementation from PharmML to NMTRAN, specified versions thereof.
 */
public class JSONToMDLConverter implements ConverterProvider {

	private static Logger logger = Logger.getLogger(this.getClass());
	
	private static final String MDL_FILE_EXTENSION = ".mdl"
	private static final String JSON_FILE_EXTENSION = ".json"	
	
	private final LanguageVersion source = new LanguageVersionImpl("JSON", new VersionImpl(0, 0, 0))
	private final LanguageVersion target = new LanguageVersionImpl("MDL", new VersionImpl(5, 1, 6))
    private final Version converterVersion = new VersionImpl(1, 0, 0);

	private String mdl 
	
	/**
	 * Convert an JSON file into MDL
	 * 
	 * Accepts the JSON file as first argument, and optionally the output folder for the second argument
	 * Default output folder is the same as the input JSON file
	 *  
	 * @param args
	 */
	static main(args) {
	
		String mdlFile = args[0]
		File inputFile = new File(mdlFile)
		
		if(inputFile==null) {
			println("Cannot open file " + mdlFile)
			System.exit(0)
		}

		String outputDirectory = inputFile.getAbsoluteFile().getParent()
		if(args.size() > 1 ) {
			outputDirectory = args[1]
		}
		println "Writing mdl to " + outputDirectory
		
		File outputDir = new File(outputDirectory)
		
		ConverterProvider converter = new MDLToJSONConverter()
		converter.performConvert(inputFile, outputDir)	
		
		println(converter.mdl)
	}

	/**
	 * Converter toolbox required entry point
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
        int dotIndex = name.lastIndexOf(MDL_FILE_EXTENSION)

		if (dotIndex == -1) {
            return name + JSON_FILE_EXTENSION
        } else {
            return name.substring(0, dotIndex) + JSON_FILE_EXTENSION
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
        return String.format("MDLTOJSONConverter [source=%s, target=%s, converterVersion=%s]", source, target, converterVersion) ;
    }
}