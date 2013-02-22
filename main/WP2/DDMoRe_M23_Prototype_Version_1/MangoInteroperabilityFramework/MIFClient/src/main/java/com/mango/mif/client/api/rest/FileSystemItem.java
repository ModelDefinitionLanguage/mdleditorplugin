/* File			:  FileContent.java
 * Project		:  MIFClient
 * Created on	:  16 Jul 2012
 */
package com.mango.mif.client.api.rest;

import java.net.URI;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *  JAXB wrapper for the datastream we get back from the REST Service method.
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
@XmlRootElement
//@XmlAccessorType(XmlAccessType.PROPERTY)
public class FileSystemItem {
	
		public static final String FOLDER_ITEM = "directory";
		public static final String FILE_ITEM = "file";
		
		private String name;
		
		private String endPath;
		
	    private URI uri;

	    private String type;

	    /**
	     * 
	     * @return
	     */
	    @XmlElement
		public URI getUri() {
			return uri;
		}

		public void setUri(URI uri) {
			this.uri = uri;
		}

		/**
		 * 
		 * @return
		 */
	    @XmlElement
		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

	    @XmlElement
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@XmlElement
		public String getEndPath() {
			return endPath;
		}

		public void setEndPath(String endPath) {
			this.endPath = endPath;
		}
		
}
