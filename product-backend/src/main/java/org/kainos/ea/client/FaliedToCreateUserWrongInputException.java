package org.kainos.ea.client;

public class FaliedToCreateUserWrongInputException extends Exception {
    public FaliedToCreateUserWrongInputException(String message) {
        super(message);
    }

    public static class RoleNotExistException extends Throwable {
        public RoleNotExistException() {
        super("Could not found JobSpecification.");

        }
    }
}
