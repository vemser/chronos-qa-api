package client;

import io.restassured.response.Response;
import model.modulo.ModuloRequestDTO;
import specs.AuthSpec;
import specs.InicialSpecs;


import static io.restassured.RestAssured.given;

public class ModuloClient {
    private static final String PATH_MODULO = "/modulo";
    private static final String PATH_DELETE_MODULO = "/modulo" + "/{idModulo}" + "/delete";
    private static final String PATH_DISABLE_MODULO = "/modulo" + "/{idModulo}" + "/disable";
    private static final String PATH_MODULO_ID = "/modulo" + "/{idModulo}";


    public Response cadastrar(ModuloRequestDTO body) {
        return
                given()
                        .spec(AuthSpec.setup())
                        .body(body)
                        .when()
                        .post(PATH_MODULO);
    }

    public Response cadastrarSemAuth(ModuloRequestDTO body) {
        return
                given()
                        .spec(InicialSpecs.setup())
                        .body(body)
                        .when()
                        .post(PATH_MODULO);
    }


    public Response buscarTudo() {
        return
                given()
                        .spec(AuthSpec.setup())
                        .when()
                        .get(PATH_MODULO);

    }

    public Response buscarTudoSemAuth() {
        return
                given()
                        .spec(InicialSpecs.setup())
                        .when()
                        .get(PATH_MODULO);

    }


    public Response buscarPorID(Integer integer) {
        return
                given()
                        .spec(AuthSpec.setup())
                        .pathParam("idModulo", integer)
                        .when()
                        .get(PATH_MODULO_ID);

    }

    public Response buscarPorIDSemAuth(Integer integer) {
        return
                given()
                        .spec(InicialSpecs.setup())
                        .pathParam("idModulo", integer)
                        .when()
                        .get(PATH_MODULO_ID);

    }


    public Response atualizar(Integer integer, ModuloRequestDTO body) {
        return
                given()
                        .spec(AuthSpec.setup())
                        .pathParam("idModulo", integer)
                        .body(body)

                        .when()
                        .put(PATH_MODULO_ID);
    }


    public Response atualizarSemAuth(Integer integer, ModuloRequestDTO body) {
        return
                given()
                        .spec(InicialSpecs.setup())
                        .pathParam("idModulo", integer)
                        .body(body)

                        .when()
                        .put(PATH_MODULO_ID);
    }


    public Response deletar(Integer integer) {
        return
                given()
                        .spec(AuthSpec.setup())
                        .pathParam("idModulo",integer)
                        .when()
                        .delete(PATH_DELETE_MODULO);
    }

    public Response desabilitar(Integer integer) {
        return
                given()
                        .spec(AuthSpec.setup())
                        .pathParam("idModulo",integer)
                        .when()
                        .delete(PATH_DISABLE_MODULO);
    }

    public Response desabilitarSemAuth(Integer integer) {
        return
                given()
                        .spec(InicialSpecs.setup())
                        .pathParam("idModulo",integer)
                        .when()
                        .delete(PATH_DISABLE_MODULO);
    }
}
