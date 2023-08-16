package org.kainos.ea.client;

public class FailedToInsertTokenException extends Throwable {
    @Override
    public String getMessage() {
        return "Failed To Insert Token Exception" ;
    }

    public static class NameTooShortException extends Throwable {
    }

    public static class DatabaseConnectionException extends Throwable {
        public DatabaseConnectionException(Exception e) {
            super(e);
        }
    }
}
