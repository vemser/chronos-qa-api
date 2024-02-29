package client;

import io.restassured.response.Response;
import model.edicao.EdicaoRequestDTO;
import specs.AuthSpec;
import specs.NoAuthSpec;

import static io.restassured.RestAssured.given;

public class EdicaoClient {

    private final static String PATH_EDICAO = "/edicao";
    private final static String PATH_EDICAO_ID = PATH_EDICAO + "/edicao/{idEdicao}";

    public Response cadastrarEdicao(EdicaoRequestDTO body) {
        return given()
                .spec(AuthSpec.setup())
                    .body(body)
                .when()
                    .post(PATH_EDICAO);
    }

    public Response cadastrarEdicaoSemToken(EdicaoRequestDTO body) {
        return given()
                    .spec(NoAuthSpec.setup())
                    .body(body)
                .when()
                    .post(PATH_EDICAO);
    }

    public Response buscarTudo() {
        return given()
                    .spec(AuthSpec.setup())
                .when()
                    .post(PATH_EDICAO);
    }

    public Response buscarPorID(Integer id) {
        return given()
                    .spec(AuthSpec.setup())
                    .pathParam("_id", id)
                .when()
                    .post(PATH_EDICAO);
    }

    public Response atualizar(Integer id, EdicaoRequestDTO body) {
        return given()
                    .spec(AuthSpec.setup())
                    .body(body)
                    .pathParam("_id", id)
                .when()
                    .put(PATH_EDICAO_ID);
    }

    public Response deletar(Integer id) {
        return given()
                    .spec(AuthSpec.setup())
                    .pathParam("idEdicao", id)
                .when()
                    .delete(PATH_EDICAO_ID);
    }
}
