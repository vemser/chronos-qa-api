package com.chronos.tests.foto;

import client.EdicaoClient;
import client.FotoClient;
import data.factory.EdicaoFactory;
import data.factory.FotoFactory;
import io.restassured.http.ContentType;
import model.edicao.EdicaoResponseDTO;
import model.foto.FotoResponseDTO;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.image.ImageTypes;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;

public class BuscarFotoFuncionalTest {

    private final FotoClient fotoClient = new FotoClient();
    private final EdicaoClient edicaoClient = new EdicaoClient();

    @Test
    @Tag("Fumaca")
    public void testDeveRetornarUmaFotoPeloIDComSucesso() {

        Integer idEdicao = edicaoClient.cadastrarEdicao(EdicaoFactory.edicaoValida())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(EdicaoResponseDTO.class).getIdEdicao();

        FotoResponseDTO fotoGerada = fotoClient.cadastrarFotoComEdicaoComSucesso(FotoFactory.gerarJPG(), ImageTypes.JPG, idEdicao)
                .then()
                .extract().as(FotoResponseDTO.class);


        FotoResponseDTO fotoConsultada = fotoClient.resgatarFotoPorId(fotoGerada.getIdFoto())
                .then()
                    .contentType(ContentType.JSON)
                    .statusCode(HttpStatus.SC_OK)
                    .extract().as(FotoResponseDTO.class);

        assertAll("fotoConsultada",
                () -> Assertions.assertEquals(fotoConsultada.getIdFoto(), fotoGerada.getIdFoto()),
                () -> Assertions.assertEquals(fotoConsultada.getNome(), fotoGerada.getNome()),
                () -> Assertions.assertEquals(fotoConsultada.getArquivo(), fotoGerada.getArquivo()),
                () -> Assertions.assertEquals(fotoConsultada.getTipo(), fotoGerada.getTipo())
        );

       edicaoClient.deletarPorID(idEdicao).then().statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void testNaoDeveRetornarUmaFotoPoisIDNaoEncontrado() {
        fotoClient.resgatarFotoPorId(-1)
                .then()
                    .contentType(ContentType.JSON)
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .body("errors[0]", equalTo("Imagem não encontrada!"))
                    .body("status", equalTo(HttpStatus.SC_BAD_REQUEST));

    }

}
