package com.mango.mif.core.resource.shell;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.mango.mif.core.api.SharedLocationManager;
import com.mango.mif.core.exec.CommandBuilder;
import com.mango.mif.core.exec.Invoker;
import com.mango.mif.core.resource.ResourceCopier;

/**
 * The Class ShellBasedResourcePublisherParameters.
 * his acts as a Object that is passed for the creation of the ShellBasedResourcePublisher.
 */
public class PublisherParameters {

    /** The invoker. */
    Invoker invoker;

    /** The resource copier. */
    ResourceCopier resourceCopier;

    /**
     * The script preamble.
     */
    private String submitHostPreamble;

    /**
     * The grid preamble.
     */
    private String gridHostPreamble;

    /**
     * Root directory relative to which files should be copied
     */
    protected File rootDirectory;

    /**
     * The command builder
     */
    protected CommandBuilder commandBuilder;

    /**
     * The request attributes which basically come across in the ExecutionProfile as a list of ExecutionRequestAttribute
     * objects (basically name/value pairs).
     */
    private Map<String, String> requestAttributes;

    /**
     * Shared Location Manager providing basic shared location operations
     */
    protected SharedLocationManager	sharedLocationManager;

    public File getRootDirectory() {
        return rootDirectory;
    }

    public void setRootDirectory(File rootDirectory) {
        this.rootDirectory = rootDirectory;
    }

    public CommandBuilder getCommandBuilder() {
        return commandBuilder;
    }

    public void setCommandBuilder(CommandBuilder commandBuilder) {
        this.commandBuilder = commandBuilder;
    }

    public SharedLocationManager getSharedLocationManager() {
        return sharedLocationManager;
    }

    public void setSharedLocationManager(SharedLocationManager sharedLocationManager) {
        this.sharedLocationManager = sharedLocationManager;
    }

    public Invoker getInvoker() {
        return invoker;
    }

    public void setInvoker(Invoker invoker) {
        this.invoker = invoker;
    }

    public ResourceCopier getResourceCopier() {
        return resourceCopier;
    }

    public void setResourceCopier(ResourceCopier resourceCopier) {
        this.resourceCopier = resourceCopier;
    }

    public String getSubmitHostPreamble() {
        return submitHostPreamble;
    }

    public void setSubmitHostPreamble(String submitHostPreamble) {
        this.submitHostPreamble = submitHostPreamble;
    }

    public String getGridHostPreamble() {
        return gridHostPreamble;
    }

    public void setGridHostPreamble(String gridHostPreamble) {
        this.gridHostPreamble = gridHostPreamble;
    }

    public Map<String, String> getRequestAttributes() {
        return requestAttributes;
    }

    public void setRequestAttributes(Map<String, String> requestAttributes) {
        this.requestAttributes = requestAttributes;
    }

    public void addRequestAttribute(String name, String value) {
        if (requestAttributes == null) {
            requestAttributes = new HashMap<String, String>();
        }
        requestAttributes.put(name, value);
    }
}
