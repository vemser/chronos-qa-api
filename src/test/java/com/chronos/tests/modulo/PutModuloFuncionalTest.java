package com.chronos.tests.modulo;

import client.ModuloClient;
import data.factory.ModuloDataFactory;
import model.ModuloRequestDTO;
import model.ModuloResponseDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PutModuloFuncionalTest {
    Integer idModuloCadastrado = 0;
    ModuloClient moduloClient = new ModuloClient();
    ModuloResponseDTO moduloCadastrado;

    @BeforeEach
    public void setUp() {
        ModuloRequestDTO moduloACadastrar = ModuloDataFactory.moduloComTodosOsCampos();
        moduloCadastrado =
                moduloClient.cadastrar(moduloACadastrar)
                        .then()
                        .statusCode(201)
                        .extract().as(ModuloResponseDTO.class);

        idModuloCadastrado = moduloCadastrado.getIdModulo();
    }

    @AfterEach
    public void cleanUp() {
        if (idModuloCadastrado != 0) {
            moduloClient.deletar(idModuloCadastrado)
                    .then()
                    .statusCode(204);
        }
    }

    @Test
    public void testEditarModuloComTodosOsCamposValidosComSucesso() {
        ModuloRequestDTO moduloAEditar = ModuloDataFactory.moduloComTodosOsCampos();
                moduloClient.atualizar(idModuloCadastrado, moduloAEditar)
                        .then()
                        .statusCode(200);
    }

    @Test
    public void testEditarModuloComTodosOsCamposObrigatoriosValidosComSucesso() {
        ModuloRequestDTO moduloAEditar = ModuloDataFactory.moduloSemCamposNaoObrigatoriosPreenchidos();
        moduloClient.atualizar(idModuloCadastrado, moduloAEditar)
                .then()
                .statusCode(400);
    }

    @Test
    public void testEditarModuloComTodosOsCamposNaoObrigatoriosValidosSemObrigatoriosSemSucesso() {
        ModuloRequestDTO moduloAEditar = ModuloDataFactory.moduloSemCamposObrigatoriosPreenchidos();
        moduloClient.atualizar(idModuloCadastrado, moduloAEditar)
                .then()
                .statusCode(400);
    }

    @Test
    public void testEditarModuloComCampoNomeComCaracteresAMenosSemSucesso() {
        ModuloRequestDTO moduloAEditar = ModuloDataFactory.moduloComNomeComCaracterAMenos();
        moduloClient.atualizar(idModuloCadastrado, moduloAEditar)
                .then()
                .statusCode(400);
    }

    @Test
    public void testEditarModuloComCampoNomeComCaracteresAMaisSemSucesso() {
        ModuloRequestDTO moduloAEditar = ModuloDataFactory.moduloComNomeComCaracterAMais();
        moduloClient.atualizar(idModuloCadastrado, moduloAEditar)
                .then()
                .statusCode(400);
    }

    @Test
    public void testEditarModuloComCampoConteudoProgramaticoComCaracteresAMenosSemSucesso() {
        ModuloRequestDTO moduloAEditar = ModuloDataFactory.moduloComConteudoProgramaticoComCaracterAMenos();
        moduloClient.atualizar(idModuloCadastrado, moduloAEditar)
                .then()
                .statusCode(400);
    }

    @Test
    public void testEditarModuloComCampoConteudoProgramaticoComCaracteresAMaisSemSucesso() {
        ModuloRequestDTO moduloAEditar = ModuloDataFactory.moduloComConteudoProgramaticoComCaracterAMais();
        moduloClient.atualizar(idModuloCadastrado, moduloAEditar)
                .then()
                .statusCode(400);
    }

    @Test
    public void testEditarModuloComTodosOsCamposSemAutorizacaoSemSucesso() {
        ModuloRequestDTO moduloAEditar = ModuloDataFactory.moduloComTodosOsCampos();
        moduloClient.atualizarSemAuth(idModuloCadastrado, moduloAEditar)
                .then()
                .statusCode(403);
    }

    @Test
    public void testDeletarModuloEspecificoPorIdInvalidoSemSucesso() {
        ModuloRequestDTO moduloAEditar = ModuloDataFactory.moduloComTodosOsCampos();
        moduloClient.atualizar(0, moduloAEditar)
                .then()
                .statusCode(400);
    }
}
