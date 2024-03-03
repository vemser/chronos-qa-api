package com.chronos.tests.areaEnvolvida;

import client.AreaEnvolvidaClient;
import data.factory.AreaEnvovidaDataFactory;
import data.factory.TokenFactory;
import io.qameta.allure.*;
import model.areaEnvolvida.AreaEnvolvidaRequestDTO;
import model.areaEnvolvida.AreaEnvolvidaResponseDTO;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class PutAreaEnvolvidaFuncionalTest {


    AreaEnvolvidaClient areaEnvolvidaClient = new AreaEnvolvidaClient();


    @Feature("Area Envolvida")
    @Story("Atualizar uma area envolvida com sucesso")
    @Description("Testa se a requisição consegue atualizar uma area envolvida deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @Tag("Fumaca")
    public void testAtualizarAreaEnvolvidaComSucesso() {
        areaEnvolvidaClient.setTOKEN(TokenFactory.getTokenAdmin());

        AreaEnvolvidaRequestDTO areaEnvolvidaRequestDTO = AreaEnvovidaDataFactory.areaEnvolvidaComSucesso();
        AreaEnvolvidaRequestDTO areaEnvolvidaRequestDTOEditavel = AreaEnvovidaDataFactory.areaEnvolvidaComSucesso();

        AreaEnvolvidaResponseDTO areaEnvolvidaResponseDTO = areaEnvolvidaClient.cadastrar(areaEnvolvidaRequestDTO)
                .then()
                .statusCode(200).extract().as(AreaEnvolvidaResponseDTO.class);

        areaEnvolvidaClient.atualizar(areaEnvolvidaResponseDTO.getIdAreaEnvolvida(), areaEnvolvidaRequestDTOEditavel)
                .then()
                .statusCode(202);

        areaEnvolvidaClient.deletar(areaEnvolvidaResponseDTO.getIdAreaEnvolvida()).then().statusCode(204);

    }

    @Feature("Area Envolvida")
    @Story("Atualizar uma area envolvida com nome repetido sem sucesso")
    @Description("Testa se a requisição não consegue atualizar uma area envolvida deve retornar uma mensagem de erro")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @Tag("Fumaca")
    public void testAtualizarAreaEnvolvidaComNomeRepetido() {
        areaEnvolvidaClient.setTOKEN(TokenFactory.getTokenAdmin());

        AreaEnvolvidaRequestDTO areaEnvolvidaRequestDTO = AreaEnvovidaDataFactory.areaEnvolvidaComSucesso();
        AreaEnvolvidaRequestDTO areaEnvolvidaRequestDTO2 = AreaEnvovidaDataFactory.areaEnvolvidaComSucesso();

        AreaEnvolvidaRequestDTO areaEnvolvidaRequestDTORepetido = AreaEnvovidaDataFactory.areaEnvolvidaComSucesso();
        areaEnvolvidaRequestDTORepetido.setNome(areaEnvolvidaRequestDTO.getNome());

        AreaEnvolvidaResponseDTO areaEnvolvidaResponseDTO = areaEnvolvidaClient.cadastrar(areaEnvolvidaRequestDTO)
                .then()
                .statusCode(200).extract().as(AreaEnvolvidaResponseDTO.class);

        AreaEnvolvidaResponseDTO areaEnvolvidaResponseDTO2 = areaEnvolvidaClient.cadastrar(areaEnvolvidaRequestDTO2)
                .then()
                .statusCode(200).extract().as(AreaEnvolvidaResponseDTO.class);

        String message = areaEnvolvidaClient.atualizar(areaEnvolvidaResponseDTO2.getIdAreaEnvolvida(), areaEnvolvidaRequestDTORepetido)
                .then()
                .statusCode(409).extract().jsonPath().getString("message");

        assertEquals("Erro na validação dos seguintes campos: nome.",message);

        areaEnvolvidaClient.deletar(areaEnvolvidaResponseDTO.getIdAreaEnvolvida()).then().statusCode(204);

    }

    @Feature("Area Envolvida")
    @Story("Atualizar uma area envolvida sem sucesso com nome vazio")
    @Description("Testa se a requisição não consegue atualizar uma area envolvida deve retornar uma mensagem de erro")
    @Severity(SeverityLevel.CRITICAL)
@Test
    @Tag("Fumaca")
    public void testTentarAtualizarAreaEnvolvidaComNomeVazio() {
        areaEnvolvidaClient.setTOKEN(TokenFactory.getTokenAdmin());

        AreaEnvolvidaRequestDTO areaEnvolvidaRequestDTO = AreaEnvovidaDataFactory.areaEnvolvidaComSucesso();
        AreaEnvolvidaRequestDTO areaEnvolvidaRequestDTOEditavel = AreaEnvovidaDataFactory.comNomeVazio();

        AreaEnvolvidaResponseDTO areaEnvolvidaResponseDTO = areaEnvolvidaClient.cadastrar(areaEnvolvidaRequestDTO)
                .then()
                .statusCode(200).extract().as(AreaEnvolvidaResponseDTO.class);

        areaEnvolvidaClient.atualizar(areaEnvolvidaResponseDTO.getIdAreaEnvolvida(), areaEnvolvidaRequestDTOEditavel)
                .then()
                .statusCode(400);

        areaEnvolvidaClient.deletar(areaEnvolvidaResponseDTO.getIdAreaEnvolvida()).then().statusCode(204);

    }

    }

