package com.chronos.tests.areaEnvolvida;

import client.AreaEnvolvidaClient;
import data.factory.AreaEnvovidaDataFactory;
import data.factory.TokenFactory;
import io.qameta.allure.*;
import model.areaEnvolvida.AreaEnvolvidaRequestDTO;
import model.areaEnvolvida.AreaEnvolvidaResponseDTO;
import org.junit.jupiter.api.Test;

public class PostAreaEnvolvidaFuncionalTest {
AreaEnvolvidaClient areaEnvolvidaClient = new AreaEnvolvidaClient();


    @Feature("Area Envolvida")
    @Story("Criar uma area envolvida com sucesso")
    @Description("Testa se a requisição consegue criar uma area envolvida deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testCriarUmaAreaEnvolvidaComSucesso() {
        areaEnvolvidaClient.setTOKEN(TokenFactory.getTokenAdmin());

        AreaEnvolvidaRequestDTO areaEnvolvidaRequestDTO = AreaEnvovidaDataFactory.areaEnvolvidaComSucesso();
        AreaEnvolvidaResponseDTO areaEnvolvidaResponseDTO = areaEnvolvidaClient.cadastrar(areaEnvolvidaRequestDTO)
                .then()
                .statusCode(200).extract().as(AreaEnvolvidaResponseDTO.class);

        areaEnvolvidaClient.deletar(areaEnvolvidaResponseDTO.getIdAreaEnvolvida()).then().statusCode(204);

    }


    @Feature("Area Envolvida")
    @Story("Criar uma area envolvida com sucesso com campo nome vazio")
    @Description("Testa se a requisição não consegue criar uma area envolvida deve retornar uma mensagem de erro")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testCriarUmaAreaEnvolvidaComCampoNomeVazio() {
        areaEnvolvidaClient.setTOKEN(TokenFactory.getTokenAdmin());

        AreaEnvolvidaRequestDTO areaEnvolvidaRequestDTO = AreaEnvovidaDataFactory.comNomeVazio();
         areaEnvolvidaClient.cadastrar(areaEnvolvidaRequestDTO)
                .then()
                .statusCode(400);

    }

    @Feature("Area Envolvida")
    @Story("Criar uma area envolvida com sucesso com campo valor numérico")
    @Description("Testa se a requisição consegue criar uma area envolvida deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testCriarUmaAreaEnvolvidaComCampoComValorNumerico() {
        areaEnvolvidaClient.setTOKEN(TokenFactory.getTokenAdmin());

        AreaEnvolvidaRequestDTO areaEnvolvidaRequestDTO = AreaEnvovidaDataFactory.comCampoNomeNumerico();
        areaEnvolvidaClient.cadastrar(areaEnvolvidaRequestDTO)
                .then()
                .statusCode(200);







    }}
