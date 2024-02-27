package client;

import io.restassured.response.Response;
import model.TrilhaRequestDTO;
import specs.AuthSpec;


import static io.restassured.RestAssured.given;

public class TrilhaClient implements ClientInterface<Integer, TrilhaRequestDTO>{
    private static final String PATH_TRILHA = "/";
    private static final String PATH_DELETE_TRILHA = "/";
    private static final String PATH_TRILHA_ID = "/";

    @Override
    public Response cadastrar(TrilhaRequestDTO body) {
        return
                given()
                        .spec(AuthSpec.setup())
                        .body(body)

                .when()
                        .post(PATH_TRILHA);
    }

    @Override
    public Response buscarTudo() {
        return null;
    }

    @Override
    public Response buscarPorID(Integer integer) {
        return
                given()
                        .spec(AuthSpec.setup())
                        .pathParam("_id", integer)
                .when()
                        .get(PATH_TRILHA_ID);

    }

    @Override
    public Response atualizar(Integer integer, TrilhaRequestDTO body) {
        return
                given()
                        .spec(AuthSpec.setup())
                        .pathParam("_id", integer)
                        .body(body)

                .when()
                        .post(PATH_TRILHA);
    }

    @Override
    public Response deletar(Integer integer) {
        return
                given()
                        .spec(AuthSpec.setup())
                        .pathParam("_id",integer)
                .when()
                        .delete(PATH_DELETE_TRILHA);
    }
}
