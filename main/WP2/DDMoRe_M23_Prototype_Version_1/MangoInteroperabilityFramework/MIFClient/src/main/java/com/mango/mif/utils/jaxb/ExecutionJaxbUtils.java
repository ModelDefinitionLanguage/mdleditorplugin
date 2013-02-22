/* File			:  ExecutionJaxbUtils.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Apr 4, 2012
 */
package com.mango.mif.utils.jaxb;

import com.mango.mif.client.api.rest.BootstrapActiveChildJobs;
import com.mango.mif.client.api.rest.DetailedStatusResponse;
import com.mango.mif.domain.DetailedStatus;
import com.mango.mif.domain.nonmemsgeconnector.NONMEMProcessingDetailedStatus;
import com.mango.mif.domain.psnsgeconnector.PsNBootstrapProcessingDetailedStatus;
import com.mango.mif.domain.psnsgeconnector.PsNSCMModelProcessingStatus;
import com.mango.mif.domain.psnsgeconnector.PsNSCMProcessingDetailedStatus;
import com.mango.mif.domain.psnsgeconnector.PsNVPCProcessingDetailedStatus;


/**
 * The Class ExecutionJaxbUtils.
 *
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class ExecutionJaxbUtils {
	
	/** The EXECUTION  REQUEST CLASS that are being used by JAXB Context. 
     * @deprecated use CONTEXT_CLASSES */
    @Deprecated
	public final static Class[] EXECUTION_REQUEST_CLASS_ARRAY = new Class[]{com.mango.mif.domain.ExecutionRequest.class,
		com.mango.mif.utils.jaxb.MapEntryType.class,
		com.mango.mif.utils.jaxb.MapType.class};
	
	/** The EXECUTION  RESPONSE CLASS that are being used by JAXB Context. 
     * @deprecated use CONTEXT_CLASSES */
    @Deprecated
	public static final Class[] EXECUTION_RESPONSE_CLASS_ARRAY = new Class[]{com.mango.mif.domain.ExecutionResponse.class,
		com.mango.mif.utils.jaxb.MapEntryType.class,
		com.mango.mif.utils.jaxb.MapType.class};

    /** The NONMEMProcessingDetailedStatus  CLASS that are being used by JAXB Context.
     * @deprecated use CONTEXT_CLASSES */
	@Deprecated
    public final static Class[] NONMEM_PROCESSING_DETAILED_STATUS_CLASS_ARRAY = new Class[]{
        BootstrapActiveChildJobs.class,
        PsNSCMModelProcessingStatus.class,
        DetailedStatusResponse.class,
        DetailedStatus.class,
        PsNSCMProcessingDetailedStatus.class,
        PsNBootstrapProcessingDetailedStatus.class,
        PsNVPCProcessingDetailedStatus.class,
        NONMEMProcessingDetailedStatus.class,
        com.mango.mif.utils.jaxb.MapEntryType.class,
        com.mango.mif.utils.jaxb.MapType.class};
    
    /**
     * A classes used to build up a JAXB context for marshalling/unmarshalling beans involved in the execution process
     */
    public final static Class<?>[] CONTEXT_CLASSES = new Class[] {
        com.mango.mif.domain.ExecutionRequest.class,
        com.mango.mif.domain.ExecutionResponse.class,
        PsNBootstrapProcessingDetailedStatus.class,
        BootstrapActiveChildJobs.class,
        PsNSCMModelProcessingStatus.class,
        PsNSCMProcessingDetailedStatus.class,
        DetailedStatusResponse.class,
        DetailedStatus.class,
        PsNVPCProcessingDetailedStatus.class,
        NONMEMProcessingDetailedStatus.class,
        com.mango.mif.utils.jaxb.MapEntryType.class,
        com.mango.mif.utils.jaxb.MapType.class
    };
}
