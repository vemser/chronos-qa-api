package com.chronos.tests.calendarioGeral;


import client.CalendarioClient;
import client.EdicaoClient;
import data.factory.CalendarioGeralDataFactory;
import data.factory.EdicaoFactory;
import io.qameta.allure.*;
import io.restassured.response.Response;
import model.calendario.DiaCalendarioEdicaoResponseDTO;
import model.calendario.DiaCalendarioGeralResponseDTO;
import model.edicao.EdicaoRequestDTO;
import model.edicao.EdicaoResponseDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;


public class GetCalendarioGeralFuncionalTest {

    EdicaoClient edicaoClient = new EdicaoClient();

    EdicaoResponseDTO edicaoCadastrada;

    Integer idEdicaoCadastrado = 0;

    @BeforeEach
    public void setUp() {
        EdicaoRequestDTO edicaoACadastrar = EdicaoFactory.edicaoValida();
        edicaoCadastrada =
                edicaoClient.cadastrarEdicao(edicaoACadastrar)
                        .then()
                        .statusCode(200)
                        .extract().as(EdicaoResponseDTO.class);

        idEdicaoCadastrado = edicaoCadastrada.getIdEdicao();
    }

    @AfterEach
    public void cleanUp() {
        edicaoClient.deletarPorID(idEdicaoCadastrado)
                .then()
                .statusCode(204);
    }

    CalendarioClient calendarioClient = new CalendarioClient();

    @Feature("Calendario Geral")
    @Story("Gerar e buscar o calendario geral")
    @Description("Testa se a requisição consegue gerar e buscar o calendario geral")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @Tag("Fumaca")
    public void testBuscarEGerarCalendarioGeral() {
        Response response =
                calendarioClient.buscarEGerarCalendarioGeral()
                        .then()
                        .statusCode(200).extract().response();

        List<DiaCalendarioGeralResponseDTO> calendarioGeralList = response.jsonPath().getList("", DiaCalendarioGeralResponseDTO.class);

        Assertions.assertNotNull(calendarioGeralList);
        Assertions.assertFalse(calendarioGeralList.isEmpty());

    }


    @Feature("Calendario Geral")
    @Story("Gerar e buscar o calendario geral sem token de autorização")
    @Description("Testa se a requisição não consegue gerar e buscar o calendario geral sem auth")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @Tag("Fumaca")
    public void testBuscarEGerarCalendarioGeralSemAuth() {
                calendarioClient.buscarEGerarCalendarioGeralSemAuth()
                        .then()
                        .statusCode(403);

    }
    @Feature("Calendario Geral")
    @Story("Gerar e buscar o calendario geral por mês com sucesso")
    @Description("Testa se a requisição consegue gerar e buscar o calendario geral filtrado por mês")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @Tag("Fumaca")
    public void testBuscarEGerarCalendarioGeralPorMês() {
        Response response =
                calendarioClient.buscarEGerarCalendarioGeralPorMes(CalendarioGeralDataFactory.gerarMesCalendario())
                        .then()
                        .statusCode(200).extract().response();

        List<DiaCalendarioGeralResponseDTO> calendarioGeralList = response.jsonPath().getList("", DiaCalendarioGeralResponseDTO.class);

        Assertions.assertNotNull(calendarioGeralList);

    }

    @Feature("Calendario Geral")
    @Story("Gerar e buscar o calendario geral por mês sem token de autorização")
    @Description("Testa se a requisição não consegue gerar e buscar o calendario geral filtrado por mês sem auth")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @Tag("Fumaca")
    public void testBuscarEGerarCalendarioGeralPorMêsSemAuth() {
        calendarioClient.buscarEGerarCalendarioGeralPorMesSemAuth(CalendarioGeralDataFactory.gerarMesCalendario())
                .then()
                .statusCode(403);

    }

    @Feature("Calendario Geral")
    @Story("Gerar e buscar o calendario geral com excel")
    @Description("Testa se a requisição consegue gerar e buscar o calendario geral excel")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testBuscarEGerarCalendarioGeralExcel() {
        calendarioClient.buscarEGerarCalendarioGeralExcel(idEdicaoCadastrado)
                .then()
                .statusCode(200);

    }

    @Feature("Calendario Geral")
    @Story("Gerar e buscar o calendario geral com excel sem auth sem sucesso")
    @Description("Testa se a requisição não consegue gerar e buscar o calendario geral excel sem auth")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testBuscarEGerarCalendarioGeralExcelSemAuth() {
        calendarioClient.buscarEGerarCalendarioGeralExcelSemAuth(idEdicaoCadastrado)
                .then()
                .statusCode(403);

    }

    @Feature("Calendario Geral")
    @Story("Gerar e buscar o calendario geral por edicao com sucesso")
    @Description("Testa se a requisição consegue gerar e buscar o calendario geral por edicao com sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testBuscarEGerarCalendarioGeralPorEdicaoComSucesso() {
        Response response =
                calendarioClient.buscarEGerarCalendarioGeralDaEdicao(idEdicaoCadastrado)
                        .then()
                        .statusCode(200).extract().response();

        List<DiaCalendarioEdicaoResponseDTO> calendarioGeralList = response.jsonPath().getList("", DiaCalendarioEdicaoResponseDTO.class);

        Assertions.assertNotNull(calendarioGeralList);

    }

    @Feature("Calendario Geral")
    @Story("Gerar e buscar o calendario geral por edicao sem auth sem sucesso")
    @Description("Testa se a requisição consegue gerar e buscar o calendario geral por edicao sem sucesso sem auth")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testBuscarEGerarCalendarioGeralPorEdicaoSemAuthSemSucesso() {
                calendarioClient.buscarEGerarCalendarioGeralDaEdicaoSemAuth(idEdicaoCadastrado)
                        .then()
                        .statusCode(403);

    }
}
