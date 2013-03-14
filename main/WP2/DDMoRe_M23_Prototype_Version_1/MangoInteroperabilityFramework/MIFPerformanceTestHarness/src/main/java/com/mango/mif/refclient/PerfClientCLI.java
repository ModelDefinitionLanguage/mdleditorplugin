package com.mango.mif.refclient;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.spi.LongOptionHandler;
import org.kohsuke.args4j.spi.StringOptionHandler;

public class PerfClientCLI {

	private static final boolean ENABLE_JOB_MONITORING = true;
	
	@SuppressWarnings("unused")
	private static final boolean DISABLE_JOB_MONITORING = false;

	private static final String DASH = "-";
	
	private static final String PSN = "psn";
	private static final String NM = "nm";
	private static final String BS = "bs";
	private static final String VPC = "vpc";
	private static final String SCM = "scm";
	
	private final String SUFFIX = " \"Num Batches Num Concurrent Jobs\", e.g. \"1 1\"";
		
	@Option(name=DASH + "mifshare", handler=StringOptionHandler.class, usage="Location of the MIF shared directory (must be visible on the grid), e.g. /tmp/mifshare")
    private String mifshare;
	
	@Option(name=DASH + NM, handler=StringOptionHandler.class, usage = "NONMEM " + SUFFIX)
    private String nonmem;

    @Option(name=DASH + PSN, handler=StringOptionHandler.class, usage = "PSN Execute " + SUFFIX)
    private String psnexec;
    
    @Option(name=DASH + BS, handler=StringOptionHandler.class, usage = "Bootstrap " + SUFFIX)
    private String bootstrap;
    
    @Option(name=DASH + VPC, handler=StringOptionHandler.class, usage = "VPC " + SUFFIX)
    private String vpc;
    
    @Option(name=DASH + SCM, handler=StringOptionHandler.class, usage =  "SCM " + SUFFIX)
    private String scm;   
    
    @Option(name=DASH + "mifurl", handler=StringOptionHandler.class, usage="Location of the MIFServer, defaults to: http://localhost:8080/MIFServer")
    private String mifurl;       

    @Option(name=DASH + "user", handler=StringOptionHandler.class, usage="User Name")
    private String userName;
    
    @Option(name=DASH + "password", handler=StringOptionHandler.class, usage="Password")
    private String userPassword;

    @Option(name=DASH + "interval", required=false, handler=LongOptionHandler.class, usage="Job status polling interval in ms.")
    private long intervalMs = 500;
    
    private int numSubmissionTypes = 0;
    
    private boolean submitJobs(PerfClientCLI cli) throws FileNotFoundException, IOException {

		PerfClient perfClient = new PerfClient(ENABLE_JOB_MONITORING);

		perfClient.setMIFUrl(mifurl);
		perfClient.setMIFShare(mifshare);
        perfClient.setUserName(userName);
        perfClient.setUserPassword(userPassword);
        perfClient.setIntervalMs(intervalMs);
		
		splitAndSubmit(perfClient, NM, nonmem);
		splitAndSubmit(perfClient, PSN, psnexec);
		splitAndSubmit(perfClient, BS, bootstrap);
		splitAndSubmit(perfClient, VPC, vpc);
		splitAndSubmit(perfClient, SCM, scm);
		
		if (numSubmissionTypes == 0) {
			System.err.println("There were no execution types specified on the command line ");
			return false;
		}
		return true;
	}

	private void splitAndSubmit(PerfClient perfClient, String execType, String numBatchesAndJobs) {
		
		if (StringUtils.isNotEmpty(numBatchesAndJobs)) {
			
			String[] numPair = StringUtils.split(numBatchesAndJobs);
			if (numPair != null && numPair.length == 2) {
				perfClient.submit(numPair[0], numPair[1], execType);
			} else {			
				System.err.println("Could not parse arguments: " + numBatchesAndJobs + " for type: " + execType);
			}
			numSubmissionTypes++;
		}
	}
    
    public static void main(String[] args) throws Exception {
            	
		CmdLineParser parser = null;

		try {
			PerfClientCLI cli = new PerfClientCLI();
			parser = new CmdLineParser(cli);			
			parser.parseArgument(args);			
			
			if (StringUtils.isEmpty(cli.mifshare)) exit(parser, "");
			
			if (!cli.submitJobs(cli)) {
				parser.printUsage(System.out);
			}
		
		} catch (CmdLineException e) {
			exit(parser, e.getMessage());
		}
    }
    
	private static void exit(CmdLineParser parser, String msg) {		
		System.err.println(msg);
		parser.printUsage(System.out);
		System.exit(0);
	}
}
