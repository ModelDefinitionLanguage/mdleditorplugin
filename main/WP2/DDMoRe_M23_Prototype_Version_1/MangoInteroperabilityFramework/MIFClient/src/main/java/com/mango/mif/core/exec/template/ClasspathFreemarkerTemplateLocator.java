/* File			:  ClasspathFreemarkerTemplateLocator.java
 * Project		:  MIFServer
 * Created on	:  02 Jul 2012
 */
package com.mango.mif.core.exec.template;

import java.io.File;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.google.common.base.Preconditions;
import com.mango.mif.core.exec.ExecutionException;


/**
 * 
 * Template locator that locates templates on the classpath
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 *
 */
public class ClasspathFreemarkerTemplateLocator implements FreemarkerTemplateLocator {
    /**
     * A folder containing templates
     */
    private String commandTemplateDirectory = "";

    @Override
    public String locate(String name) throws ExecutionException {
        Preconditions.checkNotNull(name, "Cannot locate template name of null");
        String templateName = commandTemplateDirectory + name;
        URL templateUrl = ClasspathFreemarkerTemplateLocator.class.getResource(templateName);
        if (templateUrl == null) {
            throw new ExecutionException("Failed to locate template " + templateName + " on classpath");
        }
        File template = FileUtils.toFile(templateUrl);
        return template.getAbsolutePath();
    }


    public String getCommandTemplateDirectory() {
        return commandTemplateDirectory;
    }

    public void setCommandTemplateDirectory(String commandTemplateDirectory) {
        this.commandTemplateDirectory = commandTemplateDirectory;
        if (StringUtils.isNotBlank(commandTemplateDirectory) && !commandTemplateDirectory.endsWith("/")) {
            this.commandTemplateDirectory = this.commandTemplateDirectory + "/";
        }
    }
}
