/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * REST-friendly implementation of { @link ConversionReportImpl }
 */
public final class ConversionReport {

    private ConversionReportOutcomeCode returnCode;
    private List<ConversionDetail> details;

    public ConversionReport() {
        details = new ArrayList<ConversionDetail>();
    }

    public ConversionReportOutcomeCode getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(ConversionReportOutcomeCode returnCode) {
        this.returnCode = returnCode;
    }

    public List<ConversionDetail> getDetails() {
        List<ConversionDetail> result = new ArrayList<ConversionDetail>();
        result.addAll(details);
        return result;
    }

    public void addDetail(ConversionDetail conversionDetail) {
        details.add(conversionDetail);
    }

    @Override
    public String toString() {
        return String.format("ConversionReportImpl [returnCode=%s, details=%s]", returnCode, details);
    }
}
