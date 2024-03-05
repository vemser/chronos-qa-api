package client;

import io.restassured.response.Response;
import lombok.Setter;
import model.trilha.TrilhaRequestDTO;
import specs.AuthSpec;


import static io.restassured.RestAssured.given;

@Setter
public class TrilhaClient implements ClientInterface<Integer, TrilhaRequestDTO>{

    private static final String PATH_TRILHA = "/trilha";
    private static final String PATH_DELETE_TRILHA = "/trilha/{_id}/delete";
    private static final String PATH_TRILHA_ID = "/trilha/{_id}";
    private static final String PATH_TRILHA_MODULO = "/trilha/listar-com-modulo";
    private static final String PATH_VINCULAR_TRILHA ="/trilha/vincular-modulo/{_id}/{_idModulo}";
    private static final String PATH_DESVINCULAR_TRILHA ="/trilha/desvincular-trilha/{_id}/{_idModulo}";

    private String TOKEN = null;


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
        return
                given()
                        .spec(AuthSpec.setup())
                .when()
                        .get(PATH_TRILHA_MODULO);
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
                        .put(PATH_TRILHA_ID);
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

    public Response vincularModulo(Integer integer, Integer idModulo) {
        return
                given()
                        .spec(AuthSpec.setup())
                        .pathParam("_id",integer)
                        .pathParam("_idModulo",idModulo)
                .when()
                        .put(PATH_VINCULAR_TRILHA);
    }
    public Response desvincularModulo(Integer integer, Integer idModulo) {
        return
                given()
                        .spec(AuthSpec.setup())
                        .pathParam("_id",integer)
                        .pathParam("_idModulo",idModulo)
                        .when()
                        .put(PATH_DESVINCULAR_TRILHA);
                    
    }
}
