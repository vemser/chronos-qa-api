package com.chronos.tests.trilha;

import client.TrilhaClient;
import data.factory.TokenFactory;
import data.factory.TrilhaDataFactory;
import io.qameta.allure.*;
import model.trilha.TrilhaRequestDTO;
import model.trilha.TrilhaResponseDTO;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PostTrilhaFuncionalTest {
    private final TrilhaClient trilhaClient = new TrilhaClient();

    @Feature("Trilha")
    @Story("Cadastrar uma trilha com sucesso")
    @Description("Testa se a requisição consegue cadastrar uma trilha deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testCriarUmaTrilhaComSucesso() {

        TrilhaRequestDTO trilhaRequestDTO = TrilhaDataFactory.trilhaComTodosOsCampos();
        TrilhaResponseDTO trilhaResponseDTO = trilhaClient.cadastrar(trilhaRequestDTO)
                .then().log().all()
                .statusCode(200).extract().as(TrilhaResponseDTO.class);

        trilhaClient.deletar(trilhaResponseDTO.getIdTrilha()).then().statusCode(204);

    }
    @Feature("Trilha")
    @Story("Cadastrar uma trilha com sucesso")
    @Description("Testa se a requisição consegue cadastrar uma trilha deve retornar uma mensagem de erro")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testCriarUmaTrilhaSemPreencherOsCamposObrigatorios(){

        TrilhaRequestDTO trilhaRequestDTO = TrilhaDataFactory.trilhaSemCampoObrigatorioPreenchido();
        trilhaClient.cadastrar(trilhaRequestDTO)
                .then()
                .statusCode(400);

    }
    @Feature("Trilha")
    @Story("Cadastrar uma trilha com sucesso")
    @Description("Testa se a requisição consegue cadastrar uma trilha deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testCriarUmaTrilhaPreenchendoApenasOsCamposObrigatorios(){
        trilhaClient.setTOKEN(TokenFactory.getTokenAdmin());

        TrilhaRequestDTO trilhaRequestDTO = TrilhaDataFactory.trilhaComCamposObrigatoriosPreenchidos();
        TrilhaResponseDTO trilhaResponseDTO = trilhaClient.cadastrar(trilhaRequestDTO)
                .then()
                .statusCode(200).extract().as(TrilhaResponseDTO.class);

        trilhaClient.deletar(trilhaResponseDTO.getIdTrilha()).then().statusCode(204);
    }
    @Feature("Trilha")
    @Story("Cadastrar uma trilha com sucesso")
    @Description("Testa se a requisição consegue cadastrar uma trilha deve retornar uma mensagem de erro")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testCriarUmaTrilhaPreenchendoOCampoNomeCom51Caracteres(){

        TrilhaRequestDTO trilhaRequestDTO = TrilhaDataFactory.trilhaComCampoNomeCom51Caracteres();
        trilhaClient.cadastrar(trilhaRequestDTO)
                .then()
                .statusCode(400);
    }
    @Feature("Trilha")
    @Story("Cadastrar uma trilha com sucesso")
    @Description("Testa se a requisição consegue cadastrar uma trilha deve retornar uma mensagem de erro")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testCriarUmaTrilhaPreenchendoOCampoNomeCom1Caracter(){

        TrilhaRequestDTO trilhaRequestDTO = TrilhaDataFactory.trilhaComCampoNomeCom1Caracter();
        trilhaClient.cadastrar(trilhaRequestDTO)
                .then()
                .statusCode(400);

    }
    @Feature("Trilha")
    @Story("Cadastrar uma trilha com sucesso")
    @Description("Testa se a requisição consegue cadastrar uma trilha deve retornar uma mensagem de erro")
    @Severity(SeverityLevel.CRITICAL)

    @Test
    public void testTentarCriarTrilhaComNomeRepetido(){

        TrilhaRequestDTO trilhaRequestDTO = TrilhaDataFactory.trilhaComTodosOsCampos();
        TrilhaRequestDTO trilhaRequestDTONomeRepetido = TrilhaDataFactory.trilhaComTodosOsCampos();
        trilhaRequestDTONomeRepetido.setNome(trilhaRequestDTO.getNome());

        TrilhaResponseDTO trilhaResponseDTO = trilhaClient.cadastrar(trilhaRequestDTO)
                .then()
                .statusCode(200).extract().as(TrilhaResponseDTO.class);

        String message= trilhaClient.cadastrar(trilhaRequestDTONomeRepetido)
                .then()
                .statusCode(409).extract().jsonPath().getString("message");

        assertEquals("Restrição de valor único violada - nome.",message);


        trilhaClient.deletar(trilhaResponseDTO.getIdTrilha()).then().statusCode(204);
    }

    @Feature("Trilha")
    @Story("Cadastrar uma trilha com sucesso")
    @Description("Testa se a requisição consegue cadastrar uma trilha deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testCriarUmaTrilhaPreenchendoOCampoDescricaoCom255Caracteres(){

       TrilhaRequestDTO trilhaRequestDTO = TrilhaDataFactory.trilhaComCampoDescricaoCom255();
       TrilhaResponseDTO trilhaResponseDTO= trilhaClient.cadastrar(trilhaRequestDTO)
                .then()
                .statusCode(200).extract().as(TrilhaResponseDTO.class);

       assertAll("Assert trilha response",
               () -> assertEquals(trilhaRequestDTO.getNome(),trilhaResponseDTO.getNome()),
               () -> assertEquals(trilhaRequestDTO.getDescricao(),trilhaResponseDTO.getDescricao()),
               () -> assertEquals(trilhaRequestDTO.getStatus(),trilhaResponseDTO.getStatus()),
               () -> assertTrue(trilhaResponseDTO.getIdTrilha() > 0)
       );

        trilhaClient.deletar(trilhaResponseDTO.getIdTrilha()).then().statusCode(204);


    }

    @Feature("Trilha")
    @Story("Cadastrar uma trilha com sucesso")
    @Description("Testa se a requisição consegue cadastrar uma trilha deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testCriarUmaTrilhaPreenchendoOCampoDescricaovazio(){

        TrilhaRequestDTO trilhaRequestDTO = TrilhaDataFactory.trilhaComCampoDescricaoVazio();
        TrilhaResponseDTO trilhaResponseDTO= trilhaClient.cadastrar(trilhaRequestDTO)
                .then()
                .statusCode(200).extract().as(TrilhaResponseDTO.class);

        assertAll("Assert trilha response",
                () -> assertEquals(trilhaRequestDTO.getNome(),trilhaResponseDTO.getNome()),
                () -> assertEquals(trilhaRequestDTO.getDescricao(),trilhaResponseDTO.getDescricao()),
                () -> assertEquals(trilhaRequestDTO.getStatus(),trilhaResponseDTO.getStatus()),
                () -> assertTrue(trilhaResponseDTO.getIdTrilha() > 0)
        );

        trilhaClient.deletar(trilhaResponseDTO.getIdTrilha()).then().statusCode(204);

    }
    @Feature("Trilha")
    @Story("Cadastrar uma trilha com sucesso")
    @Description("Testa se a requisição consegue cadastrar uma trilha deve retornar uma mensagem de erro")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testCriarUmaTrilhaPreenchendoOCampoNomeCom256Caracteres(){

        TrilhaRequestDTO trilhaRequestDTO = TrilhaDataFactory.trilhaComCampoDescricaoCom256Caracteres();
        trilhaClient.cadastrar(trilhaRequestDTO)
                .then()
                .statusCode(400);


}}
