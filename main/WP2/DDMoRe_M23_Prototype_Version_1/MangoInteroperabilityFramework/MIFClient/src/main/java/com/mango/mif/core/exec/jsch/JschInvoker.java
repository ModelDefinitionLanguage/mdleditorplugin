/**
 * Copyright Mango Solutions, 2012.
 * <p>CVS Entry : $Header$
 * <p>Last edited by : $Author$
 * @version $Revision$ as of on $Date$
 */
package com.mango.mif.core.exec.jsch;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.google.common.base.Preconditions;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.Invoker;
import com.mango.mif.core.exec.InvokerResult;

/**
 * The JSCH Invoker class lets us execute things in a JSCH way.
 * We need only two things, username and password, plus a whole bunch of optional stuff which is all hidden in JschParameters.
 */
public class JschInvoker implements Invoker {

    private static final String INVOKER_SHELL = "/bin/bash";

    private static final Logger logger = Logger.getLogger(JschInvoker.class);

    private final JschParameters parameters;

    public JschInvoker(JschParameters parameters) {
        this.parameters = parameters;
    }

    @Override
    public InvokerResult execute(String commandLine) throws ExecutionException {
        return execute(commandLine, null);
    }

    /* (non-Javadoc)
     * @see com.mango.mif.core.exec.Invoker#execute(java.lang.String, java.lang.String)
     */
    @Override
    public InvokerResult execute(String commandLine, String stdin) throws ExecutionException {
        Preconditions.checkNotNull(parameters, "the parameters cannot be null");
        Preconditions.checkArgument(StringUtils.isNotBlank(parameters.getUserName()), "The username must have been set by this point");
        Preconditions.checkArgument(StringUtils.isNotBlank(parameters.getClearTextPassword()), "The clear text password must have been set by this point");

        InvokerResult results = null;
        JschSessionPool pool = new JschSessionPool();
        JschSession session = pool.get(parameters);
        Preconditions.checkNotNull(session, "The session pool returned a null session!");

        try {
            logger.debug("JschInvoker.execute (session: " + session + " ) command: " + commandLine);
            if (stdin != null) {
                logger.debug("input: " + stdin);
            }

            // Bug 10227.  Things go horribly wrong with bootstrap because the list of files is just
            // HUGE.  Throwing all this text at the shell on its standard input for it to execute
            // causes it to barf and die with an arg list too long error.
            // The fix for this is to write the whole command to a file.  Its a shame we have to do
            // this for everything just because of bootstrap, but it does mean everything will work.
            File temp = null;
            try {
                temp = File.createTempFile(Long.toString(System.currentTimeMillis()), ".sh");
                temp.setReadable(true);
                temp.setExecutable(true);
                FileUtils.writeStringToFile(temp, commandLine);
                session.setLastCommand(commandLine);

                // The big problem here, of course, is that tomcat writes the file, but can the
                // user actually read it?  I've set the readability to true but I suspect in Java land
                // this only applies to the file owner, and not group or others.
                //
                results = session.run(INVOKER_SHELL + " " + temp.getAbsolutePath(), stdin);
            } catch (IOException ioe) {
                logger.fatal("CONFIGURATION ERROR: You do not have permission to write to the temporary directory " + System.getProperty("java.io.tmpdir"));
                logger.fatal("The actual exception is: ", ioe);
                throw ioe;
            } finally {
                // We simply MUST ensure this file is deleted after we've finished with it, otherwise
                // we'll get thousands of the little buggers cluttering the file system.
                if (temp != null) {
                    FileUtils.deleteQuietly(temp);
                }
            }
        } catch (Exception e) {
            String text = "Exception "
                    + e.getClass().getName()
                    + " using JSCH to execute \""
                    + commandLine
                    + "\" with parameters "
                    + parameters.toString();
            if (stdin != null) {
                text += " and input "
                        + stdin;
            }
            logger.error(text, e);
            throw new ExecutionException(text, e);
        } finally {
            if (session != null) {
                logger.debug("JschInvoker.execute returning session " + session);
                pool.returnObject(parameters, session);
            }
        }
        return results;
    }

    public JschParameters getParameters() {
        return parameters;
    }
}
