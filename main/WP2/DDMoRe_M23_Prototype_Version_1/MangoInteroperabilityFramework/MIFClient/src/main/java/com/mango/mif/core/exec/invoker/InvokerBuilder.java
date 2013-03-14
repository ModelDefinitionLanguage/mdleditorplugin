/**
 * Copyright Mango Solutions, 2012.
 * <p>CVS Entry : $Header$
 * <p>Last edited by : $Author$
 * @version $Revision$ as of on $Date$
 */
package com.mango.mif.core.exec.invoker;

import com.mango.mif.core.exec.Invoker;

/**
 * Interface for building invokers.
 */
public interface InvokerBuilder {

    Invoker buildInvoker();
}
