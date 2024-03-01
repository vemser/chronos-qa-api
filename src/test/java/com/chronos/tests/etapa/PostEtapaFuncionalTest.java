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

public class PostEtapaFuncionalTest {
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
                        .log().all()
                        .statusCode(200)
                        .extract().as(EdicaoResponseDTO.class);

        idEdicaoCadastrado = edicaoCadastrada.getIdEdicao();
    }
    @AfterEach
    public void cleanUp() {
        edicaoClient.deletar(idEdicaoCadastrado)
                .then()
                .statusCode(204);
    }

    @Test
    public void testCriarEtapaComTodosOsCamposValidosComSucesso() {
        EtapaRequestDTO etapaACadastrar = EtapaDataFactory.etapaComTodosOsCampos();
        etapaCadastrada =
                etapaClient.cadastrar(idEdicaoCadastrado, etapaACadastrar)
                        .then()
                        .statusCode(200)
                        .extract().as(EtapaResponseDTO.class);

        idEtapaCadastrada = etapaCadastrada.getIdEtapa();
    }

    @Test
    public void testCriarEtapaComTodosOsCamposValidosSemAuthSemSucesso() {
        EtapaRequestDTO etapaACadastrar = EtapaDataFactory.etapaComTodosOsCampos();
                etapaClient.cadastrarSemAuth(idEdicaoCadastrado, etapaACadastrar)
                        .then()
                        .statusCode(403);
    }

    @Test
    public void testCriarEtapaSemCamposObrigatoriosSemSucesso() {
        EtapaRequestDTO etapaACadastrar = EtapaDataFactory.etapaSemCamposObrigatoriosPreenchidos();
                etapaClient.cadastrar(idEdicaoCadastrado, etapaACadastrar)
                        .then()
                        .statusCode(400);
    }

    @Test
    public void testCriarEtapaComCampoNomeComCaracterAMaisSemSucesso() {
        EtapaRequestDTO etapaACadastrar = EtapaDataFactory.etapaComCampoNomeComCaracterAMais();
        etapaClient.cadastrar(idEdicaoCadastrado, etapaACadastrar)
                .then()
                .statusCode(400);
    }

    @Test
    public void testCriarEtapaetapaComCampoDuracaoDiaUtilNegativoSemSucesso() {
        EtapaRequestDTO etapaACadastrar = EtapaDataFactory.etapaComCampoDuracaoDiaUtilNegativo();
        etapaClient.cadastrar(idEdicaoCadastrado, etapaACadastrar)
                .then()
                .statusCode(400);
    }
}
