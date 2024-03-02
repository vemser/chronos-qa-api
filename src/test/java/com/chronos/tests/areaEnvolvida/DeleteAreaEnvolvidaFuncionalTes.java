package com.chronos.tests.areaEnvolvida;

import client.AreaEnvolvidaClient;
import data.factory.AreaEnvovidaDataFactory;
import data.factory.TokenFactory;
import io.qameta.allure.*;
import model.areaEnvolvida.AreaEnvolvidaRequestDTO;
import model.areaEnvolvida.AreaEnvolvidaResponseDTO;
import org.junit.jupiter.api.Test;

public class DeleteAreaEnvolvidaFuncionalTes {

    private final AreaEnvolvidaClient areaEnvolvidaClient = new AreaEnvolvidaClient();

    @Feature("Area Envolvida")
    @Story("Deletar uma area envolvida com sucesso")
    @Description("Testa se a requisição consegue deletar uma area envolvida deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testDeletarAreaEnvolvidaComSucesso() {
        areaEnvolvidaClient.setTOKEN(TokenFactory.getTokenAdmin());

        AreaEnvolvidaRequestDTO areaEnvolvidaRequestDTO = AreaEnvovidaDataFactory.areaEnvolvidaComSucesso();
        AreaEnvolvidaResponseDTO areaEnvolvidaResponseDTO = areaEnvolvidaClient.cadastrar(areaEnvolvidaRequestDTO)
                .then()
                .statusCode(200).extract().as(AreaEnvolvidaResponseDTO.class);

        areaEnvolvidaClient.deletar(areaEnvolvidaResponseDTO.getIdAreaEnvolvida()).then().statusCode(204);

    }
    @Feature("Area Envolvida")
    @Story("Deletar uma area envolvida sem sucesso, id invalido")
    @Description("Testa se a requisição não consegue deletar uma area envolvida deve retornar uma mensagem de erro")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testTentarDeletarAreaEnvolvidaComIdInvalido() {
        int idInvalido = AreaEnvovidaDataFactory.idInvalido();
        areaEnvolvidaClient.setTOKEN(TokenFactory.getTokenAdmin());
        areaEnvolvidaClient.deletar(idInvalido).then().statusCode(400);

    }

}
