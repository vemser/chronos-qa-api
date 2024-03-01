package com.chronos.tests.diaNaoUtil;

import client.DiaNaoUtilClient;
import data.factory.DiaNaoUtilFactory;
import data.factory.TokenFactory;
import model.diaNaoUtil.DiaNaoUtilRequestDTO;
import model.diaNaoUtil.DiaNaoUtilResposeDTO;
import org.junit.jupiter.api.Test;

public class PutDiaNaoUtilFuncionalTest {

    DiaNaoUtilClient diaNaoUtilClient = new DiaNaoUtilClient();


    @Test
    public void testTentarEdiarDiaNaoUtil() {
        diaNaoUtilClient.setTOKEN((TokenFactory.getTokenAdmin()));

        DiaNaoUtilRequestDTO diaNaoUtilRequestDTO  = DiaNaoUtilFactory.diaNaoUtilTodosOsCampos();
        DiaNaoUtilRequestDTO diaNaoUtilRequestDTOEditado  = DiaNaoUtilFactory.diaNaoUtilTodosOsCampos();

        DiaNaoUtilResposeDTO diaNaoUtilResposeDTO = diaNaoUtilClient.cadastrar(diaNaoUtilRequestDTO)
                .then()
                .statusCode(200).extract().as(DiaNaoUtilResposeDTO.class);

        DiaNaoUtilResposeDTO diaNaoUtilResposeDTOEditado = diaNaoUtilClient.atualizar(diaNaoUtilResposeDTO.getIdDiaNaoUtil(), diaNaoUtilRequestDTOEditado).then()
                .statusCode(200).extract().as(DiaNaoUtilResposeDTO.class);

        diaNaoUtilClient.deletar(diaNaoUtilResposeDTO.getIdDiaNaoUtil()).then().statusCode(204);

    }


    @Test
    public void testTentarEditarUmDiaNaoUtilApenasComCamposObrigatorios(){
        diaNaoUtilClient.setTOKEN((TokenFactory.getTokenAdmin()));

        DiaNaoUtilRequestDTO diaNaoUtilRequestDTO  = DiaNaoUtilFactory.diaNaoUtilSemDataFinal();
        DiaNaoUtilRequestDTO diaNaoUtilRequestDTOEditado  = DiaNaoUtilFactory.diaNaoUtilSemDataFinal();

        DiaNaoUtilResposeDTO diaNaoUtilResposeDTO = diaNaoUtilClient.cadastrar(diaNaoUtilRequestDTO)
                .then()
                .statusCode(200).extract().as(DiaNaoUtilResposeDTO.class);

        DiaNaoUtilResposeDTO diaNaoUtilResposeDTOEditado = diaNaoUtilClient.atualizar(diaNaoUtilResposeDTO.getIdDiaNaoUtil(), diaNaoUtilRequestDTOEditado).then()
                .statusCode(200).extract().as(DiaNaoUtilResposeDTO.class);

        diaNaoUtilClient.deletar(diaNaoUtilResposeDTO.getIdDiaNaoUtil()).then().statusCode(204);


    }
@Test
    public void testTentarEditarUmDiaUtilJaCriado(){
        diaNaoUtilClient.setTOKEN((TokenFactory.getTokenAdmin()));

        DiaNaoUtilRequestDTO diaNaoUtilRequestDTO  = DiaNaoUtilFactory.diaNaoUtilTodosOsCampos();
        DiaNaoUtilRequestDTO diaNaoUtilRequestDTOEditado  = DiaNaoUtilFactory.diaNaoUtilTodosOsCampos();
        diaNaoUtilRequestDTOEditado.setDataInicial(diaNaoUtilRequestDTO.getDataInicial());

        DiaNaoUtilResposeDTO diaNaoUtilResposeDTO = diaNaoUtilClient.cadastrar(diaNaoUtilRequestDTO)
                .then().log().all()
                .statusCode(200).extract().as(DiaNaoUtilResposeDTO.class);

        diaNaoUtilClient.cadastrar(diaNaoUtilRequestDTOEditado)
                .then()
                .statusCode(409);

    }

}
