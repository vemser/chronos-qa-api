package com.chronos.tests.modulo;

import client.ModuloClient;
import data.factory.ModuloDataFactory;
import io.qameta.allure.*;
import model.ModuloRequestDTO;
import model.ModuloResponseDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;


public class PostModuloFuncionalTest {
    Integer idModuloCadastrado = 0;
    ModuloClient moduloClient = new ModuloClient();
    ModuloResponseDTO moduloCadastrado;

    @AfterEach
    public void cleanUp() {
        if (idModuloCadastrado != 0) {
            moduloClient.deletar(idModuloCadastrado)
                    .then()
                    .statusCode(204);
        }
    }

    @Feature("Modulo")
    @Story("Cria um modulo com sucesso")
    @Description("Testa se a requisição consegue criar um modulo deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testCriarModuloComTodosOsCamposValidosComSucesso() {
        ModuloRequestDTO moduloACadastrar = ModuloDataFactory.moduloComTodosOsCampos();
        moduloCadastrado =
                moduloClient.cadastrar(moduloACadastrar)
                        .then()
                        .statusCode(201)
                        .extract().as(ModuloResponseDTO.class);

        idModuloCadastrado = moduloCadastrado.getIdModulo();
    }


    @Feature("Modulo")
    @Story("Cria um modulo com sucesso")
    @Description("Testa se a requisição consegue criar um modulo deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testCriarModuloComTodosOsCamposObrigatoriosValidosComSucesso() {
        ModuloRequestDTO moduloACadastrar = ModuloDataFactory.moduloSemCamposNaoObrigatoriosPreenchidos();
                moduloClient.cadastrar(moduloACadastrar)
                        .then()
                        .statusCode(201);
    }

    @Feature("Modulo")
    @Story("Cria um modulo sem sucesso")
    @Description("Testa se a requisição não consegue criar um modulo deve retornar uma mensagem de erro")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testCriarModuloComTodosOsCamposNaoObrigatoriosValidosSemObrigatoriosSemSucesso() {
        ModuloRequestDTO moduloACadastrar = ModuloDataFactory.moduloSemCamposObrigatoriosPreenchidos();
                moduloClient.cadastrar(moduloACadastrar)
                        .then()
                        .statusCode(400);
    }


    @Feature("Modulo")
    @Story("Cria um modulo sem sucesso")
    @Description("Testa se a requisição não consegue criar um modulo deve retornar uma mensagem de erro")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testCriarModuloComCampoNomeComCaracteresAMenosSemSucesso() {
        ModuloRequestDTO moduloACadastrar = ModuloDataFactory.moduloComNomeComCaracterAMenos();
                moduloClient.cadastrar(moduloACadastrar)
                        .then()
                        .statusCode(400);
    }


    @Feature("Modulo")
    @Story("Cria um modulo sem sucesso")
    @Description("Testa se a requisição não consegue criar um modulo deve retornar uma mensagem de erro")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testCriarModuloComCampoNomeComCaracteresAMaisSemSucesso() {
        ModuloRequestDTO moduloACadastrar = ModuloDataFactory.moduloComNomeComCaracterAMais();
                moduloClient.cadastrar(moduloACadastrar)
                        .then()
                        .statusCode(400);
    }



    @Feature("Modulo")
    @Story("Cria um modulo sem sucesso")
    @Description("Testa se a requisição não consegue criar um modulo deve retornar uma mensagem de erro")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testCriarModuloComCampoConteudoProgramaticoComCaracteresAMenosSemSucesso() {
        ModuloRequestDTO moduloACadastrar = ModuloDataFactory.moduloComConteudoProgramaticoComCaracterAMais();
        moduloClient.cadastrar(moduloACadastrar)
                .then()
                .statusCode(400);
    }


    @Feature("Modulo")
    @Story("Cria um modulo sem sucesso")
    @Description("Testa se a requisição não consegue criar um modulo deve retornar uma mensagem de erro")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testCriarModuloComCampoConteudoProgramaticoComCaracteresAMaisSemSucesso() {
        ModuloRequestDTO moduloACadastrar = ModuloDataFactory.moduloComConteudoProgramaticoComCaracterAMais();
        moduloClient.cadastrar(moduloACadastrar)
                .then()
                .statusCode(400);
    }


    @Feature("Modulo")
    @Story("Cria um modulo sem sucesso")
    @Description("Testa se a requisição não consegue criar um modulo deve retornar uma mensagem de erro")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testCriarModuloComTodosOsCamposSemAutorizacaoSemSucesso() {
        ModuloRequestDTO moduloACadastrar = ModuloDataFactory.moduloComTodosOsCampos();
        moduloClient.cadastrarSemAuth(moduloACadastrar)
                .then()
                .statusCode(403);
    }
}
