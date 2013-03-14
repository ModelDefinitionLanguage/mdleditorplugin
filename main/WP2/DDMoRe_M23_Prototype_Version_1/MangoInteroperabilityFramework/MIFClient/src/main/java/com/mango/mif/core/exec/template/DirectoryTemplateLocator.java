/**
 * Copyright Mango Solutions, 2012.
 * <p>SVN Entry: $HeadURL: $
 * <p>SVN ID: $Id: $
 * <p>Last edited by : $Author$
 * @author Tony Bamford &lt;tbamford@mango-solutions.com&gt;
 * @version $Revision$ as of on $Date$
 */
package com.mango.mif.core.exec.template;

import java.io.File;

import org.apache.log4j.Logger;

import com.mango.mif.core.exec.ExecutionException;
/**
 * A directory based template locator implementation
 * @author mrogalski
 *
 */
public class DirectoryTemplateLocator implements FreemarkerTemplateLocator {
	/**
	 * Logger
	 */
	private final static Logger LOG = Logger.getLogger(DirectoryTemplateLocator.class);
    /**
     * A folder containing templates
     */
    private File	commandTemplateDirectory;

    @Override
    public String locate(String templateName) throws ExecutionException {
        String path = commandTemplateDirectory.getAbsolutePath();
        File template = new File(path, templateName);

        if (!template.exists()) {
            throw new ExecutionException("Cannot locate template "
                    + templateName
                    + " because the path "
                    + template.getAbsolutePath()
                    + " does not exist");
        }
        if (!template.canRead()) {
            throw new ExecutionException("Cannot load template "
                    + templateName
                    + " because the path "
                    + template.getAbsolutePath()
                    + " (although it exists) cannot be read");
        }
        return template.getAbsolutePath();
    }

    public File getCommandTemplateDirectory() {
        return commandTemplateDirectory;
    }

    public void setCommandTemplateDirectory(File commandTemplateDirectory) {
    	LOG.info("templates directory " + commandTemplateDirectory.getAbsolutePath());
        this.commandTemplateDirectory = commandTemplateDirectory;
    }
}
