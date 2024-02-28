package com.chronos.tests.modulo;

import client.ModuloClient;
import data.factory.GetDataFactory;
import data.factory.ModuloDataFactory;
import io.restassured.response.Response;
import model.GetRequestDTO;
import model.ModuloRequestDTO;
import model.ModuloResponseDTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class GetModuloFuncionalTest {
    Integer idModuloCadastrado = 0;
    ModuloClient moduloClient = new ModuloClient();
    ModuloResponseDTO moduloCadastrado;
    @Before
    public void setUp() {
        ModuloRequestDTO moduloACadastrar = ModuloDataFactory.moduloComTodosOsCampos();
        moduloCadastrado =
                moduloClient.cadastrar(moduloACadastrar)
                        .then()
                        .statusCode(200)
                        .extract().as(ModuloResponseDTO.class);

        idModuloCadastrado = moduloCadastrado.getIdModulo();
    }
    @After
    public void cleanUp() {
        moduloClient.deletar(idModuloCadastrado)
                .then()
                .statusCode(204);
    }
    @Test
    public void testBuscarTodosOsModulos() {
        GetRequestDTO getPadraoRequest = GetDataFactory.getPadrao();
        Response response =
                moduloClient.buscarTudo(getPadraoRequest)
                        .then()
                        .statusCode(201)
                        .extract().response();
        List<ModuloResponseDTO> modulos = response.jsonPath().getList("content", ModuloResponseDTO.class);

        Assertions.assertNotNull(modulos);
        Assertions.assertFalse(modulos.isEmpty());
    }

    @Test
    public void testBuscarTodosOsModulosSemAutorizacao() {
        GetRequestDTO getPadraoRequest = GetDataFactory.getPadrao();
                moduloClient.buscarTudoSemAuth(getPadraoRequest)
                        .then()
                        .statusCode(403);
    }

    @Test
    public void testBuscarPorModuloEspecifico() {
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
}
