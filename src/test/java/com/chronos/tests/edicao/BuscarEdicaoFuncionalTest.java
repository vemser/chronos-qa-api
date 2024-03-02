package com.chronos.tests.edicao;

import client.EdicaoClient;
import data.factory.EdicaoFactory;
import io.restassured.http.ContentType;
import model.edicao.EdicaoResponseDTO;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class BuscarEdicaoFuncionalTest {

    private EdicaoClient edicaoClient = new EdicaoClient();

    @Test
    public void testDeveRetornarUmaPaginacaoDeEdicao() {

        // CRIAR MASSA
        EdicaoResponseDTO dataCreated = edicaoClient.cadastrarEdicao(EdicaoFactory.edicaoValida())
                .then()
                        .statusCode(HttpStatus.SC_OK)
                    .extract().as(EdicaoResponseDTO.class);

        edicaoClient.buscarTudo()
                .then()
                    .contentType(ContentType.JSON)
                    .statusCode(HttpStatus.SC_OK)
                    .body("content", notNullValue());

        edicaoClient.deletarPorID(dataCreated.getIdEdicao())
                .then()
                    .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void testDeveRetornarUmaEdicaoPorID() {
        EdicaoResponseDTO dataCreated = edicaoClient.cadastrarEdicao(EdicaoFactory.edicaoValida())
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .extract().as(EdicaoResponseDTO.class);

        edicaoClient.buscarPorID(dataCreated.getIdEdicao())
                .then()
                    .contentType(ContentType.JSON)
                    .statusCode(HttpStatus.SC_OK)
                    .body("idEdicao", equalTo(dataCreated.getIdEdicao()))
                    .body("nome", equalTo(dataCreated.getNome()))
                    .body("descricao", equalTo(dataCreated.getDescricao()))
                    .body("status", equalTo(dataCreated.getStatus()))
                    .body("dataInicial", containsStringIgnoringCase(dataCreated.getDataInicial().toString()))
                    .body("dataFinal", containsStringIgnoringCase(dataCreated.getDataFinal().toString()));

        edicaoClient.deletarPorID(dataCreated.getIdEdicao())
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void testNaoDeveRetornarEdicaoPoisIdInvalido() {
        edicaoClient.buscarPorID(-1)
                .then()
                    .contentType(ContentType.JSON)
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .body("errors[0]", equalTo("Edição não encontrada!"));
    }

    @Test
    public void testNaoDeveBuscarEdicaoPoisTokenInvalido() {
        edicaoClient.buscarTudoSemToken()
                .then()
                .statusCode(HttpStatus.SC_FORBIDDEN)
                .body("error", equalTo("Forbidden"));
    }

}
