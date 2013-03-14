/* File			:  FileList.java
 * Project		:  MIFClient
 * Created on	:  16 Jul 2012
 */
package com.mango.mif.client.api.rest;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * JAXB wrapper for the List of file links we get back from the REST Service method.
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
@XmlRootElement(name = "fileList")
//@XmlAccessorType(XmlAccessType.PROPERTY)
public class FileList {
	
	
	/**
	 * List of wrapper objects that model a file/folder item.
	 */
	private List<FileSystemItem> fileSystemItems = new ArrayList<FileSystemItem>();


	/**
	 * @return the files
	 */
	@XmlElementWrapper(name="fileSystemItems")
	@XmlElement(name="fileSystemItem")
	public List<FileSystemItem> getFileSystemItems() {
		return fileSystemItems;
	}

	
	/**
	 * @param files the files to set
	 */
	public void setFileSystemItems(List<FileSystemItem> fileSystemItems) {
		this.fileSystemItems = fileSystemItems;
	}

}
