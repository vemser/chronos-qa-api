package com.chronos.tests.trilha;

import client.TrilhaClient;
import data.factory.TokenFactory;
import data.factory.TrilhaDataFactory;
import io.restassured.http.ContentType;
import model.TrilhaRequestDTO;
import model.TrilhaResponseDTO;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

public class PostTrilhaTest {
    private final TrilhaClient trilhaClient = new TrilhaClient();

//BUG CAMPO DESCRICAO//
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
        trilhaClient.cadastrar(trilhaRequestDTO)
                .then()
                .statusCode(400);

    }

    @Test
    public void testCriarUmaTrilhaPreenchendoApenasOsCamposObrigatorios(){
        trilhaClient.setTOKEN(TokenFactory.getTokenAdmin());

        TrilhaRequestDTO trilhaRequestDTO = TrilhaDataFactory.trilhaComCamposObrigatoriosPreenchidos();
        TrilhaResponseDTO trilhaResponseDTO = trilhaClient.cadastrar(trilhaRequestDTO)
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

//BUG//
    @Test
    public void testTentarCriarTrilhaComNomeRepetido(){

        trilhaClient.setTOKEN(TokenFactory.getTokenAdmin());

        TrilhaRequestDTO trilhaRequestDTO = TrilhaDataFactory.trilhaComTodosOsCampos();
        TrilhaRequestDTO trilhaRequestDTONomeRepetido = TrilhaDataFactory.trilhaComTodosOsCampos();
        trilhaRequestDTONomeRepetido.setNome(trilhaRequestDTO.getNome());

        TrilhaResponseDTO trilhaResponseDTO = trilhaClient.cadastrar(trilhaRequestDTO)
                .then()
                .statusCode(200).extract().as(TrilhaResponseDTO.class);

        trilhaClient.cadastrar(trilhaRequestDTONomeRepetido)
                .then()
                .statusCode(409);


        trilhaClient.deletar(trilhaResponseDTO.getIdTrilha()).then().statusCode(204);
    }
}
