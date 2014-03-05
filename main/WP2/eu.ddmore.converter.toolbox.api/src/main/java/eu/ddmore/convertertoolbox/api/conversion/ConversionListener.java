/*******************************************************************************
 * Copyright (C) 2013 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.api.conversion;

import eu.ddmore.convertertoolbox.api.response.ConversionReport;

/**
 * Listener which clients can implement in order to receive callbacks
 */
public interface ConversionListener {

    /**
     * Called when conversion processing completes (failure or success).
     * @param report the details of the conversion
     */
    void conversionComplete(ConversionReport report);
    
}
