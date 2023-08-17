package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.CapabilityService;
import org.kainos.ea.client.FailedToGetCapabilitiesException;
import org.kainos.ea.db.CapabilityDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Api("Capability API")
@Path("/api")
public class CapabilityController {
    private CapabilityService capabilityService = new CapabilityService(new CapabilityDao());
    Logger logger = Logger.getLogger(this.getClass().getName());

    @GET
    @Path("/capability-leads")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCapabilities() {
        try {
            return Response.ok(capabilityService.getAllCapabilities()).build();
        } catch (FailedToGetCapabilitiesException e) {
            logger.severe(e.getMessage());
            return Response.serverError().build();
        }
    }
}
