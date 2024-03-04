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

public class CriarCurriculoTest {

    EdicaoClient edicaoClient = new EdicaoClient();
    CurriculoClient curriculoClient = new CurriculoClient();
    EstagiarioClient estagiarioClient = new EstagiarioClient();

    @Feature("Curriculo")
    @Story("Criar um curriculo associado a uma estagiário com sucesso")
    @Description("Testa se a requisição consegue criar um curriculo associado a uma estagiário deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @Tag("Fumaca")
    public void testDeveCadastrarCurriculoComSucesso() {
        EstagiarioResponseDTO estagiario = estagiarioClient.criarMassaDeDados().then().extract().as(EstagiarioResponseDTO.class);

        Integer idCurriculo = curriculoClient.cadastrarCurriculo(estagiario.getIdEstagiario(), CurriculoDataFactory.curriculo1())
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_CREATED)
                .body("idCurriculo", notNullValue())
                .body("idEstagiario", equalTo(estagiario.getIdEstagiario()))
                .log().all()
                .extract().as(CurriculoResponseDTO.class).getIdCurriculo();

        curriculoClient.deletarCurriculo(idCurriculo).then().statusCode(HttpStatus.SC_NO_CONTENT);
        estagiarioClient.deletar(estagiario.getIdEstagiario());
        edicaoClient.deletarPorID(estagiario.getEdicao().getIdEdicao());
    }

    @Feature("Curriculo")
    @Story("Criar um curriculo associado a uma estagiário com sucesso")
    @Description("Testa se a requisição consegue criar um curriculo associado a uma estagiário deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @Tag("Fumaca")
    public void testDeveSobrescreverUmCurriculoComSucesso() {
        EstagiarioResponseDTO estagiario = estagiarioClient.criarMassaDeDados().then().extract().as(EstagiarioResponseDTO.class);

        CurriculoResponseDTO curriculoResponseDTO = curriculoClient.cadastrarCurriculo(estagiario.getIdEstagiario(), CurriculoDataFactory.curriculo1())
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_CREATED)
                .extract().as(CurriculoResponseDTO.class);

        CurriculoResponseDTO curriculoAlterDTO = curriculoClient.cadastrarCurriculo(estagiario.getIdEstagiario(), CurriculoDataFactory.curriculo2())
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_CREATED)
                .extract().as(CurriculoResponseDTO.class);

        assertAll("curriculoAlterDTO",
                () -> Assertions.assertEquals(curriculoAlterDTO.getIdCurriculo(), curriculoResponseDTO.getIdCurriculo()),
                () -> Assertions.assertEquals(curriculoAlterDTO.getIdEstagiario(), curriculoResponseDTO.getIdEstagiario()),
                () -> Assertions.assertNotEquals(curriculoAlterDTO.getArquivo(), curriculoResponseDTO.getArquivo())
        );

        curriculoClient.deletarCurriculo(curriculoResponseDTO.getIdCurriculo()).then().statusCode(HttpStatus.SC_NO_CONTENT);
        estagiarioClient.deletar(estagiario.getIdEstagiario());
        edicaoClient.deletarPorID(estagiario.getEdicao().getIdEdicao());
    }


}
