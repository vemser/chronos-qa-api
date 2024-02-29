package com.chronos.tests.modulo;

import client.ModuloClient;
import data.factory.ModuloDataFactory;
import model.ModuloRequestDTO;
import model.ModuloResponseDTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DeleteModuloFuncionalTest {
    Integer idModuloCadastrado = 0;
    ModuloClient moduloClient = new ModuloClient();
    ModuloResponseDTO moduloCadastrado;
    @Before
    public void setUp() {
        ModuloRequestDTO moduloACadastrar = ModuloDataFactory.moduloComTodosOsCampos();
        moduloCadastrado =
                moduloClient.cadastrar(moduloACadastrar)
                        .then()
                        .statusCode(200)
                        .extract().as(ModuloResponseDTO.class);

        idModuloCadastrado = moduloCadastrado.getIdModulo();
    }
    @After
    public void cleanUp() {
        moduloClient.deletar(idModuloCadastrado)
                .then()
                .statusCode(204);
    }
    @Test
    public void testDeletarModuloEspecificoPorIdComSucesso() {
                moduloClient.desabilitar(idModuloCadastrado)
                        .then()
                        .statusCode(204);
    }

    @Test
    public void testDeletarModuloEspecificoPorIdSemAutorizacaoSemSucesso() {
        moduloClient.desabilitarSemAuth(idModuloCadastrado)
                .then()
                .statusCode(403);
    }

    @Test
    public void testDeletarModuloEspecificoPorIdInvalidoSemSucesso() {
                moduloClient.desabilitar(0)
                        .then()
                        .statusCode(400);
    }

}
