package com.chronos.tests.foto;

import client.FotoClient;
import data.factory.Factory;
import data.factory.FotoFactory;
import org.apache.http.HttpStatus;
import org.junit.Test;
import utils.image.ImageTypes;

import static org.hamcrest.CoreMatchers.equalTo;

public class DeletarFotoFuncionalTest {
    private final FotoClient fotoClient = new FotoClient();

    @Test
    public void testDeveDeletarUmaFotoComSucesso() {
        Integer idMassa = fotoClient.cadastrarFotoComSucesso(FotoFactory.gerarPNG(), ImageTypes.PNG, Factory.nome())
                .then()
                    .extract()
                    .path("idFoto");

        fotoClient.deletarFotoPorId(idMassa)
                .then()
                    .statusCode(HttpStatus.SC_NO_CONTENT);

    }

    @Test
    public void testNaoDeveDeletarPoisIDNaoEncontrar() {
        fotoClient.deletarFotoPorId(-1)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .body("errors[0]", equalTo("Imagem não encontrada!"))
                    .body("status", equalTo(HttpStatus.SC_BAD_REQUEST));
    }

}
