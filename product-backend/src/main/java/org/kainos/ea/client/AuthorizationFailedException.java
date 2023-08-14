package org.kainos.ea.client;

public class AuthorizationFailedException extends Throwable {
    @Override
    public String getMessage() {
        return "Authorization Failed Exception" ;
    }
}
