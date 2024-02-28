package com.chronos.tests;

import client.TrilhaClient;
import data.factory.TokenFactory;
import data.factory.TrilhaDataFactory;
import model.TrilhaRequestDTO;
import model.TrilhaResponseDTO;
import org.junit.jupiter.api.Test;


public class TrilhaFuncionalTest {
    private final TrilhaClient trilhaClient = new TrilhaClient();

    @Test
    public void testCriarUmaTrilhaComSucesso() {
        trilhaClient.setTOKEN(TokenFactory.getTokenAdmin());

        TrilhaRequestDTO trilhaRequestDTO = TrilhaDataFactory.trilhaComTodosOsCampos();
        TrilhaResponseDTO trilhaResponseDTO = trilhaClient.cadastrar(trilhaRequestDTO)
                .then()
                .log().all()
                .statusCode(200).extract().as(TrilhaResponseDTO.class);

        trilhaClient.deletar(trilhaResponseDTO.getIdTrilha()).then().statusCode(204);

    }

    @Test
    public void testCriarUmaTrilhaSemPreencherOsCamposObrigatorios(){
        trilhaClient.setTOKEN(TokenFactory.getTokenAdmin());

        TrilhaRequestDTO trilhaRequestDTO = TrilhaDataFactory.trilhaSemCampoObrigatorioPreenchido();
        TrilhaResponseDTO trilhaResponseDTO = trilhaClient.cadastrar(trilhaRequestDTO)
                .then()
                .statusCode(400).extract().as(TrilhaResponseDTO.class);

    }

    @Test
    public void testCriarUmaTrilhaPreenchendoOsCamposObrigatorios(){
        trilhaClient.setTOKEN(TokenFactory.getTokenAdmin());

        TrilhaRequestDTO trilhaRequestDTO = TrilhaDataFactory.trilhaComCamposObrigatoriosPreenchidos();
        TrilhaResponseDTO trilhaResponseDTO = trilhaClient.cadastrar(trilhaRequestDTO)
                .then()
                .statusCode(400).extract().as(TrilhaResponseDTO.class);


    }

    @Test
    public void testCriarUmaTrilhaPreenchendoOCampoNomeCom51Caracteres(){
        trilhaClient.setTOKEN(TokenFactory.getTokenAdmin());

        TrilhaRequestDTO trilhaRequestDTO = TrilhaDataFactory.trilhaComCampoNomeCom51Caracteres();
        trilhaClient.cadastrar(trilhaRequestDTO)
                .then()
                .statusCode(400)
                .extract().as(TrilhaResponseDTO.class);
    }

    @Test
    public void testCriarUmaTrilhaPreenchendoOCampoNomeCom1Caracter(){
        trilhaClient.setTOKEN(TokenFactory.getTokenAdmin());

        TrilhaRequestDTO trilhaRequestDTO = TrilhaDataFactory.trilhaComCampoNomeCom1Caracter();
        TrilhaResponseDTO trilhaResponseDTO = trilhaClient.cadastrar(trilhaRequestDTO)
                .then()
                .statusCode(400).extract().as(TrilhaResponseDTO.class);

    }


}
