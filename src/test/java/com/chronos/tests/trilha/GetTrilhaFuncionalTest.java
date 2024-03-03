package com.chronos.tests.trilha;
import client.TrilhaClient;
import data.factory.TokenFactory;
import io.qameta.allure.*;
import io.restassured.response.Response;
import model.trilha.TrilhaResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;


public class GetTrilhaFuncionalTest {

    private final TrilhaClient trilhaClient = new TrilhaClient();

    @Feature("Trilha")
    @Story("Buscar uma trilha com sucesso")
    @Description("Testa se a requisição consegue buscar uma trilha deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @Tag("Fumaca")
    public void testBuscarTrilhaComSucesso() {
        trilhaClient.setTOKEN(TokenFactory.getTokenAdmin());
        Response response =
        trilhaClient.buscarTudo()
                .then()
                .statusCode(200) .extract().response();

        List<TrilhaResponseDTO> trilha = response.jsonPath().getList("content", TrilhaResponseDTO.class);

        Assertions.assertNotNull(trilha);
        Assertions.assertFalse(trilha.isEmpty());

    }
}
