package com.chronos.tests.edicao;

import client.EdicaoClient;
import client.TrilhaClient;
import data.factory.EdicaoFactory;
import data.factory.TrilhaDataFactory;
import io.restassured.http.ContentType;
import model.edicao.EdicaoMapper;
import model.edicao.EdicaoRequestDTO;
import model.edicao.EdicaoResponseDTO;
import model.trilha.TrilhaResponseDTO;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class AlterarEdicaoFuncionalTest {

    private final EdicaoClient edicaoClient = new EdicaoClient();
    private final TrilhaClient trilhaClient = new TrilhaClient();

    @Test
    @Tag("Fumaca")
    public void testDeveAlterarUmaEdicaoComSucesso() {
        Integer idCreated = edicaoClient.cadastrarEdicao(EdicaoFactory.edicaoValida())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(EdicaoResponseDTO.class).getIdEdicao();

        EdicaoRequestDTO alterarRequestDTO = EdicaoFactory.edicaoAlterada();

        edicaoClient.atualizar(idCreated, alterarRequestDTO)
                .then()
                    .contentType(ContentType.JSON)
                    .statusCode(HttpStatus.SC_OK)
                    .body("nome", equalTo(alterarRequestDTO.getNome()))
                    .body("descricao", equalTo(alterarRequestDTO.getDescricao()))
                    .body("dataInicial", containsStringIgnoringCase(alterarRequestDTO.getDataInicial().toString()))
                    .body("status", equalTo(alterarRequestDTO.getStatus()));

        edicaoClient.deletarPorID(idCreated)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void testNaoDeveAlterarEdicaoPoisNomeUsadoJaExiste() {
        // CRIAR MASSA
        EdicaoResponseDTO dataCreated = edicaoClient.cadastrarEdicao(EdicaoFactory.edicaoValida())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(EdicaoResponseDTO.class);

        // CRIAR MASSA PARA SER ALTERAR COM MESMO NOME
        EdicaoResponseDTO dataToAlter = edicaoClient.cadastrarEdicao(EdicaoFactory.edicaoValida())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(EdicaoResponseDTO.class);

        dataToAlter.setNome(dataCreated.getNome());

        // ATUALIZAR DADOS
        edicaoClient.atualizar(dataToAlter.getIdEdicao(), EdicaoMapper.converterParaRequestBody(dataToAlter))
                .then()
                    .contentType(ContentType.JSON)
                    .statusCode(HttpStatus.SC_CONFLICT)
                    .body("status", equalTo(HttpStatus.SC_CONFLICT))
                    .body("message", equalTo("Erro na validação dos seguintes campos: nome."));

        // DELETAR MASSAS
        edicaoClient.deletarPorID(dataCreated.getIdEdicao())
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);

        edicaoClient.deletarPorID(dataToAlter.getIdEdicao())
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);

    }

    @Test
    @Tag("Fumaca")
    public void testNaoDeveAlterarEdicaoPoisTokenInvalido() {
        Integer idCreated = edicaoClient.cadastrarEdicao(EdicaoFactory.edicaoValida())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(EdicaoResponseDTO.class).getIdEdicao();

        edicaoClient.atualizarSemToken(idCreated)
                .then()
                    .contentType(ContentType.JSON)
                    .statusCode(HttpStatus.SC_FORBIDDEN)
                .body("error", equalTo("Forbidden"))
                .body("status", equalTo(HttpStatus.SC_FORBIDDEN));

        edicaoClient.deletarPorID(idCreated);
    }

    @Test
    public void deveAlterarStatusEdicaoParaInativo() {
        Integer idCreated = edicaoClient.cadastrarEdicao(EdicaoFactory.edicaoValida())
                .then()
                   .extract().as(EdicaoResponseDTO.class).getIdEdicao();

        // MUDAR STATUS PARA INATIVO

        edicaoClient.mudarStatus(idCreated)
                .then()
                    .statusCode(HttpStatus.SC_NO_CONTENT);

        edicaoClient.buscarPorID(idCreated)
                .then()
                    .contentType(ContentType.JSON)
                    .statusCode(HttpStatus.SC_OK)
                    .body("status", equalTo("INATIVO"));

        edicaoClient.deletarPorID(idCreated);
    }

    @Test
    public void deveAlterarStatusEdicaoParaAtivo(){
        Integer idCreated = edicaoClient.cadastrarEdicao(EdicaoFactory.edicaoValida())
                .then()
                .extract().as(EdicaoResponseDTO.class).getIdEdicao();

        // MUDAR STATUS PARA INATIVO

        edicaoClient.mudarStatus(idCreated)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);

        // MUDAR STATUS PARA ATIVO

        edicaoClient.mudarStatus(idCreated)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);

        edicaoClient.buscarPorID(idCreated)
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_OK)
                .body("status", equalTo("ATIVO"));

        edicaoClient.deletarPorID(idCreated);
    }

    @Test
    public void deveVincularUmaTrilhaAEdicaoComSucesso() {
        TrilhaResponseDTO trilhaCriada = trilhaClient.cadastrar(TrilhaDataFactory.trilhaComTodosOsCampos())
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .extract().as(TrilhaResponseDTO.class);

        EdicaoResponseDTO edicaoCriada = edicaoClient.cadastrarEdicao(EdicaoFactory.edicaoValida())
                .then()
                    .statusCode(HttpStatus.SC_OK)
                .extract().as(EdicaoResponseDTO.class);

        edicaoClient.vincularTrilha(edicaoCriada.getIdEdicao(), trilhaCriada.getIdTrilha())
                .then()
                    .contentType(ContentType.JSON)
                    .statusCode(HttpStatus.SC_OK)
                    .body("idEdicao", equalTo(edicaoCriada.getIdEdicao()))
                    .body("nome", equalTo(edicaoCriada.getNome()))
                    .body("status", equalTo(edicaoCriada.getStatus()))
                    .body("trilhas[0].idTrilha", equalTo(trilhaCriada.getIdTrilha()))
                    .body("trilhas[0].nome", equalTo(trilhaCriada.getNome()))
                    .body("trilhas[0].descricao", equalTo(trilhaCriada.getDescricao()))
                    .body("trilhas[0].status", equalTo(trilhaCriada.getStatus()));

        trilhaClient.deletar(trilhaCriada.getIdTrilha())
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);

    }

    @Test
    public void testDesvincularUmaTrilhaComSucesso() {
        TrilhaResponseDTO trilhaCriada = trilhaClient.cadastrar(TrilhaDataFactory.trilhaComTodosOsCampos())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(TrilhaResponseDTO.class);

        EdicaoResponseDTO edicaoCriada = edicaoClient.cadastrarEdicao(EdicaoFactory.edicaoValida())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(EdicaoResponseDTO.class);

        edicaoClient.vincularTrilha(edicaoCriada.getIdEdicao(), trilhaCriada.getIdTrilha())
                .then()
                .statusCode(HttpStatus.SC_OK);

        edicaoClient.desvincularTrilha(edicaoCriada.getIdEdicao(), trilhaCriada.getIdTrilha())
                .then()
                    .contentType(ContentType.JSON)
                    .statusCode(HttpStatus.SC_OK)
                    .body("idEdicao", equalTo(edicaoCriada.getIdEdicao()))
                    .body("nome", equalTo(edicaoCriada.getNome()))
                    .body("status", equalTo(edicaoCriada.getStatus()))
                    .body("trilhas", empty());

        trilhaClient.deletar(trilhaCriada.getIdTrilha())
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);

    }

    @Test
    public void testNaoDeveVincularTrilhaPoisIdEdicaoInvalido(){
        TrilhaResponseDTO trilhaCriada = trilhaClient.cadastrar(TrilhaDataFactory.trilhaComTodosOsCampos())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(TrilhaResponseDTO.class);

        edicaoClient.vincularTrilha(-1, trilhaCriada.getIdTrilha())
                .then()
                    .contentType(ContentType.JSON)
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .body("status", equalTo(HttpStatus.SC_BAD_REQUEST))
                    .body("errors[0]", equalTo("Edição não encontrada!"));

        trilhaClient.deletar(trilhaCriada.getIdTrilha())
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void testNaoDeveVincularTrilhaPoisIdTrilhaInvalido() {
        EdicaoResponseDTO edicaoCriada = edicaoClient.cadastrarEdicao(EdicaoFactory.edicaoValida())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(EdicaoResponseDTO.class);

        edicaoClient.vincularTrilha(edicaoCriada.getIdEdicao(), -1)
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("status", equalTo(HttpStatus.SC_BAD_REQUEST))
                .body("errors[0]", equalTo("Trilha não encontrada"));

        edicaoClient.deletarPorID(edicaoCriada.getIdEdicao())
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
}
