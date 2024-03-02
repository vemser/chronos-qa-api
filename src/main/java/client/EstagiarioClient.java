package client;


import io.restassured.response.Response;
import model.EstagiarioRequestDTO;
import specs.AuthSpec;
import specs.InicialSpecs;
import specs.NoAuthSpec;

import static io.restassured.RestAssured.given;


public class EstagiarioClient implements ClientInterface<Integer, EstagiarioRequestDTO> {
        private static final String PATH_ESTAGIARIO = "/estagiario";

        private static final String PATH_ID_ESTAGIARIO = "/estagiario" + "/{idEstagiario}";

        private static final String PATH_DELETE_ESTAGIARIO = "/estagiario" + "/{idEstagiario}" + "/delete";

        private static final String PATH_DISABLE_ESTAGIARIO = "/estagiario" + "/{idEstagiario}}" + "/disable";

        private String TOKEN;

        public Response cadastrar(EstagiarioRequestDTO body) {
            return given()
                        .spec(AuthSpec.setup())
                        .body(body)
                        .when()
                    .post(PATH_ESTAGIARIO);
        }
        public void setToken(String token) {
            this.TOKEN = token;
        }

        public Response cadastrarSemToken(EstagiarioRequestDTO body) {
            return given()
                        .spec(NoAuthSpec.setup())
                        .body(body)
                        .when()
                    .post(PATH_ESTAGIARIO);
        }


        public Response buscarTudo() {
            return
                    given()
                            .spec(AuthSpec.setup())
                            .when()
                            .post(PATH_ESTAGIARIO);
        }

    @Override
    public Response buscarPorID(Integer integer) {
        return null;
    }

    public Response buscarTudoSemToken() {
            return
                given()
                        .spec(InicialSpecs.setup())
                        .when()
                        .post(PATH_ESTAGIARIO);
        }

    public Response listarPorId(Integer idEstagiario) {
        return given()
                .spec(AuthSpec.setup())
                .pathParam("idEstagiario", idEstagiario)
                .when()
                .get(PATH_ID_ESTAGIARIO);
    }
        public Response listarPorID(Integer idEstagiario) {
            return
                    given()
                            .spec(AuthSpec.setup())
                            .pathParam("idEstagiario", idEstagiario)
                            .when()
                            .get(PATH_ID_ESTAGIARIO);
        }

        public Response buscarPorIDSemToken(Integer integer) {
            return
                    given()
                            .spec(InicialSpecs.setup())
                            .pathParam("idEstagiario", integer)
                            .when()
                            .get(PATH_ID_ESTAGIARIO);
        }

        public Response atualizar(Integer integer, EstagiarioRequestDTO body) {
            return
                    given()
                            .spec(AuthSpec.setup())
                            .pathParam("idEstagiario", integer)
                            .body(body)
                            .when()
                            .put(PATH_ID_ESTAGIARIO);
        }

        public Response atualizarSemToken(Integer integer, EstagiarioRequestDTO body) {
            return
                    given()
                            .spec(InicialSpecs.setup())
                            .pathParam("idEstagiario", integer)
                            .body(body)
                            .when()
                            .put(PATH_ID_ESTAGIARIO);
        }

        public Response deletar(Integer integer) {
            return
                    given()
                            .spec(AuthSpec.setup())
                            .pathParam("idEstagiario",integer)
                            .when()
                            .delete(PATH_DELETE_ESTAGIARIO);
        }

        public Response desabilitar(Integer integer) {
            return
                    given()
                            .spec(AuthSpec.setup())
                            .pathParam("idEstagiario",integer)
                            .when()
                            .delete(PATH_DISABLE_ESTAGIARIO);
        }

        public Response desabilitarSemToken(Integer integer) {
            return
                    given()
                            .spec(InicialSpecs.setup())
                            .pathParam("idEstagiario",integer)
                            .when()
                            .delete(PATH_DISABLE_ESTAGIARIO);
        }
    }

