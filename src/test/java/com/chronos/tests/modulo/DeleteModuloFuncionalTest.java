package com.chronos.tests.modulo;

import client.ModuloClient;
import data.factory.ModuloDataFactory;
import io.qameta.allure.*;
import model.modulo.ModuloRequestDTO;
import model.modulo.ModuloResponseDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
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

    @Feature("Modulo")
    @Story("Deletar um modulo com sucesso")
    @Description("Testa se a requisição consegue deletar um modulo deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @Tag("Fumaca")
    public void testDeletarModuloEspecificoPorIdComSucesso() {
        moduloClient.desabilitar(idModuloCadastrado)
                .then()
                .statusCode(204);
    }

    @Feature("Modulo")
    @Story("Deletar um modulo sem sucesso")
    @Description("Testa se a requisição não consegue deletar um modulo deve retornar uma mensagem de erro")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @Tag("Fumaca")
    public void testDeletarModuloEspecificoPorIdSemAutorizacaoSemSucesso() {
        moduloClient.desabilitarSemAuth(idModuloCadastrado)
                .then()
                .statusCode(403);
    }

}
