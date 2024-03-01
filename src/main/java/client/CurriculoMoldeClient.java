package client;

import io.restassured.response.Response;
import model.curriculoMolde.CurriculoMoldeRequestDTO;
import specs.AuthSpec;
import specs.FotoSpec;
import specs.InicialSpecs;

import java.io.File;

import static io.restassured.RestAssured.given;

public class CurriculoMoldeClient {
    private static final String PATH_CURRICULO = "/curriculo-molde" + "/{idTrilha}";
    private static final String PATH_CURRICULO_ARQUIVO = "/curriculo-molde/arquivo" + "/{idTrilha}";

    public Response cadastrar(Integer integer, CurriculoMoldeRequestDTO body) {
        return
                given()
                        .spec(AuthSpec.setup())
                        .body(body)
                        .log().all()
                        .pathParam("idTrilha", integer)
                        .when()
                        .post(PATH_CURRICULO);
    }

    public Response cadastrarSemAuth(Integer integer, CurriculoMoldeRequestDTO body) {
        return
                given()
                        .spec(InicialSpecs.setup())
                        .body(body)
                        .pathParam("idTrilha", integer)
                        .when()
                        .post(PATH_CURRICULO);
    }

    public Response cadastrarArquivo(Integer integer, File file) {
        return
                given()
                        .spec(FotoSpec.setup())
                        .pathParam("idTrilha", integer)
                        .multiPart("arquivo", file, "application/vnd.openxmlformats-officedocument.wordprocessingml.document")
                        .when()
                        .post(PATH_CURRICULO_ARQUIVO);
    }
    public Response cadastrarArquivoSemAuth(Integer integer, File file) {
        return
                given()
                        .spec(FotoSpec.setupWithoutToken())
                        .pathParam("idTrilha", integer)
                        .multiPart("arquivo", file, "application/vnd.openxmlformats-officedocument.wordprocessingml.document")
                        .when()
                        .post(PATH_CURRICULO_ARQUIVO);
    }

    public Response buscarCurriculoEspecificoPorTrilha(Integer integer) {
        return
                given()
                        .spec(AuthSpec.setup())
                        .pathParam("idTrilha", integer)
                        .when()
                        .get(PATH_CURRICULO);
    }

    public Response buscarCurriculoEspecificoPorTrilhaSemAuth(Integer integer) {
        return
                given()
                        .spec(InicialSpecs.setup())
                        .pathParam("idTrilha", integer)
                        .when()
                        .get(PATH_CURRICULO);
    }

    public Response buscarCurriculoArquivoEspecificoPorTrilha(Integer integer) {
        return
                given()
                        .spec(AuthSpec.setup())
                        .pathParam("idTrilha", integer)
                        .when()
                        .get(PATH_CURRICULO_ARQUIVO);
    }

    public Response buscarCurriculoArquivoEspecificoPorTrilhaSemAuth(Integer integer) {
        return
                given()
                        .spec(InicialSpecs.setup())
                        .pathParam("idTrilha", integer)
                        .when()
                        .get(PATH_CURRICULO_ARQUIVO);
    }

    public Response deletarCurriculoEspecificoPorTrilha(Integer integer) {
        return
                given()
                        .spec(AuthSpec.setup())
                        .pathParam("idTrilha", integer)
                        .when()
                        .delete(PATH_CURRICULO);
    }

    public Response deletarCurriculoEspecificoPorTrilhaSemAuth(Integer integer) {
        return
                given()
                        .spec(InicialSpecs.setup())
                        .pathParam("idTrilha", integer)
                        .when()
                        .delete(PATH_CURRICULO);
    }

    public Response deletarCurriculoArquivoEspecificoPorTrilha(Integer integer) {
        return
                given()
                        .spec(AuthSpec.setup())
                        .pathParam("idTrilha", integer)
                        .when()
                        .delete(PATH_CURRICULO_ARQUIVO);
    }

    public Response deletarCurriculoArquivoEspecificoPorTrilhaSemAuth(Integer integer) {
        return
                given()
                        .spec(InicialSpecs.setup())
                        .pathParam("idTrilha", integer)
                        .when()
                        .delete(PATH_CURRICULO_ARQUIVO);
    }

    public Response atualizar(Integer integer, CurriculoMoldeRequestDTO body) {
        return
                given()
                        .spec(AuthSpec.setup())
                        .body(body)
                        .pathParam("idTrilha", integer)
                        .when()
                        .post(PATH_CURRICULO);
    }

    public Response atualizarSemAuth(Integer integer, CurriculoMoldeRequestDTO body) {
        return
                given()
                        .spec(InicialSpecs.setup())
                        .body(body)
                        .pathParam("idTrilha", integer)
                        .when()
                        .post(PATH_CURRICULO);
    }

    public Response atualizarArquivo(Integer integer, File file) {
        return
                given()
                        .spec(FotoSpec.setup())
                        .pathParam("idTrilha", integer)
                        .multiPart("arquivo", file, "application/vnd.openxmlformats-officedocument.wordprocessingml.document")
                        .when()
                        .post(PATH_CURRICULO_ARQUIVO);
    }
    public Response atualizarArquivoSemAuth(Integer integer, File file) {
        return
                given()
                        .spec(FotoSpec.setupWithoutToken())
                        .pathParam("idTrilha", integer)
                        .multiPart("arquivo", file, "application/vnd.openxmlformats-officedocument.wordprocessingml.document")
                        .when()
                        .post(PATH_CURRICULO_ARQUIVO);
    }


}
