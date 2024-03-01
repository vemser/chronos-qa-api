package com.chronos.tests.modulo;

import client.ModuloClient;
import data.factory.ModuloDataFactory;
import io.restassured.response.Response;
import model.ModuloRequestDTO;
import model.ModuloResponseDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class GetModuloFuncionalTest {
    Integer idModuloCadastrado = 0;
    ModuloClient moduloClient = new ModuloClient();
    ModuloResponseDTO moduloCadastrado;
    @BeforeEach
    public void setUp() {
        ModuloRequestDTO moduloACadastrar = ModuloDataFactory.moduloComTodosOsCampos();
        moduloCadastrado =
                moduloClient.cadastrar(moduloACadastrar)
                        .then()
                        .statusCode(201)
                        .extract().as(ModuloResponseDTO.class);

        idModuloCadastrado = moduloCadastrado.getIdModulo();
    }
    @AfterEach
    public void cleanUp() {
        moduloClient.deletar(idModuloCadastrado)
                .then()
                .statusCode(204);
    }
    @Test
    public void testBuscarTodosOsModulosComSucesso() {
        Response response =
                moduloClient.buscarTudo()
                        .then()
                        .statusCode(200)
                        .extract().response();
        List<ModuloResponseDTO> modulos = response.jsonPath().getList("content", ModuloResponseDTO.class);

        Assertions.assertNotNull(modulos);
        Assertions.assertFalse(modulos.isEmpty());
    }

    @Test
    public void testBuscarTodosOsModulosSemAutorizacao() {
                moduloClient.buscarTudoSemAuth()
                        .then()
                        .statusCode(403);
    }

    @Test
    public void testBuscarPorModuloEspecificoIDValido() {
        ModuloResponseDTO moduloBuscado =
        moduloClient.buscarPorID(idModuloCadastrado)
                .then()
                .statusCode(200)
                .extract().as(ModuloResponseDTO.class);

        Assertions.assertEquals(moduloBuscado.getNome(), moduloCadastrado.getNome());
        Assertions.assertEquals(moduloBuscado.getStatus(), moduloCadastrado.getStatus());
        Assertions.assertEquals(moduloBuscado.getNomeResponsavel(), moduloCadastrado.getNomeResponsavel());
        Assertions.assertEquals(moduloBuscado.getCargoResponsavel(), moduloCadastrado.getCargoResponsavel());
        Assertions.assertEquals(moduloBuscado.getIdModulo(), moduloCadastrado.getIdModulo());
        Assertions.assertEquals(moduloBuscado.getConteudoProgramatico(), moduloCadastrado.getConteudoProgramatico());
    }

    @Test
    public void testBuscarPorModuloEspecificoIDInvalido() {
                moduloClient.buscarPorID(0)
                        .then()
                        .statusCode(400);
    }

    @Test
    public void testBuscarPorModuloEspecificoIDValidoSemAutorizacao() {
                moduloClient.buscarPorIDSemAuth(idModuloCadastrado)
                        .then()
                        .statusCode(403);
    }
}
