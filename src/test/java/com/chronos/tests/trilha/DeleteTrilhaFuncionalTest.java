package com.chronos.tests.trilha;

import client.TrilhaClient;
import data.factory.TokenFactory;
import data.factory.TrilhaDataFactory;
import model.trilha.TrilhaRequestDTO;
import model.trilha.TrilhaResponseDTO;
import org.junit.jupiter.api.Test;

public class DeleteTrilhaFuncionalTest {
    private final TrilhaClient trilhaClient = new TrilhaClient();


    @Test
    public void testDeletarTrilhaComSucesso() {
        trilhaClient.setTOKEN(TokenFactory.getTokenAdmin());

        TrilhaRequestDTO trilhaRequestDTO = TrilhaDataFactory.trilhaComTodosOsCampos();
        TrilhaResponseDTO trilhaResponseDTO = trilhaClient.cadastrar(trilhaRequestDTO)
                .then()
                .statusCode(200).extract().as(TrilhaResponseDTO.class);

        trilhaClient.deletar(trilhaResponseDTO.getIdTrilha()).then().statusCode(204);

    }
    @Test
    public void testTentarDeletarTrilhaComIdInvalido() {
        int idInvalido = TrilhaDataFactory.idInvalido();
        trilhaClient.setTOKEN(TokenFactory.getTokenAdmin());
        trilhaClient.deletar(idInvalido).then().statusCode(400);

    }
}
