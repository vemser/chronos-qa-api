package com.chronos.tests.diaNaoUtil;
import client.DiaNaoUtilClient;
import data.factory.DiaNaoUtilFactory;
import data.factory.TokenFactory;
import io.qameta.allure.*;
import model.diaNaoUtil.DiaNaoUtilRequestDTO;
import model.diaNaoUtil.DiaNaoUtilResposeDTO;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class PostDiaNaoUtilFuncionalTest {
    DiaNaoUtilClient diaNaoUtilClient = new DiaNaoUtilClient();

    @Feature("Dia não util")
    @Story("Cadastrar uma dia não util com sucesso")
    @Description("Testa se a requisição consegue cadastrar uma dia não util deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @Tag("Fumaca")
    public void testCriarUmDiaNaoUtilComSucesso() {
        diaNaoUtilClient.setTOKEN((TokenFactory.getTokenAdmin()));
        DiaNaoUtilRequestDTO diaNaoUtilRequestDTO  = DiaNaoUtilFactory.diaNaoUtilTodosOsCampos();

        DiaNaoUtilResposeDTO diaNaoUtilResposeDTO = diaNaoUtilClient.cadastrar(diaNaoUtilRequestDTO)
                .then()
                .statusCode(200)
                .extract().as(DiaNaoUtilResposeDTO.class);


        diaNaoUtilClient.deletar(diaNaoUtilResposeDTO.getIdDiaNaoUtil());

    }
    @Feature("Dia não util")
    @Story("Cadastrar uma dia não util com sucesso")
    @Description("Testa se a requisição consegue cadastrar uma dia não util deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testTentarCriarUmDiaNaoUtilSomenteComCampoObrigatorios() {
        diaNaoUtilClient.setTOKEN((TokenFactory.getTokenAdmin()));
        DiaNaoUtilRequestDTO diaNaoUtilRequestDTO  = DiaNaoUtilFactory.diaNaoUtilSemDataFinal();
        DiaNaoUtilResposeDTO diaNaoUtilResposeDTO = diaNaoUtilClient.cadastrar(diaNaoUtilRequestDTO)
                .then()
                .statusCode(200)
                .extract().as(DiaNaoUtilResposeDTO.class);

        diaNaoUtilClient.deletar(diaNaoUtilResposeDTO.getIdDiaNaoUtil());

    }
    @Feature("Dia não util")
    @Story("Cadastrar uma dia não util com sucesso")
    @Description("Testa se a requisição consegue cadastrar uma dia não util deve retornar uma mensagem de erro")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @Tag("Fumaca")
    public void testTentarCriarUmDiaUtilComCampoDataInvalido() {
        diaNaoUtilClient.setTOKEN((TokenFactory.getTokenAdmin()));
        DiaNaoUtilRequestDTO diaNaoUtilRequestDTO  = DiaNaoUtilFactory.dataComFormatoInvalido();
        diaNaoUtilClient.cadastrar(diaNaoUtilRequestDTO)
                .then()
                .statusCode(400);
    }
    @Feature("Dia não util")
    @Story("Cadastrar uma dia não util com sucesso")
    @Description("Testa se a requisição consegue cadastrar uma dia não util deve retornar uma mensagem de erro")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @Tag("Fumaca")
    public void testTentarCriarUmDiaUtilJaCriado(){
        diaNaoUtilClient.setTOKEN((TokenFactory.getTokenAdmin()));
        DiaNaoUtilRequestDTO diaNaoUtilRequestDTO  = DiaNaoUtilFactory.diaNaoUtilTodosOsCampos();
        DiaNaoUtilRequestDTO diaNaoUtilRequestDTOJaCadastrado  = DiaNaoUtilFactory.diaNaoUtilTodosOsCampos();
        diaNaoUtilRequestDTOJaCadastrado.setDataInicial(diaNaoUtilRequestDTO.getDataInicial());

        DiaNaoUtilResposeDTO diaNaoUtilResposeDTO = diaNaoUtilClient.cadastrar(diaNaoUtilRequestDTO)
                .then().log().all()
                .statusCode(200).extract().as(DiaNaoUtilResposeDTO.class);

        diaNaoUtilClient.cadastrar(diaNaoUtilRequestDTOJaCadastrado)
                .then()
                .statusCode(409);

    }

    @Feature("Dia não util")
    @Story("Cadastrar uma dia não util com sucesso")
    @Description("Testa se a requisição consegue cadastrar uma dia não util deve retornar uma mensagem de erro")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @Tag("Fumaca")
    public void testTentarCriarUmDiaUtilSomenteComCampoOpcionais(){

        diaNaoUtilClient.setTOKEN((TokenFactory.getTokenAdmin()));
        DiaNaoUtilRequestDTO diaNaoUtilRequestDTO  = DiaNaoUtilFactory.diaNaoUtilCampoOpcional();
       diaNaoUtilClient.cadastrar(diaNaoUtilRequestDTO)
                .then()
                .statusCode(400);


    }
}

