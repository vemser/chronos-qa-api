package com.chronos.tests.areaEnvolvida;

import client.AreaEnvolvidaClient;
import data.factory.AreaEnvovidaDataFactory;
import data.factory.TokenFactory;
import model.AreaEnvolvidaRequestDTO;
import model.AreaEnvolvidaResponseDTO;
import org.junit.jupiter.api.Test;

public class PostAreaEnvolvidaFuncionalTest {
AreaEnvolvidaClient areaEnvolvidaClient = new AreaEnvolvidaClient();


    @Test
    public void testCriarUmaAreaEnvolvidaComSucesso() {
        areaEnvolvidaClient.setTOKEN(TokenFactory.getTokenAdmin());

        AreaEnvolvidaRequestDTO areaEnvolvidaRequestDTO = AreaEnvovidaDataFactory.areaEnvolvidaComSucesso();
        AreaEnvolvidaResponseDTO areaEnvolvidaResponseDTO = areaEnvolvidaClient.cadastrar(areaEnvolvidaRequestDTO)
                .then()
                .statusCode(200).extract().as(AreaEnvolvidaResponseDTO.class);

        areaEnvolvidaClient.deletar(areaEnvolvidaResponseDTO.getIdAreaEnvolvida()).then().statusCode(204);

    }


    @Test
    public void testCriarUmaAreaEnvolvidaComCampoNomeVazio() {
        areaEnvolvidaClient.setTOKEN(TokenFactory.getTokenAdmin());

        AreaEnvolvidaRequestDTO areaEnvolvidaRequestDTO = AreaEnvovidaDataFactory.comNomeVazio();
         areaEnvolvidaClient.cadastrar(areaEnvolvidaRequestDTO)
                .then()
                .statusCode(400);

    }

    @Test

    public void testCriarUmaAreaEnvolvidaComCampoComValorNumerico() {
        areaEnvolvidaClient.setTOKEN(TokenFactory.getTokenAdmin());

        AreaEnvolvidaRequestDTO areaEnvolvidaRequestDTO = AreaEnvovidaDataFactory.comCampoNomeNumerico();
        areaEnvolvidaClient.cadastrar(areaEnvolvidaRequestDTO)
                .then()
                .statusCode(200);







    }}
