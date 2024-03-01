package com.chronos.tests.curriculoMolde;

import client.CurriculoMoldeClient;
import client.TrilhaClient;
import data.factory.CurriculoMoldeDataFactory;
import data.factory.TokenFactory;
import data.factory.TrilhaDataFactory;
import model.trilha.TrilhaRequestDTO;
import model.trilha.TrilhaResponseDTO;
import model.curriculoMolde.CurriculoMoldeDOCXResponseDTO;
import model.curriculoMolde.CurriculoMoldeRequestDTO;
import model.curriculoMolde.CurriculoMoldeResponseDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PutCurriculoMoldeFuncionalTest {
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
//        trilhaClient.setTOKEN(TokenFactory.getTokenAdmin());
//        TrilhaRequestDTO trilhaACadastrar = TrilhaDataFactory.trilhaComTodosOsCampos();
//        trilhaCadastrada =
//                trilhaClient.cadastrar(trilhaACadastrar)
//                        .then()
//                        .statusCode(200)
//                        .extract().as(TrilhaResponseDTO.class);
//
//        idTrilhaCadastrada = trilhaCadastrada.getIdTrilha();
        idTrilhaCadastrada = 93;

        CurriculoMoldeRequestDTO curriculoMoldeACadastrar = CurriculoMoldeDataFactory.gerarCurriculoMoldeComTodosOsCampos();
        curriculoMoldeCadastrado =
                curriculoMoldeClient.cadastrar(idTrilhaCadastrada, curriculoMoldeACadastrar)
                        .then()
                        .statusCode(201)
                        .extract().as(CurriculoMoldeResponseDTO.class);

        idCurriculoMoldeCadastrado = curriculoMoldeCadastrado.getIdCurriculoMolde();

                curriculoMoldeClient.cadastrarArquivo(idTrilhaCadastrada, CurriculoMoldeDataFactory.gerarCurriculoDOCX())
                        .then()
                        .statusCode(201);
    }

    @Test
    public void testAtualizarCurriculoMoldeComTodosOsCamposValidosComSucesso() {
        CurriculoMoldeRequestDTO curriculoMoldeACadastrar = CurriculoMoldeDataFactory.gerarCurriculoMoldeComTodosOsCampos();
        curriculoMoldeCadastrado =
                curriculoMoldeClient.atualizar(idTrilhaCadastrada, curriculoMoldeACadastrar)
                        .then()
                        .statusCode(201)
                        .extract().as(CurriculoMoldeResponseDTO.class);

        idCurriculoMoldeCadastrado = curriculoMoldeCadastrado.getIdCurriculoMolde();
    }

    @Test
    public void testAtualizarCurriculoMoldeComTodosOsCamposValidosSemAuthSemSucesso() {
        CurriculoMoldeRequestDTO curriculoMoldeACadastrar = CurriculoMoldeDataFactory.gerarCurriculoMoldeComTodosOsCampos();
                curriculoMoldeClient.atualizarSemAuth(idTrilhaCadastrada, curriculoMoldeACadastrar)
                        .then()
                        .statusCode(403);
    }

    @Test
    public void testAtualizarCurriculoMoldeComTodosOsCamposECampoQualificacoesComCaracteresAMaisSemSucesso() {
        CurriculoMoldeRequestDTO curriculoMoldeACadastrar = CurriculoMoldeDataFactory.gerarCurriculoMoldeComTodosOsCamposECampoQualificacoesComCaracteresAMais();
                curriculoMoldeClient.atualizar(idTrilhaCadastrada, curriculoMoldeACadastrar)
                        .then()
                        .statusCode(409);
    }

    @Test
    public void testAtualizarCurriculoMoldeComTodosOsCamposECampoEmpresaComCaracteresAMaisSemSucesso() {
        CurriculoMoldeRequestDTO curriculoMoldeACadastrar = CurriculoMoldeDataFactory.gerarCurriculoMoldeComTodosOsCamposECampoEmpresaComCaracteresAMais();
                curriculoMoldeClient.atualizar(idTrilhaCadastrada, curriculoMoldeACadastrar)
                        .then()
                        .statusCode(409);
    }

    @Test
    public void testAtualizarCurriculoMoldeComTodosOsCamposECampoDescricaoComCaracteresAMaisSemSucesso() {
        CurriculoMoldeRequestDTO curriculoMoldeACadastrar = CurriculoMoldeDataFactory.gerarCurriculoMoldeComTodosOsCamposECampoDescricaoComCaracteresAMais();
                curriculoMoldeClient.atualizar(idTrilhaCadastrada, curriculoMoldeACadastrar)
                        .then()
                        .statusCode(409);
    }

    @Test
    public void testAtualizarCurriculoMoldeComTodosOsCamposECampoConhecimentoComCaracteresAMaisSemSucesso() {
        CurriculoMoldeRequestDTO curriculoMoldeACadastrar = CurriculoMoldeDataFactory.gerarCurriculoMoldeComTodosOsCamposECampoConhecimentoComCaracteresAMais();
                curriculoMoldeClient.atualizar(idTrilhaCadastrada, curriculoMoldeACadastrar)
                        .then()
                        .statusCode(409);
    }

    @Test
    public void testAtualizarCurriculoMoldeComTodosOsCamposECampoCargoComCaracteresAMais() {
        CurriculoMoldeRequestDTO curriculoMoldeACadastrar = CurriculoMoldeDataFactory.gerarCurriculoMoldeComTodosOsCamposECampoCargoComCaracteresAMais();
                curriculoMoldeClient.atualizar(idTrilhaCadastrada, curriculoMoldeACadastrar)
                        .then()
                        .statusCode(409);
    }

    @Test
    public void testAtualizarCurriculoMoldeVazioSemSucesso() {
        CurriculoMoldeRequestDTO curriculoMoldeACadastrar = CurriculoMoldeDataFactory.gerarCurriculoMoldeVazio();
                curriculoMoldeClient.atualizar(idTrilhaCadastrada, curriculoMoldeACadastrar)
                        .then()
                        .statusCode(500);
    }

    @Test
    public void testAtualizarrCurriculoMoldeArquivoComTodosOsCamposValidosComSucesso() {
                curriculoMoldeClient.atualizarArquivo(idTrilhaCadastrada, CurriculoMoldeDataFactory.gerarCurriculoDOCX())
                        .then()
                        .statusCode(201);
    }

    @Test
    public void testAtualizarCurriculoMoldeArquivoComTodosOsCamposValidosSemAuthSemSucesso() {
                curriculoMoldeClient.atualizarArquivoSemAuth(idTrilhaCadastrada, CurriculoMoldeDataFactory.gerarCurriculoDOCX())
                        .then()
                        .statusCode(403);
    }
}
