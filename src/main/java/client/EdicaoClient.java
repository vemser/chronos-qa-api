package client;

import io.restassured.response.Response;
import model.EdicaoRequestDTO;
import specs.AuthSpec;

import static io.restassured.RestAssured.given;

public class EdicaoClient implements ClientInterface<Integer, EdicaoRequestDTO> {

    private final static String PATH_EDICAO = "/";
    private final static String PATH_EDICAO_ID = PATH_EDICAO + "/{idEdicao}";

    @Override
    public Response cadastrar(EdicaoRequestDTO body) {
        return given()
                .spec(AuthSpec.setup())
                    .body(body)
                .when()
                    .post(PATH_EDICAO);
    }

    @Override
    public Response buscarTudo() {
        return given()
                    .spec(AuthSpec.setup())
                .when()
                    .post(PATH_EDICAO);
    }

    @Override
    public Response buscarPorID(Integer id) {
        return given()
                    .spec(AuthSpec.setup())
                    .pathParam("_id", id)
                .when()
                    .post(PATH_EDICAO);
    }

    @Override
    public Response atualizar(Integer id, EdicaoRequestDTO body) {
        return given()
                    .spec(AuthSpec.setup())
                    .body(body)
                    .pathParam("_id", id)
                .when()
                    .put(PATH_EDICAO_ID);
    }

    @Override
    public Response deletar(Integer id) {
        return given()
                    .spec(AuthSpec.setup())
                    .pathParam("_id", id)
                .when()
                    .post(PATH_EDICAO_ID);
    }
}
