package org.kainos.ea.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.kainos.ea.cli.User;
import org.kainos.ea.client.FailedToGetUserException;
import org.kainos.ea.db.AuthDao;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(1)
public class AuthorisationFilter implements ContainerRequestFilter {
    private final AuthDao authDao = new AuthDao();

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String accessToken = requestContext.getHeaderString("Authorization");
        String path = requestContext.getUriInfo().getRequestUri().getPath();

        // check token if it is not /login, /register or /swagger
        if ( !(path.contains("auth") || path.contains("swagger")) ) {
            if (accessToken == null || !isValidAccessToken(accessToken, path)) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }
        }

    }

    private boolean isValidAccessToken(String token, String path) {
        Algorithm algorithm = Algorithm.HMAC256(System.getenv("jwt_secret"));
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);

        String userEmailFromToken = decodedJWT.getSubject();
        int userIdFromToken = decodedJWT.getClaim("user_id").asInt();

        User userFromDb;
        try {
            userFromDb = authDao.getUser(userEmailFromToken);
        } catch (FailedToGetUserException e) {
            return false;
        }
        if ( userFromDb == null || userIdFromToken != userFromDb.getId() ) return false;

        String userRole = decodedJWT.getClaim("user_role").asString();
        if (path.contains("admin") && !userRole.equals("Admin")) return false;

        return true;
    }
}
