package com.chronos.tests.trilha;
import client.TrilhaClient;
import data.factory.TokenFactory;
import org.junit.jupiter.api.Test;



public class GetTrilhaTest {

    private final TrilhaClient trilhaClient = new TrilhaClient();


    @Test
    public void testBuscarTrilhaComSucesso() {
        trilhaClient.setTOKEN(TokenFactory.getTokenAdmin());

        trilhaClient.buscarTudo()
                .then()
                .statusCode(200);

    }
}
