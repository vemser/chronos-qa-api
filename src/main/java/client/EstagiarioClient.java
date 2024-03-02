package client;


import data.factory.EdicaoFactory;
import data.factory.EstagiarioFactory;
import data.factory.TrilhaDataFactory;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.EstagiarioRequestDTO;
import model.edicao.EdicaoResponseDTO;
import model.estagiario.EstagiarioResponseDTO;
import model.trilha.TrilhaResponseDTO;
import org.apache.http.HttpStatus;
import specs.AuthSpec;
import specs.InicialSpecs;
import specs.NoAuthSpec;

import static io.restassured.RestAssured.given;


public class EstagiarioClient {
        private static final String PATH_ESTAGIARIO = "/estagiario";

        private static final String PATH_ID_ESTAGIARIO = "/estagiario" + "/{idEstagiario}";

        private static final String PATH_DELETE_ESTAGIARIO = "/estagiario" + "/{idEstagiario}" + "/delete";

        private static final String PATH_DISABLE_ESTAGIARIO = "/estagiario" + "/{idEstagiario}}" + "/disable";

        private String TOKEN;

        private final TrilhaClient trilhaClient = new TrilhaClient();
        private final EdicaoClient edicaoClient = new EdicaoClient();

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
            return given()
                        .spec(AuthSpec.setup())
                        .when()
                    .get(PATH_ESTAGIARIO);
        }

    public Response buscarPorID(Integer integer) {
        return null;
    }

    public Response criarMassaDeDados() {
        Integer idEdicao = edicaoClient.cadastrarEdicao(EdicaoFactory.edicaoValida())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(EdicaoResponseDTO.class).getIdEdicao();

        Integer idTrilha = trilhaClient.cadastrar(TrilhaDataFactory.trilhaComTodosOsCampos())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(TrilhaResponseDTO.class).getIdTrilha();

        // A2: VINCULAR TRILHAS
        edicaoClient.vincularTrilha(idEdicao, idTrilha)
                .then()
                .statusCode(HttpStatus.SC_OK);

        // A3: CRIAR ESTAGIÁRIO

        EstagiarioRequestDTO bodyRequest = EstagiarioFactory.estagiarioValido(idTrilha, idEdicao);

        return cadastrar(bodyRequest);
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

