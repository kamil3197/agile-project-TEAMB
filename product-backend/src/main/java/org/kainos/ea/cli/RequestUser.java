package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class RequestUser {

    @ApiModelProperty(example = "tester@kainos.com", required = true)
    private String email;

    @ApiModelProperty(example = "Test123!", required = true)
    private String password;
    @ApiModelProperty(example = "Employee", required = true)
    private String role;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @JsonCreator
    public RequestUser(
            @JsonProperty("email") String email,
            @JsonProperty("password") String password,
            @JsonProperty("role") String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
