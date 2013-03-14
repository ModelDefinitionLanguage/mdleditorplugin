package com.mango.mif.core.resource.shell;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.mango.mif.core.api.SharedLocationManager;
import com.mango.mif.core.exec.Invoker;
import com.mango.mif.core.exec.template.FreemarkerTemplateCommandBuilder;
import com.mango.mif.core.resource.ResourceCopier;

/**
 * Test for {@link ShellBasedResourcePublisher}.
 * 
 * 
 * @version $Revision: $ as of $Date: $
 *          <p>
 *          SVN Entry : $HeadURL: $
 *          <p>
 *          SVN ID : $Id: $
 *          <p>
 *          Last edited by : $Author: $
 */
public class ShellBasedResourcePublisherTest {

    private static final String ROOT_DIRECTORY = "test";
    private static final String PROJ_DIRECTORY = ROOT_DIRECTORY + File.separator + " project";

    FreemarkerTemplateCommandBuilder commandBuilder;

    Invoker invoker;

    ResourceCopier resourceCopier;

    SharedLocationManager sharedlocationManager;

    @Before
    public void setUp() {
        invoker = mock(Invoker.class);
        resourceCopier = mock(ResourceCopier.class);
        sharedlocationManager = mock(SharedLocationManager.class);
        commandBuilder = mock(FreemarkerTemplateCommandBuilder.class);
    }

    @Test
    public void ShouldConstructPublisher() {
        PublisherParameters shellBasedResourcePublisherParameters = new PublisherParameters();
        shellBasedResourcePublisherParameters.setCommandBuilder(commandBuilder);
        shellBasedResourcePublisherParameters.setInvoker(invoker);
        shellBasedResourcePublisherParameters.setResourceCopier(resourceCopier);
        shellBasedResourcePublisherParameters.setRootDirectory(new File(
                PROJ_DIRECTORY));
        shellBasedResourcePublisherParameters
        .setSharedLocationManager(sharedlocationManager);

        when(sharedlocationManager.getSharedLocationRoot()).thenReturn(new File(
                ROOT_DIRECTORY));

        ShellBasedResourcePublisher shellBasedResourcePublisher = new ShellBasedResourcePublisher(
                shellBasedResourcePublisherParameters);

        assertNotNull(shellBasedResourcePublisher != null);
    }

    @Test(expected=RuntimeException.class)
    public void ShouldThrowExceptionWhenInvokerIsnotSetDuringPublisherConstruction() {
        PublisherParameters shellBasedResourcePublisherParameters = new PublisherParameters();

        new ShellBasedResourcePublisher(shellBasedResourcePublisherParameters);
    }

    @Test(expected=RuntimeException.class)
    public void ShouldThrowExceptionWhenCommandBuilderIsnotSetDuringPublisherConstruction() {
        PublisherParameters shellBasedResourcePublisherParameters = new PublisherParameters();
        shellBasedResourcePublisherParameters.setInvoker(invoker);


        new ShellBasedResourcePublisher(shellBasedResourcePublisherParameters);

    }

}
