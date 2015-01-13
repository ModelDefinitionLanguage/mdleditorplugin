/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.service.impl.conversion;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;

import eu.ddmore.convertertoolbox.domain.ConversionDetail;
import eu.ddmore.convertertoolbox.domain.ConversionDetailSeverity;
import eu.ddmore.convertertoolbox.domain.ConversionReport;
import eu.ddmore.convertertoolbox.domain.ConversionReportOutcomeCode;
import eu.ddmore.convertertoolbox.domain.ConversionStatus;
import eu.ddmore.convertertoolbox.domain.internal.Conversion;

/**
 * Represents a conversion being scheduled and ready to be assigned a working thread to perform conversion
 */
public class ConversionTask implements Runnable {
    private final Logger LOG = Logger.getLogger(ConversionTask.class);
    private final ConversionContext conversionContext;
    private List<ConversionStep> conversionSteps;
    /**
     * Creates a new instance of conversion task
     * @param conversionContext
     */
    public ConversionTask(ConversionContext conversionContext) {
        super();
        Preconditions.checkNotNull(conversionContext, "Converter context can't be null");
        this.conversionContext = conversionContext;
    }
    
    @Override
    public void run() {
        LOG.debug(String.format("Conversion [%s] Executing conversion from %s to %s using converter %s", 
            conversionContext.getConversion().getId(), conversionContext.getConversion().getFrom(), 
            conversionContext.getConversion().getTo(), conversionContext.getConverter().toString()));
        try {
            main();
        } catch(Exception ex) {
            LOG.error(String.format("Conversion [%s] Exception was thrown during conversion execution",conversionContext.getConversion().getId()), ex);
            conversionContext.getConversion().setConversionReport(generateFailedProcessingErrorReport(ex));
        } finally {
            Conversion conversion = conversionContext.getConversion();
            conversion.setCompletionTime(new Date().getTime());
            conversion.setStatus(ConversionStatus.Completed);
            conversionContext.updateConversion(conversion);
        }
    }

    private void main() {
        Preconditions.checkNotNull(conversionSteps,"Conversion steps list was null");
        Conversion conversion = conversionContext.getConversion();
        conversion.setStatus(ConversionStatus.Running);
        conversionContext.updateConversion(conversion);
        for(ConversionStep step : conversionSteps) {
            step.execute(conversionContext);
        }
    }
    
    @VisibleForTesting
    ConversionReport generateFailedProcessingErrorReport(Exception exception) {
        ConversionReport conversionReport = new ConversionReport();
        conversionReport.setReturnCode(ConversionReportOutcomeCode.FAILURE);
        
        ConversionDetail conversionDetail = new ConversionDetail();
        conversionDetail.setMessage(exception.getMessage());
        conversionDetail.setSeverity(ConversionDetailSeverity.ERROR);
        conversionReport.addDetail(conversionDetail);
        if(exception.getCause()!=null && exception.getCause().getMessage()!=null) {
            conversionDetail.addInfo("error", exception.getCause().getMessage());
        }
        return conversionReport;
    }
    
    
    public void setConversionSteps(List<ConversionStep> conversionSteps) {
        this.conversionSteps = conversionSteps;
    }
}
