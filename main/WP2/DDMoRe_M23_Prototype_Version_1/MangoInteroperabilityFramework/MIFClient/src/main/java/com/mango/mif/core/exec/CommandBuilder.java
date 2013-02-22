/* File			:  CommandBuilder.java
 * Project		:  MIFServer
 * Created on	:  22 Jun 2012
 */
package com.mango.mif.core.exec;

import java.io.File;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.mango.mif.client.domain.ExecutionRequestAttributeName;
import com.mango.mif.exception.MIFException;
import com.mango.mif.utils.OSType;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 *
 * Common interface of command builder
 */
public abstract class CommandBuilder {

    private static Logger logger = Logger.getLogger(CommandBuilder.class);

    /**
     * The expected name for the job instance when placed in the context.
     */
    public static final String CONTEXT_NAME_JOB = "job";

    /**
     * The expected name for the script preamble instance when placed in the context.
     */
    public static final String CONTEXT_NAME_SUBMIT_HOST_PREAMBLE = "SUBMIT_HOST_PREAMBLE";

    /**
     * The expected name for the script preamble instance when placed in the context.
     */
    public static final String CONTEXT_NAME_GRID_HOST_PREAMBLE = "GRID_HOST_PREAMBLE";

    /**
     * The expected name for the directory containing the mango shell script utilities.
     */
    public static final String CONTEXT_NAME_MANGO_UTIL_PATH = "MANGO_UTILS";

    /**
     * The expected name for the directory containing the shell scripts associated with the currently executing connector
     */
    public static final String CONTEXT_NAME_CONNECTOR_PATH = "CONNECTOR_UTILS";

    /**
     * The expected name for the absolute path to the shell for scripts executed on the GRID HOST.
     */
    public static final String CONTEXT_NAME_GRID_SHELL_PATH = "GRID_SHELL";

    /**
     * The expected name for the absolute path to the shell for scripts executed on the SUBMIT HOST.
     */
    public static final String CONTEXT_NAME_SHELL_PATH = "SHELL";

    /**
     * The expected name for the mango logging enable/disable variable.
     */
    public static final String CONTEXT_NAME_MANGO_DEBUGGING = "MANGO_LOGGING";

    /**
     * The expected name for the umask the user would like to have.
     */
    public static final String CONTEXT_NAME_UMASK = "UMASK";

    /**
     * The expected name for the regexps to use when ignoring files to copy (back).
     */
    public static final String CONTEXT_NAME_FILE_IGNORE_PATTERN = "MANGO_FILE_IGNORE";

    /**
     * The expected name for the regexps to use when ignoring directories to copy (back).
     */
    public static final String CONTEXT_NAME_DIRECTORY_IGNORE_PATTERN = "MANGO_DIRECTORY_IGNORE";

    /**
     * The expected name for all the variables defined in the execution request attributes
     * that haven't been used elsewhere (SHELL, GRID_SHELL, etc. etc.).
     */
    public static final String CONTEXT_NAME_REQUEST_ATTRIBUTE_BLOCK = "REQUEST_ATTRIBUTE_BLOCK";

    /**
     * Command template
     */
    protected String template;

    /**
     * Builds a command
     * @return the built command
     * @throws MIFException if anything goes wrong
     */
    public abstract String getCommand() throws ExecutionException;

    /**
     * Add an object into the freemarker context, or whatever context you happen to use.
     * @param variableName The name by which the object will be known
     * @param value The object itself
     * @throws ExecutionException if we need to throw an exception, this will be thrown.
     */
    public abstract void setVariable(String variableName, Object value) throws ExecutionException;

    /**
     * Prove something is in the freemarker context by spitting it back out again.
     * @param variableName The name by which the object may be known
     * @return The object itself
     */
    public abstract Object getVariable(String variableName);

    /**
     * Add the submit host preamble as a variable to the context.  This encourages all to use the suggested context name.
     * @param preamble The preamble, or if null, the empty string.
     * @throws ExecutionException If things screw up.
     */
    public void setSubmitHostPreamble(String preamble) throws ExecutionException {
        setVariable(CONTEXT_NAME_SUBMIT_HOST_PREAMBLE, preamble);
    }

    /**
     * Add the grid host preamble as a variable to the context.  This encourages all to use the suggested context name.
     * @param preamble The preamble, or if null, the empty string.
     * @throws ExecutionException If things screw up.
     */
    public void setGridHostPreamble(String preamble) throws ExecutionException {
        setVariable(CONTEXT_NAME_GRID_HOST_PREAMBLE, preamble);
    }

    /**
     * Add the mango utilities execution path to the context.  This encourages all to use the suggested context name.
     * @param path The path to the utilities
     * @throws ExecutionException If things screw up.
     */
    public void setUtilPath(String path) throws ExecutionException {
        validatePath(path, true, CONTEXT_NAME_MANGO_UTIL_PATH);
        setVariable(CONTEXT_NAME_MANGO_UTIL_PATH, new File(path).getAbsolutePath());
    }

