package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.CapabilityService;
import org.kainos.ea.api.JobRoleService;
import org.kainos.ea.cli.AddJobRole;
import org.kainos.ea.cli.Capability;
import org.kainos.ea.client.FailedToCreateCapabilityException;
import org.kainos.ea.client.FailedToCreateJobRoleException;
import org.kainos.ea.client.FailedToGetJobRolesException;
import org.kainos.ea.db.CapabilityDao;
import org.kainos.ea.db.JobRoleDao;
import org.kainos.ea.exception.NameTooShortException;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.sql.SQLException;
import java.util.OptionalInt;

@Api("Job Roles API")
@Path("/api")
public class JobRoleController {
    private JobRoleService jobRoleService = new JobRoleService(new JobRoleDao());
    private CapabilityService capabilityService = new CapabilityService(new CapabilityDao());

    @GET
    @Path("/job-roles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllJobRoles() {
        try {
            return Response.ok(jobRoleService.getAllJobRoles()).build();
        } catch (FailedToGetJobRolesException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/admin/job-roles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createJobRole(@Valid AddJobRole addJobRole) {
        try {
            OptionalInt createdRoleId = jobRoleService.createJobRole(addJobRole);
            return Response.status(Response.Status.CREATED)
                    .entity("Job role created with ID: " + createdRoleId)
                    .build();
        } catch (FailedToCreateJobRoleException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Failed to create job role")
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Database error while creating job role")
                    .build();
        }
    }

    @POST
    @Path("/admin/capabilities")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCapability(Capability capability) throws NameTooShortException, SQLException, FailedToCreateCapabilityException {

        try {
            int CapabilityId = capabilityService.addCapability(capability);
            URI location = UriBuilder.fromPath("/admin/band/" + CapabilityId).build();

            return Response.created(location).build();
        } catch (FailedToCreateCapabilityException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }
}
