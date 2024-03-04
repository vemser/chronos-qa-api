package com.chronos.tests.curriculo;

import client.CurriculoClient;
import client.EdicaoClient;
import client.EstagiarioClient;
import data.factory.CurriculoDataFactory;
import io.qameta.allure.*;
import io.restassured.http.ContentType;
import model.curriculo.CurriculoResponseDTO;
import model.estagiario.EstagiarioResponseDTO;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertAll;

public class BuscarCurriculoTest {
    EdicaoClient edicaoClient = new EdicaoClient();
    CurriculoClient curriculoClient = new CurriculoClient();
    EstagiarioClient estagiarioClient = new EstagiarioClient();


    @Feature("Curriculo")
    @Story("Buscar um curriculo associado a uma estagiário com sucesso")
    @Description("Testa se a requisição consegue retornar um curriculo associado a uma estagiário deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @Tag("Fumaca")
    public void testDeveBuscarCurriculoPeloIDComSucesso() {
        EstagiarioResponseDTO estagiario = estagiarioClient.criarMassaDeDados().then().extract().as(EstagiarioResponseDTO.class);

        CurriculoResponseDTO curriculoResponseDTO = curriculoClient.cadastrarCurriculo(estagiario.getIdEstagiario(), CurriculoDataFactory.curriculo1())
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_CREATED)
                .extract().as(CurriculoResponseDTO.class);


        CurriculoResponseDTO curriculoBusca = curriculoClient.buscarCurriculoPeloIDCurriculo(curriculoResponseDTO.getIdCurriculo())
                        .then()
                        .contentType(ContentType.JSON)
                        .statusCode(HttpStatus.SC_OK)
                        .body("arquivo", notNullValue())
                        .extract().as(CurriculoResponseDTO.class);

        curriculoClient.deletarCurriculo(curriculoResponseDTO.getIdCurriculo()).then().statusCode(HttpStatus.SC_NO_CONTENT);
        estagiarioClient.deletar(estagiario.getIdEstagiario());
        edicaoClient.deletarPorID(estagiario.getEdicao().getIdEdicao());
    }

    @Feature("Curriculo")
    @Story("Buscar um curriculo associado a uma estagiário com sucesso")
    @Description("Testa se a requisição consegue retornar um curriculo associado a uma estagiário deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @Tag("Fumaca")
    public void testDeveBuscarCurriculoPeloEstagiarioComSucesso() {
        EstagiarioResponseDTO estagiario = estagiarioClient.criarMassaDeDados().then().extract().as(EstagiarioResponseDTO.class);

        CurriculoResponseDTO curriculoResponseDTO = curriculoClient.cadastrarCurriculo(estagiario.getIdEstagiario(), CurriculoDataFactory.curriculo1())
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_CREATED)
                .extract().as(CurriculoResponseDTO.class);


        CurriculoResponseDTO curriculoBusca = curriculoClient.buscarCurriculoPeloIDEstagiario(curriculoResponseDTO.getIdEstagiario())
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_OK)
                .body("arquivo", notNullValue())
                .extract().as(CurriculoResponseDTO.class);


        assertAll("curriculoBusca",
                () -> Assertions.assertEquals(curriculoResponseDTO.getArquivo(), curriculoBusca.getArquivo()),
                () -> Assertions.assertEquals(curriculoResponseDTO.getIdEstagiario(), curriculoBusca.getIdEstagiario()),
                () -> Assertions.assertEquals(curriculoResponseDTO.getIdCurriculo(), curriculoBusca.getIdCurriculo())
        );

        curriculoClient.deletarCurriculo(curriculoResponseDTO.getIdCurriculo()).then().statusCode(HttpStatus.SC_NO_CONTENT).log().all();
        estagiarioClient.deletar(estagiario.getIdEstagiario());
        edicaoClient.deletarPorID(estagiario.getEdicao().getIdEdicao());
    }

    @Feature("Curriculo")
    @Story("Retornar um curriculo pelo seu id inválido sem sucesso")
    @Description("Testa se a requisição consegue criar um curriculo associado a uma estagiário deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @Tag("Fumaca")
    public void testNaoDeveRetornarUmCurriculoPeloIdEstagiarioPoisIDInvalido() {
        curriculoClient.buscarCurriculoPeloIDEstagiario(-1)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("status", equalTo(HttpStatus.SC_BAD_REQUEST))
                .body("errors[0]", equalTo("Estagiário não encontrado"));
    }
}
