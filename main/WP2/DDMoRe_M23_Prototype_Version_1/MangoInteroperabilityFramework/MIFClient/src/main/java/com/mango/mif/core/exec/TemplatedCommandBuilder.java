/**
 * Copyright Mango Solutions, 2012.
 * <p>SVN Entry: $HeadURL: $
 * <p>SVN ID: $Id: $
 * <p>Last edited by : $Author$
 * @author Tony Bamford &lt;tbamford@mango-solutions.com&gt;
 * @version $Revision$ as of on $Date$
 */
package com.mango.mif.core.exec;

import com.google.common.base.Preconditions;
import com.mango.mif.core.exec.template.FreemarkerTemplateLocator;

/**
 * Introduce the concept of a template into a command builder
 */
public abstract class TemplatedCommandBuilder extends CommandBuilder {

    /**
     * Command template
     */
    protected String template;

    /**
     * Command template locator
     */
    protected FreemarkerTemplateLocator templateLocator;

    /**
     * @param template the template to set
     */
    public void setTemplate(String template) {
        this.template = template;
    }

    /**
     * @return the template name
     */
    public String getTemplate() {
        return this.template;
    }

    /**
     * Sets the template locator.
     *
     * @param templateLocator the new template locator
     */
    public void setTemplateLocator(FreemarkerTemplateLocator templateLocator) {
        this.templateLocator = templateLocator;
    }

    /**
     * @return the current template locator.
     */
    public FreemarkerTemplateLocator getTemplateLocator() {
        return this.templateLocator;
    }

    /**
     * @return The location given by the template locator of the specified template.
     * @throws ExecutionException if template location fails
     */
    public String getLocation() throws ExecutionException {
        Preconditions.checkNotNull(template, "Cannot get the location of a null template");
        Preconditions.checkNotNull(templateLocator, "Cannot get the location with a null template locator");

        return templateLocator.locate(template);
    }

    /**
     * Get some kind of path for debugging purposes.  Basically this is a cut down getLocation
     * but without the tedious exceptions.
     * @return The full path of the template, if we can, otherwise the template name.
     */
    public String getPath() {
        String result = template;

        try {
            result = getLocation();
        } catch (ExecutionException ignored) {
        }
        return result;
    }
}
