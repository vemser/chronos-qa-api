package com.chronos.tests.estagiario;
import client.EstagiarioClient;
import org.junit.Test;
import specs.AuthSpec;
import specs.InicialSpecs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.notNullValue;

public class ListarEstagiariosTest {
    private final EstagiarioClient estagiarioClient = new EstagiarioClient();

    @Test
    public void testListarEstagiarios() {
        given()
                .spec(AuthSpec.setup())
                .when()
                .get("/estagiario")
                .then()
                .statusCode(200 );
    }

    @Test
    public void testListarEstagiariosSemToken() {
        given()
                .spec(InicialSpecs.setup())
                .when()
                .get("/estagiario")
                .then()
                .statusCode(403 );
    }
    @Test
    public void testBuscarEstagiarioPorID() {

        int estagiarioID = 1;

        given()
                .spec(AuthSpec.setup())
                .when()
                .get("/estagiario/" + estagiarioID)
                .then()
                .statusCode(200);
    }

    @Test
    public void testBuscarEstagiarioPorIDComTokenInvalido() {

        int estagiarioID = 1;

        given()
                .spec(InicialSpecs.setup())
                .when()
                .get("/estagiario/" + estagiarioID)
                .then()
                .statusCode(403);
    }

}
