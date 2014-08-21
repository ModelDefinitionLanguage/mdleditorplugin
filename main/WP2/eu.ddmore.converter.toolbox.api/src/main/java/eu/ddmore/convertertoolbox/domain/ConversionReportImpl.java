/*******************************************************************************
 * Copyright (C) 2013 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.domain;

import java.util.ArrayList;
import java.util.List;

import eu.ddmore.convertertoolbox.api.response.ConversionDetail;
import eu.ddmore.convertertoolbox.api.response.ConversionDetail.Severity;
import eu.ddmore.convertertoolbox.api.response.ConversionReport;

/**
 * A report which contains the outcome of a conversion
 */
public final class ConversionReportImpl implements ConversionReport {

    private ConversionCode returnCode;
    private List<ConversionDetail> details;

    public ConversionReportImpl() {
        details = new ArrayList<ConversionDetail>();
    }

    @Override
    public ConversionCode getReturnCode() {
        return returnCode;
    }

    @Override
    public void setReturnCode(ConversionCode returnCode) {
        this.returnCode = returnCode;
    }

    @Override
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

    @Override
    public void addDetail(ConversionDetail conversionDetail) {
        details.add(conversionDetail);
    }

    @Override
    public String toString() {
        return String.format("ConversionReportImpl [returnCode=%s, details=%s]", returnCode, details);
    }

}
