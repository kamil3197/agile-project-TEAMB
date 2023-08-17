package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.kainos.ea.api.BandService;
import org.kainos.ea.cli.Band;
import org.kainos.ea.client.FailedToCreateBandException;
import org.kainos.ea.db.BandDao;
import org.kainos.ea.exception.NameTooShortException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.sql.SQLException;

@Api("Admin API")
@Path("/api")
public class BandController {

    private BandService bandService = new BandService(new BandDao());



    @POST
    @Path("/admin/band")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Creates new band")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully added new band to the database"),
            @ApiResponse(code = 400, message = "Failed to add new band to the database"),
            @ApiResponse(code = 500, message = "Failed to connect with the database")
    })
    public Response createBand(Band band) throws NameTooShortException, SQLException, FailedToCreateBandException {

        try {
            int BandId = bandService.createBand(band);
            URI location = UriBuilder.fromPath("/admin/band/" + BandId).build();

            return Response.created(location).build();
        } catch (FailedToCreateBandException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }
}
