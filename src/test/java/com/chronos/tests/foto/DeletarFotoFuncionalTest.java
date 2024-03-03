package com.chronos.tests.foto;

import client.EdicaoClient;
import client.FotoClient;
import data.factory.EdicaoFactory;
import data.factory.Factory;
import data.factory.FotoFactory;
import model.edicao.EdicaoResponseDTO;
import model.foto.FotoResponseDTO;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import utils.image.ImageTypes;

import static org.hamcrest.CoreMatchers.equalTo;

public class DeletarFotoFuncionalTest {
    private final FotoClient fotoClient = new FotoClient();
    private final EdicaoClient edicaoClient = new EdicaoClient();

    @Test
    public void testDeveDeletarUmaFotoComSucesso() {
        Integer idEdicao = edicaoClient.cadastrarEdicao(EdicaoFactory.edicaoValida()).then().extract().as(EdicaoResponseDTO.class).getIdEdicao();

        FotoResponseDTO massaCriada = fotoClient.cadastrarFotoComEdicaoComSucesso(FotoFactory.gerarJPG(), ImageTypes.JPG, idEdicao)
                .then()
                .extract()
                .as(FotoResponseDTO.class);

        fotoClient.deletarFotoPorId(massaCriada.getIdFoto())
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

    @Test
    public void testNaoDeveDeletarPoisTokenNaoEnviado() {
        fotoClient.deletarFotoPorIdSemToken(-1)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_FORBIDDEN)
                .body("error", equalTo("Forbidden"));
    }

}
