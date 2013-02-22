/* File			:  MIFInvoker.java
 * Project		:  MIFClient
 * Created on	:  May 23, 2012
 */
package com.mango.mif.client.cli;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.common.base.Preconditions;
import com.mango.mif.client.api.TaskExecutionManager;
import com.mango.mif.client.api.TaskExecutionMessageHandler;
import com.mango.mif.core.api.ResourceUtils;
import com.mango.mif.core.api.SharedLocationManager;
import com.mango.mif.core.resource.BaseResourcePublisher;
import com.mango.mif.core.resource.fileutils.FileUtilsResourcePublisher;
import com.mango.mif.domain.ExecutionRequest;
import com.mango.mif.domain.ExecutionRequestBuilder;
import com.mango.mif.domain.ExecutionResponse;
import com.mango.mif.exception.MIFException;
import com.mango.mif.utils.encrypt.EncrypterFactory;

/**
 * 
 * A simple MIF client command line implementation. It takes the following
 * properties: mif.broker.url - an url of broker of MIF server
 * mif.shared.location - a shared location between
 * the client and the server mif.rest.service.url - a rest service URL
 * 
 * @version $Revision: $ as of $Date: $
 *          <p>
 *          SVN Entry : $HeadURL: $
 *          <p>
 *          SVN ID : $Id: $
 *          <p>
 *          Last edited by : $Author: $
 */
public class MIFInvoker implements TaskExecutionMessageHandler {

	/** The Constant LOG. */
	final static Logger LOG = Logger.getLogger(MIFInvoker.class);
	/**
	 * Task execution manager component
	 */
	@Autowired
	private TaskExecutionManager taskExecutionManager;

	/** Used to synchronize test thread and requests handling. */
	static CountDownLatch signal = new CountDownLatch(1);

	/** Used to synchronize test thread and requests handling. */
	static CountDownLatch restServiceSignal = new CountDownLatch(1);
	/**
	 * User name
	 */
	private static String	userName;
	/**
	 * Password
	 */
	private static String	userPassword;

