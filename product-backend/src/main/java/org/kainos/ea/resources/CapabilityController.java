package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(value = "View the list of all capabilities and the leads")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully fetched the list of capabilities and" +
                    " the leads from the database"),
            @ApiResponse(code = 404, message = "Failed to fetched the list of capabilities and the leads from the database"),
            @ApiResponse(code = 500, message = "Failed to connect with the database")
    })
    public Response getAllCapabilities() {
        try {
            return Response.ok(capabilityService.getAllCapabilities()).build();
        } catch (FailedToGetCapabilitiesException e) {
            logger.severe(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
