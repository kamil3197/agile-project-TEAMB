package org.kainos.ea.client;

public class FailedToGetUserException extends Throwable {
    @Override
    public String getMessage() {
        return "Failed to get user Exception";
    }
}
