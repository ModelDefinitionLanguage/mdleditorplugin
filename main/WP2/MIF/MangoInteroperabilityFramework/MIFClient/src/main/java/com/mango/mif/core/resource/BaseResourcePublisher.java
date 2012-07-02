/* File			:  BasicResourcePublisher.java
 * Project		:  MIFClient
 * Created on	:  11 Jun 2012
 */
package com.mango.mif.core.resource;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Preconditions;
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
    /**
     * Files list
     */
    protected List<File> files = new ArrayList<File>();
    /**
     * Files with their own root list.
     */
    protected List<Pair<File, File>> rootedFiles = new ArrayList<Pair<File, File>>();
    /**
     * Root directory relative to which files should be copied
     */
    protected File rootDirectory;

	@Override
    public String getRequestID() {
        Preconditions.checkNotNull(requestID, "Request ID is null, files have not yet been published");
        return requestID;
    }

    /**
     * @param files the files to set
     */
    @Override
	public void addFiles(List<File> files) {
        this.files.addAll(files);
    }

    /**
     * @param files the files to set
     */
    @Override
	public void addFile(File file) {
        this.files.add(file);
    }

    /**
     * Add a file with its own root.  So, you want to add the file /usr/share/foo/bar/budgie/myfile1.txt
     * but you want to remove the /usr/share/foo/bar part and just get the file copied across to the target
     * directory as /target/budgie/myfile1.txt
     *
     * @param file The file you want to add, say /usr/share/foo/bar/budgie/myfile1.txt
     * @param root the root you want to subtract when copying, say /usr/share/foo/bar
     */
    @Override
	public void addFile(File file, File root) {
        Pair<File, File> pair = Pair.of(file, root);
        rootedFiles.add(pair);
    }

    /**
     * @param rootDirectory the rootDirectory to set
     */
    @Override
	public void setRootDirectory(File rootDirectory) {
        this.rootDirectory = rootDirectory;
    }

	public void setPassword(String password) {
	}

	public void setUserName(String userName) {
	}

	public void setPort(int port) {
	}
}
