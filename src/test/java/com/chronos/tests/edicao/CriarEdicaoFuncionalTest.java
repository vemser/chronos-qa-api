package com.chronos.tests.edicao;

import client.EdicaoClient;
import data.factory.EdicaoFactory;
import model.edicao.EdicaoRequestDTO;
import model.edicao.EdicaoResponseDTO;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

public class CriarEdicaoFuncionalTest {

    private final EdicaoClient edicaoClient = new EdicaoClient();

    @ParameterizedTest
    @MethodSource("data.provider.EdicaoProvider#criarEdicaoComSucessoProvider")
    @DisplayName("Teste: Deve criar uma nova edição com sucesso")
    public void testDeveCriarUmEdicaoComSucesso(EdicaoRequestDTO requestDTO, Integer responseExpected) {

        EdicaoResponseDTO responseDTO = edicaoClient.cadastrarEdicao(requestDTO)
                .then()
                    .statusCode(responseExpected)
                    .extract().as(EdicaoResponseDTO.class);

        assertAll("responseDTO",
                () -> assertEquals(responseDTO.getNome(), requestDTO.getNome()),
                () -> assertEquals(responseDTO.getDescricao(), requestDTO.getDescricao()),
                () -> assertEquals(responseDTO.getDataInicial(), requestDTO.getDataInicial()),
                () -> assertEquals(responseDTO.getDataFinal(), requestDTO.getDataFinal()),
                () -> assertEquals(9, responseDTO.getEtapas().size()),
                () -> assertNotNull(responseDTO.getIdEdicao()),
                () -> assertTrue(responseDTO.getTrilhas().isEmpty()),
                () -> assertTrue(responseDTO.getEstagiarios().isEmpty())
        );

        edicaoClient.deletar(responseDTO.getIdEdicao())
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
    public void testNaoDeveCadastrarEdicaoPoisTokenInvalido() {
        edicaoClient.cadastrarEdicaoSemToken(EdicaoFactory.edicaoValida())
                .then()
                .statusCode(HttpStatus.SC_FORBIDDEN)
                .body("error", equalTo("Forbidden"));
    }
}
