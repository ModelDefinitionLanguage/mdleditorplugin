/* File			:  ExecutionJaxbUtils.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Apr 4, 2012
 */
package com.mango.mif.utils.jaxb;


/**
 * The Class ExecutionJaxbUtils.
 *
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class ExecutionJaxbUtils {
	
	/** The EXECUTION  REQUEST CLASS that are being used by JAXB Context. */
	public final static Class[] EXECUTION_REQUEST_CLASS_ARRAY = new Class[]{com.mango.mif.domain.ExecutionRequest.class,
		com.mango.mif.domain.Parameter.class,
		com.mango.mif.domain.CommandDefinition.class,
        com.mango.mif.domain.ParameterDefinition.class,
		com.mango.mif.utils.jaxb.MapEntryType.class,
		com.mango.mif.utils.jaxb.MapType.class};
	
	/** The EXECUTION  RESPONSE CLASS that are being used by JAXB Context. */
	public static final Class[] EXECUTION_RESPONSE_CLASS_ARRAY = new Class[]{com.mango.mif.domain.ExecutionResponse.class,
		com.mango.mif.domain.Parameter.class,
		com.mango.mif.utils.jaxb.MapEntryType.class,
		com.mango.mif.utils.jaxb.MapType.class};

}
