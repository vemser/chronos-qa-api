package com.chronos.tests.estagiario;
import client.EstagiarioClient;
import io.qameta.allure.*;
import org.junit.Test;
import specs.AuthSpec;
import specs.InicialSpecs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.notNullValue;

public class ListarEstagiariosTest {
    private final EstagiarioClient estagiarioClient = new EstagiarioClient();

    @Feature("Area Envolvida")
    @Story("Atualizar uma area envolvida com sucesso")
    @Description("Testa se a requisição consegue atualizar uma area envolvida deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testListarEstagiarios() {
        given()
                .spec(AuthSpec.setup())
                .when()
                .get("/estagiario")
                .then()
                .statusCode(200 );
    }

    @Feature("Area Envolvida")
    @Story("Atualizar uma area envolvida com sucesso")
    @Description("Testa se a requisição consegue atualizar uma area envolvida deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testListarEstagiariosSemToken() {
        given()
                .spec(InicialSpecs.setup())
                .when()
                .get("/estagiario")
                .then()
                .statusCode(403 );
    }

    @Feature("Area Envolvida")
    @Story("Atualizar uma area envolvida com sucesso")
    @Description("Testa se a requisição consegue atualizar uma area envolvida deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
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


    @Feature("Area Envolvida")
    @Story("Atualizar uma area envolvida com sucesso")
    @Description("Testa se a requisição consegue atualizar uma area envolvida deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
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
