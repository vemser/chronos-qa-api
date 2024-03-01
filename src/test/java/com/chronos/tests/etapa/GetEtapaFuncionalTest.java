package com.chronos.tests.etapa;

import client.EdicaoClient;
import client.EtapaClient;
import data.factory.EdicaoFactory;
import data.factory.EtapaDataFactory;
import io.restassured.response.Response;
import model.EtapaRequestDTO;
import model.EtapaResponseDTO;
import model.edicao.EdicaoRequestDTO;
import model.edicao.EdicaoResponseDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GetEtapaFuncionalTest {
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
                        .statusCode(201)
                        .extract().as(EdicaoResponseDTO.class);

        idEdicaoCadastrado = etapaCadastrada.getIdEtapa();

        EtapaRequestDTO etapaACadastrar = EtapaDataFactory.etapaComTodosOsCampos();
        etapaCadastrada =
                etapaClient.cadastrar(idEdicaoCadastrado, etapaACadastrar)
                        .then()
                        .statusCode(201)
                        .extract().as(EtapaResponseDTO.class);

        idEtapaCadastrada = etapaCadastrada.getIdEtapa();
    }
    @AfterEach
    public void cleanUp() {
        edicaoClient.deletar(idEdicaoCadastrado)
                .then()
                .statusCode(204);
    }

    @Test
    public void testBuscarTodasAsEtapasComSucesso() {
        Response response =
                etapaClient.buscarTudo()
                        .then()
                        .statusCode(200)
                        .extract().response();
        List<EtapaResponseDTO> etapas = response.jsonPath().getList("content", EtapaResponseDTO.class);

        Assertions.assertNotNull(etapas);
        Assertions.assertFalse(etapas.isEmpty());
    }

    @Test
    public void testBuscarTodasAsEtapasSemAutorizacaoSemSucesso() {
                etapaClient.buscarTudoSemAuth()
                        .then()
                        .statusCode(403);
    }

    @Test
    public void testBuscarTodasAsEtapasPorEdicaoComSucesso() {
        Response response =
                etapaClient.buscarTudoPorEdicao(idEdicaoCadastrado)
                        .then()
                        .statusCode(200)
                        .extract().response();
        List<EtapaResponseDTO> etapas = response.jsonPath().getList("elementos", EtapaResponseDTO.class);

        Assertions.assertNotNull(etapas);
        Assertions.assertFalse(etapas.isEmpty());
    }


    @Test
    public void testBuscarTodasAsEtapasPorEdicaoSemAutorizacaoSemSucesso() {
                etapaClient.buscarTudoPorEdicaoSemAuth(idEdicaoCadastrado)
                        .then()
                        .statusCode(403);
    }

    @Test
    public void testBuscarEtapaEspecificaPorIdComSucesso() {
        EtapaResponseDTO etapaBuscada =
        etapaClient.buscarPorID(idEtapaCadastrada)
                .then()
                .statusCode(200)
                .extract().as(EtapaResponseDTO.class);

        Assertions.assertEquals(etapaBuscada.getNome(), etapaCadastrada.getNome());
        Assertions.assertEquals(etapaBuscada.getIdEtapa(), etapaCadastrada.getIdEtapa());
        Assertions.assertEquals(etapaBuscada.getOrdemExecucao(), etapaCadastrada.getOrdemExecucao());
        Assertions.assertEquals(etapaBuscada.getDuracaoDiaUtil(), etapaCadastrada.getDuracaoDiaUtil());

    }

    @Test
    public void testBuscarEtapaEspecificaPorIdSemAutorizacaoSemSucesso() {
                etapaClient.buscarPorIDSemAuth(idEtapaCadastrada)
                        .then()
                        .statusCode(403);
    }
}
