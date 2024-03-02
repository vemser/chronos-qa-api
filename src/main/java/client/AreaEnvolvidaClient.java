package client;

import io.restassured.response.Response;
import lombok.Setter;
import model.AreaEnvolvidaRequestDTO;
import model.trilha.TrilhaRequestDTO;
import specs.InicialSpecs;

import static io.restassured.RestAssured.given;
@Setter
public class AreaEnvolvidaClient implements ClientInterface<Integer, AreaEnvolvidaRequestDTO> {
    private static final String PATH_AREA_ENVOLVIDA = "/area-envolvida";
    private static final String PATH_AREA_ENVOLVIDA_ID = "/area-envolvida/{id_area}";
    private static final String PATH_AREA_ENVOLVIDA_ID_CONEXAO = "/area-envolvida/{id_area}/conexao/{id_etapa}";
    private String TOKEN = null;

    @Override
    public Response cadastrar(AreaEnvolvidaRequestDTO body) {
        return
                given()
                        .spec(InicialSpecs.setup())
                        .header("Authorization", TOKEN)
                        .body(body)

                        .when().log().all()
                        .post(PATH_AREA_ENVOLVIDA);
    }

    @Override
    public Response buscarTudo() {
        return
                given()
                        .spec(InicialSpecs.setup())
                        .header("Authorization", TOKEN)
                        .when()
                        .get(PATH_AREA_ENVOLVIDA);
    }

    @Override
    public Response buscarPorID(Integer integer) {
        return null;

    }

    @Override
    public Response atualizar(Integer integer, AreaEnvolvidaRequestDTO body) {
        return
                given()
                        .spec(InicialSpecs.setup())
                        .header("Authorization", TOKEN)
                        .pathParam("id_area", integer)
                        .body(body)

                        .when().log().all()
                        .put(PATH_AREA_ENVOLVIDA_ID);
    }

    @Override
    public Response deletar(Integer integer) {
        return
                given()
                        .spec(InicialSpecs.setup())
                        .header("Authorization", TOKEN)
                        .log().all()
                        .pathParam("id_area",integer)
                        .when()
                        .delete(PATH_AREA_ENVOLVIDA_ID);
    }

    public Response conexao(Integer integer, Integer idModulo) {
        return
                given()
                        .spec(InicialSpecs.setup())
                        .header("Authorization", TOKEN)
                        .pathParam("id_area",integer)
                        .pathParam("id_etapa",idModulo)
                        .log().all()
                        .when()
                        .put(PATH_AREA_ENVOLVIDA_ID_CONEXAO);
    }

}
