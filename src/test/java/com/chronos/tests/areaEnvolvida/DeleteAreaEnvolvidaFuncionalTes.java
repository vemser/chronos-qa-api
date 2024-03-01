package com.chronos.tests.areaEnvolvida;

import client.AreaEnvolvidaClient;
import data.factory.AreaEnvovidaDataFactory;
import data.factory.TokenFactory;
import model.AreaEnvolvidaRequestDTO;
import model.AreaEnvolvidaResponseDTO;
import org.junit.jupiter.api.Test;

public class DeleteAreaEnvolvidaFuncionalTes {

    private final AreaEnvolvidaClient areaEnvolvidaClient = new AreaEnvolvidaClient();


    @Test
    public void testDeletarAreaEnvolvidaComSucesso() {
        areaEnvolvidaClient.setTOKEN(TokenFactory.getTokenAdmin());

        AreaEnvolvidaRequestDTO areaEnvolvidaRequestDTO = AreaEnvovidaDataFactory.areaEnvolvidaComSucesso();
        AreaEnvolvidaResponseDTO areaEnvolvidaResponseDTO = areaEnvolvidaClient.cadastrar(areaEnvolvidaRequestDTO)
                .then()
                .statusCode(200).extract().as(AreaEnvolvidaResponseDTO.class);

        areaEnvolvidaClient.deletar(areaEnvolvidaResponseDTO.getIdAreaEnvolvida()).then().statusCode(204);

    }
    @Test
    public void testTentarDeletarAreaEnvolvidaComIdInvalido() {
        int idInvalido = AreaEnvovidaDataFactory.idInvalido();
        areaEnvolvidaClient.setTOKEN(TokenFactory.getTokenAdmin());
        areaEnvolvidaClient.deletar(idInvalido).then().statusCode(400);

    }

}
