/**
 * Copyright Mango Solutions, 2012.
 * <p>SVN Entry: $HeadURL: $
 * <p>SVN ID: $Id: $
 * <p>Last edited by : $Author$
 * @author Tony Bamford &lt;tbamford@mango-solutions.com&gt;
 * @version $Revision$ as of on $Date$
 */
package com.mango.mif.nonmemsgeconnector;

import java.util.UUID;

import org.junit.Before;
import org.junit.Ignore;
import com.mango.mif.connector.TestsHelper;
import com.mango.mif.connector.runner.status.SummaryMessageBuilderTest;
import com.mango.mif.domain.ExecutionType;
import com.mango.mif.sge.SGE;

/**
 */
@Ignore
public class NONMEMSummaryMessageBuilderTest extends SummaryMessageBuilderTest {
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();


        job = TestsHelper.createJob("TEST-JOB", UUID.randomUUID().toString());
        job.setUserName("USER");
        job.setSharedLocationManager(sharedLocationManager);
        job.setInvoker(invoker);

        job.getExecutionRequest().setType(ExecutionType.NMFE.name());
        job.getRunName();

        job.addToDataMap(SGE.SGE_SUBMIT_SCRIPT_KEY, "nonmem-test.sh");
        job.addToDataMap(SGE.SGE_JOB_ID_KEY, "10000");

        textBuilder.setJob(job);
    }
    
    
}
