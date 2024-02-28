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

    private static final String FOTO_PATH = "/foto";
    private static final String FOTO_PATH_ID = "/foto/{idFoto}";

    public Response cadastrarFotoComSucesso(File file, String typeImage, String nome) {
        return given()
                .spec(FotoSpec.setup())
                    .multiPart("arquivo", file, typeImage)
                    .queryParam("nome", nome)
                .when()
                    .post(FOTO_PATH);
    }

    public Response cadastrarFotoSemToken(File file, String typeImage, String nome) {
        return given()
                .spec(FotoSpec.setupWithoutToken())
                    .multiPart("arquivo", file, typeImage)
                    .queryParam("nome", nome)
                .when()
                    .post(FOTO_PATH);
    }

    public Response cadastrarFotoSemQuery(File file, String typeImage, String nome) {
        return given()
                .spec(FotoSpec.setup())
                    .multiPart("arquivo", file, typeImage)
                .when()
                    .post(FOTO_PATH);
    }

    public Response resgatarFotosComSucesso() {
        return given()
                .spec(AuthSpec.setup())
                    .when()
                    .get(FOTO_PATH);
    }

    public Response resgatarFotoPorId(Integer id) {
        return given()
                    .spec(AuthSpec.setup())
                    .pathParam("idFoto", id)
                .when()
                    .get(FOTO_PATH_ID);
    }

    public Response alterarFoto(Integer idFoto, File file, String typeImage, String nome) {
        return given()
                    .spec(FotoSpec.setup())
                    .multiPart("arquivo", file, typeImage)
                    .pathParam("idFoto", idFoto)
                    .queryParam("nome", nome)
                .when()
                    .put(FOTO_PATH_ID);
    }

    public Response alterarFotoSemNome() {
        return given()
                    .spec(FotoSpec.setup())
                    .multiPart("arquivo", FotoFactory.gerarPNG(), ImageTypes.PNG)
                    .pathParam("idFoto", 3)
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
