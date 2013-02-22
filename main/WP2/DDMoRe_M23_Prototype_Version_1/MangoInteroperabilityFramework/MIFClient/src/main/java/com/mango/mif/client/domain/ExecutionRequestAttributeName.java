/**
 * Copyright Mango Solutions, 2012.
 * <p>SVN Entry: $HeadURL: $
 * <p>SVN ID: $Id: $
 * <p>Last edited by : $Author$
 * @author Tony Bamford &lt;tbamford@mango-solutions.com&gt;
 * @version $Revision$ as of on $Date$
 */
package com.mango.mif.client.domain;

/**
 * These are names of variables placed in the Execution Request which are fixed between Navigator and MIF.
 */
public enum ExecutionRequestAttributeName {

    SHELL("SHELL", "/bin/bash", ExecutionRequestAttributeName.DO_NOT_INCLUDE_IN_REQUEST_ATTRIBUTE_BLOCK),
    GRID_SHELL("GRID_SHELL", "/bin/bash", ExecutionRequestAttributeName.DO_NOT_INCLUDE_IN_REQUEST_ATTRIBUTE_BLOCK),
    MANGO_DEBUG("MANGO_LOGGING", ""),
    UMASK("UMASK", "007"),
    FILEIGNOREPATTERN("FILE_IGNORE_PATTERN", ""),
    DIRIGNOREPATTERN("DIR_IGNORE_PATTERN", ""),
    MANGO_SGE_QSTAT("MANGO_SGE_QSTAT", "qstat"),
    MANGO_SGE_QACCT("MANGO_SGE_QACCT", "qacct"),
    MANGO_SGE_QDEL("MANGO_SGE_QDEL", "qdel"),
    MANGO_SGE_QSUB("MANGO_SGE_QSUB", "qsub"),
    QSUB_PARAMETERS("QSUB_PARAMETERS", ""),
    USER_DIR_ADDITIONAL_MODES("USER_DIR_ADDITIONAL_MODES", ""),
    COPY_MODE("COPY_MODE", "all");

    private static final boolean DO_NOT_INCLUDE_IN_REQUEST_ATTRIBUTE_BLOCK = false;
    private final String name;
    private final String defaultValue;

    private final boolean wantedInBlock;

    ExecutionRequestAttributeName(String name, String defaultValue) {
        this.name = name;
        this.defaultValue = defaultValue;
        this.wantedInBlock = true;
    }

    ExecutionRequestAttributeName(String name, String defaultValue, boolean includeInBlock) {
        this.name = name;
        this.defaultValue = defaultValue;
        this.wantedInBlock = includeInBlock;
    }

    public String getName() {
        return this.name;
    }

    public String getDefaultValue() {
        return this.defaultValue;
    }

    /**
     * Return whether the variable should be expanded into the request attribute block at the top of the script.
     * @return whether this is wanted in the script block or not 
     */
    public boolean isWantedInBlock() {
        return this.wantedInBlock;
    }

    /**
     * Find an ExecutionRequestAttributeName corresponding to the specified name.
     * @param s the specified name
     * @return The corresponding ExecutionRequestAttributeName, or null if name is not found.
     */
    public static ExecutionRequestAttributeName find(String s) {
        for (ExecutionRequestAttributeName attr : ExecutionRequestAttributeName.values()) {
            if (attr.getName().equals(s)) {
                return attr;
            }
        }
        return null;
    }
}
