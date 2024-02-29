package com.chronos.tests.diaNaoUtil;
import client.DiaNaoUtilClient;
import data.factory.DiaNaoUtilFactory;
import data.factory.TokenFactory;
import model.diaNaoUtil.DiaNaoUtilRequestDTO;
import model.diaNaoUtil.DiaNaoUtilResposeDTO;
import org.junit.jupiter.api.Test;

public class PostDiaNaoUtilFuncionalTest {
    DiaNaoUtilClient diaNaoUtilClient = new DiaNaoUtilClient();


    @Test
    public void testCriarUmDiaNaoUtilComSucesso() {
        diaNaoUtilClient.setTOKEN((TokenFactory.getTokenAdmin()));
        DiaNaoUtilRequestDTO diaNaoUtilRequestDTO  = DiaNaoUtilFactory.diaNaoUtilTodosOsCampos();

        diaNaoUtilClient.cadastrar(diaNaoUtilRequestDTO)
                .then()
                .statusCode(200);



    }

    @Test
    public void testTentarCriarUmDiaNaoUtilSomenteComCampoObrigatorios() {
        diaNaoUtilClient.setTOKEN((TokenFactory.getTokenAdmin()));
        DiaNaoUtilRequestDTO diaNaoUtilRequestDTO  = DiaNaoUtilFactory.diaNaoUtilSemDataFinal();
        DiaNaoUtilResposeDTO diaNaoUtilResposeDTO = diaNaoUtilClient.cadastrar(diaNaoUtilRequestDTO)
                .then()
                .statusCode(200).extract().as(DiaNaoUtilResposeDTO.class);


    }

    @Test
    public void testTentarCriarUmDiaUtilComCampoDataInvalido() {
        diaNaoUtilClient.setTOKEN((TokenFactory.getTokenAdmin()));
        DiaNaoUtilRequestDTO diaNaoUtilRequestDTO  = DiaNaoUtilFactory.dataComFormatoInvalido();
        diaNaoUtilClient.cadastrar(diaNaoUtilRequestDTO)
                .then()
                .statusCode(400);


    }

    @Test
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
                .statusCode(400);

    }


    @Test
    public void testTentarCriarUmDiaUtilSomenteComCampoOpcionais(){

        diaNaoUtilClient.setTOKEN((TokenFactory.getTokenAdmin()));
        DiaNaoUtilRequestDTO diaNaoUtilRequestDTO  = DiaNaoUtilFactory.diaNaoUtilCampoOpcional();
       diaNaoUtilClient.cadastrar(diaNaoUtilRequestDTO)
                .then()
                .statusCode(400);


    }
}

