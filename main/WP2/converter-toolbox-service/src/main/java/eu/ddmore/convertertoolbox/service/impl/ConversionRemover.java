/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.service.impl;

import eu.ddmore.convertertoolbox.domain.internal.Conversion;

/**
 * Component responsible for removing { @link Conversion } and their associated resources
 */
public interface ConversionRemover {
    /**
     * Removes conversion
     * @param conversion
     */
    void remove(Conversion conversion);
}
