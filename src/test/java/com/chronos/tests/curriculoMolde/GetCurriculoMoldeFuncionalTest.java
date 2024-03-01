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

public class GetCurriculoMoldeFuncionalTest {
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
    public void testBuscarCurriculoPorIdTrilhaComSucesso() {
        curriculoMoldeClient.buscarCurriculoEspecificoPorTrilha(idTrilhaCadastrada)
                .then()
                .statusCode(200);
    }
    @Test
    public void testBuscarCurriculoPorIdTrilhaSemAuthSemSucesso() {
        curriculoMoldeClient.buscarCurriculoEspecificoPorTrilhaSemAuth(idTrilhaCadastrada)
                .then()
                .statusCode(403);
    }

    @Test
    public void testbuscarCurriculoArquivoEspecificoPorTrilhaComSucesso() {
        curriculoMoldeClient.buscarCurriculoArquivoEspecificoPorTrilha(idTrilhaCadastrada)
                .then()
                .statusCode(200);
    }

    @Test
    public void testbuscarCurriculoArquivoEspecificoPorTrilhaSemAuthSemSucesso() {
        curriculoMoldeClient.buscarCurriculoArquivoEspecificoPorTrilhaSemAuth(idTrilhaCadastrada)
                .then()
                .statusCode(403);
    }
}
