package com.chronos.tests.foto;

import client.FotoClient;
import data.factory.Factory;
import data.factory.FotoFactory;
import io.restassured.http.ContentType;
import model.foto.FotoResponseDTO;
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

    @ParameterizedTest
    @MethodSource("data.provider.FotoProvider#providerCadastrarFoto")
    @DisplayName("Testes: Cadastro de fotos")
    public void testDeveCriarUmaFotoComSucesso(File file, String typeImage, String nome) {

        // CRIAR MASSA
        FotoResponseDTO responseDTO = fotoClient.cadastrarFotoComSucesso(file, typeImage, nome)
                .then()
                    .contentType(ContentType.JSON)
                    .statusCode(HttpStatus.SC_CREATED)
                    .extract().as(FotoResponseDTO.class);

        // VALIDAR REQUEST
        assertAll("responseDTO",
                () -> Assertions.assertNotNull(responseDTO.getIdFoto()),
                () -> Assertions.assertNotNull(responseDTO.getArquivo()),
                () -> Assertions.assertEquals(typeImage, responseDTO.getTipo()),
                () -> Assertions.assertEquals(nome, responseDTO.getNome())
        );

        // DELETAR MASSA
        fotoClient.deletarFotoPorId(responseDTO.getIdFoto())
                .then()
                    .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void testNaoDeveCriarFotoPoisTokenNaoEnviado() {
        fotoClient.cadastrarFotoSemToken(FotoFactory.gerarJPG(), ImageTypes.JPG, Factory.nome())
                .then()
                    .statusCode(HttpStatus.SC_FORBIDDEN)
                    .body("error", equalTo("Forbidden"));
    }
    @Test
    public void testNaoDeveCriarFotoPoisNomeNaoEnviado() {
        fotoClient.cadastrarFotoSemQuery(FotoFactory.gerarJPG(), ImageTypes.JPG, Factory.nome())
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

}
