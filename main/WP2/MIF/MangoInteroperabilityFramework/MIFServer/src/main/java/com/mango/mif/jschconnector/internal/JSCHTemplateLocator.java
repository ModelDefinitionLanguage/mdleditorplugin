/**
 * Copyright Mango Solutions, 2012.
 * <p>SVN Entry: $HeadURL: $
 * <p>SVN ID: $Id: $
 * <p>Last edited by : $Author$
 * @author Tony Bamford &lt;tbamford@mango-solutions.com&gt;
 * @version $Revision$ as of on $Date$
 */
package com.mango.mif.jschconnector.internal;

import java.io.File;

import com.mango.mif.core.jsch.JschTemplateLocator;
import com.mango.mif.exception.MIFException;


public class JSCHTemplateLocator implements JschTemplateLocator
{
    // "/spring/jschconnector/Execution-context.xml"

    private File commandTemplateDirectory;

    //    private static void initialise() throws MIFException {
    //        if (commandTemplateDirectory == null) {
    //            try {
    //                ApplicationContext ctx = new ClassPathXmlApplicationContext("/spring/jschconnector/Execution-context.xml");
    //                commandTemplateDirectory = (File) ctx.getBean("commandTemplateDirectory");
    //            } catch (Exception e) {
    //                throw new MIFException("Caught exception while trying to initialise command template directory bean", e);
    //            }
    //            if (commandTemplateDirectory == null) {
    //                // can this happen?
    //                throw new MIFException("Failed to initialise command template directory bean, although there were no exceptions");
    //            }
    //        }
    //    }

    public String locate(String templateName) throws MIFException {
        //        initialise();
        String path = commandTemplateDirectory.getAbsolutePath();
        File template = new File(path, templateName);

        if (!template.exists()) {
            throw new MIFException("Cannot locate template "
                    + templateName
                    + " because the path "
                    + template.getAbsolutePath()
                    + " does not exist");
        }
        if (!template.canRead()) {
            throw new MIFException("Cannot load template "
                    + templateName
                    + " because the path "
                    + template.getAbsolutePath()
                    + " (although it exists) cannot be read");
        }
        return template.getAbsolutePath();
    }

    //    public static void main(String args[]) {
    //        try {
    //            initialise();
    //        } catch (MIFException mifex) {
    //            System.out.print("Caught MIF Exception: ");
    //            System.out.println(mifex);
    //        }
    //        if (commandTemplateDirectory == null) {
    //            System.out.println("Failed to instantiate bean, (got null)");
    //        } else {
    //            System.out.println("command template directory is: " + commandTemplateDirectory.getAbsolutePath());
    //        }
    //    }

    // @VisibleForTesting
    public File getCommandTemplateDirectory() {
        return commandTemplateDirectory;
    }

    public void setCommandTemplateDirectory(File commandTemplateDirectory) {
        this.commandTemplateDirectory = commandTemplateDirectory;
    }
}
