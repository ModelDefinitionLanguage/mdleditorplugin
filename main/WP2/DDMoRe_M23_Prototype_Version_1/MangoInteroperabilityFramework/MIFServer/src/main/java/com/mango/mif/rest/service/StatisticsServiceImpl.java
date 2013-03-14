package com.mango.mif.rest.service;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import javax.management.MBeanServerConnection;

import org.apache.log4j.Logger;
import org.apache.log4j.RollingFileAppender;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.mango.mif.client.api.rest.StatisticsService;
import com.mango.mif.connector.Connector;
import com.mango.mif.connector.domain.Job;
import com.mango.mif.connector.runner.api.JobRunner;
import com.mango.mif.core.exec.jsch.JschSession;
import com.mango.mif.core.exec.jsch.JschSessionPool;
import com.mango.mif.core.exec.jsch.JschSessionPoolObjectFactory;
import com.mango.mif.core.impl.ConnectorsRegistry;
import com.mango.mif.managers.JobManagementService;
import com.mango.mif.utils.FullThreadDump;
import com.mango.mif.utils.MIFProperties;

/**
 * Simple statistics service for monitoring core MIF operations for performance.
 */
public class StatisticsServiceImpl implements StatisticsService {

	private static final Logger LOG = Logger
			.getLogger(StatisticsServiceImpl.class);

	private ConnectorsRegistry connectorsRegistry;

	private JobManagementService jobManagementService;

	private JschSessionPool sessionPool;

	/**
	 * {@inheritDoc}.
	 */
	public String getStatistics() {

		StringBuilder info = new StringBuilder();
		info.append("<font face='Courier' size='1'>");

		systemConfig(info);

		systemStats(info);

		for (String name : connectorsRegistry.getConnectors().keySet()) {

			Connector conn = connectorsRegistry.getConnector(name);

			// Exclude NMFE as there are two connectors (PsN_EXEC/NMFE) that are
			// effectively the same
			if (!name.startsWith("NMFE")) {
				buildConnectorMetrics(info, name, conn);
			}
		}

		buildJschSessionMetrics(info);
		info.append("<font/>");
		return info.toString();
	}

	private void systemConfig(StringBuilder info) {

		MIFProperties props = MIFProperties.getInstance();
		info.append("<b>MIF Config</b><br><br>");
		appendKeyValue("MIFShare", props.getProperty("mif.shared.location"),
				info);
		appendKeyValue("GridShare",
				props.getProperty("sgeconnector.sgeSharedLocation"), info);
		info.append("<br><br>");
	}

	private void appendKeyValue(String key, String value, StringBuilder info) {
		info.append(key);
		info.append(": ");
		info.append(value);
		info.append("<br>");
	}

	/**
	 * {@inheritDoc}.
	 */
	public String getHeapDump() {
		FullThreadDump dump = new FullThreadDump();
		dump.dump();
		Logger rootLogger = Logger.getRootLogger();
		RollingFileAppender rfa = (RollingFileAppender) rootLogger
				.getAppender("file");
		if (rfa != null) {
			return "Heap dump initiated to MIF log: "
					+ rfa.getFile()
					+ " from line beginning 'Full Java thread dump with locks info'";
		} else {
			return "Heap dump initiated to MIF log";
		}
	}

	private void systemStats(StringBuilder info) {

		MBeanServerConnection mbsc = ManagementFactory.getPlatformMBeanServer();

		try {
			OperatingSystemMXBean os = ManagementFactory
					.newPlatformMXBeanProxy(mbsc,
							ManagementFactory.OPERATING_SYSTEM_MXBEAN_NAME,
							OperatingSystemMXBean.class);

			MemoryMXBean mem = ManagementFactory.newPlatformMXBeanProxy(mbsc,
					ManagementFactory.MEMORY_MXBEAN_NAME, MemoryMXBean.class);

			ThreadMXBean thread = ManagementFactory.newPlatformMXBeanProxy(
					mbsc, ManagementFactory.THREAD_MXBEAN_NAME,
					ThreadMXBean.class);

			info.append("<b>System Info</b><br>");

			info.append(String.format("NumCPUs: [%s]<br>",
					os.getAvailableProcessors()));
			info.append(String.format("Load Average: [%s]<br>",
					os.getSystemLoadAverage()));
			info.append(String
					.format("Threads: [%s], Peak: [%s], Total: [%s], DaemonCount: [%s] <br>",
							thread.getThreadCount(),
							thread.getPeakThreadCount(),
							thread.getTotalStartedThreadCount(),
							thread.getDaemonThreadCount()));
			info.append(String.format("Heap Memory: [%s]<br>",
					mem.getHeapMemoryUsage()));
			info.append(String.format("Non Heap Memory: [%s]<br>",
					mem.getNonHeapMemoryUsage()));
			info.append("<br>");

		} catch (IOException e) {
			LOG.error("Exception thrown ", e);
		}
	}

