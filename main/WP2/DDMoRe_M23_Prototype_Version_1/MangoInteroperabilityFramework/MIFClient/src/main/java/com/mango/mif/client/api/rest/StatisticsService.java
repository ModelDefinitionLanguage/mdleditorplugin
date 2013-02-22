package com.mango.mif.client.api.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.model.wadl.Description;
import org.apache.cxf.jaxrs.model.wadl.Descriptions;

/**
 * Provides simple stats on the running of the MIF services. 
 */
@Path("/statistics")
public interface StatisticsService {

    @GET        
    @Path("/connectors")
    @Produces({ MediaType.TEXT_HTML })
    @Descriptions({ @Description("Provide a list of statistics for clients") })
    String getStatistics();
    
    @GET        
    @Path("/heapdump")
    @Produces({ MediaType.TEXT_HTML })
    @Descriptions({ @Description("Perform a heap dump") })
    String getHeapDump();
}
