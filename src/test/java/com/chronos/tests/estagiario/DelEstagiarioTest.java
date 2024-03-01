package com.chronos.tests.estagiario;

import client.EstagiarioClient;
import org.junit.Test;
import specs.AuthSpec;
import specs.NoAuthSpec;

import static io.restassured.RestAssured.given;

public class DelEstagiarioTest {
    private final EstagiarioClient estagiarioClient = new EstagiarioClient();

    @Test
    public void testExcluirEstagiario() {
        int estagiarioID = 5;

        given()
                .spec(AuthSpec.setup())
                .when()
                .delete("/estagiario/" + estagiarioID + "/delete")
                .then()
                .statusCode(204);
    }

    @Test
    public void testExcluirEstagiarioSemToken() {
        int estagiarioID = 4;

        given()
                .spec(NoAuthSpec.setup())
                .when()
                .delete("/estagiario/" + estagiarioID + "/delete")
                .then()
                .statusCode(403);
    }

    @Test
    public void testExcluirEstagiarioQueNãoExiste() {
        int estagiarioID = 4;

        given()
                .spec(AuthSpec.setup())
                .when()
                .delete("/estagiario/" + estagiarioID + "/delete")
                .then()
                .statusCode(400);
    }
}
