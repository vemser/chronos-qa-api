package client;

import data.factory.RelatorioDataFactory;
import io.restassured.response.Response;
import lombok.Setter;
import specs.AuthSpec;

import static io.restassured.RestAssured.given;
@Setter
public class RelatorioClient {
    private static final String PATH_RELATORIO_EESTAGIARIO = "/relatorio/{_id}/quantidade-estagiarios";
    private static final String PATH_RELATORIO_TRILHA = "/relatorio/{_id}/quantidade-estagiarios/trilha";
    private static final String PATH_RELATORIO_DESLIGAMENTO = "/relatorio/{_id}/quantidade-desligamentos";
    private static final String PATH_RELATORIO_REGIAO = "/relatorio/{_id}/mapeamento-regiao";
    private static final String PATH_RELATORIO_GERAL = "/relatorio/{_id}/geral";
    private String TOKEN = null;


    public Response buscarEstagiario(Integer integer) {
        return
                 given()
                .spec(AuthSpec.setup())
                .pathParam("_id", integer)
                         .log().all()
                .when()
                .get(PATH_RELATORIO_EESTAGIARIO);
    }
    public Response buscarTrilha(Integer integer) {
        return
                given()
                        .spec(AuthSpec.setup())
                        .pathParam("_id", integer)
                        .log().all()
                        .when()
                        .get(PATH_RELATORIO_TRILHA);
    }
    public Response buscarDesligamento(Integer integer) {
        return
                given()
                        .spec(AuthSpec.setup())
                        .pathParam("_id", integer)

                        .when()
                        .get(PATH_RELATORIO_DESLIGAMENTO);
    }
    public Response buscarRegiao(Integer integer) {
        return
                given()
                        .spec(AuthSpec.setup())
                        .pathParam("_id", integer)

                        .when()
                        .get(PATH_RELATORIO_REGIAO);
    }
    public Response buscarGeral(Integer integer) {
        return
                given()
                        .spec(AuthSpec.setup())
                        .pathParam("_id", integer)
                        .log().all()
                        .when()
                        .get(PATH_RELATORIO_GERAL);
    }


}
