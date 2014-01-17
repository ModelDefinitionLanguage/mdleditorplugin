/*******************************************************************************
 * Copyright (C) 2013 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.api.response;

import java.util.List;

import eu.ddmore.convertertoolbox.api.response.ConversionDetail.Severity;

/**
 * A report which contains the outcome of a conversion
 */
public interface ConversionReport {

	/**
	 * Models the conversion code result.
	 */
	public enum ConversionCode {
		SUCCESS, FAILURE;
	}
	
	/**
	 * @return the result associated with this ConversionReport
	 */
	ConversionCode getReturnCode();

	/**
	 * Sets the conversion code result.
	 * @param returnCode
	 */
    void setReturnCode(ConversionCode returnCode);

    /**
     * Get the list of ConversionDetails that match the input severity level.
     * @param severity the severity level
     * @return the list of ConversionDetails that match the input severity level.
     */
    List<ConversionDetail> getDetails(Severity severity);

    /**
     * Adds a ConversionDetail to this ConversionReport
     * @param conversionDetail the ConversionDetail to add
     */
    void addDetail(ConversionDetail conversionDetail);
}
