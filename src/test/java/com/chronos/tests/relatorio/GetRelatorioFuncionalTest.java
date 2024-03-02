package com.chronos.tests.relatorio;

import client.EdicaoClient;
import client.RelatorioClient;
import data.factory.EdicaoFactory;
import data.factory.TokenFactory;

import model.edicao.EdicaoRequestDTO;
import model.edicao.EdicaoResponseDTO;
import model.relatorio.*;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;



public class GetRelatorioFuncionalTest {
    RelatorioClient relatorioClient = new RelatorioClient();
    EdicaoClient edicaoClient = new EdicaoClient();

    @Test
    public void testBuscarQuantidadeDeEstagiarios() {
        EdicaoRequestDTO edicaoResponseDTO = EdicaoFactory.edicaoValida();

        EdicaoResponseDTO edicaoResponseDTO1 = edicaoClient.cadastrarEdicao(edicaoResponseDTO)
                .then()
                .statusCode(200)
                .extract()
                .as(EdicaoResponseDTO.class);

        relatorioClient.setTOKEN(TokenFactory.getTokenAdmin());
        QuantidadeEstagiariosResponseDTO response =
                relatorioClient.buscarEstagiario(edicaoResponseDTO1.getIdEdicao())
                        .then()
                        .log().all()
                        .statusCode(200).extract().as(QuantidadeEstagiariosResponseDTO.class);

        Assertions.assertNotNull(response);
        Assertions.assertFalse(response.getQuantidade() < 0);

    }



    @Test
    public void testBuscarTrilhaPorDesligamento() {
        EdicaoRequestDTO edicaoResponseDTO = EdicaoFactory.edicaoValida();

        EdicaoResponseDTO edicaoResponseDTO1 = edicaoClient.cadastrarEdicao(edicaoResponseDTO)
                .then()
                .statusCode(200)
                .extract()
                .as(EdicaoResponseDTO.class);


        relatorioClient.setTOKEN(TokenFactory.getTokenAdmin());
        QuantidadeDesligamentosResponseDTO response =
                relatorioClient.buscarDesligamento(edicaoResponseDTO1.getIdEdicao())
                        .then()
                        .statusCode(200).extract().as(QuantidadeDesligamentosResponseDTO.class);

        Assertions.assertNotNull(response);
        Assertions.assertFalse(response.getQuantidade() < 0);

    }

    @Test
    public void testBuscarTrilhaPorRegiao() {
        EdicaoRequestDTO edicaoResponseDTO = EdicaoFactory.edicaoValida();

        EdicaoResponseDTO edicaoResponseDTO1 = edicaoClient.cadastrarEdicao(edicaoResponseDTO)
                .then()
                .statusCode(200)
                .extract()
                .as(EdicaoResponseDTO.class);



        relatorioClient.setTOKEN(TokenFactory.getTokenAdmin());
        RegioesResponseDTO response =
                relatorioClient.buscarRegiao(edicaoResponseDTO1.getIdEdicao())
                        .then()
                        .statusCode(200).extract().as(RegioesResponseDTO.class);

        Assertions.assertNotNull(response);
        Assertions.assertFalse(response.getCentroOeste() < 0);
        Assertions.assertFalse(response.getNordeste() < 0);
        Assertions.assertFalse(response.getNorte() < 0);
        Assertions.assertFalse(response.getSul() < 0);
        Assertions.assertFalse(response.getSudeste() < 0);


    }

    @Test
    public void testBuscarTrilhaGeral() {

        EdicaoRequestDTO edicaoResponseDTO = EdicaoFactory.edicaoValida();

        EdicaoResponseDTO edicaoResponseDTO1 = edicaoClient.cadastrarEdicao(edicaoResponseDTO)
                .then()
                .statusCode(200)
                .extract()
                .as(EdicaoResponseDTO.class);


        relatorioClient.setTOKEN(TokenFactory.getTokenAdmin());
        RelatorioResponseDTO response =
                relatorioClient.buscarGeral(edicaoResponseDTO1.getIdEdicao())
                        .then().log().all()
                        .statusCode(200).extract().as(RelatorioResponseDTO.class);

        Assertions.assertNotNull(response.getTrilhas());
        Assertions.assertNotNull(response.getDesligamentos());
        Assertions.assertNotNull(response.getEstagiarios());
        Assertions.assertNotNull(response.getRegioes());
    }


}
