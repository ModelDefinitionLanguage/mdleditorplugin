/*******************************************************************************
 * Copyright (C) 2002 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.service.impl.conversion;

import java.io.File;

import org.apache.log4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;

import eu.ddmore.convertertoolbox.api.conversion.Converter;
import eu.ddmore.convertertoolbox.domain.Conversion;
import eu.ddmore.convertertoolbox.domain.ConversionReport;
import eu.ddmore.convertertoolbox.service.impl.ConversionResourcesConvention;

/**
 * {@link ConversionStep} implementation responsible for executing a { @link Converter } 
 */
@Order(2)
@Component
public class ExecuteConversionStep implements ConversionStep {
    private static final Logger LOG = Logger.getLogger(ProcessOutputsStep.class);
    @Override
    public void execute(ConversionContext conversionContext) {
        Preconditions.checkNotNull(conversionContext, "Conversion context was null");
        Preconditions.checkNotNull(conversionContext.getConversion(), "Conversion in Conversion Context was null");
        Preconditions.checkNotNull(conversionContext.getConverter(), "Converter in Conversion Context was null");
        Conversion conversion = conversionContext.getConversion();
        File inputFile = new File(new File(conversion.getWorkingDirectory(),ConversionResourcesConvention.INPUTS_DIRECTORY_NAME),conversion.getInputFileName());
        Preconditions.checkState(inputFile.exists(), String.format("Conversion [%s] input file %s does not exist", conversion.getId(), inputFile.getAbsolutePath()));
        
        
        Converter converter = conversionContext.getConverter();
        eu.ddmore.convertertoolbox.api.response.ConversionReport conversionReport = null;
        try {
            File outputDirectory = new File(conversion.getWorkingDirectory(),ConversionResourcesConvention.OUTPUTS_DIRECTORY_NAME);
            outputDirectory.mkdirs();
            LOG.debug(String.format("Conversion [%s] Executing conversion of %s with output directory %s",conversion.getId(), inputFile.getAbsolutePath(), outputDirectory.getAbsolutePath() ));
            conversionReport = converter.convert(inputFile, outputDirectory);
        } catch (Exception e) {
            throw new RuntimeException("Converter execution failed with unexpected error.",e);
        }
        Preconditions.checkNotNull(conversionReport, "Converter did not return conversion");
        
        conversion.setConversionReport(ConversionReport.fromOldAPI(conversionReport));
    }

}
