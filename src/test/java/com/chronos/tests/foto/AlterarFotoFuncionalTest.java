package com.chronos.tests.foto;

import client.EdicaoClient;
import client.FotoClient;
import data.factory.EdicaoFactory;
import data.factory.Factory;
import data.factory.FotoFactory;
import io.restassured.http.ContentType;
import model.edicao.EdicaoResponseDTO;
import model.estagiario.EstagiarioResponseDTO;
import model.foto.FotoResponseDTO;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import utils.image.ImageTypes;

import java.awt.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;

public class AlterarFotoFuncionalTest {
    private FotoClient fotoClient = new FotoClient();
    private EdicaoClient edicaoClient = new EdicaoClient();

    @Test
    public void testDeveAlterarFotoComSucesso() {

        Integer idEdicao = edicaoClient.cadastrarEdicao(EdicaoFactory.edicaoValida()).then().extract().as(EdicaoResponseDTO.class).getIdEdicao();

        FotoResponseDTO massaCriada = fotoClient.cadastrarFotoComEdicaoComSucesso(FotoFactory.gerarJPG(), ImageTypes.JPG, idEdicao)
                .then()
                .extract()
                .as(FotoResponseDTO.class);

        FotoResponseDTO responseDTO = fotoClient.alterarFoto(massaCriada.getIdFoto(), FotoFactory.gerarPNG(), ImageTypes.PNG)
                .then()
                    .contentType(ContentType.JSON)
                    .statusCode(HttpStatus.SC_OK)
                    .extract().as(FotoResponseDTO.class);

        assertAll("responseDTO",
                () -> Assertions.assertEquals(responseDTO.getIdFoto(), massaCriada.getIdFoto()),
                () -> Assertions.assertNotEquals(responseDTO.getArquivo(), massaCriada.getArquivo()),
                () -> Assertions.assertNotEquals(responseDTO.getTipo(), massaCriada.getTipo()),
                () -> Assertions.assertNotEquals(responseDTO.getNome(), massaCriada.getNome())
        );

        edicaoClient.deletarPorID(idEdicao)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);

    }

    @Test
    public void testNaoDeveAlterarFotoPoisIDInvalido() {

        fotoClient.alterarFoto(-1, FotoFactory.gerarPNG(), ImageTypes.PNG)
                .then()
                    .contentType(ContentType.JSON)
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .body("errors[0]", equalTo("Imagem não encontrada!"))
                    .body("status", equalTo(HttpStatus.SC_BAD_REQUEST));
    }

    @Test
    public void testNaoDeveAlterarFotoPoisTokenNaoEnviado() {
        fotoClient.alterarFotoSemToken()
                .then()
                .statusCode(HttpStatus.SC_FORBIDDEN)
                .body("error", equalTo("Forbidden"));
    }
}
