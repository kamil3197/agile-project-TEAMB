package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.kainos.ea.api.AuthService;
import org.kainos.ea.api.DateService;
import org.kainos.ea.cli.Login;
import org.kainos.ea.cli.RequestUser;
import org.kainos.ea.client.*;
import org.kainos.ea.db.AuthDao;
import org.kainos.ea.validator.RegisterValidator;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Api("Kainos new user`s API")
@Path("/api")
public class AuthController {
    private static final String CREATE = "/auth/register";
    private final AuthService authService = new AuthService(new AuthDao(), new RegisterValidator(), new DateService());
    Logger logger = Logger.getLogger(this.getClass().getName());


    @POST
    @Path(CREATE)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Creates new employee/admin")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully added new employee/admin to the database"),
            @ApiResponse(code = 400, message = "Failed to add new employee/admin to the database"),
            @ApiResponse(code = 500, message = "Failed to connect with the database")
    })
    public Response createNewUser(RequestUser user) {
        try {
            authService.createNewUser(user);
            return Response.ok().build();
        } catch (FaliedToCreateUserWrongInputException | FailedToCreateNewUserException e) {
            logger.severe(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("/auth/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Sign in to existing account")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully signed in."),
        @ApiResponse(code = 400, message = "Wrong email address or password."),
        @ApiResponse(code = 500, message = "Failed to connect with the database.")
    })
    public Response login (Login login){
        try {
            return Response.ok(authService.login(login)).build();

        } catch (WrongPasswordException | WrongEmailException e) {
            logger.severe(e.getMessage());
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        
        } catch (FailedToInsertTokenException | FailedToGetUserException e)  {
            logger.severe(e.getMessage());
            System.err.println(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}