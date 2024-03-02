package com.chronos.tests.edicao;

import client.EdicaoClient;
import data.factory.EdicaoFactory;
import io.restassured.http.ContentType;
import model.edicao.EdicaoResponseDTO;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class DeletarEdicaoFuncionalTest {
    private final EdicaoClient edicaoClient = new EdicaoClient();

    @Test
    public void testDeveDeletarUmaEdicaoComSucesso() {
        Integer idEdicao = edicaoClient.cadastrarEdicao(EdicaoFactory.edicaoValida())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(EdicaoResponseDTO.class).getIdEdicao();

        edicaoClient.deletarPorID(idEdicao)
                .then()
                    .statusCode(HttpStatus.SC_NO_CONTENT);

    }

    @Test
    public void testNaoDeveDeletarUmaEdicaoPoisIdInvalido() {
        edicaoClient.deletarPorID(-1)
                .then()
                    .contentType(ContentType.JSON)
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .body("status", equalTo(HttpStatus.SC_BAD_REQUEST))
                    .body("errors[0]", equalTo("Edição não encontrada!"));

    }

    @Test
    public void testNaoDeveDeletarUmaEdicaoPoisTokenInvalido() {
        edicaoClient.deletarSemToken(-1)
                .then()
                    .contentType(ContentType.JSON)
                    .statusCode(HttpStatus.SC_FORBIDDEN)
                    .body("status", equalTo(HttpStatus.SC_FORBIDDEN))
                    .body("error", equalTo("Forbidden"));

    }
}
