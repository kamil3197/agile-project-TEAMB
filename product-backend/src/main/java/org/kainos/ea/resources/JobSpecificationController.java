package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.eclipse.jetty.http.HttpStatus;
import org.kainos.ea.api.JobSpecificationService;
import org.kainos.ea.db.JobSpecificationDao;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.exception.RoleNotExistException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("Job Roles API")
@Path("/api")
public class JobSpecificationController {
    private  JobSpecificationService jobSpecificationService = new JobSpecificationService(new JobSpecificationDao());

    @GET
    @Path("/job-specification/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobSpecification(@PathParam("id") int role_id) {
        try {
            return Response.status(HttpStatus.OK_200).entity(jobSpecificationService.getJobSpecification(role_id)).build();
        } catch (DatabaseConnectionException | Exception | RoleNotExistException e) {
            return Response.status(HttpStatus.NOT_FOUND_404).entity(e.getMessage()).build();
        }
    }
}

