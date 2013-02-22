package com.mango.mif.refclient;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.xml.bind.JAXBException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.util.concurrent.UncaughtExceptionHandlers;
import com.mango.mif.domain.ExecutionRequest;
import com.mango.mif.domain.ExecutionType;
import com.mango.mif.refclient.helper.HttpClientHelper;
import com.mango.mif.refclient.helper.PerfTestExecutionRequestBuilder;
import com.mango.mif.utils.encrypt.EncrypterFactory;

/**
 * Simple performance test client.
 */
public class PerfClient {

	private static final Logger log = Logger.getLogger(PerfClient.class);

	private final Map<String, PerfStat> pendingJobs = Maps.newConcurrentMap();
	private final Map<String, PerfStat> successfulJobs = Maps
			.newConcurrentMap();
	private final Map<String, PerfStat> failedJobs = Maps.newConcurrentMap();

	private int totalSuccess = 0;
	private int totalFailed = 0;
	
	private long intervalMs = 500;

	private Set<Integer> failedBatches = Sets.newHashSet();

	private boolean shouldMonitorJobs;

	private static final String ERROR_OBTAINING_STATUS = "ERROR OBTAINING STATUS";
	
	// Set defaults
	private String url = "http://localhost:8080/MIFServer/REST/services/";	
	
	private String mifshare;

	private static CountDownLatch latch;
	private String userName;
	private String userPassword;
	/**
	 * PerfClient.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public PerfClient(boolean shouldMonitorJobs) throws FileNotFoundException, IOException {

		this.shouldMonitorJobs = shouldMonitorJobs;
	}
	
	public void submit(String batches, String jobs, String execName) {
		
		int numBatches = Integer.valueOf(batches);
		int numJobs = Integer.valueOf(jobs);
		
		Preconditions.checkArgument(numBatches > 0);		
		Preconditions.checkArgument(numJobs > 0);
		//FIXME That won't submit all jobs for all execution types at once, each execution type is processed sequentially, so in fact we performance test just a single Connector at once.
		if (execName.equals("nm")) {
			submitBatch(numBatches, numJobs, ExecutionType.NMFE);
		} else if (execName.equals("psn")) {
			submitBatch(numBatches, numJobs, ExecutionType.PsN_EXEC);
		} else if (execName.equals("bs")) {
			submitBatch(numBatches, numJobs, ExecutionType.PsN_Bootstrap);
		} else if (execName.equals("scm")) {
			submitBatch(numBatches, numJobs, ExecutionType.PsN_SCM);
		} else if (execName.equals("vpc")) {
			submitBatch(numBatches, numJobs, ExecutionType.PsN_VPC);
		} else if (execName.equals("r")) {
			submitBatch(numBatches, numJobs, ExecutionType.R_Script);
		}
	}

	/**
	 * Update the statistics of a given job.
	 * 
	 * @param helper
	 * @param jobId
	 */
	private void updateJobStatistics(HttpClientHelper helper, String jobId) {

		String jobStatus = helper.checkStatus(jobId);

		if (jobStatus != null) {

			if (complete(jobStatus)) {

				PerfStat stats = pendingJobs.get(jobId);
				stats.setEnd(System.currentTimeMillis());
				successfulJobs.put(jobId, stats);
				pendingJobs.remove(jobId);

			} else if (failed(jobStatus)) {

				PerfStat stats = pendingJobs.get(jobId);
				stats.setEnd(System.currentTimeMillis());
				failedJobs.put(jobId, stats);
				pendingJobs.remove(jobId);

			}

			// else running

		} else {
			PerfStat stats = new PerfStat();
			stats.setStatus(ERROR_OBTAINING_STATUS);
			pendingJobs.put(jobId, stats);
		}
	}

	private boolean failed(String status) {
		return status.equals("FAILED");
	}

	private boolean complete(String status) {
		return status.equals("COMPLETED");
	}
	
	public void setMIFUrl(String url) {
		if (StringUtils.isNotEmpty(url)) {
			this.url = url;
		}
	}

	public void setMIFShare(String mifshare) {
		this.mifshare = mifshare;
	}

	/**
	 * Start a job submission thread.
	 * 
	 * @param execType
	 */
	private void startJobSubmissionThread(final int batchNum, final int jobNum,
			final ExecutionType execType) {

		Runnable r = new Runnable() {

			@Override
			public void run() {

				try {

					submitJob();

				} catch (Throwable t) {
					log.error("Exception thrown ", t);
				}
			}

			private void submitJob() throws JAXBException {

				HttpClientHelper helper = new HttpClientHelper(url);
				FilePublisher publisher = new FilePublisher(mifshare);

				UUID uuid = helper.prepareJob(userName,EncrypterFactory.getEncrypter().encrypt(userPassword) );
				Preconditions.checkNotNull(uuid, "Could not generate UUID.  Server down?");
				
				try {
					publisher.publishDataResourceFiles(execType, uuid);
				} catch (Exception e) {
					System.err.println("Could not publish the data files for " + execType.name() + " " + e);			
					System.exit(-1);
				}

                PerfTestExecutionRequestBuilder builder = new PerfTestExecutionRequestBuilder().buildJobType(execType, batchNum, jobNum,
                    uuid).setUserName(userName).setEncryptedUserPassword(EncrypterFactory.getEncrypter().encrypt(userPassword));
                ExecutionRequest request = builder.getExecutionRequest();
				
				Preconditions.checkNotNull(request);

				helper = new HttpClientHelper(url);

				String submittedJobId = helper.executeJob(request);
				if (submittedJobId != null) {
					PerfStat stats = new PerfStat();
					stats.setStart(System.currentTimeMillis());
					stats.setStatus("JOB_NOT_YET_CONSUMED");
					pendingJobs.put(submittedJobId, stats);
					latch.countDown();
				}
			}
		}; 
		Thread t = new Thread(r);
		t.setUncaughtExceptionHandler(UncaughtExceptionHandlers.systemExit());
		t.start();
	}

