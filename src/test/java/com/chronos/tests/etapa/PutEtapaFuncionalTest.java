package com.chronos.tests.etapa;

import client.EdicaoClient;
import client.EtapaClient;
import data.factory.EdicaoFactory;
import data.factory.EtapaDataFactory;
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
        EdicaoRequestDTO edicaoACadastrar = EdicaoFactory.edicaoValida();
        edicaoCadastrada =
                edicaoClient.cadastrarEdicao(edicaoACadastrar)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().as(EdicaoResponseDTO.class);

        idEdicaoCadastrado = edicaoCadastrada.getIdEdicao();

        EtapaRequestDTO etapaACadastrar = EtapaDataFactory.etapaComTodosOsCampos();
        etapaCadastrada =
                etapaClient.cadastrar(idEdicaoCadastrado, etapaACadastrar)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().as(EtapaResponseDTO.class);

        idEtapaCadastrada = etapaCadastrada.getIdEtapa();
    }
    @AfterEach
    public void cleanUp() {
        edicaoClient.deletarPorID(idEdicaoCadastrado)
                .then()
                .statusCode(204);
    }

    @Test
    public void testEditarEtapaComTodosOsCamposValidosComSucesso() {
        EtapaRequestDTO etapaParaAtualizar = EtapaDataFactory.etapaComTodosOsCampos();
                etapaClient.atualizar(idEtapaCadastrada, etapaParaAtualizar)
                        .then()
                        .statusCode(200);
    }

    @Test
    public void testEditarEtapaComTodosOsCamposValidosSemAuthSemSucesso() {
        EtapaRequestDTO etapaParaAtualizar = EtapaDataFactory.etapaComTodosOsCampos();
                etapaClient.atualizarSemAuth(idEtapaCadastrada, etapaParaAtualizar)
                        .then()
                        .statusCode(403);
    }

    @Test
    public void testEditarEtapaSemCamposObrigatoriosSemSucesso() {
        EtapaRequestDTO etapaParaAtualizar = EtapaDataFactory.etapaSemCamposObrigatoriosPreenchidos();
        etapaClient.atualizar(idEtapaCadastrada, etapaParaAtualizar)
                .then()
                .statusCode(400);
    }

    @Test
    public void testEditarEtapaComCampoNomeComCaracterAMaisSemSucesso() {
        EtapaRequestDTO etapaParaAtualizar = EtapaDataFactory.etapaComCampoNomeComCaracterAMais();
        etapaClient.atualizar(idEtapaCadastrada, etapaParaAtualizar)
                .then()
                .statusCode(400);
    }

    @Test
    public void testEditarEtapaetapaComCampoDuracaoDiaUtilNegativoSemSucesso() {
        EtapaRequestDTO etapaParaAtualizar = EtapaDataFactory.etapaComCampoDuracaoDiaUtilNegativo();
        etapaClient.atualizar(idEtapaCadastrada, etapaParaAtualizar)
                .then()
                .statusCode(400);
    }
}
