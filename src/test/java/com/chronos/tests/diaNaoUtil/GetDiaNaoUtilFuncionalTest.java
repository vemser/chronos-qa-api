package com.chronos.tests.diaNaoUtil;

import client.DiaNaoUtilClient;
import data.factory.TokenFactory;
import io.restassured.response.Response;
import model.ModuloResponseDTO;
import model.diaNaoUtil.DiaNaoUtilResposeDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GetDiaNaoUtilFuncionalTest {
DiaNaoUtilClient diaNaoUtilClient = new DiaNaoUtilClient();


    @Test
    public void testBuscarDiaNaoUtilComSucesso() {
        diaNaoUtilClient.setTOKEN((TokenFactory.getTokenAdmin()));
        Response response =
                diaNaoUtilClient.buscarTudo()
                        .then()
                        .statusCode(200).log().all() .extract().response();

        List<DiaNaoUtilResposeDTO> diaNaoUtil = response.jsonPath().getList("content", DiaNaoUtilResposeDTO.class);

        Assertions.assertNotNull(diaNaoUtil);
        Assertions.assertFalse(diaNaoUtil.isEmpty());

    }
}
