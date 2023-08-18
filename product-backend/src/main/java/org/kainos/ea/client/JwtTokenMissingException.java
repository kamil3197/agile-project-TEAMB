package org.kainos.ea.client;

public class JwtTokenMissingException extends Throwable {
    @Override
    public String getMessage() {
        return "No JWT token found in request headers" ;
    }
}
