/*******************************************************************************
 * Copyright (C) 2013 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.domain;

import java.util.ArrayList;
import java.util.List;

import eu.ddmore.convertertoolbox.api.response.ConversionDetail.Severity;
import eu.ddmore.convertertoolbox.api.response.ConversionReport.ConversionCode;

/**
 * A report which contains the outcome of a conversion
 */
public final class ConversionReport {

    private ConversionCode returnCode;
    private List<ConversionDetail> details;

    public ConversionReport() {
        details = new ArrayList<ConversionDetail>();
    }

    public ConversionCode getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(ConversionCode returnCode) {
        this.returnCode = returnCode;
    }

    public List<ConversionDetail> getDetails(Severity severity) {
        if (severity == Severity.ALL) {
            return details;
        }
        List<ConversionDetail> res = new ArrayList<ConversionDetail>();
        for (ConversionDetail d : details) {
            if (d.getServerity().getRank() <= severity.getRank()) {
                res.add(d);
            }
        }
        return res;
    }

    public void addDetail(ConversionDetail conversionDetail) {
        details.add(conversionDetail);
    }

    @Override
    public String toString() {
        return String.format("ConversionReportImpl [returnCode=%s, details=%s]", returnCode, details);
    }
    
    public eu.ddmore.convertertoolbox.api.response.ConversionReport toOldAPI() {
        ConversionReportImpl result = new ConversionReportImpl();
        result.setReturnCode(returnCode);
        for(ConversionDetail conversionDetail : details) {
            result.addDetail(conversionDetail.toOldAPI());
        }
        return result;
    }
    
    public static ConversionReport fromOldAPI(eu.ddmore.convertertoolbox.api.response.ConversionReport conversionReport) {
        ConversionReport result = new ConversionReport();
        result.setReturnCode(conversionReport.getReturnCode());
        for(eu.ddmore.convertertoolbox.api.response.ConversionDetail conversionDetail: conversionReport.getDetails(Severity.ALL)) {
            result.addDetail(ConversionDetail.fromOldAPI(conversionDetail));
        }
        return result;
    }
}
