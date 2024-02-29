package com.chronos.tests.edicao;

import client.EdicaoClient;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;

public class BuscarEdicaoFuncionalTest {

    private EdicaoClient edicaoClient = new EdicaoClient();

    @Test
    public void testDeveRetornarUmaPaginacaoDeEdicao() {
        edicaoClient.buscarTudo()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("content", notNullValue());
    }
}
