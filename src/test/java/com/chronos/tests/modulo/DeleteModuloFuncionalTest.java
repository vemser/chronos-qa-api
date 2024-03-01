package com.chronos.tests.modulo;

import client.ModuloClient;
import data.factory.ModuloDataFactory;
import model.ModuloRequestDTO;
import model.ModuloResponseDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeleteModuloFuncionalTest {
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

}
