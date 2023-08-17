package org.kainos.ea.resources;


import io.swagger.annotations.Api;
import org.kainos.ea.api.CapabilityService;
import org.kainos.ea.cli.Capability;
import org.kainos.ea.client.FailedToCreateCapabilityException;
import org.kainos.ea.db.CapabilityDao;
import org.kainos.ea.exception.NameTooShortException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.sql.SQLException;

@Api("Admin API")
@Path("/api")
public class CapabilityController {
    private CapabilityService capabilityService = new CapabilityService(new CapabilityDao());

    @POST
    @Path("/admin/capabilities")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCapability(Capability capability) throws SQLException {

        try {
            int CapabilityId = capabilityService.addCapability(capability);
            URI location = UriBuilder.fromPath("/admin/capabilities/" + CapabilityId).build();
            return Response.created(location).build();

        } catch (FailedToCreateCapabilityException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
