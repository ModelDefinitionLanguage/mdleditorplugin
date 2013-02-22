/* File			:  BasicResourcePublisher.java
 * Project		:  MIFClient
 * Created on	:  11 Jun 2012
 */
package com.mango.mif.core.resource;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.common.base.Preconditions;
import com.mango.mif.core.exec.CommandBuilder;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.utils.Pair;

/**
 * Basic implementation of the resource publisher.
 * 
 * @ToDo add the DESDecryptor and use it to decrypt the password
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 * 
 */
public abstract class BaseResourcePublisher extends BaseSharedLocationOperation implements ResourcePublisher {

    private static Logger logger = Logger.getLogger(BaseResourcePublisher.class);
    /**
     * Files list
     */
    protected List<File> filesToPublish = new ArrayList<File>();
    /**
     * Files with their own root list.
     */
    protected List<Pair<File, File>> rootedFiles = new ArrayList<Pair<File, File>>();
    /**
     * Root directory relative to which files should be copied
     */
    protected File rootDirectory;
    /**
     * The command builder
     */
    protected CommandBuilder commandBuilder;
    /**
     * The request attributes
     */
    protected Map<String, String> requestAttributes;
    /**
     * The preamble for the grid host.
     */
    protected String gridHostPreamble;
    /**
     * The preamble for the script host.
     */
    protected String submitHostPreamble;

    @Override
    public String getRequestID() {
        Preconditions.checkNotNull(requestID, "Request ID is null, files have not yet been published");
        return requestID;
    }

    /**
     * @param files
     *            the files to set
     */
    @Override
    public void addFiles(List<File> files) {
        this.filesToPublish.addAll(files);
    }

    /**
     * @param filesToPublish
     *            the files to set
     */
    @Override
    public void addFile(File file) {
        this.filesToPublish.add(file);
    }

    /**
     * Add a file with its own root. So, you want to add the file
     * /usr/share/foo/bar/budgie/myfile1.txt but you want to remove the
     * /usr/share/foo/bar part and just get the file copied across to the target
     * directory as /target/budgie/myfile1.txt
     * 
     * @param file
     *            The file you want to add, say
     *            /usr/share/foo/bar/budgie/myfile1.txt
     * @param root
     *            the root you want to subtract when copying, say
     *            /usr/share/foo/bar
     */
    @Override
    public void addFile(File file, File root) {
        Pair<File, File> pair = Pair.of(file, root);
        rootedFiles.add(pair);
    }

    /**
     * @param rootDirectory
     *            the rootDirectory to set
     */
    @Override
    public void setRootDirectory(File rootDirectory) {
        this.rootDirectory = rootDirectory;
    }

    public CommandBuilder getCommandBuilder() {
        return commandBuilder;
    }

    public void setSubmitHostPreamble(String submitHostPreamble) {
        this.submitHostPreamble = submitHostPreamble;
    }

    public void setGridHostPreamble(String gridHostPreamble) {
        this.gridHostPreamble = gridHostPreamble;
    }

    @Override
    public void setResourceCopier(ResourceCopier resourceCopier) {
        throw new RuntimeException("Method Not Supported Exeception");
    }

    /**
     * This code was originally in the constructor of the shell based resource
     * publisher, however, the execution of this obviously depends upon the
     * existence and populatedness of the request attributes, which aren't
     * available in the CTOR.
     * 
     * @param requestAttributes
     *            The request attributes to force into the freemarker (command
     *            builder) context
     * @throws ExecutionException
     *             If we screw up.
     */
    @Override
    public void setCommandBuilderContext(Map<String, String> requestAttributes) throws ExecutionException {
        this.requestAttributes = requestAttributes;

        try {
            commandBuilder.populateCommandBuilderContext(this.requestAttributes);
            commandBuilder.setGridHostPreamble(this.gridHostPreamble);
            commandBuilder.setSubmitHostPreamble(this.submitHostPreamble);
        } catch (ExecutionException e) {
            logger.error("Caught Exception while initialising the freemarker context", e);
        }
    }

    @Override
    public void setTheCommandBuilderContext(Map<String, String> requestAttributes, String submitHostPreamble, String gridHostPreamble) throws ExecutionException {
        this.gridHostPreamble = gridHostPreamble;
        this.submitHostPreamble = submitHostPreamble;
        setCommandBuilderContext(requestAttributes);
    }

}
