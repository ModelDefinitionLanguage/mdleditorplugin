/* File			:  GenericShellBasedJobRunner.java
 * Project		:  MIFServer
 * Created on	:  29 Jun 2012
 */
package com.mango.mif.connector.runner.impl;

import com.mango.mif.core.exec.Invoker;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 * Job Runner implementation that uses shell based state handlers for execution
 */
public class GenericShellBasedJobRunner extends DefaultJobRunner {
    /**
     * invoker
     */
    private Invoker invoker;

    /**
     * @param invoker the invoker to set
     */
    public void setInvoker(Invoker invoker) {
        this.invoker = invoker;
    }

    /**
     * @return the invoker
     */
    public Invoker getInvoker() {
        return invoker;
    }
}
