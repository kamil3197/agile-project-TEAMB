package org.kainos.ea.client;

public class WrongPasswordException extends Throwable {
    @Override
    public String getMessage() {
        return "Wrong Password Exception" ;
    }
}
