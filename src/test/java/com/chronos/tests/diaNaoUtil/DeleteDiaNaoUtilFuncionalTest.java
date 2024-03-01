package com.chronos.tests.diaNaoUtil;

import client.DiaNaoUtilClient;
import data.factory.DiaNaoUtilFactory;
import data.factory.TokenFactory;
import data.factory.TrilhaDataFactory;
import model.diaNaoUtil.DiaNaoUtilRequestDTO;
import model.diaNaoUtil.DiaNaoUtilResposeDTO;
import model.trilha.TrilhaRequestDTO;
import model.trilha.TrilhaResponseDTO;
import org.junit.jupiter.api.Test;

public class DeleteDiaNaoUtilFuncionalTest {

DiaNaoUtilClient diaNaoUtilClient = new DiaNaoUtilClient();
    @Test
    public void testDeletarDiaNaoUtilComSucesso() {
        diaNaoUtilClient.setTOKEN(TokenFactory.getTokenAdmin());

        DiaNaoUtilRequestDTO diaNaoUtilRequestDTO = DiaNaoUtilFactory.diaNaoUtilTodosOsCampos();
        DiaNaoUtilResposeDTO diaNaoUtilResposeDTO = diaNaoUtilClient.cadastrar(diaNaoUtilRequestDTO)
                .then()
                .statusCode(200).extract().as(DiaNaoUtilResposeDTO.class);

        diaNaoUtilClient.deletar(diaNaoUtilResposeDTO.getIdDiaNaoUtil()).then().log().all().statusCode(204);

    }

    @Test
    public void testTentarDeletarDiaNaoUtilComIdInvalido() {
        diaNaoUtilClient.setTOKEN(TokenFactory.getTokenAdmin());

       int idInvalido = DiaNaoUtilFactory.idInvalido();
       diaNaoUtilClient.deletar(idInvalido).then().statusCode(400);

    }

}
