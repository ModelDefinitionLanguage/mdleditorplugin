/* File			:  FreemarkerTemplateCommandBuilder.java
 * Project		:  MIFServer
 * Created on	:  13 Jun 2012
 */
package com.mango.mif.core.exec.template;


import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.mango.mif.client.domain.ExecutionRequestAttributeName;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.TemplatedCommandBuilder;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * A command builder that uses Freemarker to build up commands
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */

/**
 * @author mrogalski
 */
public class FreemarkerTemplateCommandBuilder extends TemplatedCommandBuilder {

    /**
     * Log
     */
    private final static Logger LOG = Logger.getLogger(FreemarkerTemplateCommandBuilder.class);

    /**
     * This is the freemarker execution context.  This is for shoving objects into which will be available
     * when the template is expanded.  By the way, they should be unwrapped objects - they get wrapped on
     * addition to the context.
     */
    private Map<String, Object> context = Maps.newHashMap();

    /**
     * Creates a freemarker template command builder from nothing.
     * 
     * As you will see elsewhere (in the CommandBuilder class, for example) there are some variables
     * (like SHELL) which we just absolutely, utterly need.  What we do here is to provide default
     * fallback values for these variables.
     *
     * These values can be overridden by Spring (which effectively they will be in the CTOR below,
     * where the map values come directly from Spring) or they can come from request attributes
     * which are, confusingly, handled in the parent class.
     */
    public FreemarkerTemplateCommandBuilder() throws ExecutionException {
        for (ExecutionRequestAttributeName attr : ExecutionRequestAttributeName.values()) {
            String name = attr.getName();
            String value = attr.getDefaultValue();

            setVariable(name, value);
        }

        // This is just for the test harnesses really...
        setGridHostPreamble("");
        setSubmitHostPreamble("");
    }

    /**
     * Creates a command builder with the given initial context
     * @param initialContext
     * @throws ExecutionException
     */
    public FreemarkerTemplateCommandBuilder(Map<String, Object> initialContext) throws ExecutionException {
        this();
        for(Entry<String,Object> en : initialContext.entrySet()) {
            setVariable(en.getKey(), en.getValue());
        }
    }

    /**
     * Expand the template into a command.
     */
    @Override
    public String getCommand() throws ExecutionException {
        Preconditions.checkNotNull(template, "Command template is not set.");
        Preconditions.checkNotNull(templateLocator, "Template locator is not set.");

        FreemarkerTemplateCache templateCache = FreemarkerTemplateCache.getInstance();

        String fullPath = getLocation();
        if (StringUtils.isBlank(fullPath)) {
            throw new ExecutionException("The template locator could not resolve the template path " + template);
        }
        Template template = templateCache.get(fullPath);
        if (template == null) {
            throw new ExecutionException("Unable to retrieve template for path " + fullPath);
        }
        StringWriter writer = new StringWriter();

        try {
            template.process(context, writer);
        } catch (IOException ioe) {
            throw new ExecutionException("Caught IO exception while processing template " + getPath(), ioe);
        } catch (TemplateException te) {
            throw new ExecutionException("Caught TemplateException while processing template " + getPath(), te);
        }
        return writer.toString();
    }

    /**
     * Add an entry to the map which will be used to populate the freemarker context.
     * Note that WE DO THE WRAPPING HERE.  You do NOT need to wrap the object before
     * calling this function.
     * 
     * @param key The key
     * @param unwrappedValue The unwrapped object.
     * @throws ExecutionException if we fail to wrap the object for some reason
     */
    @Override
    public void setVariable(String key, Object unwrappedValue) throws ExecutionException {

        // If someone tries to put null into our context, we change it to an empty string.
        if (unwrappedValue == null) {
            unwrappedValue = new String();
        }

        BeansWrapper beansWrapper = BeansWrapper.getDefaultInstance();
        beansWrapper.setExposureLevel(BeansWrapper.EXPOSE_SAFE);
        TemplateModel model;
        try {
            model = beansWrapper.wrap(unwrappedValue);
        } catch (TemplateModelException e) {
            LOG.error(e);
            throw new ExecutionException("Failed to wrap object " + unwrappedValue.getClass().getCanonicalName() + " for freemarker", e);
        }
        context.put(key, model);
    }
    
    @Override
    public Object getVariable(String key) {
        return context.get(key);
    }

    /**
     * Put as much helpful information into the generated string as we can.
     */
    @Override
    public String toString() {

        String resolved;
        try {
            resolved = "Template: [" + template + "(" + templateLocator.locate(template) + ")] Command: [" + getCommand() + "]";
        } catch (Exception ignored) {
            try {
                resolved = "Template: [" + template + "(" + templateLocator.locate(template) + ")] Command: [could not be resolved]";
            } catch (Exception alsoIgnored) {
                resolved = "Template: [" + template + " (could not be located)]";
            }
        }
        return resolved;
    }
}
