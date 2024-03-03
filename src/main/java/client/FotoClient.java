package client;

import data.factory.Factory;
import data.factory.FotoFactory;
import io.restassured.response.Response;
import specs.AuthSpec;
import specs.FotoSpec;
import specs.NoAuthSpec;
import utils.image.ImageTypes;

import java.io.File;

import static io.restassured.RestAssured.given;

public class FotoClient {

    public static final String FOTO_PATH = "/foo/";
    public static final String FOTO_PATH_ID = "/foto/{idFoto}";
    public static final String FOTO_EDICAO_PATH_ID = "/foto/edicao/{idEdicao}";
    public static final String FOTO_TRILHA_PATH_ID = "/foto/trilha/{idTrilha}";

    public Response cadastrarFotoComSucesso(File file, String typeImage, Integer idEdicao) {
        return given()
                .spec(FotoSpec.setup())
                    .multiPart("arquivo", file, typeImage)
                    .pathParam("idEdicao", idEdicao)
                .when()
                    .post(FOTO_PATH);
    }

    public Response cadastrarFotoComEdicaoComSucesso(File file, String typeImage, Integer idEdicao) {
        return given()
                    .spec(FotoSpec.setup())
                    .multiPart("arquivo", file, typeImage)
                    .pathParam("idEdicao", idEdicao)
                    .when()
                .post(FOTO_EDICAO_PATH_ID);
    }

    public Response cadastrarFotoComTrilhaComSucesso(File file, String typeImage, Integer idTrilha) {
        return given()
                    .spec(FotoSpec.setup())
                    .multiPart("arquivo", file, typeImage)
                    .pathParam("idTrilha", idTrilha)
                    .when()
                .post(FOTO_TRILHA_PATH_ID);
    }

    public Response cadastrarFotoComTrilhaSemToken(File file, String typeImage) {
        return given()
                .spec(FotoSpec.setupWithoutToken())
                    .multiPart("arquivo", file, typeImage)
                    .pathParam("idTrilha", -1)
                .when()
                    .post(FOTO_TRILHA_PATH_ID);
    }
    public Response cadastrarFotoComEdicaoSemToken(File file, String typeImage) {
        return given()
                .spec(FotoSpec.setupWithoutToken())
                    .multiPart("arquivo", file, typeImage)
                    .pathParam("idTrilha", -1)
                .when()
                    .post(FOTO_TRILHA_PATH_ID);
    }

    public Response cadastrarFotoComTrilhaSemID(File file, String typeImage) {
        return given()
                .spec(FotoSpec.setup())
                    .multiPart("arquivo", file, typeImage)
                    .pathParam("idTrilha", -1)
                .when()
                    .post(FOTO_TRILHA_PATH_ID);
    }
    public Response cadastrarFotoComEdicaoSemID(File file, String typeImage) {
        return given()
                .spec(FotoSpec.setup())
                    .multiPart("arquivo", file, typeImage)
                    .pathParam("idEdicao", -1)
                .when()
                    .post(FOTO_EDICAO_PATH_ID);
    }

    public Response resgatarFotoPorId(Integer id) {
        return given()
                    .spec(AuthSpec.setup())
                    .pathParam("idFoto", id)
                .when()
                    .get(FOTO_PATH_ID);
    }

    public Response alterarFoto(Integer idFoto, File file, String typeImage) {
        return given()
                    .spec(FotoSpec.setup())
                    .multiPart("arquivo", file, typeImage)
                    .pathParam("idFoto", idFoto)
                .when()
                    .put(FOTO_PATH_ID);
    }

    public Response alterarFotoSemToken() {
        return given()
                    .spec(FotoSpec.setupWithoutToken())
                    .multiPart("arquivo", FotoFactory.gerarPNG(), ImageTypes.PNG)
                    .pathParam("idFoto", 3)
                    .queryParam("nome", Factory.nome())
                .when()
                    .put(FOTO_PATH_ID);
    }

    public Response deletarFotoPorId(Integer id) {
        return given()
                .spec(AuthSpec.setup())
                .pathParam("idFoto", id)
                .when()
                .delete(FOTO_PATH_ID);
    }

    public Response deletarFotoPorIdSemToken(Integer id) {
        return given()
                    .spec(NoAuthSpec.setup())
                    .pathParam("idFoto", id)
                .when()
                    .delete(FOTO_PATH_ID);
    }
}
