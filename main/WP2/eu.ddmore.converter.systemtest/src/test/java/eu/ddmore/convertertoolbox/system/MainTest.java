package eu.ddmore.convertertoolbox.system;

import static org.junit.Assert.fail;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.junit.Test;


public class MainTest {
    
    private final static Logger LOGGER = Logger.getLogger(MainTest.class);

    final static String pathToModelsDir = "/eu/ddmore/testdata/models/";
    final static String subpathToMdlModels = "mdl";
    final static String subpathToPharmmlModels = "pharmml";
    
    final static String PARENT_WORKING_DIR = "target/WorkingDir";
    
    final static String CONVERTER_TOOLBOX_EXECUTABLE = "target/converter-toolbox-bundle-0.0.1-SNAPSHOT/convert.bat";
    
    // We'll consider a conversion to have failed if the converted output file has a size that is less than this number of bytes
    final static int FILE_SIZE_THRESHOLD = 50;
    
    private Set<File> mdlModels = new LinkedHashSet<File>();
    private Set<File> pharmmlModels = new LinkedHashSet<File>();
    
    
    @Test
    public void runTests() throws IOException {
        final URL urlToModelsDir = MainTest.class.getResource(pathToModelsDir);
        final URLConnection conn = urlToModelsDir.openConnection();
        if (conn instanceof JarURLConnection) {
            
            final JarFile jarFile = ((JarURLConnection) conn).getJarFile();
            final Enumeration<JarEntry> jarEntries = jarFile.entries();
            while (jarEntries.hasMoreElements()) {
                final JarEntry jarEntry = jarEntries.nextElement();
                processJarEntry(jarEntry);
            }
            
            runMdlConversions();
            copyGeneratedPharmmlFiles();
            runPharmmlConversions();
            
        } else {
            fail("Please disable workspace resolution on this project in order to run the tests in Eclipse.");
        }
    }
    
    private void processJarEntry(final JarEntry jarEntry) {
        if (! jarEntry.isDirectory()) {
            if (jarEntry.getName().startsWith(pathToModelsDir.substring(1) + subpathToMdlModels)) {
                processMdlModel(jarEntry);
            } else if (jarEntry.getName().startsWith(pathToModelsDir.substring(1) + subpathToPharmmlModels)) {
                processPharmmlModel(jarEntry);
            }
        }
    }
    
    private void processMdlModel(final JarEntry jarEntry) {
        LOGGER.info("Adding MDL model: " + jarEntry.getName());
        this.mdlModels.add(copyFileOutOfJar(jarEntry));
    }
    
    private void processPharmmlModel(final JarEntry jarEntry) {
        LOGGER.info("Adding PharmML model: " + jarEntry.getName());
        this.pharmmlModels.add(copyFileOutOfJar(jarEntry));
    }
    
    private File copyFileOutOfJar(final JarEntry jarEntry) {
        final File destFile = new File(PARENT_WORKING_DIR, jarEntry.getName().substring(pathToModelsDir.length() - 1));
        try {
            FileUtils.copyInputStreamToFile(MainTest.class.getResourceAsStream("/" + jarEntry.getName()), destFile);
        } catch (final IOException ioe) {
            fail("IOException thrown when trying to copy file " + jarEntry.getName() + " out of the testdata models JAR: " + ioe);
        }
        return destFile;
    }
    
    private void runMdlConversions() {
        final Iterator<File> modelFilesIter = this.mdlModels.iterator();
        while (modelFilesIter.hasNext()) {
            runMdlConversion(modelFilesIter.next());
        }
    }
    
    private void runPharmmlConversions() {
        final Iterator<File> modelFilesIter = this.pharmmlModels.iterator();
        while (modelFilesIter.hasNext()) {
            runPharmmlConversion(modelFilesIter.next());
        }
    }
    
    private void runMdlConversion(final File modelFile) {
        runConversion(modelFile, ".xml", "MDL", "5.1.6", "PharmML", "0.3.0");
    }
    
    private void copyGeneratedPharmmlFiles() {
        final Iterator<File> modelFilesIter = this.mdlModels.iterator();
        while (modelFilesIter.hasNext()) {
            final File modelFile = modelFilesIter.next();
            final File srcDir = new File(modelFile.getParentFile(), "output");
            final File destDir = new File(PARENT_WORKING_DIR, "pharmml-generated-from-MDL/" + modelFile.getParentFile().getName());
            try {
                FileUtils.copyDirectory(srcDir, destDir);
            } catch (IOException ioe) {
                fail("IOException thrown while trying to copy directory " + srcDir + " to " + destDir);
            }
            for (final File generatedPharmmlModelFile : destDir.listFiles()) {
                LOGGER.info("Found generated PharmML model file " + generatedPharmmlModelFile);
                this.pharmmlModels.add(generatedPharmmlModelFile);
            }
        }
    }
    
