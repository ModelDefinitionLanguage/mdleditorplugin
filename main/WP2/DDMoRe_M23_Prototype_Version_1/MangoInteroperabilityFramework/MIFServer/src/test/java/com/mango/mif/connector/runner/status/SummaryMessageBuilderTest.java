/* File			:  SummaryMessageBuilderTest.java
 * Project		:  MIFServer
 * Created on	:  Aug 13, 2012
 */
package com.mango.mif.connector.runner.status;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.google.common.base.Preconditions;
import com.mango.mif.connector.domain.Job;
import com.mango.mif.core.api.SharedLocationManager;
import com.mango.mif.core.exec.Invoker;
import com.mango.mif.core.exec.invoker.InvokerFactory;
import com.mango.mif.core.exec.template.ClasspathFreemarkerTemplateLocator;
import com.mango.mif.core.exec.template.JobAwareFreemarkerTemplateCommandBuilder;
import com.mango.mif.utils.MIFProperties;
import com.mango.mif.utils.TestProperties;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * A super class containg common settup for all message builder tests
 */
public class SummaryMessageBuilderTest {

    /**
     * logger
     */
    protected static final Logger logger = Logger.getLogger(SummaryMessageBuilderTest.class);
    /**
     * Scripts directory
     */
    protected final static String TEMPLATES = "/runtime/templates";
    /**
     * invoker
     */
    protected Invoker invoker;
    /**
     * common scripts location
     */
    protected String commonScripts;
    /**
     * generic scripts location
     */
    protected String genericScripts;
    /**
     * nonmem sge connector scripts
     */
    protected String connectorScripts;
    /**
     * directory in which nonmem sge connector templates are located
     */
    protected String templateDirectory;
    /**
     * Job
     */
    protected Job job;
    /**
     * shared location
     */
    @Mock
    protected SharedLocationManager sharedLocationManager;
    /**
     * builder
     */
    protected JobAwareFreemarkerTemplateCommandBuilder textBuilder;

    public SummaryMessageBuilderTest() {
        super();
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Properties properties = new Properties();
        properties.load(SummaryMessageBuilderTest.class.getResourceAsStream("/com/mango/mif/sgeconnector/connector.properties"));
        MIFProperties.loadProperties(properties);

        commonScripts = System.getProperty(TestProperties.MIF_COMMON_SCRIPTS_LOCATION);
        genericScripts = System.getProperty(TestProperties.MIF_GENERIC_SCRIPTS_LOCATION);
        connectorScripts = System.getProperty(TestProperties.MIF_NONMEMSGE_CONNECTOR_SCRIPTS_LOCATION);

        Preconditions.checkNotNull(commonScripts, TestProperties.MIF_COMMON_SCRIPTS_LOCATION + " property is not set.");
        Preconditions.checkNotNull(genericScripts, TestProperties.MIF_GENERIC_SCRIPTS_LOCATION + " property is not set.");
        Preconditions.checkNotNull(connectorScripts, TestProperties.MIF_NONMEMSGE_CONNECTOR_SCRIPTS_LOCATION + " property is not set.");


        InvokerFactory invokerFactory = new InvokerFactory();
        
        invoker = invokerFactory.createRunAsNavplusInvoker();

        textBuilder = new JobAwareFreemarkerTemplateCommandBuilder();
        ClasspathFreemarkerTemplateLocator templateLocator = new ClasspathFreemarkerTemplateLocator();
        templateLocator.setCommandTemplateDirectory(TEMPLATES);
        textBuilder.setTemplateLocator(templateLocator);
        textBuilder.setVariable("NONMEMSGE_CONNECTOR_UTILS", connectorScripts);
        textBuilder.setVariable("MANGO_UTILS", commonScripts);
    }

}