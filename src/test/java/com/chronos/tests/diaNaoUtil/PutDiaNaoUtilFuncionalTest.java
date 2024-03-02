package com.chronos.tests.diaNaoUtil;

import client.DiaNaoUtilClient;
import data.factory.DiaNaoUtilFactory;
import data.factory.TokenFactory;
import io.qameta.allure.*;
import model.diaNaoUtil.DiaNaoUtilRequestDTO;
import model.diaNaoUtil.DiaNaoUtilResposeDTO;
import org.junit.jupiter.api.Test;

public class PutDiaNaoUtilFuncionalTest {

    DiaNaoUtilClient diaNaoUtilClient = new DiaNaoUtilClient();

    @Feature("Dia não util")
    @Story("Atualizar uma dia não util com sucesso")
    @Description("Testa se a requisição consegue atualizar uma dia não util deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
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

    @Feature("Dia não util")
    @Story("Atualizar uma dia não util com sucesso")
    @Description("Testa se a requisição consegue atualizar uma dia não util deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
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
    @Feature("Dia não util")
    @Story("Atualizar uma dia não util com sucesso")
    @Description("Testa se a requisição consegue atualizar uma dia não util deve retornar uma mensagem de erro")
    @Severity(SeverityLevel.CRITICAL)
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
