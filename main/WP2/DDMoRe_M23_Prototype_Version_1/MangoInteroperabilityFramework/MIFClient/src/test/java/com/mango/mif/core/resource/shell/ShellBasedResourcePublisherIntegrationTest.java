/**
 * Copyright Mango Solutions, 2012.
 * <p>SVN Entry: $HeadURL: $
 * <p>SVN ID: $Id: $
 * <p>Last edited by : $Author$
 * @author Tony Bamford &lt;tbamford@mango-solutions.com&gt;
 * @version $Revision$ as of on $Date$
 */
package com.mango.mif.core.resource.shell;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mango.mif.connector.TestsHelper;
import com.mango.mif.core.exec.Invoker;
import com.mango.mif.core.exec.invoker.InvokerFactory;
import com.mango.mif.core.exec.template.DirectoryTemplateLocator;
import com.mango.mif.core.exec.template.FreemarkerTemplateCommandBuilder;
import com.mango.mif.core.impl.DefaultWorkspaceManager;
import com.mango.mif.core.resource.UserSharedLocationManager;
import com.mango.mif.exception.MIFException;
import com.mango.mif.utils.TestProperties;

public class ShellBasedResourcePublisherIntegrationTest {
    private final static Logger LOG = Logger.getLogger(ShellBasedResourcePublisherIntegrationTest.class);

    private FreemarkerTemplateCommandBuilder commandBuilder;

    /**
     * The script preamble.
     */
    private String submitHostPreamble;

    File tmpDestinationDirectory;

    File tmpSourceDirectory;

    UserSharedLocationManager sharedLocationManager;

    private String commonScripts;
    
    private String genericScripts;

    private Invoker invoker;

    private String templatesDirectory;
    
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        TestsHelper.initProperties(this.getClass(),"/tests.properties");

        templatesDirectory = System.getProperty(TestProperties.TEMPLATES_DIRECTORY_LOCATION);
        commonScripts = System.getProperty(TestProperties.MIF_COMMON_SCRIPTS_LOCATION);
        genericScripts = System.getProperty(TestProperties.MIF_GENERIC_SCRIPTS_LOCATION);
        
        UUID uuid = UUID.randomUUID();
        String base = uuid.toString();
        tmpDestinationDirectory = TestsHelper.tmpDirectoryPath(base + "-DEST");
        tmpSourceDirectory = TestsHelper.createTmpDirectory(base + "-SRC");
        sharedLocationManager = new UserSharedLocationManager();
        sharedLocationManager.setWorkspaceManager(new DefaultWorkspaceManager(tmpDestinationDirectory, false));

        DirectoryTemplateLocator templateLocator = new DirectoryTemplateLocator();
        templateLocator.setCommandTemplateDirectory(new File(templatesDirectory));
        commandBuilder = new FreemarkerTemplateCommandBuilder();
        commandBuilder.setTemplateLocator(templateLocator);
        commandBuilder.setTemplate("generic/publishHandler.ftl");
        commandBuilder.setVariable("CONNECTOR_UTILS", genericScripts);
        commandBuilder.setVariable("MANGO_UTILS", commonScripts);
        
        submitHostPreamble = "# TEST PREAMBLE 1\n# TEST PREAMBLE 2\n# TEST PREAMBLE 3";
        InvokerFactory invokerFactory = new InvokerFactory();
        invoker = invokerFactory.createRunAsNavplusInvoker();
        LOG.info("Resource source:" +tmpSourceDirectory );
        LOG.info("Resource destination:" +tmpDestinationDirectory );
    }

    /**
     * Try to clean up the temporary directories.  Since they were created by another user
     * it is doubtful whether we will be able to do this - hence the ignored exceptions
     */
    @After
    public void tearDown() {
        try {
            FileUtils.deleteDirectory(tmpDestinationDirectory);
        } catch (Exception ignored) {
        }
        try {
            FileUtils.deleteDirectory(tmpSourceDirectory);
        } catch (Exception ignored) {
        }
    }

    @Test
    public void shouldPublishFilesToRequestDirWhenRunInRequestDirectory_true() throws IOException, MIFException {
        sharedLocationManager.setRunInRequestDirectory(true);
        String requestID = publishFiles();
        File requestInputDirectory = sharedLocationManager.getRequestInputDirectory(requestID);
        assertEquals(requestID,requestInputDirectory.getName());
    }

    @Test
    public void shouldPublishFilesTo_inputs_directoryWhenRunInRequestDirectory_false() throws IOException, MIFException {
        publishFiles();
        String requestID = publishFiles();
        File requestInputDirectory = sharedLocationManager.getRequestInputDirectory(requestID);
        assertEquals("inputs",requestInputDirectory.getName());
        assertEquals(requestID,requestInputDirectory.getParentFile().getName());
    }

    public String publishFiles() throws IOException, MIFException {
        PublisherParameters parameters = new PublisherParameters();

        Map<String,String> requestAttributes = Maps.newHashMap();
        parameters.setRequestAttributes(requestAttributes);
        parameters.setRootDirectory(tmpSourceDirectory);
        parameters.setSharedLocationManager(sharedLocationManager);
        parameters.setInvoker(invoker);
        parameters.setSubmitHostPreamble(submitHostPreamble);
        parameters.setCommandBuilder(commandBuilder);

        ShellBasedResourcePublisher publisher = new ShellBasedResourcePublisher(parameters);

        File dirA = new File(tmpSourceDirectory, "A");
        dirA.mkdir();
        File dirB = new File(tmpSourceDirectory, "B");
        dirB.mkdir();
        File dirC = new File(tmpSourceDirectory, "C");
        dirC.mkdir();

        File aFile = new File(dirA, "a.txt");
        FileUtils.writeStringToFile(aFile, "This is test content of a.txt file.");

        File bFile = new File(dirB, "b.txt");
        FileUtils.writeStringToFile(bFile, "This is test content of b.txt file.");

        List<File> files = Lists.newArrayList(aFile, bFile);

        publisher.addFiles(files);

        File requestInputDirectory = null;
        String requestID = null;
        
        requestID = publisher.publish();
        requestInputDirectory = sharedLocationManager.getRequestInputDirectory(requestID);

        assertTrue(requestInputDirectory != null);
        assertTrue(new File(requestInputDirectory, dirA.getName()).exists());
        assertTrue(new File(requestInputDirectory, dirB.getName()).exists());
        assertTrue(new File(requestInputDirectory, dirC.getName()).exists());
        assertTrue(new File(requestInputDirectory, dirA.getName() + "/" + aFile.getName()).exists());
        assertTrue(new File(requestInputDirectory, dirB.getName() + "/" + bFile.getName()).exists());
        assertTrue(requestID != null);
        
        return requestID;
    }

}
