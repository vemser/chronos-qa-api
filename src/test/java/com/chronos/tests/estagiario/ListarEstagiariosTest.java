package com.chronos.tests.estagiario;
import client.EdicaoClient;
import client.EstagiarioClient;
import client.TrilhaClient;
import io.qameta.allure.*;
import io.restassured.http.ContentType;
import model.estagiario.EstagiarioResponseDTO;
import org.apache.http.HttpStatus;
import org.junit.Test;
import specs.AuthSpec;
import specs.InicialSpecs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ListarEstagiariosTest {
    private final EstagiarioClient estagiarioClient = new EstagiarioClient();
    private final EdicaoClient edicaoClient = new EdicaoClient();
    private final TrilhaClient trilhaClient = new TrilhaClient();

    @Feature("Estagiario")
    @Story("Lista um Estagiario com sucesso")
    @Description("Testa se a requisição consegue retornar uma paginação de usuários e deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testDeveListarEstagiariosComSucesso() {

        EstagiarioResponseDTO responseDTO = estagiarioClient.criarMassaDeDados()
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .extract().as(EstagiarioResponseDTO.class);

        estagiarioClient.buscarTudo()
                        .then()
                            .contentType(ContentType.JSON)
                            .statusCode(HttpStatus.SC_OK)
                            .body("content.size()", greaterThan(0));

        trilhaClient.deletar(responseDTO.getTrilha().getIdTrilha())
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);

        estagiarioClient.deletar(responseDTO.getIdEstagiario())
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);


    }

    @Feature("Estagiario")
    @Story("Atualizar um Estagiario com sucesso")
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

    @Feature("Estagiario")
    @Story("Atualizar um Estagiario com sucesso")
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


    @Feature("Estagiario")
    @Story("Atualizar um Estagiario com sucesso")
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
