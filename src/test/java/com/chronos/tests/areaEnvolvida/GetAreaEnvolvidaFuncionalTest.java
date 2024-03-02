package com.chronos.tests.areaEnvolvida;

import client.AreaEnvolvidaClient;
import data.factory.TokenFactory;
import io.qameta.allure.*;
import io.restassured.response.Response;
import model.areaEnvolvida.AreaEnvolvidaResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GetAreaEnvolvidaFuncionalTest {

    AreaEnvolvidaClient areaEnvolvidaClient = new AreaEnvolvidaClient();

    @Feature("Area Envolvida")
    @Story("Buscar uma area envolvida com sucesso")
    @Description("Testa se a requisição consegue buscar uma area envolvida deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testBuscarTrilhaComSucesso() {
        areaEnvolvidaClient.setTOKEN(TokenFactory.getTokenAdmin());
        Response response =
                areaEnvolvidaClient.buscarTudo()
                        .then()
                        .statusCode(200) .extract().response();

        List<AreaEnvolvidaResponseDTO> area= response.jsonPath().getList("content", AreaEnvolvidaResponseDTO.class);

        Assertions.assertNotNull(area);
        Assertions.assertFalse(area.isEmpty());

    }
}
