package client;

import io.restassured.response.Response;
import specs.AuthSpec;
import specs.FotoSpec;

import java.io.File;

import static io.restassured.RestAssured.given;

public class CurriculoClient {

    private static final String PATH_CURRICULO_ARQUIVO = "/curriculo/arquivo";
    private static final String PATH_CURRICULO_ESTAGIARIO_ID = "/curriculo/arquivo/{idCurriculo}";
    private static final String PATH_CURRICULO_ESTAGIARIO = "/curriculo/{idEstagiario}";

    public Response cadastrarCurriculo(Integer idEstagiario, File file) {
        return
                given()
                        .spec(FotoSpec.setup())
                        .queryParam("idEstagiario", idEstagiario)
                        .multiPart("curriculo", file, "application/vnd.openxmlformats-officedocument.wordprocessingml.document")
                        .when()
                        .post(PATH_CURRICULO_ARQUIVO);
    }


    public Response buscarCurriculoPeloIDCurriculo(Integer idCurriculo) {
        return
                given()
                        .spec(AuthSpec.setup())
                        .pathParam("idCurriculo", idCurriculo)
                        .when()
                        .get(PATH_CURRICULO_ESTAGIARIO_ID);
    }

    public Response buscarCurriculoPeloIDEstagiario(Integer idEstagiario) {
        return
                given()
                    .spec(AuthSpec.setup())
                    .pathParam("idEstagiario", idEstagiario)
                    .when()
                    .get(PATH_CURRICULO_ESTAGIARIO);
    }



    public Response deletarCurriculo(Integer idCurriculo) {
        return given()
                .spec(AuthSpec.setup())
                .pathParam("idCurriculo", idCurriculo)
                .when()
                .delete(PATH_CURRICULO_ESTAGIARIO_ID);
    }

    public Response deletarCurriculoPeloIDEstagiario(Integer idEstagiario) {
        return given()
                .spec(AuthSpec.setup())
                .pathParam("idEstagiario", idEstagiario)
                .when()
                .delete(PATH_CURRICULO_ESTAGIARIO);
    }

}
