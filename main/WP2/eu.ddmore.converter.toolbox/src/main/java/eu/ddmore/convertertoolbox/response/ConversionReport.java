/*******************************************************************************
 * Copyright (C) 2013 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.response;

import java.util.List;

/**
 * A report which contains the outcome of a conversion
 */
public interface ConversionReport {

    int getReturnCode();

    void setReturnCode(int returnCode);

    List<ConversionDetail> getDetails(int severity);

    void addDetail(ConversionDetail conversionDetail);

    String toString();
}
