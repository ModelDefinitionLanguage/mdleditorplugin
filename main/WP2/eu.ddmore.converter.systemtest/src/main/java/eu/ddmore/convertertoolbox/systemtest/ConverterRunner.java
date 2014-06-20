package eu.ddmore.convertertoolbox.systemtest;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;


public class ConverterRunner {
    private final static Logger LOGGER = Logger.getLogger(ConverterRunner.class);

    final static String CONVERTER_TOOLBOX_EXECUTABLE = "target/converter-toolbox-bundle-0.0.1-SNAPSHOT/convert.bat";
    final static String OUTPUT_SUBDIRECTORY = "output";
    
    private final File modelFile;
    private final String outputFileExtension;
    private final String sourceLang;
    private final String sourceVersion;
    private final String targetLang;
    private final String targetVersion;
    // We'll consider a conversion to have failed if the converted output file has a size that is less than this number of bytes
    private final int outputFileSizeThreshhold;
    
    ConverterRunner(final File modelFile, final String outputFileExtension,
            final String sourceLang, final String sourceVersion, final String targetLang, final String targetVersion,
            final int outputFileSizeThreshhold) {
        this.modelFile = modelFile;
        this.outputFileExtension = outputFileExtension;
        this.sourceLang = sourceLang;
        this.sourceVersion = sourceVersion;
        this.targetLang = targetLang;
        this.targetVersion = targetVersion;
        this.outputFileSizeThreshhold = outputFileSizeThreshhold;
    }
    
    boolean run() {
        
        // Define the output directory and stdout and stderr files
        final File outputDir = new File(this.modelFile.getParent(), OUTPUT_SUBDIRECTORY);
        final File stdoutFile = new File(FilenameUtils.removeExtension(this.modelFile.getAbsolutePath()) + ".convert.stdout");
        final File stderrFile = new File(FilenameUtils.removeExtension(this.modelFile.getAbsolutePath()) + ".convert.stderr");
        
        // Initialise output directory and stdout and stderr files
        try {
            FileUtils.deleteDirectory(outputDir);
        } catch (IOException e1) {
            LOGGER.error("Unable to delete directory " + outputDir);
        }
        outputDir.mkdir();
        if (stdoutFile.exists() && !stdoutFile.delete()) {
            LOGGER.error("Unable to delete file " + stdoutFile);
        }
        if (stderrFile.exists() && !stderrFile.delete()) {
            LOGGER.error("Unable to delete file " + stderrFile);
        }
        
        // Build up the command line to execute
        CommandLine cmdLine = new CommandLine("cmd");
        cmdLine.addArgument("/c");
        cmdLine.addArgument(new File(CONVERTER_TOOLBOX_EXECUTABLE).getName());
        cmdLine.addArgument(this.modelFile.getAbsolutePath());
        cmdLine.addArgument(outputDir.getAbsolutePath());
        cmdLine.addArgument(this.sourceLang);
        cmdLine.addArgument(this.sourceVersion);
        cmdLine.addArgument(this.targetLang);
        cmdLine.addArgument(this.targetVersion);
        
        // Set up output streams to handle the standard out and standard error
        final BufferedOutputStream stdoutOS;
        final BufferedOutputStream stderrOS;
        try {
            stdoutOS = new BufferedOutputStream(new FileOutputStream(stdoutFile));
            IOUtils.write("Invoking converter toolbox command : " + cmdLine + "\n\n", stdoutOS);
            stderrOS = new BufferedOutputStream(new FileOutputStream(stderrFile));
        } catch (IOException ioe) {
            throw new RuntimeException("IOException thrown while trying to set up output streams for converter stdout and stderr: " + ioe);
        }
        
        // Create the executor object, providing a stream handler that will avoid
        // the child process becoming blocked because nothing is consuming its output,
        // and also a timeout
        DefaultExecutor executor = new DefaultExecutor();
        executor.setWorkingDirectory(new File(CONVERTER_TOOLBOX_EXECUTABLE).getParentFile());
        executor.setExitValue(0); // Required "success" return code
        ExecuteWatchdog watchdog = new ExecuteWatchdog(30000); // Will kill the process after 15 seconds
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
            throw new RuntimeException("IOException occurred trying to execute converter batch script: " + ioe);
        }
        
        try {
            stdoutOS.close();
            stderrOS.close();
        } catch (final IOException e) {
        }
        
        // Heuristically test if the conversion didn't fail (we can only generically check for failure rather than success)
        final File expectedOutputFile = new File(outputDir, FilenameUtils.getBaseName(this.modelFile.getPath()) + this.outputFileExtension); 
        final boolean passed = isConversionErrorFree(expectedOutputFile, stderrFile);
        
        if (passed) {
            LOGGER.info("PASSED conversion of " + this.modelFile);
        } else {
            LOGGER.error("FAILED conversion of " + this.modelFile);
        }
        return passed;
    }
    
    private boolean isConversionErrorFree(final File expectedOutputFile, final File stderrFile) {
        if (! expectedOutputFile.exists()) {
            return false;
        }
        if (FileUtils.sizeOf(expectedOutputFile) < this.outputFileSizeThreshhold) {
            return false;
        }
        if (FileUtils.sizeOf(stderrFile) > 0) {
            // Errors were produced onto the standard error stream
            return false;
        }
        return true;
    }
    
}
