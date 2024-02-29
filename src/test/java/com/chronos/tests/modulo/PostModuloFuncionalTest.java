package com.chronos.tests.modulo;

import client.ModuloClient;
import data.factory.ModuloDataFactory;
import model.ModuloRequestDTO;
import model.ModuloResponseDTO;
import org.junit.After;
import org.junit.jupiter.api.Test;


public class PostModuloFuncionalTest {
    Integer idModuloCadastrado = 0;
    ModuloClient moduloClient = new ModuloClient();
    ModuloResponseDTO moduloCadastrado;

    @After
    public void cleanUp() {
        if (idModuloCadastrado != 0) {
            moduloClient.deletar(idModuloCadastrado)
                    .then()
                    .statusCode(204);
        }
    }
    @Test
    public void testCriarModuloComTodosOsCamposValidosComSucesso() {
        ModuloRequestDTO moduloACadastrar = ModuloDataFactory.moduloComTodosOsCampos();
        moduloCadastrado =
                moduloClient.cadastrar(moduloACadastrar)
                        .then().log().all()
                        .statusCode(200)
                        .extract().as(ModuloResponseDTO.class);

        idModuloCadastrado = moduloCadastrado.getIdModulo();
    }

    @Test
    public void testCriarModuloComTodosOsCamposObrigatoriosValidosComSucesso() {
        ModuloRequestDTO moduloACadastrar = ModuloDataFactory.moduloSemCamposNaoObrigatoriosPreenchidos();
                moduloClient.cadastrar(moduloACadastrar)
                        .then()
                        .statusCode(400);
    }

    @Test
    public void testCriarModuloComTodosOsCamposNaoObrigatoriosValidosSemObrigatoriosSemSucesso() {
        ModuloRequestDTO moduloACadastrar = ModuloDataFactory.moduloSemCamposObrigatoriosPreenchidos();
                moduloClient.cadastrar(moduloACadastrar)
                        .then()
                        .statusCode(400);
    }

    @Test
    public void testCriarModuloComCampoNomeComCaracteresAMenosSemSucesso() {
        ModuloRequestDTO moduloACadastrar = ModuloDataFactory.moduloComNomeComCaracterAMenos();
                moduloClient.cadastrar(moduloACadastrar)
                        .then()
                        .statusCode(400);
    }

    @Test
    public void testCriarModuloComCampoNomeComCaracteresAMaisSemSucesso() {
        ModuloRequestDTO moduloACadastrar = ModuloDataFactory.moduloComNomeComCaracterAMais();
                moduloClient.cadastrar(moduloACadastrar)
                        .then()
                        .statusCode(400);
    }

    @Test
    public void testCriarModuloComCampoConteudoProgramaticoComCaracteresAMenosSemSucesso() {
        ModuloRequestDTO moduloACadastrar = ModuloDataFactory.moduloComConteudoProgramaticoComCaracterAMenos();
        moduloClient.cadastrar(moduloACadastrar)
                .then()
                .statusCode(400);
    }

    @Test
    public void testCriarModuloComCampoConteudoProgramaticoComCaracteresAMaisSemSucesso() {
        ModuloRequestDTO moduloACadastrar = ModuloDataFactory.moduloComConteudoProgramaticoComCaracterAMais();
        moduloClient.cadastrar(moduloACadastrar)
                .then()
                .statusCode(400);
    }

    @Test
    public void testCriarModuloComTodosOsCamposSemAutorizacaoSemSucesso() {
        ModuloRequestDTO moduloACadastrar = ModuloDataFactory.moduloComTodosOsCampos();
        moduloClient.cadastrarSemAuth(moduloACadastrar)
                .then()
                .statusCode(403);
    }
}
