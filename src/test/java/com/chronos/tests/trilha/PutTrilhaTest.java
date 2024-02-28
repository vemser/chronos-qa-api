package com.chronos.tests.trilha;

import client.TrilhaClient;
import data.factory.TokenFactory;
import data.factory.TrilhaDataFactory;
import model.TrilhaRequestDTO;
import model.TrilhaResponseDTO;
import org.junit.jupiter.api.Test;


public class PutTrilhaTest {


    private final TrilhaClient trilhaClient = new TrilhaClient();

    @Test
    public void testEditarTrilhaComSucesso() {
        trilhaClient.setTOKEN(TokenFactory.getTokenAdmin());

        TrilhaRequestDTO trilhaRequestDTO = TrilhaDataFactory.trilhaComTodosOsCampos();
        TrilhaRequestDTO trilhaRequestDTOEditado = TrilhaDataFactory.trilhaComTodosOsCampos();

        TrilhaResponseDTO trilhaResponseDTO = trilhaClient.cadastrar(trilhaRequestDTO)
                .then()
                .statusCode(200).extract().as(TrilhaResponseDTO.class);

        TrilhaResponseDTO trilhaResponseDTOEditado = trilhaClient.atualizar(trilhaResponseDTO.getIdTrilha(), trilhaRequestDTOEditado)
                .then()
                .statusCode(200).extract().as(TrilhaResponseDTO.class);

        trilhaClient.deletar(trilhaResponseDTO.getIdTrilha()).then().statusCode(204);

    }

    @Test
    public void testEditarTrilhaPreenchendoCampoNomeCom1Caractere() {
        trilhaClient.setTOKEN(TokenFactory.getTokenAdmin());

        TrilhaRequestDTO trilhaRequestDTO = TrilhaDataFactory.trilhaComTodosOsCampos();
        TrilhaRequestDTO trilhaRequestDTOEditado = TrilhaDataFactory.trilhaComCampoNomeCom1Caracter();

        TrilhaResponseDTO trilhaResponseDTO = trilhaClient.cadastrar(trilhaRequestDTO)
                .then()
                .statusCode(200).extract().as(TrilhaResponseDTO.class);

        trilhaClient.atualizar(trilhaResponseDTO.getIdTrilha(), trilhaRequestDTOEditado)
                .then()
                .statusCode(400);

        trilhaClient.deletar(trilhaResponseDTO.getIdTrilha()).then().statusCode(204);

    }

    @Test
    public void testEditarTrilhaPreenchendoCampoNomeCom51Caractere() {
        trilhaClient.setTOKEN(TokenFactory.getTokenAdmin());

        TrilhaRequestDTO trilhaRequestDTO = TrilhaDataFactory.trilhaComTodosOsCampos();
        TrilhaRequestDTO trilhaRequestDTOEditado = TrilhaDataFactory.trilhaComCampoNomeCom51Caracteres();

        TrilhaResponseDTO trilhaResponseDTO = trilhaClient.cadastrar(trilhaRequestDTO)
                .then()
                .statusCode(200).extract().as(TrilhaResponseDTO.class);

        trilhaClient.atualizar(trilhaResponseDTO.getIdTrilha(), trilhaRequestDTOEditado)
                .then()
                .statusCode(400);

        trilhaClient.deletar(trilhaResponseDTO.getIdTrilha()).then().statusCode(204);

    }


    @Test
    public void testEditarTrilhaPreenchendoCampoDescricaoCom256Caracteres() {
        trilhaClient.setTOKEN(TokenFactory.getTokenAdmin());

        TrilhaRequestDTO trilhaRequestDTO = TrilhaDataFactory.trilhaComTodosOsCampos();
        TrilhaRequestDTO trilhaRequestDTOEditado = TrilhaDataFactory.trilhaComCampoDescricaoCom256Caracteres();

        TrilhaResponseDTO trilhaResponseDTO = trilhaClient.cadastrar(trilhaRequestDTO)
                .then()
                .statusCode(200).extract().as(TrilhaResponseDTO.class);

        trilhaClient.atualizar(trilhaResponseDTO.getIdTrilha(), trilhaRequestDTOEditado)
                .then()
                .statusCode(400);

        trilhaClient.deletar(trilhaResponseDTO.getIdTrilha()).then().statusCode(204);

    }

    @Test
    public void testVincularModulo() {
    }
}