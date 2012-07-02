/**
 * Copyright Mango Solutions, 2012.
 * <p>SVN Entry: $HeadURL: $
 * <p>SVN ID: $Id: $
 * <p>Last edited by : $Author$
 * @author Tony Bamford &lt;tbamford@mango-solutions.com&gt;
 * @version $Revision$ as of on $Date$
 */
package com.mango.mif.exception;

public class MIFException extends Exception {

    private static final long serialVersionUID = -7508563859115466602L;

    public MIFException(String message, Throwable cause) {
        super(message, cause);
    }

    public MIFException(String message) {
        super(message);
    }

    public MIFException(Throwable cause) {
        super(cause);
    }

}
