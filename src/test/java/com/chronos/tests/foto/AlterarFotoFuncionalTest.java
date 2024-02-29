package com.chronos.tests.foto;

import client.FotoClient;
import data.factory.Factory;
import data.factory.FotoFactory;
import io.restassured.http.ContentType;
import model.foto.FotoResponseDTO;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;

public class AlterarFotoFuncionalTest {
    private FotoClient fotoClient = new FotoClient();


    @Test
    public void testDeveAlterarFotoComSucesso() {
        FotoResponseDTO massaCriada = fotoClient.cadastrarFotoComSucesso(FotoFactory.gerarJPG(), "image/jpg", Factory.nome())
                .then()
                .extract().as(FotoResponseDTO.class);

        FotoResponseDTO responseDTO = fotoClient.alterarFoto(massaCriada.getIdFoto(), FotoFactory.gerarPNG(), "image/png", Factory.nome())
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_OK)
                .extract().as(FotoResponseDTO.class);

        assertAll("responseDTO",
                () -> Assertions.assertEquals(massaCriada.getIdFoto(), responseDTO.getIdFoto()),
                () -> Assertions.assertNotEquals(responseDTO.getArquivo(), massaCriada.getArquivo()),
                () -> Assertions.assertNotEquals(responseDTO.getTipo(), massaCriada.getTipo()),
                () -> Assertions.assertNotEquals(responseDTO.getNome(), massaCriada.getNome())
        );

        fotoClient.deletarFotoPorId(massaCriada.getIdFoto())
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void testNaoDeveAlterarFotoPoisIDInvalido() {
        fotoClient.alterarFoto(-1, FotoFactory.gerarPNG(), "image/png", Factory.nome())
                .then()
                    .contentType(ContentType.JSON)
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .body("errors[0]", equalTo("Imagem não encontrada!"))
                    .body("status", equalTo(HttpStatus.SC_BAD_REQUEST));
    }

    @Test
    public void testNaoDeveAlterarFotoPoisNomeNaoEnviado() {
        fotoClient.alterarFotoSemNome()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
    @Test
    public void testNaoDeveAlterarFotoPoisTokenNaoEnviado() {
        fotoClient.alterarFotoSemToken()
                .then()
                .statusCode(HttpStatus.SC_FORBIDDEN);
    }
}
