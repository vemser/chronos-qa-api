package com.chronos.tests.trilha;

import client.TrilhaClient;
import data.factory.TokenFactory;
import data.factory.TrilhaDataFactory;
import model.trilha.TrilhaRequestDTO;
import model.trilha.TrilhaResponseDTO;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PostTrilhaFuncionalTest {
    private final TrilhaClient trilhaClient = new TrilhaClient();


    @Test
    public void testCriarUmaTrilhaComSucesso() {
        trilhaClient.setTOKEN(TokenFactory.getTokenAdmin());

        TrilhaRequestDTO trilhaRequestDTO = TrilhaDataFactory.trilhaComTodosOsCampos();
        TrilhaResponseDTO trilhaResponseDTO = trilhaClient.cadastrar(trilhaRequestDTO)
                .then()
                .statusCode(200).extract().as(TrilhaResponseDTO.class);

        trilhaClient.deletar(trilhaResponseDTO.getIdTrilha()).then().statusCode(204);

    }

    @Test
    public void testCriarUmaTrilhaSemPreencherOsCamposObrigatorios(){
        trilhaClient.setTOKEN(TokenFactory.getTokenAdmin());

        TrilhaRequestDTO trilhaRequestDTO = TrilhaDataFactory.trilhaSemCampoObrigatorioPreenchido();
        trilhaClient.cadastrar(trilhaRequestDTO)
                .then()
                .statusCode(400);

    }

    @Test
    public void testCriarUmaTrilhaPreenchendoApenasOsCamposObrigatorios(){
        trilhaClient.setTOKEN(TokenFactory.getTokenAdmin());

        TrilhaRequestDTO trilhaRequestDTO = TrilhaDataFactory.trilhaComCamposObrigatoriosPreenchidos();
        trilhaClient.cadastrar(trilhaRequestDTO)
                .then()
                .statusCode(200).extract().as(TrilhaResponseDTO.class);


    }

    @Test
    public void testCriarUmaTrilhaPreenchendoOCampoNomeCom51Caracteres(){
        trilhaClient.setTOKEN(TokenFactory.getTokenAdmin());

        TrilhaRequestDTO trilhaRequestDTO = TrilhaDataFactory.trilhaComCampoNomeCom51Caracteres();
        trilhaClient.cadastrar(trilhaRequestDTO)
                .then()
                .statusCode(400);
    }

    @Test
    public void testCriarUmaTrilhaPreenchendoOCampoNomeCom1Caracter(){
        trilhaClient.setTOKEN(TokenFactory.getTokenAdmin());

        TrilhaRequestDTO trilhaRequestDTO = TrilhaDataFactory.trilhaComCampoNomeCom1Caracter();
        trilhaClient.cadastrar(trilhaRequestDTO)
                .then()
                .statusCode(400);

    }


    @Test
    public void testTentarCriarTrilhaComNomeRepetido(){

        trilhaClient.setTOKEN(TokenFactory.getTokenAdmin());

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


    @Test
    public void testCriarUmaTrilhaPreenchendoOCampoDescricaoCom255Caracteres(){
        trilhaClient.setTOKEN(TokenFactory.getTokenAdmin());

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


    @Test
    public void testCriarUmaTrilhaPreenchendoOCampoDescricaovazio(){
        trilhaClient.setTOKEN(TokenFactory.getTokenAdmin());

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

    @Test
    public void testCriarUmaTrilhaPreenchendoOCampoNomeCom256Caracteres(){
        trilhaClient.setTOKEN(TokenFactory.getTokenAdmin());

        TrilhaRequestDTO trilhaRequestDTO = TrilhaDataFactory.trilhaComCampoDescricaoCom256Caracteres();
        trilhaClient.cadastrar(trilhaRequestDTO)
                .then()
                .statusCode(400);


}}
