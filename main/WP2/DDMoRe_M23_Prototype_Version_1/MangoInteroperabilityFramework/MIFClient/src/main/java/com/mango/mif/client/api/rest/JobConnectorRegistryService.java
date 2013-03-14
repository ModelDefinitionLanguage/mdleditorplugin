package com.mango.mif.client.api.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


/**
 * REST Service interface for determining available job connectors on the server.
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 */
@Path("/jobconnectorregistry")
public interface JobConnectorRegistryService {

    /**
     * Get a list supported connectors for the server.
     * @return a list of supported connectors.
     */
    @GET
    @Produces({ "application/json", "text/xml" })
    String getConnectorNames();
}
