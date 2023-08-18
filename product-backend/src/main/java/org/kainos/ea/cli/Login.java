package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class Login {
    @ApiModelProperty(example = "tester@kainos.com", required = true)
    private String email;
    @ApiModelProperty(example = "Test123!", required = true)
    private String password;

    @JsonCreator
    public Login(@JsonProperty("email") String email,
                 @JsonProperty("password") String password) {
        this.email = email;
        this.password = password;
    }

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
}
