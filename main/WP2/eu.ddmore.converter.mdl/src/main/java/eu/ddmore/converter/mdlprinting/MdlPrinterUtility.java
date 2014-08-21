/*******************************************************************************
 * Copyright (C) 2013 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdlprinting;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import eu.ddmore.convertertoolbox.api.response.ConversionReport;
import eu.ddmore.convertertoolbox.api.response.ConversionReport.ConversionCode;
import eu.ddmore.convertertoolbox.domain.ConversionReportImpl;

/**
 * This class is created to model the common behaviour of eu.ddmore.converter.mdl2pharmml.MDLToPharmMLConverter and 
 * eu.ddmore.converter.mdl2nonmem.MDLToNonmemConverter. 
 * I did not want to put that inside MdlPrinting, because that's xtend generated code and will be updated in the future.
 * 
 * @author itheocharis
 *
 */
public class MdlPrinterUtility {
	
	public ConversionReport printOutputFile(File src, File outputDirectory, String text, String fileEnding) {
		String outputFileName = src.getName().replace(".mdl", fileEnding);
		File outputFile = new File(outputDirectory.getAbsolutePath() +'/'+ outputFileName);

		ConversionReport report = new ConversionReportImpl();
		
		//TODO: There is no way to get this info from the xtend genertated code.
		report.setReturnCode(ConversionCode.SUCCESS);
		try {
			FileUtils.writeStringToFile(outputFile, text);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return report;
	}

}
