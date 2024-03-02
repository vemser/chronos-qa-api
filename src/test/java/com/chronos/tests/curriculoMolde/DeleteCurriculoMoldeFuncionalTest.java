package com.chronos.tests.curriculoMolde;

import client.TrilhaClient;
import client.CurriculoMoldeClient;
import data.factory.CurriculoMoldeDataFactory;
import data.factory.TokenFactory;
import data.factory.TrilhaDataFactory;
import io.qameta.allure.*;
import model.curriculoMolde.CurriculoMoldeDOCXResponseDTO;
import model.curriculoMolde.CurriculoMoldeRequestDTO;
import model.curriculoMolde.CurriculoMoldeResponseDTO;
import model.trilha.TrilhaRequestDTO;
import model.trilha.TrilhaResponseDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeleteCurriculoMoldeFuncionalTest {
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
                        .log().all()
                        .statusCode(201)
                        .extract().as(CurriculoMoldeResponseDTO.class);

        idCurriculoMoldeCadastrado = curriculoMoldeCadastrado.getIdCurriculoMolde();


                curriculoMoldeClient.cadastrarArquivo(idTrilhaCadastrada, CurriculoMoldeDataFactory.gerarCurriculoDOCX())
                        .then()
                        .statusCode(201);
    }

    @Feature("Curriculo Molde")
    @Story("Deletar um curriculo molde com sucesso")
    @Description("Testa se a requisição consegue criar um curriculo molde deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testDeletarCurriculoEspecificoPorIdPorTrilhaComSucesso() {
        curriculoMoldeClient.deletarCurriculoEspecificoPorTrilha(idTrilhaCadastrada)
                .then()
                .statusCode(200);
    }
    @Feature("Curriculo Molde")
    @Story("Deletar um curriculo molde sem sucesso")
    @Description("Testa se a requisição consegue criar um curriculo molde deve retornar uma mensagem de erro")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testDeletarCurriculoEspecificoPorIdPorTrilhaSemAuthSemSucesso() {
        curriculoMoldeClient.deletarCurriculoEspecificoPorTrilhaSemAuth(idTrilhaCadastrada)
                .then()
                .statusCode(403);
    }

    @Feature("Curriculo Molde")
    @Story("Deletar um curriculo molde, arquivo DOCX, com sucesso")
    @Description("Testa se a requisição consegue criar um curriculo molde deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testDeletarCurriculoArquivoEspecificoPorIdPorTrilhaComSucesso() {
        curriculoMoldeClient.deletarCurriculoArquivoEspecificoPorTrilha(idTrilhaCadastrada)
                .then()
                .statusCode(204);
    }
    @Feature("Curriculo Molde")
    @Story("Deletar um curriculo molde sem sucesso, sem autorização")
    @Description("Testa se a requisição consegue criar um curriculo molde deve retornar uma mensagem de erro")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testDeletarCurriculoArquivoEspecificoPorIdPorTrilhaSemAuthSemSucesso() {
        curriculoMoldeClient.deletarCurriculoArquivoEspecificoPorTrilhaSemAuth(idTrilhaCadastrada)
                .then()
                .statusCode(403);
    }
}
