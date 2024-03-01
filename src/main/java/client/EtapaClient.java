package client;

import io.restassured.response.Response;
import model.EtapaRequestDTO;
import specs.AuthSpec;
import specs.InicialSpecs;


import static io.restassured.RestAssured.given;

public class EtapaClient {
    private static final String PATH_ETAPA_CRIAR = "/etapa" + "/{idEdicao}";

    private static final String PATH_ETAPA_ATUALIZAR = "/etapa" + "/{idEtapa}";
    private static final String PATH_ETAPA = "/etapa";
    private static final String PATH_ETAPA_POR_EDICAO = "/etapa" + "/{idEdicao}";
    private static final String PATH_DELETE_ETAPA = "/etapa" + "/{idEtapa}" + "/delete";
    private static final String PATH_DISABLE_ETAPA = "/etapa" + "/{idEtapa}" + "/disable";
    private static final String PATH_ETAPA_ID = "/etapa" + "/{idEtapa}" + "/etapa";


    public Response cadastrar(Integer integer, EtapaRequestDTO body) {
        return
                given()
                        .spec(AuthSpec.setup())
                        .pathParam("idEdicao", integer)
                        .body(body)
                        .when()
                        .post(PATH_ETAPA_CRIAR);
    }

    public Response cadastrarSemAuth(Integer integer, EtapaRequestDTO body) {
        return
                given()
                        .spec(InicialSpecs.setup())
                        .pathParam("idEdicao", integer)
                        .body(body)
                        .when()
                        .post(PATH_ETAPA_CRIAR);
    }


    public Response buscarTudo() {
        return
                given()
                        .spec(AuthSpec.setup())
                        .when()
                        .get(PATH_ETAPA);

    }

    public Response buscarTudoPorEdicao(Integer integer) {
        return
                given()
                        .spec(AuthSpec.setup())
                        .pathParam("idEdicao", integer)
                        .when()
                        .get(PATH_ETAPA_POR_EDICAO);

    }

    public Response buscarTudoPorEdicaoSemAuth(Integer integer) {
        return
                given()
                        .spec(InicialSpecs.setup())
                        .pathParam("idEdicao", integer)
                        .when()
                        .get(PATH_ETAPA_POR_EDICAO);

    }

    public Response buscarTudoSemAuth() {
        return
                given()
                        .spec(InicialSpecs.setup())
                        .when()
                        .get(PATH_ETAPA);

    }


    public Response buscarPorID(Integer integer) {
        return
                given()
                        .spec(AuthSpec.setup())
                        .pathParam("idEtapa", integer)
                        .when()
                        .get(PATH_ETAPA_ID);

    }

    public Response buscarPorIDSemAuth(Integer integer) {
        return
                given()
                        .spec(InicialSpecs.setup())
                        .pathParam("idEtapa", integer)
                        .when()
                        .get(PATH_ETAPA_ID);

    }


    public Response atualizar(Integer integer, EtapaRequestDTO body) {
        return
                given()
                        .spec(AuthSpec.setup())
                        .pathParam("idEtapa", integer)
                        .body(body)

                        .when()
                        .put(PATH_ETAPA_ATUALIZAR);
    }


    public Response atualizarSemAuth(Integer integer, EtapaRequestDTO body) {
        return
                given()
                        .spec(InicialSpecs.setup())
                        .pathParam("idEtapa", integer)
                        .body(body)

                        .when()
                        .put(PATH_ETAPA_ATUALIZAR);
    }


    public Response deletar(Integer integer) {
        return
                given()
                        .spec(AuthSpec.setup())
                        .pathParam("idEtapa",integer)
                        .when()
                        .delete(PATH_DELETE_ETAPA);
    }

    public Response deletarSemAuth(Integer integer) {
        return
                given()
                        .spec(InicialSpecs.setup())
                        .pathParam("idEtapa",integer)
                        .when()
                        .delete(PATH_DELETE_ETAPA);
    }
}
