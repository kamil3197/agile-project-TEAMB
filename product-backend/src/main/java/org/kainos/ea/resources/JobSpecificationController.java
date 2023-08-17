package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(value = "View the job specification of a job role by Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully fetched the job specification of a " +
                    "job role from the database"),
            @ApiResponse(code = 404, message = "Failed to fetch the job specification of a job role from the database"),
            @ApiResponse(code = 500, message = "Failed to connect with the database")
    })
    public Response getJobSpecification(@PathParam("id") int role_id) {
        try {
            return Response.status(HttpStatus.OK_200).entity(jobSpecificationService.getJobSpecification(role_id)).build();
        } catch (DatabaseConnectionException | Exception | RoleNotExistException e) {
            return Response.status(HttpStatus.NOT_FOUND_404).entity(e.getMessage()).build();
        }
    }
}

