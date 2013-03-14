/* File			:  FreemarkerTemplateCommandBuilderTest.java
 * Project		:  MIFServer
 * Created on	:  13 Jun 2012
 */
package com.mango.mif.core.exec.template;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.io.File;
import java.net.URL;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.google.common.collect.Maps;
import com.mango.mif.core.api.SharedLocationManager;
import com.mango.mif.core.exec.ExecutionException;


/**
 * 
 * Tests template command builder
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 *
 */
public class FreemarkerTemplateCommandBuilderTest {
    /**
     * Log
     */
    final static Logger LOG = Logger.getLogger(FreemarkerTemplateCommandBuilderTest.class);

    File testCommandTemplateDirectory;

    DirectoryTemplateLocator templateLocator;

    /**
     * shared location manager that is injected into Job
     */
    @Mock SharedLocationManager sharedLocationManager;

    /**
     * request id
     */
    UUID uuid;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        URL url = FreemarkerTemplateCommandBuilderTest.class.getResource("TEST-COMMAND.FTL");
        File foo = FileUtils.toFile(url);
        testCommandTemplateDirectory = foo.getParentFile();
        templateLocator = new DirectoryTemplateLocator();
        templateLocator.setCommandTemplateDirectory(testCommandTemplateDirectory);
        uuid = UUID.randomUUID();
        when(sharedLocationManager.getRequestOutputDirectory((String)any())).thenReturn(new File("."));
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionIfTemplateLocatorNotSet() throws ExecutionException {
        FreemarkerTemplateCommandBuilder instance = new FreemarkerTemplateCommandBuilder();
        instance.setTemplate("TEST-COMMAND.FTL");
        instance.getCommand();
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionIfTemplateNotSet() throws ExecutionException {
        FreemarkerTemplateCommandBuilder instance = new FreemarkerTemplateCommandBuilder();
        instance.setTemplateLocator(templateLocator);
        instance.getCommand();
    }

    @Test(expected = ExecutionException.class)
    public void shouldThrowExceptionIfTemplateFileDoesNotExist() throws ExecutionException  {
        FreemarkerTemplateCommandBuilder instance = new FreemarkerTemplateCommandBuilder();
        instance.setTemplate("DOES-NOT-EXIST.FTL");
        instance.setTemplateLocator(templateLocator);
        instance.getCommand();
    }

    @Test
    public void shouldAddAnEntryToThePassedMap() throws ExecutionException  {
        FreemarkerTemplateCommandBuilder instance = new FreemarkerTemplateCommandBuilder();
        instance.setTemplate("ADD-ENTRY-TO-MAP.FTL");
        instance.setTemplateLocator(templateLocator);
        Map<String,String> map = Maps.newHashMap();
        map.put("variable2", "variable from map");
        instance.setVariable("map", map);
        instance.setVariable("variable", " test variable ");
        instance.getCommand();
        assertNotNull(map.get("NEW-ENTRY"));
    }
    
    @Test
    public void shouldProduceATextWithDefaultValues() throws ExecutionException  {
        FreemarkerTemplateCommandBuilder instance = new FreemarkerTemplateCommandBuilder();
        instance.setTemplate("DEFAULT-VALUE.FTL");
        instance.setTemplateLocator(templateLocator);
        instance.setVariable("defaultValue", "defaultValue");
        instance.setVariable("emptyExpression", "");
        LOG.debug(instance.getCommand());
        assertEquals("defaultValue\nnot empty\nnot null\ndefaultValue\ndefaultValue",instance.getCommand().trim());
    }
}
