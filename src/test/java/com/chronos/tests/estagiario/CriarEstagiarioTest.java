package com.chronos.tests.estagiario;

import client.EdicaoClient;
import client.EstagiarioClient;
import client.TrilhaClient;
import data.factory.EdicaoFactory;
import data.factory.EstagiarioFactory;
import data.factory.TrilhaDataFactory;
import io.restassured.http.ContentType;
import model.EstagiarioRequestDTO;
import model.edicao.EdicaoResponseDTO;
import model.estagiario.EstagiarioResponseDTO;
import model.trilha.TrilhaResponseDTO;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import specs.AuthSpec;
import specs.InicialSpecs;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("ESTAGIÁRIO: CRIAÇÃO")
public class CriarEstagiarioTest {

    private final EstagiarioClient estagiarioClient = new EstagiarioClient();
    private final EdicaoClient edicaoClient = new EdicaoClient();
    private final TrilhaClient trilhaClient = new TrilhaClient();

    @Test
    @DisplayName("Deve criar um Estagiário com sucesso e retornar 201")
    public void testCriarNovoEstagiario() {
        // A1: CRIAR MASSAS
        Integer idEdicao = edicaoClient.cadastrarEdicao(EdicaoFactory.edicaoValida())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(EdicaoResponseDTO.class).getIdEdicao();

        Integer idTrilha = trilhaClient.cadastrar(TrilhaDataFactory.trilhaComTodosOsCampos())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(TrilhaResponseDTO.class).getIdTrilha();

        // A2: VINCULAR TRILHAS
        edicaoClient.vincularTrilha(idEdicao, idTrilha)
                .then()
                .statusCode(HttpStatus.SC_OK);

        // A3: CRIAR ESTAGIÁRIO

        EstagiarioRequestDTO bodyRequest = EstagiarioFactory.estagiarioValido(idTrilha, idEdicao);

        EstagiarioResponseDTO responseDTO = estagiarioClient.cadastrar(bodyRequest)
                .then()
                    .contentType(ContentType.JSON)
                    .statusCode(HttpStatus.SC_CREATED)
                    .extract().as(EstagiarioResponseDTO.class);

        assertAll(
                () -> Assertions.assertNotNull(responseDTO.getIdEstagiario()),
                () -> Assertions.assertEquals(bodyRequest.getCpf(), responseDTO.getCpf()),
                () -> Assertions.assertEquals(bodyRequest.getNome(), responseDTO.getNome()),
                () -> Assertions.assertEquals(bodyRequest.getTelefone(), responseDTO.getTelefone()),
                () -> Assertions.assertEquals(bodyRequest.getCurso(), responseDTO.getCurso()),
                () -> Assertions.assertEquals(bodyRequest.getCidade(), responseDTO.getCidade()),
                () -> Assertions.assertEquals(bodyRequest.getEstado(), responseDTO.getEstado()),
                () -> Assertions.assertEquals(bodyRequest.getEmailPessoal(), responseDTO.getEmailPessoal()),
                () -> Assertions.assertEquals(bodyRequest.getEmailCorporativo(), responseDTO.getEmailCorporativo()),
                () -> Assertions.assertEquals(bodyRequest.getInstituicaoEnsino(), responseDTO.getInstituicaoEnsino()),
                () -> Assertions.assertEquals(bodyRequest.getGithub(), responseDTO.getGithub()),
                () -> Assertions.assertEquals(bodyRequest.getLinkedin(), responseDTO.getLinkedin()),
                () -> Assertions.assertEquals(bodyRequest.getStatus(), responseDTO.getStatus()),
                () -> Assertions.assertEquals(bodyRequest.getObservacao(), responseDTO.getObservacao()),
                () -> Assertions.assertEquals(bodyRequest.getIdTrilha(), responseDTO.getTrilha().getIdTrilha()),
                () -> Assertions.assertEquals(bodyRequest.getIdEdicao(), responseDTO.getEdicao().getIdEdicao())
        );

        // A4: DELETAR MASSAS
        estagiarioClient.deletar(responseDTO.getIdEstagiario())
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);

        trilhaClient.deletar(idTrilha)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);

    }

    @ParameterizedTest
    @MethodSource("data.provider.EstagiarioProvider#providerCadastroComCamposObrigatoriosNaoPreenchidos")
    @DisplayName("Teste: não deve cadastrar novos usuários pois campos obrigatórios não foram preenchidos")
    public void testNaoDeveCadastrarPoisCamposObrigatoriosNaoPreenchidos(EstagiarioRequestDTO bodyRequest, String message){
        estagiarioClient.cadastrar(bodyRequest)
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("status", equalTo(HttpStatus.SC_BAD_REQUEST))
                .body("errors[0]", equalTo(message));
    }

    @Test
    public void testNaoDeveCadastrarEstagiarioPoisTokenInvalido() {

        estagiarioClient.cadastrarSemToken(EstagiarioFactory.estagiarioComCpfNulo())
                .then()
                    .log().body()
                    .contentType(ContentType.JSON)
                    .statusCode(HttpStatus.SC_FORBIDDEN)
                    .body("status", equalTo(HttpStatus.SC_FORBIDDEN))
                    .body("error", equalTo("Forbidden"));

    }

}
