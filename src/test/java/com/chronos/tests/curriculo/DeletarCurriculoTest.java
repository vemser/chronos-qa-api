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
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class DeletarCurriculoTest {
    EdicaoClient edicaoClient = new EdicaoClient();
    CurriculoClient curriculoClient = new CurriculoClient();
    EstagiarioClient estagiarioClient = new EstagiarioClient();


    @Feature("Curriculo")
    @Story("Deletar um curriculo pelo seu id com sucesso")
    @Description("Testa se a requisição consegue criar um curriculo associado a uma estagiário deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @Tag("Fumaca")
    public void testDeveDeletarUmCurriculoPeloIdComSucesso() {
        EstagiarioResponseDTO estagiario = estagiarioClient.criarMassaDeDados().then().extract().as(EstagiarioResponseDTO.class);

        Integer idCurriculo = curriculoClient.cadastrarCurriculo(estagiario.getIdEstagiario(), CurriculoDataFactory.curriculo1())
                .then()
                .extract().as(CurriculoResponseDTO.class).getIdCurriculo();

        curriculoClient.deletarCurriculo(idCurriculo)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);

        estagiarioClient.deletar(estagiario.getIdEstagiario());
        edicaoClient.deletarPorID(estagiario.getEdicao().getIdEdicao());
    }

    @Feature("Curriculo")
    @Story("Deletar um curriculo pelo seu id do estagiário com sucesso")
    @Description("Testa se a requisição consegue criar um curriculo associado a uma estagiário deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @Tag("Fumaca")
    public void testDeveDeletarUmCurriculoPeloIdEstagiarioComSucesso() {
        EstagiarioResponseDTO estagiario = estagiarioClient.criarMassaDeDados().then().extract().as(EstagiarioResponseDTO.class);

        Integer idCurriculo = curriculoClient.cadastrarCurriculo(estagiario.getIdEstagiario(), CurriculoDataFactory.curriculo1())
                .then()
                .extract().as(CurriculoResponseDTO.class).getIdCurriculo();

        curriculoClient.deletarCurriculoPeloIDEstagiario(estagiario.getIdEstagiario())
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);

        estagiarioClient.deletar(estagiario.getIdEstagiario());
        edicaoClient.deletarPorID(estagiario.getEdicao().getIdEdicao());
    }

    @Feature("Curriculo")
    @Story("Deletar um curriculo pelo seu id inválido sem sucesso")
    @Description("Testa se a requisição consegue criar um curriculo associado a uma estagiário deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @Tag("Fumaca")
    public void testNaoDeveDeletarUmCurriculoPeloIdEstagiarioPoisIDInvalido() {

        curriculoClient.deletarCurriculoPeloIDEstagiario(-1)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("status", equalTo(HttpStatus.SC_BAD_REQUEST))
                .body("errors[0]", equalTo("Estagiário não encontrado"));
    }

    @Feature("Curriculo")
    @Story("Deletar um curriculo pelo seu id inválido sem sucesso")
    @Description("Testa se a requisição consegue criar um curriculo associado a uma estagiário deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @Tag("Fumaca")
    public void testNaoDeveDeletarUmCurriculoPeloIdCurriculoPoisIDInvalido() {
        curriculoClient.deletarCurriculo(-1)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("status", equalTo(HttpStatus.SC_BAD_REQUEST))
                .body("errors[0]", equalTo("Currículo não encontrado"));
    }
}
