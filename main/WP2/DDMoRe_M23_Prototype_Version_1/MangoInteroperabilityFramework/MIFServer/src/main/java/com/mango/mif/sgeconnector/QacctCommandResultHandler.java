/* File			:  QacctCommandResultHandler.java
 * Project		:  MIFServer
 * Created on	:  Jul 24, 2012
 */
package com.mango.mif.sgeconnector;

import java.io.File;
import java.text.ParseException;

import com.google.common.base.Preconditions;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.InvokerResult;
import com.mango.mif.core.exec.invoker.InvokerHelper;
import com.mango.mif.sge.QacctOutputJobProperty;
import com.mango.mif.sge.SGE;
import com.mango.mif.sge.SGEOutput;
import com.mango.mif.utils.CommonUtils;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * A Qacct command results handler
 */
public class QacctCommandResultHandler extends SGECommandResultHandler {
    /**
     * MIF hidden directory name
     */
    private String mifHiddenDirectory;
    /**
     * SGE STD ERR stream file name
     */
    private String sgeJobStdErrFileName;
    /**
     * SGE STD OUT stream file name
     */
    private String sgeJobStdOutFileName;

    /**
     * Constructor
     * @param successEvent
     */
    public QacctCommandResultHandler(String successEvent) {
        super(successEvent);
    }

    @Override
    public void handle(InvokerResult invokerResult) throws ExecutionException {
        super.handle(invokerResult);
        Preconditions.checkNotNull(mifHiddenDirectory, "MIF hidden directory name not set.");
        Preconditions.checkNotNull(sgeJobStdErrFileName, "SGE job STD ERR file name not set.");
        Preconditions.checkNotNull(sgeJobStdOutFileName, "SGE job STD OUT file name not set.");
        SGEOutput output = outputParser.parseAccountingMessage(invokerResult.getOutputStream());
        String exitStatus = output.get(QacctOutputJobProperty.EXIT_STATUS.getToken());
        String failureStatus = output.get(QacctOutputJobProperty.FAILED.getToken());

        String completionTime = output.get(QacctOutputJobProperty.END_TIME.getToken());
        try {
            job.addToDataMap(SGE.SGE_JOB_END_TIME_KEY, CommonUtils.convertToMilliseconds(completionTime).toString());
        } catch (ParseException e1) {
            LOG.error("could not parse the SGE job end time", e1);
            job.addToDataMap(SGE.SGE_JOB_END_TIME_KEY, "-1");
        }

        if (exitStatus == null) {
            InvokerResult gridProcessResult = buildInvokerResultForGridExecution(-1);
            throw new ExecutionException(buildErrorMessage("Tool execution failed on the grid.", gridProcessResult), new Exception(buildErrorMessage("Job exit status has not been returned by qacct. ", invokerResult)));
        }
        if (failureStatus == null) {
            InvokerResult gridProcessResult = buildInvokerResultForGridExecution(-1);
            throw new ExecutionException(buildErrorMessage("Tool execution failed on the grid.", gridProcessResult), new Exception(buildErrorMessage("Job failure status has not been returned by qacct.", invokerResult)));
        }
        int failureCode = 0;

        String[] failureMsg = failureStatus.split(" ");

        failureCode = Integer.parseInt(failureMsg[0]);

        if (failureCode != 0) {
            InvokerResult gridProcessResult = buildInvokerResultForGridExecution(-1);
            throw new ExecutionException(buildErrorMessage("Tool execution failed on the grid.", gridProcessResult), new Exception(buildErrorMessage("Job processing on grid failed.", invokerResult)));
        }

        int exitCode = 0;

        exitCode = Integer.parseInt(exitStatus);

        if (exitCode != 0) {
            InvokerResult gridProcessResult = buildInvokerResultForGridExecution(exitCode);
            throw new ExecutionException(buildErrorMessage("Process running on grid returned with code " + exitCode + ".", gridProcessResult), new Exception(buildErrorMessage("Tool execution failed on the grid.", invokerResult)));
        }

    }

    /**
     * Builds a invoker result representation of grid execution
     * @param exitCode
     * @return
     * @throws ExecutionException
     */
    private InvokerResult buildInvokerResultForGridExecution(int exitCode) throws ExecutionException {
        InvokerHelper invokerHelper = new InvokerHelper(job.getInvoker());
        File mifHiddenDir = new File(job.getRequestOutputDirectory(), mifHiddenDirectory);
        File gridErrStream = new File(mifHiddenDir, sgeJobStdErrFileName);
        File gridOutStream = new File(mifHiddenDir, sgeJobStdOutFileName);

        LOG.debug("File containing error stream of a script executed on grid:" + gridErrStream.getAbsolutePath());
        LOG.debug("File containing output stream of a script executed on grid:" + gridOutStream.getAbsolutePath());

        String stdErrFileContent = invokerHelper.getFileContents(gridErrStream);
        if (stdErrFileContent == null) {
            stdErrFileContent = "Does not exist.";
        }
        String stdOutFileContent = invokerHelper.getFileContents(gridOutStream);
        if (stdOutFileContent == null) {
            stdOutFileContent = "Does not exist.";
        }
        LOG.debug("Grid process error stream: " + stdErrFileContent);
        LOG.debug("Grid process output stream: " + stdOutFileContent);

        InvokerResult gridProcessResult = new InvokerResult("", stdOutFileContent, stdErrFileContent, exitCode);
        return gridProcessResult;
    }

    public void setMifHiddenDirectory(String mifHiddenDirectory) {
        this.mifHiddenDirectory = mifHiddenDirectory;
    }

    public void setSgeJobStdErrFileName(String sgeJobStdErrFileName) {
        this.sgeJobStdErrFileName = sgeJobStdErrFileName;
    }

    public void setSgeJobStdOutFileName(String sgeJobStdOutFileName) {
        this.sgeJobStdOutFileName = sgeJobStdOutFileName;
    }
}