	/**
	 * Monitor the batch until it is complete.
	 * 
	 * @param batchNumber
	 * @param intervalMs
	 */
	private void monitorBatch(int batchNumber) {

		log.info("\n\n - Monitoring Batch [" + batchNumber + "] - \n");
		HttpClientHelper helper = new HttpClientHelper(url);
		while (pendingJobs.size() > 0) {

			for (String jobId : pendingJobs.keySet()) {

				updateJobStatistics(helper, jobId);
				sleep(intervalMs);

				int totalJobs = this.pendingJobs.size()
						+ this.successfulJobs.size() + this.failedJobs.size();
				
				log.info("Currently running: " + this.pendingJobs.size()
						+ ", Successful: [" + this.successfulJobs.size() + "]"
						+ ", Failed: [" + this.failedJobs.size()
						+ "] out of a total of " + totalJobs);
			}
		}
	}

	/**
	 * Display a summary of the batch.
     * FIXME For multiple execution types the summary messages give false failures because the maps do not hold execution types.
     * TODO It would  be also nice to print out the cause of a job failure. It will be a nightmare to investigate why performance tests failed when there just a single job failed out of 1000.  Currently there is no REST method on JobService to retrieve execution response.
	 */
	private void displayBatchSummary() {

		System.out.println("\n- Successful Jobs - \n");

		for (String key : successfulJobs.keySet()) {
			PerfStat job = successfulJobs.get(key);
			System.out.println("Job: " + key + " completed in "
					+ job.getTimeTakenSecs() + " secs ");
		}

		if (failedJobs.size() > 0) {

			System.out.println("\n\n- Failed Jobs - \n");
			for (String key : failedJobs.keySet()) {
				PerfStat job = failedJobs.get(key);
				System.out.println("Job: " + key + " completed in "
						+ job.getTimeTakenSecs() + " secs ");
			}
		}

		if (failedJobs.size() > 0) {
			System.out.println("\nBATCH FAILED\n");
		} else {
			System.out.println("\nBATCH PASSED");
		}
	}

	/**
	 * Submit a batch of jobs.
	 * 
	 * @param concurrentJobsPerBatch
	 * @param numBatches
	 * @param psnExec
	 */
	private void submitBatch(int numBatches, int concurrentJobsPerBatch,
			ExecutionType execType) {

		Stopwatch watch = new Stopwatch().start();
		for (int i = 0; i < numBatches; i++) {

			int batchNum = i + 1;
			System.out.println("\nSubmitting concurrent " + execType.name() + " job batch [" + batchNum
					+ "] to: " + url + " \n");
			latch = new CountDownLatch(concurrentJobsPerBatch);
			for (int n = 0; n < concurrentJobsPerBatch; n++) {
				int jobNum = n + 1;
				startJobSubmissionThread(batchNum, jobNum, execType);
			}

			try {
				latch.await();
			} catch (InterruptedException e) {

				log.info("Interrupted ", e);
			}

			if (shouldMonitorJobs) {
				monitorBatch(batchNum);
				displayBatchSummary();
				calculateRunningNumbers();
			}
		}
		watch.stop();

		printPerformanceTestResults(execType, watch);
	}

	private void printPerformanceTestResults(ExecutionType execType, Stopwatch watch) {
		
		System.out.println(StringUtils.repeat("#", 100));
		if (totalFailed > 0) {
			System.out.println("\nPERFORMANCE TEST " + execType.name() + " FAILED\n");
			for (int batchNum : failedBatches) {
				System.out.println("Batch number failed: " + batchNum);
			}
		} else {
			System.out.println("\nPERFORMANCE TEST " + execType.name() + " PASSED");
		}

		System.out.println("Total Successful: " + totalSuccess + "\nTotal Failed: "
				+ totalFailed);

		System.out.println("\nPERFORMANCE TEST completed in : "
				+ watch.elapsedTime(TimeUnit.SECONDS) + " secs / "
				+ watch.elapsedTime(TimeUnit.MINUTES) + " min(s) / "
				+ watch.elapsedTime(TimeUnit.HOURS) + " hours\n");

		System.out.println(StringUtils.repeat("#", 100));
	}

	private void sleep(long intervalMs) {

		try {
			Thread.sleep(intervalMs);
		} catch (InterruptedException e) {
			log.error("Interrupted ", e);
		}
	}

	/**
	 * Calculate running numbers.
	 */
	private void calculateRunningNumbers() {		
		totalSuccess += successfulJobs.size();
		totalFailed += failedJobs.size();
		successfulJobs.clear();
		failedJobs.clear();
	}
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    
    public void setIntervalMs(long intervalMs) {
        this.intervalMs = intervalMs;
    }
}
