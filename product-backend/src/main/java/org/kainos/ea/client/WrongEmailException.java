package org.kainos.ea.client;

public class WrongEmailException extends Throwable {
    @Override
    public String getMessage() {
        return "Wrong Email Exception" ;
    }
}
