/**
 * Copyright Mango Solutions, 2012.
 * <p>CVS Entry : $Header$
 * <p>Last edited by : $Author$
 * @version $Revision$ as of on $Date$
 */
package com.mango.mif.core.exec.ce;

import com.mango.mif.core.exec.InvokerResult;

/**
 * Not really very much for this class to do (except be concrete), since all the common code was shoved into InvokerResult.
 * This is being kept just in case there really are differences between commons exec and JSCH.
 */
public class CeInvokerResult extends InvokerResult {

    public CeInvokerResult(String command, String stdout, String stderr, int exitCode) {
        super(command, stdout, stderr, exitCode);
    }
}
