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
                "Bloggs",
                1
        );
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(addJobRole)
                .post("http://localhost:8080/api/admin/job-roles");

        assertEquals(201, response.getStatusCode());
    }

    @Test
    public void postJobRoles_shouldReturn400() {
        AddJobRole addJobRole = new AddJobRole(
                "tomeweqqweqweqweweqqweeqweqwqweqwee" +
                        "wqewqeqweqweqweqweqweqweqwweqq" +
                        "weqweeqwweqeqweqwqweeqwqweqweeqwqweqweweqqwe" +
                        "qweqweeqwqweqweqweqweqweeqwqwekkasdsadasdasdasdsasaasdasdsaasdasd",
                "test",
                "Bloggs",
                1
        );
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(addJobRole)
                .post("http://localhost:8080/api/admin/job-roles");
        assertEquals(400, response.getStatusCode());
    }
}