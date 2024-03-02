package com.chronos.tests.trilha;

import client.TrilhaClient;
import data.factory.TokenFactory;
import data.factory.TrilhaDataFactory;
import io.qameta.allure.*;
import model.trilha.TrilhaRequestDTO;
import model.trilha.TrilhaResponseDTO;
import org.junit.jupiter.api.Test;

public class DeleteTrilhaFuncionalTest {
    private final TrilhaClient trilhaClient = new TrilhaClient();

    @Feature("Trilha")
    @Story("Deletar uma trilha com sucesso")
    @Description("Testa se a requisição consegue deletar uma trilha deve retornar uma mensagem de sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testDeletarTrilhaComSucesso() {
        trilhaClient.setTOKEN(TokenFactory.getTokenAdmin());

        TrilhaRequestDTO trilhaRequestDTO = TrilhaDataFactory.trilhaComTodosOsCampos();
        TrilhaResponseDTO trilhaResponseDTO = trilhaClient.cadastrar(trilhaRequestDTO)
                .then()
                .statusCode(200).extract().as(TrilhaResponseDTO.class);

        trilhaClient.deletar(trilhaResponseDTO.getIdTrilha()).then().statusCode(204);

    }

    @Feature("Trilha")
    @Story("Deletar uma trilha com sucesso")
    @Description("Testa se a requisição consegue deletar uma trilha deve retornar uma mensagem de erro")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testTentarDeletarTrilhaComIdInvalido() {
        int idInvalido = TrilhaDataFactory.idInvalido();
        trilhaClient.setTOKEN(TokenFactory.getTokenAdmin());
        trilhaClient.deletar(idInvalido).then().statusCode(400);

    }
}
