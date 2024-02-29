package com.chronos.tests.trilha;
import client.TrilhaClient;
import data.factory.TokenFactory;
import io.restassured.response.Response;
import model.ModuloResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


public class GetTrilhaFuncionalTest {

    private final TrilhaClient trilhaClient = new TrilhaClient();


    @Test
    public void testBuscarTrilhaComSucesso() {
        trilhaClient.setTOKEN(TokenFactory.getTokenAdmin());
        Response response =
        trilhaClient.buscarTudo()
                .then()
                .statusCode(200) .extract().response();

        List<ModuloResponseDTO> trilha = response.jsonPath().getList("content", ModuloResponseDTO.class);

        Assertions.assertNotNull(trilha);
        Assertions.assertFalse(trilha.isEmpty());

    }
}
