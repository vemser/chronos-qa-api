package com.chronos.tests.estagiario;

import client.EdicaoClient;
import client.EstagiarioClient;
import client.TrilhaClient;
import data.factory.EdicaoFactory;
import data.factory.EstagiarioFactory;
import data.factory.TrilhaDataFactory;
import io.restassured.http.ContentType;
import model.edicao.EdicaoResponseDTO;
import model.estagiario.EstagiarioRequestDTO;
import model.estagiario.EstagiarioResponseDTO;
import model.trilha.TrilhaResponseDTO;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;

public class EditarEstagiarioTest {
    private final EstagiarioClient estagiarioClient = new EstagiarioClient();
    private final EdicaoClient edicaoClient = new EdicaoClient();
    private final TrilhaClient trilhaClient = new TrilhaClient();
    @Test
    @Tag("Fumaca")
    public void testDeveAtualizarEstagiarioComSucesso() {
        Integer idEdicao = edicaoClient.cadastrarEdicao(EdicaoFactory.edicaoValida())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(EdicaoResponseDTO.class).getIdEdicao();

        Integer idTrilha = trilhaClient.cadastrar(TrilhaDataFactory.trilhaComTodosOsCampos())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(TrilhaResponseDTO.class).getIdTrilha();

        edicaoClient.vincularTrilha(idEdicao, idTrilha)
                .then()
                .statusCode(HttpStatus.SC_OK);

        EstagiarioRequestDTO bodyRequest = EstagiarioFactory.estagiarioValido(idTrilha, idEdicao);

        Integer createdEstagiarioID = estagiarioClient.cadastrar(bodyRequest)
                .then()
                .log().body()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_CREATED)
                .extract().as(EstagiarioResponseDTO.class).getIdEstagiario();

        EstagiarioRequestDTO bodyRequestAlter = EstagiarioFactory.estagiarioValido(idTrilha, idEdicao);

        EstagiarioResponseDTO responseDTO = estagiarioClient.atualizar(createdEstagiarioID, bodyRequestAlter)
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_OK)
                .extract().as(EstagiarioResponseDTO.class);

        assertAll("responseDTO",
                () -> Assertions.assertEquals(createdEstagiarioID, responseDTO.getIdEstagiario()),
                () -> Assertions.assertEquals(bodyRequestAlter.getCpf(), responseDTO.getCpf()),
                () -> Assertions.assertEquals(bodyRequestAlter.getNome(), responseDTO.getNome()),
                () -> Assertions.assertEquals(bodyRequestAlter.getTelefone(), responseDTO.getTelefone()),
                () -> Assertions.assertEquals(bodyRequestAlter.getCurso(), responseDTO.getCurso()),
                () -> Assertions.assertEquals(bodyRequestAlter.getCidade(), responseDTO.getCidade()),
                () -> Assertions.assertEquals(bodyRequestAlter.getEstado(), responseDTO.getEstado()),
                () -> Assertions.assertEquals(bodyRequestAlter.getEmailPessoal(), responseDTO.getEmailPessoal()),
                () -> Assertions.assertEquals(bodyRequestAlter.getEmailCorporativo(), responseDTO.getEmailCorporativo()),
                () -> Assertions.assertEquals(bodyRequestAlter.getInstituicaoEnsino(), responseDTO.getInstituicaoEnsino()),
                () -> Assertions.assertEquals(bodyRequestAlter.getGithub(), responseDTO.getGithub()),
                () -> Assertions.assertEquals(bodyRequestAlter.getLinkedin(), responseDTO.getLinkedin()),
                () -> Assertions.assertEquals(bodyRequestAlter.getStatus(), responseDTO.getStatus()),
                () -> Assertions.assertEquals(bodyRequestAlter.getObservacao(), responseDTO.getObservacao()),
                () -> Assertions.assertEquals(bodyRequestAlter.getIdTrilha(), responseDTO.getTrilha().getIdTrilha()),
                () -> Assertions.assertEquals(bodyRequestAlter.getIdEdicao(), responseDTO.getEdicao().getIdEdicao())
        );

        trilhaClient.deletar(responseDTO.getTrilha().getIdTrilha())
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);


    }

    @ParameterizedTest
    @MethodSource("data.provider.EstagiarioProvider#providerCadastroComCamposObrigatoriosNaoPreenchidos")
    @DisplayName("Teste: Não deve atualizar estagiário pois body request não atende a critério de aceite")
    public void testNaoDeveAlterarEstagiarioPoisCritériosNaoAceitos(EstagiarioRequestDTO bodyRequestAlter, String message) {
        Integer idEdicao = edicaoClient.cadastrarEdicao(EdicaoFactory.edicaoValida())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(EdicaoResponseDTO.class).getIdEdicao();

        Integer idTrilha = trilhaClient.cadastrar(TrilhaDataFactory.trilhaComTodosOsCampos())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(TrilhaResponseDTO.class).getIdTrilha();

        edicaoClient.vincularTrilha(idEdicao, idTrilha)
                .then()
                .statusCode(HttpStatus.SC_OK);

        EstagiarioRequestDTO bodyRequest = EstagiarioFactory.estagiarioValido(idTrilha, idEdicao);

        Integer createdEstagiarioID = estagiarioClient.cadastrar(bodyRequest)
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_CREATED)
                .extract().as(EstagiarioResponseDTO.class).getIdEstagiario();

        estagiarioClient.atualizar(createdEstagiarioID, bodyRequestAlter)
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("status", equalTo(HttpStatus.SC_BAD_REQUEST))
                .body("errors[0]", equalTo(message));

        trilhaClient.deletar(idTrilha)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void testNaoDeveAlterarPoisTokenInvalido() {
        estagiarioClient.atualizarSemToken(-1, EstagiarioFactory.estagiarioComCpfNulo())
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_FORBIDDEN)
                .body("status", equalTo(HttpStatus.SC_FORBIDDEN))
                .body("error", equalTo("Forbidden"));
    }

}
