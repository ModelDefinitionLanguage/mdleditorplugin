/**
 * Copyright Mango Solutions, 2012.
 * <p>SVN Entry: $HeadURL: $
 * <p>SVN ID: $Id: $
 * <p>Last edited by : $Author$
 * @author Tony Bamford &lt;tbamford@mango-solutions.com&gt;
 * @version $Revision$ as of on $Date$
 */
package com.mango.mif.core.jsch;

import com.mango.mif.exception.MIFException;

public interface JschTemplateLocator {

    /**
     * Given a template name, map that to the absolute pathname of the file which contains the template
     * in any way you want, although this will invariably be by way of grabbing a path from a property file
     * and prepending it to the template name.
     *
     * @param name The template name
     * @return The full path of the file containing the named template.
     * @throws MIFException if anything goes wrong
     */
    String locate(String name) throws MIFException;
}
