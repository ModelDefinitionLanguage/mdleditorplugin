/**
 * Copyright Mango Solutions, 2012.
 * <p>CVS Entry : $Header$
 * <p>Last edited by : $Author$
 * @author Tony Bamford &lt;tbamford@mango-solutions.com&gt;
 * @version $Revision$ as of on $Date$
 */
package com.mango.mif.core.jsch;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mango.mif.exception.MIFException;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


/**
 * Find a template using a template locator.  Expand it and execute it.
 * 
 * The time expired cache saves us searching for templates too often.
 */
public class JschTemplatedCommand {

    private static Logger logger = Logger.getLogger(JschTemplatedCommand.class);

    private final LoadingCache<String, Template> timedCache;

    private String templateName;
    private Map<String, Object> context;
    private JschTemplateLocator locator;

    /**
     * Create the Freemarker context (this is basically a hashmap which Freemarker will use to expand the parameters
     * used by any particular template).
     * @param templateName The template we are initialising with - won't be validated until later.
     */
    public JschTemplatedCommand(String templateName, JschTemplateLocator locator) {
        this.context = new HashMap<String, Object>();
        this.templateName = templateName;
        this.locator = locator;

        this.timedCache = CacheBuilder.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build(
                        new CacheLoader<String, Template>() {
                            public Template load(String key) throws Exception {
                                return loadTemplate(key);
                            }
                        });
    }

    public void addParam(String name, Object value) {
        context.put(name, value);
    }
    /**
     * Resolve the command, substituting in all the parameters and squashing everything onto one line.
     * The incoming template may contain multiple lines - this for comments and readability.  We crush
     * out blank lines and glue everything together.
     * @return single string with parameters substituted.
     * @throws SmeeException when a template can't be found
     */
    public String resolveCommand() throws MIFException {

        String fullPath = locator.locate(templateName);

        try {
            Template t = null;

            // First look to see if we have the resource cached.  If not, then get the resource and
            // cache it for next time.  The time expired cache is just so we have the opportunity to
            // change what is in the cache and not have to restart Tomcat etc.
            //
            t = timedCache.get(fullPath);
            if (t == null) {
                FileTemplateLoader ftl = new FileTemplateLoader(new File(fullPath).getParentFile());
                Configuration freeMarkerConfig = new Configuration();
                freeMarkerConfig.setTemplateLoader(ftl);
                t = freeMarkerConfig.getTemplate(new File(fullPath).getName());
                timedCache.put(fullPath, t);
            }
            StringWriter writer = new StringWriter();
            t.process(context, writer);

            // removing empty lines
            StringBuilder sb = new StringBuilder();
            Scanner scanner = new Scanner(writer.toString()).useDelimiter("\n");
            while (scanner.hasNext()) {
                String line = scanner.nextLine().trim();
                if (StringUtils.isNotBlank(line)) {
                    sb.append(line);
                    sb.append("\n");
                }
            }
            scanner.close();
            return sb.toString().trim();
        } catch (TemplateException e) {
            logger.error("Caught template exception for template " + templateName + " (" + fullPath + "): ", e);
            throw new MIFException("Caught template exception for template " + templateName + " (" + fullPath + "): ", e);
        } catch (IOException e) {
            logger.error("Caught I/O exception for template " + templateName + " (" + fullPath + "): ", e);
            throw new MIFException("Caught I/O exception for template " + templateName + " (" + fullPath + "): ", e);
        } catch (ExecutionException e) {
            logger.error("Caught Exection exception for template " + templateName + " (" + fullPath + "): ", e);
            throw new MIFException("Caught Execution exception for template " + templateName + " (" + fullPath + "): ", e);
        }
    }

    /**
     * Load a template.
     * @param fullPath the full path of the template.
     * @return The loaded template
     * @throws Exception if anything goes wrong
     */
    private Template loadTemplate(String fullPath) throws Exception {
        FileTemplateLoader ftl = new FileTemplateLoader(new File(fullPath).getParentFile());
        Configuration freeMarkerConfig = new Configuration();
        freeMarkerConfig.setTemplateLoader(ftl);
        return freeMarkerConfig.getTemplate(new File(fullPath).getName());
    }

    /**
     * Put as much helpful information into the generated string as we can.
     */
    @Override
    public String toString() {

        String resolved;
        try {
            resolved = "Template: [" + templateName + "(" + locator.locate(templateName) + ")] Command: [" + resolveCommand() + "]";
        } catch (Exception ignored) {
            try {
                resolved = "Template: [" + templateName + "(" + locator.locate(templateName) + ")] Command: [could not be resolved]";
            } catch (Exception alsoIgnored) {
                resolved = "Template: [" + templateName + " (could not be located)]";
            }
        }

        return resolved;
    }
}