	private void buildJschSessionMetrics(StringBuilder info) {

		info.append("<b>JSch Session Pool:</b>");
		info.append(" NumIdle: ");
		info.append(sessionPool.getPool().getNumIdle());
		info.append(" NumActive: ");
		info.append(sessionPool.getPool().getNumActive());
		info.append(" MaxIdle: ");
		info.append(sessionPool.getPool().getMaxIdle());
		info.append(" Max (-1 = infinite): ");
		info.append(sessionPool.getPool().getMaxActive());
		info.append(" MinEvictTime: ");
		info.append(sessionPool.getPool().getMinEvictableIdleTimeMillis());
		info.append(" EvictionRunInterval: ");
		info.append(sessionPool.getPool().getTimeBetweenEvictionRunsMillis());
		info.append(" TestWhenIdle: ");
		info.append(sessionPool.getPool().getTestWhileIdle());
		info.append(" Lifo: ");
		info.append(sessionPool.getPool().getLifo());

		info.append("<br><br>");

		if (sessionPool != null) {
			buildSwimmerStats(info);
		}
	}

	private void buildSwimmerStats(StringBuilder info) {

		JschSessionPoolObjectFactory sessionPool = JschSessionPool
				.getPoolObjectFactory();

		info.append(String.format(" <b>SSH Swimmers</b> NumSessions [%s]  ",
				sessionPool.getSwimmers().size()));
		info.append("<br>");

		for (JschSession session : sessionPool.getSwimmers().keySet()) {
			info.append(" Connected: ");
			info.append(session.isConnected());
			info.append(" InUse: ");
			info.append(session.isInUse());
			info.append(" Username: ");
			info.append(session.getParameters().getUserName());
			info.append(" Port: ");
			info.append(session.getParameters().getPort());						
			info.append("<br>");			
		}
	}

	private void buildConnectorMetrics(StringBuilder info,
			String connectorName, Connector connector) {

		ConcurrentMap<String, JobRunner> jobRegistry = connector
				.getJobRunnerRegistry();
		info.append("<b>Connector Name:</b> ");
		info.append(connectorName);
		info.append(" - ");
		info.append(String.format(" NumJobs [%s]", jobRegistry.keySet().size()));
		info.append(" -");
		AsyncTaskExecutor executor = connector.getTaskExecutor();
		if (executor instanceof ThreadPoolTaskExecutor) {
			ThreadPoolTaskExecutor pExec = (ThreadPoolTaskExecutor) executor;
			info.append(" Active: ");
			info.append(pExec.getActiveCount());
			info.append(" used of ");
			info.append(pExec.getMaxPoolSize());
			info.append(". Curr: ");
			info.append(pExec.getPoolSize());
			info.append(" Core Num Threads: ");
			info.append(pExec.getCorePoolSize());
			info.append(" KeepAliveSecs: ");
			info.append(pExec.getKeepAliveSeconds());
		}
		info.append("<br><br>");

		for (String jobId : jobRegistry.keySet()) {

			Job job = jobManagementService.getJob(jobId);

			Long startTime = Long.valueOf(job.getSGEStartTime());
			String seconds = "n/a";
			if (startTime > 0) {
				long timeTaken = System.currentTimeMillis() - startTime;
				seconds = String.valueOf(TimeUnit.MILLISECONDS
						.toSeconds(timeTaken));
			}

			info.append(" Job ID: ");
			info.append(jobId);
			info.append(" SGE JobID: ");
			info.append(job.getSGEJobID());
			info.append(" Time Running On SGE: ");
			info.append(seconds);
			info.append("(secs) Run Name: ");
			info.append(job.getRunName());
			info.append(" Username: ");
			info.append(job.getUserName());

			info.append("<br>");
		}
		info.append("<br>");
	}

	public ConnectorsRegistry getConnectorsRegistry() {
		return connectorsRegistry;
	}

	public void setConnectorsRegistry(ConnectorsRegistry connectorsRegistry) {
		this.connectorsRegistry = connectorsRegistry;
	}

	public JobManagementService getJobManagementService() {
		return jobManagementService;
	}

	public void setJobManagementService(
			JobManagementService jobManagementService) {
		this.jobManagementService = jobManagementService;
	}

	public JschSessionPool getSessionPool() {
		return sessionPool;
	}

	public void setSessionPool(JschSessionPool sessionPool) {
		this.sessionPool = sessionPool;
	}
}
