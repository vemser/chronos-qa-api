package com.chronos.tests.foto;

import client.EdicaoClient;
import client.FotoClient;
import client.TrilhaClient;
import data.factory.EdicaoFactory;
import data.factory.FotoFactory;
import data.factory.TrilhaDataFactory;
import io.restassured.http.ContentType;
import model.edicao.EdicaoResponseDTO;
import model.foto.FotoResponseDTO;
import model.trilha.TrilhaResponseDTO;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import utils.image.ImageTypes;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;

public class CriarFotoFuncionalTest {

    private final FotoClient fotoClient = new FotoClient();
    private final EdicaoClient edicaoClient = new EdicaoClient();
    private final TrilhaClient trilhaClient = new TrilhaClient();


    @ParameterizedTest
    @MethodSource("data.provider.FotoProvider#providerCadastrarFoto")
    @DisplayName("Testes: Deve cadastrar fotos asssociadas a uma EDIÇÃO com sucesso")
    public void testDeveCriarUmaFotoComEdicaoComSucesso(File file, String typeImage, String typeResponseImage) {

        Integer idEdicao = edicaoClient.cadastrarEdicao(EdicaoFactory.edicaoValida())
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .extract().as(EdicaoResponseDTO.class).getIdEdicao();

        // CRIAR MASSA
        FotoResponseDTO responseDTO = fotoClient.cadastrarFotoComEdicaoComSucesso(file, typeImage, idEdicao)
                .then()
                    .contentType(ContentType.JSON)
                    .statusCode(HttpStatus.SC_CREATED)
                    .extract().as(FotoResponseDTO.class);

        // VALIDAR REQUEST
        assertAll("responseDTO",
                () -> Assertions.assertNotNull(responseDTO.getIdFoto()),
                () -> Assertions.assertNotNull(responseDTO.getArquivo()),
                () -> Assertions.assertEquals(typeResponseImage, responseDTO.getTipo())
        );

        // DELETAR MASSA
        edicaoClient.deletarPorID(idEdicao)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @ParameterizedTest
    @MethodSource("data.provider.FotoProvider#providerCadastrarFoto")
    @DisplayName("Testes: Deve cadastrar fotos asssociadas a uma TRILHA com sucesso")
    public void testDeveCriarUmaFotoComTrilhaComSucesso(File file, String typeImage, String typeResponseImage) {

        Integer idTrilha = trilhaClient.cadastrar(TrilhaDataFactory.trilhaComTodosOsCampos())
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .extract().as(TrilhaResponseDTO.class)
                    .getIdTrilha();

        // CRIAR MASSA
        FotoResponseDTO responseDTO = fotoClient.cadastrarFotoComTrilhaComSucesso(file, typeImage, idTrilha)
                .then()
                    .contentType(ContentType.JSON)
                    .statusCode(HttpStatus.SC_CREATED)
                    .extract().as(FotoResponseDTO.class);

        // VALIDAR REQUEST
        assertAll("responseDTO",
                () -> Assertions.assertNotNull(responseDTO.getIdFoto()),
                () -> Assertions.assertNotNull(responseDTO.getArquivo()),
                () -> Assertions.assertEquals(typeResponseImage, responseDTO.getTipo())
        );

        // DELETAR MASSA
        trilhaClient.deletar(idTrilha)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void testNaoDeveCriarFotoComTrilhaPoisTokenNaoEnviado() {
        fotoClient.cadastrarFotoComTrilhaSemToken(FotoFactory.gerarJPG(), ImageTypes.JPG)
                .then()
                    .statusCode(HttpStatus.SC_FORBIDDEN)
                    .body("error", equalTo("Forbidden"));
    }

    @Test
    public void testNaoDeveCriarFotoComEdicaoPoisTokenNaoEnviado() {
        fotoClient.cadastrarFotoComEdicaoSemToken(FotoFactory.gerarJPG(), ImageTypes.JPG)
                .then()
                    .statusCode(HttpStatus.SC_FORBIDDEN)
                    .body("error", equalTo("Forbidden"));
    }

    @Test
    public void testNaoDeveCriarFotoEmTrilhaPoisIdNaoEnviado() {
        fotoClient.cadastrarFotoComTrilhaSemID(FotoFactory.gerarJPG(), ImageTypes.JPG)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .body("status", equalTo(HttpStatus.SC_BAD_REQUEST))
                    .body("errors[0]", equalTo("Trilha não encontrada"));
    }

    @Test
    public void testNaoDeveCriarFotoEmEdicaoPoisIdNaoEnviado() {
        fotoClient.cadastrarFotoComEdicaoSemID(FotoFactory.gerarJPG(), ImageTypes.JPG)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .body("status", equalTo(HttpStatus.SC_BAD_REQUEST))
                    .body("errors[0]", equalTo("Edição não encontrada!"));
    }

}
