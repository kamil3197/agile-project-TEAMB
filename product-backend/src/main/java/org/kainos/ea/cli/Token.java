package org.kainos.ea.cli;

import java.sql.Timestamp;

public class Token {
    private int id;
    private String email;
    private String token;
    private Timestamp expiry;

    public Token(int id, String email, String token, Timestamp expiry) {
        this.id = id;
        this.email = email;
        this.token = token;
        this.expiry = expiry;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getExpiry() {
        return expiry;
    }

    public void setExpiry(Timestamp expiry) {
        this.expiry = expiry;
    }
}
