package integration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.kainos.ea.cli.AddJobRole;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddJobRoleIT {


    @Test
    public void postJobRole_shouldReturn201() {
        AddJobRole addJobRole = new AddJobRole(
                "tomekk",
                "test",
                "Bloggs"
        );
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(addJobRole)
                .post("http://localhost:8080/api/admin/job-roles");

        assertEquals(201, response.getStatusCode());
    }

    @Test
    public void postJobRoles_shouldReturn500() {
        AddJobRole addJobRole = new AddJobRole(
                "tomeweqqweqweqweweqqweeqweqwqweqwee" +
                        "wqewqeqweqweqweqweqweqweqwweqq" +
                        "weqweeqwweqeqweqwqweeqwqweqweeqwqweqweweqqwe" +
                        "qweqweeqwqweqweqweqweqweeqwqwekk",
                "test",
                "Bloggs"
        );
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(addJobRole)
                .post("http://localhost:8080/api/admin/job-roles");
        assertEquals(500, response.getStatusCode());
    }
}