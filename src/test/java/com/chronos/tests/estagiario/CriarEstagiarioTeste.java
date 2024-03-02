package com.chronos.tests.estagiario;

import client.EstagiarioClient;
import io.qameta.allure.*;
import io.restassured.http.ContentType;
import model.EstagiarioRequestDTO;
import net.datafaker.Faker;
import org.junit.Test;
import specs.AuthSpec;
import specs.InicialSpecs;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class CriarEstagiarioTeste {

    private static Faker faker = new Faker();
    private final EstagiarioClient estagiarioClient = new EstagiarioClient();


    @Feature("Estagiario")
    @Story("Atualizar um Estagiario com sucesso")
    @Description("Testa se a requisição consegue atualizar uma area envolvida deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testCriarNovoEstagiario() {


        //String cpf = faker.number().digits(11);
        String nome = faker.name().fullName();
        //String emailPessoal = faker.internet().emailAddress();
        //String emailCorporativo = faker.internet().emailAddress();


        EstagiarioRequestDTO estagiarioRequestDTO = new EstagiarioRequestDTO();
        estagiarioRequestDTO.setCpf("20943027098");
        estagiarioRequestDTO.setNome(nome);
        estagiarioRequestDTO.setTelefone("62000000000");
        estagiarioRequestDTO.setDataNascimento("1990-01-26");
        estagiarioRequestDTO.setEmailPessoal("exampleteste03@example.com");
        estagiarioRequestDTO.setEmailCorporativo("exampleteste03@dbccompany.com.br");
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


        given()
                .spec(AuthSpec.setup())
                .contentType(ContentType.JSON)
                .body(estagiarioRequestDTO)
                .when()
                .post("/estagiario")
                .then()
                .statusCode(200);
    }

    @Feature("Estagiario")
    @Story("Atualizar um Estagiario com sucesso")
    @Description("Testa se a requisição consegue atualizar uma area envolvida deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testCriarEstagiarioComCPFInvalido() {
        EstagiarioRequestDTO estagiarioRequestDTO = new EstagiarioRequestDTO();
        estagiarioRequestDTO.setCpf("12345678900");

        given()
                .spec(AuthSpec.setup())
                .contentType(ContentType.JSON)
                .body(estagiarioRequestDTO)
                .when()
                .post("/estagiario")
                .then()
                .statusCode(400);
    }

    @Feature("Estagiario")
    @Story("Atualizar um Estagiario com sucesso")
    @Description("Testa se a requisição consegue atualizar uma area envolvida deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testCriarEstagiarioComCPFNulo() {
        EstagiarioRequestDTO estagiarioRequestDTO = new EstagiarioRequestDTO();
        estagiarioRequestDTO.setCpf("");

        given()
                .spec(AuthSpec.setup())
                .contentType(ContentType.JSON)
                .body(estagiarioRequestDTO)
                .when()
                .post("/estagiario")
                .then()
                .statusCode(400);
    }

    @Feature("Estagiario")
    @Story("Atualizar um Estagiario com sucesso")
    @Description("Testa se a requisição consegue atualizar uma area envolvida deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testCriarEstagiarioComCPFSemToken() {
        EstagiarioRequestDTO estagiarioRequestDTO = new EstagiarioRequestDTO();
        estagiarioRequestDTO.setCpf("12345678900");

        given()
                .spec(InicialSpecs.setup())
                .contentType(ContentType.JSON)
                .body(estagiarioRequestDTO)
                .when()
                .post("/estagiario")
                .then()
                .statusCode(403);
    }

}