    private void runPharmmlConversion(final File modelFile) {
        runConversion(modelFile, ".ctl", "PharmML", "0.3.0", "NMTRAN", "7.2");
    }
    
    private boolean runConversion(final File modelFile, final String outputFileExtension,
            final String sourceLang, final String sourceVersion, final String targetLang, final String targetVersion) {
        
        final File outputDir = new File(modelFile.getParent(), "output");
        outputDir.mkdir();
        
        final File stdoutFile = new File(FilenameUtils.removeExtension(modelFile.getAbsolutePath()) + ".convert.stdout");
        final File stderrFile = new File(FilenameUtils.removeExtension(modelFile.getAbsolutePath()) + ".convert.stderr");
        
        
        // Build up the command line to execute
        CommandLine cmdLine = new CommandLine("cmd");
        cmdLine.addArgument("/c");
        cmdLine.addArgument(new File(CONVERTER_TOOLBOX_EXECUTABLE).getAbsolutePath());
        cmdLine.addArgument(modelFile.getAbsolutePath());
        cmdLine.addArgument(outputDir.getAbsolutePath());
        cmdLine.addArgument(sourceLang);
        cmdLine.addArgument(sourceVersion);
        cmdLine.addArgument(targetLang);
        cmdLine.addArgument(targetVersion);
        
        // Set up some output streams to handle the standard out and standard error
        final BufferedOutputStream stdoutOS;
        final BufferedOutputStream stderrOS;
        try {
            stdoutOS = new BufferedOutputStream(new FileOutputStream(stdoutFile));
            IOUtils.write("Invoking converter toolbox command : " + cmdLine + "\n\n", stdoutOS);
            stderrOS = new BufferedOutputStream(new FileOutputStream(stderrFile));
        } catch (IOException ioe) {
            fail("IOException thrown while trying to set up output streams for converter stdout and stderr: " + ioe);
            return false; // Not reached but keeps compiler happy
        }
        
        // Create the executor object, providing a stream handler that will avoid
        // the child process becoming blocked because nothing is consuming its output,
        // and also a timeout
        DefaultExecutor executor = new DefaultExecutor();
        executor.setExitValue(0); // Required "success" return code
        ExecuteWatchdog watchdog = new ExecuteWatchdog(15000); // Will kill the process after 15 seconds
        executor.setWatchdog(watchdog);
        PumpStreamHandler pumpStreamHandler = new PumpStreamHandler(stdoutOS, stderrOS);
        executor.setStreamHandler(pumpStreamHandler);

        // Finally actually execute the batch script
        try {
            executor.execute(cmdLine);
        } catch (final ExecuteException eex) { // Command has failed or timed out
            try {
                IOUtils.write("\n\nError code " + eex.getExitValue() + " returned from converter toolbox command : " + cmdLine + "\n\n", stderrOS);
                IOUtils.write("ExecuteException cause: " + eex.getMessage(), stderrOS);
            } catch (IOException ioe) {
            }
        } catch (final IOException ioe) {
            fail("IOException occurred trying to execute converter batch script: " + ioe);
        }
        
        try {
            stdoutOS.close();
            stderrOS.close();
        } catch (final IOException e) {
        }
        
        // Test if the conversion was successful
        final File expectedOutputFile = new File(outputDir, FilenameUtils.getBaseName(modelFile.getPath()) + outputFileExtension); 
        final boolean passed = isConversionErrorFree(expectedOutputFile, stderrFile);
        
        if (passed) {
            LOGGER.info("PASSED conversion of " + modelFile);
        } else {
            LOGGER.error("FAILED conversion of " + modelFile);
        }
        return passed;
    }
    
    private boolean isConversionErrorFree(final File expectedOutputFile, final File stderrFile) {
        if (! expectedOutputFile.exists()) {
            return false;
        }
        if (FileUtils.sizeOf(expectedOutputFile) < FILE_SIZE_THRESHOLD) {
            return false;
        }
        if (FileUtils.sizeOf(stderrFile) > 0) {
            // Errors were produced onto the standard error stream
            return false;
        }
        return true;
    }
    
}
