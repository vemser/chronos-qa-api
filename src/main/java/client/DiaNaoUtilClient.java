package client;

import io.restassured.response.Response;
import lombok.Setter;
import model.diaNaoUtil.DiaNaoUtilRequestDTO;
import specs.InicialSpecs;

import static io.restassured.RestAssured.given;
@Setter
public class DiaNaoUtilClient  implements ClientInterface<Integer, DiaNaoUtilRequestDTO>  {
    private String TOKEN = null;
    private String PATH_DIA_NAO_UTIL = "/dia-nao-util";
    private String PATH_DIA_NAO_UTIL_ID = "/dia-nao-util/{_id}";

    private String PATH_DIA_NAO_UTIL_FILTRO = "/dia-nao-util/filtro-dia-nao-util";

    @Override
    public Response cadastrar(DiaNaoUtilRequestDTO body) {
        return
                given()
                        .spec(InicialSpecs.setup())
                        .header("Authorization", TOKEN)
                        .body(body)
                        .when()
                        .post(PATH_DIA_NAO_UTIL);
    }

    @Override
    public Response buscarTudo() {
        return

                given()
                .spec(InicialSpecs.setup())
                .header("Authorization", TOKEN)
                .when()
                .get(PATH_DIA_NAO_UTIL);
    }


    public Response buscarPorFiltro() {
        return
                given()
                .spec(InicialSpecs.setup())
                .header("Authorization", TOKEN)
                .when()
                .get(PATH_DIA_NAO_UTIL_FILTRO);
    }


    @Override
    public Response buscarPorID(Integer integer) {
        return null;
    }
    @Override
    public Response atualizar(Integer integer, DiaNaoUtilRequestDTO body) {
        return
                given()
                .spec(InicialSpecs.setup())
                .header("Authorization", TOKEN)
                .pathParam("_id", integer)
                .body(body)

                .when()
                .put(PATH_DIA_NAO_UTIL_ID);
    }



    @Override
    public Response deletar(Integer integer) {
        return given()
                .spec(InicialSpecs.setup())
                .header("Authorization", TOKEN)
                .pathParam("_id",integer)
                .when()
                .delete(PATH_DIA_NAO_UTIL_ID);
    }
}
