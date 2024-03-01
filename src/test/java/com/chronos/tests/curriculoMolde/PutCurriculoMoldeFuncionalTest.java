package com.chronos.tests.curriculoMolde;

import client.CurriculoMoldeClient;
import client.TrilhaClient;
import data.factory.CurriculoMoldeDataFactory;
import data.factory.TokenFactory;
import data.factory.TrilhaDataFactory;
import model.TrilhaRequestDTO;
import model.TrilhaResponseDTO;
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
        trilhaClient.setTOKEN(TokenFactory.getTokenAdmin());
        TrilhaRequestDTO trilhaACadastrar = TrilhaDataFactory.trilhaComTodosOsCampos();
        trilhaCadastrada =
                trilhaClient.cadastrar(trilhaACadastrar)
                        .then()
                        .statusCode(200)
                        .extract().as(TrilhaResponseDTO.class);

        idTrilhaCadastrada = trilhaCadastrada.getIdTrilha();

        CurriculoMoldeRequestDTO curriculoMoldeACadastrar = CurriculoMoldeDataFactory.gerarCurriculoMoldeComTodosOsCampos();
        curriculoMoldeCadastrado =
                curriculoMoldeClient.cadastrar(idTrilhaCadastrada, curriculoMoldeACadastrar)
                        .then()
                        .statusCode(200)
                        .extract().as(CurriculoMoldeResponseDTO.class);

        idCurriculoMoldeCadastrado = curriculoMoldeCadastrado.getIdCurriculoMolde();

        curriculoMoldeDOCXCadastrado =
                curriculoMoldeClient.cadastrarArquivo(idTrilhaCadastrada, CurriculoMoldeDataFactory.gerarCurriculoDOCX())
                        .then()
                        .statusCode(200)
                        .extract().as(CurriculoMoldeDOCXResponseDTO.class);

        idCurriculoMoldeDOCXCadastrado = curriculoMoldeDOCXCadastrado.getIdCurriculoMolde();
    }
    @AfterEach
    public void cleanUp() {
        trilhaClient.deletar(idTrilhaCadastrada)
                .then()
                .statusCode(204);
    }

    @Test
    public void testAtualizarCurriculoMoldeComTodosOsCamposValidosComSucesso() {
        CurriculoMoldeRequestDTO curriculoMoldeACadastrar = CurriculoMoldeDataFactory.gerarCurriculoMoldeComTodosOsCampos();
        curriculoMoldeCadastrado =
                curriculoMoldeClient.atualizar(idTrilhaCadastrada, curriculoMoldeACadastrar)
                        .then()
                        .statusCode(200)
                        .extract().as(CurriculoMoldeResponseDTO.class);

        idCurriculoMoldeCadastrado = curriculoMoldeCadastrado.getIdCurriculoMolde();
    }

    @Test
    public void testAtualizarCurriculoMoldeComTodosOsCamposValidosSemAuthSemSucesso() {
        CurriculoMoldeRequestDTO curriculoMoldeACadastrar = CurriculoMoldeDataFactory.gerarCurriculoMoldeComTodosOsCampos();
        curriculoMoldeCadastrado =
                curriculoMoldeClient.atualizarSemAuth(idTrilhaCadastrada, curriculoMoldeACadastrar)
                        .then()
                        .statusCode(403)
                        .extract().as(CurriculoMoldeResponseDTO.class);

        idCurriculoMoldeCadastrado = curriculoMoldeCadastrado.getIdCurriculoMolde();
    }

    @Test
    public void testAtualizarCurriculoMoldeComTodosOsCamposECampoQualificacoesComCaracteresAMaisSemSucesso() {
        CurriculoMoldeRequestDTO curriculoMoldeACadastrar = CurriculoMoldeDataFactory.gerarCurriculoMoldeComTodosOsCamposECampoQualificacoesComCaracteresAMais();
        curriculoMoldeCadastrado =
                curriculoMoldeClient.atualizar(idTrilhaCadastrada, curriculoMoldeACadastrar)
                        .then()
                        .statusCode(400)
                        .extract().as(CurriculoMoldeResponseDTO.class);

        idCurriculoMoldeCadastrado = curriculoMoldeCadastrado.getIdCurriculoMolde();
    }

    @Test
    public void testAtualizarCurriculoMoldeComTodosOsCamposECampoEmpresaComCaracteresAMaisSemSucesso() {
        CurriculoMoldeRequestDTO curriculoMoldeACadastrar = CurriculoMoldeDataFactory.gerarCurriculoMoldeComTodosOsCamposECampoEmpresaComCaracteresAMais();
        curriculoMoldeCadastrado =
                curriculoMoldeClient.atualizar(idTrilhaCadastrada, curriculoMoldeACadastrar)
                        .then()
                        .statusCode(400)
                        .extract().as(CurriculoMoldeResponseDTO.class);

        idCurriculoMoldeCadastrado = curriculoMoldeCadastrado.getIdCurriculoMolde();
    }

    @Test
    public void testAtualizarCurriculoMoldeComTodosOsCamposECampoDescricaoComCaracteresAMaisSemSucesso() {
        CurriculoMoldeRequestDTO curriculoMoldeACadastrar = CurriculoMoldeDataFactory.gerarCurriculoMoldeComTodosOsCamposECampoDescricaoComCaracteresAMais();
        curriculoMoldeCadastrado =
                curriculoMoldeClient.atualizar(idTrilhaCadastrada, curriculoMoldeACadastrar)
                        .then()
                        .statusCode(400)
                        .extract().as(CurriculoMoldeResponseDTO.class);

        idCurriculoMoldeCadastrado = curriculoMoldeCadastrado.getIdCurriculoMolde();
    }

    @Test
    public void testAtualizarCurriculoMoldeComTodosOsCamposECampoConhecimentoComCaracteresAMaisSemSucesso() {
        CurriculoMoldeRequestDTO curriculoMoldeACadastrar = CurriculoMoldeDataFactory.gerarCurriculoMoldeComTodosOsCamposECampoConhecimentoComCaracteresAMais();
        curriculoMoldeCadastrado =
                curriculoMoldeClient.atualizar(idTrilhaCadastrada, curriculoMoldeACadastrar)
                        .then()
                        .statusCode(400)
                        .extract().as(CurriculoMoldeResponseDTO.class);

        idCurriculoMoldeCadastrado = curriculoMoldeCadastrado.getIdCurriculoMolde();
    }

    @Test
    public void testAtualizarCurriculoMoldeComTodosOsCamposECampoCargoComCaracteresAMais() {
        CurriculoMoldeRequestDTO curriculoMoldeACadastrar = CurriculoMoldeDataFactory.gerarCurriculoMoldeComTodosOsCamposECampoCargoComCaracteresAMais();
        curriculoMoldeCadastrado =
                curriculoMoldeClient.atualizar(idTrilhaCadastrada, curriculoMoldeACadastrar)
                        .then()
                        .statusCode(400)
                        .extract().as(CurriculoMoldeResponseDTO.class);

        idCurriculoMoldeCadastrado = curriculoMoldeCadastrado.getIdCurriculoMolde();
    }

    @Test
    public void testAtualizarCurriculoMoldeVazioSemSucesso() {
        CurriculoMoldeRequestDTO curriculoMoldeACadastrar = CurriculoMoldeDataFactory.gerarCurriculoMoldeVazio();
        curriculoMoldeCadastrado =
                curriculoMoldeClient.atualizar(idTrilhaCadastrada, curriculoMoldeACadastrar)
                        .then()
                        .statusCode(400)
                        .extract().as(CurriculoMoldeResponseDTO.class);

        idCurriculoMoldeCadastrado = curriculoMoldeCadastrado.getIdCurriculoMolde();
    }

    @Test
    public void testAtualizarrCurriculoMoldeArquivoComTodosOsCamposValidosComSucesso() {
        curriculoMoldeDOCXCadastrado =
                curriculoMoldeClient.atualizarArquivo(idTrilhaCadastrada, CurriculoMoldeDataFactory.gerarCurriculoDOCX())
                        .then()
                        .statusCode(200)
                        .extract().as(CurriculoMoldeDOCXResponseDTO.class);

        idCurriculoMoldeDOCXCadastrado = curriculoMoldeDOCXCadastrado.getIdCurriculoMolde();
    }

    @Test
    public void testAtualizarCurriculoMoldeArquivoComTodosOsCamposValidosSemAuthSemSucesso() {
        curriculoMoldeDOCXCadastrado =
                curriculoMoldeClient.atualizarArquivoSemAuth(idTrilhaCadastrada, CurriculoMoldeDataFactory.gerarCurriculoDOCX())
                        .then()
                        .statusCode(403)
                        .extract().as(CurriculoMoldeDOCXResponseDTO.class);

        idCurriculoMoldeDOCXCadastrado = curriculoMoldeDOCXCadastrado.getIdCurriculoMolde();
    }
}
