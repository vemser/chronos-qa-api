package com.chronos.tests.areaEnvolvida;

import client.AreaEnvolvidaClient;
import data.factory.TokenFactory;
import io.restassured.response.Response;
import model.AreaEnvolvidaResponseDTO;
import model.trilha.TrilhaResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GetAreaEnvolvidaFuncionalTest {

    AreaEnvolvidaClient areaEnvolvidaClient = new AreaEnvolvidaClient();

    @Test
    public void testBuscarTrilhaComSucesso() {
        areaEnvolvidaClient.setTOKEN(TokenFactory.getTokenAdmin());
        Response response =
                areaEnvolvidaClient.buscarTudo()
                        .then()
                        .statusCode(200) .extract().response();

        List<AreaEnvolvidaResponseDTO> areaEnvolvidaResponseDTOS = response.jsonPath().getList("content", AreaEnvolvidaResponseDTO.class);

        Assertions.assertNotNull(areaEnvolvidaResponseDTOS);
        Assertions.assertFalse(areaEnvolvidaResponseDTOS.isEmpty());

    }
}
