/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.systemtest;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.log4j.Logger;
import org.junit.runners.Parameterized;

/**
 * A super-class for Converters acceptance tests.
 */
public class ConverterATParent {
    /**
     * A directory name where all results from test suite are copied into
     */
    private static final String RESULTS_DIRECTORY_NAME = "results";
    private static final Logger LOG = Logger.getLogger(ConverterATParent.class);
    /**
     * Collects working directories from all individual test cases into single directory, it ignores any 
     * converter and nonmem temporary files.
     * 
     * @param acceptanceTestSuiteWorkingDirectory - a working directory of the test suite
     */
    public static void collectResults(File acceptanceTestSuiteWorkingDirectory) {
        IOFileFilter ignored = FileFilterUtils
                .or( FileFilterUtils.suffixFileFilter(".stderr"), FileFilterUtils.suffixFileFilter(".stdout"), 
                    FileFilterUtils.suffixFileFilter(".res"), FileFilterUtils.suffixFileFilter(".set"),
                    FileFilterUtils.suffixFileFilter(".tmp"),FileFilterUtils.suffixFileFilter(".exe"),
                    FileFilterUtils.suffixFileFilter(".mod"),FileFilterUtils.suffixFileFilter(".txt"),
                    FileFilterUtils.suffixFileFilter(".f90",IOCase.INSENSITIVE),FileFilterUtils.suffixFileFilter(".o"),
                    FileFilterUtils.suffixFileFilter(".bat",IOCase.INSENSITIVE),FileFilterUtils.suffixFileFilter(".lnk",IOCase.INSENSITIVE),
                    FileFilterUtils.suffixFileFilter(".out"),
                    FileFilterUtils.nameFileFilter("FCON"), FileFilterUtils.nameFileFilter("FREPORT"), 
                    FileFilterUtils.nameFileFilter("FDATA"), FileFilterUtils.nameFileFilter("FSIZES"), 
                    FileFilterUtils.nameFileFilter("FMSG"), FileFilterUtils.nameFileFilter("FSTREAM"), 
                    FileFilterUtils.nameFileFilter("FSUBS"), FileFilterUtils.nameFileFilter("newline") 
               );
        IOFileFilter fileFilter = FileFilterUtils.notFileFilter(ignored);
        File collectedResultFilesDirectory = new File(acceptanceTestSuiteWorkingDirectory,RESULTS_DIRECTORY_NAME);
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(acceptanceTestSuiteWorkingDirectory.toPath())) {
            for (Path path : directoryStream) {
                if(path.toFile().isDirectory()) {
                    FileUtils.copyDirectory(path.toFile(), collectedResultFilesDirectory, fileFilter);
                }
            }
        } catch (IOException ex) {
            LOG.error("Error when collecting all results into a single directory.", ex);
        }
    }
    
    private final String model;
    private final File workingDirectory;
    
    /**
     * Construct an instance of this test class for a particular model.
     * 
     *  This constructor is to be invoked by the {@link Parameterized} JUnit runner extemdomg classes
     *  are expected to provide the parameter-provider method.
     * <p>
     * @param workingDirectory - working directory where the given model should be invoked in
     * @param model - the model path relative to the workingDirectory (and testDataDir)
     * @param testDataDir - the location of the source test data directory
     */
    public ConverterATParent(final File workingDirectory, final String model, final File testDataDir) {
        this.model = model;
        this.workingDirectory = workingDirectory;
        try {
            FileUtils.copyDirectory(testDataDir, workingDirectory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * @return working directory location
     */
    public File getWorkingDirectory() {
        return workingDirectory;
    }
    
    /**
     * @return A path of the model file that is to be executed, relative to the workingDirectory
     */
    public String getModel() {
        return model;
    }
    
    /**
     * @return absolute File path to a model 
     */
    public File getModelAbsoluteFile() {
        return new File(getWorkingDirectory(), getModel());
    }
}