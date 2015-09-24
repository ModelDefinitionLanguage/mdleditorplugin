/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.systemtest;

import java.io.File;
import static org.junit.Assert.fail;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.google.common.base.Preconditions;


/**
 * A checker that performs Nonmem compilation step.
 */
public class NmTranCompilationChecker extends DefaultConverterOutputFailureChecker {
    private static final Logger LOG = Logger.getLogger(NmTranCompilationChecker.class);
    private final String compilerCommand;
    private final File compilerLocation;
    public NmTranCompilationChecker(String compilerCommand, File compilerLocation) {
        super(5);
        Preconditions.checkArgument(StringUtils.isNotBlank(compilerCommand), "Nonmem command may not be blank.");
        Preconditions.checkArgument(compilerLocation.exists(), "Nonmem executable must exist.");
        this.compilerCommand = compilerCommand;
        this.compilerLocation = compilerLocation;
    }
    @Override
    public void check(File expectedOutputFile, File stdoutFile, File stderrFile) {
        super.check(expectedOutputFile, stdoutFile, stderrFile);
        
        CommandRunner runner = new CommandRunner()
        .setCommand(String.format(compilerCommand,compilerLocation,expectedOutputFile.getName(),expectedOutputFile.getName()+".res"))
        .setName(expectedOutputFile.getName())
        .setWorkingDirectory(expectedOutputFile.getParentFile())
        .setDryRun(Boolean.parseBoolean(System.getProperty("NmTranCompilationChecker.dryRun","false")));
        
        try {
            runner.run();
        } catch (Exception e) {
            LOG.error(e);
            fail(String.format("Failed to perform NM-TRAN compilation of [%s].\n Cause:\n %s",expectedOutputFile,e.getMessage()));
        }
    }

}
