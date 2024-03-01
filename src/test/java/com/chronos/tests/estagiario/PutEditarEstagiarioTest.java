package com.chronos.tests.estagiario;

import client.EstagiarioClient;
import io.qameta.allure.*;
import io.restassured.http.ContentType;
import model.EstagiarioRequestDTO;
import org.junit.Test;
import specs.AuthSpec;
import specs.InicialSpecs;

import static io.restassured.RestAssured.given;

public class PutEditarEstagiarioTest {
    private final EstagiarioClient estagiarioClient = new EstagiarioClient();


    @Feature("Area Envolvida")
    @Story("Atualizar uma area envolvida com sucesso")
    @Description("Testa se a requisição consegue atualizar uma area envolvida deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testAtualizarEstagiario() {

        EstagiarioRequestDTO estagiarioRequestDTO = new EstagiarioRequestDTO();
        estagiarioRequestDTO.setCpf("58531748003");
        estagiarioRequestDTO.setNome("Novo Nome");
        estagiarioRequestDTO.setTelefone("62000000000");
        estagiarioRequestDTO.setDataNascimento("1990-01-26");
        estagiarioRequestDTO.setEmailPessoal("novoteste01@example.com");
        estagiarioRequestDTO.setEmailCorporativo("novoteste01@dbccompany.com.br");
        estagiarioRequestDTO.setCidade("Porto Alegre");
        estagiarioRequestDTO.setEstado("RS");
        estagiarioRequestDTO.setCurso("Análise e Desenvolvimento de Sistemas");
        estagiarioRequestDTO.setInstituicaoEnsino("Pontifícia Universidade Example");
        estagiarioRequestDTO.setGithub("https://github.c/example");
        estagiarioRequestDTO.setLinkedin("https://linkedin.c/example");
        estagiarioRequestDTO.setStatus("DIS");
        estagiarioRequestDTO.setObservacao("Desligado devido ao comportamento");
        estagiarioRequestDTO.setIdEdicao(1);
        estagiarioRequestDTO.setIdTrilha(2);

        int estagiarioID = 14;

        given()
                .spec(AuthSpec.setup())
                .contentType(ContentType.JSON)
                .body(estagiarioRequestDTO)
                .when()
                .put("/estagiario/" + estagiarioID)
                .then()
                .statusCode(200);
    }

    @Feature("Area Envolvida")
    @Story("Atualizar uma area envolvida com sucesso")
    @Description("Testa se a requisição consegue atualizar uma area envolvida deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testAtualizarEstagiarioComCpfNulo() {

        EstagiarioRequestDTO estagiarioRequestDTO = new EstagiarioRequestDTO();
        estagiarioRequestDTO.setCpf("");
        estagiarioRequestDTO.setNome("Novo Nome");
        estagiarioRequestDTO.setTelefone("62000000000");
        estagiarioRequestDTO.setDataNascimento("1990-01-26");
        estagiarioRequestDTO.setEmailPessoal("novoteste01@example.com");
        estagiarioRequestDTO.setEmailCorporativo("novoteste01@dbccompany.com.br");
        estagiarioRequestDTO.setCidade("Porto Alegre");
        estagiarioRequestDTO.setEstado("RS");
        estagiarioRequestDTO.setCurso("Análise e Desenvolvimento de Sistemas");
        estagiarioRequestDTO.setInstituicaoEnsino("Pontifícia Universidade Example");
        estagiarioRequestDTO.setGithub("https://github.c/example");
        estagiarioRequestDTO.setLinkedin("https://linkedin.c/example");
        estagiarioRequestDTO.setStatus("DIS");
        estagiarioRequestDTO.setObservacao("Desligado devido ao comportamento");
        estagiarioRequestDTO.setIdEdicao(1);
        estagiarioRequestDTO.setIdTrilha(2);

        int estagiarioID = 14;


        given()
                .spec(AuthSpec.setup())
                .contentType(ContentType.JSON)
                .body(estagiarioRequestDTO)
                .when()
                .put("/estagiario/" + estagiarioID)
                .then()
                .statusCode(400);
    }

    @Feature("Area Envolvida")
    @Story("Atualizar uma area envolvida com sucesso")
    @Description("Testa se a requisição consegue atualizar uma area envolvida deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testAtualizarEstagiarioSemTokenValido() {

        EstagiarioRequestDTO estagiarioRequestDTO = new EstagiarioRequestDTO();
        estagiarioRequestDTO.setCpf("88283303090");
        estagiarioRequestDTO.setNome("Novo Nome");
        estagiarioRequestDTO.setTelefone("62000000000");
        estagiarioRequestDTO.setDataNascimento("1990-01-26");
        estagiarioRequestDTO.setEmailPessoal("novoteste02@example.com");
        estagiarioRequestDTO.setEmailCorporativo("novoteste02@dbccompany.com.br");
        estagiarioRequestDTO.setCidade("Porto Alegre");
        estagiarioRequestDTO.setEstado("RS");
        estagiarioRequestDTO.setCurso("Análise e Desenvolvimento de Sistemas");
        estagiarioRequestDTO.setInstituicaoEnsino("Pontifícia Universidade Example");
        estagiarioRequestDTO.setGithub("https://github.c/example");
        estagiarioRequestDTO.setLinkedin("https://linkedin.c/example");
        estagiarioRequestDTO.setStatus("DIS");
        estagiarioRequestDTO.setObservacao("Desligado devido ao comportamento");
        estagiarioRequestDTO.setIdEdicao(1);
        estagiarioRequestDTO.setIdTrilha(2);

        int estagiarioID = 14;


        given()
                .spec(InicialSpecs.setup())
                .contentType(ContentType.JSON)
                .body(estagiarioRequestDTO)
                .when()
                .put("/estagiario/" + estagiarioID)
                .then()
                .statusCode(403);
    }

}
