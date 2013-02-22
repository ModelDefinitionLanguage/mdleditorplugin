/* File			:  SgeConnectorScriptTemplatesTest.java
 * Project		:  MIFServer
 * Created on	:  6 Jul 2012
 */
package com.mango.mif.sgeconnector;


import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mango.mif.connector.TestsHelper;
import com.mango.mif.connector.domain.Job;
import com.mango.mif.core.api.SharedLocationManager;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.template.ClasspathFreemarkerTemplateLocator;
import com.mango.mif.core.exec.template.FreemarkerTemplateCommandBuilder;
import com.mango.mif.sge.SGE;


/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 * A test validating the sge script templates
 */
public class ConnectorScriptTemplatesTest {
    /**
     * Logger
     */
    private final static Logger LOG = Logger.getLogger(ConnectorScriptTemplatesTest.class);
    /**
     * Scripts directory
     */
    private final static String TEMPLATES = "/runtime/templates";
    /**
     * variable used in templates
     */
    private static String connectorUtilScriptsLocationVariable = "CONNECTOR_UTILS-DIR";
    /**
     * variable used in templates
     */
    private static String mangoUtilScriptsLocationVariable = "MANGO_UTILS-DIR";
    /**
     * Sge shared location
     */
    private static String sgeSharedLocationVariable = "SGE-SHARED-LOCATION";
    /**
     * Sge shared location
     */
    private static String sgeJobFinalStatusFileName = "QACCT-DUMP";
    /**
     * 
     */
    private static String executablePath = "EXECUTABLE";
    /**
     * Job
     */
    Job job;
    /**
     * shared location
     */
    @Mock SharedLocationManager sharedLocationManager;
    /**
     * initial context
     */
    HashMap<String,Object> initialContext = Maps.newHashMap();
    private FreemarkerTemplateCommandBuilder commandBuilder;
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        job = TestsHelper.createJob("TEST-JOB", UUID.randomUUID().toString());
        job.getExecutionRequest().setExecutionFile("EXECUTION-FILE.R");
        job.setUserName("navplus");

        job.addToDataMap(SGE.SGE_JOB_ID_KEY,"SGE-JOB-ID");
        job.addToDataMap(SGE.SGE_SUBMIT_SCRIPT_KEY,"SGE-JOB-SCRIPT.sh");
        job.setGridJobDirectory(new File(".").getAbsolutePath());
        job.setSharedLocationManager(sharedLocationManager);
        when(sharedLocationManager.getRequestInputDirectory((String)any())).thenReturn(new File("JOB-REQUEST-INPUT-DIR"));
        when(sharedLocationManager.getRequestOutputDirectory((String)any())).thenReturn(new File("JOB-REQUEST-OUTPUT-DIR"));

        initialContext.put("job", job);
        initialContext.put("sharedLocationManager", sharedLocationManager);
        initialContext.put("CONNECTOR_UTILS", connectorUtilScriptsLocationVariable);
        initialContext.put("MANGO_UTILS", mangoUtilScriptsLocationVariable);
        initialContext.put("PSN_SGECONNECTOR_UTILS", connectorUtilScriptsLocationVariable);
        initialContext.put("sgeSharedLocation", sgeSharedLocationVariable);
        initialContext.put("scriptExecutablePath",executablePath);
        initialContext.put("sgeJobFinalStatusFileName",sgeJobFinalStatusFileName);
        initialContext.put("REQUEST_ATTRIBUTE_BLOCK", "");

        commandBuilder = new FreemarkerTemplateCommandBuilder(initialContext);
        ClasspathFreemarkerTemplateLocator templateLocator = new ClasspathFreemarkerTemplateLocator();
        templateLocator.setCommandTemplateDirectory(TEMPLATES);
        commandBuilder.setTemplateLocator(templateLocator);

    }

    @Test
    public void shouldGenerateValidPreparingHandlerScript() throws ExecutionException {
        commandBuilder.setTemplate("generic/prepareHandler.ftl");
        String command = commandBuilder.getCommand();
        LOG.debug(command);
        assertNotNull(command);
    }

    @Test
    public void shouldGenerateValidQacctScript() throws ExecutionException {
        commandBuilder.setTemplate("common/qacct.ftl");
        String command = commandBuilder.getCommand();
        LOG.debug(command);
        assertNotNull(command);
    }
    @Test
    public void shouldGenerateValidQstatScript() throws ExecutionException {
        commandBuilder.setTemplate("common/qstat.ftl");
        String command = commandBuilder.getCommand();
        LOG.debug(command);
        assertNotNull(command);
    }
    @Test
    public void shouldGenerateValidQdelScript() throws ExecutionException {
        commandBuilder.setTemplate("common/qdel.ftl");
        String command = commandBuilder.getCommand();
        LOG.debug(command);
        assertNotNull(command);
    }

    @Test
    public void shouldGenerateValidQdelChildScript() throws ExecutionException {
        List<String> sgeJobIDs = Lists.newArrayList("1","2","3");
        commandBuilder.setTemplate("psnbootstrapsgeconnector/qdel-child.ftl");
        commandBuilder.setVariable("sgeJobIDs", sgeJobIDs);
        String command = commandBuilder.getCommand();
        LOG.debug(command);
        assertNotNull(command);
    }
    @Test
    public void shouldGenerateValidRetrieveScript() throws ExecutionException {

        commandBuilder.setVariable("mifOutputDirectory", "REQUEST_OUTPUT_DIRECTORY");
        commandBuilder.setVariable("mifHiddenDirectory", "HIDDEN_MIF_DIRECTORY");
        commandBuilder.setVariable("mifModifiedListFilename", "TIMESTAMP_FILE");
        commandBuilder.setVariable("gridOutputFileName", "STD_OUT");
        commandBuilder.setVariable("gridErrorFileName", "STD_ERR");
        commandBuilder.setVariable("gridJobFinalStatusFileName", "QACCT_FILE");
        commandBuilder.setVariable("sharedDirectory", "SHARED_DIRECTORY_PATH");
        commandBuilder.setVariable("filesToCopy", Lists.newArrayList("A","B","C"));
        commandBuilder.setTemplate("generic/retrieveHandler.ftl");
        String command = commandBuilder.getCommand();
        LOG.debug(command);
        assertNotNull(command);
    }
}
