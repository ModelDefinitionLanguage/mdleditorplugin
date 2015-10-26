package eu.ddmore.convertertoolbox.systemtest;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;


public class ConverterRunner {
    private static final Logger LOG = Logger.getLogger(ConverterRunner.class);
    public static final String CONVERT_STDERR = ".convert.stderr";
    public static final String CONVERT_STDOUT = ".convert.stdout";
    private static final String CONVERTER_TOOLBOX_EXECUTABLE = new File(System.getProperty("converter-toolbox.executable")).getAbsolutePath();
    
    private final File modelFile;
    private final String outputFileExtension;
    private final String sourceLang;
    private final String sourceVersion;
    private final String targetLang;
    private final String targetVersion;
    private final ConverterOutputFailureChecker converterOutputFailureChecker;
    
    ConverterRunner(final File modelFile, final String outputFileExtension,
            final String sourceLang, final String sourceVersion, final String targetLang, final String targetVersion,
            final ConverterOutputFailureChecker convFailChecker) {
        this.modelFile = modelFile;
        this.outputFileExtension = outputFileExtension;
        this.sourceLang = sourceLang;
        this.sourceVersion = sourceVersion;
        this.targetLang = targetLang;
        this.targetVersion = targetVersion;
        this.converterOutputFailureChecker = convFailChecker;
    }
    
    private File getOutputDirectory() {
    	return this.modelFile.getParentFile();
    }
    
    void run() {
        final File outputDir = getOutputDirectory();
        outputDir.mkdir();

        CommandLine cmdLine = new CommandLine(CONVERTER_TOOLBOX_EXECUTABLE);
        cmdLine.addArgument(modelFile.getAbsolutePath());
        cmdLine.addArgument(outputDir.getAbsolutePath());
        cmdLine.addArgument(sourceLang);
        cmdLine.addArgument(sourceVersion);
        cmdLine.addArgument(targetLang);
        cmdLine.addArgument(targetVersion);
        
        CommandRunner cRunner = new CommandRunner().setName(this.modelFile.getName() + ".convert")
                .setWorkingDirectory(outputDir);
        cRunner.setCommand(cmdLine.toString());
        
        try {
            cRunner.run();
        } catch (Exception e) {
            LOG.error(String.format("Failed to execute %s", cmdLine), e);
            throw new RuntimeException(String.format("Failed to perform conversion of [%s] from [%s, %s] to [%s, %s]",
                modelFile,sourceLang,sourceVersion,targetLang,targetVersion), e);
        }
        // Heuristically test if the conversion didn't fail (we can only generically check for failure rather than success)
        final File expectedOutputFile = new File(outputDir, FilenameUtils.getBaseName(this.modelFile.getPath()) + "." + this.outputFileExtension);
        this.converterOutputFailureChecker.check(expectedOutputFile, cRunner.getStdOut(), cRunner.getStdErr());
    }
    
}
