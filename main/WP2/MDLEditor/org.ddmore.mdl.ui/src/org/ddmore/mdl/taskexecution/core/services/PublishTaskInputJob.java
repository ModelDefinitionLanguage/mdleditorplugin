/**
 * 
 */
package org.ddmore.mdl.taskexecution.core.services;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.ddmore.mdl.taskexecution.core.services.http.TESServer;
import org.ddmore.mdl.ui.internal.MdlActivator;
import org.ddmore.mdl.ui.preference.MDLPreferenceConstants;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * A long-running Job responsible for copying files to a shared location with TES and submitting an execution request to TES.
 * 
 * @author jcarr
 */
public class PublishTaskInputJob extends Job {

    private final transient IFile modelFiles;
    private final transient IFile dataFiles;

    private transient String requestId;
    private transient String jobId;

    /**
     * @param name the name of the job
     */
    public PublishTaskInputJob(final String name, final IFile model, final IFile data) {
        super(name);
        this.modelFiles = model;
        this.dataFiles = data;
    }

    /* (non-Javadoc)
     * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    protected IStatus run(IProgressMonitor monitor) {
        monitor.subTask("Task Execution Service: Publisher");
        IStatus status = Status.OK_STATUS;
        try {

            String symbolicName = MdlActivator.getInstance().getBundle().getSymbolicName();
            if (modelFiles == null) {
                return new Status(IStatus.ERROR, symbolicName, "A model file must be supplied");
            }
            if (dataFiles == null) {
                return new Status(IStatus.ERROR, symbolicName, "A data file must be supplied");
            }

            TESServer serverConn = new TESServer();

            // get the UID for this run 
            this.requestId = serverConn.prepare();

            if (monitor.isCanceled()) {
                return Status.CANCEL_STATUS;
            }

            // move all the input files to the shared dir using the GUID
            try {
                publishFileToSharedDir(
                    modelFiles.getProject().getLocation().append("src-gen")
                            .append(modelFiles.getName().substring(0, modelFiles.getName().length() - 3) + "ctl").toFile(), this.requestId);
                publishFileToSharedDir(dataFiles.getLocation().toFile(), this.requestId);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            if (monitor.isCanceled()) {
                return Status.CANCEL_STATUS;
            }

            // then exec the job
            this.jobId = serverConn.exec(this.requestId, modelFiles.getName().substring(0, modelFiles.getName().length() - 3) + "ctl");

            Thread.sleep(1000);

        } catch (Exception ex) {
            return new Status(IStatus.ERROR, this.getClass().getPackage().getName(), "Error while executing a command", ex);
        }
        return status;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getJobId() {
        return jobId;
    }

    private void publishFileToSharedDir(final File file, final String UID) throws IOException {
        IPreferenceStore preferenceStore = MdlActivator.getInstance().getPreferenceStore();
        final String targetDir = preferenceStore.getString(MDLPreferenceConstants.TES_SHARED_DIR);
        final String targetInputPath = preferenceStore.getString(MDLPreferenceConstants.TES_SHARED_DIR_INPUT);

        if (targetDir == null || targetDir.isEmpty()) {
            throw new IllegalArgumentException("TES Shared Directory Path not set");
        }

        FileUtils.copyFileToDirectory(file, new File(new File(new File(targetDir), UID), targetInputPath), true);
    }

}