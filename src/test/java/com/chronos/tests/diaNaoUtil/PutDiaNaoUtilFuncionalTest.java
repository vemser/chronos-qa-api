package com.chronos.tests.diaNaoUtil;

import client.DiaNaoUtilClient;
import data.factory.DiaNaoUtilFactory;
import model.diaNaoUtil.DiaNaoUtilRequestDTO;
import model.diaNaoUtil.DiaNaoUtilResposeDTO;
import org.junit.jupiter.api.Test;

public class PutDiaNaoUtilFuncionalTest {

    DiaNaoUtilClient diaNaoUtilClient = new DiaNaoUtilClient();


    @Test
    public void testTentarEdiarDiaNaoUtil() {

        DiaNaoUtilRequestDTO diaNaoUtilRequestDTO  = DiaNaoUtilFactory.diaNaoUtilTodosOsCampos();
        DiaNaoUtilRequestDTO diaNaoUtilRequestDTOEditado  = DiaNaoUtilFactory.diaNaoUtilTodosOsCampos();

        DiaNaoUtilResposeDTO diaNaoUtilResposeDTO = diaNaoUtilClient.cadastrar(diaNaoUtilRequestDTO)
                .then()
                .statusCode(200).extract().as(DiaNaoUtilResposeDTO.class);

        DiaNaoUtilResposeDTO diaNaoUtilResposeDTOEditado = diaNaoUtilClient.atualizar(diaNaoUtilResposeDTO.get_id(), diaNaoUtilRequestDTOEditado).then()
                .statusCode(200).extract().as(DiaNaoUtilResposeDTO.class);

        diaNaoUtilClient.deletar(diaNaoUtilResposeDTO.get_id()).then().statusCode(204);

    }


    @Test
    public void testTentarEditarUmDiaNaoUtilApenasComCamposObrigatorios(){
        DiaNaoUtilRequestDTO diaNaoUtilRequestDTO  = DiaNaoUtilFactory.diaNaoUtilSemDataFinal();
        DiaNaoUtilRequestDTO diaNaoUtilRequestDTOEditado  = DiaNaoUtilFactory.diaNaoUtilSemDataFinal();

        DiaNaoUtilResposeDTO diaNaoUtilResposeDTO = diaNaoUtilClient.cadastrar(diaNaoUtilRequestDTO)
                .then()
                .statusCode(200).extract().as(DiaNaoUtilResposeDTO.class);

        DiaNaoUtilResposeDTO diaNaoUtilResposeDTOEditado = diaNaoUtilClient.atualizar(diaNaoUtilResposeDTO.get_id(), diaNaoUtilRequestDTOEditado).then()
                .statusCode(200).extract().as(DiaNaoUtilResposeDTO.class);

        diaNaoUtilClient.deletar(diaNaoUtilResposeDTO.get_id()).then().statusCode(204);


    }
}
