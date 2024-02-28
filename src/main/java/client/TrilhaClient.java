package client;

import data.factory.Factory;
import data.factory.TokenFactory;
import io.restassured.response.Response;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import model.TrilhaRequestDTO;
import specs.AuthSpec;
import specs.InicialSpecs;


import static io.restassured.RestAssured.given;

@Setter
public class TrilhaClient implements ClientInterface<Integer, TrilhaRequestDTO>{
    private static final String PATH_TRILHA = "/trilha";
    private static final String PATH_DELETE_TRILHA = "/trilha/{_id}/delete";
    private static final String PATH_TRILHA_ID = "/trilha{_id}";
    private String TOKEN = null;


    @Override
    public Response cadastrar(TrilhaRequestDTO body) {
        return
                given()
                        .spec(InicialSpecs.setup())
                        .header("Authorization", TOKEN)
                        .body(body)
                        .log().all()

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
                        .spec(InicialSpecs.setup())
                        .header("Authorization", TOKEN)
                        .pathParam("_id", integer)
                .when()
                        .get(PATH_TRILHA_ID);

    }

    @Override
    public Response atualizar(Integer integer, TrilhaRequestDTO body) {
        return
                given()
                        .spec(InicialSpecs.setup())
                        .header("Authorization", TOKEN)
                        .pathParam("_id", integer)
                        .body(body)

                .when()
                        .put(PATH_TRILHA);
    }

    @Override
    public Response deletar(Integer integer) {
        return
                given()
                        .spec(InicialSpecs.setup())
                        .header("Authorization", TOKEN)
                        .pathParam("_id",integer)
                .when()
                        .delete(PATH_DELETE_TRILHA);
    }
}
