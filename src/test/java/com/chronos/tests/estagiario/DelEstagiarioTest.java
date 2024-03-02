package com.chronos.tests.estagiario;

import client.EstagiarioClient;
import io.qameta.allure.*;
import org.junit.Test;
import specs.AuthSpec;
import specs.NoAuthSpec;

import static io.restassured.RestAssured.given;

public class DelEstagiarioTest {
    private final EstagiarioClient estagiarioClient = new EstagiarioClient();

    @Feature("Estagiario")
    @Story("Atualizar um Estagiario com sucesso")
    @Description("Testa se a requisição consegue atualizar uma area envolvida deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
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

    @Feature("Estagiario")
    @Story("Atualizar um Estagiario com sucesso")
    @Description("Testa se a requisição consegue atualizar uma area envolvida deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
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

    @Feature("Estagiario")
    @Story("Atualizar um Estagiario com sucesso")
    @Description("Testa se a requisição consegue atualizar uma area envolvida deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
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
