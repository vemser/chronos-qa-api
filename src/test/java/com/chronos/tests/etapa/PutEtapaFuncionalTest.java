package com.chronos.tests.etapa;

import client.EdicaoClient;
import client.EtapaClient;
import data.factory.EdicaoFactory;
import data.factory.EtapaDataFactory;
import io.qameta.allure.*;
import model.EtapaRequestDTO;
import model.EtapaResponseDTO;
import model.edicao.EdicaoRequestDTO;
import model.edicao.EdicaoResponseDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PutEtapaFuncionalTest {
    Integer idEdicaoCadastrado = 0;
    Integer idEtapaCadastrada = 0;
    EtapaClient etapaClient = new EtapaClient();
    EdicaoClient edicaoClient = new EdicaoClient();
    EtapaResponseDTO etapaCadastrada;
    EdicaoResponseDTO edicaoCadastrada;
    @BeforeEach
    public void setUp() {
//        EdicaoRequestDTO edicaoACadastrar = EdicaoFactory.edicaoValida();
//        edicaoCadastrada =
//                edicaoClient.cadastrarEdicao(edicaoACadastrar)
//                        .then()
//                        .statusCode(200)
//                        .log().all()
//                        .extract().as(EdicaoResponseDTO.class);
//
//        idEdicaoCadastrado = edicaoCadastrada.getIdEdicao();
        idEdicaoCadastrado = 229;

        EtapaRequestDTO etapaACadastrar = EtapaDataFactory.etapaComTodosOsCampos();
        etapaCadastrada =
                etapaClient.cadastrar(idEdicaoCadastrado, etapaACadastrar)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().as(EtapaResponseDTO.class);

        idEtapaCadastrada = etapaCadastrada.getIdEtapa();
    }
//    @AfterEach
//    public void cleanUp() {
//        edicaoClient.deletarPorID(idEdicaoCadastrado)
//                .then()
//                .statusCode(204);
//    }

    @Feature("Etapa")
    @Story("Editar uma etapa com sucesso")
    @Description("Testa se a requisição consegue editar uma etapa deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testEditarEtapaComTodosOsCamposValidosComSucesso() {
        EtapaRequestDTO etapaParaAtualizar = EtapaDataFactory.etapaComTodosOsCampos();
                etapaClient.atualizar(idEtapaCadastrada, etapaParaAtualizar)
                        .then()
                        .statusCode(200);
    }

    @Feature("Etapa")
    @Story("Editar uma etapa sem sucesso")
    @Description("Testa se a requisição consegue editar uma etapa deve retornar uma mensagem de erro")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testEditarEtapaComTodosOsCamposValidosSemAuthSemSucesso() {
        EtapaRequestDTO etapaParaAtualizar = EtapaDataFactory.etapaComTodosOsCampos();
                etapaClient.atualizarSemAuth(idEtapaCadastrada, etapaParaAtualizar)
                        .then()
                        .statusCode(403);
    }

    @Feature("Etapa")
    @Story("Editar uma etapa sem sucesso")
    @Description("Testa se a requisição consegue editar uma etapa deve retornar uma mensagem de erro")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testEditarEtapaSemCamposObrigatoriosSemSucesso() {
        EtapaRequestDTO etapaParaAtualizar = EtapaDataFactory.etapaSemCamposObrigatoriosPreenchidos();
        etapaClient.atualizar(idEtapaCadastrada, etapaParaAtualizar)
                .then()
                .statusCode(400);
    }

    @Feature("Etapa")
    @Story("Editar uma etapa sem sucesso")
    @Description("Testa se a requisição consegue editar uma etapa deve retornar uma mensagem de erro")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testEditarEtapaComCampoNomeComCaracterAMaisSemSucesso() {
        EtapaRequestDTO etapaParaAtualizar = EtapaDataFactory.etapaComCampoNomeComCaracterAMais();
        etapaClient.atualizar(idEtapaCadastrada, etapaParaAtualizar)
                .then()
                .statusCode(400);
    }

    @Feature("Etapa")
    @Story("Editar uma etapa sem sucesso")
    @Description("Testa se a requisição consegue editar uma etapa deve retornar uma mensagem de erro")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testEditarEtapaetapaComCampoDuracaoDiaUtilNegativoSemSucesso() {
        EtapaRequestDTO etapaParaAtualizar = EtapaDataFactory.etapaComCampoDuracaoDiaUtilNegativo();
        etapaClient.atualizar(idEtapaCadastrada, etapaParaAtualizar)
                .then()
                .statusCode(400);
    }
}
