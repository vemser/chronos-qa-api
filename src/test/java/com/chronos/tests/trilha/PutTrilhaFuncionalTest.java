package com.chronos.tests.trilha;

import client.ModuloClient;
import client.TrilhaClient;
import data.factory.ModuloDataFactory;
import data.factory.TokenFactory;
import data.factory.TrilhaDataFactory;
import model.ModuloRequestDTO;
import model.ModuloResponseDTO;
import model.trilha.TrilhaRequestDTO;
import model.trilha.TrilhaResponseDTO;
import org.junit.jupiter.api.Test;


public class PutTrilhaFuncionalTest {


    private final TrilhaClient trilhaClient = new TrilhaClient();
    private final ModuloClient moduloClient = new ModuloClient();

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
    public void testEditarTrilhaPreenchendoCampoDescricaoCom255Caracteres() {
        trilhaClient.setTOKEN(TokenFactory.getTokenAdmin());

        TrilhaRequestDTO trilhaRequestDTO = TrilhaDataFactory.trilhaComTodosOsCampos();
        TrilhaRequestDTO trilhaRequestDTOEditado = TrilhaDataFactory.trilhaComCampoDescricaoCom255();

        TrilhaResponseDTO trilhaResponseDTO = trilhaClient.cadastrar(trilhaRequestDTO)
                .then()
                .statusCode(200).extract().as(TrilhaResponseDTO.class);

        trilhaClient.atualizar(trilhaResponseDTO.getIdTrilha(), trilhaRequestDTOEditado)
                .then()
                .statusCode(200);

        trilhaClient.deletar(trilhaResponseDTO.getIdTrilha()).then().statusCode(204);

    }

    @Test
    public void testEditarTrilhaPreenchendoCampoDescricaoVazio() {
        trilhaClient.setTOKEN(TokenFactory.getTokenAdmin());

        TrilhaRequestDTO trilhaRequestDTO = TrilhaDataFactory.trilhaComTodosOsCampos();
        TrilhaRequestDTO trilhaRequestDTOEditado = TrilhaDataFactory.trilhaComCampoDescricaoVazio();

        TrilhaResponseDTO trilhaResponseDTO = trilhaClient.cadastrar(trilhaRequestDTO)
                .then()
                .statusCode(200).extract().as(TrilhaResponseDTO.class);

        trilhaClient.atualizar(trilhaResponseDTO.getIdTrilha(), trilhaRequestDTOEditado)
                .then()
                .statusCode(200);

        trilhaClient.deletar(trilhaResponseDTO.getIdTrilha()).then().statusCode(204);

    }



    @Test
    public void testVincularModuloATrilha() {
        trilhaClient.setTOKEN(TokenFactory.getTokenAdmin());


        TrilhaRequestDTO trilhaRequestDTO = TrilhaDataFactory.trilhaComTodosOsCampos();
        ModuloRequestDTO moduloRequestDTO = ModuloDataFactory.moduloComTodosOsCampos();

        TrilhaResponseDTO trilhaResponseDTO = trilhaClient.cadastrar(trilhaRequestDTO)
                .then().log().all()
                .statusCode(200).extract().as(TrilhaResponseDTO.class);

        ModuloResponseDTO moduloResponseDTO = moduloClient.cadastrar(moduloRequestDTO).then().statusCode(201).extract().as(ModuloResponseDTO.class);

        TrilhaResponseDTO trilhaResponseDTOVinculado = trilhaClient.vincularModulo(trilhaResponseDTO.getIdTrilha(), moduloResponseDTO.getIdModulo())
                .then().log().all()
                .statusCode(200).extract().as(TrilhaResponseDTO.class);

        trilhaClient.deletar(trilhaResponseDTO.getIdTrilha()).then().statusCode(204);
    }

    @Test
    public void testTentarDesvingularModuloTrilha(){
        trilhaClient.setTOKEN(TokenFactory.getTokenAdmin());

        TrilhaRequestDTO trilhaRequestDTO = TrilhaDataFactory.trilhaComTodosOsCampos();
        ModuloRequestDTO moduloRequestDTO = ModuloDataFactory.moduloComTodosOsCampos();

        TrilhaResponseDTO trilhaResponseDTO = trilhaClient.cadastrar(trilhaRequestDTO)
                .then().log().all()
                .statusCode(200).extract().as(TrilhaResponseDTO.class);

        ModuloResponseDTO moduloResponseDTO = moduloClient.cadastrar(moduloRequestDTO).then().log().all().statusCode(201).extract().as(ModuloResponseDTO.class);

        TrilhaResponseDTO trilhaResponseDTOVinculado = trilhaClient.vincularModulo(trilhaResponseDTO.getIdTrilha(), moduloResponseDTO.getIdModulo())
                .then().log().all()
                .statusCode(200).extract().as(TrilhaResponseDTO.class);

        TrilhaResponseDTO trilhaResponseDTODesvincular = trilhaClient.desvincularModulo(trilhaResponseDTO.getIdTrilha(), moduloResponseDTO.getIdModulo())
                .then().log().all()
                .statusCode(200).extract().as(TrilhaResponseDTO.class);

        trilhaClient.deletar(trilhaResponseDTO.getIdTrilha()).then().statusCode(204);





    }
}