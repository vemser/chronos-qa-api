package com.chronos.tests.estagiario;
import client.EdicaoClient;
import client.EstagiarioClient;
import client.TrilhaClient;
import io.qameta.allure.*;
import io.restassured.http.ContentType;
import model.estagiario.EstagiarioResponseDTO;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class ListarEstagiariosTest {
    private final EstagiarioClient estagiarioClient = new EstagiarioClient();
    private final EdicaoClient edicaoClient = new EdicaoClient();
    private final TrilhaClient trilhaClient = new TrilhaClient();

    @Feature("Estagiario")
    @Story("Lista um Estagiario com sucesso")
    @Description("Testa se a requisição consegue retornar uma paginação de usuários e deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @Tag("Fumaca")
    public void testDeveListarEstagiariosComSucesso() {

        EstagiarioResponseDTO responseDTO = estagiarioClient.criarMassaDeDados()
                        .then()
                        .extract().as(EstagiarioResponseDTO.class);

        estagiarioClient.buscarTudo()
                        .then()
                            .contentType(ContentType.JSON)
                            .statusCode(HttpStatus.SC_OK)
                            .body("content.size()", greaterThan(0));

        trilhaClient.deletar(responseDTO.getTrilha().getIdTrilha())
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);

        edicaoClient.deletarPorID(responseDTO.getEdicao().getIdEdicao())
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);


    }

    @Feature("Estagiario")
    @Story("Buscar um Estagiario com base na sua edição com sucesso")
    @Description("Testa se a requisição consegue buscar um estagiários com base na edição escolhida")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testDeveRetornarEstagiariosComBaseNaEdicaoComSucesso() {

        EstagiarioResponseDTO responseDTO = estagiarioClient.criarMassaDeDados()
                .then()
                .extract().as(EstagiarioResponseDTO.class);

        estagiarioClient.buscarPorEdicao(responseDTO.getEdicao().getIdEdicao())
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_OK)
                .body("totalElements", equalTo(1))
                .body("content.size()", greaterThan(0));

        trilhaClient.deletar(responseDTO.getTrilha().getIdTrilha());

        edicaoClient.deletarPorID(responseDTO.getEdicao().getIdEdicao())
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Feature("Estagiario")
    @Story("Atualizar um Estagiario com sucesso")
    @Description("Testa se a requisição consegue atualizar uma area envolvida deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @Tag("Fumaca")
    public void testBuscarEstagiarioPorID() {

        EstagiarioResponseDTO responseDTO = estagiarioClient.criarMassaDeDados()
                .then()
                .extract().as(EstagiarioResponseDTO.class);

        estagiarioClient.buscarPorId(responseDTO.getIdEstagiario())
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_OK)
                .body("content[0].idEstagiario", equalTo(responseDTO.getIdEstagiario()));

        trilhaClient.deletar(responseDTO.getTrilha().getIdTrilha());

        edicaoClient.deletarPorID(responseDTO.getEdicao().getIdEdicao())
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }


    @Feature("Estagiario")
    @Story("Atualizar um Estagiario com sucesso")
    @Description("Testa se a requisição consegue atualizar uma area envolvida deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testBuscarEstagiarioPorIDComTokenInvalido() {

        estagiarioClient.buscarSemToken()
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_FORBIDDEN)
                .body("status", equalTo(HttpStatus.SC_FORBIDDEN))
                .body("error", equalTo("Forbidden"));
    }

}