    /**
     * Add the directory containing to the context.  This encourages all to use the suggested context name.
     * @param path The path to the utilities
     * @throws ExecutionException If things screw up.
     */
    public void setConnectorPath(String path) throws ExecutionException {
        validatePath(path, true, CONTEXT_NAME_CONNECTOR_PATH);
        setVariable(CONTEXT_NAME_CONNECTOR_PATH, new File(path).getAbsolutePath());
    }

    @VisibleForTesting
    public void setGridShellPath(String value) throws ExecutionException {
        setVariable(CONTEXT_NAME_GRID_SHELL_PATH, value);
    }

    /**
     * @param template the template to set
     */
    public void setTemplate(String template) {
        this.template = template;
    }

    /**
     * The command builder CTOR is invoked with a map whose values (are intended to) come from Spring.  Thus we
     * can use Spring to give suitable defaults for things like SHELL etc.
     * 
     * This function allows the command builder to accept an incoming map of execution request attributes, which
     * have journeyed all the way from the execution profile in Navigator.  These execution request attributes
     * are basically name/value (String) pairs held in yet another map.  This map can contain any variables the
     * user cares to define, i.e we will get a mix of things we expect (like SHELL etc.) and things we don't.
     * 
     * The thing is, we absolutely need a value for SHELL, but that's ok because if you have a look at the CTOR
     * you will see that all the things we depend on (defined by the enum ExecutionRequestAttributeName) are given
     * suitable defaults there.
     *
     * We walk through the values in the execution request attributes and put some of them into a block of
     * newline separated:
     * export NAME="VALUE"
     * assignments which are then placed into the Freemarker context via setVariable.
     */
    public void populateCommandBuilderContext(Map<String, String> requestAttributes) throws ExecutionException {

        Preconditions.checkNotNull(requestAttributes, "Cannot populate the command builder context with null request attributes!");

        // Here we deal with the expected values, using a default from the enumerated type if we find
        // no suitable value defined in the map
        //
        for (ExecutionRequestAttributeName attr : ExecutionRequestAttributeName.values()) {
            String name = attr.getName();

            // If the attribute has a value in the request attributes map, use it, whatever it is...
            // even if it is complete rubbish
            //
            if (requestAttributes.containsKey(name)) {
                String value = requestAttributes.get(name);
                setVariable(name, value);
            }
        }

        // Here we deal with the unexpected values and the ones we want in the block.
        //
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : requestAttributes.entrySet()) {
            String variableName = entry.getKey();
            ExecutionRequestAttributeName attr = ExecutionRequestAttributeName.find(variableName);

            // All variables not in the list in ExecutionRequestAttributeName are wanted.
            boolean isWanted = true;

            // For those in ExecutionRequestAttributeName, we need the enum to decide whether it is wanted
            if (attr != null) {
                isWanted = attr.isWantedInBlock();
            }

            // Generate Bourne Shell syntax because generating bash syntax has
            // just bitten us in the arse with a Jansen machine, i.e. we generate
            //       VAR="value"; export VAR
            // as opposed to the bash syntax of:
            //       export VAR="value"
            if (isWanted) {
                String value = entry.getValue();
                sb.append(variableName);
                sb.append("=\"");
                sb.append(value);
                sb.append("\"; export ");
                sb.append(variableName);
                sb.append("\n");
            }
        }
        setVariable(CONTEXT_NAME_REQUEST_ATTRIBUTE_BLOCK, sb.toString());
    }

    /**
     * Set all the good things from the request attributes, but also set the grid and submit host preambles.
     * 
     * @param requestAttributes The request attribute map.
     * @param gridHostPreamble The grid host preamble.
     * @param submitHostPreamble The submit host preamble.
     * @throws ExecutionException Thrown if the setVariable stuff fails for any reason.
     */
    public void populateCommandBuilderContext(Map<String, String> requestAttributes, String gridHostPreamble, String submitHostPreamble) throws ExecutionException {
        populateCommandBuilderContext(requestAttributes);
        setGridHostPreamble(gridHostPreamble);
        setSubmitHostPreamble(submitHostPreamble);
    }

    /**
     * Validate that the path is set and is absolute, and if checkExists is true, check the path for existence.
     * @param path The path to validate
     * @param checkExists Boolean flag indicating to check if the path exists
     * @param contextString a string to put any errors raised into context
     * @throws ExecutionException Thrown if the path is blank or is not absolute, or conditionally does not exist
     */
    private void validatePath(String path, boolean checkExists, String contextString) throws ExecutionException {
        if (StringUtils.isBlank(path)) {
            throw new ExecutionException("The path cannot be blank");
        }
        if (OSType.isUnixFlavoured() && !path.startsWith("/")) {
            throw new ExecutionException("The path \"" + path + "\" must be absolute");
        }
        if (checkExists) {
            File entry = new File(path);
            if (!entry.exists()) {
                throw new ExecutionException("While setting " + contextString + ": The path \"" + path + "\" does not exist");
            }
        }
    }
}
