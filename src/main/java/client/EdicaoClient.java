package client;

import io.restassured.response.Response;
import model.edicao.EdicaoRequestDTO;
import specs.AuthSpec;
import specs.NoAuthSpec;

import static io.restassured.RestAssured.given;

public class EdicaoClient {

    private final static String PATH_EDICAO = "/edicao";
    private final static String PATH_BUSCAR_EDICAO = PATH_EDICAO + "/listar";
    private final static String PATH_EDICAO_ID = PATH_EDICAO + "/{idEdicao}";
    private final static String PATH_EDICAO_ATUALIZAR_STATUS = PATH_EDICAO + "/enable-disable/{idEdicao}";
    private final static String PATH_EDICAO_TRILHA = PATH_EDICAO + "/listar-com-trilha";
    private final static String PATH_EDICAO_VINCULAR_TRILHA = PATH_EDICAO + "/vincular-trilha/{idEdicao}/{idTrilha}";
    private final static String PATH_EDICAO_DESVINCULAR_TRILHA = PATH_EDICAO + "/desvincular-trilha/{idEdicao}/{idTrilha}";

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
                    .get(PATH_BUSCAR_EDICAO);
    }

    public Response buscarTudoSemToken() {
        return given()
                    .spec(NoAuthSpec.setup())
                .when()
                    .get(PATH_BUSCAR_EDICAO);
    }

    public Response buscarTudoComParams(Integer size) {
        return given()
                    .spec(AuthSpec.setup())
                .queryParam("size", size)
                .when()
                    .get(PATH_BUSCAR_EDICAO);
    }

    public Response buscarPorID(Integer id) {
        return given()
                    .spec(AuthSpec.setup())
                    .pathParam("idEdicao", id)
                .when()
                    .get(PATH_EDICAO_ID);
    }

    public Response atualizar(Integer id, EdicaoRequestDTO body) {
        return given()
                    .spec(AuthSpec.setup())
                    .body(body)
                    .pathParam("idEdicao", id)
                .when()
                    .put(PATH_EDICAO_ID);
    }

    public Response atualizarSemToken(Integer idEdicao) {
        return given()
                    .spec(NoAuthSpec.setup())
                    .pathParam("idEdicao", idEdicao)
                .when()
                    .put(PATH_EDICAO_ID);
    }

    public Response mudarStatus(Integer idEdicao) {
        return given()
                    .spec(AuthSpec.setup())
                .pathParam("idEdicao", idEdicao)
                    .when()
                .put(PATH_EDICAO_ATUALIZAR_STATUS);
    }

    public Response vincularTrilha( Integer idEdicao, Integer idTrilha) {
        return given()
                    .spec(AuthSpec.setup())
                .pathParam("idEdicao", idEdicao)
                .pathParam("idTrilha", idTrilha)

                    .when()
                .put(PATH_EDICAO_VINCULAR_TRILHA);
    }

    public Response desvincularTrilha( Integer idEdicao, Integer idTrilha) {
        return given()
                    .spec(AuthSpec.setup())
                    .pathParam("idEdicao", idEdicao)
                    .pathParam("idTrilha", idTrilha)
                .when()
                    .put(PATH_EDICAO_DESVINCULAR_TRILHA);
    }

    public Response deletarPorID(Integer id) {
        return given()
                    .spec(AuthSpec.setup())
                    .pathParam("idEdicao", id)
                .when()
                    .delete(PATH_EDICAO_ID);
    }

    public Response deletarSemToken(Integer id) {
        return given()
                    .spec(NoAuthSpec.setup())
                    .pathParam("idEdicao", id)
                .when()
                    .delete(PATH_EDICAO_ID);
    }


}