	@Autowired
	private SharedLocationManager sharedLocationManager;
	/**
	 * A directory to which results will be copied
	 */
	private File outputDirectory;
	/**
	 * connector id
	 */
	private String connectorID;
	/**
	 * A directory containing input files for execution
	 */
	private File scriptDirectory;
	/**
	 * Script file
	 */
	private String scriptFile;
	/**
	 * A request ID that was sent to MIF
	 */
	private String requestUUID;
	/**
	 * Execution response received from MIF
	 */
	private ExecutionResponse response;
	/**
	 * Execution request sent to MIF
	 */
	private ExecutionRequest executionRequest;
	/**
	 * Executable to use to execute test data
	 */
    private String executable;
	/**
	 * MIF broker URL property
	 */
	public final static String MIF_BROKER_URL = "mif.broker.url";
	/**
	 * Shared files location property
	 */
	public final static String SHARED_FILES = "mif.shared.location";
	/**
	 * MIF Rest service URL
	 */
	public final static String MIF_REST_SERVICE_URL = "mif.rest.service.url";
	/**
	 * user name
	 */
	public final static String MIF_CLIENT_USER_NAME = "mif.client.userName";
	/**
	 * unencrypted password
	 */
	public final static String MIF_CLIENT_USER_PASSWORD = "mif.client.password";
	/**
	 * Error exit code
	 */
	private final static int ERROR = -1;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			checkArguments(args, System.getProperties());
		} catch(Exception e) {
			LOG.error(e);
			printHelp();
			System.exit(ERROR);
		}
		File scriptDir = new File(args[0]);
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"com/mango/mif/client/cli/MIFInvoker-context.xml");
		MIFInvoker main = ctx.getBean(MIFInvoker.class);

		main.scriptFile = args[1];

		main.connectorID = args[2];
		if(args.length>3) {
		    main.executable = args[3];
		} else {
		    main.executable = "";
		}
		main.scriptDirectory = scriptDir;
		main.outputDirectory = new File(".").getAbsoluteFile();
		try {
			main.taskExecutionManager.setExecutionMessageHandler(main);
			main.execute();
		} catch (MIFException e) {
			LOG.error(e);
			System.exit(ERROR);
		} catch (Exception e) {
			LOG.error(e);
			System.exit(ERROR);
		}
		System.exit(0);
	}

	/**
	 * Validates the arguments
	 * @param args
	 * @param properties
	 */
	protected static void checkArguments(String[] args, Properties properties) {
		Preconditions
				.checkArgument(args.length >= 2,
						"Not enough arguments");
		Preconditions
				.checkNotNull(properties.getProperty(MIF_BROKER_URL),
						"Property "+MIF_BROKER_URL+" must be set, e.g. tcp://localhost:61616");
		Preconditions
				.checkNotNull(
						properties.getProperty(SHARED_FILES),
						"Property "+SHARED_FILES+" must be set, e.g. /usr/globals/username");
		Preconditions
		.checkNotNull(properties.getProperty(MIF_CLIENT_USER_NAME),
				"Property "+MIF_CLIENT_USER_NAME+" must be set.");
		Preconditions
		.checkNotNull(properties.getProperty(MIF_CLIENT_USER_PASSWORD),
				"Property "+MIF_CLIENT_USER_PASSWORD+" must be set.");
		File scriptDirectory = new File(args[0]);
		Preconditions.checkArgument(scriptDirectory.exists(),
				"Execution request test data directory does not exist.");
        File scriptFile = new File(scriptDirectory,args[1]);
        Preconditions.checkArgument(scriptFile.exists(),
                "Specified script file for execution does not exist.");
		userName = properties.getProperty(MIF_CLIENT_USER_NAME);
		userPassword = properties.getProperty(MIF_CLIENT_USER_PASSWORD);
		
	}
	/**
	 * Prints help
	 */
	private static void printHelp() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("Usage:").append("\n").append("MIFInvoker").append(" ")
				.append("request-directory").append(" ").append("path/to/script/file/within/request-directory").append(" ").append("connector-id").append(" ").append("executable")
				.append("\n").append("Required System Properties").append("\n")
				.append(MIF_BROKER_URL).append(" - MIF server broker url\n")
				.append(SHARED_FILES)
				.append(" - shared location between MIF Server and client\n")
				.append(MIF_CLIENT_USER_NAME)
				.append(" - MIF client user name that will be used to run scripts against\n")
				.append(MIF_CLIENT_USER_PASSWORD)
				.append(" - MIF client user password that will be used to run scripts against(unecrypted)\n")
				.append("Example").append("\n")
				.append("java -D"+MIF_BROKER_URL+"=\"tcp://hendrix.mango.local:61616\"")
				.append(" -D"+SHARED_FILES+"=/usr/global/miftst-hendrix/hendrix-server/sharedfiles")
				.append(" -cp ./MIFClient.jar:./lib/* com.mango.mif.client.cli.MIFInvoker")
				.append(" ./testData path/relative/to/testData/script.R R_SGE /usr/bin/Rscript");
	
		System.out.println(sBuilder.toString());
	}

	/**
	 * Executes script and waits for its completion
	 * 
	 * @throws JAXBException
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws MIFException 
	 */
	private void execute() throws JAXBException,
			InterruptedException, IOException, MIFException {
		requestUUID = sendRequest();
		System.out.println("A request has been sent to MIF. Request UUID is "
				+ requestUUID);
		signal.await();
		
		ResourceUtils.dumpExecutionFiles(outputDirectory, sharedLocationManager, executionRequest, response);

		System.out.println("Results are available in :" + outputDirectory);
	}

	/**
	 * Prepares execution request and passes it to MIF-client components for
	 * submitting
	 * 
	 * @return
	 * @throws JAXBException
	 * @throws MalformedURLException
	 * @throws MIFException 
	 */
	private String sendRequest() throws 
			JAXBException, MalformedURLException, MIFException {
        FileUtilsResourcePublisher publisher = new FileUtilsResourcePublisher();
        publisher.setSharedLocationManager(sharedLocationManager);
		ExecutionRequestBuilder executionRequestBuilder = createExecutionRequestBuilderForInputDirectory(publisher);
		executionRequestBuilder.setExecutionType(connectorID);
        executionRequestBuilder.setUserName(userName);
        executionRequestBuilder.setCommand(executable);
        executionRequestBuilder.setUserPassword(EncrypterFactory.getEncrypter().encrypt(userPassword)).setSubmitAsUserMode(true);
        executionRequestBuilder.setExecutionFile(scriptFile);
		executionRequest = executionRequestBuilder.getExecutionRequest();
		taskExecutionManager.submit(executionRequest);
		return executionRequest.getRequestId().toString();
	}
    /**
     * Creates execution request
     * @param requestID 
     * @return
     * @throws JAXBException 
     * @throws MIFException 
     */
    public ExecutionRequestBuilder createExecutionRequestBuilderForInputDirectory(BaseResourcePublisher resourcePublisher) throws JAXBException, MIFException {
        ExecutionRequestBuilder requestBuilder = new ExecutionRequestBuilder();
        List<File> files = ResourceUtils.listFiles(scriptDirectory, new FilenameFilter() {
            
            @Override
            public boolean accept(File dir, String name) {
                return true;
            }
        });
        String requestID = publishFiles(resourcePublisher, files, scriptDirectory);
        requestBuilder.setRequestId(requestID);
        requestBuilder.setExecutionFile(scriptFile);
        return requestBuilder;
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
	@Override
	public void handle(ExecutionResponse executionResponse) {
		try {
			LOG.info("Received message for handling "
					+ executionResponse.getRequestId() + " for handling.");
			if (!executionResponse.getRequestId().toString()
					.equals(requestUUID))
				return;
			response = executionResponse;
			signal.countDown();
		} catch (Exception e) {
			LOG.error(e);
			System.exit(ERROR);
		}
	}

	public void setTaskExecutionManager(
			TaskExecutionManager taskExecutionManager) {
		this.taskExecutionManager = taskExecutionManager;
	}
	

	/**
	 * @param sharedLocationManager the sharedLocationManager to set
	 */
	public void setSharedLocationManager(SharedLocationManager sharedLocationManager) {
		this.sharedLocationManager = sharedLocationManager;
	}
}
