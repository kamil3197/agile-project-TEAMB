package org.kainos.ea.client;

public class FailedToCreateNewUserException extends Exception {
    @Override
    public String getMessage() {
        return "Failed to create new employee";
    }
}
