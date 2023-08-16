package org.kainos.ea.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.net.URI;
import java.util.Date;

@Provider
@Priority(1)
public class AuthorisationFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String accessToken = requestContext.getHeaderString("Authorization");
        String path = requestContext.getUriInfo().getRequestUri().getPath();
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.println("PATH: " + path);

        // check token if it is not /login or /register
        if (!path.contains("auth")) {
            if (accessToken == null || !isValidAccessToken(accessToken, path)) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }
        }

    }

    private boolean isValidAccessToken(String token, String path) {
        Algorithm algorithm = Algorithm.HMAC256("NOT_HARDCODED_SECRET");
        JWTVerifier verifier = JWT.require(algorithm).build();

        DecodedJWT decodedJWT = verifier.verify(token);

        String subject = decodedJWT.getSubject();
        Long userId = decodedJWT.getClaim("user_id").asLong();
        String userRole = decodedJWT.getClaim("user_role").asString();
        Date expiresAt = decodedJWT.getExpiresAt();
        Date now = new Date();

        System.out.println("subject: " + subject);
        System.out.println("userId: " + userId);
        System.out.println("userRole: " + userRole);
        System.out.println("expiresAt: " + expiresAt);
        System.out.println("--------------------------------------------------------------------------------------------------------------------");

        // check date
        if (expiresAt == null || !expiresAt.after(now)) {
            return false;
        }

        // if admin required check is admin
        if (path.contains("admin") && !userRole.equals("Admin")) {
            return false;
        }

        return true;
    }
}
