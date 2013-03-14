/**
 * Copyright Mango Solutions, 2012.
 * <p>CVS Entry : $Header$
 * <p>Last edited by : $Author$
 * @author Tony Bamford &lt;tbamford@mango-solutions.com&gt;
 * @version $Revision$ as of on $Date$
 */
package com.mango.mif.core.exec.jsch;

import com.mango.mif.core.exec.InvokerResult;

/**
 * Not really very much for this class to do (except be concrete), since all the common code was shoved into InvokerResult.
 * This is being kept just in case there really are differences between commons exec and JSCH.
 */
public class JschInvokerResult extends InvokerResult {

    public JschInvokerResult(String command, String stdout, String stderr, int exitCode) {
        super(command, stdout, stderr, exitCode);
    }
}
