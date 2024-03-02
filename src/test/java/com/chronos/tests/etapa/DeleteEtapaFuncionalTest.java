package com.chronos.tests.etapa;

import client.EdicaoClient;
import client.EtapaClient;
import data.factory.EdicaoFactory;
import data.factory.EtapaDataFactory;
import model.etapa.EtapaRequestDTO;
import model.etapa.EtapaResponseDTO;
import model.edicao.EdicaoRequestDTO;
import model.edicao.EdicaoResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeleteEtapaFuncionalTest {

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
                        .extract().as(EdicaoResponseDTO.class);

        idEdicaoCadastrado = edicaoCadastrada.getIdEdicao();

        EtapaRequestDTO etapaACadastrar = EtapaDataFactory.etapaComTodosOsCampos();
        etapaCadastrada =
                etapaClient.cadastrar(idEdicaoCadastrado, etapaACadastrar)
                        .then()
                        .statusCode(200)
                        .extract().as(EtapaResponseDTO.class);

        idEtapaCadastrada = etapaCadastrada.getIdEtapa();
    }

    @Test
    public void testDeletarEtapaEspecificoPorIdComSucesso() {
        etapaClient.deletar(idEtapaCadastrada)
                .then()
                .statusCode(204);
    }

    @Test
    public void testDeletarModuloEspecificoPorIdSemAutorizacaoSemSucesso() {
        etapaClient.deletarSemAuth(idEtapaCadastrada)
                .then()
                .statusCode(403);
    }
}
