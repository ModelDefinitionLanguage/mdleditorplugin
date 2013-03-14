/* File			:  ExecutionRequestHelper.java
 * Project		:  MIFServer
 * Created on	:  Nov 14, 2012
 */
package com.mango.mif.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.commons.io.FileUtils;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.mango.mif.core.api.ResourceUtils;
import com.mango.mif.core.resource.BaseResourcePublisher;
import com.mango.mif.domain.ExecutionRequestBuilder;
import com.mango.mif.exception.MIFException;

/**
 * 
 * A helper class used to prepare execution request builders that are used in tests, it encapsulates all the boilerplate code around publishing files
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class ExecutionRequestHelper {
    /**
     * Creates execution request
     * @param requestID 
     * @return
     * @throws JAXBException 
     * @throws MIFException 
     */
    public static ExecutionRequestBuilder createBasicRExecutionRequestBuilder(BaseResourcePublisher resourcePublisher) throws JAXBException, MIFException {
        ExecutionRequestBuilder requestBuilder = createRequestBuilder();
        String requestID = publishFiles(resourcePublisher);
        requestBuilder.setRequestId(requestID);
        File scriptFile = getScriptFile();
        requestBuilder.setExecutionFile(scriptFile.getName());
        return requestBuilder;
    }
    
    /**
     * Creates execution request
     * @param requestID 
     * @return
     * @throws JAXBException 
     * @throws MIFException 
     */
    public static ExecutionRequestBuilder createRExecutionRequestBuilderForInputDirectory(BaseResourcePublisher resourcePublisher, File dataDirectory) throws JAXBException, MIFException {
        ExecutionRequestBuilder requestBuilder = createRequestBuilder();
        List<File> files = ResourceUtils.listFiles(dataDirectory, new FilenameFilter() {
            
            @Override
            public boolean accept(File dir, String name) {
                return true;
            }
        });
        String requestID = publishFiles(resourcePublisher, files, dataDirectory);
        requestBuilder.setRequestId(requestID);
        Iterable<File> scriptFiles = Iterables.filter(Arrays.asList(dataDirectory.listFiles()), new Predicate<File> () {

            @Override
            public boolean apply(File file) {
                return file.getName().toLowerCase().endsWith(".r");
            }
            
        });
        File scriptFile = scriptFiles.iterator().next();
        requestBuilder.setExecutionFile(scriptFile.getName());
        return requestBuilder;
    }
    /**
     * Creates execution request
     * @param requestID 
     * @return
     * @throws JAXBException 
     * @throws MIFException 
     */
    public static ExecutionRequestBuilder createExecutionRequestBuilderForInputDirectory(BaseResourcePublisher resourcePublisher, File dataDirectory, final String scriptFilePath) throws JAXBException, MIFException {
        ExecutionRequestBuilder requestBuilder = createRequestBuilder();
        List<File> files = ResourceUtils.listFiles(dataDirectory, new FilenameFilter() {
            
            @Override
            public boolean accept(File dir, String name) {
                return true;
            }
        });
        String requestID = publishFiles(resourcePublisher, files, dataDirectory);
        requestBuilder.setRequestId(requestID);
        requestBuilder.setExecutionFile(scriptFilePath);
        return requestBuilder;
    }
    /**
     * Creates execution request
     * @param requestID 
     * @return
     * @throws JAXBException 
     * @throws MIFException 
     */
    public static ExecutionRequestBuilder createNONMEMExecutionRequestBuilderForInputDirectory(BaseResourcePublisher resourcePublisher, File dataDirectory) throws JAXBException, MIFException {
        ExecutionRequestBuilder requestBuilder = createRequestBuilder();
        List<File> files = ResourceUtils.listFiles(dataDirectory, new FilenameFilter() {
            
            @Override
            public boolean accept(File dir, String name) {
                return true;
            }
        });
        String requestID = publishFiles(resourcePublisher, files, dataDirectory);
        requestBuilder.setRequestId(requestID);
        Iterable<File> scriptFiles = Iterables.filter(Arrays.asList(dataDirectory.listFiles()), new Predicate<File> () {

            @Override
            public boolean apply(File file) {
                return file.getName().toLowerCase().endsWith(".ctl");
            }
            
        });
        File scriptFile = scriptFiles.iterator().next();
        requestBuilder.setExecutionFile(scriptFile.getName());
        return requestBuilder;
    }
    /**
     * Creates request builder
     * @return
     */
    private static ExecutionRequestBuilder createRequestBuilder() {
        ExecutionRequestBuilder requestBuilder = new ExecutionRequestBuilder();
        return requestBuilder;
    }
    
    private static File getScriptFile() {
        return FileUtils.toFile(ExecutionRequestHelper.class.getResource("test.R"));
    }

    private static File getDataFile() {
        return FileUtils.toFile(ExecutionRequestHelper.class.getResource("data.csv"));
    }
    /**
     * Creates request input parameters
     * @return
     * @throws MIFException 
     */
    private static String publishFiles(BaseResourcePublisher publisher) throws MIFException {
        File fileA = getScriptFile();
        File fileB = getDataFile();
        File root = fileA.getParentFile();
        publisher.addFile(fileA);
        publisher.addFile(fileB);
        publisher.setRootDirectory(root);
        return publisher.publish();
    }
    /**
     * Creates request input parameters
     * @return
     * @throws MIFException 
     */
    private static String publishFiles(BaseResourcePublisher publisher, List<File> files, File rootDir) throws MIFException {
        publisher.addFiles(files);
        publisher.setRootDirectory(rootDir);
        return publisher.publish();
    }
}
