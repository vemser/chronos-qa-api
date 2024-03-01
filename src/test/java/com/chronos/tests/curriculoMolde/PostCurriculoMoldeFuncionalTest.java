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
    }

    @Test
    public void testCriarCurriculoMoldeComTodosOsCamposValidosComSucesso() {
        CurriculoMoldeRequestDTO curriculoMoldeACadastrar = CurriculoMoldeDataFactory.gerarCurriculoMoldeComTodosOsCampos();
        curriculoMoldeCadastrado =
                curriculoMoldeClient.cadastrar(idTrilhaCadastrada, curriculoMoldeACadastrar)
                        .then()
                        .statusCode(200)
                        .extract().as(CurriculoMoldeResponseDTO.class);

        idCurriculoMoldeCadastrado = curriculoMoldeCadastrado.getIdCurriculoMolde();
    }

    @Test
    public void testCriarCurriculoMoldeComTodosOsCamposValidosSemAuthSemSucesso() {
        CurriculoMoldeRequestDTO curriculoMoldeACadastrar = CurriculoMoldeDataFactory.gerarCurriculoMoldeComTodosOsCampos();
        curriculoMoldeCadastrado =
                curriculoMoldeClient.cadastrarSemAuth(idTrilhaCadastrada, curriculoMoldeACadastrar)
                        .then()
                        .statusCode(403)
                        .extract().as(CurriculoMoldeResponseDTO.class);

        idCurriculoMoldeCadastrado = curriculoMoldeCadastrado.getIdCurriculoMolde();
    }

    @Test
    public void testCriarCurriculoMoldeComTodosOsCamposECampoQualificacoesComCaracteresAMaisSemSucesso() {
        CurriculoMoldeRequestDTO curriculoMoldeACadastrar = CurriculoMoldeDataFactory.gerarCurriculoMoldeComTodosOsCamposECampoQualificacoesComCaracteresAMais();
        curriculoMoldeCadastrado =
                curriculoMoldeClient.cadastrar(idTrilhaCadastrada, curriculoMoldeACadastrar)
                        .then()
                        .statusCode(400)
                        .extract().as(CurriculoMoldeResponseDTO.class);

        idCurriculoMoldeCadastrado = curriculoMoldeCadastrado.getIdCurriculoMolde();
    }

    @Test
    public void testGerarCurriculoMoldeComTodosOsCamposECampoEmpresaComCaracteresAMaisSemSucesso() {
        CurriculoMoldeRequestDTO curriculoMoldeACadastrar = CurriculoMoldeDataFactory.gerarCurriculoMoldeComTodosOsCamposECampoEmpresaComCaracteresAMais();
        curriculoMoldeCadastrado =
                curriculoMoldeClient.cadastrar(idTrilhaCadastrada, curriculoMoldeACadastrar)
                        .then()
                        .statusCode(400)
                        .extract().as(CurriculoMoldeResponseDTO.class);

        idCurriculoMoldeCadastrado = curriculoMoldeCadastrado.getIdCurriculoMolde();
    }

    @Test
    public void testGerarCurriculoMoldeComTodosOsCamposECampoDescricaoComCaracteresAMaisSemSucesso() {
        CurriculoMoldeRequestDTO curriculoMoldeACadastrar = CurriculoMoldeDataFactory.gerarCurriculoMoldeComTodosOsCamposECampoDescricaoComCaracteresAMais();
        curriculoMoldeCadastrado =
                curriculoMoldeClient.cadastrar(idTrilhaCadastrada, curriculoMoldeACadastrar)
                        .then()
                        .statusCode(400)
                        .extract().as(CurriculoMoldeResponseDTO.class);

        idCurriculoMoldeCadastrado = curriculoMoldeCadastrado.getIdCurriculoMolde();
    }

    @Test
    public void testGerarCurriculoMoldeComTodosOsCamposECampoConhecimentoComCaracteresAMaisSemSucesso() {
        CurriculoMoldeRequestDTO curriculoMoldeACadastrar = CurriculoMoldeDataFactory.gerarCurriculoMoldeComTodosOsCamposECampoConhecimentoComCaracteresAMais();
        curriculoMoldeCadastrado =
                curriculoMoldeClient.cadastrar(idTrilhaCadastrada, curriculoMoldeACadastrar)
                        .then()
                        .statusCode(400)
                        .extract().as(CurriculoMoldeResponseDTO.class);

        idCurriculoMoldeCadastrado = curriculoMoldeCadastrado.getIdCurriculoMolde();
    }

    @Test
    public void testGerarCurriculoMoldeComTodosOsCamposECampoCargoComCaracteresAMais() {
        CurriculoMoldeRequestDTO curriculoMoldeACadastrar = CurriculoMoldeDataFactory.gerarCurriculoMoldeComTodosOsCamposECampoCargoComCaracteresAMais();
        curriculoMoldeCadastrado =
                curriculoMoldeClient.cadastrar(idTrilhaCadastrada, curriculoMoldeACadastrar)
                        .then()
                        .statusCode(400)
                        .extract().as(CurriculoMoldeResponseDTO.class);

        idCurriculoMoldeCadastrado = curriculoMoldeCadastrado.getIdCurriculoMolde();
    }

    @Test
    public void testGerarCurriculoMoldeVazioSemSucesso() {
        CurriculoMoldeRequestDTO curriculoMoldeACadastrar = CurriculoMoldeDataFactory.gerarCurriculoMoldeVazio();
        curriculoMoldeCadastrado =
                curriculoMoldeClient.cadastrar(idTrilhaCadastrada, curriculoMoldeACadastrar)
                        .then()
                        .statusCode(400)
                        .extract().as(CurriculoMoldeResponseDTO.class);

        idCurriculoMoldeCadastrado = curriculoMoldeCadastrado.getIdCurriculoMolde();
    }

    @Test
    public void testCriarCurriculoMoldeArquivoComTodosOsCamposValidosComSucesso() {
        curriculoMoldeDOCXCadastrado =
                curriculoMoldeClient.cadastrarArquivo(idTrilhaCadastrada, CurriculoMoldeDataFactory.gerarCurriculoDOCX())
                        .then()
                        .statusCode(200)
                        .extract().as(CurriculoMoldeDOCXResponseDTO.class);

        idCurriculoMoldeDOCXCadastrado = curriculoMoldeDOCXCadastrado.getIdCurriculoMolde();
    }

    @Test
    public void testCriarCurriculoMoldeArquivoComTodosOsCamposValidosSemAuthSemSucesso() {
        curriculoMoldeDOCXCadastrado =
                curriculoMoldeClient.cadastrarArquivoSemAuth(idTrilhaCadastrada, CurriculoMoldeDataFactory.gerarCurriculoDOCX())
                        .then()
                        .statusCode(403)
                        .extract().as(CurriculoMoldeDOCXResponseDTO.class);

        idCurriculoMoldeDOCXCadastrado = curriculoMoldeDOCXCadastrado.getIdCurriculoMolde();
    }
}
