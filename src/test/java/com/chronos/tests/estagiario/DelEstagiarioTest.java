package com.chronos.tests.estagiario;

import client.EdicaoClient;
import client.EstagiarioClient;
import client.TrilhaClient;
import data.factory.EdicaoFactory;
import data.factory.EstagiarioFactory;
import data.factory.TrilhaDataFactory;
import io.qameta.allure.*;
import io.restassured.http.ContentType;
import model.edicao.EdicaoResponseDTO;
import model.estagiario.EstagiarioRequestDTO;
import model.estagiario.EstagiarioResponseDTO;
import model.trilha.TrilhaResponseDTO;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class DelEstagiarioTest {
    private final EstagiarioClient estagiarioClient = new EstagiarioClient();
    private final EdicaoClient edicaoClient = new EdicaoClient();
    private final TrilhaClient trilhaClient = new TrilhaClient();

    @Feature("Estagiario")
    @Story("Deletar um Estagiario com sucesso")
    @Description("Testa se a requisição consegue deletar um estagiário retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @Tag("Fumaca")
    public void testExcluirEstagiario() {
        Integer idEdicao = edicaoClient.cadastrarEdicao(EdicaoFactory.edicaoValida())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(EdicaoResponseDTO.class).getIdEdicao();

        Integer idTrilha = trilhaClient.cadastrar(TrilhaDataFactory.trilhaComTodosOsCampos())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(TrilhaResponseDTO.class).getIdTrilha();

        edicaoClient.vincularTrilha(idEdicao, idTrilha)
                .then()
                .statusCode(HttpStatus.SC_OK);

        EstagiarioRequestDTO bodyRequest = EstagiarioFactory.estagiarioValido(idTrilha, idEdicao);

        Integer createdEstagiarioID = estagiarioClient.cadastrar(bodyRequest)
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_CREATED)
                .extract().as(EstagiarioResponseDTO.class).getIdEstagiario();

        estagiarioClient.desabilitar(createdEstagiarioID)
                .then()
                    .statusCode(HttpStatus.SC_NO_CONTENT);

        estagiarioClient.deletar(createdEstagiarioID)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);

        trilhaClient.deletar(idTrilha)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);

        edicaoClient.deletarPorID(idEdicao)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Feature("Estagiario")
    @Story("Deletar um Estagiario sem sucesso")
    @Description("Testa se a requisição consegue deletar um estagiário retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testExcluirEstagiarioSemToken() {
        estagiarioClient.desabilitarSemToken(-1)
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_FORBIDDEN)
                .body("status", equalTo(HttpStatus.SC_FORBIDDEN))
                .body("error", equalTo("Forbidden"));
    }

    @Feature("Estagiario")
    @Story("Atualizar um Estagiario com sucesso")
    @Description("Testa se a requisição consegue atualizar uma area envolvida deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testExcluirEstagiarioQueNãoExiste() {
        estagiarioClient.desabilitar(-1)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);

    }
}
