package integration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.kainos.ea.cli.Band;

import java.util.Random;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class BandIT {

    public class RandomStrGenerator {

        public String generateRandomString() {
            Random rand = new Random();
            String str = rand.ints(48, 123)
                    .filter(num -> (num < 58 || num > 64) && (num < 91 || num > 96))
                    .limit(15)
                    .mapToObj(c -> (char) c)
                    .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                    .toString();

            return str;
        }
    }
    RandomStrGenerator randomStrGenerator = new RandomStrGenerator();
    String randomString = randomStrGenerator.generateRandomString();
    String data = randomString;

    @Test
    public void postBand_shouldReturn201() {
        Band band = new Band(
                data,
                1,
                "Test"
        );
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(band)
                .post("http://localhost:8080/api/admin/band");

        assertEquals(201, response.getStatusCode());
    }
    @Test
    public void postBand_shouldReturn500() {
        Band band = new Band(
                data,
                1,
                "Test"
        );
         RestAssured.given()
                .contentType(ContentType.JSON)
                .body(band)
                .post("http://localhost:8080/api/admin/band");

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(band)
                .post("http://localhost:8080/api/admin/band");
        assertEquals(500, response.getStatusCode());
    }
}
