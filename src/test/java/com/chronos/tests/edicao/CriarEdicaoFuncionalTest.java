package com.chronos.tests.edicao;

import client.EdicaoClient;
import data.factory.EdicaoFactory;
import model.edicao.EdicaoRequestDTO;
import model.edicao.EdicaoResponseDTO;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class CriarEdicaoFuncionalTest {

    private final EdicaoClient edicaoClient = new EdicaoClient();

    @ParameterizedTest
    @MethodSource("data.provider.EdicaoProvider#criarEdicaoComSucessoProvider")
    @DisplayName("Teste: Deve criar uma nova edição com sucesso")
    @Tag("Fumaca")
    public void testDeveCriarUmEdicaoComSucesso(EdicaoRequestDTO requestDTO, Integer responseExpected) {

        EdicaoResponseDTO responseDTO = edicaoClient.cadastrarEdicao(requestDTO)
                .then()
                    .statusCode(responseExpected)
                    .extract().as(EdicaoResponseDTO.class);

        assertAll("responseDTO",
                () -> assertEquals(responseDTO.getNome(), requestDTO.getNome()),
                () -> assertEquals(responseDTO.getDescricao(), requestDTO.getDescricao()),
                () -> assertEquals(responseDTO.getDataInicial(), requestDTO.getDataInicial()),
                () -> assertEquals(4, responseDTO.getEtapas().size()),
                () -> assertNotNull(responseDTO.getIdEdicao()),
                () -> assertTrue(responseDTO.getTrilhas().isEmpty()),
                () -> assertTrue(responseDTO.getEstagiarios().isEmpty())
        );

        edicaoClient.deletarPorID(responseDTO.getIdEdicao())
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @ParameterizedTest
    @MethodSource("data.provider.EdicaoProvider#criarEdicaoSemSucessoProvider")
    @DisplayName("Teste: Não deve criar novas edições")
    public void testNaoDeveCriarEdicaoComSucesso(EdicaoRequestDTO requestDTO, Integer responseExpected) {
        edicaoClient.cadastrarEdicao(requestDTO)
                .then()
                .statusCode(responseExpected);
    }

    @Test
    @Tag("Fumaca")
    public void testNaoDeveCadastrarEdicaoPoisTokenInvalido() {
        edicaoClient.cadastrarEdicaoSemToken(EdicaoFactory.edicaoValida())
                .then()
                .statusCode(HttpStatus.SC_FORBIDDEN)
                .body("error", equalTo("Forbidden"));
    }

    @Test
    @Tag("Fumaca")
    public void testNaoDeveCadastrarEdicaoPoisNomeJaExistente() {
        EdicaoRequestDTO massaDados = EdicaoFactory.edicaoValida();
        EdicaoRequestDTO massaNomeExistente = EdicaoFactory.edicaoValida();

        massaNomeExistente.setNome(massaDados.getNome());

        Integer idEdicao = edicaoClient.cadastrarEdicao(massaDados)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(EdicaoResponseDTO.class).getIdEdicao();

        edicaoClient.cadastrarEdicao(massaNomeExistente)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_CONFLICT)
                .body("message", equalTo("Erro na validação dos seguintes campos: nome."))
                .body("status", equalTo(HttpStatus.SC_CONFLICT));

        edicaoClient.deletarPorID(idEdicao);
    }
}
