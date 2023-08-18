package org.kainos.ea.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.jvnet.mimepull.DecodingException;
import org.kainos.ea.cli.User;
import org.kainos.ea.client.FailedToGetUserException;
import org.kainos.ea.client.TokenDecodeException;
import org.kainos.ea.db.AuthDao;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Array;
import java.util.List;

@Provider
@Priority(1)
public class AuthorisationFilter implements ContainerRequestFilter {
    private final AuthDao authDao;

    public AuthorisationFilter() {
        this.authDao = new AuthDao();
    }
    public AuthorisationFilter(AuthDao authDao) {
        this.authDao = authDao; // for mocking
    }
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

    public User getUserFromToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(System.getenv("JWT_SECRET"));
        JWTVerifier verifier = JWT.require(algorithm).build();
        try {
            DecodedJWT decodedJWT = verifier.verify(token);
            return new User(
                    decodedJWT.getClaim("user_id").asInt(),
                    decodedJWT.getSubject(),
                    null,
                    decodedJWT.getClaim("user_role").asString()
            );
        } catch (Exception e) {
            return null;
        }
    }

    public boolean isValidAccessToken(String token, String path) {
        User userFromToken = getUserFromToken(token);
        if ( userFromToken == null ) return false;

        User userFromDb;
        try {
            userFromDb = authDao.getUser(userFromToken.getEmail());
        } catch (FailedToGetUserException e) {
            return false;
        }

        if ( userFromDb == null ) return false;
        if ( userFromDb.getId() != userFromToken.getId() ) return false;
        if ( path.contains("admin") && !userFromDb.getRole().equals("Admin") ) return false;

        return true;
    }
}
