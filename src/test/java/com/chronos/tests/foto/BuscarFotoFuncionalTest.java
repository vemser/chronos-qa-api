package com.chronos.tests.foto;

import client.FotoClient;
import data.factory.Factory;
import data.factory.FotoFactory;
import io.restassured.http.ContentType;
import model.foto.FotoResponseDTO;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import utils.image.ImageTypes;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;

public class BuscarFotoFuncionalTest {

    private final FotoClient fotoClient = new FotoClient();

    @Test
    @Tag("Fumaca")
    public void testDeveRetornarUmaListaDeFotos() {
        // CRIAR MASSA
        FotoResponseDTO responseDTO = fotoClient.cadastrarFotoComSucesso(FotoFactory.gerarPNG(), ImageTypes.PNG, Factory.nome())
                        .then()
                            .statusCode(HttpStatus.SC_CREATED)
                            .extract().as(FotoResponseDTO.class);

        // BUSCAR MASSA
        List buscarTotalResponseDTO = fotoClient.resgatarFotosComSucesso()
                .then()
                    .contentType(ContentType.JSON)
                    .statusCode(HttpStatus.SC_OK)
                    .extract().as(List.class);

        assertFalse(buscarTotalResponseDTO.isEmpty());

        fotoClient.deletarFotoPorId(responseDTO.getIdFoto());
    }

    @Test
    public void testDeveRetornarUmaFotoPeloIDComSucesso() {

        FotoResponseDTO responseDTO = fotoClient.cadastrarFotoComSucesso(FotoFactory.gerarJPG(), ImageTypes.JPG, Factory.nome())
                .then()
                .extract().as(FotoResponseDTO.class);

        fotoClient.resgatarFotoPorId(responseDTO.getIdFoto())
                .then()
                    .contentType(ContentType.JSON)
                    .statusCode(HttpStatus.SC_OK)
                    .body("nome", equalTo(responseDTO.getNome()))
                    .body("tipo", equalTo(responseDTO.getTipo()));

        fotoClient.deletarFotoPorId(responseDTO.getIdFoto())
                .then()
                    .statusCode(HttpStatus.SC_NO_CONTENT);
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
