package com.chronos.tests.curriculoMolde;

import client.CurriculoMoldeClient;
import client.TrilhaClient;
import data.factory.CurriculoMoldeDataFactory;
import data.factory.TokenFactory;
import data.factory.TrilhaDataFactory;
import io.qameta.allure.*;
import model.trilha.TrilhaRequestDTO;
import model.trilha.TrilhaResponseDTO;
import model.curriculoMolde.CurriculoMoldeDOCXResponseDTO;
import model.curriculoMolde.CurriculoMoldeRequestDTO;
import model.curriculoMolde.CurriculoMoldeResponseDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PostCurriculoMoldeFuncionalTest {
    Integer idTrilhaCadastrada = 0;
    Integer idCurriculoMoldeCadastrado = 0;
    Integer idCurriculoMoldeDOCXCadastrado = 0;
    TrilhaClient trilhaClient = new TrilhaClient();
    CurriculoMoldeClient curriculoMoldeClient = new CurriculoMoldeClient();
    TrilhaResponseDTO trilhaCadastrada;
    CurriculoMoldeResponseDTO curriculoMoldeCadastrado;
    CurriculoMoldeDOCXResponseDTO curriculoMoldeDOCXCadastrado;

    @BeforeEach
    public void setUp() {
        trilhaClient.setTOKEN(TokenFactory.getTokenAdmin());
        TrilhaRequestDTO trilhaACadastrar = TrilhaDataFactory.trilhaComTodosOsCampos();
        trilhaCadastrada =
                trilhaClient.cadastrar(trilhaACadastrar)
                        .then()
                        .statusCode(200)
                        .extract().as(TrilhaResponseDTO.class);

        idTrilhaCadastrada = trilhaCadastrada.getIdTrilha();
    }

    @AfterEach
    public void cleanUp() {
        trilhaClient.deletar(idTrilhaCadastrada)
                .then()
                .statusCode(204);
    }

    @Feature("Curriculo Molde")
    @Story("Criar um curriculo molde com sucesso")
    @Description("Testa se a requisição consegue criar um curriculo molde deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testCriarCurriculoMoldeComTodosOsCamposValidosComSucesso() {
        CurriculoMoldeRequestDTO curriculoMoldeACadastrar = CurriculoMoldeDataFactory.gerarCurriculoMoldeComTodosOsCampos();
        curriculoMoldeCadastrado =
                curriculoMoldeClient.cadastrar(idTrilhaCadastrada, curriculoMoldeACadastrar)
                        .then()
                        .statusCode(201)
                        .extract().as(CurriculoMoldeResponseDTO.class);

        idCurriculoMoldeCadastrado = curriculoMoldeCadastrado.getIdCurriculoMolde();
    }

    @Feature("Curriculo Molde")
    @Story("Criar um curriculo molde sem sucesso, sem auth")
    @Description("Testa se a requisição consegue criar um curriculo molde deve retornar uma mensagem de erro")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testCriarCurriculoMoldeComTodosOsCamposValidosSemAuthSemSucesso() {
        CurriculoMoldeRequestDTO curriculoMoldeACadastrar = CurriculoMoldeDataFactory.gerarCurriculoMoldeComTodosOsCampos();
                curriculoMoldeClient.cadastrarSemAuth(idTrilhaCadastrada, curriculoMoldeACadastrar)
                        .then()
                        .statusCode(403);
    }

    @Feature("Curriculo Molde")
    @Story("Criar um curriculo molde sem sucesso")
    @Description("Testa se a requisição consegue criar um curriculo molde deve retornar uma mensagem de erro")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testCriarCurriculoMoldeComTodosOsCamposECampoQualificacoesComCaracteresAMaisSemSucesso() {
        CurriculoMoldeRequestDTO curriculoMoldeACadastrar = CurriculoMoldeDataFactory.gerarCurriculoMoldeComTodosOsCamposECampoQualificacoesComCaracteresAMais();
                curriculoMoldeClient.cadastrar(idTrilhaCadastrada, curriculoMoldeACadastrar)
                        .then()
                        .statusCode(409);
    }

    @Feature("Curriculo Molde")
    @Story("Criar um curriculo molde sem sucesso")
    @Description("Testa se a requisição consegue criar um curriculo molde deve retornar uma mensagem de erro")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testGerarCurriculoMoldeComTodosOsCamposECampoEmpresaComCaracteresAMaisSemSucesso() {
        CurriculoMoldeRequestDTO curriculoMoldeACadastrar = CurriculoMoldeDataFactory.gerarCurriculoMoldeComTodosOsCamposECampoEmpresaComCaracteresAMais();
                curriculoMoldeClient.cadastrar(idTrilhaCadastrada, curriculoMoldeACadastrar)
                        .then()
                        .statusCode(409);
    }

    @Feature("Curriculo Molde")
    @Story("Criar um curriculo molde sem sucesso")
    @Description("Testa se a requisição consegue criar um curriculo molde deve retornar uma mensagem de erro")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testGerarCurriculoMoldeComTodosOsCamposECampoDescricaoComCaracteresAMaisSemSucesso() {
        CurriculoMoldeRequestDTO curriculoMoldeACadastrar = CurriculoMoldeDataFactory.gerarCurriculoMoldeComTodosOsCamposECampoDescricaoComCaracteresAMais();
                curriculoMoldeClient.cadastrar(idTrilhaCadastrada, curriculoMoldeACadastrar)
                        .then()
                        .statusCode(409);
    }

    @Feature("Curriculo Molde")
    @Story("Criar um curriculo molde sem sucesso")
    @Description("Testa se a requisição consegue criar um curriculo molde deve retornar uma mensagem de erro")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testGerarCurriculoMoldeComTodosOsCamposECampoConhecimentoComCaracteresAMaisSemSucesso() {
        CurriculoMoldeRequestDTO curriculoMoldeACadastrar = CurriculoMoldeDataFactory.gerarCurriculoMoldeComTodosOsCamposECampoConhecimentoComCaracteresAMais();
                curriculoMoldeClient.cadastrar(idTrilhaCadastrada, curriculoMoldeACadastrar)
                        .then()
                        .statusCode(409);
    }

    @Feature("Curriculo Molde")
    @Story("Criar um curriculo molde sem sucesso")
    @Description("Testa se a requisição consegue criar um curriculo molde deve retornar uma mensagem de erro")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testGerarCurriculoMoldeComTodosOsCamposECampoCargoComCaracteresAMais() {
        CurriculoMoldeRequestDTO curriculoMoldeACadastrar = CurriculoMoldeDataFactory.gerarCurriculoMoldeComTodosOsCamposECampoCargoComCaracteresAMais();
                curriculoMoldeClient.cadastrar(idTrilhaCadastrada, curriculoMoldeACadastrar)
                        .then()
                        .statusCode(409);
    }

    @Feature("Curriculo Molde")
    @Story("Criar um curriculo molde sem sucesso")
    @Description("Testa se a requisição consegue criar um curriculo molde deve retornar uma mensagem de erro")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testGerarCurriculoMoldeVazioSemSucesso() {
        CurriculoMoldeRequestDTO curriculoMoldeACadastrar = CurriculoMoldeDataFactory.gerarCurriculoMoldeVazio();
                curriculoMoldeClient.cadastrar(idTrilhaCadastrada, curriculoMoldeACadastrar)
                        .then()
                        .statusCode(500);
    }

    @Feature("Curriculo Molde")
    @Story("Criar um curriculo molde sem sucesso")
    @Description("Testa se a requisição consegue criar um curriculo molde deve retornar uma mensagem de erro")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testCriarCurriculoMoldeArquivoComTodosOsCamposValidosComSucesso() {
                curriculoMoldeClient.cadastrarArquivo(idTrilhaCadastrada, CurriculoMoldeDataFactory.gerarCurriculoDOCX())
                        .then()
                        .statusCode(201);
    }

    @Feature("Curriculo Molde")
    @Story("Criar um curriculo molde sem sucesso")
    @Description("Testa se a requisição consegue criar um curriculo molde deve retornar uma mensagem de erro")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testCriarCurriculoMoldeArquivoComTodosOsCamposValidosSemAuthSemSucesso() {
                curriculoMoldeClient.cadastrarArquivoSemAuth(idTrilhaCadastrada, CurriculoMoldeDataFactory.gerarCurriculoDOCX())
                        .then()
                        .statusCode(403);
    }
}
